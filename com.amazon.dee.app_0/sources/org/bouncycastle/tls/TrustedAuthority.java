package org.bouncycastle.tls;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class TrustedAuthority {
    protected Object identifier;
    protected short identifierType;

    public TrustedAuthority(short s, Object obj) {
        if (isCorrectType(s, obj)) {
            this.identifierType = s;
            this.identifier = obj;
            return;
        }
        throw new IllegalArgumentException("'identifier' is not an instance of the correct type");
    }

    protected static boolean isCorrectType(short s, Object obj) {
        if (s == 0) {
            return obj == null;
        }
        if (s != 1) {
            if (s == 2) {
                return obj instanceof X500Name;
            }
            if (s != 3) {
                throw new IllegalArgumentException("'identifierType' is an unsupported IdentifierType");
            }
        }
        return isSHA1Hash(obj);
    }

    protected static boolean isSHA1Hash(Object obj) {
        return (obj instanceof byte[]) && ((byte[]) obj).length == 20;
    }

    public static TrustedAuthority parse(InputStream inputStream) throws IOException {
        Object obj;
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (readUint8 != 0) {
            if (readUint8 != 1) {
                if (readUint8 == 2) {
                    obj = X500Name.getInstance(TlsUtils.readDERObject(TlsUtils.readOpaque16(inputStream, 1)));
                } else if (readUint8 != 3) {
                    throw new TlsFatalAlert((short) 50);
                }
            }
            obj = TlsUtils.readFully(20, inputStream);
        } else {
            obj = null;
        }
        return new TrustedAuthority(readUint8, obj);
    }

    protected void checkCorrectType(short s) {
        if (this.identifierType != s || !isCorrectType(s, this.identifier)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TrustedAuthority is not of type ");
            outline107.append(IdentifierType.getName(s));
            throw new IllegalStateException(outline107.toString());
        }
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.identifierType, outputStream);
        short s = this.identifierType;
        if (s != 0) {
            if (s != 1) {
                if (s == 2) {
                    TlsUtils.writeOpaque16(((X500Name) this.identifier).getEncoded("DER"), outputStream);
                    return;
                } else if (s != 3) {
                    throw new TlsFatalAlert((short) 80);
                }
            }
            outputStream.write((byte[]) this.identifier);
        }
    }

    public byte[] getCertSHA1Hash() {
        return Arrays.clone((byte[]) this.identifier);
    }

    public Object getIdentifier() {
        return this.identifier;
    }

    public short getIdentifierType() {
        return this.identifierType;
    }

    public byte[] getKeySHA1Hash() {
        return Arrays.clone((byte[]) this.identifier);
    }

    public X500Name getX509Name() {
        checkCorrectType((short) 2);
        return (X500Name) this.identifier;
    }
}
