package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.util.Arrays;
/* loaded from: classes.dex */
public class KEKRecipientId extends RecipientId {
    private final byte[] keyIdentifier;

    public KEKRecipientId(byte[] bArr) {
        super(1);
        this.keyIdentifier = bArr;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientId, com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public Object clone() {
        return new KEKRecipientId(this.keyIdentifier);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof KEKRecipientId)) {
            return false;
        }
        return Arrays.areEqual(this.keyIdentifier, ((KEKRecipientId) obj).keyIdentifier);
    }

    public byte[] getKeyIdentifier() {
        return Arrays.clone(this.keyIdentifier);
    }

    public int hashCode() {
        return Arrays.hashCode(this.keyIdentifier);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof byte[]) {
            return Arrays.areEqual(this.keyIdentifier, (byte[]) obj);
        }
        if (!(obj instanceof KEKRecipientInformation)) {
            return false;
        }
        return ((KEKRecipientInformation) obj).getRID().equals(this);
    }
}
