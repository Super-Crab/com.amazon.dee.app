package com.amazon.alexa.voice.ftue;

import com.amazon.alexa.voice.locale.DlsFeatureEnabler;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.metrics.VoxLaunchConstants;
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
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes11.dex */
public class HandsFreeFtueManager extends FtueManager {
    private static final Set<String> SUPPORTED_ACTIONS = Collections.singleton(VoxLaunchConstants.VOICE_INGRESS);
    private static final Set<String> SUPPORTED_REFERRER_TYPES = Sets.newHashSet("jasperhomevoxcard", "jasperhomeheader");
    private static final String TAG = "HandsFreeFtueManager";

    public HandsFreeFtueManager(FtuePreference ftuePreference, VoxMetricEventProcessingService voxMetricEventProcessingService, LocaleInteractor localeInteractor, DlsFeatureEnabler dlsFeatureEnabler) {
        super(ftuePreference, voxMetricEventProcessingService, localeInteractor, dlsFeatureEnabler);
        this.needUpdateHandsFreePrimer = true;
    }

    private boolean flowSupportsReturningUserPrimer(String str, String str2) {
        String str3 = "LaunchType: " + str + " , referrerType " + str2;
        return SUPPORTED_ACTIONS.contains(str) || SUPPORTED_REFERRER_TYPES.contains(str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getVoicePermissionGrantedListener$1() {
    }

    private boolean needsNewUserFtue() {
        return !this.ftuePreference.hasCompletedLegacyFtue() && !this.ftuePreference.hasCompletedWakewordFtue();
    }

    private boolean needsReturningUserFtue(String str, String str2) {
        return !this.ftuePreference.hasCompletedWakewordFtue() && flowSupportsReturningUserPrimer(str, str2);
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public OnPermissionResultReceivedListener getOnPermissionResultReceivedListener(final Router router, final VoicePermissionsChecker voicePermissionsChecker, final OnFtueCompletedListener onFtueCompletedListener) {
        return new OnPermissionResultReceivedListener() { // from class: com.amazon.alexa.voice.ftue.-$$Lambda$HandsFreeFtueManager$Wr2ZOU6L15FPKr6HalX8fIuIbYg
            @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.OnPermissionResultReceivedListener
            public final void onPermissionResultReceived() {
                HandsFreeFtueManager.this.lambda$getOnPermissionResultReceivedListener$0$HandsFreeFtueManager(voicePermissionsChecker, router, onFtueCompletedListener);
            }
        };
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public VoicePermissionGrantedListener getVoicePermissionGrantedListener(Router router, OnFtueCompletedListener onFtueCompletedListener) {
        return $$Lambda$HandsFreeFtueManager$2xriQO2vOej0JZCY9yS58EGru7Y.INSTANCE;
    }

    public /* synthetic */ void lambda$getOnPermissionResultReceivedListener$0$HandsFreeFtueManager(VoicePermissionsChecker voicePermissionsChecker, Router router, OnFtueCompletedListener onFtueCompletedListener) {
        if (voicePermissionsChecker.hasMinimumPermissions()) {
            if (needsLanguageSettings()) {
                showLanguageSettingsPrimer(router);
                return;
            }
            finishFtueWorkflow(onFtueCompletedListener);
            router.popCurrentController();
            return;
        }
        replaceViewController(router, GoToSettingsController.create());
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public boolean requiresFtue(VoicePermissionsChecker voicePermissionsChecker, String str, String str2) {
        return needsNewUserFtue() || (voicePermissionsChecker.hasMinimumPermissions() ^ true) || needsReturningUserFtue(str, str2);
    }

    @Override // com.amazon.alexa.voice.ftue.FtueManager
    public boolean startFtueWorkflowIfNeeded(VoicePermissionsChecker voicePermissionsChecker, Router router, ScreenLockManager screenLockManager, String str, String str2) {
        if (needsNewUserFtue()) {
            replaceScrimController(screenLockManager, router, NewUserPrimerController.create(VoicePermissionsAuthority.getAllPermissions()));
            this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.FTUE_STARTED));
            return false;
        } else if (voicePermissionsChecker.hasMinimumPermissions()) {
            if (!needsReturningUserFtue(str, str2)) {
                return true;
            }
            replaceScrimController(screenLockManager, router, ReturningPrimerController.create());
            return false;
        } else {
            replaceScrimController(screenLockManager, router, GoToSettingsController.create());
            return false;
        }
    }
}
