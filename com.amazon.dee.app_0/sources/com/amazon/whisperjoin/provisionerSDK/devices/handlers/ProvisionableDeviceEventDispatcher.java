package com.amazon.whisperjoin.provisionerSDK.devices.handlers;

import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DeviceEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.EventHandler;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.data.ExceptionData;
import com.amazon.whisperjoin.provisionerSDK.utility.Observers;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ProvisionableDeviceEventDispatcher implements EventHandler<DeviceEvent> {
    private static final String TAG = "com.amazon.whisperjoin.provisionerSDK.devices.handlers.ProvisionableDeviceEventDispatcher";
    private final WhisperJoinPeripheralDeviceDetails mDeviceDetails;
    private final Observers<ProvisioningEvent<DeviceEvent>> mDeviceObservers;

    /* renamed from: com.amazon.whisperjoin.provisionerSDK.devices.handlers.ProvisionableDeviceEventDispatcher$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent = new int[DeviceEvent.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.NETWORK_STATE_UPDATED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.NETWORK_SCAN_COMPLETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.REGISTRATION_STATE_UPDATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.PROVISIONING_DONE_SUCCESS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.PROVISIONING_DONE_FAILURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public ProvisionableDeviceEventDispatcher(Observers<ProvisioningEvent<DeviceEvent>> observers, WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        if (observers != null) {
            if (whisperJoinPeripheralDeviceDetails != null) {
                this.mDeviceObservers = observers;
                this.mDeviceDetails = whisperJoinPeripheralDeviceDetails;
                return;
            }
            throw new IllegalArgumentException("deviceDetails must not be null");
        }
        throw new IllegalArgumentException("deviceObservers must not be null");
    }

    private void handleMessageProcessingException(Exception exc) {
        invokeEvent(new ProvisioningEvent<>(DeviceEvent.MESSAGE_PROCESSING_EXCEPTION, new ExceptionData(null, exc)));
    }

    private void invokeEvent(ProvisioningEvent<DeviceEvent> provisioningEvent) {
        this.mDeviceObservers.invoke(this.mDeviceDetails, provisioningEvent);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.events.EventHandler
    public boolean handleEvent(ProvisioningEvent<DeviceEvent> provisioningEvent) {
        if (provisioningEvent != null) {
            int ordinal = provisioningEvent.getEvent().ordinal();
            if (ordinal == 0) {
                if (provisioningEvent.getEventObject() == null) {
                    handleMessageProcessingException(new NullPointerException("Wifi Connection Details can not be null"));
                    return true;
                }
                invokeEvent(provisioningEvent);
                return true;
            } else if (ordinal == 1) {
                if (provisioningEvent.getEventObject() != null) {
                    handleMessageProcessingException(new NullPointerException("Wifi Scan Result can not be null"));
                    return true;
                }
                invokeEvent(provisioningEvent);
                return true;
            } else if (ordinal == 2) {
                if (provisioningEvent.getEventObject() == null) {
                    handleMessageProcessingException(new NullPointerException("Registration Details can not be null"));
                    return true;
                }
                invokeEvent(provisioningEvent);
                return true;
            } else if (ordinal == 3) {
                invokeEvent(provisioningEvent);
                return true;
            } else if (ordinal == 4) {
                invokeEvent(provisioningEvent);
                return true;
            } else if (ordinal == 5) {
                if (provisioningEvent.getEventObject() == null) {
                    handleMessageProcessingException(new IllegalArgumentException("Unexpected data with disconnection event"));
                    return true;
                }
                invokeEvent(provisioningEvent);
                return true;
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected Device Event ");
                outline107.append(provisioningEvent.getEvent());
                throw new IllegalStateException(outline107.toString());
            }
        }
        throw new IllegalArgumentException("Event can't be null");
    }
}
