package com.amazon.alexa.accessory.avsclient;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.avsclient.context.SingleBluetoothProfileWatcher;
import com.amazon.alexa.accessory.avsclient.sco.AlexaClientScoPrioritizer;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class IfAnyScoPrioritizer implements ScoPrioritizer {
    private final List<ScoPrioritizer> scoPrioritizerList;

    public IfAnyScoPrioritizer(ComponentGetter componentGetter, Context context) {
        AlexaClientScoPrioritizer alexaClientScoPrioritizer;
        Logger.d("Creating IfAnyScoPrioritizer");
        PhoneStateScoPrioritizer phoneStateScoPrioritizer = new PhoneStateScoPrioritizer(context);
        try {
            alexaClientScoPrioritizer = new AlexaClientScoPrioritizer(Accessories.Shared.INSTANCE.get(context).getSessionSupplier(), new SingleBluetoothProfileWatcher(context), context);
        } catch (Exception e) {
            Logger.e("Handled exception in AlexaClientScoPrioritizer", e);
            alexaClientScoPrioritizer = null;
        }
        this.scoPrioritizerList = Arrays.asList(phoneStateScoPrioritizer, alexaClientScoPrioritizer);
    }

    @VisibleForTesting
    List<ScoPrioritizer> getScoPrioritizerList() {
        return Collections.unmodifiableList(this.scoPrioritizerList);
    }

    @Override // com.amazon.alexa.accessory.sco.ScoPrioritizer
    public boolean shouldUseSco() {
        boolean z;
        Iterator<ScoPrioritizer> it2 = this.scoPrioritizerList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            ScoPrioritizer next = it2.next();
            if (next != null && next.shouldUseSco()) {
                z = true;
                break;
            }
        }
        Logger.d("IfAnyScoPrioritizer: Should use sco is [" + z + "]");
        return z;
    }

    public IfAnyScoPrioritizer(ScoPrioritizer... scoPrioritizerArr) {
        if (scoPrioritizerArr.length != 0) {
            this.scoPrioritizerList = Arrays.asList(scoPrioritizerArr);
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("ScoPrioritizersList must not be empty");
        Logger.e("Throwing ", illegalArgumentException);
        throw illegalArgumentException;
    }
}
