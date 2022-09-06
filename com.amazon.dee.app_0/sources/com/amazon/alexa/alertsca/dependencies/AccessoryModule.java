package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class AccessoryModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public ConnectedAccessoryInquirer providesConnectedAccessoryInquirer() {
        return (ConnectedAccessoryInquirer) GeneratedOutlineSupport1.outline20(ConnectedAccessoryInquirer.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public ScoPrioritizer providesScoPrioritizer() {
        return (ScoPrioritizer) GeneratedOutlineSupport1.outline20(ScoPrioritizer.class);
    }
}
