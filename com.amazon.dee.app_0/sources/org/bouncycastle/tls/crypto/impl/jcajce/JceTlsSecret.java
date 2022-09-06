package org.bouncycastle.tls.crypto.impl.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.bouncycastle.tls.HashAlgorithm;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.impl.AbstractTlsCrypto;
import org.bouncycastle.tls.crypto.impl.AbstractTlsSecret;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes5.dex */
public class JceTlsSecret extends AbstractTlsSecret {
    private static final byte[] SSL3_CONST = generateSSL3Constants();
    protected final JcaTlsCrypto crypto;

    public JceTlsSecret(JcaTlsCrypto jcaTlsCrypto, byte[] bArr) {
        super(bArr);
        this.crypto = jcaTlsCrypto;
    }

    private static byte[] generateSSL3Constants() {
        byte[] bArr = new byte[120];
        int i = 0;
        int i2 = 0;
        while (i < 15) {
            byte b = (byte) (i + 65);
            int i3 = i2;
            int i4 = 0;
            while (i4 <= i) {
                bArr[i3] = b;
                i4++;
                i3++;
            }
            i++;
            i2 = i3;
        }
        return bArr;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized TlsSecret deriveUsingPRF(int i, String str, byte[] bArr, int i2) {
        checkAlive();
        try {
            if (i == 4) {
                return TlsCryptoUtils.hkdfExpandLabel(this, (short) 4, str, bArr, i2);
            } else if (i != 5) {
                return this.crypto.adoptLocalSecret(prf(i, str, bArr, i2));
            } else {
                return TlsCryptoUtils.hkdfExpandLabel(this, (short) 5, str, bArr, i2);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.AbstractTlsSecret
    protected AbstractTlsCrypto getCrypto() {
        return this.crypto;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized TlsSecret hkdfExpand(short s, byte[] bArr, int i) {
        if (i < 1) {
            return this.crypto.adoptLocalSecret(TlsUtils.EMPTY_BYTES);
        }
        int outputSize = HashAlgorithm.getOutputSize(s);
        if (i > outputSize * 255) {
            throw new IllegalArgumentException("'length' must be <= 255 * (output size of 'hashAlgorithm')");
        }
        checkAlive();
        byte[] bArr2 = this.data;
        try {
            String hMACAlgorithmName = this.crypto.getHMACAlgorithmName(s);
            Mac createMac = this.crypto.getHelper().createMac(hMACAlgorithmName);
            createMac.init(new SecretKeySpec(bArr2, 0, bArr2.length, hMACAlgorithmName));
            byte[] bArr3 = new byte[i];
            byte[] bArr4 = new byte[outputSize];
            byte b = 0;
            int i2 = 0;
            while (true) {
                createMac.update(bArr, 0, bArr.length);
                b = (byte) (b + 1);
                createMac.update(b);
                createMac.doFinal(bArr4, 0);
                int i3 = i - i2;
                if (i3 <= outputSize) {
                    System.arraycopy(bArr4, 0, bArr3, i2, i3);
                    return this.crypto.adoptLocalSecret(bArr3);
                }
                System.arraycopy(bArr4, 0, bArr3, i2, outputSize);
                i2 += outputSize;
                createMac.update(bArr4, 0, bArr4.length);
            }
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized TlsSecret hkdfExtract(short s, byte[] bArr) {
        Mac createMac;
        checkAlive();
        byte[] bArr2 = this.data;
        this.data = null;
        try {
            String hMACAlgorithmName = this.crypto.getHMACAlgorithmName(s);
            createMac = this.crypto.getHelper().createMac(hMACAlgorithmName);
            createMac.init(new SecretKeySpec(bArr2, 0, bArr2.length, hMACAlgorithmName));
            createMac.update(bArr, 0, bArr.length);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        return this.crypto.adoptLocalSecret(createMac.doFinal());
    }

    protected void hmacHash(String str, byte[] bArr, int i, int i2, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        String outline72 = GeneratedOutlineSupport1.outline72("Hmac", str);
        Mac createMac = this.crypto.getHelper().createMac(outline72);
        createMac.init(new SecretKeySpec(bArr, i, i2, outline72));
        int macLength = createMac.getMacLength();
        byte[] bArr4 = new byte[macLength];
        byte[] bArr5 = new byte[macLength];
        int i3 = 0;
        byte[] bArr6 = bArr2;
        while (i3 < bArr3.length) {
            createMac.update(bArr6, 0, bArr6.length);
            createMac.doFinal(bArr4, 0);
            createMac.update(bArr4, 0, bArr4.length);
            createMac.update(bArr2, 0, bArr2.length);
            createMac.doFinal(bArr5, 0);
            System.arraycopy(bArr5, 0, bArr3, i3, Math.min(macLength, bArr3.length - i3));
            i3 += macLength;
            bArr6 = bArr4;
        }
    }

    protected byte[] prf(int i, String str, byte[] bArr, int i2) throws GeneralSecurityException {
        if (i == 0) {
            return prf_SSL(bArr, i2);
        }
        byte[] concatenate = Arrays.concatenate(Strings.toByteArray(str), bArr);
        return 1 == i ? prf_1_0(concatenate, i2) : prf_1_2(i, concatenate, i2);
    }

    protected byte[] prf_1_0(byte[] bArr, int i) throws GeneralSecurityException {
        byte[] bArr2 = this.data;
        int length = (bArr2.length + 1) / 2;
        byte[] bArr3 = new byte[i];
        hmacHash(MessageDigestAlgorithms.MD5, bArr2, 0, length, bArr, bArr3);
        byte[] bArr4 = new byte[i];
        byte[] bArr5 = this.data;
        hmacHash("SHA1", bArr5, bArr5.length - length, length, bArr, bArr4);
        for (int i2 = 0; i2 < i; i2++) {
            bArr3[i2] = (byte) (bArr3[i2] ^ bArr4[i2]);
        }
        return bArr3;
    }

    protected byte[] prf_1_2(int i, byte[] bArr, int i2) throws GeneralSecurityException {
        String replaceAll = this.crypto.getDigestName(TlsUtils.getHashAlgorithmForPRFAlgorithm(i)).replaceAll(ProcessIdUtil.DEFAULT_PROCESSID, "");
        byte[] bArr2 = new byte[i2];
        byte[] bArr3 = this.data;
        hmacHash(replaceAll, bArr3, 0, bArr3.length, bArr, bArr2);
        return bArr2;
    }

    protected byte[] prf_SSL(byte[] bArr, int i) throws GeneralSecurityException {
        MessageDigest createDigest = this.crypto.getHelper().createDigest(MessageDigestAlgorithms.MD5);
        MessageDigest createDigest2 = this.crypto.getHelper().createDigest("SHA-1");
        int digestLength = createDigest.getDigestLength();
        int digestLength2 = createDigest2.getDigestLength();
        byte[] bArr2 = new byte[Math.max(digestLength, digestLength2)];
        byte[] bArr3 = new byte[i];
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            createDigest2.update(SSL3_CONST, i4, i2);
            int i5 = i2 + 1;
            i4 += i2;
            byte[] bArr4 = this.data;
            createDigest2.update(bArr4, 0, bArr4.length);
            createDigest2.update(bArr, 0, bArr.length);
            createDigest2.digest(bArr2, 0, digestLength2);
            byte[] bArr5 = this.data;
            createDigest.update(bArr5, 0, bArr5.length);
            createDigest.update(bArr2, 0, digestLength2);
            int i6 = i - i3;
            if (i6 < digestLength) {
                createDigest.digest(bArr2, 0, digestLength);
                System.arraycopy(bArr2, 0, bArr3, i3, i6);
                i3 += i6;
            } else {
                createDigest.digest(bArr3, i3, digestLength);
                i3 += digestLength;
            }
            i2 = i5;
        }
        return bArr3;
    }
}
