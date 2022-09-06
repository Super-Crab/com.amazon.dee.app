package com.google.android.exoplayer2.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes2.dex */
public class DefaultTimeBar extends View implements TimeBar {
    private static final String ACCESSIBILITY_CLASS_NAME = "android.widget.SeekBar";
    public static final int BAR_GRAVITY_BOTTOM = 1;
    public static final int BAR_GRAVITY_CENTER = 0;
    public static final int DEFAULT_AD_MARKER_COLOR = -1291845888;
    public static final int DEFAULT_AD_MARKER_WIDTH_DP = 4;
    public static final int DEFAULT_BAR_HEIGHT_DP = 4;
    public static final int DEFAULT_BUFFERED_COLOR = -855638017;
    private static final int DEFAULT_INCREMENT_COUNT = 20;
    public static final int DEFAULT_PLAYED_AD_MARKER_COLOR = 872414976;
    public static final int DEFAULT_PLAYED_COLOR = -1;
    public static final int DEFAULT_SCRUBBER_COLOR = -1;
    public static final int DEFAULT_SCRUBBER_DISABLED_SIZE_DP = 0;
    public static final int DEFAULT_SCRUBBER_DRAGGED_SIZE_DP = 16;
    public static final int DEFAULT_SCRUBBER_ENABLED_SIZE_DP = 12;
    public static final int DEFAULT_TOUCH_TARGET_HEIGHT_DP = 26;
    public static final int DEFAULT_UNPLAYED_COLOR = 872415231;
    private static final int FINE_SCRUB_RATIO = 3;
    private static final int FINE_SCRUB_Y_THRESHOLD_DP = -50;
    private static final float HIDDEN_SCRUBBER_SCALE = 0.0f;
    private static final float SHOWN_SCRUBBER_SCALE = 1.0f;
    private static final long STOP_SCRUBBING_TIMEOUT_MS = 1000;
    private int adGroupCount;
    @Nullable
    private long[] adGroupTimesMs;
    private final Paint adMarkerPaint;
    private final int adMarkerWidth;
    private final int barGravity;
    private final int barHeight;
    private final Rect bufferedBar;
    private final Paint bufferedPaint;
    private long bufferedPosition;
    private final float density;
    private long duration;
    private final int fineScrubYThreshold;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private int keyCountIncrement;
    private long keyTimeIncrement;
    private int lastCoarseScrubXPosition;
    private Rect lastExclusionRectangle;
    private final CopyOnWriteArraySet<TimeBar.OnScrubListener> listeners;
    @Nullable
    private boolean[] playedAdGroups;
    private final Paint playedAdMarkerPaint;
    private final Paint playedPaint;
    private long position;
    private final Rect progressBar;
    private long scrubPosition;
    private final Rect scrubberBar;
    private final int scrubberDisabledSize;
    private final int scrubberDraggedSize;
    @Nullable
    private final Drawable scrubberDrawable;
    private final int scrubberEnabledSize;
    private final int scrubberPadding;
    private boolean scrubberPaddingDisabled;
    private final Paint scrubberPaint;
    private float scrubberScale;
    private ValueAnimator scrubberScalingAnimator;
    private boolean scrubbing;
    private final Rect seekBounds;
    private final Runnable stopScrubbingRunnable;
    private final Point touchPosition;
    private final int touchTargetHeight;
    private final Paint unplayedPaint;

    public DefaultTimeBar(Context context) {
        this(context, null);
    }

    private static int dpToPx(float f, int i) {
        return (int) ((i * f) + 0.5f);
    }

    private void drawPlayhead(Canvas canvas) {
        int i;
        if (this.duration <= 0) {
            return;
        }
        Rect rect = this.scrubberBar;
        int constrainValue = Util.constrainValue(rect.right, rect.left, this.progressBar.right);
        int centerY = this.scrubberBar.centerY();
        Drawable drawable = this.scrubberDrawable;
        if (drawable == null) {
            if (!this.scrubbing && !isFocused()) {
                i = isEnabled() ? this.scrubberEnabledSize : this.scrubberDisabledSize;
            } else {
                i = this.scrubberDraggedSize;
            }
            canvas.drawCircle(constrainValue, centerY, (int) ((i * this.scrubberScale) / 2.0f), this.scrubberPaint);
            return;
        }
        int intrinsicWidth = ((int) (drawable.getIntrinsicWidth() * this.scrubberScale)) / 2;
        int intrinsicHeight = ((int) (this.scrubberDrawable.getIntrinsicHeight() * this.scrubberScale)) / 2;
        this.scrubberDrawable.setBounds(constrainValue - intrinsicWidth, centerY - intrinsicHeight, constrainValue + intrinsicWidth, centerY + intrinsicHeight);
        this.scrubberDrawable.draw(canvas);
    }

