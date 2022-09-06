package com.amazon.alexa.identity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.identity.MAPAccountUpgradeService;
import com.amazon.alexa.identity.api.AccountUpgradeAuthority;
import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
/* loaded from: classes9.dex */
public final class MAPAccountUpgradeService implements AccountUpgradeService, AccountUpgradeAuthority {
    private static final String ACCOUNT_UPGRADE_DATASTORE = "ACCOUNT_UPGRADE_DATASTORE";
    private static final String REGISTERED_FOR_AVS_DEVICE = "REGISTERED_FOR_AVS_DEVICE";
    private static final String TAG = "MAPAccountUpgradeService";
    private final CrashMetadata crashMetadata;
    private final String deviceNameTemplate;
    private final MAPAccountManager mapAccountManager;
    private final MAPIdentityService mapIdentityService;
    private final PersistentStorage persistentStorage;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.identity.MAPAccountUpgradeService$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public class AnonymousClass1 implements Callback {
        final /* synthetic */ String val$oldAccountToken;
        final /* synthetic */ Subscriber val$subscriber;

        AnonymousClass1(String str, Subscriber subscriber) {
            this.val$oldAccountToken = str;
            this.val$subscriber = subscriber;
        }

        public /* synthetic */ void lambda$onSuccess$0$MAPAccountUpgradeService$1(Subscriber subscriber, UserIdentity userIdentity) {
            MAPAccountUpgradeService.this.mapIdentityService.updateUserIdentity(userIdentity);
            subscriber.onNext(userIdentity);
            subscriber.onCompleted();
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            GeneratedOutlineSupport1.outline164(Utils.V2_LOG_PREFIX, "deregisterAccount() failed.", MAPAccountUpgradeService.TAG);
            this.val$subscriber.onError(new RuntimeException("deregisterAccount() failed."));
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            String unused = MAPAccountUpgradeService.TAG;
            Observable registerAccountFromToken = MAPAccountUpgradeService.this.registerAccountFromToken(this.val$oldAccountToken);
            final Subscriber subscriber = this.val$subscriber;
            registerAccountFromToken.subscribe(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountUpgradeService$1$YaITVzT2GcnWCRxFuXAG_b2x4F4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MAPAccountUpgradeService.AnonymousClass1.this.lambda$onSuccess$0$MAPAccountUpgradeService$1(subscriber, (UserIdentity) obj);
                }
            }, new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountUpgradeService$1$3dxv0y4_i0lTaG0Fd_Yj37-kec0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Subscriber.this.onError((Throwable) obj);
                }
            });
        }
    }

    public MAPAccountUpgradeService(MAPAccountManager mAPAccountManager, String str) {
        this.mapAccountManager = mAPAccountManager;
        this.deviceNameTemplate = str;
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.mapIdentityService = (MAPIdentityService) componentRegistry.get(IdentityService.class).get();
        this.crashMetadata = (CrashMetadata) componentRegistry.get(CrashMetadata.class).get();
        this.persistentStorage = ((PersistentStorage.Factory) componentRegistry.get(PersistentStorage.Factory.class).get()).create(ACCOUNT_UPGRADE_DATASTORE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<UserIdentity> registerAccountFromToken(final String str) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountUpgradeService$6VH00h8oousSSnAk7TPyWNoZjxo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountUpgradeService.this.lambda$registerAccountFromToken$1$MAPAccountUpgradeService(str, (Subscriber) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountUpgradeService$mSHp09ivWc0tJBfR2MLrnFdjuio
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountUpgradeService.this.lambda$registerAccountFromToken$2$MAPAccountUpgradeService((String[]) obj);
            }
        }).doOnError($$Lambda$MAPAccountUpgradeService$uLolrzBYxV7pwpJ_8ufeNYt96Q.INSTANCE).switchMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountUpgradeService$r6ZdFYEoyFDJ3qT9ZgwIUSx788Q
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return MAPAccountUpgradeService.this.lambda$registerAccountFromToken$4$MAPAccountUpgradeService((String[]) obj);
            }
        });
    }

    @Override // com.amazon.alexa.identity.api.AccountUpgradeService, com.amazon.alexa.identity.api.AccountUpgradeAuthority
    public boolean isAccountUpgraded() {
        return this.persistentStorage.getBoolean(REGISTERED_FOR_AVS_DEVICE);
    }

    public /* synthetic */ void lambda$registerAccountFromToken$1$MAPAccountUpgradeService(String str, Subscriber subscriber) {
        Bundle outline11 = GeneratedOutlineSupport1.outline11("com.amazon.dcp.sso.AddAccount.options.AccessToken", str);
        outline11.putString("device_name", this.deviceNameTemplate);
        this.mapAccountManager.registerAccount(RegistrationType.FROM_ACCESS_TOKEN, outline11, new MAPCookiesCallback(subscriber, this.crashMetadata));
    }

    public /* synthetic */ void lambda$registerAccountFromToken$2$MAPAccountUpgradeService(String[] strArr) {
        notifyAccountUpgraded();
    }

    public /* synthetic */ Observable lambda$registerAccountFromToken$4$MAPAccountUpgradeService(String[] strArr) {
        return this.mapIdentityService.refresh(TAG);
    }

    public /* synthetic */ void lambda$upgradeAccount$0$MAPAccountUpgradeService(Subscriber subscriber) {
        if (!this.mapIdentityService.isAuthenticated(TAG)) {
            subscriber.onError(new RuntimeException("Can't upgrade account when not authenticated."));
            return;
        }
        String accessToken = this.mapIdentityService.getUser(TAG).getAccessToken();
        MAPAccountManager mAPAccountManager = this.mapAccountManager;
        mAPAccountManager.deregisterAccount(mAPAccountManager.getAccount(), new AnonymousClass1(accessToken, subscriber));
    }

    @Override // com.amazon.alexa.identity.api.AccountUpgradeAuthority
    public void notifyAccountUpgraded() {
        this.persistentStorage.edit().set(REGISTERED_FOR_AVS_DEVICE, true).commit();
    }

    @Override // com.amazon.alexa.identity.api.AccountUpgradeService
    @NonNull
    public Observable<UserIdentity> upgradeAccount() {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountUpgradeService$tdw-n2Sg5hwcti9d0ifKvGAiEgg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountUpgradeService.this.lambda$upgradeAccount$0$MAPAccountUpgradeService((Subscriber) obj);
            }
        });
    }
}
