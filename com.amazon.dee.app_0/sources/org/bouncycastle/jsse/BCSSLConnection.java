package org.bouncycastle.jsse;
/* loaded from: classes4.dex */
public interface BCSSLConnection {
    String getApplicationProtocol();

    byte[] getChannelBinding(String str);

    /* renamed from: getSession */
    BCExtendedSSLSession mo12855getSession();
}
