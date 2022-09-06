package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.protocol.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.session.-$$Lambda$SessionSystemCallback$EECRROvN60IFoAEIl7bNLXlzU3c  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SessionSystemCallback$EECRROvN60IFoAEIl7bNLXlzU3c implements Comparator {
    public static final /* synthetic */ $$Lambda$SessionSystemCallback$EECRROvN60IFoAEIl7bNLXlzU3c INSTANCE = new $$Lambda$SessionSystemCallback$EECRROvN60IFoAEIl7bNLXlzU3c();

    private /* synthetic */ $$Lambda$SessionSystemCallback$EECRROvN60IFoAEIl7bNLXlzU3c() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((Device.DeviceInformation) obj).getDeviceType().compareTo(((Device.DeviceInformation) obj2).getDeviceType());
        return compareTo;
    }
}
