package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_dir {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_dir PJMEDIA_DIR_NONE = new pjmedia_dir("PJMEDIA_DIR_NONE", pjsua2JNI.PJMEDIA_DIR_NONE_get());
    public static final pjmedia_dir PJMEDIA_DIR_ENCODING = new pjmedia_dir("PJMEDIA_DIR_ENCODING", pjsua2JNI.PJMEDIA_DIR_ENCODING_get());
    public static final pjmedia_dir PJMEDIA_DIR_CAPTURE = new pjmedia_dir("PJMEDIA_DIR_CAPTURE", pjsua2JNI.PJMEDIA_DIR_CAPTURE_get());
    public static final pjmedia_dir PJMEDIA_DIR_DECODING = new pjmedia_dir("PJMEDIA_DIR_DECODING", pjsua2JNI.PJMEDIA_DIR_DECODING_get());
    public static final pjmedia_dir PJMEDIA_DIR_PLAYBACK = new pjmedia_dir("PJMEDIA_DIR_PLAYBACK", pjsua2JNI.PJMEDIA_DIR_PLAYBACK_get());
    public static final pjmedia_dir PJMEDIA_DIR_RENDER = new pjmedia_dir("PJMEDIA_DIR_RENDER", pjsua2JNI.PJMEDIA_DIR_RENDER_get());
    public static final pjmedia_dir PJMEDIA_DIR_ENCODING_DECODING = new pjmedia_dir("PJMEDIA_DIR_ENCODING_DECODING", pjsua2JNI.PJMEDIA_DIR_ENCODING_DECODING_get());
    public static final pjmedia_dir PJMEDIA_DIR_CAPTURE_PLAYBACK = new pjmedia_dir("PJMEDIA_DIR_CAPTURE_PLAYBACK", pjsua2JNI.PJMEDIA_DIR_CAPTURE_PLAYBACK_get());
    public static final pjmedia_dir PJMEDIA_DIR_CAPTURE_RENDER = new pjmedia_dir("PJMEDIA_DIR_CAPTURE_RENDER", pjsua2JNI.PJMEDIA_DIR_CAPTURE_RENDER_get());
    private static pjmedia_dir[] swigValues = {PJMEDIA_DIR_NONE, PJMEDIA_DIR_ENCODING, PJMEDIA_DIR_CAPTURE, PJMEDIA_DIR_DECODING, PJMEDIA_DIR_PLAYBACK, PJMEDIA_DIR_RENDER, PJMEDIA_DIR_ENCODING_DECODING, PJMEDIA_DIR_CAPTURE_PLAYBACK, PJMEDIA_DIR_CAPTURE_RENDER};
    private static int swigNext = 0;

    private pjmedia_dir(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_dir swigToEnum(int i) {
        pjmedia_dir[] pjmedia_dirVarArr = swigValues;
        if (i >= pjmedia_dirVarArr.length || i < 0 || pjmedia_dirVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_dir[] pjmedia_dirVarArr2 = swigValues;
                if (i2 < pjmedia_dirVarArr2.length) {
                    if (pjmedia_dirVarArr2[i2].swigValue == i) {
                        return pjmedia_dirVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_dir.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_dirVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_dir(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_dir(String str, pjmedia_dir pjmedia_dirVar) {
        this.swigName = str;
        this.swigValue = pjmedia_dirVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
