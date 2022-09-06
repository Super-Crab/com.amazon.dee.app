package com.amazon.alexa.accessorykit.finishsetup;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.MetricsUtils;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.alexa.accessorykit.ApplicationLifecycleObserverEventEmitter;
import com.amazon.alexa.accessorykit.finishsetup.bridge.FinishSetupModule;
import com.amazon.alexa.accessorykit.finishsetup.metrics.DefaultFinishSetupMetricsRecorder;
import com.amazon.alexa.accessorykit.finishsetup.metrics.FinishSetupMetricsRecorder;
import com.amazon.alexa.accessorykit.finishsetup.persistence.FinishSetupRecord;
import com.amazon.alexa.accessorykit.finishsetup.persistence.FinishSetupRecordSupplier;
import com.amazon.alexa.accessorykit.finishsetup.presenters.FASCardPresenter;
import com.amazon.alexa.accessorykit.finishsetup.presenters.FinishSetupPresenter;
import com.amazon.alexa.accessorykit.interprocess.utils.IPCUtils;
import com.amazon.alexa.accessorykit.utils.Clock;
import com.amazon.alexa.accessorykit.utils.DefaultClock;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public class InterProcessFASViewCoordinator implements FinishSetupViewCoordinator {
    private static final String TAG = "InterProcessFASViewCoordinator:";
    private ActivityManager activityManager;
    private final long blacklistDurationForNotification;
    private final Clock clock;
    private Context context;
    private final DeviceSupplierV2 deviceSupplier;
    private final FinishSetupRecordSupplier finishSetupRecordSupplier;
    private final int maxAttemptsForNotification;
    private final FinishSetupMetricsRecorder metricsRecorder;
    private final FinishSetupPresenter notificationPresenter;
    private Disposable observeNewlySetupDevicesDisposable;
    private final Set<FinishSetupEvent> finishSetupEventSet = Collections.synchronizedSet(new LinkedHashSet());
    private boolean initialized = false;
    private final FinishSetupEventReceiver finishSetupEventReceiver = new FinishSetupEventReceiver();
    private final ApplicationLifecycleEventReceiver applicationLifecycleEventReceiver = new ApplicationLifecycleEventReceiver();

    @VisibleForTesting
    /* loaded from: classes6.dex */
    final class ApplicationLifecycleEventReceiver extends BroadcastReceiver {
        ApplicationLifecycleEventReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!ApplicationLifecycleObserverEventEmitter.INTENT_ACTION_APPLICATION_LIFECYCLE.equals(intent.getAction())) {
                return;
            }
            String stringExtra = intent.getStringExtra(ApplicationLifecycleObserverEventEmitter.KEY_LIFECYCLE_EVENT);
            if (ApplicationLifecycleObserverEventEmitter.LIFECYCLE_EVENT_START.equals(stringExtra)) {
                Logger.d("%s app brought to foreground", InterProcessFASViewCoordinator.TAG);
                InterProcessFASViewCoordinator.this.presentOldestEventAsCard();
            } else if (!ApplicationLifecycleObserverEventEmitter.LIFECYCLE_EVENT_STOP.equals(stringExtra)) {
            } else {
                Logger.d("%s app sent to background", InterProcessFASViewCoordinator.TAG);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class Builder {
        ApplicationLifecycleService applicationLifecycleService;
        Clock clock;
        Context context;
        DeviceSupplierV2 deviceSupplier;
        Integer maxPresentationAttemptsAllowed;
        FinishSetupMetricsRecorder metricsRecorder;
        FinishSetupPresenter notificationPresenter;
        Long presentationCooldownDuration;
        FinishSetupRecordSupplier recordSupplier;
        FinishSetupPresenter takeoverCardPresenter;

        public InterProcessFASViewCoordinator build() {
            Preconditions.notNull(this.deviceSupplier, "deviceSupplier");
            Preconditions.notNull(this.notificationPresenter, "notificationPresenter");
            Preconditions.notNull(this.context, "context");
            if (this.maxPresentationAttemptsAllowed == null) {
                this.maxPresentationAttemptsAllowed = 3;
            }
            if (this.presentationCooldownDuration == null) {
                this.presentationCooldownDuration = Long.valueOf(FinishSetupConstants.DEFAULT_PRESENTATION_COOLDOWN_DURATION_IN_MILLISECONDS);
            }
            if (this.clock == null) {
                this.clock = new DefaultClock();
            }
            if (this.metricsRecorder == null) {
                this.metricsRecorder = new DefaultFinishSetupMetricsRecorder(AccessoryMetricsServiceHolder.getInstance().get());
            }
            return new InterProcessFASViewCoordinator(this);
        }

        public Builder setApplicationLifecycleService(ApplicationLifecycleService applicationLifecycleService) {
            this.applicationLifecycleService = applicationLifecycleService;
            return this;
        }

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setDeviceSupplier(DeviceSupplierV2 deviceSupplierV2) {
            this.deviceSupplier = deviceSupplierV2;
            return this;
        }

        public Builder setFinishSetupRecordSupplier(FinishSetupRecordSupplier finishSetupRecordSupplier) {
            this.recordSupplier = finishSetupRecordSupplier;
            return this;
        }

        public Builder setMaxPresentationAttemptsAllowed(Integer num) {
            this.maxPresentationAttemptsAllowed = num;
            return this;
        }

        public Builder setMetricsRecorder(FinishSetupMetricsRecorder finishSetupMetricsRecorder) {
            this.metricsRecorder = finishSetupMetricsRecorder;
            return this;
        }

        public Builder setNotificationPresenter(FinishSetupPresenter finishSetupPresenter) {
            this.notificationPresenter = finishSetupPresenter;
            return this;
        }

        public Builder setPresentationCooldownDuration(long j) {
            this.presentationCooldownDuration = Long.valueOf(j);
            return this;
        }

        public Builder setTakeoverCardPresenter(FinishSetupPresenter finishSetupPresenter) {
            this.takeoverCardPresenter = finishSetupPresenter;
            return this;
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    final class FinishSetupEventReceiver extends BroadcastReceiver {
        FinishSetupEventReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!FinishSetupModule.INTENT_FINISH_SETUP_ACTION.equals(intent.getAction())) {
                return;
            }
            String stringExtra = intent.getStringExtra("event_type");
            FinishSetupEvent finishSetupEvent = new FinishSetupEvent(intent.getBundleExtra("finish_setup_event"));
            if (FinishSetupModule.EVENT_ON_BLACKLISTED.equals(stringExtra)) {
                InterProcessFASViewCoordinator.this.dismiss(finishSetupEvent);
                InterProcessFASViewCoordinator.this.blacklistUserRecord(finishSetupEvent);
            } else if (!FinishSetupModule.EVENT_ON_DISMISS.equals(stringExtra) && !FinishSetupModule.EVENT_ON_CONTINUE.equals(stringExtra)) {
                Logger.d("%s unknown eventType: %s in FinishSetupEventReceiver.", InterProcessFASViewCoordinator.TAG, stringExtra);
            } else {
                InterProcessFASViewCoordinator.this.dismiss(finishSetupEvent);
            }
        }
    }

    public InterProcessFASViewCoordinator(Builder builder) {
        this.deviceSupplier = builder.deviceSupplier;
        this.notificationPresenter = builder.notificationPresenter;
        this.finishSetupRecordSupplier = builder.recordSupplier;
        this.maxAttemptsForNotification = builder.maxPresentationAttemptsAllowed.intValue();
        this.blacklistDurationForNotification = builder.presentationCooldownDuration.longValue();
        this.context = builder.context;
        this.clock = builder.clock;
        this.metricsRecorder = builder.metricsRecorder;
        this.activityManager = (ActivityManager) this.context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void blacklistUserRecord(FinishSetupEvent finishSetupEvent) {
        FinishSetupRecord finishSetupRecord = this.finishSetupRecordSupplier.get(finishSetupEvent.getSerialNumber());
        if (finishSetupRecord == null) {
            finishSetupRecord = new FinishSetupRecord(finishSetupEvent.getSerialNumber(), 1, this.clock.elapsedRealtime(), true);
        } else {
            finishSetupRecord.blacklistedByUser();
        }
        this.finishSetupRecordSupplier.set(finishSetupRecord);
    }

    private Single<Boolean> isValidAccordingToRules(final FinishSetupEvent finishSetupEvent, final long j) {
        return this.deviceSupplier.hasDeviceGroup(finishSetupEvent.getAccessory().getAddress()).map(new Function() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$InterProcessFASViewCoordinator$Dx4clwki3sXAz7iZjm4VvMSdG0w
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return InterProcessFASViewCoordinator.this.lambda$isValidAccordingToRules$2$InterProcessFASViewCoordinator(finishSetupEvent, j, (Boolean) obj);
            }
        });
    }

    static /* synthetic */ Iterable lambda$observeNewlySetupDevices$3(List list) throws Throwable {
        return list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$observeNewlySetupDevices$4(DeviceGroup deviceGroup) throws Throwable {
        return !deviceGroup.getDevices().isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$presentViewIfValid$1(FinishSetupEvent finishSetupEvent, Throwable th) throws Throwable {
        Logger.d("%s ERROR: Unable to present FAS view for %s.", th, TAG, finishSetupEvent.getAccessory());
        Logger.e("%s Unable to present FAS view for %s.", th, TAG, RedactionUtil.redact(finishSetupEvent.getAccessory()));
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private Disposable observeNewlySetupDevices() {
        return this.deviceSupplier.queryDeviceGroups().flatMapIterable($$Lambda$InterProcessFASViewCoordinator$JsO1ZfYJodMwR2Xcd8iADB0xGyk.INSTANCE).filter($$Lambda$InterProcessFASViewCoordinator$9fSB7_NEkNVrbABCXLZ3jAKeCOA.INSTANCE).filter(new Predicate() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$InterProcessFASViewCoordinator$c0_gCTjZtbuSPRrO0N86ZskCsPM
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return InterProcessFASViewCoordinator.this.lambda$observeNewlySetupDevices$5$InterProcessFASViewCoordinator((DeviceGroup) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$InterProcessFASViewCoordinator$CclQI50--ujzRZcmJ8Q1wBei75E
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InterProcessFASViewCoordinator.this.lambda$observeNewlySetupDevices$6$InterProcessFASViewCoordinator((DeviceGroup) obj);
            }
        }, $$Lambda$InterProcessFASViewCoordinator$GOTIT3s0gB3gSCaBByOGxlV90d4.INSTANCE);
    }

    private void presentCardIfValid(FinishSetupEvent finishSetupEvent) {
        FinishSetupRecord finishSetupRecord = this.finishSetupRecordSupplier.get(finishSetupEvent.getSerialNumber());
        if (finishSetupRecord != null && finishSetupRecord.isBlacklistedByUser()) {
            Logger.d("%s Cannot present event. Explicitly blacklisted by user.", TAG);
        } else {
            sendFASCardEvent(FASCardPresenter.ACTION_PRESENT, finishSetupEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void presentOldestEventAsCard() {
        synchronized (this.finishSetupEventSet) {
            Iterator<FinishSetupEvent> it2 = this.finishSetupEventSet.iterator();
            if (it2.hasNext()) {
                presentCardIfValid(it2.next());
            }
        }
    }

    @SuppressLint({"CheckResult"})
    private void presentViewIfValid(final FinishSetupEvent finishSetupEvent, final FinishSetupPresenter finishSetupPresenter) {
        final long elapsedRealtime = this.clock.elapsedRealtime();
        isValidAccordingToRules(finishSetupEvent, elapsedRealtime).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$InterProcessFASViewCoordinator$1BemAgESZERzTU_yX-SPJRRMrKo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InterProcessFASViewCoordinator.this.lambda$presentViewIfValid$0$InterProcessFASViewCoordinator(finishSetupEvent, finishSetupPresenter, elapsedRealtime, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$InterProcessFASViewCoordinator$Vx-bMxMkQv30rtkc7ZpgMGfTsO4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InterProcessFASViewCoordinator.lambda$presentViewIfValid$1(FinishSetupEvent.this, (Throwable) obj);
            }
        });
    }

    private void sendFASCardEvent(String str, FinishSetupEvent finishSetupEvent) {
        this.context.sendBroadcast(new Intent(FASCardPresenter.INTENT_ACTION_FAS_CARD_PRESENTER).setPackage(AccessoriesFactory.getAppName()).setFlags(268435456).putExtra(FASCardPresenter.KEY_ACTION_TYPE, str).putExtra("finish_setup_event", finishSetupEvent.toBundle()), "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }

    private void updateRecordAfterViewPresented(FinishSetupEvent finishSetupEvent, long j) {
        FinishSetupRecord finishSetupRecord = this.finishSetupRecordSupplier.get(finishSetupEvent.getSerialNumber());
        if (finishSetupRecord == null) {
            this.finishSetupRecordSupplier.set(new FinishSetupRecord(finishSetupEvent.getSerialNumber(), 1, j, false));
        } else {
            this.finishSetupRecordSupplier.set(finishSetupRecord.incrementViewPresentedCount().setLastTimeViewPresented(j));
        }
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.FinishSetupViewCoordinator
    public void activate() {
        if (this.initialized) {
            return;
        }
        this.initialized = true;
        Logger.d("%s activating view coordinator.", TAG);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FinishSetupModule.INTENT_FINISH_SETUP_ACTION);
        this.context.registerReceiver(this.finishSetupEventReceiver, intentFilter, "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION", null);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(ApplicationLifecycleObserverEventEmitter.INTENT_ACTION_APPLICATION_LIFECYCLE);
        this.context.registerReceiver(this.applicationLifecycleEventReceiver, intentFilter2, "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION", null);
        this.observeNewlySetupDevicesDisposable = observeNewlySetupDevices();
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.FinishSetupViewCoordinator
    public void deactivate() {
        if (!this.initialized) {
            return;
        }
        this.initialized = false;
        Logger.d("%s deactivating view coordinator.", TAG);
        this.context.unregisterReceiver(this.finishSetupEventReceiver);
        this.context.unregisterReceiver(this.applicationLifecycleEventReceiver);
        ObservableUtils.dispose(this.observeNewlySetupDevicesDisposable);
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.FinishSetupViewCoordinator
    public void dismiss(FinishSetupEvent finishSetupEvent) {
        Logger.d("%s received FAS dismiss event.", TAG);
        if (this.finishSetupEventSet.contains(finishSetupEvent)) {
            Logger.d("%s dismissing fas event: %s", TAG, finishSetupEvent);
            this.finishSetupEventSet.remove(finishSetupEvent);
            this.notificationPresenter.dismiss(finishSetupEvent);
            sendFASCardEvent(FASCardPresenter.ACTION_DISMISS, finishSetupEvent);
        }
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.FinishSetupViewCoordinator
    public void dismissAll() {
        Logger.d("%s dismissing all fas events.", TAG);
        synchronized (this.finishSetupEventSet) {
            Iterator<FinishSetupEvent> it2 = this.finishSetupEventSet.iterator();
            while (it2.hasNext()) {
                FinishSetupEvent next = it2.next();
                this.notificationPresenter.dismiss(next);
                sendFASCardEvent(FASCardPresenter.ACTION_DISMISS, next);
                it2.remove();
            }
        }
    }

    public /* synthetic */ Boolean lambda$isValidAccordingToRules$2$InterProcessFASViewCoordinator(FinishSetupEvent finishSetupEvent, long j, Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            Logger.d("%s Invalid event %s, Device is already set up!", TAG, finishSetupEvent.getAccessory());
            return false;
        }
        FinishSetupRecord finishSetupRecord = this.finishSetupRecordSupplier.get(finishSetupEvent.getSerialNumber());
        if (finishSetupRecord == null) {
            Logger.d("%s Valid event %s, no record exists", TAG, finishSetupEvent.getAccessory());
            return true;
        } else if (finishSetupRecord.getViewPresentedCount() >= this.maxAttemptsForNotification) {
            Logger.d("%s Invalid event %s, Max attempts have been made.", TAG, finishSetupEvent.getAccessory());
            return false;
        } else if (finishSetupRecord.isBlacklistedByUser()) {
            Logger.d("%s Invalid event %s, is blacklisted by user.", TAG, finishSetupEvent.getAccessory());
            return false;
        } else if (j - finishSetupRecord.getLastTimeViewPresented() >= this.blacklistDurationForNotification) {
            return true;
        } else {
            Logger.d("%s Invalid event %s, backoff duration hasn't expired.", TAG, finishSetupEvent.getAccessory());
            return false;
        }
    }

    public /* synthetic */ boolean lambda$observeNewlySetupDevices$5$InterProcessFASViewCoordinator(DeviceGroup deviceGroup) throws Throwable {
        return this.finishSetupRecordSupplier.has(deviceGroup.getDeviceWithHighestId().getSerialNumber());
    }

    public /* synthetic */ void lambda$observeNewlySetupDevices$6$InterProcessFASViewCoordinator(DeviceGroup deviceGroup) throws Throwable {
        this.metricsRecorder.recordKnownDeviceSetupCompleted(MetricsUtils.getDeviceTypeOfHighestId(deviceGroup));
        this.finishSetupRecordSupplier.remove(deviceGroup.getDeviceWithHighestId().getSerialNumber());
        dismiss(new FinishSetupEvent(deviceGroup));
    }

    public /* synthetic */ void lambda$presentViewIfValid$0$InterProcessFASViewCoordinator(FinishSetupEvent finishSetupEvent, FinishSetupPresenter finishSetupPresenter, long j, Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            Logger.d("%s presenting fas event for %s", TAG, finishSetupEvent.getAccessory());
            finishSetupPresenter.present(finishSetupEvent);
            updateRecordAfterViewPresented(finishSetupEvent, j);
        }
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.FinishSetupViewCoordinator
    public void present(FinishSetupEvent finishSetupEvent) {
        Logger.d("%s received FAS present event: %s", TAG, finishSetupEvent);
        if (this.finishSetupEventSet.contains(finishSetupEvent)) {
            Logger.d("%s active event set already contained the event, ignoring event %s", TAG, finishSetupEvent);
            return;
        }
        this.finishSetupEventSet.add(finishSetupEvent);
        if (IPCUtils.isMainProcessAliveAndForeground(this.activityManager)) {
            Logger.d("%s presenting FAS Card for event: %s", TAG, finishSetupEvent);
            presentCardIfValid(finishSetupEvent);
        } else if (!this.notificationPresenter.canPresent()) {
        } else {
            Logger.d("%s presenting FAS notification event: %s", TAG, finishSetupEvent);
            presentViewIfValid(finishSetupEvent, this.notificationPresenter);
        }
    }
}
