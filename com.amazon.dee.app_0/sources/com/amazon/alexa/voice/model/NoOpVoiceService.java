package com.amazon.alexa.voice.model;

import android.app.Activity;
import android.content.Context;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.ui.TabBarAnimator;
import com.amazon.alexa.voice.downchannel.NoOpMessagingReceiver;
import com.amazon.alexa.voice.enablement.ComponentEnabler;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.navigation.NavigationAppInfo;
import com.amazon.alexa.voice.nowplaying.AudioPlayerListener;
import com.amazon.alexa.voice.nowplaying.NoOpNowPlayingCardManager;
import com.amazon.alexa.voice.nowplaying.NoOpPlaybackController;
import com.amazon.alexa.voice.nowplaying.NowPlayingCardManager;
import com.amazon.alexa.voice.sdk.AlexaConnectionManager;
import com.amazon.alexa.voice.sdk.NoOpAlexaConnectionManager;
import com.amazon.alexa.voice.ui.VoiceActivityLauncher;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel;
import com.amazon.alexa.voice.ui.player.PlaybackController;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class NoOpVoiceService implements VoiceService {
    private final PlaybackController playbackController = new NoOpPlaybackController();
    private final AlexaConnectionManager alexaConnectionManager = new NoOpAlexaConnectionManager();
    private final MessagingReceiver messagingReceiver = new NoOpMessagingReceiver();
    private final NowPlayingCardManager nowPlayingCardManager = new NoOpNowPlayingCardManager();

    public NoOpVoiceService(ComponentEnabler componentEnabler) {
        componentEnabler.disableUiComponents();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void addAudioPlayerListener(AudioPlayerListener audioPlayerListener) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void cancel() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void clearScrimSubscription() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void doNotBackground(boolean z) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void enableHandsfreeSettings(boolean z) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public List<NavigationAppInfo> getAllNavigationApps() {
        return Collections.emptyList();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public MessagingReceiver getMessagingReceiver() {
        return this.messagingReceiver;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public NowPlayingCardManager getNowPlayingCardManager() {
        return this.nowPlayingCardManager;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public PlaybackController getPlaybackController() {
        return this.playbackController;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean hasMinimumPermission() {
        return false;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void ingressClicked() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isAlexaActive() {
        return false;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isHandsfreeEnabled() {
        return false;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService, com.amazon.alexa.voice.model.HandsFreeSupportChecker
    public boolean isHandsfreeSupported() {
        return false;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isListeningOrProcessing() {
        return false;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isVoiceAllowed() {
        return false;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public boolean isVoicePossible() {
        return false;
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onBackground() {
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onForeground(VoiceService.RecordPermission recordPermission) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public Observable<Boolean> onIdle() {
        return Observable.empty();
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onResumeMainActivity(Activity activity) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onStartMainActivity(Activity activity) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onStopMainActivity(Activity activity) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void onUserLoggedOut() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public Single<Boolean> recognizeSpeech(VoiceService.InvocationType invocationType) {
        return Observable.just(true).single(true);
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void registerScrimListenerIfSpeaking() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void release() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void removeAudioPlayerListener(AudioPlayerListener audioPlayerListener) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void setPreferredNavigationApp(String str) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void setTabBarAnimator(TabBarAnimator tabBarAnimator) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void setVoiceActivityLauncher(VoiceActivityLauncher voiceActivityLauncher) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void startVoiceExperience(Context context, String str, String str2, Map<String, String> map, VoiceService.InvocationType invocationType, ScrimParametersModel scrimParametersModel) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void stopListening() {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public void updateLocale(Locale locale) {
    }

    @Override // com.amazon.alexa.voice.model.VoiceService
    public Single<Boolean> recognizeSpeech(VoiceService.InvocationType invocationType, ScrimParametersModel scrimParametersModel) {
        return Observable.just(true).single(true);
    }
}
