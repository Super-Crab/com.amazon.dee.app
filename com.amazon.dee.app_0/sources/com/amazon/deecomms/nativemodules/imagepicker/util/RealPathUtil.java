package com.amazon.deecomms.nativemodules.imagepicker.util;

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
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public final class RealPathUtil {
    public static final String AUTHORITY_DOWNLOADED_DOCUMENT = "com.android.providers.downloads.documents";
    public static final String AUTHORITY_EXTERNAL_DOCUMENT = "com.android.externalstorage.documents";
    public static final String AUTHORITY_GOOGLE_DOCUMENT = "com.google.android.apps.docs.storage";
    public static final String AUTHORITY_GOOGLE_PHOTOS = "com.google.android.apps.photos.content";
    public static final String AUTHORITY_MEDIA_DOCUMENT = "com.android.providers.media.documents";
    private static final String DOC_ID_PATTERN = ".+:.+";
    private static final String DOWNLOAD_CONTENT_URI = "content://downloads/public_downloads";
    private static final String DOWNLOAD_DOC_ID_PREFIX = "raw:";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, RealPathUtil.class);

    private RealPathUtil() {
    }

    private static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor query = context.getContentResolver().query(uri, new String[]{"_data", "_display_name"}, str, strArr, null);
        String str2 = null;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex("_data");
                    if (columnIndex > -1) {
                        str2 = query.getString(columnIndex);
                    }
                    if (str2 != null) {
                        String string = query.getString(columnIndex);
                        query.close();
                        return string;
                    }
                    String writeURIToFile = writeURIToFile(context, uri, query.getString(query.getColumnIndexOrThrow("_display_name")));
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
        }
        return null;
    }

    private static String getPathToNonPrimaryVolume(Context context, String str) {
        String absolutePath;
        int indexOf;
        File[] externalCacheDirs = context.getExternalCacheDirs();
        if (externalCacheDirs != null) {
            for (File file : externalCacheDirs) {
                if (file != null && (absolutePath = file.getAbsolutePath()) != null && (indexOf = absolutePath.indexOf(str)) != -1) {
                    return absolutePath.substring(0, indexOf) + str;
                }
            }
            return null;
        }
        return null;
    }

    public static String getRealPathFromURI(@NonNull Context context, @Nullable Uri uri) {
        Uri uri2;
        if (uri == null) {
            return null;
        }
        if (Utils.isKitKatAndAbove() && DocumentsContract.isDocumentUri(context, uri)) {
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId) && documentId.matches(DOC_ID_PATTERN)) {
                    String[] split = documentId.split(":");
                    if ("primary".equalsIgnoreCase(split[0])) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }
                    int indexOf = documentId.indexOf(58, 1);
                    String substring = documentId.substring(0, indexOf);
                    String substring2 = documentId.substring(indexOf + 1);
                    String pathToNonPrimaryVolume = getPathToNonPrimaryVolume(context, substring);
                    if (pathToNonPrimaryVolume != null) {
                        String outline75 = GeneratedOutlineSupport1.outline75(pathToNonPrimaryVolume, "/", substring2);
                        File file = new File(outline75);
                        if (file.exists() && file.canRead()) {
                            return outline75;
                        }
                    }
                }
                return null;
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                String documentId2 = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId2)) {
                    if (documentId2.startsWith(DOWNLOAD_DOC_ID_PREFIX)) {
                        return documentId2.replaceFirst(DOWNLOAD_DOC_ID_PREFIX, "");
                    }
                    try {
                        return getDataColumn(context, ContentUris.withAppendedId(Uri.parse(DOWNLOAD_CONTENT_URI), Long.valueOf(documentId2).longValue()), null, null);
                    } catch (NumberFormatException unused) {
                    }
                }
                return null;
            } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String documentId3 = DocumentsContract.getDocumentId(uri);
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
            } else if ("com.google.android.apps.docs.storage".equals(uri.getAuthority())) {
                return getDataColumn(context, uri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.toString();
        }
        return null;
    }

    private static boolean isDownloadsDocument(@NonNull Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isExternalStorageDocument(@NonNull Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isGoogleDocsUri(@NonNull Uri uri) {
        return "com.google.android.apps.docs.storage".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(@NonNull Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(@NonNull Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static File writeToFile(Context context, String str, String str2, InputStream inputStream) {
        String str3 = context.getCacheDir() + "/" + str;
        Boolean.valueOf(new File(str3).mkdir());
        File file = new File(new File(str3), str2.substring(str2.lastIndexOf(47) + 1));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr, 0, bArr.length);
                if (read <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    static String writeURIToFile(Context context, Uri uri, String str) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            String absolutePath = writeToFile(context, "react-native-image-crop-picker", str, openInputStream).getAbsolutePath();
            if (openInputStream != null) {
                openInputStream.close();
            }
            return absolutePath;
        } catch (IOException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("RealPathUtil: Could not open input stream from uri: " + uri, e);
            return null;
        }
    }
}
