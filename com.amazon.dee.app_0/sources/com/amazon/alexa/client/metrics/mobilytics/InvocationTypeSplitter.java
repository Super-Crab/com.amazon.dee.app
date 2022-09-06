package com.amazon.alexa.client.metrics.mobilytics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.AlexaService;
import com.amazon.alexa.mobilytics.event.metadata.A4ASdkMetadata;
import com.amazon.alexa.sharing.Constants;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
/* loaded from: classes6.dex */
public class InvocationTypeSplitter {
    private InvocationTypeSplitter() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static A4ASdkMetadata addInvocationType(@NonNull A4ASdkMetadata a4ASdkMetadata, @NonNull String str) {
        char c;
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return a4ASdkMetadata;
        }
        String[] split = str.split(ArcusConfig.PATH_SEPARATOR);
        if (split.length != 1 && split.length <= 3) {
            String str4 = split[0];
            switch (str4.hashCode()) {
                case -613257390:
                    if (str4.equals(AlexaService.zZm)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 650201872:
                    if (str4.equals("Accessories")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 675840438:
                    if (str4.equals("VoiceEnrollment")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1684994366:
                    if (str4.equals(Constants.ALEXA_APP_SOURCE)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 2073650768:
                    if (str4.equals("HandsFree")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            String str5 = null;
            if (c == 0) {
                str2 = split[0];
                if (split.length == 3) {
                    str5 = split[2];
                    str3 = split[1];
                } else {
                    if (split.length == 2) {
                        str3 = split[1];
                    }
                    str3 = null;
                }
            } else if (c != 1) {
                str2 = split[0];
                if (split.length == 3) {
                    str5 = split[1];
                    str3 = split[2];
                } else {
                    if (split.length == 2) {
                        str3 = split[1];
                    }
                    str3 = null;
                }
            } else {
                str3 = str;
                str2 = null;
            }
            return a4ASdkMetadata.withInvocationIdentifier(str5).withInvocationNamespace(str2).withInvocationType(str3);
        }
        return a4ASdkMetadata.withInvocationType(str);
    }
}
