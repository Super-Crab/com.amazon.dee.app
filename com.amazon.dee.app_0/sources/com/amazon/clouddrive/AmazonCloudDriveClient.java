package com.amazon.clouddrive;

import com.amazon.clouddrive.auth.ClientSdkAuthenticator;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.handlers.AsyncHandler;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.internal.Build;
import com.amazon.clouddrive.internal.CloudDriveOperation;
import com.amazon.clouddrive.internal.OperationFactory;
import com.amazon.clouddrive.internal.UserAgentInterceptor;
import com.amazon.clouddrive.model.AddChildToParentRequest;
import com.amazon.clouddrive.model.AddNodePropertyRequest;
import com.amazon.clouddrive.model.AddNodePropertyResponse;
import com.amazon.clouddrive.model.CloudDriveRequest;
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
import com.amazon.clouddrive.model.deserializer.CreateNodeResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.GetApplicationAccessRulesResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.GetChangesResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.GetNodeResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.ListChildrenResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.ListNodeResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.ListTrashResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.MoveChildResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.OverwriteFileResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.RestoreNodeFromTrashResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.UpdateNodeResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.UploadFileResponseDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.OkHttpClient;
/* loaded from: classes11.dex */
public class AmazonCloudDriveClient implements AmazonCloudDrive {
    private ExecutorService mExecutorService;
    private OperationFactory mOperationFactory;

    /* loaded from: classes11.dex */
    private static class BackgroundThreadFactory implements ThreadFactory {
        private static final int THREAD_PRIORITY_BACKGROUND = 4;
        private static final String mPrefix = "AmazonCloudDriveClient";
        private static AtomicInteger sCounter = new AtomicInteger();

