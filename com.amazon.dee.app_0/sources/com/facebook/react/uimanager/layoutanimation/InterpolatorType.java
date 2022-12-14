package com.facebook.react.uimanager.layoutanimation;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public enum InterpolatorType {
    LINEAR,
    EASE_IN,
    EASE_OUT,
    EASE_IN_EASE_OUT,
    SPRING;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static InterpolatorType fromString(String str) {
        char c;
        String lowerCase = str.toLowerCase(Locale.US);
        switch (lowerCase.hashCode()) {
            case -1965056864:
                if (lowerCase.equals("easeout")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1310315117:
                if (lowerCase.equals("easein")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1102672091:
                if (lowerCase.equals("linear")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -895679987:
                if (lowerCase.equals("spring")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1164546989:
                if (lowerCase.equals("easeineaseout")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c == 1) {
                return EASE_IN;
            }
            if (c == 2) {
                return EASE_OUT;
            }
            if (c == 3) {
                return EASE_IN_EASE_OUT;
            }
            if (c == 4) {
                return SPRING;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unsupported interpolation type : ", str));
        }
        return LINEAR;
    }
}
