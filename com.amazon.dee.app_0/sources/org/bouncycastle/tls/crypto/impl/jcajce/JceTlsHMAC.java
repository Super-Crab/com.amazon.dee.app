package org.bouncycastle.tls.crypto.impl.jcajce;

import com.amazonaws.services.s3.internal.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidKeyException;
import java.util.Hashtable;
import javax.crypto.Mac;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.util.Integers;
/* loaded from: classes5.dex */
public class JceTlsHMAC implements TlsHMAC {
    private static final Hashtable internalBlockSizes = new Hashtable();
    private final String algorithm;
    private final Mac hmac;
    private final Integer internalBlockSize;

    static {
        internalBlockSizes.put("HmacMD5", Integers.valueOf(64));
        internalBlockSizes.put(Constants.HMAC_SHA1_ALGORITHM, Integers.valueOf(64));
        internalBlockSizes.put("HmacSHA256", Integers.valueOf(64));
        internalBlockSizes.put("HmacSHA384", Integers.valueOf(128));
        internalBlockSizes.put("HmacSHA512", Integers.valueOf(128));
    }

    public JceTlsHMAC(Mac mac, String str) {
        this(mac, str, getInternalBlockSize(str));
    }

    public JceTlsHMAC(Mac mac, String str, int i) {
        this.hmac = mac;
        this.algorithm = str;
        this.internalBlockSize = Integers.valueOf(i);
    }

    private static int getInternalBlockSize(String str) {
        if (internalBlockSizes.containsKey(str)) {
            return ((Integer) internalBlockSizes.get(str)).intValue();
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("HMAC ", str, " unknown"));
    }

    @Override // org.bouncycastle.tls.crypto.TlsMAC
    public void calculateMAC(byte[] bArr, int i) {
        try {
            this.hmac.doFinal(bArr, i);
        } catch (ShortBufferException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsMAC
    public byte[] calculateMAC() {
        return this.hmac.doFinal();
    }

    @Override // org.bouncycastle.tls.crypto.TlsHMAC
    public int getInternalBlockSize() {
        return this.internalBlockSize.intValue();
    }

    @Override // org.bouncycastle.tls.crypto.TlsMAC
    public int getMacLength() {
        return this.hmac.getMacLength();
    }

    @Override // org.bouncycastle.tls.crypto.TlsMAC
    public void reset() {
        this.hmac.reset();
    }

    @Override // org.bouncycastle.tls.crypto.TlsMAC
    public void setKey(byte[] bArr, int i, int i2) {
        try {
            this.hmac.init(new SecretKeySpec(bArr, i, i2, this.algorithm));
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsMAC
    public void update(byte[] bArr, int i, int i2) {
        this.hmac.update(bArr, i, i2);
    }
}
