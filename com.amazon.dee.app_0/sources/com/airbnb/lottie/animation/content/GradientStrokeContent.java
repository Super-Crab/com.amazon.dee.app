package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;
/* loaded from: classes.dex */
public class GradientStrokeContent extends BaseStrokeContent {
    private static final int CACHE_STEPS_MS = 32;
    private final RectF boundsRect;
    private final int cacheSteps;
    private final BaseKeyframeAnimation<GradientColor, GradientColor> colorAnimation;
    @Nullable
    private ValueCallbackKeyframeAnimation colorCallbackAnimation;
    private final BaseKeyframeAnimation<PointF, PointF> endPointAnimation;
    private final boolean hidden;
    private final LongSparseArray<LinearGradient> linearGradientCache;
    private final String name;
    private final LongSparseArray<RadialGradient> radialGradientCache;
    private final BaseKeyframeAnimation<PointF, PointF> startPointAnimation;
    private final GradientType type;

    public GradientStrokeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, GradientStroke gradientStroke) {
        super(lottieDrawable, baseLayer, gradientStroke.getCapType().toPaintCap(), gradientStroke.getJoinType().toPaintJoin(), gradientStroke.getMiterLimit(), gradientStroke.getOpacity(), gradientStroke.getWidth(), gradientStroke.getLineDashPattern(), gradientStroke.getDashOffset());
        this.linearGradientCache = new LongSparseArray<>();
        this.radialGradientCache = new LongSparseArray<>();
        this.boundsRect = new RectF();
        this.name = gradientStroke.getName();
        this.type = gradientStroke.getGradientType();
        this.hidden = gradientStroke.isHidden();
        this.cacheSteps = (int) (lottieDrawable.getComposition().getDuration() / 32.0f);
        this.colorAnimation = gradientStroke.getGradientColor().mo271createAnimation();
        this.colorAnimation.addUpdateListener(this);
        baseLayer.addAnimation(this.colorAnimation);
        this.startPointAnimation = gradientStroke.getStartPoint().mo271createAnimation();
        this.startPointAnimation.addUpdateListener(this);
        baseLayer.addAnimation(this.startPointAnimation);
        this.endPointAnimation = gradientStroke.getEndPoint().mo271createAnimation();
        this.endPointAnimation.addUpdateListener(this);
        baseLayer.addAnimation(this.endPointAnimation);
    }

    private int[] applyDynamicColorsIfNeeded(int[] iArr) {
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorCallbackAnimation;
        if (valueCallbackKeyframeAnimation != null) {
            Integer[] numArr = (Integer[]) valueCallbackKeyframeAnimation.mo268getValue();
            int i = 0;
            if (iArr.length == numArr.length) {
                while (i < iArr.length) {
                    iArr[i] = numArr[i].intValue();
                    i++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i < numArr.length) {
                    iArr[i] = numArr[i].intValue();
                    i++;
                }
            }
        }
        return iArr;
    }

    private int getGradientHash() {
        int round = Math.round(this.startPointAnimation.getProgress() * this.cacheSteps);
        int round2 = Math.round(this.endPointAnimation.getProgress() * this.cacheSteps);
        int round3 = Math.round(this.colorAnimation.getProgress() * this.cacheSteps);
        int i = round != 0 ? 527 * round : 17;
        if (round2 != 0) {
            i = i * 31 * round2;
        }
        return round3 != 0 ? i * 31 * round3 : i;
    }

    private LinearGradient getLinearGradient() {
        RectF rectF;
        RectF rectF2;
        long gradientHash = getGradientHash();
        LinearGradient linearGradient = this.linearGradientCache.get(gradientHash);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF mo268getValue = this.startPointAnimation.mo268getValue();
        PointF mo268getValue2 = this.endPointAnimation.mo268getValue();
        GradientColor mo268getValue3 = this.colorAnimation.mo268getValue();
        int[] applyDynamicColorsIfNeeded = applyDynamicColorsIfNeeded(mo268getValue3.getColors());
        float[] positions = mo268getValue3.getPositions();
        float f = this.boundsRect.left;
        RectF rectF3 = this.boundsRect;
        int height = (int) ((rectF3.height() / 2.0f) + rectF3.top + mo268getValue.y);
        float f2 = this.boundsRect.left;
        RectF rectF4 = this.boundsRect;
        LinearGradient linearGradient2 = new LinearGradient((int) ((rectF.width() / 2.0f) + f + mo268getValue.x), height, (int) ((rectF2.width() / 2.0f) + f2 + mo268getValue2.x), (int) ((rectF4.height() / 2.0f) + rectF4.top + mo268getValue2.y), applyDynamicColorsIfNeeded, positions, Shader.TileMode.CLAMP);
        this.linearGradientCache.put(gradientHash, linearGradient2);
        return linearGradient2;
    }

    private RadialGradient getRadialGradient() {
        RectF rectF;
        RectF rectF2;
        long gradientHash = getGradientHash();
        RadialGradient radialGradient = this.radialGradientCache.get(gradientHash);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF mo268getValue = this.startPointAnimation.mo268getValue();
        PointF mo268getValue2 = this.endPointAnimation.mo268getValue();
        GradientColor mo268getValue3 = this.colorAnimation.mo268getValue();
        int[] applyDynamicColorsIfNeeded = applyDynamicColorsIfNeeded(mo268getValue3.getColors());
        float[] positions = mo268getValue3.getPositions();
        RectF rectF3 = this.boundsRect;
        int width = (int) ((rectF3.width() / 2.0f) + rectF3.left + mo268getValue.x);
        RectF rectF4 = this.boundsRect;
        int height = (int) ((rectF4.height() / 2.0f) + rectF4.top + mo268getValue.y);
        float f = this.boundsRect.left;
        float f2 = this.boundsRect.top;
        RadialGradient radialGradient2 = new RadialGradient(width, height, (float) Math.hypot(((int) (((rectF.width() / 2.0f) + f) + mo268getValue2.x)) - width, ((int) (((rectF2.height() / 2.0f) + f2) + mo268getValue2.y)) - height), applyDynamicColorsIfNeeded, positions, Shader.TileMode.CLAMP);
        this.radialGradientCache.put(gradientHash, radialGradient2);
        return radialGradient2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t, lottieValueCallback);
        if (t == LottieProperty.GRADIENT_COLOR) {
            if (lottieValueCallback == null) {
                ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorCallbackAnimation;
                if (valueCallbackKeyframeAnimation != null) {
                    this.layer.removeAnimation(valueCallbackKeyframeAnimation);
                }
                this.colorCallbackAnimation = null;
                return;
            }
            this.colorCallbackAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.colorCallbackAnimation.addUpdateListener(this);
            this.layer.addAnimation(this.colorCallbackAnimation);
        }
    }

    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix matrix, int i) {
        Shader radialGradient;
        if (this.hidden) {
            return;
        }
        getBounds(this.boundsRect, matrix, false);
        if (this.type == GradientType.LINEAR) {
            radialGradient = getLinearGradient();
        } else {
            radialGradient = getRadialGradient();
        }
        this.paint.setShader(radialGradient);
        super.draw(canvas, matrix, i);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }
}
