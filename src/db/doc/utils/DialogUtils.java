package db.doc.utils;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * dbDoc
 * db.doc.utils
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/27 9:19 星期五
 */
public class DialogUtils {
    public static final String DEFAULT_TITLE = "dbDoc";

    public static void showInfo(@NotNull String message) {
        ApplicationManager.getApplication().invokeLater(() -> Messages.showInfoMessage(message, DEFAULT_TITLE));
    }

    public static void showError(@NotNull String message) {
        ApplicationManager.getApplication().invokeLater(() -> Messages.showErrorDialog(message, DEFAULT_TITLE));
    }

    public static void showInfo(@NotNull String message, @NotNull String title) {
        ApplicationManager.getApplication().invokeLater(() -> Messages.showInfoMessage(message, title));
    }

    public static void showError(@NotNull String message, @NotNull String title) {
        ApplicationManager.getApplication().invokeLater(() -> Messages.showErrorDialog(message, title));
    }

    private DialogUtils() {
    }
}
