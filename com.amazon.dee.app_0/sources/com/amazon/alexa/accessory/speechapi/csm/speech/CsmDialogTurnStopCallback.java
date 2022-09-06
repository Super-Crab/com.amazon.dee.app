package com.amazon.alexa.accessory.speechapi.csm.speech;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.speechrecognizer.AudioEventListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmDialogTurnStopCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmDialogTurnStopCallback;", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;", "utteranceId", "", "dialogTurnStopCallback", "audioEventListener", "Lcom/amazon/alexa/devices/speechrecognizer/AudioEventListener;", "userSpeechProvider", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;", "(Ljava/lang/String;Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;Lcom/amazon/alexa/devices/speechrecognizer/AudioEventListener;Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;)V", "stopRecording", "", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmDialogTurnStopCallback implements DialogTurnStopCallback {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CsmDialogTurnStopCallback:";
    private final AudioEventListener audioEventListener;
    private final DialogTurnStopCallback dialogTurnStopCallback;
    private final UserSpeechProvider userSpeechProvider;
    private final String utteranceId;

    /* compiled from: CsmDialogTurnStopCallback.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmDialogTurnStopCallback$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CsmDialogTurnStopCallback(@NotNull String utteranceId, @NotNull DialogTurnStopCallback dialogTurnStopCallback, @NotNull AudioEventListener audioEventListener, @NotNull UserSpeechProvider userSpeechProvider) {
        Intrinsics.checkParameterIsNotNull(utteranceId, "utteranceId");
        Intrinsics.checkParameterIsNotNull(dialogTurnStopCallback, "dialogTurnStopCallback");
        Intrinsics.checkParameterIsNotNull(audioEventListener, "audioEventListener");
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        this.utteranceId = utteranceId;
        this.dialogTurnStopCallback = dialogTurnStopCallback;
        this.audioEventListener = audioEventListener;
        this.userSpeechProvider = userSpeechProvider;
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback
    public void stopRecording() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmDialogTurnStopCallback: stopping utterance with ID: ");
        outline107.append(this.utteranceId);
        Logger.d(outline107.toString());
        try {
            try {
                this.audioEventListener.stopUtterance(this.utteranceId, AudioEventListener.ABORT_REASON.STOP_AUDIO_CAPTURE);
            } catch (AlexaException e) {
                Logger.e("CsmDialogTurnStopCallback: error in stop utterance", e);
            }
        } finally {
            this.dialogTurnStopCallback.stopRecording();
            this.userSpeechProvider.onDialogTurnFinished();
        }
    }
}
