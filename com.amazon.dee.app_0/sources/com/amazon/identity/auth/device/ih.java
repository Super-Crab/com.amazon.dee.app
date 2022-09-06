package com.amazon.identity.auth.device;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ih {
    public static int compare(Integer num, Integer num2) {
        if (num == null) {
            return num2 != null ? -1 : 0;
        } else if (num2 != null) {
            return Integer.signum(num.intValue() - num2.intValue());
        } else {
            return 1;
        }
    }
}
