package org.bouncycastle.tls;

import java.io.IOException;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.tls.crypto.TlsMAC;
import org.bouncycastle.tls.crypto.TlsMACOutputStream;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class DTLSVerifier {
    private final TlsMAC cookieMAC;
    private final TlsMACOutputStream cookieMACOutputStream;

    public DTLSVerifier(TlsCrypto tlsCrypto) {
        this.cookieMAC = createCookieMAC(tlsCrypto);
        this.cookieMACOutputStream = new TlsMACOutputStream(this.cookieMAC);
    }

    private static TlsMAC createCookieMAC(TlsCrypto tlsCrypto) {
        TlsHMAC createHMAC = tlsCrypto.createHMAC(3);
        byte[] bArr = new byte[createHMAC.getMacLength()];
        tlsCrypto.getSecureRandom().nextBytes(bArr);
        createHMAC.setKey(bArr, 0, bArr.length);
        return createHMAC;
    }

    public synchronized DTLSRequest verifyRequest(byte[] bArr, byte[] bArr2, int i, int i2, DatagramSender datagramSender) {
        TlsMAC tlsMAC;
        boolean z = true;
        try {
            this.cookieMAC.update(bArr, 0, bArr.length);
            DTLSRequest readClientRequest = DTLSReliableHandshake.readClientRequest(bArr2, i, i2, this.cookieMACOutputStream);
            if (readClientRequest != null) {
                byte[] calculateMAC = this.cookieMAC.calculateMAC();
                try {
                    if (Arrays.constantTimeAreEqual(calculateMAC, readClientRequest.getClientHello().getCookie())) {
                        return readClientRequest;
                    }
                    DTLSReliableHandshake.sendHelloVerifyRequest(datagramSender, readClientRequest.getRecordSeq(), calculateMAC);
                    z = false;
                } catch (IOException unused) {
                    z = false;
                    if (z) {
                        tlsMAC = this.cookieMAC;
                        tlsMAC.reset();
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    if (z) {
                        this.cookieMAC.reset();
                    }
                    throw th;
                }
            }
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        if (z) {
            tlsMAC = this.cookieMAC;
            tlsMAC.reset();
        }
        return null;
    }
}
