package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* compiled from: ComponentStateMerger.java */
/* loaded from: classes.dex */
public abstract class pna {
    public static final String zZm = "pna";
    public final Gson BIo;
    public final Namespace zQM;
    public final Name zyO;

    public pna(Gson gson, Namespace namespace, Name name) {
        this.BIo = gson;
        this.zQM = namespace;
        this.zyO = name;
    }

    public abstract ComponentState BIo(@NonNull Set<ComponentState> set);

    public void zZm(Set<ComponentState> set) {
        HashSet<ComponentState> hashSet = new HashSet();
        Iterator<ComponentState> it2 = set.iterator();
        while (true) {
            boolean z = true;
            if (!it2.hasNext()) {
                break;
            }
            ComponentState next = it2.next();
            ComponentStateHeader header = next.getHeader();
            if (header == null || !this.zQM.equals(header.BIo()) || !this.zyO.equals(header.zZm())) {
                z = false;
            }
            if (z) {
                hashSet.add(next);
            }
        }
        if (hashSet.size() > 1) {
            ComponentState next2 = hashSet.iterator().next();
            r0 = "Merging ComponentState: " + next2;
            for (ComponentState componentState : hashSet) {
                set.remove(componentState);
            }
            ComponentState BIo = BIo(hashSet);
            if (BIo != null) {
                set.add(BIo);
                return;
            }
            Log.e(zZm, "Failed to merge ComponentStates: " + next2 + ". The duplicates were all removed.");
        }
    }
}
