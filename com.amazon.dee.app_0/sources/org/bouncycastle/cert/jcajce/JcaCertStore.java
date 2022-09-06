package org.bouncycastle.cert.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.CollectionStore;
/* loaded from: classes4.dex */
public class JcaCertStore extends CollectionStore {
    public JcaCertStore(Collection collection) throws CertificateEncodingException {
        super(convertCerts(collection));
    }

    private static Collection convertCerts(Collection collection) throws CertificateEncodingException {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Object obj : collection) {
            if (obj instanceof X509Certificate) {
                try {
                    arrayList.add(new X509CertificateHolder(((X509Certificate) obj).getEncoded()));
                } catch (IOException e) {
                    throw new CertificateEncodingException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to read encoding: ")));
                }
            } else {
                arrayList.add((X509CertificateHolder) obj);
            }
        }
        return arrayList;
    }
}
