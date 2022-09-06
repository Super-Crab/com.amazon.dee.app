package org.bouncycastle.openpgp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.bcpg.BCPGInputStream;
/* loaded from: classes5.dex */
class Util {
    Util() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCPGInputStream createBCPGInputStream(InputStream inputStream, int i) throws IOException {
        BCPGInputStream bCPGInputStream = new BCPGInputStream(inputStream);
        if (bCPGInputStream.nextPacketTag() == i) {
            return bCPGInputStream;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unexpected tag ");
        outline107.append(bCPGInputStream.nextPacketTag());
        outline107.append(" encountered");
        throw new IOException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCPGInputStream createBCPGInputStream(InputStream inputStream, int i, int i2) throws IOException {
        BCPGInputStream bCPGInputStream = new BCPGInputStream(inputStream);
        if (bCPGInputStream.nextPacketTag() == i || bCPGInputStream.nextPacketTag() == i2) {
            return bCPGInputStream;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unexpected tag ");
        outline107.append(bCPGInputStream.nextPacketTag());
        outline107.append(" encountered");
        throw new IOException(outline107.toString());
    }
}
