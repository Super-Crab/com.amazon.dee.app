package com.amazon.deecomms.calling.controller;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.CallFilters;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.ringservice.dagger.RingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class CallingContentProvider extends ContentProvider {
    @VisibleForTesting
    public static final int URI_CALLS = 1;
    private CallingContentProviderNotifier contentNotifier;
    private final UriMatcher uriMatcher = new UriMatcher(-1);
    private String[] EMPTY_STRING_ARRAY = new String[0];
    private final DeviceCallingService deviceCallingService = RingService.INSTANCE.getDeviceCallingService();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public enum CallsColumn {
        callCount
    }

    private Cursor queryCalls(@NonNull String[] strArr) {
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        ArrayList arrayList = new ArrayList();
        List<Call> calls = this.deviceCallingService.getCalls(CallFilters.DEFAULT);
        for (String str : strArr) {
            if (CallsColumn.callCount.name().equals(str)) {
                arrayList.add(Integer.valueOf(calls.size()));
            }
        }
        if (!arrayList.isEmpty()) {
            matrixCursor.addRow(arrayList.toArray());
        }
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        this.contentNotifier = new CallingContentProviderNotifier(context);
        this.contentNotifier.notifyObservers();
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        if (this.uriMatcher.match(uri) == 1) {
            return CallingProviderContract.getCallsContentType(getContext());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline58("Unknown URI ", uri));
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.uriMatcher.addURI(CallingProviderContract.getAuthorityId(getContext()), CallingProviderContract.CALLS_PATH, 1);
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        if (this.uriMatcher.match(uri) == 1) {
            if (strArr == null) {
                strArr = this.EMPTY_STRING_ARRAY;
            }
            return queryCalls(strArr);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline58("Unknown URI ", uri));
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }
}
