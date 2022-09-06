package com.amazon.alexa.accessorykit.accessories.session.supplier;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class SessionTransportChangedEvent {
    final Accessory newAccessory;
    final AccessoryTransport.Type newTransport;
    final Accessory oldAccessory;
    final AccessoryTransport.Type oldTransport;

    /* loaded from: classes6.dex */
    static class Transformer implements MapModelTransformer<SessionTransportChangedEvent> {
        private static final String NEW_ACCESSORY_KEY = "newAccessory";
        private static final String NEW_ACCESSORY_TRANSPORT_KEY = "newAccessoryTransport";
        private static final String OLD_ACCESSORY_KEY = "oldAccessory";
        private static final String OLD_ACCESSORY_TRANSPORT_KEY = "oldAccessoryTransport";
        private final MapModelTransformer<Accessory> accessoryTransformer;
        private final ContainerProvider containerProvider;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Transformer(ContainerProvider containerProvider, MapModelTransformer<Accessory> mapModelTransformer) {
            this.containerProvider = containerProvider;
            this.accessoryTransformer = mapModelTransformer;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
        /* renamed from: transform */
        public SessionTransportChangedEvent mo626transform(ReadableMap readableMap) throws ParseException {
            throw new ParseException("Not Implemented!", 0);
        }

        @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
        public WritableMap transformToMap(SessionTransportChangedEvent sessionTransportChangedEvent) throws NotSerializableException {
            WritableMap map = this.containerProvider.getMap();
            map.putMap(OLD_ACCESSORY_KEY, this.accessoryTransformer.transformToMap(sessionTransportChangedEvent.oldAccessory));
            map.putString(OLD_ACCESSORY_TRANSPORT_KEY, sessionTransportChangedEvent.oldTransport.name());
            map.putMap(NEW_ACCESSORY_KEY, this.accessoryTransformer.transformToMap(sessionTransportChangedEvent.newAccessory));
            map.putString(NEW_ACCESSORY_TRANSPORT_KEY, sessionTransportChangedEvent.newTransport.name());
            return map;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionTransportChangedEvent(Accessory accessory, AccessoryTransport.Type type, Accessory accessory2, AccessoryTransport.Type type2) {
        this.oldAccessory = accessory;
        this.oldTransport = type;
        this.newAccessory = accessory2;
        this.newTransport = type2;
    }
}
