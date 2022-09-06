package com.amazon.alexa;

import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: PlayerRuntimeStatePersister.java */
@Singleton
/* loaded from: classes.dex */
public class qKe extends YJe<vQe, dEA> {
    @Inject
    public qKe(@Named("player_runtime_state_loader") Lazy<uTP> lazy) {
        super(dEA.class, new sQf(), lazy);
    }
}
