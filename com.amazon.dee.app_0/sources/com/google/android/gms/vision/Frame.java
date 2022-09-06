package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public class Frame {
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;
    private Metadata zzap;
    private ByteBuffer zzaq;
    private Bitmap zzar;

    /* loaded from: classes2.dex */
    public static class Builder {
        private Frame zzas = new Frame();

        public Frame build() {
            if (this.zzas.zzaq == null && this.zzas.zzar == null) {
                throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
            }
            return this.zzas;
        }

        public Builder setBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            this.zzas.zzar = bitmap;
            Metadata metadata = this.zzas.getMetadata();
            metadata.width = width;
            metadata.height = height;
            return this;
        }

        public Builder setId(int i) {
            this.zzas.getMetadata().id = i;
            return this;
        }

        public Builder setImageData(ByteBuffer byteBuffer, int i, int i2, int i3) {
            if (byteBuffer != null) {
                if (byteBuffer.capacity() < i * i2) {
                    throw new IllegalArgumentException("Invalid image data size.");
                }
                if (i3 != 16 && i3 != 17 && i3 != 842094169) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(37, "Unsupported image format: ", i3));
                }
                this.zzas.zzaq = byteBuffer;
                Metadata metadata = this.zzas.getMetadata();
                metadata.width = i;
                metadata.height = i2;
                metadata.format = i3;
                return this;
            }
            throw new IllegalArgumentException("Null image data supplied.");
        }

        public Builder setRotation(int i) {
            this.zzas.getMetadata().rotation = i;
            return this;
        }

        public Builder setTimestampMillis(long j) {
            this.zzas.getMetadata().zzat = j;
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public static class Metadata {
        private int format = -1;
        private int height;
        private int id;
        private int rotation;
        private int width;
        private long zzat;

        public Metadata() {
        }

        public Metadata(Metadata metadata) {
            this.width = metadata.getWidth();
            this.height = metadata.getHeight();
            this.id = metadata.getId();
            this.zzat = metadata.getTimestampMillis();
            this.rotation = metadata.getRotation();
        }

        public int getFormat() {
            return this.format;
        }

        public int getHeight() {
            return this.height;
        }

        public int getId() {
            return this.id;
        }

        public int getRotation() {
            return this.rotation;
        }

        public long getTimestampMillis() {
            return this.zzat;
        }

        public int getWidth() {
            return this.width;
        }

        public final void zzd() {
            if (this.rotation % 2 != 0) {
                int i = this.width;
                this.width = this.height;
                this.height = i;
            }
            this.rotation = 0;
        }
    }

    private Frame() {
        this.zzap = new Metadata();
        this.zzaq = null;
        this.zzar = null;
    }

    public Bitmap getBitmap() {
        return this.zzar;
    }

    public ByteBuffer getGrayscaleImageData() {
        Bitmap bitmap = this.zzar;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = this.zzar.getHeight();
            int i = width * height;
            int[] iArr = new int[i];
            this.zzar.getPixels(iArr, 0, width, 0, 0, width, height);
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                bArr[i2] = (byte) ((Color.blue(iArr[i2]) * 0.114f) + (Color.green(iArr[i2]) * 0.587f) + (Color.red(iArr[i2]) * 0.299f));
            }
            return ByteBuffer.wrap(bArr);
        }
        return this.zzaq;
    }

    public Metadata getMetadata() {
        return this.zzap;
    }
}
