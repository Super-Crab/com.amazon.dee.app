package com.amazon.dp.logger;

import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class DPFormattedMessage {
    private final String mFormattedString;
    private Throwable mThrowable;

    public DPFormattedMessage(String str, String str2, Object... objArr) {
        this.mFormattedString = toDPFormat(str, str2, objArr);
        if (objArr.length <= 0 || !(objArr[objArr.length - 1] instanceof Throwable)) {
            return;
        }
        setThrowable((Throwable) objArr[objArr.length - 1]);
    }

    public static String toDPFormat(String str, String str2, Object... objArr) {
        int length = objArr.length;
        if (str == null) {
            str = DefaultRecordChecker.Regex.EMPTY;
        }
        if (str2 == null) {
            str2 = DefaultRecordChecker.Regex.EMPTY;
        }
        StringBuilder sb = new StringBuilder((length * 40) + str2.length() + str.length());
        GeneratedOutlineSupport1.outline181(sb, str, " - ", str2, ";");
        if (length > 0 && (objArr[length - 1] instanceof Throwable)) {
            length--;
        }
        int i = 0;
        while (i < length) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(" ");
            sb.append(objArr[i]);
            int i2 = i + 1;
            if (i2 < length) {
                sb.append(RealTimeTextConstants.COLON_SPACE);
                sb.append(objArr[i2]);
            }
            i = i2 + 1;
        }
        return sb.toString();
    }

    public Throwable getThrowable() {
        return this.mThrowable;
    }

    public void setThrowable(Throwable th) {
        this.mThrowable = th;
    }

    public String toString() {
        return this.mFormattedString;
    }
}
