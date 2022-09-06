package org.bouncycastle.jcajce.provider.asymmetric.ec;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.bouncycastle.crypto.digests.Blake2sDigest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.WhirlpoolDigest;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.util.BadBlockException;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.ECKey;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class GMCipherSpi extends CipherSpi {
    private SM2Engine engine;
    private AsymmetricKeyParameter key;
    private SecureRandom random;
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private int state = -1;
    private ErasableOutputStream buffer = new ErasableOutputStream();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static final class ErasableOutputStream extends ByteArrayOutputStream {
        public void erase() {
            Arrays.fill(((ByteArrayOutputStream) this).buf, (byte) 0);
            reset();
        }

        public byte[] getBuf() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2 extends GMCipherSpi {
        public SM2() {
            super(new SM2Engine());
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withBlake2b extends GMCipherSpi {
        public SM2withBlake2b() {
            super(new SM2Engine(new Blake2bDigest(512)));
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withBlake2s extends GMCipherSpi {
        public SM2withBlake2s() {
            super(new SM2Engine(new Blake2sDigest(256)));
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withMD5 extends GMCipherSpi {
        public SM2withMD5() {
            super(new SM2Engine(new MD5Digest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withRMD extends GMCipherSpi {
        public SM2withRMD() {
            super(new SM2Engine(new RIPEMD160Digest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withSha1 extends GMCipherSpi {
        public SM2withSha1() {
            super(new SM2Engine(new SHA1Digest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withSha224 extends GMCipherSpi {
        public SM2withSha224() {
            super(new SM2Engine(new SHA224Digest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withSha256 extends GMCipherSpi {
        public SM2withSha256() {
            super(new SM2Engine(new SHA256Digest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withSha384 extends GMCipherSpi {
        public SM2withSha384() {
            super(new SM2Engine(new SHA384Digest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withSha512 extends GMCipherSpi {
        public SM2withSha512() {
            super(new SM2Engine(new SHA512Digest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class SM2withWhirlpool extends GMCipherSpi {
        public SM2withWhirlpool() {
            super(new SM2Engine(new WhirlpoolDigest()));
        }
    }

    public GMCipherSpi(SM2Engine sM2Engine) {
        this.engine = sM2Engine;
    }

    @Override // javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        byte[] engineDoFinal = engineDoFinal(bArr, i, i2);
        System.arraycopy(engineDoFinal, 0, bArr2, i3, engineDoFinal.length);
        return engineDoFinal.length;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        byte[] processBlock;
        if (i2 != 0) {
            this.buffer.write(bArr, i, i2);
        }
        try {
            try {
                if (this.state != 1 && this.state != 3) {
                    if (this.state != 2 && this.state != 4) {
                        throw new IllegalStateException("cipher not initialised");
                    }
                    try {
                        this.engine.init(false, this.key);
                        processBlock = this.engine.processBlock(this.buffer.getBuf(), 0, this.buffer.size());
                        return processBlock;
                    } catch (Exception e) {
                        throw new BadBlockException("unable to process block", e);
                    }
                }
                this.engine.init(true, new ParametersWithRandom(this.key, this.random));
                processBlock = this.engine.processBlock(this.buffer.getBuf(), 0, this.buffer.size());
                return processBlock;
            } catch (Exception e2) {
                throw new BadBlockException("unable to process block", e2);
            }
        } finally {
            this.buffer.erase();
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineGetIV() {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        if (key instanceof ECKey) {
            return ((ECKey) key).getParameters().getCurve().getFieldSize();
        }
        throw new IllegalArgumentException("not an EC key");
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetOutputSize(int i) {
        int i2 = this.state;
        if (i2 == 1 || i2 == 3 || i2 == 2 || i2 == 4) {
            return this.engine.getOutputSize(i);
        }
        throw new IllegalStateException("cipher not initialised");
    }

    @Override // javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (algorithmParameters == null) {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot recognise parameters: ");
        outline107.append(algorithmParameters.getClass().getName());
        throw new InvalidAlgorithmParameterException(outline107.toString());
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot handle supplied parameter spec: ");
            outline107.append(e.getMessage());
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException, InvalidKeyException {
        AsymmetricKeyParameter generatePublicKeyParameter;
        if (i == 1 || i == 3) {
            if (!(key instanceof PublicKey)) {
                throw new InvalidKeyException("must be passed public EC key for encryption");
            }
            generatePublicKeyParameter = ECUtils.generatePublicKeyParameter((PublicKey) key);
        } else if (i != 2 && i != 4) {
            throw new InvalidKeyException("must be passed EC key");
        } else {
            if (!(key instanceof PrivateKey)) {
                throw new InvalidKeyException("must be passed private EC key for decryption");
            }
            generatePublicKeyParameter = ECUtil.generatePrivateKeyParameter((PrivateKey) key);
        }
        this.key = generatePublicKeyParameter;
        if (secureRandom != null) {
            this.random = secureRandom;
        } else {
            this.random = CryptoServicesRegistrar.getSecureRandom();
        }
        this.state = i;
        this.buffer.reset();
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        if (Strings.toUpperCase(str).equals("NONE")) {
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("can't support mode ", str));
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        if (Strings.toUpperCase(str).equals("NOPADDING")) {
            return;
        }
        throw new NoSuchPaddingException("padding not available with IESCipher");
    }

    @Override // javax.crypto.CipherSpi
    public int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.buffer.write(bArr, i, i2);
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.buffer.write(bArr, i, i2);
        return null;
    }
}
