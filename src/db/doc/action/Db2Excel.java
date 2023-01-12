// package db.doc.action;
//
// import com.intellij.database.console.RunSqlScriptAction;
// import com.intellij.database.dataSource.LocalDataSource;
// import com.intellij.database.model.DasColumn;
// import com.intellij.database.psi.DbElement;
// import com.intellij.database.psi.DbTable;
// import com.intellij.database.util.DasUtil;
// import com.intellij.database.util.DbImplUtil;
// import com.intellij.database.util.ObjectPath;
// import com.intellij.database.view.DatabaseView;
// import com.intellij.openapi.actionSystem.AnAction;
// import com.intellij.openapi.actionSystem.AnActionEvent;
// import com.intellij.openapi.actionSystem.CommonDataKeys;
// import com.intellij.openapi.actionSystem.LangDataKeys;
// import com.intellij.openapi.fileChooser.FileChooser;
// import com.intellij.openapi.fileChooser.FileChooserDescriptor;
// import com.intellij.openapi.fileEditor.FileDocumentManager;
// import com.intellij.openapi.project.Project;
// import com.intellij.openapi.util.Conditions;
// import com.intellij.openapi.util.Pair;
// import com.intellij.psi.PsiElement;
// import com.intellij.util.containers.ContainerUtil;
// import com.intellij.util.containers.JBIterable;
// import db.doc.utils.ClipBoardTools;
// import org.jetbrains.annotations.NotNull;
//
// import java.util.List;
//
// /**
//  * dbDoc
//  * db.doc.action
//  *
//  * @author BlueDriver
//  * @email cpwu@foxmail.com
//  * @date 2020/7/1 10:24 周三
//  */
// public class Db2Excel extends AnAction {
//
//     private void doWork(DbTable table, AnActionEvent e) {
//         StringBuilder sb = new StringBuilder();
//         for (DasColumn column : DasUtil.getColumns(table)) {
//             String name = column.getName();
//             String comment = column.getComment();
//             boolean notNull = column.isNotNull();
//             sb.append(notNull ? "*" : "").append(comment).append("\t").append(name).append("\n");
//         }
//         ClipBoardTools.setClipBoard(sb.toString().trim());
//     }
//
//     @Override
//     public void actionPerformed(@NotNull AnActionEvent e) {
//         PsiElement[] psiElements = e.getData(LangDataKeys.PSI_ELEMENT_ARRAY);
//         if (psiElements == null) {
//             return;
//         }
//
//         for (PsiElement psiElement : psiElements) {
//             if (psiElement instanceof DbTable) {
//                 doWork((DbTable) psiElement, e);
//             }
//         }
//
//
//         //region do do do TODO: 2021/9/28 15:43
//         JBIterable<DbElement> elements = DatabaseView.getSelectedElements(e.getDataContext(), true);
//         Project project = e.getRequiredData(CommonDataKeys.PROJECT);
//         FileDocumentManager.getInstance().saveAllDocuments();
//
//
//         FileChooserDescriptor descriptor = new FileChooserDescriptor(true,
//                 false, false, false, false, true);
//
//
//         FileChooser.chooseFiles(descriptor, project, null, (files) -> {
//             List<Pair<LocalDataSource, ObjectPath>> paths = elements.map((it) -> {
//                 LocalDataSource dataSource = DbImplUtil.getLocalDataSource(it.getDataSource());
//                 Object delegate = it.getDelegate();
//                 return RunSqlScriptAction.makeTargetPair(dataSource, delegate);
//             }).filter(Conditions.notNull()).toList();
//
//             List<Pair<LocalDataSource, ObjectPath>> result = JBIterable.from(paths).filter((it) -> {
//                 return !ContainerUtil.exists(paths, (pair) -> {
//                     return true;
//                 });
//             }).toList();
//             RunSqlScriptAction.perform(project, JBIterable.from(files), result);
//         });
//         //endregion
//
//     }
//
//
//     @Override
//     public void update(AnActionEvent e) {
//         PsiElement[] psiElements = e.getData(LangDataKeys.PSI_ELEMENT_ARRAY);
//         if (psiElements == null || psiElements.length != 1) {
//             e.getPresentation().setEnabledAndVisible(false);
//             return;
//         }
//     }
// }
