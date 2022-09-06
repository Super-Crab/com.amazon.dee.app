package com.amazon.deecomms.notifications;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccount;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.SendAnnouncementPlayback;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.media.audio.AudioRecorder;
import com.amazon.deecomms.notifications.model.announcement.AnnouncementPushNotification;
import com.android.tools.r8.GeneratedOutlineSupport;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.text.MessageFormat;
import java.util.concurrent.Callable;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class InboundAnnouncementCoordinator {
    private final CommsLogger LOG;
    private final ACMSClient client;
    private final Context context;
    private volatile boolean isPlayingAnnouncement;
    private final AudioManager mAudioManager;
    private final AudioRecorder mAudioRecorder;
    private final CallManager mCallManager;
    private final CapabilitiesManager mCapabilitiesManager;
    private final CommsIdentityManager mCommsIdentityManager;
    private final MediaRouter mMediaRouter;
    private final MetricsService mMetricService;
    private final CommsAccessorySessionListener mSessionListener;
    private final String sourceTag;

    @Inject
    public InboundAnnouncementCoordinator(@NonNull Context context, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CommsAccessorySessionListener commsAccessorySessionListener, @NonNull CallManager callManager, @NonNull AudioRecorder audioRecorder, @NonNull MetricsService metricsService, @NonNull CommsIdentityManager commsIdentityManager) {
        this.LOG = CommsLogger.getLogger(Constants.LOG_TAG, InboundAnnouncementCoordinator.class);
        this.isPlayingAnnouncement = false;
        this.sourceTag = "AnnouncementCoordinator";
        this.context = context;
        this.mAudioManager = (AudioManager) this.context.getSystemService("audio");
        this.mMediaRouter = (MediaRouter) this.context.getSystemService("media_router");
        this.mCapabilitiesManager = capabilitiesManager;
        this.mSessionListener = commsAccessorySessionListener;
        this.mCallManager = callManager;
        this.mAudioRecorder = audioRecorder;
        this.mMetricService = metricsService;
        this.client = new ACMSClient(MetricKeys.OP_GET_ACCESSORY_ANNOUNCEMENT_PERMISSIONS);
        this.mCommsIdentityManager = commsIdentityManager;
    }

    private void recordOperationMetric(String str) {
        CounterMetric counterMetric = new CounterMetric(CommsMetric.MetricType.Operational, str);
        counterMetric.setCounter(Double.valueOf(1.0d));
        this.mMetricService.recordCounterMetric(counterMetric);
    }

    SendAnnouncementPlayback createAnnouncementPlaybackRequest(AnnouncementPushNotification announcementPushNotification) {
        return new SendAnnouncementPlayback(this.mCommsIdentityManager.getDirectedId("AnnouncementCoordinator", false), announcementPushNotification.getSourceName(), announcementPushNotification.getType(), announcementPushNotification.getAnnouncementContent());
    }

    boolean dummyHasAnnouncementPermissionForAccessory(String str) {
        if (str == null) {
            return false;
        }
        try {
            return generateSettingRequest(str).authenticated().get().mo3640execute() != null;
        } catch (ServiceException e) {
            CommsLogger commsLogger = this.LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Error retrieving announcement preferences for accessory");
            outline1.append(e.getMessage());
            commsLogger.e(outline1.toString());
            return true;
        }
    }

    @VisibleForTesting
    public void enqueueAnnouncement(final AnnouncementPushNotification announcementPushNotification) {
        provideAnnouncementPermissionSettingQuery().subscribe(new Consumer() { // from class: com.amazon.deecomms.notifications.-$$Lambda$InboundAnnouncementCoordinator$82I5QTdhQM20-FD-hc7C73jTatU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InboundAnnouncementCoordinator.this.lambda$enqueueAnnouncement$3$InboundAnnouncementCoordinator(announcementPushNotification, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.deecomms.notifications.-$$Lambda$InboundAnnouncementCoordinator$lofsGVrqDr3Nbw9LV3IeIa9EkTs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InboundAnnouncementCoordinator.this.lambda$enqueueAnnouncement$4$InboundAnnouncementCoordinator((Throwable) obj);
            }
        });
        recordOperationMetric(MetricKeys.NOTIFICATION_ANN_AUTOPLAY);
    }

    IHttpClient.Request generateSettingRequest(String str) {
        return this.client.request(MessageFormat.format(AppUrl.GET_USS_SETTING, "devices", str, Constants.INBOUND_ANNOUNCEMENT_ENABLEMENT));
    }

    String getAccessoryName() {
        return this.mMediaRouter.getSelectedRoute(1).getName().toString();
    }

    @VisibleForTesting
    Single<String> getDeviceMasterIdForConnectedAccessory() {
        final String accessoryName = getAccessoryName();
        String cachedDeviceMasterId = this.mSessionListener.getCachedDeviceMasterId(accessoryName);
        if (cachedDeviceMasterId != null) {
            return Single.just(cachedDeviceMasterId);
        }
        return this.mSessionListener.provideDeviceAccountQuery(this.mSessionListener.getDeviceInformationFromTable(accessoryName)).flatMap(new Function() { // from class: com.amazon.deecomms.notifications.-$$Lambda$InboundAnnouncementCoordinator$s1HzzIq4LDg0ER_7LH_oJRH9mlQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return InboundAnnouncementCoordinator.this.lambda$getDeviceMasterIdForConnectedAccessory$0$InboundAnnouncementCoordinator(accessoryName, (DeviceAccount) obj);
            }
        });
    }

    boolean isActiveAccessorySupported() {
        String deviceType = this.mSessionListener.getDeviceInformationFromTable(getAccessoryName()).getDeviceType();
        return deviceType.equals("A303PJF6ISQ7IC") || deviceType.equals("A3IYPH06PH1HRA") || deviceType.equals("A16MZVIFVHX6P6");
    }

    @VisibleForTesting
    boolean isBluetoothActiveRoute() {
        if (sdkVersionLaterThanM()) {
            for (AudioDeviceInfo audioDeviceInfo : this.mAudioManager.getDevices(2)) {
                if (audioDeviceInfo.getType() == 8) {
                    return true;
                }
            }
            return false;
        }
        return this.mAudioManager.isBluetoothA2dpOn();
    }

    public /* synthetic */ void lambda$enqueueAnnouncement$3$InboundAnnouncementCoordinator(AnnouncementPushNotification announcementPushNotification, Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            sendAnnouncementPlaybackRequest(announcementPushNotification);
        }
    }

    public /* synthetic */ void lambda$enqueueAnnouncement$4$InboundAnnouncementCoordinator(Throwable th) throws Throwable {
        this.LOG.e(th.getMessage());
    }

    public /* synthetic */ SingleSource lambda$getDeviceMasterIdForConnectedAccessory$0$InboundAnnouncementCoordinator(String str, DeviceAccount deviceAccount) throws Throwable {
        String deviceAccountId = deviceAccount.getDeviceAccountResponse().getDeviceAccountId();
        this.mSessionListener.storeDeviceMasterId(str, deviceAccountId);
        return Single.just(deviceAccountId);
    }

    public /* synthetic */ Boolean lambda$null$1$InboundAnnouncementCoordinator(String str) throws Exception {
        return Boolean.valueOf(dummyHasAnnouncementPermissionForAccessory(str));
    }

    public /* synthetic */ SingleSource lambda$provideAnnouncementPermissionSettingQuery$2$InboundAnnouncementCoordinator(final String str) throws Throwable {
        return Single.fromCallable(new Callable() { // from class: com.amazon.deecomms.notifications.-$$Lambda$InboundAnnouncementCoordinator$VDBoTN_EWSnFzPCVjS9YvUi6kmI
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return InboundAnnouncementCoordinator.this.lambda$null$1$InboundAnnouncementCoordinator(str);
            }
        });
    }

    @VisibleForTesting
    public Single<Boolean> provideAnnouncementPermissionSettingQuery() {
        return getDeviceMasterIdForConnectedAccessory().flatMap(new Function() { // from class: com.amazon.deecomms.notifications.-$$Lambda$InboundAnnouncementCoordinator$PwzhfGqw2u2Ec_56TVfbdQLTtwc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return InboundAnnouncementCoordinator.this.lambda$provideAnnouncementPermissionSettingQuery$2$InboundAnnouncementCoordinator((String) obj);
            }
        });
    }

    boolean sdkVersionLaterThanM() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @VisibleForTesting
    IHttpClient.Response sendAnnouncementPlaybackRequest(AnnouncementPushNotification announcementPushNotification) {
        try {
            IHttpClient.Response sendAnnouncementPlayback = createAnnouncementPlaybackRequest(announcementPushNotification).sendAnnouncementPlayback();
            StringBuilder sb = new StringBuilder();
            sb.append("Announcement playback request sent with code: ");
            sb.append(sendAnnouncementPlayback.code());
            Log.i("AnnouncementCoordinator", sb.toString());
            return sendAnnouncementPlayback;
        } catch (ServiceException e) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Announcement playback request error: ");
            outline1.append(e.getMessage());
            Log.e("AnnouncementCoordinator", outline1.toString(), e);
            return null;
        }
    }

    @VisibleForTesting
    public boolean shouldPlayAnnouncementOnAccessory(AnnouncementPushNotification announcementPushNotification) {
        if (!this.mSessionListener.isAnyAccessoryConnected()) {
            return false;
        }
        return announcementPushNotification.getType().equals("announcement/audio") && this.mCapabilitiesManager.isAnnouncementAccessoriesEnabled() && (this.mCallManager.isAnyActiveCallPresent() ^ true) && (this.mAudioRecorder.isAudioMessageRecording() ^ true) && isBluetoothActiveRoute() && isActiveAccessorySupported();
    }

    @VisibleForTesting
    public InboundAnnouncementCoordinator(@NonNull Context context, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CommsAccessorySessionListener commsAccessorySessionListener, @NonNull CallManager callManager, @NonNull AudioRecorder audioRecorder, @NonNull MetricsService metricsService, @NonNull ACMSClient aCMSClient, @NonNull AudioManager audioManager, @NonNull MediaRouter mediaRouter, @NonNull CommsIdentityManager commsIdentityManager) {
        this.LOG = CommsLogger.getLogger(Constants.LOG_TAG, InboundAnnouncementCoordinator.class);
        this.isPlayingAnnouncement = false;
        this.sourceTag = "AnnouncementCoordinator";
        this.context = context;
        this.mCapabilitiesManager = capabilitiesManager;
        this.mSessionListener = commsAccessorySessionListener;
        this.mCallManager = callManager;
        this.mAudioRecorder = audioRecorder;
        this.mMetricService = metricsService;
        this.client = aCMSClient;
        this.mAudioManager = audioManager;
        this.mMediaRouter = mediaRouter;
        this.mCommsIdentityManager = commsIdentityManager;
    }
}
