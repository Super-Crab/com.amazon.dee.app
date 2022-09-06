package com.amazon.alexa.device.setup.echo.bouncycastle.cms;
/* loaded from: classes.dex */
public class PasswordRecipientId extends RecipientId {
    public PasswordRecipientId() {
        super(3);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientId, com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public Object clone() {
        return new PasswordRecipientId();
    }

    public boolean equals(Object obj) {
        return obj instanceof PasswordRecipientId;
    }

    public int hashCode() {
        return 3;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public boolean match(Object obj) {
        return obj instanceof PasswordRecipientInformation;
    }
}
