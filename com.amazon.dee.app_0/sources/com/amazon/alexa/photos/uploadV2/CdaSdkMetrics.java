package com.amazon.alexa.photos.uploadV2;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.EnumMap;
import retrofit2.HttpException;
/* loaded from: classes9.dex */
public class CdaSdkMetrics implements SdkMetrics {
    @NonNull
    @VisibleForTesting
    static final String CDS_COMPONENT = "CDSMetrics";
    @NonNull
    @VisibleForTesting
    static final String CDUS_COMPONENT = "CdusMetrics";
    @NonNull
    @VisibleForTesting
    static final String ERROR_CODE_TYPE = "Code";
    @NonNull
    @VisibleForTesting
    static final String ERROR_SUFFIX = "Error";
    @NonNull
    @VisibleForTesting
    static final String OVERALL_METRIC = "Overall";
    @NonNull
    private static final EnumMap<SdkMetrics.Service, String> SERVICE_TO_TAG_MAP = createServiceToTagMap();
    @NonNull
    @VisibleForTesting
    static final String SUCCESS_SUFFIX = "Success";
    @NonNull
    private static final String TAG = "CdaSdkMetrics";
    @NonNull
    @VisibleForTesting
    static final String TIME_SUFFIX = "Time";
    @NonNull
    @VisibleForTesting
    static final String UNKNOWN_CDASDK_METRICS = "UnknownCdaSdkMetrics";
    @NonNull
    private final Metrics metrics;

    public CdaSdkMetrics(@NonNull Metrics metrics) {
        this.metrics = metrics;
    }

    @NonNull
    private static EnumMap<SdkMetrics.Service, String> createServiceToTagMap() {
        EnumMap<SdkMetrics.Service, String> enumMap = new EnumMap<>(SdkMetrics.Service.class);
        enumMap.put((EnumMap<SdkMetrics.Service, String>) SdkMetrics.Service.CDUS, (SdkMetrics.Service) CDUS_COMPONENT);
        enumMap.put((EnumMap<SdkMetrics.Service, String>) SdkMetrics.Service.CDS, (SdkMetrics.Service) CDS_COMPONENT);
        return enumMap;
    }

    @NonNull
    private String getComponentNameForService(@NonNull final SdkMetrics.Service service) {
        String str = SERVICE_TO_TAG_MAP.get(service);
        if (str != null) {
            return str;
        }
        this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$eQt1XnNDxMBUG7PS2JHJbLrA-NE
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                return CdaSdkMetrics.lambda$getComponentNameForService$10(SdkMetrics.Service.this);
            }
        }, new MetricRecordingType[0]);
        return UNKNOWN_CDASDK_METRICS;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$getComponentNameForService$10(SdkMetrics.Service service) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UnknownCdaSdkMetrics_");
        outline107.append(service.name());
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$recordCallError$5() {
        return "OverallError";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$recordCallError$6(String str, Exception exc, int i) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "Error");
        outline113.append(exc.getClass().getSimpleName());
        outline113.append("Code");
        outline113.append(i);
        return outline113.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$recordCallError$7(Exception exc, int i) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OverallError");
        outline107.append(exc.getClass().getSimpleName());
        outline107.append("Code");
        outline107.append(i);
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$recordCallError$8(String str, Exception exc) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "Error");
        outline113.append(exc.getClass().getSimpleName());
        return outline113.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$recordCallError$9(Exception exc) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OverallError");
        outline107.append(exc.getClass().getSimpleName());
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$recordCallSuccess$1() {
        return "OverallSuccess";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$recordCallSuccess$3() {
        return "OverallTime";
    }

    @Override // com.amazon.clouddrive.cdasdk.SdkMetrics
    public void recordCallError(@NonNull SdkMetrics.Service service, @NonNull final String str, @NonNull final Exception exc) {
        ClientMetric clientMetric = new ClientMetric();
        clientMetric.addCounter(new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$MrIhaOziiEyujJfk2ROpIAqxQv8
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                String outline72;
                outline72 = GeneratedOutlineSupport1.outline72(str, "Error");
                return outline72;
            }
        }, 1);
        clientMetric.addCounter($$Lambda$CdaSdkMetrics$3E54hD5doAmJugUTAiAWmseMLc8.INSTANCE, 1);
        boolean z = exc instanceof HttpException;
        if (!z && !(exc instanceof CloudDriveException)) {
            clientMetric.addCounter(new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$Gd64RT7uYJNbF7UuN_AN69P815o
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                public final String getEventName() {
                    return CdaSdkMetrics.lambda$recordCallError$8(str, exc);
                }
            }, 1);
            clientMetric.addCounter(new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$wV1pJovLfoXLwf0bQtOYIP1qOnc
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                public final String getEventName() {
                    return CdaSdkMetrics.lambda$recordCallError$9(exc);
                }
            }, 1);
        } else {
            final int code = z ? ((HttpException) exc).code() : ((CloudDriveException) exc).getCode();
            clientMetric.addCounter(new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$ek_eHEy33Hssr_754sTq5p8uE4U
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                public final String getEventName() {
                    return CdaSdkMetrics.lambda$recordCallError$6(str, exc, code);
                }
            }, 1);
            clientMetric.addCounter(new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$FMjC3ZsQB7NUXoH77lvEpyXt9Io
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                public final String getEventName() {
                    return CdaSdkMetrics.lambda$recordCallError$7(exc, code);
                }
            }, 1);
        }
        this.metrics.recordCustomMetric(getComponentNameForService(service), clientMetric, new MetricRecordingType[0]);
    }

    @Override // com.amazon.clouddrive.cdasdk.SdkMetrics
    public void recordCallSuccess(@NonNull SdkMetrics.Service service, @NonNull final String str, long j) {
        ClientMetric clientMetric = new ClientMetric();
        clientMetric.addCounter(new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$9mVo-1jNIadORE5NXf5vO01WX5k
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                String outline72;
                outline72 = GeneratedOutlineSupport1.outline72(str, "Success");
                return outline72;
            }
        }, 1);
        clientMetric.addCounter($$Lambda$CdaSdkMetrics$PCo5cQ0Ao0RgP5uwsO8VZ8k_Auk.INSTANCE, 1);
        MetricName metricName = new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$k7uJFjlTTYmvit0SZb4XBxhEXSM
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                String outline72;
                outline72 = GeneratedOutlineSupport1.outline72(str, CdaSdkMetrics.TIME_SUFFIX);
                return outline72;
            }
        };
        double d = j;
        clientMetric.addTimer(metricName, d);
        clientMetric.addTimer($$Lambda$CdaSdkMetrics$nBqqDpNW9ximx6bwYEXiE7CDymM.INSTANCE, d);
        this.metrics.recordCustomMetric(getComponentNameForService(service), clientMetric, new MetricRecordingType[0]);
    }
}
