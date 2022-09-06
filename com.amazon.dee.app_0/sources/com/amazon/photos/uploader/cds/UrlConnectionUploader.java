package com.amazon.photos.uploader.cds;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.StandardHeaderInterceptor;
import com.amazon.clouddrive.cdasdk.cdp.ConflictResolution;
import com.amazon.clouddrive.cdasdk.cds.common.ContentSignatureType;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cdus.CDUSCalls;
import com.amazon.clouddrive.cdasdk.cdus.CDUSError;
import com.amazon.clouddrive.cdasdk.cdus.CDUSException;
import com.amazon.clouddrive.cdasdk.cdus.CDUSRetrofitBinding;
import com.amazon.clouddrive.cdasdk.cdus.UploadContentRequest;
import com.amazon.clouddrive.internal.MultiPartPostBodyWriterHelper;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.cds.multipart.LocalValidationException;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: UrlConnectionUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 _2\u00020\u0001:\u0001_B/\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fB=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u001d\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0001¢\u0006\u0002\b\u001cJ\u0012\u0010\u001d\u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J;\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020)H\u0001¢\u0006\u0002\b+J\u0015\u0010,\u001a\u00020#2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b-J\"\u0010.\u001a\u00020\u00192\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190100H\u0002J%\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0019H\u0001¢\u0006\u0002\b8J\"\u00109\u001a\u0002032\b\u00104\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0019H\u0002J2\u0010:\u001a\b\u0012\u0004\u0012\u00020!0;2\u0006\u0010<\u001a\u00020=2\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020)J4\u0010>\u001a\b\u0012\u0004\u0012\u00020!0;2\u0006\u0010<\u001a\u00020=2\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020)H\u0002J3\u0010?\u001a\u00020@2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u00104\u001a\u0002052\u0006\u0010A\u001a\u00020B2\u0006\u0010*\u001a\u00020)H\u0001¢\u0006\u0002\bCJ+\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2\u0006\u00104\u001a\u0002052\f\u0010F\u001a\b\u0012\u0004\u0012\u0002HE0GH\u0001¢\u0006\u0004\bH\u0010IJ\u0018\u0010J\u001a\u00020\u00132\u0006\u0010K\u001a\u00020\u00192\u0006\u0010L\u001a\u00020)H\u0002J\u0018\u0010M\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\u00192\u0006\u0010O\u001a\u000203H\u0002J\u0018\u0010P\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\u00192\u0006\u0010Q\u001a\u00020RH\u0002J\u0010\u0010S\u001a\u00020T2\u0006\u0010\"\u001a\u00020#H\u0002J<\u0010U\u001a\b\u0012\u0004\u0012\u00020!0;2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020)H\u0002J*\u0010V\u001a\b\u0012\u0004\u0012\u0002HW0;\"\u0004\b\u0000\u0010W2\u0006\u0010K\u001a\u00020\u00192\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HW0;H\u0002J\u0010\u0010Y\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020TH\u0002J\u0010\u0010[\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020TH\u0002J.\u0010\\\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020T2\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020)H\u0002J.\u0010]\u001a\u00020\u00132\u0006\u0010A\u001a\u00020B2\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020)H\u0002J\u0018\u0010^\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020T2\u0006\u0010$\u001a\u00020\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"Lcom/amazon/photos/uploader/cds/UrlConnectionUploader;", "", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "urlConnectionProvider", "Lcom/amazon/photos/uploader/cds/UrlConnectionProvider;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "frameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "(Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/photos/uploader/cds/UrlConnectionProvider;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/UploadFrameworkContext;)V", "objectMapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "timeout", "", "(Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/photos/uploader/cds/UrlConnectionProvider;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/fasterxml/jackson/databind/ObjectMapper;ILcom/amazon/photos/uploader/UploadFrameworkContext;)V", "addQueryParameters", "", "builder", "Landroid/net/Uri$Builder;", "uploadContentRequest", "Lcom/amazon/clouddrive/cdasdk/cdus/UploadContentRequest;", "base64", "", "kotlin.jvm.PlatformType", "applicationId", "base64$AndroidPhotosUploader_release", "closeQuietly", "closeable", "Ljava/io/Closeable;", "connectAndUpload", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "url", "Ljava/net/URL;", SierraContentProviderContract.MD5_VALUE, "contentUri", "Landroid/net/Uri;", "progressSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "", "fileSize", "connectAndUpload$AndroidPhotosUploader_release", "createUrl", "createUrl$AndroidPhotosUploader_release", "getHeaders", HttpClientModule.ElementsRequestKey.HEADERS, "", "", "parseErrorStream", "Lcom/amazon/clouddrive/cdasdk/CloudDriveException;", "inputStream", "Ljava/io/InputStream;", "responseCode", "message", "parseErrorStream$AndroidPhotosUploader_release", "parseServiceError", "postNode", "Lio/reactivex/rxjava3/core/Single;", "uploadDataPair", "Lcom/amazon/photos/uploader/cds/UploadDataPair;", "postNodeInternal", "progressUpdateEnabledWriter", "Lcom/amazon/photos/uploader/cds/ProgressUpdateEnabledWriter;", "outputStream", "Ljava/io/OutputStream;", "progressUpdateEnabledWriter$AndroidPhotosUploader_release", "readValue", ExifInterface.GPS_DIRECTION_TRUE, "valueType", "Ljava/lang/Class;", "readValue$AndroidPhotosUploader_release", "(Ljava/io/InputStream;Ljava/lang/Class;)Ljava/lang/Object;", "recordCallSuccess", "callName", "duration", "recordErrorMetric", "metricEventName", ADMRegistrationConstants.CALL_EXCEPTION, "recordUnknownErrorMetric", "throwable", "", "setupConnection", "Ljava/net/HttpURLConnection;", "upload", "wrapCallForMetrics", "O", "source", "writeBodyHeaders", "connection", "writeDefaultHeaders", "writePostData", "writeRequest", "writeRequestHeaders", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UrlConnectionUploader {
    private static final int BLOCK_SIZE = 32768;
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String POST_METHOD = "POST";
    private static final String TAG = "UrlConnectionUploader";
    private static final long TIME_OUT_SECONDS = 60;
    private final CDClient cdClient;
    private final UploadFrameworkContext frameworkContext;
    private final UploadLogger logger;
    private final Metrics metrics;
    private final ObjectMapper objectMapper;
    private final int timeout;
    private final UrlConnectionProvider urlConnectionProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UrlConnectionUploader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/cds/UrlConnectionUploader$Companion;", "", "()V", "BLOCK_SIZE", "", "POST_METHOD", "", "TAG", "TIME_OUT_SECONDS", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public UrlConnectionUploader(@NotNull CDClient cdClient, @NotNull UploadLogger logger, @NotNull UrlConnectionProvider urlConnectionProvider, @NotNull Metrics metrics, @NotNull ObjectMapper objectMapper, int i, @NotNull UploadFrameworkContext frameworkContext) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(urlConnectionProvider, "urlConnectionProvider");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(objectMapper, "objectMapper");
        Intrinsics.checkParameterIsNotNull(frameworkContext, "frameworkContext");
        this.cdClient = cdClient;
        this.logger = logger;
        this.urlConnectionProvider = urlConnectionProvider;
        this.metrics = metrics;
        this.objectMapper = objectMapper;
        this.timeout = i;
        this.frameworkContext = frameworkContext;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private final void addQueryParameters(Uri.Builder builder, UploadContentRequest uploadContentRequest) {
        builder.appendQueryParameter(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, uploadContentRequest.getKind()).appendQueryParameter("addToFamilyArchive", String.valueOf(uploadContentRequest.getAddToFamilyArchive().booleanValue())).appendQueryParameter("name", uploadContentRequest.getName()).appendQueryParameter("fileSize", String.valueOf(uploadContentRequest.getFileSize().longValue()));
        String contentDate = uploadContentRequest.getContentDate();
        if (contentDate != null) {
            builder.appendQueryParameter("contentDate", contentDate);
            builder.appendQueryParameter("fallbackContentDate", contentDate);
        }
        ConflictResolution conflictResolution = uploadContentRequest.getConflictResolution();
        if (conflictResolution != null) {
            builder.appendQueryParameter("conflictResolution", conflictResolution.name());
        }
        String suppress = uploadContentRequest.getSuppress();
        if (suppress != null) {
            builder.appendQueryParameter("suppress", suppress);
        }
        String parentNodeId = uploadContentRequest.getParentNodeId();
        if (parentNodeId != null) {
            builder.appendQueryParameter("parentNodeId", parentNodeId);
        }
        String contentSignature = uploadContentRequest.getContentSignature();
        if (contentSignature != null) {
            builder.appendQueryParameter("contentSignature", contentSignature);
            builder.appendQueryParameter("contentSignatureType", ContentSignatureType.MD5.name());
        }
    }

    private final void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private final String getHeaders(Map<String, ? extends List<String>> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ? extends List<String>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(RealTimeTextConstants.COLON_SPACE);
            sb.append(entry.getValue());
            sb.append("\n");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }

    private final CloudDriveException parseServiceError(InputStream inputStream, int i, String str) {
        if (inputStream == null) {
            this.metrics.recordSimpleEvent(TAG, UrlConnectionUploader$parseServiceError$1.INSTANCE, new MetricRecordingType[0]);
            return new CloudDriveException(i, str);
        }
        return parseErrorStream$AndroidPhotosUploader_release(inputStream, i, str);
    }

    private final Single<NodeInfo> postNodeInternal(UploadDataPair uploadDataPair, Uri uri, PublishSubject<Long> publishSubject, long j) {
        return upload(createUrl$AndroidPhotosUploader_release(uploadDataPair.getUploadContentRequest()), uploadDataPair.getMd5(), uri, publishSubject, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordCallSuccess(final String str, long j) {
        this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$recordCallSuccess$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return GeneratedOutlineSupport1.outline91(new StringBuilder(), str, "Success");
            }
        }, 1).addTimer(new MetricName() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$recordCallSuccess$clientMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return GeneratedOutlineSupport1.outline91(new StringBuilder(), str, "Time");
            }
        }, j).withTagName(TAG), new MetricRecordingType[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordErrorMetric(final String str, final CloudDriveException cloudDriveException) {
        ClientMetric withTagName = new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$recordErrorMetric$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return GeneratedOutlineSupport1.outline91(new StringBuilder(), str, "Error");
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$recordErrorMetric$clientMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + JsonReaderKt.COLON + cloudDriveException.getClass().getSimpleName() + "_HttpCode_" + cloudDriveException.getCode();
            }
        }, 1).withTagName(TAG);
        if (cloudDriveException instanceof CDUSException) {
            withTagName.addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$recordErrorMetric$1
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(JsonReaderKt.COLON);
                    sb.append(((CDUSException) cloudDriveException).getClass().getSimpleName());
                    sb.append("_ErrorCode_");
                    CDUSError cdusError = ((CDUSException) cloudDriveException).getCdusError();
                    Intrinsics.checkExpressionValueIsNotNull(cdusError, "exception.cdusError");
                    sb.append(cdusError.getErrorCode());
                    return sb.toString();
                }
            }, 1);
        }
        this.metrics.recordCustomMetric(TAG, withTagName, new MetricRecordingType[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordUnknownErrorMetric(String str, Throwable th) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ":UnknownError_");
        outline113.append(th.getClass().getSimpleName());
        final String sb = outline113.toString();
        this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$recordUnknownErrorMetric$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return sb;
            }
        }, 1).withTagName(TAG), new MetricRecordingType[0]);
    }

    private final HttpURLConnection setupConnection(URL url) {
        HttpURLConnection createHttpURLConnection = this.urlConnectionProvider.createHttpURLConnection(url);
        createHttpURLConnection.setReadTimeout(this.timeout);
        createHttpURLConnection.setConnectTimeout(this.timeout);
        return createHttpURLConnection;
    }

    private final Single<NodeInfo> upload(final URL url, final String str, final Uri uri, final PublishSubject<Long> publishSubject, final long j) {
        Single<NodeInfo> fromCallable = Single.fromCallable(new Callable<T>() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$upload$1
            @Override // java.util.concurrent.Callable
            @NotNull
            /* renamed from: call */
            public final NodeInfo mo4364call() {
                return UrlConnectionUploader.this.connectAndUpload$AndroidPhotosUploader_release(url, str, uri, publishSubject, j);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Single.fromCallable {\n  …ject, fileSize)\n        }");
        return fromCallable;
    }

    private final <O> Single<O> wrapCallForMetrics(String str, Single<O> single) {
        Single<O> single2 = (Single<O>) single.compose(new UrlConnectionUploader$wrapCallForMetrics$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(single2, "source.compose(object : …\n            }\n        })");
        return single2;
    }

    private final void writeBodyHeaders(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.addRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
    }

    private final void writeDefaultHeaders(HttpURLConnection httpURLConnection) {
        httpURLConnection.addRequestProperty("user-agent", this.urlConnectionProvider.userAgent());
        httpURLConnection.addRequestProperty(StandardHeaderInterceptor.APP_ID_HEADER, base64$AndroidPhotosUploader_release(this.urlConnectionProvider.applicationId()));
    }

    private final void writePostData(HttpURLConnection httpURLConnection, Uri uri, PublishSubject<Long> publishSubject, long j) {
        OutputStream outputStream = null;
        try {
            try {
                UploadLogger uploadLogger = this.logger;
                StringBuilder sb = new StringBuilder();
                sb.append("writePostData request headers = ");
                sb.append(this.logger.obfuscate$AndroidPhotosUploader_release(httpURLConnection.getURL().toString()));
                sb.append(Chars.SPACE);
                UploadLogger uploadLogger2 = this.logger;
                Map<String, List<String>> requestProperties = httpURLConnection.getRequestProperties();
                Intrinsics.checkExpressionValueIsNotNull(requestProperties, "connection.requestProperties");
                sb.append(uploadLogger2.obfuscate$AndroidPhotosUploader_release(getHeaders(requestProperties)));
                uploadLogger.d$AndroidPhotosUploader_release(TAG, sb.toString());
                outputStream = httpURLConnection.getOutputStream();
                httpURLConnection.connect();
                Intrinsics.checkExpressionValueIsNotNull(outputStream, "outputStream");
                writeRequest(outputStream, uri, publishSubject, j);
                outputStream.flush();
                Unit unit = Unit.INSTANCE;
            } catch (Exception e) {
                this.logger.e$AndroidPhotosUploader_release(TAG, "Exception received during writePostData.", e);
                throw e;
            }
        } finally {
            closeQuietly(outputStream);
        }
    }

    private final void writeRequest(OutputStream outputStream, Uri uri, PublishSubject<Long> publishSubject, long j) {
        InputStream openInputStream = this.frameworkContext.getApplicationContext().getContentResolver().openInputStream(uri);
        try {
            if (openInputStream != null) {
                if (progressUpdateEnabledWriter$AndroidPhotosUploader_release(publishSubject, openInputStream, outputStream, j).inputToOutput(32768) == j) {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(openInputStream, null);
                    return;
                }
                throw new LocalValidationException("Bytes transferred should be equal to file length.");
            }
            throw new FileNotFoundException("Unable to open file content Uri");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(openInputStream, th);
                throw th2;
            }
        }
    }

    private final void writeRequestHeaders(HttpURLConnection httpURLConnection, String str) {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        httpURLConnection.setRequestProperty(CDUSRetrofitBinding.MD5_HEADER, str);
        httpURLConnection.setRequestProperty("x-amzn-RequestId", uuid);
        httpURLConnection.setRequestProperty("x-amzn-Trace-Id", uuid);
        httpURLConnection.setChunkedStreamingMode(0);
        httpURLConnection.setRequestProperty(HttpHeaders.TRANSFER_ENCODING, "chunked");
        httpURLConnection.setRequestProperty("Content-Type", MultiPartPostBodyWriterHelper.HEADER_CONTENT_TYPE);
    }

    @VisibleForTesting
    public final String base64$AndroidPhotosUploader_release(@NotNull String applicationId) {
        Intrinsics.checkParameterIsNotNull(applicationId, "applicationId");
        byte[] bytes = applicationId.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return Base64.encodeToString(bytes, 3);
    }

    @VisibleForTesting
    @NotNull
    public final NodeInfo connectAndUpload$AndroidPhotosUploader_release(@NotNull URL url, @NotNull String md5, @NotNull Uri contentUri, @NotNull PublishSubject<Long> progressSubject, long j) {
        HttpURLConnection httpURLConnection;
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(contentUri, "contentUri");
        Intrinsics.checkParameterIsNotNull(progressSubject, "progressSubject");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            httpURLConnection = setupConnection(url);
        } catch (Throwable th) {
            th = th;
            httpURLConnection = null;
        }
        try {
            writeDefaultHeaders(httpURLConnection);
            writeBodyHeaders(httpURLConnection);
            writeRequestHeaders(httpURLConnection, md5);
            writePostData(httpURLConnection, contentUri, progressSubject, j);
            int responseCode = httpURLConnection.getResponseCode();
            UploadLogger uploadLogger = this.logger;
            StringBuilder sb = new StringBuilder();
            sb.append("connectAndUpload response headers = ");
            sb.append(this.logger.obfuscate$AndroidPhotosUploader_release(httpURLConnection.getURL().toString()));
            sb.append(Chars.SPACE);
            UploadLogger uploadLogger2 = this.logger;
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            Intrinsics.checkExpressionValueIsNotNull(headerFields, "connection.headerFields");
            sb.append(uploadLogger2.obfuscate$AndroidPhotosUploader_release(getHeaders(headerFields)));
            uploadLogger.d$AndroidPhotosUploader_release(TAG, sb.toString());
            if (responseCode >= 200 && responseCode < 300) {
                Object readValue = this.objectMapper.readValue(httpURLConnection.getInputStream(), NodeInfo.class);
                Intrinsics.checkExpressionValueIsNotNull(readValue, "objectMapper.readValue(c…am, NodeInfo::class.java)");
                NodeInfo nodeInfo = (NodeInfo) readValue;
                httpURLConnection.disconnect();
                UploadLogger uploadLogger3 = this.logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Time to upload = ");
                outline107.append(SystemClock.elapsedRealtime() - elapsedRealtime);
                uploadLogger3.d$AndroidPhotosUploader_release(TAG, outline107.toString());
                return nodeInfo;
            }
            String str = responseCode + " received from the url " + this.logger.obfuscate$AndroidPhotosUploader_release(url.toString());
            this.logger.e$AndroidPhotosUploader_release(TAG, str);
            throw parseServiceError(httpURLConnection.getErrorStream(), responseCode, str);
        } catch (Throwable th2) {
            th = th2;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            UploadLogger uploadLogger4 = this.logger;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Time to upload = ");
            outline1072.append(SystemClock.elapsedRealtime() - elapsedRealtime);
            uploadLogger4.d$AndroidPhotosUploader_release(TAG, outline1072.toString());
            throw th;
        }
    }

    @VisibleForTesting
    @NotNull
    public final URL createUrl$AndroidPhotosUploader_release(@NotNull UploadContentRequest uploadContentRequest) {
        Intrinsics.checkParameterIsNotNull(uploadContentRequest, "uploadContentRequest");
        CDUSCalls cDUSCalls = this.cdClient.getCDUSCalls();
        Intrinsics.checkExpressionValueIsNotNull(cDUSCalls, "cdClient.cdusCalls");
        Uri.Builder buildUpon = Uri.parse(cDUSCalls.getBaseUrl()).buildUpon();
        Intrinsics.checkExpressionValueIsNotNull(buildUpon, "baseUri.buildUpon()");
        buildUpon.appendEncodedPath("v2/upload");
        addQueryParameters(buildUpon, uploadContentRequest);
        String uri = buildUpon.build().toString();
        Intrinsics.checkExpressionValueIsNotNull(uri, "builder.build().toString()");
        return new URL(uri);
    }

    @VisibleForTesting
    @NotNull
    public final CloudDriveException parseErrorStream$AndroidPhotosUploader_release(@NotNull InputStream inputStream, int i, @NotNull String message) {
        CloudDriveException cloudDriveException;
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(message, "message");
        try {
            try {
                cloudDriveException = new CDUSException(i, message, (CDUSError) readValue$AndroidPhotosUploader_release(inputStream, CDUSError.class));
            } catch (Exception e) {
                this.logger.e$AndroidPhotosUploader_release(TAG, "Call response body wasn't able to be recognized/processed", e);
                this.metrics.recordSimpleErrorEvent(TAG, UrlConnectionUploader$parseErrorStream$1$1.INSTANCE, e);
                cloudDriveException = new CloudDriveException(i, message);
            }
            CloseableKt.closeFinally(inputStream, null);
            return cloudDriveException;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(inputStream, th);
                throw th2;
            }
        }
    }

    @NotNull
    public final Single<NodeInfo> postNode(@NotNull UploadDataPair uploadDataPair, @NotNull Uri contentUri, @NotNull PublishSubject<Long> progressSubject, long j) {
        Intrinsics.checkParameterIsNotNull(uploadDataPair, "uploadDataPair");
        Intrinsics.checkParameterIsNotNull(contentUri, "contentUri");
        Intrinsics.checkParameterIsNotNull(progressSubject, "progressSubject");
        return wrapCallForMetrics("postNode", postNodeInternal(uploadDataPair, contentUri, progressSubject, j));
    }

    @VisibleForTesting
    @NotNull
    public final ProgressUpdateEnabledWriter progressUpdateEnabledWriter$AndroidPhotosUploader_release(@NotNull PublishSubject<Long> progressSubject, @NotNull InputStream inputStream, @NotNull OutputStream outputStream, long j) {
        Intrinsics.checkParameterIsNotNull(progressSubject, "progressSubject");
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(outputStream, "outputStream");
        return new ProgressUpdateEnabledWriter(progressSubject, inputStream, outputStream, j, this.logger);
    }

    @VisibleForTesting
    public final <T> T readValue$AndroidPhotosUploader_release(@NotNull InputStream inputStream, @NotNull Class<T> valueType) {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(valueType, "valueType");
        return (T) this.objectMapper.readValue(inputStream, valueType);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UrlConnectionUploader(@NotNull CDClient cdClient, @NotNull UploadLogger logger, @NotNull UrlConnectionProvider urlConnectionProvider, @NotNull Metrics metrics, @NotNull UploadFrameworkContext frameworkContext) {
        this(cdClient, logger, urlConnectionProvider, metrics, new ObjectMapper(), (int) TimeUnit.SECONDS.toMillis(60L), frameworkContext);
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(urlConnectionProvider, "urlConnectionProvider");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(frameworkContext, "frameworkContext");
    }
}
