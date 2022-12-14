package org.bouncycastle.crypto.params;

import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class DHValidationParameters {
    private int counter;
    private byte[] seed;

    public DHValidationParameters(byte[] bArr, int i) {
        this.seed = Arrays.clone(bArr);
        this.counter = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHValidationParameters)) {
            return false;
        }
        DHValidationParameters dHValidationParameters = (DHValidationParameters) obj;
        if (dHValidationParameters.counter == this.counter) {
            return Arrays.areEqual(this.seed, dHValidationParameters.seed);
        }
        return false;
    }

    public int getCounter() {
        return this.counter;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public int hashCode() {
        return this.counter ^ Arrays.hashCode(this.seed);
    }
}
