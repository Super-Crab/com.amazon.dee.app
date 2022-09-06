package com.amazon.alexa.wakeword.speakerverification.profile;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
/* loaded from: classes11.dex */
public class ProfileContentProviderHelper {
    @VisibleForTesting
    static final Uri PROFILE_PROVIDER_URI = new Uri.Builder().scheme("content").authority(SpeakerVerificationProfileProvider.AUTHORITY).path("file").build();
    private static final String TAG = ProfileContentProviderHelper.class.getSimpleName();
    private final Context mContext;

    public ProfileContentProviderHelper(@NonNull Context context) {
        this.mContext = context;
    }

    @Nullable
    private String readPersonId() {
        try {
            Cursor query = this.mContext.getContentResolver().query(PROFILE_PROVIDER_URI, null, null, null, null);
            if (query != null && query.getCount() != 0) {
                query.moveToFirst();
                int columnIndex = query.getColumnIndex(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID);
                if (columnIndex < 0) {
                    Log.e(TAG, "no personId column returned from profile provider.");
                    query.close();
                    return null;
                }
                String string = query.getString(columnIndex);
                query.close();
                return string;
            }
            Log.e(TAG, "no result from profile content provider.");
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, "exception encountered when read profile content provider", e, new Object[0]);
            return null;
        }
    }

    @Nullable
    public String getPersonId() {
        return readPersonId();
    }

    @Nullable
    public byte[] getProfileAsBytes() {
        try {
            InputStream openInputStream = this.mContext.getContentResolver().openInputStream(PROFILE_PROVIDER_URI);
            if (openInputStream == null) {
                Log.e(TAG, "got null inputStream from profile provider.");
                if (openInputStream != null) {
                    openInputStream.close();
                }
                return null;
            }
            byte[] byteArray = IOUtils.toByteArray(openInputStream);
            openInputStream.close();
            return byteArray;
        } catch (Exception e) {
            Log.e(TAG, "exception when reading profile from content provider", e, new Object[0]);
            return null;
        }
    }

    public void savePersonId(@NonNull String str) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID, str);
        contentResolver.update(PROFILE_PROVIDER_URI, contentValues, null, null);
    }
}
