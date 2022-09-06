package com.amazon.alexa.voice.model;

import android.app.Activity;
import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.ui.TabBarAnimator;
import com.amazon.alexa.voice.navigation.NavigationAppInfo;
import com.amazon.alexa.voice.nowplaying.AudioPlayerListener;
import com.amazon.alexa.voice.nowplaying.NowPlayingCardManager;
import com.amazon.alexa.voice.ui.VoiceActivityLauncher;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel;
import com.amazon.alexa.voice.ui.player.PlaybackController;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public interface VoiceService extends AlexaServicesConnection.ConnectionListener {

    /* loaded from: classes11.dex */
    public enum InvocationType {
        APP_INGRESS("AlexaApp.AppIngress"),
        CARD(AlexaMetricsConstants.MetricsComponents.CARD_INGRESS_COMPONENT_NAME),
        DEVICE_ASSISTANT("AlexaApp.DeviceAssistant"),
        PUSH_BUTTON("AlexaApp.PushButton"),
        INTENT("AlexaApp.Intent"),
        SHORTCUT("AlexaApp.Shortcut"),
        QUICK_ACTIONS_WIDGET("AlexaApp.QuickActionsWidget"),
        VOICE_COMMAND_INTENT("AlexaApp.VoiceCommand"),
        ANDROID_VOICE_SEARCH_INTENT("AlexaApp.HandsfreeSearch"),
        WAKE_WORD("AlexaApp.WakeWord"),
        ROUTE("AlexaApp.Route"),
        VOICE_ENROLLMENT("AlexaApp.VoiceEnrollment"),
        DRIVE_MODE_TAP("AlexaApp.DriveModeTap"),
        UNKNOWN("AlexaApp.Unknown");
        
        private String name;

        InvocationType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    /* loaded from: classes11.dex */
    public enum RecordPermission {
        REQUIRED("AlexaApp.Required"),
        NOT_REQUIRED("AlexaApp.NotRequired");
        
        private String name;

        RecordPermission(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    void addAudioPlayerListener(AudioPlayerListener audioPlayerListener);

    void cancel();

    void clearScrimSubscription();

    void doNotBackground(boolean z);

    void enableHandsfreeSettings(boolean z);

    List<NavigationAppInfo> getAllNavigationApps();

    MessagingReceiver getMessagingReceiver();

    NowPlayingCardManager getNowPlayingCardManager();

    PlaybackController getPlaybackController();

    boolean hasMinimumPermission();

    void ingressClicked();

    boolean isAlexaActive();

    boolean isHandsfreeEnabled();

    boolean isHandsfreeSupported();

    boolean isListeningOrProcessing();

    boolean isVoiceAllowed();

    boolean isVoicePossible();

    void onBackground();

    void onForeground(RecordPermission recordPermission);

    Observable<Boolean> onIdle();

    void onResumeMainActivity(Activity activity);

    void onStartMainActivity(Activity activity);

    void onStopMainActivity(Activity activity);

    void onUserLoggedOut();

    Single<Boolean> recognizeSpeech(InvocationType invocationType);

    Single<Boolean> recognizeSpeech(InvocationType invocationType, ScrimParametersModel scrimParametersModel);

    void registerScrimListenerIfSpeaking();

    void release();

    void removeAudioPlayerListener(AudioPlayerListener audioPlayerListener);

    void setPreferredNavigationApp(String str);

    void setTabBarAnimator(TabBarAnimator tabBarAnimator);

    void setVoiceActivityLauncher(VoiceActivityLauncher voiceActivityLauncher);

    void startVoiceExperience(Context context, String str, String str2, Map<String, String> map, InvocationType invocationType, ScrimParametersModel scrimParametersModel);

    void stopListening();

    void updateLocale(Locale locale);
}
