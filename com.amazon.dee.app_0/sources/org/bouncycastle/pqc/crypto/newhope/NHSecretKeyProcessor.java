package org.bouncycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.ExchangePair;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class NHSecretKeyProcessor {
    private final Xof xof;

    /* loaded from: classes5.dex */
    public static class PartyUBuilder {
        private final AsymmetricCipherKeyPair aKp;
        private final NHAgreement agreement = new NHAgreement();
        private byte[] sharedInfo = null;
        private boolean used = false;

        public PartyUBuilder(SecureRandom secureRandom) {
            NHKeyPairGenerator nHKeyPairGenerator = new NHKeyPairGenerator();
            nHKeyPairGenerator.init(new KeyGenerationParameters(secureRandom, 2048));
            this.aKp = nHKeyPairGenerator.generateKeyPair();
            this.agreement.init(this.aKp.getPrivate());
        }

        public NHSecretKeyProcessor build(byte[] bArr) {
            if (!this.used) {
                this.used = true;
                return new NHSecretKeyProcessor(this.agreement.calculateAgreement(new NHPublicKeyParameters(bArr)), this.sharedInfo);
            }
            throw new IllegalStateException("builder already used");
        }

        public byte[] getPartA() {
            return ((NHPublicKeyParameters) this.aKp.getPublic()).getPubData();
        }

        public PartyUBuilder withSharedInfo(byte[] bArr) {
            this.sharedInfo = Arrays.clone(bArr);
            return this;
        }
    }

    /* loaded from: classes5.dex */
    public static class PartyVBuilder {
        protected final SecureRandom random;
        private byte[] sharedInfo = null;
        private byte[] sharedSecret = null;
        private boolean used = false;

        public PartyVBuilder(SecureRandom secureRandom) {
            this.random = secureRandom;
        }

        public NHSecretKeyProcessor build() {
            if (!this.used) {
                this.used = true;
                return new NHSecretKeyProcessor(this.sharedSecret, this.sharedInfo);
            }
            throw new IllegalStateException("builder already used");
        }

        public byte[] getPartB(byte[] bArr) {
            ExchangePair generateExchange = new NHExchangePairGenerator(this.random).generateExchange(new NHPublicKeyParameters(bArr));
            this.sharedSecret = generateExchange.getSharedValue();
            return ((NHPublicKeyParameters) generateExchange.getPublicKey()).getPubData();
        }

        public PartyVBuilder withSharedInfo(byte[] bArr) {
            this.sharedInfo = Arrays.clone(bArr);
            return this;
        }
    }

    private NHSecretKeyProcessor(byte[] bArr, byte[] bArr2) {
        this.xof = new SHAKEDigest(256);
        this.xof.update(bArr, 0, bArr.length);
        if (bArr2 != null) {
            this.xof.update(bArr2, 0, bArr2.length);
        }
        Arrays.fill(bArr, (byte) 0);
    }

    private static void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    public byte[] processKey(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        this.xof.doFinal(bArr2, 0, bArr2.length);
        xor(bArr, bArr2);
        Arrays.fill(bArr2, (byte) 0);
        return bArr;
    }
}
