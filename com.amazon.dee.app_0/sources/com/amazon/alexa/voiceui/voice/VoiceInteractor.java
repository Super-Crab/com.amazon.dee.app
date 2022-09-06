package com.amazon.alexa.voiceui.voice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.api.UserInterfaceOptionsHelper;
import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import com.amazon.alexa.voiceui.AlexaServicesApis;
import com.amazon.alexa.voiceui.accessibility.AccessibilityHandler;
import com.amazon.alexa.voiceui.cards.CardDismissedListener;
import com.amazon.alexa.voiceui.cards.CardInteractor;
import com.amazon.alexa.voiceui.chrome.VoiceChromeDismissedListener;
import com.amazon.alexa.voiceui.chrome.VoiceChromeInteractor;
import com.amazon.alexa.voiceui.driveMode.DriveModeStateProvider;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import com.amazon.alexa.voiceui.screen.ScreenLockManager;
import com.amazon.alexa.voiceui.window.WindowManager;
import io.reactivex.rxjava3.core.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class VoiceInteractor {
    @VisibleForTesting
    public static final String EXTRA_IS_DRIVE_MODE_ENABLED = "is_drive_mode_enabled";
    @VisibleForTesting
    static final String EXTRA_IS_SCREEN_LOCKED = "is_screen_locked";
    @VisibleForTesting
    public static final String EXTRA_SHOW_OVER_LOCKSCREEN = "show_over_lockscreen";
    @VisibleForTesting
    public static final String EXTRA_UI_OPTIONS = "ui_options";
    private final AccessibilityHandler accessibilityHandler;
    private final Activity activity;
    private final AlexaServicesApis alexaServicesApis;
    private final CardInteractor cardInteractor;
    private final DriveModeStateProvider driveModeStateProvider;
    private final LockscreenController lockscreenController;
    private final ScreenLockManager screenLockManager;
    private final SpeechRecognizer speechRecognizer;
    private final UiEventReporter uiEventReporter;
    private final VoiceChromeInteractor voiceChromeInteractor;
    private final WindowManager windowManager;

    @Inject
    public VoiceInteractor(Activity activity, AlexaServicesApis alexaServicesApis, LockscreenController lockscreenController, SpeechRecognizer speechRecognizer, ScreenLockManager screenLockManager, VoiceChromeInteractor voiceChromeInteractor, CardInteractor cardInteractor, AccessibilityHandler accessibilityHandler, UiEventReporter uiEventReporter, WindowManager windowManager, DriveModeStateProvider driveModeStateProvider) {
        this.activity = activity;
        this.alexaServicesApis = alexaServicesApis;
        this.lockscreenController = lockscreenController;
        this.screenLockManager = screenLockManager;
        this.voiceChromeInteractor = voiceChromeInteractor;
        this.cardInteractor = cardInteractor;
        this.speechRecognizer = speechRecognizer;
        this.accessibilityHandler = accessibilityHandler;
        this.uiEventReporter = uiEventReporter;
        this.windowManager = windowManager;
        this.driveModeStateProvider = driveModeStateProvider;
    }

    private boolean getDriveModeEnabled(Intent intent) {
        return intent != null && intent.getBooleanExtra(EXTRA_IS_DRIVE_MODE_ENABLED, false);
    }

    private AlexaUserInterfaceOptions getUserInterfaceOptions(@Nullable Intent intent) {
        if (intent == null) {
            return AlexaUserInterfaceOptions.builder().build();
        }
        return UserInterfaceOptionsHelper.fromBundle(intent.getBundleExtra(EXTRA_UI_OPTIONS));
    }

    private boolean shouldShowOverLockscreen(@Nullable Intent intent) {
        return intent != null && intent.getBooleanExtra(EXTRA_SHOW_OVER_LOCKSCREEN, false);
    }

    private boolean wasScreenLocked(@Nullable Intent intent) {
        return intent != null && intent.getBooleanExtra(EXTRA_IS_SCREEN_LOCKED, false);
    }

    public void acquireScreenLock() {
        this.screenLockManager.requestLock();
    }

    public void addLockscreenCallbacks(LockscreenController.LockscreenCallbacks lockscreenCallbacks) {
        this.lockscreenController.addLockscreenCallbacks(lockscreenCallbacks);
    }

    @MainThread
    public boolean backButtonPressed() {
        return this.voiceChromeInteractor.backButtonPressed() || this.cardInteractor.backButtonPressed();
    }

    public void broadcastAlexaUiFinished() {
        this.uiEventReporter.sendEvent(UiEventName.ALEXA_UI_DISMISSED, null);
    }

    public boolean canDisplayVoiceUI() {
        return this.lockscreenController.canDisplayVoiceUI() && this.screenLockManager.isScreenOn();
    }

    public void cancelVoiceExperience() {
        this.speechRecognizer.cancel();
    }

    public void connectToAlexaService() {
        this.alexaServicesApis.connect();
    }

    public void deregisterExternalCardListener() {
        this.cardInteractor.deregisterForExternalCardUpdates();
    }

    public void deregisterForDisplayStateUpdates() {
        this.screenLockManager.deregisterForDisplayStateUpdates();
    }

    public void deregisterStateListeners() {
        this.cardInteractor.deregisterForCardUpdates();
        this.voiceChromeInteractor.deregisterForAlexaStateUpdates();
    }

    public void disconnectFromAlexaService() {
        this.alexaServicesApis.disconnect();
    }

    public void finishVoiceActivity() {
        this.activity.finish();
    }

    public void finishVoiceExperience() {
        cancelVoiceExperience();
        this.activity.finish();
    }

    public void hideCard() {
        this.cardInteractor.hideCard();
    }

    @MainThread
    public boolean isActivityConfigurationChanging() {
        return this.activity.isChangingConfigurations();
    }

    public boolean isAlexaActive() {
        return this.voiceChromeInteractor.isAlexaActive();
    }

    public boolean isExpectingSubsequentLifecycleCallbacks() {
        return this.lockscreenController.isExpectingSubsequentLifecycleCallbacks();
    }

    public Observable<String> onAlexaCard() {
        return this.cardInteractor.onAlexaCard();
    }

    public Observable<Boolean> onAlexaIdle() {
        return this.voiceChromeInteractor.onAlexaIdle();
    }

    public Observable<Boolean> onAlexaListeningOrThinking() {
        return this.voiceChromeInteractor.onAlexaListeningOrThinking();
    }

    public Observable<Boolean> onCanDisplayVoiceUI() {
        return Observable.combineLatest(this.lockscreenController.onCanDisplayVoiceUI(), this.screenLockManager.subscribeToScreenOn(), $$Lambda$VoiceInteractor$TmrIUW5feDilW5NfEINWqD0KtQ.INSTANCE);
    }

    public Observable<String> onDisplayingExternalCard() {
        return this.cardInteractor.getDisplayingExternalCard();
    }

    public void promptUserToUnlockPhoneIfRequired() {
        this.lockscreenController.promptUserToUnlockPhoneIfRequired();
    }

    public void registerCardDismissedListener(CardDismissedListener cardDismissedListener) {
        this.cardInteractor.registerCardDismissedListener(cardDismissedListener);
    }

    public void registerExternalCardListener() {
        this.cardInteractor.registerForExternalCardUpdates(this.activity);
    }

    public void registerForDisplayStateUpdates() {
        this.screenLockManager.registerForDisplayStateUpdates();
    }

    public void registerStateListeners() {
        this.cardInteractor.registerForCardUpdates();
        this.voiceChromeInteractor.registerForAlexaStateUpdates();
    }

    public void registerVoiceChromeEventListener(VoiceChromeDismissedListener voiceChromeDismissedListener) {
        this.voiceChromeInteractor.registerVoiceChromeDismissedListener(voiceChromeDismissedListener);
    }

    public void releaseFromAlexaService() {
        this.alexaServicesApis.release();
    }

    public void releaseScreenLock() {
        this.screenLockManager.releaseLock();
    }

    public void requestPhoneUnlockIfRequired() {
        this.lockscreenController.requestPhoneUnlockIfRequired();
    }

    @MainThread
    public void saveInstanceState(Bundle bundle) {
        this.cardInteractor.saveInstanceState(bundle);
        this.voiceChromeInteractor.saveInstanceState(bundle);
    }

    public void setTouchEventPassThrough(boolean z) {
        this.windowManager.setTouchEventPassThrough(z);
    }

    public void showOverLockscreenIfRequired() {
        this.lockscreenController.showOverLockscreenIfRequired();
    }

    public void startControllingAccessibility() {
        this.accessibilityHandler.startControllingAccessibility();
    }

    public void startVoiceRequest(String str) {
        this.speechRecognizer.recognizeSpeech(str);
    }

    public void stopControllingAccessibility() {
        this.accessibilityHandler.stopControllingAccessibility();
    }

    @MainThread
    public void updateUiOptions(@Nullable Intent intent) {
        this.lockscreenController.setShouldShowOverLockscreen(shouldShowOverLockscreen(intent));
        this.lockscreenController.setWasScreenLocked(wasScreenLocked(intent));
        this.voiceChromeInteractor.setUserInterfaceOptions(getUserInterfaceOptions(intent));
        this.cardInteractor.setDriveModeEnabled(getDriveModeEnabled(intent));
        this.driveModeStateProvider.setDriveModeEnabled(getDriveModeEnabled(intent));
    }
}
