package org.bouncycastle.pqc.jcajce.provider.gmss;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.PublicKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.pqc.asn1.GMSSPublicKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.ParSet;
import org.bouncycastle.pqc.crypto.gmss.GMSSParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.KeyUtil;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes5.dex */
public class BCGMSSPublicKey implements CipherParameters, PublicKey {
    private static final long serialVersionUID = 1;
    private GMSSParameters gmssParameterSet;
    private GMSSParameters gmssParams;
    private byte[] publicKeyBytes;

    public BCGMSSPublicKey(GMSSPublicKeyParameters gMSSPublicKeyParameters) {
        this(gMSSPublicKeyParameters.getPublicKey(), gMSSPublicKeyParameters.getParameters());
    }

    public BCGMSSPublicKey(byte[] bArr, GMSSParameters gMSSParameters) {
        this.gmssParameterSet = gMSSParameters;
        this.publicKeyBytes = bArr;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "GMSS";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.gmss, new ParSet(this.gmssParameterSet.getNumOfLayers(), this.gmssParameterSet.getHeightOfTrees(), this.gmssParameterSet.getWinternitzParameter(), this.gmssParameterSet.getK()).toASN1Primitive()), new GMSSPublicKey(this.publicKeyBytes));
    }

    @Override // java.security.Key
    public String getFormat() {
        return KeyUtils.X509_CERITIFATE_FACTORY;
    }

    public GMSSParameters getParameterSet() {
        return this.gmssParameterSet;
    }

    public byte[] getPublicKeyBytes() {
        return this.publicKeyBytes;
    }

    public String toString() {
        String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("GMSS public key : "), new String(Hex.encode(this.publicKeyBytes)), "\nHeight of Trees: \n");
        for (int i = 0; i < this.gmssParameterSet.getHeightOfTrees().length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(outline91);
            sb.append("Layer ");
            sb.append(i);
            sb.append(" : ");
            sb.append(this.gmssParameterSet.getHeightOfTrees()[i]);
            sb.append(" WinternitzParameter: ");
            sb.append(this.gmssParameterSet.getWinternitzParameter()[i]);
            sb.append(" K: ");
            outline91 = GeneratedOutlineSupport1.outline86(sb, this.gmssParameterSet.getK()[i], "\n");
        }
        return outline91;
    }
}
