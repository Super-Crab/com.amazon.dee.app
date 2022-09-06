package com.amazon.alexa.handsfree.devices.utils.comparators;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.constants.Carrier;
import com.amazon.alexa.handsfree.devices.utils.BuildPropertyGetter;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class LGV60VersionComparator implements VersionComparator {
    private static final int ANDROID_OS_VERSION_MAIN_VERSION_POS = 0;
    private static final String LG_SW_VERSION_KEY = "ro.vendor.lge.swversion";
    private static final String LG_V60_PREFIX = "V600";
    private static final int MAIN_VERSION_POS = 1;
    private static final int SUB_VERSION_POS = 2;
    private final BuildPropertyGetter mBuildPropertyGetter;
    private final Carrier mCarrier;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public enum LGV60Versions {
        VERIZON_VERSIONS(Carrier.VESPER, "VM", "10f", "20a"),
        ATT_VERSIONS(Carrier.ATT, "AM", "10u", "20a"),
        TUMS_VERSIONS(Carrier.TM, "TM", "10v", "20a"),
        US_CELLULAR_VERSIONS(Carrier.US_CELLULAR, "TM", "10o", "20a");
        
        private final String mAndroid10Version;
        private final String mAndroid11Version;
        private final Carrier mCarrier;
        private final String mVersionCode;

        LGV60Versions(@NonNull Carrier carrier, @NonNull String str, @Nullable String str2, @Nullable String str3) {
            this.mCarrier = carrier;
            this.mVersionCode = str;
            this.mAndroid10Version = str2;
            this.mAndroid11Version = str3;
        }

        @Nullable
        static LGV60Versions getVersionsByCarrier(@NonNull Carrier carrier) {
            LGV60Versions[] values;
            for (LGV60Versions lGV60Versions : values()) {
                if (lGV60Versions.mCarrier == carrier) {
                    return lGV60Versions;
                }
            }
            return null;
        }
    }

    public LGV60VersionComparator(@NonNull Carrier carrier) {
        this(carrier, new BuildPropertyGetter());
    }

    private boolean compareByAndroidVersions(@NonNull String str, @NonNull LGV60Versions lGV60Versions) {
        char c;
        String str2 = Build.VERSION.RELEASE;
        int hashCode = str2.hashCode();
        if (hashCode == 57) {
            if (str2.equals("9")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1567) {
            if (hashCode == 1568 && str2.equals("11")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str2.equals("10")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                return compareVersion(str, lGV60Versions.mAndroid10Version);
            }
            if (c == 2) {
                return compareVersion(str, lGV60Versions.mAndroid11Version);
            }
            return true;
        }
        return false;
    }

    private boolean compareVersion(@NonNull String str, @NonNull String str2) {
        if (str2 != null && str.charAt(0) <= str2.charAt(0)) {
            if (str.charAt(0) == str2.charAt(0)) {
                if (str.charAt(1) > str2.charAt(1)) {
                    return true;
                }
                if (str.charAt(1) == str2.charAt(1) && str.charAt(2) >= str2.charAt(2)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override // com.amazon.alexa.handsfree.devices.utils.comparators.VersionComparator
    public boolean hasMinimumVersion() {
        String readProperty = this.mBuildPropertyGetter.readProperty(LG_SW_VERSION_KEY);
        if (readProperty != null && readProperty.startsWith(LG_V60_PREFIX)) {
            StringBuilder sb = new StringBuilder(readProperty);
            sb.delete(0, 4);
            LGV60Versions versionsByCarrier = LGV60Versions.getVersionsByCarrier(this.mCarrier);
            if (versionsByCarrier != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(LG_V60_PREFIX);
                outline107.append(versionsByCarrier.mVersionCode);
                if (readProperty.startsWith(outline107.toString())) {
                    sb.delete(0, versionsByCarrier.mVersionCode.length());
                    return compareByAndroidVersions(sb.toString(), versionsByCarrier);
                }
            }
        }
        return false;
    }

    @VisibleForTesting
    LGV60VersionComparator(@NonNull Carrier carrier, @NonNull BuildPropertyGetter buildPropertyGetter) {
        this.mCarrier = carrier;
        this.mBuildPropertyGetter = buildPropertyGetter;
    }
}
