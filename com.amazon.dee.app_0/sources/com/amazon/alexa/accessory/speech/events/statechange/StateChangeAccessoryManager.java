package com.amazon.alexa.accessory.speech.events.statechange;

import android.util.Pair;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.accessory.speechapi.events.AccessoryEventSender;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.google.gson.JsonIOException;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.BiPredicate;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public class StateChangeAccessoryManager {
    private static final BiPredicate<List<Device.DeviceInformation>, List<Device.DeviceInformation>> DEVICE_PRESENCE_STATUS_FILTER = $$Lambda$StateChangeAccessoryManager$QcUGQd_qzx6DsQS453AtkmDuyE.INSTANCE;
    private static final String TAG = "StateChangeAccessoryManager";
    private boolean activated;
    private final Map<String, List<Device.DeviceInformation>> cachedDeviceInfo;
    private final Object cachedDeviceInfoLock;
    private final CompositeDisposable compositeDisposable;
    private final FeatureChecker featureChecker;
    private final RegistrationSupplier registrationSupplier;
    private final SessionListener sessionListener;
    private final SessionSupplier sessionSupplier;
    private final StateChangeEventGenerator stateChangeEventGenerator;
    private final AccessoryEventSender stateChangeEventSender;
    private final UserSupplier userSupplier;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public final class SessionListener extends AccessorySessionListener {
        private SessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(Accessory accessory) {
            if (StateChangeAccessoryManager.this.featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_ANDROID_FOLLOWME)) {
                StateChangeAccessoryManager.this.monitorEventsForSession(StateChangeAccessoryManager.this.sessionSupplier.getSession(accessory));
            } else {
                Logger.d("%s: onAccessorySessionConnected ALEXA_ACCESSORY_ANDROID_FOLLOWME feature is not enabled.", StateChangeAccessoryManager.TAG);
            }
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(Accessory accessory) {
            if (StateChangeAccessoryManager.this.featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_ANDROID_FOLLOWME)) {
                StateChangeAccessoryManager.this.handleReleasedAccessory(accessory);
            } else {
                Logger.d("%s: onAccessorySessionReleased ALEXA_ACCESSORY_ANDROID_FOLLOWME feature is not enabled.", StateChangeAccessoryManager.TAG);
            }
        }
    }

    public StateChangeAccessoryManager(SessionSupplier sessionSupplier, AccessoryEventSender accessoryEventSender, StateChangeEventGenerator stateChangeEventGenerator, AccessoryMetricsService accessoryMetricsService, UserSupplier userSupplier, RegistrationSupplier registrationSupplier, FeatureChecker featureChecker) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(accessoryEventSender, "stateChangeEventSender");
        Preconditions.notNull(stateChangeEventGenerator, "stateChangeEventGenerator");
        Preconditions.notNull(accessoryMetricsService, "accessoryMetricsService");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(registrationSupplier, "registrationSupplier");
        Preconditions.notNull(featureChecker, "featureChecker");
        AccessoryMetricsServiceHolder.getInstance().set(accessoryMetricsService);
        this.sessionSupplier = sessionSupplier;
        this.stateChangeEventSender = accessoryEventSender;
        this.stateChangeEventGenerator = stateChangeEventGenerator;
        this.userSupplier = userSupplier;
        this.registrationSupplier = registrationSupplier;
        this.featureChecker = featureChecker;
        this.sessionListener = new SessionListener();
        this.compositeDisposable = new CompositeDisposable();
        this.cachedDeviceInfoLock = new Object();
        this.cachedDeviceInfo = new HashMap();
    }

    private String getDeviceTypeForReportingMetrics(List<Device.DeviceInformation> list) {
        Collections.sort(list, $$Lambda$StateChangeAccessoryManager$HHw1gzKuW5s4JRDBoJHEKvak_7s.INSTANCE);
        return !list.isEmpty() ? list.get(0).getDeviceType() : "Unknown";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReleasedAccessory(Accessory accessory) {
        List<Device.DeviceInformation> list;
        synchronized (this.cachedDeviceInfoLock) {
            list = this.cachedDeviceInfo.get(accessory.getAddress());
            this.cachedDeviceInfo.remove(accessory.getAddress());
        }
        if (list == null) {
            return;
        }
        Logger.d("%s: handleReleasedAccessory is called for %s", TAG, accessory.getAddress());
        reportDeviceStateChange(list, accessory, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getDeviceTypeForReportingMetrics$7(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation2.getDeviceId() - deviceInformation.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$0(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$1(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$reportDeviceStateChange$6(String str, Throwable th) throws Throwable {
        Logger.e("%s: Error querying device registration", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(AccessoryMetricsConstants.STATE_CHANGE_DATA_LOOKUP_ERROR, str, true, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$2(List list, List list2) throws Throwable {
        if (list.size() != 0 && list2.size() != 0 && list.size() == list2.size()) {
            Collections.sort(list, $$Lambda$StateChangeAccessoryManager$mXaY4YVnAB93RTCvjSRKobH1QU.INSTANCE);
            Collections.sort(list2, $$Lambda$StateChangeAccessoryManager$UeczBWvxPm7SRZTP_YiZzC1kyVA.INSTANCE);
            for (int i = 0; i < list.size(); i++) {
                Device.DeviceStatus status = ((Device.DeviceInformation) list.get(i)).getStatus();
                Device.DeviceStatus status2 = ((Device.DeviceInformation) list2.get(i)).getStatus();
                if (status.getPresence() != status2.getPresence() && (status.getPresence() == Device.DevicePresence.DEVICE_PRESENCE_ACTIVE || status2.getPresence() == Device.DevicePresence.DEVICE_PRESENCE_ACTIVE)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void monitorEventsForSession(final AccessorySession accessorySession) {
        this.compositeDisposable.add(accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().map($$Lambda$tQ3kld_516gDzmPlk1q2iyXOMA.INSTANCE).distinctUntilChanged(DEVICE_PRESENCE_STATUS_FILTER).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateChangeAccessoryManager$3t-pomUglGc707T9stksXv-wf9s
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateChangeAccessoryManager.this.lambda$monitorEventsForSession$3$StateChangeAccessoryManager(accessorySession, (ArrayList) obj);
            }
        }, $$Lambda$StateChangeAccessoryManager$PJmLflQnAM1uwGwakry5dHiSw.INSTANCE));
    }

    private void reportDeviceStateChange(final List<Device.DeviceInformation> list, Accessory accessory, final boolean z) {
        Logger.d("%s: Is Accessory Connected: [%s], Devices: [%s]", TAG, Boolean.valueOf(z), list);
        final String deviceTypeForReportingMetrics = getDeviceTypeForReportingMetrics(list);
        this.compositeDisposable.add(Single.zip(this.registrationSupplier.getDeviceRegistration(accessory.getAddress()), this.userSupplier.queryUser().firstOrError(), $$Lambda$hgBbwVhjQqjN4bpwMBpyDBGTggc.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateChangeAccessoryManager$Zb341E3vceHxZfshurEQeqM3JGs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateChangeAccessoryManager.this.lambda$reportDeviceStateChange$5$StateChangeAccessoryManager(list, z, deviceTypeForReportingMetrics, (Pair) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateChangeAccessoryManager$17gqF6OGM3MwEQ99lccmjIw5Gss
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateChangeAccessoryManager.lambda$reportDeviceStateChange$6(deviceTypeForReportingMetrics, (Throwable) obj);
            }
        }));
    }

    public void activate() {
        Preconditions.mainThread();
        if (this.activated) {
            return;
        }
        this.activated = true;
        Logger.d("%s Activate", TAG);
        this.sessionSupplier.addSessionListener(this.sessionListener);
    }

    public void deactivate() {
        Preconditions.mainThread();
        if (!this.activated) {
            return;
        }
        this.activated = false;
        Logger.d("%s Deactivate", TAG);
        this.sessionSupplier.removeSessionListener(this.sessionListener);
        this.compositeDisposable.clear();
    }

    public void generateAndSendEvent(List<Device.DeviceInformation> list, String str, boolean z, DeviceRegistration deviceRegistration, long j, String str2) {
        try {
            this.stateChangeEventSender.sendEvent(this.stateChangeEventGenerator.generatePayload(list, str, z, deviceRegistration, j), str2);
        } catch (JsonIOException unused) {
            Logger.e("%s: Failed to generate state change payload", TAG);
            AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(AccessoryMetricsConstants.STATE_CHANGE_PAYLOAD_CONVERSION_ERROR, str2, true, null);
        }
    }

    public /* synthetic */ void lambda$monitorEventsForSession$3$StateChangeAccessoryManager(AccessorySession accessorySession, ArrayList arrayList) throws Throwable {
        synchronized (this.cachedDeviceInfoLock) {
            this.cachedDeviceInfo.put(accessorySession.getAddress(), arrayList);
        }
        Logger.d("%s: monitorEventsForSession received event", TAG);
        reportDeviceStateChange(arrayList, accessorySession.getAccessory(), accessorySession.isConnected());
    }

    public /* synthetic */ void lambda$reportDeviceStateChange$5$StateChangeAccessoryManager(List list, boolean z, String str, Pair pair) throws Throwable {
        generateAndSendEvent(list, ((User) pair.second).getDirectedCustomerId(), z, (DeviceRegistration) pair.first, System.currentTimeMillis(), str);
    }
}
