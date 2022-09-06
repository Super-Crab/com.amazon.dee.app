package com.reactnativecommunity.slider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.content.ContextCompat;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public class ReactSlider extends AppCompatSeekBar {
    private static int DEFAULT_TOTAL_STEPS = 128;
    private static final String SLIDER_LABEL_PERCENTAGE_TYPE = "percentage";
    private static final String SLIDER_LABEL_RAW_VALUE_TYPE = "raw_value";
    private List<String> mAccessibilityIncrements;
    private String mAccessibilityUnits;
    private boolean mDragInProgress;
    private int mLabelColor;
    private String mLabelType;
    private double mMaxValue;
    private double mMinValue;
    private double mStep;
    private double mStepCalculated;
    private double mValue;

    public ReactSlider(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMinValue = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mMaxValue = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mValue = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mStep = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mStepCalculated = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mLabelType = "";
        this.mLabelColor = -1;
        this.mDragInProgress = false;
        disableStateListAnimatorIfNeeded();
        setInitialDrawable(context);
    }

    private void disableStateListAnimatorIfNeeded() {
        int i = Build.VERSION.SDK_INT;
    }

    private BitmapDrawable getBitmapDrawable(final String str) {
        try {
            return (BitmapDrawable) Executors.newSingleThreadExecutor().submit(new Callable<BitmapDrawable>() { // from class: com.reactnativecommunity.slider.ReactSlider.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                /* renamed from: call */
                public BitmapDrawable mo10135call() {
                    Bitmap decodeStream;
                    try {
                        if (!str.startsWith("http://") && !str.startsWith("https://") && !str.startsWith("file://") && !str.startsWith("asset://") && !str.startsWith("data:")) {
                            decodeStream = BitmapFactory.decodeResource(ReactSlider.this.getResources(), ReactSlider.this.getResources().getIdentifier(str, "drawable", ReactSlider.this.getContext().getPackageName()));
                            return new BitmapDrawable(ReactSlider.this.getResources(), decodeStream);
                        }
                        decodeStream = BitmapFactory.decodeStream(new URL(str).openStream());
                        return new BitmapDrawable(ReactSlider.this.getResources(), decodeStream);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private double getStepValue() {
        double d = this.mStep;
        return d > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? d : this.mStepCalculated;
    }

    private int getTotalSteps() {
        return (int) Math.ceil((this.mMaxValue - this.mMinValue) / getStepValue());
    }

    private void setInitialDrawable(Context context) {
        setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.progress_appearance));
        setThumb(ContextCompat.getDrawable(context, R.drawable.oval));
    }

    private void setupAccessibility() {
        List<String> list;
        if (this.mAccessibilityUnits == null || (list = this.mAccessibilityIncrements) == null || list.size() - 1 != ((int) this.mMaxValue)) {
            return;
        }
        String str = this.mAccessibilityIncrements.get((int) this.mValue);
        int length = this.mAccessibilityUnits.length();
        String str2 = this.mAccessibilityUnits;
        if (str != null && Integer.parseInt(str) == 1) {
            str2 = str2.substring(0, length - 1);
        }
        announceForAccessibility(String.format("%s %s", str, str2));
    }

    private void updateAll() {
        if (this.mStep == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            this.mStepCalculated = (this.mMaxValue - this.mMinValue) / DEFAULT_TOTAL_STEPS;
        }
        setMax(getTotalSteps());
        updateValue();
    }

    private void updateValue() {
        double d = this.mValue;
        double d2 = this.mMinValue;
        setProgress((int) Math.round(((d - d2) / (this.mMaxValue - d2)) * getTotalSteps()));
    }

    @Override // android.view.View
    public void announceForAccessibility(CharSequence charSequence) {
        Context context = getContext();
        final AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            final AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(16384);
            obtain.setClassName(ReactSlider.class.getName());
            obtain.setPackageName(context.getPackageName());
            obtain.getText().add(charSequence);
            new Timer().schedule(new TimerTask() { // from class: com.reactnativecommunity.slider.ReactSlider.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    accessibilityManager.sendAccessibilityEvent(obtain);
                }
            }, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if ((this.mLabelType.equals(SLIDER_LABEL_PERCENTAGE_TYPE) || this.mLabelType.equals(SLIDER_LABEL_RAW_VALUE_TYPE)) && this.mDragInProgress) {
            double paddingLeft = getPaddingLeft() + ((getProgress() / getMax()) * ((getWidth() - getPaddingLeft()) - getPaddingRight()));
            int height = (getHeight() / 2) - 35;
            String format = this.mLabelType.equals(SLIDER_LABEL_PERCENTAGE_TYPE) ? String.format("%d%%", Integer.valueOf((int) ((getProgress() * 100.0f) / getMax()))) : String.format("%d", Integer.valueOf((int) toRealProgress(getProgress())));
            Paint paint = new Paint();
            paint.setColor(this.mLabelColor);
            paint.setTextSize(30.0f);
            paint.setTypeface(Typeface.create("ember-regular", 0));
            canvas.drawText(format, ((int) paddingLeft) - (paint.measureText(format) / 2.0f), height, paint);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        if (accessibilityEvent.getEventType() == 32768 || (accessibilityEvent.getEventType() == 4 && isAccessibilityFocused())) {
            setupAccessibility();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAccessibilityIncrements(List<String> list) {
        this.mAccessibilityIncrements = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAccessibilityUnits(String str) {
        this.mAccessibilityUnits = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDragInProgress(boolean z) {
        this.mDragInProgress = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLabelColor(Integer num) {
        this.mLabelColor = num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLabelType(String str) {
        this.mLabelType = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMaxValue(double d) {
        this.mMaxValue = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMinValue(double d) {
        this.mMinValue = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStep(double d) {
        this.mStep = d;
        updateAll();
    }

    public void setThumbImage(String str) {
        if (str != null) {
            setThumb(getBitmapDrawable(str));
            int i = Build.VERSION.SDK_INT;
            setSplitTrack(false);
            return;
        }
        setThumb(getThumb());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(double d) {
        this.mValue = d;
        updateValue();
    }

    public double toRealProgress(int i) {
        if (i == getMax()) {
            return this.mMaxValue;
        }
        return (i * getStepValue()) + this.mMinValue;
    }
}
