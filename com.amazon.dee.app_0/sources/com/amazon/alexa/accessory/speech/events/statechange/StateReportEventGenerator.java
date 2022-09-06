package com.amazon.alexa.accessory.speech.events.statechange;

import android.content.Context;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo;
import com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload;
import com.amazon.alexa.accessory.avsclient.presence.ConnectionState;
import com.amazon.alexa.accessory.avsclient.presence.DeviceInfo;
import com.amazon.alexa.accessory.avsclient.presence.NoAccessoryAvailableException;
import com.amazon.alexa.accessory.avsclient.presence.PersonInfo;
import com.amazon.alexa.accessory.avsclient.presence.PresenceState;
import com.amazon.alexa.accessory.avsclient.presence.ProactivelyTargetable;
import com.amazon.alexa.accessory.avsclient.presence.State;
import com.amazon.alexa.accessory.avsclient.utils.ISO8601TimeSupplier;
import com.amazon.alexa.accessory.avsclient.utils.JsonConverter;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.DeviceRegistrationRequestIdentifier;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.accessory.speech.events.statechange.StateReportEventGenerator;
import com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Function3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class StateReportEventGenerator implements AccessoryStateReportGenerator {
    private static final String AH_CASE = "A21YKVRGQV9TET";
    private static final String AMA_SUPPORTED_DEVICES_FILE = "AMASupportedDevices.json";
    private static final String AMA_SUPPORTED_DEVICES_KEY = "amaSupportedDevices";
    private static final String ARMSTRONG_CASE = "A3HVREY4JWAZ6K";
    private static final String DEVICE_TYPE_ID_KEY = "deviceTypeId";
    private static final String EMPTY = "";
    private static final Set<String> IGNORED_DEVICE_TYPES = new HashSet(Arrays.asList("AE9FIEPOC6D9B", "A3HVREY4JWAZ6K", "A21YKVRGQV9TET"));
    private static final String P_CASE = "AE9FIEPOC6D9B";
    private static final String SERVER_KEY = "SERVER";
    private static final String SUPPORTED_INITIATION_TYPES_KEY = "supportedInitiationTypes";
    private static final String TAG = "StateReportEventGenerator:";
    private static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private final Context context;
    private final Set<String> devicesSupportingServerInitDialogs;
    private final JsonConverter jsonConverter;
    private final RegistrationSupplier registrationSupplier;
    private final SessionSupplier sessionSupplier;
    private final ISO8601TimeSupplier timeSupplier;
    private final UserSupplier userSupplier;

    /* renamed from: com.amazon.alexa.accessory.speech.events.statechange.StateReportEventGenerator$1  reason: invalid class name */
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class StateReportInfo {
        public String address;
        public Set<Device.DeviceInformation> deviceInformationSet;
        public DeviceRegistration deviceRegistration;
        public boolean isAccessoryConnected;
        public User user;

        private StateReportInfo() {
        }

        /* synthetic */ StateReportInfo(StateReportEventGenerator stateReportEventGenerator, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public StateReportEventGenerator(RegistrationSupplier registrationSupplier, SessionSupplier sessionSupplier, UserSupplier userSupplier, JsonConverter jsonConverter, Context context) {
        Preconditions.notNull(registrationSupplier, "registrationSupplier");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(jsonConverter, "jsonConverter");
        Preconditions.notNull(context, "context");
        this.registrationSupplier = registrationSupplier;
        this.sessionSupplier = sessionSupplier;
        this.userSupplier = userSupplier;
        this.jsonConverter = jsonConverter;
        this.context = context;
        this.timeSupplier = new ISO8601TimeSupplier(ZONED_DATE_TIME_FORMAT);
        this.devicesSupportingServerInitDialogs = new HashSet();
        initServerSupportedAccessoriesFromJson();
    }

    private List<StateReportInfo> filterStateReportInfoList(List<StateReportInfo> list, Predicate<StateReportInfo> predicate) {
        return Lists.newArrayList(Iterables.filter(list, predicate));
    }

    private List<AccessoryInfo> generateAccessoryInfoForAccessoriesWithSession(List<StateReportInfo> list, String str) {
        ArrayList arrayList = new ArrayList();
        for (StateReportInfo stateReportInfo : list) {
            ConnectionState connectionState = stateReportInfo.isAccessoryConnected ? ConnectionState.CONNECTED : ConnectionState.DISCONNECTED;
            DeviceRegistration deviceRegistration = stateReportInfo.deviceRegistration;
            DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier = deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier();
            for (Device.DeviceInformation deviceInformation : stateReportInfo.deviceInformationSet) {
                PresenceState presenceState = getPresenceState(stateReportInfo.isAccessoryConnected, deviceInformation);
                State.Builder connectionState2 = State.builder().setConnectionState(connectionState);
                if (presenceState != PresenceState.UNKNOWN && !IGNORED_DEVICE_TYPES.contains(deviceInformation.getDeviceType())) {
                    connectionState2.setPresenceState(presenceState);
                }
                connectionState2.setProactivelyTargetable(getTargetableState(connectionState, presenceState, deviceInformation));
                AccessoryInfo.Builder timestamp = AccessoryInfo.builder().setState(connectionState2.build()).setTimestamp(str);
                if (deviceRegistrationRequestIdentifier.isFirstParty()) {
                    if (deviceRegistrationRequestIdentifier.getFirstPartyClusterDevice() != null) {
                        timestamp.setClusterDeviceInfo(getClusterDeviceInfo(deviceRegistration));
                    }
                    timestamp.setDeviceInfo(DeviceInfo.builder().setDeviceType(deviceInformation.getDeviceType()).setDeviceSerialNumber(deviceInformation.getSerialNumber()).build());
                }
                if (deviceRegistrationRequestIdentifier.getThirdPartyDevice() != null) {
                    timestamp.setDeviceInfo(getThirdPartyDeviceInfo(deviceRegistration));
                }
                arrayList.add(timestamp.build());
            }
        }
        return arrayList;
    }

    private List<AccessoryInfo> generateAccessoryInfoForAccessoriesWithoutSession(List<StateReportInfo> list, String str) {
        ArrayList arrayList = new ArrayList();
        for (StateReportInfo stateReportInfo : list) {
            ConnectionState connectionState = ConnectionState.DISCONNECTED;
            DeviceRegistration deviceRegistration = stateReportInfo.deviceRegistration;
            DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier = deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier();
            PresenceState presenceState = PresenceState.ABSENT;
            String deviceType = deviceRegistration.getDeviceRegistrationResponse().getDeviceType();
            String internalDeviceSerialNumber = deviceRegistration.getDeviceRegistrationResponse().getInternalDeviceSerialNumber();
            if (deviceType == null) {
                deviceType = "";
            }
            if (internalDeviceSerialNumber == null) {
                internalDeviceSerialNumber = "";
            }
            State.Builder connectionState2 = State.builder().setConnectionState(connectionState);
            if (!IGNORED_DEVICE_TYPES.contains(deviceType)) {
                connectionState2.setPresenceState(presenceState);
            }
            connectionState2.setProactivelyTargetable(ProactivelyTargetable.NOT_TARGETABLE);
            AccessoryInfo.Builder timestamp = AccessoryInfo.builder().setState(connectionState2.build()).setTimestamp(str);
            if (deviceRegistrationRequestIdentifier.isFirstParty()) {
                if (deviceRegistrationRequestIdentifier.getFirstPartyClusterDevice() != null) {
                    timestamp.setClusterDeviceInfo(getClusterDeviceInfo(deviceRegistration));
                }
                timestamp.setDeviceInfo(DeviceInfo.builder().setDeviceType(deviceType).setDeviceSerialNumber(internalDeviceSerialNumber).build());
            }
            if (deviceRegistrationRequestIdentifier.getThirdPartyDevice() != null) {
                timestamp.setDeviceInfo(getThirdPartyDeviceInfo(deviceRegistration));
            }
            arrayList.add(timestamp.build());
        }
        return arrayList;
    }

    private String generatePayload(List<StateReportInfo> list, String str) {
        List<StateReportInfo> filterStateReportInfoList = filterStateReportInfoList(list, $$Lambda$StateReportEventGenerator$W1BdgeuQLX7mlAktOP6ouE_r5M.INSTANCE);
        List<StateReportInfo> filterStateReportInfoList2 = filterStateReportInfoList(list, $$Lambda$StateReportEventGenerator$aoeoHa8zOjOrG0GGmIxx9SFKxg.INSTANCE);
        String time = this.timeSupplier.getTime(new Date(System.currentTimeMillis()));
        PersonInfo build = PersonInfo.builder().setActivePersonId(list.get(0).user.getDirectedCustomerId()).build();
        List<AccessoryInfo> generateAccessoryInfoForAccessoriesWithSession = generateAccessoryInfoForAccessoriesWithSession(filterStateReportInfoList2, time);
        List<AccessoryInfo> generateAccessoryInfoForAccessoriesWithoutSession = generateAccessoryInfoForAccessoriesWithoutSession(filterStateReportInfoList, time);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(generateAccessoryInfoForAccessoriesWithSession);
        arrayList.addAll(generateAccessoryInfoForAccessoriesWithoutSession);
        return this.jsonConverter.toJson(AccessoryStateChangeEventPayload.builder().setAccessories(arrayList).setEventTimestamp(time).setPersonInfo(build).setToken(str).build());
    }

    private DeviceInfo getClusterDeviceInfo(DeviceRegistration deviceRegistration) {
        return DeviceInfo.builder().setDeviceType(deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceType()).setDeviceSerialNumber(deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceSerialNumber()).build();
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

    private Single<List<StateReportInfo>> getStateReportInfoForPayloadGeneration() {
        return Single.zip(getStateReportInfoWithDeviceRegistration(), this.registrationSupplier.queryRegistrations().firstOrError(), this.userSupplier.queryUser().filter($$Lambda$StateReportEventGenerator$kU72AilivSiWzQ0NXpRysqC3oE4.INSTANCE).distinctUntilChanged().firstOrError(), new Function3() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$NTgTnow0JSfXdSfTTFncYCJ7JdI
            @Override // io.reactivex.rxjava3.functions.Function3
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return StateReportEventGenerator.this.lambda$getStateReportInfoForPayloadGeneration$8$StateReportEventGenerator((List) obj, (Set) obj2, (User) obj3);
            }
        });
    }

    private Single<List<StateReportInfo>> getStateReportInfoWithDeviceRegistration() {
        return getStateReportInfoWithSessionInfo().flatMap(new Function() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$YIWOjQfpfwWrYt9cQNQjEPdWUXc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StateReportEventGenerator.this.lambda$getStateReportInfoWithDeviceRegistration$6$StateReportEventGenerator((List) obj);
            }
        });
    }

    private Single<List<StateReportInfo>> getStateReportInfoWithSessionInfo() {
        return Observable.fromIterable(this.sessionSupplier.getActiveSessions()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Function() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$D2iiZN74kq7ELFrV0SpTrWNKSPk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StateReportEventGenerator.this.lambda$getStateReportInfoWithSessionInfo$3$StateReportEventGenerator((AccessorySession) obj);
            }
        }).toList();
    }

    private ProactivelyTargetable getTargetableState(ConnectionState connectionState, PresenceState presenceState, Device.DeviceInformation deviceInformation) {
        List<Common.SpeechInitiationType> supportedSpeechInitiationsList = deviceInformation.getSupportedSpeechInitiationsList();
        String deviceType = deviceInformation.getDeviceType();
        if (connectionState == ConnectionState.CONNECTED && presenceState == PresenceState.PRESENT && (supportedSpeechInitiationsList.contains(Common.SpeechInitiationType.SERVER) || this.devicesSupportingServerInitDialogs.contains(deviceType))) {
            return ProactivelyTargetable.TARGETABLE;
        }
        return ProactivelyTargetable.NOT_TARGETABLE;
    }

    private DeviceInfo getThirdPartyDeviceInfo(DeviceRegistration deviceRegistration) {
        return DeviceInfo.builder().setDeviceType(deviceRegistration.getDeviceRegistrationResponse().getDeviceType()).setDeviceSerialNumber(deviceRegistration.getDeviceRegistrationResponse().getInternalDeviceSerialNumber()).build();
    }

    private void initServerSupportedAccessoriesFromJson() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.context.getAssets().open(AMA_SUPPORTED_DEVICES_FILE), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            JSONArray jSONArray = new JSONObject(sb.toString()).getJSONArray(AMA_SUPPORTED_DEVICES_KEY);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString(DEVICE_TYPE_ID_KEY);
                JSONArray jSONArray2 = jSONObject.getJSONArray(SUPPORTED_INITIATION_TYPES_KEY);
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    if (jSONArray2.getString(i2).equalsIgnoreCase(SERVER_KEY)) {
                        this.devicesSupportingServerInitDialogs.add(string);
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException | JSONException e) {
            Logger.e("%s: Error while initServerSupportedAccessoriesFromJson ", e, TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$generatePayload$10(StateReportInfo stateReportInfo) {
        return stateReportInfo.deviceInformationSet != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$generatePayload$9(StateReportInfo stateReportInfo) {
        return stateReportInfo.deviceInformationSet == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getAccessoryStateReport$1(AccessoryStateReportGenerator.StateReportCallback stateReportCallback, Throwable th) throws Throwable {
        Logger.e("%s Error generating getAccessoryStateReport: ", th, TAG);
        stateReportCallback.onError(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getStateReportInfoForPayloadGeneration$7(User user) throws Throwable {
        return user != User.ABSENT;
    }

    private Map<DeviceRegistration, StateReportInfo> mapAccessoryWithRegistration(List<StateReportInfo> list) {
        HashMap hashMap = new HashMap();
        for (StateReportInfo stateReportInfo : list) {
            hashMap.put(stateReportInfo.deviceRegistration, stateReportInfo);
        }
        return hashMap;
    }

    @Override // com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator
    public void getAccessoryStateReport(final String str, final AccessoryStateReportGenerator.StateReportCallback stateReportCallback) {
        Preconditions.notNull(str, "accessToken");
        Preconditions.notNull(stateReportCallback, "callback");
        try {
            getStateReportInfoForPayloadGeneration().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$b2jujhEhvNtz8_FKGTcBFp6mMjI
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    StateReportEventGenerator.this.lambda$getAccessoryStateReport$0$StateReportEventGenerator(stateReportCallback, str, (List) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$Kg5imKccGuuHkb0DVQ-XwWmhITs
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    StateReportEventGenerator.lambda$getAccessoryStateReport$1(AccessoryStateReportGenerator.StateReportCallback.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            Logger.e("%s Error calling getStateReportInfoForPayloadGeneration ", e, TAG);
            stateReportCallback.onError(e);
        }
    }

    public /* synthetic */ void lambda$getAccessoryStateReport$0$StateReportEventGenerator(AccessoryStateReportGenerator.StateReportCallback stateReportCallback, String str, List list) throws Throwable {
        if (list != null && list.size() != 0) {
            String generatePayload = generatePayload(list, str);
            Logger.d("%s generated payload for accessory state report: %s", TAG, generatePayload);
            stateReportCallback.onResult(generatePayload);
            return;
        }
        Logger.d("%s no accessory available", TAG);
        stateReportCallback.onError(new NoAccessoryAvailableException("No accessory available"));
    }

    public /* synthetic */ List lambda$getStateReportInfoForPayloadGeneration$8$StateReportEventGenerator(List list, Set set, User user) throws Throwable {
        Map<DeviceRegistration, StateReportInfo> mapAccessoryWithRegistration = mapAccessoryWithRegistration(list);
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            DeviceRegistration deviceRegistration = (DeviceRegistration) it2.next();
            if (!mapAccessoryWithRegistration.containsKey(deviceRegistration)) {
                StateReportInfo stateReportInfo = new StateReportInfo(this, null);
                stateReportInfo.deviceRegistration = deviceRegistration;
                stateReportInfo.isAccessoryConnected = false;
                list.add(stateReportInfo);
            }
        }
        Iterator it3 = list.iterator();
        while (it3.hasNext()) {
            ((StateReportInfo) it3.next()).user = user;
        }
        return list;
    }

    public /* synthetic */ SingleSource lambda$getStateReportInfoWithDeviceRegistration$6$StateReportEventGenerator(List list) throws Throwable {
        return Observable.fromIterable(list).flatMapSingle(new Function() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$-C95wqYK5CiAxnwixRyJwQSlr-c
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StateReportEventGenerator.this.lambda$null$5$StateReportEventGenerator((StateReportEventGenerator.StateReportInfo) obj);
            }
        }).toList();
    }

    public /* synthetic */ ObservableSource lambda$getStateReportInfoWithSessionInfo$3$StateReportEventGenerator(AccessorySession accessorySession) throws Throwable {
        final StateReportInfo stateReportInfo = new StateReportInfo(this, null);
        stateReportInfo.address = accessorySession.getAddress();
        stateReportInfo.isAccessoryConnected = accessorySession.isConnected();
        return accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map(new Function() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$aNsS0zcvUufUjWIuyi5c8B_LX2M
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                StateReportEventGenerator.StateReportInfo stateReportInfo2 = StateReportEventGenerator.StateReportInfo.this;
                stateReportInfo2.deviceInformationSet = (Set) obj;
                return stateReportInfo2;
            }
        }).toObservable();
    }

    public /* synthetic */ SingleSource lambda$null$5$StateReportEventGenerator(final StateReportInfo stateReportInfo) throws Throwable {
        return this.registrationSupplier.getDeviceRegistration(stateReportInfo.address).map(new Function() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$_24-_mMp1BsYwS_-3NOp165kQds
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                StateReportEventGenerator.StateReportInfo stateReportInfo2 = StateReportEventGenerator.StateReportInfo.this;
                stateReportInfo2.deviceRegistration = (DeviceRegistration) obj;
                return stateReportInfo2;
            }
        });
    }
}
