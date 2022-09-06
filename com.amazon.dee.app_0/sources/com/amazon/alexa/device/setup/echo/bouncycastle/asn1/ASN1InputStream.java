package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import com.amazon.alexa.device.setup.echo.bouncycastle.util.io.Streams;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class ASN1InputStream extends FilterInputStream implements BERTags {
    private final boolean lazyEvaluate;
    private final int limit;
    private final byte[][] tmpBuffers;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Primitive createPrimitiveDERObject(int i, DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        switch (i) {
            case 1:
                return DERBoolean.fromOctetString(getBuffer(definiteLengthInputStream, bArr));
            case 2:
                return new ASN1Integer(definiteLengthInputStream.toByteArray());
            case 3:
                return DERBitString.fromInputStream(definiteLengthInputStream.getRemaining(), definiteLengthInputStream);
            case 4:
                return new DEROctetString(definiteLengthInputStream.toByteArray());
            case 5:
                return DERNull.INSTANCE;
            case 6:
                return DERObjectIdentifier.fromOctetString(getBuffer(definiteLengthInputStream, bArr));
            case 7:
            case 8:
            case 9:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 21:
            case 25:
            case 29:
            default:
                throw new IOException(GeneratedOutlineSupport1.outline52("unknown tag ", i, " encountered"));
            case 10:
                return DEREnumerated.fromOctetString(getBuffer(definiteLengthInputStream, bArr));
            case 12:
                return new DERUTF8String(definiteLengthInputStream.toByteArray());
            case 18:
                return new DERNumericString(definiteLengthInputStream.toByteArray());
            case 19:
                return new DERPrintableString(definiteLengthInputStream.toByteArray());
            case 20:
                return new DERT61String(definiteLengthInputStream.toByteArray());
            case 22:
                return new DERIA5String(definiteLengthInputStream.toByteArray());
            case 23:
                return new ASN1UTCTime(definiteLengthInputStream.toByteArray());
            case 24:
                return new ASN1GeneralizedTime(definiteLengthInputStream.toByteArray());
            case 26:
                return new DERVisibleString(definiteLengthInputStream.toByteArray());
            case 27:
                return new DERGeneralString(definiteLengthInputStream.toByteArray());
            case 28:
                return new DERUniversalString(definiteLengthInputStream.toByteArray());
            case 30:
                return new DERBMPString(getBMPCharBuffer(definiteLengthInputStream));
        }
    }

    private static char[] getBMPCharBuffer(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        int read;
        int remaining = definiteLengthInputStream.getRemaining() / 2;
        char[] cArr = new char[remaining];
        for (int i = 0; i < remaining; i++) {
            int read2 = definiteLengthInputStream.read();
            if (read2 < 0 || (read = definiteLengthInputStream.read()) < 0) {
                break;
            }
            cArr[i] = (char) ((read2 << 8) | (read & 255));
        }
        return cArr;
    }

    private static byte[] getBuffer(DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        int remaining = definiteLengthInputStream.getRemaining();
        if (definiteLengthInputStream.getRemaining() < bArr.length) {
            byte[] bArr2 = bArr[remaining];
            if (bArr2 == null) {
                bArr2 = new byte[remaining];
                bArr[remaining] = bArr2;
            }
            Streams.readFully(definiteLengthInputStream, bArr2);
            return bArr2;
        }
        return definiteLengthInputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readLength(InputStream inputStream, int i) throws IOException {
        int read = inputStream.read();
        if (read >= 0) {
            if (read == 128) {
                return -1;
            }
            if (read <= 127) {
                return read;
            }
            int i2 = read & 127;
            if (i2 > 4) {
                throw new IOException(GeneratedOutlineSupport1.outline49("DER length more than 4 bytes: ", i2));
            }
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                int read2 = inputStream.read();
                if (read2 < 0) {
                    throw new EOFException("EOF found reading length");
                }
                i3 = (i3 << 8) + read2;
            }
            if (i3 < 0) {
                throw new IOException("corrupted stream - negative length found");
            }
            if (i3 >= i) {
                throw new IOException("corrupted stream - out of bounds length found");
            }
            return i3;
        }
        throw new EOFException("EOF found when length expected");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readTagNumber(InputStream inputStream, int i) throws IOException {
        int i2 = i & 31;
        if (i2 == 31) {
            int i3 = 0;
            int read = inputStream.read();
            if ((read & 127) == 0) {
                throw new IOException("corrupted stream - invalid high tag number found");
            }
            while (read >= 0 && (read & 128) != 0) {
                i3 = (i3 | (read & 127)) << 7;
                read = inputStream.read();
            }
            if (read < 0) {
                throw new EOFException("EOF found inside tag value.");
            }
            return i3 | (read & 127);
        }
        return i2;
    }

    ASN1EncodableVector buildDEREncodableVector(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        return new ASN1InputStream(definiteLengthInputStream).buildEncodableVector();
    }

    ASN1EncodableVector buildEncodableVector() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Primitive readObject = readObject();
            if (readObject != null) {
                aSN1EncodableVector.add(readObject);
            } else {
                return aSN1EncodableVector;
            }
        }
    }

    protected ASN1Primitive buildObject(int i, int i2, int i3) throws IOException {
        boolean z = (i & 32) != 0;
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, i3);
        if ((i & 64) != 0) {
            return new DERApplicationSpecific(z, i2, definiteLengthInputStream.toByteArray());
        }
        if ((i & 128) != 0) {
            return new ASN1StreamParser(definiteLengthInputStream).readTaggedObject(z, i2);
        }
        if (!z) {
            return createPrimitiveDERObject(i2, definiteLengthInputStream, this.tmpBuffers);
        }
        if (i2 == 4) {
            ASN1EncodableVector buildDEREncodableVector = buildDEREncodableVector(definiteLengthInputStream);
            ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[buildDEREncodableVector.size()];
            for (int i4 = 0; i4 != aSN1OctetStringArr.length; i4++) {
                aSN1OctetStringArr[i4] = (ASN1OctetString) buildDEREncodableVector.get(i4);
            }
            return new BEROctetString(aSN1OctetStringArr);
        } else if (i2 == 8) {
            return new DERExternal(buildDEREncodableVector(definiteLengthInputStream));
        } else {
            if (i2 != 16) {
                if (i2 == 17) {
                    return DERFactory.createSet(buildDEREncodableVector(definiteLengthInputStream));
                }
                throw new IOException(GeneratedOutlineSupport1.outline52("unknown tag ", i2, " encountered"));
            } else if (this.lazyEvaluate) {
                return new LazyEncodedSequence(definiteLengthInputStream.toByteArray());
            } else {
                return DERFactory.createSequence(buildDEREncodableVector(definiteLengthInputStream));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLimit() {
        return this.limit;
    }

    protected void readFully(byte[] bArr) throws IOException {
        if (Streams.readFully(this, bArr) == bArr.length) {
            return;
        }
        throw new EOFException("EOF encountered in middle of object");
    }

    public ASN1Primitive readObject() throws IOException {
        int read = read();
        if (read <= 0) {
            if (read == 0) {
                throw new IOException("unexpected end-of-contents marker");
            }
            return null;
        }
        int readTagNumber = readTagNumber(this, read);
        boolean z = (read & 32) != 0;
        int readLength = readLength();
        if (readLength >= 0) {
            try {
                return buildObject(read, readTagNumber, readLength);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception("corrupted stream detected", e);
            }
        } else if (z) {
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this, this.limit), this.limit);
            if ((read & 64) != 0) {
                return new BERApplicationSpecificParser(readTagNumber, aSN1StreamParser).getLoadedObject();
            }
            if ((read & 128) != 0) {
                return new BERTaggedObjectParser(true, readTagNumber, aSN1StreamParser).getLoadedObject();
            }
            if (readTagNumber == 4) {
                return new BEROctetStringParser(aSN1StreamParser).getLoadedObject();
            }
            if (readTagNumber == 8) {
                return new DERExternalParser(aSN1StreamParser).getLoadedObject();
            }
            if (readTagNumber == 16) {
                return new BERSequenceParser(aSN1StreamParser).getLoadedObject();
            }
            if (readTagNumber == 17) {
                return new BERSetParser(aSN1StreamParser).getLoadedObject();
            }
            throw new IOException("unknown BER object encountered");
        } else {
            throw new IOException("indefinite length primitive encoding encountered");
        }
    }

    public ASN1InputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    public ASN1InputStream(InputStream inputStream, int i) {
        this(inputStream, i, false);
    }

    public ASN1InputStream(InputStream inputStream, boolean z) {
        this(inputStream, StreamUtil.findLimit(inputStream), z);
    }

    public ASN1InputStream(InputStream inputStream, int i, boolean z) {
        super(inputStream);
        this.limit = i;
        this.lazyEvaluate = z;
        this.tmpBuffers = new byte[11];
    }

    protected int readLength() throws IOException {
        return readLength(this, this.limit);
    }
}
