package com.amazon.alexa;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.CapabilityAgentConnectionMessageSenderFactory;
import com.amazon.alexa.api.ExternalCapabilityAgentConnection;
import com.amazon.alexa.api.ExternalCapabilityAgents;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.MessageTransformer;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ExternalCapabilityAgentRegistry.java */
@Singleton
/* loaded from: classes.dex */
public class LrI extends xQl<zEy> {
    public static final String BIo = "LrI";
    public static final Map<String, Capability> zQM = new HashMap();
    @VisibleForTesting
    public final Map<ComponentName, zEy> HvC;
    public final AlexaClientEventBus JTe;
    public final Set<Capability> LPk;
    public final CapabilityAgentConnectionMessageSenderFactory Mlj;
    public final PackageManager Qle;
    public final gSO dMe;
    public final MessageTransformer jiA;
    public final ClientConfiguration lOf;
    public final Map<String, Set<Namespace>> uzr;
    public final zZm yPL;
    public final Context zyO;
    public final lWz zzR;

    /* compiled from: ExternalCapabilityAgentRegistry.java */
    /* loaded from: classes.dex */
    public static class zZm {
        public ExternalCapabilityAgentConnection zZm(Context context, ComponentName componentName, CapabilityAgentConnectionMessageSenderFactory capabilityAgentConnectionMessageSenderFactory) {
            return new ExternalCapabilityAgentConnection(context, componentName, capabilityAgentConnectionMessageSenderFactory);
        }
    }

    static {
        zQM.put("com.amazon.alexa.alertsca.AlertsCapabilityAgentService", Capability.create(AvsApiConstants.Alerts.zZm, "1.3"));
        zQM.put("com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentCapabilityAgentService", Capability.create(AvsApiConstants.Alexa.Notifications.Multipart.BIo, "0.1"));
        zQM.put("com.amazon.alexa.accessory.speechapi.voicesdk.capabilityagent.AmbientSoundCapabilityAgentService", Capability.create(AvsApiConstants.Alexa.AudioSignal.ActiveNoiseControl.BIo, "1.0"));
        zQM.put("com.amazon.alexa.accessory.speechapi.voicesdk.capabilityagent.ReportAccessoryStateCapabilityAgentService", Capability.create(AvsApiConstants.Alexa.IOComponents.BIo, "1.4"));
    }

    @Inject
    public LrI(Context context, MessageTransformer messageTransformer, PackageManager packageManager, AlexaClientEventBus alexaClientEventBus, CapabilityAgentConnectionMessageSenderFactory capabilityAgentConnectionMessageSenderFactory, lWz lwz, ClientConfiguration clientConfiguration, gSO gso) {
        zZm zzm = new zZm();
        this.uzr = new ConcurrentHashMap();
        this.zyO = context;
        this.jiA = messageTransformer;
        this.Qle = packageManager;
        this.JTe = alexaClientEventBus;
        this.LPk = new HashSet();
        this.HvC = new HashMap();
        this.Mlj = capabilityAgentConnectionMessageSenderFactory;
        this.zzR = lwz;
        this.lOf = clientConfiguration;
        this.dMe = gso;
        this.yPL = zzm;
    }

    public synchronized void jiA() {
        synchronized (this.HvC) {
            for (zEy zey : this.HvC.values()) {
                zey.BIo.release();
            }
        }
    }

