package org.bouncycastle.tls;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
/* loaded from: classes5.dex */
public class OfferedPsks {
    protected Vector binders;
    protected Vector identities;

    public OfferedPsks(Vector vector, Vector vector2) {
        if (vector == null || vector.isEmpty()) {
            throw new IllegalArgumentException("'identities' cannot be null or empty");
        }
        if (vector2 == null || vector.size() != vector2.size()) {
            throw new IllegalArgumentException("'binders' must be non-null and the same length as 'identities'");
        }
        this.identities = vector;
        this.binders = vector2;
    }

    public static OfferedPsks parse(InputStream inputStream) throws IOException {
        Vector vector = new Vector();
        int readUint16 = TlsUtils.readUint16(inputStream);
        if (readUint16 >= 7) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(readUint16, inputStream));
            do {
                vector.add(PskIdentity.parse(byteArrayInputStream));
            } while (byteArrayInputStream.available() > 0);
            Vector vector2 = new Vector();
            int readUint162 = TlsUtils.readUint16(inputStream);
            if (readUint162 < 33) {
                throw new TlsFatalAlert((short) 50);
            }
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(TlsUtils.readFully(readUint162, inputStream));
            do {
                vector2.add(TlsUtils.readOpaque8(inputStream, 32));
            } while (byteArrayInputStream2.available() > 0);
            return new OfferedPsks(vector, vector2);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public void encode(OutputStream outputStream) throws IOException {
        int i = 0;
        for (int i2 = 0; i2 < this.identities.size(); i2++) {
            i = GeneratedOutlineSupport1.outline1(((PskIdentity) this.identities.elementAt(i2)).getIdentity().length, 2, 4, i);
        }
        TlsUtils.checkUint16(i);
        TlsUtils.writeUint16(i, outputStream);
        for (int i3 = 0; i3 < this.identities.size(); i3++) {
            ((PskIdentity) this.identities.elementAt(i3)).encode(outputStream);
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.binders.size(); i5++) {
            i4 += ((byte[]) this.binders.elementAt(i5)).length + 1;
        }
        TlsUtils.checkUint16(i4);
        TlsUtils.writeUint16(i4, outputStream);
        for (int i6 = 0; i6 < this.binders.size(); i6++) {
            TlsUtils.writeOpaque8((byte[]) this.binders.elementAt(i6), outputStream);
        }
    }

    public Vector getBinders() {
        return this.binders;
    }

    public Vector getIdentities() {
        return this.identities;
    }
}
