package com.amazon.alexa;

import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AudioLatencyTimestampRepository.java */
@Singleton
/* loaded from: classes.dex */
public class vrF {
    public final TimeProvider BIo;
    public final AlexaHandsFreeDeviceInformation zQM;
    public final Nyy zZm;
    public long zyO;

    @Inject
    public vrF(Nyy nyy, TimeProvider timeProvider, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation) {
        this.zZm = nyy;
        this.BIo = timeProvider;
        this.zQM = alexaHandsFreeDeviceInformation;
    }
}
