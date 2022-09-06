package com.amazon.deecomms.nativemodules.model;

import com.amazon.deecomms.contacts.model.Contact;
/* loaded from: classes12.dex */
public class BridgeContact extends Contact {
    private boolean mCanDropInOnMe;
    private boolean mCanIDropInOnHim;

    @Override // com.amazon.deecomms.contacts.model.Contact
    public int canDropInOnMe() {
        return this.mCanDropInOnMe ? 1 : 0;
    }

    @Override // com.amazon.deecomms.contacts.model.Contact
    public int canIDropInOnHim() {
        return this.mCanIDropInOnHim ? 1 : 0;
    }

    public void setCanDropInOnMe(boolean z) {
        this.mCanDropInOnMe = z;
    }

    public void setCanIDropInOnHim(boolean z) {
        this.mCanIDropInOnHim = z;
    }
}
