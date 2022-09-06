package org.bouncycastle.openpgp;

import androidx.core.view.MotionEventCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.bcpg.BCPGObject;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.ContainedPacket;
import org.bouncycastle.bcpg.DSASecretBCPGKey;
import org.bouncycastle.bcpg.ECSecretBCPGKey;
import org.bouncycastle.bcpg.EdSecretBCPGKey;
import org.bouncycastle.bcpg.ElGamalSecretBCPGKey;
import org.bouncycastle.bcpg.PublicKeyPacket;
import org.bouncycastle.bcpg.PublicSubkeyPacket;
import org.bouncycastle.bcpg.RSASecretBCPGKey;
import org.bouncycastle.bcpg.S2K;
import org.bouncycastle.bcpg.SecretKeyPacket;
import org.bouncycastle.bcpg.SecretSubkeyPacket;
import org.bouncycastle.bcpg.TrustPacket;
import org.bouncycastle.bcpg.UserAttributePacket;
import org.bouncycastle.bcpg.UserIDPacket;
import org.bouncycastle.gpg.SExprParser;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
import org.bouncycastle.openpgp.operator.PBEProtectionRemoverFactory;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.openpgp.operator.PGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
/* loaded from: classes5.dex */
public class PGPSecretKey {
    PGPPublicKey pub;
    SecretKeyPacket secret;

    public PGPSecretKey(int i, PGPKeyPair pGPKeyPair, String str, PGPSignatureSubpacketVector pGPSignatureSubpacketVector, PGPSignatureSubpacketVector pGPSignatureSubpacketVector2, PGPContentSignerBuilder pGPContentSignerBuilder, PBESecretKeyEncryptor pBESecretKeyEncryptor) throws PGPException {
        this(i, pGPKeyPair, str, null, pGPSignatureSubpacketVector, pGPSignatureSubpacketVector2, pGPContentSignerBuilder, pBESecretKeyEncryptor);
    }

    public PGPSecretKey(int i, PGPKeyPair pGPKeyPair, String str, PGPDigestCalculator pGPDigestCalculator, PGPSignatureSubpacketVector pGPSignatureSubpacketVector, PGPSignatureSubpacketVector pGPSignatureSubpacketVector2, PGPContentSignerBuilder pGPContentSignerBuilder, PBESecretKeyEncryptor pBESecretKeyEncryptor) throws PGPException {
        this(pGPKeyPair.getPrivateKey(), certifiedPublicKey(i, pGPKeyPair, str, pGPSignatureSubpacketVector, pGPSignatureSubpacketVector2, pGPContentSignerBuilder), pGPDigestCalculator, true, pBESecretKeyEncryptor);
    }

    public PGPSecretKey(SecretKeyPacket secretKeyPacket, PGPPublicKey pGPPublicKey) {
        this.secret = secretKeyPacket;
        this.pub = pGPPublicKey;
    }

