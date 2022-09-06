package com.amazon.tarazed.core.logging;

import androidx.annotation.VisibleForTesting;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.amazon.tarazed.core.logging.api.LogUploadRequest;
import com.amazon.tarazed.core.types.Environment;
import io.ktor.client.HttpClient;
import io.ktor.client.HttpClientJvmKt;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedDETLogUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\r\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\nJ1\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedDETLogUploader;", "", "appender", "Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "(Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;)V", "createUploadTag", "", "deviceType", "getHttpClient", "Lio/ktor/client/HttpClient;", "getHttpClient$TarazedMobileCore_release", "postLogUpload", "", "detEndpoint", "detUploadTag", "requestParams", "Lcom/amazon/tarazed/core/logging/api/LogUploadRequest;", "log", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/tarazed/core/logging/api/LogUploadRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadLogs", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedDETLogUploader {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String DEVICE_EVENT_TRACKER_URL = "https://det-ta-g7g.amazon.com/DETLogServlet";
    private static final String DEVO_DEVICE_EVENT_TRACKER_URL = "https://det-ta-g7g.integ.amazon.com/DETLogServlet";
    private final TarazedLogFileAppender appender;

    /* compiled from: TarazedDETLogUploader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedDETLogUploader$Companion;", "", "()V", "DEVICE_EVENT_TRACKER_URL", "", "DEVO_DEVICE_EVENT_TRACKER_URL", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedDETLogUploader(@NotNull TarazedLogFileAppender appender) {
        Intrinsics.checkParameterIsNotNull(appender, "appender");
        this.appender = appender;
    }

    private final String createUploadTag(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM'_'dd'_'yyyy'_'HHmm'.'ss'_'z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        return str + "-Tarazed-" + simpleDateFormat.format(new Date());
    }

    @VisibleForTesting
    @NotNull
    public final HttpClient getHttpClient$TarazedMobileCore_release() {
        return HttpClientJvmKt.HttpClient$default(null, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01ec A[Catch: all -> 0x00c7, TryCatch #2 {all -> 0x00c7, blocks: (B:20:0x00bb, B:32:0x01db, B:34:0x01ec, B:36:0x01f1, B:38:0x0206, B:39:0x0209, B:50:0x0257, B:51:0x025e), top: B:61:0x00bb }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01f1 A[Catch: all -> 0x00c7, TryCatch #2 {all -> 0x00c7, blocks: (B:20:0x00bb, B:32:0x01db, B:34:0x01ec, B:36:0x01f1, B:38:0x0206, B:39:0x0209, B:50:0x0257, B:51:0x025e), top: B:61:0x00bb }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0249 A[Catch: all -> 0x0072, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0072, blocks: (B:13:0x006c, B:45:0x0249, B:48:0x024f, B:49:0x0256), top: B:60:0x006c }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x024f A[Catch: all -> 0x0072, TRY_ENTER, TryCatch #1 {all -> 0x0072, blocks: (B:13:0x006c, B:45:0x0249, B:48:0x024f, B:49:0x0256), top: B:60:0x006c }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0257 A[Catch: all -> 0x00c7, TRY_ENTER, TryCatch #2 {all -> 0x00c7, blocks: (B:20:0x00bb, B:32:0x01db, B:34:0x01ec, B:36:0x01f1, B:38:0x0206, B:39:0x0209, B:50:0x0257, B:51:0x025e), top: B:61:0x00bb }] */
    /* JADX WARN: Type inference failed for: r12v18, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.io.Closeable] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object postLogUpload(@org.jetbrains.annotations.NotNull java.lang.String r19, @org.jetbrains.annotations.NotNull java.lang.String r20, @org.jetbrains.annotations.NotNull com.amazon.tarazed.core.logging.api.LogUploadRequest r21, @org.jetbrains.annotations.NotNull java.lang.String r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            Method dump skipped, instructions count: 616
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.logging.TarazedDETLogUploader.postLogUpload(java.lang.String, java.lang.String, com.amazon.tarazed.core.logging.api.LogUploadRequest, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void uploadLogs(@NotNull LogUploadRequest requestParams) {
        Intrinsics.checkParameterIsNotNull(requestParams, "requestParams");
        this.appender.stop();
        try {
            StringBuilder sb = new StringBuilder();
            this.appender.forEachLine(new TarazedDETLogUploader$uploadLogs$1(sb));
            if (sb.length() == 0) {
                return;
            }
            BuildersKt__BuildersKt.runBlocking$default(null, new TarazedDETLogUploader$uploadLogs$2(this, Environment.BETA == requestParams.getEnvironment() ? DEVO_DEVICE_EVENT_TRACKER_URL : DEVICE_EVENT_TRACKER_URL, createUploadTag(requestParams.getDeviceType()), requestParams, sb, null), 1, null);
            this.appender.clearLogs();
        } finally {
            this.appender.start();
        }
    }
}
