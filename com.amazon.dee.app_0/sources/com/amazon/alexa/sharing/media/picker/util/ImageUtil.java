package com.amazon.alexa.sharing.media.picker.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingSDKException;
import com.amazon.alexa.sharing.sharingsdk.photos.BitmapOptionsFile;
import com.amazon.alexa.sharing.sharingsdk.photos.CommonContentProperties;
import com.amazon.alexa.sharing.util.DeviceInfoUtil;
import com.amazon.alexa.sharing.util.FileUtil;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class ImageUtil {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ImageUtil.class);
    private DeviceInfoUtil deviceInfoUtil;
    private ExifExtractor exifExtractor;
    private FileUtil fileUtil;
    private Context mContext;
    private UriUtil uriUtil;

    public ImageUtil(Context context) {
        this.mContext = context;
        this.uriUtil = new UriUtil();
        this.fileUtil = new FileUtil();
        this.exifExtractor = new ExifExtractor();
        this.deviceInfoUtil = new DeviceInfoUtil();
    }

    @Nullable
    public HashMap<String, String> extractExifFromFile(@NonNull String str) {
        try {
            return this.exifExtractor.extractExif(str);
        } catch (Exception e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("Failed to extract exif from the path " + str, e);
            return null;
        }
    }

    public CommonContentProperties getCommonContentProperties(Uri uri) throws IOException, AlexaSharingSDKException {
        try {
            return CommonContentProperties.fromPath(uri, this, true);
        } catch (FileNotFoundException e) {
            if (e.getMessage().indexOf("Permission denied") != -1) {
                return new CommonContentProperties.ImageBuilder().setPath(uri.toString()).setMime("unknown/permission-denied").build();
            }
            throw e;
        }
    }

    public String getMimeType(@NonNull Context context, @NonNull String str) {
        FileUtil fileUtil = this.fileUtil;
        Uri createUriFromFile = fileUtil.createUriFromFile(fileUtil.createFile(str));
        if (createUriFromFile.getScheme().equals("content")) {
            return context.getContentResolver().getType(createUriFromFile);
        }
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(createUriFromFile.toString());
        if (fileExtensionFromUrl == null) {
            return null;
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl.toLowerCase());
    }

    public int getOrientationFromFile(String str) {
        try {
            return this.fileUtil.createExifInterface(str).getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
        } catch (Exception unused) {
            return -1;
        }
    }

    public BitmapOptionsFile prepareOptionsForContentURI(Uri uri) throws AlexaSharingSDKException {
        String uri2 = uri.toString();
        return new BitmapOptionsFile(validateImage(uri2), this.fileUtil.createFile(uri2));
    }

    public BitmapOptionsFile prepareOptionsForFileURI(Uri uri) throws AlexaSharingSDKException, FileNotFoundException {
        BitmapFactory.Options createBitmapOptions = this.fileUtil.createBitmapOptions();
        createBitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(this.mContext.getContentResolver().openInputStream(uri), null, createBitmapOptions);
        String path = uri.getPath();
        if (createBitmapOptions.outMimeType != null && createBitmapOptions.outWidth != 0 && createBitmapOptions.outHeight != 0) {
            return new BitmapOptionsFile(createBitmapOptions, this.fileUtil.createFile(path));
        }
        throw new AlexaSharingSDKException(GeneratedOutlineSupport1.outline72("Invalid image selected - Width/height/mime is invalid for path: ", path));
    }

    public String resolveRealPath(@NonNull String str, @NonNull Context context, @NonNull Uri uri, boolean z) throws IOException {
        if (this.deviceInfoUtil.isBelowLolliPop()) {
            return this.uriUtil.getRealPathFromURI(context, uri);
        }
        if (this.deviceInfoUtil.isQAndAbove()) {
            if (z) {
                return this.fileUtil.createUriFromString(str).getPath();
            }
            return this.uriUtil.getRealPathFromCopy(uri, context);
        } else if (z) {
            return this.fileUtil.createUriFromString(str).getPath();
        } else {
            return this.uriUtil.getRealPathFromURI(context, uri);
        }
    }

    public BitmapFactory.Options validateImage(String str) throws AlexaSharingSDKException {
        BitmapFactory.Options createBitmapOptions = this.fileUtil.createBitmapOptions();
        createBitmapOptions.inJustDecodeBounds = true;
        createBitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        createBitmapOptions.inDither = true;
        BitmapFactory.decodeFile(str, createBitmapOptions);
        if (createBitmapOptions.outMimeType == null || createBitmapOptions.outWidth == 0 || createBitmapOptions.outHeight == 0) {
            throw new AlexaSharingSDKException(GeneratedOutlineSupport1.outline72("Invalid image selected - Width/height/mime is invalid for path: ", str));
        }
        return createBitmapOptions;
    }

    @Inject
    public ImageUtil(Context context, UriUtil uriUtil, FileUtil fileUtil, DeviceInfoUtil deviceInfoUtil, ExifExtractor exifExtractor) {
        this.mContext = context;
        this.uriUtil = uriUtil;
        this.fileUtil = fileUtil;
        this.deviceInfoUtil = deviceInfoUtil;
        this.exifExtractor = exifExtractor;
    }
}
