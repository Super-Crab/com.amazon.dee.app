package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.ExhaustedPrivateKeyException;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSSignature;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class XMSSSigner implements StateAwareMessageSigner {
    private boolean hasGenerated;
    private boolean initSign;
    private KeyedHashFunctions khf;
    private XMSSParameters params;
    private XMSSPrivateKeyParameters privateKey;
    private XMSSPublicKeyParameters publicKey;
    private WOTSPlus wotsPlus;

    private WOTSPlusSignature wotsSign(byte[] bArr, OTSHashAddress oTSHashAddress) {
        if (bArr.length == this.params.getTreeDigestSize()) {
            if (oTSHashAddress == null) {
                throw new NullPointerException("otsHashAddress == null");
            }
            WOTSPlus wOTSPlus = this.wotsPlus;
            wOTSPlus.importKeys(wOTSPlus.getWOTSPlusSecretKey(this.privateKey.getSecretKeySeed(), oTSHashAddress), this.privateKey.getPublicSeed());
            return this.wotsPlus.sign(bArr, oTSHashAddress);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        byte[] byteArray;
        if (bArr != null) {
            if (!this.initSign) {
                throw new IllegalStateException("signer not initialized for signature generation");
            }
            XMSSPrivateKeyParameters xMSSPrivateKeyParameters = this.privateKey;
            if (xMSSPrivateKeyParameters == null) {
                throw new IllegalStateException("signing key no longer usable");
            }
            synchronized (xMSSPrivateKeyParameters) {
                if (this.privateKey.getUsagesRemaining() <= 0) {
                    throw new ExhaustedPrivateKeyException("no usages of private key remaining");
                }
                if (this.privateKey.getBDSState().getAuthenticationPath().isEmpty()) {
                    throw new IllegalStateException("not initialized");
                }
                int index = this.privateKey.getIndex();
                this.hasGenerated = true;
                long j = index;
                byte[] PRF = this.khf.PRF(this.privateKey.getSecretKeyPRF(), XMSSUtil.toBytesBigEndian(j, 32));
                byteArray = new XMSSSignature.Builder(this.params).withIndex(index).withRandom(PRF).withWOTSPlusSignature(wotsSign(this.khf.HMsg(Arrays.concatenate(PRF, this.privateKey.getRoot(), XMSSUtil.toBytesBigEndian(j, this.params.getTreeDigestSize())), bArr), (OTSHashAddress) new OTSHashAddress.Builder().withOTSAddress(index).build())).withAuthPath(this.privateKey.getBDSState().getAuthenticationPath()).mo12865build().toByteArray();
                this.privateKey.getBDSState().markUsed();
                this.privateKey.rollKey();
            }
            return byteArray;
        }
        throw new NullPointerException("message == null");
    }

    @Override // org.bouncycastle.pqc.crypto.StateAwareMessageSigner
    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        synchronized (this.privateKey) {
            if (this.hasGenerated) {
                XMSSPrivateKeyParameters xMSSPrivateKeyParameters = this.privateKey;
                this.privateKey = null;
                return xMSSPrivateKeyParameters;
            }
            XMSSPrivateKeyParameters xMSSPrivateKeyParameters2 = this.privateKey;
            if (xMSSPrivateKeyParameters2 != null) {
                this.privateKey = this.privateKey.getNextKey();
            }
            return xMSSPrivateKeyParameters2;
        }
    }

    public long getUsagesRemaining() {
        return this.privateKey.getUsagesRemaining();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        XMSSParameters parameters;
        if (z) {
            this.initSign = true;
            this.hasGenerated = false;
            this.privateKey = (XMSSPrivateKeyParameters) cipherParameters;
            parameters = this.privateKey.getParameters();
        } else {
            this.initSign = false;
            this.publicKey = (XMSSPublicKeyParameters) cipherParameters;
            parameters = this.publicKey.getParameters();
        }
        this.params = parameters;
        this.wotsPlus = this.params.getWOTSPlus();
        this.khf = this.wotsPlus.getKhf();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        XMSSSignature mo12865build = new XMSSSignature.Builder(this.params).withSignature(bArr2).mo12865build();
        int index = mo12865build.getIndex();
        this.wotsPlus.importKeys(new byte[this.params.getTreeDigestSize()], this.publicKey.getPublicSeed());
        long j = index;
        byte[] HMsg = this.khf.HMsg(Arrays.concatenate(mo12865build.getRandom(), this.publicKey.getRoot(), XMSSUtil.toBytesBigEndian(j, this.params.getTreeDigestSize())), bArr);
        int height = this.params.getHeight();
        return Arrays.constantTimeAreEqual(XMSSVerifierUtil.getRootNodeFromSignature(this.wotsPlus, height, HMsg, mo12865build, (OTSHashAddress) new OTSHashAddress.Builder().withOTSAddress(index).build(), XMSSUtil.getLeafIndex(j, height)).getValue(), this.publicKey.getRoot());
    }
}
