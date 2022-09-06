package com.amazon.alexa.accessorykit.accessories.session.firmware;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.InventoryUpdate;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class InventoryUpdateModelTransformer implements MapModelTransformer<InventoryUpdate> {
    @VisibleForTesting
    static final String JSON_BINARY_METADATA = "binaryMetadata";
    @VisibleForTesting
    static final String JSON_DESCRIPTION = "description";
    @VisibleForTesting
    static final String JSON_FORCE = "force";
    @VisibleForTesting
    static final String JSON_MIME_TYPE = "mimeType";
    @VisibleForTesting
    static final String JSON_SOFTWARE_COMPONENT = "softwareComponent";
    @VisibleForTesting
    static final String JSON_TARGET_SOFTWARE_COMPONENT_VERSION_CODE = "targetSoftwareComponentVersionCode";
    @VisibleForTesting
    static final String JSON_URL = "url";
    private final BinaryMetadataModelTransformer binaryMetadataModelTransformer;
    private final ContainerProvider containerProvider;
    private final SoftwareComponentModelTransformer softwareComponentModelTransformer;

    public InventoryUpdateModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
        this.binaryMetadataModelTransformer = new BinaryMetadataModelTransformer(containerProvider);
        this.softwareComponentModelTransformer = new SoftwareComponentModelTransformer(containerProvider);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public InventoryUpdate mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(InventoryUpdate inventoryUpdate) {
        WritableMap map = this.containerProvider.getMap();
        map.putString("description", inventoryUpdate.getDescription());
        map.putBoolean(JSON_FORCE, inventoryUpdate.getForce());
        map.putString(JSON_MIME_TYPE, inventoryUpdate.getMimeType());
        map.putMap(JSON_SOFTWARE_COMPONENT, this.softwareComponentModelTransformer.transformToMap(inventoryUpdate.getSoftwareComponent()));
        map.putMap(JSON_BINARY_METADATA, this.binaryMetadataModelTransformer.transformToMap(inventoryUpdate.getBinaryMetadata()));
        map.putInt(JSON_TARGET_SOFTWARE_COMPONENT_VERSION_CODE, inventoryUpdate.getTargetSoftwareComponentVersionCode());
        map.putString("url", inventoryUpdate.getUrl());
        return map;
    }
}
