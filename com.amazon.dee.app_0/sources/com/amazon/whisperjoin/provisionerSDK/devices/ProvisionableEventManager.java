package com.amazon.whisperjoin.provisionerSDK.devices;

import android.util.SparseArray;
import com.amazon.whisperbridge.Transport;
import com.amazon.whisperbridge.constants.Command;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.CBLRegistrationUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableCommandInterfaceUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventInterfaceUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventNotification;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventType;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEvents;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisioningDoneFailureEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.VisibleNetworksUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.WifiConnectionUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisioningFailure;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DeviceEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.EventHandler;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.reflect.TypeToken;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
/* loaded from: classes13.dex */
public class ProvisionableEventManager implements Transport.ProvisionableEventNotificationCallback {
    private static final String TAG = "ProvisionableEventManager";
    private final EventHandler<DeviceEvent> mDeviceEventCallback;
    private final ExecutorService mExecutorService;
    private final PeripheralDevice mPeripheralDevice;
    private final Set<UUID> mSupportedEventUUIDs = new HashSet();
    private final SparseArray<UUID> mEventTypeKeyToUUIDMap = new SparseArray<>();
    private final Map<UUID, EventCommandInfo> mAutomaticEventRetrievalTypes = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class EventCommandInfo {
        private final Command mCommand;
        private final TypeToken mCommandReturnTypeToken;

        public EventCommandInfo(Command command, TypeToken typeToken) {
            this.mCommand = command;
            this.mCommandReturnTypeToken = typeToken;
        }

        public Command getCommand() {
            return this.mCommand;
        }

        public TypeToken getCommandReturnTypeToken() {
            return this.mCommandReturnTypeToken;
        }
    }

    public ProvisionableEventManager(Collection<ProvisionableEventType> collection, PeripheralDevice peripheralDevice, ExecutorService executorService, EventHandler<DeviceEvent> eventHandler) {
        this.mPeripheralDevice = peripheralDevice;
        this.mExecutorService = executorService;
        this.mDeviceEventCallback = eventHandler;
        initializeReservedEvents();
        initializeDynamicEvents(collection);
    }

