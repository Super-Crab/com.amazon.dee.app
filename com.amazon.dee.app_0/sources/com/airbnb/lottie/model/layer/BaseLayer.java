package com.airbnb.lottie.model.layer;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.CallSuper;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.value.LottieValueCallback;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public abstract class BaseLayer implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    private static final int CLIP_SAVE_FLAG = 2;
    private static final int CLIP_TO_LAYER_SAVE_FLAG = 16;
    private static final int MATRIX_SAVE_FLAG = 1;
    private static final int SAVE_FLAGS = 19;
    private final String drawTraceName;
    final Layer layerModel;
    final LottieDrawable lottieDrawable;
    @Nullable
    private MaskKeyframeAnimation mask;
    @Nullable
    private BaseLayer matteLayer;
    @Nullable
    private BaseLayer parentLayer;
    private List<BaseLayer> parentLayers;
    final TransformKeyframeAnimation transform;
    private final Path path = new Path();
    private final Matrix matrix = new Matrix();
    private final Paint contentPaint = new LPaint(1);
    private final Paint dstInPaint = new LPaint(1, PorterDuff.Mode.DST_IN);
    private final Paint dstOutPaint = new LPaint(1, PorterDuff.Mode.DST_OUT);
    private final Paint mattePaint = new LPaint(1);
    private final Paint clearPaint = new LPaint(PorterDuff.Mode.CLEAR);
    private final RectF rect = new RectF();
    private final RectF maskBoundsRect = new RectF();
    private final RectF matteBoundsRect = new RectF();
    private final RectF tempMaskBoundsRect = new RectF();
    final Matrix boundsMatrix = new Matrix();
    private final List<BaseKeyframeAnimation<?, ?>> animations = new ArrayList();
    private boolean visible = true;

    /* renamed from: com.airbnb.lottie.model.layer.BaseLayer$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode = new int[Mask.MaskMode.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType;

        static {
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_SUBTRACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_INTERSECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_ADD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType = new int[Layer.LayerType.values().length];
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseLayer(LottieDrawable lottieDrawable, Layer layer) {
        this.lottieDrawable = lottieDrawable;
        this.layerModel = layer;
        this.drawTraceName = GeneratedOutlineSupport1.outline91(new StringBuilder(), layer.getName(), "#draw");
        if (layer.getMatteType() == Layer.MatteType.INVERT) {
            this.mattePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            this.mattePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        this.transform = layer.getTransform().createAnimation();
        this.transform.addListener(this);
        if (layer.getMasks() != null && !layer.getMasks().isEmpty()) {
            this.mask = new MaskKeyframeAnimation(layer.getMasks());
            for (BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation : this.mask.getMaskAnimations()) {
                baseKeyframeAnimation.addUpdateListener(this);
            }
            for (BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 : this.mask.getOpacityAnimations()) {
                addAnimation(baseKeyframeAnimation2);
                baseKeyframeAnimation2.addUpdateListener(this);
            }
        }
        setupInOutAnimations();
    }

    private void applyAddMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        this.path.set(baseKeyframeAnimation.mo268getValue());
        this.path.transform(matrix);
        this.contentPaint.setAlpha((int) (baseKeyframeAnimation2.mo268getValue().intValue() * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
    }

    private void applyIntersectMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        saveLayerCompat(canvas, this.rect, this.dstInPaint, true);
        this.path.set(baseKeyframeAnimation.mo268getValue());
        this.path.transform(matrix);
        this.contentPaint.setAlpha((int) (baseKeyframeAnimation2.mo268getValue().intValue() * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
        canvas.restore();
    }

    private void applyInvertedAddMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        saveLayerCompat(canvas, this.rect, this.contentPaint, true);
        canvas.drawRect(this.rect, this.contentPaint);
        this.path.set(baseKeyframeAnimation.mo268getValue());
        this.path.transform(matrix);
        this.contentPaint.setAlpha((int) (baseKeyframeAnimation2.mo268getValue().intValue() * 2.55f));
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyInvertedIntersectMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        saveLayerCompat(canvas, this.rect, this.dstInPaint, true);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (baseKeyframeAnimation2.mo268getValue().intValue() * 2.55f));
        this.path.set(baseKeyframeAnimation.mo268getValue());
        this.path.transform(matrix);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyInvertedSubtractMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        saveLayerCompat(canvas, this.rect, this.dstOutPaint, true);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (baseKeyframeAnimation2.mo268getValue().intValue() * 2.55f));
        this.path.set(baseKeyframeAnimation.mo268getValue());
        this.path.transform(matrix);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyMasks(Canvas canvas, Matrix matrix) {
        L.beginSection("Layer#saveLayer");
        saveLayerCompat(canvas, this.rect, this.dstInPaint, false);
        L.endSection("Layer#saveLayer");
        for (int i = 0; i < this.mask.getMasks().size(); i++) {
            Mask mask = this.mask.getMasks().get(i);
            BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation = this.mask.getMaskAnimations().get(i);
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.mask.getOpacityAnimations().get(i);
            int ordinal = mask.getMaskMode().ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    if (i == 0) {
                        Paint paint = new Paint();
                        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                        canvas.drawRect(this.rect, paint);
                    }
                    if (mask.isInverted()) {
                        applyInvertedSubtractMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                    } else {
                        applySubtractMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                    }
                } else if (ordinal == 2) {
                    if (mask.isInverted()) {
                        applyInvertedIntersectMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                    } else {
                        applyIntersectMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                    }
                }
            } else if (mask.isInverted()) {
                applyInvertedAddMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
            } else {
                applyAddMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
            }
        }
        L.beginSection("Layer#restoreLayer");
        canvas.restore();
        L.endSection("Layer#restoreLayer");
    }

    private void applySubtractMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        this.path.set(baseKeyframeAnimation.mo268getValue());
        this.path.transform(matrix);
        canvas.drawPath(this.path, this.dstOutPaint);
    }

    private void buildParentLayerListIfNeeded() {
        if (this.parentLayers != null) {
            return;
        }
        if (this.parentLayer == null) {
            this.parentLayers = Collections.emptyList();
            return;
        }
        this.parentLayers = new ArrayList();
        for (BaseLayer baseLayer = this.parentLayer; baseLayer != null; baseLayer = baseLayer.parentLayer) {
            this.parentLayers.add(baseLayer);
        }
    }

    private void clearCanvas(Canvas canvas) {
        L.beginSection("Layer#clearLayer");
        RectF rectF = this.rect;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.clearPaint);
        L.endSection("Layer#clearLayer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static BaseLayer forModel(Layer layer, LottieDrawable lottieDrawable, LottieComposition lottieComposition) {
        int ordinal = layer.getLayerType().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new SolidLayer(lottieDrawable, layer);
            }
            if (ordinal == 2) {
                return new ImageLayer(lottieDrawable, layer);
            }
            if (ordinal == 3) {
                return new NullLayer(lottieDrawable, layer);
            }
            if (ordinal == 4) {
                return new ShapeLayer(lottieDrawable, layer);
            }
            if (ordinal != 5) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown layer type ");
                outline107.append(layer.getLayerType());
                Logger.warning(outline107.toString());
                return null;
            }
            return new TextLayer(lottieDrawable, layer);
        }
        return new CompositionLayer(lottieDrawable, layer, lottieComposition.getPrecomps(layer.getRefId()), lottieComposition);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0051, code lost:
        if (r5 != 2) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void intersectBoundsWithMask(android.graphics.RectF r11, android.graphics.Matrix r12) {
        /*
            r10 = this;
            android.graphics.RectF r0 = r10.maskBoundsRect
            r1 = 0
            r0.set(r1, r1, r1, r1)
            boolean r0 = r10.hasMasksOnThisLayer()
            if (r0 != 0) goto Ld
            return
        Ld:
            com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation r0 = r10.mask
            java.util.List r0 = r0.getMasks()
            int r0 = r0.size()
            r2 = 0
            r3 = r2
        L19:
            if (r3 >= r0) goto La4
            com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation r4 = r10.mask
            java.util.List r4 = r4.getMasks()
            java.lang.Object r4 = r4.get(r3)
            com.airbnb.lottie.model.content.Mask r4 = (com.airbnb.lottie.model.content.Mask) r4
            com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation r5 = r10.mask
            java.util.List r5 = r5.getMaskAnimations()
            java.lang.Object r5 = r5.get(r3)
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r5 = (com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation) r5
            java.lang.Object r5 = r5.mo268getValue()
            android.graphics.Path r5 = (android.graphics.Path) r5
            android.graphics.Path r6 = r10.path
            r6.set(r5)
            android.graphics.Path r5 = r10.path
            r5.transform(r12)
            com.airbnb.lottie.model.content.Mask$MaskMode r5 = r4.getMaskMode()
            int r5 = r5.ordinal()
            if (r5 == 0) goto L55
            r6 = 1
            if (r5 == r6) goto L54
            r6 = 2
            if (r5 == r6) goto L55
            goto L5c
        L54:
            return
        L55:
            boolean r4 = r4.isInverted()
            if (r4 == 0) goto L5c
            return
        L5c:
            android.graphics.Path r4 = r10.path
            android.graphics.RectF r5 = r10.tempMaskBoundsRect
            r4.computeBounds(r5, r2)
            if (r3 != 0) goto L6d
            android.graphics.RectF r4 = r10.maskBoundsRect
            android.graphics.RectF r5 = r10.tempMaskBoundsRect
            r4.set(r5)
            goto La0
        L6d:
            android.graphics.RectF r4 = r10.maskBoundsRect
            float r5 = r4.left
            android.graphics.RectF r6 = r10.tempMaskBoundsRect
            float r6 = r6.left
            float r5 = java.lang.Math.min(r5, r6)
            android.graphics.RectF r6 = r10.maskBoundsRect
            float r6 = r6.top
            android.graphics.RectF r7 = r10.tempMaskBoundsRect
            float r7 = r7.top
            float r6 = java.lang.Math.min(r6, r7)
            android.graphics.RectF r7 = r10.maskBoundsRect
            float r7 = r7.right
            android.graphics.RectF r8 = r10.tempMaskBoundsRect
            float r8 = r8.right
            float r7 = java.lang.Math.max(r7, r8)
            android.graphics.RectF r8 = r10.maskBoundsRect
            float r8 = r8.bottom
            android.graphics.RectF r9 = r10.tempMaskBoundsRect
            float r9 = r9.bottom
            float r8 = java.lang.Math.max(r8, r9)
            r4.set(r5, r6, r7, r8)
        La0:
            int r3 = r3 + 1
            goto L19
        La4:
            android.graphics.RectF r12 = r10.maskBoundsRect
            boolean r12 = r11.intersect(r12)
            if (r12 != 0) goto Laf
            r11.set(r1, r1, r1, r1)
        Laf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.BaseLayer.intersectBoundsWithMask(android.graphics.RectF, android.graphics.Matrix):void");
    }

    private void intersectBoundsWithMatte(RectF rectF, Matrix matrix) {
        if (hasMatteOnThisLayer() && this.layerModel.getMatteType() != Layer.MatteType.INVERT) {
            this.matteBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.matteLayer.getBounds(this.matteBoundsRect, matrix, true);
            if (rectF.intersect(this.matteBoundsRect)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void invalidateSelf() {
        this.lottieDrawable.invalidateSelf();
    }

    private void recordRenderTime(float f) {
        this.lottieDrawable.getComposition().getPerformanceTracker().recordRenderTime(this.layerModel.getName(), f);
    }

    @SuppressLint({"WrongConstant"})
    private void saveLayerCompat(Canvas canvas, RectF rectF, Paint paint, boolean z) {
        int i = Build.VERSION.SDK_INT;
        canvas.saveLayer(rectF, paint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVisible(boolean z) {
        if (z != this.visible) {
            this.visible = z;
            invalidateSelf();
        }
    }

    private void setupInOutAnimations() {
        boolean z = true;
        if (!this.layerModel.getInOutKeyframes().isEmpty()) {
            final FloatKeyframeAnimation floatKeyframeAnimation = new FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            floatKeyframeAnimation.setIsDiscrete();
            floatKeyframeAnimation.addUpdateListener(new BaseKeyframeAnimation.AnimationListener() { // from class: com.airbnb.lottie.model.layer.BaseLayer.1
                @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
                public void onValueChanged() {
                    BaseLayer.this.setVisible(floatKeyframeAnimation.getFloatValue() == 1.0f);
                }
            });
            if (floatKeyframeAnimation.mo268getValue().floatValue() != 1.0f) {
                z = false;
            }
            setVisible(z);
            addAnimation(floatKeyframeAnimation);
            return;
        }
        setVisible(true);
    }

    public void addAnimation(@Nullable BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        if (baseKeyframeAnimation == null) {
            return;
        }
        this.animations.add(baseKeyframeAnimation);
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    @CallSuper
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        this.transform.applyValueCallback(t, lottieValueCallback);
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix matrix, int i) {
        L.beginSection(this.drawTraceName);
        if (this.visible && !this.layerModel.isHidden()) {
            buildParentLayerListIfNeeded();
            L.beginSection("Layer#parentMatrix");
            this.matrix.reset();
            this.matrix.set(matrix);
            for (int size = this.parentLayers.size() - 1; size >= 0; size--) {
                this.matrix.preConcat(this.parentLayers.get(size).transform.getMatrix());
            }
            L.endSection("Layer#parentMatrix");
            int intValue = (int) ((((i / 255.0f) * (this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().mo268getValue().intValue())) / 100.0f) * 255.0f);
            if (!hasMatteOnThisLayer() && !hasMasksOnThisLayer()) {
                this.matrix.preConcat(this.transform.getMatrix());
                L.beginSection("Layer#drawLayer");
                drawLayer(canvas, this.matrix, intValue);
                L.endSection("Layer#drawLayer");
                recordRenderTime(L.endSection(this.drawTraceName));
                return;
            }
            L.beginSection("Layer#computeBounds");
            getBounds(this.rect, this.matrix, false);
            intersectBoundsWithMatte(this.rect, matrix);
            this.matrix.preConcat(this.transform.getMatrix());
            intersectBoundsWithMask(this.rect, this.matrix);
            if (!this.rect.intersect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight())) {
                this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
            L.endSection("Layer#computeBounds");
            if (!this.rect.isEmpty()) {
                L.beginSection("Layer#saveLayer");
                saveLayerCompat(canvas, this.rect, this.contentPaint, true);
                L.endSection("Layer#saveLayer");
                clearCanvas(canvas);
                L.beginSection("Layer#drawLayer");
                drawLayer(canvas, this.matrix, intValue);
                L.endSection("Layer#drawLayer");
                if (hasMasksOnThisLayer()) {
                    applyMasks(canvas, this.matrix);
                }
                if (hasMatteOnThisLayer()) {
                    L.beginSection("Layer#drawMatte");
                    L.beginSection("Layer#saveLayer");
                    saveLayerCompat(canvas, this.rect, this.mattePaint, false);
                    L.endSection("Layer#saveLayer");
                    clearCanvas(canvas);
                    this.matteLayer.draw(canvas, matrix, intValue);
                    L.beginSection("Layer#restoreLayer");
                    canvas.restore();
                    L.endSection("Layer#restoreLayer");
                    L.endSection("Layer#drawMatte");
                }
                L.beginSection("Layer#restoreLayer");
                canvas.restore();
                L.endSection("Layer#restoreLayer");
            }
            recordRenderTime(L.endSection(this.drawTraceName));
            return;
        }
        L.endSection(this.drawTraceName);
    }

    abstract void drawLayer(Canvas canvas, Matrix matrix, int i);

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    @CallSuper
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        buildParentLayerListIfNeeded();
        this.boundsMatrix.set(matrix);
        if (z) {
            List<BaseLayer> list = this.parentLayers;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.boundsMatrix.preConcat(this.parentLayers.get(size).transform.getMatrix());
                }
            } else {
                BaseLayer baseLayer = this.parentLayer;
                if (baseLayer != null) {
                    this.boundsMatrix.preConcat(baseLayer.transform.getMatrix());
                }
            }
        }
        this.boundsMatrix.preConcat(this.transform.getMatrix());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Layer getLayerModel() {
        return this.layerModel;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.layerModel.getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMasksOnThisLayer() {
        MaskKeyframeAnimation maskKeyframeAnimation = this.mask;
        return maskKeyframeAnimation != null && !maskKeyframeAnimation.getMaskAnimations().isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMatteOnThisLayer() {
        return this.matteLayer != null;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        invalidateSelf();
    }

    public void removeAnimation(BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        this.animations.remove(baseKeyframeAnimation);
    }

    void resolveChildKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        if (!keyPath.matches(getName(), i)) {
            return;
        }
        if (!"__container".equals(getName())) {
            keyPath2 = keyPath2.addKey(getName());
            if (keyPath.fullyResolvesTo(getName(), i)) {
                list.add(keyPath2.resolve(this));
            }
        }
        if (!keyPath.propagateToChildren(getName(), i)) {
            return;
        }
        resolveChildKeyPath(keyPath, keyPath.incrementDepthBy(getName(), i) + i, list, keyPath2);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMatteLayer(@Nullable BaseLayer baseLayer) {
        this.matteLayer = baseLayer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParentLayer(@Nullable BaseLayer baseLayer) {
        this.parentLayer = baseLayer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.transform.setProgress(f);
        if (this.mask != null) {
            for (int i = 0; i < this.mask.getMaskAnimations().size(); i++) {
                this.mask.getMaskAnimations().get(i).setProgress(f);
            }
        }
        if (this.layerModel.getTimeStretch() != 0.0f) {
            f /= this.layerModel.getTimeStretch();
        }
        BaseLayer baseLayer = this.matteLayer;
        if (baseLayer != null) {
            this.matteLayer.setProgress(baseLayer.layerModel.getTimeStretch() * f);
        }
        for (int i2 = 0; i2 < this.animations.size(); i2++) {
            this.animations.get(i2).setProgress(f);
        }
    }
}
