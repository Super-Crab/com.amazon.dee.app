package com.amazon.alexa.voice.ui.onedesign.widget.gradient;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes11.dex */
public final class DefaultGradientCompositor implements GradientCompositor {
    private static final int GRADIENT_BACKGROUND_ALPHA = 255;
    private static final float ZERO = 0.0f;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class BitmapContainer {
        private final Bitmap bitmap;
        private final boolean recyclable;

        BitmapContainer(Bitmap bitmap, boolean z) {
            this.bitmap = bitmap;
            this.recyclable = z;
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public boolean isRecyclable() {
            return this.recyclable;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @FunctionalInterface
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public interface BitmapFactory {
        Bitmap createInstance(int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @FunctionalInterface
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public interface CanvasFactory {
        Canvas createInstance(Bitmap bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionalInterface
    /* loaded from: classes11.dex */
    public interface DrawSteps {
        void draw(Canvas canvas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @FunctionalInterface
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public interface LinearGradientFactory {
        LinearGradient createInstance(GradientModel gradientModel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @FunctionalInterface
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public interface PaintFactory {
        Paint createInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap createBitmap(int i, int i2) {
        return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
    }

    private static BitmapContainer createBitmapFromDrawable(@NonNull Drawable drawable, @NonNull BitmapFactory bitmapFactory, @NonNull CanvasFactory canvasFactory) {
        if (drawable instanceof BitmapDrawable) {
            return new BitmapContainer(((BitmapDrawable) drawable).getBitmap(), false);
        }
        Bitmap createInstance = bitmapFactory.createInstance(Math.max(1, drawable.getIntrinsicWidth()), Math.max(1, drawable.getIntrinsicHeight()));
        drawable.draw(canvasFactory.createInstance(createInstance));
        return new BitmapContainer(createInstance, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Canvas createCanvas(Bitmap bitmap) {
        return new Canvas(bitmap);
    }

    private static Bitmap createCompositeBitmap(@NonNull GradientModel gradientModel, @NonNull BitmapFactory bitmapFactory, @NonNull CanvasFactory canvasFactory, @NonNull DrawSteps drawSteps) {
        Bitmap createInstance = bitmapFactory.createInstance(gradientModel.getWidth(), gradientModel.getHeight());
        Canvas createInstance2 = canvasFactory.createInstance(createInstance);
        drawSteps.draw(createInstance2);
        createInstance2.setBitmap(null);
        return createInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LinearGradient createLinearGradient(GradientModel gradientModel) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, gradientModel.getHeight(), gradientModel.getTopColor(), gradientModel.getBottomColor(), Shader.TileMode.CLAMP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Paint createPaint() {
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        paint.setAlpha(255);
        return paint;
    }

    private static void drawBackgroundImage(@NonNull Canvas canvas, @NonNull GradientModel gradientModel, @NonNull Bitmap bitmap) {
        float width = gradientModel.getWidth() - bitmap.getWidth();
        float height = gradientModel.getHeight() - bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postTranslate(width, height);
        canvas.drawBitmap(bitmap, matrix, null);
    }

    private static void drawGradientLayer(Canvas canvas, GradientModel gradientModel, PaintFactory paintFactory, LinearGradientFactory linearGradientFactory) {
        Paint createInstance = paintFactory.createInstance();
        createInstance.setShader(linearGradientFactory.createInstance(gradientModel));
        canvas.drawRect(0.0f, 0.0f, gradientModel.getWidth(), gradientModel.getHeight(), createInstance);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createBitmapWithBackgroundColor$0(int i, GradientModel gradientModel, PaintFactory paintFactory, LinearGradientFactory linearGradientFactory, Canvas canvas) {
        canvas.drawColor(i);
        drawGradientLayer(canvas, gradientModel, paintFactory, linearGradientFactory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createBitmapWithBackgroundColorAndDrawable$1(GradientBackgroundModel gradientBackgroundModel, BitmapFactory bitmapFactory, CanvasFactory canvasFactory, GradientModel gradientModel, PaintFactory paintFactory, LinearGradientFactory linearGradientFactory, Canvas canvas) {
        BitmapContainer createBitmapFromDrawable = createBitmapFromDrawable(gradientBackgroundModel.getDrawable(), bitmapFactory, canvasFactory);
        Bitmap bitmap = createBitmapFromDrawable.getBitmap();
        canvas.drawColor(gradientBackgroundModel.getColor());
        drawBackgroundImage(canvas, gradientModel, bitmap);
        drawGradientLayer(canvas, gradientModel, paintFactory, linearGradientFactory);
        if (createBitmapFromDrawable.isRecyclable()) {
            bitmap.recycle();
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientCompositor
    public Bitmap createBitmapWithBackgroundColor(@NonNull GradientModel gradientModel, int i) {
        return createBitmapWithBackgroundColor(gradientModel, i, $$Lambda$DefaultGradientCompositor$0LwYpjr6d6fcXbKT913wgjn46xk.INSTANCE, $$Lambda$DefaultGradientCompositor$S4GLurg3u2AfmE0e9idPYOMrXT0.INSTANCE, $$Lambda$DefaultGradientCompositor$VE2P_DtGqc5an8rbrZsKxGrOdU.INSTANCE, $$Lambda$DefaultGradientCompositor$1phK3b3gbpYKwPD8v_zL8WV0_Ww.INSTANCE);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientCompositor
    public Bitmap createBitmapWithBackgroundColorAndDrawable(@NonNull GradientModel gradientModel, @NonNull GradientBackgroundModel gradientBackgroundModel) {
        return createBitmapWithBackgroundColorAndDrawable(gradientModel, gradientBackgroundModel, $$Lambda$DefaultGradientCompositor$0LwYpjr6d6fcXbKT913wgjn46xk.INSTANCE, $$Lambda$DefaultGradientCompositor$S4GLurg3u2AfmE0e9idPYOMrXT0.INSTANCE, $$Lambda$DefaultGradientCompositor$VE2P_DtGqc5an8rbrZsKxGrOdU.INSTANCE, $$Lambda$DefaultGradientCompositor$1phK3b3gbpYKwPD8v_zL8WV0_Ww.INSTANCE);
    }

    @VisibleForTesting
    static Bitmap createBitmapWithBackgroundColor(@NonNull final GradientModel gradientModel, final int i, @NonNull BitmapFactory bitmapFactory, @NonNull CanvasFactory canvasFactory, @NonNull final PaintFactory paintFactory, @NonNull final LinearGradientFactory linearGradientFactory) {
        return createCompositeBitmap(gradientModel, bitmapFactory, canvasFactory, new DrawSteps() { // from class: com.amazon.alexa.voice.ui.onedesign.widget.gradient.-$$Lambda$DefaultGradientCompositor$U63fCZaH62q3_b-jtAZ3F4jqzeU
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.DefaultGradientCompositor.DrawSteps
            public final void draw(Canvas canvas) {
                DefaultGradientCompositor.lambda$createBitmapWithBackgroundColor$0(i, gradientModel, paintFactory, linearGradientFactory, canvas);
            }
        });
    }

    @VisibleForTesting
    static Bitmap createBitmapWithBackgroundColorAndDrawable(@NonNull final GradientModel gradientModel, @NonNull final GradientBackgroundModel gradientBackgroundModel, @NonNull final BitmapFactory bitmapFactory, @NonNull final CanvasFactory canvasFactory, @NonNull final PaintFactory paintFactory, @NonNull final LinearGradientFactory linearGradientFactory) {
        return createCompositeBitmap(gradientModel, bitmapFactory, canvasFactory, new DrawSteps() { // from class: com.amazon.alexa.voice.ui.onedesign.widget.gradient.-$$Lambda$DefaultGradientCompositor$IMewwPGeaRT7spLhE6O-PG97g-w
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.DefaultGradientCompositor.DrawSteps
            public final void draw(Canvas canvas) {
                DefaultGradientCompositor.lambda$createBitmapWithBackgroundColorAndDrawable$1(GradientBackgroundModel.this, bitmapFactory, canvasFactory, gradientModel, paintFactory, linearGradientFactory, canvas);
            }
        });
    }
}
