package com.amazon.photos.discovery.internal.server;

import androidx.annotation.WorkerThread;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.cds.bulk.BulkGetNodesByDigestResponse;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeResponse;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.photos.discovery.internal.dedupe.metadata.DayAggregations;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ServiceApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\ba\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J \u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\bH&¨\u0006\r"}, d2 = {"Lcom/amazon/photos/discovery/internal/server/ServiceApi;", "", DiscoveryMetricsKt.METRICS_SERVICE_API_AGGREGATIONS, "Lcom/amazon/photos/discovery/internal/dedupe/metadata/DayAggregations;", "bulkGetNodesByDigestRequest", "Lcom/amazon/clouddrive/cdasdk/cds/bulk/BulkGetNodesByDigestResponse;", "md5Values", "", "", DiscoveryMetricsKt.METRICS_SERVICE_API_LIST_NODES, "Lcom/amazon/clouddrive/cdasdk/cds/node/ListNodeResponse;", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "startToken", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
@WorkerThread
/* loaded from: classes13.dex */
public interface ServiceApi {

    /* compiled from: ServiceApi.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ ListNodeResponse listNodes$default(ServiceApi serviceApi, String str, String str2, int i, Object obj) throws CloudDriveException, IOException, InterruptedException {
            if (obj == null) {
                if ((i & 1) != 0) {
                    str = null;
                }
                if ((i & 2) != 0) {
                    str2 = null;
                }
                return serviceApi.listNodes(str, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listNodes");
        }
    }

    @NotNull
    DayAggregations aggregationsByDay() throws CloudDriveException, IOException, InterruptedException;

    @NotNull
    BulkGetNodesByDigestResponse bulkGetNodesByDigestRequest(@NotNull List<String> list) throws CloudDriveException, IOException, InterruptedException;

    @NotNull
    ListNodeResponse listNodes(@Nullable String str, @Nullable String str2) throws CloudDriveException, IOException, InterruptedException;
}
