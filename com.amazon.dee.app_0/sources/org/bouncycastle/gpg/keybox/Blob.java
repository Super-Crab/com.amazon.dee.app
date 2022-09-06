package org.bouncycastle.gpg.keybox;

import java.io.IOException;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class Blob {
    protected static final byte[] magicBytes = Strings.toByteArray("KBXf");
    protected final int base;
    protected final long length;
    protected final BlobType type;
    protected final int version;

    /* renamed from: org.bouncycastle.gpg.keybox.Blob$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$gpg$keybox$BlobType = new int[BlobType.values().length];

        static {
            try {
                $SwitchMap$org$bouncycastle$gpg$keybox$BlobType[BlobType.EMPTY_BLOB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$gpg$keybox$BlobType[BlobType.FIRST_BLOB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$gpg$keybox$BlobType[BlobType.X509_BLOB.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$bouncycastle$gpg$keybox$BlobType[BlobType.OPEN_PGP_BLOB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Blob(int i, long j, BlobType blobType, int i2) {
        this.base = i;
        this.length = j;
        this.type = blobType;
        this.version = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Blob getInstance(Object obj, KeyFingerPrintCalculator keyFingerPrintCalculator, BlobVerifier blobVerifier) throws IOException {
        if (obj != null) {
            KeyBoxByteBuffer wrap = KeyBoxByteBuffer.wrap(obj);
            if (!wrap.hasRemaining()) {
                return null;
            }
            int position = wrap.position();
            long u32 = wrap.u32();
            BlobType fromByte = BlobType.fromByte(wrap.u8());
            int u8 = wrap.u8();
            int ordinal = fromByte.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return FirstBlob.parseContent(position, u32, fromByte, u8, wrap);
                }
                if (ordinal == 2) {
                    return PublicKeyRingBlob.parseContent(position, u32, fromByte, u8, wrap, keyFingerPrintCalculator, blobVerifier);
                }
                if (ordinal == 3) {
                    return CertificateBlob.parseContent(position, u32, fromByte, u8, wrap, blobVerifier);
                }
            }
            return null;
        }
        throw new IllegalArgumentException("Cannot take get instance of null");
    }

    public BlobType getType() {
        return this.type;
    }

    public int getVersion() {
        return this.version;
    }
}
