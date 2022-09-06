package com.amazon.alexa.accessory.notificationpublisher;

import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.MultiDeviceDistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
/* loaded from: classes.dex */
public final class FeatureToggleModule implements FeatureToggle {
    private static final String TAG = "FeatureToggleModule";
    private static FeatureToggleModule storageInstance;
    private boolean hasRetrievedFromStorage = false;
    private boolean deviceConnected = false;
    private boolean featureEnabled = false;
    private boolean lowDistractionMode = false;
    private boolean silentDistractionMode = false;
    private boolean groupMessagesMasterToggleEnabled = false;
    private final Subject<Boolean> subject = PublishSubject.create();

    @VisibleForTesting
    FeatureToggleModule() {
    }

    protected static void assertNotMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            return;
        }
        throw new IllegalStateException("Current thread must not be main thread");
    }

    private void ensureRetrieveInitialToggleValues() {
        Log.i(TAG, "ensureRetrieveInitialToggleValues");
        if (!this.hasRetrievedFromStorage) {
            SettingsStorageModule.getInstance().migrate();
            refreshToggleValue();
        }
    }

    public static synchronized FeatureToggleModule getInstance() {
        FeatureToggleModule featureToggleModule;
        synchronized (FeatureToggleModule.class) {
            if (storageInstance == null) {
                Log.d(TAG, "getInstance - Creating new instance");
                storageInstance = new FeatureToggleModule();
            }
            featureToggleModule = storageInstance;
        }
        return featureToggleModule;
    }

    private void notifyStatusEventManagerForLowDistractionMode() {
        StatusEventManager.getInstance().onLowDistractionModeChanged(DistractionModeProvider.getCurrentDistractionMode() == 2);
    }

    private void onFeatureEnabledChanged(boolean z) {
        String str = TAG;
        Log.d(str, "onFeatureEnabledChanged " + z);
        StatusEventManager.getInstance().onFeatureToggleChanged(z);
        this.subject.onNext(Boolean.valueOf(z));
        MetricsRecorder.getInstance().recordCounter(z ? MetricsConstants.FEATURE_ON : MetricsConstants.FEATURE_OFF);
    }

    public static synchronized void releaseInstance() {
        synchronized (FeatureToggleModule.class) {
            Log.d(TAG, "releaseInstance");
            storageInstance = null;
        }
    }

    public synchronized boolean getDeviceConnectedState() {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            return !AccessoryProvider.getConnectedAccessoryList().isEmpty();
        }
        return this.deviceConnected;
    }

    public synchronized boolean getGroupMessagesMasterToggleState() {
        return this.groupMessagesMasterToggleEnabled;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (isFeatureEnabled() != false) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean hasConnectedEnabledDevices() {
        /*
            r7 = this;
            monitor-enter(r7)
            r7.ensureRetrieveInitialToggleValues()     // Catch: java.lang.Throwable -> Lcc
            boolean r0 = com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker.hasOtgVipFilterAccess()     // Catch: java.lang.Throwable -> Lcc
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L29
            java.util.List r0 = com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.getConnectedAccessoryList()     // Catch: java.lang.Exception -> L20 java.lang.Throwable -> Lcc
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Exception -> L20 java.lang.Throwable -> Lcc
            if (r0 != 0) goto L1d
            boolean r0 = r7.isFeatureEnabled()     // Catch: java.lang.Exception -> L20 java.lang.Throwable -> Lcc
            if (r0 == 0) goto L1d
            goto L1e
        L1d:
            r1 = r2
        L1e:
            monitor-exit(r7)
            return r1
        L20:
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule.TAG     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r1 = "getting forward notification to accessory failed"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r0, r1)     // Catch: java.lang.Throwable -> Lcc
            monitor-exit(r7)
            return r2
        L29:
            java.util.List r3 = com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.getConnectedAccessoryList()     // Catch: java.lang.Throwable -> Lcc
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> Lcc
            if (r3 != 0) goto L9e
            java.util.List r3 = com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.getConnectedAccessoryList()     // Catch: java.lang.Throwable -> Lcc
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> Lcc
            r4 = r2
        L3c:
            boolean r5 = r3.hasNext()     // Catch: java.lang.Throwable -> Lcc
            if (r5 == 0) goto L82
            java.lang.Object r4 = r3.next()     // Catch: java.lang.Throwable -> Lcc
            com.amazon.alexa.accessory.Accessory r4 = (com.amazon.alexa.accessory.Accessory) r4     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r4 = com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.getAccessoryDeviceType(r4)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r5 = "A3HVREY4JWAZ6K"
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch: java.lang.Throwable -> Lcc
            if (r4 != 0) goto L3c
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule.TAG     // Catch: java.lang.Throwable -> Lcc
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcc
            r3.<init>()     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r4 = "featureEnabled "
            r3.append(r4)     // Catch: java.lang.Throwable -> Lcc
            boolean r4 = r7.featureEnabled     // Catch: java.lang.Throwable -> Lcc
            r3.append(r4)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r4 = " deviceConnected "
            r3.append(r4)     // Catch: java.lang.Throwable -> Lcc
            boolean r4 = r7.deviceConnected     // Catch: java.lang.Throwable -> Lcc
            r3.append(r4)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lcc
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r0, r3)     // Catch: java.lang.Throwable -> Lcc
            boolean r0 = r7.featureEnabled     // Catch: java.lang.Throwable -> Lcc
            if (r0 == 0) goto L7f
            boolean r0 = r7.deviceConnected     // Catch: java.lang.Throwable -> Lcc
            if (r0 == 0) goto L7f
            goto L80
        L7f:
            r1 = r2
        L80:
            monitor-exit(r7)
            return r1
        L82:
            java.lang.String r3 = com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule.TAG     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r5 = "hasOtgVipFilterAccess: %s, armstrongConnected: %s"
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> Lcc
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch: java.lang.Throwable -> Lcc
            r6[r2] = r0     // Catch: java.lang.Throwable -> Lcc
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)     // Catch: java.lang.Throwable -> Lcc
            r6[r1] = r0     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r0 = java.lang.String.format(r5, r6)     // Catch: java.lang.Throwable -> Lcc
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r3, r0)     // Catch: java.lang.Throwable -> Lcc
            monitor-exit(r7)
            return r2
        L9e:
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule.TAG     // Catch: java.lang.Throwable -> Lcc
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcc
            r3.<init>()     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r4 = "featureEnabled "
            r3.append(r4)     // Catch: java.lang.Throwable -> Lcc
            boolean r4 = r7.featureEnabled     // Catch: java.lang.Throwable -> Lcc
            r3.append(r4)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r4 = " deviceConnected "
            r3.append(r4)     // Catch: java.lang.Throwable -> Lcc
            boolean r4 = r7.deviceConnected     // Catch: java.lang.Throwable -> Lcc
            r3.append(r4)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lcc
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r0, r3)     // Catch: java.lang.Throwable -> Lcc
            boolean r0 = r7.featureEnabled     // Catch: java.lang.Throwable -> Lcc
            if (r0 == 0) goto Lc9
            boolean r0 = r7.deviceConnected     // Catch: java.lang.Throwable -> Lcc
            if (r0 == 0) goto Lc9
            goto Lca
        Lc9:
            r1 = r2
        Lca:
            monitor-exit(r7)
            return r1
        Lcc:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule.hasConnectedEnabledDevices():boolean");
    }

    public synchronized boolean isFeatureEnabled() {
        assertNotMainThread();
        ensureRetrieveInitialToggleValues();
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess() && AccessoryProvider.getConnectedAccessoryList().size() > 0) {
            updateFeatureEnabled();
        }
        return this.featureEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void onConnectivityChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onConnectivityChanged -- connected: " + z);
        this.deviceConnected = z;
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess() && !AccessoryProvider.getConnectedAccessoryList().isEmpty()) {
            updateFeatureEnabled();
            notifyStatusEventManagerForLowDistractionMode();
        }
        StatusEventManager.getInstance().onDeviceConnectionChanged(getDeviceConnectedState());
        try {
            DependencyProvider.getNotificationServiceCommunicator().safeCallOnDeviceConnectionChanged(getDeviceConnectedState());
        } catch (Exception e) {
            String str2 = TAG;
            Log.w(str2, "onConnectivityChanged - Exception when updating device connection in phone notification listener service. " + e);
        }
    }

    public synchronized void onGroupMessagesMasterToggleChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onGroupMessagesMasterToggleChanged - enabled = " + z);
        this.groupMessagesMasterToggleEnabled = z;
        MetricsRecorder.getInstance().recordCounter(z ? MetricsConstants.GROUP_MESSAGES_MASTER_TOGGLE_ON : MetricsConstants.GROUP_MESSAGES_MASTER_TOGGLE_OFF);
    }

    public synchronized void onLowDistractionModeChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onLowDistractionModeChanged - enabled = " + z);
        this.lowDistractionMode = z;
        DistractionModeProvider.setLowDistractionMode(this.lowDistractionMode);
        StatusEventManager.getInstance().onLowDistractionModeChanged(z);
        MetricsRecorder.getInstance().recordCounter(z ? MetricsConstants.LOW_DISTRACTION_MODE_ON : MetricsConstants.LOW_DISTRACTION_MODE_OFF);
    }

    public synchronized void onSilentDistractionModeChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onSilentDistractionModeChanged - enabled = " + z);
        this.silentDistractionMode = z;
        DistractionModeProvider.setSilentDistractionMode(this.silentDistractionMode);
        MetricsRecorder.getInstance().recordCounter(z ? MetricsConstants.UI_SILENT_DISTRACTION_MODE_ON : MetricsConstants.UI_SILENT_DISTRACTION_MODE_OFF);
    }

    public synchronized void onToggleChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onToggleChanged -- enabled: " + z);
        ensureRetrieveInitialToggleValues();
        if (this.featureEnabled != z) {
            this.featureEnabled = z;
            onFeatureEnabledChanged(z);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.FeatureToggle
    public Observable<Boolean> queryNotificationForwardingStatus() {
        Log.i(TAG, "queryNotificationForwardingStatus()");
        return this.subject.distinctUntilChanged();
    }

    @VisibleForTesting
    void refreshToggleValue() {
        Log.i(TAG, "refreshToggleValue");
        boolean z = FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess();
        try {
            if (z) {
                MultiDeviceDistractionModeProvider.getInstance().refreshConnectedAccessory();
                notifyStatusEventManagerForLowDistractionMode();
            } else {
                this.featureEnabled = SettingsStorageModule.getInstance().getForwardNotificationToAccessoryWithDefault().booleanValue();
                String str = TAG;
                Log.i(str, "Fetching feature toggle value from storage -- featureEnabled: " + this.featureEnabled);
                this.lowDistractionMode = SettingsStorageModule.getInstance().getLowDistractionModeSettingWithDefault().booleanValue();
                DistractionModeProvider.setLowDistractionMode(this.lowDistractionMode);
                String str2 = TAG;
                Log.i(str2, "FeatureToggleModule - Low distraction mode setting fetched - " + this.lowDistractionMode);
                this.silentDistractionMode = SettingsStorageModule.getInstance().getSilentDistractionModeSettingWithDefault().booleanValue();
                DistractionModeProvider.setSilentDistractionMode(this.silentDistractionMode);
                String str3 = TAG;
                Log.i(str3, "FeatureToggleModule - Silent distraction mode setting fetched - " + this.silentDistractionMode);
            }
            this.groupMessagesMasterToggleEnabled = SettingsStorageModule.getInstance().getGroupMessagesMasterToggleSettingWithDefault().booleanValue();
            String str4 = TAG;
            Log.i(str4, "FeatureToggleModule - Group messages master toggle setting fetched - " + this.groupMessagesMasterToggleEnabled);
            this.hasRetrievedFromStorage = true;
        } catch (Exception e) {
            Log.e(TAG, "Error when fetching feature toggle value from storage.", e);
            if (z) {
                return;
            }
            this.featureEnabled = false;
        }
    }

    public synchronized void reset() {
        Log.i(TAG, "Reset");
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            this.hasRetrievedFromStorage = false;
        }
    }

    @VisibleForTesting
    void updateFeatureEnabled() {
        try {
            boolean z = this.featureEnabled;
            this.featureEnabled = SettingsStorageModule.getInstance().getForwardNotificationToAccessoryWithDefault().booleanValue();
            if (z == this.featureEnabled) {
                return;
            }
            onFeatureEnabledChanged(this.featureEnabled);
        } catch (Exception e) {
            Log.i(TAG, "Failed to get feature enabled.", e);
        }
    }

    public synchronized void onSilentDistractionModeChanged(String str, boolean z) {
        Log.i(TAG, String.format("onSilentDistractionModeChanged device - %s - enabled = %s", str, Boolean.valueOf(z)));
        DistractionModeProvider.setSilentDistractionMode(str, z);
        MetricsRecorder.getInstance().recordCounter((z ? MetricsConstants.UI_SILENT_DISTRACTION_MODE_ON : MetricsConstants.UI_SILENT_DISTRACTION_MODE_OFF).concat(".").concat(str));
    }

    public synchronized void onLowDistractionModeChanged(String str, boolean z) {
        Log.i(TAG, String.format("onLowDistractionModeChanged device - %s - enabled = %s", str, Boolean.valueOf(z)));
        DistractionModeProvider.setLowDistractionMode(str, z);
        notifyStatusEventManagerForLowDistractionMode();
        MetricsRecorder.getInstance().recordCounter((z ? MetricsConstants.LOW_DISTRACTION_MODE_ON : MetricsConstants.LOW_DISTRACTION_MODE_OFF).concat(".").concat(str));
    }

    public synchronized void onToggleChanged() {
        Log.d(TAG, "onToggleChanged");
        updateFeatureEnabled();
    }
}
