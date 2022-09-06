package com.amazon.photos.uploader.cds;

import android.os.SystemClock;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* compiled from: ProgressUpdateEnabledWriter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B3\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/uploader/cds/ProgressUpdateEnabledWriter;", "", "progressSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "", "inputStream", "Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "expectedContentLength", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "(Lio/reactivex/rxjava3/subjects/PublishSubject;Ljava/io/InputStream;Ljava/io/OutputStream;JLcom/amazon/photos/uploader/log/UploadLogger;)V", "inputToOutput", "blockSize", "", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ProgressUpdateEnabledWriter {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ProgressUpdateEnabledWriter";
    private final long expectedContentLength;
    private final InputStream inputStream;
    private final UploadLogger logger;
    private final OutputStream outputStream;
    private final PublishSubject<Long> progressSubject;

    /* compiled from: ProgressUpdateEnabledWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/cds/ProgressUpdateEnabledWriter$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ProgressUpdateEnabledWriter(@NotNull PublishSubject<Long> progressSubject, @NotNull InputStream inputStream, @NotNull OutputStream outputStream, long j, @NotNull UploadLogger logger) {
        Intrinsics.checkParameterIsNotNull(progressSubject, "progressSubject");
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(outputStream, "outputStream");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.progressSubject = progressSubject;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.expectedContentLength = j;
        this.logger = logger;
    }

    public final long inputToOutput(int i) {
        Ref.IntRef intRef = new Ref.IntRef();
        byte[] bArr = new byte[i];
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = 0;
        while (true) {
            try {
                try {
                    int read = this.inputStream.read(bArr, 0, bArr.length);
                    intRef.element = read;
                    if (read <= 0) {
                        break;
                    }
                    j += intRef.element;
                    this.progressSubject.onNext(Long.valueOf(j));
                    if (j > this.expectedContentLength) {
                        break;
                    }
                    this.outputStream.write(bArr, 0, intRef.element);
                } catch (Exception e) {
                    this.logger.e$AndroidPhotosUploader_release(TAG, "Exception received during writing to output stream.", e);
                    throw e;
                }
            } finally {
                UploadLogger uploadLogger = this.logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" Time to post body = ");
                outline107.append(SystemClock.elapsedRealtime() - elapsedRealtime);
                uploadLogger.v$AndroidPhotosUploader_release(TAG, outline107.toString());
            }
        }
        return j;
    }
}
