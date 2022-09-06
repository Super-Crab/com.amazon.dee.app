package org.bouncycastle.openssl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes5.dex */
public class PEMEncryptedKeyPair {
    private final String dekAlgName;
    private final byte[] iv;
    private final byte[] keyBytes;
    private final PEMKeyPairParser parser;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PEMEncryptedKeyPair(String str, byte[] bArr, byte[] bArr2, PEMKeyPairParser pEMKeyPairParser) {
        this.dekAlgName = str;
        this.iv = bArr;
        this.keyBytes = bArr2;
        this.parser = pEMKeyPairParser;
    }

    public PEMKeyPair decryptKeyPair(PEMDecryptorProvider pEMDecryptorProvider) throws IOException {
        try {
            return this.parser.parse(pEMDecryptorProvider.get(this.dekAlgName).decrypt(this.keyBytes, this.iv));
        } catch (IOException e) {
            throw e;
        } catch (OperatorCreationException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot create extraction operator: ");
            outline107.append(e2.getMessage());
            throw new PEMException(outline107.toString(), e2);
        } catch (Exception e3) {
            throw new PEMException(GeneratedOutlineSupport1.outline41(e3, GeneratedOutlineSupport1.outline107("exception processing key pair: ")), e3);
        }
    }

    public String getDekAlgName() {
        return this.dekAlgName;
    }
}
