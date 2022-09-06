package com.amazon.alexa.voiceui.voice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voiceui.cards.CardDismissedListener;
import com.amazon.alexa.voiceui.chrome.VoiceChromeDismissedListener;
import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class VoicePresenter {
    @VisibleForTesting
    static final String KEY_CHROME_SHOWN_FOR_FIRST_REQUEST = "chromeShownForFirstRequest";
    @VisibleForTesting
    static final String KEY_DISMISS_TRIGGERED_BY_IDLING = "dismissedByIdling";
    @VisibleForTesting
    static final String KEY_SHOWING_CARD = "showingCard";
    @VisibleForTesting
    static final String LOCKSCREEN_INVOCATION = "AlexaApp.Lockscreen";
    private static final String TAG = "VoicePresenter";
    private boolean canCancelVoiceExperience = true;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private boolean isActivityVisible;
    private volatile boolean isUiDismissTriggeredByIdling;
    private volatile boolean showingCard;
    private boolean voiceChromeShownForFirstVoiceRequest;
    private final VoiceInteractor voiceInteractor;
    private final VoiceView voiceView;

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class CardListener implements CardDismissedListener {
        CardListener() {
        }

        @Override // com.amazon.alexa.voiceui.cards.CardDismissedListener
        public void onCardDismissed() {
            VoicePresenter.this.cardDismissed();
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class LockscreenCallbacks implements LockscreenController.LockscreenCallbacks {
        LockscreenCallbacks() {
        }

        @Override // com.amazon.alexa.voiceui.lockscreen.LockscreenController.LockscreenCallbacks
        public void onUnlockFailure() {
            VoicePresenter.this.voiceInteractor.finishVoiceExperience();
        }

        @Override // com.amazon.alexa.voiceui.lockscreen.LockscreenController.LockscreenCallbacks
        public void onUnlockSuccess(boolean z, boolean z2) {
            if (z) {
                VoicePresenter.this.voiceInteractor.startVoiceRequest(VoicePresenter.LOCKSCREEN_INVOCATION);
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class VoiceChromeListener implements VoiceChromeDismissedListener {
        VoiceChromeListener() {
        }

        @Override // com.amazon.alexa.voiceui.chrome.VoiceChromeDismissedListener
        public void onVoiceChromeDismissed() {
            VoicePresenter.this.voiceChromeDismissed();
        }
    }

    @Inject
    public VoicePresenter(final VoiceInteractor voiceInteractor, VoiceView voiceView) {
        this.voiceInteractor = voiceInteractor;
        this.voiceView = voiceView;
        this.disposable.add(voiceInteractor.onAlexaCard().subscribeOn(Schedulers.computation()).subscribe(new Consumer<String>() { // from class: com.amazon.alexa.voiceui.voice.VoicePresenter.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(String str) {
                VoicePresenter.this.showCard(str);
            }
        }));
        this.disposable.add(voiceInteractor.onAlexaIdle().subscribe(new Consumer<Boolean>() { // from class: com.amazon.alexa.voiceui.voice.VoicePresenter.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull Boolean bool) {
                if (bool.booleanValue()) {
                    VoicePresenter.this.isUiDismissTriggeredByIdling = true;
                    VoicePresenter.this.hideVoiceChrome();
                }
            }
        }));
        this.disposable.add(Observable.combineLatest(voiceInteractor.onAlexaListeningOrThinking(), voiceInteractor.onCanDisplayVoiceUI(), $$Lambda$VoicePresenter$LcXEM_h57khZDT_AAUPCAuhYqBc.INSTANCE).distinctUntilChanged().subscribe(new Consumer<Boolean>() { // from class: com.amazon.alexa.voiceui.voice.VoicePresenter.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull Boolean bool) {
                if (bool.booleanValue()) {
                    VoicePresenter.this.isUiDismissTriggeredByIdling = false;
                    VoicePresenter.this.displayVoiceChrome();
                }
            }
        }));
        this.disposable.add(voiceInteractor.onDisplayingExternalCard().subscribeOn(Schedulers.computation()).subscribe(new Consumer<String>() { // from class: com.amazon.alexa.voiceui.voice.VoicePresenter.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(String str) {
                VoicePresenter.this.canCancelVoiceExperience = false;
                voiceInteractor.finishVoiceActivity();
            }
        }));
        observeCardRendering();
        voiceInteractor.registerVoiceChromeEventListener(new VoiceChromeListener());
        voiceInteractor.registerCardDismissedListener(new CardListener());
        voiceInteractor.addLockscreenCallbacks(new LockscreenCallbacks());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cardDismissed() {
        Log.i(TAG, "finishing voice because card dismissed.");
        this.voiceInteractor.finishVoiceExperience();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayVoiceChrome() {
        if (!this.voiceInteractor.canDisplayVoiceUI()) {
            Log.i(TAG, "Can't show voice chrome because can't display voice ui");
            return;
        }
        this.voiceInteractor.setTouchEventPassThrough(false);
        if (this.voiceView.isCardVisible() && !this.voiceChromeShownForFirstVoiceRequest) {
            return;
        }
        Log.i(TAG, "Showing voice chrome");
        this.voiceView.showVoiceChrome();
        this.voiceChromeShownForFirstVoiceRequest = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideVoiceChrome() {
        if (this.voiceChromeShownForFirstVoiceRequest) {
            Log.i(TAG, "Hiding voice chrome");
            this.voiceView.hideVoiceChrome();
            if (this.voiceView.isCardVisible()) {
                return;
            }
            this.voiceInteractor.setTouchEventPassThrough(true);
            return;
        }
        Log.i(TAG, "Not hiding voice chrome because it wasn't shown for the first time");
    }

    private void observeCardRendering() {
        this.disposable.add(this.voiceView.onCardRenderObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voiceui.voice.-$$Lambda$VoicePresenter$C1Vku24FJd6IQs_HTQhSyr-5QA4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                VoicePresenter.this.lambda$observeCardRendering$1$VoicePresenter((Boolean) obj);
            }
        }));
    }

    private void restoreState(@Nullable Bundle bundle) {
        if (bundle != null) {
            this.showingCard = bundle.getBoolean(KEY_SHOWING_CARD, false);
            this.voiceChromeShownForFirstVoiceRequest = bundle.getBoolean(KEY_CHROME_SHOWN_FOR_FIRST_REQUEST, false);
            this.isUiDismissTriggeredByIdling = bundle.getBoolean(KEY_DISMISS_TRIGGERED_BY_IDLING, false);
        }
    }

    private void saveState(Bundle bundle) {
        bundle.putBoolean(KEY_SHOWING_CARD, this.showingCard);
        bundle.putBoolean(KEY_CHROME_SHOWN_FOR_FIRST_REQUEST, this.voiceChromeShownForFirstVoiceRequest);
        bundle.putBoolean(KEY_DISMISS_TRIGGERED_BY_IDLING, this.isUiDismissTriggeredByIdling);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCard(String str) {
        if (!this.voiceInteractor.canDisplayVoiceUI()) {
            Log.i(TAG, "Can't show card because can't display voice ui");
        } else if (TextUtils.isEmpty(str)) {
            this.voiceView.hideCard();
            this.showingCard = false;
        } else {
            this.showingCard = true;
            this.voiceView.showCard(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void voiceChromeDismissed() {
        if (this.showingCard && this.voiceView.isCardVisible()) {
            Log.i(TAG, "Voice chrome dismissed due to showing card");
            this.showingCard = false;
        } else if (this.voiceView.isCardVisible()) {
        } else {
            Log.i(TAG, "finishing voice ui because chrome dismissed.");
            this.voiceInteractor.finishVoiceActivity();
        }
    }

    @MainThread
    public void attachToWindow() {
        this.voiceInteractor.showOverLockscreenIfRequired();
    }

    @MainThread
    public boolean backButtonPressed() {
        return this.voiceInteractor.backButtonPressed();
    }

    @MainThread
    public void destroy() {
        this.voiceInteractor.disconnectFromAlexaService();
        this.voiceInteractor.deregisterStateListeners();
        this.voiceInteractor.deregisterForDisplayStateUpdates();
        this.voiceInteractor.releaseFromAlexaService();
        if (this.voiceInteractor.isActivityConfigurationChanging()) {
            this.voiceView.prepareForConfigurationChange();
        } else {
            this.voiceView.cleanup();
        }
        this.disposable.dispose();
    }

    @MainThread
    public void initialize(@Nullable Intent intent, @Nullable Bundle bundle) {
        restoreState(bundle);
        this.voiceInteractor.updateUiOptions(intent);
        this.voiceInteractor.registerForDisplayStateUpdates();
        if (this.voiceView.isCardVisible()) {
            this.voiceInteractor.setTouchEventPassThrough(false);
        }
    }

    public /* synthetic */ void lambda$observeCardRendering$1$VoicePresenter(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            this.voiceInteractor.setTouchEventPassThrough(false);
        }
    }

    @MainThread
    public void pause() {
        this.voiceInteractor.stopControllingAccessibility();
    }

    @MainThread
    public void reinitialize(Intent intent) {
        this.voiceInteractor.updateUiOptions(intent);
        this.voiceInteractor.showOverLockscreenIfRequired();
        if (!this.isActivityVisible) {
            this.voiceInteractor.hideCard();
        }
    }

    @MainThread
    public void resume() {
        this.voiceInteractor.promptUserToUnlockPhoneIfRequired();
        this.voiceInteractor.startControllingAccessibility();
    }

    @MainThread
    public void saveInstanceState(Bundle bundle) {
        saveState(bundle);
        this.voiceInteractor.saveInstanceState(bundle);
    }

    @MainThread
    public void start() {
        this.isActivityVisible = true;
        this.voiceInteractor.acquireScreenLock();
        this.voiceInteractor.connectToAlexaService();
        this.voiceInteractor.registerStateListeners();
        this.voiceInteractor.requestPhoneUnlockIfRequired();
        this.voiceInteractor.registerExternalCardListener();
    }

    @MainThread
    public void stop() {
        Log.i(TAG, "Alexa UI activity is stopping.");
        this.isActivityVisible = false;
        if (!this.voiceInteractor.isActivityConfigurationChanging()) {
            if (this.voiceInteractor.isAlexaActive() && !this.isUiDismissTriggeredByIdling && this.canCancelVoiceExperience) {
                Log.i(TAG, "UI activity is not dismissed by idling. Cancelling ongoing interaction");
                this.voiceInteractor.cancelVoiceExperience();
            }
            this.voiceInteractor.broadcastAlexaUiFinished();
        } else {
            Log.i(TAG, "Activity configuration is changing");
        }
        this.voiceInteractor.releaseScreenLock();
        if (!this.voiceInteractor.isExpectingSubsequentLifecycleCallbacks()) {
            this.voiceInteractor.disconnectFromAlexaService();
            this.voiceInteractor.deregisterStateListeners();
        }
        this.voiceInteractor.deregisterExternalCardListener();
    }
}
