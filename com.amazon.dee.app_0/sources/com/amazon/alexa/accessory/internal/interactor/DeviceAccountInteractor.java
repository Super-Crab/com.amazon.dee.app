package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.Interactor;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccount;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public class DeviceAccountInteractor implements Interactor {
    private static final String TAG = "DeviceAccountInteractor";
    private boolean activated;
    private final CompositeDisposable compositeDisposable;
    private final DeviceAccountSupplier deviceAccountSupplier;
    private User previousUser;
    private final RegistrationSupplier registrationSupplier;
    private final SessionListener sessionListener;
    private final SessionSupplier sessionSupplier;
    private final UserSupplier userSupplier;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SessionListener extends AccessorySessionListener {
        private SessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(Accessory accessory) {
            DeviceAccountInteractor.this.compositeDisposable.add(DeviceAccountInteractor.this.handle1PAccessoryRegistrations().subscribe($$Lambda$DeviceAccountInteractor$SessionListener$UrCRk3K4zFM2FGUDddWtPuIC2RA.INSTANCE, $$Lambda$DeviceAccountInteractor$SessionListener$ZTACL3iI9vB5SUwP4YAeNgIFgUs.INSTANCE));
        }
    }

    public DeviceAccountInteractor(DeviceAccountSupplier deviceAccountSupplier, RegistrationSupplier registrationSupplier, SessionSupplier sessionSupplier, UserSupplier userSupplier) {
        Preconditions.notNull(deviceAccountSupplier, "deviceAccountSupplier");
        Preconditions.notNull(registrationSupplier, "registrationSupplier");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(userSupplier, "userSupplier");
        this.deviceAccountSupplier = deviceAccountSupplier;
        this.registrationSupplier = registrationSupplier;
        this.sessionSupplier = sessionSupplier;
        this.userSupplier = userSupplier;
        this.sessionListener = new SessionListener();
        this.compositeDisposable = new CompositeDisposable();
        this.previousUser = User.ABSENT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<List<DeviceAccount>> handle1PAccessoryRegistrations() {
        return this.registrationSupplier.queryRegistrations().flatMapIterable($$Lambda$DeviceAccountInteractor$uaFex6zRkR0bY5NAoraULVfBpG0.INSTANCE).filter($$Lambda$DeviceAccountInteractor$ICFSU3tq4cGqDuT2_7VGe2kWaV4.INSTANCE).concatMap(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceAccountInteractor$XK9BkGXuwadGazcu6Fwayy-E3bE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceAccountInteractor.this.lambda$handle1PAccessoryRegistrations$2$DeviceAccountInteractor((DeviceRegistration) obj);
            }
        }).toList();
    }

    private Completable handleDeletions(User user) {
        String directedCustomerId = this.previousUser.getDirectedCustomerId();
        if (this.previousUser == User.ABSENT) {
            this.previousUser = user;
            return Completable.complete();
        }
        this.previousUser = user;
        Logger.d("%s: logout or new login observed, triggering deleteDeviceAccounts for %s", TAG, directedCustomerId);
        return this.deviceAccountSupplier.deleteDeviceAccounts(directedCustomerId);
    }

    static /* synthetic */ Iterable lambda$handle1PAccessoryRegistrations$0(Set set) throws Throwable {
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$handle1PAccessoryRegistrations$1(DeviceRegistration deviceRegistration) throws Throwable {
        return deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().getThirdPartyDevice() == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$observeLogins$4(User user) throws Throwable {
        return user != User.ABSENT;
    }

    private void observeLogins() {
        this.compositeDisposable.add(this.userSupplier.queryUser().distinctUntilChanged().flatMap(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceAccountInteractor$GJu4LYXi6bVlNoTKfmkex8A1nAI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceAccountInteractor.this.lambda$observeLogins$3$DeviceAccountInteractor((User) obj);
            }
        }).filter($$Lambda$DeviceAccountInteractor$N_0QTEqPSPYOXebYvW5cnee6J40.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceAccountInteractor$dDg-BnrXMzBWWLZ6sGbHbl1t09g
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceAccountInteractor.this.lambda$observeLogins$5$DeviceAccountInteractor((User) obj);
            }
        }).subscribe($$Lambda$DeviceAccountInteractor$Dlr4XgaNeBFKkidjARDaHoZ4DBk.INSTANCE, $$Lambda$DeviceAccountInteractor$7Mse2OzzOStOiIZvBCRjZ57no.INSTANCE));
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void activate() {
        Preconditions.mainThread();
        if (this.activated) {
            return;
        }
        this.activated = true;
        Logger.d("%s: Activate", TAG);
        this.sessionSupplier.addSessionListener(this.sessionListener);
        observeLogins();
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void deactivate() {
        Preconditions.mainThread();
        if (!this.activated) {
            return;
        }
        this.activated = false;
        Logger.d("%s: Deactivate", TAG);
        this.sessionSupplier.removeSessionListener(this.sessionListener);
        this.compositeDisposable.clear();
    }

    public /* synthetic */ ObservableSource lambda$handle1PAccessoryRegistrations$2$DeviceAccountInteractor(DeviceRegistration deviceRegistration) throws Throwable {
        return this.deviceAccountSupplier.fetchAndStoreDeviceAccount(deviceRegistration).toObservable();
    }

    public /* synthetic */ ObservableSource lambda$observeLogins$3$DeviceAccountInteractor(User user) throws Throwable {
        return handleDeletions(user).andThen(Observable.just(user));
    }

    public /* synthetic */ ObservableSource lambda$observeLogins$5$DeviceAccountInteractor(User user) throws Throwable {
        return handle1PAccessoryRegistrations().toObservable();
    }
}
