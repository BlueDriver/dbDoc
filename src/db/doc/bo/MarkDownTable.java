package db.doc.bo;

import com.intellij.database.model.DasColumn;
import com.intellij.database.psi.DbTable;
import com.intellij.database.util.DasUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * dbDoc
 * db.doc.bo
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/24 9:56 星期二
 */
public class MarkDownTable {
    private static final String DEFAULT_TABLE_HEADER = "NO | KEY | COLUMN | COMMENT | DATA_TYPE | NOTNULL | REMARK ";
    private static final String DEFAULT_SEPARATOR_ROW = ":---: | :---: | --- | --- | --- | :---: | --- ";

    private List<String> rows;

    public MarkDownTable(DbTable dbTable) {
        MarkDownTable table = createWithDefaultHeader();
        int i = 0;
        for (DasColumn dasColumn : DasUtil.getColumns(dbTable)) {
            i++;
            ColumnData columnData = new ColumnData(i, dasColumn);
            table.addRow(columnData);
        }
        this.rows = table.getRows();
    }

    public String output() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, t = rows.size(); i < t; i++) {
            sb.append(rows.get(i)).append(i != t - 1 ? "\n" : "");
        }
        return sb.toString();
    }

    public static MarkDownTable createWithDefaultHeader() {
        MarkDownTable table = new MarkDownTable();
        table.addRow(DEFAULT_TABLE_HEADER)
                .addRow(DEFAULT_SEPARATOR_ROW);
        return table;
    }


    public MarkDownTable addRow(ColumnData column) {
        //2 | PRI | GENDER | 性别 | VARCHAR(8) | N | G02011
        StringBuilder sb = new StringBuilder();
        sb
                .append(column.getIndex()).append("|")
                .append(column.getPrimary() ? "PRI" : " ").append("|")
                .append(column.getName()).append("|")
                .append(Optional.ofNullable(column.getComment()).orElse(" ")).append("|")
                .append(column.getDataType().toString().toUpperCase()).append("|")
                .append(column.getNotNull() ? "Y" : "N").append("|")
                .append(column.getRemark());
        this.addRow(sb.toString());
        return this;
    }

    public MarkDownTable addRow(String rowString) {
        this.getRows().add(rowString.trim());
        return this;
    }

    public MarkDownTable() {
        rows = new ArrayList<>();
    }

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }
}
