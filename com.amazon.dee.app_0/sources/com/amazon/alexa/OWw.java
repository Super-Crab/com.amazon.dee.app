package com.amazon.alexa;

import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: PlayerStatePersister.java */
@Singleton
/* loaded from: classes.dex */
public class OWw extends YJe<vQe, HkJ> {
    @Inject
    public OWw(@Named("player_state_loader") Lazy<uTP> lazy) {
        super(HkJ.class, new sQf(), lazy);
    }
}
