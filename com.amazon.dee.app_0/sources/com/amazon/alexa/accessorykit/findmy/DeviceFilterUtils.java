package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessorykit.AlexaDeviceManufacturerSupplier;
import io.reactivex.rxjava3.functions.BiPredicate;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public final class DeviceFilterUtils {
    private static final List<String> UNSUPPORTED_DEVICE_TYPES = Arrays.asList("AE9FIEPOC6D9B", "A16MZVIFVHX6P6", "A3HVREY4JWAZ6K", "A15QWUTQ6FSMYX", "A21YKVRGQV9TET", "ADXK6Q246T9EA");
    static final BiPredicate<List<Device.DeviceInformation>, List<Device.DeviceInformation>> BT_STATUS_FILTER = $$Lambda$DeviceFilterUtils$4mqqQe04fchyDkquGJOZnWY_o.INSTANCE;
    static final BiPredicate<List<Device.DeviceInformation>, List<Device.DeviceInformation>> DEVICE_PRESENCE_STATUS_FILTER = $$Lambda$DeviceFilterUtils$jmv26bLnVtB81pRWP_WEeHhYd0.INSTANCE;
    static final BiPredicate<List<Device.DeviceInformation>, List<Device.DeviceInformation>> BATTERY_STATUS_FILTER = $$Lambda$DeviceFilterUtils$mgwc6rfbGTw7poLe2gBvLcZfFM.INSTANCE;
    static final Predicate<Device.DeviceInformation> SUPPORTED_DEVICE_FILTER = $$Lambda$DeviceFilterUtils$BxQ_Swb95eTDdhIuDYecdmb3ci8.INSTANCE;

    private DeviceFilterUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$0(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$1(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$3(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$4(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$6(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$7(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$2(List list, List list2) throws Throwable {
        if (list.size() != 0 && list2.size() != 0 && list.size() == list2.size()) {
            Collections.sort(list, $$Lambda$DeviceFilterUtils$Tz9o9lH3TjpEY1wwoDAGIjl4eGA.INSTANCE);
            Collections.sort(list2, $$Lambda$DeviceFilterUtils$UXoEARHmKwIjTiT54FullBOOaI.INSTANCE);
            for (int i = 0; i < list.size(); i++) {
                if (((Device.DeviceInformation) list.get(i)).getStatus().getLink() != ((Device.DeviceInformation) list2.get(i)).getStatus().getLink()) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$5(List list, List list2) throws Throwable {
        if (list.size() != 0 && list2.size() != 0 && list.size() == list2.size()) {
            Collections.sort(list, $$Lambda$DeviceFilterUtils$3TwrvnMr6u1vNLO3cuWRWj4mGw.INSTANCE);
            Collections.sort(list2, $$Lambda$DeviceFilterUtils$FvSNYQl5iHCj4rVVW_yg9GK3trQ.INSTANCE);
            for (int i = 0; i < list.size(); i++) {
                if (((Device.DeviceInformation) list.get(i)).getStatus().getPresence() != ((Device.DeviceInformation) list2.get(i)).getStatus().getPresence()) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$8(List list, List list2) throws Throwable {
        if (list.size() != 0 && list2.size() != 0 && list.size() == list2.size()) {
            Collections.sort(list, $$Lambda$DeviceFilterUtils$SlrCByjglGqtnmHX6mcM6Xijjss.INSTANCE);
            Collections.sort(list2, $$Lambda$DeviceFilterUtils$lqO6PfJgbWfnx5kDradJlmdKnk.INSTANCE);
            for (int i = 0; i < list.size(); i++) {
                if (((Device.DeviceInformation) list.get(i)).getBattery().getLevel() != ((Device.DeviceInformation) list2.get(i)).getBattery().getLevel() && ((Device.DeviceInformation) list2.get(i)).getBattery().getLevel() <= 10) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$9(Device.DeviceInformation deviceInformation) throws Throwable {
        return AlexaDeviceManufacturerSupplier.isFirstPartyDevice(deviceInformation.getDeviceType()) && !UNSUPPORTED_DEVICE_TYPES.contains(deviceInformation.getDeviceType());
    }
}
