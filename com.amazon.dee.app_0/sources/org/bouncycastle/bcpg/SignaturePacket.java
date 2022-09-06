package org.bouncycastle.bcpg;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Vector;
import org.bouncycastle.bcpg.sig.IssuerKeyID;
import org.bouncycastle.bcpg.sig.SignatureCreationTime;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class SignaturePacket extends ContainedPacket implements PublicKeyAlgorithmTags {
    private long creationTime;
    private byte[] fingerPrint;
    private int hashAlgorithm;
    private SignatureSubpacket[] hashedData;
    private int keyAlgorithm;
    private long keyID;
    private MPInteger[] signature;
    private byte[] signatureEncoding;
    private int signatureType;
    private SignatureSubpacket[] unhashedData;
    private int version;

    public SignaturePacket(int i, int i2, long j, int i3, int i4, long j2, byte[] bArr, MPInteger[] mPIntegerArr) {
        this(i, i2, j, i3, i4, null, null, bArr, mPIntegerArr);
        this.creationTime = j2;
    }

    public SignaturePacket(int i, int i2, long j, int i3, int i4, SignatureSubpacket[] signatureSubpacketArr, SignatureSubpacket[] signatureSubpacketArr2, byte[] bArr, MPInteger[] mPIntegerArr) {
        this.version = i;
        this.signatureType = i2;
        this.keyID = j;
        this.keyAlgorithm = i3;
        this.hashAlgorithm = i4;
        this.hashedData = signatureSubpacketArr;
        this.unhashedData = signatureSubpacketArr2;
        this.fingerPrint = bArr;
        this.signature = mPIntegerArr;
        if (signatureSubpacketArr != null) {
            setCreationTime();
        }
    }

    public SignaturePacket(int i, long j, int i2, int i3, SignatureSubpacket[] signatureSubpacketArr, SignatureSubpacket[] signatureSubpacketArr2, byte[] bArr, MPInteger[] mPIntegerArr) {
        this(4, i, j, i2, i3, signatureSubpacketArr, signatureSubpacketArr2, bArr, mPIntegerArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignaturePacket(BCPGInputStream bCPGInputStream) throws IOException {
        this.version = bCPGInputStream.read();
        int i = this.version;
        if (i == 3 || i == 2) {
            bCPGInputStream.read();
            this.signatureType = bCPGInputStream.read();
            this.creationTime = ((bCPGInputStream.read() << 24) | (bCPGInputStream.read() << 16) | (bCPGInputStream.read() << 8) | bCPGInputStream.read()) * 1000;
            this.keyID |= bCPGInputStream.read() << 56;
            this.keyID |= bCPGInputStream.read() << 48;
            this.keyID |= bCPGInputStream.read() << 40;
            this.keyID |= bCPGInputStream.read() << 32;
            this.keyID |= bCPGInputStream.read() << 24;
            this.keyID |= bCPGInputStream.read() << 16;
            this.keyID |= bCPGInputStream.read() << 8;
            this.keyID |= bCPGInputStream.read();
            this.keyAlgorithm = bCPGInputStream.read();
            this.hashAlgorithm = bCPGInputStream.read();
        } else if (i != 4) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unsupported version: ");
            outline107.append(this.version);
            throw new RuntimeException(outline107.toString());
        } else {
            this.signatureType = bCPGInputStream.read();
            this.keyAlgorithm = bCPGInputStream.read();
            this.hashAlgorithm = bCPGInputStream.read();
            byte[] bArr = new byte[(bCPGInputStream.read() << 8) | bCPGInputStream.read()];
            bCPGInputStream.readFully(bArr);
            SignatureSubpacketInputStream signatureSubpacketInputStream = new SignatureSubpacketInputStream(new ByteArrayInputStream(bArr));
            Vector vector = new Vector();
            while (true) {
                SignatureSubpacket readPacket = signatureSubpacketInputStream.readPacket();
                if (readPacket == null) {
                    break;
                }
                vector.addElement(readPacket);
            }
            this.hashedData = new SignatureSubpacket[vector.size()];
            for (int i2 = 0; i2 != this.hashedData.length; i2++) {
                SignatureSubpacket signatureSubpacket = (SignatureSubpacket) vector.elementAt(i2);
                if (signatureSubpacket instanceof IssuerKeyID) {
                    this.keyID = ((IssuerKeyID) signatureSubpacket).getKeyID();
                } else if (signatureSubpacket instanceof SignatureCreationTime) {
                    this.creationTime = ((SignatureCreationTime) signatureSubpacket).getTime().getTime();
                }
                this.hashedData[i2] = signatureSubpacket;
            }
            byte[] bArr2 = new byte[(bCPGInputStream.read() << 8) | bCPGInputStream.read()];
            bCPGInputStream.readFully(bArr2);
            SignatureSubpacketInputStream signatureSubpacketInputStream2 = new SignatureSubpacketInputStream(new ByteArrayInputStream(bArr2));
            vector.removeAllElements();
            while (true) {
                SignatureSubpacket readPacket2 = signatureSubpacketInputStream2.readPacket();
                if (readPacket2 == null) {
                    break;
                }
                vector.addElement(readPacket2);
            }
            this.unhashedData = new SignatureSubpacket[vector.size()];
            for (int i3 = 0; i3 != this.unhashedData.length; i3++) {
                SignatureSubpacket signatureSubpacket2 = (SignatureSubpacket) vector.elementAt(i3);
                if (signatureSubpacket2 instanceof IssuerKeyID) {
                    this.keyID = ((IssuerKeyID) signatureSubpacket2).getKeyID();
                }
                this.unhashedData[i3] = signatureSubpacket2;
            }
        }
        this.fingerPrint = new byte[2];
        bCPGInputStream.readFully(this.fingerPrint);
        int i4 = this.keyAlgorithm;
        if (i4 == 1 || i4 == 3) {
            MPInteger mPInteger = new MPInteger(bCPGInputStream);
            this.signature = new MPInteger[1];
            this.signature[0] = mPInteger;
        } else if (i4 == 22) {
            MPInteger mPInteger2 = new MPInteger(bCPGInputStream);
            MPInteger mPInteger3 = new MPInteger(bCPGInputStream);
            this.signature = new MPInteger[2];
            MPInteger[] mPIntegerArr = this.signature;
            mPIntegerArr[0] = mPInteger2;
            mPIntegerArr[1] = mPInteger3;
        } else {
            if (i4 != 16) {
                if (i4 == 17) {
                    MPInteger mPInteger4 = new MPInteger(bCPGInputStream);
                    MPInteger mPInteger5 = new MPInteger(bCPGInputStream);
                    this.signature = new MPInteger[2];
                    MPInteger[] mPIntegerArr2 = this.signature;
                    mPIntegerArr2[0] = mPInteger4;
                    mPIntegerArr2[1] = mPInteger5;
                    return;
                } else if (i4 == 19) {
                    MPInteger mPInteger6 = new MPInteger(bCPGInputStream);
                    MPInteger mPInteger7 = new MPInteger(bCPGInputStream);
                    this.signature = new MPInteger[2];
                    MPInteger[] mPIntegerArr3 = this.signature;
                    mPIntegerArr3[0] = mPInteger6;
                    mPIntegerArr3[1] = mPInteger7;
                    return;
                } else if (i4 != 20) {
                    if (i4 < 100 || i4 > 110) {
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("unknown signature key algorithm: ");
                        outline1072.append(this.keyAlgorithm);
                        throw new IOException(outline1072.toString());
                    }
                    this.signature = null;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        int read = bCPGInputStream.read();
                        if (read < 0) {
                            this.signatureEncoding = byteArrayOutputStream.toByteArray();
                            return;
                        }
                        byteArrayOutputStream.write(read);
                    }
                }
            }
            MPInteger mPInteger8 = new MPInteger(bCPGInputStream);
            MPInteger mPInteger9 = new MPInteger(bCPGInputStream);
            MPInteger mPInteger10 = new MPInteger(bCPGInputStream);
            this.signature = new MPInteger[3];
            MPInteger[] mPIntegerArr4 = this.signature;
            mPIntegerArr4[0] = mPInteger8;
            mPIntegerArr4[1] = mPInteger9;
            mPIntegerArr4[2] = mPInteger10;
        }
    }

    public static SignaturePacket fromByteArray(byte[] bArr) throws IOException {
        return new SignaturePacket(new BCPGInputStream(new ByteArrayInputStream(bArr)));
    }

    private void setCreationTime() {
        int i = 0;
        while (true) {
            SignatureSubpacket[] signatureSubpacketArr = this.hashedData;
            if (i != signatureSubpacketArr.length) {
                if (signatureSubpacketArr[i] instanceof SignatureCreationTime) {
                    this.creationTime = ((SignatureCreationTime) signatureSubpacketArr[i]).getTime().getTime();
                    return;
                }
                i++;
            } else {
                return;
            }
        }
    }

    @Override // org.bouncycastle.bcpg.ContainedPacket
    public void encode(BCPGOutputStream bCPGOutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BCPGOutputStream bCPGOutputStream2 = new BCPGOutputStream(byteArrayOutputStream);
        bCPGOutputStream2.write(this.version);
        int i = this.version;
        int i2 = 0;
        if (i == 3 || i == 2) {
            bCPGOutputStream2.write(5);
            long j = this.creationTime / 1000;
            bCPGOutputStream2.write(this.signatureType);
            bCPGOutputStream2.write((byte) (j >> 24));
            bCPGOutputStream2.write((byte) (j >> 16));
            bCPGOutputStream2.write((byte) (j >> 8));
            bCPGOutputStream2.write((byte) j);
            bCPGOutputStream2.write((byte) (this.keyID >> 56));
            bCPGOutputStream2.write((byte) (this.keyID >> 48));
            bCPGOutputStream2.write((byte) (this.keyID >> 40));
            bCPGOutputStream2.write((byte) (this.keyID >> 32));
            bCPGOutputStream2.write((byte) (this.keyID >> 24));
            bCPGOutputStream2.write((byte) (this.keyID >> 16));
            bCPGOutputStream2.write((byte) (this.keyID >> 8));
            bCPGOutputStream2.write((byte) this.keyID);
            bCPGOutputStream2.write(this.keyAlgorithm);
            bCPGOutputStream2.write(this.hashAlgorithm);
        } else if (i != 4) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown version: ");
            outline107.append(this.version);
            throw new IOException(outline107.toString());
        } else {
            bCPGOutputStream2.write(this.signatureType);
            bCPGOutputStream2.write(this.keyAlgorithm);
            bCPGOutputStream2.write(this.hashAlgorithm);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            int i3 = 0;
            while (true) {
                SignatureSubpacket[] signatureSubpacketArr = this.hashedData;
                if (i3 == signatureSubpacketArr.length) {
                    break;
                }
                signatureSubpacketArr[i3].encode(byteArrayOutputStream2);
                i3++;
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            bCPGOutputStream2.write(byteArray.length >> 8);
            bCPGOutputStream2.write(byteArray.length);
            bCPGOutputStream2.write(byteArray);
            byteArrayOutputStream2.reset();
            int i4 = 0;
            while (true) {
                SignatureSubpacket[] signatureSubpacketArr2 = this.unhashedData;
                if (i4 == signatureSubpacketArr2.length) {
                    break;
                }
                signatureSubpacketArr2[i4].encode(byteArrayOutputStream2);
                i4++;
            }
            byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
            bCPGOutputStream2.write(byteArray2.length >> 8);
            bCPGOutputStream2.write(byteArray2.length);
            bCPGOutputStream2.write(byteArray2);
        }
        bCPGOutputStream2.write(this.fingerPrint);
        if (this.signature != null) {
            while (true) {
                MPInteger[] mPIntegerArr = this.signature;
                if (i2 == mPIntegerArr.length) {
                    break;
                }
                bCPGOutputStream2.writeObject(mPIntegerArr[i2]);
                i2++;
            }
        } else {
            bCPGOutputStream2.write(this.signatureEncoding);
        }
        bCPGOutputStream2.close();
        bCPGOutputStream.writePacket(2, byteArrayOutputStream.toByteArray(), true);
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public int getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public SignatureSubpacket[] getHashedSubPackets() {
        return this.hashedData;
    }

    public int getKeyAlgorithm() {
        return this.keyAlgorithm;
    }

    public long getKeyID() {
        return this.keyID;
    }

    public MPInteger[] getSignature() {
        return this.signature;
    }

    public byte[] getSignatureBytes() {
        byte[] bArr = this.signatureEncoding;
        if (bArr == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BCPGOutputStream bCPGOutputStream = new BCPGOutputStream(byteArrayOutputStream);
            int i = 0;
            while (true) {
                MPInteger[] mPIntegerArr = this.signature;
                if (i == mPIntegerArr.length) {
                    return byteArrayOutputStream.toByteArray();
                }
                try {
                    bCPGOutputStream.writeObject(mPIntegerArr[i]);
                    i++;
                } catch (IOException e) {
                    throw new RuntimeException(GeneratedOutlineSupport1.outline65("internal error: ", e));
                }
            }
        } else {
            return Arrays.clone(bArr);
        }
    }

    public byte[] getSignatureTrailer() {
        int i = this.version;
        if (i == 3 || i == 2) {
            long j = this.creationTime / 1000;
            return new byte[]{(byte) this.signatureType, (byte) (j >> 24), (byte) (j >> 16), (byte) (j >> 8), (byte) j};
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write((byte) getVersion());
            byteArrayOutputStream.write((byte) getSignatureType());
            byteArrayOutputStream.write((byte) getKeyAlgorithm());
            byteArrayOutputStream.write((byte) getHashAlgorithm());
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            SignatureSubpacket[] hashedSubPackets = getHashedSubPackets();
            for (int i2 = 0; i2 != hashedSubPackets.length; i2++) {
                hashedSubPackets[i2].encode(byteArrayOutputStream2);
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            byteArrayOutputStream.write((byte) (byteArray.length >> 8));
            byteArrayOutputStream.write((byte) byteArray.length);
            byteArrayOutputStream.write(byteArray);
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.write((byte) getVersion());
            byteArrayOutputStream.write(-1);
            byteArrayOutputStream.write((byte) (byteArray2.length >> 24));
            byteArrayOutputStream.write((byte) (byteArray2.length >> 16));
            byteArrayOutputStream.write((byte) (byteArray2.length >> 8));
            byteArrayOutputStream.write((byte) byteArray2.length);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline65("exception generating trailer: ", e));
        }
    }

    public int getSignatureType() {
        return this.signatureType;
    }

    public SignatureSubpacket[] getUnhashedSubPackets() {
        return this.unhashedData;
    }

    public int getVersion() {
        return this.version;
    }
}
