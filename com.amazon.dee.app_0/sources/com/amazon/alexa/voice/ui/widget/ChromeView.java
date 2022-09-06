package com.amazon.alexa.voice.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.amazon.alexa.voice.ui.chrome.R;
import java.lang.ref.WeakReference;
/* loaded from: classes11.dex */
public final class ChromeView extends View {
    private static final int CYAN_FADE_DURATION = 70;
    private static final int ENTER_ANIMATION_DELAY = 0;
    private static final int ENTER_ANIMATION_DURATION = 250;
    private static final float LEVEL_DIFFERENCE_THRESHOLD = 0.1f;
    private static final float LEVEL_MAX = 1.0f;
    private static final float LEVEL_MIN = 0.35f;
    private static final int MAXIMUM_OPACITY = 255;
    static final Property<ChromeView, Float> RENDER_LEVEL = new Property<ChromeView, Float>(Float.class, "renderLevel") { // from class: com.amazon.alexa.voice.ui.widget.ChromeView.1
        @Override // android.util.Property
        public Float get(ChromeView chromeView) {
            return Float.valueOf(chromeView.getRenderLevel());
        }

        @Override // android.util.Property
        public void set(ChromeView chromeView, Float f) {
            chromeView.setRenderLevel(f.floatValue());
        }
    };
    private static final int WAIT_TIME_BEFORE_FADING_A_CYAN = 150;
    private int animatedDistance;
    private long animatedDuration;
    private float animatedFraction;
    private int backgroundColor;
    private Drawable chromeDrawable;
    private boolean completedInitialChromeAnimation;
    private Drawable leftChromeDrawable;
    private float level;
    private ObjectAnimator levelAnimator;
    private Interpolator levelInterpolator;
    private boolean locked;
    private Paint paint;
    private float renderLevel;
    private Drawable rightChromeDrawable;
    private Drawable shadowDrawable;
    private int shadowHeight;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class AnimationRunnable implements Runnable {
        private final Interpolator interpolator;
        private final WeakReference<ChromeView> weakView;

        AnimationRunnable(ChromeView chromeView, Interpolator interpolator) {
            this.weakView = new WeakReference<>(chromeView);
            this.interpolator = interpolator;
        }

        @Override // java.lang.Runnable
        public void run() {
            ChromeView chromeView = this.weakView.get();
            if (chromeView == null) {
                return;
            }
            ValueAnimator ofInt = ValueAnimator.ofInt(0, (int) (((chromeView.getWidth() * 1.0f) + chromeView.getWidth()) / 2.0f));
            ofInt.setInterpolator(this.interpolator);
            ofInt.setDuration(250L);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.alexa.voice.ui.widget.ChromeView.AnimationRunnable.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ChromeView chromeView2 = (ChromeView) AnimationRunnable.this.weakView.get();
                    if (AnimationRunnable.this.weakView.get() != null) {
                        chromeView2.setAnimatedDistance(((Integer) valueAnimator.getAnimatedValue()).intValue());
                        chromeView2.setAnimatedFraction(valueAnimator.getAnimatedFraction());
                        chromeView2.setAnimatedDuration(valueAnimator.getCurrentPlayTime());
                        chromeView2.postInvalidateOnAnimation();
                        return;
                    }
                    valueAnimator.cancel();
                }
            });
            ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.voice.ui.widget.ChromeView.AnimationRunnable.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                    ChromeView chromeView2 = (ChromeView) AnimationRunnable.this.weakView.get();
                    if (AnimationRunnable.this.weakView.get() != null) {
                        chromeView2.setChromeAnimationStatus(true);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ChromeView chromeView2 = (ChromeView) AnimationRunnable.this.weakView.get();
                    if (AnimationRunnable.this.weakView.get() != null) {
                        chromeView2.setChromeAnimationStatus(true);
                    }
                }
            });
            ofInt.start();
        }
    }

    public ChromeView(Context context) {
        super(context);
        this.completedInitialChromeAnimation = false;
        initialize(context, null);
    }

    private void drawInitialAnimationFrame(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        this.paint.setColor(this.backgroundColor);
        this.paint.setAlpha((int) (this.animatedFraction * 255.0f));
        float f = width;
        canvas.drawRect(0.0f, this.shadowHeight, f, height, this.paint);
        this.shadowDrawable.setBounds(0, 0, width, this.shadowHeight);
        this.shadowDrawable.draw(canvas);
        int i = (int) (f * 1.0f);
        int max = Math.max(this.animatedDistance - i, 0);
        int min = Math.min(width, width - this.animatedDistance);
        this.leftChromeDrawable.setBounds(max, 0, Math.min(this.animatedDistance, i) + max, height);
        this.rightChromeDrawable.setBounds(min, 0, Math.min(this.animatedDistance, i) + min, height);
        long j = this.animatedDuration;
        if (j >= 150) {
            this.leftChromeDrawable.setAlpha((int) (255 - ((Math.min(j - 150, 70L) * 255) / 150)));
        } else {
            this.leftChromeDrawable.setAlpha(255);
        }
        this.leftChromeDrawable.draw(canvas);
        this.rightChromeDrawable.draw(canvas);
    }

    private void enterAnimation() {
        postDelayed(new AnimationRunnable(this, this.levelInterpolator), 0L);
    }

    private static Drawable getDrawable(Context context, int i) {
        Resources.Theme theme = context.getTheme();
        Resources resources = context.getResources();
        int i2 = Build.VERSION.SDK_INT;
        return resources.getDrawable(i, theme);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ChromeView, 0, 0);
        try {
            this.shadowHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ChromeView_voiceShadowHeight, (int) Math.ceil(displayMetrics.density * 8.0f));
            this.shadowDrawable = obtainStyledAttributes.getDrawable(R.styleable.ChromeView_voiceShadow);
            this.chromeDrawable = obtainStyledAttributes.getDrawable(R.styleable.ChromeView_voiceChrome);
            this.backgroundColor = obtainStyledAttributes.getColor(R.styleable.ChromeView_voiceColor, -14855178);
            obtainStyledAttributes.recycle();
            if (this.shadowDrawable == null) {
                this.shadowDrawable = getDrawable(context, R.drawable.voice_ui_chrome_shadow);
            }
            if (this.chromeDrawable == null) {
                this.chromeDrawable = getDrawable(context, R.drawable.voice_ui_chrome_glow);
            }
            if (this.leftChromeDrawable == null) {
                this.leftChromeDrawable = getDrawable(context, R.drawable.voice_ui_chrome_glow_left);
            }
            if (this.rightChromeDrawable == null) {
                this.rightChromeDrawable = getDrawable(context, R.drawable.voice_ui_chrome_glow_right);
            }
            this.paint = new Paint();
            this.levelInterpolator = new DecelerateInterpolator(1.72f);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void prepareLevelAnimator(boolean z) {
        ObjectAnimator objectAnimator = this.levelAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        boolean z2 = this.levelAnimator == null;
        if (z2) {
            this.levelAnimator = new ObjectAnimator();
            this.levelAnimator.setTarget(this);
            this.levelAnimator.setProperty(RENDER_LEVEL);
        }
        if (z2 || z) {
            this.levelAnimator.setRepeatCount(0);
            this.levelAnimator.setInterpolator(this.levelInterpolator);
            this.levelAnimator.setDuration(300L);
        }
    }

    public float getLevel() {
        return this.level;
    }

    float getRenderLevel() {
        return this.renderLevel;
    }

    public void hideCyanOverlay() {
        setLevel(-0.53846157f);
    }

    public boolean isLocked() {
        return this.locked;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        enterAnimation();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(null);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (!this.completedInitialChromeAnimation) {
            drawInitialAnimationFrame(canvas);
            return;
        }
        int width = getWidth();
        int height = getHeight();
        this.paint.setColor(this.backgroundColor);
        float f = width;
        canvas.drawRect(0.0f, this.shadowHeight, f, height, this.paint);
        this.shadowDrawable.setBounds(0, 0, width, this.shadowHeight);
        this.shadowDrawable.draw(canvas);
        int i = (int) (((this.renderLevel * 0.65f) + 0.35f) * f);
        int i2 = (width - i) / 2;
        this.chromeDrawable.setBounds(i2, 0, i + i2, height);
        if (this.locked) {
            int save = canvas.save();
            canvas.clipRect(0, this.shadowHeight, width, height);
            this.chromeDrawable.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        this.chromeDrawable.draw(canvas);
    }

    void setAnimatedDistance(int i) {
        this.animatedDistance = i;
    }

    void setAnimatedDuration(long j) {
        this.animatedDuration = j;
    }

    void setAnimatedFraction(float f) {
        this.animatedFraction = f;
    }

    void setChromeAnimationStatus(boolean z) {
        this.completedInitialChromeAnimation = z;
    }

    public void setLevel(float f) {
        if (this.level == f) {
            return;
        }
        this.level = f;
        if (this.locked) {
            return;
        }
        ObjectAnimator objectAnimator = this.levelAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.renderLevel = f;
        postInvalidateOnAnimation();
    }

    public void setLevelAnimated(float f) {
        if (Math.abs(this.level - f) < 0.1f) {
            return;
        }
        this.level = f;
        if (this.locked) {
            return;
        }
        prepareLevelAnimator(false);
        this.levelAnimator.setFloatValues(f);
        this.levelAnimator.start();
    }

    public void setLocked(boolean z) {
        if (this.locked != z) {
            this.locked = z;
            postInvalidateOnAnimation();
        }
    }

    public void setLockedAnimated(boolean z) {
        if (this.locked == z) {
            return;
        }
        this.locked = z;
        prepareLevelAnimator(!z);
        if (z) {
            this.levelAnimator.setFloatValues(0.35f, 1.0f);
            this.levelAnimator.setRepeatCount(-1);
            this.levelAnimator.setRepeatMode(1);
            this.levelAnimator.setDuration(600L);
            this.levelAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        } else {
            this.levelAnimator.setFloatValues(this.level);
        }
        this.levelAnimator.start();
    }

    void setRenderLevel(float f) {
        if (this.renderLevel != f) {
            this.renderLevel = f;
            postInvalidateOnAnimation();
        }
    }

    public ChromeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.completedInitialChromeAnimation = false;
        initialize(context, attributeSet);
    }

    public ChromeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.completedInitialChromeAnimation = false;
        initialize(context, attributeSet);
    }
}
