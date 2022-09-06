package com.amazon.clouddrive.extended;

import com.amazon.clouddrive.AmazonCloudDrive;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.InvalidParameter;
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
import com.amazon.clouddrive.handlers.AsyncHandler;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.model.CreateNodeRequest;
import com.amazon.clouddrive.model.GetApplicationAccessRulesRequest;
import com.amazon.clouddrive.model.GetApplicationAccessRulesResponse;
import com.amazon.clouddrive.model.GetChangesRequest;
import com.amazon.clouddrive.model.ListNodesInTrashRequest;
import com.amazon.clouddrive.model.ListNodesRequest;
import com.amazon.clouddrive.model.MoveChildRequest;
import com.amazon.clouddrive.model.OverwriteFileRequest;
import com.amazon.clouddrive.model.RestoreNodeFromTrashRequest;
import com.amazon.clouddrive.model.UploadFileRequest;
import java.util.concurrent.Future;
/* loaded from: classes11.dex */
public interface AmazonCloudDriveExtended extends AmazonCloudDrive {
    void addChildToParentExtended(AddChildToParentExtendedRequest addChildToParentExtendedRequest) throws CloudDriveException, InterruptedException;

    Future<Void> addChildToParentExtendedAsync(AddChildToParentExtendedRequest addChildToParentExtendedRequest, AsyncHandler<AddChildToParentExtendedRequest, Void> asyncHandler);

    void addFacesToPerson(AddFacesToPersonRequest addFacesToPersonRequest) throws CloudDriveException, InterruptedException;

    Future<Void> addFacesToPersonAsync(AddFacesToPersonRequest addFacesToPersonRequest, AsyncHandler<AddFacesToPersonRequest, Void> asyncHandler);

    BatchAddNodeResponse batchAddNode(BatchAddNodeRequest batchAddNodeRequest) throws CloudDriveException, InterruptedException;

    Future<BatchAddNodeResponse> batchAddNodeAsync(BatchAddNodeRequest batchAddNodeRequest, AsyncHandler<BatchAddNodeRequest, BatchAddNodeResponse> asyncHandler);

    BatchCreateInviteResponse batchCreateInvite(BatchCreateInviteRequest batchCreateInviteRequest) throws CloudDriveException, InterruptedException;

    Future<BatchCreateInviteResponse> batchCreateInviteAsync(BatchCreateInviteRequest batchCreateInviteRequest, AsyncHandler<BatchCreateInviteRequest, BatchCreateInviteResponse> asyncHandler);

    BatchRemoveNodeResponse batchRemoveNode(BatchRemoveNodeRequest batchRemoveNodeRequest) throws CloudDriveException, InterruptedException;

    Future<BatchRemoveNodeResponse> batchRemoveNodeAsync(BatchRemoveNodeRequest batchRemoveNodeRequest, AsyncHandler<BatchRemoveNodeRequest, BatchRemoveNodeResponse> asyncHandler);

    BulkAddRemoveChildResponse bulkAddRemoveChild(BulkAddRemoveChildRequest bulkAddRemoveChildRequest) throws CloudDriveException, InterruptedException;

    Future<BulkAddRemoveChildResponse> bulkAddRemoveChildAsync(BulkAddRemoveChildRequest bulkAddRemoveChildRequest, AsyncHandler<BulkAddRemoveChildRequest, BulkAddRemoveChildResponse> asyncHandler);

    void bulkAddToFamilyArchive(BulkAddToFamilyArchiveRequest bulkAddToFamilyArchiveRequest) throws InterruptedException, CloudDriveException;

    Future<Void> bulkAddToFamilyArchiveAsync(BulkAddToFamilyArchiveRequest bulkAddToFamilyArchiveRequest, AsyncHandler<BulkAddToFamilyArchiveRequest, Void> asyncHandler);

    BulkGetFaceClusterResponse bulkGetFaceCluster(BulkGetFaceClusterRequest bulkGetFaceClusterRequest) throws CloudDriveException, InterruptedException;

    Future<BulkGetFaceClusterResponse> bulkGetFaceClusterAsync(BulkGetFaceClusterRequest bulkGetFaceClusterRequest, AsyncHandler<BulkGetFaceClusterRequest, BulkGetFaceClusterResponse> asyncHandler);

    BulkGetReactionSummariesResponse bulkGetReactionSummaries(BulkGetReactionSummariesRequest bulkGetReactionSummariesRequest) throws CloudDriveException, InterruptedException;

