package db.doc.bo;

import com.intellij.database.model.DasColumn;
import com.intellij.database.model.DasTable;
import com.intellij.database.model.DataType;
import com.intellij.database.util.DasUtil;

/**
 * dbDoc
 * db.doc.bo
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/23 10:20 星期一
 */
public class ColumnData {
    private int index;
    private boolean isPrimary;
    private String name;
    private String comment;
    private DataType dataType;
    private boolean notNull;
    private String defaultValue;
    private String remark;
    private DasTable table;

    public ColumnData(int index, DasColumn column) {
        this(column);
        this.index = index;
    }

    public ColumnData(DasColumn column) {
        this.isPrimary = DasUtil.isPrimary(column);
        this.name = column.getName();
        this.comment = column.getComment();
        this.dataType = column.getDataType();
        this.notNull = column.isNotNull();
        this.defaultValue = column.getDefault();
        this.remark = "";
        this.table = column.getTable();
    }


    public ColumnData() {

    }


    public DasTable getTable() {
        return table;
    }

    public void setTable(DasTable table) {
        this.table = table;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean getNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ColumnData{" +
                "index='" + index + '\'' +
                ", isPrimary=" + isPrimary +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", dataType=" + dataType +
                ", notNull=" + notNull +
                ", defaultValue='" + defaultValue + '\'' +
                ", remark='" + remark + '\'' +
                ", table=" + table +
                '}';
    }
}
