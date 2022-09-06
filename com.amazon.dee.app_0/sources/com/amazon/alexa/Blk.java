package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.core.messages.Header;
import java.util.Set;
/* compiled from: UnnamedMediaPlayer.java */
/* loaded from: classes.dex */
public interface Blk {
    boolean isAvailable();

    void teardown();

    Set<ComponentState> zZm(vQe vqe, Set<ComponentState> set);

    boolean zZm();

    boolean zZm(Header header);
}
