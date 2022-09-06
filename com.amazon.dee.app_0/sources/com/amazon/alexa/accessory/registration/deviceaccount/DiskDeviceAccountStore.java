package com.amazon.alexa.accessory.registration.deviceaccount;

import android.content.Context;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.persistence.FileBackedJsonRxMapStore;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public class DiskDeviceAccountStore implements DeviceAccountStore {
    private static final String accountsFilePath = "accessoriesAccounts/accounts.json";
    private final RxMapStore<String, DeviceAccount> store;

    public DiskDeviceAccountStore(Context context) {
        Preconditions.notNull(context, "context");
        this.store = new FileBackedJsonRxMapStore(new File(context.getFilesDir(), accountsFilePath), DeviceAccount.FACTORY, "users", "directedId", "userDeviceAccounts");
    }

    static /* synthetic */ Iterable lambda$delete$2(Set set) throws Throwable {
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$getDeviceAccount$0(String str, Set set) throws Throwable {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            DeviceAccount deviceAccount = (DeviceAccount) it2.next();
            if (deviceAccount.getDeviceAccountResponse().getDeviceAccountId().equals(str)) {
                return Maybe.just(deviceAccount);
            }
        }
        return Maybe.empty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$getDeviceAccount$1(String str, String str2, Set set) throws Throwable {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            DeviceAccount deviceAccount = (DeviceAccount) it2.next();
            if (deviceAccount.getDeviceAccountRequest().getDeviceType().equals(str) && deviceAccount.getDeviceAccountRequest().getDeviceSerialNumber().equals(str2)) {
                return Maybe.just(deviceAccount);
            }
        }
        return Maybe.empty();
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountStore
    public Completable delete(final String str) {
        return this.store.get(str).toObservable().flatMapIterable($$Lambda$DiskDeviceAccountStore$EYRyBuqxuWed8jPR_g7KgMmlFqY.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$DiskDeviceAccountStore$qmTsI_kGiGZ-GHo1qX1Fh7dhTJU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DiskDeviceAccountStore.this.lambda$delete$3$DiskDeviceAccountStore(str, (DeviceAccount) obj);
            }
        }).toList().flatMapCompletable($$Lambda$2vbaKQwBXPbDE2k6nSJKg20RnA.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountStore
    public Maybe<DeviceAccount> getDeviceAccount(String str, final String str2) {
        return this.store.get(str).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$DiskDeviceAccountStore$RtcQm7wspkvU06ff2OcTSHXzD_4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DiskDeviceAccountStore.lambda$getDeviceAccount$0(str2, (Set) obj);
            }
        });
    }

    public /* synthetic */ Completable lambda$delete$3$DiskDeviceAccountStore(String str, DeviceAccount deviceAccount) throws Throwable {
        return this.store.delete(str, deviceAccount);
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountStore
    public Single<DeviceAccount> putDeviceAccount(String str, DeviceAccount deviceAccount) {
        return this.store.put(str, deviceAccount);
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountStore
    public Observable<Map<String, Set<DeviceAccount>>> queryDeviceAccounts() {
        return this.store.queryValues();
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountStore
    public Maybe<DeviceAccount> getDeviceAccount(String str, final String str2, final String str3) {
        return this.store.get(str).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$DiskDeviceAccountStore$5k5CaX0aCZTJv5nmuJSC1CgcxBQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DiskDeviceAccountStore.lambda$getDeviceAccount$1(str2, str3, (Set) obj);
            }
        });
    }
}
