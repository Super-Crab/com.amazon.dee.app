package org.bouncycastle.crypto.agreement.kdf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class DHKEKGenerator implements DerivationFunction {
    private ASN1ObjectIdentifier algorithm;
    private final Digest digest;
    private int keySize;
    private byte[] partyAInfo;
    private byte[] z;

    public DHKEKGenerator(Digest digest) {
        this.digest = digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        if (bArr.length - i2 >= i) {
            long j = i2;
            int digestSize = this.digest.getDigestSize();
            if (j > 8589934591L) {
                throw new IllegalArgumentException("Output length too large");
            }
            long j2 = digestSize;
            int i3 = (int) (((j + j2) - 1) / j2);
            byte[] bArr2 = new byte[this.digest.getDigestSize()];
            int i4 = 0;
            int i5 = 1;
            int i6 = i;
            int i7 = 0;
            while (i4 < i3) {
                Digest digest = this.digest;
                byte[] bArr3 = this.z;
                digest.update(bArr3, i7, bArr3.length);
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                aSN1EncodableVector2.add(this.algorithm);
                aSN1EncodableVector2.add(new DEROctetString(Pack.intToBigEndian(i5)));
                aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
                byte[] bArr4 = this.partyAInfo;
                if (bArr4 != null) {
                    aSN1EncodableVector.add(new DERTaggedObject(true, i7, new DEROctetString(bArr4)));
                }
                aSN1EncodableVector.add(new DERTaggedObject(true, 2, new DEROctetString(Pack.intToBigEndian(this.keySize))));
                try {
                    byte[] encoded = new DERSequence(aSN1EncodableVector).getEncoded("DER");
                    this.digest.update(encoded, 0, encoded.length);
                    this.digest.doFinal(bArr2, 0);
                    if (i2 > digestSize) {
                        System.arraycopy(bArr2, 0, bArr, i6, digestSize);
                        i6 += digestSize;
                        i2 -= digestSize;
                    } else {
                        System.arraycopy(bArr2, 0, bArr, i6, i2);
                    }
                    i5++;
                    i4++;
                    i7 = 0;
                } catch (IOException e) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to encode parameter info: ")));
                }
            }
            this.digest.reset();
            return (int) j;
        }
        throw new OutputLengthException("output buffer too small");
    }

    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        DHKDFParameters dHKDFParameters = (DHKDFParameters) derivationParameters;
        this.algorithm = dHKDFParameters.getAlgorithm();
        this.keySize = dHKDFParameters.getKeySize();
        this.z = dHKDFParameters.getZ();
        this.partyAInfo = dHKDFParameters.getExtraInfo();
    }
}
