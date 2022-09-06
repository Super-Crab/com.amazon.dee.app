package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.FadeDrawable;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.MatrixDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class GenericDraweeHierarchy implements SettableDraweeHierarchy {
    private static final int ACTUAL_IMAGE_INDEX = 2;
    private static final int BACKGROUND_IMAGE_INDEX = 0;
    private static final int FAILURE_IMAGE_INDEX = 5;
    private static final int OVERLAY_IMAGES_INDEX = 6;
    private static final int PLACEHOLDER_IMAGE_INDEX = 1;
    private static final int PROGRESS_BAR_IMAGE_INDEX = 3;
    private static final int RETRY_IMAGE_INDEX = 4;
    private final ForwardingDrawable mActualImageWrapper;
    private final Drawable mEmptyActualImageDrawable = new ColorDrawable(0);
    private final FadeDrawable mFadeDrawable;
    private final Resources mResources;
    @Nullable
    private RoundingParams mRoundingParams;
    private final RootDrawable mTopLevelDrawable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericDraweeHierarchy(GenericDraweeHierarchyBuilder builder) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("GenericDraweeHierarchy()");
        }
        this.mResources = builder.getResources();
        this.mRoundingParams = builder.getRoundingParams();
        this.mActualImageWrapper = new ForwardingDrawable(this.mEmptyActualImageDrawable);
        int i = 1;
        int size = builder.getOverlays() != null ? builder.getOverlays().size() : 1;
        int i2 = (size == 0 ? 1 : size) + (builder.getPressedStateOverlay() != null ? 1 : 0);
        Drawable[] drawableArr = new Drawable[i2 + 6];
        drawableArr[0] = buildBranch(builder.getBackground(), null);
        drawableArr[1] = buildBranch(builder.getPlaceholderImage(), builder.getPlaceholderImageScaleType());
        drawableArr[2] = buildActualImageBranch(this.mActualImageWrapper, builder.getActualImageScaleType(), builder.getActualImageFocusPoint(), builder.getActualImageColorFilter());
        drawableArr[3] = buildBranch(builder.getProgressBarImage(), builder.getProgressBarImageScaleType());
        drawableArr[4] = buildBranch(builder.getRetryImage(), builder.getRetryImageScaleType());
        drawableArr[5] = buildBranch(builder.getFailureImage(), builder.getFailureImageScaleType());
        if (i2 > 0) {
            if (builder.getOverlays() != null) {
                i = 0;
                for (Drawable drawable : builder.getOverlays()) {
                    drawableArr[i + 6] = buildBranch(drawable, null);
                    i++;
                }
            }
            if (builder.getPressedStateOverlay() != null) {
                drawableArr[i + 6] = buildBranch(builder.getPressedStateOverlay(), null);
            }
        }
        this.mFadeDrawable = new FadeDrawable(drawableArr, false, 2);
        this.mFadeDrawable.setTransitionDuration(builder.getFadeDuration());
        this.mTopLevelDrawable = new RootDrawable(WrappingUtils.maybeWrapWithRoundedOverlayColor(this.mFadeDrawable, this.mRoundingParams));
        this.mTopLevelDrawable.mutate();
        resetFade();
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    @Nullable
    private Drawable buildActualImageBranch(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType, @Nullable PointF focusPoint, @Nullable ColorFilter colorFilter) {
        drawable.setColorFilter(colorFilter);
        return WrappingUtils.maybeWrapWithScaleType(drawable, scaleType, focusPoint);
    }

    @Nullable
    private Drawable buildBranch(@Nullable Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        return WrappingUtils.maybeWrapWithScaleType(WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources), scaleType);
    }

    private void fadeInLayer(int index) {
        if (index >= 0) {
            this.mFadeDrawable.fadeInLayer(index);
        }
    }

    private void fadeOutBranches() {
        fadeOutLayer(1);
        fadeOutLayer(2);
        fadeOutLayer(3);
        fadeOutLayer(4);
        fadeOutLayer(5);
    }

    private void fadeOutLayer(int index) {
        if (index >= 0) {
            this.mFadeDrawable.fadeOutLayer(index);
        }
    }

    private DrawableParent getParentDrawableAtIndex(int index) {
        DrawableParent drawableParentForIndex = this.mFadeDrawable.getDrawableParentForIndex(index);
        if (drawableParentForIndex.getDrawable() instanceof MatrixDrawable) {
            drawableParentForIndex = (MatrixDrawable) drawableParentForIndex.getDrawable();
        }
        return drawableParentForIndex.getDrawable() instanceof ScaleTypeDrawable ? (ScaleTypeDrawable) drawableParentForIndex.getDrawable() : drawableParentForIndex;
    }

    private ScaleTypeDrawable getScaleTypeDrawableAtIndex(int index) {
        DrawableParent parentDrawableAtIndex = getParentDrawableAtIndex(index);
        if (parentDrawableAtIndex instanceof ScaleTypeDrawable) {
            return (ScaleTypeDrawable) parentDrawableAtIndex;
        }
        return WrappingUtils.wrapChildWithScaleType(parentDrawableAtIndex, ScalingUtils.ScaleType.FIT_XY);
    }

    private boolean hasScaleTypeDrawableAtIndex(int index) {
        return getParentDrawableAtIndex(index) instanceof ScaleTypeDrawable;
    }

    private void resetActualImages() {
        this.mActualImageWrapper.setDrawable(this.mEmptyActualImageDrawable);
    }

    private void resetFade() {
        FadeDrawable fadeDrawable = this.mFadeDrawable;
        if (fadeDrawable != null) {
            fadeDrawable.beginBatchMode();
            this.mFadeDrawable.fadeInAllLayers();
            fadeOutBranches();
            fadeInLayer(1);
            this.mFadeDrawable.finishTransitionImmediately();
            this.mFadeDrawable.endBatchMode();
        }
    }

    private void setChildDrawableAtIndex(int index, @Nullable Drawable drawable) {
        if (drawable == null) {
            this.mFadeDrawable.setDrawable(index, null);
            return;
        }
        getParentDrawableAtIndex(index).setDrawable(WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources));
    }

    private void setProgress(float progress) {
        Drawable drawable = this.mFadeDrawable.getDrawable(3);
        if (drawable == null) {
            return;
        }
        if (progress >= 0.999f) {
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).stop();
            }
            fadeOutLayer(3);
        } else {
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
            fadeInLayer(3);
        }
        drawable.setLevel(Math.round(progress * 10000.0f));
    }

    public void getActualImageBounds(RectF outBounds) {
        this.mActualImageWrapper.getTransformedBounds(outBounds);
    }

    @Nullable
    public PointF getActualImageFocusPoint() {
        if (!hasScaleTypeDrawableAtIndex(2)) {
            return null;
        }
        return getScaleTypeDrawableAtIndex(2).getFocusPoint();
    }

    @Nullable
    public ScalingUtils.ScaleType getActualImageScaleType() {
        if (!hasScaleTypeDrawableAtIndex(2)) {
            return null;
        }
        return getScaleTypeDrawableAtIndex(2).getScaleType();
    }

    @Override // com.facebook.drawee.interfaces.DraweeHierarchy
    public Rect getBounds() {
        return this.mTopLevelDrawable.getBounds();
    }

    public int getFadeDuration() {
        return this.mFadeDrawable.getTransitionDuration();
    }

    @Nullable
    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }

    @Override // com.facebook.drawee.interfaces.DraweeHierarchy
    public Drawable getTopLevelDrawable() {
        return this.mTopLevelDrawable;
    }

    @VisibleForTesting
    public boolean hasImage() {
        return this.mActualImageWrapper.getDrawable() != this.mEmptyActualImageDrawable;
    }

    public boolean hasPlaceholderImage() {
        return this.mFadeDrawable.getDrawable(1) != null;
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void reset() {
        resetActualImages();
        resetFade();
    }

    public void setActualImageColorFilter(ColorFilter colorfilter) {
        this.mActualImageWrapper.setColorFilter(colorfilter);
    }

    public void setActualImageFocusPoint(PointF focusPoint) {
        Preconditions.checkNotNull(focusPoint);
        getScaleTypeDrawableAtIndex(2).setFocusPoint(focusPoint);
    }

    public void setActualImageScaleType(ScalingUtils.ScaleType scaleType) {
        Preconditions.checkNotNull(scaleType);
        getScaleTypeDrawableAtIndex(2).setScaleType(scaleType);
    }

    public void setBackgroundImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(0, drawable);
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setControllerOverlay(@Nullable Drawable drawable) {
        this.mTopLevelDrawable.setControllerOverlay(drawable);
    }

    public void setFadeDuration(int durationMs) {
        this.mFadeDrawable.setTransitionDuration(durationMs);
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setFailure(Throwable throwable) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(5) != null) {
            fadeInLayer(5);
        } else {
            fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setFailureImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(5, drawable);
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setImage(Drawable drawable, float progress, boolean immediate) {
        Drawable maybeApplyLeafRounding = WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources);
        maybeApplyLeafRounding.mutate();
        this.mActualImageWrapper.setDrawable(maybeApplyLeafRounding);
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        fadeInLayer(2);
        setProgress(progress);
        if (immediate) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setOnFadeListener(FadeDrawable.OnFadeListener onFadeFinished) {
        this.mFadeDrawable.setOnFadeListener(onFadeFinished);
    }

    public void setOverlayImage(int index, @Nullable Drawable drawable) {
        Preconditions.checkArgument(index >= 0 && index + 6 < this.mFadeDrawable.getNumberOfLayers(), "The given index does not correspond to an overlay image.");
        setChildDrawableAtIndex(index + 6, drawable);
    }

    public void setPlaceholderImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(1, drawable);
    }

    public void setPlaceholderImageFocusPoint(PointF focusPoint) {
        Preconditions.checkNotNull(focusPoint);
        getScaleTypeDrawableAtIndex(1).setFocusPoint(focusPoint);
    }

    public void setProgressBarImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(3, drawable);
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setRetry(Throwable throwable) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(4) != null) {
            fadeInLayer(4);
        } else {
            fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setRetryImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(4, drawable);
    }

    public void setRoundingParams(@Nullable RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        WrappingUtils.updateOverlayColorRounding(this.mTopLevelDrawable, this.mRoundingParams);
        for (int i = 0; i < this.mFadeDrawable.getNumberOfLayers(); i++) {
            WrappingUtils.updateLeafRounding(getParentDrawableAtIndex(i), this.mRoundingParams, this.mResources);
        }
    }

    public void setFailureImage(Drawable drawable, ScalingUtils.ScaleType scaleType) {
        setChildDrawableAtIndex(5, drawable);
        getScaleTypeDrawableAtIndex(5).setScaleType(scaleType);
    }

    public void setPlaceholderImage(Drawable drawable, ScalingUtils.ScaleType scaleType) {
        setChildDrawableAtIndex(1, drawable);
        getScaleTypeDrawableAtIndex(1).setScaleType(scaleType);
    }

    public void setProgressBarImage(Drawable drawable, ScalingUtils.ScaleType scaleType) {
        setChildDrawableAtIndex(3, drawable);
        getScaleTypeDrawableAtIndex(3).setScaleType(scaleType);
    }

    public void setRetryImage(Drawable drawable, ScalingUtils.ScaleType scaleType) {
        setChildDrawableAtIndex(4, drawable);
        getScaleTypeDrawableAtIndex(4).setScaleType(scaleType);
    }

    public void setFailureImage(int resourceId) {
        setFailureImage(this.mResources.getDrawable(resourceId));
    }

    public void setPlaceholderImage(int resourceId) {
        setPlaceholderImage(this.mResources.getDrawable(resourceId));
    }

    public void setProgressBarImage(int resourceId) {
        setProgressBarImage(this.mResources.getDrawable(resourceId));
    }

    public void setRetryImage(int resourceId) {
        setRetryImage(this.mResources.getDrawable(resourceId));
    }

    public void setFailureImage(int resourceId, ScalingUtils.ScaleType scaleType) {
        setFailureImage(this.mResources.getDrawable(resourceId), scaleType);
    }

    public void setOverlayImage(@Nullable Drawable drawable) {
        setOverlayImage(0, drawable);
    }

    public void setPlaceholderImage(int resourceId, ScalingUtils.ScaleType scaleType) {
        setPlaceholderImage(this.mResources.getDrawable(resourceId), scaleType);
    }

    public void setProgressBarImage(int resourceId, ScalingUtils.ScaleType scaleType) {
        setProgressBarImage(this.mResources.getDrawable(resourceId), scaleType);
    }

    public void setRetryImage(int resourceId, ScalingUtils.ScaleType scaleType) {
        setRetryImage(this.mResources.getDrawable(resourceId), scaleType);
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setProgress(float progress, boolean immediate) {
        if (this.mFadeDrawable.getDrawable(3) == null) {
            return;
        }
        this.mFadeDrawable.beginBatchMode();
        setProgress(progress);
        if (immediate) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }
}
