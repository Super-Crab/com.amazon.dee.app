package com.amazon.photos.discovery.internal.server;

import androidx.annotation.WorkerThread;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.cds.CDSCalls;
import com.amazon.clouddrive.cdasdk.cds.bulk.BulkGetNodesByDigestRequest;
import com.amazon.clouddrive.cdasdk.cds.bulk.BulkGetNodesByDigestResponse;
import com.amazon.clouddrive.cdasdk.cds.bulk.DigestType;
import com.amazon.clouddrive.cdasdk.cds.common.ResourceVersion;
import com.amazon.clouddrive.cdasdk.cds.common.TimeGroupBy;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeRequest;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeResponse;
import com.amazon.clouddrive.cdasdk.cds.search.AggregationContext;
import com.amazon.clouddrive.cdasdk.cds.search.AggregationRequest;
import com.amazon.clouddrive.cdasdk.cds.search.AggregationResponse;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.photos.discovery.internal.dedupe.metadata.DayAggregations;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DefaultServiceApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u001c\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/photos/discovery/internal/server/DefaultServiceApi;", "Lcom/amazon/photos/discovery/internal/server/ServiceApi;", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "(Lcom/amazon/clouddrive/cdasdk/CDClient;)V", DiscoveryMetricsKt.METRICS_SERVICE_API_AGGREGATIONS, "Lcom/amazon/photos/discovery/internal/dedupe/metadata/DayAggregations;", "bulkGetNodesByDigestRequest", "Lcom/amazon/clouddrive/cdasdk/cds/bulk/BulkGetNodesByDigestResponse;", "md5Values", "", "", DiscoveryMetricsKt.METRICS_SERVICE_API_LIST_NODES, "Lcom/amazon/clouddrive/cdasdk/cds/node/ListNodeResponse;", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "startToken", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
@WorkerThread
/* loaded from: classes13.dex */
public final class DefaultServiceApi implements ServiceApi {
    private final CDClient cdClient;

    public DefaultServiceApi(@NotNull CDClient cdClient) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        this.cdClient = cdClient;
    }

    @Override // com.amazon.photos.discovery.internal.server.ServiceApi
    @NotNull
    public DayAggregations aggregationsByDay() throws CloudDriveException, IOException, InterruptedException {
        AggregationRequest aggregationRequest = new AggregationRequest();
        aggregationRequest.setAggregationContext(AggregationContext.CUSTOMER);
        aggregationRequest.setCategory("time");
        aggregationRequest.setGroupBy(TimeGroupBy.DAY);
        try {
            CDSCalls cDSCalls = this.cdClient.getCDSCalls();
            Intrinsics.checkExpressionValueIsNotNull(cDSCalls, "cdClient.cdsCalls");
            AggregationResponse resp = cDSCalls.getSearchCalls().aggregation(aggregationRequest).blockingGet();
            Intrinsics.checkExpressionValueIsNotNull(resp, "resp");
            return new DayAggregations(resp);
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                throw e;
            }
            throw cause;
        }
    }

    @Override // com.amazon.photos.discovery.internal.server.ServiceApi
    @NotNull
    public BulkGetNodesByDigestResponse bulkGetNodesByDigestRequest(@NotNull List<String> md5Values) throws CloudDriveException, IOException, InterruptedException {
        Intrinsics.checkParameterIsNotNull(md5Values, "md5Values");
        BulkGetNodesByDigestRequest bulkGetNodesByDigestRequest = new BulkGetNodesByDigestRequest();
        bulkGetNodesByDigestRequest.setDigestType(DigestType.FILE_MD5);
        bulkGetNodesByDigestRequest.setDigestList(md5Values);
        try {
            CDSCalls cDSCalls = this.cdClient.getCDSCalls();
            Intrinsics.checkExpressionValueIsNotNull(cDSCalls, "cdClient.cdsCalls");
            BulkGetNodesByDigestResponse blockingGet = cDSCalls.getBulkCalls().bulkGetNodesByDigest(bulkGetNodesByDigestRequest).blockingGet();
            Intrinsics.checkExpressionValueIsNotNull(blockingGet, "cdClient.cdsCalls.bulkCa…st(request).blockingGet()");
            return blockingGet;
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                throw e;
            }
            throw cause;
        }
    }

    @Override // com.amazon.photos.discovery.internal.server.ServiceApi
    @NotNull
    public ListNodeResponse listNodes(@Nullable String str, @Nullable String str2) throws CloudDriveException, IOException, InterruptedException {
        ListNodeRequest listNodeRequest = new ListNodeRequest();
        if (str != null) {
            listNodeRequest.setFilters(str);
        }
        if (str2 != null) {
            listNodeRequest.setStartToken(str2);
        }
        listNodeRequest.setResourceVersion(ResourceVersion.V2);
        try {
            CDSCalls cDSCalls = this.cdClient.getCDSCalls();
            Intrinsics.checkExpressionValueIsNotNull(cDSCalls, "cdClient.cdsCalls");
            ListNodeResponse blockingGet = cDSCalls.getNodeCalls().listNodes(listNodeRequest).blockingGet();
            Intrinsics.checkExpressionValueIsNotNull(blockingGet, "cdClient.cdsCalls.nodeCa…es(request).blockingGet()");
            return blockingGet;
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                throw e;
            }
            throw cause;
        }
    }
}
