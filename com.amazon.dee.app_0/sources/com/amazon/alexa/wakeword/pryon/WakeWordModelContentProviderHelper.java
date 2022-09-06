package com.amazon.alexa.wakeword.pryon;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.PackageNameProvider;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class WakeWordModelContentProviderHelper {
    static final String CONTENT_AUTHORITY_POSTFIX = ".pryon.wakeword.model";
    private static final String EMPTY_STRING = "";
    private static final String TAG = "WakeWordModelContentProviderHelper";
    private static final String WAKE_WORD_MODEL_DIRECTORY = "wake_word_model";
    private final String contentProviderAuthority;
    private final ContentResolver wakeWordModelContentResolver;
    private final List<WakeWordsChangedListener> wakeWordsChangedListeners = new ArrayList();
    private final LocalContentObserver observer = new LocalContentObserver(new Handler(Looper.getMainLooper()));

    /* loaded from: classes11.dex */
    protected class LocalContentObserver extends ContentObserver {
        public LocalContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            List<String> readWakeWords = WakeWordModelContentProviderHelper.this.readWakeWords();
            if (readWakeWords != null) {
                for (WakeWordsChangedListener wakeWordsChangedListener : WakeWordModelContentProviderHelper.this.wakeWordsChangedListeners) {
                    wakeWordsChangedListener.onWakeWordsChanged(readWakeWords);
                }
                return;
            }
            Log.e(WakeWordModelContentProviderHelper.TAG, "wake words changed to null!");
        }
    }

    /* loaded from: classes11.dex */
    public interface WakeWordsChangedListener {
        void onWakeWordsChanged(List<String> list);
    }

    public WakeWordModelContentProviderHelper(ContentResolver contentResolver, PackageNameProvider packageNameProvider) {
        this.wakeWordModelContentResolver = contentResolver;
        this.contentProviderAuthority = packageNameProvider.getPackageName() + CONTENT_AUTHORITY_POSTFIX;
    }

    private Cursor getCursorForUri(Uri uri) {
        return this.wakeWordModelContentResolver.query(uri, null, null, null, null);
    }

    private String getStringFromURI(Uri uri) {
        String str = "";
        try {
            Cursor cursorForUri = getCursorForUri(uri);
            if (cursorForUri != null && cursorForUri.moveToNext()) {
                str = cursorForUri.getString(cursorForUri.getColumnIndexOrThrow(uri.getLastPathSegment()));
            }
            if (cursorForUri != null) {
                cursorForUri.close();
            }
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "Exception occurred retrieving string from URI: " + uri, e);
        }
        return str;
    }

    private void insert(Uri uri, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, str2);
        this.wakeWordModelContentResolver.insert(uri, contentValues);
    }

    @VisibleForTesting
    Uri getArtifactDataUri() {
        return getBaseContentUri().buildUpon().appendPath(WakeWordModelContentProvider.KEY_ARTIFACT_DATA).build();
    }

    @VisibleForTesting
    Uri getArtifactDownloadedTimeUri() {
        return getBaseContentUri().buildUpon().appendPath(WakeWordModelContentProvider.KEY_ARTIFACT_DOWNLOADED_TIME).build();
    }

    @VisibleForTesting
    Uri getArtifactIdentifierUri() {
        return getBaseContentUri().buildUpon().appendPath(WakeWordModelContentProvider.KEY_ARTIFACT_IDENTIFIER).build();
    }

    @VisibleForTesting
    Uri getArtifactWakeWordsUri() {
        return getBaseContentUri().buildUpon().appendPath(WakeWordModelContentProvider.KEY_LAST_USED_WAKE_WORDS).build();
    }

    @VisibleForTesting
    Uri getBackupModelFilenameUri() {
        return getBaseContentUri().buildUpon().appendPath(WakeWordModelContentProvider.KEY_BACKUP_MODEL_FILENAME).build();
    }

    @VisibleForTesting
    Uri getBaseContentUri() {
        return new Uri.Builder().scheme("content").authority(this.contentProviderAuthority).build();
    }

    @VisibleForTesting
    Uri getLastUsedEngineCompatIdUri() {
        return getBaseContentUri().buildUpon().appendPath(WakeWordModelContentProvider.KEY_LAST_USED_ENGINE_COMPAT_ID).build();
    }

    @VisibleForTesting
    Uri getLastUsedLocaleUri() {
        return getBaseContentUri().buildUpon().appendPath(WakeWordModelContentProvider.KEY_LAST_USED_LOCALE).build();
    }

    @VisibleForTesting
    LocalContentObserver getObserver() {
        return this.observer;
    }

    @VisibleForTesting
    Uri getWakewordModelUri() {
        return getBaseContentUri().buildUpon().appendPath("wake_word_model").build();
    }

    public InputStream openWakeWordModelInputStream() throws IOException {
        return this.wakeWordModelContentResolver.openInputStream(getWakewordModelUri());
    }

    public ArtifactModel readExistingModelArtifactData() {
        try {
            Cursor cursorForUri = getCursorForUri(getArtifactDataUri());
            if (cursorForUri == null || !cursorForUri.moveToNext()) {
                if (cursorForUri == null) {
                    return null;
                }
                cursorForUri.close();
                return null;
            }
            String string = cursorForUri.getString(cursorForUri.getColumnIndexOrThrow(WakeWordModelContentProvider.KEY_LAST_USED_WAKE_WORDS));
            ArtifactModel build = ArtifactModel.builder().setArtifactIdentifier(cursorForUri.getString(cursorForUri.getColumnIndexOrThrow(WakeWordModelContentProvider.KEY_ARTIFACT_IDENTIFIER))).setLocale(cursorForUri.getString(cursorForUri.getColumnIndexOrThrow(WakeWordModelContentProvider.KEY_LAST_USED_LOCALE))).setEngineCompatibilityId(cursorForUri.getString(cursorForUri.getColumnIndexOrThrow(WakeWordModelContentProvider.KEY_LAST_USED_ENGINE_COMPAT_ID))).setWakeWords((List) new Gson().fromJson(string, new TypeToken<List<String>>() { // from class: com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper.1
            }.getType())).setArtifactDownloadedTime(Long.valueOf(cursorForUri.getLong(cursorForUri.getColumnIndexOrThrow(WakeWordModelContentProvider.KEY_ARTIFACT_DOWNLOADED_TIME)))).build();
            cursorForUri.close();
            return build;
        } catch (Exception e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception occurred retrieving data from URI: ");
            outline107.append(getArtifactDataUri());
            Log.e(str, outline107.toString(), e);
            return null;
        }
    }

    public String readExistingModelArtifactId() {
        return getStringFromURI(getArtifactIdentifierUri());
    }

    public String readExistingModelEngineCompatibilityId() {
        return getStringFromURI(getLastUsedEngineCompatIdUri());
    }

    public String readLastUsedLocale() {
        return getStringFromURI(getLastUsedLocaleUri());
    }

    public long readWakeWordModelDownloadTime() {
        long j = 0;
        try {
            Cursor cursorForUri = getCursorForUri(getArtifactDownloadedTimeUri());
            if (cursorForUri != null && cursorForUri.moveToNext()) {
                j = cursorForUri.getLong(cursorForUri.getColumnIndexOrThrow(WakeWordModelContentProvider.KEY_ARTIFACT_DOWNLOADED_TIME));
            }
            if (cursorForUri != null) {
                cursorForUri.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception occurred reading wake word model download time", e);
        }
        return j;
    }

    public List<String> readWakeWords() {
        String stringFromURI = getStringFromURI(getArtifactWakeWordsUri());
        try {
            return (List) new Gson().fromJson(stringFromURI, new TypeToken<List<String>>() { // from class: com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper.2
            }.getType());
        } catch (JsonSyntaxException unused) {
            GeneratedOutlineSupport1.outline162("invalid serialized wake words list: ", stringFromURI, TAG);
            return null;
        }
    }

    public void registerWakeWordsListener(WakeWordsChangedListener wakeWordsChangedListener) {
        if (this.wakeWordsChangedListeners.contains(wakeWordsChangedListener)) {
            Log.w(TAG, "listener already registered, ignoring");
            return;
        }
        this.wakeWordsChangedListeners.add(wakeWordsChangedListener);
        if (this.wakeWordsChangedListeners.size() != 1) {
            return;
        }
        this.wakeWordModelContentResolver.registerContentObserver(getArtifactWakeWordsUri(), true, this.observer);
    }

    public void setArtifactData(ArtifactModel artifactModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WakeWordModelContentProvider.KEY_ARTIFACT_IDENTIFIER, artifactModel.getArtifactIdentifier());
        contentValues.put(WakeWordModelContentProvider.KEY_LAST_USED_LOCALE, artifactModel.getLocale());
        contentValues.put(WakeWordModelContentProvider.KEY_LAST_USED_ENGINE_COMPAT_ID, artifactModel.getEngineCompatibilityId());
        contentValues.put(WakeWordModelContentProvider.KEY_ARTIFACT_DOWNLOADED_TIME, artifactModel.getArtifactDownloadedTime());
        contentValues.put(WakeWordModelContentProvider.KEY_LAST_USED_WAKE_WORDS, new Gson().toJson(artifactModel.getWakeWords()));
        insert(getArtifactDataUri(), contentValues);
    }

    public void setArtifactDownloadTime(long j) {
        insert(getArtifactDownloadedTimeUri(), WakeWordModelContentProvider.KEY_ARTIFACT_DOWNLOADED_TIME, j);
    }

    public void setArtifactEcid(@NonNull String str) {
        Preconditions.notNull(str, "ecid cannot be null");
        insert(getLastUsedEngineCompatIdUri(), WakeWordModelContentProvider.KEY_LAST_USED_ENGINE_COMPAT_ID, str);
    }

    public void setArtifactId(@NonNull String str) {
        Preconditions.notNull(str, "id cannot be null");
        insert(getArtifactIdentifierUri(), WakeWordModelContentProvider.KEY_ARTIFACT_IDENTIFIER, str);
    }

    public void setArtifactLocale(@NonNull String str) {
        Preconditions.notNull(str, "locale cannot be null");
        insert(getLastUsedLocaleUri(), WakeWordModelContentProvider.KEY_LAST_USED_LOCALE, str);
    }

    public void setArtifactWakeWords(List<String> list) {
        insert(getArtifactWakeWordsUri(), WakeWordModelContentProvider.KEY_LAST_USED_WAKE_WORDS, new Gson().toJson(list));
    }

    public void setBackupModel(@NonNull String str) {
        Preconditions.notNull(str, "assetName cannot be null");
        ContentValues contentValues = new ContentValues();
        contentValues.put(WakeWordModelContentProvider.KEY_BACKUP_MODEL_ASSET, str);
        this.wakeWordModelContentResolver.insert(getBackupModelFilenameUri(), contentValues);
    }

    public void unregisterWakeWordsListener(WakeWordsChangedListener wakeWordsChangedListener) {
        if (!this.wakeWordsChangedListeners.contains(wakeWordsChangedListener)) {
            Log.w(TAG, "listener is not registered, ignoring");
            return;
        }
        this.wakeWordsChangedListeners.remove(wakeWordsChangedListener);
        if (this.wakeWordsChangedListeners.size() != 0) {
            return;
        }
        this.wakeWordModelContentResolver.unregisterContentObserver(this.observer);
    }

    private void insert(Uri uri, String str, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, Long.valueOf(j));
        this.wakeWordModelContentResolver.insert(uri, contentValues);
    }

    private void insert(Uri uri, ContentValues contentValues) {
        this.wakeWordModelContentResolver.insert(uri, contentValues);
    }
}