    public PGPSecretKey(PGPKeyPair pGPKeyPair, PGPKeyPair pGPKeyPair2, PGPDigestCalculator pGPDigestCalculator, PGPSignatureSubpacketVector pGPSignatureSubpacketVector, PGPSignatureSubpacketVector pGPSignatureSubpacketVector2, PGPContentSignerBuilder pGPContentSignerBuilder, PBESecretKeyEncryptor pBESecretKeyEncryptor) throws PGPException {
        PGPSignatureGenerator pGPSignatureGenerator = new PGPSignatureGenerator(pGPContentSignerBuilder);
        pGPSignatureGenerator.init(24, pGPKeyPair.getPrivateKey());
        if (!pGPKeyPair2.getPublicKey().isEncryptionKey()) {
            if (pGPSignatureSubpacketVector == null) {
                PGPSignatureGenerator pGPSignatureGenerator2 = new PGPSignatureGenerator(pGPContentSignerBuilder);
                pGPSignatureGenerator2.init(25, pGPKeyPair2.getPrivateKey());
                PGPSignatureSubpacketGenerator pGPSignatureSubpacketGenerator = new PGPSignatureSubpacketGenerator();
                try {
                    pGPSignatureSubpacketGenerator.setEmbeddedSignature(false, pGPSignatureGenerator2.generateCertification(pGPKeyPair.getPublicKey(), pGPKeyPair2.getPublicKey()));
                    pGPSignatureSubpacketVector = pGPSignatureSubpacketGenerator.generate();
                } catch (IOException e) {
                    throw new PGPException(e.getMessage(), e);
                }
            } else if (!pGPSignatureSubpacketVector.hasSubpacket(32)) {
                throw new PGPException("signing subkey requires embedded PRIMARYKEY_BINDING signature");
            }
        }
        pGPSignatureGenerator.setHashedSubpackets(pGPSignatureSubpacketVector);
        pGPSignatureGenerator.setUnhashedSubpackets(pGPSignatureSubpacketVector2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(pGPSignatureGenerator.generateCertification(pGPKeyPair.getPublicKey(), pGPKeyPair2.getPublicKey()));
        PGPPublicKey pGPPublicKey = new PGPPublicKey(pGPKeyPair2.getPublicKey(), null, arrayList);
        pGPPublicKey.publicPk = new PublicSubkeyPacket(pGPPublicKey.getAlgorithm(), pGPPublicKey.getCreationTime(), pGPPublicKey.publicPk.getKey());
        this.pub = pGPPublicKey;
        this.secret = buildSecretKeyPacket(false, pGPKeyPair2.getPrivateKey(), pGPKeyPair2.getPublicKey(), pBESecretKeyEncryptor, pGPDigestCalculator);
    }

    public PGPSecretKey(PGPKeyPair pGPKeyPair, PGPKeyPair pGPKeyPair2, PGPDigestCalculator pGPDigestCalculator, PGPContentSignerBuilder pGPContentSignerBuilder, PBESecretKeyEncryptor pBESecretKeyEncryptor) throws PGPException {
        this(pGPKeyPair, pGPKeyPair2, pGPDigestCalculator, (PGPSignatureSubpacketVector) null, (PGPSignatureSubpacketVector) null, pGPContentSignerBuilder, pBESecretKeyEncryptor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PGPSecretKey(PGPPrivateKey pGPPrivateKey, PGPPublicKey pGPPublicKey, PGPDigestCalculator pGPDigestCalculator, PBESecretKeyEncryptor pBESecretKeyEncryptor) throws PGPException {
        this(pGPPrivateKey, pGPPublicKey, pGPDigestCalculator, false, pBESecretKeyEncryptor);
    }

    public PGPSecretKey(PGPPrivateKey pGPPrivateKey, PGPPublicKey pGPPublicKey, PGPDigestCalculator pGPDigestCalculator, boolean z, PBESecretKeyEncryptor pBESecretKeyEncryptor) throws PGPException {
        this.pub = pGPPublicKey;
        this.secret = buildSecretKeyPacket(z, pGPPrivateKey, pGPPublicKey, pBESecretKeyEncryptor, pGPDigestCalculator);
    }

    private static SecretKeyPacket buildSecretKeyPacket(boolean z, PGPPrivateKey pGPPrivateKey, PGPPublicKey pGPPublicKey, PBESecretKeyEncryptor pBESecretKeyEncryptor, PGPDigestCalculator pGPDigestCalculator) throws PGPException {
        int i;
        BCPGObject bCPGObject = (BCPGObject) pGPPrivateKey.getPrivateKeyDataPacket();
        if (bCPGObject == null) {
            return z ? new SecretKeyPacket(pGPPublicKey.publicPk, 0, null, null, new byte[0]) : new SecretSubkeyPacket(pGPPublicKey.publicPk, 0, null, null, new byte[0]);
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BCPGOutputStream bCPGOutputStream = new BCPGOutputStream(byteArrayOutputStream);
            bCPGOutputStream.writeObject(bCPGObject);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            int algorithm = pBESecretKeyEncryptor != null ? pBESecretKeyEncryptor.getAlgorithm() : 0;
            if (algorithm == 0) {
                bCPGOutputStream.write(checksum(null, byteArray, byteArray.length));
                return z ? new SecretKeyPacket(pGPPublicKey.publicPk, algorithm, null, null, byteArrayOutputStream.toByteArray()) : new SecretSubkeyPacket(pGPPublicKey.publicPk, algorithm, null, null, byteArrayOutputStream.toByteArray());
            }
            bCPGOutputStream.write(checksum(pGPDigestCalculator, byteArray, byteArray.length));
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            byte[] encryptKeyData = pBESecretKeyEncryptor.encryptKeyData(byteArray2, 0, byteArray2.length);
            byte[] cipherIV = pBESecretKeyEncryptor.getCipherIV();
            S2K s2k = pBESecretKeyEncryptor.getS2K();
            if (pGPDigestCalculator == null) {
                i = 255;
            } else if (pGPDigestCalculator.getAlgorithm() != 2) {
                throw new PGPException("only SHA1 supported for key checksum calculations.");
            } else {
                i = 254;
            }
            int i2 = i;
            return z ? new SecretKeyPacket(pGPPublicKey.publicPk, algorithm, i2, s2k, cipherIV, encryptKeyData) : new SecretSubkeyPacket(pGPPublicKey.publicPk, algorithm, i2, s2k, cipherIV, encryptKeyData);
        } catch (PGPException e) {
            throw e;
        } catch (Exception e2) {
            throw new PGPException("Exception encrypting key", e2);
        }
    }

    private static PGPPublicKey certifiedPublicKey(int i, PGPKeyPair pGPKeyPair, String str, PGPSignatureSubpacketVector pGPSignatureSubpacketVector, PGPSignatureSubpacketVector pGPSignatureSubpacketVector2, PGPContentSignerBuilder pGPContentSignerBuilder) throws PGPException {
        try {
            PGPSignatureGenerator pGPSignatureGenerator = new PGPSignatureGenerator(pGPContentSignerBuilder);
            pGPSignatureGenerator.init(i, pGPKeyPair.getPrivateKey());
            pGPSignatureGenerator.setHashedSubpackets(pGPSignatureSubpacketVector);
            pGPSignatureGenerator.setUnhashedSubpackets(pGPSignatureSubpacketVector2);
            try {
                return PGPPublicKey.addCertification(pGPKeyPair.getPublicKey(), str, pGPSignatureGenerator.generateCertification(str, pGPKeyPair.getPublicKey()));
            } catch (Exception e) {
                throw new PGPException(GeneratedOutlineSupport1.outline68("exception doing certification: ", e), e);
            }
        } catch (Exception e2) {
            throw new PGPException(GeneratedOutlineSupport1.outline68("creating signature generator: ", e2), e2);
        }
    }

    private static byte[] checksum(PGPDigestCalculator pGPDigestCalculator, byte[] bArr, int i) throws PGPException {
        if (pGPDigestCalculator != null) {
            OutputStream outputStream = pGPDigestCalculator.getOutputStream();
            try {
                outputStream.write(bArr, 0, i);
                outputStream.close();
                return pGPDigestCalculator.getDigest();
            } catch (Exception e) {
                throw new PGPException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("checksum digest calculation failed: ")), e);
            }
        }
        int i2 = 0;
        for (int i3 = 0; i3 != i; i3++) {
            i2 += bArr[i3] & 255;
        }
        return new byte[]{(byte) (i2 >> 8), (byte) i2};
    }

    public static PGPSecretKey copyWithNewPassword(PGPSecretKey pGPSecretKey, PBESecretKeyDecryptor pBESecretKeyDecryptor, PBESecretKeyEncryptor pBESecretKeyEncryptor) throws PGPException {
        S2K s2k;
        byte[] bArr;
        byte[] bArr2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        byte[] encryptKeyData;
        if (!pGPSecretKey.isPrivateKeyEmpty()) {
            byte[] extractKeyData = pGPSecretKey.extractKeyData(pBESecretKeyDecryptor);
            int s2KUsage = pGPSecretKey.secret.getS2KUsage();
            if (pBESecretKeyEncryptor == null || pBESecretKeyEncryptor.getAlgorithm() == 0) {
                if (pGPSecretKey.secret.getS2KUsage() == 254) {
                    byte[] bArr3 = new byte[extractKeyData.length - 18];
                    System.arraycopy(extractKeyData, 0, bArr3, 0, bArr3.length - 2);
                    byte[] checksum = checksum(null, bArr3, bArr3.length - 2);
                    bArr3[bArr3.length - 2] = checksum[0];
                    bArr3[bArr3.length - 1] = checksum[1];
                    bArr2 = bArr3;
                    s2k = null;
                    bArr = null;
                } else {
                    s2k = null;
                    bArr = null;
                    bArr2 = extractKeyData;
                }
                i = 0;
                i2 = 0;
            } else {
                byte b = 255;
                int i6 = s2KUsage == 0 ? 255 : s2KUsage;
                if (pGPSecretKey.secret.getPublicKeyPacket().getVersion() < 4) {
                    byte[] key = pBESecretKeyEncryptor.getKey();
                    byte[] bArr4 = new byte[extractKeyData.length];
                    if (pBESecretKeyEncryptor.getHashAlgorithm() != 1) {
                        throw new PGPException("MD5 Digest Calculator required for version 3 key encryptor.");
                    }
                    byte[] bArr5 = null;
                    int i7 = 0;
                    int i8 = 0;
                    while (i7 != 4) {
                        int i9 = i8 + 1;
                        int i10 = ((((extractKeyData[i8] & b) << 8) | (extractKeyData[i9] & b)) + 7) / 8;
                        bArr4[i8] = extractKeyData[i8];
                        bArr4[i9] = extractKeyData[i9];
                        int i11 = i8 + 2;
                        if (i10 > extractKeyData.length - i11) {
                            throw new PGPException("out of range encLen found in rawKeyData");
                        }
                        if (i7 == 0) {
                            encryptKeyData = pBESecretKeyEncryptor.encryptKeyData(key, extractKeyData, i11, i10);
                            bArr5 = pBESecretKeyEncryptor.getCipherIV();
                            i3 = i11;
                            i4 = i10;
                            i5 = i7;
                        } else {
                            byte[] bArr6 = new byte[bArr5.length];
                            System.arraycopy(bArr4, i8 - bArr5.length, bArr6, 0, bArr6.length);
                            i3 = i11;
                            i4 = i10;
                            i5 = i7;
                            encryptKeyData = pBESecretKeyEncryptor.encryptKeyData(key, bArr6, extractKeyData, i3, i4);
                            bArr5 = bArr5;
                        }
                        System.arraycopy(encryptKeyData, 0, bArr4, i3, encryptKeyData.length);
                        i8 = i4 + 2 + i8;
                        i7 = i5 + 1;
                        b = 255;
                    }
                    bArr4[i8] = extractKeyData[i8];
                    int i12 = i8 + 1;
                    bArr4[i12] = extractKeyData[i12];
                    S2K s2k2 = pBESecretKeyEncryptor.getS2K();
                    i = pBESecretKeyEncryptor.getAlgorithm();
                    i2 = i6;
                    bArr2 = bArr4;
                    bArr = bArr5;
                    s2k = s2k2;
                } else {
                    byte[] encryptKeyData2 = pBESecretKeyEncryptor.encryptKeyData(extractKeyData, 0, extractKeyData.length);
                    byte[] cipherIV = pBESecretKeyEncryptor.getCipherIV();
                    s2k = pBESecretKeyEncryptor.getS2K();
                    bArr = cipherIV;
                    bArr2 = encryptKeyData2;
                    i = pBESecretKeyEncryptor.getAlgorithm();
                    i2 = i6;
                }
            }
            SecretKeyPacket secretKeyPacket = pGPSecretKey.secret;
            return new PGPSecretKey(secretKeyPacket instanceof SecretSubkeyPacket ? new SecretSubkeyPacket(secretKeyPacket.getPublicKeyPacket(), i, i2, s2k, bArr, bArr2) : new SecretKeyPacket(secretKeyPacket.getPublicKeyPacket(), i, i2, s2k, bArr, bArr2), pGPSecretKey.pub);
        }
        throw new PGPException("no private key in this SecretKey - public key present only.");
    }

    private byte[] extractKeyData(PBESecretKeyDecryptor pBESecretKeyDecryptor) throws PGPException {
        int i;
        byte[] secretKeyData = this.secret.getSecretKeyData();
        if (this.secret.getEncAlgorithm() != 0) {
            try {
                int i2 = 0;
                if (this.secret.getPublicKeyPacket().getVersion() == 4) {
                    byte[] recoverKeyData = pBESecretKeyDecryptor.recoverKeyData(this.secret.getEncAlgorithm(), pBESecretKeyDecryptor.makeKeyFromPassPhrase(this.secret.getEncAlgorithm(), this.secret.getS2K()), this.secret.getIV(), secretKeyData, 0, secretKeyData.length);
                    boolean z = this.secret.getS2KUsage() == 254;
                    byte[] checksum = checksum(z ? pBESecretKeyDecryptor.getChecksumCalculator(2) : null, recoverKeyData, z ? recoverKeyData.length - 20 : recoverKeyData.length - 2);
                    while (i2 != checksum.length) {
                        if (checksum[i2] != recoverKeyData[(recoverKeyData.length - checksum.length) + i2]) {
                            throw new PGPException("checksum mismatch at " + i2 + " of " + checksum.length);
                        }
                        i2++;
                    }
                    return recoverKeyData;
                }
                byte[] makeKeyFromPassPhrase = pBESecretKeyDecryptor.makeKeyFromPassPhrase(this.secret.getEncAlgorithm(), this.secret.getS2K());
                byte[] bArr = new byte[secretKeyData.length];
                byte[] bArr2 = new byte[this.secret.getIV().length];
                System.arraycopy(this.secret.getIV(), 0, bArr2, 0, bArr2.length);
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 4; i3 != i5; i5 = 4) {
                    int i6 = i4 + 1;
                    int i7 = ((((secretKeyData[i4] & 255) << 8) | (secretKeyData[i6] & 255)) + 7) / 8;
                    bArr[i4] = secretKeyData[i4];
                    bArr[i6] = secretKeyData[i6];
                    int i8 = i4 + 2;
                    if (i7 > secretKeyData.length - i8) {
                        throw new PGPException("out of range encLen found in encData");
                    }
                    int i9 = i3;
                    byte[] recoverKeyData2 = pBESecretKeyDecryptor.recoverKeyData(this.secret.getEncAlgorithm(), makeKeyFromPassPhrase, bArr2, secretKeyData, i8, i7);
                    System.arraycopy(recoverKeyData2, 0, bArr, i8, recoverKeyData2.length);
                    i4 = i7 + 2 + i4;
                    if (i9 != 3) {
                        System.arraycopy(secretKeyData, i4 - bArr2.length, bArr2, 0, bArr2.length);
                    }
                    i3 = i9 + 1;
                }
                bArr[i4] = secretKeyData[i4];
                int i10 = i4 + 1;
                bArr[i10] = secretKeyData[i10];
                int i11 = (secretKeyData[i10] & 255) | ((secretKeyData[i4] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
                int i12 = 0;
                while (i2 < bArr.length - 2) {
                    i12 += bArr[i2] & 255;
                    i2++;
                }
                if ((i12 & 65535) == i11) {
                    return bArr;
                }
                throw new PGPException("checksum mismatch: passphrase wrong, expected " + Integer.toHexString(i11) + " found " + Integer.toHexString(i));
            } catch (PGPException e) {
                throw e;
            } catch (Exception e2) {
                throw new PGPException("Exception decrypting key", e2);
            }
        }
        return secretKeyData;
    }

    public static PGPSecretKey parseSecretKeyFromSExpr(InputStream inputStream, PBEProtectionRemoverFactory pBEProtectionRemoverFactory, PGPPublicKey pGPPublicKey) throws IOException, PGPException {
        return new SExprParser(null).parseSecretKey(inputStream, pBEProtectionRemoverFactory, pGPPublicKey);
    }

    public static PGPSecretKey parseSecretKeyFromSExpr(InputStream inputStream, PBEProtectionRemoverFactory pBEProtectionRemoverFactory, KeyFingerPrintCalculator keyFingerPrintCalculator) throws IOException, PGPException {
        return new SExprParser(null).parseSecretKey(inputStream, pBEProtectionRemoverFactory, keyFingerPrintCalculator);
    }

    public static PGPSecretKey replacePublicKey(PGPSecretKey pGPSecretKey, PGPPublicKey pGPPublicKey) {
        if (pGPPublicKey.getKeyID() == pGPSecretKey.getKeyID()) {
            return new PGPSecretKey(pGPSecretKey.secret, pGPPublicKey);
        }
        throw new IllegalArgumentException("keyIDs do not match");
    }

    public void encode(OutputStream outputStream) throws IOException {
        BCPGOutputStream bCPGOutputStream = outputStream instanceof BCPGOutputStream ? (BCPGOutputStream) outputStream : new BCPGOutputStream(outputStream);
        bCPGOutputStream.writePacket(this.secret);
        TrustPacket trustPacket = this.pub.trustPk;
        if (trustPacket != null) {
            bCPGOutputStream.writePacket(trustPacket);
        }
        if (this.pub.subSigs != null) {
            for (int i = 0; i != this.pub.subSigs.size(); i++) {
                ((PGPSignature) this.pub.subSigs.get(i)).encode(bCPGOutputStream);
            }
            return;
        }
        for (int i2 = 0; i2 != this.pub.keySigs.size(); i2++) {
            ((PGPSignature) this.pub.keySigs.get(i2)).encode(bCPGOutputStream);
        }
        for (int i3 = 0; i3 != this.pub.ids.size(); i3++) {
            if (this.pub.ids.get(i3) instanceof UserIDPacket) {
                bCPGOutputStream.writePacket((UserIDPacket) this.pub.ids.get(i3));
            } else {
                bCPGOutputStream.writePacket(new UserAttributePacket(((PGPUserAttributeSubpacketVector) this.pub.ids.get(i3)).toSubpacketArray()));
            }
            if (this.pub.idTrusts.get(i3) != null) {
                bCPGOutputStream.writePacket((ContainedPacket) this.pub.idTrusts.get(i3));
            }
            ArrayList arrayList = (ArrayList) this.pub.idSigs.get(i3);
            for (int i4 = 0; i4 != arrayList.size(); i4++) {
                ((PGPSignature) arrayList.get(i4)).encode(bCPGOutputStream);
            }
        }
    }

    public PGPKeyPair extractKeyPair(PBESecretKeyDecryptor pBESecretKeyDecryptor) throws PGPException {
        return new PGPKeyPair(getPublicKey(), extractPrivateKey(pBESecretKeyDecryptor));
    }

    public PGPPrivateKey extractPrivateKey(PBESecretKeyDecryptor pBESecretKeyDecryptor) throws PGPException {
        if (isPrivateKeyEmpty()) {
            return null;
        }
        PublicKeyPacket publicKeyPacket = this.secret.getPublicKeyPacket();
        try {
            BCPGInputStream bCPGInputStream = new BCPGInputStream(new ByteArrayInputStream(extractKeyData(pBESecretKeyDecryptor)));
            int algorithm = publicKeyPacket.getAlgorithm();
            if (algorithm == 1 || algorithm == 2 || algorithm == 3) {
                return new PGPPrivateKey(getKeyID(), publicKeyPacket, new RSASecretBCPGKey(bCPGInputStream));
            } else if (algorithm == 22) {
                return new PGPPrivateKey(getKeyID(), publicKeyPacket, new EdSecretBCPGKey(bCPGInputStream));
            } else {
                switch (algorithm) {
                    case 16:
                    case 20:
                        return new PGPPrivateKey(getKeyID(), publicKeyPacket, new ElGamalSecretBCPGKey(bCPGInputStream));
                    case 17:
                        return new PGPPrivateKey(getKeyID(), publicKeyPacket, new DSASecretBCPGKey(bCPGInputStream));
                    case 18:
                    case 19:
                        return new PGPPrivateKey(getKeyID(), publicKeyPacket, new ECSecretBCPGKey(bCPGInputStream));
                    default:
                        throw new PGPException("unknown public key algorithm encountered");
                }
            }
        } catch (PGPException e) {
            throw e;
        } catch (Exception e2) {
            throw new PGPException("Exception constructing key", e2);
        }
    }

    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public int getKeyEncryptionAlgorithm() {
        return this.secret.getEncAlgorithm();
    }

    public long getKeyID() {
        return this.pub.getKeyID();
    }

    public PGPPublicKey getPublicKey() {
        return this.pub;
    }

    public S2K getS2K() {
        return this.secret.getS2K();
    }

    public int getS2KUsage() {
        return this.secret.getS2KUsage();
    }

    public Iterator<PGPUserAttributeSubpacketVector> getUserAttributes() {
        return this.pub.getUserAttributes();
    }

    public Iterator<String> getUserIDs() {
        return this.pub.getUserIDs();
    }

    public boolean isMasterKey() {
        return this.pub.isMasterKey();
    }

    public boolean isPrivateKeyEmpty() {
        byte[] secretKeyData = this.secret.getSecretKeyData();
        return secretKeyData == null || secretKeyData.length < 1;
    }

    public boolean isSigningKey() {
        int algorithm = this.pub.getAlgorithm();
        return algorithm == 1 || algorithm == 3 || algorithm == 17 || algorithm == 19 || algorithm == 22 || algorithm == 20;
    }
}
