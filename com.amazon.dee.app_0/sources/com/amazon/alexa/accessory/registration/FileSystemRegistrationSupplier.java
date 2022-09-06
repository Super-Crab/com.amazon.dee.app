package com.amazon.alexa.accessory.registration;

import android.annotation.SuppressLint;
import android.content.Context;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.DeviceManufacturerSupplier;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.json.JSONException;
/* loaded from: classes6.dex */
public final class FileSystemRegistrationSupplier implements RegistrationSupplier {
    private static final String TAG = "FileSystemRegistrationSupplier:";
    private static final long THREE_DAYS_MS = 259200000;
    private final DeviceManufacturerSupplier deviceManufacturerSupplier;
    private final DeviceSupplierV2 deviceSupplier;
    private final RegistrationExecutor registrationExecutor;
    private final DeviceRegistrationStore registrationStore;
    private final UserSupplier userSupplier;

    public FileSystemRegistrationSupplier(Context context, UserSupplier userSupplier, DeviceSupplier deviceSupplier, DeviceManufacturerSupplier deviceManufacturerSupplier, RegistrationExecutor registrationExecutor) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(deviceSupplier, "deviceSupplier");
        Preconditions.notNull(deviceManufacturerSupplier, "deviceManufacturerSupplier");
        Preconditions.notNull(registrationExecutor, "registrationExecutor");
        this.deviceSupplier = deviceSupplier;
        this.deviceManufacturerSupplier = deviceManufacturerSupplier;
        this.userSupplier = userSupplier;
        this.registrationExecutor = registrationExecutor;
        this.registrationStore = new DiskDeviceRegistrationStore(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkStale */
    public void lambda$null$8$FileSystemRegistrationSupplier(DeviceRegistration deviceRegistration, DeviceRegistrationRequest deviceRegistrationRequest, User user) throws JSONException {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = deviceRegistration.getTimeOfRegistration() < System.currentTimeMillis() - THREE_DAYS_MS;
        Logger.d("Checking for stale registration %s. Delta since last registration: %d, is stale? %b", deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().toJsonObject().toString(4), Long.valueOf(currentTimeMillis - deviceRegistration.getTimeOfRegistration()), Boolean.valueOf(z));
        if (z) {
            registerCloudAsync(deviceRegistrationRequest, user);
        }
    }

    private DeviceDeregistrationRequest createDeviceDeregistrationRequest(DeviceGroup deviceGroup) {
        return new DeviceDeregistrationRequest(createDeviceRegistrationRequestIdentifier(deviceGroup, Collections.emptyMap()), getAssociatedDeviceRelationship(deviceGroup), getAssociatedDeviceRole(deviceGroup));
    }

    private static DeviceRegistrationRequest createDeviceRegistrationRequest(DeviceGroup deviceGroup, DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier, Map<Integer, String> map, boolean z) throws NoSuchElementException {
        if (deviceRegistrationRequestIdentifier.getFirstPartyDevice() != null) {
            String str = map.get(0);
            if (str != null) {
                return new DeviceRegistrationRequest(deviceRegistrationRequestIdentifier, null, str, Boolean.toString(!z), Boolean.toString(z), getAssociatedDeviceRelationship(deviceGroup), getAssociatedDeviceRole(deviceGroup));
            }
            throw new NoSuchElementException("Firmware not available for 1p device, cannot  generate device registration request.");
        } else if (deviceRegistrationRequestIdentifier.getFirstPartyClusterDevice() == null && deviceRegistrationRequestIdentifier.getThirdPartyDevice() == null) {
            throw new NoSuchElementException("Invalid device registration request identifier, not first party, first party cluster, or third party device");
        } else {
            return new DeviceRegistrationRequest(deviceRegistrationRequestIdentifier, null, null, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE, "true", getAssociatedDeviceRelationship(deviceGroup), getAssociatedDeviceRole(deviceGroup));
        }
    }

    private DeviceRegistrationRequestIdentifier createDeviceRegistrationRequestIdentifier(DeviceGroup deviceGroup, Map<Integer, String> map) {
        Preconditions.precondition(!deviceGroup.getDevices().isEmpty(), "Devices cannot be empty");
        Preconditions.notNull(map, "deviceIdToFirmwareVersionMap");
        Device deviceWithHighestDeviceId = deviceGroup.getDeviceWithHighestDeviceId();
        if (this.deviceManufacturerSupplier.isFirstParty(deviceWithHighestDeviceId.getType())) {
            if (deviceGroup.getDevices().size() > 1) {
                return new DeviceRegistrationRequestIdentifier(new FirstPartyClusterDevice(deviceGroup, map));
            }
            return new DeviceRegistrationRequestIdentifier(new FirstPartyDevice(deviceWithHighestDeviceId.getSerialNumber(), deviceWithHighestDeviceId.getType()));
        }
        return new DeviceRegistrationRequestIdentifier(new ThirdPartyDevice(deviceWithHighestDeviceId.getSerialNumber(), deviceWithHighestDeviceId.getType()));
    }

    private Completable deregisterCloudAndPersist(DeviceDeregistrationRequest deviceDeregistrationRequest, User user) {
        return this.registrationExecutor.deregister(deviceDeregistrationRequest, user).andThen(this.registrationStore.removeDeviceRegistration(user.getDirectedCustomerId(), deviceDeregistrationRequest.getDeviceRegistrationRequestIdentifier()));
    }

    private static String getAssociatedDeviceRelationship(DeviceGroup deviceGroup) {
        return "DeviceIOComponentPair";
    }

    private static String getAssociatedDeviceRole(DeviceGroup deviceGroup) {
        return "IOComponent";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<List<Completable>> getRemovalCompletablesForKnownIdentifiers(final Set<DeviceRegistrationRequestIdentifier> set) {
        return this.registrationStore.queryRegistrations().firstOrError().map(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$9cVe7joa5AoS7Z_C1i3hPVW9TMw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$getRemovalCompletablesForKnownIdentifiers$22$FileSystemRegistrationSupplier(set, (Map) obj);
            }
        });
    }

