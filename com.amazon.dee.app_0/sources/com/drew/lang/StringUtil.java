package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
/* loaded from: classes2.dex */
public final class StringUtil {
    public static int compare(@Nullable String str, @Nullable String str2) {
        boolean z = str == null;
        boolean z2 = str2 == null;
        if (!z || !z2) {
            if (z) {
                return -1;
            }
            if (!z2) {
                return str.compareTo(str2);
            }
            return 1;
        }
        return 0;
    }

    @NotNull
    public static String fromStream(@NotNull InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                return sb.toString();
            }
        }
    }

    @NotNull
    public static String join(@NotNull Iterable<? extends CharSequence> iterable, @NotNull String str) {
        int length = str.length();
        Iterator<? extends CharSequence> it2 = iterable.iterator();
        int i = 0;
        if (it2.hasNext()) {
            i = 0 + it2.next().length() + length;
        }
        StringBuilder sb = new StringBuilder(i);
        Iterator<? extends CharSequence> it3 = iterable.iterator();
        if (it3.hasNext()) {
            while (true) {
                sb.append(it3.next());
                if (!it3.hasNext()) {
                    break;
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    @NotNull
    public static <T extends CharSequence> String join(@NotNull T[] tArr, @NotNull String str) {
        int length = str.length();
        int i = 0;
        for (T t : tArr) {
            i += t.length() + length;
        }
        StringBuilder sb = new StringBuilder(i);
        boolean z = true;
        for (T t2 : tArr) {
            if (!z) {
                sb.append(str);
            } else {
                z = false;
            }
            sb.append((CharSequence) t2);
        }
        return sb.toString();
    }

    @NotNull
    public static String urlEncode(@NotNull String str) {
        return str.replace(" ", "%20");
    }
}
