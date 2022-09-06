package com.amazon.alexa.accessory.registration.deviceaccount;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.DeviceRegistrationRequestIdentifier;
import com.amazon.alexa.accessory.registration.DeviceRegistrationResponse;
import com.amazon.alexa.accessory.registration.FirstPartyClusterDevice;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public class FileSystemDeviceAccountSupplier implements DeviceAccountSupplier {
    private static final String TAG = "FileSystemDeviceAccountSupplier";
    private final DeviceAccountExecutor deviceAccountExecutor;
    private final DeviceAccountStore deviceAccountStore;
    private final DeviceSupplierV2 deviceSupplier;
    private final UserSupplier userSupplier;

    public FileSystemDeviceAccountSupplier(Context context, UserSupplier userSupplier, DeviceAccountExecutor deviceAccountExecutor, DeviceSupplierV2 deviceSupplierV2) {
        this(context, userSupplier, deviceAccountExecutor, deviceSupplierV2, new DiskDeviceAccountStore(context));
    }

    private Single<DeviceAccountRequest> build1PDeviceAccountRequest(DeviceRegistration deviceRegistration) {
        final DeviceRegistrationResponse deviceRegistrationResponse = deviceRegistration.getDeviceRegistrationResponse();
        DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier = deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier();
        if (deviceRegistrationResponse.getClusterDeviceType() == null) {
            return Single.just(new DeviceAccountRequest(deviceRegistrationResponse.getDeviceType(), deviceRegistrationRequestIdentifier.getFirstPartyDevice().getDsn(), null, null));
        }
        final List<FirstPartyClusterDevice.ConstituentDevice> constituentDevices = deviceRegistrationRequestIdentifier.getFirstPartyClusterDevice().getConstituentDevices();
        return getPrimaryDeviceType(constituentDevices.get(0).getDeviceType()).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$RMI0Ro1IIuL1AwgaWi8_QEnEz8w
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemDeviceAccountSupplier.this.lambda$build1PDeviceAccountRequest$11$FileSystemDeviceAccountSupplier(constituentDevices, deviceRegistrationResponse, (String) obj);
            }
        });
    }

    private Single<DeviceAccount> getDeviceAccountAndPersist(DeviceAccountRequest deviceAccountRequest, final User user) {
        return this.deviceAccountExecutor.fetchDeviceAccount(deviceAccountRequest, user).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$6hdIZhQCyUKUIOt9xi7Ie-7RuHw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemDeviceAccountSupplier.this.lambda$getDeviceAccountAndPersist$8$FileSystemDeviceAccountSupplier(user, (DeviceAccount) obj);
            }
        }).doOnSuccess($$Lambda$FileSystemDeviceAccountSupplier$Po8DLDHklbLiH6aHo3VNY0KlSsg.INSTANCE).doOnError($$Lambda$FileSystemDeviceAccountSupplier$v2K87kody2qgoYeqUYzAMST5oTw.INSTANCE);
    }

    private Single<String> getDeviceIdentifierForDsn(final String str) {
        return this.deviceSupplier.queryDeviceGroups().firstOrError().flatMapObservable($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).filter(new Predicate() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$k29dp1Lj6g8F6w8oeJwViy-cQ-o
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return FileSystemDeviceAccountSupplier.lambda$getDeviceIdentifierForDsn$6(str, (DeviceGroup) obj);
            }
        }).map($$Lambda$BawggZJfidgCs2CntNc8YXhpEU.INSTANCE).firstOrError();
    }

    private String getPrimaryDeviceDsn(List<FirstPartyClusterDevice.ConstituentDevice> list, String str) {
        for (FirstPartyClusterDevice.ConstituentDevice constituentDevice : list) {
            if (str.equals(constituentDevice.getDeviceType())) {
                return constituentDevice.getDeviceSerialNumber();
            }
        }
        return null;
    }

    private Single<String> getPrimaryDeviceType(final String str) {
        return this.deviceSupplier.queryDeviceGroups().firstOrError().flatMapObservable($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).filter(new Predicate() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$-fLzuGd8-Y5GTkMZhfc2MiohuVw
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return FileSystemDeviceAccountSupplier.lambda$getPrimaryDeviceType$12(str, (DeviceGroup) obj);
            }
        }).map($$Lambda$FileSystemDeviceAccountSupplier$lY23vMLukyl5N9Ly_pbyVEdW8.INSTANCE).firstOrError();
    }

    private Single<User> getUser() {
        return this.userSupplier.queryUser().firstOrError().flatMap($$Lambda$FileSystemDeviceAccountSupplier$NafE0LtHJ8c2eVXRghEo_7i64.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getDeviceIdentifierForDsn$6(String str, DeviceGroup deviceGroup) throws Throwable {
        return deviceGroup.getDeviceWithHighestDeviceId() != null && str.equals(deviceGroup.getDeviceWithHighestDeviceId().getSerialNumber());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getPrimaryDeviceType$12(String str, DeviceGroup deviceGroup) throws Throwable {
        for (Device device : deviceGroup.getDevices()) {
            if (device.getType().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SingleSource lambda$getUser$7(User user) throws Throwable {
        if (user.equals(User.ABSENT)) {
            return Single.error(new IllegalStateException("User is absent!"));
        }
        if (user.getAccessToken() == null) {
            return Single.error(new IllegalStateException("Access token not available for user: " + user));
        }
        return Single.just(user);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$null$16(String str, String str2, DeviceAccount deviceAccount) throws Throwable {
        return str.equals(deviceAccount.getDeviceAccountRequest().getDeviceType()) && str2.equals(deviceAccount.getDeviceAccountRequest().getDeviceSerialNumber());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Set lambda$queryMatchingDeviceAccount$15(String str, Map map) throws Throwable {
        return (Set) map.get(str);
    }

    private Observable<DeviceAccount> queryMatchingDeviceAccount(final String str, final String str2, final String str3) {
        return this.deviceAccountStore.queryDeviceAccounts().filter(new Predicate() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$BeqvhjEII5IqwmLb49-tF079eu8
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return ((Map) obj).containsKey(str);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$6AJqPV4EtbNRdzImYJYXuSY80pk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemDeviceAccountSupplier.lambda$queryMatchingDeviceAccount$15(str, (Map) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$BqnI8ibqJE66r-VclV1riFXjmJ0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                ObservableSource filter;
                filter = Observable.fromIterable((Set) obj).filter(new Predicate() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$7zRJmxCtE5bpBtb3pvBVp2O3ZEI
                    @Override // io.reactivex.rxjava3.functions.Predicate
                    public final boolean test(Object obj2) {
                        return FileSystemDeviceAccountSupplier.lambda$null$16(r1, r2, (DeviceAccount) obj2);
                    }
                });
                return filter;
            }
        });
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Maybe<DeviceAccount> awaitDeviceAccount(String str, String str2, String str3) {
        return Observable.concat(getDeviceAccount(str2, str3).toObservable().onErrorResumeWith(Observable.empty()), queryMatchingDeviceAccount(str, str2, str3)).firstElement().subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Completable deleteDeviceAccounts(String str) {
        return this.deviceAccountStore.delete(str).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Single<DeviceAccount> fetchAndStoreDeviceAccount(DeviceRegistration deviceRegistration) {
        Preconditions.precondition(deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().isFirstParty(), "This API should only be called with first party registration objects.");
        return build1PDeviceAccountRequest(deviceRegistration).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$OG7oIA6837RpHpNehTbap7AavK8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemDeviceAccountSupplier.this.lambda$fetchAndStoreDeviceAccount$2$FileSystemDeviceAccountSupplier((DeviceAccountRequest) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Single<DeviceAccount> getDeviceAccount(final String str, final String str2) {
        return getUser().flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$qMEXqtccEGdQ8YuQJn5jpSUwGIc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemDeviceAccountSupplier.this.lambda$getDeviceAccount$3$FileSystemDeviceAccountSupplier(str, str2, (User) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Single<String> getDeviceIdentifier(String str) {
        return getDeviceAccount(str).flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$DKr-LOOoIIapcSWacX-uOz_kAZI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemDeviceAccountSupplier.this.lambda$getDeviceIdentifier$4$FileSystemDeviceAccountSupplier((DeviceAccount) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    public /* synthetic */ SingleSource lambda$build1PDeviceAccountRequest$11$FileSystemDeviceAccountSupplier(List list, DeviceRegistrationResponse deviceRegistrationResponse, String str) throws Throwable {
        return Single.just(new DeviceAccountRequest(str, getPrimaryDeviceDsn(list, str), deviceRegistrationResponse.getClusterDeviceType(), deviceRegistrationResponse.getClusterDeviceSerialNumber()));
    }

    public /* synthetic */ SingleSource lambda$fetchAndStoreDeviceAccount$2$FileSystemDeviceAccountSupplier(final DeviceAccountRequest deviceAccountRequest) throws Throwable {
        return getUser().flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$udNV1iBepvSruHmBoQCaoNWUVL8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemDeviceAccountSupplier.this.lambda$null$1$FileSystemDeviceAccountSupplier(deviceAccountRequest, (User) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$getDeviceAccount$3$FileSystemDeviceAccountSupplier(String str, String str2, User user) throws Throwable {
        return this.deviceAccountStore.getDeviceAccount(user.getDirectedCustomerId(), str, str2).toSingle();
    }

    public /* synthetic */ SingleSource lambda$getDeviceAccount$5$FileSystemDeviceAccountSupplier(String str, User user) throws Throwable {
        return this.deviceAccountStore.getDeviceAccount(user.getDirectedCustomerId(), str).toSingle();
    }

    public /* synthetic */ SingleSource lambda$getDeviceAccountAndPersist$8$FileSystemDeviceAccountSupplier(User user, DeviceAccount deviceAccount) throws Throwable {
        return this.deviceAccountStore.putDeviceAccount(user.getDirectedCustomerId(), deviceAccount);
    }

    public /* synthetic */ SingleSource lambda$getDeviceIdentifier$4$FileSystemDeviceAccountSupplier(DeviceAccount deviceAccount) throws Throwable {
        return getDeviceIdentifierForDsn(deviceAccount.getDeviceAccountRequest().getDeviceSerialNumber());
    }

    public /* synthetic */ SingleSource lambda$null$0$FileSystemDeviceAccountSupplier(DeviceAccountRequest deviceAccountRequest, User user, Throwable th) throws Throwable {
        return getDeviceAccountAndPersist(deviceAccountRequest, user);
    }

    public /* synthetic */ SingleSource lambda$null$1$FileSystemDeviceAccountSupplier(final DeviceAccountRequest deviceAccountRequest, final User user) throws Throwable {
        return getDeviceAccount(deviceAccountRequest.getDeviceType(), deviceAccountRequest.getDeviceSerialNumber()).onErrorResumeNext(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$_27CjJpbL316kiBRbY-IDZHAY5s
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemDeviceAccountSupplier.this.lambda$null$0$FileSystemDeviceAccountSupplier(deviceAccountRequest, user, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    public FileSystemDeviceAccountSupplier(Context context, UserSupplier userSupplier, DeviceAccountExecutor deviceAccountExecutor, DeviceSupplierV2 deviceSupplierV2, DeviceAccountStore deviceAccountStore) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(deviceAccountExecutor, "deviceAccountExecutor");
        Preconditions.notNull(deviceSupplierV2, "deviceSupplier");
        Preconditions.notNull(deviceAccountStore, "accountStore");
        this.userSupplier = userSupplier;
        this.deviceAccountExecutor = deviceAccountExecutor;
        this.deviceSupplier = deviceSupplierV2;
        this.deviceAccountStore = deviceAccountStore;
    }

    private Single<DeviceAccount> getDeviceAccount(final String str) {
        return getUser().flatMap(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$q6kxahTSbgcqgCOoT9EgqEc7B2g
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FileSystemDeviceAccountSupplier.this.lambda$getDeviceAccount$5$FileSystemDeviceAccountSupplier(str, (User) obj);
            }
        });
    }
}
