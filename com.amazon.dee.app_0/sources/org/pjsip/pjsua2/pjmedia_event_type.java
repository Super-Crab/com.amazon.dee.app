package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_event_type {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_event_type PJMEDIA_EVENT_NONE = new pjmedia_event_type("PJMEDIA_EVENT_NONE");
    public static final pjmedia_event_type PJMEDIA_EVENT_FMT_CHANGED = new pjmedia_event_type("PJMEDIA_EVENT_FMT_CHANGED", pjsua2JNI.PJMEDIA_EVENT_FMT_CHANGED_get());
    public static final pjmedia_event_type PJMEDIA_EVENT_WND_CLOSING = new pjmedia_event_type("PJMEDIA_EVENT_WND_CLOSING", pjsua2JNI.PJMEDIA_EVENT_WND_CLOSING_get());
    public static final pjmedia_event_type PJMEDIA_EVENT_WND_CLOSED = new pjmedia_event_type("PJMEDIA_EVENT_WND_CLOSED", pjsua2JNI.PJMEDIA_EVENT_WND_CLOSED_get());
    public static final pjmedia_event_type PJMEDIA_EVENT_WND_RESIZED = new pjmedia_event_type("PJMEDIA_EVENT_WND_RESIZED", pjsua2JNI.PJMEDIA_EVENT_WND_RESIZED_get());
    public static final pjmedia_event_type PJMEDIA_EVENT_MOUSE_BTN_DOWN = new pjmedia_event_type("PJMEDIA_EVENT_MOUSE_BTN_DOWN", pjsua2JNI.PJMEDIA_EVENT_MOUSE_BTN_DOWN_get());
    public static final pjmedia_event_type PJMEDIA_EVENT_KEYFRAME_FOUND = new pjmedia_event_type("PJMEDIA_EVENT_KEYFRAME_FOUND", pjsua2JNI.PJMEDIA_EVENT_KEYFRAME_FOUND_get());
    public static final pjmedia_event_type PJMEDIA_EVENT_KEYFRAME_MISSING = new pjmedia_event_type("PJMEDIA_EVENT_KEYFRAME_MISSING", pjsua2JNI.PJMEDIA_EVENT_KEYFRAME_MISSING_get());
    public static final pjmedia_event_type PJMEDIA_EVENT_ORIENT_CHANGED = new pjmedia_event_type("PJMEDIA_EVENT_ORIENT_CHANGED", pjsua2JNI.PJMEDIA_EVENT_ORIENT_CHANGED_get());
    private static pjmedia_event_type[] swigValues = {PJMEDIA_EVENT_NONE, PJMEDIA_EVENT_FMT_CHANGED, PJMEDIA_EVENT_WND_CLOSING, PJMEDIA_EVENT_WND_CLOSED, PJMEDIA_EVENT_WND_RESIZED, PJMEDIA_EVENT_MOUSE_BTN_DOWN, PJMEDIA_EVENT_KEYFRAME_FOUND, PJMEDIA_EVENT_KEYFRAME_MISSING, PJMEDIA_EVENT_ORIENT_CHANGED};
    private static int swigNext = 0;

    private pjmedia_event_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_event_type swigToEnum(int i) {
        pjmedia_event_type[] pjmedia_event_typeVarArr = swigValues;
        if (i >= pjmedia_event_typeVarArr.length || i < 0 || pjmedia_event_typeVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_event_type[] pjmedia_event_typeVarArr2 = swigValues;
                if (i2 < pjmedia_event_typeVarArr2.length) {
                    if (pjmedia_event_typeVarArr2[i2].swigValue == i) {
                        return pjmedia_event_typeVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_event_type.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_event_typeVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_event_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_event_type(String str, pjmedia_event_type pjmedia_event_typeVar) {
        this.swigName = str;
        this.swigValue = pjmedia_event_typeVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
