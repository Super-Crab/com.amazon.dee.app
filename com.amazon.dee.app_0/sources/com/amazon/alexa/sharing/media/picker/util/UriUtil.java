package com.amazon.alexa.sharing.media.picker.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.util.DeviceInfoUtil;
import com.amazon.alexa.sharing.util.FileUtil;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes10.dex */
public class UriUtil {
    public static final String AUTHORITY_DOWNLOADED_DOCUMENT = "com.android.providers.downloads.documents";
    public static final String AUTHORITY_EXTERNAL_DOCUMENT = "com.android.externalstorage.documents";
    public static final String AUTHORITY_GOOGLE_DOCUMENT = "com.google.android.apps.docs.storage";
    public static final String AUTHORITY_GOOGLE_PHOTOS = "com.google.android.apps.photos.content";
    public static final String AUTHORITY_MEDIA_DOCUMENT = "com.android.providers.media.documents";
    private static final String DOC_ID_PATTERN = ".+:.+";
    private static final String DOWNLOAD_CONTENT_URI = "content://downloads/public_downloads";
    private static final String DOWNLOAD_DOC_ID_PREFIX = "raw:";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, UriUtil.class);
    private final DeviceInfoUtil deviceInfoUtil;
    private final FileUtil fileUtil;

    public UriUtil() {
        this.fileUtil = new FileUtil();
        this.deviceInfoUtil = new DeviceInfoUtil();
    }

    Uri getContentUriWithAppendedId(String str) {
        return ContentUris.withAppendedId(Uri.parse(DOWNLOAD_CONTENT_URI), Long.valueOf(str).longValue());
    }

    String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        String[] strArr2 = {"_data", "_display_name"};
        Cursor query = context.getContentResolver().query(uri, strArr2, str, strArr, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex(strArr2[0]);
                    if (columnIndex > -1) {
                        String string = query.getString(columnIndex);
                        query.close();
                        return string;
                    }
                    String writeURIToFile = writeURIToFile(context, uri, query.getString(query.getColumnIndexOrThrow(strArr2[1])));
                    query.close();
                    return writeURIToFile;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
            return null;
        }
        return null;
    }

    String getDocumentId(Uri uri) {
        return DocumentsContract.getDocumentId(uri);
    }

    String getExternalStoragePath(String str) {
        return Environment.getExternalStorageDirectory() + "/" + str;
    }

    public String getRealPathFromCopy(Uri uri, Context context) throws IOException {
        InputStream openInputStream = context.getContentResolver().openInputStream(uri);
        try {
            File createFile = this.fileUtil.createFile(context.getFilesDir(), this.fileUtil.getFileNameFromUri(uri, context));
            this.fileUtil.writeWithFileOutputStream(createFile, openInputStream);
            String absolutePath = createFile.getAbsolutePath();
            if (openInputStream != null) {
                openInputStream.close();
            }
            return absolutePath;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (openInputStream != null) {
                    try {
                        openInputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public String getRealPathFromURI(@NonNull Context context, @Nullable Uri uri) {
        Uri uri2;
        if (uri == null) {
            return null;
        }
        if (isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String documentId = getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId) && documentId.matches(DOC_ID_PATTERN)) {
                    String[] split = documentId.split(":");
                    if ("primary".equalsIgnoreCase(split[0])) {
                        return getExternalStoragePath(split[1]);
                    }
                    int indexOf = documentId.indexOf(58, 1);
                    String substring = documentId.substring(0, indexOf);
                    String substring2 = documentId.substring(indexOf + 1);
                    String pathToNonPrimaryVolume = this.fileUtil.getPathToNonPrimaryVolume(context, substring);
                    if (pathToNonPrimaryVolume != null) {
                        String outline75 = GeneratedOutlineSupport1.outline75(pathToNonPrimaryVolume, "/", substring2);
                        File createFile = this.fileUtil.createFile(outline75);
                        if (createFile.exists() && createFile.canRead()) {
                            return outline75;
                        }
                    }
                }
                return null;
            } else if (isDownloadsDocument(uri)) {
                String documentId2 = getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId2)) {
                    if (documentId2.startsWith(DOWNLOAD_DOC_ID_PREFIX)) {
                        return documentId2.replaceFirst(DOWNLOAD_DOC_ID_PREFIX, "");
                    }
                    try {
                        return getDataColumn(context, getContentUriWithAppendedId(documentId2), null, null);
                    } catch (NumberFormatException unused) {
                    }
                }
                return null;
            } else if (isMediaDocument(uri)) {
                String documentId3 = getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId3) && documentId3.matches(DOC_ID_PATTERN)) {
                    String[] split2 = documentId3.split(":");
                    String str = split2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
                }
                return null;
            } else if (isGoogleDocsUri(uri)) {
                return getDataColumn(context, uri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.toString();
        }
        return null;
    }

    boolean isDocumentUri(@NonNull Context context, @NonNull Uri uri) {
        return this.deviceInfoUtil.isKitKatAndAbove() && DocumentsContract.isDocumentUri(context, uri);
    }

    boolean isDownloadsDocument(@NonNull Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    boolean isExternalStorageDocument(@NonNull Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    boolean isGoogleDocsUri(@NonNull Uri uri) {
        return "com.google.android.apps.docs.storage".equals(uri.getAuthority());
    }

    boolean isGooglePhotosUri(@NonNull Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    boolean isMediaDocument(@NonNull Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    String writeURIToFile(Context context, Uri uri, String str) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            String absolutePath = this.fileUtil.writeToFile(context, "react-native-image-crop-picker", str, openInputStream).getAbsolutePath();
            if (openInputStream != null) {
                openInputStream.close();
            }
            return absolutePath;
        } catch (IOException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("UriUtil: Could not open input stream from uri: " + uri, e);
            return null;
        }
    }

    public UriUtil(FileUtil fileUtil, DeviceInfoUtil deviceInfoUtil) {
        this.fileUtil = fileUtil;
        this.deviceInfoUtil = deviceInfoUtil;
    }
}
