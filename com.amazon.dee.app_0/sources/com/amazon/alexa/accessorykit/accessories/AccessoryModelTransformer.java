package com.amazon.alexa.accessorykit.accessories;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.auth.BuildConfig;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
/* loaded from: classes6.dex */
public final class AccessoryModelTransformer implements MapModelTransformer<Accessory> {
    private static final String ADDRESS_KEY = "address";
    private static final String NAME_KEY = "name";
    private static final String TRANSPORT_KEY = "transport";
    private final ContainerProvider containerProvider;

    public AccessoryModelTransformer(ContainerProvider containerProvider) {
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public Accessory mo626transform(ReadableMap readableMap) throws ParseException {
        Preconditions.notNull(readableMap, BuildConfig.FLAVOR_authtype);
        try {
            return new Accessory(readableMap.getString("address"), AccessoryTransport.Type.valueOf(readableMap.getString("transport")), readableMap.getString("name"));
        } catch (Exception e) {
            throw new ParseException(e.toString(), 0);
        }
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(Accessory accessory) {
        WritableMap map = this.containerProvider.getMap();
        map.putString("address", accessory.getAddress());
        map.putString("transport", accessory.getTransport().name());
        map.putString("name", accessory.getName());
        return map;
    }
}
