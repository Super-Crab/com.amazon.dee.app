package com.amazon.alexa.accessorykit.cbl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryExplorer;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessorykit.R;
import com.amazon.alexa.accessorykit.interprocess.feature.InterprocessFeatureChecker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes6.dex */
public class DefaultCblInteractor implements CblInteractor, AccessoryExplorer.Observer {
    private static final String CBL_DEVICE_TYPE = "C57E2F8C-379C-42E1-A6F2-9F9ED067C22C";
    private static final String CBL_NOTIFICATION_ID_PREFIX = "CBL";
    private static final String TAG = "DefaultCblInteractor";
    private final AccessoryExplorer accessoryExplorer;
    private boolean activated;
    private final Context context;
    private final AccessorySession.Factory factory;
    private final InterprocessFeatureChecker featureChecker;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final CblNotificationInteractor notificationInteractor;
    private final SessionSupplier sessionSupplier;
    private final UserSupplier userSupplier;

    /* loaded from: classes6.dex */
    public static final class Builder {
        private AccessoryExplorer accessoryExplorer;
        private Context context;
        private AccessorySession.Factory factory;
        private InterprocessFeatureChecker featureChecker;
        private CblNotificationInteractor notificationInteractor;
        private SessionSupplier sessionSupplier;
        private UserSupplier userSupplier;

        public DefaultCblInteractor build() {
            return new DefaultCblInteractor(this);
        }

        public Builder setAccessoryExplorer(AccessoryExplorer accessoryExplorer) {
            this.accessoryExplorer = accessoryExplorer;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setFeatureChecker(InterprocessFeatureChecker interprocessFeatureChecker) {
            this.featureChecker = interprocessFeatureChecker;
            return this;
        }

        public Builder setNotificationInteractor(CblNotificationInteractor cblNotificationInteractor) {
            this.notificationInteractor = cblNotificationInteractor;
            return this;
        }

        public Builder setSessionFactory(AccessorySession.Factory factory) {
            this.factory = factory;
            return this;
        }

        public Builder setSessionSupplier(SessionSupplier sessionSupplier) {
            this.sessionSupplier = sessionSupplier;
            return this;
        }

        public Builder setUserSupplier(UserSupplier userSupplier) {
            this.userSupplier = userSupplier;
            return this;
        }

        private Builder() {
        }
    }

