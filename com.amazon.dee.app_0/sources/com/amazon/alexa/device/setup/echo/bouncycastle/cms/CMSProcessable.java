package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes.dex */
public interface CMSProcessable {
    Object getContent();

    void write(OutputStream outputStream) throws IOException, CMSException;
}
