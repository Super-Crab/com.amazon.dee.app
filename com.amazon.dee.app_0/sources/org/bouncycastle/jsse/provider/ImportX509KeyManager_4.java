package org.bouncycastle.jsse.provider;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509KeyManager;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
import org.bouncycastle.jsse.BCX509Key;
/* loaded from: classes4.dex */
final class ImportX509KeyManager_4 extends BCX509ExtendedKeyManager implements ImportX509KeyManager {
    final X509KeyManager x509KeyManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImportX509KeyManager_4(X509KeyManager x509KeyManager) {
        this.x509KeyManager = x509KeyManager;
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        return this.x509KeyManager.chooseClientAlias(strArr, principalArr, socket);
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        return this.x509KeyManager.chooseServerAlias(str, principalArr, socket);
    }

    @Override // javax.net.ssl.X509KeyManager
    public X509Certificate[] getCertificateChain(String str) {
        return this.x509KeyManager.getCertificateChain(str);
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getClientAliases(String str, Principal[] principalArr) {
        return this.x509KeyManager.getClientAliases(str, principalArr);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key getKeyBC(String str) {
        return ProvX509Key.from(this.x509KeyManager, str);
    }

    @Override // javax.net.ssl.X509KeyManager
    public PrivateKey getPrivateKey(String str) {
        return this.x509KeyManager.getPrivateKey(str);
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getServerAliases(String str, Principal[] principalArr) {
        return this.x509KeyManager.getClientAliases(str, principalArr);
    }

    @Override // org.bouncycastle.jsse.provider.ImportX509KeyManager
    public X509KeyManager unwrap() {
        return this.x509KeyManager;
    }
}
