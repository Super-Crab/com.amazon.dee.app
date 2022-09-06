package org.bouncycastle.cms;

import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes4.dex */
public interface SignerInformationVerifierProvider {
    SignerInformationVerifier get(SignerId signerId) throws OperatorCreationException;
}
