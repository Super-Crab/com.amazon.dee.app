package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class MultiDetector extends Detector<Object> {
    private List<Detector<? extends Object>> zzax;

    /* loaded from: classes2.dex */
    public static class Builder {
        private MultiDetector zzay = new MultiDetector();

        public Builder add(Detector<? extends Object> detector) {
            this.zzay.zzax.add(detector);
            return this;
        }

        public MultiDetector build() {
            if (this.zzay.zzax.size() != 0) {
                return this.zzay;
            }
            throw new RuntimeException("No underlying detectors added to MultiDetector.");
        }
    }

    private MultiDetector() {
        this.zzax = new ArrayList();
    }

    @Override // com.google.android.gms.vision.Detector
    public SparseArray<Object> detect(Frame frame) {
        SparseArray<Object> sparseArray = new SparseArray<>();
        for (Detector<? extends Object> detector : this.zzax) {
            SparseArray<? extends Object> detect = detector.detect(frame);
            for (int i = 0; i < detect.size(); i++) {
                int keyAt = detect.keyAt(i);
                if (sparseArray.get(keyAt) != null) {
                    StringBuilder sb = new StringBuilder(104);
                    sb.append("Detection ID overlap for id = ");
                    sb.append(keyAt);
                    sb.append("  This means that one of the detectors is not using global IDs.");
                    throw new IllegalStateException(sb.toString());
                }
                sparseArray.append(keyAt, detect.valueAt(i));
            }
        }
        return sparseArray;
    }

    @Override // com.google.android.gms.vision.Detector
    public boolean isOperational() {
        for (Detector<? extends Object> detector : this.zzax) {
            if (!detector.isOperational()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.vision.Detector
    public void receiveFrame(Frame frame) {
        for (Detector<? extends Object> detector : this.zzax) {
            detector.receiveFrame(frame);
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public void release() {
        for (Detector<? extends Object> detector : this.zzax) {
            detector.release();
        }
        this.zzax.clear();
    }

    @Override // com.google.android.gms.vision.Detector
    public void setProcessor(Detector.Processor<Object> processor) {
        throw new UnsupportedOperationException("MultiDetector.setProcessor is not supported.  You should set a processor instance on each underlying detector instead.");
    }
}
