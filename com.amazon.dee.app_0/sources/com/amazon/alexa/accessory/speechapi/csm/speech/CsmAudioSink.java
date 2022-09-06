package com.amazon.alexa.accessory.speechapi.csm.speech;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.devices.speechrecognizer.AudioFormat;
import com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmAudioSink.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmAudioSink;", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "speechRecognizerComponent", "Lcom/amazon/alexa/devices/speechrecognizer/SpeechRecognizerComponent;", "(Lcom/amazon/alexa/devices/speechrecognizer/SpeechRecognizerComponent;)V", "getAccessoryOutputStream", "Ljava/io/OutputStream;", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmAudioSink implements AccessorySink {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CsmAudioSink:";
    private final SpeechRecognizerComponent speechRecognizerComponent;

    /* compiled from: CsmAudioSink.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmAudioSink$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CsmAudioSink(@NotNull SpeechRecognizerComponent speechRecognizerComponent) {
        Intrinsics.checkParameterIsNotNull(speechRecognizerComponent, "speechRecognizerComponent");
        this.speechRecognizerComponent = speechRecognizerComponent;
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.AccessorySink
    @NotNull
    public OutputStream getAccessoryOutputStream() {
        Logger.d("CsmAudioSink: getAccessoryOutputStream");
        OutputStream replaceAudioOutputStream = this.speechRecognizerComponent.replaceAudioOutputStream(new AudioFormat(AudioFormat.SampleRate.SAMPLE_RATE_16K, AudioFormat.Encoding.ENCODING_OPUS_16BIT));
        Intrinsics.checkExpressionValueIsNotNull(replaceAudioOutputStream, "speechRecognizerComponen…ing.ENCODING_OPUS_16BIT))");
        return replaceAudioOutputStream;
    }
}
