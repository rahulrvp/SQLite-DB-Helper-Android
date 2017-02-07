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
@Target(ElementType.FIELD)
public @interface SQLiteColumn {

    String name() default "";

    boolean primaryKey() default false;

    boolean notNull() default false;

    boolean autoIncrement() default false;

    boolean asc() default false;

    // can't set a default value as the type of object is unknown.
}
