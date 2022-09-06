package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: IOComponentsComponentStateProvider.java */
@Singleton
/* loaded from: classes.dex */
public class pBR extends Bwo {
    public final PRf BIo;
    public ExtendedClient zQM;
    public AlexaUserSpeechProviderScope zyO;

    @Inject
    public pBR(@Nullable PRf pRf) {
        super(AvsApiConstants.Alexa.IOComponents.zZm, AvsApiConstants.Alexa.IOComponents.ComponentStates.IOComponentStates.zZm);
        this.BIo = pRf;
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        Set singleton;
        PRf pRf = this.BIo;
        ExtendedClient extendedClient = null;
        if (pRf == null || (singleton = Collections.singleton(((AbstractC0207hoU) pRf).zZm())) == null || singleton.isEmpty()) {
            return null;
        }
        HashSet hashSet = new HashSet();
        PRf pRf2 = this.BIo;
        ExtendedClient extendedClient2 = this.zQM;
        if (extendedClient2 != null) {
            extendedClient = extendedClient2.getActiveSubClient();
        }
        hashSet.addAll(((AbstractC0207hoU) pRf2).zZm(extendedClient, this.zyO));
        return ComponentState.create(this.zZm, Agi.zZm(hashSet, singleton));
    }
}
