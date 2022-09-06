package com.google.android.gms.vision.face;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.zzc;
import java.nio.ByteBuffer;
import java.util.HashSet;
import javax.annotation.concurrent.GuardedBy;
import org.aspectj.lang.JoinPoint;
/* loaded from: classes2.dex */
public final class FaceDetector extends Detector<Face> {
    public static final int ACCURATE_MODE = 1;
    public static final int ALL_CLASSIFICATIONS = 1;
    public static final int ALL_LANDMARKS = 1;
    public static final int FAST_MODE = 0;
    public static final int NO_CLASSIFICATIONS = 0;
    public static final int NO_LANDMARKS = 0;
    private final Object lock;
    private final zzc zzbv;
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private final com.google.android.gms.vision.face.internal.client.zza zzbw;
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private boolean zzbx;

    /* loaded from: classes2.dex */
    public static class Builder {
        private final Context zze;
        private int zzby = 0;
        private boolean zzbz = false;
        private int zzca = 0;
        private boolean zzcb = true;
        private int mode = 0;
        private float zzcc = -1.0f;

        public Builder(Context context) {
            this.zze = context;
        }

        public FaceDetector build() {
            com.google.android.gms.vision.face.internal.client.zzc zzcVar = new com.google.android.gms.vision.face.internal.client.zzc();
            zzcVar.mode = this.mode;
            zzcVar.zzby = this.zzby;
            zzcVar.zzca = this.zzca;
            zzcVar.zzbz = this.zzbz;
            zzcVar.zzcb = this.zzcb;
            zzcVar.zzcc = this.zzcc;
            return new FaceDetector(new com.google.android.gms.vision.face.internal.client.zza(this.zze, zzcVar));
        }

        public Builder setClassificationType(int i) {
            if (i == 0 || i == 1) {
                this.zzca = i;
                return this;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(40, "Invalid classification type: ", i));
        }

        public Builder setLandmarkType(int i) {
            if (i == 0 || i == 1) {
                this.zzby = i;
                return this;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(34, "Invalid landmark type: ", i));
        }

        public Builder setMinFaceSize(float f) {
            if (f >= 0.0f && f <= 1.0f) {
                this.zzcc = f;
                return this;
            }
            StringBuilder sb = new StringBuilder(47);
            sb.append("Invalid proportional face size: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setMode(int i) {
            if (i == 0 || i == 1) {
                this.mode = i;
                return this;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(25, "Invalid mode: ", i));
        }

        public Builder setProminentFaceOnly(boolean z) {
            this.zzbz = z;
            return this;
        }

        public Builder setTrackingEnabled(boolean z) {
            this.zzcb = z;
            return this;
        }
    }

    private FaceDetector() {
        this.zzbv = new zzc();
        this.lock = new Object();
        this.zzbx = true;
        throw new IllegalStateException("Default constructor called");
    }

    private FaceDetector(com.google.android.gms.vision.face.internal.client.zza zzaVar) {
        this.zzbv = new zzc();
        this.lock = new Object();
        this.zzbx = true;
        this.zzbw = zzaVar;
    }

    @Override // com.google.android.gms.vision.Detector
    public final SparseArray<Face> detect(Frame frame) {
        Face[] zzb;
        if (frame != null) {
            ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
            synchronized (this.lock) {
                if (!this.zzbx) {
                    throw new RuntimeException("Cannot use detector after release()");
                }
                zzb = this.zzbw.zzb(grayscaleImageData, zzm.zzc(frame));
            }
            HashSet hashSet = new HashSet();
            SparseArray<Face> sparseArray = new SparseArray<>(zzb.length);
            int i = 0;
            for (Face face : zzb) {
                int id = face.getId();
                i = Math.max(i, id);
                if (hashSet.contains(Integer.valueOf(id))) {
                    id = i + 1;
                    i = id;
                }
                hashSet.add(Integer.valueOf(id));
                sparseArray.append(this.zzbv.zzb(id), face);
            }
            return sparseArray;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    protected final void finalize() throws Throwable {
        try {
            synchronized (this.lock) {
                if (this.zzbx) {
                    Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
                    release();
                }
            }
        } finally {
            super.finalize();
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean isOperational() {
        return this.zzbw.isOperational();
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        synchronized (this.lock) {
            if (!this.zzbx) {
                return;
            }
            this.zzbw.zzo();
            this.zzbx = false;
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean setFocus(int i) {
        boolean zzd;
        int zzc = this.zzbv.zzc(i);
        synchronized (this.lock) {
            if (!this.zzbx) {
                throw new RuntimeException("Cannot use detector after release()");
            }
            zzd = this.zzbw.zzd(zzc);
        }
        return zzd;
    }
}
