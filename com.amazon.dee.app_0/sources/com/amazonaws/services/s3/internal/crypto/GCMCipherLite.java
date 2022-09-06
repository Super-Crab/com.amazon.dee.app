package com.amazonaws.services.s3.internal.crypto;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
@Deprecated
/* loaded from: classes13.dex */
final class GCMCipherLite extends CipherLite {
    private static final int BITS = 8;
    private static final int TAG_LENGTH = ContentCryptoScheme.AES_GCM.getTagLengthInBits() / 8;
    private CipherLite aux;
    private long currentCount;
    private boolean doneFinal;
    private byte[] finalBytes;
    private boolean invisiblyProcessed;
    private long markedCount;
    private long outputByteCount;
    private boolean securityViolated;
    private final int tagLen;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GCMCipherLite(Cipher cipher, SecretKey secretKey, int i) {
        super(cipher, ContentCryptoScheme.AES_GCM, secretKey, i);
        this.tagLen = i == 1 ? TAG_LENGTH : 0;
        if (i == 1 || i == 2) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private int checkMax(int i) {
        if (this.outputByteCount + i <= 68719476704L) {
            return i;
        }
        this.securityViolated = true;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of bytes processed has exceeded the maximum allowed by AES/GCM; [outputByteCount=");
        outline107.append(this.outputByteCount);
        outline107.append(", delta=");
        outline107.append(i);
        outline107.append("]");
        throw new SecurityException(outline107.toString());
    }

    private final byte[] doFinal0(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2;
        if (this.doneFinal) {
            if (!this.securityViolated) {
                if (2 == getCipherMode()) {
                    byte[] bArr3 = this.finalBytes;
                    if (bArr3 != null) {
                        return (byte[]) bArr3.clone();
                    }
                    return null;
                }
                byte[] bArr4 = this.finalBytes;
                int length = bArr4.length;
                int i3 = this.tagLen;
                int i4 = length - i3;
                if (i2 == i4) {
                    return (byte[]) bArr4.clone();
                }
                if (i2 < i4 && i2 + this.currentCount == this.outputByteCount) {
                    return Arrays.copyOfRange(bArr4, (bArr4.length - i3) - i2, bArr4.length);
                }
                throw new IllegalStateException("Inconsistent re-rencryption");
            }
            throw new SecurityException();
        }
        this.doneFinal = true;
        this.finalBytes = super.doFinal(bArr, i, i2);
        if (this.finalBytes == null) {
            return null;
        }
        this.outputByteCount += checkMax(bArr2.length - this.tagLen);
        return (byte[]) this.finalBytes.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.s3.internal.crypto.CipherLite
    public byte[] doFinal() throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr;
        if (this.doneFinal) {
            if (!this.securityViolated) {
                byte[] bArr2 = this.finalBytes;
                if (bArr2 != null) {
                    return (byte[]) bArr2.clone();
                }
                return null;
            }
            throw new SecurityException();
        }
        this.doneFinal = true;
        this.finalBytes = super.doFinal();
        if (this.finalBytes == null) {
            return null;
        }
        this.outputByteCount += checkMax(bArr.length - this.tagLen);
        return (byte[]) this.finalBytes.clone();
    }

    long getCurrentCount() {
        return this.currentCount;
    }

    byte[] getFinalBytes() {
        byte[] bArr = this.finalBytes;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    long getMarkedCount() {
        return this.markedCount;
    }

    long getOutputByteCount() {
        return this.outputByteCount;
    }

    byte[] getTag() {
        byte[] bArr;
        if (getCipherMode() != 1 || (bArr = this.finalBytes) == null) {
            return null;
        }
        return Arrays.copyOfRange(bArr, bArr.length - this.tagLen, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.s3.internal.crypto.CipherLite
    public long mark() {
        this.markedCount = this.aux == null ? this.outputByteCount : this.currentCount;
        return this.markedCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.s3.internal.crypto.CipherLite
    public boolean markSupported() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.s3.internal.crypto.CipherLite
    public void reset() {
        if (this.markedCount < this.outputByteCount || this.invisiblyProcessed) {
            try {
                this.aux = createAuxiliary(this.markedCount);
                this.currentCount = this.markedCount;
            } catch (Exception e) {
                if (!(e instanceof RuntimeException)) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.s3.internal.crypto.CipherLite
    public byte[] update(byte[] bArr, int i, int i2) {
        byte[] update;
        CipherLite cipherLite = this.aux;
        boolean z = true;
        int i3 = 0;
        if (cipherLite == null) {
            update = super.update(bArr, i, i2);
            if (update == null) {
                if (bArr.length <= 0) {
                    z = false;
                }
                this.invisiblyProcessed = z;
                return null;
            }
            this.outputByteCount += checkMax(update.length);
            if (update.length != 0 || i2 <= 0) {
                z = false;
            }
            this.invisiblyProcessed = z;
        } else {
            update = cipherLite.update(bArr, i, i2);
            if (update == null) {
                return null;
            }
            this.currentCount += update.length;
            long j = this.currentCount;
            long j2 = this.outputByteCount;
            if (j == j2) {
                this.aux = null;
            } else if (j > j2) {
                if (1 != getCipherMode()) {
                    byte[] bArr2 = this.finalBytes;
                    if (bArr2 != null) {
                        i3 = bArr2.length;
                    }
                    long j3 = this.outputByteCount;
                    long j4 = i3;
                    this.currentCount = j3 - j4;
                    this.aux = null;
                    return Arrays.copyOf(update, (int) ((j3 - (this.currentCount - update.length)) - j4));
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("currentCount=");
                outline107.append(this.currentCount);
                outline107.append(" > outputByteCount=");
                outline107.append(this.outputByteCount);
                throw new IllegalStateException(outline107.toString());
            }
        }
        return update;
    }

    @Override // com.amazonaws.services.s3.internal.crypto.CipherLite
    final byte[] doFinal(byte[] bArr) throws IllegalBlockSizeException, BadPaddingException {
        return doFinal0(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.s3.internal.crypto.CipherLite
    public final byte[] doFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        return doFinal0(bArr, i, i2);
    }
}