    private void drawTimeBar(Canvas canvas) {
        int min;
        int height = this.progressBar.height();
        int centerY = this.progressBar.centerY() - (height / 2);
        int i = height + centerY;
        if (this.duration <= 0) {
            Rect rect = this.progressBar;
            canvas.drawRect(rect.left, centerY, rect.right, i, this.unplayedPaint);
            return;
        }
        Rect rect2 = this.bufferedBar;
        int i2 = rect2.left;
        int i3 = rect2.right;
        int max = Math.max(Math.max(this.progressBar.left, i3), this.scrubberBar.right);
        int i4 = this.progressBar.right;
        if (max < i4) {
            canvas.drawRect(max, centerY, i4, i, this.unplayedPaint);
        }
        int max2 = Math.max(i2, this.scrubberBar.right);
        if (i3 > max2) {
            canvas.drawRect(max2, centerY, i3, i, this.bufferedPaint);
        }
        if (this.scrubberBar.width() > 0) {
            Rect rect3 = this.scrubberBar;
            canvas.drawRect(rect3.left, centerY, rect3.right, i, this.playedPaint);
        }
        if (this.adGroupCount == 0) {
            return;
        }
        long[] jArr = (long[]) Assertions.checkNotNull(this.adGroupTimesMs);
        boolean[] zArr = (boolean[]) Assertions.checkNotNull(this.playedAdGroups);
        int i5 = this.adMarkerWidth / 2;
        for (int i6 = 0; i6 < this.adGroupCount; i6++) {
            long constrainValue = Util.constrainValue(jArr[i6], 0L, this.duration);
            Rect rect4 = this.progressBar;
            canvas.drawRect(Math.min(rect4.width() - this.adMarkerWidth, Math.max(0, ((int) ((this.progressBar.width() * constrainValue) / this.duration)) - i5)) + rect4.left, centerY, min + this.adMarkerWidth, i, zArr[i6] ? this.playedAdMarkerPaint : this.adMarkerPaint);
        }
    }

    private long getPositionIncrement() {
        long j = this.keyTimeIncrement;
        if (j == C.TIME_UNSET) {
            long j2 = this.duration;
            if (j2 != C.TIME_UNSET) {
                return j2 / this.keyCountIncrement;
            }
            return 0L;
        }
        return j;
    }

    private String getProgressText() {
        return Util.getStringForTime(this.formatBuilder, this.formatter, this.position);
    }

    private long getScrubberPosition() {
        if (this.progressBar.width() <= 0 || this.duration == C.TIME_UNSET) {
            return 0L;
        }
        return (this.scrubberBar.width() * this.duration) / this.progressBar.width();
    }

    private boolean isInSeekBar(float f, float f2) {
        return this.seekBounds.contains((int) f, (int) f2);
    }

    private void positionScrubber(float f) {
        Rect rect = this.scrubberBar;
        Rect rect2 = this.progressBar;
        rect.right = Util.constrainValue((int) f, rect2.left, rect2.right);
    }

    private static int pxToDp(float f, int i) {
        return (int) (i / f);
    }

    private Point resolveRelativeTouchPosition(MotionEvent motionEvent) {
        this.touchPosition.set((int) motionEvent.getX(), (int) motionEvent.getY());
        return this.touchPosition;
    }

    private boolean scrubIncrementally(long j) {
        if (this.duration <= 0) {
            return false;
        }
        long j2 = this.scrubbing ? this.scrubPosition : this.position;
        long constrainValue = Util.constrainValue(j2 + j, 0L, this.duration);
        if (constrainValue == j2) {
            return false;
        }
        if (!this.scrubbing) {
            startScrubbing(constrainValue);
        } else {
            updateScrubbing(constrainValue);
        }
        update();
        return true;
    }

    private boolean setDrawableLayoutDirection(Drawable drawable) {
        return Util.SDK_INT >= 23 && setDrawableLayoutDirection(drawable, getLayoutDirection());
    }

