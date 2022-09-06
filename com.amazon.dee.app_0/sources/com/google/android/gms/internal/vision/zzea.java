package com.google.android.gms.internal.vision;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.util.Chars;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzea {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzdx zzdxVar, String str) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("# ", str);
        zza(zzdxVar, outline113, 0);
        return outline113.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x01e7, code lost:
        if (((java.lang.Boolean) r11).booleanValue() == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01e9, code lost:
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01f9, code lost:
        if (((java.lang.Integer) r11).intValue() == 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x020a, code lost:
        if (((java.lang.Float) r11).floatValue() == 0.0f) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x021c, code lost:
        if (((java.lang.Double) r11).doubleValue() == com.amazon.comms.ringservice.webrtc.FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) goto L79;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void zza(com.google.android.gms.internal.vision.zzdx r18, java.lang.StringBuilder r19, int r20) {
        /*
            Method dump skipped, instructions count: 661
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zza(com.google.android.gms.internal.vision.zzdx, java.lang.StringBuilder, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                zza(sb, i, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                zza(sb, i, str, entry);
            }
        } else {
            sb.append('\n');
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(Chars.SPACE);
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzfb.zzd(zzbo.zzg((String) obj)));
                sb.append('\"');
            } else if (obj instanceof zzbo) {
                sb.append(": \"");
                sb.append(zzfb.zzd((zzbo) obj));
                sb.append('\"');
            } else if (obj instanceof zzcr) {
                sb.append(" {");
                zza((zzcr) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(Chars.SPACE);
                    i2++;
                }
                sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            } else if (!(obj instanceof Map.Entry)) {
                sb.append(RealTimeTextConstants.COLON_SPACE);
                sb.append(obj.toString());
            } else {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int i4 = i + 2;
                zza(sb, i4, "key", entry2.getKey());
                zza(sb, i4, "value", entry2.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(Chars.SPACE);
                    i2++;
                }
                sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        }
    }

    private static final String zzj(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
