package db.doc.utils;

import java.io.*;

/**
 * dbDoc
 * db.doc.utils
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/27 9:18 星期五
 */
public class FileUtils {
    /**
     * 向指定路径写入文件
     *
     * @param absolutePath
     * @param content
     */
    public static boolean createFile(String absolutePath, String content) {
        return createFile(absolutePath, content, false);
    }

    /**
     * 向指定路径写入文件
     *
     * @param absolutePath
     * @param content
     * @param isAppend
     * @return true is success
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean createFile(String absolutePath, String content, boolean isAppend) {
        try {
            File file = new File(absolutePath);
            if (!file.exists()) {
                //file not exits
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), isAppend);//if true append
            OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            BufferedWriter bw = new BufferedWriter(writerStream);
            bw.write(content);
            bw.close();
            writerStream.close();
            fileWriter.close();
            return true;
        } catch (Exception e) {
            DialogUtils.showError(e.getMessage());
        }
        return false;
    }

    private FileUtils() {
    }
}
