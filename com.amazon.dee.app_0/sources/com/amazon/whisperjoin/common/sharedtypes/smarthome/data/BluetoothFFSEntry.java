package com.amazon.whisperjoin.common.sharedtypes.smarthome.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class BluetoothFFSEntry implements Parcelable {
    private static final String AUTH_MATERIALS_KEY = "authMaterials";
    public static final String BLUETOOTH_FFS_ENTRY_KEY = "bluetoothFFSEntry";
    public static final Parcelable.Creator<BluetoothFFSEntry> CREATOR = new Parcelable.Creator<BluetoothFFSEntry>() { // from class: com.amazon.whisperjoin.common.sharedtypes.smarthome.data.BluetoothFFSEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public BluetoothFFSEntry mo5474createFromParcel(Parcel parcel) {
            return new BluetoothFFSEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public BluetoothFFSEntry[] mo5475newArray(int i) {
            return new BluetoothFFSEntry[i];
        }
    };
    private static final String MAC_ADDRESS_KEY = "macAddress";
    private static final String RSSI_KEY = "rssi";
    private static final String SCAN_RECORD_KEY = "scanRecord";
    private static final String TIME_STAMP_KEY = "timeStamp";
    private List<BluetoothAuthMaterial> mAuthMaterials;

    /* loaded from: classes13.dex */
    public static class BluetoothAuthMaterial implements Parcelable {
        public static final Parcelable.Creator<BluetoothAuthMaterial> CREATOR = new Parcelable.Creator<BluetoothAuthMaterial>() { // from class: com.amazon.whisperjoin.common.sharedtypes.smarthome.data.BluetoothFFSEntry.BluetoothAuthMaterial.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public BluetoothAuthMaterial mo5476createFromParcel(Parcel parcel) {
                return new BluetoothAuthMaterial(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public BluetoothAuthMaterial[] mo5477newArray(int i) {
                return new BluetoothAuthMaterial[i];
            }
        };
        private String mMacAddress;
        private int mRssi;
        private byte[] mScanRecord;
        private long mTimeStamp;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mMacAddress);
            parcel.writeInt(this.mRssi);
            parcel.writeLong(this.mTimeStamp);
            parcel.writeByteArray(this.mScanRecord);
        }

        private BluetoothAuthMaterial(Parcel parcel) {
            this.mMacAddress = parcel.readString();
            this.mRssi = parcel.readInt();
            this.mTimeStamp = parcel.readLong();
            this.mScanRecord = parcel.createByteArray();
        }

        public BluetoothAuthMaterial(String str, int i, long j, byte[] bArr) {
            this.mMacAddress = str;
            this.mRssi = i;
            this.mTimeStamp = j;
            this.mScanRecord = bArr;
        }
    }

    public static String convertToBase64EncodedString(byte[] bArr) {
        return Base64.encodeToString(bArr, 0);
    }

    public static byte[] convertToByteArray(String str) {
        return Base64.decode(str, 0);
    }

    public static BluetoothFFSEntry fromJson(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        if (jSONObject == null) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("bluetoothFFSEntry");
        if (optJSONObject == null || (optJSONArray = optJSONObject.optJSONArray(AUTH_MATERIALS_KEY)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            String optString = jSONObject2.optString(MAC_ADDRESS_KEY);
            int optInt = jSONObject2.optInt(RSSI_KEY);
            long optLong = jSONObject2.optLong(TIME_STAMP_KEY);
            String optString2 = jSONObject2.optString(SCAN_RECORD_KEY);
            if (!isBlank(optString) && !isBlank(optString2)) {
                arrayList.add(new BluetoothAuthMaterial(optString, optInt, optLong, convertToByteArray(optString2)));
            }
        }
        return new BluetoothFFSEntry(arrayList);
    }

    private static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<BluetoothAuthMaterial> getAuthMaterials() {
        return this.mAuthMaterials;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (BluetoothAuthMaterial bluetoothAuthMaterial : this.mAuthMaterials) {
            if (!isBlank(bluetoothAuthMaterial.mMacAddress)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(MAC_ADDRESS_KEY, bluetoothAuthMaterial.mMacAddress);
                jSONObject2.put(RSSI_KEY, bluetoothAuthMaterial.mRssi);
                jSONObject2.put(TIME_STAMP_KEY, bluetoothAuthMaterial.mTimeStamp);
                jSONObject2.put(SCAN_RECORD_KEY, convertToBase64EncodedString(bluetoothAuthMaterial.mScanRecord));
                jSONArray.put(jSONObject2);
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(AUTH_MATERIALS_KEY, jSONArray);
        jSONObject.put("bluetoothFFSEntry", jSONObject3);
        return jSONObject;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mAuthMaterials);
    }

    private BluetoothFFSEntry(Parcel parcel) {
        this.mAuthMaterials = parcel.createTypedArrayList(BluetoothAuthMaterial.CREATOR);
    }

    public BluetoothFFSEntry(List<BluetoothAuthMaterial> list) {
        this.mAuthMaterials = list;
    }
}