    private void enableAutoRetrievalForEvent(UUID uuid, Command command, TypeToken typeToken) {
        if (!this.mSupportedEventUUIDs.contains(uuid)) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to register unsupported UUID: ");
            outline107.append(uuid.toString());
            outline107.append(" with command: ");
            outline107.append(command.toString());
            outline107.append(" for TypeToken: ");
            outline107.append(typeToken.toString());
            WJLog.w(str, outline107.toString());
            return;
        }
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Registered UUID: ");
        outline1072.append(uuid.toString());
        outline1072.append(" with command: ");
        outline1072.append(command.toString());
        outline1072.append(" for token TypeToken: ");
        outline1072.append(typeToken.toString());
        WJLog.v(str2, outline1072.toString());
        this.mAutomaticEventRetrievalTypes.put(uuid, new EventCommandInfo(command, typeToken));
    }

    private void initializeDynamicEvents(Collection<ProvisionableEventType> collection) {
        for (ProvisionableEventType provisionableEventType : collection) {
            WJLog.d(TAG, String.format(Locale.ENGLISH, "Adding Support Event UUID: %s, Event Type Key: %d", provisionableEventType.getEventUuid().toString(), Integer.valueOf(provisionableEventType.getEventType())));
            this.mSupportedEventUUIDs.add(provisionableEventType.getEventUuid());
            this.mEventTypeKeyToUUIDMap.put(provisionableEventType.getEventType(), provisionableEventType.getEventUuid());
        }
        enableAutoRetrievalForEvent(ProvisionableEvents.WIFI_CONNECTION_DETAILS_UPDATED_EVENT_UUID, Command.GET_CONNECTION_STATUS, TypeToken.of(WifiConnectionDetails.class));
        enableAutoRetrievalForEvent(ProvisionableEvents.CBL_REGISTRATION_DETAILS_UPDATED_EVENT_UUID, Command.GET_REGISTRATION_INFORMATION, TypeToken.of(CBLRegistrationDetails.class));
        enableAutoRetrievalForEvent(ProvisionableEvents.PROVISIONING_DONE_FAILURE_EVENT_UUID, Command.GET_PROVISIONING_FAILURE_CAUSE, TypeToken.of(ProvisioningFailure.class));
    }

    private void initializeReservedEvents() {
        this.mSupportedEventUUIDs.add(ProvisionableEvents.PROVISIONING_COMMAND_INTERFACE_UPDATED_EVENT_UUID);
        this.mEventTypeKeyToUUIDMap.put(0, ProvisionableEvents.PROVISIONING_COMMAND_INTERFACE_UPDATED_EVENT_UUID);
        enableAutoRetrievalForEvent(ProvisionableEvents.PROVISIONING_COMMAND_INTERFACE_UPDATED_EVENT_UUID, Command.GET_NOTIFICATION_EVENT_DATA, TypeToken.of(ProvisionableEventInterfaceUpdatedEvent.class));
        this.mSupportedEventUUIDs.add(ProvisionableEvents.PROVISIONING_EVENT_INTERFACE_UPDATED_EVENT_UUID);
        this.mEventTypeKeyToUUIDMap.put(1, ProvisionableEvents.PROVISIONING_EVENT_INTERFACE_UPDATED_EVENT_UUID);
        enableAutoRetrievalForEvent(ProvisionableEvents.PROVISIONING_EVENT_INTERFACE_UPDATED_EVENT_UUID, Command.GET_NOTIFICATION_EVENT_DATA, TypeToken.of(ProvisionableEventInterfaceUpdatedEvent.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeDeviceEvent(ProvisionableEvent provisionableEvent) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invoking device event ");
        outline107.append(provisionableEvent.getUuid());
        WJLog.d(str, outline107.toString());
        if (provisionableEvent instanceof ProvisionableCommandInterfaceUpdatedEvent) {
            WJLog.d(TAG, "Command Interface Updated");
        } else if (provisionableEvent instanceof ProvisionableEventInterfaceUpdatedEvent) {
            WJLog.d(TAG, "Event Interface Updated");
        } else if (provisionableEvent instanceof VisibleNetworksUpdatedEvent) {
            WJLog.d(TAG, "Visible Networks Updated");
            this.mDeviceEventCallback.handleEvent(new ProvisioningEvent<>(DeviceEvent.NETWORK_SCAN_COMPLETE, provisionableEvent));
        } else if (provisionableEvent instanceof WifiConnectionUpdatedEvent) {
            WJLog.d(TAG, "Wifi Connected Updated");
            this.mDeviceEventCallback.handleEvent(new ProvisioningEvent<>(DeviceEvent.NETWORK_STATE_UPDATED, provisionableEvent));
        } else if (provisionableEvent instanceof CBLRegistrationUpdatedEvent) {
            WJLog.d(TAG, "CBL Registration Updated");
            this.mDeviceEventCallback.handleEvent(new ProvisioningEvent<>(DeviceEvent.REGISTRATION_STATE_UPDATED, provisionableEvent));
        } else if (!(provisionableEvent instanceof ProvisioningDoneFailureEvent)) {
        } else {
            WJLog.d(TAG, "Provisioning Done Failure Event");
            this.mDeviceEventCallback.handleEvent(new ProvisioningEvent<>(DeviceEvent.PROVISIONING_DONE_FAILURE, provisionableEvent));
        }
    }

    @Override // com.amazon.whisperbridge.Transport.ProvisionableEventNotificationCallback
    public void onProvisionableEventNotification(ProvisionableEventNotification provisionableEventNotification) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("New event notification ");
        outline107.append(provisionableEventNotification.getEventType());
        outline107.append(" ");
        outline107.append(provisionableEventNotification.getEventKey());
        WJLog.d(str, outline107.toString());
        UUID uuid = this.mEventTypeKeyToUUIDMap.get(provisionableEventNotification.getEventType());
        if (uuid == null) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Received unsupported event notification for type ");
            outline1072.append(provisionableEventNotification.getEventType());
            WJLog.w(str2, outline1072.toString());
            return;
        }
        String str3 = TAG;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Handling Event Notification UUID: ");
        outline1073.append(uuid.toString());
        WJLog.d(str3, outline1073.toString());
        if (ProvisionableEvents.PROVISIONING_DONE_SUCESS_EVENT_UUID.equals(uuid)) {
            this.mDeviceEventCallback.handleEvent(new ProvisioningEvent<>(DeviceEvent.PROVISIONING_DONE_SUCCESS, null));
        } else if (ProvisionableEvents.WIFI_VISIBLE_NETWORKS_UPDATED_EVENT_UUID.equals(uuid)) {
            this.mDeviceEventCallback.handleEvent(new ProvisioningEvent<>(DeviceEvent.NETWORK_SCAN_COMPLETE, null));
        } else if (!this.mAutomaticEventRetrievalTypes.containsKey(uuid)) {
            String str4 = TAG;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Automatic Retrieval not enabled for event UUID ");
            outline1074.append(uuid.toString());
            WJLog.w(str4, outline1074.toString());
        } else {
            final EventCommandInfo eventCommandInfo = this.mAutomaticEventRetrievalTypes.get(uuid);
            this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.ProvisionableEventManager.1
                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    ProvisionableEvent provisionableEvent;
                    try {
                        Object executeOperation = ProvisionableEventManager.this.mPeripheralDevice.executeOperation(eventCommandInfo.getCommand(), null, eventCommandInfo.getCommandReturnTypeToken());
                        if (executeOperation instanceof WifiConnectionDetails) {
                            WJLog.d(ProvisionableEventManager.TAG, "Retrieved WifiConnectionDetails");
                            provisionableEvent = new WifiConnectionUpdatedEvent((WifiConnectionDetails) executeOperation);
                        } else if (executeOperation instanceof CBLRegistrationDetails) {
                            WJLog.d(ProvisionableEventManager.TAG, "Retrieved CBLRegistrationDetails");
                            provisionableEvent = new CBLRegistrationUpdatedEvent((CBLRegistrationDetails) executeOperation);
                        } else if (executeOperation instanceof ProvisioningFailure) {
                            WJLog.d(ProvisionableEventManager.TAG, "Retrieved ProvisioningFailureCause");
                            provisionableEvent = new ProvisioningDoneFailureEvent((ProvisioningFailure) executeOperation);
                        } else {
                            try {
                                provisionableEvent = (ProvisionableEvent) executeOperation;
                            } catch (ClassCastException e) {
                                WJLog.e(ProvisionableEventManager.TAG, "Unknown return object", e);
                                throw new IllegalArgumentException("Unknown return object");
                            }
                        }
                        ProvisionableEventManager.this.invokeDeviceEvent(provisionableEvent);
                        return null;
                    } catch (Exception e2) {
                        WJLog.e(ProvisionableEventManager.TAG, "Execute operation failed", e2);
                        throw e2;
                    }
                }
            });
        }
    }
}
