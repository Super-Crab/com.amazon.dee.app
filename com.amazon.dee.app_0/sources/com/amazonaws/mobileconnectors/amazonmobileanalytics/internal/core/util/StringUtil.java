package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Set;
@Deprecated
/* loaded from: classes13.dex */
public final class StringUtil {
    public static final String UTF_8 = "UTF-8";

    private StringUtil() {
    }

    public static String clipString(String str, int i, boolean z) {
        String substring = str.substring(0, Math.min(i, str.length()));
        return (!z || substring.length() >= str.length()) ? substring : GeneratedOutlineSupport1.outline72(substring, "...");
    }

    public static String convertArrayToString(String[] strArr) {
        if (strArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'");
            outline107.append(strArr[i]);
            outline107.append("'");
            sb.append(outline107.toString());
        }
        return sb.toString();
    }

    public static String convertSetToString(Set<String> set) {
        if (set == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : set) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append("'" + str + "'");
        }
        return sb.toString();
    }

    public static String convertStreamToString(InputStream inputStream, Charset charset) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine + "\n");
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }

    public static String convertStreamToUTF8String(InputStream inputStream) throws IOException {
        return convertStreamToString(inputStream, Charset.forName("UTF-8"));
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String trimOrPadString(String str, int i, char c) {
        if (i < 0) {
            i = 0;
        }
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (str.length() > i - 1) {
            stringBuffer.append(str.substring(str.length() - i));
        } else {
            for (int i2 = 0; i2 < i - str.length(); i2++) {
                stringBuffer.append(c);
            }
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }
}
