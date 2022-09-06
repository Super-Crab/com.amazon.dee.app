package com.amazon.alexa.accessory.avsclient.context;

import android.annotation.SuppressLint;
import android.util.Pair;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.avsclient.context.IOComponent;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.accessory.avsclient.utils.ISO8601TimeSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.DeviceRegistrationRequestIdentifier;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Function3;
import io.reactivex.rxjava3.functions.Function4;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public final class AlexaIOComponentsSupplier implements IOComponentsSupplier {
    private static final String TAG = "AlexaIOCOmponentsSupplier:";
    private final AccessorySessionListener cacheListener;
    private volatile Map<String, IOComponents> componentsCache;
    private Disposable deviceInformationDisposable;
    private final Subject<Map<String, IOComponents>> ioComponentsSubject;
    private final ISO8601TimeSupplier iso8601TimeSupplier;
    private Disposable registrationDisposable;
    private final RegistrationSupplier registrationSupplier;
    private final SessionSupplier sessionSupplier;
    private Disposable userDisposable;
    private final UserSupplier userSupplier;

    public AlexaIOComponentsSupplier(RegistrationSupplier registrationSupplier, SessionSupplier sessionSupplier, UserSupplier userSupplier) {
        Preconditions.notNull(registrationSupplier, "registrationSupplier");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        this.registrationSupplier = registrationSupplier;
        this.sessionSupplier = sessionSupplier;
        this.componentsCache = new HashMap();
        this.userSupplier = userSupplier;
        this.iso8601TimeSupplier = new ISO8601TimeSupplier();
        this.ioComponentsSubject = BehaviorSubject.createDefault(Collections.emptyMap()).toSerialized();
        this.cacheListener = new AccessorySessionListener() { // from class: com.amazon.alexa.accessory.avsclient.context.AlexaIOComponentsSupplier.1
            @Override // com.amazon.alexa.accessory.AccessorySessionListener
            public void onAccessorySessionConnected(Accessory accessory) {
                Logger.d("AlexaIOComponentsSupplier: accessory session connected detected, caching io components");
                AlexaIOComponentsSupplier.this.observeDeviceInformation();
                AlexaIOComponentsSupplier.this.cacheIOComponents();
            }

            @Override // com.amazon.alexa.accessory.AccessorySessionListener
            public void onAccessorySessionReleased(Accessory accessory) {
                Logger.d("AlexaIOComponentsSupplier: accessory session released detected, caching io components");
                AlexaIOComponentsSupplier.this.cacheIOComponents();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void cacheIOComponents() {
        Preconditions.mainThread();
        Logger.d("Caching IOComponents!");
        getPreviouslyRegisteredActiveSessions().observeOn(Schedulers.io()).flatMapObservable($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$dYevprDpXa3jbVrUAX7ZD0Ygajc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaIOComponentsSupplier.this.lambda$cacheIOComponents$15$AlexaIOComponentsSupplier((AccessorySession) obj);
            }
        }).toList().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$iYKeBVHpi7Q8bObpf9aVjsHQ16Q
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaIOComponentsSupplier.this.lambda$cacheIOComponents$16$AlexaIOComponentsSupplier((List) obj);
            }
        }, $$Lambda$AlexaIOComponentsSupplier$xr28vq9uN8BuKguhTVdEoCWVYw.INSTANCE);
    }

    private void deactivateInternal() {
        this.sessionSupplier.removeSessionListener(this.cacheListener);
        ObservableUtils.dispose(this.userDisposable);
        ObservableUtils.dispose(this.registrationDisposable);
        ObservableUtils.dispose(this.deviceInformationDisposable);
    }

    @SuppressLint({"CheckResult"})
    private void ensureSessionRegistered(final String str) {
        Preconditions.mainThread();
        Single firstOrError = Observable.fromIterable(this.sessionSupplier.getActiveSessions()).observeOn(Schedulers.io()).filter(new Predicate() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$6N6LDW4fHDUuJmTMBuqQdzFT8ac
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = Objects.equals(((AccessorySession) obj).getUuid(), str);
                return equals;
            }
        }).firstOrError();
        final RegistrationSupplier registrationSupplier = this.registrationSupplier;
        registrationSupplier.getClass();
        firstOrError.flatMap(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$C_sr7q6xNhDATw0VJAHZ-3SzYuE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return RegistrationSupplier.this.getOrCreateDeviceRegistration((AccessorySession) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$XW-v91HB8GL2FXlz5HGrNi7ao4g
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DeviceRegistration deviceRegistration = (DeviceRegistration) obj;
                Logger.d("Successfully ensured registration for session with session identifier %s", str);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$7kiQv55r0sOtezeUb2cNCXNuq1A
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Logger.e("Failed to ensure registration for session with session identifier %s", (Throwable) obj, str);
            }
        });
    }

    private static IOComponent.Connection getConnectionType(AccessorySession accessorySession) {
        return accessorySession.getTransport() == AccessoryTransport.Type.GATT ? IOComponent.Connection.BLUETOOTH_LE : IOComponent.Connection.BLUETOOTH_CLASSIC;
    }

    private Single<IOComponents> getIOComponents(AccessorySession accessorySession, DeviceRegistration deviceRegistration) {
        Logger.d("Generating IOComponents for session %s", accessorySession.getAddress());
        DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier = deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier();
        if (deviceRegistrationRequestIdentifier.getFirstPartyDevice() != null) {
            return getIOComponentsForFirstPartyDevice(accessorySession, deviceRegistration);
        }
        if (deviceRegistrationRequestIdentifier.getFirstPartyClusterDevice() != null) {
            return getIOComponentsForFirstPartyClusterDevice(accessorySession, deviceRegistration);
        }
        if (deviceRegistrationRequestIdentifier.getThirdPartyDevice() != null) {
            return getIOComponentsForThirdPartyDevice(accessorySession, deviceRegistration, this.iso8601TimeSupplier.getTime(new Date()));
        }
        return Single.error(new NoSuchElementException("No first party, first party cluster, or third party device available in registration"));
    }

    private Single<IOComponents> getIOComponentsForFirstPartyClusterDevice(final AccessorySession accessorySession, final DeviceRegistration deviceRegistration) {
        return Single.zip(accessorySession.getFirmwareRepositoryV2().queryInformationSet(), accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_A2DP_CONNECTED).firstOrError(), accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError(), new Function3() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$mKgvxPABK5l0Yalg1Sf8e1DCd9E
            @Override // io.reactivex.rxjava3.functions.Function3
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return AlexaIOComponentsSupplier.this.lambda$getIOComponentsForFirstPartyClusterDevice$21$AlexaIOComponentsSupplier(deviceRegistration, accessorySession, (Set) obj, (StateOuterClass.State) obj2, (Set) obj3);
            }
        });
    }

    private Single<IOComponents> getIOComponentsForFirstPartyDevice(final AccessorySession accessorySession, final DeviceRegistration deviceRegistration) {
        return Single.zip(accessorySession.getFirmwareRepositoryV2().queryInformationSet(), accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError(), accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_A2DP_CONNECTED).firstOrError(), accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_HFP_CONNECTED).firstOrError(), new Function4() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$SuGbbiO4T8dhCzzwWY4h84G5j6c
            @Override // io.reactivex.rxjava3.functions.Function4
            public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
                return AlexaIOComponentsSupplier.this.lambda$getIOComponentsForFirstPartyDevice$22$AlexaIOComponentsSupplier(deviceRegistration, accessorySession, (Set) obj, (Set) obj2, (StateOuterClass.State) obj3, (StateOuterClass.State) obj4);
            }
        });
    }

    private static Single<IOComponents> getIOComponentsForThirdPartyDevice(final AccessorySession accessorySession, final DeviceRegistration deviceRegistration, final String str) {
        return accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$JLOXTHq0M9EBKQhnFxCXbKSp5Es
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaIOComponentsSupplier.lambda$getIOComponentsForThirdPartyDevice$23(DeviceRegistration.this, accessorySession, str, (Set) obj);
            }
        });
    }

    private Single<List<AccessorySession>> getPreviouslyRegisteredActiveSessions() {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$W68q0G7masmDX-62Gq8ywzUaVO4
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return AlexaIOComponentsSupplier.this.lambda$getPreviouslyRegisteredActiveSessions$20$AlexaIOComponentsSupplier();
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ IOComponents lambda$getIOComponentsForThirdPartyDevice$23(DeviceRegistration deviceRegistration, AccessorySession accessorySession, String str, Set set) throws Throwable {
        Device.DeviceInformation deviceInformation = (Device.DeviceInformation) set.iterator().next();
        IOComponent.Builder type = new IOComponent.Builder().deviceType(deviceRegistration.getDeviceRegistrationResponse().getDeviceType()).dsn1p(deviceRegistration.getDeviceRegistrationResponse().getInternalDeviceSerialNumber()).connection(getConnectionType(accessorySession)).type(IOComponent.Type.AUDIO_OUTPUT);
        if (deviceInformation.hasBattery()) {
            type.batteryContext(new IOComponent.BatteryContext(deviceInformation.getBattery().getLevel(), str));
            Logger.d("IOComponent created with battery context");
        }
        IOComponent build = type.build();
        IOComponent build2 = type.type(IOComponent.Type.MICROPHONE).build();
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(build2);
        arrayList.add(build);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The microphone component is: ");
        outline107.append(build2.toJsonObject().toString());
        Logger.d(outline107.toString());
        Logger.d("The speaker component is: " + build.toJsonObject().toString());
        return new IOComponents(arrayList, arrayList);
    }

    static /* synthetic */ Pair lambda$null$14(AccessorySession accessorySession, IOComponents iOComponents) throws Throwable {
        return new Pair(accessorySession, iOComponents);
    }

    static /* synthetic */ AccessorySession lambda$null$18(AccessorySession accessorySession, DeviceRegistration deviceRegistration) throws Throwable {
        return accessorySession;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$observeUserChanges$9(User user) throws Throwable {
        return user != User.ABSENT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void observeDeviceInformation() {
        ObservableUtils.dispose(this.deviceInformationDisposable);
        this.deviceInformationDisposable = Observable.fromIterable(this.sessionSupplier.getActiveSessions()).flatMap($$Lambda$AlexaIOComponentsSupplier$ujAdyB6s2OmLYtVQ49NrzvQYVXM.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$QXuF90RZDWK3FAR3dlmMnrbQ-qA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaIOComponentsSupplier.this.lambda$observeDeviceInformation$4$AlexaIOComponentsSupplier((Set) obj);
            }
        }, Functions.emptyConsumer());
    }

    private Disposable observeRegistrations() {
        return this.registrationSupplier.queryRegistrations().skip(1L).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$xI2d7KtAKr4SF186gT8ErTQLDNE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaIOComponentsSupplier.this.lambda$observeRegistrations$5$AlexaIOComponentsSupplier((Set) obj);
            }
        }).subscribe($$Lambda$AlexaIOComponentsSupplier$jb_zH68VmLvT7ppFSrwoYrERwzs.INSTANCE, $$Lambda$AlexaIOComponentsSupplier$u24UmAwDQUxe_tBgOOBWjBW2bCw.INSTANCE);
    }

    private Disposable observeUserChanges() {
        return this.userSupplier.queryUser().doOnNext(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$2MOJl71985BK0sqWHYNI8xzuXV0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaIOComponentsSupplier.this.lambda$observeUserChanges$8$AlexaIOComponentsSupplier((User) obj);
            }
        }).filter($$Lambda$AlexaIOComponentsSupplier$MPMdt29kbHNXFfCzptlvr7_G8.INSTANCE).distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$9kfSNrxeRN-ddGijt1zxUg0cjrQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaIOComponentsSupplier.this.lambda$observeUserChanges$10$AlexaIOComponentsSupplier((User) obj);
            }
        }).subscribe($$Lambda$AlexaIOComponentsSupplier$jMcm_Gg08W0a9bGchMMeCAg59sc.INSTANCE, $$Lambda$AlexaIOComponentsSupplier$Hg1KlfoNiYW5RjxfxYfvXbWQHqg.INSTANCE);
    }

    @SuppressLint({"CheckResult"})
    private void recordIOComponentsMetric(final String str, final boolean z) {
        Preconditions.mainThread();
        Observable.fromIterable(this.sessionSupplier.getActiveSessions()).filter(new Predicate() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$Kgi3Phxf_L3ueQMS_KveDDAqPe0
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = Objects.equals(((AccessorySession) obj).getUuid(), str);
                return equals;
            }
        }).firstOrError().flatMap($$Lambda$AlexaIOComponentsSupplier$J1zgnBoeMImtnbc_fKvoQOhDlvA.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$ISGHpw-U3BUjBJkI_kNRg5sFkU0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(AccessoryMetricsConstants.IO_COMPONENTS_PRESENT, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$7ugJ7C3miln5GLjiCebu8-_OIO0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                GeneratedOutlineSupport1.outline171(AccessoryMetricsConstants.IO_COMPONENTS_PRESENT, "Unknown", z, null);
            }
        });
    }

    private void setCache(Map<String, IOComponents> map) {
        this.componentsCache = new HashMap(map);
        this.ioComponentsSubject.onNext(Collections.unmodifiableMap(map));
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public void activate() {
        Preconditions.mainThread();
        Logger.d("AlexaIOComponentsSupplier: activate");
        deactivateInternal();
        this.sessionSupplier.addSessionListener(this.cacheListener);
        this.userDisposable = observeUserChanges();
        this.registrationDisposable = observeRegistrations();
        observeDeviceInformation();
        cacheIOComponents();
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public void deactivate() {
        Preconditions.mainThread();
        Logger.d("AlexaIOComponentsSupplier: deactivate");
        deactivateInternal();
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public IOComponents getIOComponentsCached(String str) {
        Preconditions.mainThread();
        cacheIOComponents();
        ensureSessionRegistered(str);
        HashMap hashMap = new HashMap(this.componentsCache);
        IOComponents iOComponents = (IOComponents) hashMap.get(str);
        recordIOComponentsMetric(str, iOComponents != null);
        if (iOComponents == null) {
            iOComponents = new IOComponents(Collections.emptyList(), Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        for (IOComponents iOComponents2 : hashMap.values()) {
            arrayList.addAll(iOComponents2.getAllIOComponentList());
        }
        return new IOComponents(iOComponents.getActiveIOComponentList(), arrayList);
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public IOComponents getIOComponentsCachedNoUtterance() {
        ArrayList arrayList = new ArrayList();
        for (IOComponents iOComponents : this.componentsCache.values()) {
            arrayList.addAll(iOComponents.getAllIOComponentList());
        }
        return new IOComponents(Collections.emptyList(), arrayList);
    }

    public /* synthetic */ MaybeSource lambda$cacheIOComponents$15$AlexaIOComponentsSupplier(final AccessorySession accessorySession) throws Throwable {
        return this.registrationSupplier.getDeviceRegistration(accessorySession.getAddress()).toMaybe().onErrorResumeWith(Maybe.empty()).flatMap(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$gAcVsB2U59_H0Dqnyv6KDczxDzA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaIOComponentsSupplier.this.lambda$null$13$AlexaIOComponentsSupplier(accessorySession, (DeviceRegistration) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$_CKiRwokr1xS1dPKh3qIdv8qmCQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return new Pair(AccessorySession.this, (IOComponents) obj);
            }
        });
    }

    public /* synthetic */ void lambda$cacheIOComponents$16$AlexaIOComponentsSupplier(List list) throws Throwable {
        Logger.d("Successfully generated IOComponents in IOComponentsSupplier");
        Map<String, IOComponents> hashMap = new HashMap<>();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Pair pair = (Pair) it2.next();
            hashMap.put(((AccessorySession) pair.first).getUuid(), pair.second);
            Logger.d("Put IOComponents in cache for session with uuid %s and identifier %s", ((AccessorySession) pair.first).getUuid(), ((AccessorySession) pair.first).getAddress());
        }
        setCache(hashMap);
    }

    public /* synthetic */ IOComponents lambda$getIOComponentsForFirstPartyClusterDevice$21$AlexaIOComponentsSupplier(DeviceRegistration deviceRegistration, AccessorySession accessorySession, Set set, StateOuterClass.State state, Set set2) throws Throwable {
        String time = this.iso8601TimeSupplier.getTime(new Date());
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = new ArrayList(4);
        String clusterDeviceType = deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceType();
        String clusterDeviceSerialNumber = deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceSerialNumber();
        IOComponent.Builder clusterDevice = new IOComponent.Builder().type(IOComponent.Type.AUDIO_OUTPUT).connection(getConnectionType(accessorySession)).deviceType(clusterDeviceType).dsn1p(clusterDeviceSerialNumber).clusterDevice(new IOComponent.ClusterDevice(clusterDeviceType, clusterDeviceSerialNumber));
        IOComponent build = clusterDevice.build();
        IOComponent build2 = clusterDevice.type(IOComponent.Type.MICROPHONE).build();
        arrayList2.add(build2);
        Iterator it2 = set2.iterator();
        while (it2.hasNext()) {
            Device.DeviceInformation deviceInformation = (Device.DeviceInformation) it2.next();
            String str = null;
            Iterator it3 = set.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Firmware.FirmwareInformation firmwareInformation = (Firmware.FirmwareInformation) it3.next();
                    if (firmwareInformation.getDeviceId() == deviceInformation.getDeviceId()) {
                        str = Integer.toString(firmwareInformation.getVersion());
                        break;
                    }
                }
            }
            arrayList2.add(clusterDevice.deviceType(deviceInformation.getDeviceType()).dsn1p(deviceInformation.getSerialNumber()).firmwareVersion(str).batteryContext(new IOComponent.BatteryContext(deviceInformation.getBattery().getLevel(), time)).build());
        }
        arrayList.add(build2);
        if (state.getBoolean()) {
            arrayList.add(build);
        }
        return new IOComponents(arrayList, arrayList2);
    }

    public /* synthetic */ IOComponents lambda$getIOComponentsForFirstPartyDevice$22$AlexaIOComponentsSupplier(DeviceRegistration deviceRegistration, AccessorySession accessorySession, Set set, Set set2, StateOuterClass.State state, StateOuterClass.State state2) throws Throwable {
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = new ArrayList(2);
        IOComponent.Builder type = new IOComponent.Builder().firmwareVersion(Integer.toString(((Firmware.FirmwareInformation) set.iterator().next()).getVersion())).deviceType(deviceRegistration.getDeviceRegistrationResponse().getDeviceType()).dsn1p(deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().getFirstPartyDevice().getDsn()).connection(getConnectionType(accessorySession)).batteryContext(new IOComponent.BatteryContext(((Device.DeviceInformation) set2.iterator().next()).getBattery().getLevel(), this.iso8601TimeSupplier.getTime(new Date()))).type(IOComponent.Type.MICROPHONE);
        IOComponent build = type.build();
        arrayList2.add(build);
        arrayList.add(build);
        if (state.getBoolean()) {
            IOComponent build2 = type.type(IOComponent.Type.AUDIO_OUTPUT).build();
            arrayList2.add(build2);
            arrayList.add(build2);
        }
        if (state2.getBoolean()) {
            IOComponent build3 = type.type(IOComponent.Type.AUDIO_OUTPUT).connection(IOComponent.Connection.BLUETOOTH_HFP).build();
            arrayList2.add(build3);
            arrayList.add(build3);
        }
        return new IOComponents(arrayList, arrayList2);
    }

    public /* synthetic */ SingleSource lambda$getPreviouslyRegisteredActiveSessions$20$AlexaIOComponentsSupplier() throws Throwable {
        return Observable.fromIterable(this.sessionSupplier.getActiveSessions()).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$I2LDqJ0Rqj_u_iFtXAaRBWYoeqE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaIOComponentsSupplier.this.lambda$null$19$AlexaIOComponentsSupplier((AccessorySession) obj);
            }
        }).toList();
    }

    public /* synthetic */ MaybeSource lambda$null$13$AlexaIOComponentsSupplier(AccessorySession accessorySession, DeviceRegistration deviceRegistration) throws Throwable {
        return getIOComponents(accessorySession, deviceRegistration).toMaybe();
    }

    public /* synthetic */ MaybeSource lambda$null$19$AlexaIOComponentsSupplier(final AccessorySession accessorySession) throws Throwable {
        return this.registrationSupplier.getDeviceRegistration(accessorySession.getAddress()).toMaybe().onErrorResumeWith(Maybe.empty()).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$gFfkJzek_6BgOPQAymIIZhkMV_I
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                DeviceRegistration deviceRegistration = (DeviceRegistration) obj;
                return AccessorySession.this;
            }
        });
    }

    public /* synthetic */ void lambda$observeDeviceInformation$4$AlexaIOComponentsSupplier(Set set) throws Throwable {
        cacheIOComponents();
    }

    public /* synthetic */ void lambda$observeRegistrations$5$AlexaIOComponentsSupplier(Set set) throws Throwable {
        cacheIOComponents();
    }

    public /* synthetic */ void lambda$observeUserChanges$10$AlexaIOComponentsSupplier(User user) throws Throwable {
        cacheIOComponents();
    }

    public /* synthetic */ void lambda$observeUserChanges$8$AlexaIOComponentsSupplier(User user) throws Throwable {
        Logger.d("User not found, clearing IOComponents cache");
        if (user == User.ABSENT) {
            setCache(Collections.emptyMap());
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public Observable<Map<String, IOComponents>> queryIOComponents() {
        return this.ioComponentsSubject;
    }
}
