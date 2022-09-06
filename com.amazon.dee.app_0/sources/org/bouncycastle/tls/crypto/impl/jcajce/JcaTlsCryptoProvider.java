package org.bouncycastle.tls.crypto.impl.jcajce;

import com.amazon.alexa.redesign.view.carousel.ChipIconTitleViewHolder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.DigestException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.tls.crypto.TlsCryptoProvider;
/* loaded from: classes5.dex */
public class JcaTlsCryptoProvider implements TlsCryptoProvider {
    private JcaJceHelper helper = new DefaultJcaJceHelper();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class NonceEntropySource extends SecureRandom {

        /* loaded from: classes5.dex */
        private static class NonceEntropySourceSpi extends SecureRandomSpi {
            private final MessageDigest digest;
            private final byte[] seed;
            private final SecureRandom source;
            private final byte[] state;

            NonceEntropySourceSpi(SecureRandom secureRandom, MessageDigest messageDigest) {
                this.source = secureRandom;
                this.digest = messageDigest;
                this.seed = secureRandom.generateSeed(messageDigest.getDigestLength());
                this.state = new byte[this.seed.length];
            }

            private void runDigest(byte[] bArr, byte[] bArr2, byte[] bArr3) {
                this.digest.update(bArr);
                this.digest.update(bArr2);
                try {
                    this.digest.digest(bArr3, 0, bArr3.length);
                } catch (DigestException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unable to generate nonce data: ");
                    outline107.append(e.getMessage());
                    throw Exceptions.illegalStateException(outline107.toString(), e);
                }
            }

            @Override // java.security.SecureRandomSpi
            protected byte[] engineGenerateSeed(int i) {
                return this.source.generateSeed(i);
            }

            @Override // java.security.SecureRandomSpi
            protected void engineNextBytes(byte[] bArr) {
                synchronized (this.digest) {
                    int length = this.state.length;
                    int i = 0;
                    while (i != bArr.length) {
                        if (length == this.state.length) {
                            this.source.nextBytes(this.state);
                            runDigest(this.seed, this.state, this.state);
                            length = 0;
                        }
                        bArr[i] = this.state[length];
                        i++;
                        length++;
                    }
                }
            }

            @Override // java.security.SecureRandomSpi
            protected void engineSetSeed(byte[] bArr) {
                synchronized (this.digest) {
                    runDigest(this.seed, bArr, this.seed);
                }
            }
        }

        NonceEntropySource(JcaJceHelper jcaJceHelper, SecureRandom secureRandom) throws GeneralSecurityException {
            super(new NonceEntropySourceSpi(secureRandom, jcaJceHelper.createDigest("SHA-512")), secureRandom.getProvider());
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCryptoProvider
    /* renamed from: create  reason: collision with other method in class */
    public JcaTlsCrypto mo12866create(SecureRandom secureRandom) {
        if (secureRandom == null) {
            try {
                secureRandom = this.helper instanceof DefaultJcaJceHelper ? SecureRandom.getInstance(ChipIconTitleViewHolder.STATE_DEFAULT) : SecureRandom.getInstance(ChipIconTitleViewHolder.STATE_DEFAULT, this.helper.createDigest("SHA-512").getProvider());
            } catch (GeneralSecurityException e) {
                throw Exceptions.illegalStateException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("unable to create JcaTlsCrypto: ")), e);
            }
        }
        return mo12867create(secureRandom, (SecureRandom) new NonceEntropySource(this.helper, secureRandom));
    }

    @Override // org.bouncycastle.tls.crypto.TlsCryptoProvider
    /* renamed from: create  reason: collision with other method in class */
    public JcaTlsCrypto mo12867create(SecureRandom secureRandom, SecureRandom secureRandom2) {
        return new JcaTlsCrypto(this.helper, secureRandom, secureRandom2);
    }

    public JcaJceHelper getHelper() {
        return this.helper;
    }

    public JcaTlsCryptoProvider setProvider(String str) {
        this.helper = new NamedJcaJceHelper(str);
        return this;
    }

    public JcaTlsCryptoProvider setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }
}
