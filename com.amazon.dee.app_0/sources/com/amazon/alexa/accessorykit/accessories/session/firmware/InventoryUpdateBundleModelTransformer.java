package com.amazon.alexa.accessorykit.accessories.session.firmware;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.InventoryUpdateBundle;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
import java.util.Set;
/* loaded from: classes6.dex */
public class InventoryUpdateBundleModelTransformer implements ArrayModelTransformer<Set<InventoryUpdateBundle>> {
    @VisibleForTesting
    static final String JSON_INVENTORY_UPDATE = "inventoryUpdate";
    @VisibleForTesting
    static final String JSON_UPDATE_REQUEST = "updateRequest";
    private final ContainerProvider containerProvider;
    private final InventoryUpdateModelTransformer inventoryUpdateModelTransformer;
    private final UpdateRequestModelTransformer updateRequestModelTransformer;

    public InventoryUpdateBundleModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
        this.inventoryUpdateModelTransformer = new InventoryUpdateModelTransformer(containerProvider);
        this.updateRequestModelTransformer = new UpdateRequestModelTransformer(containerProvider);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    /* renamed from: transform  reason: collision with other method in class */
    public Set<InventoryUpdateBundle> mo597transform(ReadableArray readableArray) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    public WritableArray transformToArray(Set<InventoryUpdateBundle> set) {
        WritableArray array = this.containerProvider.getArray();
        for (InventoryUpdateBundle inventoryUpdateBundle : set) {
            WritableMap map = this.containerProvider.getMap();
            map.putMap("inventoryUpdate", this.inventoryUpdateModelTransformer.transformToMap(inventoryUpdateBundle.getInventoryUpdate()));
            map.putMap("updateRequest", this.updateRequestModelTransformer.transformToMap(inventoryUpdateBundle.getUpdateRequest()));
            array.pushMap(map);
        }
        return array;
    }
}
