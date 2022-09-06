package amazon.speech.io;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public abstract class DataStreamReader extends InputStream {
    public static final long POSITION_LATEST = Long.MAX_VALUE;

    public abstract long getPosition() throws IOException;

    public abstract long setPosition(long j) throws IOException;
}
