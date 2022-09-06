package org.bouncycastle.openpgp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.bcpg.Packet;
import org.bouncycastle.bcpg.SignaturePacket;
import org.bouncycastle.bcpg.TrustPacket;
import org.bouncycastle.bcpg.UserAttributePacket;
import org.bouncycastle.bcpg.UserIDPacket;
/* loaded from: classes5.dex */
public abstract class PGPKeyRing {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrustPacket readOptionalTrustPacket(BCPGInputStream bCPGInputStream) throws IOException {
        if (bCPGInputStream.nextPacketTag() == 12) {
            return (TrustPacket) bCPGInputStream.readPacket();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List readSignaturesAndTrust(BCPGInputStream bCPGInputStream) throws IOException {
        try {
            ArrayList arrayList = new ArrayList();
            while (bCPGInputStream.nextPacketTag() == 2) {
                arrayList.add(new PGPSignature((SignaturePacket) bCPGInputStream.readPacket(), readOptionalTrustPacket(bCPGInputStream)));
            }
            return arrayList;
        } catch (PGPException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("can't create signature object: ");
            outline107.append(e.getMessage());
            outline107.append(", cause: ");
            outline107.append(e.getUnderlyingException().toString());
            throw new IOException(outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void readUserIDs(BCPGInputStream bCPGInputStream, List list, List list2, List list3) throws IOException {
        while (true) {
            if (bCPGInputStream.nextPacketTag() == 13 || bCPGInputStream.nextPacketTag() == 17) {
                Packet readPacket = bCPGInputStream.readPacket();
                if (readPacket instanceof UserIDPacket) {
                    list.add((UserIDPacket) readPacket);
                } else {
                    list.add(new PGPUserAttributeSubpacketVector(((UserAttributePacket) readPacket).getSubpackets()));
                }
                list2.add(readOptionalTrustPacket(bCPGInputStream));
                list3.add(readSignaturesAndTrust(bCPGInputStream));
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCPGInputStream wrap(InputStream inputStream) {
        return inputStream instanceof BCPGInputStream ? (BCPGInputStream) inputStream : new BCPGInputStream(inputStream);
    }

    public abstract void encode(OutputStream outputStream) throws IOException;

    public abstract byte[] getEncoded() throws IOException;

    public abstract Iterator<PGPPublicKey> getKeysWithSignaturesBy(long j);

    public abstract PGPPublicKey getPublicKey();

    public abstract PGPPublicKey getPublicKey(long j);

    public abstract PGPPublicKey getPublicKey(byte[] bArr);

    public abstract Iterator<PGPPublicKey> getPublicKeys();
}
