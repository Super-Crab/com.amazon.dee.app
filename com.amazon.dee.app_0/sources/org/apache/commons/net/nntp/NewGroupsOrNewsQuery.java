package org.apache.commons.net.nntp;

import java.util.Calendar;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes4.dex */
public final class NewGroupsOrNewsQuery {
    private String __date;
    private boolean __isGMT;
    private String __time;
    private StringBuffer __distributions = null;
    private StringBuffer __newsgroups = null;

    public NewGroupsOrNewsQuery(Calendar calendar, boolean z) {
        this.__isGMT = z;
        StringBuffer stringBuffer = new StringBuffer();
        String num = Integer.toString(calendar.get(1));
        int length = num.length();
        if (length >= 2) {
            stringBuffer.append(num.substring(length - 2));
        } else {
            stringBuffer.append("00");
        }
        String num2 = Integer.toString(calendar.get(2) + 1);
        int length2 = num2.length();
        if (length2 == 1) {
            stringBuffer.append('0');
            stringBuffer.append(num2);
        } else if (length2 == 2) {
            stringBuffer.append(num2);
        } else {
            stringBuffer.append("01");
        }
        String num3 = Integer.toString(calendar.get(5));
        int length3 = num3.length();
        if (length3 == 1) {
            stringBuffer.append('0');
            stringBuffer.append(num3);
        } else if (length3 == 2) {
            stringBuffer.append(num3);
        } else {
            stringBuffer.append("01");
        }
        this.__date = stringBuffer.toString();
        stringBuffer.setLength(0);
        String num4 = Integer.toString(calendar.get(11));
        int length4 = num4.length();
        if (length4 == 1) {
            stringBuffer.append('0');
            stringBuffer.append(num4);
        } else if (length4 == 2) {
            stringBuffer.append(num4);
        } else {
            stringBuffer.append("00");
        }
        String num5 = Integer.toString(calendar.get(12));
        int length5 = num5.length();
        if (length5 == 1) {
            stringBuffer.append('0');
            stringBuffer.append(num5);
        } else if (length5 == 2) {
            stringBuffer.append(num5);
        } else {
            stringBuffer.append("00");
        }
        String num6 = Integer.toString(calendar.get(13));
        int length6 = num6.length();
        if (length6 == 1) {
            stringBuffer.append('0');
            stringBuffer.append(num6);
        } else if (length6 == 2) {
            stringBuffer.append(num6);
        } else {
            stringBuffer.append("00");
        }
        this.__time = stringBuffer.toString();
    }

    public void addDistribution(String str) {
        StringBuffer stringBuffer = this.__distributions;
        if (stringBuffer != null) {
            stringBuffer.append(JsonReaderKt.COMMA);
        } else {
            this.__distributions = new StringBuffer();
        }
        this.__distributions.append(str);
    }

    public void addNewsgroup(String str) {
        StringBuffer stringBuffer = this.__newsgroups;
        if (stringBuffer != null) {
            stringBuffer.append(JsonReaderKt.COMMA);
        } else {
            this.__newsgroups = new StringBuffer();
        }
        this.__newsgroups.append(str);
    }

    public String getDate() {
        return this.__date;
    }

    public String getDistributions() {
        StringBuffer stringBuffer = this.__distributions;
        if (stringBuffer == null) {
            return null;
        }
        return stringBuffer.toString();
    }

    public String getNewsgroups() {
        StringBuffer stringBuffer = this.__newsgroups;
        if (stringBuffer == null) {
            return null;
        }
        return stringBuffer.toString();
    }

    public String getTime() {
        return this.__time;
    }

    public boolean isGMT() {
        return this.__isGMT;
    }

    public void omitNewsgroup(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("!");
        stringBuffer.append(str);
        addNewsgroup(stringBuffer.toString());
    }
}
