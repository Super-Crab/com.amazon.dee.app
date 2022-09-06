package com.amazon.alexa;

import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.constants.VoiceApp;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: DeviceIOComponentsStateProviderFactory.java */
@Singleton
/* loaded from: classes.dex */
public class Efr {
    public final String BIo;
    public final PackageManager zZm;

    @Inject
    public Efr(@Named("androidId") String str, PackageManager packageManager) {
        this.zZm = packageManager;
        this.BIo = str;
    }

    public PRf zZm(@Nullable DeviceInformation deviceInformation) {
        if (deviceInformation == null) {
            return null;
        }
        VoiceApp voiceApp = deviceInformation.getVoiceApp();
        IKe zZm = IKe.zZm(deviceInformation.getType());
        int i = Ved.zZm[voiceApp.ordinal()];
        if (i == 1) {
            return new PYs(this.BIo, this.zZm, zZm);
        }
        if (i == 2) {
            return new AVk(this.BIo, this.zZm, zZm);
        }
        if (i == 3) {
            return new sDX(this.BIo, this.zZm, zZm);
        }
        return null;
    }
}
