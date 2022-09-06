package com.amazon.alexa.voice.ui.onedesign.tta;

import com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler;
import com.amazon.alexa.voice.ui.tta.TtaMessageHandler;
import com.amazon.alexa.voice.ui.tta.search.TtaResultHandler;
/* loaded from: classes11.dex */
public class TtaHandlerProvider {
    private final SuggestionsHandler suggestionsHandler;
    private final TtaMessageHandler ttaMessageHandler;
    private final TtaResultHandler ttaResultHandler;

    public TtaHandlerProvider(TtaMessageHandler ttaMessageHandler, TtaResultHandler ttaResultHandler, SuggestionsHandler suggestionsHandler) {
        this.ttaMessageHandler = ttaMessageHandler;
        this.ttaResultHandler = ttaResultHandler;
        this.suggestionsHandler = suggestionsHandler;
    }

    public SuggestionsHandler getSuggestionsHandler() {
        return this.suggestionsHandler;
    }

    public TtaMessageHandler getTtaMessageHandler() {
        return this.ttaMessageHandler;
    }

    public TtaResultHandler getTtaResultHandler() {
        return this.ttaResultHandler;
    }
}
