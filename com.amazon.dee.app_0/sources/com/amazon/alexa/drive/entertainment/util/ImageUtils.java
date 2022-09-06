package com.amazon.alexa.drive.entertainment.util;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
/* loaded from: classes7.dex */
public final class ImageUtils {
    private static final String TAG = "ImageUtils";

    private ImageUtils() {
    }

    public static Bitmap applyFastGaussianBlurToBitmap(@NonNull Bitmap bitmap, int i) {
        int[] iArr;
        int i2 = i;
        Log.i(TAG, "applyFastGaussianBlurToBitmap " + i2);
        if (bitmap.isRecycled()) {
            Log.i(TAG, "Cannot blur recycled bitmaps");
            return null;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i3 = width * height;
            int[] iArr2 = new int[i3];
            bitmap.getPixels(iArr2, 0, width, 0, 0, width, height);
            int i4 = width - 1;
            int i5 = height - 1;
            int i6 = i2 + i2 + 1;
            int[] iArr3 = new int[i3];
            int[] iArr4 = new int[i3];
            int[] iArr5 = new int[i3];
            int[] iArr6 = new int[Math.max(width, height)];
            int i7 = (i6 + 1) >> 1;
            int i8 = i7 * i7;
            int i9 = i8 * 256;
            int[] iArr7 = new int[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                iArr7[i10] = i10 / i8;
            }
            int[][] iArr8 = (int[][]) Array.newInstance(int.class, i6, 3);
            int i11 = i2 + 1;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            while (i12 < height) {
                int i15 = height;
                int i16 = -i2;
                int i17 = 0;
                int i18 = 0;
                int i19 = 0;
                int i20 = 0;
                int i21 = 0;
                int i22 = 0;
                int i23 = 0;
                int i24 = 0;
                int i25 = 0;
                while (i16 <= i2) {
                    int i26 = i5;
                    int i27 = iArr2[Math.min(i4, Math.max(i16, 0)) + i13];
                    int[] iArr9 = iArr8[i16 + i2];
                    iArr9[0] = (i27 & 16711680) >> 16;
                    iArr9[1] = (i27 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                    iArr9[2] = i27 & 255;
                    int abs = i11 - Math.abs(i16);
                    i17 = (iArr9[0] * abs) + i17;
                    i18 = (iArr9[1] * abs) + i18;
                    i19 = (iArr9[2] * abs) + i19;
                    if (i16 > 0) {
                        i21 += iArr9[0];
                        i23 += iArr9[1];
                        i25 += iArr9[2];
                    } else {
                        i20 += iArr9[0];
                        i22 += iArr9[1];
                        i24 += iArr9[2];
                    }
                    i16++;
                    i5 = i26;
                }
                int i28 = i5;
                int i29 = 0;
                int i30 = i2;
                while (i29 < width) {
                    iArr3[i13] = iArr7[i17];
                    iArr4[i13] = iArr7[i18];
                    iArr5[i13] = iArr7[i19];
                    int i31 = i17 - i20;
                    int i32 = i18 - i22;
                    int i33 = i19 - i24;
                    int[] iArr10 = iArr8[((i30 - i2) + i6) % i6];
                    int i34 = i20 - iArr10[0];
                    int i35 = i22 - iArr10[1];
                    int i36 = i24 - iArr10[2];
                    if (i12 == 0) {
                        iArr = iArr7;
                        iArr6[i29] = Math.min(i29 + i2 + 1, i4);
                    } else {
                        iArr = iArr7;
                    }
                    int i37 = iArr2[i14 + iArr6[i29]];
                    iArr10[0] = (i37 & 16711680) >> 16;
                    iArr10[1] = (i37 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                    iArr10[2] = i37 & 255;
                    int i38 = i21 + iArr10[0];
                    int i39 = i23 + iArr10[1];
                    int i40 = i25 + iArr10[2];
                    i17 = i31 + i38;
                    i18 = i32 + i39;
                    i19 = i33 + i40;
                    i30 = (i30 + 1) % i6;
                    int[] iArr11 = iArr8[i30 % i6];
                    i20 = i34 + iArr11[0];
                    i22 = i35 + iArr11[1];
                    i24 = i36 + iArr11[2];
                    i21 = i38 - iArr11[0];
                    i23 = i39 - iArr11[1];
                    i25 = i40 - iArr11[2];
                    i13++;
                    i29++;
                    iArr7 = iArr;
                }
                i14 += width;
                i12++;
                height = i15;
                i5 = i28;
            }
            int i41 = height;
            int i42 = i5;
            int[] iArr12 = iArr7;
            int i43 = 0;
            while (i43 < width) {
                int i44 = -i2;
                int i45 = i44 * width;
                int i46 = 0;
                int i47 = 0;
                int i48 = 0;
                int i49 = 0;
                int i50 = 0;
                int i51 = 0;
                int i52 = 0;
                int i53 = 0;
                int i54 = 0;
                while (i44 <= i2) {
                    int[] iArr13 = iArr6;
                    int max = Math.max(0, i45) + i43;
                    int[] iArr14 = iArr8[i44 + i2];
                    iArr14[0] = iArr3[max];
                    iArr14[1] = iArr4[max];
                    iArr14[2] = iArr5[max];
                    int abs2 = i11 - Math.abs(i44);
                    i46 = (iArr3[max] * abs2) + i46;
                    i47 = (iArr4[max] * abs2) + i47;
                    i48 = (iArr5[max] * abs2) + i48;
                    if (i44 > 0) {
                        i50 += iArr14[0];
                        i52 += iArr14[1];
                        i54 += iArr14[2];
                    } else {
                        i49 += iArr14[0];
                        i51 += iArr14[1];
                        i53 += iArr14[2];
                    }
                    int i55 = i42;
                    if (i44 < i55) {
                        i45 += width;
                    }
                    i44++;
                    i42 = i55;
                    iArr6 = iArr13;
                }
                int[] iArr15 = iArr6;
                int i56 = i42;
                int i57 = 0;
                int i58 = i43;
                int i59 = i54;
                int i60 = i52;
                int i61 = i50;
                int i62 = i2;
                int i63 = i47;
                int i64 = i46;
                int i65 = i41;
                int i66 = i49;
                int i67 = i48;
                int i68 = i63;
                while (i57 < i65) {
                    iArr2[i58] = (iArr2[i58] & ViewCompat.MEASURED_STATE_MASK) | (iArr12[i64] << 16) | (iArr12[i68] << 8) | iArr12[i67];
                    int i69 = i64 - i66;
                    int i70 = i68 - i51;
                    int i71 = i67 - i53;
                    int[] iArr16 = iArr8[((i62 - i2) + i6) % i6];
                    int i72 = i66 - iArr16[0];
                    int i73 = i51 - iArr16[1];
                    int i74 = i53 - iArr16[2];
                    if (i43 == 0) {
                        iArr15[i57] = Math.min(i57 + i11, i56) * width;
                    }
                    int i75 = iArr15[i57] + i43;
                    iArr16[0] = iArr3[i75];
                    iArr16[1] = iArr4[i75];
                    iArr16[2] = iArr5[i75];
                    int i76 = i61 + iArr16[0];
                    int i77 = i60 + iArr16[1];
                    int i78 = i59 + iArr16[2];
                    i64 = i69 + i76;
                    i68 = i70 + i77;
                    i67 = i71 + i78;
                    i62 = (i62 + 1) % i6;
                    int[] iArr17 = iArr8[i62];
                    i66 = i72 + iArr17[0];
                    i51 = i73 + iArr17[1];
                    i53 = i74 + iArr17[2];
                    i61 = i76 - iArr17[0];
                    i60 = i77 - iArr17[1];
                    i59 = i78 - iArr17[2];
                    i58 += width;
                    i57++;
                    i2 = i;
                }
                i43++;
                i2 = i;
                i42 = i56;
                i41 = i65;
                iArr6 = iArr15;
            }
            bitmap.setPixels(iArr2, 0, width, 0, 0, width, i41);
            return bitmap;
        } catch (OutOfMemoryError e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OutOfMemoryError occurred when loading bitmap ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
            bitmap.recycle();
            return null;
        }
    }
}
