package amazon.speech.io;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes.dex */
public abstract class DataStreamWriter extends OutputStream {
    public abstract long getPosition() throws IOException;

    public abstract long setPosition(long j) throws IOException;
}
