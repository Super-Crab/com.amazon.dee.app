package com.amazon.alexa.capabilities;

import android.content.Context;
import com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent;
import dagger.Module;
import dagger.Provides;
import java.util.Collections;
import java.util.Set;
import javax.inject.Named;
@Module
/* loaded from: classes6.dex */
public class FireOsCapabilityAgentsModule {
    public static final String FIRE_OS_TABLET_CAPABILITIES = "fire_os_tablet_capabilities";

    @Provides
    @Named(FIRE_OS_TABLET_CAPABILITIES)
    public static Set<CapabilityAgent> providesFireOsCapabilityAgents(Context context) {
        return Collections.emptySet();
    }
}
