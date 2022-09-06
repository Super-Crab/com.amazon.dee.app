package com.amazon.alexa.accessorykit.accessories.session.input;

import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class InputBehaviorConfigurationSetTransformer implements MapModelTransformer<Input.InputBehaviorConfigurationSet> {
    private static final String CONFIGURATIONS_KEY = "configurations";
    private final ContainerProvider containerProvider;
    private final MapModelTransformer<Input.InputBehaviorConfiguration> inputBehaviorConfigurationTransformer;

    public InputBehaviorConfigurationSetTransformer(ContainerProvider containerProvider) {
        this.containerProvider = containerProvider;
        this.inputBehaviorConfigurationTransformer = new InputBehaviorConfigurationTransformer(containerProvider);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public Input.InputBehaviorConfigurationSet mo626transform(ReadableMap readableMap) throws ParseException {
        ReadableArray array = readableMap.getArray(CONFIGURATIONS_KEY);
        Input.InputBehaviorConfigurationSet.Builder newBuilder = Input.InputBehaviorConfigurationSet.newBuilder();
        for (int i = 0; i < array.size(); i++) {
            newBuilder.addConfigurations(this.inputBehaviorConfigurationTransformer.mo626transform(array.mo6944getMap(i)));
        }
        return newBuilder.mo10084build();
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet) throws NotSerializableException {
        WritableArray array = this.containerProvider.getArray();
        for (Input.InputBehaviorConfiguration inputBehaviorConfiguration : inputBehaviorConfigurationSet.getConfigurationsList()) {
            array.pushMap(this.inputBehaviorConfigurationTransformer.transformToMap(inputBehaviorConfiguration));
        }
        WritableMap map = this.containerProvider.getMap();
        map.putArray(CONFIGURATIONS_KEY, array);
        return map;
    }
}
