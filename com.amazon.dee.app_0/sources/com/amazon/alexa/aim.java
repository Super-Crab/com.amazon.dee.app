package com.amazon.alexa;

import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: PlayerStructurePersister.java */
@Singleton
/* loaded from: classes.dex */
public class aim extends YJe<vQe, Kyp> {
    @Inject
    public aim(@Named("playback_state_loader") Lazy<uTP> lazy) {
        super(Kyp.class, new sQf(), lazy);
    }
}
