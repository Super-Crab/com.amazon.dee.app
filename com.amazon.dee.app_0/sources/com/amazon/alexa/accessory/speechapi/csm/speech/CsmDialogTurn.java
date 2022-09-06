package com.amazon.alexa.accessory.speechapi.csm.speech;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.speechapi.csm.metrics.MetricsConstants;
import com.amazon.alexa.accessory.speechapi.speech.AccessoryAudioMetadata;
import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.accessory.speechapi.speech.AccessoryWakeWord;
import com.amazon.alexa.accessory.speechapi.speech.DialogExtras;
import com.amazon.alexa.accessory.speechapi.speech.DialogRequest;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurn;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnMetricsCallback;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.speechrecognizer.AudioEvent;
import com.amazon.alexa.devices.speechrecognizer.AudioEventListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CsmDialogTurn.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\fH\u0016J:\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmDialogTurn;", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurn;", "dialogRequest", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogRequest;", "audioEventListener", "Lcom/amazon/alexa/devices/speechrecognizer/AudioEventListener;", "userSpeechProvider", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;", "csmUtteranceProvider", "Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider;", "(Lcom/amazon/alexa/accessory/speechapi/speech/DialogRequest;Lcom/amazon/alexa/devices/speechrecognizer/AudioEventListener;Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider;)V", "utteranceId", "", "getDialogTurnId", "startTurn", "", "audioMetadata", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata;", "audioSink", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "dataSink", "dialogTurnStopCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;", "dialogTurnMetricsCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnMetricsCallback;", "dialogExtras", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras;", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmDialogTurn implements DialogTurn {
    public static final Companion Companion = new Companion(null);
    private static final int DEFAULT_CONFIDENCE = 99;
    private static final String METADATA_FORMAT = "Pryon-lite";
    private static final String TAG = "CsmDialogTurn:";
    private final AudioEventListener audioEventListener;
    private final CsmUtteranceProvider csmUtteranceProvider;
    private final DialogRequest dialogRequest;
    private final UserSpeechProvider userSpeechProvider;
    private String utteranceId;

    /* compiled from: CsmDialogTurn.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmDialogTurn$Companion;", "", "()V", "DEFAULT_CONFIDENCE", "", "METADATA_FORMAT", "", "TAG", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CsmDialogTurn(@NotNull DialogRequest dialogRequest, @NotNull AudioEventListener audioEventListener, @NotNull UserSpeechProvider userSpeechProvider, @NotNull CsmUtteranceProvider csmUtteranceProvider) {
        Intrinsics.checkParameterIsNotNull(dialogRequest, "dialogRequest");
        Intrinsics.checkParameterIsNotNull(audioEventListener, "audioEventListener");
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        Intrinsics.checkParameterIsNotNull(csmUtteranceProvider, "csmUtteranceProvider");
        this.dialogRequest = dialogRequest;
        this.audioEventListener = audioEventListener;
        this.userSpeechProvider = userSpeechProvider;
        this.csmUtteranceProvider = csmUtteranceProvider;
        this.utteranceId = "Not available";
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.DialogTurn
    @NotNull
    public String getDialogTurnId() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmDialogTurn: getDialogTurnId returned: ");
        outline107.append(this.utteranceId);
        Logger.d(outline107.toString());
        return this.utteranceId;
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.DialogTurn
    public void startTurn(@NotNull AccessoryAudioMetadata audioMetadata, @NotNull AccessorySink audioSink, @Nullable AccessorySink accessorySink, @NotNull DialogTurnStopCallback dialogTurnStopCallback, @NotNull DialogTurnMetricsCallback dialogTurnMetricsCallback, @NotNull DialogExtras dialogExtras) {
        AudioEvent build;
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(dialogTurnStopCallback, "dialogTurnStopCallback");
        Intrinsics.checkParameterIsNotNull(dialogTurnMetricsCallback, "dialogTurnMetricsCallback");
        Intrinsics.checkParameterIsNotNull(dialogExtras, "dialogExtras");
        Logger.d("CsmDialogTurn: starting dialog turn.");
        AccessoryWakeWord accessoryWakeWord = audioMetadata.getAccessoryWakeWord();
        if (accessoryWakeWord == null) {
            build = new AudioEvent.Builder().withPressToTalk().build();
            Intrinsics.checkExpressionValueIsNotNull(build, "AudioEvent.Builder()\n   …\n                .build()");
        } else {
            CsmDataSink csmDataSink = (CsmDataSink) accessorySink;
            AudioEvent.Builder withWakeword = new AudioEvent.Builder().withPreroll(accessoryWakeWord.getStartIndexInSamples()).withBeginIndex(accessoryWakeWord.getStartIndexInSamples()).withWakeword(accessoryWakeWord.getWakeWord(), accessoryWakeWord.getStartIndexInSamples(), accessoryWakeWord.getEndIndexInSamples(), 99);
            if (csmDataSink == null) {
                Intrinsics.throwNpe();
            }
            build = withWakeword.withMetadata(METADATA_FORMAT, csmDataSink.getOutputStream().toByteArray()).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "AudioEvent.Builder()\n   …\n                .build()");
        }
        try {
            String startUtterance = this.audioEventListener.startUtterance(build);
            Intrinsics.checkExpressionValueIsNotNull(startUtterance, "audioEventListener.startUtterance(audioEvent)");
            this.utteranceId = startUtterance;
            Logger.d("CsmDialogTurn: received utteranceId for the turn: " + this.utteranceId);
            this.csmUtteranceProvider.registerDialogTurnStopCallback(this.utteranceId, new CsmDialogTurnStopCallback(this.utteranceId, dialogTurnStopCallback, this.audioEventListener, this.userSpeechProvider));
            this.userSpeechProvider.onDialogStarted();
            this.userSpeechProvider.onDialogTurnStarted();
        } catch (AlexaException e) {
            Logger.e("CsmDialogTurn: error in startTurn", e);
            AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.CsmSpeechMetrics.ERROR_ALEXA_EXCEPTION_IN_DIALOG_TURN, "alexa_accessories", true, null);
            this.userSpeechProvider.onDialogRequestDenied();
        }
    }
}
