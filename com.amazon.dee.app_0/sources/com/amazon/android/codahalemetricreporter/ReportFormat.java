package com.amazon.android.codahalemetricreporter;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class ReportFormat {
    private static final char SEPARATOR_CATEGORY = ':';
    private static final char SEPARATOR_TYPE = '/';
    private final String mCategory;
    private final String mType;

    /* loaded from: classes11.dex */
    public static class BadFormatException extends Exception {
        public BadFormatException(String str) {
            super(str);
        }
    }

    public ReportFormat(String str) throws BadFormatException {
        try {
            int indexOf = str.indexOf(58);
            this.mCategory = str.substring(0, indexOf);
            this.mType = str.substring(indexOf + 1);
        } catch (StringIndexOutOfBoundsException unused) {
            throw new BadFormatException("Bad report format string");
        }
    }

    public static String name(String str, String str2) {
        return GeneratedOutlineSupport1.outline48(str, ':', str2);
    }

    public static boolean subtypeOf(String str, String str2) {
        if (!str.startsWith(str2)) {
            return false;
        }
        int length = str2.length();
        return length == str.length() || '/' == str.charAt(length);
    }

    public static String type(String str, String... strArr) {
        for (String str2 : strArr) {
            str = GeneratedOutlineSupport1.outline48(str, '/', str2);
        }
        return str;
    }

    public String category() {
        return this.mCategory;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ReportFormat)) {
            return false;
        }
        ReportFormat reportFormat = (ReportFormat) obj;
        return this.mCategory.equals(reportFormat.mCategory) && this.mType.equals(reportFormat.mType);
    }

    public String toString() {
        return name(this.mCategory, this.mType);
    }

    public String type() {
        return this.mType;
    }
}
