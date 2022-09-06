package com.amazon.alexa.voice.wakeword;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaServicesApis;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.amazon.alexa.voice.wakeword.WakeWordFeatureGate;
import com.amazon.alexa.wakeword.precondition.WakeWordPrecondition;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class WakeWordAuthority implements WakeWordFeatureGate.AvailabilityListener {
    private final AlexaServicesConnection alexaServicesConnection;
    private Boolean currentState;
    private final Map<ObservableWakeWordPrecondition, DisposableWakeWordPrecondition> preconditions = new HashMap();
    private final PreconditionsChangeListener preconditionsChangeListener = new PreconditionsChangeListener();
    private final WakeWordFeatureGate wakeWordFeatureGate;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class PreconditionsChangeListener implements WakeWordPrecondition.ChangeListener {
        private PreconditionsChangeListener() {
        }

        @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition.ChangeListener
        public void onPreconditionStateChanged(boolean z) {
            synchronized (WakeWordAuthority.this) {
                if (WakeWordAuthority.this.wakeWordFeatureGate.isAvailable()) {
                    WakeWordAuthority.this.determineIfWakeWordIsAllowed();
                }
            }
        }
    }

    public WakeWordAuthority(AlexaServicesConnection alexaServicesConnection, WakewordPreference wakewordPreference, WakeWordFeatureGate wakeWordFeatureGate, ApplicationLifecycleService applicationLifecycleService, VoicePermissionsAuthority voicePermissionsAuthority, WakeWordEventHandler wakeWordEventHandler, VoiceOverUtility voiceOverUtility) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.wakeWordFeatureGate = wakeWordFeatureGate;
        wakeWordFeatureGate.register(this);
        initPreconditions(wakewordPreference, applicationLifecycleService, voicePermissionsAuthority, wakeWordEventHandler, voiceOverUtility);
    }

    private void addPreconditions(ObservableWakeWordPrecondition... observableWakeWordPreconditionArr) {
        for (ObservableWakeWordPrecondition observableWakeWordPrecondition : observableWakeWordPreconditionArr) {
            if (!this.preconditions.containsKey(observableWakeWordPrecondition)) {
                DisposableWakeWordPrecondition disposableWakeWordPrecondition = new DisposableWakeWordPrecondition(observableWakeWordPrecondition);
                this.preconditions.put(observableWakeWordPrecondition, disposableWakeWordPrecondition);
                disposableWakeWordPrecondition.subscribe(this.preconditionsChangeListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void determineIfWakeWordIsAllowed() {
        if (this.wakeWordFeatureGate.isAvailable()) {
            boolean z = !this.preconditions.isEmpty();
            for (DisposableWakeWordPrecondition disposableWakeWordPrecondition : this.preconditions.values()) {
                z &= disposableWakeWordPrecondition.isWakeWordAllowed();
            }
            Boolean bool = this.currentState;
            if (bool != null && z == bool.booleanValue()) {
                return;
            }
            this.currentState = Boolean.valueOf(z);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("In app wake word is ");
            outline107.append(this.currentState.booleanValue() ? "allowed" : "disallowed");
            Logger.info(outline107.toString());
            if (this.currentState.booleanValue()) {
                startListeningForWakeWord(this.alexaServicesConnection);
            } else {
                stopListeningForWakeWord(this.alexaServicesConnection);
            }
        }
    }

    @VisibleForTesting
    void initPreconditions(WakewordPreference wakewordPreference, ApplicationLifecycleService applicationLifecycleService, VoicePermissionsAuthority voicePermissionsAuthority, WakeWordEventHandler wakeWordEventHandler, VoiceOverUtility voiceOverUtility) {
        addPreconditions(new PermissionsWakewordPrecondition(voicePermissionsAuthority), new UserAllowedWakewordPrecondition(wakewordPreference), new AppFocusWakeWordPrecondition(applicationLifecycleService), new FeatureOverrideWakewordPrecondition(wakeWordEventHandler), new VoiceOverWakeWordPrecondition(voiceOverUtility, applicationLifecycleService));
    }

    public synchronized boolean isDetectingWakeWord() {
        if (this.wakeWordFeatureGate.isAvailable()) {
            return this.currentState.booleanValue();
        }
        Logger.info("can't send isDetectingWakeWord as wake word is unavailable.");
        return false;
    }

    @VisibleForTesting
    synchronized boolean isWakeWordAllowed() {
        boolean z;
        if (this.currentState != null) {
            if (this.currentState.booleanValue()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    @Override // com.amazon.alexa.voice.wakeword.WakeWordFeatureGate.AvailabilityListener
    public synchronized void onAvailable() {
        this.currentState = null;
        if (this.wakeWordFeatureGate.isAvailable()) {
            Logger.info("In app wake word is available.");
            determineIfWakeWordIsAllowed();
        } else {
            Logger.info("can't send registerWakeWordClient as wake word is unavailable.");
        }
    }

    @Override // com.amazon.alexa.voice.wakeword.WakeWordFeatureGate.AvailabilityListener
    public synchronized void onUnavailable() {
        Logger.info("In app wake word is unavailable.");
        if (this.alexaServicesConnection.isConnected()) {
            stopListeningForWakeWord(this.alexaServicesConnection);
        } else {
            Logger.info("can't send deregisterWakeWordClient as wake word is unavailable.");
        }
    }

    @VisibleForTesting
    void startListeningForWakeWord(AlexaServicesConnection alexaServicesConnection) {
        AlexaServicesApis.WakeWord.startListening(alexaServicesConnection, null);
    }

    @VisibleForTesting
    void stopListeningForWakeWord(AlexaServicesConnection alexaServicesConnection) {
        AlexaServicesApis.WakeWord.stopListening(alexaServicesConnection);
    }

    @VisibleForTesting
    WakeWordAuthority(AlexaServicesConnection alexaServicesConnection, WakeWordFeatureGate wakeWordFeatureGate, AppFocusWakeWordPrecondition appFocusWakeWordPrecondition, UserAllowedWakewordPrecondition userAllowedWakewordPrecondition, PermissionsWakewordPrecondition permissionsWakewordPrecondition, FeatureOverrideWakewordPrecondition featureOverrideWakewordPrecondition, VoiceOverWakeWordPrecondition voiceOverWakeWordPrecondition) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.wakeWordFeatureGate = wakeWordFeatureGate;
        addPreconditions(appFocusWakeWordPrecondition, userAllowedWakewordPrecondition, permissionsWakewordPrecondition, featureOverrideWakewordPrecondition, voiceOverWakeWordPrecondition);
    }
}
