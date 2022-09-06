package com.amazon.alexa.accessorykit.accessories.session.supplier;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class SessionFailedEvent {
    final Accessory accessory;
    final Throwable throwable;

    /* loaded from: classes6.dex */
    static class Transformer implements MapModelTransformer<SessionFailedEvent> {
        private static final String ACCESSORY_KEY = "accessory";
        private static final String ERROR_KEY = "error";
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
        public SessionFailedEvent mo626transform(ReadableMap readableMap) throws ParseException {
            throw new ParseException("Not Implemented!", 0);
        }

        @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
        public WritableMap transformToMap(SessionFailedEvent sessionFailedEvent) throws NotSerializableException {
            WritableMap map = this.containerProvider.getMap();
            map.putMap("accessory", this.accessoryTransformer.transformToMap(sessionFailedEvent.accessory));
            map.putString("error", sessionFailedEvent.throwable.getMessage());
            return map;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionFailedEvent(Accessory accessory, Throwable th) {
        this.accessory = accessory;
        this.throwable = th;
    }
}
