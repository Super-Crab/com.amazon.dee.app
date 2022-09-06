package org.bouncycastle.jsse;

import java.net.Socket;
import java.security.Principal;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;
/* loaded from: classes4.dex */
public abstract class BCX509ExtendedKeyManager extends X509ExtendedKeyManager {
    public BCX509Key chooseClientKeyBC(String[] strArr, Principal[] principalArr, Socket socket) {
        return getKeyBC(chooseClientAlias(strArr, principalArr, socket));
    }

    public BCX509Key chooseEngineClientKeyBC(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return getKeyBC(chooseEngineClientAlias(strArr, principalArr, sSLEngine));
    }

    public BCX509Key chooseEngineServerKeyBC(String str, Principal[] principalArr, SSLEngine sSLEngine) {
        return getKeyBC(chooseEngineServerAlias(str, principalArr, sSLEngine));
    }

    public BCX509Key chooseServerKeyBC(String str, Principal[] principalArr, Socket socket) {
        return getKeyBC(chooseServerAlias(str, principalArr, socket));
    }

    public abstract BCX509Key getKeyBC(String str);
}
