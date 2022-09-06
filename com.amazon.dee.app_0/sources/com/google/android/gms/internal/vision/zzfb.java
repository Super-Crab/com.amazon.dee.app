package com.google.android.gms.internal.vision;
/* loaded from: classes2.dex */
final class zzfb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzd(zzbo zzboVar) {
        String str;
        zzfc zzfcVar = new zzfc(zzboVar);
        StringBuilder sb = new StringBuilder(zzfcVar.size());
        for (int i = 0; i < zzfcVar.size(); i++) {
            int zzl = zzfcVar.zzl(i);
            if (zzl == 34) {
                str = "\\\"";
            } else if (zzl == 39) {
                str = "\\'";
            } else if (zzl != 92) {
                switch (zzl) {
                    case 7:
                        str = "\\a";
                        break;
                    case 8:
                        str = "\\b";
                        break;
                    case 9:
                        str = "\\t";
                        break;
                    case 10:
                        str = "\\n";
                        break;
                    case 11:
                        str = "\\v";
                        break;
                    case 12:
                        str = "\\f";
                        break;
                    case 13:
                        str = "\\r";
                        break;
                    default:
                        if (zzl < 32 || zzl > 126) {
                            sb.append('\\');
                            sb.append((char) (((zzl >>> 6) & 3) + 48));
                            sb.append((char) (((zzl >>> 3) & 7) + 48));
                            zzl = (zzl & 7) + 48;
                        }
                        sb.append((char) zzl);
                        continue;
                        break;
                }
            } else {
                str = "\\\\";
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
