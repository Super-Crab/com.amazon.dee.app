package com.amazon.deecomms.calling.enums;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public final class CallProvider {
    public static final String Alexa = "Alexa";
    public static final String DEFAULT = "";
    public static final String A2A = "A2A";
    public static final String COBO = "Bacon";
    public static final String BELL = "Bell";
    private static final String[] CALL_PROVIDERS = {"", A2A, COBO, BELL};

    private CallProvider() {
    }

    @NonNull
    public static String fromBundle(@Nullable Bundle bundle, @NonNull String str) {
        String string;
        return (bundle == null || (string = bundle.getString(str)) == null) ? "" : fromString(string);
    }

    @NonNull
    public static String fromString(@Nullable String str) {
        String[] strArr;
        for (String str2 : CALL_PROVIDERS) {
            if (str2.equalsIgnoreCase(str)) {
                return str2;
            }
        }
        return "";
    }
}
