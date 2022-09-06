package com.amazon.alexa.voice.ftue;

import com.amazon.alexa.voice.locale.DlsFeatureEnabler;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.screen.ScreenLockManager;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.OnPermissionResultReceivedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerController;
import com.amazon.alexa.voice.ui.onedesign.ftue.primer.VoicePermissionGrantedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsController;
import com.amazon.regulator.Router;
/* loaded from: classes11.dex */
public class LegacyFtueManager extends FtueManager {
    public LegacyFtueManager(FtuePreference ftuePreference, VoxMetricEventProcessingService voxMetricEventProcessingService, LocaleInteractor localeInteractor, DlsFeatureEnabler dlsFeatureEnabler) {
        super(ftuePreference, voxMetricEventProcessingService, localeInteractor, dlsFeatureEnabler);
    }

    static /* synthetic */ void lambda$getOnPermissionResultReceivedListener$1() {
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public OnPermissionResultReceivedListener getOnPermissionResultReceivedListener(Router router, VoicePermissionsChecker voicePermissionsChecker, OnFtueCompletedListener onFtueCompletedListener) {
        return $$Lambda$LegacyFtueManager$GP0B3SwyEtN9BF5KEQbnyJK86w.INSTANCE;
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public VoicePermissionGrantedListener getVoicePermissionGrantedListener(final Router router, final OnFtueCompletedListener onFtueCompletedListener) {
        return new VoicePermissionGrantedListener() { // from class: com.amazon.alexa.voice.ftue.-$$Lambda$LegacyFtueManager$d3rdhhOA5RXDzzQYR0np-lEFmZI
            @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.VoicePermissionGrantedListener
            public final void onVoicePermissionGranted() {
                LegacyFtueManager.this.lambda$getVoicePermissionGrantedListener$0$LegacyFtueManager(router, onFtueCompletedListener);
            }
        };
    }

    public /* synthetic */ void lambda$getVoicePermissionGrantedListener$0$LegacyFtueManager(Router router, OnFtueCompletedListener onFtueCompletedListener) {
        if (needsLanguageSettings()) {
            showLanguageSettingsPrimer(router);
            return;
        }
        finishFtueWorkflow(onFtueCompletedListener);
        router.popCurrentController();
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public boolean requiresFtue(VoicePermissionsChecker voicePermissionsChecker, String str, String str2) {
        return !this.ftuePreference.hasCompletedLegacyFtue() || !voicePermissionsChecker.hasMinimumPermissions();
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public boolean startFtueWorkflowIfNeeded(VoicePermissionsChecker voicePermissionsChecker, Router router, ScreenLockManager screenLockManager, String str, String str2) {
        boolean hasCompletedLegacyFtue = this.ftuePreference.hasCompletedLegacyFtue();
        if (!hasCompletedLegacyFtue || !voicePermissionsChecker.hasMinimumPermissions()) {
            if (!hasCompletedLegacyFtue) {
                replaceScrimController(screenLockManager, router, PrimerController.create(VoicePermissionsAuthority.getAllPermissions(), VoicePermissionsAuthority.getMinimumPermissions()));
                this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.FTUE_STARTED));
                return false;
            }
            replaceScrimController(screenLockManager, router, GoToSettingsController.create());
            return false;
        }
        return true;
    }
}
