package com.github.rahulrvp.sqlite_db_helper.types;

/**
 * @author Rahul Raveendran V P
 *         Created on 8/2/17 @ 3:41 PM
 *         https://github.com/rahulrvp
 */


public enum SQLiteConstraint {
    AUTO_INCREMENT,
    PRIMARY_KEY,
    UNIQUE,
    NOT_NULL,
    ASC;

    @Override
    public String toString() {
        switch (this) {
            case AUTO_INCREMENT:
                return "AUTO INCREMENT";

            case PRIMARY_KEY:
                return "PRIMARY KEY";

            case NOT_NULL:
                return "NOT NULL";

            default:
                return super.toString();
        }
    }
}
