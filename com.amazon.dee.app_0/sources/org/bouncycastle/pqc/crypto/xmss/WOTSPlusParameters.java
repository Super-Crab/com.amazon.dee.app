package org.bouncycastle.pqc.crypto.xmss;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.Digest;
/* loaded from: classes5.dex */
final class WOTSPlusParameters {
    private final int digestSize;
    private final int len;
    private final int len1;
    private final int len2;
    private final XMSSOid oid;
    private final ASN1ObjectIdentifier treeDigest;
    private final int winternitzParameter;

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier != null) {
            this.treeDigest = aSN1ObjectIdentifier;
            Digest digest = DigestUtil.getDigest(aSN1ObjectIdentifier);
            this.digestSize = XMSSUtil.getDigestSize(digest);
            this.winternitzParameter = 16;
            this.len1 = (int) Math.ceil((this.digestSize * 8) / XMSSUtil.log2(this.winternitzParameter));
            this.len2 = ((int) Math.floor(XMSSUtil.log2((this.winternitzParameter - 1) * this.len1) / XMSSUtil.log2(this.winternitzParameter))) + 1;
            this.len = this.len1 + this.len2;
            this.oid = WOTSPlusOid.lookup(digest.getAlgorithmName(), this.digestSize, this.winternitzParameter, this.len);
            if (this.oid != null) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot find OID for digest algorithm: ");
            outline107.append(digest.getAlgorithmName());
            throw new IllegalArgumentException(outline107.toString());
        }
        throw new NullPointerException("treeDigest == null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLen() {
        return this.len;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLen1() {
        return this.len1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLen2() {
        return this.len2;
    }

    protected XMSSOid getOid() {
        return this.oid;
    }

    public ASN1ObjectIdentifier getTreeDigest() {
        return this.treeDigest;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getTreeDigestSize() {
        return this.digestSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getWinternitzParameter() {
        return this.winternitzParameter;
    }
}
