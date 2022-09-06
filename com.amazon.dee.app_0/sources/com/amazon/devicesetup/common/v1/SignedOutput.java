package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public abstract class SignedOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.SignedOutput");
    private String signature;

    public boolean equals(Object obj) {
        if (!(obj instanceof SignedOutput)) {
            return false;
        }
        return Helper.equals(this.signature, ((SignedOutput) obj).signature);
    }

    public String getSignature() {
        return this.signature;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.signature);
    }

    public void setSignature(String str) {
        this.signature = str;
    }
}
