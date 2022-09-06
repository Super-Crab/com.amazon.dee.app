package com.google.android.exoplayer2.ext.okhttp;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.upstream.BaseDataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Predicate;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes2.dex */
public class OkHttpDataSource extends BaseDataSource implements HttpDataSource {
    private static final byte[] SKIP_BUFFER;
    private long bytesRead;
    private long bytesSkipped;
    private long bytesToRead;
    private long bytesToSkip;
    @Nullable
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    @Nullable
    private Predicate<String> contentTypePredicate;
    @Nullable
    private DataSpec dataSpec;
    @Nullable
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    private boolean opened;
    private final HttpDataSource.RequestProperties requestProperties;
    @Nullable
    private Response response;
    @Nullable
    private InputStream responseByteStream;
    @Nullable
    private final String userAgent;

    /* loaded from: classes2.dex */
    public static final class Factory implements HttpDataSource.Factory {
        @Nullable
        private CacheControl cacheControl;
        private final Call.Factory callFactory;
        @Nullable
        private Predicate<String> contentTypePredicate;
        private final HttpDataSource.RequestProperties defaultRequestProperties = new HttpDataSource.RequestProperties();
        @Nullable
        private TransferListener transferListener;
        @Nullable
        private String userAgent;

        public Factory(Call.Factory factory) {
            this.callFactory = factory;
        }

        @Override // com.google.android.exoplayer2.upstream.HttpDataSource.Factory
        @Deprecated
        public final HttpDataSource.RequestProperties getDefaultRequestProperties() {
            return this.defaultRequestProperties;
        }

        public Factory setCacheControl(@Nullable CacheControl cacheControl) {
            this.cacheControl = cacheControl;
            return this;
        }

        public Factory setContentTypePredicate(@Nullable Predicate<String> predicate) {
            this.contentTypePredicate = predicate;
            return this;
        }

        @Override // com.google.android.exoplayer2.upstream.HttpDataSource.Factory
        /* renamed from: setDefaultRequestProperties  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ HttpDataSource.Factory mo7459setDefaultRequestProperties(Map map) {
            return mo7459setDefaultRequestProperties((Map<String, String>) map);
        }

        public Factory setTransferListener(@Nullable TransferListener transferListener) {
            this.transferListener = transferListener;
            return this;
        }

        public Factory setUserAgent(@Nullable String str) {
            this.userAgent = str;
            return this;
        }

        @Override // com.google.android.exoplayer2.upstream.HttpDataSource.Factory
        /* renamed from: setDefaultRequestProperties */
        public final Factory mo7459setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        @Override // com.google.android.exoplayer2.upstream.HttpDataSource.Factory, com.google.android.exoplayer2.upstream.DataSource.Factory
        /* renamed from: createDataSource */
        public OkHttpDataSource mo7468createDataSource() {
            OkHttpDataSource okHttpDataSource = new OkHttpDataSource(this.callFactory, this.userAgent, this.cacheControl, this.defaultRequestProperties, this.contentTypePredicate);
            TransferListener transferListener = this.transferListener;
            if (transferListener != null) {
                okHttpDataSource.addTransferListener(transferListener);
            }
            return okHttpDataSource;
        }
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.okhttp");
        SKIP_BUFFER = new byte[4096];
    }

    private void closeConnectionQuietly() {
        Response response = this.response;
        if (response != null) {
            ((ResponseBody) Assertions.checkNotNull(response.body())).close();
            this.response = null;
        }
        this.responseByteStream = null;
    }

