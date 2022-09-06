package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_vid_packing {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_vid_packing PJMEDIA_VID_PACKING_UNKNOWN = new pjmedia_vid_packing("PJMEDIA_VID_PACKING_UNKNOWN");
    public static final pjmedia_vid_packing PJMEDIA_VID_PACKING_PACKETS = new pjmedia_vid_packing("PJMEDIA_VID_PACKING_PACKETS", pjsua2JNI.PJMEDIA_VID_PACKING_PACKETS_get());
    public static final pjmedia_vid_packing PJMEDIA_VID_PACKING_WHOLE = new pjmedia_vid_packing("PJMEDIA_VID_PACKING_WHOLE", pjsua2JNI.PJMEDIA_VID_PACKING_WHOLE_get());
    private static pjmedia_vid_packing[] swigValues = {PJMEDIA_VID_PACKING_UNKNOWN, PJMEDIA_VID_PACKING_PACKETS, PJMEDIA_VID_PACKING_WHOLE};
    private static int swigNext = 0;

    private pjmedia_vid_packing(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_vid_packing swigToEnum(int i) {
        pjmedia_vid_packing[] pjmedia_vid_packingVarArr = swigValues;
        if (i >= pjmedia_vid_packingVarArr.length || i < 0 || pjmedia_vid_packingVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_vid_packing[] pjmedia_vid_packingVarArr2 = swigValues;
                if (i2 < pjmedia_vid_packingVarArr2.length) {
                    if (pjmedia_vid_packingVarArr2[i2].swigValue == i) {
                        return pjmedia_vid_packingVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_vid_packing.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_vid_packingVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_vid_packing(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_vid_packing(String str, pjmedia_vid_packing pjmedia_vid_packingVar) {
        this.swigName = str;
        this.swigValue = pjmedia_vid_packingVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
