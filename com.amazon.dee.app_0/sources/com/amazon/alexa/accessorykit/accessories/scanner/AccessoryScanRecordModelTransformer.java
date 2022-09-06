package com.amazon.alexa.accessorykit.accessories.scanner;

import com.amazon.alexa.accessory.AccessoryScanRecord;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
/* loaded from: classes6.dex */
public final class AccessoryScanRecordModelTransformer implements MapModelTransformer<AccessoryScanRecord> {
    private static final String COLOR_KEY = "color";
    private static final String HAS_AN_UPDATE_AVAILABLE_KEY = "hasAnUpdateAvailable";
    private static final String IS_DISCOVERABLE_OVER_BT_CLASSIC_KEY = "isDiscoverableOverBTClassic";
    private static final String IS_IN_OOBE_MODE_KEY = "isInOOBEMode";
    private static final String PREFERS_RFCOMM_CONNECTION_KEY = "prefersRFCOMMConnection";
    private static final String PRODUCT_ID_KEY = "productId";
    private static final String VENDOR_ID_KEY = "vendorId";
    private final ContainerProvider containerProvider;

    public AccessoryScanRecordModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public AccessoryScanRecord mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(AccessoryScanRecord accessoryScanRecord) {
        WritableMap map = this.containerProvider.getMap();
        if (accessoryScanRecord == null) {
            return map;
        }
        map.putInt(VENDOR_ID_KEY, accessoryScanRecord.getVendorId());
        map.putInt(PRODUCT_ID_KEY, accessoryScanRecord.getProductId());
        map.putInt("color", accessoryScanRecord.getColor());
        map.putBoolean(IS_IN_OOBE_MODE_KEY, accessoryScanRecord.isInOOBEMode());
        map.putBoolean(IS_DISCOVERABLE_OVER_BT_CLASSIC_KEY, accessoryScanRecord.isDiscoverableOverBTClassic());
        map.putBoolean(PREFERS_RFCOMM_CONNECTION_KEY, accessoryScanRecord.prefersRFCOMMConnection());
        map.putBoolean(HAS_AN_UPDATE_AVAILABLE_KEY, accessoryScanRecord.hasAnUpdateAvailable());
        return map;
    }
}
