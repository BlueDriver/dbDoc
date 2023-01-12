package db.doc.action;

import com.intellij.database.model.DasColumn;
import com.intellij.database.psi.DbTable;
import com.intellij.database.util.DasUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiElement;
import db.doc.bo.ColumnData;
import db.doc.utils.ClipBoardTools;
import db.doc.utils.NotifyUtils;
import db.doc.utils.VarNameUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * dbDoc
 * db.doc.action
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/19 11:48 星期四
 */
public class DocSetterAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiElement[] psiElements = e.getData(LangDataKeys.PSI_ELEMENT_ARRAY);
        if (psiElements == null) {
            return;
        }

        for (PsiElement psiElement : psiElements) {
            if (psiElement instanceof DbTable) {
                setter((DbTable) psiElement, e);
            }
        }
    }


    private void setter(@NotNull DbTable table, @NotNull AnActionEvent e) {
        StringBuilder sb = new StringBuilder();
        String tableName = table.getName();
        String tableComment = Optional.ofNullable(table.getComment()).orElse("");
        String daoObjName = VarNameUtils.BIG_CAMEL_CASE$littleCamelCase(tableName);

        sb.append("//").append(tableName).append(" ").append(tableComment).append("\n");
        String clazzName = VarNameUtils.BIG_CAMEL_CASE$BigCamelCase(tableName);
        String objName = VarNameUtils.BIG_CAMEL_CASE$littleCamelCase(tableName);
        sb.append(clazzName).append(" ").append(objName).append(" = new ").append(clazzName).append("();\n");

        int i = 0;
        for (DasColumn dasColumn : DasUtil.getColumns(table)) {
            i++;
            ColumnData columnData = new ColumnData(i, dasColumn);
            String columnName = columnData.getName();
            String field = VarNameUtils.BIG_CAMEL_CASE$littleCamelCase(columnName);

            sb.append(daoObjName).append(".set").append(VarNameUtils.upperFirstChar(field)).append("(" +
                    (columnData.getNotNull() ? "" : "null") + " );")
                    .append("//")
                    .append(columnData.getNotNull() ? "notNull" : "").append(" ")
                    .append(Optional.ofNullable(columnData.getComment()).orElse(""))
                    .append(" default: ")
                    .append(Optional.ofNullable(columnData.getDefaultValue()).orElse("no default value"))
                    .append("\n");
        }

        ClipBoardTools.setClipBoard(sb.toString());
        NotifyUtils.notifyInfo(e.getProject(), "Copy codes OK, use Ctrl+V to paste");
    }


    @Override
    public void update(AnActionEvent e) {
        PsiElement[] psiElements = e.getData(LangDataKeys.PSI_ELEMENT_ARRAY);
        if (psiElements == null || psiElements.length != 1) {
            e.getPresentation().setEnabledAndVisible(false);
            return;
        }
    }
}
