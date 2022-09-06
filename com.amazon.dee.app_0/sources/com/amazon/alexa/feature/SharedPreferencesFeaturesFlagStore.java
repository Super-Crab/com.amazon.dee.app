package com.amazon.alexa.feature;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.Map;
/* loaded from: classes7.dex */
public class SharedPreferencesFeaturesFlagStore implements FeatureFlagStore {
    @VisibleForTesting
    static final String COLUMN_FEATURE = "feature";
    @VisibleForTesting
    static final String FEATURE_SELECTION = "feature = ?";
    private static final String TAG = "SharedPreferencesFeaturesFlagStore";
    private final SharedPreferences sharedPreferences;
    @VisibleForTesting
    static final String COLUMN_IS_ENABLED = "isEnabled";
    @VisibleForTesting
    static final String[] COLUMNS = {"feature", COLUMN_IS_ENABLED};

    public SharedPreferencesFeaturesFlagStore(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override // com.amazon.alexa.feature.FeatureFlagStore
    public int bulkInsert(@NonNull Uri uri, @Nullable ContentValues[] contentValuesArr) {
        int i;
        if (contentValuesArr == null) {
            return 0;
        }
        synchronized (this.sharedPreferences) {
            SharedPreferences.Editor edit = this.sharedPreferences.edit();
            i = 0;
            for (ContentValues contentValues : contentValuesArr) {
                if (contentValues.containsKey(COLUMN_IS_ENABLED) && contentValues.containsKey("feature")) {
                    edit.putBoolean(contentValues.getAsString("feature"), contentValues.getAsBoolean(COLUMN_IS_ENABLED).booleanValue());
                    i++;
                } else {
                    Log.e(TAG, "Insertion failed due to wrong arguments: " + contentValues);
                }
            }
            edit.apply();
        }
        return i;
    }

    @Override // com.amazon.alexa.feature.FeatureFlagStore
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        int i;
        synchronized (this.sharedPreferences) {
            SharedPreferences.Editor edit = this.sharedPreferences.edit();
            i = 0;
            if (str == null) {
                i = this.sharedPreferences.getAll().size();
                edit.clear();
            } else if (FEATURE_SELECTION.equals(str) && strArr != null) {
                int length = strArr.length;
                int i2 = 0;
                while (i < length) {
                    String str2 = strArr[i];
                    if (this.sharedPreferences.contains(str2)) {
                        edit.remove(str2);
                        i2++;
                    }
                    i++;
                }
                i = i2;
            }
            edit.apply();
        }
        return i;
    }

    @Override // com.amazon.alexa.feature.FeatureFlagStore
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if (contentValues == null) {
            return null;
        }
        if (contentValues.containsKey(COLUMN_IS_ENABLED) && contentValues.containsKey("feature")) {
            synchronized (this.sharedPreferences) {
                SharedPreferences.Editor edit = this.sharedPreferences.edit();
                edit.putBoolean(contentValues.getAsString("feature"), contentValues.getAsBoolean(COLUMN_IS_ENABLED).booleanValue());
                edit.apply();
            }
        } else {
            Log.e(TAG, "Insertion failed due to wrong arguments.");
        }
        return uri;
    }

    @Override // com.amazon.alexa.feature.FeatureFlagStore
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        MatrixCursor matrixCursor = new MatrixCursor(COLUMNS);
        synchronized (this.sharedPreferences) {
            if (str == null) {
                for (Map.Entry<String, ?> entry : this.sharedPreferences.getAll().entrySet()) {
                    matrixCursor.newRow().add("feature", entry.getKey()).add(COLUMN_IS_ENABLED, entry.getValue());
                }
            } else if (FEATURE_SELECTION.equals(str) && strArr2 != null) {
                for (String str3 : strArr2) {
                    if (this.sharedPreferences.contains(str3)) {
                        matrixCursor.newRow().add("feature", str3).add(COLUMN_IS_ENABLED, Boolean.valueOf(this.sharedPreferences.getBoolean(str3, false)));
                    }
                }
            }
        }
        return matrixCursor;
    }

    @Override // com.amazon.alexa.feature.FeatureFlagStore
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        if (strArr == null || strArr.length == 0 || contentValues == null || !contentValues.containsKey(COLUMN_IS_ENABLED) || !FEATURE_SELECTION.equals(str)) {
            return 0;
        }
        synchronized (this.sharedPreferences) {
            SharedPreferences.Editor edit = this.sharedPreferences.edit();
            for (String str2 : strArr) {
                edit.putBoolean(str2, contentValues.getAsBoolean(COLUMN_IS_ENABLED).booleanValue());
            }
            edit.apply();
        }
        return strArr.length;
    }
}
