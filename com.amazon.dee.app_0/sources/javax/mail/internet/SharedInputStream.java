package javax.mail.internet;

import java.io.InputStream;
/* loaded from: classes3.dex */
public interface SharedInputStream {
    long getPosition();

    InputStream newStream(long j, long j2);
}
