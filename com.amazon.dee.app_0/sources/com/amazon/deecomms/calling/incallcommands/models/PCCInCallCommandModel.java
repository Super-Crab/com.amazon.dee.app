package com.amazon.deecomms.calling.incallcommands.models;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PCCEventModel;
/* loaded from: classes12.dex */
public class PCCInCallCommandModel extends InCallCommandModel {
    public PCCInCallCommandModel(@NonNull PCCEventModel pCCEventModel, @NonNull PCCContextProvider pCCContextProvider, @NonNull int i) {
        super(pCCEventModel, pCCContextProvider, String.valueOf(i));
    }

    @Override // com.amazon.deecomms.calling.incallcommands.models.InCallCommandModel
    public boolean hasContext() {
        return true;
    }
}
