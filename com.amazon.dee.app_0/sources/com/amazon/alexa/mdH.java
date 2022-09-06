package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.core.configuration.Feature;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AudioFormatConvertingAttachmentFactoryImpl.java */
@Singleton
/* loaded from: classes.dex */
public class mdH implements Vyl {
    public final C0228ryy zZm;

    @Inject
    public mdH(C0228ryy c0228ryy) {
        this.zZm = c0228ryy;
    }

    public Aml zZm(Aml aml) {
        C0228ryy c0228ryy = this.zZm;
        boolean z = false;
        if (!c0228ryy.zQM.zZm(Feature.ALEXA_VOX_ANDROID_OPUS)) {
            Log.i(C0228ryy.zZm, "Opus WebLab not dialed-up. Opus feature is disabled");
        } else {
            Log.i(C0228ryy.zZm, "Opus WebLab dialed-up");
            if (c0228ryy.zyO.isCurrentDeviceHandsFree()) {
                Log.i(C0228ryy.zZm, "The current device is AMPD device");
                if (!c0228ryy.zyO.isCurrentDeviceMTK() && c0228ryy.zyO.isCurrentDeviceTrueTurnkeyAudio()) {
                    Log.i(C0228ryy.zZm, "The current device is QC TT");
                } else {
                    Log.i(C0228ryy.zZm, "The current device is MTK / Amok / Amok Lite");
                    if (!c0228ryy.zQM.zZm(Feature.ALEXA_VOX_ANDROID_OPUS_MTK)) {
                        Log.i(C0228ryy.zZm, "MTK/Amok/AmokLite WebLab not dialed-up. Opus feature is disabled");
                    } else {
                        Log.i(C0228ryy.zZm, "MTK/Amok/AmokLite WebLab dialed-up");
                    }
                }
            }
            if (c0228ryy.BIo.zZm()) {
                Log.i(C0228ryy.zZm, "Device is charging. Opus feature is enabled");
            } else if (c0228ryy.BIo.BIo()) {
                Log.i(C0228ryy.zZm, "Battery level is low. Opus feature is disabled");
            } else {
                Log.i(C0228ryy.zZm, "Battery level isn't low. Opus feature is enabled");
            }
            z = true;
        }
        if (z) {
            if (wul.BIo.equals(aml.getDataFormat())) {
                return new GLA(aml);
            }
        }
        return aml;
    }
}
