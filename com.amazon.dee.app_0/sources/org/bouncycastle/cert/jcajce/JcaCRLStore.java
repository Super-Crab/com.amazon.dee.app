package org.bouncycastle.cert.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.util.CollectionStore;
/* loaded from: classes4.dex */
public class JcaCRLStore extends CollectionStore {
    public JcaCRLStore(Collection collection) throws CRLException {
        super(convertCRLs(collection));
    }

    private static Collection convertCRLs(Collection collection) throws CRLException {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Object obj : collection) {
            if (obj instanceof X509CRL) {
                try {
                    arrayList.add(new X509CRLHolder(((X509CRL) obj).getEncoded()));
                } catch (IOException e) {
                    throw new CRLException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("cannot read encoding: ")));
                }
            } else {
                arrayList.add((X509CRLHolder) obj);
            }
        }
        return arrayList;
    }
}
