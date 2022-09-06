package com.amazon.alexa.accessory.speechapi.voicesdk.mapper;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.listeners.AudioPlaybackStatusListener;
import com.amazon.alexa.accessory.speechapi.listeners.ReadyState;
import com.amazon.alexa.accessory.speechapi.speech.AccessoryAudioMetadata;
import com.amazon.alexa.accessory.speechapi.speech.AccessoryAudioProfile;
import com.amazon.alexa.accessory.speechapi.speech.AccessoryWakeWord;
import com.amazon.alexa.accessory.speechapi.speech.DialogExtras;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnUserPerceivedLatencyData;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProviderMetadata;
import com.amazon.alexa.api.AlexaAudioChannel;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.api.AudioDevice;
import com.amazon.alexa.api.AudioOutputContext;
import com.amazon.alexa.api.compat.AlexaReadyState;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.compat.SupportedInitiationType;
import com.amazon.alexa.api.compat.metrics.UserPerceivedLatencyData;
import com.amazon.device.messaging.ADMConstants;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SpeechApiModelMapper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\f\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010\f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0010\u0010\f\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0010\u0010\f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0007¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/mapper/SpeechApiModelMapper;", "", "()V", "convertSupportedInitiationTypes", "", "Lcom/amazon/alexa/api/compat/SupportedInitiationType;", "initiationTypes", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider$SupportedInitiationType;", "convertToAlexaProfile", "Lcom/amazon/alexa/api/AlexaProfile;", "audioProfile", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioProfile;", ADMConstants.EXTRA_FROM, "Lcom/amazon/alexa/api/AlexaAudioMetadata;", "accessoryAudioMetadata", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata;", "Lcom/amazon/alexa/api/AlexaDialogExtras;", "dialogExtras", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderMetadata;", "userSpeechProviderMetadata", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProviderMetadata;", "Lcom/amazon/alexa/accessory/speechapi/listeners/AudioPlaybackStatusListener$AudioType;", "alexaAudioChannel", "Lcom/amazon/alexa/api/AlexaAudioChannel;", "Lcom/amazon/alexa/accessory/speechapi/listeners/ReadyState;", "readyState", "Lcom/amazon/alexa/api/compat/AlexaReadyState;", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnUserPerceivedLatencyData;", "userPerceivedLatencyData", "Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyData;", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SpeechApiModelMapper {
    public static final SpeechApiModelMapper INSTANCE = new SpeechApiModelMapper();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[AlexaAudioChannel.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[AlexaAudioChannel.IMPORTANT.ordinal()] = 1;
            $EnumSwitchMapping$0[AlexaAudioChannel.DIALOG.ordinal()] = 2;
            $EnumSwitchMapping$0[AlexaAudioChannel.COMMUNICATIONS.ordinal()] = 3;
            $EnumSwitchMapping$0[AlexaAudioChannel.ALERTS.ordinal()] = 4;
            $EnumSwitchMapping$0[AlexaAudioChannel.CONTENT.ordinal()] = 5;
            $EnumSwitchMapping$1 = new int[AccessoryAudioProfile.values().length];
            $EnumSwitchMapping$1[AccessoryAudioProfile.CLOSE_TALK.ordinal()] = 1;
            $EnumSwitchMapping$1[AccessoryAudioProfile.NEAR_FIELD.ordinal()] = 2;
            $EnumSwitchMapping$1[AccessoryAudioProfile.FAR_FIELD.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[UserSpeechProvider.SupportedInitiationType.values().length];
            $EnumSwitchMapping$2[UserSpeechProvider.SupportedInitiationType.WAKE_WORD.ordinal()] = 1;
            $EnumSwitchMapping$2[UserSpeechProvider.SupportedInitiationType.TAP_TO_TALK.ordinal()] = 2;
            $EnumSwitchMapping$2[UserSpeechProvider.SupportedInitiationType.PUSH_TO_TALK.ordinal()] = 3;
            $EnumSwitchMapping$2[UserSpeechProvider.SupportedInitiationType.SERVER.ordinal()] = 4;
        }
    }

    private SpeechApiModelMapper() {
    }

    @JvmStatic
    @NotNull
    public static final ReadyState from(@NotNull AlexaReadyState readyState) {
        Intrinsics.checkParameterIsNotNull(readyState, "readyState");
        return new ReadyState(readyState.isReady(), readyState.isQuickReady());
    }

    @NotNull
    public final Set<SupportedInitiationType> convertSupportedInitiationTypes(@NotNull Set<? extends UserSpeechProvider.SupportedInitiationType> initiationTypes) {
        Intrinsics.checkParameterIsNotNull(initiationTypes, "initiationTypes");
        HashSet hashSet = new HashSet();
        for (UserSpeechProvider.SupportedInitiationType supportedInitiationType : initiationTypes) {
            int i = WhenMappings.$EnumSwitchMapping$2[supportedInitiationType.ordinal()];
            if (i == 1) {
                hashSet.add(SupportedInitiationType.WAKE_WORD);
            } else if (i == 2 || i == 3) {
                hashSet.add(SupportedInitiationType.TAP_TO_TALK);
            } else if (i != 4) {
                Logger.d("SpeechApiModelMapper: unknown UserSpeechProvider.SupportedInitiationType: %s", supportedInitiationType);
            } else {
                hashSet.add(SupportedInitiationType.SERVER);
            }
        }
        return hashSet;
    }

    @NotNull
    public final AlexaProfile convertToAlexaProfile(@NotNull AccessoryAudioProfile audioProfile) {
        Intrinsics.checkParameterIsNotNull(audioProfile, "audioProfile");
        int i = WhenMappings.$EnumSwitchMapping$1[audioProfile.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return AlexaProfile.NEAR_FIELD;
            }
            if (i == 3) {
                return AlexaProfile.FAR_FIELD;
            }
            throw new IllegalStateException("Unrecognized Alexa Profile from accessory");
        }
        return AlexaProfile.CLOSE_TALK;
    }

    @JvmStatic
    @NotNull
    public static final AlexaUserSpeechProviderMetadata from(@NotNull UserSpeechProviderMetadata userSpeechProviderMetadata) {
        Intrinsics.checkParameterIsNotNull(userSpeechProviderMetadata, "userSpeechProviderMetadata");
        return AlexaUserSpeechProviderMetadata.Companion.create(INSTANCE.convertSupportedInitiationTypes(userSpeechProviderMetadata.getSupportedInitiationTypes()), userSpeechProviderMetadata.getSupportedWakeWords(), AlexaUserSpeechProviderScope.EXTERNAL_DEVICE);
    }

    @JvmStatic
    @NotNull
    public static final DialogTurnUserPerceivedLatencyData from(@NotNull UserPerceivedLatencyData userPerceivedLatencyData) {
        Intrinsics.checkParameterIsNotNull(userPerceivedLatencyData, "userPerceivedLatencyData");
        return new DialogTurnUserPerceivedLatencyData(userPerceivedLatencyData.getDialogRequestId(), userPerceivedLatencyData.getEstimatedUserPerceivedLatency(), userPerceivedLatencyData.getEndOfSpeechOffset(), userPerceivedLatencyData.getUserPerceivedLatency(), userPerceivedLatencyData.getAlexaSpeechStarted());
    }

    @JvmStatic
    @NotNull
    public static final AlexaAudioMetadata from(@NotNull AccessoryAudioMetadata accessoryAudioMetadata) {
        Intrinsics.checkParameterIsNotNull(accessoryAudioMetadata, "accessoryAudioMetadata");
        AlexaProfile convertToAlexaProfile = INSTANCE.convertToAlexaProfile(accessoryAudioMetadata.getAccessoryAudioProfile());
        if (accessoryAudioMetadata.getAccessoryWakeWord() == null) {
            return new AlexaAudioMetadata(convertToAlexaProfile, accessoryAudioMetadata.getAudioFormat());
        }
        AccessoryWakeWord accessoryWakeWord = accessoryAudioMetadata.getAccessoryWakeWord();
        Integer num = null;
        String wakeWord = accessoryWakeWord != null ? accessoryWakeWord.getWakeWord() : null;
        Integer valueOf = accessoryWakeWord != null ? Integer.valueOf(accessoryWakeWord.getStartIndexInSamples()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        long intValue = valueOf.intValue();
        if (accessoryWakeWord != null) {
            num = Integer.valueOf(accessoryWakeWord.getEndIndexInSamples());
        }
        if (num == null) {
            Intrinsics.throwNpe();
        }
        return new AlexaAudioMetadata(convertToAlexaProfile, new AlexaWakeWord(wakeWord, intValue, num.intValue()), accessoryAudioMetadata.getAudioFormat());
    }

    @JvmStatic
    @NotNull
    public static final AlexaDialogExtras from(@NotNull DialogExtras dialogExtras) {
        Intrinsics.checkParameterIsNotNull(dialogExtras, "dialogExtras");
        AlexaDialogExtras build = AlexaDialogExtras.builder().setInvocationType(dialogExtras.getInvocationType()).suppressWakeSound(dialogExtras.getSuppressWakeSound()).suppressEndpointSound(dialogExtras.getSuppressEndpointSound()).setUserVoiceVerified(false).suppressUserInterface(dialogExtras.getSuppressUserInterface()).suppressSpeechResponse(dialogExtras.getSuppressSpeechResponse()).setAudioOutputContext(new AudioOutputContext(AudioDevice.create(dialogExtras.getDefaultAudioDevicePhysicalAddress(), dialogExtras.getDefaultAudioDeviceFriendlyName()), dialogExtras.getShouldUseSco())).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "AlexaDialogExtras.builde…co))\n            .build()");
        return build;
    }

    @JvmStatic
    @NotNull
    public static final AudioPlaybackStatusListener.AudioType from(@NotNull AlexaAudioChannel alexaAudioChannel) {
        Intrinsics.checkParameterIsNotNull(alexaAudioChannel, "alexaAudioChannel");
        int i = WhenMappings.$EnumSwitchMapping$0[alexaAudioChannel.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return AudioPlaybackStatusListener.AudioType.DIALOG;
            }
            if (i == 3) {
                return AudioPlaybackStatusListener.AudioType.CALLS;
            }
            if (i == 4) {
                return AudioPlaybackStatusListener.AudioType.ALERTS;
            }
            if (i != 5) {
                return AudioPlaybackStatusListener.AudioType.UNKNOWN;
            }
            return AudioPlaybackStatusListener.AudioType.CONTENT;
        }
        return AudioPlaybackStatusListener.AudioType.IMPORTANT;
    }
}
