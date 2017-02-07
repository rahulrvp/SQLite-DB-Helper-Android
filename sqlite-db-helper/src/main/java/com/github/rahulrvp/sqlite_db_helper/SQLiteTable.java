package com.github.rahulrvp.sqlite_db_helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Rahul Raveendran V P
 *         Created on 3/2/17 @ 5:48 PM
 *         https://github.com/rahulrvp
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // class level
public @interface SQLiteTable {
    String dbName() default ""; // if default found, take name specified in the config

    String tableName() default ""; // if default found, take class name as table name

    String mode() default "CREATE TABLE"; // need to define an enum to support all possible actions
}
