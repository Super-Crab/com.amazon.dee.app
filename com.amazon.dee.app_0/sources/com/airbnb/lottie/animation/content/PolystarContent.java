package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes.dex */
public class PolystarContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private static final float POLYGON_MAGIC_NUMBER = 0.25f;
    private static final float POLYSTAR_MAGIC_NUMBER = 0.47829f;
    private final boolean hidden;
    @Nullable
    private final BaseKeyframeAnimation<?, Float> innerRadiusAnimation;
    @Nullable
    private final BaseKeyframeAnimation<?, Float> innerRoundednessAnimation;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<?, Float> outerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> outerRoundednessAnimation;
    private final BaseKeyframeAnimation<?, Float> pointsAnimation;
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final BaseKeyframeAnimation<?, Float> rotationAnimation;
    private final PolystarShape.Type type;
    private final Path path = new Path();
    private CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();

    /* renamed from: com.airbnb.lottie.animation.content.PolystarContent$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type = new int[PolystarShape.Type.values().length];

        static {
            try {
                $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[PolystarShape.Type.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[PolystarShape.Type.POLYGON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public PolystarContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, PolystarShape polystarShape) {
        this.lottieDrawable = lottieDrawable;
        this.name = polystarShape.getName();
        this.type = polystarShape.getType();
        this.hidden = polystarShape.isHidden();
        this.pointsAnimation = polystarShape.getPoints().mo271createAnimation();
        this.positionAnimation = polystarShape.getPosition().mo271createAnimation();
        this.rotationAnimation = polystarShape.getRotation().mo271createAnimation();
        this.outerRadiusAnimation = polystarShape.getOuterRadius().mo271createAnimation();
        this.outerRoundednessAnimation = polystarShape.getOuterRoundedness().mo271createAnimation();
        if (this.type == PolystarShape.Type.STAR) {
            this.innerRadiusAnimation = polystarShape.getInnerRadius().mo271createAnimation();
            this.innerRoundednessAnimation = polystarShape.getInnerRoundedness().mo271createAnimation();
        } else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        baseLayer.addAnimation(this.pointsAnimation);
        baseLayer.addAnimation(this.positionAnimation);
        baseLayer.addAnimation(this.rotationAnimation);
        baseLayer.addAnimation(this.outerRadiusAnimation);
        baseLayer.addAnimation(this.outerRoundednessAnimation);
        if (this.type == PolystarShape.Type.STAR) {
            baseLayer.addAnimation(this.innerRadiusAnimation);
            baseLayer.addAnimation(this.innerRoundednessAnimation);
        }
        this.pointsAnimation.addUpdateListener(this);
        this.positionAnimation.addUpdateListener(this);
        this.rotationAnimation.addUpdateListener(this);
        this.outerRadiusAnimation.addUpdateListener(this);
        this.outerRoundednessAnimation.addUpdateListener(this);
        if (this.type == PolystarShape.Type.STAR) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
    }

    private void createPolygonPath() {
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation;
        double d;
        double d2;
        double d3;
        int i;
        int floor = (int) Math.floor(this.pointsAnimation.mo268getValue().floatValue());
        double radians = Math.toRadians((this.rotationAnimation == null ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : baseKeyframeAnimation.mo268getValue().floatValue()) - 90.0d);
        double d4 = floor;
        float floatValue = this.outerRoundednessAnimation.mo268getValue().floatValue() / 100.0f;
        float floatValue2 = this.outerRadiusAnimation.mo268getValue().floatValue();
        double d5 = floatValue2;
        float cos = (float) (Math.cos(radians) * d5);
        float sin = (float) (Math.sin(radians) * d5);
        this.path.moveTo(cos, sin);
        double d6 = (float) (6.283185307179586d / d4);
        double d7 = radians + d6;
        double ceil = Math.ceil(d4);
        int i2 = 0;
        while (i2 < ceil) {
            float cos2 = (float) (Math.cos(d7) * d5);
            double d8 = ceil;
            float sin2 = (float) (Math.sin(d7) * d5);
            if (floatValue != 0.0f) {
                d2 = d5;
                i = i2;
                d = d7;
                double atan2 = (float) (Math.atan2(sin, cos) - 1.5707963267948966d);
                float cos3 = (float) Math.cos(atan2);
                d3 = d6;
                double atan22 = (float) (Math.atan2(sin2, cos2) - 1.5707963267948966d);
                float f = floatValue2 * floatValue * POLYGON_MAGIC_NUMBER;
                this.path.cubicTo(cos - (cos3 * f), sin - (((float) Math.sin(atan2)) * f), cos2 + (((float) Math.cos(atan22)) * f), sin2 + (f * ((float) Math.sin(atan22))), cos2, sin2);
            } else {
                d = d7;
                d2 = d5;
                d3 = d6;
                i = i2;
                this.path.lineTo(cos2, sin2);
            }
            d7 = d + d3;
            i2 = i + 1;
            sin = sin2;
            cos = cos2;
            ceil = d8;
            d5 = d2;
            d6 = d3;
        }
        PointF mo268getValue = this.positionAnimation.mo268getValue();
        this.path.offset(mo268getValue.x, mo268getValue.y);
        this.path.close();
    }

    private void createStarPath() {
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation;
        float f;
        int i;
        float sin;
        double d;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        double d2;
        float f11;
        float floatValue = this.pointsAnimation.mo268getValue().floatValue();
        double radians = Math.toRadians((this.rotationAnimation == null ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : baseKeyframeAnimation.mo268getValue().floatValue()) - 90.0d);
        double d3 = floatValue;
        float f12 = (float) (6.283185307179586d / d3);
        float f13 = f12 / 2.0f;
        float f14 = floatValue - ((int) floatValue);
        float f15 = 0.0f;
        int i2 = (f14 > 0.0f ? 1 : (f14 == 0.0f ? 0 : -1));
        if (i2 != 0) {
            radians += (1.0f - f14) * f13;
        }
        float floatValue2 = this.outerRadiusAnimation.mo268getValue().floatValue();
        float floatValue3 = this.innerRadiusAnimation.mo268getValue().floatValue();
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.innerRoundednessAnimation;
        float floatValue4 = baseKeyframeAnimation2 != null ? baseKeyframeAnimation2.mo268getValue().floatValue() / 100.0f : 0.0f;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation3 = this.outerRoundednessAnimation;
        if (baseKeyframeAnimation3 != null) {
            f15 = baseKeyframeAnimation3.mo268getValue().floatValue() / 100.0f;
        }
        if (i2 != 0) {
            f2 = GeneratedOutlineSupport1.outline0(floatValue2, floatValue3, f14, floatValue3);
            double d4 = f2;
            f = f15;
            i = i2;
            f3 = (float) (Math.cos(radians) * d4);
            sin = (float) (d4 * Math.sin(radians));
            this.path.moveTo(f3, sin);
            d = radians + ((f12 * f14) / 2.0f);
        } else {
            f = f15;
            i = i2;
            double d5 = floatValue2;
            float cos = (float) (Math.cos(radians) * d5);
            sin = (float) (Math.sin(radians) * d5);
            this.path.moveTo(cos, sin);
            d = radians + f13;
            f2 = 0.0f;
            f3 = cos;
        }
        double ceil = Math.ceil(d3) * 2.0d;
        boolean z = false;
        float f16 = sin;
        float f17 = f3;
        double d6 = d;
        int i3 = 0;
        while (true) {
            double d7 = i3;
            if (d7 < ceil) {
                float f18 = z ? floatValue2 : floatValue3;
                int i4 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
                if (i4 == 0 || d7 != ceil - 2.0d) {
                    f4 = f12;
                    f5 = f13;
                } else {
                    f4 = f12;
                    f5 = (f12 * f14) / 2.0f;
                }
                if (i4 == 0 || d7 != ceil - 1.0d) {
                    f6 = f13;
                    f7 = floatValue3;
                    f8 = f18;
                    f9 = floatValue2;
                } else {
                    f6 = f13;
                    f9 = floatValue2;
                    f7 = floatValue3;
                    f8 = f2;
                }
                double d8 = f8;
                float f19 = f5;
                float cos2 = (float) (Math.cos(d6) * d8);
                float sin2 = (float) (d8 * Math.sin(d6));
                if (floatValue4 == 0.0f && f == 0.0f) {
                    this.path.lineTo(cos2, sin2);
                    d2 = d6;
                    f10 = floatValue4;
                    f11 = f2;
                } else {
                    float f20 = f16;
                    f10 = floatValue4;
                    d2 = d6;
                    double atan2 = (float) (Math.atan2(f20, f17) - 1.5707963267948966d);
                    float cos3 = (float) Math.cos(atan2);
                    float sin3 = (float) Math.sin(atan2);
                    f11 = f2;
                    double atan22 = (float) (Math.atan2(sin2, cos2) - 1.5707963267948966d);
                    float cos4 = (float) Math.cos(atan22);
                    float sin4 = (float) Math.sin(atan22);
                    float f21 = z ? f10 : f;
                    float f22 = z ? f : f10;
                    float f23 = z ? f7 : f9;
                    float f24 = z ? f9 : f7;
                    float f25 = f23 * f21 * POLYSTAR_MAGIC_NUMBER;
                    float f26 = cos3 * f25;
                    float f27 = f25 * sin3;
                    float f28 = f24 * f22 * POLYSTAR_MAGIC_NUMBER;
                    float f29 = cos4 * f28;
                    float f30 = f28 * sin4;
                    if (i != 0) {
                        if (i3 == 0) {
                            f26 *= f14;
                            f27 *= f14;
                        } else if (d7 == ceil - 1.0d) {
                            f29 *= f14;
                            f30 *= f14;
                        }
                    }
                    this.path.cubicTo(f17 - f26, f20 - f27, cos2 + f29, sin2 + f30, cos2, sin2);
                }
                d6 = d2 + f19;
                z = !z;
                i3++;
                f17 = cos2;
                floatValue2 = f9;
                floatValue4 = f10;
                f12 = f4;
                floatValue3 = f7;
                f2 = f11;
                f16 = sin2;
                f13 = f6;
            } else {
                PointF mo268getValue = this.positionAnimation.mo268getValue();
                this.path.offset(mo268getValue.x, mo268getValue.y);
                this.path.close();
                return;
            }
        }
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2;
        if (t == LottieProperty.POLYSTAR_POINTS) {
            this.pointsAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_ROTATION) {
            this.rotationAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_INNER_RADIUS && (baseKeyframeAnimation2 = this.innerRadiusAnimation) != null) {
            baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_OUTER_RADIUS) {
            this.outerRadiusAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS && (baseKeyframeAnimation = this.innerRoundednessAnimation) != null) {
            baseKeyframeAnimation.setValueCallback(lottieValueCallback);
        } else if (t != LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
        } else {
            this.outerRoundednessAnimation.setValueCallback(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        int ordinal = this.type.ordinal();
        if (ordinal == 0) {
            createStarPath();
        } else if (ordinal == 1) {
            createPolygonPath();
        }
        this.path.close();
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        invalidate();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
        for (int i = 0; i < list.size(); i++) {
            Content content = list.get(i);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.getType() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.trimPaths.addTrimPath(trimPathContent);
                    trimPathContent.addListener(this);
                }
            }
        }
    }
}
