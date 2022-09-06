package com.amazon.alexa.accessorykit.focusfilter;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class FocusFilterInfoTransformer implements MapModelTransformer<String> {
    private static final String VALUE = "value";
    private final ContainerProvider containerProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FocusFilterInfoTransformer(ContainerProvider containerProvider) {
        this.containerProvider = containerProvider;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform  reason: collision with other method in class */
    public String mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not yet implemented", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(String str) throws NotSerializableException {
        Preconditions.notNull(str, "value");
        WritableMap map = this.containerProvider.getMap();
        map.putString("value", str);
        return map;
    }
}
