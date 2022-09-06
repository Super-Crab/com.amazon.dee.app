package org.bouncycastle.mail.smime;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public interface SMIMEStreamingProcessor {
    void write(OutputStream outputStream) throws IOException;
}
