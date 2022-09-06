package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_call_vid_strm_op {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_NO_OP = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_NO_OP");
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_ADD = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_ADD");
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_REMOVE = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_REMOVE");
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_CHANGE_DIR = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_CHANGE_DIR");
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV");
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_START_TRANSMIT = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_START_TRANSMIT");
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_STOP_TRANSMIT = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_STOP_TRANSMIT");
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_SEND_KEYFRAME = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_SEND_KEYFRAME");
    private static pjsua_call_vid_strm_op[] swigValues = {PJSUA_CALL_VID_STRM_NO_OP, PJSUA_CALL_VID_STRM_ADD, PJSUA_CALL_VID_STRM_REMOVE, PJSUA_CALL_VID_STRM_CHANGE_DIR, PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV, PJSUA_CALL_VID_STRM_START_TRANSMIT, PJSUA_CALL_VID_STRM_STOP_TRANSMIT, PJSUA_CALL_VID_STRM_SEND_KEYFRAME};
    private static int swigNext = 0;

    private pjsua_call_vid_strm_op(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_call_vid_strm_op swigToEnum(int i) {
        pjsua_call_vid_strm_op[] pjsua_call_vid_strm_opVarArr = swigValues;
        if (i >= pjsua_call_vid_strm_opVarArr.length || i < 0 || pjsua_call_vid_strm_opVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_call_vid_strm_op[] pjsua_call_vid_strm_opVarArr2 = swigValues;
                if (i2 < pjsua_call_vid_strm_opVarArr2.length) {
                    if (pjsua_call_vid_strm_opVarArr2[i2].swigValue == i) {
                        return pjsua_call_vid_strm_opVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_call_vid_strm_op.class, " with value ", i));
                }
            }
        } else {
            return pjsua_call_vid_strm_opVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_call_vid_strm_op(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_call_vid_strm_op(String str, pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar) {
        this.swigName = str;
        this.swigValue = pjsua_call_vid_strm_opVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
