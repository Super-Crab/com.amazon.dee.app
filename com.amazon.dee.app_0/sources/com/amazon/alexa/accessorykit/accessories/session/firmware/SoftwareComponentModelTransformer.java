package com.amazon.alexa.accessorykit.accessories.session.firmware;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.SoftwareComponent;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class SoftwareComponentModelTransformer implements MapModelTransformer<SoftwareComponent> {
    @VisibleForTesting
    static final String JSON_SOFTWARE_COMPONENT_ID = "softwareComponentId";
    @VisibleForTesting
    static final String JSON_SOFTWARE_COMPONENT_TYPE = "softwareComponentType";
    private final ContainerProvider containerProvider;

    public SoftwareComponentModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public SoftwareComponent mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(SoftwareComponent softwareComponent) {
        WritableMap map = this.containerProvider.getMap();
        map.putString(JSON_SOFTWARE_COMPONENT_ID, softwareComponent.getSoftwareComponentId());
        map.putString(JSON_SOFTWARE_COMPONENT_TYPE, softwareComponent.getSoftwareComponentType());
        return map;
    }
}