    public final boolean zQM() {
        return Boolean.TRUE.equals(this.lOf.getECAV2()) || this.dMe.zZm(Feature.ALEXA_VOX_ANDROID_ECAV2);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x013a A[Catch: all -> 0x0235, TryCatch #3 {, blocks: (B:4:0x0003, B:6:0x0018, B:7:0x001c, B:8:0x004a, B:10:0x0050, B:12:0x006a, B:14:0x0070, B:16:0x007c, B:19:0x00a1, B:20:0x00b9, B:21:0x00c0, B:45:0x0101, B:55:0x013a, B:57:0x0152, B:47:0x010a, B:49:0x0116, B:53:0x0121, B:17:0x008f, B:63:0x0175, B:64:0x0179, B:66:0x0183, B:68:0x0187, B:72:0x01a0, B:73:0x01e0, B:75:0x01e6, B:76:0x01fc, B:78:0x0202, B:79:0x0215, B:71:0x0199, B:22:0x00c1, B:24:0x00cb, B:26:0x00d4, B:28:0x00dc, B:29:0x00e6, B:32:0x00ed, B:35:0x00f6, B:43:0x00fe, B:33:0x00ee, B:34:0x00f5, B:30:0x00e7, B:31:0x00ec), top: B:87:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0152 A[Catch: all -> 0x0235, TRY_LEAVE, TryCatch #3 {, blocks: (B:4:0x0003, B:6:0x0018, B:7:0x001c, B:8:0x004a, B:10:0x0050, B:12:0x006a, B:14:0x0070, B:16:0x007c, B:19:0x00a1, B:20:0x00b9, B:21:0x00c0, B:45:0x0101, B:55:0x013a, B:57:0x0152, B:47:0x010a, B:49:0x0116, B:53:0x0121, B:17:0x008f, B:63:0x0175, B:64:0x0179, B:66:0x0183, B:68:0x0187, B:72:0x01a0, B:73:0x01e0, B:75:0x01e6, B:76:0x01fc, B:78:0x0202, B:79:0x0215, B:71:0x0199, B:22:0x00c1, B:24:0x00cb, B:26:0x00d4, B:28:0x00dc, B:29:0x00e6, B:32:0x00ed, B:35:0x00f6, B:43:0x00fe, B:33:0x00ee, B:34:0x00f5, B:30:0x00e7, B:31:0x00ec), top: B:87:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void zyO() {
        /*
            Method dump skipped, instructions count: 568
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.LrI.zyO():void");
    }

    public synchronized Set<Namespace> zZm(String str) {
        Preconditions.isFalse(TextUtils.isEmpty(str), "Package name cannot be empty");
        synchronized (this.uzr) {
            if (this.uzr.containsKey(str)) {
                return this.uzr.get(str);
            }
            return Collections.emptySet();
        }
    }

    @Nullable
    @VisibleForTesting
    public zEy zZm(ComponentName componentName) {
        if (zQM.containsKey(componentName.getClassName())) {
            return zZm(componentName, zQM.get(componentName.getClassName()));
        }
        String className = componentName.getClassName();
        GeneratedOutlineSupport1.outline158("Found ", className);
        ExternalCapabilityAgentConnection zZm2 = this.yPL.zZm(this.zyO, componentName, this.Mlj);
        zZm2.connect();
        try {
            if (!zZm2.isConnected()) {
                Log.e(BIo, "Connection failed to establish in time");
                return null;
            }
            try {
                zEy zey = new zEy(zZm2, this.jiA, ExternalCapabilityAgents.BIo(zZm2), this.JTe);
                synchronized (this.HvC) {
                    this.HvC.put(componentName, zey);
                }
                return zey;
            } catch (noF e) {
                String str = BIo;
                StringBuilder sb = new StringBuilder();
                sb.append("Failure retrieving capabilities from external capability agent: ");
                sb.append(className);
                Log.e(str, sb.toString(), e);
                return null;
            } catch (Exception e2) {
                String str2 = BIo;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unknown exception adding capability agent: ");
                sb2.append(className);
                Log.e(str2, sb2.toString(), e2);
                return null;
            }
        } finally {
            zZm2.disconnect();
            this.Mlj.zZm.BIo(componentName);
        }
    }

    public final zEy zZm(ComponentName componentName, Capability capability) {
        String str = "Found " + componentName.getClassName() + ". Adding Offline.";
        zEy zey = new zEy(this.yPL.zZm(this.zyO, componentName, this.Mlj), this.jiA, new HashSet(Arrays.asList(capability)), this.JTe);
        synchronized (this.HvC) {
            this.HvC.put(componentName, zey);
        }
        return zey;
    }

    public final void zZm(Map<zEy, Set<Namespace>> map) {
        synchronized (this.zZm) {
            this.zZm.clear();
            for (Map.Entry<zEy, Set<Namespace>> entry : map.entrySet()) {
                zEy key = entry.getKey();
                for (Namespace namespace : entry.getValue()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Registering: ");
                    sb.append(namespace);
                    sb.append(", ");
                    sb.append(key);
                    sb.toString();
                    if (!(!AvsApiConstants.BIo.contains(namespace))) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Namespace ");
                        sb2.append(namespace);
                        sb2.append(" is an internal-only namespace");
                        sb2.toString();
                    } else {
                        Set set = (Set) this.zZm.get(namespace);
                        if (set == null) {
                            set = new HashSet();
                            this.zZm.put(namespace, set);
                        }
                        set.add(key);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002c A[Catch: all -> 0x0063, TryCatch #0 {, blocks: (B:4:0x0004, B:15:0x002c, B:17:0x0061, B:16:0x004c, B:7:0x0011, B:8:0x0015, B:10:0x001b), top: B:22:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004c A[Catch: all -> 0x0063, TryCatch #0 {, blocks: (B:4:0x0004, B:15:0x002c, B:17:0x0061, B:16:0x004c, B:7:0x0011, B:8:0x0015, B:10:0x001b), top: B:22:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zZm(long r13, int r15, int r16, java.util.Set<com.amazon.alexa.client.core.capabilities.Capability> r17) {
        /*
            r12 = this;
            r1 = r12
            java.util.Set<com.amazon.alexa.client.core.capabilities.Capability> r2 = r1.LPk
            monitor-enter(r2)
            java.util.Set<com.amazon.alexa.client.core.capabilities.Capability> r0 = r1.LPk     // Catch: java.lang.Throwable -> L63
            int r3 = r0.size()     // Catch: java.lang.Throwable -> L63
            int r4 = r17.size()     // Catch: java.lang.Throwable -> L63
            if (r3 == r4) goto L11
            goto L27
        L11:
            java.util.Iterator r3 = r17.iterator()     // Catch: java.lang.Throwable -> L63
        L15:
            boolean r4 = r3.hasNext()     // Catch: java.lang.Throwable -> L63
            if (r4 == 0) goto L29
            java.lang.Object r4 = r3.next()     // Catch: java.lang.Throwable -> L63
            com.amazon.alexa.client.core.capabilities.Capability r4 = (com.amazon.alexa.client.core.capabilities.Capability) r4     // Catch: java.lang.Throwable -> L63
            boolean r4 = r0.contains(r4)     // Catch: java.lang.Throwable -> L63
            if (r4 != 0) goto L15
        L27:
            r0 = 1
            goto L2a
        L29:
            r0 = 0
        L2a:
            if (r0 == 0) goto L4c
            java.util.Set<com.amazon.alexa.client.core.capabilities.Capability> r0 = r1.LPk     // Catch: java.lang.Throwable -> L63
            r0.clear()     // Catch: java.lang.Throwable -> L63
            java.util.Set<com.amazon.alexa.client.core.capabilities.Capability> r0 = r1.LPk     // Catch: java.lang.Throwable -> L63
            r6 = r17
            r0.addAll(r6)     // Catch: java.lang.Throwable -> L63
            com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus r0 = r1.JTe     // Catch: java.lang.Throwable -> L63
            com.amazon.alexa.Hvd r11 = new com.amazon.alexa.Hvd     // Catch: java.lang.Throwable -> L63
            r4 = 1
            r5 = 0
            r3 = r11
            r6 = r17
            r7 = r13
            r9 = r15
            r10 = r16
            r3.<init>(r4, r5, r6, r7, r9, r10)     // Catch: java.lang.Throwable -> L63
            r0.zyO(r11)     // Catch: java.lang.Throwable -> L63
            goto L61
        L4c:
            com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus r0 = r1.JTe     // Catch: java.lang.Throwable -> L63
            com.amazon.alexa.Hvd r11 = new com.amazon.alexa.Hvd     // Catch: java.lang.Throwable -> L63
            java.util.Set r6 = java.util.Collections.emptySet()     // Catch: java.lang.Throwable -> L63
            r4 = 0
            r5 = 0
            r3 = r11
            r7 = r13
            r9 = r15
            r10 = r16
            r3.<init>(r4, r5, r6, r7, r9, r10)     // Catch: java.lang.Throwable -> L63
            r0.zyO(r11)     // Catch: java.lang.Throwable -> L63
        L61:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L63
            return
        L63:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L63
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.LrI.zZm(long, int, int, java.util.Set):void");
    }

    public boolean zZm(@NonNull Namespace namespace) {
        if (zQM()) {
            Iterator<KHc> it2 = ((wdQ) this.zzR).BIo().iterator();
            while (it2.hasNext()) {
                Iterator<Capability> it3 = ((Qrg) it2.next()).zyO.iterator();
                while (it3.hasNext()) {
                    if (it3.next().getInterface().getValue().equals(namespace.getValue())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return this.zZm.containsKey(namespace);
    }
}
