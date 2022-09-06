package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_vid_req_keyframe_method {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_vid_req_keyframe_method PJSUA_VID_REQ_KEYFRAME_SIP_INFO = new pjsua_vid_req_keyframe_method("PJSUA_VID_REQ_KEYFRAME_SIP_INFO", pjsua2JNI.PJSUA_VID_REQ_KEYFRAME_SIP_INFO_get());
    public static final pjsua_vid_req_keyframe_method PJSUA_VID_REQ_KEYFRAME_RTCP_PLI = new pjsua_vid_req_keyframe_method("PJSUA_VID_REQ_KEYFRAME_RTCP_PLI", pjsua2JNI.PJSUA_VID_REQ_KEYFRAME_RTCP_PLI_get());
    private static pjsua_vid_req_keyframe_method[] swigValues = {PJSUA_VID_REQ_KEYFRAME_SIP_INFO, PJSUA_VID_REQ_KEYFRAME_RTCP_PLI};
    private static int swigNext = 0;

    private pjsua_vid_req_keyframe_method(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_vid_req_keyframe_method swigToEnum(int i) {
        pjsua_vid_req_keyframe_method[] pjsua_vid_req_keyframe_methodVarArr = swigValues;
        if (i >= pjsua_vid_req_keyframe_methodVarArr.length || i < 0 || pjsua_vid_req_keyframe_methodVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_vid_req_keyframe_method[] pjsua_vid_req_keyframe_methodVarArr2 = swigValues;
                if (i2 < pjsua_vid_req_keyframe_methodVarArr2.length) {
                    if (pjsua_vid_req_keyframe_methodVarArr2[i2].swigValue == i) {
                        return pjsua_vid_req_keyframe_methodVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_vid_req_keyframe_method.class, " with value ", i));
                }
            }
        } else {
            return pjsua_vid_req_keyframe_methodVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_vid_req_keyframe_method(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_vid_req_keyframe_method(String str, pjsua_vid_req_keyframe_method pjsua_vid_req_keyframe_methodVar) {
        this.swigName = str;
        this.swigValue = pjsua_vid_req_keyframe_methodVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
