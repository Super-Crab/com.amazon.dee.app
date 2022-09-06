package org.bouncycastle.cms.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipient;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.bc.BcRSAAsymmetricKeyUnwrapper;
/* loaded from: classes4.dex */
public abstract class BcKeyTransRecipient implements KeyTransRecipient {
    private AsymmetricKeyParameter recipientKey;

    public BcKeyTransRecipient(AsymmetricKeyParameter asymmetricKeyParameter) {
        this.recipientKey = asymmetricKeyParameter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CipherParameters extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CMSException {
        try {
            return CMSUtils.getBcKey(new BcRSAAsymmetricKeyUnwrapper(algorithmIdentifier, this.recipientKey).generateUnwrappedKey(algorithmIdentifier2, bArr));
        } catch (OperatorException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception unwrapping key: ");
            outline107.append(e.getMessage());
            throw new CMSException(outline107.toString(), e);
        }
    }
}
