package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaUserSpeechProvider;
import com.amazon.alexa.api.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: UserSpeechProviderRegistry.java */
@Singleton
/* loaded from: classes.dex */
public class jdJ {
    public static final String zZm = "jdJ";
    public final AlexaClientEventBus BIo;
    public final Lbc Qle;
    public final Map<UWt, AlexaUserSpeechProvider> jiA;
    public final Shr<UWt> zQM;
    public final Map<AlexaUserSpeechProvider, UWt> zyO;

    @Inject
    public jdJ(AlexaClientEventBus alexaClientEventBus, Lbc lbc) {
        Shr<UWt> shr = new Shr<>();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        this.BIo = alexaClientEventBus;
        this.Qle = lbc;
        this.zQM = shr;
        this.zyO = hashMap;
        this.jiA = hashMap2;
        alexaClientEventBus.zZm(this);
    }

    public synchronized Set<mqw> BIo() {
        return new LinkedHashSet(this.zQM.zZm());
    }

    @Subscribe
    public synchronized void on(xZV xzv) {
        Set<UWt> BIo = this.zQM.BIo(((uyC) xzv).BIo);
        if (!BIo.isEmpty()) {
            for (UWt uWt : BIo) {
                Map<UWt, AlexaUserSpeechProvider> map = this.jiA;
                this.zyO.remove(map.remove(uWt));
            }
            this.BIo.zyO(TxC.BIo());
        }
    }

    public synchronized Lbc zQM() {
        return this.Qle;
    }

    public synchronized void zZm(ExtendedClient extendedClient, AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        UWt uWt = new UWt(extendedClient, alexaUserSpeechProvider, alexaUserSpeechProviderMetadata);
        this.zQM.zZm(extendedClient, uWt);
        this.zyO.put(alexaUserSpeechProvider, uWt);
        this.jiA.put(uWt, alexaUserSpeechProvider);
        this.BIo.zyO(TxC.BIo());
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Registered UserSpeechProvider for ");
        zZm2.append(extendedClient.getId());
        zZm2.append(", provider ");
        zZm2.append(uWt);
        Log.i(str, zZm2.toString());
    }

    public synchronized void zZm(ExtendedClient extendedClient, AlexaUserSpeechProvider alexaUserSpeechProvider) {
        UWt remove = this.zyO.remove(alexaUserSpeechProvider);
        if (remove == null) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Attempted to deregister an invalid UserSpeechProvider. client = ");
            zZm2.append(extendedClient.getId());
            zZm2.append(", provider = ");
            zZm2.append(alexaUserSpeechProvider);
            Log.e(str, zZm2.toString());
            return;
        }
        this.zQM.remove(remove);
        this.jiA.remove(remove);
        this.BIo.zyO(TxC.BIo());
        String str2 = zZm;
        StringBuilder zZm3 = C0179Pya.zZm("Deregistered UserSpeechProvider for ");
        zZm3.append(extendedClient.getId());
        zZm3.append(", provider ");
        zZm3.append(remove);
        Log.i(str2, zZm3.toString());
    }

    @Nullable
    public synchronized UWt zZm(AlexaUserSpeechProvider alexaUserSpeechProvider) {
        return this.zyO.get(alexaUserSpeechProvider);
    }

    @Nullable
    public synchronized AlexaUserSpeechProvider zZm(mqw mqwVar) {
        if (mqwVar instanceof UWt) {
            return this.jiA.get(mqwVar);
        }
        return null;
    }

    public synchronized Set<mqw> zZm() {
        LinkedHashSet linkedHashSet;
        linkedHashSet = new LinkedHashSet(this.zQM.zZm());
        linkedHashSet.add(this.Qle);
        return linkedHashSet;
    }
}
