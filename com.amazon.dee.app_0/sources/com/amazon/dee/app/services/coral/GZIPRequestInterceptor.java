package com.amazon.dee.app.services.coral;

import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
/* loaded from: classes12.dex */
public class GZIPRequestInterceptor implements Interceptor {
    public static final String GZIP_HEADER = "gzipBody";
    private static final String TAG = Log.tag(GZIPRequestInterceptor.class);

    private RequestBody forceContentLength(final RequestBody requestBody) throws IOException {
        final Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return new RequestBody() { // from class: com.amazon.dee.app.services.coral.GZIPRequestInterceptor.1
            @Override // okhttp3.RequestBody
            public long contentLength() {
                long size = buffer.size();
                String unused = GZIPRequestInterceptor.TAG;
                GeneratedOutlineSupport1.outline153("New content length ", size);
                return size;
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return requestBody.contentType();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.mo12591write(buffer.snapshot());
            }
        };
    }

    private RequestBody gzip(final RequestBody requestBody) {
        return new RequestBody() { // from class: com.amazon.dee.app.services.coral.GZIPRequestInterceptor.2
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return -1L;
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return requestBody.contentType();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                BufferedSink bufferedSink2 = null;
                try {
                    bufferedSink2 = Okio.buffer(new GzipSink(bufferedSink));
                    requestBody.writeTo(bufferedSink2);
                } finally {
                    if (bufferedSink2 != null) {
                        bufferedSink2.close();
                    }
                }
            }
        };
    }

    private Response proceedWithOriginal(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().removeHeader(GZIP_HEADER).build());
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (request.header(GZIP_HEADER) == null) {
            return chain.proceed(request);
        }
        RequestBody body = request.body();
        if (body == null) {
            return proceedWithOriginal(chain);
        }
        try {
            if (request.header(GZIP_HEADER) == "true" && body.contentLength() >= 1024) {
                try {
                    String str = "Gzipping request... old length is " + body.contentLength();
                    return chain.proceed(request.newBuilder().removeHeader(GZIP_HEADER).addHeader("Content-Encoding", "gzip").method(request.method(), forceContentLength(gzip(body))).build());
                } catch (IOException e) {
                    Log.e(TAG, "Failed to gzip request", e);
                    return proceedWithOriginal(chain);
                }
            }
            return proceedWithOriginal(chain);
        } catch (IOException e2) {
            Log.e(TAG, "Failed to determine if we should gzip", e2);
            return proceedWithOriginal(chain);
        }
    }
}
