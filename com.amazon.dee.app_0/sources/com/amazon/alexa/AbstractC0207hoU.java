package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_Connection;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_DeviceInfo;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_IOComponent;
import com.amazon.alexa.tWv;
import com.amazon.alexa.urO;
import java.util.Collections;
import java.util.Set;
/* compiled from: DeviceIOComponentsStateProvider.java */
/* renamed from: com.amazon.alexa.hoU  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0207hoU implements PRf {
    public final String BIo;
    public final pGm zQM;
    public final IKe zZm;
    public tWv zyO;

    public AbstractC0207hoU(String str, IKe iKe, String str2) {
        this.zQM = pGm.zZm(str);
        this.zZm = iKe;
        this.BIo = str2;
    }

    public abstract cMY BIo();

    public final tWv zZm() {
        if (this.zyO == null) {
            this.zyO = new AutoValue_IOComponent(tWv.zZm.MICROPHONE, new AutoValue_Connection(urO.zZm.MICROPHONE), new AutoValue_DeviceInfo(this.zZm, this.zQM, BIo()), null, null);
        }
        return this.zyO;
    }

    public Set<tWv> zZm(@Nullable ExtendedClient extendedClient, @Nullable AlexaUserSpeechProviderScope alexaUserSpeechProviderScope) {
        if (extendedClient != null) {
            if ((!"com.amazon.dee.app".equals(this.BIo) && extendedClient.getPackageName().equals(this.BIo)) || AlexaUserSpeechProviderScope.SYSTEM.equals(alexaUserSpeechProviderScope)) {
                return Collections.singleton(zZm());
            }
        }
        return Collections.emptySet();
    }
}
