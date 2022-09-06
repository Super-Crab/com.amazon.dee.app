package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_aud_dev_route {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_DEFAULT = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_DEFAULT", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_DEFAULT_get());
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER_get());
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_EARPIECE = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_EARPIECE", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_EARPIECE_get());
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH_get());
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_CUSTOM = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_CUSTOM", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_CUSTOM_get());
    private static pjmedia_aud_dev_route[] swigValues = {PJMEDIA_AUD_DEV_ROUTE_DEFAULT, PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER, PJMEDIA_AUD_DEV_ROUTE_EARPIECE, PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH, PJMEDIA_AUD_DEV_ROUTE_CUSTOM};
    private static int swigNext = 0;

    private pjmedia_aud_dev_route(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_aud_dev_route swigToEnum(int i) {
        pjmedia_aud_dev_route[] pjmedia_aud_dev_routeVarArr = swigValues;
        if (i >= pjmedia_aud_dev_routeVarArr.length || i < 0 || pjmedia_aud_dev_routeVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_aud_dev_route[] pjmedia_aud_dev_routeVarArr2 = swigValues;
                if (i2 < pjmedia_aud_dev_routeVarArr2.length) {
                    if (pjmedia_aud_dev_routeVarArr2[i2].swigValue == i) {
                        return pjmedia_aud_dev_routeVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_aud_dev_route.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_aud_dev_routeVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_aud_dev_route(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_aud_dev_route(String str, pjmedia_aud_dev_route pjmedia_aud_dev_routeVar) {
        this.swigName = str;
        this.swigValue = pjmedia_aud_dev_routeVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
