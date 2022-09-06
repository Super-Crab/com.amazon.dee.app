package com.amazon.rtcsc.android.typedapi;

import com.amazon.rtcsc.android.typedapi.constants.Keywords;
import com.amazon.rtcsc.android.typedapi.payloads.Payload;
import com.amazon.rtcsc.android.typedapi.payloads.events.OfferEventPayload;
import com.amazon.rtcsc.android.typedapi.payloads.events.PeerConnectionUpdatedEventPayload;
import com.amazon.rtcsc.android.typedapi.payloads.events.SessionConnectedEventPayload;
import com.amazon.rtcsc.android.typedapi.payloads.events.SessionDisconnectedEventPayload;
import com.amazon.rtcsc.android.typedapi.payloads.events.SessionOperationFailedEventPayload;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes13.dex */
public class EventInterpreter {
    public static final List<String> SUPPORTED_EVENT_TYPES = new ArrayList<String>() { // from class: com.amazon.rtcsc.android.typedapi.EventInterpreter.1
        {
            add("InitiateSessionFailed");
            add(Keywords.OFFER_GENERATED_FOR_SESSION_EVENT);
            add(Keywords.OFFER_GENERATED_FOR_SESSION_UPDATE_EVENT);
            add("SessionConnected");
            add("SessionDisconnected");
            add(Keywords.UPDATE_PEERCONNECTION_FAILED_EVENT);
            add(Keywords.UPDATE_SESSION_FAILED_EVENT);
            add(Keywords.PEERCONNECTION_UPDATED_EVENT);
        }
    };
    private static Gson gson = new Gson();

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Nullable
    public static Payload getPayload(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1829768549:
                if (str.equals(Keywords.PEERCONNECTION_UPDATED_EVENT)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -896787503:
                if (str.equals("SessionDisconnected")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -688497466:
                if (str.equals(Keywords.UPDATE_PEERCONNECTION_FAILED_EVENT)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -34936774:
                if (str.equals("InitiateSessionFailed")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 484909449:
                if (str.equals(Keywords.OFFER_GENERATED_FOR_SESSION_UPDATE_EVENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 668616714:
                if (str.equals(Keywords.UPDATE_SESSION_FAILED_EVENT)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 886725939:
                if (str.equals("SessionConnected")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1533277344:
                if (str.equals(Keywords.OFFER_GENERATED_FOR_SESSION_EVENT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return (Payload) gson.fromJson(str2, (Class<Object>) SessionOperationFailedEventPayload.class);
            case 1:
                return (Payload) gson.fromJson(str2, (Class<Object>) OfferEventPayload.class);
            case 2:
                return (Payload) gson.fromJson(str2, (Class<Object>) OfferEventPayload.class);
            case 3:
                return (Payload) gson.fromJson(str2, (Class<Object>) SessionConnectedEventPayload.class);
            case 4:
                return (Payload) gson.fromJson(str2, (Class<Object>) SessionDisconnectedEventPayload.class);
            case 5:
                return (Payload) gson.fromJson(str2, (Class<Object>) SessionOperationFailedEventPayload.class);
            case 6:
                return (Payload) gson.fromJson(str2, (Class<Object>) SessionOperationFailedEventPayload.class);
            case 7:
                return (Payload) gson.fromJson(str2, (Class<Object>) PeerConnectionUpdatedEventPayload.class);
            default:
                return null;
        }
    }
}
