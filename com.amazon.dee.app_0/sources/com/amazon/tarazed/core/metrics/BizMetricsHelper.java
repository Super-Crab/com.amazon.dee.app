package com.amazon.tarazed.core.metrics;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* compiled from: BizMetricsHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\n2\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0013H\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\n2\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "sessionId", "", "getSessionId", "()Ljava/lang/String;", "setSessionId", "(Ljava/lang/String;)V", "createBizMetricJson", "Lorg/json/JSONObject;", "eventName", "metadata", "", "publishBizMetric", "", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BizMetricsHelper {
    private static final String BIZ_METRICS_APP_FIELD_NAME = "appName";
    private static final String BIZ_METRICS_DSN_FIELD_NAME = "dsn";
    private static final String BIZ_METRICS_EVENT_NAME_FIELD_NAME = "eventName";
    private static final String BIZ_METRICS_EVENT_TIMESTAMP_FIELD_NAME = "eventTimestamp";
    private static final String BIZ_METRICS_METADATA_FIELD_NAME = "metadata";
    private static final String BIZ_METRICS_METRIC_FIELD_NAME = "metric";
    private static final String BIZ_METRICS_METRIC_TYPE_FIELD_NAME = "type";
    private static final String BIZ_METRICS_METRIC_TYPE_VALUE = "EVENT";
    private static final String BIZ_METRICS_SESSION_ID_FIELD_NAME = "sessionId";
    private static final String BIZ_METRICS_UUID_FIELD_NAME = "id";
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_PUBLISH_BIZ_METRIC_FAILED = "PUBLISH_BIZ_METRIC_FAILED";
    private static final String TAG = "BizMetricsHelper";
    private final DeviceInfoUtility deviceInfoUtility;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    @Nullable
    private String sessionId;

    /* compiled from: BizMetricsHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/tarazed/core/metrics/BizMetricsHelper$Companion;", "", "()V", "BIZ_METRICS_APP_FIELD_NAME", "", "BIZ_METRICS_DSN_FIELD_NAME", "BIZ_METRICS_EVENT_NAME_FIELD_NAME", "BIZ_METRICS_EVENT_TIMESTAMP_FIELD_NAME", "BIZ_METRICS_METADATA_FIELD_NAME", "BIZ_METRICS_METRIC_FIELD_NAME", "BIZ_METRICS_METRIC_TYPE_FIELD_NAME", "BIZ_METRICS_METRIC_TYPE_VALUE", "BIZ_METRICS_SESSION_ID_FIELD_NAME", "BIZ_METRICS_UUID_FIELD_NAME", "METRIC_PUBLISH_BIZ_METRIC_FAILED", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BizMetricsHelper(@NotNull TarazedSessionLogger logger, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.logger = logger;
        this.deviceInfoUtility = deviceInfoUtility;
        this.metricsHelper = metricsHelper;
    }

    private final JSONObject createBizMetricJson(String str, Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
        double timeInMillis = calendar.getTimeInMillis() / 1000;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "EVENT");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("id", UUID.randomUUID().toString());
        jSONObject2.put("eventName", str);
        jSONObject2.put("eventTimestamp", timeInMillis);
        jSONObject2.put("sessionId", this.sessionId);
        jSONObject2.put("dsn", this.deviceInfoUtility.getSerialNumber());
        jSONObject2.put("metadata", new JSONObject((Map) map));
        jSONObject2.put("appName", this.deviceInfoUtility.getApp());
        jSONObject.put(BIZ_METRICS_METRIC_FIELD_NAME, jSONObject2);
        return jSONObject;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ JSONObject createBizMetricJson$default(BizMetricsHelper bizMetricsHelper, String str, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt__MapsKt.emptyMap();
        }
        return bizMetricsHelper.createBizMetricJson(str, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void publishBizMetric$default(BizMetricsHelper bizMetricsHelper, String str, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt__MapsKt.emptyMap();
        }
        bizMetricsHelper.publishBizMetric(str, map);
    }

    @Nullable
    public final String getSessionId() {
        return this.sessionId;
    }

    public final void publishBizMetric(@NotNull String eventName, @NotNull Map<String, String> metadata) {
        Intrinsics.checkParameterIsNotNull(eventName, "eventName");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "publishBizMetric - Publishing business metrics for event: " + eventName);
        if (this.sessionId == null) {
            this.metricsHelper.addCount(TAG, METRIC_PUBLISH_BIZ_METRIC_FAILED, 1.0d);
            return;
        }
        JSONObject createBizMetricJson = createBizMetricJson(eventName, metadata);
        TarazedSessionLogger tarazedSessionLogger2 = this.logger;
        tarazedSessionLogger2.i(TAG, "[BI_METRIC] " + createBizMetricJson);
    }

    public final void setSessionId(@Nullable String str) {
        this.sessionId = str;
    }
}
