package com.amazon.alexa.accessorykit.accessories.scanner;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryScanRecord;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessoryclient.common.api.AccessoryScanResult;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.accessorykit.accessories.AccessoryModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public final class AccessoryScanResultModelTransformer implements MapModelTransformer<AccessoryScanResult> {
    private final MapModelTransformer<AccessoryScanRecord> accessoryScanRecordTransformer;
    private final MapModelTransformer<Accessory> accessoryTransformer;
    private final ContainerProvider containerProvider;

    public AccessoryScanResultModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
        this.accessoryTransformer = new AccessoryModelTransformer(containerProvider);
        this.accessoryScanRecordTransformer = new AccessoryScanRecordModelTransformer(containerProvider);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public AccessoryScanResult mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not yet implemented", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(AccessoryScanResult accessoryScanResult) throws NotSerializableException {
        Preconditions.notNull(accessoryScanResult, "accessoryScanResult");
        WritableMap map = this.containerProvider.getMap();
        map.putMap(ModelTransformer.KEY_ACCESSORY, this.accessoryTransformer.transformToMap(accessoryScanResult.getAccessory()));
        map.putMap("record", this.accessoryScanRecordTransformer.transformToMap(accessoryScanResult.getAccessoryScanRecord()));
        map.putInt("rssi", accessoryScanResult.getRssi());
        return map;
    }
}
