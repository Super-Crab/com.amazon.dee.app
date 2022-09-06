package com.amazon.whisperjoin.common.sharedtypes.smarthome.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class ZigbeeFFSEntry implements Parcelable {
    private static final String AUTH_MATERIALS_KEY = "authMaterials";
    public static final Parcelable.Creator<ZigbeeFFSEntry> CREATOR = new Parcelable.Creator<ZigbeeFFSEntry>() { // from class: com.amazon.whisperjoin.common.sharedtypes.smarthome.data.ZigbeeFFSEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ZigbeeFFSEntry mo5478createFromParcel(Parcel parcel) {
            return new ZigbeeFFSEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ZigbeeFFSEntry[] mo5479newArray(int i) {
            return new ZigbeeFFSEntry[i];
        }
    };
    private static final String INSTALL_CODE_KEY = "installCode";
    private static final String MAC_ADDRESS_KEY = "macAddress";
    public static final String ZIGBEE_FFS_ENTRY_KEY = "zigbeeFFSEntry";
    private List<ZigbeeAuthMaterial> mAuthMaterials;

    /* loaded from: classes13.dex */
    public static class ZigbeeAuthMaterial implements Parcelable {
        public static final Parcelable.Creator<ZigbeeAuthMaterial> CREATOR = new Parcelable.Creator<ZigbeeAuthMaterial>() { // from class: com.amazon.whisperjoin.common.sharedtypes.smarthome.data.ZigbeeFFSEntry.ZigbeeAuthMaterial.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public ZigbeeAuthMaterial mo5480createFromParcel(Parcel parcel) {
                return new ZigbeeAuthMaterial(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public ZigbeeAuthMaterial[] mo5481newArray(int i) {
                return new ZigbeeAuthMaterial[i];
            }
        };
        private String mInstallCode;
        private String mMacAddress;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ZigbeeAuthMaterial)) {
                return false;
            }
            ZigbeeAuthMaterial zigbeeAuthMaterial = (ZigbeeAuthMaterial) obj;
            return Objects.equal(this.mMacAddress, zigbeeAuthMaterial.mMacAddress) && Objects.equal(this.mInstallCode, zigbeeAuthMaterial.mInstallCode);
        }

        public int hashCode() {
            return Objects.hashCode(this.mMacAddress, this.mInstallCode);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("mMacAddress", this.mMacAddress).add("mInstallCode", this.mInstallCode).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mMacAddress);
            parcel.writeString(this.mInstallCode);
        }

        private ZigbeeAuthMaterial(Parcel parcel) {
            this.mMacAddress = parcel.readString();
            this.mInstallCode = parcel.readString();
        }

        public ZigbeeAuthMaterial(String str, String str2) {
            this.mMacAddress = str;
            this.mInstallCode = str2;
        }
    }

    public static ZigbeeFFSEntry fromJson(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        if (jSONObject == null) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("zigbeeFFSEntry");
        if (optJSONObject == null || (optJSONArray = optJSONObject.optJSONArray(AUTH_MATERIALS_KEY)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            arrayList.add(new ZigbeeAuthMaterial(jSONObject2.optString(MAC_ADDRESS_KEY), jSONObject2.optString(INSTALL_CODE_KEY)));
        }
        return new ZigbeeFFSEntry(arrayList);
    }

    private boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZigbeeFFSEntry) {
            return Objects.equal(this.mAuthMaterials, ((ZigbeeFFSEntry) obj).mAuthMaterials);
        }
        return false;
    }

    public List<ZigbeeAuthMaterial> getAuthMaterials() {
        return this.mAuthMaterials;
    }

    public int hashCode() {
        return Objects.hashCode(this.mAuthMaterials);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (ZigbeeAuthMaterial zigbeeAuthMaterial : this.mAuthMaterials) {
            if (!isBlank(zigbeeAuthMaterial.mMacAddress) && !isBlank(zigbeeAuthMaterial.mInstallCode)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(MAC_ADDRESS_KEY, zigbeeAuthMaterial.mMacAddress);
                jSONObject2.put(INSTALL_CODE_KEY, zigbeeAuthMaterial.mInstallCode);
                jSONArray.put(jSONObject2);
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(AUTH_MATERIALS_KEY, jSONArray);
        jSONObject.put("zigbeeFFSEntry", jSONObject3);
        return jSONObject;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mAuthMaterials", this.mAuthMaterials).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mAuthMaterials);
    }

    private ZigbeeFFSEntry(Parcel parcel) {
        this.mAuthMaterials = parcel.createTypedArrayList(ZigbeeAuthMaterial.CREATOR);
    }

    public ZigbeeFFSEntry(List<ZigbeeAuthMaterial> list) {
        this.mAuthMaterials = list;
    }
}
