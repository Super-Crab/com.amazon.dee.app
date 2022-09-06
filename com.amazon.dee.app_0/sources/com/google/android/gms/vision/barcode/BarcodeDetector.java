package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
/* loaded from: classes2.dex */
public final class BarcodeDetector extends Detector<Barcode> {
    private final com.google.android.gms.internal.vision.zzg zzbl;

    /* loaded from: classes2.dex */
    public static class Builder {
        private com.google.android.gms.internal.vision.zze zzbm = new com.google.android.gms.internal.vision.zze();
        private Context zze;

        public Builder(Context context) {
            this.zze = context;
        }

        public BarcodeDetector build() {
            return new BarcodeDetector(new com.google.android.gms.internal.vision.zzg(this.zze, this.zzbm));
        }

        public Builder setBarcodeFormats(int i) {
            this.zzbm.zzbn = i;
            return this;
        }
    }

    private BarcodeDetector() {
        throw new IllegalStateException("Default constructor called");
    }

    private BarcodeDetector(com.google.android.gms.internal.vision.zzg zzgVar) {
        this.zzbl = zzgVar;
    }

    @Override // com.google.android.gms.vision.Detector
    public final SparseArray<Barcode> detect(Frame frame) {
        Barcode[] zza;
        if (frame != null) {
            com.google.android.gms.internal.vision.zzm zzc = com.google.android.gms.internal.vision.zzm.zzc(frame);
            if (frame.getBitmap() != null) {
                zza = this.zzbl.zza(frame.getBitmap(), zzc);
                if (zza == null) {
                    throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
                }
            } else {
                zza = this.zzbl.zza(frame.getGrayscaleImageData(), zzc);
            }
            SparseArray<Barcode> sparseArray = new SparseArray<>(zza.length);
            for (Barcode barcode : zza) {
                sparseArray.append(barcode.rawValue.hashCode(), barcode);
            }
            return sparseArray;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean isOperational() {
        return this.zzbl.isOperational();
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        this.zzbl.zzo();
    }
}
