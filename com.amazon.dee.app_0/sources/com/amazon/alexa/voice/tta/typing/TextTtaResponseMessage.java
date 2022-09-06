package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
import java.util.UUID;
/* loaded from: classes11.dex */
public class TextTtaResponseMessage implements TtaResponseMessage {
    private final String id;
    private final String message;

    public TextTtaResponseMessage(String str) {
        this(UUID.randomUUID().toString(), str);
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaResponseMessage
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaResponseMessage, com.amazon.alexa.voice.ui.tta.TtaMessage
    public String getMessage() {
        return this.message;
    }

    public TextTtaResponseMessage(String str, String str2) {
        this.id = str;
        this.message = str2;
    }
}
