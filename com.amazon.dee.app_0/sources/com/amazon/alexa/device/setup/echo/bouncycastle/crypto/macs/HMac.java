package com.amazon.alexa.device.setup.echo.bouncycastle.crypto.macs;

import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.CipherParameters;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Digest;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.ExtendedDigest;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Mac;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.params.KeyParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Hashtable;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
/* loaded from: classes.dex */
public class HMac implements Mac {
    private static final byte IPAD = 54;
    private static final byte OPAD = 92;
    private static final Hashtable blockLengths = new Hashtable();
    private final int blockLength;
    private final Digest digest;
    private final int digestSize;
    private final byte[] inputPad;
    private byte[] outputPad;

    static {
        blockLengths.put("GOST3411", new Integer(32));
        blockLengths.put(MessageDigestAlgorithms.MD2, new Integer(16));
        blockLengths.put("MD4", new Integer(64));
        blockLengths.put(MessageDigestAlgorithms.MD5, new Integer(64));
        blockLengths.put("RIPEMD128", new Integer(64));
        blockLengths.put("RIPEMD160", new Integer(64));
        blockLengths.put("SHA-1", new Integer(64));
        blockLengths.put(McElieceCCA2KeyGenParameterSpec.SHA224, new Integer(64));
        blockLengths.put("SHA-256", new Integer(64));
        blockLengths.put("SHA-384", new Integer(128));
        blockLengths.put("SHA-512", new Integer(128));
        blockLengths.put("Tiger", new Integer(64));
        blockLengths.put("Whirlpool", new Integer(64));
    }

    public HMac(Digest digest) {
        this(digest, getByteLength(digest));
    }

    private static int getByteLength(Digest digest) {
        if (digest instanceof ExtendedDigest) {
            return ((ExtendedDigest) digest).getByteLength();
        }
        Integer num = (Integer) blockLengths.get(digest.getAlgorithmName());
        if (num != null) {
            return num.intValue();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown digest passed: ");
        outline107.append(digest.getAlgorithmName());
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.digestSize];
        this.digest.doFinal(bArr2, 0);
        Digest digest = this.digest;
        byte[] bArr3 = this.outputPad;
        digest.update(bArr3, 0, bArr3.length);
        this.digest.update(bArr2, 0, bArr2.length);
        int doFinal = this.digest.doFinal(bArr, i);
        reset();
        return doFinal;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "/HMAC";
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.digestSize;
    }

    public Digest getUnderlyingDigest() {
        return this.digest;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        this.digest.reset();
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length > this.blockLength) {
            this.digest.update(key, 0, key.length);
            this.digest.doFinal(this.inputPad, 0);
            int i = this.digestSize;
            while (true) {
                byte[] bArr = this.inputPad;
                if (i >= bArr.length) {
                    break;
                }
                bArr[i] = 0;
                i++;
            }
        } else {
            System.arraycopy(key, 0, this.inputPad, 0, key.length);
            int length = key.length;
            while (true) {
                byte[] bArr2 = this.inputPad;
                if (length >= bArr2.length) {
                    break;
                }
                bArr2[length] = 0;
                length++;
            }
        }
        byte[] bArr3 = this.inputPad;
        this.outputPad = new byte[bArr3.length];
        System.arraycopy(bArr3, 0, this.outputPad, 0, bArr3.length);
        int i2 = 0;
        while (true) {
            byte[] bArr4 = this.inputPad;
            if (i2 >= bArr4.length) {
                break;
            }
            bArr4[i2] = (byte) (bArr4[i2] ^ IPAD);
            i2++;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr5 = this.outputPad;
            if (i3 < bArr5.length) {
                bArr5[i3] = (byte) (bArr5[i3] ^ OPAD);
                i3++;
            } else {
                Digest digest = this.digest;
                byte[] bArr6 = this.inputPad;
                digest.update(bArr6, 0, bArr6.length);
                return;
            }
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Mac
    public void reset() {
        this.digest.reset();
        Digest digest = this.digest;
        byte[] bArr = this.inputPad;
        digest.update(bArr, 0, bArr.length);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Mac
    public void update(byte b) {
        this.digest.update(b);
    }

    private HMac(Digest digest, int i) {
        this.digest = digest;
        this.digestSize = digest.getDigestSize();
        this.blockLength = i;
        int i2 = this.blockLength;
        this.inputPad = new byte[i2];
        this.outputPad = new byte[i2];
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
    }
}
