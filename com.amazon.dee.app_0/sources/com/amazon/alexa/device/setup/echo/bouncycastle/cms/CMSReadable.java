package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
interface CMSReadable {
    InputStream getInputStream() throws IOException, CMSException;
}
