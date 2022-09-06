package com.amazon.clouddrive;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.handlers.AsyncHandler;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.model.AddChildToParentRequest;
import com.amazon.clouddrive.model.AddNodePropertyRequest;
import com.amazon.clouddrive.model.AddNodePropertyResponse;
import com.amazon.clouddrive.model.CollectionPropertiesResponse;
import com.amazon.clouddrive.model.CreateBatchLinkRequest;
import com.amazon.clouddrive.model.CreateBatchLinkResponse;
import com.amazon.clouddrive.model.CreateNodeRequest;
import com.amazon.clouddrive.model.CreateNodeResponse;
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
import com.amazon.clouddrive.model.GetChangesResponse;
import com.amazon.clouddrive.model.GetNodePropertyRequest;
import com.amazon.clouddrive.model.GetNodePropertyResponse;
import com.amazon.clouddrive.model.GetNodeRequest;
import com.amazon.clouddrive.model.GetNodeResponse;
import com.amazon.clouddrive.model.GetOverwriteFileProgressRequest;
import com.amazon.clouddrive.model.GetOverwriteFileProgressResponse;
import com.amazon.clouddrive.model.GetThumbnailRequest;
import com.amazon.clouddrive.model.GetUploadFileProgressRequest;
import com.amazon.clouddrive.model.GetUploadFileProgressResponse;
import com.amazon.clouddrive.model.ListChildrenRequest;
import com.amazon.clouddrive.model.ListChildrenResponse;
import com.amazon.clouddrive.model.ListNodePropertiesRequest;
import com.amazon.clouddrive.model.ListNodePropertiesResponse;
import com.amazon.clouddrive.model.ListNodesInTrashRequest;
import com.amazon.clouddrive.model.ListNodesInTrashResponse;
import com.amazon.clouddrive.model.ListNodesRequest;
import com.amazon.clouddrive.model.ListNodesResponse;
import com.amazon.clouddrive.model.MoveChildRequest;
import com.amazon.clouddrive.model.MoveChildResponse;
import com.amazon.clouddrive.model.MoveNodeToTrashRequest;
import com.amazon.clouddrive.model.MoveNodeToTrashResponse;
import com.amazon.clouddrive.model.OverwriteFileRequest;
import com.amazon.clouddrive.model.OverwriteFileResponse;
import com.amazon.clouddrive.model.RemoveChildFromParentRequest;
import com.amazon.clouddrive.model.RestoreNodeFromTrashRequest;
import com.amazon.clouddrive.model.RestoreNodeFromTrashResponse;
import com.amazon.clouddrive.model.ResumeOverwriteFileRequest;
import com.amazon.clouddrive.model.ResumeUploadFileRequest;
import com.amazon.clouddrive.model.SetupSourceRequest;
import com.amazon.clouddrive.model.SourceInfoResponse;
import com.amazon.clouddrive.model.UpdateCollectionPropertiesRequest;
import com.amazon.clouddrive.model.UpdateNodeRequest;
import com.amazon.clouddrive.model.UpdateNodeResponse;
import com.amazon.clouddrive.model.UploadFileRequest;
import com.amazon.clouddrive.model.UploadFileResponse;
import java.util.concurrent.Future;
/* loaded from: classes11.dex */
public interface AmazonCloudDrive {
    void addChildToParent(AddChildToParentRequest addChildToParentRequest) throws CloudDriveException, InterruptedException;

    Future<Void> addChildToParentAsync(AddChildToParentRequest addChildToParentRequest, AsyncHandler<AddChildToParentRequest, Void> asyncHandler);

    AddNodePropertyResponse addNodeProperty(AddNodePropertyRequest addNodePropertyRequest) throws CloudDriveException, InterruptedException;

    Future<AddNodePropertyResponse> addNodePropertyAsync(AddNodePropertyRequest addNodePropertyRequest, AsyncHandler<AddNodePropertyRequest, AddNodePropertyResponse> asyncHandler);

    CreateBatchLinkResponse createBatchLink(CreateBatchLinkRequest createBatchLinkRequest) throws CloudDriveException, InterruptedException;

    Future<CreateBatchLinkResponse> createBatchLinkAsync(CreateBatchLinkRequest createBatchLinkRequest, AsyncHandler<CreateBatchLinkRequest, CreateBatchLinkResponse> asyncHandler);

    CreateNodeResponse createNode(CreateNodeRequest createNodeRequest) throws CloudDriveException, InterruptedException;

    Future<CreateNodeResponse> createNodeAsync(CreateNodeRequest createNodeRequest, AsyncHandler<CreateNodeRequest, CreateNodeResponse> asyncHandler);

    void deleteNodeProperty(DeleteNodePropertyRequest deleteNodePropertyRequest) throws CloudDriveException, InterruptedException;

