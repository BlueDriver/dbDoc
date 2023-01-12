package db.doc.action;

import com.intellij.database.psi.DbTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiElement;
import db.doc.utils.DialogUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * dbDoc
 * db.doc.action
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2020/9/13 10:36 周日
 */
public class TestAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
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
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        PsiElement[] psiElements = e.getData(LangDataKeys.PSI_ELEMENT_ARRAY);
        if (psiElements == null || psiElements.length == 0) {
            e.getPresentation().setEnabledAndVisible(false);
            return;
        }


    }
}
