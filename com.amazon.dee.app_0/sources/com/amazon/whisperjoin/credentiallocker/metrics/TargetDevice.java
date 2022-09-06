package com.amazon.whisperjoin.credentiallocker.metrics;

import android.util.Log;
import org.apache.commons.lang.StringUtils;
/* loaded from: classes13.dex */
public class TargetDevice {
    private static final String TAG = "com.amazon.whisperjoin.credentiallocker.metrics.TargetDevice";
    private String targetManufacturer;
    private String targetName;
    private String targetVersion;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String targetManufacturer;
        private String targetName;
        private String targetVerison;

        public TargetDevice build() {
            String str;
            String str2;
            String str3 = this.targetName;
            if (str3 == null || (str = this.targetVerison) == null || (str2 = this.targetManufacturer) == null) {
                Log.e(TargetDevice.TAG, "Target name, version or manufacturer cannot be null");
                throw new IllegalArgumentException("Target name, version or manufacturer cannot be null");
            }
            return new TargetDevice(str3, str, str2);
        }

        public Builder withTargetManufacturer(String str) {
            if (!StringUtils.isEmpty(str)) {
                this.targetManufacturer = str;
                return this;
            }
            throw new IllegalArgumentException("Target Manufacturer cannot be null or empty");
        }

        public Builder withTargetName(String str) {
            if (!StringUtils.isEmpty(str)) {
                this.targetName = str;
                return this;
            }
            throw new IllegalArgumentException("Target Name cannot be null or empty");
        }

        public Builder withTargetVersion(String str) {
            if (!StringUtils.isEmpty(str)) {
                this.targetVerison = str;
                return this;
            }
            throw new IllegalArgumentException("Target Version cannot be null or empty");
        }
    }

    public String getTargetManufacturer() {
        return this.targetManufacturer;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public String getTargetVersion() {
        return this.targetVersion;
    }

    private TargetDevice(String str, String str2, String str3) {
        this.targetName = str;
        this.targetVersion = str2;
        this.targetManufacturer = str3;
    }
}
