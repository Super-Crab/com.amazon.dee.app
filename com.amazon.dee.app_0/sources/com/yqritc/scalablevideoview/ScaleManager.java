package com.yqritc.scalablevideoview;

import android.graphics.Matrix;
/* loaded from: classes3.dex */
public class ScaleManager {
    private Size mVideoSize;
    private Size mViewSize;

    /* renamed from: com.yqritc.scalablevideoview.ScaleManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$yqritc$scalablevideoview$PivotPoint = new int[PivotPoint.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$yqritc$scalablevideoview$ScalableType;

        static {
            try {
                $SwitchMap$com$yqritc$scalablevideoview$PivotPoint[PivotPoint.LEFT_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$PivotPoint[PivotPoint.LEFT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$PivotPoint[PivotPoint.LEFT_BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$PivotPoint[PivotPoint.CENTER_TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$PivotPoint[PivotPoint.CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$PivotPoint[PivotPoint.CENTER_BOTTOM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$PivotPoint[PivotPoint.RIGHT_TOP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$PivotPoint[PivotPoint.RIGHT_CENTER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$PivotPoint[PivotPoint.RIGHT_BOTTOM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            $SwitchMap$com$yqritc$scalablevideoview$ScalableType = new int[ScalableType.values().length];
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.FIT_XY.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.LEFT_TOP.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.LEFT_CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.LEFT_BOTTOM.ordinal()] = 8;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.CENTER_TOP.ordinal()] = 9;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.CENTER.ordinal()] = 10;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.CENTER_BOTTOM.ordinal()] = 11;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.RIGHT_TOP.ordinal()] = 12;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.RIGHT_CENTER.ordinal()] = 13;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.RIGHT_BOTTOM.ordinal()] = 14;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.LEFT_TOP_CROP.ordinal()] = 15;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.LEFT_CENTER_CROP.ordinal()] = 16;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.LEFT_BOTTOM_CROP.ordinal()] = 17;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.CENTER_TOP_CROP.ordinal()] = 18;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.CENTER_CROP.ordinal()] = 19;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.CENTER_BOTTOM_CROP.ordinal()] = 20;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.RIGHT_TOP_CROP.ordinal()] = 21;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.RIGHT_CENTER_CROP.ordinal()] = 22;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.RIGHT_BOTTOM_CROP.ordinal()] = 23;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.START_INSIDE.ordinal()] = 24;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.CENTER_INSIDE.ordinal()] = 25;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$yqritc$scalablevideoview$ScalableType[ScalableType.END_INSIDE.ordinal()] = 26;
            } catch (NoSuchFieldError unused35) {
            }
        }
    }

    public ScaleManager(Size size, Size size2) {
        this.mViewSize = size;
        this.mVideoSize = size2;
    }

    private Matrix centerInside() {
        if (this.mVideoSize.getHeight() <= this.mViewSize.getWidth() && this.mVideoSize.getHeight() <= this.mViewSize.getHeight()) {
            return getOriginalScale(PivotPoint.CENTER);
        }
        return fitCenter();
    }

    private Matrix endInside() {
        if (this.mVideoSize.getHeight() <= this.mViewSize.getWidth() && this.mVideoSize.getHeight() <= this.mViewSize.getHeight()) {
            return getOriginalScale(PivotPoint.RIGHT_BOTTOM);
        }
        return fitEnd();
    }

    private Matrix fitCenter() {
        return getFitScale(PivotPoint.CENTER);
    }

    private Matrix fitEnd() {
        return getFitScale(PivotPoint.RIGHT_BOTTOM);
    }

    private Matrix fitStart() {
        return getFitScale(PivotPoint.LEFT_TOP);
    }

    private Matrix fitXY() {
        return getMatrix(1.0f, 1.0f, PivotPoint.LEFT_TOP);
    }

    private Matrix getCropScale(PivotPoint pivotPoint) {
        float width = this.mViewSize.getWidth() / this.mVideoSize.getWidth();
        float height = this.mViewSize.getHeight() / this.mVideoSize.getHeight();
        float max = Math.max(width, height);
        return getMatrix(max / width, max / height, pivotPoint);
    }

    private Matrix getFitScale(PivotPoint pivotPoint) {
        float width = this.mViewSize.getWidth() / this.mVideoSize.getWidth();
        float height = this.mViewSize.getHeight() / this.mVideoSize.getHeight();
        float min = Math.min(width, height);
        return getMatrix(min / width, min / height, pivotPoint);
    }

    private Matrix getMatrix(float f, float f2, float f3, float f4) {
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2, f3, f4);
        return matrix;
    }

    private Matrix getNoScale() {
        return getMatrix(this.mVideoSize.getWidth() / this.mViewSize.getWidth(), this.mVideoSize.getHeight() / this.mViewSize.getHeight(), PivotPoint.LEFT_TOP);
    }

    private Matrix getOriginalScale(PivotPoint pivotPoint) {
        return getMatrix(this.mVideoSize.getWidth() / this.mViewSize.getWidth(), this.mVideoSize.getHeight() / this.mViewSize.getHeight(), pivotPoint);
    }

    private Matrix startInside() {
        if (this.mVideoSize.getHeight() <= this.mViewSize.getWidth() && this.mVideoSize.getHeight() <= this.mViewSize.getHeight()) {
            return getOriginalScale(PivotPoint.LEFT_TOP);
        }
        return fitStart();
    }

    public Matrix getScaleMatrix(ScalableType scalableType) {
        switch (scalableType.ordinal()) {
            case 0:
                return getNoScale();
            case 1:
                return fitXY();
            case 2:
                return fitStart();
            case 3:
                return fitCenter();
            case 4:
                return fitEnd();
            case 5:
                return getOriginalScale(PivotPoint.LEFT_TOP);
            case 6:
                return getOriginalScale(PivotPoint.LEFT_CENTER);
            case 7:
                return getOriginalScale(PivotPoint.LEFT_BOTTOM);
            case 8:
                return getOriginalScale(PivotPoint.CENTER_TOP);
            case 9:
                return getOriginalScale(PivotPoint.CENTER);
            case 10:
                return getOriginalScale(PivotPoint.CENTER_BOTTOM);
            case 11:
                return getOriginalScale(PivotPoint.RIGHT_TOP);
            case 12:
                return getOriginalScale(PivotPoint.RIGHT_CENTER);
            case 13:
                return getOriginalScale(PivotPoint.RIGHT_BOTTOM);
            case 14:
                return getCropScale(PivotPoint.LEFT_TOP);
            case 15:
                return getCropScale(PivotPoint.LEFT_CENTER);
            case 16:
                return getCropScale(PivotPoint.LEFT_BOTTOM);
            case 17:
                return getCropScale(PivotPoint.CENTER_TOP);
            case 18:
                return getCropScale(PivotPoint.CENTER);
            case 19:
                return getCropScale(PivotPoint.CENTER_BOTTOM);
            case 20:
                return getCropScale(PivotPoint.RIGHT_TOP);
            case 21:
                return getCropScale(PivotPoint.RIGHT_CENTER);
            case 22:
                return getCropScale(PivotPoint.RIGHT_BOTTOM);
            case 23:
                return startInside();
            case 24:
                return centerInside();
            case 25:
                return endInside();
            default:
                return null;
        }
    }

    private Matrix getMatrix(float f, float f2, PivotPoint pivotPoint) {
        switch (pivotPoint.ordinal()) {
            case 0:
                return getMatrix(f, f2, 0.0f, 0.0f);
            case 1:
                return getMatrix(f, f2, 0.0f, this.mViewSize.getHeight() / 2.0f);
            case 2:
                return getMatrix(f, f2, 0.0f, this.mViewSize.getHeight());
            case 3:
                return getMatrix(f, f2, this.mViewSize.getWidth() / 2.0f, 0.0f);
            case 4:
                return getMatrix(f, f2, this.mViewSize.getWidth() / 2.0f, this.mViewSize.getHeight() / 2.0f);
            case 5:
                return getMatrix(f, f2, this.mViewSize.getWidth() / 2.0f, this.mViewSize.getHeight());
            case 6:
                return getMatrix(f, f2, this.mViewSize.getWidth(), 0.0f);
            case 7:
                return getMatrix(f, f2, this.mViewSize.getWidth(), this.mViewSize.getHeight() / 2.0f);
            case 8:
                return getMatrix(f, f2, this.mViewSize.getWidth(), this.mViewSize.getHeight());
            default:
                throw new IllegalArgumentException("Illegal PivotPoint");
        }
    }
}
