package com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolScope;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.inject.Inject;
@FalcoProtocolScope
/* loaded from: classes8.dex */
public class EnrollmentStatusManager {
    public static final String COLUMN_ENROLLMENT_STATUS = "enrollment_status";
    public static final String UVR_ENROLLMENT_STATUS_FILE_PATH = "file";
    private static EnrollmentStatusManager sInstance;
    private Set<StatusListener> listeners = new CopyOnWriteArraySet();
    private final ContentResolver mContentResolver;
    public static final String AUTHORITY = "com.amazon.alexa.voice.handsfree.features.enrollmentstatus";
    public static final Uri URI = new Uri.Builder().scheme("content").authority(AUTHORITY).path("file").build();

    /* loaded from: classes8.dex */
    public interface StatusListener {
        void onStatusChanged(EnrollmentStatus enrollmentStatus);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public EnrollmentStatusManager(@NonNull ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    public static synchronized EnrollmentStatusManager getInstance(@NonNull Context context) {
        EnrollmentStatusManager enrollmentStatusManager;
        synchronized (EnrollmentStatusManager.class) {
            if (sInstance == null) {
                sInstance = new EnrollmentStatusManager(context.getApplicationContext().getContentResolver());
            }
            enrollmentStatusManager = sInstance;
        }
        return enrollmentStatusManager;
    }

    public void addStatusListener(StatusListener statusListener) {
        this.listeners.add(statusListener);
    }

    public EnrollmentStatus getEnrollmentStatus() {
        Cursor query = this.mContentResolver.query(URI, null, null, null, null);
        if (query == null) {
            return EnrollmentStatus.SETUP_NOT_SET;
        }
        try {
            if (query.getCount() == 0) {
                return EnrollmentStatus.SETUP_NOT_SET;
            }
            query.moveToFirst();
            int i = query.getInt(query.getColumnIndex(COLUMN_ENROLLMENT_STATUS));
            query.close();
            return EnrollmentStatus.fromValue(i);
        } finally {
            query.close();
        }
    }

    public void removeStatusListener(StatusListener statusListener) {
        this.listeners.remove(statusListener);
    }

    public void setEnrollmentStatus(@NonNull EnrollmentStatus enrollmentStatus) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ENROLLMENT_STATUS, Integer.valueOf(enrollmentStatus.getValue()));
        this.mContentResolver.update(URI, contentValues, null, null);
        for (StatusListener statusListener : this.listeners) {
            statusListener.onStatusChanged(enrollmentStatus);
        }
    }
}
