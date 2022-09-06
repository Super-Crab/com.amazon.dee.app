package com.amazon.deecomms.nativemodules.imagepicker.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.sharing.exceptions.SharingSDKException;
import com.amazon.deecomms.sharing.photos.BitmapOptionsFile;
import com.amazon.deecomms.sharing.photos.CommonContentProperties;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
/* loaded from: classes12.dex */
public class Compression {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, Compression.class);
    private Context mContext;

    public Compression(Context context) {
        this.mContext = context;
    }

    private File resize(@NonNull String str, int i, int i2, int i3) throws IOException {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        float width = decodeFile.getWidth() / decodeFile.getHeight();
        float f = i;
        float f2 = i2;
        if (f / f2 > 1.0f) {
            i = (int) (f2 * width);
        } else {
            i2 = (int) (f / width);
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeFile, i, i2, true);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), UUID.randomUUID() + com.amazon.alexa.sharing.Constants.DEFAULT_IMAGE_EXTENSION);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        createScaledBitmap.compress(Bitmap.CompressFormat.JPEG, i3, bufferedOutputStream);
        bufferedOutputStream.close();
        decodeFile.recycle();
        createScaledBitmap.recycle();
        return file;
    }

    public File compressImage(@NonNull ReadableMap readableMap, @NonNull String str, @NonNull BitmapFactory.Options options) throws IOException {
        Integer valueOf;
        Integer valueOf2;
        Double d = null;
        Integer valueOf3 = readableMap.hasKey("compressImageMaxWidth") ? Integer.valueOf(readableMap.getInt("compressImageMaxWidth")) : null;
        Integer valueOf4 = readableMap.hasKey("compressImageMaxHeight") ? Integer.valueOf(readableMap.getInt("compressImageMaxHeight")) : null;
        if (readableMap.hasKey("compressImageQuality")) {
            d = Double.valueOf(readableMap.getDouble("compressImageQuality"));
        }
        boolean z = false;
        boolean z2 = d == null || d.doubleValue() == 1.0d;
        boolean z3 = valueOf3 == null || valueOf3.intValue() >= options.outWidth;
        boolean z4 = valueOf4 == null || valueOf4.intValue() >= options.outHeight;
        List asList = Arrays.asList(MimeTypes.IMAGE_JPEG, "image/jpg", "image/png", "image/gif", "image/tiff");
        String str2 = options.outMimeType;
        if (str2 != null && asList.contains(str2.toLowerCase())) {
            z = true;
        }
        if (z2 && z3 && z4 && z) {
            LOG.d("Skip image compression");
            return new File(str);
        }
        int doubleValue = d != null ? (int) (d.doubleValue() * 100.0d) : 100;
        LOG.d("Compressing image with quality " + doubleValue);
        if (valueOf3 == null) {
            valueOf = Integer.valueOf(options.outWidth);
        } else {
            valueOf = Integer.valueOf(Math.min(valueOf3.intValue(), options.outWidth));
        }
        if (valueOf4 == null) {
            valueOf2 = Integer.valueOf(options.outHeight);
        } else {
            valueOf2 = Integer.valueOf(Math.min(valueOf4.intValue(), options.outHeight));
        }
        return resize(str, valueOf.intValue(), valueOf2.intValue(), doubleValue);
    }

    @Nullable
    public HashMap<String, String> extractExifFromFile(@NonNull String str) {
        try {
            return ExifExtractor.extractExif(str);
        } catch (Exception e) {
            Log.w("comms.sharing", "Failed to extract exif from the path " + str);
            e.printStackTrace();
            return null;
        }
    }

    public String getBase64StringFromFile(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[8192];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                try {
                    int read = fileInputStream.read(bArr);
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

    public CommonContentProperties getCommonContentProperties(Uri uri) throws IOException, SharingSDKException {
        try {
            return CommonContentProperties.fromPath(uri, this, null, true, false);
        } catch (FileNotFoundException e) {
            if (e.getMessage().indexOf("Permission denied") != -1) {
                return new CommonContentProperties.ImageBuilder().setPath(uri.toString()).setMime("unknown/permission-denied").build();
            }
            throw e;
        }
    }

    public String getMimeType(@NonNull Context context, @NonNull String str) {
        Uri fromFile = Uri.fromFile(new File(str));
        if (fromFile.getScheme().equals("content")) {
            return context.getContentResolver().getType(fromFile);
        }
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(fromFile.toString());
        if (fileExtensionFromUrl == null) {
            return null;
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl.toLowerCase());
    }

    public int getOrientationFromFile(String str) {
        try {
            return new ExifInterface(str).getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
        } catch (Exception unused) {
            return -1;
        }
    }

    public BitmapOptionsFile prepareOptionsForContentURI(Uri uri) throws SharingSDKException {
        String uri2 = uri.toString();
        return new BitmapOptionsFile(validateImage(uri2), new File(uri2));
    }

    public BitmapOptionsFile prepareOptionsForFileURI(Uri uri) throws SharingSDKException, FileNotFoundException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(this.mContext.getContentResolver().openInputStream(uri), null, options);
        String path = uri.getPath();
        if (options.outMimeType != null && options.outWidth != 0 && options.outHeight != 0) {
            return new BitmapOptionsFile(options, new File(path));
        }
        throw new SharingSDKException(GeneratedOutlineSupport.outline0("Invalid image selected - Width/height/mime is invalid for path: ", path));
    }

    public String resolveRealPath(@NonNull String str, @NonNull Context context, @NonNull Uri uri, boolean z) throws IOException {
        if (Build.VERSION.SDK_INT >= 29) {
            if (z) {
                return Uri.parse(str).getPath();
            }
            return Utils.getRealPathFromCopy(uri, context);
        } else if (z) {
            return Uri.parse(str).getPath();
        } else {
            return RealPathUtil.getRealPathFromURI(context, uri);
        }
    }

    public BitmapFactory.Options validateImage(String str) throws SharingSDKException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;
        BitmapFactory.decodeFile(str, options);
        if (options.outMimeType == null || options.outWidth == 0 || options.outHeight == 0) {
            throw new SharingSDKException(GeneratedOutlineSupport.outline0("Invalid image selected - Width/height/mime is invalid for path: ", str));
        }
        return options;
    }
}
