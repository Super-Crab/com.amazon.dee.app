package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessorykit.AlexaDeviceManufacturerSupplier;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: TrustedStatesStateMerger.java */
@Singleton
/* loaded from: classes.dex */
public class ZCC extends pna {
    public static final List<String> Qle = Collections.unmodifiableList(Arrays.asList("A303PJF6ISQ7IC", AlexaDeviceManufacturerSupplier.M, "A3IYPH06PH1HRA", "A16MZVIFVHX6P6", AlexaDeviceManufacturerSupplier.HK, "A15QWUTQ6FSMYX", AlexaDeviceManufacturerSupplier.WEAR_APP, "A13W6HQIHKEN3Z", "ADXK6Q246T9EA", AlexaDeviceManufacturerSupplier.Z));
    public static final String jiA = "ZCC";

    @Inject
    public ZCC(Gson gson) {
        super(gson, AvsApiConstants.Alexa.IOComponents.zZm, AvsApiConstants.Alexa.IOComponents.ComponentStates.TrustedStates.zZm);
    }

    @Override // com.amazon.alexa.pna
    @Nullable
    public ComponentState BIo(@NonNull Set<ComponentState> set) {
        pUe pue;
        String str = jiA;
        StringBuilder zZm = C0179Pya.zZm("size of TrustedStates to merge: ");
        zZm.append(set.size());
        Log.i(str, zZm.toString());
        if (set.size() > 2) {
            Log.e(jiA, "more than 2 TrustedStates component state found.");
        }
        ComponentState componentState = null;
        for (ComponentState componentState2 : set) {
            ComponentStatePayload payload = componentState2.getPayload();
            if (payload instanceof RawStringPayload) {
                try {
                    pue = (pUe) this.BIo.fromJson(((RawStringPayload) payload).getValue(), (Class<Object>) pUe.class);
                } catch (JsonSyntaxException | NullPointerException unused) {
                    Log.w(jiA, "failed to deserialize TrustedState.");
                }
            } else if (payload instanceof pUe) {
                pue = (pUe) payload;
            } else {
                Log.e(jiA, "Not a TrustedState component payload. Should never happen.");
                pue = null;
            }
            if (pue != null && componentState == null) {
                componentState = componentState2;
            }
            if (pue != null) {
                BcN bcN = (BcN) pue;
                if (bcN.zyO.size() > 0 && Qle.contains(((CMx) bcN.zyO.get(0)).zyO)) {
                    Log.i(jiA, "merge result: use the first one with AMA sessionStates.");
                    return componentState2;
                }
            }
        }
        Log.i(jiA, "merge result: use the first valid one or null.");
        return componentState;
    }
}
