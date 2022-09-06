package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.interactions.channels.AutoValue_ChannelState;
import com.google.auto.value.AutoValue;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* compiled from: ChannelState.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class aNh {
    public static aNh zZm(dnp dnpVar) {
        return zZm(dnpVar, 0L, true);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("cs: ");
        hgr hgrVar = (hgr) this;
        zZm.append(hgrVar.zZm);
        zZm.append(ProcessIdUtil.DEFAULT_PROCESSID);
        zZm.append(hgrVar.BIo);
        return zZm.toString();
    }

    public static aNh zZm(dnp dnpVar, long j) {
        return zZm(dnpVar, j, false);
    }

    public static aNh zZm(dnp dnpVar, long j, boolean z) {
        return new AutoValue_ChannelState(dnpVar, j, z);
    }
}
