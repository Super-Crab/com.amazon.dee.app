package com.amazon.alexa.voice.ui.onedesign.tta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.tta.search.ItemPosition;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
/* loaded from: classes11.dex */
public class TtaInChatResultRenderedCard implements TtaInChatResultCard {
    private final int cardType;
    private final String id;
    private final ItemPosition itemPosition;
    private final ItemRoute itemRoute;
    private final String message;
    private final String secondMessage;

    public TtaInChatResultRenderedCard(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable ItemRoute itemRoute, @NonNull int i, @NonNull ItemPosition itemPosition) {
        this.id = str;
        this.message = str2;
        this.secondMessage = str3;
        this.itemRoute = itemRoute;
        this.cardType = i;
        this.itemPosition = itemPosition;
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaResponseMessage
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard
    public ItemPosition getItemPosition() {
        return this.itemPosition;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard
    public ItemRoute getItemRoute() {
        return this.itemRoute;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard, com.amazon.alexa.voice.ui.tta.TtaResponseMessage, com.amazon.alexa.voice.ui.tta.TtaMessage
    public String getMessage() {
        return this.message;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard
    public String getSecondMessage() {
        return this.secondMessage;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard
    public int getType() {
        return this.cardType;
    }
}
