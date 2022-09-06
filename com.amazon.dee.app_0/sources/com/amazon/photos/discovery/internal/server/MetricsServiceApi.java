package com.amazon.photos.discovery.internal.server;

import androidx.annotation.WorkerThread;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.cds.bulk.BulkGetNodesByDigestResponse;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeResponse;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.photos.discovery.internal.dedupe.metadata.DayAggregations;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MetricsServiceApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u001c\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\bH\u0016J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\bR\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/photos/discovery/internal/server/MetricsServiceApi;", "Lcom/amazon/photos/discovery/internal/server/ServiceApi;", "serviceApi", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/photos/discovery/internal/server/ServiceApi;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "callCounts", "", "", "", DiscoveryMetricsKt.METRICS_SERVICE_API_AGGREGATIONS, "Lcom/amazon/photos/discovery/internal/dedupe/metadata/DayAggregations;", "bulkGetNodesByDigestRequest", "Lcom/amazon/clouddrive/cdasdk/cds/bulk/BulkGetNodesByDigestResponse;", "md5Values", "", "incrementCountMetric", "", "call", DiscoveryMetricsKt.METRICS_SERVICE_API_LIST_NODES, "Lcom/amazon/clouddrive/cdasdk/cds/node/ListNodeResponse;", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "startToken", "recordAndResetCallMetrics", "source", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
@WorkerThread
/* loaded from: classes13.dex */
public final class MetricsServiceApi implements ServiceApi {
    private final Map<String, Integer> callCounts;
    private final Metrics metrics;
    private final ServiceApi serviceApi;

    public MetricsServiceApi(@NotNull ServiceApi serviceApi, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(serviceApi, "serviceApi");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.serviceApi = serviceApi;
        this.metrics = metrics;
        this.callCounts = new LinkedHashMap();
    }

    private final void incrementCountMetric(String str) {
        Map<String, Integer> map = this.callCounts;
        Integer num = map.get(str);
        map.put(str, Integer.valueOf((num != null ? num.intValue() : 0) + 1));
    }

    @Override // com.amazon.photos.discovery.internal.server.ServiceApi
    @NotNull
    public DayAggregations aggregationsByDay() throws CloudDriveException, IOException, InterruptedException {
        incrementCountMetric(DiscoveryMetricsKt.METRICS_SERVICE_API_AGGREGATIONS);
        return this.serviceApi.aggregationsByDay();
    }

    @Override // com.amazon.photos.discovery.internal.server.ServiceApi
    @NotNull
    public BulkGetNodesByDigestResponse bulkGetNodesByDigestRequest(@NotNull List<String> md5Values) throws CloudDriveException, IOException, InterruptedException {
        Intrinsics.checkParameterIsNotNull(md5Values, "md5Values");
        incrementCountMetric(DiscoveryMetricsKt.METRICS_SERVICE_API_BULK_DIGEST);
        return this.serviceApi.bulkGetNodesByDigestRequest(md5Values);
    }

    @Override // com.amazon.photos.discovery.internal.server.ServiceApi
    @NotNull
    public ListNodeResponse listNodes(@Nullable String str, @Nullable String str2) throws CloudDriveException, IOException, InterruptedException {
        incrementCountMetric(DiscoveryMetricsKt.METRICS_SERVICE_API_LIST_NODES);
        return this.serviceApi.listNodes(str, str2);
    }

    public final void recordAndResetCallMetrics(@NotNull String source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (this.callCounts.isEmpty()) {
            return;
        }
        ClientMetric clientMetric = new ClientMetric();
        for (Map.Entry<String, Integer> entry : this.callCounts.entrySet()) {
            final String key = entry.getKey();
            clientMetric.addCounter(new MetricName() { // from class: com.amazon.photos.discovery.internal.server.MetricsServiceApi$recordAndResetCallMetrics$1
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    return key;
                }
            }, entry.getValue().intValue());
        }
        this.metrics.recordCustomMetric(source, clientMetric, new MetricRecordingType[0]);
        this.callCounts.clear();
    }
}
