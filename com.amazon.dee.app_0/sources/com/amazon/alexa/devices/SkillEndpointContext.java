package com.amazon.alexa.devices;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class SkillEndpointContext implements Parcelable {
    private String mContext;
    private String mSkillEndpointInstanceId;
    private String mSkillEndpointVersion;
    private static final String TAG = SkillEndpointContext.class.getSimpleName();
    public static final Parcelable.Creator<SkillEndpointContext> CREATOR = new Parcelable.Creator<SkillEndpointContext>() { // from class: com.amazon.alexa.devices.SkillEndpointContext.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public SkillEndpointContext mo1152createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new SkillEndpointContext(parcel.readString(), parcel.readString(), parcel.readString());
            }
            throw new IllegalArgumentException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public SkillEndpointContext[] mo1153newArray(int i) {
            return new SkillEndpointContext[i];
        }
    };

    public SkillEndpointContext(String str, String str2, String str3) {
        this.mSkillEndpointVersion = str;
        this.mSkillEndpointInstanceId = str2;
        this.mContext = str3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getContext() {
        return this.mContext;
    }

    public String getSkillEndpointInstanceId() {
        return this.mSkillEndpointInstanceId;
    }

    public String getSkillEndpointVersion() {
        return this.mSkillEndpointVersion;
    }

    public String toString() {
        try {
            return new JSONObject().put("skillEndpointVersion", this.mSkillEndpointVersion).put("skillEndpointInstanceId", this.mSkillEndpointInstanceId).put("context", this.mContext).toString();
        } catch (JSONException e) {
            Log.e(TAG, "Error in toString ", e);
            return null;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(this.mSkillEndpointVersion);
            parcel.writeString(this.mSkillEndpointInstanceId);
            parcel.writeString(this.mContext);
            return;
        }
        throw new IllegalArgumentException("dest must not be null");
    }
}
