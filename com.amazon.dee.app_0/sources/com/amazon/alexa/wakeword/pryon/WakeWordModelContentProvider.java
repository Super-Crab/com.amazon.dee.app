package com.amazon.alexa.wakeword.pryon;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.alexa.wakeword.davs.ArtifactData;
import com.amazon.alexa.wakeword.davs.ArtifactNameFactory;
import com.amazon.alexa.wakeword.davs.SharedPrefWakeWordModelArtifactDataPersister;
import com.amazon.alexa.wakeword.davs.WakeWordModelArtifactDataPersister;
import com.amazon.alexa.wakeword.davs.WakeWordModelArtifactInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
/* loaded from: classes11.dex */
public class WakeWordModelContentProvider extends ContentProvider {
    @VisibleForTesting
    static final String BACKUP_MODEL_LOCALE = "en-US";
    private static final String BACKUP_MODEL_ROOT = "backup_ww_model";
    private static final String BACKUP_MODEL_SHARED_PREF_NAME = "backup_model_store";
    private static final String EMPTY_STRING = "";
    public static final String KEY_ARTIFACT_DATA = "artifact_data";
    public static final String KEY_ARTIFACT_DOWNLOADED_TIME = "artifact_downloaded_time";
    public static final String KEY_ARTIFACT_IDENTIFIER = "artifact_identifier";
    public static final String KEY_BACKUP_MODEL_ASSET = "backup_model_asset";
    public static final String KEY_BACKUP_MODEL_ECID = "backup_model_ecid";
    public static final String KEY_BACKUP_MODEL_FILENAME = "backup_model_filename";
    public static final String KEY_BACKUP_MODEL_SOURCE = "backup_model_source";
    public static final String KEY_LAST_USED_ENGINE_COMPAT_ID = "engine_compatibility_id";
    public static final String KEY_LAST_USED_LOCALE = "last_used_locale";
    public static final String KEY_LAST_USED_WAKE_WORDS = "wake_words";
    private static final String READ_MODE = "r";
    private static final String SDK_ASSET_BACKUP_MODEL = "X_OTG.en-US.alexa.bin";
    private static final String TAG = WakeWordModelContentProvider.class.getName();
    public static final String WAKE_WORD_MODEL_DIRECTORY = "wake_word_model";
    private static final String WAKE_WORD_SHARED_PREF_NAME = "wakeword_store";
    private WakeWordModelArtifactDataPersister artifactDataPersister;
    private Provider<SharedPreferences> backupModelSharedPreferencesProvider;
    private Context context;
    private Provider<SharedPreferences> sharedPreferencesProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public enum BackupModelSource {
        NONE,
        CLIENT,
        SDK
    }

    private File fetchBackupModelFromAssets(String str, BackupModelSource backupModelSource) {
        String str2 = "setting asset " + str + " as a backup model";
        try {
            InputStream inputStreamFromAsset = getInputStreamFromAsset(str);
            File destFile = getDestFile(str);
            boolean backupModel = setBackupModel(inputStreamFromAsset, destFile);
            if (backupModel) {
                setBackupModelFilename(destFile.getName());
                setBackupModelSource(backupModelSource);
                setBackupModelEcid(WakeWordModelArtifactInfo.Companion.getEngineCompatibilityId());
                String str3 = "backup model name=" + getBackupModelFilename() + ", source=" + getBackupModelSource().name() + ", ecid=" + getBackupModelEcid();
            }
            if (!backupModel) {
                destFile = null;
            }
            if (inputStreamFromAsset != null) {
                inputStreamFromAsset.close();
            }
            return destFile;
        } catch (IOException e) {
            String str4 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot find asset ");
            outline107.append(e.getMessage());
            Log.e(str4, outline107.toString());
            return null;
        }
    }

