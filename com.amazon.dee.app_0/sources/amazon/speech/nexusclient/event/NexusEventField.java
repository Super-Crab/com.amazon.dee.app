package amazon.speech.nexusclient.event;

import com.amazon.alexa.auth.BuildConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public abstract class NexusEventField {
    private final String mName;
    private final boolean mNullable;

    /* loaded from: classes.dex */
    public static class ArrayField extends NexusEventField {
        public ArrayField(String str, boolean z) {
            super(str, z);
        }

        @Override // amazon.speech.nexusclient.event.NexusEventField
        public void addToJson(JSONObject jSONObject, Object[] objArr) throws JSONException {
            Object jSONArray;
            if (objArr == null) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(objArr);
            }
            if (isNullable()) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("array", jSONArray);
                jSONObject.put(getName(), jSONObject2);
                return;
            }
            jSONObject.put(getName(), jSONArray);
        }

        @Override // amazon.speech.nexusclient.event.NexusEventField
        String getTypeString() {
            return "array";
        }
    }

    /* loaded from: classes.dex */
    public static class LongField extends NexusEventField {
        public LongField(String str, boolean z) {
            super(str, z);
        }

        @Override // amazon.speech.nexusclient.event.NexusEventField
        public void addToJson(JSONObject jSONObject, long j) throws JSONException {
            jSONObject.put(getName(), j);
        }

        @Override // amazon.speech.nexusclient.event.NexusEventField
        String getTypeString() {
            return "long";
        }
    }

    /* loaded from: classes.dex */
    public static class MapField extends NexusEventField {
        public MapField(String str, boolean z) {
            super(str, z);
        }

        @Override // amazon.speech.nexusclient.event.NexusEventField
        public void addToJson(JSONObject jSONObject, Map<String, String> map) throws JSONException {
            JSONObject jSONObject2 = new JSONObject();
            if (isNullable()) {
                JSONObject jSONObject3 = new JSONObject();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("string", entry.getValue());
                    jSONObject3.put(entry.getKey(), jSONObject4);
                }
                jSONObject2.put(BuildConfig.FLAVOR_authtype, jSONObject3);
            } else {
                for (Map.Entry<String, String> entry2 : map.entrySet()) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("string", entry2.getValue());
                    jSONObject2.put(entry2.getKey(), jSONObject5);
                }
            }
            jSONObject.put(getName(), jSONObject2);
        }

        @Override // amazon.speech.nexusclient.event.NexusEventField
        String getTypeString() {
            return BuildConfig.FLAVOR_authtype;
        }
    }

    /* loaded from: classes.dex */
    public static class StringField extends NexusEventField {
        public StringField(String str, boolean z) {
            super(str, z);
        }

        @Override // amazon.speech.nexusclient.event.NexusEventField
        public void addToJson(JSONObject jSONObject, String str) throws JSONException {
            if (isNullable()) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(getTypeString(), str);
                jSONObject.put(getName(), jSONObject2);
                return;
            }
            jSONObject.put(getName(), str);
        }

        @Override // amazon.speech.nexusclient.event.NexusEventField
        String getTypeString() {
            return "string";
        }
    }

    public NexusEventField(String str, boolean z) {
        if (str != null) {
            this.mName = str;
            this.mNullable = z;
            return;
        }
        throw new IllegalArgumentException("name is null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addNullToJson(JSONObject jSONObject) throws JSONException {
        if (isNullable()) {
            jSONObject.put(getName(), JSONObject.NULL);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("value cannot be null for required key:");
        outline107.append(getName());
        throw new IllegalArgumentException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addToJson(JSONObject jSONObject, String str) throws JSONException {
        throw new IllegalStateException("Not implemented");
    }

    String getName() {
        return this.mName;
    }

    abstract String getTypeString();

    boolean isNullable() {
        return this.mNullable;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NexusEventField (");
        outline107.append(getTypeString());
        outline107.append(") , name: ");
        outline107.append(getName());
        outline107.append(", nullable: ");
        outline107.append(isNullable());
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addToJson(JSONObject jSONObject, long j) throws JSONException {
        throw new IllegalStateException("Not implemented");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addToJson(JSONObject jSONObject, Map<String, String> map) throws JSONException {
        throw new IllegalStateException("Not implemented");
    }

    void addToJson(JSONObject jSONObject, Object[] objArr) throws JSONException {
        throw new IllegalStateException("Not implemented");
    }
}
