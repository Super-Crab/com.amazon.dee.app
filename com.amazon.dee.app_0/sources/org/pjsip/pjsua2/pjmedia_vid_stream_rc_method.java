package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_vid_stream_rc_method {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_vid_stream_rc_method PJMEDIA_VID_STREAM_RC_NONE = new pjmedia_vid_stream_rc_method("PJMEDIA_VID_STREAM_RC_NONE", pjsua2JNI.PJMEDIA_VID_STREAM_RC_NONE_get());
    public static final pjmedia_vid_stream_rc_method PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING = new pjmedia_vid_stream_rc_method("PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING", pjsua2JNI.PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING_get());
    private static pjmedia_vid_stream_rc_method[] swigValues = {PJMEDIA_VID_STREAM_RC_NONE, PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING};
    private static int swigNext = 0;

    private pjmedia_vid_stream_rc_method(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_vid_stream_rc_method swigToEnum(int i) {
        pjmedia_vid_stream_rc_method[] pjmedia_vid_stream_rc_methodVarArr = swigValues;
        if (i >= pjmedia_vid_stream_rc_methodVarArr.length || i < 0 || pjmedia_vid_stream_rc_methodVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_vid_stream_rc_method[] pjmedia_vid_stream_rc_methodVarArr2 = swigValues;
                if (i2 < pjmedia_vid_stream_rc_methodVarArr2.length) {
                    if (pjmedia_vid_stream_rc_methodVarArr2[i2].swigValue == i) {
                        return pjmedia_vid_stream_rc_methodVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_vid_stream_rc_method.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_vid_stream_rc_methodVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_vid_stream_rc_method(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_vid_stream_rc_method(String str, pjmedia_vid_stream_rc_method pjmedia_vid_stream_rc_methodVar) {
        this.swigName = str;
        this.swigValue = pjmedia_vid_stream_rc_methodVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