    private Single<DeviceGroup> getSetupDeviceGroup(final String str) {
        return this.deviceSupplier.getDeviceGroup(str).map(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$0KJ5MeTWlJEksZzLr4RGZsY2iQc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                DeviceGroup deviceGroup = (DeviceGroup) obj;
                FileSystemRegistrationSupplier.lambda$getSetupDeviceGroup$17(str, deviceGroup);
                return deviceGroup;
            }
        });
    }

    private Single<User> getUser() {
        return this.userSupplier.queryUser().firstOrError().map($$Lambda$FileSystemRegistrationSupplier$SwErAGADfhrNlLJo2rPe8sKmlQU.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceGroup lambda$getSetupDeviceGroup$17(String str, DeviceGroup deviceGroup) throws Throwable {
        if (!deviceGroup.getDevices().isEmpty()) {
            return deviceGroup;
        }
        throw new NoSuchElementException(String.format(Locale.US, "Device not setup for identifier %s", str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ User lambda$getUser$16(User user) throws Throwable {
        if (user != User.ABSENT) {
            return user;
        }
        throw new IOException("User is not available");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$null$18(DeviceGroup deviceGroup, Set set) throws Throwable {
        HashMap hashMap = new HashMap();
        for (Device device : deviceGroup.getDevices()) {
            Iterator it2 = set.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Firmware.FirmwareInformation firmwareInformation = (Firmware.FirmwareInformation) it2.next();
                    if (device.getDeviceId().intValue() == firmwareInformation.getDeviceId()) {
                        hashMap.put(device.getDeviceId(), Integer.toString(firmwareInformation.getVersion()));
                        break;
                    }
                }
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Set lambda$null$2(Map map, User user) throws Throwable {
        if (user == User.ABSENT) {
            return Collections.emptySet();
        }
        Set set = (Set) map.get(user.getDirectedCustomerId());
        return set == null ? Collections.emptySet() : set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordDirectedIdIsEqual$32(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.DIRECTED_ID_IS_EQUAL, "unknown", z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordDirectedIdRetrievedForGetOrCreateRegistration$34(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.DIRECTED_ID_RETRIEVED_FOR_GET_OR_CREATE, "unknown", z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordFRORegistrationMetric$28(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.FRO_REGISTRATION_SUCCESS, "unknown", z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordRegisterCloudAndPersistErrorMetric$30(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.REGISTER_CLOUD_AND_PERSIST_ERROR, "unknown", z, null);
    }

    private void recordDirectedIdIsEqual(final boolean z, DeviceRegistrationRequest deviceRegistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$XKL1sV3DvbG9jdgAuM31B4SK_4g
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.DIRECTED_ID_IS_EQUAL, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$d-VPixLHFssPImQIbqNai60lK9Y
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.lambda$recordDirectedIdIsEqual$32(z, (Throwable) obj);
            }
        });
    }

    private void recordDirectedIdRetrievedForGetOrCreateRegistration(final boolean z, DeviceRegistrationRequest deviceRegistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$Df4Ia3Je1IBKeg_2Gb5M9ojggm8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.DIRECTED_ID_RETRIEVED_FOR_GET_OR_CREATE, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$rZHpVGS842V3o8FXgjd7rRQWHII
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.lambda$recordDirectedIdRetrievedForGetOrCreateRegistration$34(z, (Throwable) obj);
            }
        });
    }

    private void recordFRORegistrationMetric(final boolean z, DeviceRegistrationRequest deviceRegistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$pplwm2d6zu9qNkRQsx9Dyq3ZYw0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.FRO_REGISTRATION_SUCCESS, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$jwuNr_a86_L7zCKqZ8pxgS9sGIc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.lambda$recordFRORegistrationMetric$28(z, (Throwable) obj);
            }
        });
    }

    private void recordRegisterCloudAndPersistErrorMetric(final boolean z, DeviceRegistrationRequest deviceRegistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$ACIvNfiW4RDVSmO5fg-apaFsXUg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.REGISTER_CLOUD_AND_PERSIST_ERROR, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$yTPWmwSzzv0AJ2hjOTjwf7h1waQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.lambda$recordRegisterCloudAndPersistErrorMetric$30(z, (Throwable) obj);
            }
        });
    }

    private Single<DeviceRegistration> registerCloudAndPersist(final DeviceRegistrationRequest deviceRegistrationRequest, final User user) {
        return this.registrationExecutor.register(deviceRegistrationRequest, user).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$xgSYC-e7o4jYUIXQ33dFzXN_sDI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$registerCloudAndPersist$25$FileSystemRegistrationSupplier(user, deviceRegistrationRequest, (DeviceRegistration) obj);
            }
        });
    }

    private Single<DeviceRegistration> registerCloudAndPersistForFRO(final DeviceRegistrationRequest deviceRegistrationRequest, User user) {
        return registerCloudAndPersist(deviceRegistrationRequest, user).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$fKiDwvoZtr3tCtc2y8Sbdbxkuic
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.this.lambda$registerCloudAndPersistForFRO$26$FileSystemRegistrationSupplier(deviceRegistrationRequest, (Throwable) obj);
            }
        });
    }

    @SuppressLint({"CheckResult"})
    private void registerCloudAsync(DeviceRegistrationRequest deviceRegistrationRequest, User user) {
        registerCloudAndPersist(deviceRegistrationRequest, user).subscribeOn(Schedulers.io()).subscribe($$Lambda$FileSystemRegistrationSupplier$ZmVYOwaCnDmY1WmqlmICLKTEC14.INSTANCE, $$Lambda$FileSystemRegistrationSupplier$yUE9Zqle1uetaKHKd4PsgD7r1g.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Completable deregister(String str) {
        return getSetupDeviceGroup(str).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$bmZMiX24kc6MFAryYBlF3QMJpbw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$deregister$1$FileSystemRegistrationSupplier((DeviceGroup) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Single<DeviceRegistration> getDeviceRegistration(final String str) {
        return getUser().flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$dgu1e5jQjAs3G8C_-X7KY77gHkU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$getDeviceRegistration$15$FileSystemRegistrationSupplier(str, (User) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Single<DeviceRegistration> getOrCreateDeviceRegistration(AccessorySession accessorySession) {
        return createDeviceRegistrationRequest(accessorySession).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$66SkLdumRpd-ugrq_TZTj7Xey5E
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$getOrCreateDeviceRegistration$13$FileSystemRegistrationSupplier((DeviceRegistrationRequest) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    public /* synthetic */ SingleSource lambda$createDeviceRegistrationRequest$21$FileSystemRegistrationSupplier(AccessorySession accessorySession, final DeviceGroup deviceGroup) throws Throwable {
        return accessorySession.getFirmwareRepositoryV2().queryInformationSet().map(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$p_JffguJa4qLYu94G90fnl7tBK8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.lambda$null$18(DeviceGroup.this, (Set) obj);
            }
        }).onErrorResumeNext($$Lambda$FileSystemRegistrationSupplier$6Gb7J6YAZXilbjVM4TgZ3Vrbc.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$54iD-ElZqYdBhFJNXMOqsc3EGdU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$null$20$FileSystemRegistrationSupplier(deviceGroup, (Map) obj);
            }
        });
    }

    public /* synthetic */ CompletableSource lambda$deregister$1$FileSystemRegistrationSupplier(final DeviceGroup deviceGroup) throws Throwable {
        return getUser().flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$PzCXTikMj7ru5n_jIpoWyWDfJUE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$null$0$FileSystemRegistrationSupplier(deviceGroup, (User) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$getDeviceRegistration$15$FileSystemRegistrationSupplier(String str, final User user) throws Throwable {
        return getSetupDeviceGroup(str).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$Ws4SwvnLwmruDjXYqwrdj-hH39Q
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$null$14$FileSystemRegistrationSupplier(user, (DeviceGroup) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$getOrCreateDeviceRegistration$13$FileSystemRegistrationSupplier(final DeviceRegistrationRequest deviceRegistrationRequest) throws Throwable {
        return getUser().doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$2kYKdAg9v_VDTZmB2HWbEHGjNxg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.this.lambda$null$6$FileSystemRegistrationSupplier(deviceRegistrationRequest, (Throwable) obj);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$AJYCoNbfRiEgr0WPw_9zbzjMZAk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.this.lambda$null$7$FileSystemRegistrationSupplier(deviceRegistrationRequest, (User) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$yUQ_lQj4qnFPrkTqW7dhcgL0zbE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$null$12$FileSystemRegistrationSupplier(deviceRegistrationRequest, (User) obj);
            }
        });
    }

    public /* synthetic */ List lambda$getRemovalCompletablesForKnownIdentifiers$22$FileSystemRegistrationSupplier(Set set, Map map) throws Throwable {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            for (DeviceRegistration deviceRegistration : (Set) entry.getValue()) {
                DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier = deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier();
                if (!set.contains(deviceRegistrationRequestIdentifier)) {
                    arrayList.add(this.registrationStore.removeDeviceRegistration((String) entry.getKey(), deviceRegistrationRequestIdentifier));
                }
            }
        }
        return arrayList;
    }

    public /* synthetic */ CompletableSource lambda$null$0$FileSystemRegistrationSupplier(DeviceGroup deviceGroup, User user) throws Throwable {
        return deregisterCloudAndPersist(createDeviceDeregistrationRequest(deviceGroup), user);
    }

    public /* synthetic */ void lambda$null$10$FileSystemRegistrationSupplier(DeviceRegistrationRequest deviceRegistrationRequest, Throwable th) throws Throwable {
        recordFRORegistrationMetric(false, deviceRegistrationRequest);
    }

    public /* synthetic */ SingleSource lambda$null$11$FileSystemRegistrationSupplier(final DeviceRegistrationRequest deviceRegistrationRequest, User user, Throwable th) throws Throwable {
        return registerCloudAndPersistForFRO(deviceRegistrationRequest, user).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$_PIWOyiyoYIhHSSUpOQZWN4CZ40
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.this.lambda$null$9$FileSystemRegistrationSupplier(deviceRegistrationRequest, (DeviceRegistration) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$gXJbPhT99Em-vimm51EOC96YDw4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.this.lambda$null$10$FileSystemRegistrationSupplier(deviceRegistrationRequest, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$null$12$FileSystemRegistrationSupplier(final DeviceRegistrationRequest deviceRegistrationRequest, final User user) throws Throwable {
        return this.registrationStore.getDeviceRegistration(user.getDirectedCustomerId(), deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier()).toSingle().doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$WyrEM2_9HLJ5Rr9XR1o9LHppIBo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FileSystemRegistrationSupplier.this.lambda$null$8$FileSystemRegistrationSupplier(deviceRegistrationRequest, user, (DeviceRegistration) obj);
            }
        }).onErrorResumeNext(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$8dgtsYC8wfdNZ9Clns52Wm0nOZk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$null$11$FileSystemRegistrationSupplier(deviceRegistrationRequest, user, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$null$14$FileSystemRegistrationSupplier(User user, DeviceGroup deviceGroup) throws Throwable {
        return this.registrationStore.getDeviceRegistration(user.getDirectedCustomerId(), createDeviceRegistrationRequestIdentifier(deviceGroup, Collections.emptyMap())).toSingle();
    }

    public /* synthetic */ DeviceRegistrationRequest lambda$null$20$FileSystemRegistrationSupplier(DeviceGroup deviceGroup, Map map) throws Throwable {
        return createDeviceRegistrationRequest(deviceGroup, createDeviceRegistrationRequestIdentifier(deviceGroup, map), map, deviceGroup.getDevice() != null && this.deviceManufacturerSupplier.allowMultipleAccounts(deviceGroup.getDevice().getType()));
    }

    public /* synthetic */ void lambda$null$6$FileSystemRegistrationSupplier(DeviceRegistrationRequest deviceRegistrationRequest, Throwable th) throws Throwable {
        recordDirectedIdRetrievedForGetOrCreateRegistration(false, deviceRegistrationRequest);
    }

    public /* synthetic */ void lambda$null$7$FileSystemRegistrationSupplier(DeviceRegistrationRequest deviceRegistrationRequest, User user) throws Throwable {
        recordDirectedIdRetrievedForGetOrCreateRegistration(true, deviceRegistrationRequest);
    }

    public /* synthetic */ void lambda$null$9$FileSystemRegistrationSupplier(DeviceRegistrationRequest deviceRegistrationRequest, DeviceRegistration deviceRegistration) throws Throwable {
        recordFRORegistrationMetric(true, deviceRegistrationRequest);
    }

    public /* synthetic */ SingleSource lambda$queryRegistrations$3$FileSystemRegistrationSupplier(final Map map) throws Throwable {
        return this.userSupplier.queryUser().firstOrError().map(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$5RpIJ8Cmmvs2qDpUJ-Mw5jQ26dY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.lambda$null$2(map, (User) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$registerCloudAndPersist$25$FileSystemRegistrationSupplier(User user, DeviceRegistrationRequest deviceRegistrationRequest, DeviceRegistration deviceRegistration) throws Throwable {
        String directedCustomerId = user.getDirectedCustomerId();
        recordDirectedIdIsEqual(directedCustomerId.equals(deviceRegistration.getDeviceRegistrationResponse().getCustomerDirectedId()), deviceRegistrationRequest);
        return this.registrationStore.putDeviceRegistration(directedCustomerId, deviceRegistration);
    }

    public /* synthetic */ void lambda$registerCloudAndPersistForFRO$26$FileSystemRegistrationSupplier(DeviceRegistrationRequest deviceRegistrationRequest, Throwable th) throws Throwable {
        recordRegisterCloudAndPersistErrorMetric(true, deviceRegistrationRequest);
    }

    public /* synthetic */ MaybeSource lambda$retainRegistrations$4$FileSystemRegistrationSupplier(DeviceGroup deviceGroup) throws Throwable {
        return getSetupDeviceGroup(deviceGroup.getIdentifier()).toMaybe().onErrorResumeWith(Maybe.empty());
    }

    public /* synthetic */ DeviceRegistrationRequestIdentifier lambda$retainRegistrations$5$FileSystemRegistrationSupplier(DeviceGroup deviceGroup) throws Throwable {
        return createDeviceRegistrationRequestIdentifier(deviceGroup, Collections.emptyMap());
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Observable<Set<DeviceRegistration>> queryRegistrations() {
        return this.registrationStore.queryRegistrations().flatMapSingle(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$uXauJ-JY6EINIPcErqK6HOE8u70
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$queryRegistrations$3$FileSystemRegistrationSupplier((Map) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Completable retainRegistrations(List<DeviceGroup> list) {
        return Observable.fromIterable(list).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$1xYgK6XYTYz6ApxPXqZNsQBaXxw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$retainRegistrations$4$FileSystemRegistrationSupplier((DeviceGroup) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$nyBRRYBKiIp_LIKHr43E8xHgPsE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$retainRegistrations$5$FileSystemRegistrationSupplier((DeviceGroup) obj);
            }
        }).toList().map($$Lambda$Xlmt0nL10_onlo2I_4ahXL4SvwI.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$_TleaWacxkGZLckxgzGHdFtF9tA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Single removalCompletablesForKnownIdentifiers;
                removalCompletablesForKnownIdentifiers = FileSystemRegistrationSupplier.this.getRemovalCompletablesForKnownIdentifiers((HashSet) obj);
                return removalCompletablesForKnownIdentifiers;
            }
        }).flatMapCompletable($$Lambda$2vbaKQwBXPbDE2k6nSJKg20RnA.INSTANCE);
    }

    private Single<DeviceRegistrationRequest> createDeviceRegistrationRequest(final AccessorySession accessorySession) {
        return getSetupDeviceGroup(accessorySession.getAddress()).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$b1B9L8qz8SQ6MT4vp0wzAB3LwAo
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemRegistrationSupplier.this.lambda$createDeviceRegistrationRequest$21$FileSystemRegistrationSupplier(accessorySession, (DeviceGroup) obj);
            }
        });
    }
}
