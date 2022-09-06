package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector;
/* loaded from: classes.dex */
public abstract class RecipientId implements Selector {
    public static final int kek = 1;
    public static final int keyAgree = 2;
    public static final int keyTrans = 0;
    public static final int password = 3;
    private final int type;

    /* JADX INFO: Access modifiers changed from: protected */
    public RecipientId(int i) {
        this.type = i;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public abstract Object clone();

    public int getType() {
        return this.type;
    }
}
