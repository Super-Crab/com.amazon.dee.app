package com.reactnativecommunity.cameraroll;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.appmanager.lib.DefaultPreloadManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
@ReactModule(name = CameraRollModule.NAME)
/* loaded from: classes3.dex */
public class CameraRollModule extends ReactContextBaseJavaModule {
    private static final String ASSET_TYPE_ALL = "All";
    private static final String ASSET_TYPE_PHOTOS = "Photos";
    private static final String ASSET_TYPE_VIDEOS = "Videos";
    private static final String ERROR_UNABLE_TO_DELETE = "E_UNABLE_TO_DELETE";
    private static final String ERROR_UNABLE_TO_FILTER = "E_UNABLE_TO_FILTER";
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    private static final String INCLUDE_FILENAME = "filename";
    private static final String INCLUDE_FILE_SIZE = "fileSize";
    private static final String INCLUDE_IMAGE_SIZE = "imageSize";
    private static final String INCLUDE_LOCATION = "location";
    private static final String INCLUDE_PLAYABLE_DURATION = "playableDuration";
    public static final String NAME = "RNCCameraRoll";
    private static final String[] PROJECTION = {"_id", "mime_type", "bucket_display_name", "datetaken", "date_added", "date_modified", "width", "height", "_size", "_data"};
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";
    private static final String SELECTION_DATE_TAKEN = "datetaken < ?";

    /* loaded from: classes3.dex */
    private static class DeletePhotos extends GuardedAsyncTask<Void, Void> {
        private final Context mContext;
        private final Promise mPromise;
        private final ReadableArray mUris;

