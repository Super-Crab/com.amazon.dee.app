package com.amazon.clouddrive.internal;

import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.SourceInfoCache;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.internal.RequestPathGenerator;
import com.amazon.clouddrive.metrics.MetricListener;
import com.amazon.clouddrive.model.AddChildToParentRequest;
import com.amazon.clouddrive.model.AddNodePropertyRequest;
import com.amazon.clouddrive.model.AddNodePropertyResponse;
import com.amazon.clouddrive.model.CollectionPropertiesResponse;
import com.amazon.clouddrive.model.CreateBatchLinkRequest;
import com.amazon.clouddrive.model.CreateBatchLinkResponse;
import com.amazon.clouddrive.model.CreateNodeRequest;
import com.amazon.clouddrive.model.DeleteNodePropertyRequest;
import com.amazon.clouddrive.model.DownloadFileRequest;
import com.amazon.clouddrive.model.GetAccountEndpointRequest;
import com.amazon.clouddrive.model.GetAccountEndpointResponse;
import com.amazon.clouddrive.model.GetAccountInfoRequest;
import com.amazon.clouddrive.model.GetAccountInfoResponse;
import com.amazon.clouddrive.model.GetAccountQuotaRequest;
import com.amazon.clouddrive.model.GetAccountQuotaResponse;
import com.amazon.clouddrive.model.GetAccountUsageRequest;
import com.amazon.clouddrive.model.GetAccountUsageResponse;
import com.amazon.clouddrive.model.GetApplicationAccessRulesRequest;
import com.amazon.clouddrive.model.GetApplicationAccessRulesResponse;
import com.amazon.clouddrive.model.GetChangesRequest;
import com.amazon.clouddrive.model.GetNodePropertyRequest;
import com.amazon.clouddrive.model.GetNodePropertyResponse;
import com.amazon.clouddrive.model.GetNodeRequest;
import com.amazon.clouddrive.model.GetOverwriteFileProgressRequest;
import com.amazon.clouddrive.model.GetOverwriteFileProgressResponse;
import com.amazon.clouddrive.model.GetThumbnailRequest;
import com.amazon.clouddrive.model.GetUploadFileProgressRequest;
import com.amazon.clouddrive.model.GetUploadFileProgressResponse;
import com.amazon.clouddrive.model.IGetChangesResponse;
import com.amazon.clouddrive.model.INode;
import com.amazon.clouddrive.model.ListChildrenRequest;
import com.amazon.clouddrive.model.ListNodePropertiesRequest;
import com.amazon.clouddrive.model.ListNodePropertiesResponse;
import com.amazon.clouddrive.model.ListNodesInTrashRequest;
import com.amazon.clouddrive.model.ListNodesRequest;
import com.amazon.clouddrive.model.MoveChildRequest;
import com.amazon.clouddrive.model.MoveNodeToTrashRequest;
import com.amazon.clouddrive.model.MoveNodeToTrashResponse;
import com.amazon.clouddrive.model.OverwriteFileRequest;
import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import com.amazon.clouddrive.model.RemoveChildFromParentRequest;
import com.amazon.clouddrive.model.RestoreNodeFromTrashRequest;
import com.amazon.clouddrive.model.ResumeOverwriteFileRequest;
import com.amazon.clouddrive.model.ResumeUploadFileRequest;
import com.amazon.clouddrive.model.SetupSourceRequest;
import com.amazon.clouddrive.model.SourceInfoResponse;
import com.amazon.clouddrive.model.UpdateCollectionPropertiesRequest;
import com.amazon.clouddrive.model.UpdateNodeRequest;
import com.amazon.clouddrive.model.UploadFileRequest;
import com.amazon.clouddrive.model.deserializer.AddNodePropertyResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.CollectionPropertiesResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.CreateBatchLinkDeserializer;
import com.amazon.clouddrive.model.deserializer.GetAccountEndpointResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.GetAccountInfoResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.GetAccountQuotaResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.GetAccountUsageResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.GetNodePropertyResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.GetOverwriteFileProgressResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.GetUploadFileProgressResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListNodePropertiesResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.MoveNodeToTrashResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.SourceInfoResponseDeserializer;
import com.amazon.clouddrive.model.serializer.AddNodePropertyRequestSerializer;
import com.amazon.clouddrive.model.serializer.CreateBatchLinkRequestSerializer;
import com.amazon.clouddrive.model.serializer.CreateNodeRequestSerializer;
import com.amazon.clouddrive.model.serializer.GetChangesRequestSerializer;
import com.amazon.clouddrive.model.serializer.MoveChildRequestSerializer;
import com.amazon.clouddrive.model.serializer.MoveNodeToTrashRequestSerializer;
import com.amazon.clouddrive.model.serializer.RestoreNodeFromTrashRequestSerializer;
import com.amazon.clouddrive.model.serializer.SetupSourceRequestSerializer;
import com.amazon.clouddrive.model.serializer.UpdateCollectionPropertiesRequestSerializer;
import com.amazon.clouddrive.model.serializer.UpdateNodeRequestSerializer;
import com.amazon.clouddrive.utils.Optional;
import com.amazon.clouddrive.utils.TransformUtils;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.util.UriUtil;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes11.dex */
public class OperationFactory {
    public static final String DOWNLOAD_SIGNED_OPERATION = "download/signed/";
    private final AccountConfiguration mAccountConfiguration;
    private final ClientConfiguration mClientConfiguration;
    private final MetricListener mMetricListener;
    private final RequestPathGenerator mRequestPathGenerator;
    private final SourceInfoGenerator mSourceInfoGenerator;
    protected final String RESOURCE_VERSION = "V2";
    private final AtomicLong mDelay = new AtomicLong(0);

