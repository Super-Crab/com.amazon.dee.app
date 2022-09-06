package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class FadeDrawable extends ArrayDrawable {
    @VisibleForTesting
    public static final int TRANSITION_NONE = 2;
    @VisibleForTesting
    public static final int TRANSITION_RUNNING = 1;
    @VisibleForTesting
    public static final int TRANSITION_STARTING = 0;
    private final int mActualImageLayer;
    @VisibleForTesting
    int mAlpha;
    @VisibleForTesting
    int[] mAlphas;
    private final int mDefaultLayerAlpha;
    private final boolean mDefaultLayerIsOn;
    @VisibleForTesting
    int mDurationMs;
    private boolean mIsFadingActualImage;
    @VisibleForTesting
    boolean[] mIsLayerOn;
    private final Drawable[] mLayers;
    private boolean mMutateDrawables;
    @Nullable
    private OnFadeListener mOnFadeListener;
    private boolean mOnFadeListenerShowImmediately;
    @VisibleForTesting
    int mPreventInvalidateCount;
    @VisibleForTesting
    int[] mStartAlphas;
    @VisibleForTesting
    long mStartTimeMs;
    @VisibleForTesting
    int mTransitionState;

    /* loaded from: classes2.dex */
    public interface OnFadeListener {
        void onFadeFinished();

        void onFadeStarted();

        void onShownImmediately();
    }

    public FadeDrawable(Drawable[] layers) {
        this(layers, false, -1);
    }

    private void drawDrawableWithAlpha(Canvas canvas, Drawable drawable, int alpha) {
        if (drawable == null || alpha <= 0) {
            return;
        }
        this.mPreventInvalidateCount++;
        if (this.mMutateDrawables) {
            drawable.mutate();
        }
        drawable.setAlpha(alpha);
        this.mPreventInvalidateCount--;
        drawable.draw(canvas);
    }

    private void maybeOnFadeFinished() {
        if (!this.mIsFadingActualImage) {
            return;
        }
        this.mIsFadingActualImage = false;
        OnFadeListener onFadeListener = this.mOnFadeListener;
        if (onFadeListener == null) {
            return;
        }
        onFadeListener.onFadeFinished();
    }

    private void maybeOnFadeStarted() {
        int i;
        if (!this.mIsFadingActualImage && (i = this.mActualImageLayer) >= 0) {
            boolean[] zArr = this.mIsLayerOn;
            if (i >= zArr.length || !zArr[i]) {
                return;
            }
            this.mIsFadingActualImage = true;
            OnFadeListener onFadeListener = this.mOnFadeListener;
            if (onFadeListener == null) {
                return;
            }
            onFadeListener.onFadeStarted();
        }
    }

    private void maybeOnImageShownImmediately() {
        if (this.mOnFadeListenerShowImmediately && this.mTransitionState == 2 && this.mIsLayerOn[this.mActualImageLayer]) {
            OnFadeListener onFadeListener = this.mOnFadeListener;
            if (onFadeListener != null) {
                onFadeListener.onShownImmediately();
            }
            this.mOnFadeListenerShowImmediately = false;
        }
    }

    private void resetInternal() {
        this.mTransitionState = 2;
        Arrays.fill(this.mStartAlphas, this.mDefaultLayerAlpha);
        this.mStartAlphas[0] = 255;
        Arrays.fill(this.mAlphas, this.mDefaultLayerAlpha);
        this.mAlphas[0] = 255;
        Arrays.fill(this.mIsLayerOn, this.mDefaultLayerIsOn);
        this.mIsLayerOn[0] = true;
    }

    private boolean updateAlphas(float ratio) {
        boolean z = true;
        for (int i = 0; i < this.mLayers.length; i++) {
            int i2 = this.mIsLayerOn[i] ? 1 : -1;
            int[] iArr = this.mAlphas;
            iArr[i] = (int) ((i2 * 255 * ratio) + this.mStartAlphas[i]);
            if (iArr[i] < 0) {
                iArr[i] = 0;
            }
            int[] iArr2 = this.mAlphas;
            if (iArr2[i] > 255) {
                iArr2[i] = 255;
            }
            if (this.mIsLayerOn[i] && this.mAlphas[i] < 255) {
                z = false;
            }
            if (!this.mIsLayerOn[i] && this.mAlphas[i] > 0) {
                z = false;
            }
        }
        return z;
    }

    public void beginBatchMode() {
        this.mPreventInvalidateCount++;
    }

    @Override // com.facebook.drawee.drawable.ArrayDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean updateAlphas;
        int i = this.mTransitionState;
        int i2 = 2;
        int i3 = 0;
        if (i == 0) {
            System.arraycopy(this.mAlphas, 0, this.mStartAlphas, 0, this.mLayers.length);
            this.mStartTimeMs = getCurrentTimeMs();
            updateAlphas = updateAlphas(this.mDurationMs == 0 ? 1.0f : 0.0f);
            maybeOnFadeStarted();
            if (!updateAlphas) {
                i2 = 1;
            }
            this.mTransitionState = i2;
        } else if (i != 1) {
            updateAlphas = true;
        } else {
            Preconditions.checkState(this.mDurationMs > 0);
            updateAlphas = updateAlphas(((float) (getCurrentTimeMs() - this.mStartTimeMs)) / this.mDurationMs);
            if (!updateAlphas) {
                i2 = 1;
            }
            this.mTransitionState = i2;
        }
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i3 >= drawableArr.length) {
                break;
            }
            drawDrawableWithAlpha(canvas, drawableArr[i3], (int) Math.ceil((this.mAlphas[i3] * this.mAlpha) / 255.0d));
            i3++;
        }
        if (updateAlphas) {
            maybeOnFadeFinished();
            maybeOnImageShownImmediately();
            return;
        }
        invalidateSelf();
    }

    public void endBatchMode() {
        this.mPreventInvalidateCount--;
        invalidateSelf();
    }

    public void fadeInAllLayers() {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, true);
        invalidateSelf();
    }

    public void fadeInLayer(int index) {
        this.mTransitionState = 0;
        this.mIsLayerOn[index] = true;
        invalidateSelf();
    }

    public void fadeOutAllLayers() {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, false);
        invalidateSelf();
    }

    public void fadeOutLayer(int index) {
        this.mTransitionState = 0;
        this.mIsLayerOn[index] = false;
        invalidateSelf();
    }

    public void fadeToLayer(int index) {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, false);
        this.mIsLayerOn[index] = true;
        invalidateSelf();
    }

    public void fadeUpToLayer(int index) {
        this.mTransitionState = 0;
        int i = index + 1;
        Arrays.fill(this.mIsLayerOn, 0, i, true);
        Arrays.fill(this.mIsLayerOn, i, this.mLayers.length, false);
        invalidateSelf();
    }

    public void finishTransitionImmediately() {
        this.mTransitionState = 2;
        for (int i = 0; i < this.mLayers.length; i++) {
            this.mAlphas[i] = this.mIsLayerOn[i] ? 255 : 0;
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    protected long getCurrentTimeMs() {
        return SystemClock.uptimeMillis();
    }

    public int getTransitionDuration() {
        return this.mDurationMs;
    }

    @VisibleForTesting
    public int getTransitionState() {
        return this.mTransitionState;
    }

    public void hideLayerImmediately(int index) {
        this.mIsLayerOn[index] = false;
        this.mAlphas[index] = 0;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.mPreventInvalidateCount == 0) {
            super.invalidateSelf();
        }
    }

    public boolean isDefaultLayerIsOn() {
        return this.mDefaultLayerIsOn;
    }

    public boolean isLayerOn(int index) {
        return this.mIsLayerOn[index];
    }

    public void reset() {
        resetInternal();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.ArrayDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        if (this.mAlpha != alpha) {
            this.mAlpha = alpha;
            invalidateSelf();
        }
    }

    public void setMutateDrawables(boolean mutateDrawables) {
        this.mMutateDrawables = mutateDrawables;
    }

    public void setOnFadeListener(@Nullable OnFadeListener onFadeListener) {
        this.mOnFadeListener = onFadeListener;
    }

    public void setTransitionDuration(int durationMs) {
        this.mDurationMs = durationMs;
        if (this.mTransitionState == 1) {
            this.mTransitionState = 0;
        }
    }

    public void showLayerImmediately(int index) {
        this.mIsLayerOn[index] = true;
        this.mAlphas[index] = 255;
        if (index == this.mActualImageLayer) {
            this.mOnFadeListenerShowImmediately = true;
        }
        invalidateSelf();
    }

    public FadeDrawable(Drawable[] layers, boolean allLayersVisible, int actualImageLayer) {
        super(layers);
        boolean z = true;
        this.mMutateDrawables = true;
        Preconditions.checkState(layers.length < 1 ? false : z, "At least one layer required!");
        this.mLayers = layers;
        this.mStartAlphas = new int[layers.length];
        this.mAlphas = new int[layers.length];
        int i = 255;
        this.mAlpha = 255;
        this.mIsLayerOn = new boolean[layers.length];
        this.mPreventInvalidateCount = 0;
        this.mDefaultLayerIsOn = allLayersVisible;
        this.mDefaultLayerAlpha = !this.mDefaultLayerIsOn ? 0 : i;
        this.mActualImageLayer = actualImageLayer;
        resetInternal();
    }
}
