package db.doc.utils;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * dbDoc
 * db.doc.utils
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/27 9:24 星期五
 */
public class NotifyUtils {
    /**
     * 通知栏文字消息通知
     */
    public static void notifyBarMsg(@NotNull String content) {
        Notification notification = new Notification(
                DialogUtils.DEFAULT_TITLE,
                DialogUtils.DEFAULT_TITLE,
                content,
                NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }


    public static void notifyError(@NotNull Project project, @NotNull String content) {
        notifyMsg(project, content, NotificationType.ERROR);
    }

    public static void notifyWarning(@NotNull Project project, @NotNull String content) {
        notifyMsg(project, content, NotificationType.WARNING);
    }

    public static void notifyInfo(@NotNull Project project, @NotNull String content) {
        notifyMsg(project, content, NotificationType.INFORMATION);
    }

    /**
     * 右下角消息通知
     *
     * @param project 没有这个的话，仅仅是通知栏有文字，无Balloon
     */
    public static void notifyMsg(@NotNull Project project, @NotNull String content, @NotNull NotificationType type) {
        Notification notification = new Notification(
                DialogUtils.DEFAULT_TITLE,
                DialogUtils.DEFAULT_TITLE,
                content,
                type);
        Notifications.Bus.notify(notification, project);
    }

    private NotifyUtils() {
    }
}
