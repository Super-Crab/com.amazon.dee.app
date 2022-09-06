package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_vid_dev_cap {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_FORMAT = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_FORMAT", pjsua2JNI.PJMEDIA_VID_DEV_CAP_FORMAT_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_INPUT_SCALE = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_INPUT_SCALE", pjsua2JNI.PJMEDIA_VID_DEV_CAP_INPUT_SCALE_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_RESIZE = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_RESIZE", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_RESIZE_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_POSITION = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_POSITION", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_POSITION_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_HIDE = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_HIDE", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_HIDE_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_INPUT_PREVIEW = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_INPUT_PREVIEW", pjsua2JNI.PJMEDIA_VID_DEV_CAP_INPUT_PREVIEW_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_ORIENTATION = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_ORIENTATION", pjsua2JNI.PJMEDIA_VID_DEV_CAP_ORIENTATION_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_SWITCH = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_SWITCH", pjsua2JNI.PJMEDIA_VID_DEV_CAP_SWITCH_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_FLAGS = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_FLAGS", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_FLAGS_get());
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_MAX = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_MAX", pjsua2JNI.PJMEDIA_VID_DEV_CAP_MAX_get());
    private static pjmedia_vid_dev_cap[] swigValues = {PJMEDIA_VID_DEV_CAP_FORMAT, PJMEDIA_VID_DEV_CAP_INPUT_SCALE, PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW, PJMEDIA_VID_DEV_CAP_OUTPUT_RESIZE, PJMEDIA_VID_DEV_CAP_OUTPUT_POSITION, PJMEDIA_VID_DEV_CAP_OUTPUT_HIDE, PJMEDIA_VID_DEV_CAP_INPUT_PREVIEW, PJMEDIA_VID_DEV_CAP_ORIENTATION, PJMEDIA_VID_DEV_CAP_SWITCH, PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_FLAGS, PJMEDIA_VID_DEV_CAP_MAX};
    private static int swigNext = 0;

    private pjmedia_vid_dev_cap(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_vid_dev_cap swigToEnum(int i) {
        pjmedia_vid_dev_cap[] pjmedia_vid_dev_capVarArr = swigValues;
        if (i >= pjmedia_vid_dev_capVarArr.length || i < 0 || pjmedia_vid_dev_capVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_vid_dev_cap[] pjmedia_vid_dev_capVarArr2 = swigValues;
                if (i2 < pjmedia_vid_dev_capVarArr2.length) {
                    if (pjmedia_vid_dev_capVarArr2[i2].swigValue == i) {
                        return pjmedia_vid_dev_capVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_vid_dev_cap.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_vid_dev_capVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_vid_dev_cap(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_vid_dev_cap(String str, pjmedia_vid_dev_cap pjmedia_vid_dev_capVar) {
        this.swigName = str;
        this.swigValue = pjmedia_vid_dev_capVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