    @RequiresApi(29)
    private void setSystemGestureExclusionRectsV29(int i, int i2) {
        Rect rect = this.lastExclusionRectangle;
        if (rect != null && rect.width() == i && this.lastExclusionRectangle.height() == i2) {
            return;
        }
        this.lastExclusionRectangle = new Rect(0, 0, i, i2);
        setSystemGestureExclusionRects(Collections.singletonList(this.lastExclusionRectangle));
    }

    private void startScrubbing(long j) {
        this.scrubPosition = j;
        this.scrubbing = true;
        setPressed(true);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        Iterator<TimeBar.OnScrubListener> it2 = this.listeners.iterator();
        while (it2.hasNext()) {
            it2.next().onScrubStart(this, j);
        }
    }

    private void stopScrubbing(boolean z) {
        removeCallbacks(this.stopScrubbingRunnable);
        this.scrubbing = false;
        setPressed(false);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        invalidate();
        Iterator<TimeBar.OnScrubListener> it2 = this.listeners.iterator();
        while (it2.hasNext()) {
            it2.next().onScrubStop(this, this.scrubPosition, z);
        }
    }

    private void update() {
        this.bufferedBar.set(this.progressBar);
        this.scrubberBar.set(this.progressBar);
        long j = this.scrubbing ? this.scrubPosition : this.position;
        if (this.duration > 0) {
            Rect rect = this.bufferedBar;
            Rect rect2 = this.progressBar;
            rect.right = Math.min(rect2.left + ((int) ((this.progressBar.width() * this.bufferedPosition) / this.duration)), rect2.right);
            int width = (int) ((this.progressBar.width() * j) / this.duration);
            Rect rect3 = this.scrubberBar;
            Rect rect4 = this.progressBar;
            rect3.right = Math.min(rect4.left + width, rect4.right);
        } else {
            Rect rect5 = this.bufferedBar;
            int i = this.progressBar.left;
            rect5.right = i;
            this.scrubberBar.right = i;
        }
        invalidate(this.seekBounds);
    }

    private void updateDrawableState() {
        Drawable drawable = this.scrubberDrawable;
        if (drawable == null || !drawable.isStateful() || !this.scrubberDrawable.setState(getDrawableState())) {
            return;
        }
        invalidate();
    }

