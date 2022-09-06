package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: IOComponentsStateMerger.java */
@Singleton
/* loaded from: classes.dex */
public class ClS extends pna {
    public static final String jiA = "ClS";

    @Inject
    public ClS(Gson gson) {
        super(gson, AvsApiConstants.Alexa.IOComponents.zZm, AvsApiConstants.Alexa.IOComponents.ComponentStates.IOComponentStates.zZm);
    }

    @Override // com.amazon.alexa.pna
    @Nullable
    public ComponentState BIo(@NonNull Set<ComponentState> set) {
        Agi agi;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (ComponentState componentState : set) {
            ComponentStatePayload payload = componentState.getPayload();
            if (payload instanceof RawStringPayload) {
                try {
                    agi = (Agi) this.BIo.fromJson(((RawStringPayload) payload).getValue(), (Class<Object>) Agi.class);
                } catch (JsonSyntaxException | NullPointerException e) {
                    Log.w(jiA, "Failed to deserialize IOComponent", e);
                    return null;
                }
            } else if (payload instanceof Agi) {
                agi = (Agi) payload;
            } else {
                String str = jiA;
                StringBuilder zZm = C0179Pya.zZm("The type of ComponentStatePayload for the IOComponentStates was unexpected: ");
                zZm.append(payload.getClass().getSimpleName());
                Log.e(str, zZm.toString());
                return null;
            }
            JdP jdP = (JdP) agi;
            for (tWv twv : jdP.zZm) {
                linkedHashSet.add(twv);
            }
            for (tWv twv2 : jdP.BIo) {
                linkedHashSet2.add(twv2);
            }
        }
        return ComponentState.create(ComponentStateHeader.zZm(this.zQM, this.zyO), Agi.zZm(linkedHashSet, linkedHashSet2));
    }
}
