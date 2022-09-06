package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.voice.handsfree.utils.IdentityServiceSignInContract;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes11.dex */
public class ConnectionModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public AlexaAppSignInContract provideAlexaAppSignInContract() {
        return new IdentityServiceSignInContract();
    }
}
