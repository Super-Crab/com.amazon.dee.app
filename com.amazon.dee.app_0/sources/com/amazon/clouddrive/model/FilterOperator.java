package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class FilterOperator {
    public static final String LESS_THAN = "LT";
    public static final String LESS_THAN_OR_EQUAL = "LE";
    public static final String EQUAL_TO = "EQ";
    public static final String GREATER_THAN = "GT";
    public static final String GREATER_THAN_OR_EQUAL = "GE";
    public static final String BETWEEN = "BTW";
    public static final String STARTS_WITH = "SW";
    private static final String[] values = {LESS_THAN, LESS_THAN_OR_EQUAL, EQUAL_TO, GREATER_THAN, GREATER_THAN_OR_EQUAL, BETWEEN, STARTS_WITH};

    private FilterOperator() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
