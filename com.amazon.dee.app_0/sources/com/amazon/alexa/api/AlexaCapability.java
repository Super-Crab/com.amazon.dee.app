package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class AlexaCapability implements Parcelable {
    static final String DEFAULT_CAPABILITY_TYPE = "AlexaInterface";
    private final String capabilityInterface;
    private final String capabilityType;
    @Nullable
    private final JSONObject configurations;
    private final String version;
    private static final String TAG = AlexaCapability.class.getSimpleName();
    public static final Parcelable.Creator<AlexaCapability> CREATOR = new Parcelable.Creator<AlexaCapability>() { // from class: com.amazon.alexa.api.AlexaCapability.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaCapability mo774createFromParcel(Parcel parcel) {
            return new AlexaCapability(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaCapability[] mo775newArray(int i) {
            return new AlexaCapability[i];
        }
    };

    protected AlexaCapability(Parcel parcel) {
        JSONObject jSONObject;
        this.capabilityType = parcel.readString();
        this.capabilityInterface = parcel.readString();
        this.version = parcel.readString();
        String readString = parcel.readString();
        if (readString != null) {
            try {
                jSONObject = new JSONObject(readString);
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse Configurations", e);
            }
            this.configurations = jSONObject;
        }
        jSONObject = null;
        this.configurations = jSONObject;
    }

    private AlexaCapability(String str, String str2, String str3, @Nullable JSONObject jSONObject) {
        Preconditions.notNull(str, "Null CapabilityType");
        Preconditions.notNull(str2, "Null CapabilityInterface");
        Preconditions.notNull(str3, "Null Version");
        this.capabilityType = str;
        this.capabilityInterface = str2;
        this.version = str3;
        this.configurations = jSONObject;
    }

    public static AlexaCapability create(String str, String str2) {
        return createWithConfigurations(str, str2, null);
    }

    public static AlexaCapability create(String str, String str2, String str3) {
        return create(str, str2, str3, null);
    }

    public static AlexaCapability create(String str, String str2, String str3, @Nullable JSONObject jSONObject) {
        return new AlexaCapability(str, str2, str3, jSONObject);
    }

    public static AlexaCapability createWithConfigurations(String str, String str2, @Nullable JSONObject jSONObject) {
        return create(DEFAULT_CAPABILITY_TYPE, str, str2, jSONObject);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public JSONObject getConfigurations() {
        return this.configurations;
    }

    public String getInterface() {
        return this.capabilityInterface;
    }

    public String getType() {
        return this.capabilityType;
    }

    public String getVersion() {
        return this.version;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.capabilityType);
        parcel.writeString(this.capabilityInterface);
        parcel.writeString(this.version);
        JSONObject jSONObject = this.configurations;
        parcel.writeString(jSONObject == null ? null : jSONObject.toString());
    }
}
