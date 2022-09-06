package com.amazon.alexa;

import android.graphics.Point;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.UMd;
import com.amazon.alexa.api.ExternalCapabilityAgents;
import com.amazon.alexa.client.alexaservice.capabilities.AutoValue_CapabilityPublishRequest;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.CapabilityInterface;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.alexa.vHh;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.identity.auth.device.authorization.RegionUtil;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: CapabilityAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class pZY {
    @VisibleForTesting
    public static final Map<CapabilityInterface, Capability> BIo;
    @VisibleForTesting
    public static final Map<CapabilityInterface, Capability> zQM = new HashMap();
    public static final String zZm = "pZY";
    public final AlexaClientEventBus JTe;
    public final PersistentStorage LPk;
    public final Provider<Cta> Mlj;
    public final qxC Qle;
    public final ClientConfiguration dMe;
    public final lWz lOf;
    public DeviceInformation wDP;
    public final Gson yPL;
    public final ScheduledExecutorService zyO;
    public final WnL zzR;
    public volatile boolean uzr = false;
    public final AtomicInteger jiA = new AtomicInteger(0);
    public Set<Capability> HvC = Collections.emptySet();
    public Set<Capability> vkx = Collections.emptySet();

    static {
        zZm(zQM, AvsApiConstants.SipClient.zZm, "0.1");
        zZm(zQM, AvsApiConstants.Alexa.AudioSignal.ActiveNoiseControl.BIo, "1.0");
        zZm(zQM, AvsApiConstants.Alexa.Notifications.Multipart.BIo, "0.1");
        zZm(zQM, AvsApiConstants.Alexa.Translation.LiveTranslation.zZm, "0.1");
        zZm(zQM, AvsApiConstants.Alerts.zZm, "1.3");
        zZm(zQM, AvsApiConstants.Alexa.IOComponents.BIo, "1.4");
        HashMap hashMap = new HashMap();
        zZm(hashMap, AvsApiConstants.AudioActivityTracker.BIo, "1.0");
        zZm(hashMap, AvsApiConstants.VisualActivityTracker.BIo, "1.1");
        zZm(hashMap, AvsApiConstants.PlaybackController.BIo, "1.0");
        zZm(hashMap, AvsApiConstants.Settings.BIo, "1.0");
        zZm(hashMap, AvsApiConstants.AccessoryKit.BIo, "0.1");
        zZm(hashMap, AvsApiConstants.Geolocation.BIo, "1.1");
        BIo = hashMap;
    }

    @Inject
    public pZY(AlexaClientEventBus alexaClientEventBus, Gson gson, qxC qxc, @Named("capabilities") PersistentStorage persistentStorage, Provider<Cta> provider, DeviceInformation deviceInformation, WnL wnL, lWz lwz, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, ClientConfiguration clientConfiguration) {
        this.JTe = alexaClientEventBus;
        this.LPk = persistentStorage;
        this.yPL = gson;
        this.Qle = qxc;
        this.Mlj = provider;
        this.wDP = deviceInformation;
        this.lOf = lwz;
        this.zzR = wnL;
        this.zyO = scheduledExecutorService;
        this.dMe = clientConfiguration;
        Map<CapabilityInterface, Capability> map = BIo;
        CapabilityInterface capabilityInterface = AvsApiConstants.Alexa.InteractionMode.zZm;
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("id", "mobile_mode");
        jsonObject2.addProperty("uiMode", "MOBILE");
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.addProperty("unit", "INCHES");
        jsonObject3.addProperty("value", (Number) 18);
        jsonObject2.add("interactionDistance", jsonObject3);
        jsonObject2.addProperty("touch", "SUPPORTED");
        jsonObject2.addProperty("keyboard", "SUPPORTED");
        jsonObject2.addProperty("video", "SUPPORTED");
        jsonObject2.addProperty("dialog", "SUPPORTED");
        jsonArray.add(jsonObject2);
        JsonObject jsonObject4 = new JsonObject();
        jsonObject4.addProperty("id", "auto_mode");
        jsonObject4.addProperty("uiMode", RegionUtil.REGION_STRING_AUTO);
        JsonObject jsonObject5 = new JsonObject();
        jsonObject5.addProperty("unit", "INCHES");
        jsonObject5.addProperty("value", (Number) 36);
        jsonObject4.add("interactionDistance", jsonObject5);
        jsonObject4.addProperty("touch", "SUPPORTED");
        jsonObject4.addProperty("keyboard", "UNSUPPORTED");
        jsonObject4.addProperty("video", "UNSUPPORTED");
        jsonObject4.addProperty("dialog", "SUPPORTED");
        jsonArray.add(jsonObject4);
        jsonObject.add("interactionModes", jsonArray);
        map.put(capabilityInterface, Capability.create(AvsApiConstants.Alexa.InteractionMode.zZm, "1.0", jsonObject));
        Map<CapabilityInterface, Capability> map2 = BIo;
        CapabilityInterface capabilityInterface2 = AvsApiConstants.Alexa.Display.zZm;
        map2.put(capabilityInterface2, Capability.create(capabilityInterface2, "1.0"));
        Map<CapabilityInterface, Capability> map3 = BIo;
        CapabilityInterface capabilityInterface3 = AvsApiConstants.Alexa.Display.Window.BIo;
        JsonObject jsonObject6 = new JsonObject();
        JsonArray jsonArray2 = new JsonArray();
        JsonObject jsonObject7 = new JsonObject();
        jsonObject7.addProperty("id", "app_window_template");
        jsonObject7.addProperty("type", AlexaMetricsConstants.CardMode.STANDARD_MODE);
        JsonObject jsonObject8 = new JsonObject();
        JsonArray jsonArray3 = new JsonArray();
        JsonObject jsonObject9 = new JsonObject();
        jsonObject9.addProperty("type", "DISCRETE");
        jsonObject9.addProperty("id", "fullscreen");
        JsonObject jsonObject10 = new JsonObject();
        jsonObject10.addProperty("unit", "PIXEL");
        Point deviceSize = deviceInformation.getDeviceSize();
        int i = deviceSize.x;
        int i2 = deviceSize.y;
        JsonObject jsonObject11 = new JsonObject();
        jsonObject11.addProperty("width", Integer.valueOf(i));
        jsonObject11.addProperty("height", Integer.valueOf(i2));
        jsonObject10.add("value", jsonObject11);
        jsonObject9.add("value", jsonObject10);
        jsonArray3.add(jsonObject9);
        jsonObject8.add("sizes", jsonArray3);
        JsonArray jsonArray4 = new JsonArray();
        jsonArray4.add("mobile_mode");
        jsonArray4.add("auto_mode");
        jsonObject8.add("interactionModes", jsonArray4);
        jsonObject7.add(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, jsonObject8);
        jsonArray2.add(jsonObject7);
        jsonObject6.add("templates", jsonArray2);
        map3.put(capabilityInterface3, Capability.create(AvsApiConstants.Alexa.Display.Window.BIo, "1.0", jsonObject6));
        alexaClientEventBus.zZm(this);
    }

    @Subscribe(priority = 100)
    public synchronized void on(UMd.zZm zzm) {
        if (!zzm.BIo()) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Capabilities were refreshed, but only: ");
            Hvd hvd = (Hvd) zzm;
            zZm2.append(hvd.JTe);
            zZm2.append(" of ");
            zZm2.append(hvd.Qle);
            zZm2.append(" capability agents responded.");
            Log.e(str, zZm2.toString());
        }
        Hvd hvd2 = (Hvd) zzm;
        if (hvd2.BIo) {
            if (hvd2.zQM) {
                this.vkx = hvd2.zyO;
            } else {
                this.HvC = hvd2.zyO;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x009f, code lost:
        if (r2 == false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void BIo() {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.pZY.BIo():void");
    }

    public final void zQM() {
        if (this.Qle.zQM()) {
            this.uzr = true;
            LinkedList linkedList = new LinkedList();
            linkedList.addAll(BIo.values());
            linkedList.addAll(this.vkx);
            linkedList.addAll(this.HvC);
            HashSet hashSet = new HashSet();
            for (Capability capability : this.HvC) {
                hashSet.add(capability.getInterface());
            }
            if (this.wDP.isFireOS()) {
                zQM.remove(AvsApiConstants.Alerts.zZm);
            }
            Sets.SetView<CapabilityInterface> difference = Sets.difference(zQM.keySet(), hashSet);
            HashSet hashSet2 = new HashSet();
            Boolean hardcodedCapabilitiesDisabled = this.dMe.getHardcodedCapabilitiesDisabled();
            if (hardcodedCapabilitiesDisabled == null || !hardcodedCapabilitiesDisabled.booleanValue()) {
                for (CapabilityInterface capabilityInterface : difference) {
                    hashSet2.add(zQM.get(capabilityInterface));
                }
            }
            this.JTe.zyO(new rJR(hashSet2.size()));
            linkedList.addAll(hashSet2);
            List<KHc> BIo2 = ((wdQ) this.lOf).BIo();
            StringBuilder zZm2 = C0179Pya.zZm("Getting V2 ECAs. Number of external Capability agents: ");
            zZm2.append(BIo2.size());
            zZm2.toString();
            ArrayList arrayList = new ArrayList();
            for (KHc kHc : BIo2) {
                if (kHc != null) {
                    ArrayList<Capability> arrayList2 = ((Qrg) kHc).zyO;
                    if (arrayList2 != null) {
                        StringBuilder zZm3 = C0179Pya.zZm("flattening EC, count: ");
                        zZm3.append(arrayList2.size());
                        zZm3.toString();
                        arrayList.addAll(arrayList2);
                    } else {
                        Log.e(zZm, "Encountered unexpected null reading supportedCapabilities");
                    }
                } else {
                    Log.e(zZm, "Encountered unexpected null reading followingCapability");
                }
            }
            StringBuilder zZm4 = C0179Pya.zZm("Flattened V2 ECAs. Number of external capabilities: ");
            zZm4.append(arrayList.size());
            zZm4.toString();
            linkedList.addAll(arrayList);
            this.JTe.zyO(new MXm(new AutoValue_CapabilityPublishRequest("20160207", linkedList, this.Mlj.mo10268get()), new zZm(linkedList)));
        }
    }

    public static void zZm(Map<CapabilityInterface, Capability> map, CapabilityInterface capabilityInterface, String str) {
        map.put(capabilityInterface, Capability.create(capabilityInterface, str));
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zZm(java.util.Collection<com.amazon.alexa.client.core.capabilities.Capability> r5) {
        /*
            r4 = this;
            java.util.Iterator r5 = r5.iterator()
        L4:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L36
            java.lang.Object r0 = r5.next()
            com.amazon.alexa.client.core.capabilities.Capability r0 = (com.amazon.alexa.client.core.capabilities.Capability) r0
            com.amazon.alexa.client.core.capabilities.CapabilityInterface r1 = r0.getInterface()
            com.amazon.alexa.client.core.device.PersistentStorage r2 = r4.LPk
            java.lang.String r1 = r1.getValue()
            java.lang.String r1 = r2.getString(r1)
            if (r1 == 0) goto L2b
            com.google.gson.Gson r2 = r4.yPL
            java.lang.Class<com.amazon.alexa.client.core.capabilities.Capability> r3 = com.amazon.alexa.client.core.capabilities.Capability.class
            java.lang.Object r1 = r2.fromJson(r1, r3)
            com.amazon.alexa.client.core.capabilities.Capability r1 = (com.amazon.alexa.client.core.capabilities.Capability) r1
            goto L2c
        L2b:
            r1 = 0
        L2c:
            if (r1 == 0) goto L34
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L4
        L34:
            r5 = 1
            return r5
        L36:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.pZY.zZm(java.util.Collection):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CapabilityAuthority.java */
    /* loaded from: classes.dex */
    public class zZm implements vHh {
        public final List<Capability> zZm;

        public zZm(List<Capability> list) {
            this.zZm = list;
        }

        public void zZm() {
            String str = pZY.zZm;
            pZY.this.zZm(this.zZm);
            pZY.this.uzr = false;
            pZY.this.JTe.zyO(new Dzn());
        }

        public void zZm(vHh.zZm zzm) {
            synchronized (pZY.this) {
                Log.e(pZY.zZm, "Unable to publish capabilities");
                pZY.this.uzr = false;
                if (pZY.this.zzR.LPk) {
                    int andIncrement = pZY.this.jiA.getAndIncrement();
                    ExternalCapabilityAgents.zZm();
                    if (andIncrement < 8 && pZY.this.Qle.zQM()) {
                        pZY.this.JTe.zyO(new RBR(zzm, false));
                        pZY.this.zyO.schedule(new Dtz(this), ExternalCapabilityAgents.zZm(pZY.this.jiA.get()), TimeUnit.MILLISECONDS);
                    }
                }
                pZY.this.JTe.zyO(new RBR(zzm, true));
            }
        }
    }

    public final void zZm(List<Capability> list) {
        PersistentStorage.Transaction edit = this.LPk.edit();
        edit.clear();
        for (Capability capability : list) {
            edit.set(capability.getInterface().getValue(), this.yPL.toJson(capability));
        }
        edit.set("envelope_version", "20160207");
        edit.set("legacy_flags", this.yPL.toJson(this.Mlj.mo10268get()));
        edit.commitAsynchronously();
    }

    @Subscribe
    public synchronized void on(rKi rki) {
        Log.i(zZm, "on: EnableExternalCapabilityAgentEvent");
        if (((wdQ) this.lOf).zZm(true, ((tsJ) rki).jiA)) {
            zQM();
        }
    }

    public synchronized void zZm() {
        this.LPk.edit().clear().commitSynchronously();
    }

    @Subscribe
    public synchronized void on(AbstractC0242ykQ abstractC0242ykQ) {
        Log.i(zZm, "on: DisableExternalCapabilityAgentEvent");
        if (((wdQ) this.lOf).zZm(false, ((dVL) abstractC0242ykQ).jiA)) {
            zQM();
        }
    }

    @Subscribe(priority = 100)
    public synchronized void on(WBQ wbq) {
        this.LPk.edit().clear().commitSynchronously();
    }
}
