package amazon.speech.util;

import android.net.Uri;
import java.io.InputStream;
/* loaded from: classes.dex */
public class InProcTtsStreamProvider {
    private static final InProcTtsStreamProvider gInstance = new InProcTtsStreamProvider();
    private volatile Source mSource;

    /* loaded from: classes.dex */
    public interface Source {
        InputStream get(Uri uri);
    }

    private InProcTtsStreamProvider() {
    }

    public static InProcTtsStreamProvider getInstance() {
        return gInstance;
    }

    public InputStream get(Uri uri) {
        Source source = this.mSource;
        if (source == null) {
            return null;
        }
        return source.get(uri);
    }

    public void setSource(Source source) {
        this.mSource = source;
    }
}
