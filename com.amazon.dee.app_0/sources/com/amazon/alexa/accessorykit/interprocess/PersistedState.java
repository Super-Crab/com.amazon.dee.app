package com.amazon.alexa.accessorykit.interprocess;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class PersistedState implements JsonObjectSerializable {
    private static final String BOOLEAN = "boolean";
    private static final String FEATURE = "feature";
    private static final String INTEGER = "integer";
    private static final String IS_BOOLEAN = "isBoolean";
    public final StateOuterClass.State state;

    /* loaded from: classes6.dex */
    static class Factory implements JsonObjectSerializable.Factory<PersistedState> {
        public static final Factory INSTANCE = new Factory();

        Factory() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public PersistedState mo1239create(JSONObject jSONObject) throws JSONException {
            StateOuterClass.State.Builder feature = StateOuterClass.State.newBuilder().setFeature(jSONObject.getInt("feature"));
            if (jSONObject.getBoolean(PersistedState.IS_BOOLEAN)) {
                return new PersistedState(feature.setBoolean(jSONObject.getBoolean(PersistedState.BOOLEAN)).mo10084build());
            }
            return new PersistedState(feature.setInteger(jSONObject.getInt(PersistedState.INTEGER)).mo10084build());
        }
    }

    public PersistedState(StateOuterClass.State state) {
        Preconditions.notNull(state, "state");
        this.state = state;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && PersistedState.class == obj.getClass()) {
            return Objects.equals(Integer.valueOf(this.state.getFeature()), Integer.valueOf(((PersistedState) obj).state.getFeature()));
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.state.getFeature()));
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(IS_BOOLEAN, this.state.getValueCase() == StateOuterClass.State.ValueCase.BOOLEAN).put(BOOLEAN, this.state.getBoolean()).put(INTEGER, this.state.getInteger()).put("feature", this.state.getFeature());
    }
}
