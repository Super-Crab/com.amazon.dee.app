package com.amazon.alexa.voice.wakeword;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public class PermissionsWakewordPrecondition implements ObservableWakeWordPrecondition {
    private final VoicePermissionsAuthority voicePermissionsAuthority;

    @VisibleForTesting
    public PermissionsWakewordPrecondition(VoicePermissionsAuthority voicePermissionsAuthority) {
        this.voicePermissionsAuthority = voicePermissionsAuthority;
    }

    @Override // com.amazon.alexa.voice.wakeword.ObservableWakeWordPrecondition
    public Observable<Boolean> isWakeWordAllowed() {
        return this.voicePermissionsAuthority.minimumPermissions();
    }
}
