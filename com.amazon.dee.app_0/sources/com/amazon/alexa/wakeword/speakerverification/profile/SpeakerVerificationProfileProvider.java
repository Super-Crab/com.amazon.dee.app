package com.amazon.alexa.wakeword.speakerverification.profile;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationAuthority;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
/* loaded from: classes11.dex */
public class SpeakerVerificationProfileProvider extends ContentProvider {
    public static final String AUTHORITY = "com.amazon.alexa.pryon.speakerverification.profile";
    public static final String COLUMN_PERSON_ID = "personId";
    @VisibleForTesting
    static final String KEY_PERSON_ID = "personId";
    private static final int MATCH_CODE_PROFILE_FILE = 1;
    public static final String PROFILE_FILE_PATH = "file";
    private static final String PROFILE_MIME_TYPE = "application/octet-stream";
    private static final String READ_MODE = "r";
    private static final String SHARED_PREF_NAME = "SpeakerVerificationProfileProviderSharedPref";
    private static final String TAG = SpeakerVerificationProfileProvider.class.getSimpleName();
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
    private SharedPreferences mSharedPreferences;
    private SpeakerVerificationAuthority mSpeakerVerificationAuthority;

    static {
        URI_MATCHER.addURI(AUTHORITY, "file", 1);
    }

    public SpeakerVerificationProfileProvider() {
    }

    private ParcelFileDescriptor getFileDescriptor(byte[] bArr) {
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]);
            try {
                IOUtils.copy(byteArrayInputStream, autoCloseOutputStream);
                autoCloseOutputStream.flush();
                autoCloseOutputStream.close();
                byteArrayInputStream.close();
                return createPipe[0];
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        autoCloseOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Error in getting ParcelFileDescriptor.", e, new Object[0]);
            return null;
        }
    }

    private String readPersonId() {
        return this.mSharedPreferences.getString("personId", "");
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, String str, String[] strArr) {
        throw new RuntimeException("Not supported.");
    }

    @Override // android.content.ContentProvider
    public String getType(@NonNull Uri uri) {
        if (URI_MATCHER.match(uri) == 1) {
            return "application/octet-stream";
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        throw new RuntimeException("Not supported.");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mSharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, 0);
        this.mSpeakerVerificationAuthority = new SpeakerVerificationAuthority(getContext(), null);
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String str) {
        if (URI_MATCHER.match(uri) != 1) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("URI doesn't match. Uri: ");
            outline107.append(uri.toString());
            Log.i(str2, outline107.toString());
            return null;
        } else if (!READ_MODE.equals(str)) {
            Log.w(TAG, String.format("Invalid read mode: %s, should be: %s", str, READ_MODE));
            return null;
        } else {
            String readPersonId = readPersonId();
            if (Strings.isNullOrEmpty(readPersonId)) {
                Log.e(TAG, "no personId set yet.");
                return null;
            }
            byte[] profile = this.mSpeakerVerificationAuthority.getProfile(readPersonId);
            if (profile == null) {
                Log.e(TAG, "failed to read profile.");
                return null;
            }
            return getFileDescriptor(profile);
        }
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
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"personId"});
        matrixCursor.newRow().add("personId", readPersonId());
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (URI_MATCHER.match(uri) != 1) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("URI doesn't match. Uri: ");
            outline107.append(uri.toString());
            Log.i(str2, outline107.toString());
            return 0;
        } else if (contentValues != null && contentValues.containsKey("personId")) {
            this.mSharedPreferences.edit().putString("personId", contentValues.getAsString("personId")).apply();
            return 1;
        } else {
            Log.w(TAG, "no expected values.");
            return 0;
        }
    }

    @VisibleForTesting
    SpeakerVerificationProfileProvider(SharedPreferences sharedPreferences, SpeakerVerificationAuthority speakerVerificationAuthority) {
        this.mSharedPreferences = sharedPreferences;
        this.mSpeakerVerificationAuthority = speakerVerificationAuthority;
    }
}
