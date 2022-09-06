package com.amazon.mobile.heremapsexplore.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.mobile.heremapsexplore.Constants.Constants;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class ImageUtils {
    private static ImageUtils instance;
    private WeakReference<Context> context;

    /* renamed from: com.amazon.mobile.heremapsexplore.Utilities.ImageUtils$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mobile$heremapsexplore$Utilities$ImageUtils$ImageLayoutPosition = new int[ImageLayoutPosition.values().length];

        static {
            try {
                $SwitchMap$com$amazon$mobile$heremapsexplore$Utilities$ImageUtils$ImageLayoutPosition[ImageLayoutPosition.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public enum ImageLayoutPosition {
        CENTER,
        BOTTOM_CENTER
    }

    private ImageUtils() {
    }

    public static ImageUtils getInstance() {
        if (instance == null) {
            synchronized (ImageUtils.class) {
                if (instance == null) {
                    instance = new ImageUtils();
                }
            }
        }
        return instance;
    }

    public Bitmap combineImage(Bitmap bitmap, Bitmap bitmap2, ImageLayoutPosition imageLayoutPosition) {
        if (imageLayoutPosition.ordinal() != 0) {
            return null;
        }
        return combineImageWithForegroundOriginInPx(bitmap, bitmap2, (int) (((bitmap2.getWidth() - bitmap.getWidth()) / 2.0f) + 0.5f), (int) (((bitmap2.getHeight() - bitmap.getHeight()) / 2.0f) + 0.5f));
    }

    public Bitmap combineImageWithForegroundOriginInPx(Bitmap bitmap, Bitmap bitmap2, int i, int i2) {
        int i3 = 0;
        int max = Math.max(bitmap2.getWidth(), bitmap.getWidth() + i) - Math.min(0, i);
        int max2 = Math.max(bitmap2.getHeight(), bitmap.getHeight() + i2) - Math.min(0, i2);
        int i4 = i < 0 ? 0 : i;
        int i5 = i2 < 0 ? 0 : i2;
        int i6 = i > 0 ? 0 : -i;
        if (i2 <= 0) {
            i3 = -i2;
        }
        Bitmap createBitmap = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap2, i6, i3, (Paint) null);
        canvas.drawBitmap(bitmap, i4, i5, (Paint) null);
        return createBitmap;
    }

    public Bitmap cropImage(Bitmap bitmap, int i, int i2, int i3, int i4) {
        int dpToPx = (int) (UIUtils.dpToPx(getContext(), i) + 0.5f);
        int dpToPx2 = (int) (UIUtils.dpToPx(getContext(), i3) + 0.5f);
        Bitmap createBitmap = Bitmap.createBitmap(Math.min(bitmap.getWidth(), (bitmap.getWidth() - dpToPx) - ((int) (UIUtils.dpToPx(getContext(), i2) + 0.5f))), Math.min(bitmap.getHeight(), (bitmap.getHeight() - dpToPx2) - ((int) (UIUtils.dpToPx(getContext(), i4) + 0.5f))), Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, -dpToPx, -dpToPx2, (Paint) null);
        return createBitmap;
    }

    public Bitmap drawCircles(List<Map<String, Integer>> list) {
        int i = 0;
        for (Map<String, Integer> map : list) {
            if (map.containsKey("radius")) {
                i = Math.max(i, map.get("radius").intValue());
            }
        }
        int dpToPx = ((int) (UIUtils.dpToPx(getContext(), i) + 0.5f)) * 2;
        Bitmap createBitmap = Bitmap.createBitmap(dpToPx, dpToPx, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        for (Map<String, Integer> map2 : list) {
            if (map2.containsKey("color")) {
                paint.setColor(map2.get("color").intValue());
            }
            if (map2.containsKey("radius")) {
                float f = dpToPx / 2.0f;
                canvas.drawCircle(f, f, (int) (UIUtils.dpToPx(getContext(), map2.get("radius").intValue()) + 0.5f), paint);
            }
        }
        return createBitmap;
    }

    public Bitmap dropShadow(Bitmap bitmap, int i, int i2, int i3, int i4) throws IllegalStateException {
        if (getContext() != null) {
            int dpToPx = (int) (UIUtils.dpToPx(getContext(), i) + 0.5f);
            int dpToPx2 = (int) (UIUtils.dpToPx(getContext(), i2) + 0.5f);
            float f = i3;
            int i5 = (int) (0.5f + f);
            int i6 = dpToPx + i5;
            int i7 = i5 + dpToPx2;
            int width = (i6 * 2) + bitmap.getWidth();
            int height = (i7 * 2) + bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.setTranslate(i6, i7);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.postTranslate(dpToPx, dpToPx2);
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint(1);
            canvas.drawBitmap(bitmap, matrix2, paint);
            BlurMaskFilter blurMaskFilter = new BlurMaskFilter(f, BlurMaskFilter.Blur.NORMAL);
            paint.reset();
            paint.setAntiAlias(true);
            paint.setColor(i4);
            paint.setMaskFilter(blurMaskFilter);
            paint.setFilterBitmap(true);
            Bitmap createBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap2);
            canvas2.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
            canvas2.drawBitmap(bitmap, matrix, null);
            createBitmap.recycle();
            return createBitmap2;
        }
        throw new IllegalStateException("Context is not set for ImageUtils class");
    }

    public Context getContext() {
        WeakReference<Context> weakReference = this.context;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public Bitmap imageFromText(String str, String str2, int i, int i2) throws IllegalStateException {
        if (getContext() != null) {
            Rect rect = new Rect();
            Paint paint = new Paint();
            paint.setColor(i2);
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTextSize((int) (UIUtils.dpToPx(getContext(), i) + 0.5f));
            try {
                paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), str2));
            } catch (Exception e) {
                paint.setFakeBoldText(true);
                Log.w(Constants.LOG_TAG, e.toString());
            }
            paint.getTextBounds(str, 0, str.length(), rect);
            Bitmap createBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap).drawText(str, -rect.left, rect.height() - rect.bottom, paint);
            return createBitmap;
        }
        throw new IllegalStateException("Context is not set for ImageUtils class");
    }

    public Bitmap loadImage(int i) {
        return BitmapFactory.decodeResource(getContext().getResources(), i);
    }

    public Bitmap loadImageWithTintColor(int i, int i2) {
        return tintImage(loadImage(i), i2);
    }

    public void setContext(Context context) {
        this.context = new WeakReference<>(context);
    }

    public Bitmap tintImage(Bitmap bitmap, int i) {
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public Bitmap combineImage(Bitmap bitmap, Bitmap bitmap2, int i, int i2) {
        return combineImageWithForegroundOriginInPx(bitmap, bitmap2, (int) (UIUtils.dpToPx(getContext(), i) + 0.5f), (int) (UIUtils.dpToPx(getContext(), i2) + 0.5f));
    }
}
