package org.bouncycastle.operator.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
/* loaded from: classes5.dex */
public class BcSymmetricKeyUnwrapper extends SymmetricKeyUnwrapper {
    private SecureRandom random;
    private Wrapper wrapper;
    private KeyParameter wrappingKey;

    public BcSymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, Wrapper wrapper, KeyParameter keyParameter) {
        super(algorithmIdentifier);
        this.wrapper = wrapper;
        this.wrappingKey = keyParameter;
    }

    @Override // org.bouncycastle.operator.KeyUnwrapper
    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) throws OperatorException {
        this.wrapper.init(false, this.wrappingKey);
        try {
            return new GenericKey(algorithmIdentifier, this.wrapper.unwrap(bArr, 0, bArr.length));
        } catch (InvalidCipherTextException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unable to unwrap key: ");
            outline107.append(e.getMessage());
            throw new OperatorException(outline107.toString(), e);
        }
    }

    public BcSymmetricKeyUnwrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
