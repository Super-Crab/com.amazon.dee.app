package org.bouncycastle.openpgp;

import java.io.IOException;
import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.bcpg.MarkerPacket;
import org.bouncycastle.bcpg.Packet;
/* loaded from: classes5.dex */
public class PGPMarker {
    private MarkerPacket p;

    public PGPMarker(BCPGInputStream bCPGInputStream) throws IOException {
        Packet readPacket = bCPGInputStream.readPacket();
        if (readPacket instanceof MarkerPacket) {
            this.p = (MarkerPacket) readPacket;
            return;
        }
        throw new IOException("unexpected packet in stream: " + readPacket);
    }
}
