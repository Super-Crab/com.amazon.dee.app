package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.net.ssl.X509TrustManager;
/* loaded from: classes.dex */
public class CompositeX509TrustManager implements X509TrustManager {
    private static final String TAG = "CompositeX509TrustManager:";
    private final List<X509TrustManager> trustManagers = new ArrayList();

    public CompositeX509TrustManager(List<X509TrustManager> list) {
        this.trustManagers.addAll(list);
    }

    private void checkEach(WrapperUtil.ConsumerWithException<X509TrustManager, CertificateException> consumerWithException) throws CertificateException {
        CertificateException e = null;
        for (X509TrustManager x509TrustManager : this.trustManagers) {
            try {
                consumerWithException.consume(x509TrustManager);
                Logger.d("%s trusted", TAG);
                return;
            } catch (CertificateException e2) {
                e = e2;
            }
        }
        Logger.e("%s not trusted", TAG);
        throw e;
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(final X509Certificate[] x509CertificateArr, final String str) throws CertificateException {
        checkEach(new WrapperUtil.ConsumerWithException() { // from class: com.amazon.alexa.accessory.internal.http.-$$Lambda$CompositeX509TrustManager$KD2PApbEJ7U6OCkikPSggfJNSzo
            @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ConsumerWithException
            public final void consume(Object obj) {
                ((X509TrustManager) obj).checkClientTrusted(x509CertificateArr, str);
            }
        });
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(final X509Certificate[] x509CertificateArr, final String str) throws CertificateException {
        checkEach(new WrapperUtil.ConsumerWithException() { // from class: com.amazon.alexa.accessory.internal.http.-$$Lambda$CompositeX509TrustManager$ong6Z9fYjsz5XtDmOs_M1WFS7kc
            @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ConsumerWithException
            public final void consume(Object obj) {
                ((X509TrustManager) obj).checkServerTrusted(x509CertificateArr, str);
            }
        });
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        HashSet hashSet = new HashSet();
        for (X509TrustManager x509TrustManager : this.trustManagers) {
            hashSet.addAll(Arrays.asList(x509TrustManager.getAcceptedIssuers()));
        }
        Logger.d("%s acceptedIssuers size %d", TAG, Integer.valueOf(hashSet.size()));
        return (X509Certificate[]) hashSet.toArray(new X509Certificate[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<X509TrustManager> getTrustManagers() {
        return Collections.unmodifiableList(this.trustManagers);
    }
}
