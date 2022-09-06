package org.bouncycastle.cert.cmp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cmp.PKIBody;
import org.bouncycastle.asn1.cmp.PKIHeader;
import org.bouncycastle.asn1.cmp.PKIMessage;
import org.bouncycastle.cert.CertIOException;
/* loaded from: classes4.dex */
public class GeneralPKIMessage {
    private final PKIMessage pkiMessage;

    public GeneralPKIMessage(PKIMessage pKIMessage) {
        this.pkiMessage = pKIMessage;
    }

    public GeneralPKIMessage(byte[] bArr) throws IOException {
        this(parseBytes(bArr));
    }

    private static PKIMessage parseBytes(byte[] bArr) throws IOException {
        try {
            return PKIMessage.getInstance(ASN1Primitive.fromByteArray(bArr));
        } catch (ClassCastException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("malformed data: ");
            outline107.append(e.getMessage());
            throw new CertIOException(outline107.toString(), e);
        } catch (IllegalArgumentException e2) {
            throw new CertIOException(GeneratedOutlineSupport1.outline43(e2, GeneratedOutlineSupport1.outline107("malformed data: ")), e2);
        }
    }

    public PKIBody getBody() {
        return this.pkiMessage.getBody();
    }

    public PKIHeader getHeader() {
        return this.pkiMessage.getHeader();
    }

    public boolean hasProtection() {
        return this.pkiMessage.getHeader().getProtectionAlg() != null;
    }

    public PKIMessage toASN1Structure() {
        return this.pkiMessage;
    }
}
