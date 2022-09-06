package com.amazon.alexa.accessoryclient.common.query;

import android.os.Bundle;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.query.request.AccessoryRequest;
import com.amazon.alexa.accessoryclient.common.query.request.AtCommandRequest;
import com.amazon.alexa.accessoryclient.common.query.request.CallInfoRequest;
import com.amazon.alexa.accessoryclient.common.query.request.CloudPairingSeedRequest;
import com.amazon.alexa.accessoryclient.common.query.request.CreateSessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.DeviceAccountRequest;
import com.amazon.alexa.accessoryclient.common.query.request.DeviceGroupRequest;
import com.amazon.alexa.accessoryclient.common.query.request.DiagnosticsRequest;
import com.amazon.alexa.accessoryclient.common.query.request.DownloadPackageRequest;
import com.amazon.alexa.accessoryclient.common.query.request.IdentityRequest;
import com.amazon.alexa.accessoryclient.common.query.request.InventoryUpdateRequest;
import com.amazon.alexa.accessoryclient.common.query.request.LongRequest;
import com.amazon.alexa.accessoryclient.common.query.request.MediaControlRequest;
import com.amazon.alexa.accessoryclient.common.query.request.NotificationWithContentRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanStringRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionByteArrayRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionIntListRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionLocaleRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionStringIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionStringRequest;
import com.amazon.alexa.accessoryclient.common.query.request.ReplaceCloudPairingKeysRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetAudiogramRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetCloudPairingKeysRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetFitnessSessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetInputConfigurationRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetMediaEnhancementCorrectionAmountRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetPlaybackStatusRequest;
import com.amazon.alexa.accessoryclient.common.query.request.StateRequest;
import com.amazon.alexa.accessoryclient.common.query.request.UpdateRequestRequest;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryDataBeaconResultResponse;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryInquiryResultResponse;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryListResponse;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryResponse;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryScanResultResponse;
import com.amazon.alexa.accessoryclient.common.query.response.AudiogramResponse;
import com.amazon.alexa.accessoryclient.common.query.response.BooleanResponse;
import com.amazon.alexa.accessoryclient.common.query.response.CblInformationResponse;
import com.amazon.alexa.accessoryclient.common.query.response.CblLoginStateResponse;
import com.amazon.alexa.accessoryclient.common.query.response.CloudPairingAttributesResponse;
import com.amazon.alexa.accessoryclient.common.query.response.CloudPairingStatusResponse;
import com.amazon.alexa.accessoryclient.common.query.response.CompletableQueryResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceAccountResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceConfigurationResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceFeaturesResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceGroupListResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceGroupResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceInformationSetResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceRegistrationResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceRegistrationSetResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DiagnosticsResponse;
import com.amazon.alexa.accessoryclient.common.query.response.ErrorCodeResponse;
import com.amazon.alexa.accessoryclient.common.query.response.ExecuteNotificationActionResponse;
import com.amazon.alexa.accessoryclient.common.query.response.FirmwareInformationSetResponse;
import com.amazon.alexa.accessoryclient.common.query.response.FirmwareUpdateStatusResponse;
import com.amazon.alexa.accessoryclient.common.query.response.FitnessDataAvailableNotificationResponse;
import com.amazon.alexa.accessoryclient.common.query.response.FitnessDataSourceResponse;
import com.amazon.alexa.accessoryclient.common.query.response.FitnessSessionUpdateMetadataResponse;
import com.amazon.alexa.accessoryclient.common.query.response.I18nConfigResponse;
import com.amazon.alexa.accessoryclient.common.query.response.InputBehaviorConfigurationSetResponse;
import com.amazon.alexa.accessoryclient.common.query.response.InventoryUpdateBundleSetResponse;
import com.amazon.alexa.accessoryclient.common.query.response.InventoryUpdateResponse;
import com.amazon.alexa.accessoryclient.common.query.response.LiveFitnessDataResponse;
import com.amazon.alexa.accessoryclient.common.query.response.LocalesResponse;
import com.amazon.alexa.accessoryclient.common.query.response.MediaControlResponse;
import com.amazon.alexa.accessoryclient.common.query.response.MediaEnhancementCorrectionAmountResponse;
import com.amazon.alexa.accessoryclient.common.query.response.QueryConnectionStatusResponse;
import com.amazon.alexa.accessoryclient.common.query.response.RegisterForMediaEventsResponse;
import com.amazon.alexa.accessoryclient.common.query.response.StateResponse;
import com.amazon.alexa.accessoryclient.common.query.response.StopLiveFitnessDataResponse;
import com.amazon.alexa.accessoryclient.common.query.response.StringResponse;
import com.amazon.alexa.accessoryclient.common.query.response.TransportChangedResponse;
import com.amazon.alexa.accessoryclient.common.query.response.UpdateRequestResponse;
import com.amazon.alexa.accessoryclient.common.query.response.UsersResponse;
import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ApiIdentifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\bm\b\u0086\u0001\u0018\u0000 x2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002wxB/\b\u0002\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00060\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0016R\u001d\u0010\u0002\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\u0005\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\buj\u0002\bv¨\u0006y"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;", "", "requestClass", "Ljava/lang/Class;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "responseClass", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "(Ljava/lang/String;ILjava/lang/Class;Ljava/lang/Class;)V", "getRequestClass", "()Ljava/lang/Class;", "getResponseClass", "toString", "", "QUERY_COMPLETABLE_TEST", "QUERY_SINGLE_TEST", "QUERY_MAYBE_TEST", "QUERY_OBSERVABLE_TEST", "QUERY_CONNECTION_STATUS", "QUERY_CONNECTED_ACCESSORY", "RELEASE_SESSION", "LINK_ACCESSORY", "UNLINK_ACCESSORY", "OBSERVE_SESSION_CREATED", "OBSERVE_SESSION_CONNECTED", "OBSERVE_SESSION_FAILED", "OBSERVE_SESSION_DISCONNECTED", "OBSERVE_SESSION_RELEASED", "OBSERVE_SESSION_TRANSPORT_CHANGED", "GET_ACTIVE_ACCESSORIES", "CREATE_AND_CONNECT_SESSION", "CREATE_AND_CONNECT_SESSION_WITH_OPTIONS", "CREATE_AND_CONNECT_SESSION_AWAIT_CONNECTION", "CREATE_AND_CONNECT_SESSION_WITH_OPTIONS_AWAIT_CONNECTION", "SET_STATE", "QUERY_STATE", "REQUEST_OVERRIDE_ASSISTANT", "REQUEST_COMPLETE_SETUP", "QUERY_DEVICE_CONFIGURATION", "REQUEST_UPDATE_DEVICE_INFORMATION", "QUERY_DEVICE_INFORMATION_SET", "REQUEST_START_SETUP", "QUERY_DEVICE_FEATURES", "QUERY_DEVICE_GROUPS", "REMOVE_DEVICE_GROUP", "REMOVE_DEVICE_GROUP_BY_DEVICE_GROUP", "GET_DEVICE_GROUP", "HAS_DEVICE_GROUP", "UPDATE_DEVICE_GROUP", "ADD_DEVICE_GROUP", "QUERY_FIRMWARE_INFORMATION_SET", "QUERY_FIRMWARE_UPDATE_STATUS", "QUERY_INVENTORY_UPDATE_SET", "INITIATE_FIRMWARE_TRANSFER", "OBSERVE_BLE_ACCESSORY_FOUND_NEARBY", "OBSERVE_BLE_ACCESSORY_DATA_BEACON_FOUND_NEARBY", "OBSERVE_CONNECTED_ACCESSORY_FOUND", "OBSERVE_CONNECTED_ACCESSORY_LOST", "GET_DEVICE_REGISTRATION", "GET_OR_CREATE_DEVICE_REGISTRATION", "DEREGISTER", "QUERY_REGISTRATIONS", "QUERY_INPUT_CONFIGURATION", "SET_INPUT_CONFIGURATION", "RESET_INPUT_CONFIGURATION", "SHOULD_UPGRADE_TRANSPORT", "REQUEST_TRANSPORT_UPGRADE", "REQUEST_COMPANION_DEVICE", "QUERY_NEW_COMPANION_DEVICES", "QUERY_REMOVED_COMPANION_DEVICES", "IS_COMPANION_DEVICE", "REMOVE_COMPANION_DEVICE", "QUERY_USERS", "CONNECT_USER", "DISCONNECT_USER", "UNPAIR_USER", "REQUEST_RESET_CONNECTION", "QUERY_LOCALES", "SET_LOCALE", "QUERY_IS_AWAITING_DERIVED_KEYS", "GENERATE_UPDATE_REQUEST", "GET_AVAILABLE_INVENTORY_UPDATE_REQUEST", "DOWNLOAD_PACKAGE_REQUEST", "FORWARD_AT_COMMAND", "GET_FITNESS_DATA", "GET_FITNESS_DATA_WITH_TOKEN", "OBSERVE_FITNESS_SESSION_UPDATES", "FITNESS_UPDATE_PROCESSED_INTERNAL", "SET_FITNESS_SESSION", "START_LIVE_FITNESS_DATA", "STOP_LIVE_FITNESS_DATA", "OBSERVE_LIVE_FITNESS_DATA_NOTIFICATIONS", "OBSERVE_STOP_LIVE_FITNESS_DATA_NOTIFICATIONS", "OBSERVE_FITNESS_DATA_AVAILABLE_NOTIFICATIONS", "FETCH_AND_STORE_DEVICE_ACCOUNT", "GET_DEVICE_ACCOUNT", "GET_DEVICE_IDENTIFIER", "DELETE_DEVICE_ACCOUNTS", "QUERY_DIAGNOSTICS", "ISSUE_MEDIA_CONTROL", "SET_PLAYBACK_STATUS", "QUERY_MEDIA_CONTROL", "QUERY_REGISTER_FOR_MEDIA_EVENTS", "GET_AUDIOGRAM", "SET_AUDIOGRAM", "GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT", "SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT", "QUERY_CBL_LOGIN_STATE", "REQUEST_CBL_INFORMATION", "UPDATE_CALL_INFO", "ADD_OUTGOING_NOTIFICATION", "UPDATE_OUTGOING_NOTIFICATION", "REMOVE_OUTGOING_NOTIFICATION", "QUERY_ACTION_COMMANDS_FOR_OUTGOING_NOTIFICATIONS", "QUERY_I18N_CONFIG", "GET_CLOUD_PAIRING_STATUS", "GET_CLOUD_PAIRING_ATTRIBUTES", "SET_CLOUD_PAIRING_KEYS", "REPLACE_CLOUD_PAIRING_KEYS", "REMOVE_CLOUD_PAIRING_KEYS", "ApiRequest", "Companion", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum ApiIdentifier {
    QUERY_COMPLETABLE_TEST(QuerySessionRequest.class, CompletableQueryResponse.class),
    QUERY_SINGLE_TEST(QuerySessionRequest.class, QueryConnectionStatusResponse.class),
    QUERY_MAYBE_TEST(QuerySessionRequest.class, QueryConnectionStatusResponse.class),
    QUERY_OBSERVABLE_TEST(QuerySessionRequest.class, QueryConnectionStatusResponse.class),
    QUERY_CONNECTION_STATUS(QuerySessionRequest.class, QueryConnectionStatusResponse.class),
    QUERY_CONNECTED_ACCESSORY(QuerySessionRequest.class, AccessoryResponse.class),
    RELEASE_SESSION(QuerySessionRequest.class, CompletableQueryResponse.class),
    LINK_ACCESSORY(AccessoryRequest.class, CompletableQueryResponse.class),
    UNLINK_ACCESSORY(AccessoryRequest.class, CompletableQueryResponse.class),
    OBSERVE_SESSION_CREATED(IdentityRequest.class, AccessoryResponse.class),
    OBSERVE_SESSION_CONNECTED(IdentityRequest.class, AccessoryResponse.class),
    OBSERVE_SESSION_FAILED(IdentityRequest.class, AccessoryResponse.class),
    OBSERVE_SESSION_DISCONNECTED(IdentityRequest.class, AccessoryResponse.class),
    OBSERVE_SESSION_RELEASED(IdentityRequest.class, AccessoryResponse.class),
    OBSERVE_SESSION_TRANSPORT_CHANGED(IdentityRequest.class, TransportChangedResponse.class),
    GET_ACTIVE_ACCESSORIES(IdentityRequest.class, AccessoryListResponse.class),
    CREATE_AND_CONNECT_SESSION(AccessoryRequest.class, CompletableQueryResponse.class),
    CREATE_AND_CONNECT_SESSION_WITH_OPTIONS(CreateSessionRequest.class, CompletableQueryResponse.class),
    CREATE_AND_CONNECT_SESSION_AWAIT_CONNECTION(AccessoryRequest.class, CompletableQueryResponse.class),
    CREATE_AND_CONNECT_SESSION_WITH_OPTIONS_AWAIT_CONNECTION(CreateSessionRequest.class, CompletableQueryResponse.class),
    SET_STATE(StateRequest.class, CompletableQueryResponse.class),
    QUERY_STATE(StateRequest.class, StateResponse.class),
    REQUEST_OVERRIDE_ASSISTANT(QuerySessionBooleanRequest.class, ErrorCodeResponse.class),
    REQUEST_COMPLETE_SETUP(QuerySessionBooleanRequest.class, ErrorCodeResponse.class),
    QUERY_DEVICE_CONFIGURATION(QuerySessionRequest.class, DeviceConfigurationResponse.class),
    REQUEST_UPDATE_DEVICE_INFORMATION(QuerySessionStringIntRequest.class, ErrorCodeResponse.class),
    QUERY_DEVICE_INFORMATION_SET(QuerySessionRequest.class, DeviceInformationSetResponse.class),
    REQUEST_START_SETUP(QuerySessionRequest.class, ErrorCodeResponse.class),
    QUERY_DEVICE_FEATURES(QuerySessionRequest.class, DeviceFeaturesResponse.class),
    QUERY_DEVICE_GROUPS(IdentityRequest.class, DeviceGroupListResponse.class),
    REMOVE_DEVICE_GROUP(LongRequest.class, CompletableQueryResponse.class),
    REMOVE_DEVICE_GROUP_BY_DEVICE_GROUP(DeviceGroupRequest.class, CompletableQueryResponse.class),
    GET_DEVICE_GROUP(QuerySessionRequest.class, DeviceGroupResponse.class),
    HAS_DEVICE_GROUP(QuerySessionRequest.class, BooleanResponse.class),
    UPDATE_DEVICE_GROUP(DeviceGroupRequest.class, CompletableQueryResponse.class),
    ADD_DEVICE_GROUP(DeviceGroupRequest.class, DeviceGroupResponse.class),
    QUERY_FIRMWARE_INFORMATION_SET(QuerySessionRequest.class, FirmwareInformationSetResponse.class),
    QUERY_FIRMWARE_UPDATE_STATUS(QuerySessionRequest.class, FirmwareUpdateStatusResponse.class),
    QUERY_INVENTORY_UPDATE_SET(QuerySessionBooleanRequest.class, InventoryUpdateBundleSetResponse.class),
    INITIATE_FIRMWARE_TRANSFER(QuerySessionRequest.class, CompletableQueryResponse.class),
    OBSERVE_BLE_ACCESSORY_FOUND_NEARBY(IdentityRequest.class, AccessoryScanResultResponse.class),
    OBSERVE_BLE_ACCESSORY_DATA_BEACON_FOUND_NEARBY(IdentityRequest.class, AccessoryDataBeaconResultResponse.class),
    OBSERVE_CONNECTED_ACCESSORY_FOUND(IdentityRequest.class, AccessoryInquiryResultResponse.class),
    OBSERVE_CONNECTED_ACCESSORY_LOST(IdentityRequest.class, AccessoryInquiryResultResponse.class),
    GET_DEVICE_REGISTRATION(QuerySessionRequest.class, DeviceRegistrationResponse.class),
    GET_OR_CREATE_DEVICE_REGISTRATION(QuerySessionRequest.class, DeviceRegistrationResponse.class),
    DEREGISTER(QuerySessionRequest.class, CompletableQueryResponse.class),
    QUERY_REGISTRATIONS(IdentityRequest.class, DeviceRegistrationSetResponse.class),
    QUERY_INPUT_CONFIGURATION(QuerySessionIntRequest.class, InputBehaviorConfigurationSetResponse.class),
    SET_INPUT_CONFIGURATION(SetInputConfigurationRequest.class, CompletableQueryResponse.class),
    RESET_INPUT_CONFIGURATION(QuerySessionIntRequest.class, CompletableQueryResponse.class),
    SHOULD_UPGRADE_TRANSPORT(QuerySessionRequest.class, BooleanResponse.class),
    REQUEST_TRANSPORT_UPGRADE(QuerySessionRequest.class, CompletableQueryResponse.class),
    REQUEST_COMPANION_DEVICE(QuerySessionRequest.class, BooleanResponse.class),
    QUERY_NEW_COMPANION_DEVICES(IdentityRequest.class, StringResponse.class),
    QUERY_REMOVED_COMPANION_DEVICES(IdentityRequest.class, StringResponse.class),
    IS_COMPANION_DEVICE(QuerySessionRequest.class, BooleanResponse.class),
    REMOVE_COMPANION_DEVICE(QuerySessionRequest.class, CompletableQueryResponse.class),
    QUERY_USERS(QuerySessionRequest.class, UsersResponse.class),
    CONNECT_USER(QuerySessionStringRequest.class, ErrorCodeResponse.class),
    DISCONNECT_USER(QuerySessionStringRequest.class, ErrorCodeResponse.class),
    UNPAIR_USER(QuerySessionStringRequest.class, ErrorCodeResponse.class),
    REQUEST_RESET_CONNECTION(QuerySessionBooleanIntRequest.class, ErrorCodeResponse.class),
    QUERY_LOCALES(QuerySessionRequest.class, LocalesResponse.class),
    SET_LOCALE(QuerySessionLocaleRequest.class, ErrorCodeResponse.class),
    QUERY_IS_AWAITING_DERIVED_KEYS(QuerySessionRequest.class, BooleanResponse.class),
    GENERATE_UPDATE_REQUEST(UpdateRequestRequest.class, UpdateRequestResponse.class),
    GET_AVAILABLE_INVENTORY_UPDATE_REQUEST(InventoryUpdateRequest.class, InventoryUpdateResponse.class),
    DOWNLOAD_PACKAGE_REQUEST(DownloadPackageRequest.class, CompletableQueryResponse.class),
    FORWARD_AT_COMMAND(AtCommandRequest.class, ErrorCodeResponse.class),
    GET_FITNESS_DATA(QuerySessionRequest.class, FitnessDataSourceResponse.class),
    GET_FITNESS_DATA_WITH_TOKEN(QuerySessionByteArrayRequest.class, FitnessDataSourceResponse.class),
    OBSERVE_FITNESS_SESSION_UPDATES(QuerySessionRequest.class, FitnessSessionUpdateMetadataResponse.class),
    FITNESS_UPDATE_PROCESSED_INTERNAL(QuerySessionBooleanStringRequest.class, CompletableQueryResponse.class),
    SET_FITNESS_SESSION(SetFitnessSessionRequest.class, CompletableQueryResponse.class),
    START_LIVE_FITNESS_DATA(QuerySessionIntListRequest.class, CompletableQueryResponse.class),
    STOP_LIVE_FITNESS_DATA(QuerySessionIntListRequest.class, CompletableQueryResponse.class),
    OBSERVE_LIVE_FITNESS_DATA_NOTIFICATIONS(QuerySessionRequest.class, LiveFitnessDataResponse.class),
    OBSERVE_STOP_LIVE_FITNESS_DATA_NOTIFICATIONS(QuerySessionRequest.class, StopLiveFitnessDataResponse.class),
    OBSERVE_FITNESS_DATA_AVAILABLE_NOTIFICATIONS(QuerySessionRequest.class, FitnessDataAvailableNotificationResponse.class),
    FETCH_AND_STORE_DEVICE_ACCOUNT(DeviceAccountRequest.class, DeviceAccountResponse.class),
    GET_DEVICE_ACCOUNT(QuerySessionStringRequest.class, DeviceAccountResponse.class),
    GET_DEVICE_IDENTIFIER(QuerySessionRequest.class, StringResponse.class),
    DELETE_DEVICE_ACCOUNTS(QuerySessionRequest.class, CompletableQueryResponse.class),
    QUERY_DIAGNOSTICS(DiagnosticsRequest.class, DiagnosticsResponse.class),
    ISSUE_MEDIA_CONTROL(MediaControlRequest.class, CompletableQueryResponse.class),
    SET_PLAYBACK_STATUS(SetPlaybackStatusRequest.class, CompletableQueryResponse.class),
    QUERY_MEDIA_CONTROL(QuerySessionRequest.class, MediaControlResponse.class),
    QUERY_REGISTER_FOR_MEDIA_EVENTS(QuerySessionRequest.class, RegisterForMediaEventsResponse.class),
    GET_AUDIOGRAM(QuerySessionIntRequest.class, AudiogramResponse.class),
    SET_AUDIOGRAM(SetAudiogramRequest.class, CompletableQueryResponse.class),
    GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT(QuerySessionIntRequest.class, MediaEnhancementCorrectionAmountResponse.class),
    SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT(SetMediaEnhancementCorrectionAmountRequest.class, CompletableQueryResponse.class),
    QUERY_CBL_LOGIN_STATE(QuerySessionRequest.class, CblLoginStateResponse.class),
    REQUEST_CBL_INFORMATION(QuerySessionRequest.class, CblInformationResponse.class),
    UPDATE_CALL_INFO(CallInfoRequest.class, ErrorCodeResponse.class),
    ADD_OUTGOING_NOTIFICATION(NotificationWithContentRequest.class, ErrorCodeResponse.class),
    UPDATE_OUTGOING_NOTIFICATION(NotificationWithContentRequest.class, ErrorCodeResponse.class),
    REMOVE_OUTGOING_NOTIFICATION(QuerySessionIntRequest.class, ErrorCodeResponse.class),
    QUERY_ACTION_COMMANDS_FOR_OUTGOING_NOTIFICATIONS(QuerySessionRequest.class, ExecuteNotificationActionResponse.class),
    QUERY_I18N_CONFIG(QuerySessionRequest.class, I18nConfigResponse.class),
    GET_CLOUD_PAIRING_STATUS(CloudPairingSeedRequest.class, CloudPairingStatusResponse.class),
    GET_CLOUD_PAIRING_ATTRIBUTES(QuerySessionRequest.class, CloudPairingAttributesResponse.class),
    SET_CLOUD_PAIRING_KEYS(SetCloudPairingKeysRequest.class, CompletableQueryResponse.class),
    REPLACE_CLOUD_PAIRING_KEYS(ReplaceCloudPairingKeysRequest.class, CompletableQueryResponse.class),
    REMOVE_CLOUD_PAIRING_KEYS(CloudPairingSeedRequest.class, CompletableQueryResponse.class);
    
    @NotNull
    private final Class<? extends Query.Request<?>> requestClass;
    @NotNull
    private final Class<? extends Query.Response<?>> responseClass;
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final ApiIdentifier[] values = values();

    /* compiled from: ApiIdentifier.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\u000e\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier$ApiRequest;", "", "apiIdentifier", "Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;", "queryRequest", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "(Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;)V", "requestBundle", "Landroid/os/Bundle;", "(Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;Landroid/os/Bundle;)V", "getApiIdentifier", "()Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;", "getRequestBundle", "()Landroid/os/Bundle;", "toBundle", "Companion", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ApiRequest {
        public static final Companion Companion = new Companion(null);
        @NotNull
        private static String REQUEST_API_IDENTIFIER_KEY = "api";
        @NotNull
        private static String REQUEST_BODY_KEY = "body";
        @NotNull
        private final ApiIdentifier apiIdentifier;
        @NotNull
        private final Bundle requestBundle;

        /* compiled from: ApiIdentifier.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier$ApiRequest$Companion;", "", "()V", "REQUEST_API_IDENTIFIER_KEY", "", "getREQUEST_API_IDENTIFIER_KEY", "()Ljava/lang/String;", "setREQUEST_API_IDENTIFIER_KEY", "(Ljava/lang/String;)V", "REQUEST_BODY_KEY", "getREQUEST_BODY_KEY", "setREQUEST_BODY_KEY", ADMConstants.EXTRA_FROM, "Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier$ApiRequest;", "bundle", "Landroid/os/Bundle;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final ApiRequest from(@NotNull Bundle bundle) {
                Intrinsics.checkParameterIsNotNull(bundle, "bundle");
                ApiIdentifier apiIdentifier = ApiIdentifier.Companion.getValues()[bundle.getInt(getREQUEST_API_IDENTIFIER_KEY())];
                Bundle bundle2 = bundle.getBundle(getREQUEST_BODY_KEY());
                if (bundle2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(REQUEST_BODY_KEY)!!");
                return new ApiRequest(apiIdentifier, bundle2, null);
            }

            @NotNull
            public final String getREQUEST_API_IDENTIFIER_KEY() {
                return ApiRequest.REQUEST_API_IDENTIFIER_KEY;
            }

            @NotNull
            public final String getREQUEST_BODY_KEY() {
                return ApiRequest.REQUEST_BODY_KEY;
            }

            public final void setREQUEST_API_IDENTIFIER_KEY(@NotNull String str) {
                Intrinsics.checkParameterIsNotNull(str, "<set-?>");
                ApiRequest.REQUEST_API_IDENTIFIER_KEY = str;
            }

            public final void setREQUEST_BODY_KEY(@NotNull String str) {
                Intrinsics.checkParameterIsNotNull(str, "<set-?>");
                ApiRequest.REQUEST_BODY_KEY = str;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public /* synthetic */ ApiRequest(ApiIdentifier apiIdentifier, Bundle bundle, DefaultConstructorMarker defaultConstructorMarker) {
            this(apiIdentifier, bundle);
        }

        @NotNull
        public final ApiIdentifier getApiIdentifier() {
            return this.apiIdentifier;
        }

        @NotNull
        public final Bundle getRequestBundle() {
            return this.requestBundle;
        }

        @NotNull
        public final Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt(REQUEST_API_IDENTIFIER_KEY, this.apiIdentifier.ordinal());
            bundle.putBundle(REQUEST_BODY_KEY, this.requestBundle);
            return bundle;
        }

        public ApiRequest(@NotNull ApiIdentifier apiIdentifier, @NotNull Query.Request<?> queryRequest) {
            Intrinsics.checkParameterIsNotNull(apiIdentifier, "apiIdentifier");
            Intrinsics.checkParameterIsNotNull(queryRequest, "queryRequest");
            boolean areEqual = Intrinsics.areEqual(queryRequest.getClass(), apiIdentifier.getRequestClass());
            Preconditions.precondition(areEqual, "queryRequest " + queryRequest + " is not the same class as apiIdentifier.requestClass " + apiIdentifier.getRequestClass());
            this.apiIdentifier = apiIdentifier;
            this.requestBundle = queryRequest.getBundle();
        }

        private ApiRequest(ApiIdentifier apiIdentifier, Bundle bundle) {
            this.apiIdentifier = apiIdentifier;
            this.requestBundle = bundle;
        }
    }

    /* compiled from: ApiIdentifier.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier$Companion;", "", "()V", "values", "", "Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;", "getValues", "()[Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;", "[Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ApiIdentifier[] getValues() {
            return ApiIdentifier.values;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    ApiIdentifier(Class cls, Class cls2) {
        this.requestClass = cls;
        this.responseClass = cls2;
    }

    @NotNull
    public final Class<? extends Query.Request<?>> getRequestClass() {
        return this.requestClass;
    }

    @NotNull
    public final Class<? extends Query.Response<?>> getResponseClass() {
        return this.responseClass;
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ApiIdentifier ");
        outline107.append(name());
        outline107.append(" (requestClass=");
        outline107.append(this.requestClass);
        outline107.append(", responseClass=");
        outline107.append(this.responseClass);
        outline107.append(')');
        return outline107.toString();
    }
}
