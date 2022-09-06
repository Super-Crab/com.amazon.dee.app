package com.amazon.clouddrive.cdasdk;

import android.content.ContentResolver;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.rxjava3.subjects.Subject;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.annotation.Nonnull;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
/* loaded from: classes11.dex */
public final class ProgressReportingRequestBody extends RequestBody {
    static final long READ_SIZE_BYTES = 8096;
    @Nullable
    private final Subject<ProgressUpdate> progressSubject;
    @NonNull
    private final SourceData sourceData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface SourceData {
        @Nonnull
        MediaType getContentType();

        @Nonnull
        Source getSource() throws IOException;

        long totalContentLength() throws IOException;
    }

    private ProgressReportingRequestBody(@NonNull SourceData sourceData, @Nullable Subject<ProgressUpdate> subject) {
        this.sourceData = sourceData;
        this.progressSubject = subject;
    }

    @NonNull
    public static ProgressReportingRequestBody createProgressRequestBody(@NonNull final MediaType mediaType, @NonNull final File file, @NonNull Subject<ProgressUpdate> subject) {
        return new ProgressReportingRequestBody(new SourceData() { // from class: com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.1
            @Override // com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.SourceData
            @Nonnull
            public MediaType getContentType() {
                return MediaType.this;
            }

            @Override // com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.SourceData
            @Nonnull
            public Source getSource() throws FileNotFoundException {
                return Okio.source(file);
            }

            @Override // com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.SourceData
            public long totalContentLength() {
                return file.length();
            }
        }, subject);
    }

    @NonNull
    public static ProgressReportingRequestBody createUriSourceRequestBody(@NonNull final ContentResolver contentResolver, @NonNull final MediaType mediaType, @NonNull final Uri uri, final long j, @javax.annotation.Nullable Subject<ProgressUpdate> subject) {
        return new ProgressReportingRequestBody(new SourceData() { // from class: com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.3
            @Override // com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.SourceData
            @Nonnull
            public MediaType getContentType() {
                return MediaType.this;
            }

            @Override // com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.SourceData
            @Nonnull
            public Source getSource() throws FileNotFoundException {
                return Okio.source(contentResolver.openInputStream(uri));
            }

            @Override // com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.SourceData
            public long totalContentLength() {
                return j;
            }
        }, subject);
    }

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        return this.sourceData.totalContentLength();
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.sourceData.getContentType();
    }

    @Override // okhttp3.RequestBody
    public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException, IllegalStateException {
        writeTo(bufferedSink, true);
    }

    @NonNull
    public static ProgressReportingRequestBody createProgressRequestBody(@NonNull final MediaType mediaType, @NonNull final ByteArrayInputStream byteArrayInputStream, @NonNull Subject<ProgressUpdate> subject) {
        final long available = byteArrayInputStream.available();
        return new ProgressReportingRequestBody(new SourceData() { // from class: com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.2
            @Override // com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.SourceData
            @Nonnull
            public MediaType getContentType() {
                return MediaType.this;
            }

            @Override // com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.SourceData
            @Nonnull
            public Source getSource() {
                return Okio.source(byteArrayInputStream);
            }

            @Override // com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody.SourceData
            public long totalContentLength() {
                return available;
            }
        }, subject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeTo(@NonNull BufferedSink bufferedSink, boolean z) throws IOException, IllegalStateException {
        Buffer buffer = new Buffer();
        Source source = this.sourceData.getSource();
        long j = 0;
        try {
            for (long read = source.read(buffer, READ_SIZE_BYTES); read != -1; read = source.read(buffer, READ_SIZE_BYTES)) {
                bufferedSink.write(buffer, read);
                j += read;
                if (this.progressSubject != null && z) {
                    this.progressSubject.onNext(new ProgressUpdate(j, this.sourceData.totalContentLength()));
                }
            }
            source.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (source != null) {
                    try {
                        source.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
