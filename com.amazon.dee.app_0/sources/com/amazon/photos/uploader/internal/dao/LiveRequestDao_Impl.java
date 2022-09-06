package com.amazon.photos.uploader.internal.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.paging.LimitOffsetDataSource;
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
import com.amazon.photos.uploader.internal.Converters;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
/* loaded from: classes13.dex */
public final class LiveRequestDao_Impl implements LiveRequestDao {
    private final Converters __converters = new Converters();
    private final RoomDatabase __db;

    public LiveRequestDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public LiveData<RequestSummary> getPendingRequestSummary() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state in ('RUNNING', 'BLOCKED', 'ENQUEUED')", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"upload_request"}, false, new Callable<RequestSummary>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.2
            protected void finalize() {
                acquire.release();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public RequestSummary mo4449call() throws Exception {
                RequestSummary requestSummary = null;
                Cursor query = DBUtil.query(LiveRequestDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
                    if (query.moveToFirst()) {
                        requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
                    }
                    return requestSummary;
                } finally {
                    query.close();
                }
            }
        });
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public LiveData<RequestSummary> getRequestSummary() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"upload_request"}, false, new Callable<RequestSummary>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.1
            protected void finalize() {
                acquire.release();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public RequestSummary mo4448call() throws Exception {
                RequestSummary requestSummary = null;
                Cursor query = DBUtil.query(LiveRequestDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
                    if (query.moveToFirst()) {
                        requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
                    }
                    return requestSummary;
                } finally {
                    query.close();
                }
            }
        });
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public LiveData<RequestSummary> getRequestSummaryForState(UploadState uploadState, String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = ? AND queue = ?", 2);
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
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"upload_request"}, false, new Callable<RequestSummary>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.4
            protected void finalize() {
                acquire.release();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public RequestSummary mo4451call() throws Exception {
                RequestSummary requestSummary = null;
                Cursor query = DBUtil.query(LiveRequestDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
                    if (query.moveToFirst()) {
                        requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
                    }
                    return requestSummary;
                } finally {
                    query.close();
                }
            }
        });
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public DataSource.Factory<Integer, UploadRequest> getRequestsForStateDataSource(UploadState uploadState) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE state = ? ORDER BY id DESC", 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        return new DataSource.Factory<Integer, UploadRequest>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.6
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, UploadRequest> create() {
                return new LimitOffsetDataSource<UploadRequest>(LiveRequestDao_Impl.this.__db, acquire, true, "upload_request") { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.6.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<UploadRequest> convertRows(Cursor cursor) {
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_path");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "upload_path");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "content_date");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, SierraContentProviderContract.MD5_VALUE);
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "visual_digest");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "suppress_duplication");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "rename_on_name_conflict");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "upload_category");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "state");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "queue");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "current_progress");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "max_progress");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "error_code");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "error_category");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "blocker");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "total_attempt_count");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "attempt_count");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursor2, "max_attempts_exceeded");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursor2, "creation_time_millis");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_size");
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursor2, "priority");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursor2, "add_to_family_vault");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursor2, "app_data");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursor2, "parent_id");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursor2, "content_uri");
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            long j = cursor2.getLong(columnIndexOrThrow);
                            String string = cursor2.getString(columnIndexOrThrow2);
                            String string2 = cursor2.getString(columnIndexOrThrow3);
                            String string3 = cursor2.getString(columnIndexOrThrow4);
                            String string4 = cursor2.getString(columnIndexOrThrow5);
                            String string5 = cursor2.getString(columnIndexOrThrow6);
                            boolean z = cursor2.getInt(columnIndexOrThrow7) != 0;
                            boolean z2 = cursor2.getInt(columnIndexOrThrow8) != 0;
                            String string6 = cursor2.getString(columnIndexOrThrow9);
                            int i = columnIndexOrThrow;
                            int i2 = columnIndexOrThrow2;
                            UploadState fromUploadStateString = LiveRequestDao_Impl.this.__converters.fromUploadStateString(cursor2.getString(columnIndexOrThrow10));
                            String string7 = cursor2.getString(columnIndexOrThrow11);
                            long j2 = cursor2.getLong(columnIndexOrThrow12);
                            long j3 = cursor2.getLong(columnIndexOrThrow13);
                            String string8 = cursor2.getString(columnIndexOrThrow14);
                            UploadErrorCategory fromUploadErrorCategoryString = LiveRequestDao_Impl.this.__converters.fromUploadErrorCategoryString(cursor2.getString(columnIndexOrThrow15));
                            Blocker fromBlockerString = LiveRequestDao_Impl.this.__converters.fromBlockerString(cursor2.getString(columnIndexOrThrow16));
                            int i3 = cursor2.getInt(columnIndexOrThrow17);
                            int i4 = columnIndexOrThrow18;
                            int i5 = cursor2.getInt(i4);
                            int i6 = columnIndexOrThrow20;
                            boolean z3 = cursor2.getInt(columnIndexOrThrow19) != 0;
                            long j4 = cursor2.getLong(i6);
                            columnIndexOrThrow20 = i6;
                            int i7 = columnIndexOrThrow24;
                            columnIndexOrThrow24 = i7;
                            columnIndexOrThrow18 = i4;
                            arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i3, i5, z3, j4, cursor2.getLong(columnIndexOrThrow21), cursor2.getInt(columnIndexOrThrow22), cursor2.getInt(columnIndexOrThrow23) != 0, cursor2.getString(i7), cursor2.getString(columnIndexOrThrow25), LiveRequestDao_Impl.this.__converters.fromContentUriString(cursor2.getString(columnIndexOrThrow26))));
                            cursor2 = cursor;
                            columnIndexOrThrow = i;
                            columnIndexOrThrow2 = i2;
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public LiveData<List<UploadRequest>> getRequestsForStateLiveData(UploadState uploadState) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE state = ? ORDER BY id DESC", 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"upload_request"}, true, new Callable<List<UploadRequest>>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.7
            protected void finalize() {
                acquire.release();
            }

            @Override // java.util.concurrent.Callable
            public List<UploadRequest> call() throws Exception {
                LiveRequestDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor query = DBUtil.query(LiveRequestDao_Impl.this.__db, acquire, false, null);
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
                        UploadState fromUploadStateString = LiveRequestDao_Impl.this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i3 = i;
                        long j3 = query.getLong(i3);
                        int i4 = columnIndexOrThrow14;
                        String string8 = query.getString(i4);
                        i = i3;
                        int i5 = columnIndexOrThrow2;
                        int i6 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i6;
                        UploadErrorCategory fromUploadErrorCategoryString = LiveRequestDao_Impl.this.__converters.fromUploadErrorCategoryString(query.getString(i6));
                        int i7 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i7;
                        Blocker fromBlockerString = LiveRequestDao_Impl.this.__converters.fromBlockerString(query.getString(i7));
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
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i9, i11, z3, j4, j5, i17, z4, string9, string10, LiveRequestDao_Impl.this.__converters.fromContentUriString(query.getString(i22))));
                        columnIndexOrThrow2 = i5;
                        columnIndexOrThrow = i2;
                        columnIndexOrThrow14 = i4;
                    }
                    LiveRequestDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return arrayList;
                } finally {
                    LiveRequestDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public DataSource.Factory<Integer, UploadRequest> getRequestsForStatesDataSource(Set<? extends UploadState> set) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM upload_request WHERE state IN(");
        int size = set.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") ORDER BY id DESC");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
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
        return new DataSource.Factory<Integer, UploadRequest>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.8
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, UploadRequest> create() {
                return new LimitOffsetDataSource<UploadRequest>(LiveRequestDao_Impl.this.__db, acquire, true, "upload_request") { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.8.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<UploadRequest> convertRows(Cursor cursor) {
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_path");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "upload_path");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "content_date");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, SierraContentProviderContract.MD5_VALUE);
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "visual_digest");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "suppress_duplication");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "rename_on_name_conflict");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "upload_category");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "state");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "queue");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "current_progress");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "max_progress");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "error_code");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "error_category");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "blocker");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "total_attempt_count");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "attempt_count");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursor2, "max_attempts_exceeded");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursor2, "creation_time_millis");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_size");
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursor2, "priority");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursor2, "add_to_family_vault");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursor2, "app_data");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursor2, "parent_id");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursor2, "content_uri");
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            long j = cursor2.getLong(columnIndexOrThrow);
                            String string = cursor2.getString(columnIndexOrThrow2);
                            String string2 = cursor2.getString(columnIndexOrThrow3);
                            String string3 = cursor2.getString(columnIndexOrThrow4);
                            String string4 = cursor2.getString(columnIndexOrThrow5);
                            String string5 = cursor2.getString(columnIndexOrThrow6);
                            boolean z = cursor2.getInt(columnIndexOrThrow7) != 0;
                            boolean z2 = cursor2.getInt(columnIndexOrThrow8) != 0;
                            String string6 = cursor2.getString(columnIndexOrThrow9);
                            int i2 = columnIndexOrThrow;
                            int i3 = columnIndexOrThrow2;
                            UploadState fromUploadStateString = LiveRequestDao_Impl.this.__converters.fromUploadStateString(cursor2.getString(columnIndexOrThrow10));
                            String string7 = cursor2.getString(columnIndexOrThrow11);
                            long j2 = cursor2.getLong(columnIndexOrThrow12);
                            long j3 = cursor2.getLong(columnIndexOrThrow13);
                            String string8 = cursor2.getString(columnIndexOrThrow14);
                            UploadErrorCategory fromUploadErrorCategoryString = LiveRequestDao_Impl.this.__converters.fromUploadErrorCategoryString(cursor2.getString(columnIndexOrThrow15));
                            Blocker fromBlockerString = LiveRequestDao_Impl.this.__converters.fromBlockerString(cursor2.getString(columnIndexOrThrow16));
                            int i4 = cursor2.getInt(columnIndexOrThrow17);
                            int i5 = columnIndexOrThrow18;
                            int i6 = cursor2.getInt(i5);
                            int i7 = columnIndexOrThrow20;
                            boolean z3 = cursor2.getInt(columnIndexOrThrow19) != 0;
                            long j4 = cursor2.getLong(i7);
                            columnIndexOrThrow20 = i7;
                            int i8 = columnIndexOrThrow24;
                            columnIndexOrThrow24 = i8;
                            columnIndexOrThrow18 = i5;
                            arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i4, i6, z3, j4, cursor2.getLong(columnIndexOrThrow21), cursor2.getInt(columnIndexOrThrow22), cursor2.getInt(columnIndexOrThrow23) != 0, cursor2.getString(i8), cursor2.getString(columnIndexOrThrow25), LiveRequestDao_Impl.this.__converters.fromContentUriString(cursor2.getString(columnIndexOrThrow26))));
                            cursor2 = cursor;
                            columnIndexOrThrow = i2;
                            columnIndexOrThrow2 = i3;
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public LiveData<List<UploadRequest>> getRequestsForStatesLiveData(Set<? extends UploadState> set) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM upload_request WHERE state IN(");
        int size = set.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") ORDER BY id DESC");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
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
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"upload_request"}, true, new Callable<List<UploadRequest>>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.9
            protected void finalize() {
                acquire.release();
            }

            @Override // java.util.concurrent.Callable
            public List<UploadRequest> call() throws Exception {
                LiveRequestDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor query = DBUtil.query(LiveRequestDao_Impl.this.__db, acquire, false, null);
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
                        UploadState fromUploadStateString = LiveRequestDao_Impl.this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
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
                        UploadErrorCategory fromUploadErrorCategoryString = LiveRequestDao_Impl.this.__converters.fromUploadErrorCategoryString(query.getString(i7));
                        int i8 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i8;
                        Blocker fromBlockerString = LiveRequestDao_Impl.this.__converters.fromBlockerString(query.getString(i8));
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
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i10, i12, z3, j4, j5, i18, z4, string9, string10, LiveRequestDao_Impl.this.__converters.fromContentUriString(query.getString(i23))));
                        columnIndexOrThrow2 = i6;
                        columnIndexOrThrow = i3;
                        columnIndexOrThrow14 = i5;
                    }
                    LiveRequestDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return arrayList;
                } finally {
                    LiveRequestDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public LiveData<RequestSummary> getPendingRequestSummary(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE queue = ? AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED')", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"upload_request"}, false, new Callable<RequestSummary>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.3
            protected void finalize() {
                acquire.release();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public RequestSummary mo4450call() throws Exception {
                RequestSummary requestSummary = null;
                Cursor query = DBUtil.query(LiveRequestDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
                    if (query.moveToFirst()) {
                        requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
                    }
                    return requestSummary;
                } finally {
                    query.close();
                }
            }
        });
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public DataSource.Factory<Integer, UploadRequest> getRequestsForStateDataSource(Set<String> set, UploadState uploadState) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT ");
        newStringBuilder.append("*");
        newStringBuilder.append(" FROM upload_request WHERE state = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" AND queue IN(");
        int size = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") ORDER BY id DESC");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 1);
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
        return new DataSource.Factory<Integer, UploadRequest>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.10
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, UploadRequest> create() {
                return new LimitOffsetDataSource<UploadRequest>(LiveRequestDao_Impl.this.__db, acquire, true, "upload_request") { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.10.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<UploadRequest> convertRows(Cursor cursor) {
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_path");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "upload_path");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "content_date");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, SierraContentProviderContract.MD5_VALUE);
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "visual_digest");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "suppress_duplication");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "rename_on_name_conflict");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "upload_category");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "state");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "queue");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "current_progress");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "max_progress");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "error_code");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "error_category");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "blocker");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "total_attempt_count");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "attempt_count");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursor2, "max_attempts_exceeded");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursor2, "creation_time_millis");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_size");
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursor2, "priority");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursor2, "add_to_family_vault");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursor2, "app_data");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursor2, "parent_id");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursor2, "content_uri");
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            long j = cursor2.getLong(columnIndexOrThrow);
                            String string = cursor2.getString(columnIndexOrThrow2);
                            String string2 = cursor2.getString(columnIndexOrThrow3);
                            String string3 = cursor2.getString(columnIndexOrThrow4);
                            String string4 = cursor2.getString(columnIndexOrThrow5);
                            String string5 = cursor2.getString(columnIndexOrThrow6);
                            boolean z = cursor2.getInt(columnIndexOrThrow7) != 0;
                            boolean z2 = cursor2.getInt(columnIndexOrThrow8) != 0;
                            String string6 = cursor2.getString(columnIndexOrThrow9);
                            int i2 = columnIndexOrThrow;
                            int i3 = columnIndexOrThrow2;
                            UploadState fromUploadStateString = LiveRequestDao_Impl.this.__converters.fromUploadStateString(cursor2.getString(columnIndexOrThrow10));
                            String string7 = cursor2.getString(columnIndexOrThrow11);
                            long j2 = cursor2.getLong(columnIndexOrThrow12);
                            long j3 = cursor2.getLong(columnIndexOrThrow13);
                            String string8 = cursor2.getString(columnIndexOrThrow14);
                            UploadErrorCategory fromUploadErrorCategoryString = LiveRequestDao_Impl.this.__converters.fromUploadErrorCategoryString(cursor2.getString(columnIndexOrThrow15));
                            Blocker fromBlockerString = LiveRequestDao_Impl.this.__converters.fromBlockerString(cursor2.getString(columnIndexOrThrow16));
                            int i4 = cursor2.getInt(columnIndexOrThrow17);
                            int i5 = columnIndexOrThrow18;
                            int i6 = cursor2.getInt(i5);
                            int i7 = columnIndexOrThrow20;
                            boolean z3 = cursor2.getInt(columnIndexOrThrow19) != 0;
                            long j4 = cursor2.getLong(i7);
                            columnIndexOrThrow20 = i7;
                            int i8 = columnIndexOrThrow24;
                            columnIndexOrThrow24 = i8;
                            columnIndexOrThrow18 = i5;
                            arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i4, i6, z3, j4, cursor2.getLong(columnIndexOrThrow21), cursor2.getInt(columnIndexOrThrow22), cursor2.getInt(columnIndexOrThrow23) != 0, cursor2.getString(i8), cursor2.getString(columnIndexOrThrow25), LiveRequestDao_Impl.this.__converters.fromContentUriString(cursor2.getString(columnIndexOrThrow26))));
                            cursor2 = cursor;
                            columnIndexOrThrow = i2;
                            columnIndexOrThrow2 = i3;
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public LiveData<List<UploadRequest>> getRequestsForStateLiveData(Set<String> set, UploadState uploadState) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT ");
        newStringBuilder.append("*");
        newStringBuilder.append(" FROM upload_request WHERE state = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" AND queue IN(");
        int size = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") ORDER BY id DESC");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 1);
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
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"upload_request"}, true, new Callable<List<UploadRequest>>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.11
            protected void finalize() {
                acquire.release();
            }

            @Override // java.util.concurrent.Callable
            public List<UploadRequest> call() throws Exception {
                LiveRequestDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor query = DBUtil.query(LiveRequestDao_Impl.this.__db, acquire, false, null);
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
                        UploadState fromUploadStateString = LiveRequestDao_Impl.this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
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
                        UploadErrorCategory fromUploadErrorCategoryString = LiveRequestDao_Impl.this.__converters.fromUploadErrorCategoryString(query.getString(i7));
                        int i8 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i8;
                        Blocker fromBlockerString = LiveRequestDao_Impl.this.__converters.fromBlockerString(query.getString(i8));
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
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i10, i12, z3, j4, j5, i18, z4, string9, string10, LiveRequestDao_Impl.this.__converters.fromContentUriString(query.getString(i23))));
                        columnIndexOrThrow2 = i6;
                        columnIndexOrThrow = i3;
                        columnIndexOrThrow14 = i5;
                    }
                    LiveRequestDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return arrayList;
                } finally {
                    LiveRequestDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public LiveData<RequestSummary> getRequestSummaryForState(UploadState uploadState) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = ?", 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"upload_request"}, false, new Callable<RequestSummary>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.5
            protected void finalize() {
                acquire.release();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public RequestSummary mo4452call() throws Exception {
                RequestSummary requestSummary = null;
                Cursor query = DBUtil.query(LiveRequestDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "count");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalSize");
                    if (query.moveToFirst()) {
                        requestSummary = new RequestSummary(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2));
                    }
                    return requestSummary;
                } finally {
                    query.close();
                }
            }
        });
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public DataSource.Factory<Integer, UploadRequest> getRequestsForStatesDataSource(Set<String> set, Set<? extends UploadState> set2) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM upload_request WHERE state IN(");
        int size = set2.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") AND queue IN(");
        int size2 = set.size();
        StringUtil.appendPlaceholders(outline114, size2);
        outline114.append(") ORDER BY id DESC");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0 + size2);
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
        return new DataSource.Factory<Integer, UploadRequest>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.12
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, UploadRequest> create() {
                return new LimitOffsetDataSource<UploadRequest>(LiveRequestDao_Impl.this.__db, acquire, true, "upload_request") { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.12.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<UploadRequest> convertRows(Cursor cursor) {
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_path");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "upload_path");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "content_date");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, SierraContentProviderContract.MD5_VALUE);
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "visual_digest");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "suppress_duplication");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "rename_on_name_conflict");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "upload_category");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "state");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "queue");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "current_progress");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "max_progress");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "error_code");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "error_category");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "blocker");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "total_attempt_count");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "attempt_count");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursor2, "max_attempts_exceeded");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursor2, "creation_time_millis");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_size");
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursor2, "priority");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursor2, "add_to_family_vault");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursor2, "app_data");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursor2, "parent_id");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursor2, "content_uri");
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            long j = cursor2.getLong(columnIndexOrThrow);
                            String string = cursor2.getString(columnIndexOrThrow2);
                            String string2 = cursor2.getString(columnIndexOrThrow3);
                            String string3 = cursor2.getString(columnIndexOrThrow4);
                            String string4 = cursor2.getString(columnIndexOrThrow5);
                            String string5 = cursor2.getString(columnIndexOrThrow6);
                            boolean z = cursor2.getInt(columnIndexOrThrow7) != 0;
                            boolean z2 = cursor2.getInt(columnIndexOrThrow8) != 0;
                            String string6 = cursor2.getString(columnIndexOrThrow9);
                            int i3 = columnIndexOrThrow;
                            int i4 = columnIndexOrThrow2;
                            UploadState fromUploadStateString = LiveRequestDao_Impl.this.__converters.fromUploadStateString(cursor2.getString(columnIndexOrThrow10));
                            String string7 = cursor2.getString(columnIndexOrThrow11);
                            long j2 = cursor2.getLong(columnIndexOrThrow12);
                            long j3 = cursor2.getLong(columnIndexOrThrow13);
                            String string8 = cursor2.getString(columnIndexOrThrow14);
                            UploadErrorCategory fromUploadErrorCategoryString = LiveRequestDao_Impl.this.__converters.fromUploadErrorCategoryString(cursor2.getString(columnIndexOrThrow15));
                            Blocker fromBlockerString = LiveRequestDao_Impl.this.__converters.fromBlockerString(cursor2.getString(columnIndexOrThrow16));
                            int i5 = cursor2.getInt(columnIndexOrThrow17);
                            int i6 = columnIndexOrThrow18;
                            int i7 = cursor2.getInt(i6);
                            int i8 = columnIndexOrThrow20;
                            boolean z3 = cursor2.getInt(columnIndexOrThrow19) != 0;
                            long j4 = cursor2.getLong(i8);
                            columnIndexOrThrow20 = i8;
                            int i9 = columnIndexOrThrow24;
                            columnIndexOrThrow24 = i9;
                            columnIndexOrThrow18 = i6;
                            arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i5, i7, z3, j4, cursor2.getLong(columnIndexOrThrow21), cursor2.getInt(columnIndexOrThrow22), cursor2.getInt(columnIndexOrThrow23) != 0, cursor2.getString(i9), cursor2.getString(columnIndexOrThrow25), LiveRequestDao_Impl.this.__converters.fromContentUriString(cursor2.getString(columnIndexOrThrow26))));
                            cursor2 = cursor;
                            columnIndexOrThrow = i3;
                            columnIndexOrThrow2 = i4;
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.photos.uploader.internal.dao.LiveRequestDao
    public LiveData<List<UploadRequest>> getRequestsForStatesLiveData(Set<String> set, Set<? extends UploadState> set2) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM upload_request WHERE state IN(");
        int size = set2.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") AND queue IN(");
        int size2 = set.size();
        StringUtil.appendPlaceholders(outline114, size2);
        outline114.append(") ORDER BY id DESC");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0 + size2);
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
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"upload_request"}, true, new Callable<List<UploadRequest>>() { // from class: com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl.13
            protected void finalize() {
                acquire.release();
            }

            @Override // java.util.concurrent.Callable
            public List<UploadRequest> call() throws Exception {
                LiveRequestDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor query = DBUtil.query(LiveRequestDao_Impl.this.__db, acquire, false, null);
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
                        UploadState fromUploadStateString = LiveRequestDao_Impl.this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
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
                        UploadErrorCategory fromUploadErrorCategoryString = LiveRequestDao_Impl.this.__converters.fromUploadErrorCategoryString(query.getString(i8));
                        int i9 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i9;
                        Blocker fromBlockerString = LiveRequestDao_Impl.this.__converters.fromBlockerString(query.getString(i9));
                        int i10 = columnIndexOrThrow17;
                        int i11 = query.getInt(i10);
                        int i12 = columnIndexOrThrow18;
                        int i13 = query.getInt(i12);
                        columnIndexOrThrow17 = i10;
                        int i14 = columnIndexOrThrow19;
                        int i15 = query.getInt(i14);
                        columnIndexOrThrow19 = i14;
                        int i16 = columnIndexOrThrow20;
                        boolean z3 = i15 != 0;
                        long j4 = query.getLong(i16);
                        columnIndexOrThrow20 = i16;
                        int i17 = columnIndexOrThrow21;
                        long j5 = query.getLong(i17);
                        columnIndexOrThrow21 = i17;
                        int i18 = columnIndexOrThrow22;
                        int i19 = query.getInt(i18);
                        columnIndexOrThrow22 = i18;
                        int i20 = columnIndexOrThrow23;
                        int i21 = query.getInt(i20);
                        columnIndexOrThrow23 = i20;
                        int i22 = columnIndexOrThrow24;
                        boolean z4 = i21 != 0;
                        String string9 = query.getString(i22);
                        columnIndexOrThrow24 = i22;
                        int i23 = columnIndexOrThrow25;
                        String string10 = query.getString(i23);
                        columnIndexOrThrow25 = i23;
                        columnIndexOrThrow18 = i12;
                        int i24 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i24;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i11, i13, z3, j4, j5, i19, z4, string9, string10, LiveRequestDao_Impl.this.__converters.fromContentUriString(query.getString(i24))));
                        columnIndexOrThrow2 = i7;
                        columnIndexOrThrow = i4;
                        columnIndexOrThrow14 = i6;
                    }
                    LiveRequestDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return arrayList;
                } finally {
                    LiveRequestDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }
}
