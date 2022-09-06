package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AlexaDialogExtras {
    private final AlexaUserInterfaceOptions alexaUserInterfaceOptions;
    private final AudioOutputContext audioOutputContext;
    private final boolean enableSpeakerVerification;
    private final String invocationType;
    private final boolean isUserVoiceVerified;
    private final LaunchType launchType;
    private final boolean suppressEndpointSound;
    private final boolean suppressEndpointSoundOnlyOnFirstTurn;
    private final boolean suppressSpeechResponse;
    private final boolean suppressUserInterface;
    private final boolean suppressWakeSound;
    private final boolean suppressWakeSoundOnlyOnFirstTurn;
    private final boolean suppressWakewordVerification;
    static final DialogExtrasKeys KEY_IS_USER_VOICE_VERIFIED = new DialogExtrasKeys(DialogExtrasKeysEnum.IS_USER_VOICE_VERIFIED);
    static final DialogExtrasKeys KEY_SUPPRESS_ENDPOINT_SOUND = new DialogExtrasKeys(DialogExtrasKeysEnum.SUPPRESS_ENDPOINT_SOUND);
    static final DialogExtrasKeys KEY_SUPPRESS_ENDPOINT_SOUND_ONLY_ON_FIRST_TURN = new DialogExtrasKeys(DialogExtrasKeysEnum.SUPPRESS_ENDPOINT_SOUND_ONLY_ON_FIRST_TURN);
    static final DialogExtrasKeys KEY_SUPPRESS_UI = new DialogExtrasKeys(DialogExtrasKeysEnum.SUPPRESS_UI);
    static final DialogExtrasKeys KEY_SUPPRESS_WAKE_SOUND = new DialogExtrasKeys(DialogExtrasKeysEnum.SUPPRESS_WAKESOUND);
    static final DialogExtrasKeys KEY_SUPPRESS_WAKE_SOUND_ONLY_ON_FIRST_TURN = new DialogExtrasKeys(DialogExtrasKeysEnum.SUPPRESS_WAKESOUND_ONLY_ON_FIRST_TURN);
    static final DialogExtrasKeys KEY_INVOCATION_TYPE = new DialogExtrasKeys(DialogExtrasKeysEnum.INVOCATION_TYPE);
    static final DialogExtrasKeys KEY_LAUNCH_TYPE = new DialogExtrasKeys(DialogExtrasKeysEnum.LAUNCH_TYPE);
    static final DialogExtrasKeys KEY_AUDIO_OUTPUT_CONTEXT = new DialogExtrasKeys(DialogExtrasKeysEnum.AUDIO_OUTPUT_CONTEXT);
    static final DialogExtrasKeys KEY_AUDIO_OUTPUT_DEVICE_ADDRESS = new DialogExtrasKeys(DialogExtrasKeysEnum.AUDIO_OUTPUT_DEVICE_ADDRESS);
    static final DialogExtrasKeys KEY_AUDIO_OUTPUT_DEVICE_FRIENDLY_NAME = new DialogExtrasKeys(DialogExtrasKeysEnum.AUDIO_OUTPUT_DEVICE_FRIENDLY_NAME);
    static final DialogExtrasKeys KEY_AUDIO_OUTPUT_SHOULD_PLAY_OVER_SCO = new DialogExtrasKeys(DialogExtrasKeysEnum.AUDIO_OUTPUT_SHOULD_PLAY_OVER_SCO);
    static final DialogExtrasKeys KEY_USER_INTERFACE_OPTIONS = new DialogExtrasKeys(DialogExtrasKeysEnum.USER_INTERFACE_OPTIONS);
    static final DialogExtrasKeys KEY_SUPPRESS_WW_VERIFICATION = new DialogExtrasKeys(DialogExtrasKeysEnum.SUPPRESS_WW_VERIFICATION);
    static final DialogExtrasKeys KEY_ENABLE_SPEAKER_VERIFICATION = new DialogExtrasKeys(DialogExtrasKeysEnum.ENABLE_SPEAKER_VERIFICATION);
    static final DialogExtrasKeys KEY_SUPPRESS_SPEECH_RESPONSE = new DialogExtrasKeys(DialogExtrasKeysEnum.SUPPRESS_SPEECH_RESPONSE);

    /* loaded from: classes6.dex */
    public static class Builder {
        private AlexaUserInterfaceOptions alexaUserInterfaceOptions;
        private AudioOutputContext audioOutputContext;
        private boolean enableSpeakerVerification;
        private String invocationType;
        private boolean isUserVoiceVerified;
        private LaunchType launchType;
        private boolean suppressEndpointSound;
        private boolean suppressEndpointSoundOnlyOnFirstTurn;
        private boolean suppressSpeechResponse;
        private boolean suppressUserInterface;
        private boolean suppressWakeSound;
        private boolean suppressWakeSoundOnlyOnFirstTurn;
        private boolean suppressWakewordVerification;

        private Builder() {
        }

        public AlexaDialogExtras build() {
            return new AlexaDialogExtras(this);
        }

        public Builder enableSpeakerVerification(boolean z) {
            this.enableSpeakerVerification = z;
            return this;
        }

        public Builder setAlexaUserInterfaceOptions(AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
            this.alexaUserInterfaceOptions = alexaUserInterfaceOptions;
            return this;
        }

        public Builder setAudioOutputContext(AudioOutputContext audioOutputContext) {
            this.audioOutputContext = audioOutputContext;
            return this;
        }

        public Builder setInvocationType(String str) {
            this.invocationType = str;
            return this;
        }

        public Builder setLaunchType(LaunchType launchType) {
            this.launchType = launchType;
            return this;
        }

        public Builder setUserVoiceVerified(boolean z) {
            this.isUserVoiceVerified = z;
            return this;
        }

        public Builder suppressEndpointSound(boolean z) {
            this.suppressEndpointSound = z;
            return this;
        }

        public Builder suppressEndpointSoundOnlyOnFirstTurn(boolean z) {
            this.suppressEndpointSoundOnlyOnFirstTurn = z;
            return this;
        }

        public Builder suppressSpeechResponse(boolean z) {
            this.suppressSpeechResponse = z;
            return this;
        }

        public Builder suppressUserInterface(boolean z) {
            this.suppressUserInterface = z;
            return this;
        }

        public Builder suppressWakeSound(boolean z) {
            this.suppressWakeSound = z;
            return this;
        }

        public Builder suppressWakeSoundOnlyOnFirstTurn(boolean z) {
            this.suppressWakeSoundOnlyOnFirstTurn = z;
            return this;
        }

        public Builder suppressWakewordVerification(boolean z) {
            this.suppressWakewordVerification = z;
            return this;
        }
    }

    /* loaded from: classes6.dex */
    static class DialogExtrasBundleAdapter implements u<AlexaDialogExtras> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public AlexaDialogExtras mo844createFromBundle(Bundle bundle) {
            return new AlexaDialogExtras(bundle);
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(AlexaDialogExtras alexaDialogExtras) {
            return alexaDialogExtras.getBundle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class DialogExtrasKeys implements Bundles.Key {
        private final DialogExtrasKeysEnum enumVal;

        DialogExtrasKeys(DialogExtrasKeysEnum dialogExtrasKeysEnum) {
            this.enumVal = dialogExtrasKeysEnum;
        }

        @Override // com.amazon.alexa.api.Bundles.Key
        public String name() {
            return this.enumVal.getName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public enum DialogExtrasKeysEnum {
        IS_USER_VOICE_VERIFIED("userVoiceVerified"),
        SUPPRESS_ENDPOINT_SOUND("suppressEndpointSound"),
        SUPPRESS_UI("suppressUserInterface"),
        SUPPRESS_WAKESOUND("suppressWakeSound"),
        SUPPRESS_WAKESOUND_ONLY_ON_FIRST_TURN("suppressWakeSoundOnlyOnFirstTurn"),
        SUPPRESS_ENDPOINT_SOUND_ONLY_ON_FIRST_TURN("suppressEndpointSoundOnlyOnFirstTurn"),
        INVOCATION_TYPE(AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE),
        LAUNCH_TYPE("launchType"),
        AUDIO_OUTPUT_CONTEXT("audioOutputContext"),
        AUDIO_OUTPUT_DEVICE_ADDRESS("deviceAddress"),
        AUDIO_OUTPUT_DEVICE_FRIENDLY_NAME("deviceFriendlyName"),
        USER_INTERFACE_OPTIONS("uiOptions"),
        AUDIO_OUTPUT_SHOULD_PLAY_OVER_SCO("shouldPlayOverSco"),
        SUPPRESS_WW_VERIFICATION("suppressWakewordVerification"),
        ENABLE_SPEAKER_VERIFICATION("enableSpeakerVerification"),
        SUPPRESS_SPEECH_RESPONSE("suppressSpeechResponse");
        
        private String name;

        DialogExtrasKeysEnum(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogExtras(@Nullable Bundle bundle) {
        this.isUserVoiceVerified = getBoolean(bundle, KEY_IS_USER_VOICE_VERIFIED);
        this.suppressEndpointSound = getBoolean(bundle, KEY_SUPPRESS_ENDPOINT_SOUND);
        this.suppressEndpointSoundOnlyOnFirstTurn = getBoolean(bundle, KEY_SUPPRESS_ENDPOINT_SOUND_ONLY_ON_FIRST_TURN);
        this.suppressUserInterface = getBoolean(bundle, KEY_SUPPRESS_UI);
        this.suppressWakeSound = getBoolean(bundle, KEY_SUPPRESS_WAKE_SOUND);
        this.suppressWakeSoundOnlyOnFirstTurn = getBoolean(bundle, KEY_SUPPRESS_WAKE_SOUND_ONLY_ON_FIRST_TURN);
        this.invocationType = getString(bundle, KEY_INVOCATION_TYPE);
        this.launchType = LaunchType.fromString(getString(bundle, KEY_LAUNCH_TYPE));
        this.audioOutputContext = getAudioOutputContext(bundle);
        this.alexaUserInterfaceOptions = getAlexaUserInterfaceOptions(bundle);
        this.suppressWakewordVerification = getBoolean(bundle, KEY_SUPPRESS_WW_VERIFICATION);
        this.enableSpeakerVerification = getBoolean(bundle, KEY_ENABLE_SPEAKER_VERIFICATION);
        this.suppressSpeechResponse = getBoolean(bundle, KEY_SUPPRESS_SPEECH_RESPONSE);
    }

    private AlexaDialogExtras(Builder builder) {
        this.isUserVoiceVerified = builder.isUserVoiceVerified;
        this.suppressEndpointSound = builder.suppressEndpointSound;
        this.suppressEndpointSoundOnlyOnFirstTurn = builder.suppressEndpointSoundOnlyOnFirstTurn;
        this.suppressWakeSound = builder.suppressWakeSound;
        this.suppressWakeSoundOnlyOnFirstTurn = builder.suppressWakeSoundOnlyOnFirstTurn;
        this.suppressUserInterface = builder.suppressUserInterface;
        this.invocationType = builder.invocationType;
        this.launchType = builder.launchType != null ? builder.launchType : LaunchType.UNKNOWN;
        this.audioOutputContext = builder.audioOutputContext != null ? builder.audioOutputContext : AudioOutputContext.DEFAULT;
        this.alexaUserInterfaceOptions = builder.alexaUserInterfaceOptions != null ? builder.alexaUserInterfaceOptions : AlexaUserInterfaceOptions.builder().build();
        this.suppressWakewordVerification = builder.suppressWakewordVerification;
        this.enableSpeakerVerification = builder.enableSpeakerVerification;
        this.suppressSpeechResponse = builder.suppressSpeechResponse;
    }

    public static Builder builder() {
        return new Builder();
    }

    private boolean getBoolean(@Nullable Bundle bundle, Bundles.Key key) {
        return bundle != null && Bundles.getBoolean(bundle, key);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder getBuilder(@NonNull AlexaDialogExtras alexaDialogExtras) {
        return builder().setAudioOutputContext(alexaDialogExtras.getAudioOutputContext()).setInvocationType(alexaDialogExtras.getInvocationType()).setLaunchType(alexaDialogExtras.getLaunchType()).setUserVoiceVerified(alexaDialogExtras.isUserVoiceVerified()).suppressUserInterface(alexaDialogExtras.suppressUserInterface()).setAlexaUserInterfaceOptions(alexaDialogExtras.getAlexaUserInterfaceOptions()).suppressWakeSoundOnlyOnFirstTurn(alexaDialogExtras.suppressWakeSoundOnlyOnFirstTurn()).suppressWakeSound(alexaDialogExtras.suppressWakeSound()).suppressEndpointSoundOnlyOnFirstTurn(alexaDialogExtras.suppressEndpointSoundOnlyOnFirstTurn()).suppressEndpointSound(alexaDialogExtras.suppressEndpointSound()).suppressWakewordVerification(alexaDialogExtras.suppressWakewordVerification()).enableSpeakerVerification(alexaDialogExtras.enableSpeakerVerification()).suppressSpeechResponse(alexaDialogExtras.suppressSpeechResponse());
    }

    private String getString(@Nullable Bundle bundle, Bundles.Key key) {
        if (bundle != null) {
            return Bundles.getOptionalString(bundle, key);
        }
        return null;
    }

    public boolean enableSpeakerVerification() {
        return this.enableSpeakerVerification;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlexaDialogExtras)) {
            return false;
        }
        AlexaDialogExtras alexaDialogExtras = (AlexaDialogExtras) obj;
        return isUserVoiceVerified() == alexaDialogExtras.isUserVoiceVerified() && this.suppressEndpointSound == alexaDialogExtras.suppressEndpointSound && this.suppressEndpointSoundOnlyOnFirstTurn == alexaDialogExtras.suppressEndpointSoundOnlyOnFirstTurn && this.suppressUserInterface == alexaDialogExtras.suppressUserInterface && this.suppressWakeSound == alexaDialogExtras.suppressWakeSound && this.suppressWakeSoundOnlyOnFirstTurn == alexaDialogExtras.suppressWakeSoundOnlyOnFirstTurn && this.suppressWakewordVerification == alexaDialogExtras.suppressWakewordVerification && this.enableSpeakerVerification == alexaDialogExtras.enableSpeakerVerification && this.suppressSpeechResponse == alexaDialogExtras.suppressSpeechResponse && Objects.equals(getInvocationType(), alexaDialogExtras.getInvocationType()) && getLaunchType() == alexaDialogExtras.getLaunchType() && Objects.equals(getAudioOutputContext(), alexaDialogExtras.getAudioOutputContext()) && Objects.equals(getAlexaUserInterfaceOptions(), alexaDialogExtras.getAlexaUserInterfaceOptions());
    }

    public AlexaUserInterfaceOptions getAlexaUserInterfaceOptions() {
        return this.alexaUserInterfaceOptions;
    }

    AlexaUserInterfaceOptions getAlexaUserInterfaceOptions(@Nullable Bundle bundle) {
        return (bundle == null || !bundle.containsKey(KEY_USER_INTERFACE_OPTIONS.name())) ? AlexaUserInterfaceOptions.builder().build() : new AlexaUserInterfaceOptions(Bundles.getOptionalBundle(bundle, KEY_USER_INTERFACE_OPTIONS));
    }

    public AudioOutputContext getAudioOutputContext() {
        return this.audioOutputContext;
    }

    AudioOutputContext getAudioOutputContext(@Nullable Bundle bundle) {
        boolean z;
        Bundle bundle2;
        AudioDevice audioDevice = AudioDevice.DEFAULT;
        if (bundle == null || (bundle2 = bundle.getBundle(KEY_AUDIO_OUTPUT_CONTEXT.name())) == null) {
            z = false;
        } else {
            String string = bundle2.getString(KEY_AUDIO_OUTPUT_DEVICE_ADDRESS.name());
            String string2 = bundle2.getString(KEY_AUDIO_OUTPUT_DEVICE_FRIENDLY_NAME.name());
            if (string == null) {
                string = AudioDevice.DEFAULT_PHYSICAL_ADDRESS;
            }
            if (string2 == null) {
                string2 = AudioDevice.DEFAULT_FRIENDLY_NAME;
            }
            audioDevice = AudioDevice.create(string, string2);
            z = bundle2.getBoolean(KEY_AUDIO_OUTPUT_SHOULD_PLAY_OVER_SCO.name());
        }
        return new AudioOutputContext(audioDevice, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle2.putString(KEY_AUDIO_OUTPUT_DEVICE_ADDRESS.name(), this.audioOutputContext.getDevice().getPhysicalAddress());
        bundle2.putString(KEY_AUDIO_OUTPUT_DEVICE_FRIENDLY_NAME.name(), this.audioOutputContext.getDevice().getFriendlyName());
        bundle2.putBoolean(KEY_AUDIO_OUTPUT_SHOULD_PLAY_OVER_SCO.name(), this.audioOutputContext.shouldPlayOverSco());
        bundle.putBoolean(KEY_IS_USER_VOICE_VERIFIED.name(), this.isUserVoiceVerified);
        bundle.putBoolean(KEY_SUPPRESS_ENDPOINT_SOUND.name(), this.suppressEndpointSound);
        bundle.putBoolean(KEY_SUPPRESS_ENDPOINT_SOUND_ONLY_ON_FIRST_TURN.name(), this.suppressEndpointSoundOnlyOnFirstTurn);
        bundle.putBoolean(KEY_SUPPRESS_UI.name(), this.suppressUserInterface);
        bundle.putBoolean(KEY_SUPPRESS_WAKE_SOUND.name(), this.suppressWakeSound);
        bundle.putBoolean(KEY_SUPPRESS_WAKE_SOUND_ONLY_ON_FIRST_TURN.name(), this.suppressWakeSoundOnlyOnFirstTurn);
        bundle.putString(KEY_INVOCATION_TYPE.name(), this.invocationType);
        bundle.putString(KEY_LAUNCH_TYPE.name(), this.launchType.name());
        bundle.putBundle(KEY_AUDIO_OUTPUT_CONTEXT.name(), bundle2);
        bundle.putBundle(KEY_USER_INTERFACE_OPTIONS.name(), this.alexaUserInterfaceOptions.getBundle());
        bundle.putBoolean(KEY_SUPPRESS_WW_VERIFICATION.name(), this.suppressWakewordVerification);
        bundle.putBoolean(KEY_ENABLE_SPEAKER_VERIFICATION.name(), this.enableSpeakerVerification);
        bundle.putBoolean(KEY_SUPPRESS_SPEECH_RESPONSE.name(), this.suppressSpeechResponse);
        return bundle;
    }

    @Nullable
    public String getInvocationType() {
        return this.invocationType;
    }

    public LaunchType getLaunchType() {
        return this.launchType;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(isUserVoiceVerified()), Boolean.valueOf(this.suppressEndpointSound), Boolean.valueOf(this.suppressEndpointSoundOnlyOnFirstTurn), Boolean.valueOf(this.suppressUserInterface), Boolean.valueOf(this.suppressWakeSound), Boolean.valueOf(this.suppressWakeSoundOnlyOnFirstTurn), Boolean.valueOf(this.suppressWakewordVerification), Boolean.valueOf(this.enableSpeakerVerification), Boolean.valueOf(this.suppressSpeechResponse), getInvocationType(), getLaunchType(), getAudioOutputContext(), getAlexaUserInterfaceOptions());
    }

    public boolean isUserVoiceVerified() {
        return this.isUserVoiceVerified;
    }

    public boolean suppressEndpointSound() {
        return this.suppressEndpointSound;
    }

    public boolean suppressEndpointSoundOnlyOnFirstTurn() {
        return this.suppressEndpointSoundOnlyOnFirstTurn;
    }

    public boolean suppressSpeechResponse() {
        return this.suppressSpeechResponse;
    }

    public boolean suppressUserInterface() {
        return this.suppressUserInterface;
    }

    public boolean suppressWakeSound() {
        return this.suppressWakeSound;
    }

    public boolean suppressWakeSoundOnlyOnFirstTurn() {
        return this.suppressWakeSoundOnlyOnFirstTurn;
    }

    public boolean suppressWakewordVerification() {
        return this.suppressWakewordVerification;
    }
}
