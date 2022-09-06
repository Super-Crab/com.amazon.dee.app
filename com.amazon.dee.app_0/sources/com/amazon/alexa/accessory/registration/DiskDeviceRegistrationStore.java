package com.amazon.alexa.accessory.registration;

import android.content.Context;
import com.amazon.alexa.accessory.persistence.FileBackedJsonRxMapStore;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
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
public final class DiskDeviceRegistrationStore implements DeviceRegistrationStore {
    private static final String registrationFilePath = "accessoriesRegistrations/registrations.json";
    private final RxMapStore<String, DeviceRegistration> store;

    public DiskDeviceRegistrationStore(Context context) {
        this.store = new FileBackedJsonRxMapStore(new File(context.getFilesDir(), registrationFilePath), DeviceRegistration.FACTORY, "users", "directedId", "userRegistrations");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$getDeviceRegistration$0(DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier, Set set) throws Throwable {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            DeviceRegistration deviceRegistration = (DeviceRegistration) it2.next();
            if (deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().equals(deviceRegistrationRequestIdentifier)) {
                return Maybe.just(deviceRegistration);
            }
        }
        return Maybe.empty();
    }

    @Override // com.amazon.alexa.accessory.registration.DeviceRegistrationStore
    public Maybe<DeviceRegistration> getDeviceRegistration(String str, final DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier) {
        return this.store.get(str).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DiskDeviceRegistrationStore$VhynO9MtkuWIC6hrUGKxMOs9Eto
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DiskDeviceRegistrationStore.lambda$getDeviceRegistration$0(DeviceRegistrationRequestIdentifier.this, (Set) obj);
            }
        });
    }

    public /* synthetic */ CompletableSource lambda$removeDeviceRegistration$1$DiskDeviceRegistrationStore(DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier, String str, Set set) throws Throwable {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            DeviceRegistration deviceRegistration = (DeviceRegistration) it2.next();
            if (deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().equals(deviceRegistrationRequestIdentifier)) {
                return this.store.delete(str, deviceRegistration);
            }
        }
        return Completable.complete();
    }

    @Override // com.amazon.alexa.accessory.registration.DeviceRegistrationStore
    public Single<DeviceRegistration> putDeviceRegistration(String str, DeviceRegistration deviceRegistration) {
        return this.store.put(str, deviceRegistration);
    }

    @Override // com.amazon.alexa.accessory.registration.DeviceRegistrationStore
    public Observable<Map<String, Set<DeviceRegistration>>> queryRegistrations() {
        return this.store.queryValues();
    }

    @Override // com.amazon.alexa.accessory.registration.DeviceRegistrationStore
    public Completable removeDeviceRegistration(final String str, final DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier) {
        return this.store.get(str).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DiskDeviceRegistrationStore$tvaqQ4m6kdp5T9_om5O3SAgyi0g
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DiskDeviceRegistrationStore.this.lambda$removeDeviceRegistration$1$DiskDeviceRegistrationStore(deviceRegistrationRequestIdentifier, str, (Set) obj);
            }
        });
    }
}
