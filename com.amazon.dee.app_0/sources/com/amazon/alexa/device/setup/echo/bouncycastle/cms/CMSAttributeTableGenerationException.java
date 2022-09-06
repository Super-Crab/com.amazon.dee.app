package com.amazon.alexa.device.setup.echo.bouncycastle.cms;
/* loaded from: classes.dex */
public class CMSAttributeTableGenerationException extends CMSRuntimeException {
    Exception e;

    public CMSAttributeTableGenerationException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSRuntimeException, java.lang.Throwable
    public Throwable getCause() {
        return this.e;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSRuntimeException
    public Exception getUnderlyingException() {
        return this.e;
    }

    public CMSAttributeTableGenerationException(String str, Exception exc) {
        super(str);
        this.e = exc;
    }
}
