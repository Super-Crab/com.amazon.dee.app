package com.amazon.alexa.presence.bleconn.service;
/* loaded from: classes9.dex */
public interface Response {
    void sendMultipartResponseSuccess(int i, byte[] bArr);

    void sendResponseFailure();

    void sendResponseSuccess(byte[] bArr);

    void sendResponseTest();
}
