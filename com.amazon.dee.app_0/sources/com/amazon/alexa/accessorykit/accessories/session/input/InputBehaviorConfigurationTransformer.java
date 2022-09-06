package com.amazon.alexa.accessorykit.accessories.session.input;

import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class InputBehaviorConfigurationTransformer implements MapModelTransformer<Input.InputBehaviorConfiguration> {
    private static final String ACTION_KEY = "action";
    private static final String BEHAVIOR_KEY = "behavior";
    private static final String SOURCE_KEY = "source";
    private final ContainerProvider containerProvider;

    public InputBehaviorConfigurationTransformer(ContainerProvider containerProvider) {
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public Input.InputBehaviorConfiguration mo626transform(ReadableMap readableMap) throws ParseException {
        try {
            return Input.InputBehaviorConfiguration.newBuilder().setAction(Input.InputAction.valueOf(readableMap.getString("action"))).setBehavior(Input.InputBehavior.valueOf(readableMap.getString("behavior"))).setSource(Input.InputSource.valueOf(readableMap.getString("source"))).mo10084build();
        } catch (Exception e) {
            throw new ParseException(e.toString(), 0);
        }
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(Input.InputBehaviorConfiguration inputBehaviorConfiguration) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putString("action", inputBehaviorConfiguration.getAction().name());
        map.putString("behavior", inputBehaviorConfiguration.getBehavior().name());
        map.putString("source", inputBehaviorConfiguration.getSource().name());
        return map;
    }
}
