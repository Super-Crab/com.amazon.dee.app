package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Singleton;
/* compiled from: ConnectedAccessoriesStatusProvider.java */
@Singleton
/* renamed from: com.amazon.alexa.zoo  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0245zoo {
    public static final Namespace BIo = AvsApiConstants.Alexa.IOComponents.zZm;
    public static final Name zQM = AvsApiConstants.Alexa.IOComponents.ComponentStates.IOComponentStates.zZm;
    public static final String zZm = "zoo";
    public boolean Qle;
    public boolean jiA;
    public final Gson zyO;

    public C0245zoo(Gson gson) {
        this.zyO = gson;
    }

    public synchronized boolean BIo() {
        return this.Qle;
    }

    public synchronized boolean zZm() {
        return this.jiA;
    }

    public synchronized void zZm(Set<ComponentState> set) {
        for (ComponentState componentState : set) {
            ComponentStateHeader header = componentState.getHeader();
            if (BIo.equals(header.BIo()) && zQM.equals(header.zZm())) {
                ComponentStatePayload payload = componentState.getPayload();
                Agi agi = null;
                if (payload instanceof Agi) {
                    agi = (Agi) payload;
                } else if (payload instanceof RawStringPayload) {
                    String value = ((RawStringPayload) payload).getValue();
                    try {
                        agi = (Agi) this.zyO.fromJson(value, (Class<Object>) Agi.class);
                    } catch (JsonSyntaxException | NullPointerException unused) {
                        String str = zZm;
                        Log.w(str, "Failed to get IOComponentStatePayload from: " + value);
                    }
                }
                if (agi == null) {
                    this.jiA = false;
                    this.Qle = false;
                    return;
                }
                this.jiA = true;
                Iterator<tWv> it2 = ((JdP) agi).BIo.iterator();
                while (it2.hasNext()) {
                    if ("A303PJF6ISQ7IC".equals(((MTM) ((LdP) it2.next()).zQM).zZm.getValue())) {
                        this.Qle = true;
                        return;
                    }
                }
                continue;
            }
        }
        this.Qle = false;
    }
}
