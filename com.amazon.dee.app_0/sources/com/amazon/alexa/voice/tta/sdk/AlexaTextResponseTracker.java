package com.amazon.alexa.voice.tta.sdk;

import com.amazon.alexa.api.compat.AlexaTextResponseListener;
import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.voice.tta.typing.TextTtaResponseMessage;
import com.amazon.alexa.voice.tta.typing.TtaMessageSanitizer;
import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
/* loaded from: classes11.dex */
public class AlexaTextResponseTracker implements AlexaTextResponseListener {
    private final TtaMessageSanitizer messageSanitizer;
    private final PublishSubject<TtaResponseMessage> ttaResponse = PublishSubject.create();

    public AlexaTextResponseTracker(TtaMessageSanitizer ttaMessageSanitizer) {
        this.messageSanitizer = ttaMessageSanitizer;
    }

    public void onResponse(TtaResponseMessage ttaResponseMessage) {
        this.ttaResponse.onNext(ttaResponseMessage);
    }

    @Override // com.amazon.alexa.api.compat.AlexaTextResponseListener
    public void onTextReceived(TextResponse textResponse) {
        if (textResponse == null || textResponse.getTitle().isEmpty()) {
            return;
        }
        this.ttaResponse.onNext(new TextTtaResponseMessage(this.messageSanitizer.sanitizeResponse(textResponse.getTitle())));
    }

    public Observable<TtaResponseMessage> onResponse() {
        return this.ttaResponse;
    }
}
