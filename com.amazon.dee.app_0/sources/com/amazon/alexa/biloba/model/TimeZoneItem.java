package com.amazon.alexa.biloba.model;

import android.text.TextUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
/* loaded from: classes6.dex */
public class TimeZoneItem {
    private static final String TAG = "TimeZoneItem";
    private final String text;
    private final String value;

    public TimeZoneItem(String str, String str2) {
        this.text = str;
        this.value = str2;
    }

    public String getPrimaryText() {
        String[] split = this.text.split("\\(");
        if (!TextUtils.isEmpty(split[0])) {
            return split[0].substring(0, split[0].length() - 1);
        }
        String str = TAG;
        LogUtils.i(str, this.text + " cannot be split as it was not in the format PrimaryText (SecondaryText)");
        return this.text;
    }

    public String getSecondaryText() {
        String[] split = this.text.split("\\(");
        if (!TextUtils.isEmpty(split[1])) {
            return split[1].substring(0, split[1].length() - 1);
        }
        String str = TAG;
        LogUtils.i(str, this.text + " cannot be split as it was not in the format PrimaryText (SecondaryText)");
        return this.text;
    }

    public String getText() {
        return this.text;
    }

    public String getValue() {
        return this.value;
    }
}
