package com.amazon.clouddrive.extended;

import com.amazon.clouddrive.auth.ClientSdkAuthenticator;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.extended.model.AddChildToParentExtendedRequest;
import com.amazon.clouddrive.extended.model.AddFacesToPersonRequest;
import com.amazon.clouddrive.extended.model.BatchAddNodeRequest;
import com.amazon.clouddrive.extended.model.BatchAddNodeResponse;
import com.amazon.clouddrive.extended.model.BatchCreateInviteRequest;
import com.amazon.clouddrive.extended.model.BatchCreateInviteResponse;
import com.amazon.clouddrive.extended.model.BatchRemoveNodeRequest;
import com.amazon.clouddrive.extended.model.BatchRemoveNodeResponse;
import com.amazon.clouddrive.extended.model.BulkAddRemoveChildRequest;
import com.amazon.clouddrive.extended.model.BulkAddRemoveChildResponse;
import com.amazon.clouddrive.extended.model.BulkAddToFamilyArchiveRequest;
import com.amazon.clouddrive.extended.model.BulkGetFaceClusterRequest;
import com.amazon.clouddrive.extended.model.BulkGetFaceClusterResponse;
import com.amazon.clouddrive.extended.model.BulkGetReactionSummariesRequest;
import com.amazon.clouddrive.extended.model.BulkGetReactionSummariesResponse;
import com.amazon.clouddrive.extended.model.BulkRemoveFromFamilyArchiveRequest;
import com.amazon.clouddrive.extended.model.BulkTrashRestoreNodesRequest;
import com.amazon.clouddrive.extended.model.BulkTrashRestoreNodesResponse;
import com.amazon.clouddrive.extended.model.CreateContactBlockRequest;
import com.amazon.clouddrive.extended.model.CreateContactBlockResponse;
import com.amazon.clouddrive.extended.model.CreateEventRequest;
import com.amazon.clouddrive.extended.model.CreateFaceClusterRequest;
import com.amazon.clouddrive.extended.model.CreateFaceClusterResponse;
import com.amazon.clouddrive.extended.model.CreateGroupRequest;
import com.amazon.clouddrive.extended.model.CreateGroupResponse;
import com.amazon.clouddrive.extended.model.CreateMemberRequest;
import com.amazon.clouddrive.extended.model.CreateMemberResponse;
import com.amazon.clouddrive.extended.model.CreateMetadataDatabaseRequest;
import com.amazon.clouddrive.extended.model.CreateMetadataDatabaseResponse;
import com.amazon.clouddrive.extended.model.CreateNodeExtendedResponse;
import com.amazon.clouddrive.extended.model.CreatePersonRequest;
import com.amazon.clouddrive.extended.model.CreateReactionRequest;
import com.amazon.clouddrive.extended.model.CreateReactionResponse;
import com.amazon.clouddrive.extended.model.DeleteGroupRequest;
import com.amazon.clouddrive.extended.model.DeleteMemberRequest;
import com.amazon.clouddrive.extended.model.DeleteReactionRequest;
import com.amazon.clouddrive.extended.model.DownloadFileExtendedRequest;
import com.amazon.clouddrive.extended.model.GetAggregationsRequest;
import com.amazon.clouddrive.extended.model.GetAggregationsResponse;
import com.amazon.clouddrive.extended.model.GetChangesExtendedResponse;
import com.amazon.clouddrive.extended.model.GetFacesForNodeRequest;
import com.amazon.clouddrive.extended.model.GetFacesForNodeResponse;
import com.amazon.clouddrive.extended.model.GetFacesForPersonRequest;
import com.amazon.clouddrive.extended.model.GetFacesForPersonResponse;
import com.amazon.clouddrive.extended.model.GetFamilyChangesRequest;
import com.amazon.clouddrive.extended.model.GetFamilyChangesResponse;
import com.amazon.clouddrive.extended.model.GetFamilyRequest;
import com.amazon.clouddrive.extended.model.GetFamilyResponse;
import com.amazon.clouddrive.extended.model.GetGroupCacheInfoResponse;
import com.amazon.clouddrive.extended.model.GetGroupPrivacyPreferencesRequest;
import com.amazon.clouddrive.extended.model.GetGroupPrivacyPreferencesResponse;
import com.amazon.clouddrive.extended.model.GetGroupRequest;
import com.amazon.clouddrive.extended.model.GetGroupResponse;
import com.amazon.clouddrive.extended.model.GetGroupShareBehaviorRequest;
import com.amazon.clouddrive.extended.model.GetGroupShareBehaviorResponse;
import com.amazon.clouddrive.extended.model.GetGroupShareInfoRequest;
import com.amazon.clouddrive.extended.model.GetGroupShareInfoResponse;
import com.amazon.clouddrive.extended.model.GetGroupShareRequest;
import com.amazon.clouddrive.extended.model.GetGroupShareResponse;
import com.amazon.clouddrive.extended.model.GetJobStatusRequest;
import com.amazon.clouddrive.extended.model.GetJobStatusResponse;
import com.amazon.clouddrive.extended.model.GetMemberPreferencesRequest;
import com.amazon.clouddrive.extended.model.GetMemberPreferencesResponse;
import com.amazon.clouddrive.extended.model.GetMetadataDatabaseStatusRequest;
import com.amazon.clouddrive.extended.model.GetMetadataDatabaseStatusResponse;
import com.amazon.clouddrive.extended.model.GetNodeExtendedRequest;
import com.amazon.clouddrive.extended.model.GetNodeExtendedResponse;
import com.amazon.clouddrive.extended.model.GetPreferencesRequest;
import com.amazon.clouddrive.extended.model.GetPreferencesResponse;
import com.amazon.clouddrive.extended.model.GetProfileRequest;
import com.amazon.clouddrive.extended.model.GetProfileResponse;
import com.amazon.clouddrive.extended.model.GetReUpdateNodesRequest;
import com.amazon.clouddrive.extended.model.GetReUpdateNodesResponse;
import com.amazon.clouddrive.extended.model.GetReactionRequest;
import com.amazon.clouddrive.extended.model.GetReactionResponse;
import com.amazon.clouddrive.extended.model.GetSearchSuggestionsRequest;
import com.amazon.clouddrive.extended.model.GetSearchSuggestionsResponse;
import com.amazon.clouddrive.extended.model.GetServicePlansRequest;
import com.amazon.clouddrive.extended.model.GetServicePlansResponse;
import com.amazon.clouddrive.extended.model.GetSplashRequest;
import com.amazon.clouddrive.extended.model.GetSplashResponse;
import com.amazon.clouddrive.extended.model.GetSubscriptionsRequest;
import com.amazon.clouddrive.extended.model.GetSubscriptionsResponse;
import com.amazon.clouddrive.extended.model.GetThumbnailExtendedRequest;
import com.amazon.clouddrive.extended.model.GroupRequest;
import com.amazon.clouddrive.extended.model.ImportContactsRequest;
import com.amazon.clouddrive.extended.model.ListChildrenExtendedRequest;
import com.amazon.clouddrive.extended.model.ListChildrenExtendedResponse;
import com.amazon.clouddrive.extended.model.ListCollectionNodesRequest;
import com.amazon.clouddrive.extended.model.ListCollectionNodesResponse;
import com.amazon.clouddrive.extended.model.ListContactsRequest;
import com.amazon.clouddrive.extended.model.ListContactsResponse;
import com.amazon.clouddrive.extended.model.ListGroupEventsRequest;
import com.amazon.clouddrive.extended.model.ListGroupEventsResponse;
import com.amazon.clouddrive.extended.model.ListGroupsRequest;
import com.amazon.clouddrive.extended.model.ListGroupsResponse;
import com.amazon.clouddrive.extended.model.ListInvitesRequest;
import com.amazon.clouddrive.extended.model.ListInvitesResponse;
import com.amazon.clouddrive.extended.model.ListMembersRequest;
import com.amazon.clouddrive.extended.model.ListMembersResponse;
import com.amazon.clouddrive.extended.model.ListNodesExtendedResponse;
import com.amazon.clouddrive.extended.model.ListNodesInTrashExtendedResponse;
import com.amazon.clouddrive.extended.model.ListNotificationTopicSubscriptionResponse;
import com.amazon.clouddrive.extended.model.ListNotificationTopicSubscriptionsRequest;
import com.amazon.clouddrive.extended.model.ListReactionsRequest;
import com.amazon.clouddrive.extended.model.ListReactionsResponse;
import com.amazon.clouddrive.extended.model.ListSourcesRequest;
import com.amazon.clouddrive.extended.model.ListSourcesResponse;
import com.amazon.clouddrive.extended.model.MoveChildExtendedResponse;
import com.amazon.clouddrive.extended.model.MoveNodeToTrashExtendedRequest;
import com.amazon.clouddrive.extended.model.MoveNodeToTrashExtendedResponse;
import com.amazon.clouddrive.extended.model.OverwriteFileExtendedResponse;
import com.amazon.clouddrive.extended.model.PatchGroupCollectionRequest;
import com.amazon.clouddrive.extended.model.PatchGroupCollectionResponse;
import com.amazon.clouddrive.extended.model.PatchGroupPrivacyPreferencesRequest;
import com.amazon.clouddrive.extended.model.PatchGroupPrivacyPreferencesResponse;
import com.amazon.clouddrive.extended.model.PatchGroupRequest;
import com.amazon.clouddrive.extended.model.PatchGroupResponse;
import com.amazon.clouddrive.extended.model.PatchMemberPreferencesRequest;
import com.amazon.clouddrive.extended.model.PatchMemberPreferencesResponse;
import com.amazon.clouddrive.extended.model.PurgeNodeRequest;
import com.amazon.clouddrive.extended.model.RefreshGroupShareRequest;
import com.amazon.clouddrive.extended.model.RefreshGroupShareResponse;
import com.amazon.clouddrive.extended.model.RemoveChildFromParentExtendedRequest;
import com.amazon.clouddrive.extended.model.RemoveFacesFromPersonRequest;
import com.amazon.clouddrive.extended.model.RenameFaceClusterRequest;
import com.amazon.clouddrive.extended.model.RenameFaceClusterResponse;
import com.amazon.clouddrive.extended.model.RestoreNodeFromTrashExtendedResponse;
import com.amazon.clouddrive.extended.model.SearchContactsRequest;
import com.amazon.clouddrive.extended.model.SearchGroupNodesExtendedResponse;
import com.amazon.clouddrive.extended.model.SearchGroupNodesRequest;
import com.amazon.clouddrive.extended.model.SearchGroupNodesResponse;
import com.amazon.clouddrive.extended.model.SearchKeyRequest;
import com.amazon.clouddrive.extended.model.SearchKeyResponse;
import com.amazon.clouddrive.extended.model.SetNodeRequest;
import com.amazon.clouddrive.extended.model.SetNodeResponse;
import com.amazon.clouddrive.extended.model.SetPreferenceRequest;
import com.amazon.clouddrive.extended.model.SetupAccountRequest;
import com.amazon.clouddrive.extended.model.SetupSourceExtendedRequest;
import com.amazon.clouddrive.extended.model.SourceInfoExtendedResponse;
import com.amazon.clouddrive.extended.model.SubscribeNotificationTopicRequest;
import com.amazon.clouddrive.extended.model.SubscribeNotificationTopicResponse;
import com.amazon.clouddrive.extended.model.SubscribePlanRequest;
import com.amazon.clouddrive.extended.model.SubscribePlanResponse;
import com.amazon.clouddrive.extended.model.UnsubscribeNotificationTopicRequest;
import com.amazon.clouddrive.extended.model.UnsubscribeNotificationTopicResponse;
import com.amazon.clouddrive.extended.model.UpdateFamilyArchiveRequest;
import com.amazon.clouddrive.extended.model.UpdateFamilyArchiveResponse;
import com.amazon.clouddrive.extended.model.UpdateGroupRequest;
import com.amazon.clouddrive.extended.model.UpdateGroupResponse;
import com.amazon.clouddrive.extended.model.UpdateNodeExtendedRequest;
import com.amazon.clouddrive.extended.model.UpdateNodeExtendedResponse;
import com.amazon.clouddrive.extended.model.UploadFileExtendedRequest;
import com.amazon.clouddrive.extended.model.UploadFileExtendedResponse;
import com.amazon.clouddrive.extended.model.ViewGroupRequest;
import com.amazon.clouddrive.extended.model.ViewGroupResponse;
import com.amazon.clouddrive.extended.model.deserializer.CreateNodeExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetChangesExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetNodeExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListChildrenExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListNodesExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListTrashExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.MoveChildExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.OverwriteFileExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.RestoreNodeFromTrashExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.UpdateNodeExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.UploadFileExtendedResponseDeserializer;
import com.amazon.clouddrive.handlers.AsyncHandler;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.internal.Build;
import com.amazon.clouddrive.internal.CloudDriveOperation;
import com.amazon.clouddrive.internal.ExtendedOperationFactory;
import com.amazon.clouddrive.internal.UploadFileExtendedV2ServiceExceptionProvider;
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
public class AmazonCloudDriveExtendedClient implements AmazonCloudDriveExtended {
    private ExecutorService mExecutorService;
    private ExtendedOperationFactory mOperationFactory;

