package com.amazon.rtcsc.capabilityagent.common.dependencies;

import com.amazon.rtcsc.capabilityagent.avs.RtcscCapabilityAgentServiceAVS;
import com.amazon.rtcsc.capabilityagent.avs.RtcscContextProvider;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ApplicationModule.class, ExecutorModule.class})
@Singleton
/* loaded from: classes13.dex */
public interface RtcscComponent {
    RtcscContextProvider getRtcscContextProvider();

    void inject(RtcscCapabilityAgentServiceAVS rtcscCapabilityAgentServiceAVS);

    void inject(RtcscContextProvider rtcscContextProvider);
}
