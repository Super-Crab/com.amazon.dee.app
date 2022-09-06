package org.bouncycastle.pqc.jcajce.provider.mceliece;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.PublicKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.asn1.McEliecePublicKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.crypto.mceliece.McEliecePublicKeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
/* loaded from: classes5.dex */
public class BCMcEliecePublicKey implements PublicKey {
    private static final long serialVersionUID = 1;
    private McEliecePublicKeyParameters params;

    public BCMcEliecePublicKey(McEliecePublicKeyParameters mcEliecePublicKeyParameters) {
        this.params = mcEliecePublicKeyParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BCMcEliecePublicKey) {
            BCMcEliecePublicKey bCMcEliecePublicKey = (BCMcEliecePublicKey) obj;
            return this.params.getN() == bCMcEliecePublicKey.getN() && this.params.getT() == bCMcEliecePublicKey.getT() && this.params.getG().equals(bCMcEliecePublicKey.getG());
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "McEliece";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.mcEliece), new McEliecePublicKey(this.params.getN(), this.params.getT(), this.params.getG())).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return KeyUtils.X509_CERITIFATE_FACTORY;
    }

    public GF2Matrix getG() {
        return this.params.getG();
    }

    public int getK() {
        return this.params.getK();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsymmetricKeyParameter getKeyParams() {
        return this.params;
    }

    public int getN() {
        return this.params.getN();
    }

    public int getT() {
        return this.params.getT();
    }

    public int hashCode() {
        int n = this.params.getN();
        return this.params.getG().hashCode() + (((this.params.getT() * 37) + n) * 37);
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("McEliecePublicKey:\n", " length of the code         : ");
        outline113.append(this.params.getN());
        outline113.append("\n");
        StringBuilder outline1132 = GeneratedOutlineSupport1.outline113(outline113.toString(), " error correction capability: ");
        outline1132.append(this.params.getT());
        outline1132.append("\n");
        StringBuilder outline1133 = GeneratedOutlineSupport1.outline113(outline1132.toString(), " generator matrix           : ");
        outline1133.append(this.params.getG());
        return outline1133.toString();
    }
}
