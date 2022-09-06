package com.amazon.alexa.sharing.sharingsdk.payload.response;

import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.ACMSPayloadReaction;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class SendReactionResponsePayload implements SharingSDKResponsePayload {
    public HashMap<String, String> metadata = new HashMap<>();
    public ACMSPayloadReaction payload = new ACMSPayloadReaction();
    public String name = "";
}
