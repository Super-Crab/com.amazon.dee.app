package com.google.android.gms.common.stats;

import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
@KeepForSdk
/* loaded from: classes2.dex */
public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    /* loaded from: classes2.dex */
    public interface Types {
        @KeepForSdk
        public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
        @KeepForSdk
        public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
    }

    public abstract int getEventType();

    public abstract long getTimeMillis();

    public String toString() {
        long timeMillis = getTimeMillis();
        int eventType = getEventType();
        long zzu = zzu();
        String zzv = zzv();
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport1.outline6(zzv, 53));
        sb.append(timeMillis);
        sb.append(DeviceDatabaseUtils.DELIMITER);
        sb.append(eventType);
        sb.append(DeviceDatabaseUtils.DELIMITER);
        sb.append(zzu);
        sb.append(zzv);
        return sb.toString();
    }

    public abstract long zzu();

    public abstract String zzv();
}
