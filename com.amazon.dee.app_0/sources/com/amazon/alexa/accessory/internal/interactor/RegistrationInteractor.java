package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.Interactor;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class RegistrationInteractor implements Interactor {
    private boolean active;
    private Disposable deviceDisposable;
    private final DeviceSupplierV2 deviceSupplierV2;
    private Disposable loginDisposable;
    private final RegistrationSupplier registrationSupplier;
    private final AccessorySessionListener sessionListener;
    private final SessionSupplier sessionSupplier;
    private final UserSupplier userSupplier;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.internal.interactor.RegistrationInteractor$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends AccessorySessionListener {
        AnonymousClass1() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(final Accessory accessory) {
            RegistrationInteractor registrationInteractor = RegistrationInteractor.this;
            registrationInteractor.registerSessions(registrationInteractor.sessionSupplier.getActiveSessions()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$1$HS2rRSUlIL2JF2W6AYwlHwkDxYM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    List list = (List) obj;
                    Logger.d("RegistrationInteractor: On session connected listener triggered %s", Accessory.this.toString());
                }
            }, $$Lambda$RegistrationInteractor$1$uBvMCjrGoiVCWDepU14FvmWdK8.INSTANCE);
        }
    }

    public RegistrationInteractor(SessionSupplier sessionSupplier, UserSupplier userSupplier, DeviceSupplierV2 deviceSupplierV2, RegistrationSupplier registrationSupplier) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(deviceSupplierV2, "deviceSupplierV2");
        Preconditions.notNull(registrationSupplier, "registrationSupplier");
        this.sessionSupplier = sessionSupplier;
        this.userSupplier = userSupplier;
        this.deviceSupplierV2 = deviceSupplierV2;
        this.registrationSupplier = registrationSupplier;
        this.sessionListener = createRegistrationAccessorySessionListener();
    }

    private AccessorySessionListener createRegistrationAccessorySessionListener() {
        return new AnonymousClass1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<AccessorySession> getSetupSessionsForDevices(List<DeviceGroup> list) {
        Preconditions.mainThread();
        List<AccessorySession> activeSessions = this.sessionSupplier.getActiveSessions();
        ArrayList arrayList = new ArrayList();
        for (DeviceGroup deviceGroup : list) {
            if (!deviceGroup.getDevices().isEmpty()) {
                for (AccessorySession accessorySession : activeSessions) {
                    if (accessorySession.getAddress().equals(deviceGroup.getIdentifier())) {
                        arrayList.add(accessorySession);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$observeLogins$2(User user) throws Throwable {
        return user != User.ABSENT;
    }

    private void observeDevices() {
        this.deviceDisposable = this.deviceSupplierV2.queryDeviceGroups().distinctUntilChanged().flatMap(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$N92cOJEa-9YICmft_F4PERnJZHk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return RegistrationInteractor.this.lambda$observeDevices$7$RegistrationInteractor((List) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).map(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$E7H0ls3N2Jiwyc8Ye1nHpRNsMCA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                List setupSessionsForDevices;
                setupSessionsForDevices = RegistrationInteractor.this.getSetupSessionsForDevices((List) obj);
                return setupSessionsForDevices;
            }
        }).flatMapSingle(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$2T5Y3jz0YbPEIsDjWN6eQOdRS5I
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Single registerSessions;
                registerSessions = RegistrationInteractor.this.registerSessions((List) obj);
                return registerSessions;
            }
        }).subscribe($$Lambda$RegistrationInteractor$GeNFAUN9QRJZur0ds_s0AjISq9I.INSTANCE, $$Lambda$RegistrationInteractor$oRuSODiWYbzCPKnzLHWfLDVkc.INSTANCE);
    }

    private void observeLogins() {
        this.loginDisposable = this.userSupplier.queryUser().skip(1L).filter($$Lambda$RegistrationInteractor$4xto7z3QlwZs4k_GOJBwfL7s4w.INSTANCE).distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).flatMapSingle(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$0rylU-SBB7HYSn2jLuAKKk3xJng
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return RegistrationInteractor.this.lambda$observeLogins$3$RegistrationInteractor((User) obj);
            }
        }).subscribe($$Lambda$RegistrationInteractor$5gjHsT0x_QjBaV2c9skpDIVLSA.INSTANCE, $$Lambda$RegistrationInteractor$ARUxiuqqFoX5C8Z46Pl8bb47Zhk.INSTANCE, $$Lambda$RegistrationInteractor$IHmZR6Vr34tAgEHMeYNlfoUjz4.INSTANCE);
    }

    private void observeSessions() {
        this.sessionSupplier.addSessionListener(this.sessionListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<List<DeviceRegistration>> registerSessions(List<AccessorySession> list) {
        Preconditions.notNull(list, "accessorySessions");
        return Observable.fromIterable(list).observeOn(Schedulers.io()).concatMapDelayError(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$5rxgHquEYtmSQeGY4pChjBz4Qyk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return RegistrationInteractor.this.lambda$registerSessions$0$RegistrationInteractor((AccessorySession) obj);
            }
        }).toList().onErrorReturn($$Lambda$RegistrationInteractor$xoK3xsz50gUBlsSCMdprly9I.INSTANCE).subscribeOn(AndroidSchedulers.mainThread());
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void activate() {
        Preconditions.mainThread();
        if (this.active) {
            return;
        }
        this.active = true;
        observeSessions();
        observeLogins();
        observeDevices();
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void deactivate() {
        Preconditions.mainThread();
        if (!this.active) {
            return;
        }
        this.active = false;
        Logger.d("RegistrationInteractor: deactivate");
        this.sessionSupplier.removeSessionListener(this.sessionListener);
        ObservableUtils.dispose(this.loginDisposable);
        ObservableUtils.dispose(this.deviceDisposable);
    }

    public /* synthetic */ ObservableSource lambda$observeDevices$7$RegistrationInteractor(List list) throws Throwable {
        return this.registrationSupplier.retainRegistrations(list).andThen(Observable.just(list));
    }

    public /* synthetic */ SingleSource lambda$observeLogins$3$RegistrationInteractor(User user) throws Throwable {
        return registerSessions(this.sessionSupplier.getActiveSessions());
    }

    public /* synthetic */ ObservableSource lambda$registerSessions$0$RegistrationInteractor(AccessorySession accessorySession) throws Throwable {
        return this.registrationSupplier.getOrCreateDeviceRegistration(accessorySession).toObservable();
    }
}
