package com.google.android.material.button;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class MaterialButtonHelper {
    private static final float CORNER_RADIUS_ADJUSTMENT = 1.0E-5f;
    private static final int DEFAULT_BACKGROUND_COLOR = -1;
    private static final boolean IS_LOLLIPOP;
    @Nullable
    private GradientDrawable backgroundDrawableLollipop;
    @Nullable
    private ColorStateList backgroundTint;
    @Nullable
    private PorterDuff.Mode backgroundTintMode;
    @Nullable
    private GradientDrawable colorableBackgroundDrawableCompat;
    private int cornerRadius;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;
    private int insetTop;
    @Nullable
    private GradientDrawable maskDrawableLollipop;
    private final MaterialButton materialButton;
    @Nullable
    private ColorStateList rippleColor;
    @Nullable
    private GradientDrawable rippleDrawableCompat;
    @Nullable
    private ColorStateList strokeColor;
    @Nullable
    private GradientDrawable strokeDrawableLollipop;
    private int strokeWidth;
    @Nullable
    private Drawable tintableBackgroundDrawableCompat;
    @Nullable
    private Drawable tintableRippleDrawableCompat;
    private final Paint buttonStrokePaint = new Paint(1);
    private final Rect bounds = new Rect();
    private final RectF rectF = new RectF();
    private boolean backgroundOverwritten = false;

    static {
        int i = Build.VERSION.SDK_INT;
        IS_LOLLIPOP = true;
    }

    public MaterialButtonHelper(MaterialButton materialButton) {
        this.materialButton = materialButton;
    }

    private Drawable createBackgroundCompat() {
        this.colorableBackgroundDrawableCompat = new GradientDrawable();
        this.colorableBackgroundDrawableCompat.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.colorableBackgroundDrawableCompat.setColor(-1);
        this.tintableBackgroundDrawableCompat = DrawableCompat.wrap(this.colorableBackgroundDrawableCompat);
        DrawableCompat.setTintList(this.tintableBackgroundDrawableCompat, this.backgroundTint);
        PorterDuff.Mode mode = this.backgroundTintMode;
        if (mode != null) {
            DrawableCompat.setTintMode(this.tintableBackgroundDrawableCompat, mode);
        }
        this.rippleDrawableCompat = new GradientDrawable();
        this.rippleDrawableCompat.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.rippleDrawableCompat.setColor(-1);
        this.tintableRippleDrawableCompat = DrawableCompat.wrap(this.rippleDrawableCompat);
        DrawableCompat.setTintList(this.tintableRippleDrawableCompat, this.rippleColor);
        return wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.tintableBackgroundDrawableCompat, this.tintableRippleDrawableCompat}));
    }

    @TargetApi(21)
    private Drawable createBackgroundLollipop() {
        this.backgroundDrawableLollipop = new GradientDrawable();
        this.backgroundDrawableLollipop.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.backgroundDrawableLollipop.setColor(-1);
        updateTintAndTintModeLollipop();
        this.strokeDrawableLollipop = new GradientDrawable();
        this.strokeDrawableLollipop.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.strokeDrawableLollipop.setColor(0);
        this.strokeDrawableLollipop.setStroke(this.strokeWidth, this.strokeColor);
        InsetDrawable wrapDrawableWithInset = wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.backgroundDrawableLollipop, this.strokeDrawableLollipop}));
        this.maskDrawableLollipop = new GradientDrawable();
        this.maskDrawableLollipop.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.maskDrawableLollipop.setColor(-1);
        return new MaterialButtonBackgroundDrawable(RippleUtils.convertToRippleDrawableColor(this.rippleColor), wrapDrawableWithInset, this.maskDrawableLollipop);
    }

    @Nullable
    private GradientDrawable unwrapBackgroundDrawable() {
        if (!IS_LOLLIPOP || this.materialButton.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(0);
    }

    @Nullable
    private GradientDrawable unwrapStrokeDrawable() {
        if (!IS_LOLLIPOP || this.materialButton.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(1);
    }

    private void updateStroke() {
        if (IS_LOLLIPOP && this.strokeDrawableLollipop != null) {
            this.materialButton.setInternalBackground(createBackgroundLollipop());
        } else if (IS_LOLLIPOP) {
        } else {
            this.materialButton.invalidate();
        }
    }

    private void updateTintAndTintModeLollipop() {
        GradientDrawable gradientDrawable = this.backgroundDrawableLollipop;
        if (gradientDrawable != null) {
            DrawableCompat.setTintList(gradientDrawable, this.backgroundTint);
            PorterDuff.Mode mode = this.backgroundTintMode;
            if (mode == null) {
                return;
            }
            DrawableCompat.setTintMode(this.backgroundDrawableLollipop, mode);
        }
    }

    private InsetDrawable wrapDrawableWithInset(Drawable drawable) {
        return new InsetDrawable(drawable, this.insetLeft, this.insetTop, this.insetRight, this.insetBottom);
    }

    void drawStroke(@Nullable Canvas canvas) {
        if (canvas == null || this.strokeColor == null || this.strokeWidth <= 0) {
            return;
        }
        this.bounds.set(this.materialButton.getBackground().getBounds());
        RectF rectF = this.rectF;
        Rect rect = this.bounds;
        int i = this.strokeWidth;
        rectF.set((i / 2.0f) + rect.left + this.insetLeft, (i / 2.0f) + rect.top + this.insetTop, (rect.right - (i / 2.0f)) - this.insetRight, (rect.bottom - (i / 2.0f)) - this.insetBottom);
        float f = this.cornerRadius - (this.strokeWidth / 2.0f);
        canvas.drawRoundRect(this.rectF, f, f, this.buttonStrokePaint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCornerRadius() {
        return this.cornerRadius;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ColorStateList getStrokeColor() {
        return this.strokeColor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList getSupportBackgroundTintList() {
        return this.backgroundTint;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBackgroundOverwritten() {
        return this.backgroundOverwritten;
    }

    public void loadFromAttributes(TypedArray typedArray) {
        int i = 0;
        this.insetLeft = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetLeft, 0);
        this.insetRight = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
        this.insetTop = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
        this.insetBottom = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
        this.cornerRadius = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_cornerRadius, 0);
        this.strokeWidth = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
        this.backgroundTintMode = ViewUtils.parseTintMode(typedArray.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.backgroundTint = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, R.styleable.MaterialButton_backgroundTint);
        this.strokeColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, R.styleable.MaterialButton_strokeColor);
        this.rippleColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, R.styleable.MaterialButton_rippleColor);
        this.buttonStrokePaint.setStyle(Paint.Style.STROKE);
        this.buttonStrokePaint.setStrokeWidth(this.strokeWidth);
        Paint paint = this.buttonStrokePaint;
        ColorStateList colorStateList = this.strokeColor;
        if (colorStateList != null) {
            i = colorStateList.getColorForState(this.materialButton.getDrawableState(), 0);
        }
        paint.setColor(i);
        int paddingStart = ViewCompat.getPaddingStart(this.materialButton);
        int paddingTop = this.materialButton.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this.materialButton);
        int paddingBottom = this.materialButton.getPaddingBottom();
        this.materialButton.setInternalBackground(IS_LOLLIPOP ? createBackgroundLollipop() : createBackgroundCompat());
        ViewCompat.setPaddingRelative(this.materialButton, paddingStart + this.insetLeft, paddingTop + this.insetTop, paddingEnd + this.insetRight, paddingBottom + this.insetBottom);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBackgroundColor(int i) {
        GradientDrawable gradientDrawable;
        GradientDrawable gradientDrawable2;
        if (IS_LOLLIPOP && (gradientDrawable2 = this.backgroundDrawableLollipop) != null) {
            gradientDrawable2.setColor(i);
        } else if (IS_LOLLIPOP || (gradientDrawable = this.colorableBackgroundDrawableCompat) == null) {
        } else {
            gradientDrawable.setColor(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBackgroundOverwritten() {
        this.backgroundOverwritten = true;
        this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
        this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCornerRadius(int i) {
        GradientDrawable gradientDrawable;
        GradientDrawable gradientDrawable2;
        if (this.cornerRadius != i) {
            this.cornerRadius = i;
            if (IS_LOLLIPOP && (gradientDrawable2 = this.backgroundDrawableLollipop) != null && this.strokeDrawableLollipop != null && this.maskDrawableLollipop != null) {
                int i2 = Build.VERSION.SDK_INT;
                float f = i + CORNER_RADIUS_ADJUSTMENT;
                gradientDrawable2.setCornerRadius(f);
                this.strokeDrawableLollipop.setCornerRadius(f);
                this.maskDrawableLollipop.setCornerRadius(f);
            } else if (IS_LOLLIPOP || (gradientDrawable = this.colorableBackgroundDrawableCompat) == null || this.rippleDrawableCompat == null) {
            } else {
                float f2 = i + CORNER_RADIUS_ADJUSTMENT;
                gradientDrawable.setCornerRadius(f2);
                this.rippleDrawableCompat.setCornerRadius(f2);
                this.materialButton.invalidate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        Drawable drawable;
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            if (IS_LOLLIPOP && (this.materialButton.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.materialButton.getBackground()).setColor(colorStateList);
            } else if (IS_LOLLIPOP || (drawable = this.tintableRippleDrawableCompat) == null) {
            } else {
                DrawableCompat.setTintList(drawable, colorStateList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        if (this.strokeColor != colorStateList) {
            this.strokeColor = colorStateList;
            Paint paint = this.buttonStrokePaint;
            int i = 0;
            if (colorStateList != null) {
                i = colorStateList.getColorForState(this.materialButton.getDrawableState(), 0);
            }
            paint.setColor(i);
            updateStroke();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStrokeWidth(int i) {
        if (this.strokeWidth != i) {
            this.strokeWidth = i;
            this.buttonStrokePaint.setStrokeWidth(i);
            updateStroke();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (this.backgroundTint != colorStateList) {
            this.backgroundTint = colorStateList;
            if (IS_LOLLIPOP) {
                updateTintAndTintModeLollipop();
                return;
            }
            Drawable drawable = this.tintableBackgroundDrawableCompat;
            if (drawable == null) {
                return;
            }
            DrawableCompat.setTintList(drawable, this.backgroundTint);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        PorterDuff.Mode mode2;
        if (this.backgroundTintMode != mode) {
            this.backgroundTintMode = mode;
            if (IS_LOLLIPOP) {
                updateTintAndTintModeLollipop();
                return;
            }
            Drawable drawable = this.tintableBackgroundDrawableCompat;
            if (drawable == null || (mode2 = this.backgroundTintMode) == null) {
                return;
            }
            DrawableCompat.setTintMode(drawable, mode2);
        }
    }

    void updateMaskBounds(int i, int i2) {
        GradientDrawable gradientDrawable = this.maskDrawableLollipop;
        if (gradientDrawable != null) {
            gradientDrawable.setBounds(this.insetLeft, this.insetTop, i2 - this.insetRight, i - this.insetBottom);
        }
    }
}