    private File getBackupModel() {
        String backupModelFilename = getBackupModelFilename();
        if (backupModelFilename != null) {
            File destFile = getDestFile(backupModelFilename);
            if (destFile.exists()) {
                if (isBackupModelCompatible()) {
                    return destFile;
                }
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("backup file (");
                outline107.append(getBackupModelEcid());
                outline107.append(") is not compatible with ecid ");
                outline107.append(WakeWordModelArtifactInfo.Companion.getEngineCompatibilityId());
                Log.w(str, outline107.toString());
            } else {
                String str2 = TAG;
                Log.e(str2, "backup file " + backupModelFilename + " does not exist!");
            }
        } else {
            Log.w(TAG, "no backup model is set");
        }
        Log.w(TAG, "setting backup file from SDK assets");
        return fetchBackupModelFromAssets(SDK_ASSET_BACKUP_MODEL, BackupModelSource.SDK);
    }

    private String getBackupModelEcid() {
        return this.backupModelSharedPreferencesProvider.mo2864get().getString(KEY_BACKUP_MODEL_ECID, "");
    }

    private String getBackupModelFilename() {
        return this.backupModelSharedPreferencesProvider.mo2864get().getString(KEY_BACKUP_MODEL_FILENAME, null);
    }

    private File getBackupModelRootFolder() {
        File file = new File(this.context.getFilesDir(), BACKUP_MODEL_ROOT);
        if (!file.exists()) {
            try {
                if (!file.mkdirs()) {
                    Log.e(TAG, "failed to create folder backup_ww_model");
                }
            } catch (SecurityException e) {
                Log.e(TAG, "failed to create folder backup_ww_model", e);
            }
        }
        return file;
    }

