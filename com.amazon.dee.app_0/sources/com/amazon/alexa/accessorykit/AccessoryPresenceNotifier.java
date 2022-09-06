package com.amazon.alexa.accessorykit;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Supplier;
import com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes6.dex */
public final class AccessoryPresenceNotifier implements ConnectedAccessoryInquirer {
    private static final String TAG = "AccessoryPresenceNotifier:";
    private DeviceSupplierV2 clientDeviceSupplier;
    private SessionSupplier clientSessionSupplier;
    private volatile List<String> connectedDeviceTypes;

    public AccessoryPresenceNotifier(ComponentGetter componentGetter, final Context context) {
        this(new Supplier() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryPresenceNotifier$JwVg3GbQ7tVnYRIlbYe1AzMm5OI
            @Override // androidx.core.util.Supplier
            public final Object get() {
                Accessories accessories;
                accessories = Accessories.Shared.INSTANCE.get(context);
                return accessories;
            }
        });
    }

    private static String getPrimaryDeviceType(DeviceGroup deviceGroup) {
        Preconditions.precondition(!deviceGroup.getDevices().isEmpty(), "deviceGroup.getDevices() cannot be empty");
        return ((Device) Collections.max(deviceGroup.getDevices(), $$Lambda$AccessoryPresenceNotifier$8zelqArHmLxVyvNJCKTDG2mbOhk.INSTANCE)).getType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Maybe<DeviceGroup> getSetupDevice(Accessory accessory) {
        return this.clientDeviceSupplier.getDeviceGroup(accessory.getAddress()).toMaybe().onErrorResumeWith(Maybe.empty()).flatMap($$Lambda$AccessoryPresenceNotifier$srg9V2NYOCa5lPdfgAk6gU1_nvw.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getPrimaryDeviceType$1(Device device, Device device2) {
        return device.getDeviceId().intValue() - device2.getDeviceId().intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$getSetupDevice$2(DeviceGroup deviceGroup) throws Throwable {
        return deviceGroup.getDevices().isEmpty() ? Maybe.empty() : Maybe.just(deviceGroup);
    }

    static /* synthetic */ Iterable lambda$updateConnectedDeviceTypes$7(List list) throws Throwable {
        return list;
    }

    private void observerClientDevices() {
        this.clientDeviceSupplier.queryDeviceGroups().skip(1L).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryPresenceNotifier$j4wStdcTRXKAmkLJDXihLrbGooo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryPresenceNotifier.this.lambda$observerClientDevices$3$AccessoryPresenceNotifier((List) obj);
            }
        }, $$Lambda$AccessoryPresenceNotifier$icSnMjNS_GUKW_293AsiYwczjm0.INSTANCE);
    }

    private void observerClientSessions() {
        Observable.merge(this.clientSessionSupplier.observeSessionConnected(), this.clientSessionSupplier.observeSessionReleased()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryPresenceNotifier$EWCVlpP7d-DOp6KMCrhx6boKwBc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryPresenceNotifier.this.lambda$observerClientSessions$5$AccessoryPresenceNotifier((Accessory) obj);
            }
        }, $$Lambda$AccessoryPresenceNotifier$093xzUQWDS4uFZ5aBGAoPtIV25I.INSTANCE);
    }

    private void updateConnectedDeviceTypes() {
        final ArrayList arrayList = new ArrayList();
        this.clientSessionSupplier.getActiveAccessories().flattenAsObservable($$Lambda$AccessoryPresenceNotifier$6pfM2sBF3JJCDGmwzFxIh7rcvo.INSTANCE).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryPresenceNotifier$2x5ntNmYAF_CnfHryuvKpykLyzg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Maybe setupDevice;
                setupDevice = AccessoryPresenceNotifier.this.getSetupDevice((Accessory) obj);
                return setupDevice;
            }
        }).toList().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryPresenceNotifier$77P4csN3sqfSV35ZiMZVF54O2Jo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryPresenceNotifier.this.lambda$updateConnectedDeviceTypes$8$AccessoryPresenceNotifier(arrayList, (List) obj);
            }
        }, $$Lambda$AccessoryPresenceNotifier$BQtAApyhR5uxEshQARC6XDyC9Kg.INSTANCE);
    }

    @Override // com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer
    public List<String> getConnectedAccessories() {
        List<String> list = this.connectedDeviceTypes;
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
            sb.append('\t');
        }
        Object[] objArr = new Object[2];
        objArr[0] = TAG;
        objArr[1] = list.isEmpty() ? "none" : sb.toString();
        Logger.d("%s getConnectedAccessories called, returning: %s", objArr);
        return list;
    }

    public /* synthetic */ void lambda$observerClientDevices$3$AccessoryPresenceNotifier(List list) throws Throwable {
        updateConnectedDeviceTypes();
    }

    public /* synthetic */ void lambda$observerClientSessions$5$AccessoryPresenceNotifier(Accessory accessory) throws Throwable {
        updateConnectedDeviceTypes();
    }

    public /* synthetic */ void lambda$updateConnectedDeviceTypes$8$AccessoryPresenceNotifier(List list, List list2) throws Throwable {
        StringBuilder sb = new StringBuilder();
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            String primaryDeviceType = getPrimaryDeviceType((DeviceGroup) it2.next());
            sb.append(primaryDeviceType);
            sb.append('\t');
            list.add(primaryDeviceType);
        }
        this.connectedDeviceTypes = Collections.unmodifiableList(list);
        Object[] objArr = new Object[1];
        objArr[0] = list.isEmpty() ? "none" : sb.toString();
        Logger.d("AccessoryPresenceNotifier:Determined connected device types(accessory client flow): %s", objArr);
    }

    @VisibleForTesting
    AccessoryPresenceNotifier(Supplier<Accessories> supplier) {
        Preconditions.mainThread();
        this.connectedDeviceTypes = new ArrayList();
        try {
            Accessories accessories = supplier.get();
            this.clientSessionSupplier = accessories.getSessionSupplier();
            this.clientDeviceSupplier = accessories.getDeviceSupplier();
            observerClientDevices();
            observerClientSessions();
            updateConnectedDeviceTypes();
        } catch (Exception e) {
            Logger.e("Handled exception in AccessoryPresenceNotifier", e);
        }
        Logger.d("%s Initialized...", TAG);
    }
}
