package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_snd_dev_mode {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_snd_dev_mode PJSUA_SND_DEV_SPEAKER_ONLY = new pjsua_snd_dev_mode("PJSUA_SND_DEV_SPEAKER_ONLY", pjsua2JNI.PJSUA_SND_DEV_SPEAKER_ONLY_get());
    public static final pjsua_snd_dev_mode PJSUA_SND_DEV_NO_IMMEDIATE_OPEN = new pjsua_snd_dev_mode("PJSUA_SND_DEV_NO_IMMEDIATE_OPEN", pjsua2JNI.PJSUA_SND_DEV_NO_IMMEDIATE_OPEN_get());
    private static pjsua_snd_dev_mode[] swigValues = {PJSUA_SND_DEV_SPEAKER_ONLY, PJSUA_SND_DEV_NO_IMMEDIATE_OPEN};
    private static int swigNext = 0;

    private pjsua_snd_dev_mode(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_snd_dev_mode swigToEnum(int i) {
        pjsua_snd_dev_mode[] pjsua_snd_dev_modeVarArr = swigValues;
        if (i >= pjsua_snd_dev_modeVarArr.length || i < 0 || pjsua_snd_dev_modeVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_snd_dev_mode[] pjsua_snd_dev_modeVarArr2 = swigValues;
                if (i2 < pjsua_snd_dev_modeVarArr2.length) {
                    if (pjsua_snd_dev_modeVarArr2[i2].swigValue == i) {
                        return pjsua_snd_dev_modeVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_snd_dev_mode.class, " with value ", i));
                }
            }
        } else {
            return pjsua_snd_dev_modeVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_snd_dev_mode(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_snd_dev_mode(String str, pjsua_snd_dev_mode pjsua_snd_dev_modeVar) {
        this.swigName = str;
        this.swigValue = pjsua_snd_dev_modeVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
