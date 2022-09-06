package com.google.android.gms.vision;

import android.util.SparseArray;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.vision.Detector;
import java.util.HashSet;
/* loaded from: classes2.dex */
public class MultiProcessor<T> implements Detector.Processor<T> {
    private int zzal;
    private Factory<T> zzaz;
    private SparseArray<zza> zzba;

    /* loaded from: classes2.dex */
    public static class Builder<T> {
        private MultiProcessor<T> zzbb = new MultiProcessor<>();

        public Builder(Factory<T> factory) {
            if (factory != null) {
                ((MultiProcessor) this.zzbb).zzaz = factory;
                return;
            }
            throw new IllegalArgumentException("No factory supplied.");
        }

        public MultiProcessor<T> build() {
            return this.zzbb;
        }

        public Builder<T> setMaxGapFrames(int i) {
            if (i >= 0) {
                ((MultiProcessor) this.zzbb).zzal = i;
                return this;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(28, "Invalid max gap: ", i));
        }
    }

    /* loaded from: classes2.dex */
    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    /* loaded from: classes2.dex */
    class zza {
        private Tracker<T> zzak;
        private int zzao;

        private zza(MultiProcessor multiProcessor) {
            this.zzao = 0;
        }

        static /* synthetic */ int zzb(zza zzaVar) {
            int i = zzaVar.zzao;
            zzaVar.zzao = i + 1;
            return i;
        }
    }

    private MultiProcessor() {
        this.zzba = new SparseArray<>();
        this.zzal = 3;
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void receiveDetections(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            T valueAt = detectedItems.valueAt(i);
            if (this.zzba.get(keyAt) == null) {
                zza zzaVar = new zza();
                zzaVar.zzak = this.zzaz.create(valueAt);
                zzaVar.zzak.onNewItem(keyAt, valueAt);
                this.zzba.append(keyAt, zzaVar);
            }
        }
        SparseArray<T> detectedItems2 = detections.getDetectedItems();
        HashSet<Integer> hashSet = new HashSet();
        for (int i2 = 0; i2 < this.zzba.size(); i2++) {
            int keyAt2 = this.zzba.keyAt(i2);
            if (detectedItems2.get(keyAt2) == null) {
                zza valueAt2 = this.zzba.valueAt(i2);
                zza.zzb(valueAt2);
                if (valueAt2.zzao >= this.zzal) {
                    valueAt2.zzak.onDone();
                    hashSet.add(Integer.valueOf(keyAt2));
                } else {
                    valueAt2.zzak.onMissing(detections);
                }
            }
        }
        for (Integer num : hashSet) {
            this.zzba.delete(num.intValue());
        }
        SparseArray<T> detectedItems3 = detections.getDetectedItems();
        for (int i3 = 0; i3 < detectedItems3.size(); i3++) {
            int keyAt3 = detectedItems3.keyAt(i3);
            T valueAt3 = detectedItems3.valueAt(i3);
            zza zzaVar2 = this.zzba.get(keyAt3);
            zzaVar2.zzao = 0;
            zzaVar2.zzak.onUpdate(detections, valueAt3);
        }
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void release() {
        for (int i = 0; i < this.zzba.size(); i++) {
            this.zzba.valueAt(i).zzak.onDone();
        }
        this.zzba.clear();
    }
}
