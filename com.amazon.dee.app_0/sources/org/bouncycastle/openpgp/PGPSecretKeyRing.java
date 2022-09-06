package org.bouncycastle.openpgp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.bcpg.PublicSubkeyPacket;
import org.bouncycastle.bcpg.SecretKeyPacket;
import org.bouncycastle.bcpg.SecretSubkeyPacket;
import org.bouncycastle.bcpg.TrustPacket;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Iterable;
/* loaded from: classes5.dex */
public class PGPSecretKeyRing extends PGPKeyRing implements Iterable<PGPSecretKey> {
    List extraPubKeys;
    List keys;

    public PGPSecretKeyRing(InputStream inputStream, KeyFingerPrintCalculator keyFingerPrintCalculator) throws IOException, PGPException {
        List list;
        Object pGPPublicKey;
        this.keys = new ArrayList();
        this.extraPubKeys = new ArrayList();
        BCPGInputStream wrap = PGPKeyRing.wrap(inputStream);
        int nextPacketTag = wrap.nextPacketTag();
        if (nextPacketTag == 5 || nextPacketTag == 7) {
            SecretKeyPacket secretKeyPacket = (SecretKeyPacket) wrap.readPacket();
            while (wrap.nextPacketTag() == 61) {
                wrap.readPacket();
            }
            TrustPacket readOptionalTrustPacket = PGPKeyRing.readOptionalTrustPacket(wrap);
            List readSignaturesAndTrust = PGPKeyRing.readSignaturesAndTrust(wrap);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            PGPKeyRing.readUserIDs(wrap, arrayList, arrayList2, arrayList3);
            this.keys.add(new PGPSecretKey(secretKeyPacket, new PGPPublicKey(secretKeyPacket.getPublicKeyPacket(), readOptionalTrustPacket, readSignaturesAndTrust, arrayList, arrayList2, arrayList3, keyFingerPrintCalculator)));
            while (true) {
                if (wrap.nextPacketTag() != 7 && wrap.nextPacketTag() != 14) {
                    return;
                }
                if (wrap.nextPacketTag() == 7) {
                    SecretSubkeyPacket secretSubkeyPacket = (SecretSubkeyPacket) wrap.readPacket();
                    while (wrap.nextPacketTag() == 61) {
                        wrap.readPacket();
                    }
                    TrustPacket readOptionalTrustPacket2 = PGPKeyRing.readOptionalTrustPacket(wrap);
                    List readSignaturesAndTrust2 = PGPKeyRing.readSignaturesAndTrust(wrap);
                    list = this.keys;
                    pGPPublicKey = new PGPSecretKey(secretSubkeyPacket, new PGPPublicKey(secretSubkeyPacket.getPublicKeyPacket(), readOptionalTrustPacket2, readSignaturesAndTrust2, keyFingerPrintCalculator));
                } else {
                    TrustPacket readOptionalTrustPacket3 = PGPKeyRing.readOptionalTrustPacket(wrap);
                    List readSignaturesAndTrust3 = PGPKeyRing.readSignaturesAndTrust(wrap);
                    list = this.extraPubKeys;
                    pGPPublicKey = new PGPPublicKey((PublicSubkeyPacket) wrap.readPacket(), readOptionalTrustPacket3, readSignaturesAndTrust3, keyFingerPrintCalculator);
                }
                list.add(pGPPublicKey);
            }
        } else {
            throw new IOException(GeneratedOutlineSupport1.outline32(nextPacketTag, GeneratedOutlineSupport1.outline107("secret key ring doesn't start with secret key tag: tag 0x")));
        }
    }

    public PGPSecretKeyRing(List list) {
        this(checkKeys(list), new ArrayList());
    }

    private PGPSecretKeyRing(List list, List list2) {
        this.keys = list;
        this.extraPubKeys = list2;
    }

    public PGPSecretKeyRing(byte[] bArr, KeyFingerPrintCalculator keyFingerPrintCalculator) throws IOException, PGPException {
        this(new ByteArrayInputStream(bArr), keyFingerPrintCalculator);
    }

