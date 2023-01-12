package db.doc.utils;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * ingTools
 * 剪切板操作
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/01/05 11:21 Saturday
 */
public class ClipBoardTools {

    /**
     * 设置剪切板内容
     *
     * @param content: the content that u want to copy
     * @return
     */
    public static void setClipBoard(@NotNull String content) {
        //获取系统剪切板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //构建String数据类型
        StringSelection selection = new StringSelection(content);
        //添加文本到系统剪切板
        clipboard.setContents(selection, null);
    }

    private ClipBoardTools() {
    }
}