    private Request makeRequest(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        long j = dataSpec.position;
        long j2 = dataSpec.length;
        HttpUrl parse = HttpUrl.parse(dataSpec.uri.toString());
        if (parse != null) {
            Request.Builder url = new Request.Builder().url(parse);
            CacheControl cacheControl = this.cacheControl;
            if (cacheControl != null) {
                url.cacheControl(cacheControl);
            }
            HashMap hashMap = new HashMap();
            HttpDataSource.RequestProperties requestProperties = this.defaultRequestProperties;
            if (requestProperties != null) {
                hashMap.putAll(requestProperties.getSnapshot());
            }
            hashMap.putAll(this.requestProperties.getSnapshot());
            hashMap.putAll(dataSpec.httpRequestHeaders);
            for (Map.Entry entry : hashMap.entrySet()) {
                url.header((String) entry.getKey(), (String) entry.getValue());
            }
            if (j != 0 || j2 != -1) {
                String outline57 = GeneratedOutlineSupport1.outline57("bytes=", j, ProcessIdUtil.DEFAULT_PROCESSID);
                if (j2 != -1) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(outline57);
                    outline107.append((j + j2) - 1);
                    outline57 = outline107.toString();
                }
                url.addHeader("Range", outline57);
            }
            String str = this.userAgent;
            if (str != null) {
                url.addHeader("User-Agent", str);
            }
            if (!dataSpec.isFlagSet(1)) {
                url.addHeader("Accept-Encoding", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
            }
            byte[] bArr = dataSpec.httpBody;
            RequestBody requestBody = null;
            if (bArr != null) {
                requestBody = RequestBody.create((MediaType) null, bArr);
            } else if (dataSpec.httpMethod == 2) {
                requestBody = RequestBody.create((MediaType) null, Util.EMPTY_BYTE_ARRAY);
            }
            url.method(dataSpec.getHttpMethodString(), requestBody);
            return url.build();
        }
        throw new HttpDataSource.HttpDataSourceException("Malformed URL", dataSpec, 1);
    }

    private int readInternal(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesToRead;
        if (j != -1) {
            long j2 = j - this.bytesRead;
            if (j2 == 0) {
                return -1;
            }
            i2 = (int) Math.min(i2, j2);
        }
        int read = ((InputStream) Util.castNonNull(this.responseByteStream)).read(bArr, i, i2);
        if (read == -1) {
            if (this.bytesToRead != -1) {
                throw new EOFException();
            }
            return -1;
        }
        this.bytesRead += read;
        bytesTransferred(read);
        return read;
    }