    @SuppressLint({"CheckResult"})
    public DefaultCblInteractor(Builder builder) {
        this.accessoryExplorer = builder.accessoryExplorer;
        this.context = builder.context;
        this.factory = builder.factory;
        this.featureChecker = builder.featureChecker;
        this.notificationInteractor = builder.notificationInteractor;
        this.sessionSupplier = builder.sessionSupplier;
        this.userSupplier = builder.userSupplier;
        this.featureChecker.observeOnFeatureLoaded().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$uMDr4dqA9jwyNGiYLjwrVOD5noo
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DefaultCblInteractor.this.onFeatureLoaded();
            }
        }, $$Lambda$DefaultCblInteractor$Cn1vldVnxhBtgSsISPt1rzvom8.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    /* renamed from: createSession */
    public void lambda$onUnknownAccessoryFound$2$DefaultCblInteractor(final Accessory accessory) {
        Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$Hrvd10amDZQ0Z-YKgpKK1XhAcPU
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                DefaultCblInteractor.this.lambda$createSession$3$DefaultCblInteractor(accessory, completableEmitter);
            }
        }).subscribe(new Action() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$ptUwvRIn8MYB1qPDM8b18xDm0Ss
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DefaultCblInteractor.this.lambda$createSession$4$DefaultCblInteractor(accessory);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$Fbm_vq5rQUzl-XD9FqyETiOixKU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                Logger.e("%s:: createSession failed: accessory: %s", DefaultCblInteractor.TAG, RedactionUtil.redact(Accessory.this));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    /* renamed from: getCblLoginState */
    public void lambda$createSession$4$DefaultCblInteractor(final Accessory accessory) {
        this.sessionSupplier.getSession(accessory).getCblRepository().queryCblLoginState().observeOn(AndroidSchedulers.mainThread()).map($$Lambda$DefaultCblInteractor$Pb8oKMe8a89PWcgUW14s8tynhS4.INSTANCE).firstOrError().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$xg1Ffr4bD8NL_O9D2YwHLMp59lk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultCblInteractor.this.lambda$getCblLoginState$8$DefaultCblInteractor(accessory, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$4rQem42u3qfCByDm2vQa49tX_KY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultCblInteractor.this.lambda$getCblLoginState$10$DefaultCblInteractor(accessory, (Throwable) obj);
            }
        });
    }

    private int getNotificationId(Accessory accessory) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CBL_NOTIFICATION_ID_PREFIX);
        outline107.append(accessory.getAddress());
        return outline107.toString().hashCode();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void onFeatureLoaded() {
        this.userSupplier.queryUser().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$1yUKhUIReIC1AjmlLcrEq5oqwKE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultCblInteractor.this.lambda$onFeatureLoaded$1$DefaultCblInteractor((User) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: releaseAccessorySession */
    public void lambda$null$9$DefaultCblInteractor(Accessory accessory) {
        AccessorySession session = this.sessionSupplier.getSession(accessory);
        if (session != null) {
            if (!session.isConnected() && !session.isConnecting()) {
                return;
            }
            session.release();
        }
    }

    private void showNotification(Accessory accessory) {
        if (!this.notificationInteractor.areNotificationsEnabled()) {
            return;
        }
        this.notificationInteractor.show(getNotificationId(accessory), CblNotification.newBuilder().setText(String.format(this.context.getString(R.string.accessories_cbl_notification_text), accessory.getName())).setTitle(this.context.getString(R.string.accessories_cbl_notification_title)).setAccessory(accessory).build());
    }

    @Override // com.amazon.alexa.accessorykit.cbl.CblInteractor
    public void activate() {
        if (this.activated) {
            return;
        }
        this.activated = true;
        this.accessoryExplorer.discover(this);
    }

    @Override // com.amazon.alexa.accessorykit.cbl.CblInteractor
    public void deactivate() {
        if (!this.activated) {
            return;
        }
        this.activated = false;
        this.accessoryExplorer.cancel(this);
    }

    public /* synthetic */ void lambda$createSession$3$DefaultCblInteractor(Accessory accessory, CompletableEmitter completableEmitter) throws Throwable {
        if (!this.sessionSupplier.hasActiveSession(accessory)) {
            this.sessionSupplier.createSession(accessory, this.factory).connect();
        }
        if (this.sessionSupplier.hasActiveSession(accessory)) {
            completableEmitter.onComplete();
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Create session failed: accessory: ");
        outline107.append(RedactionUtil.redact(accessory));
        completableEmitter.onError(new Exception(outline107.toString()));
    }

    public /* synthetic */ void lambda$getCblLoginState$10$DefaultCblInteractor(final Accessory accessory, Throwable th) throws Throwable {
        Logger.e("%s:: Get login state error", th, TAG);
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$VosSEy1d6x-boaTWCaWC6gJWUs8
            @Override // java.lang.Runnable
            public final void run() {
                DefaultCblInteractor.this.lambda$null$9$DefaultCblInteractor(accessory);
            }
        });
    }

    public /* synthetic */ void lambda$getCblLoginState$8$DefaultCblInteractor(final Accessory accessory, Boolean bool) throws Throwable {
        if (!bool.booleanValue()) {
            showNotification(accessory);
        } else {
            Logger.d("%s:: Found registered accessory: %s", TAG, RedactionUtil.redact(accessory));
        }
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$nCbnNRrAzLmv1VvDBXmk3YJiv5E
            @Override // java.lang.Runnable
            public final void run() {
                DefaultCblInteractor.this.lambda$null$7$DefaultCblInteractor(accessory);
            }
        });
    }

    public /* synthetic */ void lambda$onFeatureLoaded$1$DefaultCblInteractor(User user) throws Throwable {
        if (user.equals(User.ABSENT)) {
            Logger.d("%s:: User Absent, deactivating...", TAG);
            deactivate();
        } else if (this.featureChecker.hasAccess(AccessoryFeature.AUTOMOTIVE_LOGIN)) {
            Logger.d("%s:: Automotive feature on, activating...", TAG);
            activate();
        } else {
            Logger.d("%s:: Automotive feature off, deactivating...", TAG);
            deactivate();
        }
    }

    @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
    public void onKnownAccessoryFound(Accessory accessory) {
        Logger.d("%s:: onKnownAccessoryFound: %s", TAG, RedactionUtil.redact(accessory));
    }

    @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
    public void onKnownAccessoryLost(Accessory accessory) {
        Logger.d("%s:: onKnownAccessoryLost: %s", TAG, RedactionUtil.redact(accessory));
    }

    @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
    public void onUnknownAccessoryFound(final Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
        Logger.d("%s:: onUnknownAccessoryFound: %s", TAG, RedactionUtil.redact(accessory));
        for (Device device : accessoryInquiryRecord.getDevices()) {
            if (CBL_DEVICE_TYPE.equals(device.getType())) {
                this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$kPq74se7ZdClkmNVv7jys_z5jl0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultCblInteractor.this.lambda$onUnknownAccessoryFound$2$DefaultCblInteractor(accessory);
                    }
                });
            }
        }
    }

    @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
    public void onUnknownAccessoryLost(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
        Logger.d("%s:: onUnknownAccessoryLost: %s", TAG, RedactionUtil.redact(accessory));
        this.notificationInteractor.cancel(getNotificationId(accessory));
    }
}
