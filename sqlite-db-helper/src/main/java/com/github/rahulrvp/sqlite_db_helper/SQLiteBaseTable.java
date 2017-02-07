package com.github.rahulrvp.sqlite_db_helper;

import android.text.TextUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author Rahul Raveendran V P
 *         Created on 3/2/17 @ 5:50 PM
 *         https://github.com/rahulrvp
 */

public class SQLiteBaseTable {

    public String buildQuery() {
        StringBuilder builder = new StringBuilder();

        Annotation classAnnotation = getClass().getAnnotation(SQLiteTable.class);
        if (classAnnotation != null) {
            SQLiteTable table = (SQLiteTable) classAnnotation;

            builder
                    .append(table.mode())
                    .append(" ")
                    .append(table.tableName());
        }

        Field[] fields = getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {

            builder
                    .append(" ")
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

                    // append data type
                    // reference {@link https://www.sqlite.org/datatype3.html}
                    String type = "INTEGER";

                    if (field.getType() == String.class) {
                        type = "TEXT";
                    } else if (field.getType() == Double.class || field.getType() == Float.class) {
                        type = "REAL";
                    } else if (field.getType() == Object.class) {
                        type = "BLOB";
                    }

                    builder.append(" ").append(type);

                    if (column.primaryKey()) {
                        builder.append(" PRIMARY KEY");
                    }

                    if (column.autoIncrement()) {
                        builder.append(" AUTO INCREMENT");
                    }

                    if (column.notNull()) {
                        builder.append(" NOT NULL");
                    }

                    if (column.asc()) {
                        builder.append(" ASC");
                    }

                    builder.append(",").append(" ");
                }
            }

            int commaIndex = builder.lastIndexOf(",");

            builder.replace(commaIndex, builder.length(), "");

            builder.append(")");
        }

        return builder.toString();
    }
}
