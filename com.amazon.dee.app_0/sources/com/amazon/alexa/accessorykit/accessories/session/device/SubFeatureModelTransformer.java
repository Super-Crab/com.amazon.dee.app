package com.amazon.alexa.accessorykit.accessories.session.device;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.device.SubFeature;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class SubFeatureModelTransformer implements MapModelTransformer<SubFeature> {
    private static final String ID_KEY = "id";
    private final ContainerProvider containerProvider;

    public SubFeatureModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public SubFeature mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(SubFeature subFeature) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putInt("id", subFeature.getId());
        return map;
    }
}
