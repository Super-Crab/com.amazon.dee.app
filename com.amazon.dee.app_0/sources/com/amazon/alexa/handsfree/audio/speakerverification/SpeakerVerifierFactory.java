package com.amazon.alexa.handsfree.audio.speakerverification;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.audio.UserSpeechProvider;
import com.amazon.alexa.handsfree.audio.metrics.AudioMetricsReporter;
import com.amazon.alexa.wakeword.pryon.AudioPlaybackConfigurationHelper;
import com.amazon.alexa.wakeword.speakerverification.profile.ProfileContentProviderHelper;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class SpeakerVerifierFactory {
    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public SpeakerVerifier createSpeakerVerifier(@NonNull Context context, @NonNull VerificationCallbacks verificationCallbacks, @NonNull UserSpeechProvider userSpeechProvider, @NonNull WakeWordModelProvider wakeWordModelProvider) {
        return new SpeakerVerifier(wakeWordModelProvider, new ProfileContentProviderHelper(context), verificationCallbacks, userSpeechProvider.getOriginalAudioSinkWrapper(), userSpeechProvider.getVerifiedAudioSink(), new AudioMetricsReporter(context), AudioPlaybackConfigurationHelper.getInstance());
    }
}
