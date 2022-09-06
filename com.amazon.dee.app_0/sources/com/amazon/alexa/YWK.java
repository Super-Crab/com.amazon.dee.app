package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.core.messages.Header;
import java.util.Set;
/* compiled from: NoOpUnnamedPlayer.java */
/* loaded from: classes.dex */
public class YWK implements Blk {
    @Override // com.amazon.alexa.Blk
    public boolean isAvailable() {
        return false;
    }

    @Override // com.amazon.alexa.Blk
    public void teardown() {
    }

    @Override // com.amazon.alexa.Blk
    public Set<ComponentState> zZm(vQe vqe, Set<ComponentState> set) {
        return set;
    }

    @Override // com.amazon.alexa.Blk
    public boolean zZm() {
        return false;
    }

    @Override // com.amazon.alexa.Blk
    public boolean zZm(Header header) {
        return false;
    }
}
