package org.bouncycastle.jcajce;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
/* loaded from: classes4.dex */
public class CompositePublicKey implements PublicKey {
    private final List<PublicKey> keys;

    public CompositePublicKey(PublicKey... publicKeyArr) {
        if (publicKeyArr == null || publicKeyArr.length == 0) {
            throw new IllegalArgumentException("at least one public key must be provided");
        }
        ArrayList arrayList = new ArrayList(publicKeyArr.length);
        for (int i = 0; i != publicKeyArr.length; i++) {
            arrayList.add(publicKeyArr[i]);
        }
        this.keys = Collections.unmodifiableList(arrayList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CompositePublicKey)) {
            return false;
        }
        return this.keys.equals(((CompositePublicKey) obj).keys);
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "Composite";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.keys.size(); i++) {
            aSN1EncodableVector.add(SubjectPublicKeyInfo.getInstance(this.keys.get(i).getEncoded()));
        }
        try {
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(MiscObjectIdentifiers.id_alg_composite), new DERSequence(aSN1EncodableVector)).getEncoded("DER");
        } catch (IOException e) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to encode composite key: ")));
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return KeyUtils.X509_CERITIFATE_FACTORY;
    }

    public List<PublicKey> getPublicKeys() {
        return this.keys;
    }

    public int hashCode() {
        return this.keys.hashCode();
    }
}
