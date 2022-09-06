package org.apache.commons.io.filefilter;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
/* loaded from: classes4.dex */
public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final long byteOffset;
    private final byte[] magicNumbers;

    public MagicNumberFileFilter(byte[] bArr) {
        this(bArr, 0L);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        RandomAccessFile randomAccessFile;
        if (file != null && file.isFile() && file.canRead()) {
            try {
                byte[] bArr = new byte[this.magicNumbers.length];
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    randomAccessFile.seek(this.byteOffset);
                    if (randomAccessFile.read(bArr) != this.magicNumbers.length) {
                        IOUtils.closeQuietly(randomAccessFile);
                        return false;
                    }
                    boolean equals = Arrays.equals(this.magicNumbers, bArr);
                    IOUtils.closeQuietly(randomAccessFile);
                    return equals;
                } catch (IOException unused) {
                    IOUtils.closeQuietly(randomAccessFile);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    IOUtils.closeQuietly(randomAccessFile);
                    throw th;
                }
            } catch (IOException unused2) {
                randomAccessFile = null;
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = null;
            }
        }
        return false;
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("(");
        sb.append(new String(this.magicNumbers));
        sb.append(",");
        return GeneratedOutlineSupport1.outline87(sb, this.byteOffset, ")");
    }

    public MagicNumberFileFilter(String str) {
        this(str, 0L);
    }

    public MagicNumberFileFilter(String str, long j) {
        if (str != null) {
            if (str.length() == 0) {
                throw new IllegalArgumentException("The magic number must contain at least one byte");
            }
            if (j >= 0) {
                this.magicNumbers = str.getBytes();
                this.byteOffset = j;
                return;
            }
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        throw new IllegalArgumentException("The magic number cannot be null");
    }

    public MagicNumberFileFilter(byte[] bArr, long j) {
        if (bArr != null) {
            if (bArr.length == 0) {
                throw new IllegalArgumentException("The magic number must contain at least one byte");
            }
            if (j >= 0) {
                this.magicNumbers = new byte[bArr.length];
                System.arraycopy(bArr, 0, this.magicNumbers, 0, bArr.length);
                this.byteOffset = j;
                return;
            }
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        throw new IllegalArgumentException("The magic number cannot be null");
    }
}
