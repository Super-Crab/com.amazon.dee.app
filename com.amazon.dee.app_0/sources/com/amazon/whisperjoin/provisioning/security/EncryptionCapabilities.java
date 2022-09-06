package com.amazon.whisperjoin.provisioning.security;

import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
/* loaded from: classes13.dex */
public class EncryptionCapabilities implements Validatable {
    private int[] schemes;

    protected boolean canEqual(Object obj) {
        return obj instanceof EncryptionCapabilities;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EncryptionCapabilities)) {
            return false;
        }
        EncryptionCapabilities encryptionCapabilities = (EncryptionCapabilities) obj;
        return encryptionCapabilities.canEqual(this) && Arrays.equals(getSchemes(), encryptionCapabilities.getSchemes());
    }

    public int[] getSchemes() {
        return this.schemes;
    }

    public int hashCode() {
        return Arrays.hashCode(getSchemes()) + 59;
    }

    public void setSchemes(int[] iArr) {
        this.schemes = iArr;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EncryptionCapabilities(schemes=");
        outline107.append(Arrays.toString(getSchemes()));
        outline107.append(")");
        return outline107.toString();
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
    }
}
