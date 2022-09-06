package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.voice.ui.tta.TtaConversationHistoryListener;
import com.amazon.alexa.voice.ui.tta.TtaMessageHandler;
import com.amazon.alexa.voice.ui.tta.TtaMessageRequestListener;
import com.amazon.alexa.voice.ui.tta.TtaMessageResponseListener;
import com.amazon.alexa.voice.ui.tta.TtaRequestMessage;
import com.amazon.alexa.voice.ui.util.BooleanProperty;
import java.lang.ref.WeakReference;
/* loaded from: classes11.dex */
class DefaultTtaMessageHandler implements TtaMessageHandler {
    private WeakReference<TypingInteractor> interactorReference;
    private final TtaMessageSanitizer messageSanitizer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultTtaMessageHandler(TtaMessageSanitizer ttaMessageSanitizer) {
        this.messageSanitizer = ttaMessageSanitizer;
    }

    public void initialize(@NonNull TypingInteractor typingInteractor) {
        this.interactorReference = new WeakReference<>(typingInteractor);
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaMessageHandler
    public BooleanProperty isThinking() {
        Preconditions.notNull(this.interactorReference, "TtaMessageHandler is not initialized");
        TypingInteractor typingInteractor = this.interactorReference.get();
        return typingInteractor != null ? typingInteractor.isThinking() : new BooleanProperty(false);
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaMessageHandler
    public void send(final TtaRequestMessage ttaRequestMessage) {
        Preconditions.notNull(this.interactorReference, "TtaMessageHandler is not initialized");
        TypingInteractor typingInteractor = this.interactorReference.get();
        if (typingInteractor != null) {
            typingInteractor.send(new TtaRequestMessage() { // from class: com.amazon.alexa.voice.tta.typing.DefaultTtaMessageHandler.1
                @Override // com.amazon.alexa.voice.ui.tta.TtaRequestMessage, com.amazon.alexa.voice.ui.tta.TtaMessage
                public String getMessage() {
                    return DefaultTtaMessageHandler.this.messageSanitizer.sanitizeRequest(ttaRequestMessage.getMessage());
                }
            }, true);
        }
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaMessageHandler
    public void setConversationHistoryListener(TtaConversationHistoryListener ttaConversationHistoryListener) {
        Preconditions.notNull(this.interactorReference, "TtaMessageHandler is not initialized");
        TypingInteractor typingInteractor = this.interactorReference.get();
        if (typingInteractor != null) {
            typingInteractor.setConversationHistoryListener(ttaConversationHistoryListener);
        }
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaMessageHandler
    public void setRequestListener(TtaMessageRequestListener ttaMessageRequestListener) {
        Preconditions.notNull(this.interactorReference, "TtaMessageHandler is not initialized");
        TypingInteractor typingInteractor = this.interactorReference.get();
        if (typingInteractor != null) {
            typingInteractor.setRequestListener(ttaMessageRequestListener);
        }
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaMessageHandler
    public void setResponseListener(TtaMessageResponseListener ttaMessageResponseListener) {
        Preconditions.notNull(this.interactorReference, "TtaMessageHandler is not initialized");
        TypingInteractor typingInteractor = this.interactorReference.get();
        if (typingInteractor != null) {
            typingInteractor.setResponseListener(ttaMessageResponseListener);
        }
    }
}
