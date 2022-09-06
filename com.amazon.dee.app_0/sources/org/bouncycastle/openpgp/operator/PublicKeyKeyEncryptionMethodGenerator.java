package org.bouncycastle.openpgp.operator;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.bcpg.ContainedPacket;
import org.bouncycastle.bcpg.MPInteger;
import org.bouncycastle.bcpg.PublicKeyEncSessionPacket;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.util.Properties;
/* loaded from: classes5.dex */
public abstract class PublicKeyKeyEncryptionMethodGenerator extends PGPKeyEncryptionMethodGenerator {
    public static final String SESSION_KEY_OBFUSCATION_PROPERTY = "org.bouncycastle.openpgp.session_key_obfuscation";
    private PGPPublicKey pubKey;
    protected boolean sessionKeyObfuscation;

    /* JADX INFO: Access modifiers changed from: protected */
    public PublicKeyKeyEncryptionMethodGenerator(PGPPublicKey pGPPublicKey) {
        int algorithm = pGPPublicKey.getAlgorithm();
        if (algorithm != 1 && algorithm != 2) {
            if (algorithm == 3) {
                throw new IllegalArgumentException("Can't use an RSA_SIGN key for encryption.");
            }
            switch (algorithm) {
                case 16:
                case 18:
                case 20:
                    break;
                case 17:
                    throw new IllegalArgumentException("Can't use DSA for encryption.");
                case 19:
                    throw new IllegalArgumentException("Can't use ECDSA for encryption.");
                default:
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown asymmetric algorithm: ");
                    outline107.append(pGPPublicKey.getAlgorithm());
                    throw new IllegalArgumentException(outline107.toString());
            }
        }
        this.pubKey = pGPPublicKey;
        this.sessionKeyObfuscation = getSessionKeyObfuscationDefault();
    }

    private byte[] convertToEncodedMPI(byte[] bArr) throws PGPException {
        try {
            return new MPInteger(new BigInteger(1, bArr)).getEncoded();
        } catch (IOException e) {
            throw new PGPException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("Invalid MPI encoding: ")), e);
        }
    }

    private static boolean getSessionKeyObfuscationDefault() {
        return !Properties.isOverrideSetTo(SESSION_KEY_OBFUSCATION_PROPERTY, false);
    }

    protected abstract byte[] encryptSessionInfo(PGPPublicKey pGPPublicKey, byte[] bArr) throws PGPException;

    @Override // org.bouncycastle.openpgp.operator.PGPKeyEncryptionMethodGenerator
    public ContainedPacket generate(int i, byte[] bArr) throws PGPException {
        return new PublicKeyEncSessionPacket(this.pubKey.getKeyID(), this.pubKey.getAlgorithm(), processSessionInfo(encryptSessionInfo(this.pubKey, bArr)));
    }

    public byte[][] processSessionInfo(byte[] bArr) throws PGPException {
        int algorithm = this.pubKey.getAlgorithm();
        if (algorithm == 1 || algorithm == 2) {
            return new byte[][]{convertToEncodedMPI(bArr)};
        }
        if (algorithm != 16) {
            if (algorithm == 18) {
                return new byte[][]{bArr};
            }
            if (algorithm != 20) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown asymmetric algorithm: ");
                outline107.append(this.pubKey.getAlgorithm());
                throw new PGPException(outline107.toString());
            }
        }
        byte[] bArr2 = new byte[bArr.length / 2];
        byte[] bArr3 = new byte[bArr.length / 2];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        System.arraycopy(bArr, bArr2.length, bArr3, 0, bArr3.length);
        return new byte[][]{convertToEncodedMPI(bArr2), convertToEncodedMPI(bArr3)};
    }

    public PublicKeyKeyEncryptionMethodGenerator setSessionKeyObfuscation(boolean z) {
        this.sessionKeyObfuscation = z;
        return this;
    }
}
