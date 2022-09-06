package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
/* loaded from: classes6.dex */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    private static final String TAG = "BitmapEncoder";
    @Nullable
    private final ArrayPool arrayPool;
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

    public BitmapEncoder(@NonNull ArrayPool arrayPool) {
        this.arrayPool = arrayPool;
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (compressFormat != null) {
            return compressFormat;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    @Override // com.bumptech.glide.load.ResourceEncoder
    @NonNull
    public EncodeStrategy getEncodeStrategy(@NonNull Options options) {
        return EncodeStrategy.TRANSFORMED;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006c, code lost:
        if (r6 == null) goto L16;
     */
    @Override // com.bumptech.glide.load.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean encode(@androidx.annotation.NonNull com.bumptech.glide.load.engine.Resource<android.graphics.Bitmap> r9, @androidx.annotation.NonNull java.io.File r10, @androidx.annotation.NonNull com.bumptech.glide.load.Options r11) {
        /*
            r8 = this;
            java.lang.String r0 = "BitmapEncoder"
            java.lang.Object r9 = r9.mo6789get()
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            android.graphics.Bitmap$CompressFormat r1 = r8.getFormat(r9, r11)
            java.lang.String r2 = "encode: ["
            java.lang.StringBuilder r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r2)
            int r3 = r9.getWidth()
            r2.append(r3)
            java.lang.String r3 = "x"
            r2.append(r3)
            int r3 = r9.getHeight()
            r2.append(r3)
            java.lang.String r3 = "] "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            androidx.core.os.TraceCompat.beginSection(r2)
            long r2 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch: java.lang.Throwable -> Lc4
            com.bumptech.glide.load.Option<java.lang.Integer> r4 = com.bumptech.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_QUALITY     // Catch: java.lang.Throwable -> Lc4
            java.lang.Object r4 = r11.get(r4)     // Catch: java.lang.Throwable -> Lc4
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch: java.lang.Throwable -> Lc4
            int r4 = r4.intValue()     // Catch: java.lang.Throwable -> Lc4
            r5 = 0
            r6 = 0
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            r7.<init>(r10)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r10 = r8.arrayPool     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            if (r10 == 0) goto L58
            com.bumptech.glide.load.data.BufferedOutputStream r10 = new com.bumptech.glide.load.data.BufferedOutputStream     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r6 = r8.arrayPool     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            r10.<init>(r7, r6)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            r6 = r10
            goto L59
        L58:
            r6 = r7
        L59:
            r9.compress(r1, r4, r6)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            r6.close()     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            r5 = 1
            goto L6e
        L61:
            r9 = move-exception
            goto Lbe
        L63:
            r6 = r7
            goto L68
        L65:
            r9 = move-exception
            r7 = r6
            goto Lbe
        L68:
            r10 = 3
            android.util.Log.isLoggable(r0, r10)     // Catch: java.lang.Throwable -> L65
            if (r6 == 0) goto L71
        L6e:
            r6.close()     // Catch: java.io.IOException -> L71 java.lang.Throwable -> Lc4
        L71:
            r10 = 2
            boolean r10 = android.util.Log.isLoggable(r0, r10)     // Catch: java.lang.Throwable -> Lc4
            if (r10 == 0) goto Lba
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc4
            r10.<init>()     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r0 = "Compressed with type: "
            r10.append(r0)     // Catch: java.lang.Throwable -> Lc4
            r10.append(r1)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r0 = " of size "
            r10.append(r0)     // Catch: java.lang.Throwable -> Lc4
            int r0 = com.bumptech.glide.util.Util.getBitmapByteSize(r9)     // Catch: java.lang.Throwable -> Lc4
            r10.append(r0)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r0 = " in "
            r10.append(r0)     // Catch: java.lang.Throwable -> Lc4
            double r0 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)     // Catch: java.lang.Throwable -> Lc4
            r10.append(r0)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r0 = ", options format: "
            r10.append(r0)     // Catch: java.lang.Throwable -> Lc4
            com.bumptech.glide.load.Option<android.graphics.Bitmap$CompressFormat> r0 = com.bumptech.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_FORMAT     // Catch: java.lang.Throwable -> Lc4
            java.lang.Object r11 = r11.get(r0)     // Catch: java.lang.Throwable -> Lc4
            r10.append(r11)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r11 = ", hasAlpha: "
            r10.append(r11)     // Catch: java.lang.Throwable -> Lc4
            boolean r9 = r9.hasAlpha()     // Catch: java.lang.Throwable -> Lc4
            r10.append(r9)     // Catch: java.lang.Throwable -> Lc4
            r10.toString()     // Catch: java.lang.Throwable -> Lc4
        Lba:
            androidx.core.os.TraceCompat.endSection()
            return r5
        Lbe:
            if (r7 == 0) goto Lc3
            r7.close()     // Catch: java.io.IOException -> Lc3 java.lang.Throwable -> Lc4
        Lc3:
            throw r9     // Catch: java.lang.Throwable -> Lc4
        Lc4:
            r9 = move-exception
            androidx.core.os.TraceCompat.endSection()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.BitmapEncoder.encode(com.bumptech.glide.load.engine.Resource, java.io.File, com.bumptech.glide.load.Options):boolean");
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }
}
