package com.amazon.ptz.digital;

import android.view.View;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes13.dex */
public class DigitalZoomState {
    private static final float EPSILON = 0.05f;
    @Nonnull
    private final View view;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public DigitalZoomState(@Nonnull View view) {
        if (view != null) {
            this.view = view;
            return;
        }
        throw new IllegalArgumentException("view is null");
    }

    private static boolean areAlmostEqual(float f, float f2) {
        return Math.abs(f - f2) < EPSILON;
    }

    private float getCurrentZoomScale() {
        return this.view.getScaleX();
    }

    public boolean isZoomedOut() {
        return areAlmostEqual(getCurrentZoomScale(), DigitalMagnitudes.FULL_ZOOM_OUT.getMagnitude());
    }
}
