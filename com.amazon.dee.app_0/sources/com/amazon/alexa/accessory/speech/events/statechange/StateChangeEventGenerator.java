package com.amazon.alexa.accessory.speech.events.statechange;

import com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo;
import com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload;
import com.amazon.alexa.accessory.avsclient.presence.ConnectionState;
import com.amazon.alexa.accessory.avsclient.presence.DeviceInfo;
import com.amazon.alexa.accessory.avsclient.presence.PersonInfo;
import com.amazon.alexa.accessory.avsclient.presence.PresenceState;
import com.amazon.alexa.accessory.avsclient.presence.State;
import com.amazon.alexa.accessory.avsclient.utils.ISO8601TimeSupplier;
import com.amazon.alexa.accessory.avsclient.utils.JsonConverter;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.DeviceRegistrationRequestIdentifier;
import com.google.gson.JsonIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public class StateChangeEventGenerator {
    private static final String AH_CASE = "A21YKVRGQV9TET";
    private static final String ARMSTRONG_CASE = "A3HVREY4JWAZ6K";
    private static final Set<String> IGNORED_DEVICE_TYPES = new HashSet(Arrays.asList("AE9FIEPOC6D9B", "A3HVREY4JWAZ6K", "A21YKVRGQV9TET"));
    private static final String P_CASE = "AE9FIEPOC6D9B";
    private static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private final JsonConverter jsonConverter;
    private final ISO8601TimeSupplier timeSupplier;

    /* renamed from: com.amazon.alexa.accessory.speech.events.statechange.StateChangeEventGenerator$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Device$DevicePresence = new int[Device.DevicePresence.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Device$DevicePresence[Device.DevicePresence.DEVICE_PRESENCE_ACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Device$DevicePresence[Device.DevicePresence.DEVICE_PRESENCE_ACCESSIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Device$DevicePresence[Device.DevicePresence.DEVICE_PRESENCE_INACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public StateChangeEventGenerator(JsonConverter jsonConverter) {
        Preconditions.notNull(jsonConverter, "jsonConverter");
        this.jsonConverter = jsonConverter;
        this.timeSupplier = new ISO8601TimeSupplier(ZONED_DATE_TIME_FORMAT);
    }

    private PresenceState getPresenceState(boolean z, Device.DeviceInformation deviceInformation) {
        PresenceState presenceState;
        int ordinal = deviceInformation.getStatus().getPresence().ordinal();
        if (ordinal == 1) {
            presenceState = PresenceState.PRESENT;
        } else if (ordinal != 2 && ordinal != 3) {
            presenceState = PresenceState.UNKNOWN;
        } else {
            presenceState = PresenceState.ABSENT;
        }
        return (z || presenceState == PresenceState.UNKNOWN) ? presenceState : PresenceState.ABSENT;
    }

    public String generatePayload(List<Device.DeviceInformation> list, String str, boolean z, DeviceRegistration deviceRegistration, long j) throws JsonIOException {
        String time = this.timeSupplier.getTime(new Date(j));
        PersonInfo build = PersonInfo.builder().setActivePersonId(str).build();
        ArrayList arrayList = new ArrayList();
        ConnectionState connectionState = z ? ConnectionState.CONNECTED : ConnectionState.DISCONNECTED;
        DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier = deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier();
        for (Device.DeviceInformation deviceInformation : list) {
            PresenceState presenceState = getPresenceState(z, deviceInformation);
            State.Builder connectionState2 = State.builder().setConnectionState(connectionState);
            if (presenceState != PresenceState.UNKNOWN && !IGNORED_DEVICE_TYPES.contains(deviceInformation.getDeviceType())) {
                connectionState2.setPresenceState(presenceState);
            }
            AccessoryInfo.Builder timestamp = AccessoryInfo.builder().setState(connectionState2.build()).setTimestamp(time);
            if (deviceRegistrationRequestIdentifier.isFirstParty()) {
                if (deviceRegistrationRequestIdentifier.getFirstPartyClusterDevice() != null) {
                    timestamp.setClusterDeviceInfo(DeviceInfo.builder().setDeviceType(deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceType()).setDeviceSerialNumber(deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceSerialNumber()).build());
                }
                timestamp.setDeviceInfo(DeviceInfo.builder().setDeviceType(deviceInformation.getDeviceType()).setDeviceSerialNumber(deviceInformation.getSerialNumber()).build());
            }
            if (deviceRegistrationRequestIdentifier.getThirdPartyDevice() != null) {
                timestamp.setDeviceInfo(DeviceInfo.builder().setDeviceType(deviceRegistration.getDeviceRegistrationResponse().getDeviceType()).setDeviceSerialNumber(deviceRegistration.getDeviceRegistrationResponse().getInternalDeviceSerialNumber()).build());
            }
            arrayList.add(timestamp.build());
        }
        return this.jsonConverter.toJson(AccessoryStateChangeEventPayload.builder().setAccessories(arrayList).setEventTimestamp(time).setPersonInfo(build).build());
    }
}
