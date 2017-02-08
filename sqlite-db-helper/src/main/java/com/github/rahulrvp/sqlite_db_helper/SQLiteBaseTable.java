package com.github.rahulrvp.sqlite_db_helper;

import android.text.TextUtils;

import com.github.rahulrvp.sqlite_db_helper.annotations.SQLiteColumn;
import com.github.rahulrvp.sqlite_db_helper.annotations.SQLiteTable;
import com.github.rahulrvp.sqlite_db_helper.types.SQLiteConstraint;
import com.github.rahulrvp.sqlite_db_helper.types.SQLiteDataType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author Rahul Raveendran V P
 *         Created on 3/2/17 @ 5:50 PM
 *         https://github.com/rahulrvp
 */

public class SQLiteBaseTable {
    private final String WHITE_SPACE = " ";

    /**
     * This method is responsible for processing the table's info and building the
     * SQL query required for creating/updating the same.
     * <p>
     * Reference: <a href="https://www.sqlite.org/lang_createtable.html">https://www.sqlite.org/lang_createtable.html</a>
     *
     * @return a valid SQL query or empty string.
     */
    public String buildQuery() {
        StringBuilder builder = new StringBuilder();

        /**
         * Process the table annotation.
         */
        Annotation classAnnotation = getClass().getAnnotation(SQLiteTable.class);
        if (classAnnotation != null) {
            SQLiteTable table = (SQLiteTable) classAnnotation;

            String tableName = table.tableName();
            if (TextUtils.isEmpty(tableName)) {
                tableName = getClass().getSimpleName();
            }

            builder
                    .append(table.mode())
                    .append(WHITE_SPACE)
                    .append(tableName);
        }

        /**
         * Process the fields.
         */
        Field[] fields = getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {

            builder
                    .append(WHITE_SPACE)
                    .append("(");

            for (Field field : fields) {
                Annotation annotation = field.getAnnotation(SQLiteColumn.class);
                if (annotation != null) {
                    SQLiteColumn column = (SQLiteColumn) annotation;

                    String columnName = column.name();
                    if (TextUtils.isEmpty(columnName)) {
                        columnName = field.getName();
                    }

                    builder.append(columnName);

                    /**
                     * Append data-type and constraints
                     *
                     * Reference: <a href="https://www.sqlite.org/datatype3.html">https://www.sqlite.org/datatype3.html</a>
                     */
                    SQLiteDataType type = SQLiteDataType.INTEGER;

                    if (field.getType() == String.class) {
                        type = SQLiteDataType.TEXT;
                    } else if (field.getType() == Double.class || field.getType() == Float.class) {
                        type = SQLiteDataType.REAL;
                    } else if (field.getType() == Object.class) {
                        type = SQLiteDataType.BLOB;
                    }

                    builder.append(WHITE_SPACE).append(type);

                    if (column.primaryKey()) {
                        builder.append(WHITE_SPACE).append(SQLiteConstraint.PRIMARY_KEY);
                    }

                    if (column.notNull()) {
                        builder.append(WHITE_SPACE).append(SQLiteConstraint.NOT_NULL);
                    }

                    if (column.unique()) {
                        builder.append(WHITE_SPACE).append(SQLiteConstraint.UNIQUE);
                    }

                    if (column.autoIncrement()) {
                        builder.append(WHITE_SPACE).append(SQLiteConstraint.AUTO_INCREMENT);
                    }

                    if (column.asc()) {
                        builder.append(WHITE_SPACE).append(SQLiteConstraint.ASC);
                    }

                    builder.append(",").append(WHITE_SPACE);
                }
            }

            int commaIndex = builder.lastIndexOf(",");
            if (commaIndex != -1) {
                builder.replace(commaIndex, builder.length(), "");
            }

            builder.append(")");
        }

        return builder.toString();
    }
}
