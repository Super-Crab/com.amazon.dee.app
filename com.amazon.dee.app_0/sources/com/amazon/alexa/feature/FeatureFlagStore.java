package com.amazon.alexa.feature;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes7.dex */
public interface FeatureFlagStore {
    int bulkInsert(@NonNull Uri uri, @Nullable ContentValues[] contentValuesArr);

    int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr);

    @Nullable
    Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues);

    @Nullable
    Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2);

    int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr);
}
