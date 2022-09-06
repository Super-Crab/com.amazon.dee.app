package com.amazon.deecomms.sharing.payload.eventBus;

import com.amazon.deecomms.sharing.payload.acms.ACMSPayloadReaction;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class SendReactionEventBusPayload implements SharingEventBusPayload {
    public HashMap<String, String> metadata = new HashMap<>();
    public ACMSPayloadReaction payload = new ACMSPayloadReaction();
    public String name = "";
}
