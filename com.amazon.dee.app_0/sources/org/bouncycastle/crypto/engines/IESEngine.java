package org.bouncycastle.crypto.engines;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.IESParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class IESEngine {
    private byte[] IV;
    byte[] V;
    BasicAgreement agree;
    BufferedBlockCipher cipher;
    boolean forEncryption;
    DerivationFunction kdf;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
    Mac mac;
    byte[] macBuf;
    IESParameters param;
    CipherParameters privParam;
    CipherParameters pubParam;

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = null;
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, BufferedBlockCipher bufferedBlockCipher) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = bufferedBlockCipher;
    }

    private byte[] decryptBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        int processBytes;
        if (i2 >= this.mac.getMacSize() + this.V.length) {
            if (this.cipher == null) {
                byte[] bArr4 = new byte[(i2 - this.V.length) - this.mac.getMacSize()];
                bArr2 = new byte[this.param.getMacKeySize() / 8];
                byte[] bArr5 = new byte[bArr4.length + bArr2.length];
                this.kdf.generateBytes(bArr5, 0, bArr5.length);
                if (this.V.length != 0) {
                    System.arraycopy(bArr5, 0, bArr2, 0, bArr2.length);
                    System.arraycopy(bArr5, bArr2.length, bArr4, 0, bArr4.length);
                } else {
                    System.arraycopy(bArr5, 0, bArr4, 0, bArr4.length);
                    System.arraycopy(bArr5, bArr4.length, bArr2, 0, bArr2.length);
                }
                byte[] bArr6 = new byte[bArr4.length];
                for (int i3 = 0; i3 != bArr4.length; i3++) {
                    bArr6[i3] = (byte) (bArr[(this.V.length + i) + i3] ^ bArr4[i3]);
                }
                bArr3 = bArr6;
                processBytes = 0;
            } else {
                byte[] bArr7 = new byte[((IESWithCipherParameters) this.param).getCipherKeySize() / 8];
                bArr2 = new byte[this.param.getMacKeySize() / 8];
                byte[] bArr8 = new byte[bArr7.length + bArr2.length];
                this.kdf.generateBytes(bArr8, 0, bArr8.length);
                System.arraycopy(bArr8, 0, bArr7, 0, bArr7.length);
                System.arraycopy(bArr8, bArr7.length, bArr2, 0, bArr2.length);
                CipherParameters keyParameter = new KeyParameter(bArr7);
                byte[] bArr9 = this.IV;
                if (bArr9 != null) {
                    keyParameter = new ParametersWithIV(keyParameter, bArr9);
                }
                this.cipher.init(false, keyParameter);
                bArr3 = new byte[this.cipher.getOutputSize((i2 - this.V.length) - this.mac.getMacSize())];
                BufferedBlockCipher bufferedBlockCipher = this.cipher;
                byte[] bArr10 = this.V;
                processBytes = bufferedBlockCipher.processBytes(bArr, bArr10.length + i, (i2 - bArr10.length) - this.mac.getMacSize(), bArr3, 0);
            }
            byte[] encodingV = this.param.getEncodingV();
            byte[] bArr11 = null;
            if (this.V.length != 0) {
                bArr11 = getLengthTag(encodingV);
            }
            int i4 = i + i2;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i4 - this.mac.getMacSize(), i4);
            byte[] bArr12 = new byte[copyOfRange.length];
            this.mac.init(new KeyParameter(bArr2));
            Mac mac = this.mac;
            byte[] bArr13 = this.V;
            mac.update(bArr, i + bArr13.length, (i2 - bArr13.length) - bArr12.length);
            if (encodingV != null) {
                this.mac.update(encodingV, 0, encodingV.length);
            }
            if (this.V.length != 0) {
                this.mac.update(bArr11, 0, bArr11.length);
            }
            this.mac.doFinal(bArr12, 0);
            if (!Arrays.constantTimeAreEqual(copyOfRange, bArr12)) {
                throw new InvalidCipherTextException("invalid MAC");
            }
            BufferedBlockCipher bufferedBlockCipher2 = this.cipher;
            return bufferedBlockCipher2 == null ? bArr3 : Arrays.copyOfRange(bArr3, 0, bufferedBlockCipher2.doFinal(bArr3, processBytes) + processBytes);
        }
        throw new InvalidCipherTextException("Length of input must be greater than the MAC and V combined");
    }

    private byte[] encryptBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        BufferedBlockCipher bufferedBlockCipher;
        CipherParameters keyParameter;
        byte[] bArr3;
        if (this.cipher == null) {
            byte[] bArr4 = new byte[i2];
            bArr2 = new byte[this.param.getMacKeySize() / 8];
            byte[] bArr5 = new byte[bArr4.length + bArr2.length];
            this.kdf.generateBytes(bArr5, 0, bArr5.length);
            if (this.V.length != 0) {
                System.arraycopy(bArr5, 0, bArr2, 0, bArr2.length);
                System.arraycopy(bArr5, bArr2.length, bArr4, 0, bArr4.length);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, bArr4.length);
                System.arraycopy(bArr5, i2, bArr2, 0, bArr2.length);
            }
            byte[] bArr6 = new byte[i2];
            for (int i3 = 0; i3 != i2; i3++) {
                bArr6[i3] = (byte) (bArr[i + i3] ^ bArr4[i3]);
            }
            bArr3 = bArr6;
        } else {
            byte[] bArr7 = new byte[((IESWithCipherParameters) this.param).getCipherKeySize() / 8];
            bArr2 = new byte[this.param.getMacKeySize() / 8];
            byte[] bArr8 = new byte[bArr7.length + bArr2.length];
            this.kdf.generateBytes(bArr8, 0, bArr8.length);
            System.arraycopy(bArr8, 0, bArr7, 0, bArr7.length);
            System.arraycopy(bArr8, bArr7.length, bArr2, 0, bArr2.length);
            if (this.IV != null) {
                bufferedBlockCipher = this.cipher;
                keyParameter = new ParametersWithIV(new KeyParameter(bArr7), this.IV);
            } else {
                bufferedBlockCipher = this.cipher;
                keyParameter = new KeyParameter(bArr7);
            }
            bufferedBlockCipher.init(true, keyParameter);
            bArr3 = new byte[this.cipher.getOutputSize(i2)];
            int processBytes = this.cipher.processBytes(bArr, i, i2, bArr3, 0);
            i2 = this.cipher.doFinal(bArr3, processBytes) + processBytes;
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] bArr9 = null;
        if (this.V.length != 0) {
            bArr9 = getLengthTag(encodingV);
        }
        byte[] bArr10 = new byte[this.mac.getMacSize()];
        this.mac.init(new KeyParameter(bArr2));
        this.mac.update(bArr3, 0, bArr3.length);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.V.length != 0) {
            this.mac.update(bArr9, 0, bArr9.length);
        }
        this.mac.doFinal(bArr10, 0);
        byte[] bArr11 = this.V;
        byte[] bArr12 = new byte[bArr11.length + i2 + bArr10.length];
        System.arraycopy(bArr11, 0, bArr12, 0, bArr11.length);
        System.arraycopy(bArr3, 0, bArr12, this.V.length, i2);
        System.arraycopy(bArr10, 0, bArr12, this.V.length + i2, bArr10.length);
        return bArr12;
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.IV = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            this.IV = null;
        }
        this.param = (IESParameters) cipherParameters;
    }

    public BufferedBlockCipher getCipher() {
        return this.cipher;
    }

    protected byte[] getLengthTag(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            Pack.longToBigEndian(bArr.length * 8, bArr2, 0);
        }
        return bArr2;
    }

    public Mac getMac() {
        return this.mac;
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.forEncryption = false;
        this.privParam = asymmetricKeyParameter;
        this.keyParser = keyParser;
        extractParams(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.forEncryption = true;
        this.pubParam = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(cipherParameters);
    }

    public void init(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.forEncryption = z;
        this.privParam = cipherParameters;
        this.pubParam = cipherParameters2;
        this.V = new byte[0];
        extractParams(cipherParameters3);
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.forEncryption) {
            EphemeralKeyPairGenerator ephemeralKeyPairGenerator = this.keyPairGenerator;
            if (ephemeralKeyPairGenerator != null) {
                EphemeralKeyPair generate = ephemeralKeyPairGenerator.generate();
                this.privParam = generate.getKeyPair().getPrivate();
                this.V = generate.getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
            try {
                this.pubParam = this.keyParser.readKey(byteArrayInputStream);
                this.V = Arrays.copyOfRange(bArr, i, (i2 - byteArrayInputStream.available()) + i);
            } catch (IOException e) {
                throw new InvalidCipherTextException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to recover ephemeral public key: ")), e);
            } catch (IllegalArgumentException e2) {
                throw new InvalidCipherTextException(GeneratedOutlineSupport1.outline43(e2, GeneratedOutlineSupport1.outline107("unable to recover ephemeral public key: ")), e2);
            }
        }
        this.agree.init(this.privParam);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.agree.getFieldSize(), this.agree.calculateAgreement(this.pubParam));
        byte[] bArr2 = this.V;
        if (bArr2.length != 0) {
            byte[] concatenate = Arrays.concatenate(bArr2, asUnsignedByteArray);
            Arrays.fill(asUnsignedByteArray, (byte) 0);
            asUnsignedByteArray = concatenate;
        }
        try {
            this.kdf.init(new KDFParameters(asUnsignedByteArray, this.param.getDerivationV()));
            return this.forEncryption ? encryptBlock(bArr, i, i2) : decryptBlock(bArr, i, i2);
        } finally {
            Arrays.fill(asUnsignedByteArray, (byte) 0);
        }
    }
}
