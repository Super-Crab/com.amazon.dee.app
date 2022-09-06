package com.amazon.alexa.location.phase3;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.location.LocationManager;
import com.amazon.alexa.location.LocationPermissionService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.common.base.Optional;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import rx.Observable;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public class ConfigurationManager implements MainActivityLifecycleObserver {
    private static final String TAG = "ConfigurationManager";
    private final IdentityService identityService;
    private final LocationManager locationManager;
    private final MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar;
    private final LocationPermissionService permssionService;
    private final Observable<UserIdentity> userChangeObservable;
    private Subject<Configuration> configChangeSubject = BehaviorSubject.create();
    private Subject<Optional<UserIdentity>> userSubject = PublishSubject.create();
    private Subject<Boolean> permissionSubject = PublishSubject.create();
    private Subject<Boolean> settingsSubject = PublishSubject.create();

    public ConfigurationManager(IdentityService identityService, Observable<UserIdentity> observable, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar, LocationPermissionService locationPermissionService, LocationManager locationManager) {
        this.identityService = identityService;
        this.userChangeObservable = observable;
        this.mainActivityLifecycleObserverRegistrar = mainActivityLifecycleObserverRegistrar;
        this.permssionService = locationPermissionService;
        this.locationManager = locationManager;
        this.configChangeSubject.onNext(Configuration.DISABLED);
    }

    private void checkPermission() {
        this.permissionSubject.onNext(Boolean.valueOf(this.permssionService.hasFullLocationPermission()));
    }

    private void checkSettings() {
        this.locationManager.getLocationSettings().subscribe(new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$ConfigurationManager$z45Ee8nEcyzwXqN56eenxHnrguY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ConfigurationManager.this.lambda$checkSettings$3$ConfigurationManager((LocationSettingsStates) obj);
            }
        }, $$Lambda$ConfigurationManager$fDvKDAyLVK2elPEwZOtopubG4wg.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$checkSettings$4(Throwable th) throws Throwable {
        String str = "checkSettings onError: " + th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Configuration lambda$start$0(Optional optional, Boolean bool, Boolean bool2) throws Throwable {
        FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class).mo10268get();
        if (!bool.booleanValue() || !bool2.booleanValue() || !featureServiceV2.hasAccess("GEOFENCE_ANDROID", false)) {
            return Configuration.DISABLED;
        }
        return featureServiceV2.hasAccess("GEOFENCE_ANDROID_PHASE3", false) ? Configuration.PHASE3 : Configuration.DEFAULT;
    }

    public /* synthetic */ void lambda$checkSettings$3$ConfigurationManager(LocationSettingsStates locationSettingsStates) throws Throwable {
        this.settingsSubject.onNext(Boolean.valueOf(locationSettingsStates.isGpsPresent() && locationSettingsStates.isGpsUsable() && locationSettingsStates.isNetworkLocationPresent() && locationSettingsStates.isNetworkLocationUsable()));
    }

    public /* synthetic */ void lambda$start$1$ConfigurationManager(Configuration configuration) throws Throwable {
        this.configChangeSubject.onNext(configuration);
    }

    public /* synthetic */ void lambda$start$2$ConfigurationManager(UserIdentity userIdentity) {
        this.userSubject.onNext(Optional.fromNullable(userIdentity));
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityCreated() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityDestroy() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityPause() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityResume() {
        checkPermission();
        checkSettings();
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStart() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStop() {
    }

    public io.reactivex.rxjava3.core.Observable<Configuration> onConfigChanged() {
        return this.configChangeSubject.distinctUntilChanged();
    }

    public void start() {
        io.reactivex.rxjava3.core.Observable.combineLatest(this.userSubject, this.permissionSubject, this.settingsSubject, $$Lambda$ConfigurationManager$Ad83MyH4RGWuSS04js6TCo6mkE.INSTANCE).retry().subscribe(new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$ConfigurationManager$faQzRTWq_-Kwq2EdSUDx-jCkSe4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ConfigurationManager.this.lambda$start$1$ConfigurationManager((Configuration) obj);
            }
        });
        this.userSubject.onNext(Optional.fromNullable(this.identityService.getUser(TAG)));
        this.userChangeObservable.retry().subscribe(new Action1() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$ConfigurationManager$KygTzM1Ol0yC6qAc96fi1WXXwAY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ConfigurationManager.this.lambda$start$2$ConfigurationManager((UserIdentity) obj);
            }
        });
        this.mainActivityLifecycleObserverRegistrar.addObserver(this);
        checkPermission();
        checkSettings();
    }
}