        private BackgroundThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AmazonCloudDriveClient-");
            outline107.append(sCounter.incrementAndGet());
            Thread thread = new Thread(runnable, outline107.toString());
            thread.setPriority(4);
            return thread;
        }
    }

    /* loaded from: classes11.dex */
    private static class CallWithHandler<REQUEST extends CloudDriveRequest, RESULT> implements Callable<RESULT> {
        private final AsyncHandler<REQUEST, RESULT> mAsyncHandler;
        private final CloudDriveOperation<RESULT> mCloudDriveOperation;
        private final REQUEST mRequest;

        CallWithHandler(REQUEST request, CloudDriveOperation<RESULT> cloudDriveOperation, AsyncHandler<REQUEST, RESULT> asyncHandler) {
            this.mRequest = request;
            this.mCloudDriveOperation = cloudDriveOperation;
            this.mAsyncHandler = asyncHandler;
        }

        @Override // java.util.concurrent.Callable
        public RESULT call() throws Exception {
            try {
                RESULT call = this.mCloudDriveOperation.call();
                AsyncHandler<REQUEST, RESULT> asyncHandler = this.mAsyncHandler;
                if (asyncHandler != null) {
                    asyncHandler.onSuccess(this.mRequest, call);
                }
                return call;
            } catch (CloudDriveException e) {
                AsyncHandler<REQUEST, RESULT> asyncHandler2 = this.mAsyncHandler;
                if (asyncHandler2 != null) {
                    asyncHandler2.onError(this.mRequest, e);
                }
                throw e;
            } catch (InterruptedException e2) {
                AsyncHandler<REQUEST, RESULT> asyncHandler3 = this.mAsyncHandler;
                if (asyncHandler3 != null) {
                    asyncHandler3.onCanceled(this.mRequest);
                }
                throw e2;
            }
        }
    }

    public AmazonCloudDriveClient(AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration) {
        this(accountConfiguration, clientConfiguration, Executors.newFixedThreadPool(10, new BackgroundThreadFactory()));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void addChildToParent(AddChildToParentRequest addChildToParentRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newAddChildToParentOperation(addChildToParentRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> addChildToParentAsync(AddChildToParentRequest addChildToParentRequest, AsyncHandler<AddChildToParentRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(addChildToParentRequest, this.mOperationFactory.newAddChildToParentOperation(addChildToParentRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public AddNodePropertyResponse addNodeProperty(AddNodePropertyRequest addNodePropertyRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newAddPropertyOperation(addNodePropertyRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<AddNodePropertyResponse> addNodePropertyAsync(AddNodePropertyRequest addNodePropertyRequest, AsyncHandler<AddNodePropertyRequest, AddNodePropertyResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(addNodePropertyRequest, this.mOperationFactory.newAddPropertyOperation(addNodePropertyRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public CreateBatchLinkResponse createBatchLink(CreateBatchLinkRequest createBatchLinkRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newCreateBatchLinkOperation(createBatchLinkRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<CreateBatchLinkResponse> createBatchLinkAsync(CreateBatchLinkRequest createBatchLinkRequest, AsyncHandler<CreateBatchLinkRequest, CreateBatchLinkResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createBatchLinkRequest, this.mOperationFactory.newCreateBatchLinkOperation(createBatchLinkRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public CreateNodeResponse createNode(CreateNodeRequest createNodeRequest) throws CloudDriveException, InterruptedException {
        return (CreateNodeResponse) this.mOperationFactory.newCreateNodeOperation(createNodeRequest, CreateNodeResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<CreateNodeResponse> createNodeAsync(CreateNodeRequest createNodeRequest, AsyncHandler<CreateNodeRequest, CreateNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createNodeRequest, this.mOperationFactory.newCreateNodeOperation(createNodeRequest, CreateNodeResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void deleteNodeProperty(DeleteNodePropertyRequest deleteNodePropertyRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newDeletePropertyOperation(deleteNodePropertyRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> deleteNodePropertyAsync(DeleteNodePropertyRequest deleteNodePropertyRequest, AsyncHandler<DeleteNodePropertyRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(deleteNodePropertyRequest, this.mOperationFactory.newDeletePropertyOperation(deleteNodePropertyRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void downloadFile(DownloadFileRequest downloadFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newDownloadFileOperation(downloadFileRequest, progressListener).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> downloadFileAsync(DownloadFileRequest downloadFileRequest, ProgressListener progressListener, AsyncHandler<DownloadFileRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(downloadFileRequest, this.mOperationFactory.newDownloadFileOperation(downloadFileRequest, progressListener), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetAccountEndpointResponse getAccountEndpoint(GetAccountEndpointRequest getAccountEndpointRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetAccountEndpointOperation(getAccountEndpointRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetAccountEndpointResponse> getAccountEndpointAsync(GetAccountEndpointRequest getAccountEndpointRequest, AsyncHandler<GetAccountEndpointRequest, GetAccountEndpointResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getAccountEndpointRequest, this.mOperationFactory.newGetAccountEndpointOperation(getAccountEndpointRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetAccountInfoResponse getAccountInfo(GetAccountInfoRequest getAccountInfoRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetAccountInfoOperation(getAccountInfoRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetAccountInfoResponse> getAccountInfoAsync(GetAccountInfoRequest getAccountInfoRequest, AsyncHandler<GetAccountInfoRequest, GetAccountInfoResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getAccountInfoRequest, this.mOperationFactory.newGetAccountInfoOperation(getAccountInfoRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetAccountQuotaResponse getAccountQuota(GetAccountQuotaRequest getAccountQuotaRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetAccountQuotaOperation(getAccountQuotaRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetAccountQuotaResponse> getAccountQuotaAsync(GetAccountQuotaRequest getAccountQuotaRequest, AsyncHandler<GetAccountQuotaRequest, GetAccountQuotaResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getAccountQuotaRequest, this.mOperationFactory.newGetAccountQuotaOperation(getAccountQuotaRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetAccountUsageResponse getAccountUsage(GetAccountUsageRequest getAccountUsageRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetAccountUsageOperation(getAccountUsageRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetAccountUsageResponse> getAccountUsageAsync(GetAccountUsageRequest getAccountUsageRequest, AsyncHandler<GetAccountUsageRequest, GetAccountUsageResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getAccountUsageRequest, this.mOperationFactory.newGetAccountUsageOperation(getAccountUsageRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetApplicationAccessRulesResponse getApplicationAccessRules(GetApplicationAccessRulesRequest getApplicationAccessRulesRequest) throws InterruptedException, CloudDriveException {
        return this.mOperationFactory.newGetApplicationAccessRulesOperation(getApplicationAccessRulesRequest, GetApplicationAccessRulesResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetApplicationAccessRulesResponse> getApplicationAccessRulesAsync(GetApplicationAccessRulesRequest getApplicationAccessRulesRequest, AsyncHandler<GetApplicationAccessRulesRequest, GetApplicationAccessRulesResponse> asyncHandler) throws CloudDriveException, InterruptedException {
        return this.mExecutorService.submit(new CallWithHandler(getApplicationAccessRulesRequest, this.mOperationFactory.newGetApplicationAccessRulesOperation(getApplicationAccessRulesRequest, GetApplicationAccessRulesResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetChangesResponse getChanges(GetChangesRequest getChangesRequest) throws CloudDriveException, InterruptedException {
        return (GetChangesResponse) this.mOperationFactory.newGetChangesOperation(getChangesRequest, GetChangesResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetChangesResponse> getChangesAsync(GetChangesRequest getChangesRequest, AsyncHandler<GetChangesRequest, GetChangesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getChangesRequest, this.mOperationFactory.newGetChangesOperation(getChangesRequest, GetChangesResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetNodeResponse getNode(GetNodeRequest getNodeRequest) throws CloudDriveException, InterruptedException {
        return (GetNodeResponse) this.mOperationFactory.newGetNodeOperation(getNodeRequest, GetNodeResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetNodeResponse> getNodeAsync(GetNodeRequest getNodeRequest, AsyncHandler<GetNodeRequest, GetNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getNodeRequest, this.mOperationFactory.newGetNodeOperation(getNodeRequest, GetNodeResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetNodePropertyResponse getNodeProperty(GetNodePropertyRequest getNodePropertyRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetPropertyOperation(getNodePropertyRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetNodePropertyResponse> getNodePropertyAsync(GetNodePropertyRequest getNodePropertyRequest, AsyncHandler<GetNodePropertyRequest, GetNodePropertyResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getNodePropertyRequest, this.mOperationFactory.newGetPropertyOperation(getNodePropertyRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetOverwriteFileProgressResponse getOverwriteFileProgress(GetOverwriteFileProgressRequest getOverwriteFileProgressRequest) throws InterruptedException, CloudDriveException {
        return this.mOperationFactory.newGetOverwriteFileProgressOperation(getOverwriteFileProgressRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetOverwriteFileProgressResponse> getOverwriteFileProgressAsync(GetOverwriteFileProgressRequest getOverwriteFileProgressRequest, AsyncHandler<GetOverwriteFileProgressRequest, GetOverwriteFileProgressResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getOverwriteFileProgressRequest, this.mOperationFactory.newGetOverwriteFileProgressOperation(getOverwriteFileProgressRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void getThumbnail(GetThumbnailRequest getThumbnailRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newGetThumbnailOperation(getThumbnailRequest, progressListener).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> getThumbnailAsync(GetThumbnailRequest getThumbnailRequest, ProgressListener progressListener, AsyncHandler<GetThumbnailRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getThumbnailRequest, this.mOperationFactory.newGetThumbnailOperation(getThumbnailRequest, progressListener), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetUploadFileProgressResponse getUploadFileProgress(GetUploadFileProgressRequest getUploadFileProgressRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetUploadFileProgressOperation(getUploadFileProgressRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetUploadFileProgressResponse> getUploadFileProgressAsync(GetUploadFileProgressRequest getUploadFileProgressRequest, AsyncHandler<GetUploadFileProgressRequest, GetUploadFileProgressResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getUploadFileProgressRequest, this.mOperationFactory.newGetUploadFileProgressOperation(getUploadFileProgressRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public ListChildrenResponse listChildren(ListChildrenRequest listChildrenRequest) throws CloudDriveException, InterruptedException {
        return (ListChildrenResponse) this.mOperationFactory.newListChildrenOperation(listChildrenRequest, ListChildrenResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<ListChildrenResponse> listChildrenAsync(ListChildrenRequest listChildrenRequest, AsyncHandler<ListChildrenRequest, ListChildrenResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listChildrenRequest, this.mOperationFactory.newListChildrenOperation(listChildrenRequest, ListChildrenResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public ListNodePropertiesResponse listNodeProperties(ListNodePropertiesRequest listNodePropertiesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListPropertiesOperation(listNodePropertiesRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<ListNodePropertiesResponse> listNodePropertiesAsync(ListNodePropertiesRequest listNodePropertiesRequest, AsyncHandler<ListNodePropertiesRequest, ListNodePropertiesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listNodePropertiesRequest, this.mOperationFactory.newListPropertiesOperation(listNodePropertiesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public ListNodesResponse listNodes(ListNodesRequest listNodesRequest) throws CloudDriveException, InterruptedException {
        return (ListNodesResponse) this.mOperationFactory.newListNodesOperation(listNodesRequest, ListNodeResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<ListNodesResponse> listNodesAsync(ListNodesRequest listNodesRequest, AsyncHandler<ListNodesRequest, ListNodesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listNodesRequest, this.mOperationFactory.newListNodesOperation(listNodesRequest, ListNodeResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public ListNodesInTrashResponse listNodesInTrash(ListNodesInTrashRequest listNodesInTrashRequest) throws CloudDriveException, InterruptedException {
        return (ListNodesInTrashResponse) this.mOperationFactory.newListTrashOperation(listNodesInTrashRequest, ListTrashResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<ListNodesInTrashResponse> listNodesInTrashAsync(ListNodesInTrashRequest listNodesInTrashRequest, AsyncHandler<ListNodesInTrashRequest, ListNodesInTrashResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listNodesInTrashRequest, this.mOperationFactory.newListTrashOperation(listNodesInTrashRequest, ListTrashResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public MoveChildResponse moveChild(MoveChildRequest moveChildRequest) throws CloudDriveException, InterruptedException {
        return (MoveChildResponse) this.mOperationFactory.newMoveChildOperation(moveChildRequest, MoveChildResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<MoveChildResponse> moveChildAsync(MoveChildRequest moveChildRequest, AsyncHandler<MoveChildRequest, MoveChildResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(moveChildRequest, this.mOperationFactory.newMoveChildOperation(moveChildRequest, MoveChildResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public MoveNodeToTrashResponse moveNodeToTrash(MoveNodeToTrashRequest moveNodeToTrashRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newMoveNodeToTrashOperation(moveNodeToTrashRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<MoveNodeToTrashResponse> moveNodeToTrashAsync(MoveNodeToTrashRequest moveNodeToTrashRequest, AsyncHandler<MoveNodeToTrashRequest, MoveNodeToTrashResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(moveNodeToTrashRequest, this.mOperationFactory.newMoveNodeToTrashOperation(moveNodeToTrashRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public OverwriteFileResponse overwriteFile(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        return (OverwriteFileResponse) this.mOperationFactory.newOverwriteFileOperation(overwriteFileRequest, progressListener, OverwriteFileResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<OverwriteFileResponse> overwriteFileAsync(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener, AsyncHandler<OverwriteFileRequest, OverwriteFileResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(overwriteFileRequest, this.mOperationFactory.newOverwriteFileOperation(overwriteFileRequest, progressListener, OverwriteFileResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void removeChildFromParent(RemoveChildFromParentRequest removeChildFromParentRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newRemoveChildFromParentOperation(removeChildFromParentRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> removeChildFromParentAsync(RemoveChildFromParentRequest removeChildFromParentRequest, AsyncHandler<RemoveChildFromParentRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(removeChildFromParentRequest, this.mOperationFactory.newRemoveChildFromParentOperation(removeChildFromParentRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public RestoreNodeFromTrashResponse restoreNodeFromTrash(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest) throws CloudDriveException, InterruptedException {
        return (RestoreNodeFromTrashResponse) this.mOperationFactory.newRestoreNodeFromTrashOperation(restoreNodeFromTrashRequest, RestoreNodeFromTrashResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<RestoreNodeFromTrashResponse> restoreNodeFromTrashAsync(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest, AsyncHandler<RestoreNodeFromTrashRequest, RestoreNodeFromTrashResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(restoreNodeFromTrashRequest, this.mOperationFactory.newRestoreNodeFromTrashOperation(restoreNodeFromTrashRequest, RestoreNodeFromTrashResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void resumeOverwriteFile(ResumeOverwriteFileRequest resumeOverwriteFileRequest, ProgressListener progressListener) throws InterruptedException, CloudDriveException {
        this.mOperationFactory.newResumeOverwriteFileOperation(resumeOverwriteFileRequest, progressListener).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> resumeOverwriteFileAsync(ResumeOverwriteFileRequest resumeOverwriteFileRequest, ProgressListener progressListener, AsyncHandler<ResumeOverwriteFileRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(resumeOverwriteFileRequest, this.mOperationFactory.newResumeOverwriteFileOperation(resumeOverwriteFileRequest, progressListener), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void resumeUploadFile(ResumeUploadFileRequest resumeUploadFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newResumeUploadFileOperation(resumeUploadFileRequest, progressListener).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> resumeUploadFileAsync(ResumeUploadFileRequest resumeUploadFileRequest, ProgressListener progressListener, AsyncHandler<ResumeUploadFileRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(resumeUploadFileRequest, this.mOperationFactory.newResumeUploadFileOperation(resumeUploadFileRequest, progressListener), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public SourceInfoResponse setupSource(SetupSourceRequest setupSourceRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSetupSourceOperation(setupSourceRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<SourceInfoResponse> setupSourceAsync(SetupSourceRequest setupSourceRequest, AsyncHandler<SetupSourceRequest, SourceInfoResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(setupSourceRequest, this.mOperationFactory.newSetupSourceOperation(setupSourceRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public CollectionPropertiesResponse updateCollectionProperties(UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newUpdateCollectionPropertiesOperation(updateCollectionPropertiesRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<CollectionPropertiesResponse> updateCollectionPropertiesAsync(UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest, AsyncHandler<UpdateCollectionPropertiesRequest, CollectionPropertiesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(updateCollectionPropertiesRequest, this.mOperationFactory.newUpdateCollectionPropertiesOperation(updateCollectionPropertiesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public UpdateNodeResponse updateNode(UpdateNodeRequest updateNodeRequest) throws CloudDriveException, InterruptedException {
        return (UpdateNodeResponse) this.mOperationFactory.newUpdateNodeOperation(updateNodeRequest, UpdateNodeResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<UpdateNodeResponse> updateNodeAsync(UpdateNodeRequest updateNodeRequest, AsyncHandler<UpdateNodeRequest, UpdateNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(updateNodeRequest, this.mOperationFactory.newUpdateNodeOperation(updateNodeRequest, UpdateNodeResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public UploadFileResponse uploadFile(UploadFileRequest uploadFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        return (UploadFileResponse) this.mOperationFactory.newUploadFileOperation(uploadFileRequest, progressListener, UploadFileResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<UploadFileResponse> uploadFileAsync(UploadFileRequest uploadFileRequest, ProgressListener progressListener, AsyncHandler<UploadFileRequest, UploadFileResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(uploadFileRequest, this.mOperationFactory.newUploadFileOperation(uploadFileRequest, progressListener, UploadFileResponseDeserializer.INSTANCE), asyncHandler));
    }

    public AmazonCloudDriveClient(AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration, ExecutorService executorService) {
        ClientSdkAuthenticator clientSdkAuthenticator = new ClientSdkAuthenticator(accountConfiguration.getRequestAuthenticator());
        OkHttpClient.Builder addInterceptor = clientConfiguration.getHttpClient().newBuilder().connectTimeout(clientConfiguration.getConnectionTimeOutMillis(), TimeUnit.MILLISECONDS).readTimeout(clientConfiguration.getReadTimeOutMillis(), TimeUnit.MILLISECONDS).connectionSpecs(clientConfiguration.getConnectionSpecs()).hostnameVerifier(clientConfiguration.getHostNameVerifier()).authenticator(clientSdkAuthenticator).addInterceptor(clientSdkAuthenticator);
        this.mOperationFactory = new OperationFactory(accountConfiguration, clientConfiguration.newBuilder().setHttpClient(addInterceptor.addInterceptor(new UserAgentInterceptor(clientConfiguration.getUserAgent() + " " + Build.SDK_AGENT)).build()).build());
        this.mExecutorService = executorService;
    }
}
