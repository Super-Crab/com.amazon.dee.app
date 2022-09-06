package com.amazon.identity.auth.device.dataobject;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.datastore.ProfileDataSource;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class Profile extends AbstractDataObject {
    public static final String[] ALL_COLUMNS = {"Id", "ExpirationTime", "AppId", "Data"};
    private static final int EXPIRATION_TIME = 3600000;
    private static final String LOG_TAG = "com.amazon.identity.auth.device.dataobject.Profile";
    protected String mAppFamilyId;
    protected String mData;
    protected Date mExpirationTime;

    /* loaded from: classes12.dex */
    public enum COL_INDEX {
        ID(0),
        EXPIRATION_TIME(1),
        APP_ID(2),
        DATA(3);
        
        public final int colId;

        COL_INDEX(int i) {
            this.colId = i;
        }
    }

    public Profile() {
    }

    private boolean dataEquals(Profile profile) {
        try {
            JSONObject jSONObject = new JSONObject(this.mData);
            JSONObject jSONObject2 = new JSONObject(profile.getData());
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (!jSONObject.getString(str).equals(jSONObject2.getString(str))) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return TextUtils.equals(this.mData, profile.getData());
        }
    }

    private Bundle getDataFromJSON() throws AuthError {
        Bundle bundle = new Bundle();
        String str = this.mData;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str2 = (String) keys.next();
                        bundle.putString(str2, jSONObject.getString(str2));
                    }
                } catch (JSONException e) {
                    String str3 = LOG_TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to parse profile data in database ");
                    outline107.append(e.getMessage());
                    MAPLog.e(str3, outline107.toString());
                }
            } catch (JSONException e2) {
                MAPLog.e(LOG_TAG, "JSONException while parsing profile information in database", e2);
                throw new AuthError("JSONException while parsing profile information in database", e2, AuthError.ERROR_TYPE.ERROR_JSON);
            }
        }
        return bundle;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Profile)) {
            try {
                Profile profile = (Profile) obj;
                if (!TextUtils.equals(this.mAppFamilyId, profile.getAppFamilyId()) || !areObjectsEqual(this.mExpirationTime, profile.getExpirationTime())) {
                    return false;
                }
                return dataEquals(profile);
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
        return this.mAppFamilyId;
    }

    public String getData() {
        return this.mData;
    }

    public Bundle getDataAsBundle() throws AuthError {
        return getDataFromJSON();
    }

    public Date getExpirationTime() {
        return this.mExpirationTime;
    }

    public long getId() {
        return getRowId();
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public ContentValues getValuesForInsert() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ALL_COLUMNS[COL_INDEX.APP_ID.colId], this.mAppFamilyId);
        if (this.mExpirationTime != null) {
            contentValues.put(ALL_COLUMNS[COL_INDEX.EXPIRATION_TIME.colId], DatabaseHelper.getDateFormat().format(this.mExpirationTime));
        } else {
            contentValues.put(ALL_COLUMNS[COL_INDEX.EXPIRATION_TIME.colId], (String) null);
        }
        contentValues.put(ALL_COLUMNS[COL_INDEX.DATA.colId], this.mData);
        return contentValues;
    }

    public boolean hasExpired() {
        Date date = this.mExpirationTime;
        if (date != null) {
            return date.before(Calendar.getInstance().getTime());
        }
        return true;
    }

    public void setAppId(String str) {
        this.mAppFamilyId = str;
    }

    public void setData(String str) {
        this.mData = str;
    }

    public void setExpirationTime(Date date) {
        this.mExpirationTime = DatabaseHelper.truncateFractionalSeconds(date);
    }

    public void setId(long j) {
        setRowId(j);
    }

    public String toLogString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("{ rowid=");
        outline107.append(getId());
        outline107.append(", appId=");
        outline107.append(this.mAppFamilyId);
        outline107.append(", expirationTime=");
        outline107.append(DatabaseHelper.getDateFormat().format(this.mExpirationTime));
        outline107.append(", data=");
        return GeneratedOutlineSupport1.outline91(outline107, this.mData, " }");
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public String toString() {
        return toLogString();
    }

    public Profile(String str, String str2) {
        this(str, str2, new Date(Calendar.getInstance().getTime().getTime() + 3600000));
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    /* renamed from: getDataSource  reason: collision with other method in class */
    public ProfileDataSource mo4057getDataSource(Context context) {
        return ProfileDataSource.getInstance(context);
    }

    Profile(String str, String str2, Date date) {
        this.mAppFamilyId = str;
        this.mData = str2;
        this.mExpirationTime = date;
    }
}
