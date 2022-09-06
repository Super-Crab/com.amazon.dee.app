package com.amazon.CoralAndroidClient.Model;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class StructureValue implements Value {
    private Map<String, Value> mMap = new HashMap();

    public void clear() {
        this.mMap.clear();
    }

    public Value get(String str) {
        return this.mMap.get(str);
    }

    public Set<String> keySet() {
        return this.mMap.keySet();
    }

    public void put(String str, Value value) {
        this.mMap.put(str, value);
    }

    public void remove(String str) {
        this.mMap.remove(str);
    }

    public int size() {
        return this.mMap.size();
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, Value> entry : this.mMap.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue().toJsonInternal());
        }
        return jSONObject;
    }

    public JSONObject toJsonObject() throws NativeException {
        try {
            return (JSONObject) toJsonInternal();
        } catch (JSONException e) {
            throw new NativeException("invalid JSON object", e);
        }
    }
}
