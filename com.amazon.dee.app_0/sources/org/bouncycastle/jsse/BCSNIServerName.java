package org.bouncycastle.jsse;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.tls.NameType;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes4.dex */
public abstract class BCSNIServerName {
    private final byte[] encoded;
    private final int nameType;

    /* JADX INFO: Access modifiers changed from: protected */
    public BCSNIServerName(int i, byte[] bArr) {
        if (TlsUtils.isValidUint8(i)) {
            if (bArr == null) {
                throw new NullPointerException("'encoded' cannot be null");
            }
            this.nameType = i;
            this.encoded = TlsUtils.clone(bArr);
            return;
        }
        throw new IllegalArgumentException("'nameType' should be between 0 and 255");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BCSNIServerName)) {
            return false;
        }
        BCSNIServerName bCSNIServerName = (BCSNIServerName) obj;
        return this.nameType == bCSNIServerName.nameType && Arrays.areEqual(this.encoded, bCSNIServerName.encoded);
    }

    public final byte[] getEncoded() {
        return TlsUtils.clone(this.encoded);
    }

    public final int getType() {
        return this.nameType;
    }

    public int hashCode() {
        return this.nameType ^ Arrays.hashCode(this.encoded);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("{type=");
        outline107.append(NameType.getText((short) this.nameType));
        outline107.append(", value=");
        outline107.append(Hex.toHexString(this.encoded));
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
