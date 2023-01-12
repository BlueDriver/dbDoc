package db.doc.utils;

/**
 * dbDoc
 * db.doc.utils
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2020/1/16 19:26 星期四
 */
public class VarNameUtils {
    private static final String UNDER_LINE = "_";

    public static String BIG_CAMEL_CASE$BigCamelCase(String varName) {
        String r = BIG_CAMEL_CASE$littleCamelCase(varName);
        return upperFirstChar(r);
    }


    public static String BIG_CAMEL_CASE$littleCamelCase(String varName) {
        String[] words = varName.toLowerCase().split(UNDER_LINE);
        StringBuilder sb = new StringBuilder();
        for (int i = 0, t = words.length; i < t; i++) {
            if (i == 0) {//the first word
                sb.append(words[i]);
            } else {
                sb.append(upperFirstChar(words[i]));
            }
        }
        return sb.toString();
    }


    /**
     * 将字符串首字母转成大写
     *
     * @param s
     * @return 首字母大写的字符串
     */
    public static String upperFirstChar(String s) {
        if (s.length() >= 2) {
            if (Character.isUpperCase(s.charAt(0)))
                return s;
            else
                return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        } else if (s.length() == 1) {
            return s.toUpperCase();
        }
        return "";
    }

    /**
     * 将字符串首字母转成小写
     *
     * @param s
     * @return 首字母小写的字符串
     */
    public static String lowerFirstChar(String s) {
        if (s.length() >= 2) {
            if (Character.isLowerCase(s.charAt(0)))
                return s;
            else
                return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        } else if (s.length() == 1) {
            return s.toLowerCase();
        }
        return "";
    }

    private VarNameUtils() {
    }
}