    Future<Void> deleteNodePropertyAsync(DeleteNodePropertyRequest deleteNodePropertyRequest, AsyncHandler<DeleteNodePropertyRequest, Void> asyncHandler);

    void downloadFile(DownloadFileRequest downloadFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<Void> downloadFileAsync(DownloadFileRequest downloadFileRequest, ProgressListener progressListener, AsyncHandler<DownloadFileRequest, Void> asyncHandler);

    GetAccountEndpointResponse getAccountEndpoint(GetAccountEndpointRequest getAccountEndpointRequest) throws CloudDriveException, InterruptedException;

    Future<GetAccountEndpointResponse> getAccountEndpointAsync(GetAccountEndpointRequest getAccountEndpointRequest, AsyncHandler<GetAccountEndpointRequest, GetAccountEndpointResponse> asyncHandler);

    GetAccountInfoResponse getAccountInfo(GetAccountInfoRequest getAccountInfoRequest) throws CloudDriveException, InterruptedException;

    Future<GetAccountInfoResponse> getAccountInfoAsync(GetAccountInfoRequest getAccountInfoRequest, AsyncHandler<GetAccountInfoRequest, GetAccountInfoResponse> asyncHandler);

    GetAccountQuotaResponse getAccountQuota(GetAccountQuotaRequest getAccountQuotaRequest) throws CloudDriveException, InterruptedException;

    Future<GetAccountQuotaResponse> getAccountQuotaAsync(GetAccountQuotaRequest getAccountQuotaRequest, AsyncHandler<GetAccountQuotaRequest, GetAccountQuotaResponse> asyncHandler);

    GetAccountUsageResponse getAccountUsage(GetAccountUsageRequest getAccountUsageRequest) throws CloudDriveException, InterruptedException;

    Future<GetAccountUsageResponse> getAccountUsageAsync(GetAccountUsageRequest getAccountUsageRequest, AsyncHandler<GetAccountUsageRequest, GetAccountUsageResponse> asyncHandler);

    GetApplicationAccessRulesResponse getApplicationAccessRules(GetApplicationAccessRulesRequest getApplicationAccessRulesRequest) throws CloudDriveException, InterruptedException;

    Future<GetApplicationAccessRulesResponse> getApplicationAccessRulesAsync(GetApplicationAccessRulesRequest getApplicationAccessRulesRequest, AsyncHandler<GetApplicationAccessRulesRequest, GetApplicationAccessRulesResponse> asyncHandler) throws CloudDriveException, InterruptedException;

    GetChangesResponse getChanges(GetChangesRequest getChangesRequest) throws CloudDriveException, InterruptedException;

    Future<GetChangesResponse> getChangesAsync(GetChangesRequest getChangesRequest, AsyncHandler<GetChangesRequest, GetChangesResponse> asyncHandler);

    GetNodeResponse getNode(GetNodeRequest getNodeRequest) throws CloudDriveException, InterruptedException;

    Future<GetNodeResponse> getNodeAsync(GetNodeRequest getNodeRequest, AsyncHandler<GetNodeRequest, GetNodeResponse> asyncHandler);

    GetNodePropertyResponse getNodeProperty(GetNodePropertyRequest getNodePropertyRequest) throws CloudDriveException, InterruptedException;

    Future<GetNodePropertyResponse> getNodePropertyAsync(GetNodePropertyRequest getNodePropertyRequest, AsyncHandler<GetNodePropertyRequest, GetNodePropertyResponse> asyncHandler);

    GetOverwriteFileProgressResponse getOverwriteFileProgress(GetOverwriteFileProgressRequest getOverwriteFileProgressRequest) throws InterruptedException, CloudDriveException;

    Future<GetOverwriteFileProgressResponse> getOverwriteFileProgressAsync(GetOverwriteFileProgressRequest getOverwriteFileProgressRequest, AsyncHandler<GetOverwriteFileProgressRequest, GetOverwriteFileProgressResponse> asyncHandler);

    void getThumbnail(GetThumbnailRequest getThumbnailRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<Void> getThumbnailAsync(GetThumbnailRequest getThumbnailRequest, ProgressListener progressListener, AsyncHandler<GetThumbnailRequest, Void> asyncHandler);

    GetUploadFileProgressResponse getUploadFileProgress(GetUploadFileProgressRequest getUploadFileProgressRequest) throws CloudDriveException, InterruptedException;

    Future<GetUploadFileProgressResponse> getUploadFileProgressAsync(GetUploadFileProgressRequest getUploadFileProgressRequest, AsyncHandler<GetUploadFileProgressRequest, GetUploadFileProgressResponse> asyncHandler);

    ListChildrenResponse listChildren(ListChildrenRequest listChildrenRequest) throws CloudDriveException, InterruptedException;

    Future<ListChildrenResponse> listChildrenAsync(ListChildrenRequest listChildrenRequest, AsyncHandler<ListChildrenRequest, ListChildrenResponse> asyncHandler);

    ListNodePropertiesResponse listNodeProperties(ListNodePropertiesRequest listNodePropertiesRequest) throws CloudDriveException, InterruptedException;

    Future<ListNodePropertiesResponse> listNodePropertiesAsync(ListNodePropertiesRequest listNodePropertiesRequest, AsyncHandler<ListNodePropertiesRequest, ListNodePropertiesResponse> asyncHandler);

    ListNodesResponse listNodes(ListNodesRequest listNodesRequest) throws CloudDriveException, InterruptedException;

    Future<ListNodesResponse> listNodesAsync(ListNodesRequest listNodesRequest, AsyncHandler<ListNodesRequest, ListNodesResponse> asyncHandler);

    ListNodesInTrashResponse listNodesInTrash(ListNodesInTrashRequest listNodesInTrashRequest) throws CloudDriveException, InterruptedException;

    Future<ListNodesInTrashResponse> listNodesInTrashAsync(ListNodesInTrashRequest listNodesInTrashRequest, AsyncHandler<ListNodesInTrashRequest, ListNodesInTrashResponse> asyncHandler);

    MoveChildResponse moveChild(MoveChildRequest moveChildRequest) throws CloudDriveException, InterruptedException;

    Future<MoveChildResponse> moveChildAsync(MoveChildRequest moveChildRequest, AsyncHandler<MoveChildRequest, MoveChildResponse> asyncHandler);

    MoveNodeToTrashResponse moveNodeToTrash(MoveNodeToTrashRequest moveNodeToTrashRequest) throws CloudDriveException, InterruptedException;

    Future<MoveNodeToTrashResponse> moveNodeToTrashAsync(MoveNodeToTrashRequest moveNodeToTrashRequest, AsyncHandler<MoveNodeToTrashRequest, MoveNodeToTrashResponse> asyncHandler);

    OverwriteFileResponse overwriteFile(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<OverwriteFileResponse> overwriteFileAsync(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener, AsyncHandler<OverwriteFileRequest, OverwriteFileResponse> asyncHandler);

    void removeChildFromParent(RemoveChildFromParentRequest removeChildFromParentRequest) throws CloudDriveException, InterruptedException;

    Future<Void> removeChildFromParentAsync(RemoveChildFromParentRequest removeChildFromParentRequest, AsyncHandler<RemoveChildFromParentRequest, Void> asyncHandler);

    RestoreNodeFromTrashResponse restoreNodeFromTrash(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest) throws CloudDriveException, InterruptedException;

    Future<RestoreNodeFromTrashResponse> restoreNodeFromTrashAsync(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest, AsyncHandler<RestoreNodeFromTrashRequest, RestoreNodeFromTrashResponse> asyncHandler);

    void resumeOverwriteFile(ResumeOverwriteFileRequest resumeOverwriteFileRequest, ProgressListener progressListener) throws InterruptedException, CloudDriveException;

    Future<Void> resumeOverwriteFileAsync(ResumeOverwriteFileRequest resumeOverwriteFileRequest, ProgressListener progressListener, AsyncHandler<ResumeOverwriteFileRequest, Void> asyncHandler);

    void resumeUploadFile(ResumeUploadFileRequest resumeUploadFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<Void> resumeUploadFileAsync(ResumeUploadFileRequest resumeUploadFileRequest, ProgressListener progressListener, AsyncHandler<ResumeUploadFileRequest, Void> asyncHandler);

    SourceInfoResponse setupSource(SetupSourceRequest setupSourceRequest) throws CloudDriveException, InterruptedException;

    Future<SourceInfoResponse> setupSourceAsync(SetupSourceRequest setupSourceRequest, AsyncHandler<SetupSourceRequest, SourceInfoResponse> asyncHandler);

    CollectionPropertiesResponse updateCollectionProperties(UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest) throws CloudDriveException, InterruptedException;

    Future<CollectionPropertiesResponse> updateCollectionPropertiesAsync(UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest, AsyncHandler<UpdateCollectionPropertiesRequest, CollectionPropertiesResponse> asyncHandler);

    UpdateNodeResponse updateNode(UpdateNodeRequest updateNodeRequest) throws CloudDriveException, InterruptedException;

    Future<UpdateNodeResponse> updateNodeAsync(UpdateNodeRequest updateNodeRequest, AsyncHandler<UpdateNodeRequest, UpdateNodeResponse> asyncHandler);

    UploadFileResponse uploadFile(UploadFileRequest uploadFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<UploadFileResponse> uploadFileAsync(UploadFileRequest uploadFileRequest, ProgressListener progressListener, AsyncHandler<UploadFileRequest, UploadFileResponse> asyncHandler);
}