    Future<BulkGetReactionSummariesResponse> bulkGetReactionSummariesAsync(BulkGetReactionSummariesRequest bulkGetReactionSummariesRequest, AsyncHandler<BulkGetReactionSummariesRequest, BulkGetReactionSummariesResponse> asyncHandler);

    void bulkRemoveFromFamilyArchive(BulkRemoveFromFamilyArchiveRequest bulkRemoveFromFamilyArchiveRequest) throws InterruptedException, CloudDriveException;

    Future<Void> bulkRemoveFromFamilyArchiveAsync(BulkRemoveFromFamilyArchiveRequest bulkRemoveFromFamilyArchiveRequest, AsyncHandler<BulkRemoveFromFamilyArchiveRequest, Void> asyncHandler);

    BulkTrashRestoreNodesResponse bulkTrashRestoreNodes(BulkTrashRestoreNodesRequest bulkTrashRestoreNodesRequest) throws CloudDriveException, InterruptedException;

    Future<BulkTrashRestoreNodesResponse> bulkTrashRestoreNodesAsync(BulkTrashRestoreNodesRequest bulkTrashRestoreNodesRequest, AsyncHandler<BulkTrashRestoreNodesRequest, BulkTrashRestoreNodesResponse> asyncHandler);

    CreateContactBlockResponse createContactBlock(CreateContactBlockRequest createContactBlockRequest) throws CloudDriveException, InterruptedException;

    Future<CreateContactBlockResponse> createContactBlockAsync(CreateContactBlockRequest createContactBlockRequest, AsyncHandler<CreateContactBlockRequest, CreateContactBlockResponse> asyncHandler);

    void createEvent(CreateEventRequest createEventRequest) throws CloudDriveException, InterruptedException;

    Future<Void> createEventAsync(CreateEventRequest createEventRequest, AsyncHandler<CreateEventRequest, Void> asyncHandler);

    void createFaceCluster(CreateFaceClusterRequest createFaceClusterRequest) throws CloudDriveException, InterruptedException;

    Future<CreateFaceClusterResponse> createFaceClusterAsync(CreateFaceClusterRequest createFaceClusterRequest, AsyncHandler<CreateFaceClusterRequest, CreateFaceClusterResponse> asyncHandler);

    CreateGroupResponse createGroup(CreateGroupRequest createGroupRequest) throws CloudDriveException, InterruptedException;

    Future<CreateGroupResponse> createGroupAsync(CreateGroupRequest createGroupRequest, AsyncHandler<CreateGroupRequest, CreateGroupResponse> asyncHandler);

    CreateMemberResponse createMember(CreateMemberRequest createMemberRequest) throws CloudDriveException, InterruptedException;

    Future<CreateMemberResponse> createMemberAsync(CreateMemberRequest createMemberRequest, AsyncHandler<CreateMemberRequest, CreateMemberResponse> asyncHandler);

    CreateMetadataDatabaseResponse createMetadataDatabase(CreateMetadataDatabaseRequest createMetadataDatabaseRequest) throws CloudDriveException, InterruptedException;

    CreateNodeExtendedResponse createNodeExtended(CreateNodeRequest createNodeRequest) throws CloudDriveException, InterruptedException;

    Future<CreateNodeExtendedResponse> createNodeExtendedAsync(CreateNodeRequest createNodeRequest, AsyncHandler<CreateNodeRequest, CreateNodeExtendedResponse> asyncHandler);

    void createPerson(CreatePersonRequest createPersonRequest) throws CloudDriveException, InterruptedException;

    Future<Void> createPersonAsync(CreatePersonRequest createPersonRequest, AsyncHandler<CreatePersonRequest, Void> asyncHandler);

    CreateReactionResponse createReaction(CreateReactionRequest createReactionRequest) throws CloudDriveException, InterruptedException;

    Future<CreateReactionResponse> createReactionAsync(CreateReactionRequest createReactionRequest, AsyncHandler<CreateReactionRequest, CreateReactionResponse> asyncHandler);

    void deleteGroup(DeleteGroupRequest deleteGroupRequest) throws CloudDriveException, InterruptedException;

    Future<Void> deleteGroupAsync(DeleteGroupRequest deleteGroupRequest, AsyncHandler<DeleteGroupRequest, Void> asyncHandler);

    void deleteMember(DeleteMemberRequest deleteMemberRequest) throws CloudDriveException, InterruptedException;