    /* loaded from: classes11.dex */
    private static class BackgroundThreadFactory implements ThreadFactory {
        private static final int THREAD_PRIORITY_BACKGROUND = 4;
        private static final String mPrefix = "AmazonCloudDriveAsyncClient";
        private static AtomicInteger sCounter = new AtomicInteger();

        private BackgroundThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AmazonCloudDriveAsyncClient-");
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

    public AmazonCloudDriveExtendedClient(AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration) {
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

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void addChildToParentExtended(AddChildToParentExtendedRequest addChildToParentExtendedRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newAddChildToParentExtendedOperation(addChildToParentExtendedRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> addChildToParentExtendedAsync(AddChildToParentExtendedRequest addChildToParentExtendedRequest, AsyncHandler<AddChildToParentExtendedRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(addChildToParentExtendedRequest, this.mOperationFactory.newAddChildToParentExtendedOperation(addChildToParentExtendedRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void addFacesToPerson(AddFacesToPersonRequest addFacesToPersonRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newAddFacesToPersonOperation(addFacesToPersonRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> addFacesToPersonAsync(AddFacesToPersonRequest addFacesToPersonRequest, AsyncHandler<AddFacesToPersonRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(addFacesToPersonRequest, this.mOperationFactory.newAddFacesToPersonOperation(addFacesToPersonRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public AddNodePropertyResponse addNodeProperty(AddNodePropertyRequest addNodePropertyRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newAddPropertyOperation(addNodePropertyRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<AddNodePropertyResponse> addNodePropertyAsync(AddNodePropertyRequest addNodePropertyRequest, AsyncHandler<AddNodePropertyRequest, AddNodePropertyResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(addNodePropertyRequest, this.mOperationFactory.newAddPropertyOperation(addNodePropertyRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public BatchAddNodeResponse batchAddNode(BatchAddNodeRequest batchAddNodeRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newBatchAddNodeOperation(batchAddNodeRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<BatchAddNodeResponse> batchAddNodeAsync(BatchAddNodeRequest batchAddNodeRequest, AsyncHandler<BatchAddNodeRequest, BatchAddNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(batchAddNodeRequest, this.mOperationFactory.newBatchAddNodeOperation(batchAddNodeRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public BatchCreateInviteResponse batchCreateInvite(BatchCreateInviteRequest batchCreateInviteRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newBatchCreateInviteOperation(batchCreateInviteRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<BatchCreateInviteResponse> batchCreateInviteAsync(BatchCreateInviteRequest batchCreateInviteRequest, AsyncHandler<BatchCreateInviteRequest, BatchCreateInviteResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(batchCreateInviteRequest, this.mOperationFactory.newBatchCreateInviteOperation(batchCreateInviteRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public BatchRemoveNodeResponse batchRemoveNode(BatchRemoveNodeRequest batchRemoveNodeRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newBatchRemoveNodeOperation(batchRemoveNodeRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<BatchRemoveNodeResponse> batchRemoveNodeAsync(BatchRemoveNodeRequest batchRemoveNodeRequest, AsyncHandler<BatchRemoveNodeRequest, BatchRemoveNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(batchRemoveNodeRequest, this.mOperationFactory.newBatchRemoveNodeOperation(batchRemoveNodeRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public BulkAddRemoveChildResponse bulkAddRemoveChild(BulkAddRemoveChildRequest bulkAddRemoveChildRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newBulkAddRemoveChildOperation(bulkAddRemoveChildRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<BulkAddRemoveChildResponse> bulkAddRemoveChildAsync(BulkAddRemoveChildRequest bulkAddRemoveChildRequest, AsyncHandler<BulkAddRemoveChildRequest, BulkAddRemoveChildResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(bulkAddRemoveChildRequest, this.mOperationFactory.newBulkAddRemoveChildOperation(bulkAddRemoveChildRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void bulkAddToFamilyArchive(BulkAddToFamilyArchiveRequest bulkAddToFamilyArchiveRequest) throws InterruptedException, CloudDriveException {
        this.mOperationFactory.newBulkAddToFamilyArchiveOperation(bulkAddToFamilyArchiveRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> bulkAddToFamilyArchiveAsync(BulkAddToFamilyArchiveRequest bulkAddToFamilyArchiveRequest, AsyncHandler<BulkAddToFamilyArchiveRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(bulkAddToFamilyArchiveRequest, this.mOperationFactory.newBulkAddToFamilyArchiveOperation(bulkAddToFamilyArchiveRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public BulkGetFaceClusterResponse bulkGetFaceCluster(BulkGetFaceClusterRequest bulkGetFaceClusterRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newBulkGetFaceClusterOperation(bulkGetFaceClusterRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<BulkGetFaceClusterResponse> bulkGetFaceClusterAsync(BulkGetFaceClusterRequest bulkGetFaceClusterRequest, AsyncHandler<BulkGetFaceClusterRequest, BulkGetFaceClusterResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(bulkGetFaceClusterRequest, this.mOperationFactory.newBulkGetFaceClusterOperation(bulkGetFaceClusterRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public BulkGetReactionSummariesResponse bulkGetReactionSummaries(BulkGetReactionSummariesRequest bulkGetReactionSummariesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newBulkGetReactionSummariesOperation(bulkGetReactionSummariesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<BulkGetReactionSummariesResponse> bulkGetReactionSummariesAsync(BulkGetReactionSummariesRequest bulkGetReactionSummariesRequest, AsyncHandler<BulkGetReactionSummariesRequest, BulkGetReactionSummariesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(bulkGetReactionSummariesRequest, this.mOperationFactory.newBulkGetReactionSummariesOperation(bulkGetReactionSummariesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void bulkRemoveFromFamilyArchive(BulkRemoveFromFamilyArchiveRequest bulkRemoveFromFamilyArchiveRequest) throws InterruptedException, CloudDriveException {
        this.mOperationFactory.newBulkRemoveFromFamilyArchiveOperation(bulkRemoveFromFamilyArchiveRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> bulkRemoveFromFamilyArchiveAsync(BulkRemoveFromFamilyArchiveRequest bulkRemoveFromFamilyArchiveRequest, AsyncHandler<BulkRemoveFromFamilyArchiveRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(bulkRemoveFromFamilyArchiveRequest, this.mOperationFactory.newBulkRemoveFromFamilyArchiveOperation(bulkRemoveFromFamilyArchiveRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public BulkTrashRestoreNodesResponse bulkTrashRestoreNodes(BulkTrashRestoreNodesRequest bulkTrashRestoreNodesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newBulkTrashOrRestoreNodes(bulkTrashRestoreNodesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<BulkTrashRestoreNodesResponse> bulkTrashRestoreNodesAsync(BulkTrashRestoreNodesRequest bulkTrashRestoreNodesRequest, AsyncHandler<BulkTrashRestoreNodesRequest, BulkTrashRestoreNodesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(bulkTrashRestoreNodesRequest, this.mOperationFactory.newBulkTrashOrRestoreNodes(bulkTrashRestoreNodesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public CreateBatchLinkResponse createBatchLink(CreateBatchLinkRequest createBatchLinkRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newCreateBatchLinkOperation(createBatchLinkRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<CreateBatchLinkResponse> createBatchLinkAsync(CreateBatchLinkRequest createBatchLinkRequest, AsyncHandler<CreateBatchLinkRequest, CreateBatchLinkResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createBatchLinkRequest, this.mOperationFactory.newCreateBatchLinkOperation(createBatchLinkRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public CreateContactBlockResponse createContactBlock(CreateContactBlockRequest createContactBlockRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newCreateContactBlockOperation(createContactBlockRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<CreateContactBlockResponse> createContactBlockAsync(CreateContactBlockRequest createContactBlockRequest, AsyncHandler<CreateContactBlockRequest, CreateContactBlockResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createContactBlockRequest, this.mOperationFactory.newCreateContactBlockOperation(createContactBlockRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void createEvent(CreateEventRequest createEventRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newCreateEventOperation(createEventRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> createEventAsync(CreateEventRequest createEventRequest, AsyncHandler<CreateEventRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createEventRequest, this.mOperationFactory.newCreateEventOperation(createEventRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void createFaceCluster(CreateFaceClusterRequest createFaceClusterRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newCreateFaceClusterOperation(createFaceClusterRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<CreateFaceClusterResponse> createFaceClusterAsync(CreateFaceClusterRequest createFaceClusterRequest, AsyncHandler<CreateFaceClusterRequest, CreateFaceClusterResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createFaceClusterRequest, this.mOperationFactory.newCreateFaceClusterOperation(createFaceClusterRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public CreateGroupResponse createGroup(CreateGroupRequest createGroupRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newCreateGroupOperation(createGroupRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<CreateGroupResponse> createGroupAsync(CreateGroupRequest createGroupRequest, AsyncHandler<CreateGroupRequest, CreateGroupResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createGroupRequest, this.mOperationFactory.newCreateGroupOperation(createGroupRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public CreateMemberResponse createMember(CreateMemberRequest createMemberRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newCreateMember(createMemberRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<CreateMemberResponse> createMemberAsync(CreateMemberRequest createMemberRequest, AsyncHandler<CreateMemberRequest, CreateMemberResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createMemberRequest, this.mOperationFactory.newCreateMember(createMemberRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public CreateMetadataDatabaseResponse createMetadataDatabase(CreateMetadataDatabaseRequest createMetadataDatabaseRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newCreateMetadataDatabaseOperation(createMetadataDatabaseRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public CreateNodeResponse createNode(CreateNodeRequest createNodeRequest) throws CloudDriveException, InterruptedException {
        return (CreateNodeResponse) this.mOperationFactory.newCreateNodeOperation(createNodeRequest, CreateNodeResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<CreateNodeResponse> createNodeAsync(CreateNodeRequest createNodeRequest, AsyncHandler<CreateNodeRequest, CreateNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createNodeRequest, this.mOperationFactory.newCreateNodeOperation(createNodeRequest, CreateNodeResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public CreateNodeExtendedResponse createNodeExtended(CreateNodeRequest createNodeRequest) throws CloudDriveException, InterruptedException {
        return (CreateNodeExtendedResponse) this.mOperationFactory.newCreateNodeOperation(createNodeRequest, CreateNodeExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<CreateNodeExtendedResponse> createNodeExtendedAsync(CreateNodeRequest createNodeRequest, AsyncHandler<CreateNodeRequest, CreateNodeExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createNodeRequest, this.mOperationFactory.newCreateNodeOperation(createNodeRequest, CreateNodeExtendedResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void createPerson(CreatePersonRequest createPersonRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newCreatePersonOperation(createPersonRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> createPersonAsync(CreatePersonRequest createPersonRequest, AsyncHandler<CreatePersonRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createPersonRequest, this.mOperationFactory.newCreatePersonOperation(createPersonRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public CreateReactionResponse createReaction(CreateReactionRequest createReactionRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newCreateReactionOperation(createReactionRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<CreateReactionResponse> createReactionAsync(CreateReactionRequest createReactionRequest, AsyncHandler<CreateReactionRequest, CreateReactionResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(createReactionRequest, this.mOperationFactory.newCreateReactionOperation(createReactionRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void deleteGroup(DeleteGroupRequest deleteGroupRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newDeleteGroupOperation(deleteGroupRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> deleteGroupAsync(DeleteGroupRequest deleteGroupRequest, AsyncHandler<DeleteGroupRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(deleteGroupRequest, this.mOperationFactory.newDeleteGroupOperation(deleteGroupRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void deleteMember(DeleteMemberRequest deleteMemberRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newDeleteMemberOperation(deleteMemberRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> deleteMemberAsync(DeleteMemberRequest deleteMemberRequest, AsyncHandler<DeleteMemberRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(deleteMemberRequest, this.mOperationFactory.newDeleteMemberOperation(deleteMemberRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void deleteNodeProperty(DeleteNodePropertyRequest deleteNodePropertyRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newDeletePropertyOperation(deleteNodePropertyRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> deleteNodePropertyAsync(DeleteNodePropertyRequest deleteNodePropertyRequest, AsyncHandler<DeleteNodePropertyRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(deleteNodePropertyRequest, this.mOperationFactory.newDeletePropertyOperation(deleteNodePropertyRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Void deleteReaction(DeleteReactionRequest deleteReactionRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newDeleteReactionOperation(deleteReactionRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> deleteReactionAsync(DeleteReactionRequest deleteReactionRequest, AsyncHandler<DeleteReactionRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(deleteReactionRequest, this.mOperationFactory.newDeleteReactionOperation(deleteReactionRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void downloadFile(DownloadFileRequest downloadFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newDownloadFileOperation(downloadFileRequest, progressListener).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> downloadFileAsync(DownloadFileRequest downloadFileRequest, ProgressListener progressListener, AsyncHandler<DownloadFileRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(downloadFileRequest, this.mOperationFactory.newDownloadFileOperation(downloadFileRequest, progressListener), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void downloadFileExtended(DownloadFileExtendedRequest downloadFileExtendedRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newDownloadFileExtendedOperation(downloadFileExtendedRequest, progressListener).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> downloadFileExtendedAsync(DownloadFileExtendedRequest downloadFileExtendedRequest, ProgressListener progressListener, AsyncHandler<DownloadFileExtendedRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(downloadFileExtendedRequest, this.mOperationFactory.newDownloadFileExtendedOperation(downloadFileExtendedRequest, progressListener), asyncHandler));
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

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetAggregationsResponse getAggregations(GetAggregationsRequest getAggregationsRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetAggregationsOperation(getAggregationsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetAggregationsResponse> getAggregationsAsync(GetAggregationsRequest getAggregationsRequest, AsyncHandler<GetAggregationsRequest, GetAggregationsResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getAggregationsRequest, this.mOperationFactory.newGetAggregationsOperation(getAggregationsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended, com.amazon.clouddrive.AmazonCloudDrive
    public GetApplicationAccessRulesResponse getApplicationAccessRules(GetApplicationAccessRulesRequest getApplicationAccessRulesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetApplicationAccessRulesOperation(getApplicationAccessRulesRequest, GetApplicationAccessRulesResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended, com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetApplicationAccessRulesResponse> getApplicationAccessRulesAsync(GetApplicationAccessRulesRequest getApplicationAccessRulesRequest, AsyncHandler<GetApplicationAccessRulesRequest, GetApplicationAccessRulesResponse> asyncHandler) {
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

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetChangesExtendedResponse getChangesExtended(GetChangesRequest getChangesRequest) throws CloudDriveException, InterruptedException {
        return (GetChangesExtendedResponse) this.mOperationFactory.newGetChangesOperation(getChangesRequest, GetChangesExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetChangesExtendedResponse> getChangesExtendedAsync(GetChangesRequest getChangesRequest, AsyncHandler<GetChangesRequest, GetChangesExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getChangesRequest, this.mOperationFactory.newGetChangesOperation(getChangesRequest, GetChangesExtendedResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetFacesForNodeResponse getFacesForNode(GetFacesForNodeRequest getFacesForNodeRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetFacesForNodeOperation(getFacesForNodeRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetFacesForNodeResponse> getFacesForNodeAsync(GetFacesForNodeRequest getFacesForNodeRequest, AsyncHandler<GetFacesForNodeRequest, GetFacesForNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getFacesForNodeRequest, this.mOperationFactory.newGetFacesForNodeOperation(getFacesForNodeRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetFacesForPersonResponse getFacesForPerson(GetFacesForPersonRequest getFacesForPersonRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetFacesForPersonOperation(getFacesForPersonRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetFacesForPersonResponse> getFacesForPersonAsync(GetFacesForPersonRequest getFacesForPersonRequest, AsyncHandler<GetFacesForPersonRequest, GetFacesForPersonResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getFacesForPersonRequest, this.mOperationFactory.newGetFacesForPersonOperation(getFacesForPersonRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetFamilyResponse getFamily(GetFamilyRequest getFamilyRequest) throws InterruptedException, CloudDriveException {
        return this.mOperationFactory.newGetFamilyOperation(getFamilyRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetFamilyResponse> getFamilyAsync(GetFamilyRequest getFamilyRequest, AsyncHandler<GetFamilyRequest, GetFamilyResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getFamilyRequest, this.mOperationFactory.newGetFamilyOperation(getFamilyRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetFamilyChangesResponse getFamilyChanges(GetFamilyChangesRequest getFamilyChangesRequest) throws InterruptedException, CloudDriveException {
        return this.mOperationFactory.newGetFamilyChangesOperation(getFamilyChangesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetFamilyChangesResponse> getFamilyChangesAsync(GetFamilyChangesRequest getFamilyChangesRequest, AsyncHandler<GetFamilyChangesRequest, GetFamilyChangesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getFamilyChangesRequest, this.mOperationFactory.newGetFamilyChangesOperation(getFamilyChangesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetGroupResponse getGroup(GetGroupRequest getGroupRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetGroupOperation(getGroupRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetGroupResponse> getGroupAsync(GetGroupRequest getGroupRequest, AsyncHandler<GetGroupRequest, GetGroupResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getGroupRequest, this.mOperationFactory.newGetGroupOperation(getGroupRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetGroupCacheInfoResponse getGroupCacheInfo(GroupRequest groupRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetGroupCacheInfo(groupRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetGroupCacheInfoResponse> getGroupCacheInfoAsync(GroupRequest groupRequest, AsyncHandler<GroupRequest, GetGroupCacheInfoResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(groupRequest, this.mOperationFactory.newGetGroupCacheInfo(groupRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetGroupPrivacyPreferencesResponse getGroupPrivacyPreferences(GetGroupPrivacyPreferencesRequest getGroupPrivacyPreferencesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetGroupPrivacyPreferences(getGroupPrivacyPreferencesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetGroupPrivacyPreferencesResponse> getGroupPrivacyPreferencesAsync(GetGroupPrivacyPreferencesRequest getGroupPrivacyPreferencesRequest, AsyncHandler<GetGroupPrivacyPreferencesRequest, GetGroupPrivacyPreferencesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getGroupPrivacyPreferencesRequest, this.mOperationFactory.newGetGroupPrivacyPreferences(getGroupPrivacyPreferencesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetGroupShareResponse getGroupShare(GetGroupShareRequest getGroupShareRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetGroupShareOperation(getGroupShareRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetGroupShareResponse> getGroupShareAsync(GetGroupShareRequest getGroupShareRequest, AsyncHandler<GetGroupShareRequest, GetGroupShareResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getGroupShareRequest, this.mOperationFactory.newGetGroupShareOperation(getGroupShareRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetGroupShareBehaviorResponse getGroupShareBehavior(GetGroupShareBehaviorRequest getGroupShareBehaviorRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetGroupShareBehaviorOperation(getGroupShareBehaviorRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetGroupShareBehaviorResponse> getGroupShareBehaviorAsync(GetGroupShareBehaviorRequest getGroupShareBehaviorRequest, AsyncHandler<GetGroupShareBehaviorRequest, GetGroupShareBehaviorResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getGroupShareBehaviorRequest, this.mOperationFactory.newGetGroupShareBehaviorOperation(getGroupShareBehaviorRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetGroupShareInfoResponse getGroupShareInfo(GetGroupShareInfoRequest getGroupShareInfoRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetGroupShareInfo(getGroupShareInfoRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetGroupShareInfoResponse> getGroupShareInfoAsync(GetGroupShareInfoRequest getGroupShareInfoRequest, AsyncHandler<GetGroupShareInfoRequest, GetGroupShareInfoResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getGroupShareInfoRequest, this.mOperationFactory.newGetGroupShareInfo(getGroupShareInfoRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetJobStatusResponse getJobStatus(GetJobStatusRequest getJobStatusRequest) throws InterruptedException, CloudDriveException {
        return this.mOperationFactory.newGetJobStatusOperation(getJobStatusRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetJobStatusResponse> getJobStatusAsync(GetJobStatusRequest getJobStatusRequest, AsyncHandler<GetJobStatusRequest, GetJobStatusResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getJobStatusRequest, this.mOperationFactory.newGetJobStatusOperation(getJobStatusRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetMemberPreferencesResponse getMemberPreferences(GetMemberPreferencesRequest getMemberPreferencesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetMemberPreferences(getMemberPreferencesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetMemberPreferencesResponse> getMemberPreferencesAsync(GetMemberPreferencesRequest getMemberPreferencesRequest, AsyncHandler<GetMemberPreferencesRequest, GetMemberPreferencesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getMemberPreferencesRequest, this.mOperationFactory.newGetMemberPreferences(getMemberPreferencesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetMetadataDatabaseStatusResponse getMetadataDatabaseStatus(GetMetadataDatabaseStatusRequest getMetadataDatabaseStatusRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.getMetadataDatabaseStatusOperation(getMetadataDatabaseStatusRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetNodeResponse getNode(GetNodeRequest getNodeRequest) throws CloudDriveException, InterruptedException {
        return (GetNodeResponse) this.mOperationFactory.newGetNodeOperation(getNodeRequest, GetNodeResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetNodeResponse> getNodeAsync(GetNodeRequest getNodeRequest, AsyncHandler<GetNodeRequest, GetNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getNodeRequest, this.mOperationFactory.newGetNodeOperation(getNodeRequest, GetNodeResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetNodeExtendedResponse getNodeExtended(GetNodeExtendedRequest getNodeExtendedRequest) throws CloudDriveException, InterruptedException {
        return (GetNodeExtendedResponse) this.mOperationFactory.newGetNodeExtendedOperation(getNodeExtendedRequest, GetNodeExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetNodeExtendedResponse> getNodeExtendedAsync(GetNodeExtendedRequest getNodeExtendedRequest, AsyncHandler<GetNodeExtendedRequest, GetNodeExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getNodeExtendedRequest, this.mOperationFactory.newGetNodeExtendedOperation(getNodeExtendedRequest, GetNodeExtendedResponseDeserializer.INSTANCE), asyncHandler));
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

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetPreferencesResponse getPreferences(GetPreferencesRequest getPreferencesRequest) throws InterruptedException, CloudDriveException {
        return this.mOperationFactory.newGetPreferencesOperation(getPreferencesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetPreferencesResponse> getPreferencesAsync(GetPreferencesRequest getPreferencesRequest, AsyncHandler<GetPreferencesRequest, GetPreferencesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getPreferencesRequest, this.mOperationFactory.newGetPreferencesOperation(getPreferencesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetProfileResponse getProfile(GetProfileRequest getProfileRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetProfileOperation(getProfileRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetProfileResponse> getProfileAsync(GetProfileRequest getProfileRequest, AsyncHandler<GetProfileRequest, GetProfileResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getProfileRequest, this.mOperationFactory.newGetProfileOperation(getProfileRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetReUpdateNodesResponse getReUpdateNodes(GetReUpdateNodesRequest getReUpdateNodesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetReUpdateNodesOperation(getReUpdateNodesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetReUpdateNodesResponse> getReUpdateNodesAsync(GetReUpdateNodesRequest getReUpdateNodesRequest, AsyncHandler<GetReUpdateNodesRequest, GetReUpdateNodesResponse> asyncHandler) throws CloudDriveException, InterruptedException {
        return this.mExecutorService.submit(new CallWithHandler(getReUpdateNodesRequest, this.mOperationFactory.newGetReUpdateNodesOperation(getReUpdateNodesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetReactionResponse getReaction(GetReactionRequest getReactionRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetReactionOperation(getReactionRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetReactionResponse> getReactionAsync(GetReactionRequest getReactionRequest, AsyncHandler<GetReactionRequest, GetReactionResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getReactionRequest, this.mOperationFactory.newGetReactionOperation(getReactionRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public RefreshGroupShareResponse getRefreshGroupShare(RefreshGroupShareRequest refreshGroupShareRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newRefreshGroupShareOperation(refreshGroupShareRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<RefreshGroupShareResponse> getRefreshGroupShareAsync(RefreshGroupShareRequest refreshGroupShareRequest, AsyncHandler<RefreshGroupShareRequest, RefreshGroupShareResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(refreshGroupShareRequest, this.mOperationFactory.newRefreshGroupShareOperation(refreshGroupShareRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetSearchSuggestionsResponse getSearchSuggestions(GetSearchSuggestionsRequest getSearchSuggestionsRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetSearchSuggestionsOperation(getSearchSuggestionsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetSearchSuggestionsResponse> getSearchSuggestionsAsync(GetSearchSuggestionsRequest getSearchSuggestionsRequest, AsyncHandler<GetSearchSuggestionsRequest, GetSearchSuggestionsResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getSearchSuggestionsRequest, this.mOperationFactory.newGetSearchSuggestionsOperation(getSearchSuggestionsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetServicePlansResponse getServicePlans(GetServicePlansRequest getServicePlansRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetServicePlansOperation(getServicePlansRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetServicePlansResponse> getServicePlansAsync(GetServicePlansRequest getServicePlansRequest, AsyncHandler<GetServicePlansRequest, GetServicePlansResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getServicePlansRequest, this.mOperationFactory.newGetServicePlansOperation(getServicePlansRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetSplashResponse getSplash(GetSplashRequest getSplashRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetSplashOperation(getSplashRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetSplashResponse> getSplashAsync(GetSplashRequest getSplashRequest, AsyncHandler<GetSplashRequest, GetSplashResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getSplashRequest, this.mOperationFactory.newGetSplashOperation(getSplashRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public GetSubscriptionsResponse getSubscriptions(GetSubscriptionsRequest getSubscriptionsRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetSubscriptionsOperation(getSubscriptionsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<GetSubscriptionsResponse> getSubscriptionsAsync(GetSubscriptionsRequest getSubscriptionsRequest, AsyncHandler<GetSubscriptionsRequest, GetSubscriptionsResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getSubscriptionsRequest, this.mOperationFactory.newGetSubscriptionsOperation(getSubscriptionsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void getThumbnail(GetThumbnailRequest getThumbnailRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newGetThumbnailOperation(getThumbnailRequest, progressListener).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> getThumbnailAsync(GetThumbnailRequest getThumbnailRequest, ProgressListener progressListener, AsyncHandler<GetThumbnailRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getThumbnailRequest, this.mOperationFactory.newGetThumbnailOperation(getThumbnailRequest, progressListener), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void getThumbnailExtended(GetThumbnailExtendedRequest getThumbnailExtendedRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newGetThumbnailExtendedOperation(getThumbnailExtendedRequest, progressListener).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> getThumbnailExtendedAsync(GetThumbnailExtendedRequest getThumbnailExtendedRequest, ProgressListener progressListener, AsyncHandler<GetThumbnailExtendedRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getThumbnailExtendedRequest, this.mOperationFactory.newGetThumbnailExtendedOperation(getThumbnailExtendedRequest, progressListener), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public GetUploadFileProgressResponse getUploadFileProgress(GetUploadFileProgressRequest getUploadFileProgressRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newGetUploadFileProgressOperation(getUploadFileProgressRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<GetUploadFileProgressResponse> getUploadFileProgressAsync(GetUploadFileProgressRequest getUploadFileProgressRequest, AsyncHandler<GetUploadFileProgressRequest, GetUploadFileProgressResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(getUploadFileProgressRequest, this.mOperationFactory.newGetUploadFileProgressOperation(getUploadFileProgressRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void importContacts(ImportContactsRequest importContactsRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newImportContactsOperation(importContactsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> importContactsAsync(ImportContactsRequest importContactsRequest, AsyncHandler<ImportContactsRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(importContactsRequest, this.mOperationFactory.newImportContactsOperation(importContactsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public ListChildrenResponse listChildren(ListChildrenRequest listChildrenRequest) throws CloudDriveException, InterruptedException {
        return (ListChildrenResponse) this.mOperationFactory.newListChildrenOperation(listChildrenRequest, ListChildrenResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<ListChildrenResponse> listChildrenAsync(ListChildrenRequest listChildrenRequest, AsyncHandler<ListChildrenRequest, ListChildrenResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listChildrenRequest, this.mOperationFactory.newListChildrenOperation(listChildrenRequest, ListChildrenResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListChildrenExtendedResponse listChildrenExtended(ListChildrenExtendedRequest listChildrenExtendedRequest) throws CloudDriveException, InterruptedException {
        return (ListChildrenExtendedResponse) this.mOperationFactory.newListChildrenExtendedOperation(listChildrenExtendedRequest, ListChildrenExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListChildrenExtendedResponse> listChildrenExtendedAsync(ListChildrenExtendedRequest listChildrenExtendedRequest, AsyncHandler<ListChildrenExtendedRequest, ListChildrenExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listChildrenExtendedRequest, this.mOperationFactory.newListChildrenExtendedOperation(listChildrenExtendedRequest, ListChildrenExtendedResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListCollectionNodesResponse listCollectionNodes(ListCollectionNodesRequest listCollectionNodesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListCollectionNodes(listCollectionNodesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListCollectionNodesResponse> listCollectionNodesAsync(ListCollectionNodesRequest listCollectionNodesRequest, AsyncHandler<ListCollectionNodesRequest, ListCollectionNodesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listCollectionNodesRequest, this.mOperationFactory.newListCollectionNodes(listCollectionNodesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListContactsResponse listContacts(ListContactsRequest listContactsRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListContactsOperation(listContactsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListContactsResponse> listContactsAsync(ListContactsRequest listContactsRequest, AsyncHandler<ListContactsRequest, ListContactsResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listContactsRequest, this.mOperationFactory.newListContactsOperation(listContactsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListGroupEventsResponse listGroupEvents(ListGroupEventsRequest listGroupEventsRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListGroupEvents(listGroupEventsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListGroupEventsResponse> listGroupEventsAsync(ListGroupEventsRequest listGroupEventsRequest, AsyncHandler<ListGroupEventsRequest, ListGroupEventsResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listGroupEventsRequest, this.mOperationFactory.newListGroupEvents(listGroupEventsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListGroupsResponse listGroups(ListGroupsRequest listGroupsRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListGroupsOperation(listGroupsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListGroupsResponse> listGroupsAsync(ListGroupsRequest listGroupsRequest, AsyncHandler<ListGroupsRequest, ListGroupsResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listGroupsRequest, this.mOperationFactory.newListGroupsOperation(listGroupsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListInvitesResponse listInvites(ListInvitesRequest listInvitesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListInvitesOperation(listInvitesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListInvitesResponse> listInvitesAsync(ListInvitesRequest listInvitesRequest, AsyncHandler<ListInvitesRequest, ListInvitesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listInvitesRequest, this.mOperationFactory.newListInvitesOperation(listInvitesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListMembersResponse listMembers(ListMembersRequest listMembersRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListMembersOperation(listMembersRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListMembersResponse> listMembersAsync(ListMembersRequest listMembersRequest, AsyncHandler<ListMembersRequest, ListMembersResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listMembersRequest, this.mOperationFactory.newListMembersOperation(listMembersRequest), asyncHandler));
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

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListNodesExtendedResponse listNodesExtended(ListNodesRequest listNodesRequest) throws CloudDriveException, InterruptedException {
        return (ListNodesExtendedResponse) this.mOperationFactory.newListNodesOperation(listNodesRequest, ListNodesExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListNodesExtendedResponse> listNodesExtendedAsync(ListNodesRequest listNodesRequest, AsyncHandler<ListNodesRequest, ListNodesExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listNodesRequest, this.mOperationFactory.newListNodesOperation(listNodesRequest, ListNodesExtendedResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public ListNodesInTrashResponse listNodesInTrash(ListNodesInTrashRequest listNodesInTrashRequest) throws CloudDriveException, InterruptedException {
        return (ListNodesInTrashResponse) this.mOperationFactory.newListTrashOperation(listNodesInTrashRequest, ListTrashResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<ListNodesInTrashResponse> listNodesInTrashAsync(ListNodesInTrashRequest listNodesInTrashRequest, AsyncHandler<ListNodesInTrashRequest, ListNodesInTrashResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listNodesInTrashRequest, this.mOperationFactory.newListTrashOperation(listNodesInTrashRequest, ListTrashResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListNodesInTrashExtendedResponse listNodesInTrashExtended(ListNodesInTrashRequest listNodesInTrashRequest) throws CloudDriveException, InterruptedException {
        return (ListNodesInTrashExtendedResponse) this.mOperationFactory.newListTrashOperation(listNodesInTrashRequest, ListTrashExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListNodesInTrashExtendedResponse> listNodesInTrashExtendedAsync(ListNodesInTrashRequest listNodesInTrashRequest, AsyncHandler<ListNodesInTrashRequest, ListNodesInTrashExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listNodesInTrashRequest, this.mOperationFactory.newListTrashOperation(listNodesInTrashRequest, ListTrashExtendedResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListNotificationTopicSubscriptionResponse listNotificationTopicSubscriptions(ListNotificationTopicSubscriptionsRequest listNotificationTopicSubscriptionsRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListNotificationTopicSubscriptionsOperation(listNotificationTopicSubscriptionsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListNotificationTopicSubscriptionResponse> listNotificationTopicSubscriptionsAsync(ListNotificationTopicSubscriptionsRequest listNotificationTopicSubscriptionsRequest, AsyncHandler<ListNotificationTopicSubscriptionsRequest, ListNotificationTopicSubscriptionResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listNotificationTopicSubscriptionsRequest, this.mOperationFactory.newListNotificationTopicSubscriptionsOperation(listNotificationTopicSubscriptionsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListReactionsResponse listReactions(ListReactionsRequest listReactionsRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListReactionsOperation(listReactionsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListReactionsResponse> listReactionsAsync(ListReactionsRequest listReactionsRequest, AsyncHandler<ListReactionsRequest, ListReactionsResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listReactionsRequest, this.mOperationFactory.newListReactionsOperation(listReactionsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListSourcesResponse listSources(ListSourcesRequest listSourcesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newListSourcesOperation(listSourcesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListSourcesResponse> listSourcesAsync(ListSourcesRequest listSourcesRequest, AsyncHandler<ListSourcesRequest, ListSourcesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(listSourcesRequest, this.mOperationFactory.newListSourcesOperation(listSourcesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public MoveChildResponse moveChild(MoveChildRequest moveChildRequest) throws CloudDriveException, InterruptedException {
        return (MoveChildResponse) this.mOperationFactory.newMoveChildOperation(moveChildRequest, MoveChildResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<MoveChildResponse> moveChildAsync(MoveChildRequest moveChildRequest, AsyncHandler<MoveChildRequest, MoveChildResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(moveChildRequest, this.mOperationFactory.newMoveChildOperation(moveChildRequest, MoveChildResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public MoveChildExtendedResponse moveChildExtended(MoveChildRequest moveChildRequest) throws CloudDriveException, InterruptedException {
        return (MoveChildExtendedResponse) this.mOperationFactory.newMoveChildOperation(moveChildRequest, MoveChildExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<MoveChildExtendedResponse> moveChildExtendedAsync(MoveChildRequest moveChildRequest, AsyncHandler<MoveChildRequest, MoveChildExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(moveChildRequest, this.mOperationFactory.newMoveChildOperation(moveChildRequest, MoveChildExtendedResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public MoveNodeToTrashResponse moveNodeToTrash(MoveNodeToTrashRequest moveNodeToTrashRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newMoveNodeToTrashOperation(moveNodeToTrashRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<MoveNodeToTrashResponse> moveNodeToTrashAsync(MoveNodeToTrashRequest moveNodeToTrashRequest, AsyncHandler<MoveNodeToTrashRequest, MoveNodeToTrashResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(moveNodeToTrashRequest, this.mOperationFactory.newMoveNodeToTrashOperation(moveNodeToTrashRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public MoveNodeToTrashExtendedResponse moveNodeToTrashExtended(MoveNodeToTrashExtendedRequest moveNodeToTrashExtendedRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newMoveNodeToTrashExtendedOperation(moveNodeToTrashExtendedRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<MoveNodeToTrashExtendedResponse> moveNodeToTrashExtendedAsync(MoveNodeToTrashExtendedRequest moveNodeToTrashExtendedRequest, AsyncHandler<MoveNodeToTrashExtendedRequest, MoveNodeToTrashExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(moveNodeToTrashExtendedRequest, this.mOperationFactory.newMoveNodeToTrashExtendedOperation(moveNodeToTrashExtendedRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public OverwriteFileResponse overwriteFile(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        return (OverwriteFileResponse) this.mOperationFactory.newOverwriteFileOperation(overwriteFileRequest, progressListener, OverwriteFileResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<OverwriteFileResponse> overwriteFileAsync(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener, AsyncHandler<OverwriteFileRequest, OverwriteFileResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(overwriteFileRequest, this.mOperationFactory.newOverwriteFileOperation(overwriteFileRequest, progressListener, OverwriteFileResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public OverwriteFileExtendedResponse overwriteFileExtended(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        return (OverwriteFileExtendedResponse) this.mOperationFactory.newOverwriteFileOperation(overwriteFileRequest, progressListener, OverwriteFileExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<OverwriteFileExtendedResponse> overwriteFileExtendedAsync(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener, AsyncHandler<OverwriteFileRequest, OverwriteFileExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(overwriteFileRequest, this.mOperationFactory.newOverwriteFileOperation(overwriteFileRequest, progressListener, OverwriteFileExtendedResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public PatchGroupResponse patchGroup(PatchGroupRequest patchGroupRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newPatchGroupOperation(patchGroupRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<PatchGroupResponse> patchGroupAsync(PatchGroupRequest patchGroupRequest, AsyncHandler<PatchGroupRequest, PatchGroupResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(patchGroupRequest, this.mOperationFactory.newPatchGroupOperation(patchGroupRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public PatchGroupCollectionResponse patchGroupCollection(PatchGroupCollectionRequest patchGroupCollectionRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newPatchGroupCollectionOperation(patchGroupCollectionRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<PatchGroupCollectionResponse> patchGroupCollectionAsync(PatchGroupCollectionRequest patchGroupCollectionRequest, AsyncHandler<PatchGroupCollectionRequest, PatchGroupCollectionResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(patchGroupCollectionRequest, this.mOperationFactory.newPatchGroupCollectionOperation(patchGroupCollectionRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public PatchGroupPrivacyPreferencesResponse patchGroupPrivacyPreferences(PatchGroupPrivacyPreferencesRequest patchGroupPrivacyPreferencesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newPatchGroupPrivacyPreferences(patchGroupPrivacyPreferencesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<PatchGroupPrivacyPreferencesResponse> patchGroupPrivacyPreferencesAsync(PatchGroupPrivacyPreferencesRequest patchGroupPrivacyPreferencesRequest, AsyncHandler<PatchGroupPrivacyPreferencesRequest, PatchGroupPrivacyPreferencesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(patchGroupPrivacyPreferencesRequest, this.mOperationFactory.newPatchGroupPrivacyPreferences(patchGroupPrivacyPreferencesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public PatchMemberPreferencesResponse patchMemberPreferences(PatchMemberPreferencesRequest patchMemberPreferencesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newPatchMemberPreferences(patchMemberPreferencesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<PatchMemberPreferencesResponse> patchMemberPreferencesAsync(PatchMemberPreferencesRequest patchMemberPreferencesRequest, AsyncHandler<PatchMemberPreferencesRequest, PatchMemberPreferencesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(patchMemberPreferencesRequest, this.mOperationFactory.newPatchMemberPreferences(patchMemberPreferencesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void purgeNode(PurgeNodeRequest purgeNodeRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newPurgeNodeOperation(purgeNodeRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> purgeNodeAsync(PurgeNodeRequest purgeNodeRequest, AsyncHandler<PurgeNodeRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(purgeNodeRequest, this.mOperationFactory.newPurgeNodeOperation(purgeNodeRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public void removeChildFromParent(RemoveChildFromParentRequest removeChildFromParentRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newRemoveChildFromParentOperation(removeChildFromParentRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<Void> removeChildFromParentAsync(RemoveChildFromParentRequest removeChildFromParentRequest, AsyncHandler<RemoveChildFromParentRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(removeChildFromParentRequest, this.mOperationFactory.newRemoveChildFromParentOperation(removeChildFromParentRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void removeChildFromParentExtended(RemoveChildFromParentExtendedRequest removeChildFromParentExtendedRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newRemoveChildFromParentExtendedOperation(removeChildFromParentExtendedRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> removeChildFromParentExtendedAsync(RemoveChildFromParentExtendedRequest removeChildFromParentExtendedRequest, AsyncHandler<RemoveChildFromParentExtendedRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(removeChildFromParentExtendedRequest, this.mOperationFactory.newRemoveChildFromParentExtendedOperation(removeChildFromParentExtendedRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void removeFacesFromPerson(RemoveFacesFromPersonRequest removeFacesFromPersonRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newRemoveFacesFromPersonOperation(removeFacesFromPersonRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> removeFacesFromPersonAsync(RemoveFacesFromPersonRequest removeFacesFromPersonRequest, AsyncHandler<RemoveFacesFromPersonRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(removeFacesFromPersonRequest, this.mOperationFactory.newRemoveFacesFromPersonOperation(removeFacesFromPersonRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public RenameFaceClusterResponse renameFaceCluster(RenameFaceClusterRequest renameFaceClusterRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newRenameFaceClusterOperation(renameFaceClusterRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<RenameFaceClusterResponse> renameFaceClusterAsync(RenameFaceClusterRequest renameFaceClusterRequest, AsyncHandler<RenameFaceClusterRequest, RenameFaceClusterResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(renameFaceClusterRequest, this.mOperationFactory.newRenameFaceClusterOperation(renameFaceClusterRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public RestoreNodeFromTrashResponse restoreNodeFromTrash(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest) throws CloudDriveException, InterruptedException {
        return (RestoreNodeFromTrashResponse) this.mOperationFactory.newRestoreNodeFromTrashOperation(restoreNodeFromTrashRequest, RestoreNodeFromTrashResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<RestoreNodeFromTrashResponse> restoreNodeFromTrashAsync(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest, AsyncHandler<RestoreNodeFromTrashRequest, RestoreNodeFromTrashResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(restoreNodeFromTrashRequest, this.mOperationFactory.newRestoreNodeFromTrashOperation(restoreNodeFromTrashRequest, RestoreNodeFromTrashResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public RestoreNodeFromTrashExtendedResponse restoreNodeFromTrashExtended(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest) throws CloudDriveException, InterruptedException {
        return (RestoreNodeFromTrashExtendedResponse) this.mOperationFactory.newRestoreNodeFromTrashOperation(restoreNodeFromTrashRequest, RestoreNodeFromTrashExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<RestoreNodeFromTrashExtendedResponse> restoreNodeFromTrashExtendedAsync(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest, AsyncHandler<RestoreNodeFromTrashRequest, RestoreNodeFromTrashExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(restoreNodeFromTrashRequest, this.mOperationFactory.newRestoreNodeFromTrashOperation(restoreNodeFromTrashRequest, RestoreNodeFromTrashExtendedResponseDeserializer.INSTANCE), asyncHandler));
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

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ListContactsResponse searchContacts(SearchContactsRequest searchContactsRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSearchContactsOperation(searchContactsRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ListContactsResponse> searchContactsAsync(SearchContactsRequest searchContactsRequest, AsyncHandler<SearchContactsRequest, ListContactsResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(searchContactsRequest, this.mOperationFactory.newSearchContactsOperation(searchContactsRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public SearchGroupNodesResponse searchGroupNodes(SearchGroupNodesRequest searchGroupNodesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSearchGroupNodes(searchGroupNodesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<SearchGroupNodesResponse> searchGroupNodesAsync(SearchGroupNodesRequest searchGroupNodesRequest, AsyncHandler<SearchGroupNodesRequest, SearchGroupNodesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(searchGroupNodesRequest, this.mOperationFactory.newSearchGroupNodes(searchGroupNodesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public SearchGroupNodesExtendedResponse searchGroupNodesExtended(SearchGroupNodesRequest searchGroupNodesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSearchGroupNodesExtended(searchGroupNodesRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<SearchGroupNodesExtendedResponse> searchGroupNodesExtendedAsync(SearchGroupNodesRequest searchGroupNodesRequest, AsyncHandler<SearchGroupNodesRequest, SearchGroupNodesExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(searchGroupNodesRequest, this.mOperationFactory.newSearchGroupNodesExtended(searchGroupNodesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public SearchKeyResponse searchKey(SearchKeyRequest searchKeyRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSearchKey(searchKeyRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<SearchKeyResponse> searchKeyAsync(SearchKeyRequest searchKeyRequest, AsyncHandler<SearchKeyRequest, SearchKeyResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(searchKeyRequest, this.mOperationFactory.newSearchKey(searchKeyRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public SetNodeResponse setNode(SetNodeRequest setNodeRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSetNodeOperation(setNodeRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<SetNodeResponse> setNodeAsync(SetNodeRequest setNodeRequest, AsyncHandler<SetNodeRequest, SetNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(setNodeRequest, this.mOperationFactory.newSetNodeOperation(setNodeRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void setPreference(SetPreferenceRequest setPreferenceRequest) throws InterruptedException, CloudDriveException {
        this.mOperationFactory.newSetPreferenceOperation(setPreferenceRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> setPreferenceAsync(SetPreferenceRequest setPreferenceRequest, AsyncHandler<SetPreferenceRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(setPreferenceRequest, this.mOperationFactory.newSetPreferenceOperation(setPreferenceRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public void setupAccount(SetupAccountRequest setupAccountRequest) throws CloudDriveException, InterruptedException {
        this.mOperationFactory.newSetupAccountOperation(setupAccountRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<Void> setupAccountAsync(SetupAccountRequest setupAccountRequest, AsyncHandler<SetupAccountRequest, Void> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(setupAccountRequest, this.mOperationFactory.newSetupAccountOperation(setupAccountRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public SourceInfoResponse setupSource(SetupSourceRequest setupSourceRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSetupSourceOperation(setupSourceRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<SourceInfoResponse> setupSourceAsync(SetupSourceRequest setupSourceRequest, AsyncHandler<SetupSourceRequest, SourceInfoResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(setupSourceRequest, this.mOperationFactory.newSetupSourceOperation(setupSourceRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public SourceInfoExtendedResponse setupSourceExtended(SetupSourceExtendedRequest setupSourceExtendedRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSetupSourceExtendedOperation(setupSourceExtendedRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<SourceInfoExtendedResponse> setupSourceExtendedAsync(SetupSourceExtendedRequest setupSourceExtendedRequest, AsyncHandler<SetupSourceExtendedRequest, SourceInfoExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(setupSourceExtendedRequest, this.mOperationFactory.newSetupSourceExtendedOperation(setupSourceExtendedRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public SubscribeNotificationTopicResponse subscribeNotificationTopic(SubscribeNotificationTopicRequest subscribeNotificationTopicRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSubscribeNotificationTopicOperation(subscribeNotificationTopicRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<SubscribeNotificationTopicResponse> subscribeNotificationTopicAsync(SubscribeNotificationTopicRequest subscribeNotificationTopicRequest, AsyncHandler<SubscribeNotificationTopicRequest, SubscribeNotificationTopicResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(subscribeNotificationTopicRequest, this.mOperationFactory.newSubscribeNotificationTopicOperation(subscribeNotificationTopicRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public SubscribePlanResponse subscribePlan(SubscribePlanRequest subscribePlanRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newSubscribePlanOperation(subscribePlanRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<SubscribePlanResponse> subscribePlanAsync(SubscribePlanRequest subscribePlanRequest, AsyncHandler<SubscribePlanRequest, SubscribePlanResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(subscribePlanRequest, this.mOperationFactory.newSubscribePlanOperation(subscribePlanRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public UnsubscribeNotificationTopicResponse unsubscribeNotificationTopic(UnsubscribeNotificationTopicRequest unsubscribeNotificationTopicRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newUnubscribeNotificationTopicOperation(unsubscribeNotificationTopicRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<UnsubscribeNotificationTopicResponse> unsubscribeNotificationTopicAsync(UnsubscribeNotificationTopicRequest unsubscribeNotificationTopicRequest, AsyncHandler<UnsubscribeNotificationTopicRequest, UnsubscribeNotificationTopicResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(unsubscribeNotificationTopicRequest, this.mOperationFactory.newUnubscribeNotificationTopicOperation(unsubscribeNotificationTopicRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public CollectionPropertiesResponse updateCollectionProperties(UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newUpdateCollectionPropertiesOperation(updateCollectionPropertiesRequest).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<CollectionPropertiesResponse> updateCollectionPropertiesAsync(UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest, AsyncHandler<UpdateCollectionPropertiesRequest, CollectionPropertiesResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(updateCollectionPropertiesRequest, this.mOperationFactory.newUpdateCollectionPropertiesOperation(updateCollectionPropertiesRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public UpdateFamilyArchiveResponse updateFamilyArchive(UpdateFamilyArchiveRequest updateFamilyArchiveRequest) throws InterruptedException, CloudDriveException {
        return this.mOperationFactory.newUpdateFamilyArchiveOperation(updateFamilyArchiveRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<UpdateFamilyArchiveResponse> updateFamilyArchiveAsync(UpdateFamilyArchiveRequest updateFamilyArchiveRequest, AsyncHandler<UpdateFamilyArchiveRequest, UpdateFamilyArchiveResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(updateFamilyArchiveRequest, this.mOperationFactory.newUpdateFamilyArchiveOperation(updateFamilyArchiveRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public UpdateGroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newUpdateGroupOperation(updateGroupRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<UpdateGroupResponse> updateGroupAsync(UpdateGroupRequest updateGroupRequest, AsyncHandler<UpdateGroupRequest, UpdateGroupResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(updateGroupRequest, this.mOperationFactory.newUpdateGroupOperation(updateGroupRequest), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public UpdateNodeResponse updateNode(UpdateNodeRequest updateNodeRequest) throws CloudDriveException, InterruptedException {
        return (UpdateNodeResponse) this.mOperationFactory.newUpdateNodeOperation(updateNodeRequest, UpdateNodeResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<UpdateNodeResponse> updateNodeAsync(UpdateNodeRequest updateNodeRequest, AsyncHandler<UpdateNodeRequest, UpdateNodeResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(updateNodeRequest, this.mOperationFactory.newUpdateNodeOperation(updateNodeRequest, UpdateNodeResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public UpdateNodeExtendedResponse updateNodeExtended(UpdateNodeExtendedRequest updateNodeExtendedRequest) throws CloudDriveException, InterruptedException {
        return (UpdateNodeExtendedResponse) this.mOperationFactory.newUpdateNodeExtendedOperation(updateNodeExtendedRequest, UpdateNodeExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<UpdateNodeExtendedResponse> updateNodeExtendedAsync(UpdateNodeExtendedRequest updateNodeExtendedRequest, AsyncHandler<UpdateNodeExtendedRequest, UpdateNodeExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(updateNodeExtendedRequest, this.mOperationFactory.newUpdateNodeExtendedOperation(updateNodeExtendedRequest, UpdateNodeExtendedResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public UploadFileResponse uploadFile(UploadFileRequest uploadFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        return (UploadFileResponse) this.mOperationFactory.newUploadFileOperation(uploadFileRequest, progressListener, UploadFileResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    public Future<UploadFileResponse> uploadFileAsync(UploadFileRequest uploadFileRequest, ProgressListener progressListener, AsyncHandler<UploadFileRequest, UploadFileResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(uploadFileRequest, this.mOperationFactory.newUploadFileOperation(uploadFileRequest, progressListener, UploadFileResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public UploadFileExtendedResponse uploadFileExtended(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        return (UploadFileExtendedResponse) this.mOperationFactory.newUploadFileExtendedOperation(uploadFileExtendedRequest, progressListener, UploadFileExtendedResponseDeserializer.INSTANCE).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<UploadFileExtendedResponse> uploadFileExtendedAsync(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener, AsyncHandler<UploadFileRequest, UploadFileExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(uploadFileExtendedRequest, this.mOperationFactory.newUploadFileExtendedOperation(uploadFileExtendedRequest, progressListener, UploadFileExtendedResponseDeserializer.INSTANCE), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public UploadFileExtendedResponse uploadFileExtendedV2(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException {
        return (UploadFileExtendedResponse) this.mOperationFactory.newUploadFileExtendedV2Operation(uploadFileExtendedRequest, progressListener, UploadFileExtendedResponseDeserializer.INSTANCE, new UploadFileExtendedV2ServiceExceptionProvider()).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<UploadFileExtendedResponse> uploadFileExtendedV2Async(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener, AsyncHandler<UploadFileRequest, UploadFileExtendedResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(uploadFileExtendedRequest, this.mOperationFactory.newUploadFileExtendedV2Operation(uploadFileExtendedRequest, progressListener, UploadFileExtendedResponseDeserializer.INSTANCE, new UploadFileExtendedV2ServiceExceptionProvider()), asyncHandler));
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public ViewGroupResponse viewGroup(ViewGroupRequest viewGroupRequest) throws CloudDriveException, InterruptedException {
        return this.mOperationFactory.newViewGroupOperation(viewGroupRequest).call();
    }

    @Override // com.amazon.clouddrive.extended.AmazonCloudDriveExtended
    public Future<ViewGroupResponse> viewGroupAsync(ViewGroupRequest viewGroupRequest, AsyncHandler<ViewGroupRequest, ViewGroupResponse> asyncHandler) {
        return this.mExecutorService.submit(new CallWithHandler(viewGroupRequest, this.mOperationFactory.newViewGroupOperation(viewGroupRequest), asyncHandler));
    }

    public AmazonCloudDriveExtendedClient(AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration, ExecutorService executorService) {
        ClientSdkAuthenticator clientSdkAuthenticator = new ClientSdkAuthenticator(accountConfiguration.getRequestAuthenticator());
        OkHttpClient.Builder addInterceptor = clientConfiguration.getHttpClient().newBuilder().connectTimeout(clientConfiguration.getConnectionTimeOutMillis(), TimeUnit.MILLISECONDS).readTimeout(clientConfiguration.getReadTimeOutMillis(), TimeUnit.MILLISECONDS).connectionSpecs(clientConfiguration.getConnectionSpecs()).hostnameVerifier(clientConfiguration.getHostNameVerifier()).authenticator(clientSdkAuthenticator).addInterceptor(clientSdkAuthenticator);
        this.mOperationFactory = new ExtendedOperationFactory(accountConfiguration, clientConfiguration.newBuilder().setHttpClient(addInterceptor.addInterceptor(new UserAgentInterceptor(clientConfiguration.getUserAgent() + " " + Build.SDK_AGENT)).build()).build());
        this.mExecutorService = executorService;
    }
}
