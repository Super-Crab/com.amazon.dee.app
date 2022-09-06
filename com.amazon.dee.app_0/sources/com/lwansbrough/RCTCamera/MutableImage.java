package com.lwansbrough.RCTCamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.facebook.react.bridge.ReadableMap;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes3.dex */
public class MutableImage {
    private static final String TAG = "RNCamera";
    private Bitmap currentRepresentation;
    private boolean hasBeenReoriented = false;
    private final byte[] originalImageData;
    private Metadata originalImageMetaData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class GPS {
        private GPS() {
        }

        private static String latitudeRef(double d) {
            return d < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? ExifInterface.LATITUDE_SOUTH : "N";
        }

        private static String longitudeRef(double d) {
            return d < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? ExifInterface.LONGITUDE_WEST : ExifInterface.LONGITUDE_EAST;
        }

        private static String toDegreeMinuteSeconds(double d) {
            double abs = Math.abs(d);
            int i = (int) abs;
            double d2 = (abs * 60.0d) - (i * 60.0d);
            int i2 = (int) d2;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(i);
            stringBuffer.append("/1,");
            stringBuffer.append(i2);
            stringBuffer.append("/1,");
            stringBuffer.append((int) (((d2 * 60.0d) - (i2 * 60.0d)) * 1000.0d));
            stringBuffer.append("/1000,");
            return stringBuffer.toString();
        }

        public static void writeExifData(double d, double d2, ExifInterface exifInterface) throws IOException {
            exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE, toDegreeMinuteSeconds(d));
            exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, latitudeRef(d));
            exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, toDegreeMinuteSeconds(d2));
            exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, longitudeRef(d2));
        }
    }

    /* loaded from: classes3.dex */
    public static class ImageMutationFailedException extends Exception {
        public ImageMutationFailedException(String str, Throwable th) {
            super(str, th);
        }

        public ImageMutationFailedException(String str) {
            super(str);
        }
    }

    public MutableImage(byte[] bArr) {
        this.originalImageData = bArr;
        this.currentRepresentation = toBitmap(bArr);
    }

    private String convertExposureTimeToDoubleFormat(String str) {
        return !str.contains("/") ? "" : Double.toString(1.0d / Double.parseDouble(str.split("/")[1]));
    }

    private Metadata originalImageMetaData() throws ImageProcessingException, IOException {
        if (this.originalImageMetaData == null) {
            this.originalImageMetaData = ImageMetadataReader.readMetadata(new BufferedInputStream(new ByteArrayInputStream(this.originalImageData)), this.originalImageData.length);
        }
        return this.originalImageMetaData;
    }

    private void rewriteOrientation(ExifInterface exifInterface) {
        exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(1));
    }

    private void rotate(int i) throws ImageMutationFailedException {
        Matrix matrix = new Matrix();
        switch (i) {
            case 1:
                return;
            case 2:
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.postRotate(180.0f);
                break;
            case 4:
                matrix.postRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.postRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.postRotate(90.0f);
                break;
            case 7:
                matrix.postRotate(270.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.postRotate(270.0f);
                break;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.currentRepresentation, 0, 0, getWidth(), getHeight(), matrix, false);
        if (createBitmap != null) {
            this.currentRepresentation = createBitmap;
            this.hasBeenReoriented = true;
            return;
        }
        throw new ImageMutationFailedException("failed to rotate");
    }

    private static Bitmap toBitmap(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            Bitmap decodeStream = BitmapFactory.decodeStream(byteArrayInputStream);
            byteArrayInputStream.close();
            return decodeStream;
        } catch (IOException e) {
            throw new IllegalStateException("Will not happen", e);
        }
    }

    private static byte[] toJpeg(Bitmap bitmap, int i) throws OutOfMemoryError {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "problem compressing jpeg", e);
            }
        }
    }

    private void writeLocationExifData(ReadableMap readableMap, ExifInterface exifInterface) {
        if (!readableMap.hasKey("metadata")) {
            return;
        }
        ReadableMap mo6945getMap = readableMap.mo6945getMap("metadata");
        if (!mo6945getMap.hasKey("location")) {
            return;
        }
        ReadableMap mo6945getMap2 = mo6945getMap.mo6945getMap("location");
        if (!mo6945getMap2.hasKey("coords")) {
            return;
        }
        try {
            ReadableMap mo6945getMap3 = mo6945getMap2.mo6945getMap("coords");
            GPS.writeExifData(mo6945getMap3.getDouble("latitude"), mo6945getMap3.getDouble("longitude"), exifInterface);
        } catch (IOException e) {
            Log.e(TAG, "Couldn't write location data", e);
        }
    }

    public void cropToPreview(double d) throws IllegalArgumentException {
        int i;
        int i2;
        int width = getWidth();
        int height = getHeight();
        double d2 = height * d;
        double d3 = width;
        if (d2 > d3) {
            i2 = (int) (d3 / d);
            i = width;
        } else {
            i = (int) d2;
            i2 = height;
        }
        this.currentRepresentation = Bitmap.createBitmap(this.currentRepresentation, (width - i) / 2, (height - i2) / 2, i, i2);
    }

    public void fixOrientation() throws ImageMutationFailedException {
        int i;
        try {
            ExifIFD0Directory exifIFD0Directory = (ExifIFD0Directory) originalImageMetaData().getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (exifIFD0Directory == null || !exifIFD0Directory.containsTag(274) || (i = exifIFD0Directory.getInt(274)) == 1) {
                return;
            }
            rotate(i);
            exifIFD0Directory.setInt(274, 1);
        } catch (ImageProcessingException | MetadataException | IOException e) {
            throw new ImageMutationFailedException("failed to fix orientation", e);
        }
    }

    public int getHeight() {
        return this.currentRepresentation.getHeight();
    }

    public int getWidth() {
        return this.currentRepresentation.getWidth();
    }

    public void mirrorImage() throws ImageMutationFailedException {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(this.currentRepresentation, 0, 0, getWidth(), getHeight(), matrix, false);
        if (createBitmap != null) {
            this.currentRepresentation = createBitmap;
            return;
        }
        throw new ImageMutationFailedException("failed to mirror");
    }

    public String toBase64(int i) {
        return Base64.encodeToString(toJpeg(this.currentRepresentation, i), 2);
    }

    public void writeDataToFile(File file, ReadableMap readableMap, int i) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(toJpeg(this.currentRepresentation, i));
        fileOutputStream.close();
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            for (Directory directory : originalImageMetaData().getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    exifInterface.setAttribute(tag.getTagName(), directory.getObject(tag.getTagType()).toString());
                }
            }
            ExifSubIFDDirectory exifSubIFDDirectory = (ExifSubIFDDirectory) originalImageMetaData().getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            for (Tag tag2 : exifSubIFDDirectory.getTags()) {
                int tagType = tag2.getTagType();
                String replaceAll = tag2.getTagName().replaceAll(" ", "");
                Object object = exifSubIFDDirectory.getObject(tagType);
                if (replaceAll.equals(ExifInterface.TAG_EXPOSURE_TIME)) {
                    exifInterface.setAttribute(replaceAll, convertExposureTimeToDoubleFormat(object.toString()));
                } else {
                    exifInterface.setAttribute(replaceAll, object.toString());
                }
            }
            writeLocationExifData(readableMap, exifInterface);
            if (this.hasBeenReoriented) {
                rewriteOrientation(exifInterface);
            }
            exifInterface.saveAttributes();
        } catch (ImageProcessingException | IOException e) {
            Log.e(TAG, "failed to save exif data", e);
        }
    }
}
