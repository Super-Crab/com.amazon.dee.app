package amazon.speech.simclient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes.dex */
public class UrlWrapper {
    final URL mUrl;

    public UrlWrapper(URL url) {
        this.mUrl = url;
    }

    public HttpURLConnection openConnection() throws IOException {
        return (HttpURLConnection) this.mUrl.openConnection();
    }
}
