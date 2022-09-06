package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KEKIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KEKRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.OperatorException;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.SymmetricKeyWrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public abstract class KEKRecipientInfoGenerator implements RecipientInfoGenerator {
    private final KEKIdentifier kekIdentifier;
    protected final SymmetricKeyWrapper wrapper;

    /* JADX INFO: Access modifiers changed from: protected */
    public KEKRecipientInfoGenerator(KEKIdentifier kEKIdentifier, SymmetricKeyWrapper symmetricKeyWrapper) {
        this.kekIdentifier = kEKIdentifier;
        this.wrapper = symmetricKeyWrapper;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInfoGenerator
    public final RecipientInfo generate(GenericKey genericKey) throws CMSException {
        try {
            return new RecipientInfo(new KEKRecipientInfo(this.kekIdentifier, this.wrapper.getAlgorithmIdentifier(), new DEROctetString(this.wrapper.generateWrappedKey(genericKey))));
        } catch (OperatorException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception wrapping content key: ");
            outline107.append(e.getMessage());
            throw new CMSException(outline107.toString(), e);
        }
    }
}
