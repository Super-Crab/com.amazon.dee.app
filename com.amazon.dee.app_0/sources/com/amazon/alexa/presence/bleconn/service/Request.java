package com.amazon.alexa.presence.bleconn.service;
/* loaded from: classes9.dex */
public interface Request {
    Client getClient();

    byte[] getData();

    int getOffset();

    int getRequestId();

    /* renamed from: getResponse */
    Response mo2296getResponse();

    boolean requiresResponse();
}