    private BackupModelSource getBackupModelSource() {
        String string = this.backupModelSharedPreferencesProvider.mo2864get().getString(KEY_BACKUP_MODEL_SOURCE, BackupModelSource.NONE.name());
        try {
            return BackupModelSource.valueOf(string);
        } catch (IllegalArgumentException unused) {
            String str = TAG;
            Log.e(str, "unknown backup model source \"" + string + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            return BackupModelSource.NONE;
        }
    }

    private Uri getBaseUri() {
        return new Uri.Builder().scheme("content").authority(getContentAuthority()).build();
    }

    private String getContentAuthority() {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), getPackageName(), ".pryon.wakeword.model");
    }

    private String getLastUsedModelFile() {
        return ArtifactNameFactory.getArtifactFilename(new WakeWordModelArtifactInfo(readLastUsedLocale(), isCurrentDeviceHandsFree()), readExistingModelArtifactId());
    }

    private Provider<SharedPreferences> getWakeWordSharedPreference() {
        return new Provider<SharedPreferences>() { // from class: com.amazon.alexa.wakeword.pryon.WakeWordModelContentProvider.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.utils.Provider
            /* renamed from: get */
            public SharedPreferences mo2864get() {
                return WakeWordModelContentProvider.this.context.getSharedPreferences(WakeWordModelContentProvider.WAKE_WORD_SHARED_PREF_NAME, 0);
            }
        };
    }

    private boolean insertArtifactData(ContentValues contentValues) {
        String asString = contentValues.getAsString(KEY_ARTIFACT_IDENTIFIER);
        String asString2 = contentValues.getAsString(KEY_LAST_USED_LOCALE);
        String asString3 = contentValues.getAsString(KEY_LAST_USED_ENGINE_COMPAT_ID);
        String asString4 = contentValues.getAsString(KEY_LAST_USED_WAKE_WORDS);
        Long asLong = contentValues.getAsLong(KEY_ARTIFACT_DOWNLOADED_TIME);
        if (asString != null && asString2 != null && asString3 != null && asString4 != null && asLong != null) {
            this.artifactDataPersister.setArtifactData(new ArtifactData(asString, asString2, asString3, asString4, asLong.longValue()));
            notifyChange(KEY_ARTIFACT_IDENTIFIER);
            notifyChange(KEY_LAST_USED_LOCALE);
            notifyChange(KEY_LAST_USED_ENGINE_COMPAT_ID);
            notifyChange(KEY_LAST_USED_WAKE_WORDS);
            notifyChange(KEY_ARTIFACT_DOWNLOADED_TIME);
            notifyChange(KEY_ARTIFACT_DATA);
            return true;
        }
        Log.e(TAG, "incomplete artifact data in values");
        return false;
    }

    private boolean insertArtifactDownloadTime(Long l) {
        if (l == null) {
            Log.e(TAG, "no download time found in values");
            return false;
        }
        this.artifactDataPersister.setArtifactDownloadTime(l.longValue());
        notifyChange(KEY_ARTIFACT_DOWNLOADED_TIME);
        notifyChange(KEY_ARTIFACT_DATA);
        return true;
    }

    private boolean insertArtifactEcid(String str) {
        if (str == null) {
            Log.e(TAG, "no ecid found in values");
            return false;
        }
        this.artifactDataPersister.setArtifactEcid(str);
        notifyChange(KEY_LAST_USED_ENGINE_COMPAT_ID);
        notifyChange(KEY_ARTIFACT_DATA);
        return true;
    }

    private boolean insertArtifactId(String str) {
        if (str == null) {
            Log.e(TAG, "no id found in values");
            return false;
        }
        this.artifactDataPersister.setArtifactId(str);
        notifyChange(KEY_ARTIFACT_IDENTIFIER);
        notifyChange(KEY_ARTIFACT_DATA);
        return true;
    }

    private boolean insertArtifactLocale(String str) {
        if (str == null) {
            Log.e(TAG, "no locale found in values");
            return false;
        }
        this.artifactDataPersister.setArtifactLocale(str);
        notifyChange(KEY_LAST_USED_LOCALE);
        notifyChange(KEY_ARTIFACT_DATA);
        return true;
    }

    private boolean insertBackupModelFromAsset(String str) {
        if (str == null) {
            Log.e(TAG, "no asset filename found in values");
            return false;
        } else if (!getDestFile(str).getName().equals(getBackupModelFilename()) || BackupModelSource.CLIENT != getBackupModelSource() || !isFileExist(getDestFile(str)) || !isBackupModelCompatible()) {
            return fetchBackupModelFromAssets(str, BackupModelSource.CLIENT) != null;
        } else {
            String str2 = "asset " + str + " is already set as backup model";
            return false;
        }
    }

    private boolean insertWakeWordData(@NonNull String str, ContentValues contentValues) {
        if (contentValues == null) {
            Log.e(TAG, "no values found");
            return false;
        } else if (str.startsWith(KEY_ARTIFACT_DATA)) {
            return insertArtifactData(contentValues);
        } else {
            if (str.startsWith(KEY_ARTIFACT_IDENTIFIER)) {
                return insertArtifactId(contentValues.getAsString(KEY_ARTIFACT_IDENTIFIER));
            }
            if (str.startsWith(KEY_LAST_USED_LOCALE)) {
                return insertArtifactLocale(contentValues.getAsString(KEY_LAST_USED_LOCALE));
            }
            if (str.startsWith(KEY_LAST_USED_ENGINE_COMPAT_ID)) {
                return insertArtifactEcid(contentValues.getAsString(KEY_LAST_USED_ENGINE_COMPAT_ID));
            }
            if (str.startsWith(KEY_LAST_USED_WAKE_WORDS)) {
                return insertWakeWords(contentValues.getAsString(KEY_LAST_USED_WAKE_WORDS));
            }
            if (str.startsWith(KEY_ARTIFACT_DOWNLOADED_TIME)) {
                return insertArtifactDownloadTime(contentValues.getAsLong(KEY_ARTIFACT_DOWNLOADED_TIME));
            }
            if (str.startsWith(KEY_BACKUP_MODEL_FILENAME)) {
                return insertBackupModelFromAsset(contentValues.getAsString(KEY_BACKUP_MODEL_ASSET));
            }
            GeneratedOutlineSupport1.outline164("invalid endpoint for insert - ", str, TAG);
            return false;
        }
    }

    private boolean insertWakeWords(String str) {
        if (str == null) {
            Log.e(TAG, "no serialized wake words found in values");
            return false;
        }
        this.artifactDataPersister.setWakeWords(str);
        notifyChange(KEY_LAST_USED_WAKE_WORDS);
        notifyChange(KEY_ARTIFACT_DATA);
        return true;
    }

    private boolean isBackupModelCompatible() {
        return WakeWordModelArtifactInfo.Companion.getEngineCompatibilityId().equals(getBackupModelEcid());
    }

    private boolean isCurrentDeviceHandsFree() {
        return AlexaHandsFreeDeviceInformation.isCurrentDeviceHandsFree(this.context);
    }

    private boolean isExistingModelCompatible() {
        return WakeWordModelArtifactInfo.Companion.getEngineCompatibilityId().equals(readExistingModelEngineCompatibilityId());
    }

    private boolean isValidUri(@NonNull Uri uri) {
        return "content".equals(uri.getScheme()) && getContentAuthority().equals(uri.getAuthority()) && uri.getPath() != null;
    }

    private boolean isValidWakeWordUri(@NonNull Uri uri) {
        return isValidUri(uri) && uri.getLastPathSegment().startsWith(WAKE_WORD_MODEL_DIRECTORY);
    }

    private void notifyChange(String str) {
        Uri build = getBaseUri().buildUpon().appendPath(str).build();
        String str2 = "notifying " + build;
        getContentResolver().notifyChange(build, null);
    }

    private ParcelFileDescriptor openBackupModelFile() {
        try {
            Log.i(TAG, "Falling back to backup wake word model");
            File backupModel = getBackupModel();
            if (backupModel != null) {
                String str = "opening backup model at " + backupModel.getAbsolutePath();
                return ParcelFileDescriptor.open(backupModel, 268435456);
            }
            Log.e(TAG, "unable to retrieve backup model file");
            return null;
        } catch (FileNotFoundException e) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unable to retrieve backup model file ");
            outline107.append(e.getMessage());
            Log.e(str2, outline107.toString());
            return null;
        }
    }

    private ArtifactData readArtifactsData() {
        return this.artifactDataPersister.getArtifactData();
    }

    private String readExistingModelArtifactId() {
        return this.artifactDataPersister.getArtifactId();
    }

    private String readExistingModelEngineCompatibilityId() {
        return this.artifactDataPersister.getArtifactEcid();
    }

    private String readLastUsedLocale() {
        String artifactLocale = this.artifactDataPersister.getArtifactLocale();
        return artifactLocale.isEmpty() ? BACKUP_MODEL_LOCALE : artifactLocale;
    }

    private long readWakeWordModelDownloadTime() {
        return this.artifactDataPersister.getArtifactDownloadTime();
    }

    private String readWakeWords() {
        return this.artifactDataPersister.getWakeWords();
    }

    private boolean setBackupModel(InputStream inputStream, File file) {
        if (file.exists()) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("overwriting existing backup model! (");
            outline107.append(file.getAbsolutePath());
            outline107.append(")");
            Log.w(str, outline107.toString());
        } else {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("setting a backup model (");
            outline1072.append(file.getAbsolutePath());
            outline1072.append(")");
            outline1072.toString();
        }
        try {
            FileOutputStream fileOutputStream = getFileOutputStream(file);
            copy(inputStream, fileOutputStream);
            String str2 = "backup model was set successfully at " + file.getAbsolutePath();
            notifyChange(WAKE_WORD_MODEL_DIRECTORY);
            if (fileOutputStream == null) {
                return true;
            }
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Exception occured while saving backup file "), TAG);
            return false;
        }
    }

    private void setBackupModelEcid(String str) {
        this.backupModelSharedPreferencesProvider.mo2864get().edit().putString(KEY_BACKUP_MODEL_ECID, str).apply();
        notifyChange(KEY_BACKUP_MODEL_ECID);
    }

    private void setBackupModelFilename(String str) {
        this.backupModelSharedPreferencesProvider.mo2864get().edit().putString(KEY_BACKUP_MODEL_FILENAME, str).apply();
        notifyChange(KEY_BACKUP_MODEL_FILENAME);
    }

    private void setBackupModelSource(BackupModelSource backupModelSource) {
        this.backupModelSharedPreferencesProvider.mo2864get().edit().putString(KEY_BACKUP_MODEL_SOURCE, backupModelSource.name()).apply();
        notifyChange(KEY_BACKUP_MODEL_SOURCE);
    }

    @VisibleForTesting
    void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        IOUtils.copy(inputStream, outputStream);
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @VisibleForTesting
    WakeWordModelArtifactDataPersister getArtifactDataPersister() {
        return new SharedPrefWakeWordModelArtifactDataPersister(this.sharedPreferencesProvider.mo2864get());
    }

    @VisibleForTesting
    Provider<SharedPreferences> getBackupModelSharedPreference() {
        return new Provider<SharedPreferences>() { // from class: com.amazon.alexa.wakeword.pryon.WakeWordModelContentProvider.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.utils.Provider
            /* renamed from: get */
            public SharedPreferences mo2864get() {
                return WakeWordModelContentProvider.this.context.getSharedPreferences(WakeWordModelContentProvider.BACKUP_MODEL_SHARED_PREF_NAME, 0);
            }
        };
    }

    @VisibleForTesting
    ContentResolver getContentResolver() {
        return getContext().getContentResolver();
    }

    @VisibleForTesting
    File getDestFile(String str) {
        return new File(getBackupModelRootFolder(), new File(str).getName());
    }

    @VisibleForTesting
    FileOutputStream getFileOutputStream(File file) throws FileNotFoundException, SecurityException {
        return new FileOutputStream(file);
    }

    @VisibleForTesting
    InputStream getInputStreamFromAsset(String str) throws IOException {
        return this.context.getAssets().open(str);
    }

    @VisibleForTesting
    String getPackageName() {
        return this.context.getPackageName();
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if (isValidUri(uri) && uri.getLastPathSegment() != null) {
            if (!insertWakeWordData(uri.getLastPathSegment(), contentValues)) {
                return null;
            }
            return uri;
        }
        String str = TAG;
        Log.e(str, "insert: invalid request URI " + uri);
        return null;
    }

    @VisibleForTesting
    boolean isFileExist(File file) {
        return file.exists();
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.context = getContext();
        this.sharedPreferencesProvider = getWakeWordSharedPreference();
        this.backupModelSharedPreferencesProvider = getBackupModelSharedPreference();
        this.artifactDataPersister = getArtifactDataPersister();
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String str) throws FileNotFoundException {
        if (!READ_MODE.equals(str)) {
            GeneratedOutlineSupport1.outline162("File requested in unsupported mode: ", str, TAG);
            return null;
        } else if (!isValidWakeWordUri(uri)) {
            String str2 = TAG;
            Log.e(str2, "Found invalid request URI " + uri);
            return null;
        } else {
            String lastUsedModelFile = getLastUsedModelFile();
            File file = new File(this.context.getFilesDir(), lastUsedModelFile);
            if (!TextUtils.isEmpty(lastUsedModelFile) && file.exists() && isExistingModelCompatible()) {
                Log.i(TAG, "Loading last used model file");
                return ParcelFileDescriptor.open(file, 268435456);
            }
            return openBackupModelFile();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0054, code lost:
        if (r8.equals(com.amazon.alexa.wakeword.pryon.WakeWordModelContentProvider.KEY_ARTIFACT_DOWNLOADED_TIME) != false) goto L13;
     */
    @Override // android.content.ContentProvider
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.database.Cursor query(@androidx.annotation.NonNull android.net.Uri r8, @androidx.annotation.Nullable java.lang.String[] r9, @androidx.annotation.Nullable java.lang.String r10, @androidx.annotation.Nullable java.lang.String[] r11, @androidx.annotation.Nullable java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.wakeword.pryon.WakeWordModelContentProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }
}
