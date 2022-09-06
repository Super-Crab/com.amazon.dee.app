package com.amazon.alexa.accessorykit.registraton;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformerHelper;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
/* loaded from: classes6.dex */
public final class RegistrationSupplierModule {
    private final Accessories clientAccessories;
    private final ArrayModelTransformer<List<DeviceRegistration>> deviceRegistrationListTransformer;
    private final MapModelTransformer<DeviceRegistration> deviceRegistrationTransformer;
    private final RxRN rxRN;

    public RegistrationSupplierModule(ContainerProvider containerProvider, RxRN rxRN, Accessories accessories) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(rxRN, "rxRN");
        Preconditions.notNull(accessories, "clientAccessories");
        this.clientAccessories = accessories;
        this.deviceRegistrationTransformer = new DeviceRegistrationTransformer(containerProvider);
        this.deviceRegistrationListTransformer = new ArrayModelTransformerHelper(containerProvider, this.deviceRegistrationTransformer);
        this.rxRN = rxRN;
    }

    public void getDeviceRegistration(String str, final Promise promise) {
        Single<DeviceRegistration> deviceRegistration = this.clientAccessories.getRegistrationSupplier().getDeviceRegistration(str);
        final MapModelTransformer<DeviceRegistration> mapModelTransformer = this.deviceRegistrationTransformer;
        mapModelTransformer.getClass();
        Single<R> map = deviceRegistration.map(new Function() { // from class: com.amazon.alexa.accessorykit.registraton.-$$Lambda$kHD9XLstNpA5U07BfE6mdWa1e0A
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MapModelTransformer.this.transformToMap((DeviceRegistration) obj);
            }
        });
        promise.getClass();
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.registraton.-$$Lambda$FlWipKiXyqlqsdu4VNbVtXrTjl0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((WritableMap) obj);
            }
        };
        promise.getClass();
        map.subscribe(consumer, new Consumer() { // from class: com.amazon.alexa.accessorykit.registraton.-$$Lambda$uy64BVvzETLGSEBSP5-7B5ZCU4o
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.reject((Throwable) obj);
            }
        });
    }

    public void queryRegistrations(ReadableMap readableMap) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), (ArrayModelTransformer) this.deviceRegistrationListTransformer, (Observable) this.clientAccessories.getRegistrationSupplier().queryRegistrations().map($$Lambda$iFzK94Fgy9zI4rjkLObgso6xdo.INSTANCE));
    }
}
