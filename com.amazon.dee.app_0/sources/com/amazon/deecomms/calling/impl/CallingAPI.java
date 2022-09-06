package com.amazon.deecomms.calling.impl;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.alexa.CommsDirectiveHandler;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.api.CallInfo;
import com.amazon.deecomms.calling.api.CallRequest;
import com.amazon.deecomms.calling.api.CallStateListener;
import com.amazon.deecomms.calling.api.CallTarget;
import com.amazon.deecomms.calling.api.ContactCallTarget;
import com.amazon.deecomms.calling.api.DeviceCallTarget;
import com.amazon.deecomms.calling.api.GroupCallTarget;
import com.amazon.deecomms.calling.api.HistoricalCall;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.deecomms.calling.api.RawAddressTarget;
import com.amazon.deecomms.calling.api.ResponseCallback;
import com.amazon.deecomms.calling.api.enums.CallMediaStream;
import com.amazon.deecomms.calling.api.exceptions.CallException;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.initiation.InitiationLogicContract;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.phonecallcontroller.DriveModeCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.EventBusEventType;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.contacts.model.PhoneNumberType;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.nativemodules.model.Person;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CallingAPI implements ICallingAPI {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallingAPI.class);
    @Nullable
    private final CallHelper callHelper;
    @NonNull
    private final CallInitiator callInitiator;
    @NonNull
    private final CallingAPIMonitor callMonitor;
    @NonNull
    private final CommsAccessorySessionListener commsAccessorySessionListener;
    @NonNull
    private final CommsDirectiveHandler commsDirectiveHandler;
    @NonNull
    private final CommsIdentityManager commsIdentityManager;
    @NonNull
    private final ContactsDataStoreUtil contactsDataStoreUtil;
    @NonNull
    private final Context context;
    @NonNull
    private final DriveModeCallPermissionHandler driveModeCallPermissionHandler;
    @NonNull
    private final EventBus eventBus;
    @NonNull
    private final InitiationLogicFactory initiationLogicFactory;
    @NonNull
    private final MakeNativeCallHandler makeNativeCallHandler;
    @NonNull
    private final CallingAPIPopulator populator;
    @NonNull
    private final PushNotificationManager pushNotificationManager;

    public CallingAPI() {
        this(CommsDaggerWrapper.getComponent().getInitiationLogicFactory(), new CallInitiator(MetricKeys.CALL_INITIATED_FROM_API), new ContactsDataStoreUtil(CommsDaggerWrapper.getComponent().getContext()), CommsDaggerWrapper.getComponent().getCallingAPIMonitor(), CommsDaggerWrapper.getComponent().getCallingAPIPopulator(), CommsDaggerWrapper.getComponent().getContext(), CommsDaggerWrapper.getComponent().getCommsDirectiveHandler(), CommsDaggerWrapper.getComponent().getCommsIdentityManager(), CommsDaggerWrapper.getComponent().getPushNotificationManager(), CommsDaggerWrapper.getComponent().getCommsAccessorySessionListener(), CommsDaggerWrapper.getComponent().getMakeCallHandler(), CommsDaggerWrapper.getComponent().getDriveModeCallingPermissionHandler(), CommsDaggerWrapper.getComponent().getEventBus());
    }

    @NonNull
    private String getCommsId() {
        return this.commsIdentityManager.getCommsId("CallingAPI", false);
    }

    @NonNull
    private String getName(@NonNull String str) {
        String serverContactIdFromCommsId = this.contactsDataStoreUtil.getServerContactIdFromCommsId(str);
        if (serverContactIdFromCommsId == null) {
            GeneratedOutlineSupport.outline3("Cannot find serverId from commsId ", str, LOG);
            return "Unknown";
        }
        Person personByServerIdFromDatabase = this.contactsDataStoreUtil.getPersonByServerIdFromDatabase(serverContactIdFromCommsId);
        if (personByServerIdFromDatabase == null) {
            GeneratedOutlineSupport.outline3("Cannot find contact with serverId ", serverContactIdFromCommsId, LOG);
            return "Unknown";
        }
        return ContactUtils.getFullName(new FullContactName(personByServerIdFromDatabase.getName(), personByServerIdFromDatabase.getCompanyName()));
    }

    @Override // com.amazon.deecomms.calling.api.ICallingAPI
    public void answerCall(String str) {
        CallUtils.acceptCall(this.context, str);
    }

    @Override // com.amazon.deecomms.calling.api.ICallingAPI
    public void endCall(String str) {
        CallUtils.endActiveCall(this.context);
    }

    @Override // com.amazon.deecomms.calling.api.ICallingAPI
    public void getCallHistory(int i, ResponseCallback<List<HistoricalCall>> responseCallback) {
        new CallHistoryRetriever(i, responseCallback, getCommsId(), this.contactsDataStoreUtil).execute(new Void[0]);
    }

    @Override // com.amazon.deecomms.calling.api.ICallingAPI
    public CallInfo[] getCallInformation() {
        CallInfo currentCallInfo = this.populator.getCurrentCallInfo();
        return currentCallInfo == null ? new CallInfo[0] : new CallInfo[]{currentCallInfo};
    }

    @Override // com.amazon.deecomms.calling.api.ICallingAPI
    public void handleDirective(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.commsDirectiveHandler.handleDirective(str, str2, str3);
    }

    @Override // com.amazon.deecomms.calling.api.ICallingAPI
    public void handleNotification(@NonNull Bundle bundle) {
        this.pushNotificationManager.onPush(bundle);
    }

    @Override // com.amazon.deecomms.calling.api.ICallingAPI
    public void initiateCall(CallRequest callRequest, CallStateListener callStateListener) throws CallException {
        LOG.i("Received a request to start a call");
        InitiationLogicFactory initiationLogicFactory = this.initiationLogicFactory;
        CallInitiator callInitiator = this.callInitiator;
        Context context = this.context;
        CallHelper callHelper = this.callHelper;
        if (callHelper == null) {
            callHelper = new CallHelper();
        }
        InitiationLogicContract create = initiationLogicFactory.create(callInitiator, context, null, callHelper, callRequest.getClientMetricsInfo().getCallInitiationDomain(), callRequest.getClientMetricsInfo().getCallInitiationSourceScreen());
        boolean contains = Arrays.asList(callRequest.getSupportedMediaStreams()).contains(CallMediaStream.Video);
        CallTarget callTarget = callRequest.getCallTarget();
        if (callTarget instanceof ContactCallTarget) {
            ContactCallTarget contactCallTarget = (ContactCallTarget) callTarget;
            String name = getName(contactCallTarget.getId());
            if (contactCallTarget.isDropIn()) {
                LOG.i("Initiating a contact drop-in");
                create.initiateContactDropIn(contactCallTarget.getId(), name);
            } else if (contains) {
                LOG.i("Initiating a video call");
                create.initiateVideoCall(contactCallTarget.getId(), name);
            } else {
                LOG.i("Initiating an audio call");
                create.initiateAudioCall(contactCallTarget.getId(), name);
            }
        } else if (callTarget instanceof DeviceCallTarget) {
            DeviceCallTarget deviceCallTarget = (DeviceCallTarget) callTarget;
            String commsId = getCommsId();
            if (deviceCallTarget.isDropIn()) {
                LOG.i("Initiating a device drop-in");
                create.initiateDeviceDropIn(commsId, deviceCallTarget.getId(), "Device", contains, callRequest.getClientMetricsInfo().getCallInitiationSourceScreen());
            } else {
                LOG.i("Initiating a device call");
                create.initiateDeviceTargetedCall(commsId, "Device", deviceCallTarget.getId(), contains);
            }
        } else if (callTarget instanceof GroupCallTarget) {
            GroupCallTarget groupCallTarget = (GroupCallTarget) callTarget;
            if (groupCallTarget.isDropIn()) {
                LOG.i("Initiating a group drop-in");
                create.initiateGroupDropIn(groupCallTarget.getId());
            } else {
                LOG.i("Initiating a group drop-in");
                create.initiateGroupCall(groupCallTarget.getId());
            }
        } else if (callTarget instanceof RawAddressTarget) {
            RawAddressTarget rawAddressTarget = (RawAddressTarget) callTarget;
            if (this.commsAccessorySessionListener.isAnyAccessoryConnected()) {
                if (this.makeNativeCallHandler.initiateNativePhoneCall(rawAddressTarget.getRawAddress(), this.driveModeCallPermissionHandler, true).booleanValue()) {
                    publishHVAEvent(false, CallType.PSTN);
                }
            } else {
                create.initiateCoboCall(null, "", new ContactPhoneNumber(PhoneNumberType.Main, rawAddressTarget.getRawAddress()));
            }
        } else {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("callTarget is of invalid type: ");
            outline1.append(callTarget.getClass().getName());
            throw new IllegalArgumentException(outline1.toString());
        }
        LOG.i("Registering call state listener");
        this.callMonitor.addListener(callStateListener);
    }

    @VisibleForTesting
    void publishHVAEvent(boolean z, CallType callType) {
        EventBusEventType eventBusEventType;
        try {
            if (z) {
                eventBusEventType = callType.isDropIn() ? EventBusEventType.HVA_DROP_IN_END : EventBusEventType.HVA_ALEXA_CALL_END;
            } else {
                eventBusEventType = callType.isDropIn() ? EventBusEventType.HVA_DROP_IN_START : EventBusEventType.HVA_ALEXA_CALL_START;
            }
            this.eventBus.publish(new Message.Builder().setEventType(eventBusEventType.toString()).build());
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("published HVA event: ");
            sb.append(eventBusEventType.toString());
            commsLogger.i(sb.toString());
        } catch (Exception e) {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.e("failed to publish HVA event for callEnded=" + z + ", callType=" + callType, e);
        }
    }

    @Override // com.amazon.deecomms.calling.api.ICallingAPI
    public void rejectCall(String str) {
        CallUtils.rejectCall(this.context);
    }

    @Inject
    public CallingAPI(@NonNull InitiationLogicFactory initiationLogicFactory, @NonNull CallInitiator callInitiator, @NonNull ContactsDataStoreUtil contactsDataStoreUtil, @NonNull CallingAPIMonitor callingAPIMonitor, @NonNull CallingAPIPopulator callingAPIPopulator, @NonNull Context context, @NonNull CommsDirectiveHandler commsDirectiveHandler, @NonNull CommsIdentityManager commsIdentityManager, @NonNull PushNotificationManager pushNotificationManager, @NonNull CommsAccessorySessionListener commsAccessorySessionListener, @NonNull MakeNativeCallHandler makeNativeCallHandler, @NonNull DriveModeCallPermissionHandler driveModeCallPermissionHandler, @NonNull EventBus eventBus) {
        this.initiationLogicFactory = initiationLogicFactory;
        this.callInitiator = callInitiator;
        this.contactsDataStoreUtil = contactsDataStoreUtil;
        this.callMonitor = callingAPIMonitor;
        this.populator = callingAPIPopulator;
        this.context = context;
        this.commsDirectiveHandler = commsDirectiveHandler;
        this.pushNotificationManager = pushNotificationManager;
        this.commsIdentityManager = commsIdentityManager;
        this.commsAccessorySessionListener = commsAccessorySessionListener;
        this.makeNativeCallHandler = makeNativeCallHandler;
        this.driveModeCallPermissionHandler = driveModeCallPermissionHandler;
        this.eventBus = eventBus;
        this.callHelper = null;
    }

    @VisibleForTesting
    CallingAPI(@NonNull InitiationLogicFactory initiationLogicFactory, @NonNull CallInitiator callInitiator, @NonNull ContactsDataStoreUtil contactsDataStoreUtil, @NonNull CallingAPIMonitor callingAPIMonitor, @NonNull CallingAPIPopulator callingAPIPopulator, @NonNull Context context, @NonNull CommsDirectiveHandler commsDirectiveHandler, @NonNull CommsIdentityManager commsIdentityManager, @NonNull PushNotificationManager pushNotificationManager, @NonNull CommsAccessorySessionListener commsAccessorySessionListener, @NonNull MakeNativeCallHandler makeNativeCallHandler, @NonNull DriveModeCallPermissionHandler driveModeCallPermissionHandler, @NonNull EventBus eventBus, @NonNull CallHelper callHelper) {
        this.initiationLogicFactory = initiationLogicFactory;
        this.callInitiator = callInitiator;
        this.contactsDataStoreUtil = contactsDataStoreUtil;
        this.callMonitor = callingAPIMonitor;
        this.populator = callingAPIPopulator;
        this.context = context;
        this.commsDirectiveHandler = commsDirectiveHandler;
        this.pushNotificationManager = pushNotificationManager;
        this.commsIdentityManager = commsIdentityManager;
        this.commsAccessorySessionListener = commsAccessorySessionListener;
        this.makeNativeCallHandler = makeNativeCallHandler;
        this.driveModeCallPermissionHandler = driveModeCallPermissionHandler;
        this.eventBus = eventBus;
        this.callHelper = callHelper;
    }
}
