package com.amazon.alexa.alertsca;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer;
import com.amazon.alexa.alertsca.AlertsConstants;
import com.amazon.alexa.alertsca.events.AlertStartedEvent;
import com.amazon.alexa.alertsca.events.AlertStoppedEvent;
import com.amazon.alexa.alertsca.events.AlertUnscheduledEvent;
import com.amazon.alexa.alertsca.events.NetworkConnectivityEvent;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.payload.AlertsPayload;
import com.amazon.alexa.api.AlexaAudioChannel;
import com.amazon.alexa.api.AlexaAudioTask;
import com.amazon.alexa.api.AlexaAudioTaskChannelPriority;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.gson.Gson;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.greenrobot.eventbus.Subscribe;
/* loaded from: classes6.dex */
public class AlertInteraction implements AlexaAudioTask, PlayerEventListener {
    private static final long DEFAULT_LOOP_COUNT = 2;
    private static final long DEFAULT_LOOP_PAUSE_MS = 0;
    private static final String INTERACTION_EXECUTOR = "interaction_executor";
    static final String RES_PATH = "rawresource:///";
    private static final String SCHEDULED_ASSET_EXECUTOR = "scheduled_asset_executor";
    private static final String TAG = "AlertInteraction";
    private final AlertRecord alertRecord;
    private final AlertStoppedListener alertStoppedListener;
    private final AlertsAuthority alertsAuthority;
    private final AlertsEventBus alertsEventBus;
    private final AlexaEventSender alexaEventSender;
    private final AlexaInteractionScheduler alexaInteractionScheduler;
    private final AudioFocusManager audioFocusManager;
    private final Uri backgroundSound;
    private final ConnectedAccessoryInquirer connectedAccessoryInquirer;
    private Future currentPlayingSoundFuture;
    private final AlertsExoPlayer exoAudioPlayer;
    private final Uri foregroundSound;
    private final Gson gson;
    private final ScheduledExecutorService interactionSchedulerService;
    private boolean isConnected;
    private boolean isFinished;
    private boolean isReminderFirstTime;
    private LoopState loopState;
    private final MetricsService metricsService;
    private boolean paused;
    private boolean playerInitialized;
    private final ScheduledExecutorService scheduledAssetExecutorService;
    private final ScoAuthority scoAuthority;
    private AlertPlaybackState state;

