package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_vid_dev_std_index {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_vid_dev_std_index PJMEDIA_VID_DEFAULT_CAPTURE_DEV = new pjmedia_vid_dev_std_index("PJMEDIA_VID_DEFAULT_CAPTURE_DEV", pjsua2JNI.PJMEDIA_VID_DEFAULT_CAPTURE_DEV_get());
    public static final pjmedia_vid_dev_std_index PJMEDIA_VID_DEFAULT_RENDER_DEV = new pjmedia_vid_dev_std_index("PJMEDIA_VID_DEFAULT_RENDER_DEV", pjsua2JNI.PJMEDIA_VID_DEFAULT_RENDER_DEV_get());
    public static final pjmedia_vid_dev_std_index PJMEDIA_VID_INVALID_DEV = new pjmedia_vid_dev_std_index("PJMEDIA_VID_INVALID_DEV", pjsua2JNI.PJMEDIA_VID_INVALID_DEV_get());
    private static pjmedia_vid_dev_std_index[] swigValues = {PJMEDIA_VID_DEFAULT_CAPTURE_DEV, PJMEDIA_VID_DEFAULT_RENDER_DEV, PJMEDIA_VID_INVALID_DEV};
    private static int swigNext = 0;

    private pjmedia_vid_dev_std_index(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_vid_dev_std_index swigToEnum(int i) {
        pjmedia_vid_dev_std_index[] pjmedia_vid_dev_std_indexVarArr = swigValues;
        if (i >= pjmedia_vid_dev_std_indexVarArr.length || i < 0 || pjmedia_vid_dev_std_indexVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_vid_dev_std_index[] pjmedia_vid_dev_std_indexVarArr2 = swigValues;
                if (i2 < pjmedia_vid_dev_std_indexVarArr2.length) {
                    if (pjmedia_vid_dev_std_indexVarArr2[i2].swigValue == i) {
                        return pjmedia_vid_dev_std_indexVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_vid_dev_std_index.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_vid_dev_std_indexVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_vid_dev_std_index(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_vid_dev_std_index(String str, pjmedia_vid_dev_std_index pjmedia_vid_dev_std_indexVar) {
        this.swigName = str;
        this.swigValue = pjmedia_vid_dev_std_indexVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
