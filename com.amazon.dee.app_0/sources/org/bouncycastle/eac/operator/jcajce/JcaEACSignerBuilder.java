package org.bouncycastle.eac.operator.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.eac.operator.EACSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OperatorStreamException;
import org.bouncycastle.operator.RuntimeOperatorException;
/* loaded from: classes4.dex */
public class JcaEACSignerBuilder {
    private static final Hashtable sigNames = new Hashtable();
    private EACHelper helper = new DefaultEACHelper();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class SignatureOutputStream extends OutputStream {
        private Signature sig;

        SignatureOutputStream(Signature signature) {
            this.sig = signature;
        }

        byte[] getSignature() throws SignatureException {
            return this.sig.sign();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            try {
                this.sig.update((byte) i);
            } catch (SignatureException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception in content signer: ");
                outline107.append(e.getMessage());
                throw new OperatorStreamException(outline107.toString(), e);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            try {
                this.sig.update(bArr);
            } catch (SignatureException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception in content signer: ");
                outline107.append(e.getMessage());
                throw new OperatorStreamException(outline107.toString(), e);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.sig.update(bArr, i, i2);
            } catch (SignatureException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception in content signer: ");
                outline107.append(e.getMessage());
                throw new OperatorStreamException(outline107.toString(), e);
            }
        }
    }

    static {
        sigNames.put("SHA1withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1);
        sigNames.put("SHA256withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256);
        sigNames.put("SHA1withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1);
        sigNames.put("SHA256withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256);
        sigNames.put("SHA512withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_512);
        sigNames.put("SHA512withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_512);
        sigNames.put("SHA1withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_1);
        sigNames.put("SHA224withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_224);
        sigNames.put("SHA256withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_256);
        sigNames.put("SHA384withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_384);
        sigNames.put("SHA512withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_512);
    }

    private static void copyUnsignedInt(byte[] bArr, byte[] bArr2, int i) {
        int length = bArr.length;
        int i2 = 0;
        if (bArr[0] == 0) {
            length--;
            i2 = 1;
        }
        System.arraycopy(bArr, i2, bArr2, i, length);
    }

    public static int max(int i, int i2) {
        return i > i2 ? i : i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] reencode(byte[] bArr) {
        ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(bArr);
        BigInteger value = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue();
        BigInteger value2 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue();
        byte[] byteArray = value.toByteArray();
        byte[] byteArray2 = value2.toByteArray();
        int unsignedIntLength = unsignedIntLength(byteArray);
        int unsignedIntLength2 = unsignedIntLength(byteArray2);
        int max = max(unsignedIntLength, unsignedIntLength2);
        int i = max * 2;
        byte[] bArr2 = new byte[i];
        Arrays.fill(bArr2, (byte) 0);
        copyUnsignedInt(byteArray, bArr2, max - unsignedIntLength);
        copyUnsignedInt(byteArray2, bArr2, i - unsignedIntLength2);
        return bArr2;
    }

    private static int unsignedIntLength(byte[] bArr) {
        int length = bArr.length;
        return bArr[0] == 0 ? length - 1 : length;
    }

    public EACSigner build(String str, PrivateKey privateKey) throws OperatorCreationException {
        return build((ASN1ObjectIdentifier) sigNames.get(str), privateKey);
    }

    public EACSigner build(final ASN1ObjectIdentifier aSN1ObjectIdentifier, PrivateKey privateKey) throws OperatorCreationException {
        try {
            Signature signature = this.helper.getSignature(aSN1ObjectIdentifier);
            signature.initSign(privateKey);
            final SignatureOutputStream signatureOutputStream = new SignatureOutputStream(signature);
            return new EACSigner() { // from class: org.bouncycastle.eac.operator.jcajce.JcaEACSignerBuilder.1
                @Override // org.bouncycastle.eac.operator.EACSigner
                public OutputStream getOutputStream() {
                    return signatureOutputStream;
                }

                @Override // org.bouncycastle.eac.operator.EACSigner
                public byte[] getSignature() {
                    try {
                        byte[] signature2 = signatureOutputStream.getSignature();
                        return aSN1ObjectIdentifier.on(EACObjectIdentifiers.id_TA_ECDSA) ? JcaEACSignerBuilder.reencode(signature2) : signature2;
                    } catch (SignatureException e) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception obtaining signature: ");
                        outline107.append(e.getMessage());
                        throw new RuntimeOperatorException(outline107.toString(), e);
                    }
                }

                @Override // org.bouncycastle.eac.operator.EACSigner
                public ASN1ObjectIdentifier getUsageIdentifier() {
                    return aSN1ObjectIdentifier;
                }
            };
        } catch (InvalidKeyException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid key: ");
            outline107.append(e.getMessage());
            throw new OperatorCreationException(outline107.toString(), e);
        } catch (NoSuchAlgorithmException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("unable to find algorithm: ");
            outline1072.append(e2.getMessage());
            throw new OperatorCreationException(outline1072.toString(), e2);
        } catch (NoSuchProviderException e3) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("unable to find provider: ");
            outline1073.append(e3.getMessage());
            throw new OperatorCreationException(outline1073.toString(), e3);
        }
    }

    public JcaEACSignerBuilder setProvider(String str) {
        this.helper = new NamedEACHelper(str);
        return this;
    }

    public JcaEACSignerBuilder setProvider(Provider provider) {
        this.helper = new ProviderEACHelper(provider);
        return this;
    }
}