    /* renamed from: com.amazon.alexa.alertsca.AlertInteraction$6  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType = new int[AlertType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.REMINDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.TIMER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public enum AlertPlaybackState {
        NONE,
        FOREGROUND,
        BACKGROUND
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class LoopState {
        private boolean isPlayTone = true;
        private long loopCount;
        private final long loopPause;

        LoopState(long j, long j2) {
            this.loopPause = j;
            this.loopCount = j2;
        }

        void decreaseCount() {
            this.loopCount--;
        }

        boolean hasLoopPause() {
            return this.loopPause == 0;
        }

        boolean isPlayTone() {
            return this.isPlayTone;
        }

        void setPlayTone(boolean z) {
            this.isPlayTone = z;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LoopState{loopPause=");
            outline107.append(this.loopPause);
            outline107.append(", loopCount=");
            outline107.append(this.loopCount);
            outline107.append(", isPlayTone=");
            outline107.append(this.isPlayTone);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class PlaybackRunnable implements Runnable {
        private final AlertsExoPlayer exoAudioPlayer;

        PlaybackRunnable(AlertsExoPlayer alertsExoPlayer) {
            this.exoAudioPlayer = alertsExoPlayer;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.exoAudioPlayer.play();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlertInteraction(AlertsEventBus alertsEventBus, AlertRecord alertRecord, Uri uri, Uri uri2, AlertStoppedListener alertStoppedListener, AlertsAuthority alertsAuthority, Gson gson, AlertsExoPlayer alertsExoPlayer, AudioFocusManager audioFocusManager, AlexaEventSender alexaEventSender, AlexaInteractionScheduler alexaInteractionScheduler, ConnectedAccessoryInquirer connectedAccessoryInquirer, ScoAuthority scoAuthority, MetricsService metricsService) {
        this(alertsEventBus, alertRecord, uri, uri2, alertStoppedListener, ExecutorFactory.newScheduledExecutor(1, SCHEDULED_ASSET_EXECUTOR), alertsAuthority, gson, alertsExoPlayer, audioFocusManager, alexaEventSender, alexaInteractionScheduler, ExecutorFactory.newSingleThreadScheduledExecutor(INTERACTION_EXECUTOR), connectedAccessoryInquirer, scoAuthority, metricsService);
    }

    private void doFinish() {
        doFinish(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAlertStarted() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAlertStarted: state = ");
        outline107.append(this.state);
        outline107.toString();
        if (this.state == AlertPlaybackState.NONE) {
            sendAlertEvent(AlertsConstants.Alerts.Events.AlertStarted.NAME, false);
            this.alertsEventBus.post(AlertStartedEvent.create(this.alertRecord));
            this.metricsService.recordEvent(MetricsConstants.ALERTS.STARTED.element(this.alertRecord.getType().name()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onChangeToBackground() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onChangeToBackground: state = ");
        outline107.append(this.state);
        outline107.toString();
        AlertPlaybackState alertPlaybackState = this.state;
        AlertPlaybackState alertPlaybackState2 = AlertPlaybackState.BACKGROUND;
        if (alertPlaybackState != alertPlaybackState2) {
            this.state = alertPlaybackState2;
            sendAlertEvent(AlertsConstants.Alerts.Events.AlertEnteredBackground.NAME, false);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onChangeToForeground() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onChangeToForeground: state = ");
        outline107.append(this.state);
        outline107.toString();
        AlertPlaybackState alertPlaybackState = this.state;
        AlertPlaybackState alertPlaybackState2 = AlertPlaybackState.FOREGROUND;
        if (alertPlaybackState != alertPlaybackState2) {
            this.state = alertPlaybackState2;
            sendAlertEvent(AlertsConstants.Alerts.Events.AlertEnteredForeground.NAME, false);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playBackgroundSound(boolean z) {
        String str = "playBackgroundSound: isPreviousStateForeground = " + z + ", alertType = " + this.alertRecord.getType() + ", hasFocus = " + this.audioFocusManager.hasFocus();
        stopCurrentPlayingSound();
        if (!z && this.audioFocusManager.hasFocus()) {
            if (!this.alertRecord.isReminder()) {
                return;
            }
            doFinish();
            return;
        }
        this.scoAuthority.startScoIfNeeded();
        if (this.alertRecord.isReminder()) {
            playBackgroundReminderTone();
            return;
        }
        prepare(this.backgroundSound, false);
        this.currentPlayingSoundFuture = this.scheduledAssetExecutorService.submit(new PlaybackRunnable(this.exoAudioPlayer));
    }

    private void playForegroundSound() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("playForegroundSound: alertType = ");
        outline107.append(this.alertRecord.getType());
        outline107.append(", loopState = ");
        outline107.append(this.loopState);
        outline107.toString();
        stopCurrentPlayingSound();
        this.scoAuthority.startScoIfNeeded();
        if (this.alertRecord.getType() != AlertType.REMINDER) {
            if (this.loopState.hasLoopPause()) {
                prepare(this.foregroundSound);
            } else if (this.loopState.isPlayTone()) {
                prepare(this.backgroundSound, false);
                this.loopState.setPlayTone(false);
            } else {
                prepare(this.foregroundSound, false);
                this.loopState.setPlayTone(true);
            }
            this.currentPlayingSoundFuture = this.scheduledAssetExecutorService.submit(new PlaybackRunnable(this.exoAudioPlayer));
            return;
        }
        prepare(this.foregroundSound);
        playReminderSounds();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playSounds() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("playSounds: state = ");
        outline107.append(this.state);
        outline107.toString();
        if (this.isFinished) {
            Log.w(TAG, "Return early as alert-interaction is finished!");
        } else if (this.state == AlertPlaybackState.BACKGROUND) {
            playBackgroundSound(false);
        } else if (!this.audioFocusManager.requestFocus(this)) {
        } else {
            gainedFocus();
        }
    }

    private void prepare(Uri uri) {
        prepare(uri, this.alertRecord.getType() != AlertType.REMINDER);
    }

    private void prepareReminderTone() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(RES_PATH);
        outline107.append(AlertSoundName.REMINDER_FOREGROUND_SOUND.getResourceId());
        prepare(Uri.parse(outline107.toString()));
    }

    private void sendAlertEvent(String str, boolean z) {
        String str2 = "sendAlertEvent: " + str + ", shouldSendContext = " + z;
        this.alexaEventSender.send(new AlexaEvent(AlexaHeader.create("Alerts", str), new AlexaPayload(this.gson.toJson(AlertsPayload.create(this.alertRecord.getToken())))), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopCurrentPlayingSound() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("stopCurrentPlayingSound: state = ");
        outline107.append(this.state);
        outline107.toString();
        Future future = this.currentPlayingSoundFuture;
        if (future != null) {
            future.cancel(true);
        }
        if (this.state != AlertPlaybackState.NONE && this.playerInitialized) {
            this.exoAudioPlayer.stop();
        }
        this.scoAuthority.stopScoIfNeeded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopIfOffline(boolean z) {
        GeneratedOutlineSupport1.outline172("stopIfOffline: enteredBackgroundFromForeground = ", z);
        if (this.isConnected || !z) {
            return;
        }
        onStop();
    }

    public void gainedFocus() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("gainedFocus: alertType = ");
        outline107.append(this.alertRecord.getType());
        outline107.append(", state = ");
        outline107.append(this.state);
        outline107.append(", loopState = ");
        outline107.append(this.loopState);
        outline107.toString();
        if (this.alertRecord.isReminder() && this.loopState.isPlayTone()) {
            playReminderTone();
        } else if (this.state != AlertPlaybackState.FOREGROUND) {
        } else {
            playForegroundSound();
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioTask
    public AlexaAudioChannel getAlexaAudioChannel() {
        return AlexaAudioChannel.ALERTS;
    }

    @Override // com.amazon.alexa.api.AlexaAudioTask
    public AlexaAudioTaskChannelPriority getAlexaAudioTaskChannelPriority() {
        return AlexaAudioTaskChannelPriority.HIGH;
    }

    @Override // com.amazon.alexa.api.AlexaAudioTask
    public String getAudioTaskComponentName() {
        return "Alerts";
    }

    @VisibleForTesting
    boolean isAccessoryConnected() {
        boolean z = !this.connectedAccessoryInquirer.getConnectedAccessories().isEmpty();
        GeneratedOutlineSupport1.outline172("isAccessoryConnected? ", z);
        return z;
    }

    public void lostFocus() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("lostFocus: state = ");
        outline107.append(this.state);
        outline107.toString();
        if (this.state == AlertPlaybackState.FOREGROUND) {
            this.exoAudioPlayer.pause();
        }
    }

    @Subscribe
    public synchronized void on(AlertUnscheduledEvent alertUnscheduledEvent) {
        String str = "on AlertUnscheduledEvent: " + alertUnscheduledEvent.getAlertRecord().getToken();
        if (alertUnscheduledEvent.getAlertRecord().getToken().equals(this.alertRecord.getToken())) {
            onStop();
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioTask
    public void onBackground() {
        this.interactionSchedulerService.submit(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertInteraction.2
            @Override // java.lang.Runnable
            public void run() {
                String unused = AlertInteraction.TAG;
                String str = "onBackground-run " + this;
                AlertInteraction.this.onAlertStarted();
                boolean z = AlertInteraction.this.state == AlertPlaybackState.FOREGROUND;
                AlertInteraction.this.stopIfOffline(z);
                if (!AlertInteraction.this.onChangeToBackground() || AlertInteraction.this.paused) {
                    return;
                }
                AlertInteraction.this.playBackgroundSound(z);
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaAudioTask
    public void onForeground() {
        this.interactionSchedulerService.submit(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertInteraction.1
            @Override // java.lang.Runnable
            public void run() {
                String unused = AlertInteraction.TAG;
                String str = "onForeground-run " + this;
                AlertInteraction.this.onAlertStarted();
                if (!AlertInteraction.this.onChangeToForeground() || AlertInteraction.this.paused) {
                    return;
                }
                AlertInteraction.this.playSounds();
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaAudioTask
    public void onPause() {
        this.interactionSchedulerService.submit(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertInteraction.3
            @Override // java.lang.Runnable
            public void run() {
                String unused = AlertInteraction.TAG;
                String str = "onPause-run " + this;
                if (AlertInteraction.this.state != AlertPlaybackState.BACKGROUND || AlertInteraction.this.paused) {
                    return;
                }
                AlertInteraction.this.paused = true;
                AlertInteraction.this.stopCurrentPlayingSound();
            }
        });
    }

    @Override // com.amazon.alexa.alertsca.PlayerEventListener
    public void onPlaybackFailed(Exception exc) {
        onPlaybackStopped();
    }

    @Override // com.amazon.alexa.alertsca.PlayerEventListener
    public void onPlaybackFinished() {
        onPlaybackStopped();
    }

    @VisibleForTesting
    void onPlaybackStopped() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onPlaybackStopped: alertType = ");
        outline107.append(this.alertRecord.getType());
        outline107.append(", loopState = ");
        outline107.append(this.loopState);
        outline107.toString();
        int ordinal = this.alertRecord.getType().ordinal();
        if (ordinal == 1) {
            if (this.loopState.hasLoopPause()) {
                return;
            }
            playSounds();
        } else if (ordinal != 2) {
        } else {
            if (this.loopState.loopCount > 0) {
                playSounds();
            } else {
                doFinish();
            }
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioTask
    public void onResume() {
        this.interactionSchedulerService.submit(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertInteraction.4
            @Override // java.lang.Runnable
            public void run() {
                String unused = AlertInteraction.TAG;
                String str = "onResume-run " + this;
                if (AlertInteraction.this.paused) {
                    AlertInteraction.this.paused = false;
                    AlertInteraction.this.playSounds();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void onSilent() {
        stopCurrentPlayingSound();
    }

    @Override // com.amazon.alexa.api.AlexaAudioTask
    public void onStop() {
        stopAsync(this.alertStoppedListener);
    }

    @VisibleForTesting
    void playBackgroundReminderTone() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("playBackgroundReminderTone: loopState = ");
        outline107.append(this.loopState);
        outline107.toString();
        this.loopState.decreaseCount();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(RES_PATH);
        outline1072.append(AlertSoundName.REMINDER_BACKGROUND_SOUND.getResourceId());
        prepare(Uri.parse(outline1072.toString()));
        this.currentPlayingSoundFuture = this.scheduledAssetExecutorService.submit(new PlaybackRunnable(this.exoAudioPlayer));
    }

    @VisibleForTesting
    void playReminderSounds() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("playReminderSounds: loopState = ");
        outline107.append(this.loopState);
        outline107.toString();
        if (isAccessoryConnected()) {
            this.loopState.decreaseCount();
            this.currentPlayingSoundFuture = this.scheduledAssetExecutorService.submit(new PlaybackRunnable(this.exoAudioPlayer));
            this.loopState.setPlayTone(true);
            return;
        }
        playReminderTone();
    }

    @VisibleForTesting
    void playReminderTone() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("playReminderTone: isReminderFirstTime = ");
        outline107.append(this.isReminderFirstTime);
        outline107.toString();
        stopCurrentPlayingSound();
        prepareReminderTone();
        this.scoAuthority.startScoIfNeeded();
        if (!this.isReminderFirstTime) {
            this.currentPlayingSoundFuture = this.scheduledAssetExecutorService.schedule(new PlaybackRunnable(this.exoAudioPlayer), this.loopState.loopPause, TimeUnit.MILLISECONDS);
        } else {
            this.isReminderFirstTime = false;
            this.currentPlayingSoundFuture = this.scheduledAssetExecutorService.submit(new PlaybackRunnable(this.exoAudioPlayer));
        }
        if (!isAccessoryConnected()) {
            this.loopState.decreaseCount();
        }
        this.loopState.setPlayTone(false);
    }

    @VisibleForTesting
    void setAudioAttributes() {
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        if (this.scoAuthority.shouldUseSco()) {
            builder.setUsage(2);
            builder.setContentType(1);
        } else if (isAccessoryConnected()) {
            builder.setUsage(1);
        } else if (this.alertRecord.isReminder()) {
            builder.setUsage(6);
        } else {
            builder.setUsage(4);
        }
        AudioAttributes build = builder.build();
        this.exoAudioPlayer.setAudioAttributes(build);
        this.metricsService.recordEvent(MetricsConstants.ALERTS.EXO_PLAYER.USAGE.element(this.alertRecord.getType().name()).element(build.usage));
    }

    @VisibleForTesting
    synchronized void stop(boolean z) {
        String str = "stop: isDismissedByUser = " + z;
        stopCurrentPlayingSound();
        doFinish(z);
        if (this.state != AlertPlaybackState.NONE && !this.alertRecord.isReminder()) {
            this.alertsEventBus.post(AlertStoppedEvent.create(this.alertRecord, z));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopAsync(boolean z) {
        stopAsync(null, z);
    }

    private void doFinish(boolean z) {
        GeneratedOutlineSupport1.outline172("doFinish: isDismissedByUser = ", z);
        this.alertsEventBus.unregister(this);
        if (!this.isFinished) {
            this.alexaInteractionScheduler.unschedule(this);
            if (this.state != AlertPlaybackState.NONE) {
                sendAlertEvent(AlertsConstants.Alerts.Events.AlertStopped.NAME, true);
            }
            if (this.alertRecord.isReminder()) {
                this.alertsAuthority.deleteAlert(this.alertRecord.getToken());
                this.alertsEventBus.post(AlertStoppedEvent.create(this.alertRecord, z));
            }
            this.playerInitialized = false;
            this.isFinished = true;
        }
        this.audioFocusManager.abandonRequest();
        this.interactionSchedulerService.shutdown();
    }

    private void prepare(Uri uri, boolean z) {
        if (!this.playerInitialized) {
            setAudioAttributes();
            this.playerInitialized = true;
        }
        String str = "prepare: playItem = " + uri + ", isLoopable = " + z;
        this.exoAudioPlayer.prepare(uri, this, z);
    }

    void stopAsync(AlertStoppedListener alertStoppedListener) {
        stopAsync(alertStoppedListener, false);
    }

    void stopAsync(@Nullable final AlertStoppedListener alertStoppedListener, final boolean z) {
        this.interactionSchedulerService.submit(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertInteraction.5
            @Override // java.lang.Runnable
            public void run() {
                String unused = AlertInteraction.TAG;
                String str = "stopAsync-run " + this;
                AlertInteraction.this.stop(z);
                AlertStoppedListener alertStoppedListener2 = alertStoppedListener;
                if (alertStoppedListener2 != null) {
                    alertStoppedListener2.onAlertStopped(AlertInteraction.this.alertRecord.getToken());
                }
            }
        });
    }

    @VisibleForTesting
    AlertInteraction(AlertsEventBus alertsEventBus, AlertRecord alertRecord, Uri uri, Uri uri2, AlertStoppedListener alertStoppedListener, ScheduledExecutorService scheduledExecutorService, AlertsAuthority alertsAuthority, Gson gson, AlertsExoPlayer alertsExoPlayer, AudioFocusManager audioFocusManager, AlexaEventSender alexaEventSender, AlexaInteractionScheduler alexaInteractionScheduler, ScheduledExecutorService scheduledExecutorService2, ConnectedAccessoryInquirer connectedAccessoryInquirer, ScoAuthority scoAuthority, MetricsService metricsService) {
        this.isReminderFirstTime = true;
        Preconditions.notNull(alertsEventBus, "AlertsEventBus is null");
        Preconditions.notNull(alertRecord, "AlertRecord is null");
        Preconditions.notNull(uri, "foregroundSound is null");
        Preconditions.notNull(uri2, "backgroundSound is null");
        Preconditions.notNull(alertStoppedListener, "AlertStoppedListener is null");
        Preconditions.notNull(scheduledExecutorService, "ScheduledExecutorService is null");
        Preconditions.notNull(alertsAuthority, "AlertsAuthority is null");
        Preconditions.notNull(gson, "Gson is null");
        Preconditions.notNull(audioFocusManager, "AudioFocusManager is null");
        Preconditions.notNull(alexaEventSender, "AlexaEventSender is null");
        Preconditions.notNull(alexaInteractionScheduler, "AlexaInteractionScheduler is null");
        Preconditions.notNull(scheduledExecutorService2, "InteractionSchedulerService is null");
        Preconditions.notNull(connectedAccessoryInquirer, "ConnectedAccessoryInquirer is null");
        Preconditions.notNull(scoAuthority, "ScoAuthority is null");
        Preconditions.notNull(metricsService, "metricsService is null");
        this.alertsEventBus = alertsEventBus;
        this.alertRecord = alertRecord;
        this.foregroundSound = uri;
        this.backgroundSound = uri2;
        this.alertStoppedListener = alertStoppedListener;
        this.scheduledAssetExecutorService = scheduledExecutorService;
        this.alertsAuthority = alertsAuthority;
        this.gson = gson;
        this.exoAudioPlayer = alertsExoPlayer;
        this.audioFocusManager = audioFocusManager;
        this.alexaEventSender = alexaEventSender;
        this.alexaInteractionScheduler = alexaInteractionScheduler;
        this.interactionSchedulerService = scheduledExecutorService2;
        this.state = AlertPlaybackState.NONE;
        this.loopState = new LoopState(alertRecord.getLoopPauseInMilliseconds() != null ? alertRecord.getLoopPauseInMilliseconds().longValue() : 0L, alertRecord.getLoopCount() != null ? alertRecord.getLoopCount().longValue() : 2L);
        this.connectedAccessoryInquirer = connectedAccessoryInquirer;
        this.scoAuthority = scoAuthority;
        this.metricsService = metricsService;
        alertsEventBus.register(this);
    }

    @Subscribe(sticky = true)
    public synchronized void on(NetworkConnectivityEvent networkConnectivityEvent) {
        this.isConnected = networkConnectivityEvent.isConnected();
        String str = "on NetworkConnectivityEvent: isConnected = " + this.isConnected;
    }
}
