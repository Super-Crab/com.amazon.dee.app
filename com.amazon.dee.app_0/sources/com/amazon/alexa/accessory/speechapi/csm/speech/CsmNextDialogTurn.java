package com.amazon.alexa.accessory.speechapi.csm.speech;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.speechapi.csm.metrics.MetricsConstants;
import com.amazon.alexa.accessory.speechapi.speech.AccessoryAudioMetadata;
import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnMetricsCallback;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback;
import com.amazon.alexa.accessory.speechapi.speech.NextDialogTurn;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.speechrecognizer.AudioEvent;
import com.amazon.alexa.devices.speechrecognizer.AudioEventListener;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmNextDialogTurn.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\nH\u0016J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmNextDialogTurn;", "Lcom/amazon/alexa/accessory/speechapi/speech/NextDialogTurn;", "audioEventListener", "Lcom/amazon/alexa/devices/speechrecognizer/AudioEventListener;", "userSpeechProvider", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;", "csmUtteranceProvider", "Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider;", "(Lcom/amazon/alexa/devices/speechrecognizer/AudioEventListener;Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider;)V", "utteranceId", "", "getDialogTurnId", "startTurn", "", "audioSink", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "dialogTurnStopCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;", "audioMetadata", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata;", "dialogTurnMetricsCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnMetricsCallback;", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmNextDialogTurn implements NextDialogTurn {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CsmDialogTurn:";
    private AudioEventListener audioEventListener;
    private final CsmUtteranceProvider csmUtteranceProvider;
    private final UserSpeechProvider userSpeechProvider;
    private String utteranceId;

    /* compiled from: CsmNextDialogTurn.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmNextDialogTurn$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CsmNextDialogTurn(@NotNull AudioEventListener audioEventListener, @NotNull UserSpeechProvider userSpeechProvider, @NotNull CsmUtteranceProvider csmUtteranceProvider) {
        Intrinsics.checkParameterIsNotNull(audioEventListener, "audioEventListener");
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        Intrinsics.checkParameterIsNotNull(csmUtteranceProvider, "csmUtteranceProvider");
        this.audioEventListener = audioEventListener;
        this.userSpeechProvider = userSpeechProvider;
        this.csmUtteranceProvider = csmUtteranceProvider;
        this.utteranceId = "Not available";
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.NextDialogTurn
    @NotNull
    public String getDialogTurnId() {
        return this.utteranceId;
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.NextDialogTurn
    public void startTurn(@NotNull AccessorySink audioSink, @NotNull DialogTurnStopCallback dialogTurnStopCallback, @NotNull AccessoryAudioMetadata audioMetadata, @NotNull DialogTurnMetricsCallback dialogTurnMetricsCallback) {
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(dialogTurnStopCallback, "dialogTurnStopCallback");
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(dialogTurnMetricsCallback, "dialogTurnMetricsCallback");
        Logger.d("CsmDialogTurn: starting multi-turn dialog.");
        try {
            String startUtterance = this.audioEventListener.startUtterance(new AudioEvent.Builder().withMultiTurn().build());
            Intrinsics.checkExpressionValueIsNotNull(startUtterance, "audioEventListener.startUtterance(audioEvent)");
            this.utteranceId = startUtterance;
            Logger.d("CsmDialogTurn: received utteranceId for the turn: " + this.utteranceId);
            this.csmUtteranceProvider.registerDialogTurnStopCallback(this.utteranceId, new CsmDialogTurnStopCallback(this.utteranceId, dialogTurnStopCallback, this.audioEventListener, this.userSpeechProvider));
            this.userSpeechProvider.onDialogTurnStarted();
        } catch (AlexaException e) {
            Logger.e("CsmDialogTurn: error in startTurn", e);
            AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.CsmSpeechMetrics.ERROR_ALEXA_EXCEPTION_IN_NEXT_DIALOG_TURN, "alexa_accessories", true, null);
            this.userSpeechProvider.onDialogRequestDenied();
        }
    }
}
