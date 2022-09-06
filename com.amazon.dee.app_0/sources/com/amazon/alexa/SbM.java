package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: TextTransformProvider.java */
@Singleton
/* loaded from: classes.dex */
public class SbM implements iUS {
    public static final Map<zAS, zAS> zZm = new HashMap();
    public final Wyh BIo;
    public final AlexaClientEventBus zQM;

    static {
        zZm.put(new zAS(AvsApiConstants.SpeechSynthesizer.zZm, AvsApiConstants.SpeechSynthesizer.Directives.Speak.zZm), new zAS(AvsApiConstants.Input.Text.zZm, AvsApiConstants.Input.Text.Directives.TextMessage.zZm));
        zZm.put(new zAS(AvsApiConstants.SpeechRecognizer.zZm, AvsApiConstants.SpeechRecognizer.Directives.ExpectSpeech.zZm), new zAS(AvsApiConstants.Input.Text.zZm, AvsApiConstants.Input.Text.Directives.ExpectText.zZm));
        zZm.put(new zAS(AvsApiConstants.SpeechRecognizer.zZm, AvsApiConstants.SpeechRecognizer.Directives.SetEndOfSpeechOffset.zZm), new zAS(AvsApiConstants.Input.Text.zZm, AvsApiConstants.Input.Text.Directives.SetEndOfSpeechOffset.zZm));
        zZm.put(new zAS(AvsApiConstants.SpeechRecognizer.zZm, AvsApiConstants.SpeechRecognizer.Directives.StopCapture.zZm), new zAS(AvsApiConstants.Input.Text.zZm, AvsApiConstants.Input.Text.Directives.StopCapture.zZm));
    }

    @Inject
    public SbM(@NonNull Wyh wyh, @NonNull AlexaClientEventBus alexaClientEventBus) {
        this.BIo = wyh;
        this.zQM = alexaClientEventBus;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.alexa.client.core.messages.Message zZm(@com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.client.core.messages.Message r7) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.SbM.zZm(com.amazon.alexa.client.core.messages.Message):com.amazon.alexa.client.core.messages.Message");
    }
}
