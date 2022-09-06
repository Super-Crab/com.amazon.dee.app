package com.google.android.material.bottomappbar;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;
/* loaded from: classes2.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private float cradleVerticalOffset;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f, float f2, float f3) {
        this.fabMargin = f;
        this.roundedCornerRadius = f2;
        this.cradleVerticalOffset = f3;
        if (f3 >= 0.0f) {
            this.horizontalOffset = 0.0f;
            return;
        }
        throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, ShapePath shapePath) {
        float f3 = this.fabDiameter;
        if (f3 == 0.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f4 = ((this.fabMargin * 2.0f) + f3) / 2.0f;
        float f5 = f2 * this.roundedCornerRadius;
        float f6 = (f / 2.0f) + this.horizontalOffset;
        float outline0 = GeneratedOutlineSupport1.outline0(1.0f, f2, f4, this.cradleVerticalOffset * f2);
        if (outline0 / f4 >= 1.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f7 = f4 + f5;
        float f8 = outline0 + f5;
        float sqrt = (float) Math.sqrt((f7 * f7) - (f8 * f8));
        float f9 = f6 - sqrt;
        float f10 = f6 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan(sqrt / f8));
        float f11 = 90.0f - degrees;
        float f12 = f9 - f5;
        shapePath.lineTo(f12, 0.0f);
        float f13 = f5 * 2.0f;
        shapePath.addArc(f12, 0.0f, f9 + f5, f13, 270.0f, degrees);
        shapePath.addArc(f6 - f4, (-f4) - outline0, f6 + f4, f4 - outline0, 180.0f - f11, (f11 * 2.0f) - 180.0f);
        shapePath.addArc(f10 - f5, 0.0f, f10 + f5, f13, 270.0f - degrees, degrees);
        shapePath.lineTo(f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFabDiameter() {
        return this.fabDiameter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCradleVerticalOffset(float f) {
        this.cradleVerticalOffset = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFabCradleMargin(float f) {
        this.fabMargin = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFabCradleRoundedCornerRadius(float f) {
        this.roundedCornerRadius = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFabDiameter(float f) {
        this.fabDiameter = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHorizontalOffset(float f) {
        this.horizontalOffset = f;
    }
}