    Future<Void> deleteMemberAsync(DeleteMemberRequest deleteMemberRequest, AsyncHandler<DeleteMemberRequest, Void> asyncHandler);

    Void deleteReaction(DeleteReactionRequest deleteReactionRequest) throws CloudDriveException, InterruptedException;

    Future<Void> deleteReactionAsync(DeleteReactionRequest deleteReactionRequest, AsyncHandler<DeleteReactionRequest, Void> asyncHandler);

    void downloadFileExtended(DownloadFileExtendedRequest downloadFileExtendedRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<Void> downloadFileExtendedAsync(DownloadFileExtendedRequest downloadFileExtendedRequest, ProgressListener progressListener, AsyncHandler<DownloadFileExtendedRequest, Void> asyncHandler);

    GetAggregationsResponse getAggregations(GetAggregationsRequest getAggregationsRequest) throws CloudDriveException, InterruptedException;

    Future<GetAggregationsResponse> getAggregationsAsync(GetAggregationsRequest getAggregationsRequest, AsyncHandler<GetAggregationsRequest, GetAggregationsResponse> asyncHandler);

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    GetApplicationAccessRulesResponse getApplicationAccessRules(GetApplicationAccessRulesRequest getApplicationAccessRulesRequest) throws CloudDriveException, InterruptedException;

    @Override // com.amazon.clouddrive.AmazonCloudDrive
    Future<GetApplicationAccessRulesResponse> getApplicationAccessRulesAsync(GetApplicationAccessRulesRequest getApplicationAccessRulesRequest, AsyncHandler<GetApplicationAccessRulesRequest, GetApplicationAccessRulesResponse> asyncHandler) throws CloudDriveException, InterruptedException;

    GetChangesExtendedResponse getChangesExtended(GetChangesRequest getChangesRequest) throws CloudDriveException, InterruptedException;

    Future<GetChangesExtendedResponse> getChangesExtendedAsync(GetChangesRequest getChangesRequest, AsyncHandler<GetChangesRequest, GetChangesExtendedResponse> asyncHandler);

    GetFacesForNodeResponse getFacesForNode(GetFacesForNodeRequest getFacesForNodeRequest) throws CloudDriveException, InterruptedException;

    Future<GetFacesForNodeResponse> getFacesForNodeAsync(GetFacesForNodeRequest getFacesForNodeRequest, AsyncHandler<GetFacesForNodeRequest, GetFacesForNodeResponse> asyncHandler);

    GetFacesForPersonResponse getFacesForPerson(GetFacesForPersonRequest getFacesForPersonRequest) throws CloudDriveException, InterruptedException;

    Future<GetFacesForPersonResponse> getFacesForPersonAsync(GetFacesForPersonRequest getFacesForPersonRequest, AsyncHandler<GetFacesForPersonRequest, GetFacesForPersonResponse> asyncHandler);

    GetFamilyResponse getFamily(GetFamilyRequest getFamilyRequest) throws InterruptedException, CloudDriveException;

    Future<GetFamilyResponse> getFamilyAsync(GetFamilyRequest getFamilyRequest, AsyncHandler<GetFamilyRequest, GetFamilyResponse> asyncHandler);

    GetFamilyChangesResponse getFamilyChanges(GetFamilyChangesRequest getFamilyChangesRequest) throws InterruptedException, CloudDriveException;

    Future<GetFamilyChangesResponse> getFamilyChangesAsync(GetFamilyChangesRequest getFamilyChangesRequest, AsyncHandler<GetFamilyChangesRequest, GetFamilyChangesResponse> asyncHandler);

    GetGroupResponse getGroup(GetGroupRequest getGroupRequest) throws CloudDriveException, InterruptedException;

    Future<GetGroupResponse> getGroupAsync(GetGroupRequest getGroupRequest, AsyncHandler<GetGroupRequest, GetGroupResponse> asyncHandler);

    GetGroupCacheInfoResponse getGroupCacheInfo(GroupRequest groupRequest) throws CloudDriveException, InterruptedException;

    Future<GetGroupCacheInfoResponse> getGroupCacheInfoAsync(GroupRequest groupRequest, AsyncHandler<GroupRequest, GetGroupCacheInfoResponse> asyncHandler);

    GetGroupPrivacyPreferencesResponse getGroupPrivacyPreferences(GetGroupPrivacyPreferencesRequest getGroupPrivacyPreferencesRequest) throws CloudDriveException, InterruptedException;

    Future<GetGroupPrivacyPreferencesResponse> getGroupPrivacyPreferencesAsync(GetGroupPrivacyPreferencesRequest getGroupPrivacyPreferencesRequest, AsyncHandler<GetGroupPrivacyPreferencesRequest, GetGroupPrivacyPreferencesResponse> asyncHandler);

    GetGroupShareResponse getGroupShare(GetGroupShareRequest getGroupShareRequest) throws CloudDriveException, InterruptedException;

    Future<GetGroupShareResponse> getGroupShareAsync(GetGroupShareRequest getGroupShareRequest, AsyncHandler<GetGroupShareRequest, GetGroupShareResponse> asyncHandler);

    GetGroupShareBehaviorResponse getGroupShareBehavior(GetGroupShareBehaviorRequest getGroupShareBehaviorRequest) throws CloudDriveException, InterruptedException;

    Future<GetGroupShareBehaviorResponse> getGroupShareBehaviorAsync(GetGroupShareBehaviorRequest getGroupShareBehaviorRequest, AsyncHandler<GetGroupShareBehaviorRequest, GetGroupShareBehaviorResponse> asyncHandler);

    GetGroupShareInfoResponse getGroupShareInfo(GetGroupShareInfoRequest getGroupShareInfoRequest) throws CloudDriveException, InterruptedException;

    Future<GetGroupShareInfoResponse> getGroupShareInfoAsync(GetGroupShareInfoRequest getGroupShareInfoRequest, AsyncHandler<GetGroupShareInfoRequest, GetGroupShareInfoResponse> asyncHandler);

    GetJobStatusResponse getJobStatus(GetJobStatusRequest getJobStatusRequest) throws InterruptedException, CloudDriveException;

    Future<GetJobStatusResponse> getJobStatusAsync(GetJobStatusRequest getJobStatusRequest, AsyncHandler<GetJobStatusRequest, GetJobStatusResponse> asyncHandler);

    GetMemberPreferencesResponse getMemberPreferences(GetMemberPreferencesRequest getMemberPreferencesRequest) throws CloudDriveException, InterruptedException;

    Future<GetMemberPreferencesResponse> getMemberPreferencesAsync(GetMemberPreferencesRequest getMemberPreferencesRequest, AsyncHandler<GetMemberPreferencesRequest, GetMemberPreferencesResponse> asyncHandler);

    GetMetadataDatabaseStatusResponse getMetadataDatabaseStatus(GetMetadataDatabaseStatusRequest getMetadataDatabaseStatusRequest) throws CloudDriveException, InterruptedException;

    GetNodeExtendedResponse getNodeExtended(GetNodeExtendedRequest getNodeExtendedRequest) throws CloudDriveException, InterruptedException;

    Future<GetNodeExtendedResponse> getNodeExtendedAsync(GetNodeExtendedRequest getNodeExtendedRequest, AsyncHandler<GetNodeExtendedRequest, GetNodeExtendedResponse> asyncHandler);

    GetPreferencesResponse getPreferences(GetPreferencesRequest getPreferencesRequest) throws InterruptedException, CloudDriveException;

    Future<GetPreferencesResponse> getPreferencesAsync(GetPreferencesRequest getPreferencesRequest, AsyncHandler<GetPreferencesRequest, GetPreferencesResponse> asyncHandler);

    GetProfileResponse getProfile(GetProfileRequest getProfileRequest) throws CloudDriveException, InterruptedException;

    Future<GetProfileResponse> getProfileAsync(GetProfileRequest getProfileRequest, AsyncHandler<GetProfileRequest, GetProfileResponse> asyncHandler);

    GetReUpdateNodesResponse getReUpdateNodes(GetReUpdateNodesRequest getReUpdateNodesRequest) throws CloudDriveException, InterruptedException;

    Future<GetReUpdateNodesResponse> getReUpdateNodesAsync(GetReUpdateNodesRequest getReUpdateNodesRequest, AsyncHandler<GetReUpdateNodesRequest, GetReUpdateNodesResponse> asyncHandler) throws CloudDriveException, InterruptedException;

    GetReactionResponse getReaction(GetReactionRequest getReactionRequest) throws CloudDriveException, InterruptedException;

    Future<GetReactionResponse> getReactionAsync(GetReactionRequest getReactionRequest, AsyncHandler<GetReactionRequest, GetReactionResponse> asyncHandler);

    RefreshGroupShareResponse getRefreshGroupShare(RefreshGroupShareRequest refreshGroupShareRequest) throws CloudDriveException, InterruptedException;

    Future<RefreshGroupShareResponse> getRefreshGroupShareAsync(RefreshGroupShareRequest refreshGroupShareRequest, AsyncHandler<RefreshGroupShareRequest, RefreshGroupShareResponse> asyncHandler);

    GetSearchSuggestionsResponse getSearchSuggestions(GetSearchSuggestionsRequest getSearchSuggestionsRequest) throws CloudDriveException, InterruptedException;

    Future<GetSearchSuggestionsResponse> getSearchSuggestionsAsync(GetSearchSuggestionsRequest getSearchSuggestionsRequest, AsyncHandler<GetSearchSuggestionsRequest, GetSearchSuggestionsResponse> asyncHandler);

    GetServicePlansResponse getServicePlans(GetServicePlansRequest getServicePlansRequest) throws CloudDriveException, InterruptedException;

    Future<GetServicePlansResponse> getServicePlansAsync(GetServicePlansRequest getServicePlansRequest, AsyncHandler<GetServicePlansRequest, GetServicePlansResponse> asyncHandler) throws InvalidParameter;

    GetSplashResponse getSplash(GetSplashRequest getSplashRequest) throws CloudDriveException, InterruptedException;

    Future<GetSplashResponse> getSplashAsync(GetSplashRequest getSplashRequest, AsyncHandler<GetSplashRequest, GetSplashResponse> asyncHandler) throws InvalidParameter;

    GetSubscriptionsResponse getSubscriptions(GetSubscriptionsRequest getSubscriptionsRequest) throws CloudDriveException, InterruptedException;

    Future<GetSubscriptionsResponse> getSubscriptionsAsync(GetSubscriptionsRequest getSubscriptionsRequest, AsyncHandler<GetSubscriptionsRequest, GetSubscriptionsResponse> asyncHandler) throws InvalidParameter;

    void getThumbnailExtended(GetThumbnailExtendedRequest getThumbnailExtendedRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<Void> getThumbnailExtendedAsync(GetThumbnailExtendedRequest getThumbnailExtendedRequest, ProgressListener progressListener, AsyncHandler<GetThumbnailExtendedRequest, Void> asyncHandler);

    void importContacts(ImportContactsRequest importContactsRequest) throws CloudDriveException, InterruptedException;

    Future<Void> importContactsAsync(ImportContactsRequest importContactsRequest, AsyncHandler<ImportContactsRequest, Void> asyncHandler);

    ListChildrenExtendedResponse listChildrenExtended(ListChildrenExtendedRequest listChildrenExtendedRequest) throws CloudDriveException, InterruptedException;

    Future<ListChildrenExtendedResponse> listChildrenExtendedAsync(ListChildrenExtendedRequest listChildrenExtendedRequest, AsyncHandler<ListChildrenExtendedRequest, ListChildrenExtendedResponse> asyncHandler);

    ListCollectionNodesResponse listCollectionNodes(ListCollectionNodesRequest listCollectionNodesRequest) throws CloudDriveException, InterruptedException;

    Future<ListCollectionNodesResponse> listCollectionNodesAsync(ListCollectionNodesRequest listCollectionNodesRequest, AsyncHandler<ListCollectionNodesRequest, ListCollectionNodesResponse> asyncHandler);

    @Deprecated
    ListContactsResponse listContacts(ListContactsRequest listContactsRequest) throws CloudDriveException, InterruptedException;

    @Deprecated
    Future<ListContactsResponse> listContactsAsync(ListContactsRequest listContactsRequest, AsyncHandler<ListContactsRequest, ListContactsResponse> asyncHandler);

    ListGroupEventsResponse listGroupEvents(ListGroupEventsRequest listGroupEventsRequest) throws CloudDriveException, InterruptedException;

    Future<ListGroupEventsResponse> listGroupEventsAsync(ListGroupEventsRequest listGroupEventsRequest, AsyncHandler<ListGroupEventsRequest, ListGroupEventsResponse> asyncHandler);

    ListGroupsResponse listGroups(ListGroupsRequest listGroupsRequest) throws CloudDriveException, InterruptedException;

    Future<ListGroupsResponse> listGroupsAsync(ListGroupsRequest listGroupsRequest, AsyncHandler<ListGroupsRequest, ListGroupsResponse> asyncHandler);

    ListInvitesResponse listInvites(ListInvitesRequest listInvitesRequest) throws CloudDriveException, InterruptedException;

    Future<ListInvitesResponse> listInvitesAsync(ListInvitesRequest listInvitesRequest, AsyncHandler<ListInvitesRequest, ListInvitesResponse> asyncHandler);

    ListMembersResponse listMembers(ListMembersRequest listMembersRequest) throws CloudDriveException, InterruptedException;

    Future<ListMembersResponse> listMembersAsync(ListMembersRequest listMembersRequest, AsyncHandler<ListMembersRequest, ListMembersResponse> asyncHandler);

    ListNodesExtendedResponse listNodesExtended(ListNodesRequest listNodesRequest) throws CloudDriveException, InterruptedException;

    Future<ListNodesExtendedResponse> listNodesExtendedAsync(ListNodesRequest listNodesRequest, AsyncHandler<ListNodesRequest, ListNodesExtendedResponse> asyncHandler);

    ListNodesInTrashExtendedResponse listNodesInTrashExtended(ListNodesInTrashRequest listNodesInTrashRequest) throws CloudDriveException, InterruptedException;

    Future<ListNodesInTrashExtendedResponse> listNodesInTrashExtendedAsync(ListNodesInTrashRequest listNodesInTrashRequest, AsyncHandler<ListNodesInTrashRequest, ListNodesInTrashExtendedResponse> asyncHandler);

    ListNotificationTopicSubscriptionResponse listNotificationTopicSubscriptions(ListNotificationTopicSubscriptionsRequest listNotificationTopicSubscriptionsRequest) throws CloudDriveException, InterruptedException;

    Future<ListNotificationTopicSubscriptionResponse> listNotificationTopicSubscriptionsAsync(ListNotificationTopicSubscriptionsRequest listNotificationTopicSubscriptionsRequest, AsyncHandler<ListNotificationTopicSubscriptionsRequest, ListNotificationTopicSubscriptionResponse> asyncHandler);

    ListReactionsResponse listReactions(ListReactionsRequest listReactionsRequest) throws CloudDriveException, InterruptedException;

    Future<ListReactionsResponse> listReactionsAsync(ListReactionsRequest listReactionsRequest, AsyncHandler<ListReactionsRequest, ListReactionsResponse> asyncHandler);

    ListSourcesResponse listSources(ListSourcesRequest listSourcesRequest) throws CloudDriveException, InterruptedException;

    Future<ListSourcesResponse> listSourcesAsync(ListSourcesRequest listSourcesRequest, AsyncHandler<ListSourcesRequest, ListSourcesResponse> asyncHandler);

    MoveChildExtendedResponse moveChildExtended(MoveChildRequest moveChildRequest) throws CloudDriveException, InterruptedException;

    Future<MoveChildExtendedResponse> moveChildExtendedAsync(MoveChildRequest moveChildRequest, AsyncHandler<MoveChildRequest, MoveChildExtendedResponse> asyncHandler);

    MoveNodeToTrashExtendedResponse moveNodeToTrashExtended(MoveNodeToTrashExtendedRequest moveNodeToTrashExtendedRequest) throws CloudDriveException, InterruptedException;

    Future<MoveNodeToTrashExtendedResponse> moveNodeToTrashExtendedAsync(MoveNodeToTrashExtendedRequest moveNodeToTrashExtendedRequest, AsyncHandler<MoveNodeToTrashExtendedRequest, MoveNodeToTrashExtendedResponse> asyncHandler);

    OverwriteFileExtendedResponse overwriteFileExtended(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<OverwriteFileExtendedResponse> overwriteFileExtendedAsync(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener, AsyncHandler<OverwriteFileRequest, OverwriteFileExtendedResponse> asyncHandler);

    PatchGroupResponse patchGroup(PatchGroupRequest patchGroupRequest) throws CloudDriveException, InterruptedException;

    Future<PatchGroupResponse> patchGroupAsync(PatchGroupRequest patchGroupRequest, AsyncHandler<PatchGroupRequest, PatchGroupResponse> asyncHandler);

    PatchGroupCollectionResponse patchGroupCollection(PatchGroupCollectionRequest patchGroupCollectionRequest) throws CloudDriveException, InterruptedException;

    Future<PatchGroupCollectionResponse> patchGroupCollectionAsync(PatchGroupCollectionRequest patchGroupCollectionRequest, AsyncHandler<PatchGroupCollectionRequest, PatchGroupCollectionResponse> asyncHandler);

    PatchGroupPrivacyPreferencesResponse patchGroupPrivacyPreferences(PatchGroupPrivacyPreferencesRequest patchGroupPrivacyPreferencesRequest) throws CloudDriveException, InterruptedException;

    Future<PatchGroupPrivacyPreferencesResponse> patchGroupPrivacyPreferencesAsync(PatchGroupPrivacyPreferencesRequest patchGroupPrivacyPreferencesRequest, AsyncHandler<PatchGroupPrivacyPreferencesRequest, PatchGroupPrivacyPreferencesResponse> asyncHandler);

    PatchMemberPreferencesResponse patchMemberPreferences(PatchMemberPreferencesRequest patchMemberPreferencesRequest) throws CloudDriveException, InterruptedException;

    Future<PatchMemberPreferencesResponse> patchMemberPreferencesAsync(PatchMemberPreferencesRequest patchMemberPreferencesRequest, AsyncHandler<PatchMemberPreferencesRequest, PatchMemberPreferencesResponse> asyncHandler);

    void purgeNode(PurgeNodeRequest purgeNodeRequest) throws CloudDriveException, InterruptedException;

    Future<Void> purgeNodeAsync(PurgeNodeRequest purgeNodeRequest, AsyncHandler<PurgeNodeRequest, Void> asyncHandler);

    void removeChildFromParentExtended(RemoveChildFromParentExtendedRequest removeChildFromParentExtendedRequest) throws CloudDriveException, InterruptedException;

    Future<Void> removeChildFromParentExtendedAsync(RemoveChildFromParentExtendedRequest removeChildFromParentExtendedRequest, AsyncHandler<RemoveChildFromParentExtendedRequest, Void> asyncHandler);

    void removeFacesFromPerson(RemoveFacesFromPersonRequest removeFacesFromPersonRequest) throws CloudDriveException, InterruptedException;

    Future<Void> removeFacesFromPersonAsync(RemoveFacesFromPersonRequest removeFacesFromPersonRequest, AsyncHandler<RemoveFacesFromPersonRequest, Void> asyncHandler);

    RenameFaceClusterResponse renameFaceCluster(RenameFaceClusterRequest renameFaceClusterRequest) throws CloudDriveException, InterruptedException;

    Future<RenameFaceClusterResponse> renameFaceClusterAsync(RenameFaceClusterRequest renameFaceClusterRequest, AsyncHandler<RenameFaceClusterRequest, RenameFaceClusterResponse> asyncHandler);

    RestoreNodeFromTrashExtendedResponse restoreNodeFromTrashExtended(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest) throws CloudDriveException, InterruptedException;

    Future<RestoreNodeFromTrashExtendedResponse> restoreNodeFromTrashExtendedAsync(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest, AsyncHandler<RestoreNodeFromTrashRequest, RestoreNodeFromTrashExtendedResponse> asyncHandler);

    ListContactsResponse searchContacts(SearchContactsRequest searchContactsRequest) throws CloudDriveException, InterruptedException;

    Future<ListContactsResponse> searchContactsAsync(SearchContactsRequest searchContactsRequest, AsyncHandler<SearchContactsRequest, ListContactsResponse> asyncHandler);

    SearchGroupNodesResponse searchGroupNodes(SearchGroupNodesRequest searchGroupNodesRequest) throws CloudDriveException, InterruptedException;

    Future<SearchGroupNodesResponse> searchGroupNodesAsync(SearchGroupNodesRequest searchGroupNodesRequest, AsyncHandler<SearchGroupNodesRequest, SearchGroupNodesResponse> asyncHandler);

    SearchGroupNodesExtendedResponse searchGroupNodesExtended(SearchGroupNodesRequest searchGroupNodesRequest) throws CloudDriveException, InterruptedException;

    Future<SearchGroupNodesExtendedResponse> searchGroupNodesExtendedAsync(SearchGroupNodesRequest searchGroupNodesRequest, AsyncHandler<SearchGroupNodesRequest, SearchGroupNodesExtendedResponse> asyncHandler);

    SearchKeyResponse searchKey(SearchKeyRequest searchKeyRequest) throws CloudDriveException, InterruptedException;

    Future<SearchKeyResponse> searchKeyAsync(SearchKeyRequest searchKeyRequest, AsyncHandler<SearchKeyRequest, SearchKeyResponse> asyncHandler);

    SetNodeResponse setNode(SetNodeRequest setNodeRequest) throws CloudDriveException, InterruptedException;

    Future<SetNodeResponse> setNodeAsync(SetNodeRequest setNodeRequest, AsyncHandler<SetNodeRequest, SetNodeResponse> asyncHandler);

    void setPreference(SetPreferenceRequest setPreferenceRequest) throws InterruptedException, CloudDriveException;

    Future<Void> setPreferenceAsync(SetPreferenceRequest setPreferenceRequest, AsyncHandler<SetPreferenceRequest, Void> asyncHandler);

    void setupAccount(SetupAccountRequest setupAccountRequest) throws CloudDriveException, InterruptedException;

    Future<Void> setupAccountAsync(SetupAccountRequest setupAccountRequest, AsyncHandler<SetupAccountRequest, Void> asyncHandler) throws InvalidParameter;

    SourceInfoExtendedResponse setupSourceExtended(SetupSourceExtendedRequest setupSourceExtendedRequest) throws CloudDriveException, InterruptedException;

    Future<SourceInfoExtendedResponse> setupSourceExtendedAsync(SetupSourceExtendedRequest setupSourceExtendedRequest, AsyncHandler<SetupSourceExtendedRequest, SourceInfoExtendedResponse> asyncHandler);

    SubscribeNotificationTopicResponse subscribeNotificationTopic(SubscribeNotificationTopicRequest subscribeNotificationTopicRequest) throws CloudDriveException, InterruptedException;

    Future<SubscribeNotificationTopicResponse> subscribeNotificationTopicAsync(SubscribeNotificationTopicRequest subscribeNotificationTopicRequest, AsyncHandler<SubscribeNotificationTopicRequest, SubscribeNotificationTopicResponse> asyncHandler);

    SubscribePlanResponse subscribePlan(SubscribePlanRequest subscribePlanRequest) throws CloudDriveException, InterruptedException;

    Future<SubscribePlanResponse> subscribePlanAsync(SubscribePlanRequest subscribePlanRequest, AsyncHandler<SubscribePlanRequest, SubscribePlanResponse> asyncHandler) throws InvalidParameter;

    UnsubscribeNotificationTopicResponse unsubscribeNotificationTopic(UnsubscribeNotificationTopicRequest unsubscribeNotificationTopicRequest) throws CloudDriveException, InterruptedException;

    Future<UnsubscribeNotificationTopicResponse> unsubscribeNotificationTopicAsync(UnsubscribeNotificationTopicRequest unsubscribeNotificationTopicRequest, AsyncHandler<UnsubscribeNotificationTopicRequest, UnsubscribeNotificationTopicResponse> asyncHandler);

    UpdateFamilyArchiveResponse updateFamilyArchive(UpdateFamilyArchiveRequest updateFamilyArchiveRequest) throws InterruptedException, CloudDriveException;

    Future<UpdateFamilyArchiveResponse> updateFamilyArchiveAsync(UpdateFamilyArchiveRequest updateFamilyArchiveRequest, AsyncHandler<UpdateFamilyArchiveRequest, UpdateFamilyArchiveResponse> asyncHandler);

    UpdateGroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws CloudDriveException, InterruptedException;

    Future<UpdateGroupResponse> updateGroupAsync(UpdateGroupRequest updateGroupRequest, AsyncHandler<UpdateGroupRequest, UpdateGroupResponse> asyncHandler);

    UpdateNodeExtendedResponse updateNodeExtended(UpdateNodeExtendedRequest updateNodeExtendedRequest) throws CloudDriveException, InterruptedException;

    Future<UpdateNodeExtendedResponse> updateNodeExtendedAsync(UpdateNodeExtendedRequest updateNodeExtendedRequest, AsyncHandler<UpdateNodeExtendedRequest, UpdateNodeExtendedResponse> asyncHandler);

    UploadFileExtendedResponse uploadFileExtended(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<UploadFileExtendedResponse> uploadFileExtendedAsync(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener, AsyncHandler<UploadFileRequest, UploadFileExtendedResponse> asyncHandler);

    UploadFileExtendedResponse uploadFileExtendedV2(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener) throws CloudDriveException, InterruptedException;

    Future<UploadFileExtendedResponse> uploadFileExtendedV2Async(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener, AsyncHandler<UploadFileRequest, UploadFileExtendedResponse> asyncHandler);

    ViewGroupResponse viewGroup(ViewGroupRequest viewGroupRequest) throws CloudDriveException, InterruptedException;

    Future<ViewGroupResponse> viewGroupAsync(ViewGroupRequest viewGroupRequest, AsyncHandler<ViewGroupRequest, ViewGroupResponse> asyncHandler);
}
