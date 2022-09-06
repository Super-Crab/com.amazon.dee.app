package com.amazon.clouddrive.internal;

import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureName;
import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.extended.configuration.AccountConfigurationExtended;
import com.amazon.clouddrive.extended.configuration.PromptoFeatureFlag;
import com.amazon.clouddrive.extended.exception.InvalidFileLengthException;
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
import com.amazon.clouddrive.extended.model.CreatePersonRequest;
import com.amazon.clouddrive.extended.model.CreateReactionRequest;
import com.amazon.clouddrive.extended.model.CreateReactionResponse;
import com.amazon.clouddrive.extended.model.CropBox;
import com.amazon.clouddrive.extended.model.DeleteGroupRequest;
import com.amazon.clouddrive.extended.model.DeleteMemberRequest;
import com.amazon.clouddrive.extended.model.DeleteReactionRequest;
import com.amazon.clouddrive.extended.model.DownloadFileExtendedRequest;
import com.amazon.clouddrive.extended.model.GetAggregationsRequest;
import com.amazon.clouddrive.extended.model.GetAggregationsResponse;
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
import com.amazon.clouddrive.extended.model.ListNotificationTopicSubscriptionResponse;
import com.amazon.clouddrive.extended.model.ListNotificationTopicSubscriptionsRequest;
import com.amazon.clouddrive.extended.model.ListReactionsRequest;
import com.amazon.clouddrive.extended.model.ListReactionsResponse;
import com.amazon.clouddrive.extended.model.ListSourcesRequest;
import com.amazon.clouddrive.extended.model.ListSourcesResponse;
import com.amazon.clouddrive.extended.model.MoveNodeToTrashExtendedRequest;
import com.amazon.clouddrive.extended.model.MoveNodeToTrashExtendedResponse;
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
import com.amazon.clouddrive.extended.model.UploadFileExtendedRequest;
import com.amazon.clouddrive.extended.model.ViewGroupRequest;
import com.amazon.clouddrive.extended.model.ViewGroupResponse;
import com.amazon.clouddrive.extended.model.deserializer.BatchAddNodeResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.BatchCreateInviteResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.BatchRemoveNodeResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.BulkAddRemoveChildResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.BulkGetFaceClusterResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.BulkGetReactionSummariesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.BulkTrashRestoreNodesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.CreateContactBlockResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.CreateFaceClusterResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.CreateGroupResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.CreateMemberResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.CreateMetadataDatabaseResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.CreateReactionResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetAggregationsResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetFacesForNodeResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetFacesForPersonResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetFamilyChangesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetFamilyResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetGroupCacheInfoResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetGroupPrivacyPreferencesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetGroupResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetGroupShareBehaviorResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetGroupShareInfoResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetGroupShareResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetJobStatusResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetMemberPreferencesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetMetadataDatabaseStatusResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetPreferencesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetProfileResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetReUpdateNodesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetReactionResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetSearchSuggestionsResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetServicePlansResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetSplashResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.GetSubscriptionsResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListCollectionNodesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListContactsResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListGroupEventsResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListGroupsResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListInvitesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListMembersResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListNotificationTopicSubscriptionsResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListReactionsResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ListSourcesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.MoveNodeToTrashExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.PatchGroupCollectionResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.PatchGroupPrivacyPreferencesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.PatchGroupResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.PatchMemberPreferencesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.RefreshGroupShareResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.RenameFaceClusterResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.SearchGroupNodesExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.SearchGroupNodesResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.SearchKeyResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.SetNodeResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.SourceInfoExtendedResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.SubscribeNotificationTopicResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.SubscribePlanResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.UnsubscribeNotificationTopicResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.UpdateFamilyArchiveResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.UpdateGroupResponseDeserializer;
import com.amazon.clouddrive.extended.model.deserializer.ViewGroupResponseDeserializer;
import com.amazon.clouddrive.extended.model.serializer.AddChildExtendedRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.AddFacesToPersonRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.BatchCreateInviteRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.BatchNodeRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.BulkAddRemoveChildRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.BulkGetFaceClusterRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.BulkGetReactionSummariesRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.BulkTrashRestoreNodesRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.CreateContactBlockRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.CreateEventRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.CreateFaceClusterRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.CreateGroupRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.CreateMemberRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.CreatePersonRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.CreateReactionRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.GetFacesForPersonRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.GetFamilyChangesRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.PatchGroupCollectionRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.PatchGroupPrivacyPreferencesRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.PatchGroupRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.PatchMemberPreferencesRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.PurgeNodeRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.RemoveChildExtendedRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.RemoveFacesFromPersonRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.RenameFaceClusterRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.SearchContactsRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.SetNodeRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.SetPreferenceRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.SetupAccountRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.SetupSourceExtendedRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.SubscribePlanRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.UpdateFamilyArchiveRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.UpdateGroupRequestSerializer;
import com.amazon.clouddrive.extended.model.serializer.UpdateNodeExtendedRequestSerializer;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.internal.RequestPathGenerator;
import com.amazon.clouddrive.metrics.MetricListener;
import com.amazon.clouddrive.model.INode;
import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.serializer.MoveNodeToTrashRequestSerializer;
import com.amazon.clouddrive.utils.AssertUtils;
import com.amazon.clouddrive.utils.Optional;
import com.amazon.clouddrive.utils.TransformUtils;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.util.UriUtil;
import java.util.List;
/* loaded from: classes11.dex */
public class ExtendedOperationFactory extends OperationFactory {
    private static final String PROMPTO_FEATURE_FLAGS_PARAM = "featureFlags";
    private final AccountConfiguration mAccountConfiguration;
    private final ClientConfiguration mClientConfiguration;
    private final MetricListener mMetricListener;
    private final ExtendedRequestPathGenerator mRequestPathGenerator;
    private final SourceInfoGenerator mSourceInfoGenerator;

