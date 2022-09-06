package com.amazon.CoralAndroidClient.Model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes.dex */
public class ListValue implements Value {
    private List<Value> mChildren = new ArrayList();

    public void add(Value value) {
        if (value == null) {
            value = new NullValue();
        }
        this.mChildren.add(value);
    }

    public void clear() {
        this.mChildren.clear();
    }

    public Value get(int i) {
        if (i >= size()) {
            return null;
        }
        return this.mChildren.get(i);
    }

    public void remove(int i) {
        if (i >= size()) {
            return;
        }
        this.mChildren.remove(i);
    }

    public int size() {
        return this.mChildren.size();
    }

    public JSONArray toJsonArray() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Value value : this.mChildren) {
            if (value != null) {
                jSONArray.put(value.toJsonInternal());
            }
        }
        return jSONArray;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() throws JSONException {
        return toJsonArray();
    }
}
