package com.amazon.alexa.voice.handsfree.features;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatus;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatusManager;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class UvrEnrollmentStatusProvider extends ContentProvider {
    @VisibleForTesting
    static final String KEY_ENROLLED_STATUS = "enrollmentStatus";
    private static final int MATCH_CODE_UVR_ENROLLMENT_STATUS_FILE = 1;
    private static final String SHARED_PREF_NAME = "UVREnrollmentStatusProviderSharedPref";
    private static final String TAG = UvrEnrollmentStatusProvider.class.getSimpleName();
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
    private SharedPreferences mSharedPreferences;

    static {
        URI_MATCHER.addURI(EnrollmentStatusManager.AUTHORITY, "file", 1);
    }

    public UvrEnrollmentStatusProvider() {
    }

    private int readUvrEnrollmentStatus() {
        return this.mSharedPreferences.getInt(KEY_ENROLLED_STATUS, EnrollmentStatus.SETUP_NOT_SET.getValue());
    }

    @VisibleForTesting
    MatrixCursor createNewMatrixCursor() {
        return new MatrixCursor(new String[]{EnrollmentStatusManager.COLUMN_ENROLLMENT_STATUS});
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, String str, String[] strArr) {
        throw new RuntimeException("Not supported.");
    }

    @Override // android.content.ContentProvider
    public String getType(@NonNull Uri uri) {
        throw new RuntimeException("Not supported.");
    }

    @Override // android.content.ContentProvider
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        throw new RuntimeException("Not supported.");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mSharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, 0);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(@NonNull Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (URI_MATCHER.match(uri) != 1) {
            String str3 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("URI doesn't match. Uri: ");
            outline107.append(uri.toString());
            Log.i(str3, outline107.toString());
            return null;
        }
        MatrixCursor createNewMatrixCursor = createNewMatrixCursor();
        createNewMatrixCursor.newRow().add(EnrollmentStatusManager.COLUMN_ENROLLMENT_STATUS, Integer.valueOf(readUvrEnrollmentStatus()));
        return createNewMatrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (URI_MATCHER.match(uri) != 1) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("URI doesn't match. Uri: ");
            outline107.append(uri.toString());
            Log.i(str2, outline107.toString());
            return 0;
        } else if (contentValues != null && contentValues.containsKey(EnrollmentStatusManager.COLUMN_ENROLLMENT_STATUS)) {
            this.mSharedPreferences.edit().putInt(KEY_ENROLLED_STATUS, contentValues.getAsInteger(EnrollmentStatusManager.COLUMN_ENROLLMENT_STATUS).intValue()).apply();
            return 1;
        } else {
            Log.w(TAG, "no expected values.");
            return 0;
        }
    }

    @VisibleForTesting
    UvrEnrollmentStatusProvider(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }
}
