package com.amazon.photos.uploader.internal.dao;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.dao.RequestSummary;
import com.amazon.photos.uploader.dao.StateWithCount;
import com.amazon.photos.uploader.internal.Converters;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/* loaded from: classes13.dex */
public final class SnapshotRequestDao_Impl implements SnapshotRequestDao {
    private final Converters __converters = new Converters();
    private final RoomDatabase __db;

    public SnapshotRequestDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public RequestSummary getPendingRequestSummary() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state in ('RUNNING', 'BLOCKED', 'ENQUEUED')", 0);
        this.__db.assertNotSuspendingTransaction();
        RequestSummary requestSummary = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
            if (query.moveToFirst()) {
                requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
            }
            return requestSummary;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public List<StateWithCount> getRequestCountByStates() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT state, COUNT(*) AS count FROM upload_request GROUP BY (state)", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new StateWithCount(this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow)), query.getInt(columnIndexOrThrow2)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public RequestSummary getRequestSummary() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request", 0);
        this.__db.assertNotSuspendingTransaction();
        RequestSummary requestSummary = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
            if (query.moveToFirst()) {
                requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
            }
            return requestSummary;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public RequestSummary getRequestSummaryForState(UploadState uploadState, String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = ? AND queue = ?", 2);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        RequestSummary requestSummary = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
            if (query.moveToFirst()) {
                requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
            }
            return requestSummary;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public RequestSummary getRequestSummaryForStates(Set<? extends UploadState> set) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state IN (");
        int size = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int i = 1;
        for (UploadState uploadState : set) {
            String fromUploadState = this.__converters.fromUploadState(uploadState);
            if (fromUploadState == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, fromUploadState);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        RequestSummary requestSummary = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
            if (query.moveToFirst()) {
                requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
            }
            return requestSummary;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public List<UploadRequest> getRequestsForState(UploadState uploadState) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE state = ? ORDER BY id DESC", 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
            } catch (Throwable th) {
                th = th;
                roomSQLiteQuery = acquire;
            }
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                int i = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    long j = query.getLong(columnIndexOrThrow);
                    String string = query.getString(columnIndexOrThrow2);
                    String string2 = query.getString(columnIndexOrThrow3);
                    String string3 = query.getString(columnIndexOrThrow4);
                    String string4 = query.getString(columnIndexOrThrow5);
                    String string5 = query.getString(columnIndexOrThrow6);
                    boolean z = query.getInt(columnIndexOrThrow7) != 0;
                    boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                    String string6 = query.getString(columnIndexOrThrow9);
                    int i2 = columnIndexOrThrow;
                    UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                    String string7 = query.getString(columnIndexOrThrow11);
                    long j2 = query.getLong(columnIndexOrThrow12);
                    int i3 = i;
                    long j3 = query.getLong(i3);
                    int i4 = columnIndexOrThrow14;
                    String string8 = query.getString(i4);
                    int i5 = columnIndexOrThrow12;
                    i = i3;
                    int i6 = columnIndexOrThrow15;
                    columnIndexOrThrow15 = i6;
                    UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i6));
                    int i7 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i7;
                    Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i7));
                    int i8 = columnIndexOrThrow17;
                    int i9 = query.getInt(i8);
                    int i10 = columnIndexOrThrow18;
                    int i11 = query.getInt(i10);
                    columnIndexOrThrow17 = i8;
                    int i12 = columnIndexOrThrow19;
                    int i13 = query.getInt(i12);
                    columnIndexOrThrow19 = i12;
                    int i14 = columnIndexOrThrow20;
                    boolean z3 = i13 != 0;
                    long j4 = query.getLong(i14);
                    columnIndexOrThrow20 = i14;
                    int i15 = columnIndexOrThrow21;
                    long j5 = query.getLong(i15);
                    columnIndexOrThrow21 = i15;
                    int i16 = columnIndexOrThrow22;
                    int i17 = query.getInt(i16);
                    columnIndexOrThrow22 = i16;
                    int i18 = columnIndexOrThrow23;
                    int i19 = query.getInt(i18);
                    columnIndexOrThrow23 = i18;
                    int i20 = columnIndexOrThrow24;
                    boolean z4 = i19 != 0;
                    String string9 = query.getString(i20);
                    columnIndexOrThrow24 = i20;
                    int i21 = columnIndexOrThrow25;
                    String string10 = query.getString(i21);
                    columnIndexOrThrow25 = i21;
                    columnIndexOrThrow18 = i10;
                    int i22 = columnIndexOrThrow26;
                    columnIndexOrThrow26 = i22;
                    arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i9, i11, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i22))));
                    columnIndexOrThrow12 = i5;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow14 = i4;
                }
                this.__db.setTransactionSuccessful();
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public List<UploadRequest> getRequestsForStates(Set<? extends UploadState> set) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM upload_request WHERE state IN(");
        int size = set.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") ORDER BY id DESC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (UploadState uploadState : set) {
            String fromUploadState = this.__converters.fromUploadState(uploadState);
            if (fromUploadState == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, fromUploadState);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i2 = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i3 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i4 = i2;
                        long j3 = query.getLong(i4);
                        int i5 = columnIndexOrThrow14;
                        String string8 = query.getString(i5);
                        i2 = i4;
                        int i6 = columnIndexOrThrow2;
                        int i7 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i7;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i7));
                        int i8 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i8;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i8));
                        int i9 = columnIndexOrThrow17;
                        int i10 = query.getInt(i9);
                        int i11 = columnIndexOrThrow18;
                        int i12 = query.getInt(i11);
                        columnIndexOrThrow17 = i9;
                        int i13 = columnIndexOrThrow19;
                        columnIndexOrThrow19 = i13;
                        boolean z3 = query.getInt(i13) != 0;
                        int i14 = columnIndexOrThrow20;
                        long j4 = query.getLong(i14);
                        columnIndexOrThrow20 = i14;
                        int i15 = columnIndexOrThrow21;
                        long j5 = query.getLong(i15);
                        columnIndexOrThrow21 = i15;
                        int i16 = columnIndexOrThrow22;
                        int i17 = query.getInt(i16);
                        columnIndexOrThrow22 = i16;
                        int i18 = columnIndexOrThrow23;
                        columnIndexOrThrow23 = i18;
                        boolean z4 = query.getInt(i18) != 0;
                        int i19 = columnIndexOrThrow24;
                        String string9 = query.getString(i19);
                        columnIndexOrThrow24 = i19;
                        int i20 = columnIndexOrThrow25;
                        String string10 = query.getString(i20);
                        columnIndexOrThrow25 = i20;
                        columnIndexOrThrow18 = i11;
                        int i21 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i21;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i10, i12, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i21))));
                        columnIndexOrThrow2 = i6;
                        columnIndexOrThrow14 = i5;
                        columnIndexOrThrow = i3;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public List<UploadRequest> getRequestsWithBlockers(Set<String> set) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM upload_request WHERE state in ('RUNNING', 'BLOCKED', 'ENQUEUED') AND blocker IN(");
        int size = set.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") ORDER BY id DESC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (String str : set) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i2 = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i3 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i4 = i2;
                        long j3 = query.getLong(i4);
                        int i5 = columnIndexOrThrow14;
                        String string8 = query.getString(i5);
                        i2 = i4;
                        int i6 = columnIndexOrThrow2;
                        int i7 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i7;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i7));
                        int i8 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i8;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i8));
                        int i9 = columnIndexOrThrow17;
                        int i10 = query.getInt(i9);
                        int i11 = columnIndexOrThrow18;
                        int i12 = query.getInt(i11);
                        columnIndexOrThrow17 = i9;
                        int i13 = columnIndexOrThrow19;
                        columnIndexOrThrow19 = i13;
                        boolean z3 = query.getInt(i13) != 0;
                        int i14 = columnIndexOrThrow20;
                        long j4 = query.getLong(i14);
                        columnIndexOrThrow20 = i14;
                        int i15 = columnIndexOrThrow21;
                        long j5 = query.getLong(i15);
                        columnIndexOrThrow21 = i15;
                        int i16 = columnIndexOrThrow22;
                        int i17 = query.getInt(i16);
                        columnIndexOrThrow22 = i16;
                        int i18 = columnIndexOrThrow23;
                        columnIndexOrThrow23 = i18;
                        boolean z4 = query.getInt(i18) != 0;
                        int i19 = columnIndexOrThrow24;
                        String string9 = query.getString(i19);
                        columnIndexOrThrow24 = i19;
                        int i20 = columnIndexOrThrow25;
                        String string10 = query.getString(i20);
                        columnIndexOrThrow25 = i20;
                        columnIndexOrThrow18 = i11;
                        int i21 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i21;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i10, i12, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i21))));
                        columnIndexOrThrow2 = i6;
                        columnIndexOrThrow14 = i5;
                        columnIndexOrThrow = i3;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public RequestSummary getUploadedToFamilyVaultSummary() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = 'SUCCEEDED' AND add_to_family_vault = 1 ORDER BY id DESC", 0);
        this.__db.assertNotSuspendingTransaction();
        RequestSummary requestSummary = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
            if (query.moveToFirst()) {
                requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
            }
            return requestSummary;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public RequestSummary getPendingRequestSummary(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE queue = ? AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED')", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        RequestSummary requestSummary = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
            if (query.moveToFirst()) {
                requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
            }
            return requestSummary;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public RequestSummary getRequestSummaryForState(UploadState uploadState) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = ?", 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        this.__db.assertNotSuspendingTransaction();
        RequestSummary requestSummary = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
            if (query.moveToFirst()) {
                requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
            }
            return requestSummary;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public List<UploadRequest> getRequestsForState(Set<String> set, UploadState uploadState) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT ");
        newStringBuilder.append("*");
        newStringBuilder.append(" FROM upload_request WHERE state = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" AND queue IN(");
        int size = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") ORDER BY id DESC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        int i = 2;
        for (String str : set) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i2 = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i3 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i4 = i2;
                        long j3 = query.getLong(i4);
                        int i5 = columnIndexOrThrow14;
                        String string8 = query.getString(i5);
                        i2 = i4;
                        int i6 = columnIndexOrThrow12;
                        int i7 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i7;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i7));
                        int i8 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i8;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i8));
                        int i9 = columnIndexOrThrow17;
                        int i10 = query.getInt(i9);
                        int i11 = columnIndexOrThrow18;
                        int i12 = query.getInt(i11);
                        columnIndexOrThrow17 = i9;
                        int i13 = columnIndexOrThrow19;
                        int i14 = query.getInt(i13);
                        columnIndexOrThrow19 = i13;
                        int i15 = columnIndexOrThrow20;
                        boolean z3 = i14 != 0;
                        long j4 = query.getLong(i15);
                        columnIndexOrThrow20 = i15;
                        int i16 = columnIndexOrThrow21;
                        long j5 = query.getLong(i16);
                        columnIndexOrThrow21 = i16;
                        int i17 = columnIndexOrThrow22;
                        int i18 = query.getInt(i17);
                        columnIndexOrThrow22 = i17;
                        int i19 = columnIndexOrThrow23;
                        int i20 = query.getInt(i19);
                        columnIndexOrThrow23 = i19;
                        int i21 = columnIndexOrThrow24;
                        boolean z4 = i20 != 0;
                        String string9 = query.getString(i21);
                        columnIndexOrThrow24 = i21;
                        int i22 = columnIndexOrThrow25;
                        String string10 = query.getString(i22);
                        columnIndexOrThrow25 = i22;
                        columnIndexOrThrow18 = i11;
                        int i23 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i23;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i10, i12, z3, j4, j5, i18, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i23))));
                        columnIndexOrThrow12 = i6;
                        columnIndexOrThrow14 = i5;
                        columnIndexOrThrow = i3;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.SnapshotRequestDao
    public List<UploadRequest> getRequestsForStates(Set<String> set, Set<? extends UploadState> set2) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM upload_request WHERE state IN(");
        int size = set2.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") AND queue IN(");
        int size2 = set.size();
        StringUtil.appendPlaceholders(outline114, size2);
        outline114.append(") ORDER BY id DESC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0 + size2);
        int i = 1;
        for (UploadState uploadState : set2) {
            String fromUploadState = this.__converters.fromUploadState(uploadState);
            if (fromUploadState == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, fromUploadState);
            }
            i++;
        }
        int i2 = size + 1;
        for (String str : set) {
            if (str == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindString(i2, str);
            }
            i2++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i3 = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i4 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i5 = i3;
                        long j3 = query.getLong(i5);
                        int i6 = columnIndexOrThrow14;
                        String string8 = query.getString(i6);
                        i3 = i5;
                        int i7 = columnIndexOrThrow2;
                        int i8 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i8;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i8));
                        int i9 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i9;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i9));
                        int i10 = columnIndexOrThrow17;
                        int i11 = query.getInt(i10);
                        int i12 = columnIndexOrThrow18;
                        int i13 = query.getInt(i12);
                        columnIndexOrThrow17 = i10;
                        int i14 = columnIndexOrThrow19;
                        columnIndexOrThrow19 = i14;
                        boolean z3 = query.getInt(i14) != 0;
                        int i15 = columnIndexOrThrow20;
                        long j4 = query.getLong(i15);
                        columnIndexOrThrow20 = i15;
                        int i16 = columnIndexOrThrow21;
                        long j5 = query.getLong(i16);
                        columnIndexOrThrow21 = i16;
                        int i17 = columnIndexOrThrow22;
                        int i18 = query.getInt(i17);
                        columnIndexOrThrow22 = i17;
                        int i19 = columnIndexOrThrow23;
                        columnIndexOrThrow23 = i19;
                        boolean z4 = query.getInt(i19) != 0;
                        int i20 = columnIndexOrThrow24;
                        String string9 = query.getString(i20);
                        columnIndexOrThrow24 = i20;
                        int i21 = columnIndexOrThrow25;
                        String string10 = query.getString(i21);
                        columnIndexOrThrow25 = i21;
                        columnIndexOrThrow18 = i12;
                        int i22 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i22;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i11, i13, z3, j4, j5, i18, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i22))));
                        columnIndexOrThrow2 = i7;
                        columnIndexOrThrow14 = i6;
                        columnIndexOrThrow = i4;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }
}
