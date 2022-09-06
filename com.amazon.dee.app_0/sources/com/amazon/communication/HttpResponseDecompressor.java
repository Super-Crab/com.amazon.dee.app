package com.amazon.communication;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.InputStreamEntity;
/* loaded from: classes12.dex */
public class HttpResponseDecompressor {
    private static final String GZIP = "gzip";

    private HttpResponseDecompressor() {
    }

    public static boolean decompressResponseIfNecessary(HttpResponse httpResponse) throws IOException {
        Header contentEncoding;
        HttpEntity entity = httpResponse.getEntity();
        if (entity == null || (contentEncoding = entity.getContentEncoding()) == null) {
            return false;
        }
        if (GZIP.equalsIgnoreCase(contentEncoding.getValue())) {
            httpResponse.setEntity(new InputStreamEntity(new GZIPInputStream(entity.getContent()), -1L));
            httpResponse.setHeader("Content-Length", "-1");
            httpResponse.removeHeaders("Content-Encoding");
            return true;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported encoding type: ");
        outline107.append(contentEncoding.getValue());
        throw new UnsupportedOperationException(outline107.toString());
    }
}
