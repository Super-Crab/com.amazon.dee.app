package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes6.dex */
public class CardsResponse {
    @SerializedName("cards")
    private List<Card> cards = new ArrayList();

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public CardsResponse addCardsItem(Card card) {
        this.cards.add(card);
        return this;
    }

    public CardsResponse cards(List<Card> list) {
        this.cards = list;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && CardsResponse.class == obj.getClass()) {
            return Objects.equals(this.cards, ((CardsResponse) obj).cards);
        }
        return false;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int hashCode() {
        return Objects.hash(this.cards);
    }

    public void setCards(List<Card> list) {
        this.cards = list;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline113("class CardsResponse {\n", "    cards: "), toIndentedString(this.cards), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
