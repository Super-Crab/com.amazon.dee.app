package com.amazon.deecomms.calling.initiation;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.core.CapabilitiesManager;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class InitiationLogicFactory {
    @NonNull
    private final CallingFacade callingFacade;
    @NonNull
    private final CapabilitiesManager capabilitiesManager;
    @NonNull
    private final CommsIdentityManager commsIdentityManager;

    @Inject
    public InitiationLogicFactory(@NonNull CapabilitiesManager capabilitiesManager, @NonNull CallingFacade callingFacade, @NonNull CommsIdentityManager commsIdentityManager) {
        this.capabilitiesManager = capabilitiesManager;
        this.callingFacade = callingFacade;
        this.commsIdentityManager = commsIdentityManager;
    }

    public InitiationLogicContract create(CallInitiator callInitiator, @NonNull Context context, Activity activity, CallHelper callHelper, String str, String str2) {
        return new InitiationLogic(context, this.commsIdentityManager, activity, this.callingFacade, callHelper, str, str2);
    }
}
