package com.amazon.tarazed.core.metrics;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.registry.TarazedComponentRegistry;
import com.amazon.tarazed.core.registry.component.AccountMetadataProvider;
import com.amazon.tarazed.core.type.Account;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedMetricsHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 *2\u00020\u0001:\u0001*B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017J(\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u001aJ\u001e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017J&\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0017J\u001e\u0010!\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u0017J(\u0010#\u001a\"\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u000ej\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u000b`\u000fH\u0002J\b\u0010$\u001a\u00020\u000bH\u0002J\u001e\u0010%\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0017J\u0015\u0010&\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u000bH\u0001¢\u0006\u0002\b'J\u0016\u0010(\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bJ\u0016\u0010)\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\r\u001a\"\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u000ej\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u000b`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "metricEventCache", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/amazon/client/metrics/common/MetricEvent;", "metricEventPivots", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "metricsFactory", "Lcom/amazon/client/metrics/common/MetricsFactory;", "addCount", "", "methodName", "metricName", "count", "", "priority", "Lcom/amazon/client/metrics/common/Priority;", "", "addCountHighPriority", "addIoExceptionCount", "metricNamePrefix", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/io/IOException;", "increment", "addMetricTimer", "time", "getMetricPivots", "getPreferredMarketplace", "incrementMetricCounter", "obtainMetricEvent", "obtainMetricEvent$TarazedMobileCore_release", "startMetricTimer", "stopMetricTimer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedMetricsHelper {
    @NotNull
    public static final String APP_VERSION_KEY = "AppVersion";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String DEFAULT_VALUE_UNKNOWN = "Unknown";
    @NotNull
    public static final String DEVICE_MANUFACTURER = "DeviceManufacturer";
    @NotNull
    public static final String DEVICE_MODEL = "DeviceModel";
    @NotNull
    public static final String LIB_VERSION_KEY = "LibraryVersion";
    @NotNull
    public static final String MARKETPLACE_ID_CODE = "MarketplaceIDCode";
    @NotNull
    public static final String METRIC_CONNECT_EXCEPTION = "ConnectException";
    @NotNull
    public static final String METRIC_IO_EXCEPTION = "IoException";
    @NotNull
    public static final String METRIC_SOCKET_TIMEOUT_EXCEPTION = "SocketTimeoutException";
    private static final String PROGRAM_NAME = "TarazedAndroidLibrary";
    private static final String TAG = "TarazedMetricsHelper";
    private final DeviceInfoUtility deviceInfoUtility;
    private final TarazedSessionLogger logger;
    private final ConcurrentHashMap<String, MetricEvent> metricEventCache;
    private final HashMap<String, String> metricEventPivots;
    private final MetricsFactory metricsFactory;

    /* compiled from: TarazedMetricsHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002R\u0016\u0010\f\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u0016\u0010\u000e\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0002R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper$Companion;", "", "()V", "APP_VERSION_KEY", "", "DEFAULT_VALUE_UNKNOWN", "DEVICE_MANUFACTURER", "DEVICE_MODEL", "LIB_VERSION_KEY", "MARKETPLACE_ID_CODE", "METRIC_CONNECT_EXCEPTION", "METRIC_CONNECT_EXCEPTION$annotations", "METRIC_IO_EXCEPTION", "METRIC_IO_EXCEPTION$annotations", "METRIC_SOCKET_TIMEOUT_EXCEPTION", "METRIC_SOCKET_TIMEOUT_EXCEPTION$annotations", "PROGRAM_NAME", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void METRIC_CONNECT_EXCEPTION$annotations() {
        }

        @VisibleForTesting
        public static /* synthetic */ void METRIC_IO_EXCEPTION$annotations() {
        }

        @VisibleForTesting
        public static /* synthetic */ void METRIC_SOCKET_TIMEOUT_EXCEPTION$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedMetricsHelper(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull DeviceInfoUtility deviceInfoUtility) {
        HashMap<String, String> hashMapOf;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        this.logger = logger;
        this.deviceInfoUtility = deviceInfoUtility;
        this.metricEventCache = new ConcurrentHashMap<>();
        hashMapOf = MapsKt__MapsKt.hashMapOf(TuplesKt.to(LIB_VERSION_KEY, this.deviceInfoUtility.getTarazedVersion()), TuplesKt.to("AppVersion", this.deviceInfoUtility.getSoftwareVersion()), TuplesKt.to("DeviceManufacturer", this.deviceInfoUtility.getManufacturer()), TuplesKt.to("DeviceModel", this.deviceInfoUtility.getModel()), TuplesKt.to("MarketplaceIDCode", getPreferredMarketplace()));
        this.metricEventPivots = hashMapOf;
        MetricsFactory androidMetricsFactoryImpl = AndroidMetricsFactoryImpl.getInstance(context);
        Intrinsics.checkExpressionValueIsNotNull(androidMetricsFactoryImpl, "AndroidMetricsFactoryImpl.getInstance(context)");
        this.metricsFactory = androidMetricsFactoryImpl;
    }

    private final HashMap<String, String> getMetricPivots() {
        if (Intrinsics.areEqual(this.metricEventPivots.get("MarketplaceIDCode"), "Unknown")) {
            this.metricEventPivots.put("MarketplaceIDCode", getPreferredMarketplace());
        }
        return this.metricEventPivots;
    }

    private final String getPreferredMarketplace() {
        Account accountMetadata;
        AccountMetadataProvider accountMetadataProvider = (AccountMetadataProvider) TarazedComponentRegistry.INSTANCE.getComponent(AccountMetadataProvider.class);
        String preferredMarketplace = (accountMetadataProvider == null || (accountMetadata = accountMetadataProvider.getAccountMetadata()) == null) ? null : accountMetadata.getPreferredMarketplace();
        return !(preferredMarketplace == null || preferredMarketplace.length() == 0) ? preferredMarketplace : "Unknown";
    }

    public final void addCount(@NotNull String methodName, @NotNull String metricName, double d) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        addCount(methodName, metricName, d, Priority.NORMAL);
    }

    public final void addCountHighPriority(@NotNull String methodName, @NotNull String metricName, double d) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        addCount(methodName, metricName, d, Priority.HIGH);
    }

    public final void addIoExceptionCount(@NotNull String methodName, @NotNull String metricNamePrefix, @NotNull IOException exception, double d) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metricNamePrefix, "metricNamePrefix");
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        if (exception instanceof ConnectException) {
            addCountHighPriority(methodName, GeneratedOutlineSupport1.outline72(metricNamePrefix, METRIC_CONNECT_EXCEPTION), d);
        } else if (exception instanceof SocketTimeoutException) {
            addCountHighPriority(methodName, GeneratedOutlineSupport1.outline72(metricNamePrefix, METRIC_SOCKET_TIMEOUT_EXCEPTION), d);
        } else {
            addCountHighPriority(methodName, GeneratedOutlineSupport1.outline72(metricNamePrefix, METRIC_IO_EXCEPTION), d);
        }
    }

    public final void addMetricTimer(@NotNull String methodName, @NotNull String metricName, double d) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        try {
            MetricEvent obtainMetricEvent$TarazedMobileCore_release = obtainMetricEvent$TarazedMobileCore_release(methodName);
            obtainMetricEvent$TarazedMobileCore_release.addTimer(metricName, d);
            this.metricsFactory.record(obtainMetricEvent$TarazedMobileCore_release, Priority.NORMAL, Channel.ANONYMOUS);
        } catch (TarazedMetricsException e) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error obtaining timer metric event: ");
            outline107.append(e.getMessage());
            tarazedSessionLogger.e(TAG, outline107.toString());
        }
    }

    public final void incrementMetricCounter(@NotNull String methodName, @NotNull String metricName, double d) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        try {
            MetricEvent obtainMetricEvent$TarazedMobileCore_release = obtainMetricEvent$TarazedMobileCore_release(methodName);
            obtainMetricEvent$TarazedMobileCore_release.incrementCounter(metricName, d);
            this.metricsFactory.record(obtainMetricEvent$TarazedMobileCore_release, Priority.NORMAL, Channel.ANONYMOUS);
        } catch (TarazedMetricsException e) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error obtaining counter metric event: ");
            outline107.append(e.getMessage());
            tarazedSessionLogger.e(TAG, outline107.toString());
        }
    }

    @VisibleForTesting
    @NotNull
    public final MetricEvent obtainMetricEvent$TarazedMobileCore_release(@NotNull String methodName) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        MetricEvent metricEvent = this.metricEventCache.get(methodName);
        if (metricEvent == null) {
            metricEvent = this.metricsFactory.createMetricEvent(PROGRAM_NAME, methodName);
            for (Map.Entry<String, String> entry : getMetricPivots().entrySet()) {
                metricEvent.addString(entry.getKey(), entry.getValue());
            }
            ConcurrentHashMap<String, MetricEvent> concurrentHashMap = this.metricEventCache;
            Intrinsics.checkExpressionValueIsNotNull(metricEvent, "metricEvent");
            concurrentHashMap.put(methodName, metricEvent);
        }
        return metricEvent;
    }

    public final void startMetricTimer(@NotNull String methodName, @NotNull String metricName) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        try {
            obtainMetricEvent$TarazedMobileCore_release(methodName).startTimer(metricName);
        } catch (TarazedMetricsException e) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error obtaining timer metric event: ");
            outline107.append(e.getMessage());
            tarazedSessionLogger.e(TAG, outline107.toString());
        }
    }

    public final void stopMetricTimer(@NotNull String methodName, @NotNull String metricName) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        try {
            MetricEvent obtainMetricEvent$TarazedMobileCore_release = obtainMetricEvent$TarazedMobileCore_release(methodName);
            obtainMetricEvent$TarazedMobileCore_release.stopTimer(metricName);
            this.metricsFactory.record(obtainMetricEvent$TarazedMobileCore_release, Priority.NORMAL, Channel.ANONYMOUS);
        } catch (TarazedMetricsException e) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error obtaining timer metric event: ");
            outline107.append(e.getMessage());
            tarazedSessionLogger.e(TAG, outline107.toString());
        }
    }

    public final void addCount(@NotNull String methodName, @NotNull String metricName, long j) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        addCount(methodName, metricName, j, Priority.NORMAL);
    }

    private final void addCount(String str, String str2, double d, Priority priority) {
        try {
            MetricEvent obtainMetricEvent$TarazedMobileCore_release = obtainMetricEvent$TarazedMobileCore_release(str);
            obtainMetricEvent$TarazedMobileCore_release.addCounter(str2, d);
            this.metricsFactory.record(obtainMetricEvent$TarazedMobileCore_release, priority, Channel.ANONYMOUS);
        } catch (TarazedMetricsException e) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error obtaining count metric event: ");
            outline107.append(e.getMessage());
            tarazedSessionLogger.e(TAG, outline107.toString());
        }
    }
}
