package amazon.speech.model;

import amazon.speech.model.DeviceContext;
import android.os.Parcel;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class KeyValueDeviceContext extends DeviceContext {
    private Map<String, String> mProperties;

    public KeyValueDeviceContext(String str, String str2) {
        super(str, str2);
        this.mProperties = new HashMap();
    }

    public void addProperties(Map<String, String> map) {
        this.mProperties.putAll(map);
    }

    public void addProperty(String str, String str2) {
        this.mProperties.put(str, str2);
    }

    public void clearProperties() {
        this.mProperties.clear();
    }

    @Override // amazon.speech.model.DeviceContext
    public String getPayload() {
        return new JSONObject((Map) this.mProperties).toString();
    }

    public Map<String, String> getProperties() {
        return this.mProperties;
    }

    public String getProperty(String str) {
        return this.mProperties.get(str);
    }

    @Override // amazon.speech.model.DeviceContext
    public DeviceContext.Type getType() {
        return DeviceContext.Type.KeyValue;
    }

    @Override // amazon.speech.model.DeviceContext
    public int hashCode() {
        int hashCode = super.hashCode();
        Map<String, String> map = this.mProperties;
        return map != null ? (hashCode * 33) + map.hashCode() : hashCode;
    }

    public void removeProperty(String str) {
        if (this.mProperties.containsKey(str)) {
            this.mProperties.remove(str);
        }
    }

    @Override // amazon.speech.model.DeviceContext, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getType().toString());
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mProperties.size());
        for (Map.Entry<String, String> entry : this.mProperties.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
    }

    public KeyValueDeviceContext(String str, String str2, boolean z) {
        super(str, str2, z);
        this.mProperties = new HashMap();
    }

    public KeyValueDeviceContext(Parcel parcel) {
        super(parcel);
        this.mProperties = new HashMap();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.mProperties.put(parcel.readString(), parcel.readString());
        }
    }
}
