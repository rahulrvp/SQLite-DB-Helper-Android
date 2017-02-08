package com.github.rahulrvp.sqlite_db_helper_android;

import com.github.rahulrvp.sqlite_db_helper.SQLiteBaseTable;
import com.github.rahulrvp.sqlite_db_helper.annotations.SQLiteColumn;
import com.github.rahulrvp.sqlite_db_helper.annotations.SQLiteTable;

/**
 * @author Rahul Raveendran V P
 *         Created on 7/2/17 @ 7:59 PM
 *         https://github.com/rahulrvp
 */

@SQLiteTable(tableName = "child_table")
public class SQLiteChildTable extends SQLiteBaseTable {

    @SQLiteColumn(primaryKey = true)
    private int intValuePK;

    @SQLiteColumn(unique = true)
    private String stringValue;

    @SQLiteColumn
    private boolean booleanValue;

    @SQLiteColumn
    private double doubleValue;
}
