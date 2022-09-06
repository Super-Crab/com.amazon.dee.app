package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzad;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.internal.vision.zzo;
import com.google.android.gms.internal.vision.zzx;
import com.google.android.gms.internal.vision.zzz;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public final class TextRecognizer extends Detector<TextBlock> {
    private final zzad zzdf;

    /* loaded from: classes2.dex */
    public static class Builder {
        private zzae zzdg = new zzae();
        private Context zze;

        public Builder(Context context) {
            this.zze = context;
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzad(this.zze, this.zzdg));
        }
    }

    private TextRecognizer() {
        throw new IllegalStateException("Default constructor called");
    }

    private TextRecognizer(zzad zzadVar) {
        this.zzdf = zzadVar;
    }

    @Override // com.google.android.gms.vision.Detector
    public final SparseArray<TextBlock> detect(Frame frame) {
        byte[] bArr;
        Bitmap decodeByteArray;
        zzz zzzVar = new zzz(new Rect());
        if (frame != null) {
            zzm zzc = zzm.zzc(frame);
            if (frame.getBitmap() != null) {
                decodeByteArray = frame.getBitmap();
            } else {
                Frame.Metadata metadata = frame.getMetadata();
                ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
                int format = metadata.getFormat();
                int i = zzc.width;
                int i2 = zzc.height;
                if (!grayscaleImageData.hasArray() || grayscaleImageData.arrayOffset() != 0) {
                    byte[] bArr2 = new byte[grayscaleImageData.capacity()];
                    grayscaleImageData.get(bArr2);
                    bArr = bArr2;
                } else {
                    bArr = grayscaleImageData.array();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new YuvImage(bArr, format, i, i2, null).compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            Bitmap zzb = zzo.zzb(decodeByteArray, zzc);
            if (!zzzVar.zzdr.isEmpty()) {
                Rect rect = zzzVar.zzdr;
                int width = frame.getMetadata().getWidth();
                int height = frame.getMetadata().getHeight();
                int i3 = zzc.rotation;
                zzzVar.zzdr.set(i3 != 1 ? i3 != 2 ? i3 != 3 ? rect : new Rect(rect.top, width - rect.right, rect.bottom, width - rect.left) : new Rect(width - rect.right, height - rect.bottom, width - rect.left, height - rect.top) : new Rect(height - rect.bottom, rect.left, height - rect.top, rect.right));
            }
            zzc.rotation = 0;
            zzx[] zza = this.zzdf.zza(zzb, zzc, zzzVar);
            SparseArray sparseArray = new SparseArray();
            for (zzx zzxVar : zza) {
                SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzxVar.zzdp);
                if (sparseArray2 == null) {
                    sparseArray2 = new SparseArray();
                    sparseArray.append(zzxVar.zzdp, sparseArray2);
                }
                sparseArray2.append(zzxVar.zzdq, zzxVar);
            }
            SparseArray<TextBlock> sparseArray3 = new SparseArray<>(sparseArray.size());
            for (int i4 = 0; i4 < sparseArray.size(); i4++) {
                sparseArray3.append(sparseArray.keyAt(i4), new TextBlock((SparseArray) sparseArray.valueAt(i4)));
            }
            return sparseArray3;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean isOperational() {
        return this.zzdf.isOperational();
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        this.zzdf.zzo();
    }
}