    private void updateScrubbing(long j) {
        if (this.scrubPosition == j) {
            return;
        }
        this.scrubPosition = j;
        Iterator<TimeBar.OnScrubListener> it2 = this.listeners.iterator();
        while (it2.hasNext()) {
            it2.next().onScrubMove(this, j);
        }
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public void addListener(TimeBar.OnScrubListener onScrubListener) {
        Assertions.checkNotNull(onScrubListener);
        this.listeners.add(onScrubListener);
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public long getPreferredUpdateDelay() {
        int pxToDp = pxToDp(this.density, this.progressBar.width());
        if (pxToDp != 0) {
            long j = this.duration;
            if (j != 0 && j != C.TIME_UNSET) {
                return j / pxToDp;
            }
        }
        return Long.MAX_VALUE;
    }

    public void hideScrubber(boolean z) {
        if (this.scrubberScalingAnimator.isStarted()) {
            this.scrubberScalingAnimator.cancel();
        }
        this.scrubberPaddingDisabled = z;
        this.scrubberScale = 0.0f;
        invalidate(this.seekBounds);
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.scrubberDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public /* synthetic */ void lambda$new$0$DefaultTimeBar() {
        stopScrubbing(false);
    }

    public /* synthetic */ void lambda$new$1$DefaultTimeBar(ValueAnimator valueAnimator) {
        this.scrubberScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate(this.seekBounds);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.save();
        drawTimeBar(canvas);
        drawPlayhead(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, @Nullable Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (!this.scrubbing || z) {
            return;
        }
        stopScrubbing(false);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (accessibilityEvent.getEventType() == 4) {
            accessibilityEvent.getText().add(getProgressText());
        }
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
        accessibilityNodeInfo.setContentDescription(getProgressText());
        if (this.duration <= 0) {
            return;
        }
        if (Util.SDK_INT >= 21) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
            return;
        }
        accessibilityNodeInfo.addAction(4096);
        accessibilityNodeInfo.addAction(8192);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    @Override // android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onKeyDown(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            boolean r0 = r4.isEnabled()
            if (r0 == 0) goto L30
            long r0 = r4.getPositionIncrement()
            r2 = 66
            r3 = 1
            if (r5 == r2) goto L27
            switch(r5) {
                case 21: goto L13;
                case 22: goto L14;
                case 23: goto L27;
                default: goto L12;
            }
        L12:
            goto L30
        L13:
            long r0 = -r0
        L14:
            boolean r0 = r4.scrubIncrementally(r0)
            if (r0 == 0) goto L30
            java.lang.Runnable r5 = r4.stopScrubbingRunnable
            r4.removeCallbacks(r5)
            java.lang.Runnable r5 = r4.stopScrubbingRunnable
            r0 = 1000(0x3e8, double:4.94E-321)
            r4.postDelayed(r5, r0)
            return r3
        L27:
            boolean r0 = r4.scrubbing
            if (r0 == 0) goto L30
            r5 = 0
            r4.stopScrubbing(r5)
            return r3
        L30:
            boolean r5 = super.onKeyDown(r5, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.DefaultTimeBar.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = i7 - getPaddingRight();
        int i9 = this.scrubberPaddingDisabled ? 0 : this.scrubberPadding;
        if (this.barGravity == 1) {
            i5 = (i8 - getPaddingBottom()) - this.touchTargetHeight;
            int i10 = this.barHeight;
            i6 = ((i8 - getPaddingBottom()) - i10) - Math.max(i9 - (i10 / 2), 0);
        } else {
            i5 = (i8 - this.touchTargetHeight) / 2;
            i6 = (i8 - this.barHeight) / 2;
        }
        this.seekBounds.set(paddingLeft, i5, paddingRight, this.touchTargetHeight + i5);
        Rect rect = this.progressBar;
        Rect rect2 = this.seekBounds;
        rect.set(rect2.left + i9, i6, rect2.right - i9, this.barHeight + i6);
        if (Util.SDK_INT >= 29) {
            setSystemGestureExclusionRectsV29(i7, i8);
        }
        update();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 0) {
            size = this.touchTargetHeight;
        } else if (mode != 1073741824) {
            size = Math.min(this.touchTargetHeight, size);
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i), size);
        updateDrawableState();
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        Drawable drawable = this.scrubberDrawable;
        if (drawable == null || !setDrawableLayoutDirection(drawable, i)) {
            return;
        }
        invalidate();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        if (r3 != 3) goto L32;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            boolean r0 = r7.isEnabled()
            r1 = 0
            if (r0 == 0) goto L76
            long r2 = r7.duration
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 > 0) goto L10
            goto L76
        L10:
            android.graphics.Point r0 = r7.resolveRelativeTouchPosition(r8)
            int r2 = r0.x
            int r0 = r0.y
            int r3 = r8.getAction()
            r4 = 1
            if (r3 == 0) goto L5d
            r5 = 3
            if (r3 == r4) goto L4e
            r6 = 2
            if (r3 == r6) goto L28
            if (r3 == r5) goto L4e
            goto L76
        L28:
            boolean r8 = r7.scrubbing
            if (r8 == 0) goto L76
            int r8 = r7.fineScrubYThreshold
            if (r0 >= r8) goto L3a
            int r8 = r7.lastCoarseScrubXPosition
            int r2 = r2 - r8
            int r2 = r2 / r5
            int r2 = r2 + r8
            float r8 = (float) r2
            r7.positionScrubber(r8)
            goto L40
        L3a:
            r7.lastCoarseScrubXPosition = r2
            float r8 = (float) r2
            r7.positionScrubber(r8)
        L40:
            long r0 = r7.getScrubberPosition()
            r7.updateScrubbing(r0)
            r7.update()
            r7.invalidate()
            return r4
        L4e:
            boolean r0 = r7.scrubbing
            if (r0 == 0) goto L76
            int r8 = r8.getAction()
            if (r8 != r5) goto L59
            r1 = r4
        L59:
            r7.stopScrubbing(r1)
            return r4
        L5d:
            float r8 = (float) r2
            float r0 = (float) r0
            boolean r0 = r7.isInSeekBar(r8, r0)
            if (r0 == 0) goto L76
            r7.positionScrubber(r8)
            long r0 = r7.getScrubberPosition()
            r7.startScrubbing(r0)
            r7.update()
            r7.invalidate()
            return r4
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.DefaultTimeBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i, @Nullable Bundle bundle) {
        if (super.performAccessibilityAction(i, bundle)) {
            return true;
        }
        if (this.duration <= 0) {
            return false;
        }
        if (i == 8192) {
            if (scrubIncrementally(-getPositionIncrement())) {
                stopScrubbing(false);
            }
        } else if (i != 4096) {
            return false;
        } else {
            if (scrubIncrementally(getPositionIncrement())) {
                stopScrubbing(false);
            }
        }
        sendAccessibilityEvent(4);
        return true;
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public void removeListener(TimeBar.OnScrubListener onScrubListener) {
        this.listeners.remove(onScrubListener);
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public void setAdGroupTimesMs(@Nullable long[] jArr, @Nullable boolean[] zArr, int i) {
        Assertions.checkArgument(i == 0 || !(jArr == null || zArr == null));
        this.adGroupCount = i;
        this.adGroupTimesMs = jArr;
        this.playedAdGroups = zArr;
        update();
    }

    public void setAdMarkerColor(@ColorInt int i) {
        this.adMarkerPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setBufferedColor(@ColorInt int i) {
        this.bufferedPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public void setBufferedPosition(long j) {
        this.bufferedPosition = j;
        update();
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public void setDuration(long j) {
        this.duration = j;
        if (this.scrubbing && j == C.TIME_UNSET) {
            stopScrubbing(true);
        }
        update();
    }

    @Override // android.view.View, com.google.android.exoplayer2.ui.TimeBar
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!this.scrubbing || z) {
            return;
        }
        stopScrubbing(true);
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public void setKeyCountIncrement(int i) {
        Assertions.checkArgument(i > 0);
        this.keyCountIncrement = i;
        this.keyTimeIncrement = C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public void setKeyTimeIncrement(long j) {
        Assertions.checkArgument(j > 0);
        this.keyCountIncrement = -1;
        this.keyTimeIncrement = j;
    }

    public void setPlayedAdMarkerColor(@ColorInt int i) {
        this.playedAdMarkerPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setPlayedColor(@ColorInt int i) {
        this.playedPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public void setPosition(long j) {
        this.position = j;
        setContentDescription(getProgressText());
        update();
    }

    public void setScrubberColor(@ColorInt int i) {
        this.scrubberPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setUnplayedColor(@ColorInt int i) {
        this.unplayedPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void showScrubber() {
        if (this.scrubberScalingAnimator.isStarted()) {
            this.scrubberScalingAnimator.cancel();
        }
        this.scrubberPaddingDisabled = false;
        this.scrubberScale = 1.0f;
        invalidate(this.seekBounds);
    }

    public DefaultTimeBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private static boolean setDrawableLayoutDirection(Drawable drawable, int i) {
        return Util.SDK_INT >= 23 && drawable.setLayoutDirection(i);
    }

    public DefaultTimeBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, attributeSet);
    }

    public DefaultTimeBar(Context context, @Nullable AttributeSet attributeSet, int i, @Nullable AttributeSet attributeSet2) {
        this(context, attributeSet, i, attributeSet2, 0);
    }

    public DefaultTimeBar(Context context, @Nullable AttributeSet attributeSet, int i, @Nullable AttributeSet attributeSet2, int i2) {
        super(context, attributeSet, i);
        this.seekBounds = new Rect();
        this.progressBar = new Rect();
        this.bufferedBar = new Rect();
        this.scrubberBar = new Rect();
        this.playedPaint = new Paint();
        this.bufferedPaint = new Paint();
        this.unplayedPaint = new Paint();
        this.adMarkerPaint = new Paint();
        this.playedAdMarkerPaint = new Paint();
        this.scrubberPaint = new Paint();
        this.scrubberPaint.setAntiAlias(true);
        this.listeners = new CopyOnWriteArraySet<>();
        this.touchPosition = new Point();
        this.density = context.getResources().getDisplayMetrics().density;
        this.fineScrubYThreshold = dpToPx(this.density, FINE_SCRUB_Y_THRESHOLD_DP);
        int dpToPx = dpToPx(this.density, 4);
        int dpToPx2 = dpToPx(this.density, 26);
        int dpToPx3 = dpToPx(this.density, 4);
        int dpToPx4 = dpToPx(this.density, 12);
        int dpToPx5 = dpToPx(this.density, 0);
        int dpToPx6 = dpToPx(this.density, 16);
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.DefaultTimeBar, i, i2);
            try {
                this.scrubberDrawable = obtainStyledAttributes.getDrawable(R.styleable.DefaultTimeBar_scrubber_drawable);
                if (this.scrubberDrawable != null) {
                    setDrawableLayoutDirection(this.scrubberDrawable);
                    dpToPx2 = Math.max(this.scrubberDrawable.getMinimumHeight(), dpToPx2);
                }
                this.barHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_bar_height, dpToPx);
                this.touchTargetHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_touch_target_height, dpToPx2);
                this.barGravity = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_bar_gravity, 0);
                this.adMarkerWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_ad_marker_width, dpToPx3);
                this.scrubberEnabledSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_scrubber_enabled_size, dpToPx4);
                this.scrubberDisabledSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_scrubber_disabled_size, dpToPx5);
                this.scrubberDraggedSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_scrubber_dragged_size, dpToPx6);
                int i3 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_played_color, -1);
                int i4 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_scrubber_color, -1);
                int i5 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_buffered_color, DEFAULT_BUFFERED_COLOR);
                int i6 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_unplayed_color, DEFAULT_UNPLAYED_COLOR);
                int i7 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_ad_marker_color, DEFAULT_AD_MARKER_COLOR);
                int i8 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_played_ad_marker_color, DEFAULT_PLAYED_AD_MARKER_COLOR);
                this.playedPaint.setColor(i3);
                this.scrubberPaint.setColor(i4);
                this.bufferedPaint.setColor(i5);
                this.unplayedPaint.setColor(i6);
                this.adMarkerPaint.setColor(i7);
                this.playedAdMarkerPaint.setColor(i8);
            } finally {
                obtainStyledAttributes.recycle();
            }
        } else {
            this.barHeight = dpToPx;
            this.touchTargetHeight = dpToPx2;
            this.barGravity = 0;
            this.adMarkerWidth = dpToPx3;
            this.scrubberEnabledSize = dpToPx4;
            this.scrubberDisabledSize = dpToPx5;
            this.scrubberDraggedSize = dpToPx6;
            this.playedPaint.setColor(-1);
            this.scrubberPaint.setColor(-1);
            this.bufferedPaint.setColor(DEFAULT_BUFFERED_COLOR);
            this.unplayedPaint.setColor(DEFAULT_UNPLAYED_COLOR);
            this.adMarkerPaint.setColor(DEFAULT_AD_MARKER_COLOR);
            this.playedAdMarkerPaint.setColor(DEFAULT_PLAYED_AD_MARKER_COLOR);
            this.scrubberDrawable = null;
        }
        this.formatBuilder = new StringBuilder();
        this.formatter = new Formatter(this.formatBuilder, Locale.getDefault());
        this.stopScrubbingRunnable = new Runnable() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$DefaultTimeBar$Qcgn0kqjCzq5x_ej2phsDpb1YTU
            @Override // java.lang.Runnable
            public final void run() {
                DefaultTimeBar.this.lambda$new$0$DefaultTimeBar();
            }
        };
        Drawable drawable = this.scrubberDrawable;
        if (drawable != null) {
            this.scrubberPadding = (drawable.getMinimumWidth() + 1) / 2;
        } else {
            this.scrubberPadding = (Math.max(this.scrubberDisabledSize, Math.max(this.scrubberEnabledSize, this.scrubberDraggedSize)) + 1) / 2;
        }
        this.scrubberScale = 1.0f;
        this.scrubberScalingAnimator = new ValueAnimator();
        this.scrubberScalingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$DefaultTimeBar$blt1q3w42nX8-1PPKXBFZvsouJg
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                DefaultTimeBar.this.lambda$new$1$DefaultTimeBar(valueAnimator);
            }
        });
        this.duration = C.TIME_UNSET;
        this.keyTimeIncrement = C.TIME_UNSET;
        this.keyCountIncrement = 20;
        setFocusable(true);
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public void hideScrubber(long j) {
        if (this.scrubberScalingAnimator.isStarted()) {
            this.scrubberScalingAnimator.cancel();
        }
        this.scrubberScalingAnimator.setFloatValues(this.scrubberScale, 0.0f);
        this.scrubberScalingAnimator.setDuration(j);
        this.scrubberScalingAnimator.start();
    }

    public void showScrubber(long j) {
        if (this.scrubberScalingAnimator.isStarted()) {
            this.scrubberScalingAnimator.cancel();
        }
        this.scrubberPaddingDisabled = false;
        this.scrubberScalingAnimator.setFloatValues(this.scrubberScale, 1.0f);
        this.scrubberScalingAnimator.setDuration(j);
        this.scrubberScalingAnimator.start();
    }
}
