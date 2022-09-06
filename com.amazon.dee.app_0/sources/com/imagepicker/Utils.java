package com.imagepicker;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
/* loaded from: classes3.dex */
public class Utils {
    public static String cameraPermissionDescription = "This library does not require Manifest.permission.CAMERA, if you add this permission in manifest then you have to obtain the same.";
    public static String errCameraUnavailable = "camera_unavailable";
    public static String errOthers = "others";
    public static String errPermission = "permission";
    public static String fileNamePrefix = "rn_image_picker_lib_temp_";
    public static String mediaTypePhoto = "photo";
    public static String mediaTypeVideo = "video";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Uri> collectUrisFromData(Intent intent) {
        if (intent.getClipData() == null) {
            return Collections.singletonList(intent.getData());
        }
        ClipData clipData = intent.getClipData();
        ArrayList arrayList = new ArrayList(clipData.getItemCount());
        for (int i = 0; i < clipData.getItemCount(); i++) {
            arrayList.add(clipData.getItemAt(i).getUri());
        }
        return arrayList;
    }

    public static void copyUri(Uri uri, Uri uri2, ContentResolver contentResolver) {
        try {
            OutputStream openOutputStream = contentResolver.openOutputStream(uri2);
            InputStream openInputStream = contentResolver.openInputStream(uri);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    return;
                }
                openOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createFile(Context context, String str) {
        try {
            File file = new File(context.getCacheDir(), fileNamePrefix + UUID.randomUUID() + "." + str);
            file.createNewFile();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Uri createUri(File file, Context context) {
        return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".imagepickerprovider", file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deleteFile(Uri uri) {
        new File(uri.getPath()).delete();
    }

    public static Uri getAppSpecificStorageUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        Uri fromFile = Uri.fromFile(createFile(context, getFileTypeFromMime(contentResolver.getType(uri))));
        copyUri(uri, fromFile, contentResolver);
        return fromFile;
    }

    static String getBase64String(Uri uri, Context context) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            byte[] bArr = new byte[8192];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                try {
                    int read = openInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static Bitmap.CompressFormat getBitmapCompressFormat(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -1487394660) {
            if (hashCode == -879258763 && str.equals("image/png")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(MimeTypes.IMAGE_JPEG)) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return Bitmap.CompressFormat.JPEG;
            }
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReadableMap getCancelMap() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("didCancel", true);
        return createMap;
    }

    static int getDuration(Uri uri, Context context) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, uri);
        int round = Math.round(Float.parseFloat(mediaMetadataRetriever.extractMetadata(9))) / 1000;
        mediaMetadataRetriever.release();
        return round;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReadableMap getErrorMap(String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("errorCode", str);
        if (str2 != null) {
            createMap.putString("errorMessage", str2);
        }
        return createMap;
    }

    static double getFileSize(Uri uri, Context context) {
        try {
            return context.getContentResolver().openFileDescriptor(uri, "r").getStatSize();
        } catch (Exception e) {
            e.printStackTrace();
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
    }

    static String getFileTypeFromMime(String str) {
        if (str == null) {
            return ImageType.JPG;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1487394660) {
            if (hashCode != -879267568) {
                if (hashCode == -879258763 && str.equals("image/png")) {
                    c = 1;
                }
            } else if (str.equals("image/gif")) {
                c = 2;
            }
        } else if (str.equals(MimeTypes.IMAGE_JPEG)) {
            c = 0;
        }
        return c != 0 ? c != 1 ? c != 2 ? ImageType.JPG : "gif" : ImageType.PNG : ImageType.JPG;
    }

    static int[] getImageDimensBasedOnConstraints(int i, int i2, Options options) {
        int i3 = options.maxWidth;
        if (i3 == 0 || options.maxHeight == 0) {
            return new int[]{i, i2};
        }
        if (i3 < i) {
            i2 = (int) ((i3 / i) * i2);
            i = i3;
        }
        int i4 = options.maxHeight;
        if (i4 < i2) {
            i = (int) ((i4 / i2) * i);
            i2 = i4;
        }
        return new int[]{i, i2};
    }

    public static int[] getImageDimensions(Uri uri, Context context) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(openInputStream, null, options);
            return new int[]{options.outWidth, options.outHeight};
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new int[]{0, 0};
        }
    }

    static ReadableMap getImageResponseMap(Uri uri, Options options, Context context) {
        String lastPathSegment = uri.getLastPathSegment();
        int[] imageDimensions = getImageDimensions(uri, context);
        WritableMap createMap = Arguments.createMap();
        createMap.putString("uri", uri.toString());
        createMap.putDouble("fileSize", getFileSize(uri, context));
        createMap.putString("fileName", lastPathSegment);
        createMap.putString("type", getMimeTypeFromFileUri(uri));
        createMap.putInt("width", imageDimensions[0]);
        createMap.putInt("height", imageDimensions[1]);
        createMap.putString("type", getMimeType(uri, context));
        if (options.includeBase64.booleanValue()) {
            createMap.putString("base64", getBase64String(uri, context));
        }
        if (options.includeExtra.booleanValue()) {
            try {
                InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                Date parse = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(new ExifInterface(openInputStream).getAttribute(ExifInterface.TAG_DATETIME));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
                createMap.putString("timestamp", simpleDateFormat.format(parse));
                openInputStream.close();
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Could not load image exif data: "), "RNIP");
            }
        }
        return createMap;
    }

    static String getMimeType(Uri uri, Context context) {
        if (uri.getScheme().equals("file")) {
            return getMimeTypeFromFileUri(uri);
        }
        return context.getContentResolver().getType(uri);
    }

    static String getMimeTypeFromFileUri(Uri uri) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()));
    }

    static String getOrientation(Uri uri, Context context) throws IOException {
        return new ExifInterface(context.getContentResolver().openInputStream(uri)).getAttribute(ExifInterface.TAG_ORIENTATION);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReadableMap getResponseMap(List<Uri> list, Options options, Context context) throws RuntimeException {
        WritableArray createArray = Arguments.createArray();
        for (int i = 0; i < list.size(); i++) {
            Uri uri = list.get(i);
            if (isImageType(uri, context)) {
                if (uri.getScheme().contains("content")) {
                    uri = getAppSpecificStorageUri(uri, context);
                }
                createArray.pushMap(getImageResponseMap(resizeImage(uri, context, options), options, context));
            } else if (isVideoType(uri, context)) {
                createArray.pushMap(getVideoResponseMap(uri, context));
            } else {
                throw new RuntimeException("Unsupported file type");
            }
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putArray("assets", createArray);
        return createMap;
    }

    static ReadableMap getVideoResponseMap(Uri uri, Context context) {
        String lastPathSegment = uri.getLastPathSegment();
        WritableMap createMap = Arguments.createMap();
        createMap.putString("uri", uri.toString());
        createMap.putDouble("fileSize", getFileSize(uri, context));
        createMap.putInt("duration", getDuration(uri, context));
        createMap.putString("fileName", lastPathSegment);
        createMap.putString("type", getMimeType(uri, context));
        return createMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasPermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static boolean isCameraAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera") || context.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    public static boolean isCameraPermissionFulfilled(Context context, Activity activity) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null && Arrays.asList(strArr).contains("android.permission.CAMERA")) {
                if (ContextCompat.checkSelfPermission(activity, "android.permission.CAMERA") != 0) {
                    return false;
                }
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }

    static boolean isImageType(Uri uri, Context context) {
        return getMimeType(uri, context).contains("image/");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidRequestCode(int i) {
        switch (i) {
            case ImagePickerModule.REQUEST_LAUNCH_IMAGE_CAPTURE /* 13001 */:
            case ImagePickerModule.REQUEST_LAUNCH_VIDEO_CAPTURE /* 13002 */:
            case ImagePickerModule.REQUEST_LAUNCH_LIBRARY /* 13003 */:
                return true;
            default:
                return false;
        }
    }

    static boolean isVideoType(Uri uri, Context context) {
        return getMimeType(uri, context).contains("video/");
    }

    public static Uri resizeImage(Uri uri, Context context, Options options) {
        try {
            int[] imageDimensions = getImageDimensions(uri, context);
            if (!shouldResizeImage(imageDimensions[0], imageDimensions[1], options)) {
                return uri;
            }
            int[] imageDimensBasedOnConstraints = getImageDimensBasedOnConstraints(imageDimensions[0], imageDimensions[1], options);
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            String mimeTypeFromFileUri = getMimeTypeFromFileUri(uri);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(openInputStream), imageDimensBasedOnConstraints[0], imageDimensBasedOnConstraints[1], true);
            String orientation = getOrientation(uri, context);
            File createFile = createFile(context, getFileTypeFromMime(mimeTypeFromFileUri));
            createScaledBitmap.compress(getBitmapCompressFormat(mimeTypeFromFileUri), options.quality, context.getContentResolver().openOutputStream(Uri.fromFile(createFile)));
            setOrientation(createFile, orientation, context);
            return Uri.fromFile(createFile);
        } catch (Exception e) {
            e.printStackTrace();
            return uri;
        }
    }

    public static void saveToPublicDirectory(Uri uri, Context context, String str) {
        Uri insert;
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        if (str.equals("video")) {
            contentValues.put("_display_name", UUID.randomUUID().toString());
            contentValues.put("mime_type", contentResolver.getType(uri));
            insert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            contentValues.put("_display_name", UUID.randomUUID().toString());
            contentValues.put("mime_type", contentResolver.getType(uri));
            insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }
        copyUri(uri, insert, contentResolver);
    }

    public static void setFrontCamera(Intent intent) {
        int i = Build.VERSION.SDK_INT;
        intent.putExtra("android.intent.extras.CAMERA_FACING", 0);
        int i2 = Build.VERSION.SDK_INT;
        intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
    }

    static void setOrientation(File file, String str, Context context) throws IOException {
        if (str.equals(String.valueOf(1)) || str.equals(String.valueOf(0))) {
            return;
        }
        ExifInterface exifInterface = new ExifInterface(file);
        exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, str);
        exifInterface.saveAttributes();
    }

    static boolean shouldResizeImage(int i, int i2, Options options) {
        if ((options.maxWidth == 0 || options.maxHeight == 0) && options.quality == 100) {
            return false;
        }
        return options.maxWidth < i || options.maxHeight < i2 || options.quality != 100;
    }
}
