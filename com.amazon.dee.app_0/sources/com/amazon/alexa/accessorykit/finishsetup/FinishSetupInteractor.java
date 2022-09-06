package com.amazon.alexa.accessorykit.finishsetup;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryExplorer;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.accessorykit.finishsetup.FinishSetupInteractor;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public final class FinishSetupInteractor {
    private static final String TAG = "FinishSetupInteractor:";
    private final AccessoryExplorer accessoryExplorer;
    private final Set<String> blockListedDeviceTypes;
    private final Set<String> excludedDeviceTypes;
    private AccessoryExplorer.Observer finishSetupAccessoryExplorerObserver;
    private AccessorySessionListener finishSetupAccessorySessionListener;
    private boolean initialized;
    private final Set<Accessory> knownOobedAccessorySet;
    private final SessionSupplier sessionSupplier;
    private final FinishSetupViewCoordinator viewCoordinator;

    /* loaded from: classes6.dex */
    public static final class Builder {
        AccessoryExplorer accessoryExplorer;
        Set<String> blockListedDeviceTypes;
        Set<String> excludedDeviceTypes;
        SessionSupplier sessionSupplier;
        FinishSetupViewCoordinator viewCoordinator;

        public FinishSetupInteractor build() {
            Preconditions.notNull(this.accessoryExplorer, "accessoryExplorer");
            Preconditions.notNull(this.sessionSupplier, "sessionSupplier");
            Preconditions.notNull(this.viewCoordinator, "viewCoordinator");
            if (this.blockListedDeviceTypes == null) {
                this.blockListedDeviceTypes = new HashSet(Arrays.asList(FinishSetupConstants.DEFAULT_BLOCKLISTED_DEVICE_TYPES));
            }
            if (this.excludedDeviceTypes == null) {
                this.excludedDeviceTypes = new HashSet(Arrays.asList(FinishSetupConstants.DEFAULT_EXCLUDED_DEVICE_TYPES));
            }
            return new FinishSetupInteractor(this);
        }

        public Builder setAccessoryExplorer(AccessoryExplorer accessoryExplorer) {
            this.accessoryExplorer = accessoryExplorer;
            return this;
        }

        public Builder setBlocklistedDeviceTypes(Set<String> set) {
            this.blockListedDeviceTypes = set;
            return this;
        }

        public Builder setExcludedDeviceTypes(Set<String> set) {
            this.excludedDeviceTypes = set;
            return this;
        }

        public Builder setSessionSupplier(SessionSupplier sessionSupplier) {
            this.sessionSupplier = sessionSupplier;
            return this;
        }

        public Builder setViewCoordinator(FinishSetupViewCoordinator finishSetupViewCoordinator) {
            this.viewCoordinator = finishSetupViewCoordinator;
            return this;
        }

        private Builder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class FinishSetupAccessoryExplorerObserver implements AccessoryExplorer.Observer {
        private FinishSetupAccessoryExplorerObserver() {
        }

        private FinishSetupEvent buildEvent(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
            Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
            Preconditions.notNull(accessoryInquiryRecord, "record");
            Device extractDeviceWithHighestId = extractDeviceWithHighestId(accessoryInquiryRecord.getDevices());
            return new FinishSetupEvent(accessory, extractDeviceWithHighestId.getType(), extractDeviceWithHighestId.getSerialNumber(), extractDeviceWithHighestId.getColor());
        }

        private boolean containsExcludedDeviceType(AccessoryInquiryRecord accessoryInquiryRecord) {
            for (Device device : accessoryInquiryRecord.getDevices()) {
                if (FinishSetupInteractor.this.excludedDeviceTypes.contains(device.getType())) {
                    return true;
                }
            }
            return false;
        }

        private Device extractDeviceWithHighestId(Set<Device> set) {
            return (Device) Collections.max(set, $$Lambda$FinishSetupInteractor$FinishSetupAccessoryExplorerObserver$ewBEcbXuzJacEdrCckMzHjlCf4.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ int lambda$extractDeviceWithHighestId$2(Device device, Device device2) {
            return device.getDeviceId().intValue() - device2.getDeviceId().intValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onUnknownAccessoryFound$1(Accessory accessory, Throwable th) throws Throwable {
            Logger.d("%s ERROR: Unable to present finish setup view for %s.", th, FinishSetupInteractor.TAG, accessory);
            Logger.e("%s Unable to present finish setup view for %s.", th, FinishSetupInteractor.TAG, RedactionUtil.redact(accessory));
        }

        public /* synthetic */ void lambda$onUnknownAccessoryFound$0$FinishSetupInteractor$FinishSetupAccessoryExplorerObserver(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord, Boolean bool) throws Throwable {
            if (!bool.booleanValue()) {
                FinishSetupInteractor.this.viewCoordinator.present(buildEvent(accessory, accessoryInquiryRecord));
            } else {
                Logger.d("%s a blocklisted session is connected, ignoring present event for %s", FinishSetupInteractor.TAG, accessory);
            }
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onKnownAccessoryFound(Accessory accessory) {
            FinishSetupInteractor.this.knownOobedAccessorySet.add(accessory);
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onKnownAccessoryLost(Accessory accessory) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        @SuppressLint({"CheckResult"})
        public void onUnknownAccessoryFound(final Accessory accessory, final AccessoryInquiryRecord accessoryInquiryRecord) {
            if (accessoryInquiryRecord.getDevices().isEmpty() || containsExcludedDeviceType(accessoryInquiryRecord) || FinishSetupInteractor.this.knownOobedAccessorySet.contains(accessory)) {
                return;
            }
            FinishSetupInteractor.this.isBlocklistedSessionConnected().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$FinishSetupInteractor$FinishSetupAccessoryExplorerObserver$J1IA3-Jj_GCbK8WJ-RA3wsk7C5Q
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FinishSetupInteractor.FinishSetupAccessoryExplorerObserver.this.lambda$onUnknownAccessoryFound$0$FinishSetupInteractor$FinishSetupAccessoryExplorerObserver(accessory, accessoryInquiryRecord, (Boolean) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$FinishSetupInteractor$FinishSetupAccessoryExplorerObserver$3O3M5cd29uLTNNWvuryqBvWtfQ4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FinishSetupInteractor.FinishSetupAccessoryExplorerObserver.lambda$onUnknownAccessoryFound$1(Accessory.this, (Throwable) obj);
                }
            });
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onUnknownAccessoryLost(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
            if (accessoryInquiryRecord.getDevices().isEmpty() || containsExcludedDeviceType(accessoryInquiryRecord)) {
                return;
            }
            FinishSetupInteractor.this.knownOobedAccessorySet.remove(accessory);
            FinishSetupInteractor.this.viewCoordinator.dismiss(buildEvent(accessory, accessoryInquiryRecord));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class FinishSetupAccessorySessionListener extends AccessorySessionListener {
        private FinishSetupAccessorySessionListener() {
        }

        public /* synthetic */ void lambda$onAccessorySessionConnected$0$FinishSetupInteractor$FinishSetupAccessorySessionListener(Boolean bool) throws Throwable {
            if (bool.booleanValue()) {
                FinishSetupInteractor.this.viewCoordinator.dismissAll();
            }
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        @SuppressLint({"CheckResult"})
        public void onAccessorySessionConnected(Accessory accessory) {
            super.onAccessorySessionConnected(accessory);
            AccessorySession session = FinishSetupInteractor.this.sessionSupplier.getSession(accessory);
            if (session == null) {
                return;
            }
            FinishSetupInteractor.this.isBlocklistedSession(session).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$FinishSetupInteractor$FinishSetupAccessorySessionListener$k0fpPZF7Eduw8WKMn1_oVSIq4J4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FinishSetupInteractor.FinishSetupAccessorySessionListener.this.lambda$onAccessorySessionConnected$0$FinishSetupInteractor$FinishSetupAccessorySessionListener((Boolean) obj);
                }
            }, $$Lambda$FinishSetupInteractor$FinishSetupAccessorySessionListener$7m0pM9Uv_Ltaa5bg39OZnb7iSsQ.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<Boolean> isBlocklistedSession(AccessorySession accessorySession) {
        Preconditions.notNull(accessorySession, "session");
        Observable map = accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().flatMapObservable($$Lambda$1bAoveU_BkTObiBkj7zXNC5UkA.INSTANCE).map($$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ.INSTANCE);
        final Set<String> set = this.blockListedDeviceTypes;
        set.getClass();
        return map.filter(new Predicate() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$HBrLM3JAga5Hf67ouF9vQ9t5KyA
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return set.contains((String) obj);
            }
        }).toList().map($$Lambda$FinishSetupInteractor$zlbTFZ0pf3T3D4i20JWFUR9LNo.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<Boolean> isBlocklistedSessionConnected() {
        return Observable.fromIterable(this.sessionSupplier.getActiveSessions()).flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$FinishSetupInteractor$40dKxX_NSFpLzbckytt3zR_Et3o
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FinishSetupInteractor.this.lambda$isBlocklistedSessionConnected$0$FinishSetupInteractor((AccessorySession) obj);
            }
        }).filter($$Lambda$FinishSetupInteractor$0sAHpKfROwPD_yt_m4MmOXpBG1Q.INSTANCE).first(false);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void activate() {
        Preconditions.mainThread();
        if (this.initialized) {
            return;
        }
        this.initialized = true;
        this.viewCoordinator.activate();
        this.accessoryExplorer.discover(this.finishSetupAccessoryExplorerObserver);
        this.sessionSupplier.addSessionListener(this.finishSetupAccessorySessionListener);
    }

    public void deactivate() {
        Preconditions.mainThread();
        if (!this.initialized) {
            return;
        }
        this.initialized = false;
        this.viewCoordinator.deactivate();
        this.accessoryExplorer.cancel(this.finishSetupAccessoryExplorerObserver);
        this.sessionSupplier.removeSessionListener(this.finishSetupAccessorySessionListener);
    }

    public /* synthetic */ ObservableSource lambda$isBlocklistedSessionConnected$0$FinishSetupInteractor(AccessorySession accessorySession) throws Throwable {
        return isBlocklistedSession(accessorySession).toObservable();
    }

    private FinishSetupInteractor(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.accessoryExplorer = builder.accessoryExplorer;
        this.sessionSupplier = builder.sessionSupplier;
        this.viewCoordinator = builder.viewCoordinator;
        this.blockListedDeviceTypes = builder.blockListedDeviceTypes;
        this.excludedDeviceTypes = builder.excludedDeviceTypes;
        this.initialized = false;
        this.finishSetupAccessoryExplorerObserver = new FinishSetupAccessoryExplorerObserver();
        this.finishSetupAccessorySessionListener = new FinishSetupAccessorySessionListener();
        this.knownOobedAccessorySet = new HashSet();
    }
}
