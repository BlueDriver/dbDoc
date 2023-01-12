package db.doc.utils;

import com.intellij.database.model.DasColumn;
import com.intellij.database.model.DasTableKey;
import com.intellij.database.model.DasTypedObject;
import com.intellij.database.model.MultiRef;
import com.intellij.database.psi.DbTable;
import com.intellij.database.util.DasUtil;
import com.intellij.util.containers.JBIterable;

/**
 * dbDoc
 * db.doc.utils
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/21 20:46 星期六
 */
public class DbTableUtils {

    public static void getTableInfo(DbTable dbTable) {
        //数据库类型

        //库名

        //表名
        System.out.println("getName: " + dbTable.getName());
        //注释
        System.out.println("getComment: " + dbTable.getComment());


    }

    public static void getColumns(DbTable dbTable) {
        JBIterable<? extends DasColumn> columns = DasUtil.getColumns(dbTable);
        //主键
        DasTableKey primaryKey =  DasUtil.getPrimaryKey(dbTable);
        MultiRef<? extends DasTypedObject> ref =  primaryKey.getColumnsRef();
        System.out.println("pri --------------");
        for (String s : ref.names()) {
            System.out.println(s);
        }
        System.out.println("pri end --------------");
        //列名
        System.out.println("column --------------------------------");
        for (DasColumn column : columns) {
            System.out.println(column.getName());
            System.out.println(column.getComment());
            System.out.println(column.getDataType());
            System.out.println(column.isNotNull());
            System.out.println(column.getDefault());
        }
        System.out.println("column end ---------------");
        //说明

        //类型

        //非空

        //备注




    }

    private DbTableUtils() {
    }
}
