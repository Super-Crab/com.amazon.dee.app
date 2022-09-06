package com.amazon.alexa.feature;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class FeatureFlagContentProvider extends ContentProvider {
    private static final String AUTHORITY_POSTFIX = ".alexa.feature";
    private static final int FEATURE_FLAG = 0;
    @VisibleForTesting
    static final String NAME = "featureflags";
    private static final String TAG = FeatureFlagContentProvider.class.getSimpleName();
    private ContentResolver contentResolver;
    private Uri deleteAllURI;
    private FeatureFlagStore featureFlagStore;
    private final UriMatcher uriMatcher = new UriMatcher(-1);

    private String getAuthority() {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), getAppPackageName(), AUTHORITY_POSTFIX);
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(@NonNull Uri uri, @Nullable ContentValues[] contentValuesArr) {
        if (this.uriMatcher.match(uri) != 0) {
            return 0;
        }
        int bulkInsert = this.featureFlagStore.bulkInsert(uri, contentValuesArr);
        this.contentResolver.notifyChange(uri, null);
        return bulkInsert;
    }

    @VisibleForTesting
    FeatureFlagStore createFeatureFlagStore() {
        return new SharedPreferencesFeaturesFlagStore(getContext().getSharedPreferences(NAME, 0));
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        if (this.uriMatcher.match(uri) != 0) {
            return 0;
        }
        int delete = this.featureFlagStore.delete(uri, str, strArr);
        if (str == null) {
            this.contentResolver.notifyChange(this.deleteAllURI, null);
        } else {
            this.contentResolver.notifyChange(uri, null);
        }
        return delete;
    }

    @VisibleForTesting
    String getAppPackageName() {
        return getContext().getPackageName();
    }

    @VisibleForTesting
    ContentResolver getContentResolver() {
        return getContext().getContentResolver();
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if (this.uriMatcher.match(uri) != 0) {
            return null;
        }
        Uri insert = this.featureFlagStore.insert(uri, contentValues);
        this.contentResolver.notifyChange(uri, null);
        return insert;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        String authority = getAuthority();
        this.deleteAllURI = Uri.parse(String.format("content://%s/%s/deleteall", authority, NAME));
        this.uriMatcher.addURI(authority, NAME, 0);
        this.contentResolver = getContentResolver();
        this.featureFlagStore = createFeatureFlagStore();
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        if (this.uriMatcher.match(uri) != 0) {
            return null;
        }
        return this.featureFlagStore.query(uri, strArr, str, strArr2, str2);
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        if (this.uriMatcher.match(uri) != 0) {
            return 0;
        }
        int update = this.featureFlagStore.update(uri, contentValues, str, strArr);
        this.contentResolver.notifyChange(uri, null);
        return update;
    }
}
