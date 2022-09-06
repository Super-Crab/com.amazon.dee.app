package com.amazon.alexa.voice.ftue;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.locale.DlsFeatureEnabler;
import com.amazon.alexa.voice.locale.LocaleCombinationParameters;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.locale.LocaleParameters;
import com.amazon.alexa.voice.locale.SupportedLocaleCombinationsCallback;
import com.amazon.alexa.voice.locale.SupportedLocalesCallback;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.screen.ScreenLockManager;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.OnPermissionResultReceivedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.OnReturningPrimerDismissedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerController;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerController;
import com.amazon.alexa.voice.ui.onedesign.ftue.primer.VoicePermissionGrantedListener;
import com.amazon.alexa.voice.ui.onedesign.transitions.DismissContentTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.PushContentTransition;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public abstract class FtueManager {
    private final DlsFeatureEnabler dlsFeatureEnabler;
    protected FtuePreference ftuePreference;
    private final LocaleInteractor localeInteractor;
    protected boolean needUpdateHandsFreePrimer;
    protected final VoxMetricEventProcessingService voxMetricEventProcessingService;

    public FtueManager(FtuePreference ftuePreference, VoxMetricEventProcessingService voxMetricEventProcessingService, LocaleInteractor localeInteractor, DlsFeatureEnabler dlsFeatureEnabler) {
        this.ftuePreference = ftuePreference;
        this.needUpdateHandsFreePrimer = false;
        this.voxMetricEventProcessingService = voxMetricEventProcessingService;
        this.localeInteractor = localeInteractor;
        this.dlsFeatureEnabler = dlsFeatureEnabler;
    }

    public void clearFtuePreferences() {
        this.ftuePreference.clearLegacyFtuePreference();
        this.ftuePreference.clearWakewordFtuePreference();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finishFtueWorkflow(@Nullable OnFtueCompletedListener onFtueCompletedListener) {
        this.ftuePreference.setLegacyFtueCompleted();
        if (this.needUpdateHandsFreePrimer) {
            this.ftuePreference.setWakewordFtueCompleted();
        }
        if (onFtueCompletedListener != null) {
            onFtueCompletedListener.onFtueCompleted(true);
        }
        this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.FTUE_COMPLETED));
    }

    public abstract OnPermissionResultReceivedListener getOnPermissionResultReceivedListener(Router router, VoicePermissionsChecker voicePermissionsChecker, OnFtueCompletedListener onFtueCompletedListener);

    public OnReturningPrimerDismissedListener getOnReturningPrimerDismissedListener(final OnFtueCompletedListener onFtueCompletedListener) {
        return new OnReturningPrimerDismissedListener() { // from class: com.amazon.alexa.voice.ftue.-$$Lambda$FtueManager$kkCTm1Biwifbh07CSGA9Gw-3uVs
            @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.OnReturningPrimerDismissedListener
            public final void onReturningPrimerDismissed() {
                FtueManager.this.lambda$getOnReturningPrimerDismissedListener$0$FtueManager(onFtueCompletedListener);
            }
        };
    }

    public abstract VoicePermissionGrantedListener getVoicePermissionGrantedListener(Router router, OnFtueCompletedListener onFtueCompletedListener);

    public /* synthetic */ void lambda$getOnReturningPrimerDismissedListener$0$FtueManager(OnFtueCompletedListener onFtueCompletedListener) {
        if (this.needUpdateHandsFreePrimer) {
            this.ftuePreference.setWakewordFtueCompleted();
        }
        if (onFtueCompletedListener != null) {
            onFtueCompletedListener.onFtueCompleted(false);
        }
    }

    public /* synthetic */ void lambda$null$1$FtueManager(Router router, LocaleCombinationParameters localeCombinationParameters) {
        if (router.isAttached()) {
            replaceViewController(router, LanguageCombinationPrimerController.create(LanguageCombinationParametersAdapter.getLanguagePrimerParameters(localeCombinationParameters)));
        } else {
            Logger.debug("router is not attached when receiving supported locales");
        }
    }

    public /* synthetic */ void lambda$null$3$FtueManager(Router router, LocaleParameters localeParameters) {
        if (router.isAttached()) {
            replaceViewController(router, LanguagePrimerController.create(LanguageParametersAdapter.getLanguagePrimerParameters(localeParameters)));
        } else {
            Logger.debug("router is not attached when receiving supported locales");
        }
    }

    public /* synthetic */ void lambda$showLanguageSettingsPrimer$2$FtueManager(final Router router, final LocaleCombinationParameters localeCombinationParameters) {
        Logger.debug("SupportedLocaleCombinationsCallback is called");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.voice.ftue.-$$Lambda$FtueManager$b3Sebzyb0x89BhR23yUV_wGCIT4
            @Override // java.lang.Runnable
            public final void run() {
                FtueManager.this.lambda$null$1$FtueManager(router, localeCombinationParameters);
            }
        });
    }

    public /* synthetic */ void lambda$showLanguageSettingsPrimer$4$FtueManager(final Router router, final LocaleParameters localeParameters) {
        Logger.debug("SupportedLocalesCallback is called");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.voice.ftue.-$$Lambda$FtueManager$0Of8DhnFmS65c9OhujPfRLzG9c4
            @Override // java.lang.Runnable
            public final void run() {
                FtueManager.this.lambda$null$3$FtueManager(router, localeParameters);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean needsLanguageSettings() {
        LocaleInteractor localeInteractor = this.localeInteractor;
        return localeInteractor != null && localeInteractor.needsLanguagePickerEducation();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceScrimController(ScreenLockManager screenLockManager, Router router, ViewController viewController) {
        screenLockManager.releaseLock();
        replaceViewController(router, viewController);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceViewController(Router router, ViewController viewController) {
        router.replaceCurrentController(new ControllerTransaction(viewController, new DismissContentTransition(), new PushContentTransition()));
    }

    public abstract boolean requiresFtue(VoicePermissionsChecker voicePermissionsChecker, String str, String str2);

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLanguageSettingsPrimer(final Router router) {
        if (this.localeInteractor != null) {
            Logger.debug("showLanguageSettingsPrimer");
            finishFtueWorkflow(null);
            if (this.dlsFeatureEnabler.isDLSEnabled()) {
                this.localeInteractor.fetchSupportedLocalesCombinations(new SupportedLocaleCombinationsCallback() { // from class: com.amazon.alexa.voice.ftue.-$$Lambda$FtueManager$Njq9Rit00aJhJ1AwxKukv8mLGjM
                    @Override // com.amazon.alexa.voice.locale.SupportedLocaleCombinationsCallback
                    public final void onLocaleCombinationParams(LocaleCombinationParameters localeCombinationParameters) {
                        FtueManager.this.lambda$showLanguageSettingsPrimer$2$FtueManager(router, localeCombinationParameters);
                    }
                });
            } else {
                this.localeInteractor.fetchSupportedLocales(new SupportedLocalesCallback() { // from class: com.amazon.alexa.voice.ftue.-$$Lambda$FtueManager$H60ZiN4EZ0H4eKoAjJTl1jtxAc8
                    @Override // com.amazon.alexa.voice.locale.SupportedLocalesCallback
                    public final void onLocaleParams(LocaleParameters localeParameters) {
                        FtueManager.this.lambda$showLanguageSettingsPrimer$4$FtueManager(router, localeParameters);
                    }
                });
            }
        }
    }

    public abstract boolean startFtueWorkflowIfNeeded(VoicePermissionsChecker voicePermissionsChecker, Router router, ScreenLockManager screenLockManager, @NonNull String str, @NonNull String str2);

    public FtueManager(FtuePreference ftuePreference, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        this(ftuePreference, voxMetricEventProcessingService, null, null);
    }
}
