package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import javax.annotation.concurrent.ThreadSafe;
@DoNotStrip
@ThreadSafe
@TargetApi(19)
/* loaded from: classes2.dex */
public class KitKatPurgeableDecoder extends DalvikPurgeableDecoder {
    private final FlexByteArrayPool mFlexByteArrayPool;

    @DoNotStrip
    public KitKatPurgeableDecoder(FlexByteArrayPool flexByteArrayPool) {
        this.mFlexByteArrayPool = flexByteArrayPool;
    }

    private static void putEOI(byte[] imageBytes, int offset) {
        imageBytes[offset] = -1;
        imageBytes[offset + 1] = -39;
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, BitmapFactory.Options options) {
        PooledByteBuffer pooledByteBuffer = bytesRef.get();
        int size = pooledByteBuffer.size();
        CloseableReference<byte[]> closeableReference = this.mFlexByteArrayPool.get(size);
        try {
            byte[] bArr = closeableReference.get();
            pooledByteBuffer.read(0, bArr, 0, size);
            return (Bitmap) Preconditions.checkNotNull(BitmapFactory.decodeByteArray(bArr, 0, size, options), "BitmapFactory returned null");
        } finally {
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
        }
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int length, BitmapFactory.Options options) {
        byte[] bArr = DalvikPurgeableDecoder.endsWithEOI(bytesRef, length) ? null : DalvikPurgeableDecoder.EOI;
        PooledByteBuffer pooledByteBuffer = bytesRef.get();
        Preconditions.checkArgument(Boolean.valueOf(length <= pooledByteBuffer.size()));
        int i = length + 2;
        CloseableReference<byte[]> closeableReference = this.mFlexByteArrayPool.get(i);
        try {
            byte[] bArr2 = closeableReference.get();
            pooledByteBuffer.read(0, bArr2, 0, length);
            if (bArr != null) {
                putEOI(bArr2, length);
                length = i;
            }
            return (Bitmap) Preconditions.checkNotNull(BitmapFactory.decodeByteArray(bArr2, 0, length, options), "BitmapFactory returned null");
        } finally {
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
        }
    }
}
