package com.amazon.deecomms.alexa.fireos;

import com.amazon.deecomms.alexa.InCallEvent;
/* loaded from: classes12.dex */
public interface SimEventListener {
    void onError(InCallEvent inCallEvent, int i, String str);

    void onSuccess(InCallEvent inCallEvent);
}
