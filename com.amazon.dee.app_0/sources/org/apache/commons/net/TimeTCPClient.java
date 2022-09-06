package org.apache.commons.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes4.dex */
public final class TimeTCPClient extends SocketClient {
    public static final int DEFAULT_PORT = 37;
    public static final long SECONDS_1900_TO_1970 = 2208988800L;

    public TimeTCPClient() {
        setDefaultPort(37);
    }

    public Date getDate() throws IOException {
        return new Date((getTime() - 2208988800L) * 1000);
    }

    public long getTime() throws IOException {
        return new DataInputStream(this._input_).readInt() & BodyPartID.bodyIdMax;
    }
}
