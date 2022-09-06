package com.amazon.alexa.accessory.speechapi.speech;

import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DialogExtras.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0010\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u001e\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u001e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u001e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u001e\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras;", "", "builder", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras$Builder;", "(Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras$Builder;)V", "<set-?>", "", "defaultAudioDeviceFriendlyName", "getDefaultAudioDeviceFriendlyName", "()Ljava/lang/String;", "defaultAudioDevicePhysicalAddress", "getDefaultAudioDevicePhysicalAddress", AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, "getInvocationType", "", "shouldUseSco", "getShouldUseSco", "()Z", "suppressEndpointSound", "getSuppressEndpointSound", "suppressSpeechResponse", "getSuppressSpeechResponse", "suppressUserInterface", "getSuppressUserInterface", "suppressWakeSound", "getSuppressWakeSound", "userVoiceVerified", "getUserVoiceVerified", "Builder", "Companion", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DialogExtras {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private String defaultAudioDeviceFriendlyName;
    @NotNull
    private String defaultAudioDevicePhysicalAddress;
    @NotNull
    private String invocationType;
    private boolean shouldUseSco;
    private boolean suppressEndpointSound;
    private boolean suppressSpeechResponse;
    private boolean suppressUserInterface;
    private boolean suppressWakeSound;
    private boolean userVoiceVerified;

    /* compiled from: DialogExtras.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010$\u001a\u00020%J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0010J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0010J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0010J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0010J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0010R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\u001b\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R\u001a\u0010\u001e\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R\u001a\u0010!\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014¨\u0006&"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras$Builder;", "", "()V", "defaultAudioDeviceFriendlyName", "", "getDefaultAudioDeviceFriendlyName", "()Ljava/lang/String;", "setDefaultAudioDeviceFriendlyName", "(Ljava/lang/String;)V", "defaultAudioDevicePhysicalAddress", "getDefaultAudioDevicePhysicalAddress", "setDefaultAudioDevicePhysicalAddress", AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, "getInvocationType", "setInvocationType", "shouldUseSco", "", "getShouldUseSco", "()Z", "setShouldUseSco", "(Z)V", "suppressEndpointSound", "getSuppressEndpointSound", "setSuppressEndpointSound", "suppressSpeechResponse", "getSuppressSpeechResponse", "setSuppressSpeechResponse", "suppressUserInterface", "getSuppressUserInterface", "setSuppressUserInterface", "suppressWakeSound", "getSuppressWakeSound", "setSuppressWakeSound", "userVoiceVerified", "getUserVoiceVerified", "setUserVoiceVerified", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras;", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Builder {
        @NotNull
        public String defaultAudioDeviceFriendlyName;
        @NotNull
        public String defaultAudioDevicePhysicalAddress;
        @NotNull
        public String invocationType;
        private boolean shouldUseSco;
        private boolean suppressEndpointSound;
        private boolean suppressSpeechResponse;
        private boolean suppressUserInterface;
        private boolean suppressWakeSound;
        private boolean userVoiceVerified;

        @NotNull
        public final DialogExtras build() {
            return new DialogExtras(this, null);
        }

        @NotNull
        public final Builder defaultAudioDeviceFriendlyName(@NotNull String defaultAudioDeviceFriendlyName) {
            Intrinsics.checkParameterIsNotNull(defaultAudioDeviceFriendlyName, "defaultAudioDeviceFriendlyName");
            this.defaultAudioDeviceFriendlyName = defaultAudioDeviceFriendlyName;
            return this;
        }

        @NotNull
        public final Builder defaultAudioDevicePhysicalAddress(@NotNull String defaultAudioDevicePhysicalAddress) {
            Intrinsics.checkParameterIsNotNull(defaultAudioDevicePhysicalAddress, "defaultAudioDevicePhysicalAddress");
            this.defaultAudioDevicePhysicalAddress = defaultAudioDevicePhysicalAddress;
            return this;
        }

        @NotNull
        public final String getDefaultAudioDeviceFriendlyName() {
            String str = this.defaultAudioDeviceFriendlyName;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("defaultAudioDeviceFriendlyName");
            }
            return str;
        }

        @NotNull
        public final String getDefaultAudioDevicePhysicalAddress() {
            String str = this.defaultAudioDevicePhysicalAddress;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("defaultAudioDevicePhysicalAddress");
            }
            return str;
        }

        @NotNull
        public final String getInvocationType() {
            String str = this.invocationType;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException(AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE);
            }
            return str;
        }

        public final boolean getShouldUseSco() {
            return this.shouldUseSco;
        }

        public final boolean getSuppressEndpointSound() {
            return this.suppressEndpointSound;
        }

        public final boolean getSuppressSpeechResponse() {
            return this.suppressSpeechResponse;
        }

        public final boolean getSuppressUserInterface() {
            return this.suppressUserInterface;
        }

        public final boolean getSuppressWakeSound() {
            return this.suppressWakeSound;
        }

        public final boolean getUserVoiceVerified() {
            return this.userVoiceVerified;
        }

        @NotNull
        public final Builder invocationType(@NotNull String invocationType) {
            Intrinsics.checkParameterIsNotNull(invocationType, "invocationType");
            this.invocationType = invocationType;
            return this;
        }

        public final void setDefaultAudioDeviceFriendlyName(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.defaultAudioDeviceFriendlyName = str;
        }

        public final void setDefaultAudioDevicePhysicalAddress(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.defaultAudioDevicePhysicalAddress = str;
        }

        public final void setInvocationType(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.invocationType = str;
        }

        public final void setShouldUseSco(boolean z) {
            this.shouldUseSco = z;
        }

        public final void setSuppressEndpointSound(boolean z) {
            this.suppressEndpointSound = z;
        }

        public final void setSuppressSpeechResponse(boolean z) {
            this.suppressSpeechResponse = z;
        }

        public final void setSuppressUserInterface(boolean z) {
            this.suppressUserInterface = z;
        }

        public final void setSuppressWakeSound(boolean z) {
            this.suppressWakeSound = z;
        }

        public final void setUserVoiceVerified(boolean z) {
            this.userVoiceVerified = z;
        }

        @NotNull
        public final Builder shouldUseSco(boolean z) {
            this.shouldUseSco = z;
            return this;
        }

        @NotNull
        public final Builder suppressEndpointSound(boolean z) {
            this.suppressEndpointSound = z;
            return this;
        }

        @NotNull
        public final Builder suppressSpeechResponse(boolean z) {
            this.suppressSpeechResponse = z;
            return this;
        }

        @NotNull
        public final Builder suppressUserInterface(boolean z) {
            this.suppressUserInterface = z;
            return this;
        }

        @NotNull
        public final Builder suppressWakeSound(boolean z) {
            this.suppressWakeSound = z;
            return this;
        }

        @NotNull
        public final Builder userVoiceVerified(boolean z) {
            this.userVoiceVerified = z;
            return this;
        }
    }

    /* compiled from: DialogExtras.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras$Companion;", "", "()V", "newBuilder", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras$Builder;", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final Builder newBuilder() {
            return new Builder();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private DialogExtras(Builder builder) {
        this.invocationType = builder.getInvocationType();
        this.defaultAudioDevicePhysicalAddress = builder.getDefaultAudioDevicePhysicalAddress();
        this.defaultAudioDeviceFriendlyName = builder.getDefaultAudioDeviceFriendlyName();
        this.suppressWakeSound = builder.getSuppressWakeSound();
        this.suppressEndpointSound = builder.getSuppressEndpointSound();
        this.userVoiceVerified = builder.getUserVoiceVerified();
        this.suppressUserInterface = builder.getSuppressUserInterface();
        this.shouldUseSco = builder.getShouldUseSco();
        this.suppressSpeechResponse = builder.getSuppressSpeechResponse();
    }

    @JvmStatic
    @NotNull
    public static final Builder newBuilder() {
        return Companion.newBuilder();
    }

    @NotNull
    public final String getDefaultAudioDeviceFriendlyName() {
        return this.defaultAudioDeviceFriendlyName;
    }

    @NotNull
    public final String getDefaultAudioDevicePhysicalAddress() {
        return this.defaultAudioDevicePhysicalAddress;
    }

    @NotNull
    public final String getInvocationType() {
        return this.invocationType;
    }

    public final boolean getShouldUseSco() {
        return this.shouldUseSco;
    }

    public final boolean getSuppressEndpointSound() {
        return this.suppressEndpointSound;
    }

    public final boolean getSuppressSpeechResponse() {
        return this.suppressSpeechResponse;
    }

    public final boolean getSuppressUserInterface() {
        return this.suppressUserInterface;
    }

    public final boolean getSuppressWakeSound() {
        return this.suppressWakeSound;
    }

    public final boolean getUserVoiceVerified() {
        return this.userVoiceVerified;
    }

    public /* synthetic */ DialogExtras(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }
}
