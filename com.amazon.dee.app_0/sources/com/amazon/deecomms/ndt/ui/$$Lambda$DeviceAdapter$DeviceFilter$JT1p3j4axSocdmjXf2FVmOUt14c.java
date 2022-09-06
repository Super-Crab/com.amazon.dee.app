package com.amazon.deecomms.ndt.ui;

import com.amazon.deecomms.ndt.model.DeviceModel;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.ndt.ui.-$$Lambda$DeviceAdapter$DeviceFilter$JT1p3j4axSocdmjXf2FVmOUt14c  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$DeviceAdapter$DeviceFilter$JT1p3j4axSocdmjXf2FVmOUt14c implements Comparator {
    public static final /* synthetic */ $$Lambda$DeviceAdapter$DeviceFilter$JT1p3j4axSocdmjXf2FVmOUt14c INSTANCE = new $$Lambda$DeviceAdapter$DeviceFilter$JT1p3j4axSocdmjXf2FVmOUt14c();

    private /* synthetic */ $$Lambda$DeviceAdapter$DeviceFilter$JT1p3j4axSocdmjXf2FVmOUt14c() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((DeviceModel) obj).getDeviceName().compareTo(((DeviceModel) obj2).getDeviceName());
        return compareTo;
    }
}
