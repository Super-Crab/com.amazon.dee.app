package org.bouncycastle.tls;

import java.util.Hashtable;
import org.bouncycastle.tls.crypto.TlsCertificate;
/* loaded from: classes5.dex */
public class CertificateEntry {
    protected final TlsCertificate certificate;
    protected final Hashtable extensions;

    public CertificateEntry(TlsCertificate tlsCertificate, Hashtable hashtable) {
        if (tlsCertificate != null) {
            this.certificate = tlsCertificate;
            this.extensions = hashtable;
            return;
        }
        throw new NullPointerException("'certificate' cannot be null");
    }

    public TlsCertificate getCertificate() {
        return this.certificate;
    }

    public Hashtable getExtensions() {
        return this.extensions;
    }
}
