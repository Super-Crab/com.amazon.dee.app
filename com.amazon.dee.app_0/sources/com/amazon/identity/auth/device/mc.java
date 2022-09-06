package com.amazon.identity.auth.device;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mc {
    private static final String TAG = "com.amazon.identity.auth.device.mc";
    private final String uN;

    public mc() {
        this("");
    }

    public String getString() {
        return this.uN;
    }

    public boolean isValid() {
        if (this.uN == null) {
            io.w(TAG, "SoftwareVersion: isValid: returning false because a valid software version has not been set.");
            return false;
        }
        return true;
    }

    public mc(String str) {
        boolean z = false;
        if (str == null) {
            io.w(TAG, "SoftwareVersion: isValidVersion: returning false because a null version was given.");
        } else if (str.length() > 0 && str.length() <= 37) {
            if (!ma.eJ(str)) {
                io.w(TAG, "SoftwareVersion: isValidVersion: returning false because version contains invalid characters (can only contain digits)");
            } else {
                z = true;
            }
        } else {
            io.w(TAG, "SoftwareVersion: isValidVersion: returning false because an invalid length was given (must be between 1 and 37 characters).");
        }
        if (z) {
            this.uN = str;
            return;
        }
        io.e(TAG, "SoftwareVersion: constructed with invalid software version. Was not set.");
        this.uN = null;
    }
}