    private static List checkKeys(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i != list.size(); i++) {
            PGPSecretKey pGPSecretKey = (PGPSecretKey) list.get(i);
            boolean isMasterKey = pGPSecretKey.isMasterKey();
            if (i == 0) {
                if (!isMasterKey) {
                    throw new IllegalArgumentException("key 0 must be a master key");
                }
            } else if (isMasterKey) {
                throw new IllegalArgumentException("key 0 can be only master key");
            }
            arrayList.add(pGPSecretKey);
        }
        return arrayList;
    }

    public static PGPSecretKeyRing copyWithNewPassword(PGPSecretKeyRing pGPSecretKeyRing, PBESecretKeyDecryptor pBESecretKeyDecryptor, PBESecretKeyEncryptor pBESecretKeyEncryptor) throws PGPException {
        ArrayList arrayList = new ArrayList(pGPSecretKeyRing.keys.size());
        Iterator<PGPSecretKey> secretKeys = pGPSecretKeyRing.getSecretKeys();
        while (secretKeys.hasNext()) {
            PGPSecretKey next = secretKeys.next();
            if (!next.isPrivateKeyEmpty()) {
                next = PGPSecretKey.copyWithNewPassword(next, pBESecretKeyDecryptor, pBESecretKeyEncryptor);
            }
            arrayList.add(next);
        }
        return new PGPSecretKeyRing(arrayList, pGPSecretKeyRing.extraPubKeys);
    }

    public static PGPSecretKeyRing insertSecretKey(PGPSecretKeyRing pGPSecretKeyRing, PGPSecretKey pGPSecretKey) {
        ArrayList arrayList = new ArrayList(pGPSecretKeyRing.keys);
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i != arrayList.size(); i++) {
            PGPSecretKey pGPSecretKey2 = (PGPSecretKey) arrayList.get(i);
            if (pGPSecretKey2.getKeyID() == pGPSecretKey.getKeyID()) {
                arrayList.set(i, pGPSecretKey);
                z = true;
            }
            if (pGPSecretKey2.isMasterKey()) {
                z2 = true;
            }
        }
        if (!z) {
            if (!pGPSecretKey.isMasterKey()) {
                arrayList.add(pGPSecretKey);
            } else if (z2) {
                throw new IllegalArgumentException("cannot add a master key to a ring that already has one");
            } else {
                arrayList.add(0, pGPSecretKey);
            }
        }
        return new PGPSecretKeyRing(arrayList, pGPSecretKeyRing.extraPubKeys);
    }

    public static PGPSecretKeyRing removeSecretKey(PGPSecretKeyRing pGPSecretKeyRing, PGPSecretKey pGPSecretKey) {
        ArrayList arrayList = new ArrayList(pGPSecretKeyRing.keys);
        boolean z = false;
        for (int i = 0; i < arrayList.size(); i++) {
            if (((PGPSecretKey) arrayList.get(i)).getKeyID() == pGPSecretKey.getKeyID()) {
                arrayList.remove(i);
                z = true;
            }
        }
        if (!z) {
            return null;
        }
        return new PGPSecretKeyRing(arrayList, pGPSecretKeyRing.extraPubKeys);
    }

    public static PGPSecretKeyRing replacePublicKeys(PGPSecretKeyRing pGPSecretKeyRing, PGPPublicKeyRing pGPPublicKeyRing) {
        ArrayList arrayList = new ArrayList(pGPSecretKeyRing.keys.size());
        for (PGPSecretKey pGPSecretKey : pGPSecretKeyRing.keys) {
            arrayList.add(PGPSecretKey.replacePublicKey(pGPSecretKey, pGPPublicKeyRing.getPublicKey(pGPSecretKey.getKeyID())));
        }
        return new PGPSecretKeyRing(arrayList);
    }

    @Override // org.bouncycastle.openpgp.PGPKeyRing
    public void encode(OutputStream outputStream) throws IOException {
        for (int i = 0; i != this.keys.size(); i++) {
            ((PGPSecretKey) this.keys.get(i)).encode(outputStream);
        }
        for (int i2 = 0; i2 != this.extraPubKeys.size(); i2++) {
            ((PGPPublicKey) this.extraPubKeys.get(i2)).encode(outputStream);
        }
    }

    @Override // org.bouncycastle.openpgp.PGPKeyRing
    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public Iterator<PGPPublicKey> getExtraPublicKeys() {
        return this.extraPubKeys.iterator();
    }

    @Override // org.bouncycastle.openpgp.PGPKeyRing
    public Iterator<PGPPublicKey> getKeysWithSignaturesBy(long j) {
        ArrayList arrayList = new ArrayList();
        Iterator<PGPPublicKey> publicKeys = getPublicKeys();
        while (publicKeys.hasNext()) {
            PGPPublicKey next = publicKeys.next();
            if (next.getSignaturesForKeyID(j).hasNext()) {
                arrayList.add(next);
            }
        }
        return arrayList.iterator();
    }

    @Override // org.bouncycastle.openpgp.PGPKeyRing
    public PGPPublicKey getPublicKey() {
        return ((PGPSecretKey) this.keys.get(0)).getPublicKey();
    }

    @Override // org.bouncycastle.openpgp.PGPKeyRing
    public PGPPublicKey getPublicKey(long j) {
        PGPSecretKey secretKey = getSecretKey(j);
        if (secretKey != null) {
            return secretKey.getPublicKey();
        }
        for (int i = 0; i != this.extraPubKeys.size(); i++) {
            PGPPublicKey pGPPublicKey = (PGPPublicKey) this.keys.get(i);
            if (j == pGPPublicKey.getKeyID()) {
                return pGPPublicKey;
            }
        }
        return null;
    }

    @Override // org.bouncycastle.openpgp.PGPKeyRing
    public PGPPublicKey getPublicKey(byte[] bArr) {
        PGPSecretKey secretKey = getSecretKey(bArr);
        if (secretKey != null) {
            return secretKey.getPublicKey();
        }
        for (int i = 0; i != this.extraPubKeys.size(); i++) {
            PGPPublicKey pGPPublicKey = (PGPPublicKey) this.keys.get(i);
            if (Arrays.areEqual(bArr, pGPPublicKey.getFingerprint())) {
                return pGPPublicKey;
            }
        }
        return null;
    }

    @Override // org.bouncycastle.openpgp.PGPKeyRing
    public Iterator<PGPPublicKey> getPublicKeys() {
        ArrayList arrayList = new ArrayList();
        Iterator<PGPSecretKey> secretKeys = getSecretKeys();
        while (secretKeys.hasNext()) {
            arrayList.add(secretKeys.next().getPublicKey());
        }
        arrayList.addAll(this.extraPubKeys);
        return Collections.unmodifiableList(arrayList).iterator();
    }

    public PGPSecretKey getSecretKey() {
        return (PGPSecretKey) this.keys.get(0);
    }

    public PGPSecretKey getSecretKey(long j) {
        for (int i = 0; i != this.keys.size(); i++) {
            PGPSecretKey pGPSecretKey = (PGPSecretKey) this.keys.get(i);
            if (j == pGPSecretKey.getKeyID()) {
                return pGPSecretKey;
            }
        }
        return null;
    }

    public PGPSecretKey getSecretKey(byte[] bArr) {
        for (int i = 0; i != this.keys.size(); i++) {
            PGPSecretKey pGPSecretKey = (PGPSecretKey) this.keys.get(i);
            if (Arrays.areEqual(bArr, pGPSecretKey.getPublicKey().getFingerprint())) {
                return pGPSecretKey;
            }
        }
        return null;
    }

    public Iterator<PGPSecretKey> getSecretKeys() {
        return Collections.unmodifiableList(this.keys).iterator();
    }

    @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<PGPSecretKey> iterator() {
        return getSecretKeys();
    }
}