    private void skipInternal() throws IOException {
        if (this.bytesSkipped == this.bytesToSkip) {
            return;
        }
        while (true) {
            long j = this.bytesSkipped;
            long j2 = this.bytesToSkip;
            if (j == j2) {
                return;
            }
            int read = ((InputStream) Util.castNonNull(this.responseByteStream)).read(SKIP_BUFFER, 0, (int) Math.min(j2 - j, SKIP_BUFFER.length));
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedIOException();
            }
            if (read != -1) {
                this.bytesSkipped += read;
                bytesTransferred(read);
            } else {
                throw new EOFException();
            }
        }
    }

    protected final long bytesRead() {
        return this.bytesRead;
    }

    protected final long bytesRemaining() {
        long j = this.bytesToRead;
        return j == -1 ? j : j - this.bytesRead;
    }

    protected final long bytesSkipped() {
        return this.bytesSkipped;
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void clearRequestProperty(String str) {
        Assertions.checkNotNull(str);
        this.requestProperties.remove(str);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws HttpDataSource.HttpDataSourceException {
        if (this.opened) {
            this.opened = false;
            transferEnded();
            closeConnectionQuietly();
        }
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public int getResponseCode() {
        Response response = this.response;
        if (response == null) {
            return -1;
        }
        return response.code();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource, com.google.android.exoplayer2.upstream.HttpDataSource
    public Map<String, List<String>> getResponseHeaders() {
        Response response = this.response;
        return response == null ? Collections.emptyMap() : response.headers().toMultimap();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    @Nullable
    public Uri getUri() {
        Response response = this.response;
        if (response == null) {
            return null;
        }
        return Uri.parse(response.request().url().toString());
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        byte[] bArr;
        this.dataSpec = dataSpec;
        long j = 0;
        this.bytesRead = 0L;
        this.bytesSkipped = 0L;
        transferInitializing(dataSpec);
        try {
            this.response = this.callFactory.newCall(makeRequest(dataSpec)).execute();
            Response response = this.response;
            ResponseBody responseBody = (ResponseBody) Assertions.checkNotNull(response.body());
            this.responseByteStream = responseBody.byteStream();
            int code = response.code();
            if (!response.isSuccessful()) {
                try {
                    bArr = Util.toByteArray((InputStream) Assertions.checkNotNull(this.responseByteStream));
                } catch (IOException unused) {
                    bArr = Util.EMPTY_BYTE_ARRAY;
                }
                Map<String, List<String>> multimap = response.headers().toMultimap();
                closeConnectionQuietly();
                HttpDataSource.InvalidResponseCodeException invalidResponseCodeException = new HttpDataSource.InvalidResponseCodeException(code, response.message(), multimap, dataSpec, bArr);
                if (code == 416) {
                    invalidResponseCodeException.initCause(new DataSourceException(0));
                }
                throw invalidResponseCodeException;
            }
            MediaType contentType = responseBody.contentType();
            String mediaType = contentType != null ? contentType.toString() : "";
            Predicate<String> predicate = this.contentTypePredicate;
            if (predicate != null && !predicate.apply(mediaType)) {
                closeConnectionQuietly();
                throw new HttpDataSource.InvalidContentTypeException(mediaType, dataSpec);
            }
            if (code == 200) {
                long j2 = dataSpec.position;
                if (j2 != 0) {
                    j = j2;
                }
            }
            this.bytesToSkip = j;
            long j3 = dataSpec.length;
            long j4 = -1;
            if (j3 != -1) {
                this.bytesToRead = j3;
            } else {
                long contentLength = responseBody.contentLength();
                if (contentLength != -1) {
                    j4 = contentLength - this.bytesToSkip;
                }
                this.bytesToRead = j4;
            }
            this.opened = true;
            transferStarted(dataSpec);
            return this.bytesToRead;
        } catch (IOException e) {
            String message = e.getMessage();
            if (message != null && Util.toLowerInvariant(message).matches("cleartext communication.*not permitted.*")) {
                throw new HttpDataSource.CleartextNotPermittedException(e, dataSpec);
            }
            throw new HttpDataSource.HttpDataSourceException("Unable to connect", e, dataSpec, 1);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataReader
    public int read(byte[] bArr, int i, int i2) throws HttpDataSource.HttpDataSourceException {
        try {
            skipInternal();
            return readInternal(bArr, i, i2);
        } catch (IOException e) {
            throw new HttpDataSource.HttpDataSourceException(e, (DataSpec) Assertions.checkNotNull(this.dataSpec), 2);
        }
    }

    @Deprecated
    public void setContentTypePredicate(@Nullable Predicate<String> predicate) {
        this.contentTypePredicate = predicate;
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void setRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        this.requestProperties.set(str, str2);
    }

    @Deprecated
    public OkHttpDataSource(Call.Factory factory) {
        this(factory, null);
    }

    @Deprecated
    public OkHttpDataSource(Call.Factory factory, @Nullable String str) {
        this(factory, str, null, null);
    }

    @Deprecated
    public OkHttpDataSource(Call.Factory factory, @Nullable String str, @Nullable CacheControl cacheControl, @Nullable HttpDataSource.RequestProperties requestProperties) {
        this(factory, str, cacheControl, requestProperties, null);
    }

    private OkHttpDataSource(Call.Factory factory, @Nullable String str, @Nullable CacheControl cacheControl, @Nullable HttpDataSource.RequestProperties requestProperties, @Nullable Predicate<String> predicate) {
        super(true);
        this.callFactory = (Call.Factory) Assertions.checkNotNull(factory);
        this.userAgent = str;
        this.cacheControl = cacheControl;
        this.defaultRequestProperties = requestProperties;
        this.contentTypePredicate = predicate;
        this.requestProperties = new HttpDataSource.RequestProperties();
    }
}
