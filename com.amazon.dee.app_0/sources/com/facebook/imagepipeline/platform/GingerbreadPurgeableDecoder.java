package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.MemoryFile;
import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
@DoNotStrip
/* loaded from: classes2.dex */
public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder {
    private static Method sGetFileDescriptorMethod;
    @Nullable
    private final WebpBitmapFactory mWebpBitmapFactory = WebpSupportStatus.loadWebpBitmapFactoryIfExists();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v3 */
    private static MemoryFile copyToMemoryFile(CloseableReference<PooledByteBuffer> bytesRef, int inputLength, @Nullable byte[] suffix) throws IOException {
        OutputStream outputStream;
        PooledByteBufferInputStream pooledByteBufferInputStream;
        LimitedInputStream limitedInputStream = null;
        OutputStream outputStream2 = null;
        limitedInputStream = null;
        MemoryFile memoryFile = new MemoryFile(null, (suffix == null ? 0 : suffix.length) + inputLength);
        memoryFile.allowPurging(false);
        try {
            pooledByteBufferInputStream = new PooledByteBufferInputStream(bytesRef.get());
            try {
                LimitedInputStream limitedInputStream2 = new LimitedInputStream(pooledByteBufferInputStream, inputLength);
                try {
                    outputStream2 = memoryFile.getOutputStream();
                    ByteStreams.copy(limitedInputStream2, outputStream2);
                    if (suffix != null) {
                        memoryFile.writeBytes(suffix, 0, inputLength, suffix.length);
                    }
                    CloseableReference.closeSafely(bytesRef);
                    Closeables.closeQuietly(pooledByteBufferInputStream);
                    Closeables.closeQuietly(limitedInputStream2);
                    Closeables.close(outputStream2, true);
                    return memoryFile;
                } catch (Throwable th) {
                    th = th;
                    outputStream = outputStream2;
                    limitedInputStream = limitedInputStream2;
                    CloseableReference.closeSafely(bytesRef);
                    Closeables.closeQuietly(pooledByteBufferInputStream);
                    Closeables.closeQuietly(limitedInputStream);
                    Closeables.close(outputStream, true);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                outputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
            pooledByteBufferInputStream = null;
        }
    }

    private Bitmap decodeFileDescriptorAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int inputLength, @Nullable byte[] suffix, BitmapFactory.Options options) {
        MemoryFile memoryFile;
        MemoryFile memoryFile2 = null;
        try {
            try {
                memoryFile = copyToMemoryFile(bytesRef, inputLength, suffix);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            memoryFile = memoryFile2;
        }
        try {
            FileDescriptor memoryFileDescriptor = getMemoryFileDescriptor(memoryFile);
            if (this.mWebpBitmapFactory != null) {
                Bitmap bitmap = (Bitmap) Preconditions.checkNotNull(this.mWebpBitmapFactory.decodeFileDescriptor(memoryFileDescriptor, null, options), "BitmapFactory returned null");
                if (memoryFile != null) {
                    memoryFile.close();
                }
                return bitmap;
            }
            throw new IllegalStateException("WebpBitmapFactory is null");
        } catch (IOException e2) {
            e = e2;
            memoryFile2 = memoryFile;
            throw Throwables.propagate(e);
        } catch (Throwable th2) {
            th = th2;
            if (memoryFile != null) {
                memoryFile.close();
            }
            throw th;
        }
    }

    private synchronized Method getFileDescriptorMethod() {
        if (sGetFileDescriptorMethod == null) {
            try {
                sGetFileDescriptorMethod = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            } catch (Exception e) {
                throw Throwables.propagate(e);
            }
        }
        return sGetFileDescriptorMethod;
    }

    private FileDescriptor getMemoryFileDescriptor(MemoryFile memoryFile) {
        try {
            return (FileDescriptor) Preconditions.checkNotNull(getFileDescriptorMethod().invoke(memoryFile, new Object[0]));
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, BitmapFactory.Options options) {
        return decodeFileDescriptorAsPurgeable(bytesRef, bytesRef.get().size(), null, options);
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int length, BitmapFactory.Options options) {
        return decodeFileDescriptorAsPurgeable(bytesRef, length, DalvikPurgeableDecoder.endsWithEOI(bytesRef, length) ? null : DalvikPurgeableDecoder.EOI, options);
    }
}
