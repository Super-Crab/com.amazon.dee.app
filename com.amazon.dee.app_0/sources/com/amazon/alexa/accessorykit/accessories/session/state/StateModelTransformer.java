package com.amazon.alexa.accessorykit.accessories.session.state;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
/* loaded from: classes6.dex */
public final class StateModelTransformer implements MapModelTransformer<StateOuterClass.State> {
    private static final String FEATURE_KEY = "feature";
    private static final String VALUE_KEY = "value";
    private static final int VALUE_TYPE_BOOLEAN = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final String VALUE_TYPE_KEY = "valueType";
    private static final int VALUE_TYPE_NULL = 2;
    private final ContainerProvider containerProvider;

    public StateModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public StateOuterClass.State mo626transform(ReadableMap readableMap) throws ParseException {
        StateOuterClass.State.Builder newBuilder = StateOuterClass.State.newBuilder();
        try {
            newBuilder.setFeature(readableMap.getInt("feature"));
            int i = readableMap.getInt(VALUE_TYPE_KEY);
            if (i == 0) {
                newBuilder.setBoolean(readableMap.getBoolean("value"));
            } else if (i == 1) {
                newBuilder.setInteger(readableMap.getInt("value"));
            }
            return newBuilder.mo10084build();
        } catch (Exception e) {
            throw new ParseException(e.toString(), 0);
        }
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(StateOuterClass.State state) {
        WritableMap map = this.containerProvider.getMap();
        map.putInt("feature", state.getFeature());
        if (StateOuterClass.State.ValueCase.BOOLEAN.equals(state.getValueCase())) {
            map.putBoolean("value", state.getBoolean());
            map.putInt(VALUE_TYPE_KEY, 0);
        } else if (StateOuterClass.State.ValueCase.INTEGER.equals(state.getValueCase())) {
            map.putInt("value", state.getInteger());
            map.putInt(VALUE_TYPE_KEY, 1);
        } else {
            map.putInt(VALUE_TYPE_KEY, 2);
        }
        return map;
    }
}
