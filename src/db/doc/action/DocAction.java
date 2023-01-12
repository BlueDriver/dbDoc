package db.doc.action;

import com.intellij.database.access.ConnectionProvider;
import com.intellij.database.dataSource.connection.DGDepartment;
import com.intellij.database.dialects.DatabaseDialectEx;
import com.intellij.database.editor.DatabaseEditorHelper;
import com.intellij.database.psi.DbTable;
import com.intellij.database.util.DbImplUtil;
import com.intellij.ide.actions.RevealFileAction;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationAction;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import db.doc.bo.MarkDownTable;
import db.doc.utils.ClipBoardTools;
import db.doc.utils.DialogUtils;
import db.doc.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * dbDoc
 * db.doc.action
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/19 11:48 星期四
 */
public class DocAction extends AnAction {
    private static final Logger logger = Logger.getInstance(DocAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
//        final Editor editor = e.getData(PlatformDataKeys.EDITOR);
//        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
//        int offset = editor.getCaretModel().getOffset();
//        PsiElement ele = psiFile.findElementAt(offset);
//        System.out.println(ele.getClass() + ": " + ele.getText());

        PsiElement[] psiElements = e.getData(LangDataKeys.PSI_ELEMENT_ARRAY);
        if (psiElements == null) {
            return;
        }
        List<DbTable> tableList = new ArrayList<>();
        for (PsiElement psiElement : psiElements) {
            if (psiElement instanceof DbTable) {
                tableList.add((DbTable) psiElement);
            }
        }
        if (tableList.isEmpty()) {
            DialogUtils.showError("No table(s) selected!");
            return;
        }
        createDoc(tableList, e);
    }

    private void ddl(DbTable dbTable) {
        // TODO: 2021/9/23 11:05
//        DatabaseEditorHelper.generateDefinition(dbTable, null);

        StringBuilder def = new StringBuilder();
//
        StringBuilder ss = DatabaseEditorHelper.generateDefinition(dbTable, def);


//        final DatabaseDialectEx dialect = DbImplUtil.getDatabaseDialect(dbTable);
//
//        final ConnectionProvider provider = ConnectionProvider.forElement(dbTable, DGDepartment.INTROSPECTION);
//
//
//        if (dialect.supportsTableDefinition()) {
//            try {
//                if (!provider.acquire()) {
//                    return;
//                }
//
//                String sql = dialect.tryToLoadTableDefinition(dbTable, provider.getConnection());
//                if (!StringUtil.isEmptyOrSpaces(sql)) {
//                   logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                   logger.error(sql);
//                   logger.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//                }
//            } finally {
//                provider.release();
//            }
//
//        }
//
//        if (provider.getConnection() == null) {
//            logger.info("connection: " + null);
//            return;
//        }
//
//        String sql = null;
//        try {
//            sql = dialect.tryToLoadTableDefinition(dbTable, provider.getConnection());
//        } catch (Exception e) {
//            logger.warn("get sql exception", e);
//        }

        logger.warn("");
        logger.info(ss.toString());
        ClipBoardTools.setClipBoard(ss.toString());
        logger.warn("--------------------------");
//        logger.warn(sql);
        logger.info(def.toString());
        logger.warn("##########################");
    }

    private void createDoc(@NotNull List<DbTable> tableList, @NotNull AnActionEvent e) {
        //region doc head
        StringBuilder sb = new StringBuilder();
        //DbDataSource ds = tableList.get(0).getDataSource();
        //ds.getName();//db name
        sb.append("### Database document").append("\n")
                .append("> ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n");
        //endregion


        //region tables
        for (DbTable dbTable : tableList) {
            ddl(dbTable);


//            System.out.println(ss);
//            System.out.println("--------------------------");
//            System.out.println(def);
//            System.out.println("##########################");
            sb.append("#### ").append(dbTable.getName()).append(" ")
                    .append(Optional.ofNullable(dbTable.getComment()).orElse(" ")).append("\n");
            MarkDownTable table = new MarkDownTable(dbTable);
            sb.append(table.output());
            sb.append("\n");
            //System.out.println(table.output());
        }
        //endregion
        String filePath = e.getProject().getPresentableUrl() + System.getProperty("file.separator") + "dbDoc.md";
        boolean result = FileUtils.createFile(filePath, sb.toString());
        if (result) {
            Notification notification = new Notification(
                    DialogUtils.DEFAULT_TITLE,
                    DialogUtils.DEFAULT_TITLE,
                    "Generate successful!",
                    NotificationType.INFORMATION);

            VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(filePath);
            if (virtualFile != null) {
                notification.addAction(new NotificationAction("Open") {
                    @Override
                    public void actionPerformed(@NotNull AnActionEvent anActionEvent, @NotNull Notification notification) {
                        notification.hideBalloon();
                        notification.expire();
                        FileEditorManager.getInstance(e.getProject()).openFile(virtualFile, true);
                    }
                });
                notification.addAction(new NotificationAction("Show in Explorer") {
                    @Override
                    public void actionPerformed(@NotNull AnActionEvent anActionEvent, @NotNull Notification notification) {
                        notification.hideBalloon();
                        notification.expire();
                        RevealFileAction.openFile(new File(filePath));
                    }
                });
            }
            Notifications.Bus.notify(notification, e.getProject());
        }
    }


    @Override
    public void update(AnActionEvent e) {
        PsiElement[] psiElements = e.getData(LangDataKeys.PSI_ELEMENT_ARRAY);
        if (psiElements == null || psiElements.length == 0) {
            e.getPresentation().setEnabledAndVisible(false);
            return;
        }
    }
}
