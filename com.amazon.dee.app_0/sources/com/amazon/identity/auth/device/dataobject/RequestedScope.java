package com.amazon.identity.auth.device.dataobject;

import android.content.ContentValues;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.datastore.RequestedScopeDataSource;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class RequestedScope extends AbstractDataObject implements Parcelable {
    private String appFamilyId;
    private String directedId;
    private long mAuthorizationAccessTokenId;
    private long mAuthorizationRefreshTokenId;
    private String scopeValue;
    private static final String LOG_TAG = RequestedScope.class.getName();
    public static final String[] ALL_COLUMNS = {"rowid", DatabaseHelper.requestedScope_Scope, "AppId", "DirectedId", DatabaseHelper.requestedScope_AuthorizationAccessTokenId, DatabaseHelper.requestedScope_AuthorizationRefreshTokenId};
    public static final Parcelable.Creator<RequestedScope> CREATOR = new Parcelable.Creator<RequestedScope>() { // from class: com.amazon.identity.auth.device.dataobject.RequestedScope.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RequestedScope mo4058createFromParcel(Parcel parcel) {
            return new RequestedScope(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RequestedScope[] mo4059newArray(int i) {
            return new RequestedScope[i];
        }
    };

    /* loaded from: classes12.dex */
    public enum COL_INDEX {
        ROW_ID(0),
        SCOPE(1),
        APP_FAMILY_ID(2),
        DIRECTED_ID(3),
        AUTHORIZATION_ACCESS_TOKEN_ID(4),
        AUTHORIZATION_REFRESH_TOKEN_ID(5);
        
        public final int colId;

        COL_INDEX(int i) {
            this.colId = i;
        }
    }

    /* loaded from: classes12.dex */
    public enum OUTCOME {
        UNKNOWN(-2),
        REJECTED(-1),
        GRANTED_LOCALLY(0);
        
        public final long longVal;

        OUTCOME(long j) {
            this.longVal = j;
        }
    }

    public RequestedScope(String str, String str2, String str3) {
        long j = OUTCOME.REJECTED.longVal;
        this.mAuthorizationAccessTokenId = j;
        this.mAuthorizationRefreshTokenId = j;
        this.scopeValue = str;
        this.appFamilyId = str2;
        this.directedId = str3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public boolean equals(Object obj) {
        if (obj instanceof RequestedScope) {
            try {
                RequestedScope requestedScope = (RequestedScope) obj;
                if (!this.scopeValue.equals(requestedScope.getScopeValue()) || !this.appFamilyId.equals(requestedScope.getAppFamilyId()) || !this.directedId.equals(requestedScope.getDirectedId()) || this.mAuthorizationAccessTokenId != requestedScope.getAuthorizationAccessTokenId()) {
                    return false;
                }
                return this.mAuthorizationRefreshTokenId == requestedScope.getAuthorizationRefreshTokenId();
            } catch (NullPointerException e) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
                outline107.append(e.toString());
                MAPLog.e(str, outline107.toString());
            }
        }
        return false;
    }

    public String getAppFamilyId() {
        return this.appFamilyId;
    }

    public long getAuthorizationAccessTokenId() {
        return this.mAuthorizationAccessTokenId;
    }

    public long getAuthorizationRefreshTokenId() {
        return this.mAuthorizationRefreshTokenId;
    }

    public String getDirectedId() {
        return this.directedId;
    }

    public Scope getScope() {
        return new Scope(this.scopeValue);
    }

    public String getScopeValue() {
        return this.scopeValue;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public ContentValues getValuesForInsert() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ALL_COLUMNS[COL_INDEX.SCOPE.colId], this.scopeValue);
        contentValues.put(ALL_COLUMNS[COL_INDEX.APP_FAMILY_ID.colId], this.appFamilyId);
        contentValues.put(ALL_COLUMNS[COL_INDEX.DIRECTED_ID.colId], this.directedId);
        contentValues.put(ALL_COLUMNS[COL_INDEX.AUTHORIZATION_ACCESS_TOKEN_ID.colId], Long.valueOf(this.mAuthorizationAccessTokenId));
        contentValues.put(ALL_COLUMNS[COL_INDEX.AUTHORIZATION_REFRESH_TOKEN_ID.colId], Long.valueOf(this.mAuthorizationRefreshTokenId));
        return contentValues;
    }

    public Boolean isGranted() {
        long j = this.mAuthorizationAccessTokenId;
        if (j == OUTCOME.UNKNOWN.longVal) {
            return null;
        }
        if (j == OUTCOME.REJECTED.longVal) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void setAppFamilyId(String str) {
        this.appFamilyId = str;
    }

    public void setAuthorizationAccessTokenId(long j) {
        this.mAuthorizationAccessTokenId = j;
    }

    public void setAuthorizationRefreshTokenId(long j) {
        this.mAuthorizationRefreshTokenId = j;
    }

    public void setDirectedId(String str) {
        this.directedId = str;
    }

    public void setIsGranted(Boolean bool) {
        if (bool != isGranted()) {
            if (bool == null) {
                setAuthorizationAccessTokenId(OUTCOME.UNKNOWN.longVal);
                setAuthorizationRefreshTokenId(OUTCOME.UNKNOWN.longVal);
            } else if (bool == Boolean.FALSE) {
                setAuthorizationAccessTokenId(OUTCOME.REJECTED.longVal);
                setAuthorizationRefreshTokenId(OUTCOME.REJECTED.longVal);
            } else {
                setAuthorizationAccessTokenId(OUTCOME.GRANTED_LOCALLY.longVal);
                setAuthorizationRefreshTokenId(OUTCOME.GRANTED_LOCALLY.longVal);
            }
        }
    }

    public void setScopeValue(String str) {
        this.scopeValue = str;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("{ rowid=");
        outline107.append(getRowId());
        outline107.append(", scope=");
        outline107.append(this.scopeValue);
        outline107.append(", appFamilyId=");
        outline107.append(this.appFamilyId);
        outline107.append(", directedId=<obscured>, atzAccessTokenId=");
        outline107.append(this.mAuthorizationAccessTokenId);
        outline107.append(", atzRefreshTokenId=");
        return GeneratedOutlineSupport1.outline87(outline107, this.mAuthorizationRefreshTokenId, " }");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(getRowId());
        parcel.writeString(this.scopeValue);
        parcel.writeString(this.appFamilyId);
        parcel.writeString(this.directedId);
        parcel.writeLong(this.mAuthorizationAccessTokenId);
        parcel.writeLong(this.mAuthorizationRefreshTokenId);
    }

    /* renamed from: clone */
    public RequestedScope m4056clone() {
        return new RequestedScope(getRowId(), this.scopeValue, this.appFamilyId, this.directedId, this.mAuthorizationAccessTokenId, this.mAuthorizationRefreshTokenId);
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    /* renamed from: getDataSource  reason: collision with other method in class */
    public RequestedScopeDataSource mo4057getDataSource(Context context) {
        return RequestedScopeDataSource.getInstance(context);
    }

    public RequestedScope(String str, String str2, String str3, long j, long j2) {
        this(str, str2, str3);
        this.mAuthorizationAccessTokenId = j;
        this.mAuthorizationRefreshTokenId = j2;
    }

    private RequestedScope(long j, String str, String str2, String str3, long j2, long j3) {
        this(str, str2, str3, j2, j3);
        setRowId(j);
    }

    public RequestedScope() {
        long j = OUTCOME.REJECTED.longVal;
        this.mAuthorizationAccessTokenId = j;
        this.mAuthorizationRefreshTokenId = j;
    }

    public RequestedScope(Parcel parcel) {
        long j = OUTCOME.REJECTED.longVal;
        this.mAuthorizationAccessTokenId = j;
        this.mAuthorizationRefreshTokenId = j;
        setRowId(parcel.readLong());
        this.scopeValue = parcel.readString();
        this.appFamilyId = parcel.readString();
        this.directedId = parcel.readString();
        this.mAuthorizationAccessTokenId = parcel.readLong();
        this.mAuthorizationRefreshTokenId = parcel.readLong();
    }
}
