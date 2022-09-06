package com.amazon.alexa.voice.ftue;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.screen.ScreenLockManager;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.NewUserPrimerController;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.OnPermissionResultReceivedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.ReturningPrimerController;
import com.amazon.alexa.voice.ui.onedesign.ftue.primer.VoicePermissionGrantedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsController;
import com.amazon.regulator.Router;
/* loaded from: classes11.dex */
public class HandsFreeWithoutHintsFtueManager extends FtueManager {
    private static final String TAG = "HandsFreeWithoutHintsFtueManager";
    private final String viewProperties;

    public HandsFreeWithoutHintsFtueManager(FtuePreference ftuePreference, VoxMetricEventProcessingService voxMetricEventProcessingService, String str) {
        super(ftuePreference, voxMetricEventProcessingService);
        this.needUpdateHandsFreePrimer = true;
        this.viewProperties = str;
    }

    static /* synthetic */ void lambda$getVoicePermissionGrantedListener$1() {
    }

    private boolean needsReturningUserFtue() {
        return this.ftuePreference.hasCompletedLegacyFtue() && !this.ftuePreference.hasCompletedWakewordFtue();
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public OnPermissionResultReceivedListener getOnPermissionResultReceivedListener(final Router router, final VoicePermissionsChecker voicePermissionsChecker, final OnFtueCompletedListener onFtueCompletedListener) {
        return new OnPermissionResultReceivedListener() { // from class: com.amazon.alexa.voice.ftue.-$$Lambda$HandsFreeWithoutHintsFtueManager$_8SeKr0hVpL2ezgpwxdAJ9zZGts
            @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.OnPermissionResultReceivedListener
            public final void onPermissionResultReceived() {
                HandsFreeWithoutHintsFtueManager.this.lambda$getOnPermissionResultReceivedListener$0$HandsFreeWithoutHintsFtueManager(voicePermissionsChecker, onFtueCompletedListener, router);
            }
        };
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public VoicePermissionGrantedListener getVoicePermissionGrantedListener(Router router, OnFtueCompletedListener onFtueCompletedListener) {
        return $$Lambda$HandsFreeWithoutHintsFtueManager$qN1IVvp3kN19r8pTuDiypMf9T0.INSTANCE;
    }

    public /* synthetic */ void lambda$getOnPermissionResultReceivedListener$0$HandsFreeWithoutHintsFtueManager(VoicePermissionsChecker voicePermissionsChecker, OnFtueCompletedListener onFtueCompletedListener, Router router) {
        if (voicePermissionsChecker.hasMinimumPermissions()) {
            finishFtueWorkflow(onFtueCompletedListener);
            router.popCurrentController();
            return;
        }
        replaceViewController(router, GoToSettingsController.create());
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public boolean requiresFtue(VoicePermissionsChecker voicePermissionsChecker, String str, String str2) {
        return true;
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public boolean startFtueWorkflowIfNeeded(VoicePermissionsChecker voicePermissionsChecker, Router router, ScreenLockManager screenLockManager, String str, String str2) {
        if (voicePermissionsChecker.hasMinimumPermissions()) {
            if (needsReturningUserFtue()) {
                replaceViewController(router, ReturningPrimerController.create());
                return false;
            }
            startNewUserPrimer(router, this.viewProperties);
            return false;
        }
        startNewUserPrimer(router, this.viewProperties);
        return false;
    }

    @VisibleForTesting
    void startNewUserPrimer(Router router, String str) {
        String str2 = "Showing new user primer with ftueConfig: " + str;
        replaceViewController(router, NewUserPrimerController.create(VoicePermissionsAuthority.getAllPermissions(), str));
        this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.FTUE_STARTED));
    }
}
