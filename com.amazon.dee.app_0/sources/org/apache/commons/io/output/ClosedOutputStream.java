package org.apache.commons.io.output;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public class ClosedOutputStream extends OutputStream {
    public static final ClosedOutputStream CLOSED_OUTPUT_STREAM = new ClosedOutputStream();

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        throw new IOException(GeneratedOutlineSupport1.outline52("write(", i, ") failed: stream is closed"));
    }
}
