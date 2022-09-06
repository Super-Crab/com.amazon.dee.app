package com.amazon.deecomms.alexa;
/* loaded from: classes12.dex */
public interface AlexaInterface {
    boolean acquireCommsFocus();

    boolean releaseCommsFocus();

    boolean sendCommsEvent(InCallEvent inCallEvent);
}