    /* renamed from: com.amazon.clouddrive.internal.ExtendedOperationFactory$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$clouddrive$extended$model$SetPreferenceRequest$PreferenceType = new int[SetPreferenceRequest.PreferenceType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$clouddrive$extended$model$SetPreferenceRequest$PreferenceType[SetPreferenceRequest.PreferenceType.FAMILY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$clouddrive$extended$model$SetPreferenceRequest$PreferenceType[SetPreferenceRequest.PreferenceType.PERSONAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ExtendedOperationFactory(AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration) {
        super(accountConfiguration, clientConfiguration);
        this.mAccountConfiguration = accountConfiguration;
        this.mClientConfiguration = clientConfiguration;
        this.mMetricListener = clientConfiguration.getMetricListener();
        this.mRequestPathGenerator = new ExtendedRequestPathGenerator(this.mClientConfiguration, this.mAccountConfiguration, this);
        this.mSourceInfoGenerator = createSourceInfoGenerator(this.mAccountConfiguration);
    }

    @Override // com.amazon.clouddrive.internal.OperationFactory
    protected SourceInfoGenerator createSourceInfoGenerator(AccountConfiguration accountConfiguration) {
        if (accountConfiguration instanceof AccountConfigurationExtended) {
            return new SourceInfoExtendedGenerator((AccountConfigurationExtended) accountConfiguration, this);
        }
        return new SourceInfoGenerator(accountConfiguration, this);
    }

    public CloudDriveOperation<GetMetadataDatabaseStatusResponse> getMetadataDatabaseStatusOperation(GetMetadataDatabaseStatusRequest getMetadataDatabaseStatusRequest) {
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", this.mRequestPathGenerator.createMetaDataEndpointRequestPath(getMetadataDatabaseStatusRequest.getStatusURI()), GetMetadataDatabaseStatusResponseDeserializer.INSTANCE, "getMetadataDatabaseStatusRequest", this.mMetricListener, GetMetadataDatabaseStatusRequest.class);
    }

    public CloudDriveOperation<Void> newAddChildToParentExtendedOperation(AddChildToParentExtendedRequest addChildToParentExtendedRequest) {
        RequestAssertUtils.assertNotNull(addChildToParentExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(addChildToParentExtendedRequest.getParentId(), "The parent id must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(addChildToParentExtendedRequest.getChildId(), "The child id must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(addChildToParentExtendedRequest.getParentId());
        outline107.append("/children/");
        outline107.append(addChildToParentExtendedRequest.getChildId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString(), "V2");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(addChildToParentExtendedRequest, AddChildExtendedRequestSerializer.INSTANCE), null, createMetaDataEndpointRequestPath, SmartDeviceDataProvider.METHOD_HTTP_PUT, "addChildToParent", this.mMetricListener, AddChildToParentExtendedRequest.class);
    }

    public CloudDriveOperation<Void> newAddFacesToPersonOperation(AddFacesToPersonRequest addFacesToPersonRequest) {
        RequestAssertUtils.assertNotNull(addFacesToPersonRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(addFacesToPersonRequest.getTargetPersonId(), "The target person id must be provided.");
        AssertUtils.assertNotNullOrEmpty(addFacesToPersonRequest.getFaceList(), "The face list must be provided and not empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("people/");
        outline107.append(addFacesToPersonRequest.getTargetPersonId());
        outline107.append("/faces");
        RequestPathGenerator.RequestPath createFacesEndpointRequestPath = extendedRequestPathGenerator.createFacesEndpointRequestPath(outline107.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(addFacesToPersonRequest, AddFacesToPersonRequestSerializer.INSTANCE), null, createFacesEndpointRequestPath, "POST", "addFacesToPerson", this.mMetricListener, AddFacesToPersonRequest.class);
    }

    public CloudDriveOperation<BatchAddNodeResponse> newBatchAddNodeOperation(BatchAddNodeRequest batchAddNodeRequest) {
        RequestAssertUtils.assertNotNull(batchAddNodeRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(batchAddNodeRequest.getGroupIds(), "The groupIds cannot be null or empty.");
        AssertUtils.assertNotNullOrEmpty(batchAddNodeRequest.getNodeIds(), "The nodeIds cannot be null or empty");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = this.mRequestPathGenerator.createGroupsEndpointRequestPath("nodes");
        createGroupsEndpointRequestPath.addParameter(ContactsModuleConstants.OPERATION_TYPE_KEY, batchAddNodeRequest.getOperationType().name());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(batchAddNodeRequest, BatchNodeRequestSerializer.INSTANCE), BatchAddNodeResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "POST", "batchAddNodeRequest", this.mMetricListener, BatchAddNodeRequest.class);
    }

    public CloudDriveOperation<BatchCreateInviteResponse> newBatchCreateInviteOperation(BatchCreateInviteRequest batchCreateInviteRequest) {
        RequestAssertUtils.assertNotNull(batchCreateInviteRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNull(batchCreateInviteRequest.getGroupId(), "The groupId cannot be null.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(batchCreateInviteRequest.getGroupId());
        outline107.append("/invites");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(batchCreateInviteRequest, BatchCreateInviteRequestSerializer.INSTANCE), BatchCreateInviteResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "POST", "batchCreateInviteRequest", this.mMetricListener, BatchCreateInviteRequest.class);
    }

    public CloudDriveOperation<BatchRemoveNodeResponse> newBatchRemoveNodeOperation(BatchRemoveNodeRequest batchRemoveNodeRequest) {
        RequestAssertUtils.assertNotNull(batchRemoveNodeRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(batchRemoveNodeRequest.getGroupIds(), "The groupIds cannot be null or empty.");
        AssertUtils.assertNotNullOrEmpty(batchRemoveNodeRequest.getNodeIds(), "The nodeIds cannot be null or empty");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = this.mRequestPathGenerator.createGroupsEndpointRequestPath("nodes");
        createGroupsEndpointRequestPath.addParameter(ContactsModuleConstants.OPERATION_TYPE_KEY, batchRemoveNodeRequest.getOperationType().name());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(batchRemoveNodeRequest, BatchNodeRequestSerializer.INSTANCE), BatchRemoveNodeResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "POST", "batchRemoveNodeRequest", this.mMetricListener, BatchRemoveNodeRequest.class);
    }

    public CloudDriveOperation<BulkAddRemoveChildResponse> newBulkAddRemoveChildOperation(BulkAddRemoveChildRequest bulkAddRemoveChildRequest) {
        AssertUtils.assertNotNull(bulkAddRemoveChildRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(bulkAddRemoveChildRequest.getParentId(), "The parent id must be provided.");
        AssertUtils.assertNotNullOrEmpty(bulkAddRemoveChildRequest.getOp(), "The operation type must be provided.");
        AssertUtils.assertNotNullOrEmpty(bulkAddRemoveChildRequest.getNodeIds(), "The list of children must be provided");
        bulkAddRemoveChildRequest.setResourceVersion("V2");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("nodes/" + bulkAddRemoveChildRequest.getParentId() + "/children");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(bulkAddRemoveChildRequest, BulkAddRemoveChildRequestSerializer.INSTANCE), BulkAddRemoveChildResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, "PATCH", "bulkAddRemoveChild", this.mMetricListener, BulkAddRemoveChildRequest.class);
    }

    public CloudDriveOperation<Void> newBulkAddToFamilyArchiveOperation(BulkAddToFamilyArchiveRequest bulkAddToFamilyArchiveRequest) {
        RequestAssertUtils.assertNotNull(bulkAddToFamilyArchiveRequest, "The request cannot be null");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("bulk/nodes/familyArchive");
        createMetaDataEndpointRequestPath.addParameter("resourceVersion", "V2");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(bulkAddToFamilyArchiveRequest, null), null, createMetaDataEndpointRequestPath, "POST", "bulkAddToFamilyArchive", this.mMetricListener, BulkAddToFamilyArchiveRequest.class);
    }

    public CloudDriveOperation<BulkGetFaceClusterResponse> newBulkGetFaceClusterOperation(BulkGetFaceClusterRequest bulkGetFaceClusterRequest) {
        RequestAssertUtils.assertNotNull(bulkGetFaceClusterRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(bulkGetFaceClusterRequest.getClusterIdList(), "The clusterID must be provided.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("cluster/faces");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(bulkGetFaceClusterRequest, BulkGetFaceClusterRequestSerializer.INSTANCE), BulkGetFaceClusterResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, "POST", "bulkGetFaceClusterRequest", this.mMetricListener, BulkGetFaceClusterRequest.class);
    }

    public CloudDriveOperation<BulkGetReactionSummariesResponse> newBulkGetReactionSummariesOperation(BulkGetReactionSummariesRequest bulkGetReactionSummariesRequest) {
        AssertUtils.assertNotNull(bulkGetReactionSummariesRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(bulkGetReactionSummariesRequest.getGroupId(), "The group ID cannot be null or empty.");
        AssertUtils.assertNotNullOrEmpty(bulkGetReactionSummariesRequest.getTopics(), "The topics cannot be null or empty.");
        ClientConfiguration clientConfiguration = this.mClientConfiguration;
        AccountConfiguration accountConfiguration = this.mAccountConfiguration;
        SourceInfoGenerator sourceInfoGenerator = this.mSourceInfoGenerator;
        JsonPostRequestWriter jsonPostRequestWriter = new JsonPostRequestWriter(bulkGetReactionSummariesRequest, BulkGetReactionSummariesRequestSerializer.INSTANCE);
        JsonDeserializer<BulkGetReactionSummariesResponse> jsonDeserializer = BulkGetReactionSummariesResponseDeserializer.INSTANCE;
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(bulkGetReactionSummariesRequest.getGroupId());
        outline107.append("/reaction-summaries");
        return new CloudDriveBodyOperation(this, clientConfiguration, accountConfiguration, sourceInfoGenerator, jsonPostRequestWriter, jsonDeserializer, extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString()), "POST", "bulkGetReactionSummariesRequest", this.mMetricListener, BulkGetReactionSummariesRequest.class);
    }

    public CloudDriveOperation<Void> newBulkRemoveFromFamilyArchiveOperation(BulkRemoveFromFamilyArchiveRequest bulkRemoveFromFamilyArchiveRequest) {
        RequestAssertUtils.assertNotNull(bulkRemoveFromFamilyArchiveRequest, "The request cannot be null");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("bulk/nodes/familyArchive");
        createMetaDataEndpointRequestPath.addParameter("resourceVersion", "V2");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(bulkRemoveFromFamilyArchiveRequest, null), null, createMetaDataEndpointRequestPath, Constants.REQUEST_METHOD_DELETE, "bulkRemoveFromFamilyArchive", this.mMetricListener, BulkRemoveFromFamilyArchiveRequest.class);
    }

    public CloudDriveOperation<BulkTrashRestoreNodesResponse> newBulkTrashOrRestoreNodes(BulkTrashRestoreNodesRequest bulkTrashRestoreNodesRequest) {
        AssertUtils.assertNotNull(bulkTrashRestoreNodesRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(bulkTrashRestoreNodesRequest.getNodeIds(), "The list of nodes must be provided");
        AssertUtils.assertNotNullOrEmpty(bulkTrashRestoreNodesRequest.getOp(), "The operation type must be provided.");
        bulkTrashRestoreNodesRequest.setResourceVersion("V2");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("trash");
        createMetaDataEndpointRequestPath.addParameter("recurse", bulkTrashRestoreNodesRequest.getRecurse());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(bulkTrashRestoreNodesRequest, BulkTrashRestoreNodesRequestSerializer.INSTANCE), BulkTrashRestoreNodesResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, "PATCH", "bulkTrashRestoreRequest", this.mMetricListener, BulkTrashRestoreNodesRequest.class);
    }

    public CloudDriveOperation<CreateContactBlockResponse> newCreateContactBlockOperation(CreateContactBlockRequest createContactBlockRequest) {
        RequestAssertUtils.assertNotNull(createContactBlockRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(createContactBlockRequest.getGroupId(), "The groupId cannot be null or empty.");
        RequestAssertUtils.assertNotNullOrEmpty(createContactBlockRequest.getBlockedCustomerId(), "The blockedCustomerId cannot be null or empty.");
        RequestAssertUtils.assertNotNullOrEmpty(createContactBlockRequest.getBlockType(), "The blockType cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("customers/me/contactBlocks/");
        outline107.append(createContactBlockRequest.getBlockedCustomerId());
        outline107.append("/");
        outline107.append(createContactBlockRequest.getBlockType());
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        createGroupsEndpointRequestPath.addParameter("groupId", createContactBlockRequest.getGroupId());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(createContactBlockRequest, CreateContactBlockRequestSerializer.INSTANCE), CreateContactBlockResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "POST", "createContactBlock", this.mMetricListener, CreateContactBlockRequest.class);
    }

    public CloudDriveOperation<Void> newCreateEventOperation(CreateEventRequest createEventRequest) {
        AssertUtils.assertNotNull(createEventRequest, "The request cannot be null.");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(createEventRequest, CreateEventRequestSerializer.INSTANCE), null, this.mRequestPathGenerator.createMetaDataEndpointRequestPath("event"), "POST", "createEvent", this.mMetricListener, CreateEventRequest.class);
    }

    public CloudDriveOperation<CreateFaceClusterResponse> newCreateFaceClusterOperation(CreateFaceClusterRequest createFaceClusterRequest) {
        RequestAssertUtils.assertNotNull(createFaceClusterRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(createFaceClusterRequest.getClusterIdList(), "The clusterID list must be provided.");
        AssertUtils.assertNotNullOrEmpty(createFaceClusterRequest.getNewName(), "The new cluster name must be provided.");
        AssertUtils.assertNotNullOrEmpty(createFaceClusterRequest.getContext(), "The context must be provided.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("cluster");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(createFaceClusterRequest, CreateFaceClusterRequestSerializer.INSTANCE), CreateFaceClusterResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, "POST", "createFaceClusterRequest", this.mMetricListener, CreateFaceClusterRequest.class);
    }

    public CloudDriveOperation<CreateGroupResponse> newCreateGroupOperation(CreateGroupRequest createGroupRequest) {
        RequestAssertUtils.assertNotNull(createGroupRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = this.mRequestPathGenerator.createGroupsEndpointRequestPath(FeatureName.GROUPS);
        createGroupsEndpointRequestPath.addParameter(PROMPTO_FEATURE_FLAGS_PARAM, PromptoFeatureFlag.DEPRECATE_GROUP_OWNEDBY.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(createGroupRequest, CreateGroupRequestSerializer.INSTANCE), CreateGroupResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "POST", "createGroupRequest", this.mMetricListener, CreateGroupRequest.class);
    }

    public CloudDriveOperation<CreateMemberResponse> newCreateMember(CreateMemberRequest createMemberRequest) {
        RequestAssertUtils.assertNotNull(createMemberRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(createMemberRequest.getGroupId(), "The groupId cannot be null or empty.");
        RequestAssertUtils.assertNotNullOrEmpty(createMemberRequest.getShareToken(), "The shareToken cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(createMemberRequest.getGroupId());
        outline107.append("/members");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(createMemberRequest, CreateMemberRequestSerializer.INSTANCE), CreateMemberResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "POST", "createMember", this.mMetricListener, CreateMemberRequest.class);
    }

    public CloudDriveOperation<CreateMetadataDatabaseResponse> newCreateMetadataDatabaseOperation(CreateMetadataDatabaseRequest createMetadataDatabaseRequest) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("db/");
        outline107.append(createMetadataDatabaseRequest.getSchemaName());
        outline107.append("/");
        outline107.append(createMetadataDatabaseRequest.getSchemaVersion());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString(), "V2");
        createMetaDataEndpointRequestPath.addParameter("forceCreate", Boolean.toString(createMetadataDatabaseRequest.isForceCreate()));
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "POST", createMetaDataEndpointRequestPath, new CreateMetadataDatabaseResponseDeserializer(), "createMetadataDatabaseRequest", this.mMetricListener, CreateMetadataDatabaseRequest.class);
    }

    public CloudDriveOperation<Void> newCreatePersonOperation(CreatePersonRequest createPersonRequest) {
        RequestAssertUtils.assertNotNull(createPersonRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(createPersonRequest.getNewPersonName(), "The new person name must be provided.");
        AssertUtils.assertNotNullOrEmpty(createPersonRequest.getFaceList(), "The face list must be provided and not empty.");
        RequestPathGenerator.RequestPath createFacesEndpointRequestPath = this.mRequestPathGenerator.createFacesEndpointRequestPath("people/faces");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(createPersonRequest, CreatePersonRequestSerializer.INSTANCE), null, createFacesEndpointRequestPath, SmartDeviceDataProvider.METHOD_HTTP_PUT, "createPerson", this.mMetricListener, CreatePersonRequest.class);
    }

    public CloudDriveOperation<CreateReactionResponse> newCreateReactionOperation(CreateReactionRequest createReactionRequest) {
        AssertUtils.assertNotNull(createReactionRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(createReactionRequest.getGroupId(), "The group ID cannot be null or empty.");
        ClientConfiguration clientConfiguration = this.mClientConfiguration;
        AccountConfiguration accountConfiguration = this.mAccountConfiguration;
        SourceInfoGenerator sourceInfoGenerator = this.mSourceInfoGenerator;
        JsonPostRequestWriter jsonPostRequestWriter = new JsonPostRequestWriter(createReactionRequest, CreateReactionRequestSerializer.INSTANCE);
        JsonDeserializer<CreateReactionResponse> jsonDeserializer = CreateReactionResponseDeserializer.INSTANCE;
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(createReactionRequest.getGroupId());
        outline107.append("/reactions");
        return new CloudDriveBodyOperation(this, clientConfiguration, accountConfiguration, sourceInfoGenerator, jsonPostRequestWriter, jsonDeserializer, extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString()), "POST", "createReactionRequest", this.mMetricListener, CreateReactionRequest.class);
    }

    public CloudDriveOperation<Void> newDeleteGroupOperation(DeleteGroupRequest deleteGroupRequest) {
        RequestAssertUtils.assertNotNull(deleteGroupRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(deleteGroupRequest.getGroupId(), "The groupId cannot be null.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(deleteGroupRequest.getGroupId());
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(deleteGroupRequest, null), null, createGroupsEndpointRequestPath, Constants.REQUEST_METHOD_DELETE, "deleteGroupRequest", this.mMetricListener, DeleteGroupRequest.class);
    }

    public CloudDriveOperation<Void> newDeleteMemberOperation(DeleteMemberRequest deleteMemberRequest) {
        RequestAssertUtils.assertNotNull(deleteMemberRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(deleteMemberRequest.getGroupId(), "The groupId cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(deleteMemberRequest.getMemberId(), "The memberId cannot be null.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(deleteMemberRequest.getGroupId());
        outline107.append("/members/");
        outline107.append(deleteMemberRequest.getMemberId());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, Constants.REQUEST_METHOD_DELETE, extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString()), null, "deleteMemberRequest", this.mMetricListener, DeleteMemberRequest.class);
    }

    public CloudDriveOperation<Void> newDeleteReactionOperation(DeleteReactionRequest deleteReactionRequest) {
        RequestPathGenerator.RequestPath requestPath;
        AssertUtils.assertNotNull(deleteReactionRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(deleteReactionRequest.getGroupId(), "The group ID cannot be null or empty.");
        if (deleteReactionRequest.getReactionId() != null && !deleteReactionRequest.getReactionId().isEmpty()) {
            ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
            outline107.append(deleteReactionRequest.getGroupId());
            outline107.append("/reactions/");
            outline107.append(deleteReactionRequest.getReactionId());
            requestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        } else if (deleteReactionRequest.getTopic() != null && !deleteReactionRequest.getTopic().isEmpty() && deleteReactionRequest.getKind() != null && !deleteReactionRequest.getKind().isEmpty()) {
            ExtendedRequestPathGenerator extendedRequestPathGenerator2 = this.mRequestPathGenerator;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("groups/");
            outline1072.append(deleteReactionRequest.getGroupId());
            outline1072.append("/reactions");
            RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator2.createGroupsEndpointRequestPath(outline1072.toString());
            createGroupsEndpointRequestPath.addParameter(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, deleteReactionRequest.getKind());
            createGroupsEndpointRequestPath.addParameter("topic", deleteReactionRequest.getTopic());
            requestPath = createGroupsEndpointRequestPath;
        } else {
            throw new RuntimeException("The reactionId and topic cannot both be null or empty");
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, Constants.REQUEST_METHOD_DELETE, requestPath, null, "deleteReactionRequest", this.mMetricListener, DeleteReactionRequest.class);
    }

    public CloudDriveOperation<Void> newDownloadFileExtendedOperation(DownloadFileExtendedRequest downloadFileExtendedRequest, ProgressListener progressListener) {
        RequestAssertUtils.assertNotNull(downloadFileExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(downloadFileExtendedRequest.getId(), "A node id must be provided when downloading a node's content.");
        StringBuilder sb = new StringBuilder(OperationFactory.DOWNLOAD_SIGNED_OPERATION);
        sb.append(downloadFileExtendedRequest.getId());
        if (downloadFileExtendedRequest.hasTransform()) {
            Optional<String> transformPathSuffix = TransformUtils.getTransformPathSuffix(downloadFileExtendedRequest.getTransform());
            if (transformPathSuffix.isPresent()) {
                sb.append(transformPathSuffix.get());
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Downloading transform ");
                outline107.append(downloadFileExtendedRequest.getTransform());
                outline107.append(" is no supported.");
                throw new UnsupportedOperationException(outline107.toString());
            }
        } else {
            sb.append("/content");
        }
        RequestPathGenerator.RequestPath createContentV2EndpointRequestPath = this.mRequestPathGenerator.createContentV2EndpointRequestPath(sb.toString());
        if (downloadFileExtendedRequest.getOwnerId() != null) {
            createContentV2EndpointRequestPath.addParameter(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY, downloadFileExtendedRequest.getOwnerId());
        }
        if (downloadFileExtendedRequest.hasResolution()) {
            createContentV2EndpointRequestPath.addParameter(ReactProperties.HereMapMarker.Y, String.valueOf(downloadFileExtendedRequest.getResolution()));
        }
        CropBox cropBox = downloadFileExtendedRequest.getCropBox();
        if (cropBox != null) {
            createContentV2EndpointRequestPath.addParameter("cropOffset", String.format("%d,%d", Long.valueOf(cropBox.getOffsetX()), Long.valueOf(cropBox.getOffsetY())));
            createContentV2EndpointRequestPath.addParameter("cropSize", String.format("%d,%d", Long.valueOf(cropBox.getCropWidth()), Long.valueOf(cropBox.getCropHeight())));
        }
        return new DownloadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, createContentV2EndpointRequestPath, "downloadFile", this.mMetricListener, progressListener, DownloadFileExtendedRequest.class, downloadFileExtendedRequest.getOutputStream(), downloadFileExtendedRequest.getBlockSize());
    }

    public CloudDriveOperation<GetAggregationsResponse> newGetAggregationsOperation(GetAggregationsRequest getAggregationsRequest) {
        RequestAssertUtils.assertNotNull(getAggregationsRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(getAggregationsRequest.getCategory(), "The category must be provided.");
        AssertUtils.assertNotNullOrEmpty(getAggregationsRequest.getAggregationContext(), "The aggregationContext must be provided.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("search/aggregation");
        createMetaDataEndpointRequestPath.addParameter("category", getAggregationsRequest.getCategory());
        createMetaDataEndpointRequestPath.addParameter("aggregationContext", getAggregationsRequest.getAggregationContext());
        Long limit = getAggregationsRequest.getLimit();
        if (limit != null) {
            createMetaDataEndpointRequestPath.addParameter(MetricsUtil.LegacyMetricTypes.LIMIT, Long.toString(limit.longValue()));
        }
        List<String> familyMembers = getAggregationsRequest.getFamilyMembers();
        if (familyMembers != null && !familyMembers.isEmpty()) {
            createMetaDataEndpointRequestPath.addParameter(PhotoSearchCategory.FAMILY_MEMBERS, familyMembers.toString().replace("[", "").replace("]", ""));
        }
        String dedupeContext = getAggregationsRequest.getDedupeContext();
        if (dedupeContext != null && !dedupeContext.isEmpty()) {
            createMetaDataEndpointRequestPath.addParameter("dedupeContext", dedupeContext);
        }
        String groupBy = getAggregationsRequest.getGroupBy();
        if (groupBy != null && !groupBy.isEmpty()) {
            createMetaDataEndpointRequestPath.addParameter("groupBy", groupBy);
        }
        RequestPropertyWriterImpl requestPropertyWriterImpl = new RequestPropertyWriterImpl();
        if (getAggregationsRequest.getLanguage() != null && !getAggregationsRequest.getLanguage().isEmpty()) {
            requestPropertyWriterImpl.setRequestProperty("Accept-Language", getAggregationsRequest.getLanguage());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, requestPropertyWriterImpl, GetAggregationsResponseDeserializer.INSTANCE, "getAggregation", this.mMetricListener, GetAggregationsRequest.class);
    }

    public CloudDriveOperation<GetFacesForNodeResponse> newGetFacesForNodeOperation(GetFacesForNodeRequest getFacesForNodeRequest) {
        AssertUtils.assertNotNull(getFacesForNodeRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getFacesForNodeRequest.getNodeId(), "Node id must be provided.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("node/");
        outline107.append(getFacesForNodeRequest.getNodeId());
        outline107.append("/faces");
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", extendedRequestPathGenerator.createFacesEndpointRequestPath(outline107.toString()), GetFacesForNodeResponseDeserializer.INSTANCE, "getFacesForNodeOperation", this.mMetricListener, GetFacesForNodeRequest.class);
    }

    public CloudDriveOperation<GetFacesForPersonResponse> newGetFacesForPersonOperation(GetFacesForPersonRequest getFacesForPersonRequest) {
        AssertUtils.assertNotNull(getFacesForPersonRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getFacesForPersonRequest.getPersonId(), "Person id must be provided.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("person/");
        outline107.append(getFacesForPersonRequest.getPersonId());
        outline107.append("/faces");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(getFacesForPersonRequest, GetFacesForPersonRequestSerializer.INSTANCE), GetFacesForPersonResponseDeserializer.INSTANCE, extendedRequestPathGenerator.createFacesEndpointRequestPath(outline107.toString()), "POST", "getFacesForPerson", this.mMetricListener, GetFacesForPersonRequest.class);
    }

    public CloudDriveOperation<GetFamilyChangesResponse> newGetFamilyChangesOperation(GetFamilyChangesRequest getFamilyChangesRequest) {
        RequestAssertUtils.assertNotNull(getFamilyChangesRequest, "The request cannot be null.");
        RequestAssertUtils.assertPositive(getFamilyChangesRequest.getRequestedItemCount(), "The requested item count may not be less than one.");
        SinglePartPostRequestWriter singlePartPostRequestWriter = new SinglePartPostRequestWriter(getFamilyChangesRequest, GetFamilyChangesRequestSerializer.INSTANCE);
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("family/changes", "V2");
        createMetaDataEndpointRequestPath.addParameter("requestedItemCount", Integer.toString(getFamilyChangesRequest.getRequestedItemCount()));
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, singlePartPostRequestWriter, GetFamilyChangesResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, "POST", "getFamilyChanges", this.mMetricListener, GetFamilyChangesRequest.class);
    }

    public CloudDriveOperation<GetFamilyResponse> newGetFamilyOperation(GetFamilyRequest getFamilyRequest) {
        AssertUtils.assertNotNull(getFamilyRequest, "The request cannot be null.");
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", this.mRequestPathGenerator.createMetaDataEndpointRequestPath("family"), new RequestPropertyWriterImpl(), GetFamilyResponseDeserializer.INSTANCE, "getFamily", this.mMetricListener, GetFamilyRequest.class);
    }

    public CloudDriveOperation<GetGroupCacheInfoResponse> newGetGroupCacheInfo(GroupRequest groupRequest) {
        RequestAssertUtils.assertNotNull(groupRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(groupRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(groupRequest.getGroupId());
        outline107.append("/cache");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (groupRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", groupRequest.getLang());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, GetGroupCacheInfoResponseDeserializer.INSTANCE, "getGroupCacheInfo", this.mMetricListener, groupRequest.getClass());
    }

    public CloudDriveOperation<GetGroupResponse> newGetGroupOperation(GetGroupRequest getGroupRequest) {
        AssertUtils.assertNotNull(getGroupRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getGroupRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(getGroupRequest.getGroupId());
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (getGroupRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", getGroupRequest.getLang());
        }
        createGroupsEndpointRequestPath.addParameter(PROMPTO_FEATURE_FLAGS_PARAM, PromptoFeatureFlag.DEPRECATE_GROUP_OWNEDBY.toString());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, GetGroupResponseDeserializer.INSTANCE, "getGroup", this.mMetricListener, GetGroupRequest.class);
    }

    public CloudDriveOperation<GetGroupPrivacyPreferencesResponse> newGetGroupPrivacyPreferences(GetGroupPrivacyPreferencesRequest getGroupPrivacyPreferencesRequest) {
        RequestAssertUtils.assertNotNull(getGroupPrivacyPreferencesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getGroupPrivacyPreferencesRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(getGroupPrivacyPreferencesRequest.getGroupId());
        outline107.append("/preferences/privacy");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (getGroupPrivacyPreferencesRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", getGroupPrivacyPreferencesRequest.getLang());
        }
        if (getGroupPrivacyPreferencesRequest.getPreferenceLevel() != null) {
            createGroupsEndpointRequestPath.addParameter("preferenceLevel", getGroupPrivacyPreferencesRequest.getPreferenceLevel());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, GetGroupPrivacyPreferencesResponseDeserializer.INSTANCE, "getGroupPrivacyPreferencesRequest", this.mMetricListener, GetGroupPrivacyPreferencesRequest.class);
    }

    public CloudDriveOperation<GetGroupShareBehaviorResponse> newGetGroupShareBehaviorOperation(GetGroupShareBehaviorRequest getGroupShareBehaviorRequest) {
        RequestAssertUtils.assertNotNull(getGroupShareBehaviorRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getGroupShareBehaviorRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(getGroupShareBehaviorRequest.getGroupId());
        outline107.append("/shareBehavior");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (getGroupShareBehaviorRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", getGroupShareBehaviorRequest.getLang());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, GetGroupShareBehaviorResponseDeserializer.INSTANCE, "getGroupShareBehaviorRequest", this.mMetricListener, GetGroupShareBehaviorRequest.class);
    }

    public CloudDriveOperation<GetGroupShareInfoResponse> newGetGroupShareInfo(GetGroupShareInfoRequest getGroupShareInfoRequest) {
        RequestAssertUtils.assertNotNull(getGroupShareInfoRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getGroupShareInfoRequest.getShareToken(), "The shareToken cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("shares/");
        outline107.append(getGroupShareInfoRequest.getShareToken());
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (getGroupShareInfoRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", getGroupShareInfoRequest.getLang());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, GetGroupShareInfoResponseDeserializer.INSTANCE, "getGroupShareInfo", this.mMetricListener, GetGroupShareInfoRequest.class);
    }

    public CloudDriveOperation<GetGroupShareResponse> newGetGroupShareOperation(GetGroupShareRequest getGroupShareRequest) {
        RequestAssertUtils.assertNotNull(getGroupShareRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getGroupShareRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(getGroupShareRequest.getGroupId());
        outline107.append("/share");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (getGroupShareRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", getGroupShareRequest.getLang());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, GetGroupShareResponseDeserializer.INSTANCE, "getGroupShareRequest", this.mMetricListener, GetGroupShareRequest.class);
    }

    public CloudDriveOperation<GetJobStatusResponse> newGetJobStatusOperation(GetJobStatusRequest getJobStatusRequest) {
        RequestAssertUtils.assertNotNull(getJobStatusRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getJobStatusRequest.getJobKey(), "The job key is required.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("jobStatus/");
        outline107.append(getJobStatusRequest.getJobKey());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString(), "V2"), GetJobStatusResponseDeserializer.INSTANCE, "getJobStatus", this.mMetricListener, GetJobStatusRequest.class);
    }

    public CloudDriveOperation<GetMemberPreferencesResponse> newGetMemberPreferences(GetMemberPreferencesRequest getMemberPreferencesRequest) {
        RequestAssertUtils.assertNotNull(getMemberPreferencesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getMemberPreferencesRequest.getGroupId(), "The groupId cannot be null or empty.");
        RequestAssertUtils.assertNotNullOrEmpty(getMemberPreferencesRequest.getMemberId(), "The memberId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(getMemberPreferencesRequest.getGroupId());
        outline107.append("/members/");
        outline107.append(getMemberPreferencesRequest.getMemberId());
        outline107.append("/preferences/notifications");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (getMemberPreferencesRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", getMemberPreferencesRequest.getLang());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, GetMemberPreferencesResponseDeserializer.INSTANCE, "getMemberPreferencesRequest", this.mMetricListener, GetMemberPreferencesRequest.class);
    }

    public <T extends INode> CloudDriveOperation<T> newGetNodeExtendedOperation(GetNodeExtendedRequest getNodeExtendedRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(getNodeExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getNodeExtendedRequest.getId(), "A node id must be provided when requesting a node.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(getNodeExtendedRequest.getId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString(), "V2");
        createMetaDataEndpointRequestPath.addParameter("fields", getNodeExtendedRequest.getFields());
        createMetaDataEndpointRequestPath.addParameter(UriUtil.LOCAL_ASSET_SCHEME, getNodeExtendedRequest.getAssetMapping());
        createMetaDataEndpointRequestPath.addParameter("tempLink", getNodeExtendedRequest.getTempLink());
        createMetaDataEndpointRequestPath.addParameter(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY, getNodeExtendedRequest.getOwnerId());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, jsonDeserializer, "getNode", this.mMetricListener, GetNodeExtendedRequest.class);
    }

    public CloudDriveOperation<GetPreferencesResponse> newGetPreferencesOperation(GetPreferencesRequest getPreferencesRequest) {
        AssertUtils.assertNotNull(getPreferencesRequest, "The request cannot be null.");
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/preferences", "V2"), new RequestPropertyWriterImpl(), GetPreferencesResponseDeserializer.INSTANCE, "getPreferences", this.mMetricListener, GetPreferencesRequest.class);
    }

    public CloudDriveOperation<GetProfileResponse> newGetProfileOperation(GetProfileRequest getProfileRequest) {
        AssertUtils.assertNotNull(getProfileRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getProfileRequest.getCustomerId(), "The customerId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("profiles/");
        outline107.append(getProfileRequest.getCustomerId());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString()), GetProfileResponseDeserializer.INSTANCE, "getProfile", this.mMetricListener, GetProfileRequest.class);
    }

    public CloudDriveOperation<GetReUpdateNodesResponse> newGetReUpdateNodesOperation(GetReUpdateNodesRequest getReUpdateNodesRequest) {
        RequestAssertUtils.assertNotNull(getReUpdateNodesRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createReUpdateEndpointRequestPath = this.mRequestPathGenerator.createReUpdateEndpointRequestPath("upload/node");
        createReUpdateEndpointRequestPath.addParameter(MetricsUtil.LegacyMetricTypes.LIMIT, Integer.toString(getReUpdateNodesRequest.getLimit()));
        if (getReUpdateNodesRequest.getStartToken() != null) {
            createReUpdateEndpointRequestPath.addParameter("startToken", getReUpdateNodesRequest.getStartToken());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createReUpdateEndpointRequestPath, GetReUpdateNodesResponseDeserializer.INSTANCE, "getReUpdateNodesRequest", this.mMetricListener, GetReUpdateNodesRequest.class);
    }

    public CloudDriveOperation<GetReactionResponse> newGetReactionOperation(GetReactionRequest getReactionRequest) {
        AssertUtils.assertNotNull(getReactionRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(getReactionRequest.getGroupId(), "The group ID cannot be null or empty.");
        AssertUtils.assertNotNullOrEmpty(getReactionRequest.getReactionId(), "The reaction ID cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(getReactionRequest.getGroupId());
        outline107.append("/reactions/");
        outline107.append(getReactionRequest.getReactionId());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString()), GetReactionResponseDeserializer.INSTANCE, "getReactionRequest", this.mMetricListener, GetReactionRequest.class);
    }

    public CloudDriveOperation<GetSearchSuggestionsResponse> newGetSearchSuggestionsOperation(GetSearchSuggestionsRequest getSearchSuggestionsRequest) {
        RequestAssertUtils.assertNotNull(getSearchSuggestionsRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(getSearchSuggestionsRequest.getQueryString(), "The query string must be provided.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("search/suggestion");
        createMetaDataEndpointRequestPath.addParameter("queryString", getSearchSuggestionsRequest.getQueryString());
        String filters = getSearchSuggestionsRequest.getFilters();
        if (filters != null && !filters.isEmpty()) {
            createMetaDataEndpointRequestPath.addParameter(MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, filters);
        }
        Long limit = getSearchSuggestionsRequest.getLimit();
        if (limit != null) {
            createMetaDataEndpointRequestPath.addParameter(MetricsUtil.LegacyMetricTypes.LIMIT, Long.toString(limit.longValue()));
        }
        String sort = getSearchSuggestionsRequest.getSort();
        if (sort != null && !sort.isEmpty()) {
            createMetaDataEndpointRequestPath.addParameter(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER, sort);
        }
        RequestPropertyWriterImpl requestPropertyWriterImpl = new RequestPropertyWriterImpl();
        if (getSearchSuggestionsRequest.getLanguage() != null && !getSearchSuggestionsRequest.getLanguage().isEmpty()) {
            requestPropertyWriterImpl.setRequestProperty("Accept-Language", getSearchSuggestionsRequest.getLanguage());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, requestPropertyWriterImpl, GetSearchSuggestionsResponseDeserializer.INSTANCE, "getSearchSuggestion", this.mMetricListener, GetSearchSuggestionsRequest.class);
    }

    public CloudDriveOperation<GetServicePlansResponse> newGetServicePlansOperation(GetServicePlansRequest getServicePlansRequest) {
        AssertUtils.assertNotNull(getServicePlansRequest, "The request cannot be null.");
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/plans"), GetServicePlansResponseDeserializer.INSTANCE, "getServicePlans", this.mMetricListener, GetServicePlansRequest.class);
    }

    public CloudDriveOperation<GetSplashResponse> newGetSplashOperation(GetSplashRequest getSplashRequest) {
        AssertUtils.assertNotNull(getSplashRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/splash");
        String deviceType = getSplashRequest.getDeviceType();
        if (deviceType != null && !deviceType.isEmpty()) {
            createMetaDataEndpointRequestPath.addParameter("deviceType", getSplashRequest.getDeviceType());
        }
        RequestPropertyWriterImpl requestPropertyWriterImpl = new RequestPropertyWriterImpl();
        if (getSplashRequest.getLanguage() != null && !getSplashRequest.getLanguage().isEmpty()) {
            requestPropertyWriterImpl.setRequestProperty("Accept-Language", getSplashRequest.getLanguage());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, requestPropertyWriterImpl, GetSplashResponseDeserializer.INSTANCE, "getSplash", this.mMetricListener, GetSplashRequest.class);
    }

    public CloudDriveOperation<GetSubscriptionsResponse> newGetSubscriptionsOperation(GetSubscriptionsRequest getSubscriptionsRequest) {
        AssertUtils.assertNotNull(getSubscriptionsRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/subscriptions");
        if (getSubscriptionsRequest.getInclude() != null) {
            for (String str : getSubscriptionsRequest.getInclude()) {
                createMetaDataEndpointRequestPath.addParameter("include", str);
            }
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, GetSubscriptionsResponseDeserializer.INSTANCE, "getSubscriptions", this.mMetricListener, GetSubscriptionsRequest.class);
    }

    public CloudDriveOperation<Void> newGetThumbnailExtendedOperation(GetThumbnailExtendedRequest getThumbnailExtendedRequest, ProgressListener progressListener) {
        RequestAssertUtils.assertNotNull(getThumbnailExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(getThumbnailExtendedRequest.getId(), "A node id must be provided when getting a thumbnail.");
        RequestPathGenerator.RequestPath createContentV1EndpointRequestPath = this.mRequestPathGenerator.createContentV1EndpointRequestPath("thumbnail/" + getThumbnailExtendedRequest.getId());
        if (getThumbnailExtendedRequest.getOwnerId() != null) {
            createContentV1EndpointRequestPath.addParameter(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY, getThumbnailExtendedRequest.getOwnerId());
        }
        if (getThumbnailExtendedRequest.getGroupShareToken() != null) {
            createContentV1EndpointRequestPath.addParameter("groupShareToken", getThumbnailExtendedRequest.getGroupShareToken());
        }
        if (getThumbnailExtendedRequest.hasViewBoxSet()) {
            createContentV1EndpointRequestPath.addParameter("viewBox", getThumbnailExtendedRequest.getViewBoxWidth() == getThumbnailExtendedRequest.getViewBoxHeight() ? Integer.toString(getThumbnailExtendedRequest.getViewBoxWidth()) : String.format("%d,%d", Integer.valueOf(getThumbnailExtendedRequest.getViewBoxWidth()), Integer.valueOf(getThumbnailExtendedRequest.getViewBoxHeight())));
            if (getThumbnailExtendedRequest.hasFitType() && !getThumbnailExtendedRequest.getFitType().isDefault()) {
                createContentV1EndpointRequestPath.addParameter("fit", getThumbnailExtendedRequest.getFitType().getValue());
            }
            CropBox cropBox = getThumbnailExtendedRequest.getCropBox();
            if (cropBox != null) {
                createContentV1EndpointRequestPath.addParameter("cropOffset", String.format("%d,%d", Long.valueOf(cropBox.getOffsetX()), Long.valueOf(cropBox.getOffsetY())));
                createContentV1EndpointRequestPath.addParameter("cropSize", String.format("%d,%d", Long.valueOf(cropBox.getCropWidth()), Long.valueOf(cropBox.getCropHeight())));
            }
            return new DownloadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, createContentV1EndpointRequestPath, "getThumbnail", this.mMetricListener, progressListener, GetThumbnailExtendedRequest.class, getThumbnailExtendedRequest.getOutputStream(), getThumbnailExtendedRequest.getBlockSize());
        }
        throw new IllegalArgumentException(String.format("viewBox is required in GetThumbnailRequest, %s", getThumbnailExtendedRequest));
    }

    public CloudDriveOperation<Void> newImportContactsOperation(ImportContactsRequest importContactsRequest) {
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "POST", this.mRequestPathGenerator.createGroupsEndpointRequestPath("contacts/import"), null, "importContactsRequest", this.mMetricListener, ImportContactsRequest.class);
    }

    public <T extends PaginatedCloudDriveResponse> CloudDriveOperation<T> newListChildrenExtendedOperation(ListChildrenExtendedRequest listChildrenExtendedRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(listChildrenExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(listChildrenExtendedRequest.getId(), "An id must be provided for a list children request.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(listChildrenExtendedRequest.getId());
        outline107.append("/children");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString(), "V2");
        createMetaDataEndpointRequestPath.addRequestParameters(listChildrenExtendedRequest);
        createMetaDataEndpointRequestPath.addParameter("assetMapping", listChildrenExtendedRequest.getAssetMapping());
        createMetaDataEndpointRequestPath.addParameter("tempLink", listChildrenExtendedRequest.getTempLink());
        createMetaDataEndpointRequestPath.addParameter("searchOnFamily", Boolean.valueOf(listChildrenExtendedRequest.getSearchOnFamily()));
        if (listChildrenExtendedRequest.getOwnerId() != null) {
            createMetaDataEndpointRequestPath.addParameter(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY, listChildrenExtendedRequest.getOwnerId());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, jsonDeserializer, "listChildren", this.mMetricListener, ListChildrenExtendedRequest.class);
    }

    public CloudDriveOperation<ListCollectionNodesResponse> newListCollectionNodes(ListCollectionNodesRequest listCollectionNodesRequest) {
        RequestAssertUtils.assertNotNull(listCollectionNodesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(listCollectionNodesRequest.getGroupId(), "The groupId cannot be null or empty.");
        RequestAssertUtils.assertNotNullOrEmpty(listCollectionNodesRequest.getCollectionId(), "The collectionId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(listCollectionNodesRequest.getGroupId());
        outline107.append("/collection/");
        outline107.append(listCollectionNodesRequest.getCollectionId());
        outline107.append("/nodes");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (listCollectionNodesRequest.getNextToken() != null) {
            createGroupsEndpointRequestPath.addParameter("nextToken", listCollectionNodesRequest.getNextToken());
        }
        if (listCollectionNodesRequest.getMaxResults() != null) {
            createGroupsEndpointRequestPath.addParameter("maxResults", listCollectionNodesRequest.getMaxResults().toString());
        }
        if (listCollectionNodesRequest.getSort() != null) {
            createGroupsEndpointRequestPath.addParameter(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER, listCollectionNodesRequest.getSort());
        }
        if (listCollectionNodesRequest.getDirection() != null) {
            createGroupsEndpointRequestPath.addParameter("direction", listCollectionNodesRequest.getDirection());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, ListCollectionNodesResponseDeserializer.INSTANCE, "listCollectionNodes", this.mMetricListener, ListCollectionNodesRequest.class);
    }

    public CloudDriveOperation<ListContactsResponse> newListContactsOperation(ListContactsRequest listContactsRequest) {
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = this.mRequestPathGenerator.createGroupsEndpointRequestPath("contacts");
        if (listContactsRequest.getKindFilter() != null) {
            createGroupsEndpointRequestPath.addParameter("kindFilter", listContactsRequest.getKindFilter().name());
        }
        if (listContactsRequest.getMemberFilter() != null) {
            createGroupsEndpointRequestPath.addParameter("memberFilter", listContactsRequest.getMemberFilter());
        }
        if (listContactsRequest.getGroupFilter() != null) {
            createGroupsEndpointRequestPath.addParameter("groupFilter", listContactsRequest.getGroupFilter());
        }
        if (listContactsRequest.getNextToken() != null) {
            createGroupsEndpointRequestPath.addParameter("nextToken", listContactsRequest.getNextToken());
        }
        if (listContactsRequest.getMaxResults() != null) {
            createGroupsEndpointRequestPath.addParameter("maxResults", String.valueOf(listContactsRequest.getMaxResults()));
        }
        if (listContactsRequest.getTerm() != null) {
            createGroupsEndpointRequestPath.addParameter("term", listContactsRequest.getTerm());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, ListContactsResponseDeserializer.INSTANCE, "listContactsRequest", this.mMetricListener, ListContactsRequest.class);
    }

    public CloudDriveOperation<ListGroupEventsResponse> newListGroupEvents(ListGroupEventsRequest listGroupEventsRequest) {
        RequestAssertUtils.assertNotNull(listGroupEventsRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(listGroupEventsRequest.getGroupId(), "The groupId cannot be null or empty.");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = this.mRequestPathGenerator.createGroupsEndpointRequestPath(DefaultDeliveryClient.EVENTS_DIRECTORY);
        if (listGroupEventsRequest.getNextToken() != null) {
            createGroupsEndpointRequestPath.addParameter("nextToken", listGroupEventsRequest.getNextToken());
        }
        if (listGroupEventsRequest.getPreviousToken() != null) {
            createGroupsEndpointRequestPath.addParameter("previousToken", listGroupEventsRequest.getPreviousToken());
        }
        if (listGroupEventsRequest.getMaxResults() != null) {
            createGroupsEndpointRequestPath.addParameter("maxResults", listGroupEventsRequest.getMaxResults().toString());
        }
        if (listGroupEventsRequest.getSort() != null) {
            createGroupsEndpointRequestPath.addParameter(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER, listGroupEventsRequest.getSort());
        }
        if (listGroupEventsRequest.getDirection() != null) {
            createGroupsEndpointRequestPath.addParameter("direction", listGroupEventsRequest.getDirection());
        }
        if (listGroupEventsRequest.getAddedBy() != null) {
            createGroupsEndpointRequestPath.addParameter("addedBy", listGroupEventsRequest.getAddedBy());
        }
        if (listGroupEventsRequest.getKindSet() != null) {
            createGroupsEndpointRequestPath.addParameter("kindSet", listGroupEventsRequest.getKindSet());
        }
        if (listGroupEventsRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", listGroupEventsRequest.getLang());
        }
        if (listGroupEventsRequest.getGroupId() != null) {
            createGroupsEndpointRequestPath.addParameter("groupId", listGroupEventsRequest.getGroupId());
        }
        if (listGroupEventsRequest.getMaxCovers() != null) {
            createGroupsEndpointRequestPath.addParameter("maxCovers", listGroupEventsRequest.getMaxCovers().toString());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, ListGroupEventsResponseDeserializer.INSTANCE, "listGroupEvents", this.mMetricListener, ListGroupEventsRequest.class);
    }

    public CloudDriveOperation<ListGroupsResponse> newListGroupsOperation(ListGroupsRequest listGroupsRequest) {
        AssertUtils.assertNotNull(listGroupsRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = this.mRequestPathGenerator.createGroupsEndpointRequestPath(FeatureName.GROUPS);
        if (listGroupsRequest.getNextToken() != null) {
            createGroupsEndpointRequestPath.addParameter("nextToken", listGroupsRequest.getNextToken());
        }
        if (listGroupsRequest.getMaxResults() != null) {
            createGroupsEndpointRequestPath.addParameter("maxResults", listGroupsRequest.getMaxResults().toString());
        }
        if (listGroupsRequest.getSort() != null) {
            createGroupsEndpointRequestPath.addParameter(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER, listGroupsRequest.getSort());
        }
        if (listGroupsRequest.getDirection() != null) {
            createGroupsEndpointRequestPath.addParameter("direction", listGroupsRequest.getDirection());
        }
        if (listGroupsRequest.getKindFilter() != null) {
            createGroupsEndpointRequestPath.addParameter("kindFilter", listGroupsRequest.getKindFilter());
        }
        if (listGroupsRequest.getKindSet() != null) {
            createGroupsEndpointRequestPath.addParameter("kindSet", listGroupsRequest.getKindSet());
        }
        if (listGroupsRequest.getPrivacyLevel() != null) {
            createGroupsEndpointRequestPath.addParameter("privacyLevel", listGroupsRequest.getPrivacyLevel());
        }
        if (listGroupsRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", listGroupsRequest.getLang());
        }
        createGroupsEndpointRequestPath.addParameter(PROMPTO_FEATURE_FLAGS_PARAM, PromptoFeatureFlag.DEPRECATE_GROUP_OWNEDBY.toString());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, ListGroupsResponseDeserializer.INSTANCE, "listGroups", this.mMetricListener, ListGroupsRequest.class);
    }

    public CloudDriveOperation<ListInvitesResponse> newListInvitesOperation(ListInvitesRequest listInvitesRequest) {
        RequestAssertUtils.assertNotNull(listInvitesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNull(listInvitesRequest.getGroupId(), "The groupId cannot be null.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(listInvitesRequest.getGroupId());
        outline107.append("/invites");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (listInvitesRequest.getNextToken() != null) {
            createGroupsEndpointRequestPath.addParameter("nextToken", listInvitesRequest.getNextToken());
        }
        if (listInvitesRequest.getMaxResults() != null) {
            createGroupsEndpointRequestPath.addParameter("maxResults", listInvitesRequest.getMaxResults().toString());
        }
        if (listInvitesRequest.getSort() != null) {
            createGroupsEndpointRequestPath.addParameter(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER, listInvitesRequest.getSort());
        }
        if (listInvitesRequest.getDirection() != null) {
            createGroupsEndpointRequestPath.addParameter("direction", listInvitesRequest.getDirection());
        }
        if (listInvitesRequest.getState() != null) {
            createGroupsEndpointRequestPath.addParameter("state", listInvitesRequest.getState());
        }
        if (listInvitesRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", listInvitesRequest.getLang());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, ListInvitesResponseDeserializer.INSTANCE, "listInvitesRequest", this.mMetricListener, ListInvitesRequest.class);
    }

    public CloudDriveOperation<ListMembersResponse> newListMembersOperation(ListMembersRequest listMembersRequest) {
        AssertUtils.assertNotNull(listMembersRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(listMembersRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(listMembersRequest.getGroupId());
        outline107.append("/members");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (listMembersRequest.getNextToken() != null) {
            createGroupsEndpointRequestPath.addParameter("nextToken", listMembersRequest.getNextToken());
        }
        if (listMembersRequest.getMaxResults() != null) {
            createGroupsEndpointRequestPath.addParameter("maxResults", listMembersRequest.getMaxResults().toString());
        }
        if (listMembersRequest.getSort() != null) {
            createGroupsEndpointRequestPath.addParameter(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER, listMembersRequest.getSort().name());
        }
        if (listMembersRequest.getDirection() != null) {
            createGroupsEndpointRequestPath.addParameter("direction", listMembersRequest.getDirection().name());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, ListMembersResponseDeserializer.INSTANCE, "listMembers", this.mMetricListener, ListMembersRequest.class);
    }

    public CloudDriveOperation<ListNotificationTopicSubscriptionResponse> newListNotificationTopicSubscriptionsOperation(ListNotificationTopicSubscriptionsRequest listNotificationTopicSubscriptionsRequest) {
        AssertUtils.assertNotNull(listNotificationTopicSubscriptionsRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("notificationTopics/subscriptions");
        if (listNotificationTopicSubscriptionsRequest.getLocale() != null) {
            createMetaDataEndpointRequestPath.addParameter("locale", listNotificationTopicSubscriptionsRequest.getLocale());
        }
        createMetaDataEndpointRequestPath.addParameter("sourceId", listNotificationTopicSubscriptionsRequest.getSourceId());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, ListNotificationTopicSubscriptionsResponseDeserializer.INSTANCE, "listNotificationTopicSubscriptions", this.mMetricListener, ListNotificationTopicSubscriptionsRequest.class);
    }

    public CloudDriveOperation<ListReactionsResponse> newListReactionsOperation(ListReactionsRequest listReactionsRequest) {
        AssertUtils.assertNotNull(listReactionsRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(listReactionsRequest.getGroupId(), "The group ID cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(listReactionsRequest.getGroupId());
        outline107.append("/reactions");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (listReactionsRequest.getNextToken() != null) {
            createGroupsEndpointRequestPath.addParameter("nextToken", listReactionsRequest.getNextToken());
        }
        if (listReactionsRequest.getMaxResults() != null) {
            createGroupsEndpointRequestPath.addParameter("maxResults", listReactionsRequest.getMaxResults().toString());
        }
        if (listReactionsRequest.getTopic() != null) {
            createGroupsEndpointRequestPath.addParameter("topic", listReactionsRequest.getTopic());
        }
        if (listReactionsRequest.getSort() != null) {
            createGroupsEndpointRequestPath.addParameter(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER, listReactionsRequest.getSort());
        }
        if (listReactionsRequest.getDirection() != null) {
            createGroupsEndpointRequestPath.addParameter("direction", listReactionsRequest.getDirection());
        }
        if (listReactionsRequest.getKind() != null) {
            createGroupsEndpointRequestPath.addParameter(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, listReactionsRequest.getKind());
        }
        if (listReactionsRequest.getKindSet() != null) {
            createGroupsEndpointRequestPath.addParameter("kindSet", listReactionsRequest.getKindSet());
        }
        createGroupsEndpointRequestPath.addParameter("includeCoverPhotos", Boolean.valueOf(listReactionsRequest.isIncludeCoverPhotos()));
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, ListReactionsResponseDeserializer.INSTANCE, "listReactionsRequest", this.mMetricListener, ListReactionsRequest.class);
    }

    public CloudDriveOperation<ListSourcesResponse> newListSourcesOperation(ListSourcesRequest listSourcesRequest) {
        RequestAssertUtils.assertNotNull(listSourcesRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/source");
        createMetaDataEndpointRequestPath.addParameter("includeDevice", listSourcesRequest.getIncludeDevice());
        createMetaDataEndpointRequestPath.addParameter("parentDeviceId", listSourcesRequest.getParentDeviceId());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, ListSourcesResponseDeserializer.INSTANCE, "listSources", this.mMetricListener, ListSourcesRequest.class);
    }

    public CloudDriveOperation<MoveNodeToTrashExtendedResponse> newMoveNodeToTrashExtendedOperation(MoveNodeToTrashExtendedRequest moveNodeToTrashExtendedRequest) {
        RequestAssertUtils.assertNotNull(moveNodeToTrashExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(moveNodeToTrashExtendedRequest.getId(), "The node id must be provided to move to trash.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("trash/");
        outline107.append(moveNodeToTrashExtendedRequest.getId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = extendedRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        createMetaDataEndpointRequestPath.addParameter("recurse", Boolean.toString(moveNodeToTrashExtendedRequest.getRecurse()));
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(moveNodeToTrashExtendedRequest, MoveNodeToTrashRequestSerializer.INSTANCE), MoveNodeToTrashExtendedResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, SmartDeviceDataProvider.METHOD_HTTP_PUT, "moveNodeToTrashExtended", this.mMetricListener, MoveNodeToTrashExtendedRequest.class);
    }

    public CloudDriveOperation<PatchGroupCollectionResponse> newPatchGroupCollectionOperation(PatchGroupCollectionRequest patchGroupCollectionRequest) {
        RequestAssertUtils.assertNotNull(patchGroupCollectionRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(patchGroupCollectionRequest.getGroupId(), "The groupId cannot be null or empty.");
        RequestAssertUtils.assertNotNullOrEmpty(patchGroupCollectionRequest.getCollectionId(), "The collectionId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(patchGroupCollectionRequest.getGroupId());
        outline107.append("/collections/");
        outline107.append(patchGroupCollectionRequest.getCollectionId());
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(patchGroupCollectionRequest, PatchGroupCollectionRequestSerializer.INSTANCE), PatchGroupCollectionResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "PATCH", "patchGroupCollectionRequest", this.mMetricListener, PatchGroupCollectionRequest.class);
    }

    public CloudDriveOperation<PatchGroupResponse> newPatchGroupOperation(PatchGroupRequest patchGroupRequest) {
        RequestAssertUtils.assertNotNull(patchGroupRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(patchGroupRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(patchGroupRequest.getGroupId());
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        createGroupsEndpointRequestPath.addParameter(PROMPTO_FEATURE_FLAGS_PARAM, PromptoFeatureFlag.DEPRECATE_GROUP_OWNEDBY.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(patchGroupRequest, PatchGroupRequestSerializer.INSTANCE), PatchGroupResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "PATCH", "patchGroupRequest", this.mMetricListener, PatchGroupRequest.class);
    }

    public CloudDriveOperation<PatchGroupPrivacyPreferencesResponse> newPatchGroupPrivacyPreferences(PatchGroupPrivacyPreferencesRequest patchGroupPrivacyPreferencesRequest) {
        RequestAssertUtils.assertNotNull(patchGroupPrivacyPreferencesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(patchGroupPrivacyPreferencesRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(patchGroupPrivacyPreferencesRequest.getGroupId());
        outline107.append("/preferences/privacy");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(patchGroupPrivacyPreferencesRequest, PatchGroupPrivacyPreferencesRequestSerializer.INSTANCE), PatchGroupPrivacyPreferencesResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "PATCH", "patchGroupPrivacyPreferencesRequest", this.mMetricListener, PatchGroupPrivacyPreferencesRequest.class);
    }

    public CloudDriveOperation<PatchMemberPreferencesResponse> newPatchMemberPreferences(PatchMemberPreferencesRequest patchMemberPreferencesRequest) {
        RequestAssertUtils.assertNotNull(patchMemberPreferencesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(patchMemberPreferencesRequest.getGroupId(), "The groupId cannot be null or empty.");
        RequestAssertUtils.assertNotNullOrEmpty(patchMemberPreferencesRequest.getMemberId(), "The memberId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(patchMemberPreferencesRequest.getGroupId());
        outline107.append("/members/");
        outline107.append(patchMemberPreferencesRequest.getMemberId());
        outline107.append("/preferences/notifications");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(patchMemberPreferencesRequest, PatchMemberPreferencesRequestSerializer.INSTANCE), PatchMemberPreferencesResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "PATCH", "patchMemberPreferencesRequest", this.mMetricListener, PatchMemberPreferencesRequest.class);
    }

    public CloudDriveOperation<Void> newPurgeNodeOperation(PurgeNodeRequest purgeNodeRequest) {
        RequestAssertUtils.assertNotNull(purgeNodeRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(purgeNodeRequest.getNodeId(), "The nodeId cannot be null.");
        ClientConfiguration clientConfiguration = this.mClientConfiguration;
        AccountConfiguration accountConfiguration = this.mAccountConfiguration;
        SourceInfoGenerator sourceInfoGenerator = this.mSourceInfoGenerator;
        JsonPostRequestWriter jsonPostRequestWriter = new JsonPostRequestWriter(purgeNodeRequest, PurgeNodeRequestSerializer.INSTANCE);
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(purgeNodeRequest.getNodeId());
        return new CloudDriveBodyOperation(this, clientConfiguration, accountConfiguration, sourceInfoGenerator, jsonPostRequestWriter, null, extendedRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString(), "V2"), Constants.REQUEST_METHOD_DELETE, "purgeNodeRequest", this.mMetricListener, PurgeNodeRequest.class);
    }

    public CloudDriveOperation<RefreshGroupShareResponse> newRefreshGroupShareOperation(RefreshGroupShareRequest refreshGroupShareRequest) {
        RequestAssertUtils.assertNotNull(refreshGroupShareRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(refreshGroupShareRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(refreshGroupShareRequest.getGroupId());
        outline107.append("/share");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        if (refreshGroupShareRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", refreshGroupShareRequest.getLang());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "POST", createGroupsEndpointRequestPath, RefreshGroupShareResponseDeserializer.INSTANCE, "refreshGroupShareRequest", this.mMetricListener, RefreshGroupShareRequest.class);
    }

    public CloudDriveOperation<Void> newRemoveChildFromParentExtendedOperation(RemoveChildFromParentExtendedRequest removeChildFromParentExtendedRequest) {
        RequestAssertUtils.assertNotNull(removeChildFromParentExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(removeChildFromParentExtendedRequest.getParentId(), "The parent id must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(removeChildFromParentExtendedRequest.getChildId(), "The child id must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(removeChildFromParentExtendedRequest.getParentId());
        outline107.append("/children/");
        outline107.append(removeChildFromParentExtendedRequest.getChildId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString(), "V2");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(removeChildFromParentExtendedRequest, RemoveChildExtendedRequestSerializer.INSTANCE), null, createMetaDataEndpointRequestPath, Constants.REQUEST_METHOD_DELETE, "removeChildFromParent", this.mMetricListener, RemoveChildFromParentExtendedRequest.class);
    }

    public CloudDriveOperation<Void> newRemoveFacesFromPersonOperation(RemoveFacesFromPersonRequest removeFacesFromPersonRequest) {
        RequestAssertUtils.assertNotNull(removeFacesFromPersonRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(removeFacesFromPersonRequest.getPersonId(), "The person id must be provided.");
        AssertUtils.assertNotNullOrEmpty(removeFacesFromPersonRequest.getFaces(), "The face list must be provided and not empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("people/");
        outline107.append(removeFacesFromPersonRequest.getPersonId());
        outline107.append("/faces/remove");
        RequestPathGenerator.RequestPath createFacesEndpointRequestPath = extendedRequestPathGenerator.createFacesEndpointRequestPath(outline107.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(removeFacesFromPersonRequest, RemoveFacesFromPersonRequestSerializer.INSTANCE), null, createFacesEndpointRequestPath, "POST", "removeFacesFromPerson", this.mMetricListener, RemoveFacesFromPersonRequest.class);
    }

    public CloudDriveOperation<RenameFaceClusterResponse> newRenameFaceClusterOperation(RenameFaceClusterRequest renameFaceClusterRequest) {
        RequestAssertUtils.assertNotNull(renameFaceClusterRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(renameFaceClusterRequest.getSourceClusterId(), "The source cluster id must be provided.");
        AssertUtils.assertNotNullOrEmpty(renameFaceClusterRequest.getNewName(), "The new name must be provided.");
        AssertUtils.assertNotNullOrEmpty(renameFaceClusterRequest.getContext(), "The context must be provided.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("cluster/name");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(renameFaceClusterRequest, RenameFaceClusterRequestSerializer.INSTANCE), RenameFaceClusterResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, SmartDeviceDataProvider.METHOD_HTTP_PUT, "renameFaceCluster", this.mMetricListener, RenameFaceClusterRequest.class);
    }

    public CloudDriveOperation<ListContactsResponse> newSearchContactsOperation(SearchContactsRequest searchContactsRequest) {
        RequestAssertUtils.assertNotNull(searchContactsRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = this.mRequestPathGenerator.createGroupsEndpointRequestPath("searchContacts");
        if (searchContactsRequest.getLang() != null) {
            createGroupsEndpointRequestPath.addParameter("lang", searchContactsRequest.getLang());
        }
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(searchContactsRequest, SearchContactsRequestSerializer.INSTANCE), ListContactsResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, "POST", "searchContactsRequest", this.mMetricListener, SearchContactsRequest.class);
    }

    public CloudDriveOperation<SearchGroupNodesResponse> newSearchGroupNodes(SearchGroupNodesRequest searchGroupNodesRequest) {
        RequestAssertUtils.assertNotNull(searchGroupNodesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(searchGroupNodesRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("search/groups/");
        outline107.append(searchGroupNodesRequest.getGroupId());
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = extendedRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString());
        createMetaDataEndpointRequestPath.addParameter("resourceVersion", "V2");
        createMetaDataEndpointRequestPath.addRequestParameters(searchGroupNodesRequest);
        createMetaDataEndpointRequestPath.addParameter("assetMapping", searchGroupNodesRequest.getAssetMapping());
        createMetaDataEndpointRequestPath.addParameter("tempLink", searchGroupNodesRequest.getTempLink());
        createMetaDataEndpointRequestPath.addParameter("groupByForTime", searchGroupNodesRequest.getGroupByForTime());
        createMetaDataEndpointRequestPath.addParameter("searchContext", searchGroupNodesRequest.getSearchContext());
        createMetaDataEndpointRequestPath.addParameter("groupSharedToken", searchGroupNodesRequest.getGroupShareToken());
        RequestPropertyWriterImpl requestPropertyWriterImpl = new RequestPropertyWriterImpl();
        if (searchGroupNodesRequest.getLanguage() != null && !searchGroupNodesRequest.getLanguage().isEmpty()) {
            requestPropertyWriterImpl.setRequestProperty("Accept-Language", searchGroupNodesRequest.getLanguage());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, requestPropertyWriterImpl, SearchGroupNodesResponseDeserializer.INSTANCE, "searchGroupNodes", this.mMetricListener, searchGroupNodesRequest.getSessionId(), SearchGroupNodesRequest.class);
    }

    public CloudDriveOperation<SearchGroupNodesExtendedResponse> newSearchGroupNodesExtended(SearchGroupNodesRequest searchGroupNodesRequest) {
        RequestAssertUtils.assertNotNull(searchGroupNodesRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(searchGroupNodesRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(searchGroupNodesRequest.getGroupId());
        outline107.append("/search/nodes");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        createGroupsEndpointRequestPath.addParameter("resourceVersion", "V2");
        createGroupsEndpointRequestPath.addRequestParameters(searchGroupNodesRequest);
        createGroupsEndpointRequestPath.addParameter("assetMapping", searchGroupNodesRequest.getAssetMapping());
        createGroupsEndpointRequestPath.addParameter("tempLink", searchGroupNodesRequest.getTempLink());
        createGroupsEndpointRequestPath.addParameter("groupByForTime", searchGroupNodesRequest.getGroupByForTime());
        createGroupsEndpointRequestPath.addParameter("searchContext", searchGroupNodesRequest.getSearchContext());
        createGroupsEndpointRequestPath.addParameter("groupSharedToken", searchGroupNodesRequest.getGroupShareToken());
        RequestPropertyWriterImpl requestPropertyWriterImpl = new RequestPropertyWriterImpl();
        if (searchGroupNodesRequest.getLanguage() != null && !searchGroupNodesRequest.getLanguage().isEmpty()) {
            requestPropertyWriterImpl.setRequestProperty("Accept-Language", searchGroupNodesRequest.getLanguage());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createGroupsEndpointRequestPath, requestPropertyWriterImpl, SearchGroupNodesExtendedResponseDeserializer.INSTANCE, "searchGroupNodes", this.mMetricListener, searchGroupNodesRequest.getSessionId(), SearchGroupNodesRequest.class);
    }

    public CloudDriveOperation<SearchKeyResponse> newSearchKey(SearchKeyRequest searchKeyRequest) {
        RequestAssertUtils.assertNotNull(searchKeyRequest, "The request cannot be null.");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("search");
        createMetaDataEndpointRequestPath.addParameter("resourceVersion", "V2");
        createMetaDataEndpointRequestPath.addRequestParameters(searchKeyRequest);
        createMetaDataEndpointRequestPath.addParameter("assetMapping", searchKeyRequest.getAssetMapping());
        createMetaDataEndpointRequestPath.addParameter("tempLink", searchKeyRequest.getTempLink());
        createMetaDataEndpointRequestPath.addParameter("groupByForTime", searchKeyRequest.getGroupByForTime());
        createMetaDataEndpointRequestPath.addParameter("searchContext", searchKeyRequest.getSearchContext());
        createMetaDataEndpointRequestPath.addParameter("groupId", searchKeyRequest.getGroupId());
        RequestPropertyWriterImpl requestPropertyWriterImpl = new RequestPropertyWriterImpl();
        if (searchKeyRequest.getLanguage() != null && !searchKeyRequest.getLanguage().isEmpty()) {
            requestPropertyWriterImpl.setRequestProperty("Accept-Language", searchKeyRequest.getLanguage());
        }
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "GET", createMetaDataEndpointRequestPath, requestPropertyWriterImpl, SearchKeyResponseDeserializer.INSTANCE, "searchKey", this.mMetricListener, searchKeyRequest.getSessionId(), searchKeyRequest.getClass());
    }

    public final CloudDriveOperation<SetNodeResponse> newSetNodeOperation(SetNodeRequest setNodeRequest) {
        RequestAssertUtils.assertNotNull(setNodeRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(setNodeRequest.getId(), "A node id must be provided when setting meta-data on a node.");
        RequestAssertUtils.assertNotNullOrEmpty(setNodeRequest.getKind(), "A node kind must be provided when setting meta-data on a node.");
        if (!"FILE".equals(setNodeRequest.getKind())) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
            outline107.append(setNodeRequest.getId());
            return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(setNodeRequest, SetNodeRequestSerializer.INSTANCE), SetNodeResponseDeserializer.INSTANCE, this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString()), SmartDeviceDataProvider.METHOD_HTTP_PUT, "setNode", this.mMetricListener, SetNodeRequest.class);
        }
        throw new IllegalArgumentException("Setting the meta-data of a file is not yet supported.");
    }

    public CloudDriveOperation<Void> newSetPreferenceOperation(SetPreferenceRequest setPreferenceRequest) {
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath;
        AssertUtils.assertNotNull(setPreferenceRequest, "The request cannot be null.");
        int ordinal = setPreferenceRequest.getPreferenceType().ordinal();
        if (ordinal == 0) {
            ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("family/preferences/");
            outline107.append(setPreferenceRequest.getPreferenceKey());
            createMetaDataEndpointRequestPath = extendedRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString(), "V2");
        } else if (ordinal != 1) {
            createMetaDataEndpointRequestPath = null;
        } else {
            ExtendedRequestPathGenerator extendedRequestPathGenerator2 = this.mRequestPathGenerator;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("account/preferences/");
            outline1072.append(setPreferenceRequest.getPreferenceKey());
            createMetaDataEndpointRequestPath = extendedRequestPathGenerator2.createMetaDataEndpointRequestPath(outline1072.toString(), "V2");
        }
        RequestPathGenerator.RequestPath requestPath = createMetaDataEndpointRequestPath;
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(setPreferenceRequest, SetPreferenceRequestSerializer.INSTANCE), null, requestPath, SmartDeviceDataProvider.METHOD_HTTP_PUT, "setPreference", this.mMetricListener, SetPreferenceRequest.class);
    }

    public CloudDriveOperation<Void> newSetupAccountOperation(SetupAccountRequest setupAccountRequest) {
        AssertUtils.assertNotNull(setupAccountRequest, "The request cannot be null.");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(setupAccountRequest, SetupAccountRequestSerializer.INSTANCE), null, this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account"), "POST", "setupAccount", this.mMetricListener, SetupAccountRequest.class);
    }

    public CloudDriveOperation<SourceInfoExtendedResponse> newSetupSourceExtendedOperation(SetupSourceExtendedRequest setupSourceExtendedRequest) {
        RequestAssertUtils.assertNotNull(setupSourceExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(setupSourceExtendedRequest.getDeviceClass(), "The device class must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(setupSourceExtendedRequest.getDevicePlatform(), "The device platform must be provided.");
        RequestAssertUtils.assertNotNullOrEmpty(setupSourceExtendedRequest.getSourceApplicationName(), "The source application name must be provided");
        RequestAssertUtils.assertNotNullOrEmpty(setupSourceExtendedRequest.getSourceVersion(), "The source version must be provided");
        RequestPathGenerator.RequestPath createMetaDataEndpointRequestPath = this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/source");
        createMetaDataEndpointRequestPath.addRequestParameters(setupSourceExtendedRequest);
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(setupSourceExtendedRequest, SetupSourceExtendedRequestSerializer.INSTANCE), SourceInfoExtendedResponseDeserializer.INSTANCE, createMetaDataEndpointRequestPath, "POST", "setupSource", this.mMetricListener, SetupSourceExtendedRequest.class);
    }

    public CloudDriveOperation<SubscribeNotificationTopicResponse> newSubscribeNotificationTopicOperation(SubscribeNotificationTopicRequest subscribeNotificationTopicRequest) {
        AssertUtils.assertNotNull(subscribeNotificationTopicRequest, "The request cannot be null.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("notificationTopics/subscriptions/");
        outline107.append(subscribeNotificationTopicRequest.getSourceId());
        outline107.append("/");
        outline107.append(subscribeNotificationTopicRequest.getNotificationTopicId());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, SmartDeviceDataProvider.METHOD_HTTP_PUT, extendedRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString()), SubscribeNotificationTopicResponseDeserializer.INSTANCE, "subscribeNotificationTopic", this.mMetricListener, SubscribeNotificationTopicRequest.class);
    }

    public CloudDriveOperation<SubscribePlanResponse> newSubscribePlanOperation(SubscribePlanRequest subscribePlanRequest) {
        AssertUtils.assertNotNull(subscribePlanRequest, "The request cannot be null.");
        AssertUtils.assertNotNullOrEmpty(subscribePlanRequest.getPlanId(), "The plan id must be provided.");
        AssertUtils.assertNotNullOrEmpty(subscribePlanRequest.getPendingPlanId(), "The pending plan id must be provided.");
        AssertUtils.assertNotNullOrEmpty(subscribePlanRequest.getMarketplaceId(), "The marketplace id must be provided.");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(subscribePlanRequest, SubscribePlanRequestSerializer.INSTANCE), SubscribePlanResponseDeserializer.INSTANCE, this.mRequestPathGenerator.createMetaDataEndpointRequestPath("account/subscriptions"), "POST", "subscribePlan", this.mMetricListener, SubscribePlanRequest.class);
    }

    public CloudDriveOperation<UnsubscribeNotificationTopicResponse> newUnubscribeNotificationTopicOperation(UnsubscribeNotificationTopicRequest unsubscribeNotificationTopicRequest) {
        AssertUtils.assertNotNull(unsubscribeNotificationTopicRequest, "The request cannot be null.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("notificationTopics/subscriptions/");
        outline107.append(unsubscribeNotificationTopicRequest.getSourceId());
        outline107.append("/");
        outline107.append(unsubscribeNotificationTopicRequest.getNotificationTopicId());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, Constants.REQUEST_METHOD_DELETE, extendedRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString()), UnsubscribeNotificationTopicResponseDeserializer.INSTANCE, "unsubscribeNotificationTopic", this.mMetricListener, UnsubscribeNotificationTopicRequest.class);
    }

    public CloudDriveOperation<UpdateFamilyArchiveResponse> newUpdateFamilyArchiveOperation(UpdateFamilyArchiveRequest updateFamilyArchiveRequest) {
        RequestAssertUtils.assertNotNull(updateFamilyArchiveRequest, "The request cannot be null.");
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(updateFamilyArchiveRequest, UpdateFamilyArchiveRequestSerializer.INSTANCE), UpdateFamilyArchiveResponseDeserializer.INSTANCE, this.mRequestPathGenerator.createMetaDataEndpointRequestPath("familyArchive", "V2"), "POST", "updateFamilyArchiveRequest", this.mMetricListener, UpdateFamilyArchiveRequest.class);
    }

    public CloudDriveOperation<UpdateGroupResponse> newUpdateGroupOperation(UpdateGroupRequest updateGroupRequest) {
        RequestAssertUtils.assertNotNull(updateGroupRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNull(updateGroupRequest.getName(), "The name cannot be null.");
        RequestAssertUtils.assertNotNull(updateGroupRequest.getDescription(), "The description cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(updateGroupRequest.getGroupId(), "The groupId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(updateGroupRequest.getGroupId());
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        createGroupsEndpointRequestPath.addParameter(PROMPTO_FEATURE_FLAGS_PARAM, PromptoFeatureFlag.DEPRECATE_GROUP_OWNEDBY.toString());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new JsonPostRequestWriter(updateGroupRequest, UpdateGroupRequestSerializer.INSTANCE), UpdateGroupResponseDeserializer.INSTANCE, createGroupsEndpointRequestPath, SmartDeviceDataProvider.METHOD_HTTP_PUT, "updateGroupRequest", this.mMetricListener, UpdateGroupRequest.class);
    }

    public <T extends INode> CloudDriveOperation<T> newUpdateNodeExtendedOperation(UpdateNodeExtendedRequest updateNodeExtendedRequest, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(updateNodeExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(updateNodeExtendedRequest.getId(), "The node id must be provided.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("nodes/");
        outline107.append(updateNodeExtendedRequest.getId());
        return new CloudDriveBodyOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new SinglePartPostRequestWriter(updateNodeExtendedRequest, UpdateNodeExtendedRequestSerializer.INSTANCE), jsonDeserializer, this.mRequestPathGenerator.createMetaDataEndpointRequestPath(outline107.toString(), "V2"), "PATCH", "updateNode", this.mMetricListener, UpdateNodeExtendedRequest.class);
    }

    public <T extends INode> CloudDriveOperation<T> newUploadFileExtendedOperation(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener, JsonDeserializer<T> jsonDeserializer) {
        RequestAssertUtils.assertNotNull(uploadFileExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(uploadFileExtendedRequest.getName(), "A name must be provided when creating a node.");
        RequestAssertUtils.assertNotNullOrEmpty(uploadFileExtendedRequest.getKind(), "A node kind must be provided when creating a node.");
        RequestAssertUtils.assertNotNull(uploadFileExtendedRequest.getInputStream(), "An InputStream must be provided when creating a file node.");
        if (uploadFileExtendedRequest.getContentLength() > 0) {
            if (uploadFileExtendedRequest.getContentSignatureType() != null && !uploadFileExtendedRequest.getContentSignatureType().isEmpty()) {
                throw new IllegalArgumentException("ContentSignatureType is only supported in V2.");
            }
            if (uploadFileExtendedRequest.getContentSignature() != null && !uploadFileExtendedRequest.getContentSignature().isEmpty()) {
                throw new IllegalArgumentException("ContentSignature is only supported in V2.");
            }
            RequestPathGenerator.RequestPath createContentEndpointRequestPath = this.mRequestPathGenerator.createContentEndpointRequestPath("nodes", "V2");
            createContentEndpointRequestPath.addParameter("localId", uploadFileExtendedRequest.getLocalId());
            createContentEndpointRequestPath.addParameter("suppress", uploadFileExtendedRequest.getSuppress());
            createContentEndpointRequestPath.addParameter("addToFamilyArchive", Boolean.toString(uploadFileExtendedRequest.addToFamilyArchive()));
            return new UploadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new UploadRequestBodyWriter(uploadFileExtendedRequest, progressListener), jsonDeserializer, null, createContentEndpointRequestPath, "uploadFile", this.mMetricListener, UploadFileExtendedRequest.class, "POST");
        }
        throw new InvalidFileLengthException("The content length must be set to the size of the file.");
    }

    public <T extends INode> CloudDriveOperation<T> newUploadFileExtendedV2Operation(UploadFileExtendedRequest uploadFileExtendedRequest, ProgressListener progressListener, JsonDeserializer<T> jsonDeserializer, ServiceExceptionProvider serviceExceptionProvider) {
        RequestAssertUtils.assertNotNull(uploadFileExtendedRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(uploadFileExtendedRequest.getName(), "A name must be provided when creating a node.");
        RequestAssertUtils.assertNotNullOrEmpty(uploadFileExtendedRequest.getKind(), "A node kind must be provided when creating a node.");
        RequestAssertUtils.assertNotNull(uploadFileExtendedRequest.getInputStream(), "An InputStream must be provided when creating a file node.");
        if (uploadFileExtendedRequest.getContentLength() > 0) {
            RequestPathGenerator.RequestPath createContentEndpointRequestPath = this.mRequestPathGenerator.createContentEndpointRequestPath("v2/upload", "V2");
            createContentEndpointRequestPath.addParameter("localId", uploadFileExtendedRequest.getLocalId());
            createContentEndpointRequestPath.addParameter("contentDate", uploadFileExtendedRequest.getContentDate());
            createContentEndpointRequestPath.addParameter("addToFamilyArchive", Boolean.toString(uploadFileExtendedRequest.addToFamilyArchive()));
            createContentEndpointRequestPath.addParameter("name", uploadFileExtendedRequest.getName());
            createContentEndpointRequestPath.addParameter("fileSize", Long.toString(uploadFileExtendedRequest.getContentLength()));
            createContentEndpointRequestPath.addParameter(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, uploadFileExtendedRequest.getKind());
            String suppress = uploadFileExtendedRequest.getSuppress();
            if (suppress != null && !suppress.isEmpty()) {
                createContentEndpointRequestPath.addParameter("suppress", suppress.toUpperCase());
            }
            if (uploadFileExtendedRequest.getContentSignatureType() != null && !uploadFileExtendedRequest.getContentSignatureType().isEmpty()) {
                createContentEndpointRequestPath.addParameter("contentSignatureType", uploadFileExtendedRequest.getContentSignatureType());
            }
            if (uploadFileExtendedRequest.getContentSignature() != null && !uploadFileExtendedRequest.getContentSignature().isEmpty()) {
                createContentEndpointRequestPath.addParameter("contentSignature", uploadFileExtendedRequest.getContentSignature());
            }
            return new UploadFileOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, new UploadRequestBodyWriterV2(uploadFileExtendedRequest, progressListener), jsonDeserializer, serviceExceptionProvider, createContentEndpointRequestPath, "uploadFileV2", this.mMetricListener, UploadFileExtendedRequest.class, "POST");
        }
        throw new InvalidFileLengthException("The content length must be set to the size of the file.");
    }

    public CloudDriveOperation<ViewGroupResponse> newViewGroupOperation(ViewGroupRequest viewGroupRequest) {
        RequestAssertUtils.assertNotNull(viewGroupRequest, "The request cannot be null.");
        RequestAssertUtils.assertNotNullOrEmpty(viewGroupRequest.getGroupId(), "The groupId cannot be null or empty.");
        RequestAssertUtils.assertNotNullOrEmpty(viewGroupRequest.getMemberId(), "The memberId cannot be null or empty.");
        ExtendedRequestPathGenerator extendedRequestPathGenerator = this.mRequestPathGenerator;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("groups/");
        outline107.append(viewGroupRequest.getGroupId());
        outline107.append("/members/");
        outline107.append(viewGroupRequest.getMemberId());
        outline107.append("/views");
        RequestPathGenerator.RequestPath createGroupsEndpointRequestPath = extendedRequestPathGenerator.createGroupsEndpointRequestPath(outline107.toString());
        createGroupsEndpointRequestPath.addParameter(PROMPTO_FEATURE_FLAGS_PARAM, PromptoFeatureFlag.DEPRECATE_GROUP_OWNEDBY.toString());
        return new CloudDriveMethodOperation(this, this.mClientConfiguration, this.mAccountConfiguration, this.mSourceInfoGenerator, "POST", createGroupsEndpointRequestPath, ViewGroupResponseDeserializer.INSTANCE, "viewGroup", this.mMetricListener, ViewGroupRequest.class);
    }
}
