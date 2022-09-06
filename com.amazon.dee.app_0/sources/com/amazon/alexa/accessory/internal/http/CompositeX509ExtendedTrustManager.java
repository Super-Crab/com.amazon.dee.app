package com.amazon.alexa.accessory.internal.http;

import androidx.annotation.RequiresApi;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
@RequiresApi(api = 24)
/* loaded from: classes.dex */
public class CompositeX509ExtendedTrustManager extends X509ExtendedTrustManager {
    private static final String TAG = "CompositeX509ExtendedTrustManager:";
    private final CompositeX509TrustManager compositeTrustManager;
    private final List<X509ExtendedTrustManager> x509ExtendedTrustManagers = new ArrayList();

    public CompositeX509ExtendedTrustManager(CompositeX509TrustManager compositeX509TrustManager) {
        Preconditions.notNull(compositeX509TrustManager, "compositeX509TrustManager");
        this.compositeTrustManager = compositeX509TrustManager;
        for (X509TrustManager x509TrustManager : compositeX509TrustManager.getTrustManagers()) {
            if (x509TrustManager instanceof X509ExtendedTrustManager) {
                this.x509ExtendedTrustManagers.add((X509ExtendedTrustManager) x509TrustManager);
            }
        }
        Logger.d("%s x509ExtendedTrustManagers size: %d", TAG, Integer.valueOf(this.x509ExtendedTrustManagers.size()));
    }

    private void checkEach(WrapperUtil.ConsumerWithException<X509ExtendedTrustManager, CertificateException> consumerWithException) throws CertificateException {
        CertificateException e = null;
        for (X509ExtendedTrustManager x509ExtendedTrustManager : this.x509ExtendedTrustManagers) {
            try {
                consumerWithException.consume(x509ExtendedTrustManager);
                Logger.d("%s trusted", TAG);
                return;
            } catch (CertificateException e2) {
                e = e2;
            }
        }
        Logger.e("%s not trusted", TAG);
        throw e;
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(final X509Certificate[] x509CertificateArr, final String str, final Socket socket) throws CertificateException {
        checkEach(new WrapperUtil.ConsumerWithException() { // from class: com.amazon.alexa.accessory.internal.http.-$$Lambda$CompositeX509ExtendedTrustManager$fUg6J9gPE8jmixnbAITcECKca80
            @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ConsumerWithException
            public final void consume(Object obj) {
                ((X509ExtendedTrustManager) obj).checkClientTrusted(x509CertificateArr, str, socket);
            }
        });
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(final X509Certificate[] x509CertificateArr, final String str, final Socket socket) throws CertificateException {
        checkEach(new WrapperUtil.ConsumerWithException() { // from class: com.amazon.alexa.accessory.internal.http.-$$Lambda$CompositeX509ExtendedTrustManager$cMDpmjbDJeRjoL2i4fz76thkKzY
            @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ConsumerWithException
            public final void consume(Object obj) {
                ((X509ExtendedTrustManager) obj).checkServerTrusted(x509CertificateArr, str, socket);
            }
        });
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return this.compositeTrustManager.getAcceptedIssuers();
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(final X509Certificate[] x509CertificateArr, final String str, final SSLEngine sSLEngine) throws CertificateException {
        checkEach(new WrapperUtil.ConsumerWithException() { // from class: com.amazon.alexa.accessory.internal.http.-$$Lambda$CompositeX509ExtendedTrustManager$NZWe63Ci9LC1oqkt-g9b1Wo2zsw
            @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ConsumerWithException
            public final void consume(Object obj) {
                ((X509ExtendedTrustManager) obj).checkClientTrusted(x509CertificateArr, str, sSLEngine);
            }
        });
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(final X509Certificate[] x509CertificateArr, final String str, final SSLEngine sSLEngine) throws CertificateException {
        checkEach(new WrapperUtil.ConsumerWithException() { // from class: com.amazon.alexa.accessory.internal.http.-$$Lambda$CompositeX509ExtendedTrustManager$xSxSSfAVKbDKVlNEuRihs-o_wns
            @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ConsumerWithException
            public final void consume(Object obj) {
                ((X509ExtendedTrustManager) obj).checkServerTrusted(x509CertificateArr, str, sSLEngine);
            }
        });
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.compositeTrustManager.checkClientTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.compositeTrustManager.checkServerTrusted(x509CertificateArr, str);
    }
}
