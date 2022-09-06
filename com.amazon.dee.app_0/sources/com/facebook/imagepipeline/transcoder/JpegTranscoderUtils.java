package com.facebook.imagepipeline.transcoder;

import android.graphics.Matrix;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.ImmutableList;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class JpegTranscoderUtils {
    @VisibleForTesting
    public static final int DEFAULT_JPEG_QUALITY = 85;
    private static final int FULL_ROUND = 360;
    public static final ImmutableList<Integer> INVERTED_EXIF_ORIENTATIONS = ImmutableList.of((Object[]) new Integer[]{2, 7, 4, 5});
    public static final int MAX_QUALITY = 100;
    public static final int MAX_SCALE_NUMERATOR = 16;
    public static final int MIN_QUALITY = 0;
    public static final int MIN_SCALE_NUMERATOR = 1;
    public static final int SCALE_DENOMINATOR = 8;

    @VisibleForTesting
    public static int calculateDownsampleNumerator(int downsampleRatio) {
        return Math.max(1, 8 / downsampleRatio);
    }

    @VisibleForTesting
    public static float determineResizeRatio(ResizeOptions resizeOptions, int width, int height) {
        if (resizeOptions == null) {
            return 1.0f;
        }
        float f = width;
        float f2 = height;
        float max = Math.max(resizeOptions.width / f, resizeOptions.height / f2);
        float f3 = resizeOptions.maxBitmapSize;
        if (f * max > f3) {
            max = f3 / f;
        }
        float f4 = resizeOptions.maxBitmapSize;
        return f2 * max > f4 ? f4 / f2 : max;
    }

    private static int extractOrientationFromMetadata(EncodedImage encodedImage) {
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270) {
            return encodedImage.getRotationAngle();
        }
        return 0;
    }

    public static int getForceRotatedInvertedExifOrientation(RotationOptions rotationOptions, EncodedImage encodedImage) {
        int indexOf = INVERTED_EXIF_ORIENTATIONS.indexOf(Integer.valueOf(encodedImage.getExifOrientation()));
        if (indexOf >= 0) {
            int i = 0;
            if (!rotationOptions.useImageMetadata()) {
                i = rotationOptions.getForcedAngle();
            }
            ImmutableList<Integer> immutableList = INVERTED_EXIF_ORIENTATIONS;
            return immutableList.get((indexOf + (i / 90)) % immutableList.size()).intValue();
        }
        throw new IllegalArgumentException("Only accepts inverted exif orientations");
    }

    public static int getRotationAngle(RotationOptions rotationOptions, EncodedImage encodedImage) {
        if (!rotationOptions.rotationEnabled()) {
            return 0;
        }
        int extractOrientationFromMetadata = extractOrientationFromMetadata(encodedImage);
        return rotationOptions.useImageMetadata() ? extractOrientationFromMetadata : (rotationOptions.getForcedAngle() + extractOrientationFromMetadata) % 360;
    }

    public static int getSoftwareNumerator(RotationOptions rotationOptions, @Nullable ResizeOptions resizeOptions, EncodedImage encodedImage, boolean resizingEnabled) {
        if (resizingEnabled && resizeOptions != null) {
            int rotationAngle = getRotationAngle(rotationOptions, encodedImage);
            boolean z = false;
            int forceRotatedInvertedExifOrientation = INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation())) ? getForceRotatedInvertedExifOrientation(rotationOptions, encodedImage) : 0;
            if (rotationAngle == 90 || rotationAngle == 270 || forceRotatedInvertedExifOrientation == 5 || forceRotatedInvertedExifOrientation == 7) {
                z = true;
            }
            int roundNumerator = roundNumerator(determineResizeRatio(resizeOptions, z ? encodedImage.getHeight() : encodedImage.getWidth(), z ? encodedImage.getWidth() : encodedImage.getHeight()), resizeOptions.roundUpFraction);
            if (roundNumerator > 8) {
                return 8;
            }
            if (roundNumerator >= 1) {
                return roundNumerator;
            }
            return 1;
        }
        return 8;
    }

    @Nullable
    public static Matrix getTransformationMatrix(final EncodedImage encodedImage, final RotationOptions rotationOptions) {
        if (INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()))) {
            return getTransformationMatrixFromInvertedExif(getForceRotatedInvertedExifOrientation(rotationOptions, encodedImage));
        }
        int rotationAngle = getRotationAngle(rotationOptions, encodedImage);
        if (rotationAngle == 0) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle);
        return matrix;
    }

    @Nullable
    private static Matrix getTransformationMatrixFromInvertedExif(final int orientation) {
        Matrix matrix = new Matrix();
        if (orientation == 2) {
            matrix.setScale(-1.0f, 1.0f);
        } else if (orientation == 7) {
            matrix.setRotate(-90.0f);
            matrix.postScale(-1.0f, 1.0f);
        } else if (orientation == 4) {
            matrix.setRotate(180.0f);
            matrix.postScale(-1.0f, 1.0f);
        } else if (orientation != 5) {
            return null;
        } else {
            matrix.setRotate(90.0f);
            matrix.postScale(-1.0f, 1.0f);
        }
        return matrix;
    }

    public static boolean isExifOrientationAllowed(int exifOrientation) {
        switch (exifOrientation) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    public static boolean isRotationAngleAllowed(int degrees) {
        return degrees >= 0 && degrees <= 270 && degrees % 90 == 0;
    }

    @VisibleForTesting
    public static int roundNumerator(float maxRatio, float roundUpFraction) {
        return (int) ((maxRatio * 8.0f) + roundUpFraction);
    }
}