    public OperationFactory(AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration) {
        this.mAccountConfiguration = accountConfiguration;
        this.mClientConfiguration = clientConfiguration;
        this.mMetricListener = clientConfiguration.getMetricListener();
        this.mRequestPathGenerator = new RequestPathGenerator(this.mClientConfiguration, this.mAccountConfiguration, this);
        this.mSourceInfoGenerator = createSourceInfoGenerator(this.mAccountConfiguration);
    }

    private void appendResourceVersion(RequestPathGenerator.RequestPath requestPath) {
        requestPath.addParameter("resourceVersion", "V2");
    }

    public void clearEndpoints() {
        this.mAccountConfiguration.getEndpointsCache().clear();
    }

    public void clearSourceInfo() {
        SourceInfoCache mo2996getSourceInfoCache = this.mAccountConfiguration.mo2996getSourceInfoCache();
        if (mo2996getSourceInfoCache != null) {
            mo2996getSourceInfoCache.clear();
        }
    }

    protected SourceInfoGenerator createSourceInfoGenerator(AccountConfiguration accountConfiguration) {
        return new SourceInfoGenerator(accountConfiguration, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getOperationDelay() {
        return this.mDelay.get();
    }

    RequestPathGenerator getRequestPathGenerator() {
        return this.mRequestPathGenerator;
    }

    public CloudDriveOperation<Void> newAddChildToParentOperation(AddChildToParentRequest addChildToParentRequest) {
        RequestAssertUtils.assertNotNull(addChildToParentRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(addChildToParentRequest.getParentId(), "The parent id must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(addChildToParentRequest.getChildId(), "The child id must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(addChildToParentRequest.getParentId());
        outline107.append("/children/");
        outline107.append(addChildToParentRequest.getChildId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, SmartDeviceDataProvider.METHOD_HTTP_PUT, createMetaDataEndpointRequestPath, null, "addChildToParent", this.mMetricListener, addChildToParentRequest.getClass());
    }

    public CloudDriveOperation<AddNodePropertyResponse> newAddPropertyOperation(AddNodePropertyRequest addNodePropertyRequest) {
        RequestAssertUtils.assertNotNull(addNodePropertyRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(addNodePropertyRequest.getId(), "The node id must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(addNodePropertyRequest.getOwner(), "The owner must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(addNodePropertyRequest.getKey(), "The key must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(addNodePropertyRequest.getId());
        outline107.append("/properties/");
        outline107.append(addNodePropertyRequest.getOwner());
        outline107.append("/");
        outline107.append(addNodePropertyRequest.getKey());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        SinglePartPostRequestWriter singlePartPostRequestWriter = new SinglePartPostRequestWriter(addNodePropertyRequest, AddNodePropertyRequestSerializer.INSTANCE);
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, singlePartPostRequestWriter, AddNodePropertyResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, SmartDeviceDataProvider.METHOD_HTTP_PUT, "addNodeProperty", this.mMetricListener, AddNodePropertyRequest.class);
    }

    public CloudDriveOperation<CreateBatchLinkResponse> newCreateBatchLinkOperation(CreateBatchLinkRequest createBatchLinkRequest) {
        RequestAssertUtils.assertNotNull(createBatchLinkRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(createBatchLinkRequest.getNodeIds(), "The node id list must have at least one item");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(createBatchLinkRequest, CreateBatchLinkRequestSerializer.INSTANCE), CreateBatchLinkDeserializer.INSTANCE, this.mRequestPathGenerator.createMetaDataEndpointRequestPath("batchLink", "V2"), "POST", "createBatchLink", this.mMetricListener, CreateBatchLinkRequest.class);
    }

    public <T extends INode> CloudDriveOperation<T> newCreateNodeOperation(CreateNodeRequest createNodeRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(createNodeRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(createNodeRequest.getName(), "A name must be provided when creating a node.");
        RequestAssertUtils.assertNotNullOrEmpty(createNodeRequest.getKind(), "A node kind must be provided when creating a node.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("nodes");
        createMetaDataEndpointRequestPath.addParameter("localId", createNodeRequest.getLocalId());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(createNodeRequest, CreateNodeRequestSerializer.INSTANCE), jsonDeserializer, createMetaDataEndpointRequestPath, "POST", "createNode", this.mMetricListener, CreateNodeRequest.class);
    }

    public CloudDriveOperation<Void> newDeletePropertyOperation(DeleteNodePropertyRequest deleteNodePropertyRequest) {
        RequestAssertUtils.assertNotNull(deleteNodePropertyRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(deleteNodePropertyRequest.getId(), "The node id must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(deleteNodePropertyRequest.getOwner(), "The owner must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(deleteNodePropertyRequest.getKey(), "The property key must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(deleteNodePropertyRequest.getId());
        outline107.append("/properties/");
        outline107.append(deleteNodePropertyRequest.getOwner());
        outline107.append("/");
        outline107.append(deleteNodePropertyRequest.getKey());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        CloudDriveMethodOperation cloudDriveMethodOperation = new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, Constants.REQUEST_METHOD_DELETE, createMetaDataEndpointRequestPath, null, "deleteNodeProperty", this.mMetricListener, DeleteNodePropertyRequest.class);
        cloudDriveMethodOperation.isHeadRequest(true);
        return cloudDriveMethodOperation;
    }

    public CloudDriveOperation<Void> newDownloadFileOperation(DownloadFileRequest downloadFileRequest, ProgressListener progressListener) {
        RequestAssertUtils.assertNotNull(downloadFileRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(downloadFileRequest.getId(), "A node id must be provided when downloading a node's content.");
        StringBuilder sb = new StringBuilder(DOWNLOAD_SIGNED_OPERATION);
        sb.append(downloadFileRequest.getId());
        if (downloadFileRequest.hasTransform()) {
            Optional<String> transformPathSuffix = TransformUtils.getTransformPathSuffix(downloadFileRequest.getTransform());
            if (transformPathSuffix.isPresent()) {
                sb.append(transformPathSuffix.get());
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Downloading transform ");
                outline107.append(downloadFileRequest.getTransform());
                outline107.append(" is no supported.");
                throw new UnsupportedOperationException(outline107.toString());
            }
        } else {
            sb.append("/content");
        }
        RequestPathGenerator.RequestPath createContentV2EndpointRequestPath = this.mRequestPathGenerator.createContentV2EndpointRequestPath(sb.toString());
        if (downloadFileRequest.hasResolution()) {
            createContentV2EndpointRequestPath.addParameter(ReactProperties.HereMapMarker.Y, String.valueOf(downloadFileRequest.getResolution()));
        }
        return new DownloadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, createContentV2EndpointRequestPath, "downloadFile", this.mMetricListener, progressListener, downloadFileRequest.getClass(), downloadFileRequest.getOutputStream(), downloadFileRequest.getBlockSize());
    }

    public CloudDriveOperation<GetAccountEndpointResponse> newGetAccountEndpointOperation(GetAccountEndpointRequest getAccountEndpointRequest) {
        RequestAssertUtils.assertNotNull(getAccountEndpointRequest, "The request cannot be null.");
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", this.mRequestPathGenerator.createMasterEndpointRequestPath("account/endpoint"), GetAccountEndpointResponseDeserializer.INSTANCE, "getAccountEndpoint", this.mMetricListener, GetAccountEndpointRequest.class);
    }

    public CloudDriveOperation<GetAccountInfoResponse> newGetAccountInfoOperation(GetAccountInfoRequest getAccountInfoRequest) {
        RequestAssertUtils.assertNotNull(getAccountInfoRequest, "The request cannot be null.");
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/info"), GetAccountInfoResponseDeserializer.INSTANCE, "getAccountInfo", this.mMetricListener, GetAccountInfoRequest.class);
    }

    public CloudDriveOperation<GetAccountQuotaResponse> newGetAccountQuotaOperation(GetAccountQuotaRequest getAccountQuotaRequest) {
        RequestAssertUtils.assertNotNull(getAccountQuotaRequest, "The request cannot be null.");
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/quota"), GetAccountQuotaResponseDeserializer.INSTANCE, "getAccountQuota", this.mMetricListener, GetAccountQuotaRequest.class);
    }

    public CloudDriveOperation<GetAccountUsageResponse> newGetAccountUsageOperation(GetAccountUsageRequest getAccountUsageRequest) {
        RequestAssertUtils.assertNotNull(getAccountUsageRequest, "The request cannot be null.");
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/usage"), GetAccountUsageResponseDeserializer.INSTANCE, "getAccountUsage", this.mMetricListener, GetAccountUsageRequest.class);
    }

    public CloudDriveOperation<GetApplicationAccessRulesResponse> newGetApplicationAccessRulesOperation(GetApplicationAccessRulesRequest getApplicationAccessRulesRequest, JsonDeserializer<GetApplicationAccessRulesResponse> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(getApplicationAccessRulesRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("application/accessRules");
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, jsonDeserializer, "getApplicationAccessRules", this.mMetricListener, GetApplicationAccessRulesRequest.class);
    }

    public <U extends IGetChangesResponse> CloudDriveOperation<U> newGetChangesOperation(GetChangesRequest getChangesRequest, JsonDeserializer<U> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(getChangesRequest, "The request cannot be null.");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(getChangesRequest, GetChangesRequestSerializer.INSTANCE), jsonDeserializer, this.mRequestPathGenerator.createMetaDataEndpointRequestPath("changes", "V2"), "POST", "getChanges", this.mMetricListener, GetChangesRequest.class);
    }

    public <T extends INode> CloudDriveOperation<T> newGetNodeOperation(GetNodeRequest getNodeRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(getNodeRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getNodeRequest.getId(), "A node id must be provided when requesting a node.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(getNodeRequest.getId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        createMetaDataEndpointRequestPath.addParameter("fields", getNodeRequest.getFields());
        createMetaDataEndpointRequestPath.addParameter(UriUtil.LOCAL_ASSET_SCHEME, getNodeRequest.getAssetMapping());
        createMetaDataEndpointRequestPath.addParameter("tempLink", getNodeRequest.getTempLink());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, jsonDeserializer, "getNode", this.mMetricListener, getNodeRequest.getClass());
    }

    public CloudDriveOperation<GetOverwriteFileProgressResponse> newGetOverwriteFileProgressOperation(GetOverwriteFileProgressRequest getOverwriteFileProgressRequest) {
        RequestAssertUtils.assertNotNull(getOverwriteFileProgressRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getOverwriteFileProgressRequest.getNodeId(), "The node id of the node must be provided.");
        RequestPathGenerator.RequestPath createContentEndpointRequestPath = this.mRequestPathGenerator.createContentEndpointRequestPath("resume");
        createContentEndpointRequestPath.addParameter(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, getOverwriteFileProgressRequest.getNodeId());
        return new GetFileProgressOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createContentEndpointRequestPath, new RequestPropertyWriterImpl(), GetOverwriteFileProgressResponseDeserializer.INSTANCE, "getOverwriteFileProgress", this.mMetricListener, GetOverwriteFileProgressRequest.class);
    }

    public CloudDriveOperation<GetNodePropertyResponse> newGetPropertyOperation(GetNodePropertyRequest getNodePropertyRequest) {
        RequestAssertUtils.assertNotNull(getNodePropertyRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getNodePropertyRequest.getId(), "The node id must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(getNodePropertyRequest.getOwner(), "The owner must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(getNodePropertyRequest.getKey(), "The property key must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(getNodePropertyRequest.getId());
        outline107.append("/properties/");
        outline107.append(getNodePropertyRequest.getOwner());
        outline107.append("/");
        outline107.append(getNodePropertyRequest.getKey());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, GetNodePropertyResponseDeserializer.INSTANCE, "getNodeProperty", this.mMetricListener, GetNodePropertyRequest.class);
    }

    public CloudDriveOperation<Void> newGetThumbnailOperation(GetThumbnailRequest getThumbnailRequest, ProgressListener progressListener) {
        RequestAssertUtils.assertNotNull(getThumbnailRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getThumbnailRequest.getId(), "A node id must be provided when getting a thumbnail");
        RequestPathGenerator.RequestPath createContentV1EndpointRequestPath = this.mRequestPathGenerator.createContentV1EndpointRequestPath("thumbnail/" + getThumbnailRequest.getId());
        if (getThumbnailRequest.hasViewBoxSet()) {
            createContentV1EndpointRequestPath.addParameter("viewBox", getThumbnailRequest.getViewBoxWidth() == getThumbnailRequest.getViewBoxHeight() ? Integer.toString(getThumbnailRequest.getViewBoxWidth()) : String.format("%d,%d", Integer.valueOf(getThumbnailRequest.getViewBoxWidth()), Integer.valueOf(getThumbnailRequest.getViewBoxHeight())));
            if (getThumbnailRequest.hasFitType() && !getThumbnailRequest.getFitType().isDefault()) {
                createContentV1EndpointRequestPath.addParameter("fit", getThumbnailRequest.getFitType().getValue());
            }
            return new DownloadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, createContentV1EndpointRequestPath, "getThumbnail", this.mMetricListener, progressListener, getThumbnailRequest.getClass(), getThumbnailRequest.getOutputStream(), getThumbnailRequest.getBlockSize());
        }
        throw new IllegalArgumentException(String.format("viewBox is required in GetThumbnailRequest, %s", getThumbnailRequest));
    }

    public CloudDriveOperation<GetUploadFileProgressResponse> newGetUploadFileProgressOperation(GetUploadFileProgressRequest getUploadFileProgressRequest) {
        RequestAssertUtils.assertNotNull(getUploadFileProgressRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getUploadFileProgressRequest.getLocalId(), "The local id of the node must be provided.");
        RequestPathGenerator.RequestPath createContentEndpointRequestPath = this.mRequestPathGenerator.createContentEndpointRequestPath("resume");
        createContentEndpointRequestPath.addParameter("localId", getUploadFileProgressRequest.getLocalId());
        return new GetFileProgressOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createContentEndpointRequestPath, new RequestPropertyWriterImpl(), GetUploadFileProgressResponseDeserializer.INSTANCE, "getUploadFileProgress", this.mMetricListener, GetUploadFileProgressRequest.class);
    }

    public <T extends PaginatedCloudDriveResponse> CloudDriveOperation<T> newListChildrenOperation(ListChildrenRequest listChildrenRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(listChildrenRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(listChildrenRequest.getId(), "An id must be provided for a list children request.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(listChildrenRequest.getId());
        outline107.append("/children");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        createMetaDataEndpointRequestPath.addRequestParameters(listChildrenRequest);
        createMetaDataEndpointRequestPath.addParameter("assetMapping", listChildrenRequest.getAssetMapping());
        createMetaDataEndpointRequestPath.addParameter("tempLink", listChildrenRequest.getTempLink());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, jsonDeserializer, "listChildren", this.mMetricListener, listChildrenRequest.getClass());
    }

    public <T extends PaginatedCloudDriveResponse> CloudDriveOperation<T> newListNodesOperation(ListNodesRequest listNodesRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(listNodesRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("nodes");
        appendResourceVersion(createMetaDataEndpointRequestPath);
        createMetaDataEndpointRequestPath.addRequestParameters(listNodesRequest);
        createMetaDataEndpointRequestPath.addParameter("assetMapping", listNodesRequest.getAssetMapping());
        createMetaDataEndpointRequestPath.addParameter("tempLink", listNodesRequest.getTempLink());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, jsonDeserializer, DiscoveryMetricsKt.METRICS_SERVICE_API_LIST_NODES, this.mMetricListener, ListNodesRequest.class);
    }

    public CloudDriveOperation<ListNodePropertiesResponse> newListPropertiesOperation(ListNodePropertiesRequest listNodePropertiesRequest) {
        RequestAssertUtils.assertNotNull(listNodePropertiesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(listNodePropertiesRequest.getId(), "The node id must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(listNodePropertiesRequest.getOwner(), "The owner must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(listNodePropertiesRequest.getId());
        outline107.append("/properties/");
        outline107.append(listNodePropertiesRequest.getOwner());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, ListNodePropertiesResponseDeserializer.INSTANCE, "listNodeProperties", this.mMetricListener, ListNodePropertiesRequest.class);
    }

    public <T extends PaginatedCloudDriveResponse> CloudDriveOperation<T> newListTrashOperation(ListNodesInTrashRequest listNodesInTrashRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(listNodesInTrashRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("trash");
        createMetaDataEndpointRequestPath.addRequestParameters(listNodesInTrashRequest);
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, jsonDeserializer, "listNodesInTrash", this.mMetricListener, ListNodesInTrashRequest.class);
    }

    public <T extends INode> CloudDriveOperation<T> newMoveChildOperation(MoveChildRequest moveChildRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(moveChildRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(moveChildRequest.getChildId(), "The child node id must be provided to move.");
        RequestAssertUtils.assertNotNullOrEmpty(moveChildRequest.getFromParentId(), "The from parent node id must be provided to move.");
        RequestAssertUtils.assertNotNullOrEmpty(moveChildRequest.getToParentId(), "The to parent node id must be provided to move.");
        SinglePartPostRequestWriter singlePartPostRequestWriter = new SinglePartPostRequestWriter(moveChildRequest, MoveChildRequestSerializer.INSTANCE);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(moveChildRequest.getToParentId());
        outline107.append("/children");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, singlePartPostRequestWriter, jsonDeserializer, this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString()), "POST", "moveChild", this.mMetricListener, MoveChildRequest.class);
    }

    public CloudDriveOperation<MoveNodeToTrashResponse> newMoveNodeToTrashOperation(MoveNodeToTrashRequest moveNodeToTrashRequest) {
        RequestAssertUtils.assertNotNull(moveNodeToTrashRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(moveNodeToTrashRequest.getId(), "The node id must be provided to move to trash.");
        RequestPathGenerator requestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("trash/");
        outline107.append(moveNodeToTrashRequest.getId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = requestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(moveNodeToTrashRequest, MoveNodeToTrashRequestSerializer.INSTANCE), MoveNodeToTrashResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, SmartDeviceDataProvider.METHOD_HTTP_PUT, "moveNodeToTrash", this.mMetricListener, moveNodeToTrashRequest.getClass());
    }

    public <T extends INode> CloudDriveOperation<T> newOverwriteFileOperation(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(overwriteFileRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNull(overwriteFileRequest.getNodeId(), "Node ID must be provided when overwriting a node.");
        RequestAssertUtils.assertNotNull(overwriteFileRequest.getInputStream(), "An InputStream must be provided when overwriting a file node.");
        if (overwriteFileRequest.getContentLength() > 0) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
            outline107.append(overwriteFileRequest.getNodeId());
            outline107.append("/content");
            return new UploadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new OverwriteRequestBodyWriter(overwriteFileRequest, progressListener), jsonDeserializer, null, this.mRequestPathGenerator.createContentEndpointRequestPath(outline107.toString()), "overwriteFile", this.mMetricListener, OverwriteFileRequest.class, SmartDeviceDataProvider.METHOD_HTTP_PUT);
        }
        throw new IllegalArgumentException("The content length must be set to the size of the file.");
    }

    public CloudDriveOperation<Void> newRemoveChildFromParentOperation(RemoveChildFromParentRequest removeChildFromParentRequest) {
        RequestAssertUtils.assertNotNull(removeChildFromParentRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(removeChildFromParentRequest.getParentId(), "The parent id must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(removeChildFromParentRequest.getChildId(), "The child id must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(removeChildFromParentRequest.getParentId());
        outline107.append("/children/");
        outline107.append(removeChildFromParentRequest.getChildId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, Constants.REQUEST_METHOD_DELETE, createMetaDataEndpointRequestPath, null, "removeChildFromParent", this.mMetricListener, removeChildFromParentRequest.getClass());
    }

    public <T extends INode> CloudDriveOperation<T> newRestoreNodeFromTrashOperation(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(restoreNodeFromTrashRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(restoreNodeFromTrashRequest.getId(), "The node id must be provided to restore from trash.");
        SinglePartPostRequestWriter singlePartPostRequestWriter = new SinglePartPostRequestWriter(restoreNodeFromTrashRequest, RestoreNodeFromTrashRequestSerializer.INSTANCE);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("trash/");
        outline107.append(restoreNodeFromTrashRequest.getId());
        outline107.append("/restore");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, singlePartPostRequestWriter, jsonDeserializer, createMetaDataEndpointRequestPath, "POST", "restoreNodeFromTrash", this.mMetricListener, RestoreNodeFromTrashRequest.class);
    }

    public CloudDriveOperation<Void> newResumeOverwriteFileOperation(ResumeOverwriteFileRequest resumeOverwriteFileRequest, ProgressListener progressListener) {
        RequestAssertUtils.assertNotNull(resumeOverwriteFileRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNull(resumeOverwriteFileRequest.getInputStream(), "An InputStream must be provided when overwriting a file.");
        if (resumeOverwriteFileRequest.getContentLength() > 0) {
            if (resumeOverwriteFileRequest.getResumeFromPosition() >= 1 && resumeOverwriteFileRequest.getResumeFromPosition() < resumeOverwriteFileRequest.getContentLength()) {
                RequestPathGenerator requestPathGenerator = this.mRequestPathGenerator;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
                outline107.append(resumeOverwriteFileRequest.getNodeId());
                outline107.append("/content");
                RequestPathGenerator.RequestPath createContentEndpointRequestPath = requestPathGenerator.createContentEndpointRequestPath(outline107.toString());
                appendResourceVersion(createContentEndpointRequestPath);
                return new UploadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new ResumeFileRequestWriter(resumeOverwriteFileRequest, progressListener), null, null, createContentEndpointRequestPath, "resumeOverwrite", this.mMetricListener, ResumeOverwriteFileRequest.class, SmartDeviceDataProvider.METHOD_HTTP_PUT);
            }
            throw new IllegalArgumentException("The resume position must be within [1, content length).");
        }
        throw new IllegalArgumentException("The content length must be set to the size of the file.");
    }

    public CloudDriveOperation<Void> newResumeUploadFileOperation(ResumeUploadFileRequest resumeUploadFileRequest, ProgressListener progressListener) {
        RequestAssertUtils.assertNotNull(resumeUploadFileRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNull(resumeUploadFileRequest.getInputStream(), "An InputStream must be provided when uploading a file.");
        if (resumeUploadFileRequest.getContentLength() > 0) {
            if (resumeUploadFileRequest.getResumeFromPosition() >= 1 && resumeUploadFileRequest.getResumeFromPosition() < resumeUploadFileRequest.getContentLength()) {
                RequestPathGenerator requestPathGenerator = this.mRequestPathGenerator;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
                outline107.append(resumeUploadFileRequest.getNodeId());
                outline107.append("/content");
                RequestPathGenerator.RequestPath createContentEndpointRequestPath = requestPathGenerator.createContentEndpointRequestPath(outline107.toString());
                appendResourceVersion(createContentEndpointRequestPath);
                return new UploadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new ResumeFileRequestWriter(resumeUploadFileRequest, progressListener), null, null, createContentEndpointRequestPath, "resumeUpload", this.mMetricListener, ResumeUploadFileRequest.class, SmartDeviceDataProvider.METHOD_HTTP_PUT);
            }
            throw new IllegalArgumentException("The resume position must be within [1, content length).");
        }
        throw new IllegalArgumentException("The content length must be set to the size of the file.");
    }

    public CloudDriveOperation<SourceInfoResponse> newSetupSourceOperation(SetupSourceRequest setupSourceRequest) {
        RequestAssertUtils.assertNotNull(setupSourceRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(setupSourceRequest.getDeviceClass(), "The device class must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(setupSourceRequest.getDevicePlatform(), "The device platform must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(setupSourceRequest.getSourceApplicationName(), "The source application name must be provided");
        RequestAssertUtils.assertNotNullOrEmpty(setupSourceRequest.getSourceVersion(), "The source version must be provided");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/source");
        createMetaDataEndpointRequestPath.addRequestParameters(setupSourceRequest);
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(setupSourceRequest, SetupSourceRequestSerializer.INSTANCE), SourceInfoResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, "POST", "setupSource", this.mMetricListener, setupSourceRequest.getClass());
    }

    public CloudDriveOperation<CollectionPropertiesResponse> newUpdateCollectionPropertiesOperation(UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest) {
        RequestAssertUtils.assertNotNull(updateCollectionPropertiesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(updateCollectionPropertiesRequest.getId(), "The node id must be provided.");
        RequestAssertUtils.assertNotNull(updateCollectionPropertiesRequest.getOperations(), "The operations must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(updateCollectionPropertiesRequest.getId());
        outline107.append("/collectionProperties");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        SinglePartPostRequestWriter singlePartPostRequestWriter = new SinglePartPostRequestWriter(updateCollectionPropertiesRequest, UpdateCollectionPropertiesRequestSerializer.INSTANCE);
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, singlePartPostRequestWriter, CollectionPropertiesResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, "PATCH", "updateCollectionProperties", this.mMetricListener, UpdateCollectionPropertiesRequest.class);
    }

    public <T extends INode> CloudDriveOperation<T> newUpdateNodeOperation(UpdateNodeRequest updateNodeRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(updateNodeRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(updateNodeRequest.getId(), "The node id must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(updateNodeRequest.getId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        appendResourceVersion(createMetaDataEndpointRequestPath);
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(updateNodeRequest, UpdateNodeRequestSerializer.INSTANCE), jsonDeserializer, createMetaDataEndpointRequestPath, "PATCH", "updateNode", this.mMetricListener, updateNodeRequest.getClass());
    }

    public <T extends INode> CloudDriveOperation<T> newUploadFileOperation(UploadFileRequest uploadFileRequest, ProgressListener progressListener, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(uploadFileRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(uploadFileRequest.getName(), "A name must be provided when creating a node.");
        RequestAssertUtils.assertNotNullOrEmpty(uploadFileRequest.getKind(), "A node kind must be provided when creating a node.");
        RequestAssertUtils.assertNotNull(uploadFileRequest.getInputStream(), "An InputStream must be provided when creating a file node.");
        if (uploadFileRequest.getContentLength() > 0) {
            RequestPathGenerator.RequestPath createContentEndpointRequestPath = this.mRequestPathGenerator.createContentEndpointRequestPath("nodes");
            appendResourceVersion(createContentEndpointRequestPath);
            createContentEndpointRequestPath.addParameter("localId", uploadFileRequest.getLocalId());
            createContentEndpointRequestPath.addParameter("suppress", uploadFileRequest.getSuppress());
            return new UploadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new UploadRequestBodyWriter(uploadFileRequest, progressListener), jsonDeserializer, null, createContentEndpointRequestPath, "uploadFile", this.mMetricListener, uploadFileRequest.getClass(), "POST");
        }
        throw new IllegalArgumentException("The content length must be set to the size of the file.");
    }

    void setOperationDelay(long j) {
        if (j >= 0) {
            this.mDelay.set(j);
            return;
        }
        throw new IllegalArgumentException("Operation Delay must be a value greater than or equal to zero.");
    }
}
