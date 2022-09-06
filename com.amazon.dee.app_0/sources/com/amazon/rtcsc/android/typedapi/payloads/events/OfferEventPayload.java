package com.amazon.rtcsc.android.typedapi.payloads.events;

import com.amazon.rtcsc.android.typedapi.payloads.Payload;
import com.amazon.rtcsc.android.typedapi.types.WebRTCHandshake;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class OfferEventPayload extends Payload {
    @NonNull
    private WebRTCHandshake offer;

    public OfferEventPayload(String str, String str2, WebRTCHandshake webRTCHandshake) {
        this.offer = webRTCHandshake;
        this.sessionId = str;
        this.sessionDomain = str2;
    }

    @Override // com.amazon.rtcsc.android.typedapi.payloads.Payload
    protected boolean canEqual(Object obj) {
        return obj instanceof OfferEventPayload;
    }

    @Override // com.amazon.rtcsc.android.typedapi.payloads.Payload
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OfferEventPayload)) {
            return false;
        }
        OfferEventPayload offerEventPayload = (OfferEventPayload) obj;
        if (!offerEventPayload.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        WebRTCHandshake offer = getOffer();
        WebRTCHandshake offer2 = offerEventPayload.getOffer();
        return offer != null ? offer.equals(offer2) : offer2 == null;
    }

    @NonNull
    public WebRTCHandshake getOffer() {
        return this.offer;
    }

    @Override // com.amazon.rtcsc.android.typedapi.payloads.Payload
    public int hashCode() {
        WebRTCHandshake offer = getOffer();
        return ((super.hashCode() + 59) * 59) + (offer == null ? 43 : offer.hashCode());
    }
}