        public DeletePhotos(ReactContext reactContext, ReadableArray readableArray, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mUris = readableArray;
            this.mPromise = promise;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void... voidArr) {
            ContentResolver contentResolver = this.mContext.getContentResolver();
            String[] strArr = {"_id"};
            String str = WebConstants.UriConstants.QUESTIONMARK_KEY;
            for (int i = 1; i < this.mUris.size(); i++) {
                str = GeneratedOutlineSupport1.outline72(str, ", ?");
            }
            String outline75 = GeneratedOutlineSupport1.outline75("_data IN (", str, ")");
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] strArr2 = new String[this.mUris.size()];
            int i2 = 0;
            for (int i3 = 0; i3 < this.mUris.size(); i3++) {
                strArr2[i3] = Uri.parse(this.mUris.getString(i3)).getPath();
            }
            Cursor query = contentResolver.query(uri, strArr, outline75, strArr2, null);
            while (query.moveToNext()) {
                if (contentResolver.delete(ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, query.getLong(query.getColumnIndexOrThrow("_id"))), null, null) == 1) {
                    i2++;
                }
            }
            query.close();
            if (i2 == this.mUris.size()) {
                this.mPromise.resolve(true);
                return;
            }
            this.mPromise.reject(CameraRollModule.ERROR_UNABLE_TO_DELETE, "Could not delete all media, only deleted " + i2 + " photos.");
        }
    }

    /* loaded from: classes3.dex */
    private static class GetMediaTask extends GuardedAsyncTask<Void, Void> {
        @Nullable
        private final String mAfter;
        private final String mAssetType;
        private final Context mContext;
        private final int mFirst;
        private final long mFromTime;
        @Nullable
        private final String mGroupName;
        private final Set<String> mInclude;
        @Nullable
        private final ReadableArray mMimeTypes;
        private final Promise mPromise;
        private final long mToTime;

        private static Set<String> createSetFromIncludeArray(@Nullable ReadableArray readableArray) {
            HashSet hashSet = new HashSet();
            if (readableArray == null) {
                return hashSet;
            }
            for (int i = 0; i < readableArray.size(); i++) {
                String string = readableArray.getString(i);
                if (string != null) {
                    hashSet.add(string);
                }
            }
            return hashSet;
        }

        private GetMediaTask(ReactContext reactContext, int i, @Nullable String str, @Nullable String str2, @Nullable ReadableArray readableArray, String str3, long j, long j2, @Nullable ReadableArray readableArray2, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mFirst = i;
            this.mAfter = str;
            this.mGroupName = str2;
            this.mMimeTypes = readableArray;
            this.mPromise = promise;
            this.mAssetType = str3;
            this.mFromTime = j;
            this.mToTime = j2;
            this.mInclude = createSetFromIncludeArray(readableArray2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void... voidArr) {
            StringBuilder sb = new StringBuilder("1");
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.mGroupName)) {
                sb.append(" AND bucket_display_name = ?");
                arrayList.add(this.mGroupName);
            }
            if (this.mAssetType.equals(CameraRollModule.ASSET_TYPE_PHOTOS)) {
                sb.append(" AND media_type = 1");
            } else if (this.mAssetType.equals(CameraRollModule.ASSET_TYPE_VIDEOS)) {
                sb.append(" AND media_type = 3");
            } else if (this.mAssetType.equals(CameraRollModule.ASSET_TYPE_ALL)) {
                sb.append(" AND media_type IN (3,1)");
            } else {
                Promise promise = this.mPromise;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid filter option: '");
                GeneratedOutlineSupport1.outline181(outline107, this.mAssetType, "'. Expected one of '", CameraRollModule.ASSET_TYPE_PHOTOS, "', '");
                promise.reject(CameraRollModule.ERROR_UNABLE_TO_FILTER, GeneratedOutlineSupport1.outline93(outline107, CameraRollModule.ASSET_TYPE_VIDEOS, "' or '", CameraRollModule.ASSET_TYPE_ALL, "'."));
                return;
            }
            ReadableArray readableArray = this.mMimeTypes;
            int i = 0;
            if (readableArray != null && readableArray.size() > 0) {
                sb.append(" AND mime_type IN (");
                for (int i2 = 0; i2 < this.mMimeTypes.size(); i2++) {
                    sb.append("?,");
                    arrayList.add(this.mMimeTypes.getString(i2));
                }
                sb.replace(sb.length() - 1, sb.length(), ")");
            }
            if (this.mFromTime > 0) {
                sb.append(" AND datetaken > ?");
                arrayList.add(this.mFromTime + "");
            }
            if (this.mToTime > 0) {
                sb.append(" AND datetaken <= ?");
                arrayList.add(this.mToTime + "");
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            ContentResolver contentResolver = this.mContext.getContentResolver();
            try {
                String str = "limit=" + (this.mFirst + 1);
                if (!TextUtils.isEmpty(this.mAfter)) {
                    str = "limit=" + this.mAfter + "," + (this.mFirst + 1);
                }
                Cursor query = contentResolver.query(MediaStore.Files.getContentUri("external").buildUpon().encodedQuery(str).build(), CameraRollModule.PROJECTION, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), "date_added DESC, date_modified DESC");
                if (query != null) {
                    CameraRollModule.putEdges(contentResolver, query, writableNativeMap, this.mFirst, this.mInclude);
                    int i3 = this.mFirst;
                    if (!TextUtils.isEmpty(this.mAfter)) {
                        i = Integer.parseInt(this.mAfter);
                    }
                    CameraRollModule.putPageInfo(query, writableNativeMap, i3, i);
                    query.close();
                    this.mPromise.resolve(writableNativeMap);
                    return;
                }
                this.mPromise.reject(CameraRollModule.ERROR_UNABLE_TO_LOAD, "Could not get media");
            } catch (SecurityException e) {
                this.mPromise.reject(CameraRollModule.ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get media: need READ_EXTERNAL_STORAGE permission", e);
            }
        }
    }

    /* loaded from: classes3.dex */
    private static class SaveToCameraRoll extends GuardedAsyncTask<Void, Void> {
        private final Context mContext;
        private final ReadableMap mOptions;
        private final Promise mPromise;
        private final Uri mUri;

        public SaveToCameraRoll(ReactContext reactContext, Uri uri, ReadableMap readableMap, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mUri = uri;
            this.mPromise = promise;
            this.mOptions = readableMap;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0159  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x016a  */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void doInBackgroundGuarded(java.lang.Void... r21) {
            /*
                Method dump skipped, instructions count: 378
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.cameraroll.CameraRollModule.SaveToCameraRoll.doInBackgroundGuarded(java.lang.Void[]):void");
        }
    }

    public CameraRollModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private static void putBasicNodeInfo(Cursor cursor, WritableMap writableMap, int i, int i2, int i3, int i4, int i5) {
        writableMap.putString("type", cursor.getString(i));
        writableMap.putString("group_name", cursor.getString(i2));
        long j = cursor.getLong(i3);
        if (j == 0) {
            j = cursor.getLong(i4) * 1000;
        }
        writableMap.putDouble("timestamp", j / 1000.0d);
        writableMap.putDouble("modified", cursor.getLong(i5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putEdges(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i, Set<String> set) {
        Cursor cursor2;
        int i2;
        WritableNativeArray writableNativeArray;
        WritableNativeArray writableNativeArray2 = new WritableNativeArray();
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex("mime_type");
        int columnIndex2 = cursor.getColumnIndex("bucket_display_name");
        int columnIndex3 = cursor.getColumnIndex("datetaken");
        int columnIndex4 = cursor.getColumnIndex("date_added");
        int columnIndex5 = cursor.getColumnIndex("date_modified");
        int columnIndex6 = cursor.getColumnIndex("width");
        int columnIndex7 = cursor.getColumnIndex("height");
        int columnIndex8 = cursor.getColumnIndex("_size");
        int columnIndex9 = cursor.getColumnIndex("_data");
        boolean contains = set.contains("location");
        boolean contains2 = set.contains("filename");
        boolean contains3 = set.contains(INCLUDE_FILE_SIZE);
        boolean contains4 = set.contains(INCLUDE_IMAGE_SIZE);
        boolean contains5 = set.contains(INCLUDE_PLAYABLE_DURATION);
        int i3 = i;
        int i4 = 0;
        while (i4 < i3 && !cursor.isAfterLast()) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            WritableNativeArray writableNativeArray3 = writableNativeArray2;
            int i5 = i4;
            boolean z = contains;
            int i6 = columnIndex9;
            if (putImageInfo(contentResolver, cursor, writableNativeMap2, columnIndex6, columnIndex7, columnIndex8, columnIndex9, columnIndex, contains2, contains3, contains4, contains5)) {
                cursor2 = cursor;
                putBasicNodeInfo(cursor2, writableNativeMap2, columnIndex, columnIndex2, columnIndex3, columnIndex4, columnIndex5);
                i2 = i6;
                putLocationInfo(cursor2, writableNativeMap2, i2, z);
                writableNativeMap.putMap("node", writableNativeMap2);
                writableNativeArray = writableNativeArray3;
                writableNativeArray.pushMap(writableNativeMap);
            } else {
                cursor2 = cursor;
                i2 = i6;
                writableNativeArray = writableNativeArray3;
                i5--;
            }
            cursor.moveToNext();
            i4 = i5 + 1;
            i3 = i;
            columnIndex9 = i2;
            contains = z;
            writableNativeArray2 = writableNativeArray;
        }
        writableMap.putArray("edges", writableNativeArray2);
    }

    private static boolean putImageInfo(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, boolean z4) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("file://");
        outline107.append(cursor.getString(i4));
        Uri parse = Uri.parse(outline107.toString());
        writableNativeMap.putString("uri", parse.toString());
        String string = cursor.getString(i5);
        boolean z5 = string != null && string.startsWith("video");
        boolean putImageSize = putImageSize(contentResolver, cursor, writableNativeMap, i, i2, parse, z5, z3);
        boolean putPlayableDuration = putPlayableDuration(contentResolver, writableNativeMap, parse, z5, z4);
        if (z) {
            writableNativeMap.putString("filename", new File(cursor.getString(i4)).getName());
        } else {
            writableNativeMap.putNull("filename");
        }
        if (z2) {
            writableNativeMap.putDouble(INCLUDE_FILE_SIZE, cursor.getLong(i3));
        } else {
            writableNativeMap.putNull(INCLUDE_FILE_SIZE);
        }
        writableMap.putMap("image", writableNativeMap);
        return putImageSize && putPlayableDuration;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00fd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean putImageSize(android.content.ContentResolver r17, android.database.Cursor r18, com.facebook.react.bridge.WritableMap r19, int r20, int r21, android.net.Uri r22, boolean r23, boolean r24) {
        /*
            Method dump skipped, instructions count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.cameraroll.CameraRollModule.putImageSize(android.content.ContentResolver, android.database.Cursor, com.facebook.react.bridge.WritableMap, int, int, android.net.Uri, boolean, boolean):boolean");
    }

    private static void putLocationInfo(Cursor cursor, WritableMap writableMap, int i, boolean z) {
        writableMap.putNull("location");
        if (!z) {
            return;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(cursor.getString(i));
            float[] fArr = new float[2];
            if (!exifInterface.getLatLong(fArr)) {
                return;
            }
            double d = fArr[0];
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble("longitude", fArr[1]);
            writableNativeMap.putDouble("latitude", d);
            writableMap.putMap("location", writableNativeMap);
        } catch (IOException e) {
            FLog.e(ReactConstants.TAG, "Could not read the metadata", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putPageInfo(Cursor cursor, WritableMap writableMap, int i, int i2) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("has_next_page", i < cursor.getCount());
        if (i < cursor.getCount()) {
            writableNativeMap.putString("end_cursor", Integer.toString(i2 + i));
        }
        writableMap.putMap("page_info", writableNativeMap);
    }

    private static boolean putPlayableDuration(ContentResolver contentResolver, WritableMap writableMap, Uri uri, boolean z, boolean z2) {
        AssetFileDescriptor assetFileDescriptor;
        writableMap.putNull(INCLUDE_PLAYABLE_DURATION);
        boolean z3 = true;
        if (z2 && z) {
            boolean z4 = false;
            Integer num = null;
            try {
                assetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
            } catch (FileNotFoundException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not open asset file ");
                outline107.append(uri.toString());
                FLog.e(ReactConstants.TAG, outline107.toString(), e);
                z3 = false;
                assetFileDescriptor = null;
            }
            if (assetFileDescriptor != null) {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor());
                } catch (RuntimeException unused) {
                }
                try {
                    num = Integer.valueOf(Integer.parseInt(mediaMetadataRetriever.extractMetadata(9)) / 1000);
                    z4 = z3;
                } catch (NumberFormatException e2) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Number format exception occurred while trying to fetch video metadata for ");
                    outline1072.append(uri.toString());
                    FLog.e(ReactConstants.TAG, outline1072.toString(), e2);
                }
                mediaMetadataRetriever.release();
                z3 = z4;
            }
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException unused2) {
                }
            }
            if (num != null) {
                writableMap.putInt(INCLUDE_PLAYABLE_DURATION, num.intValue());
            }
        }
        return z3;
    }

    @ReactMethod
    public void deletePhotos(ReadableArray readableArray, Promise promise) {
        if (readableArray.size() == 0) {
            promise.reject(ERROR_UNABLE_TO_DELETE, "Need at least one URI to delete");
        } else {
            new DeletePhotos(getReactApplicationContext(), readableArray, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    @ReactMethod
    public void getAlbums(ReadableMap readableMap, Promise promise) {
        String string = readableMap.hasKey("assetType") ? readableMap.getString("assetType") : ASSET_TYPE_ALL;
        StringBuilder sb = new StringBuilder("1");
        ArrayList arrayList = new ArrayList();
        if (string.equals(ASSET_TYPE_PHOTOS)) {
            sb.append(" AND media_type = 1");
        } else if (string.equals(ASSET_TYPE_VIDEOS)) {
            sb.append(" AND media_type = 3");
        } else if (string.equals(ASSET_TYPE_ALL)) {
            sb.append(" AND media_type IN (3,1)");
        } else {
            promise.reject(ERROR_UNABLE_TO_FILTER, GeneratedOutlineSupport1.outline93(GeneratedOutlineSupport1.outline116("Invalid filter option: '", string, "'. Expected one of '", ASSET_TYPE_PHOTOS, "', '"), ASSET_TYPE_VIDEOS, "' or '", ASSET_TYPE_ALL, "'."));
            return;
        }
        try {
            Cursor query = getReactApplicationContext().getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"bucket_display_name"}, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), null);
            if (query == null) {
                promise.reject(ERROR_UNABLE_TO_LOAD, "Could not get media");
                return;
            }
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            if (query.moveToFirst()) {
                HashMap hashMap = new HashMap();
                do {
                    String string2 = query.getString(query.getColumnIndex("bucket_display_name"));
                    if (string2 != null) {
                        Integer num = (Integer) hashMap.get(string2);
                        if (num == null) {
                            hashMap.put(string2, 1);
                        } else {
                            hashMap.put(string2, Integer.valueOf(num.intValue() + 1));
                        }
                    }
                } while (query.moveToNext());
                for (Map.Entry entry : hashMap.entrySet()) {
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putString("title", (String) entry.getKey());
                    writableNativeMap.putInt("count", ((Integer) entry.getValue()).intValue());
                    writableNativeArray.pushMap(writableNativeMap);
                }
            }
            query.close();
            promise.resolve(writableNativeArray);
        } catch (Exception e) {
            promise.reject(ERROR_UNABLE_TO_LOAD, "Could not get media", e);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void getPhotos(ReadableMap readableMap, Promise promise) {
        new GetMediaTask(getReactApplicationContext(), readableMap.getInt(DefaultPreloadManager.METRIC_PATH_FIRST), readableMap.hasKey(TtmlNode.ANNOTATION_POSITION_AFTER) ? readableMap.getString(TtmlNode.ANNOTATION_POSITION_AFTER) : null, readableMap.hasKey(GroupNotificationHelper.PARSER_GROUP_NAME_KEY) ? readableMap.getString(GroupNotificationHelper.PARSER_GROUP_NAME_KEY) : null, readableMap.hasKey("mimeTypes") ? readableMap.getArray("mimeTypes") : null, readableMap.hasKey("assetType") ? readableMap.getString("assetType") : ASSET_TYPE_PHOTOS, readableMap.hasKey("fromTime") ? (long) readableMap.getDouble("fromTime") : 0L, readableMap.hasKey("toTime") ? (long) readableMap.getDouble("toTime") : 0L, readableMap.hasKey("include") ? readableMap.getArray("include") : null, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void saveToCameraRoll(String str, ReadableMap readableMap, Promise promise) {
        new SaveToCameraRoll(getReactApplicationContext(), Uri.parse(str), readableMap, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
