package com.amazon.alexa;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.navigation.MappingApplication;
import java.util.Locale;
/* compiled from: MappingApplication.java */
/* loaded from: classes.dex */
public abstract class PlA {
    public static PlA BIo;
    public static PlA zZm;
    public final String zQM;
    public final String zyO;

    /* compiled from: MappingApplication.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    static final class BIo extends PlA {
        public /* synthetic */ BIo(Btk btk) {
            super("WAZE", MappingApplication.WAZE_PACKAGENAME, null);
        }

        @Override // com.amazon.alexa.PlA
        public Uri zZm(double d, double d2, String str) {
            return Uri.parse(String.format(Locale.US, "https://waze.com/ul?ll=%f,%f&navigate=yes", Double.valueOf(d), Double.valueOf(d2)));
        }
    }

    public /* synthetic */ PlA(String str, String str2, Btk btk) {
        this.zQM = str;
        this.zyO = str2;
    }

    public static PlA zZm() {
        if (zZm == null) {
            zZm = new zZm(null);
        }
        return zZm;
    }

    public abstract Uri zZm(double d, double d2, String str);

    /* compiled from: MappingApplication.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    static final class zZm extends PlA {
        public /* synthetic */ zZm(Btk btk) {
            super("GOOGLE_MAPS", MappingApplication.GOOGLE_MAPS_PACKAGENAME, null);
        }

        @Override // com.amazon.alexa.PlA
        public Uri zZm(double d, double d2, String str) {
            return Uri.parse(String.format(Locale.US, "google.navigation:q=%f,%f&mode=%s", Double.valueOf(d), Double.valueOf(d2), EnumC0012zZm.zZm(str).zZm()));
        }

        /* compiled from: MappingApplication.java */
        /* renamed from: com.amazon.alexa.PlA$zZm$zZm  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        enum EnumC0012zZm {
            BICYCLE("b"),
            DRIVING("d"),
            WALKING("w");
            
            public final String transportationMode;

            EnumC0012zZm(String str) {
                this.transportationMode = str;
            }

            public static EnumC0012zZm zZm(@Nullable String str) {
                if (str == null) {
                    return DRIVING;
                }
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -1656617049) {
                    if (hashCode != -349077069) {
                        if (hashCode != 1836798297) {
                            if (hashCode == 1959247966 && str.equals(MappingApplication.BIKING)) {
                                c = 0;
                            }
                        } else if (str.equals(MappingApplication.WALKING)) {
                            c = 1;
                        }
                    } else if (str.equals(MappingApplication.TRANSIT)) {
                        c = 3;
                    }
                } else if (str.equals(MappingApplication.DRIVING)) {
                    c = 2;
                }
                if (c == 0) {
                    return BICYCLE;
                }
                if (c != 1) {
                    return DRIVING;
                }
                return WALKING;
            }

            public String zZm() {
                return this.transportationMode;
            }
        }
    }
}
