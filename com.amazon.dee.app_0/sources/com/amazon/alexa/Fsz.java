package com.amazon.alexa;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.ARM;
import com.amazon.alexa.BaP;
import com.amazon.alexa.BjL;
import com.amazon.alexa.Fwh;
import com.amazon.alexa.HdS;
import com.amazon.alexa.ICG;
import com.amazon.alexa.NEv;
import com.amazon.alexa.UMd;
import com.amazon.alexa.Ued;
import com.amazon.alexa.VXG;
import com.amazon.alexa.XnZ;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.AlexaMetricsCategory;
import com.amazon.alexa.api.AlexaMetricsCount;
import com.amazon.alexa.api.AlexaMetricsData;
import com.amazon.alexa.api.AlexaMetricsListener;
import com.amazon.alexa.api.AlexaMetricsMetadata;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.AlexaMetricsTime;
import com.amazon.alexa.api.AlexaMetricsType;
import com.amazon.alexa.api.AutoValue_ApiName;
import com.amazon.alexa.api.ClientVersion;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.DefaultAlexaMetricsEvent;
import com.amazon.alexa.client.metrics.core.DefaultMetricsTimer;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.alexa.client.metrics.core.MetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsCounter;
import com.amazon.alexa.client.metrics.core.MetricsTimer;
import com.amazon.alexa.fuM;
import com.amazon.alexa.gKM;
import com.amazon.alexa.jsd;
import com.amazon.alexa.pPw;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.devicesetup.common.v1.Event;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: MetricsAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class Fsz {
    public static final String zZm = "Fsz";
    public final Context BIo;
    public final Set<String> CGv;
    public final C0211jTe HvC;
    public final TimeProvider JTe;
    public long JXl;
    public boolean MNR;
    public final DeviceInformation Mlj;
    public final IUt NXS;
    public final KPv Qgh;
    public final Lazy<PersistentStorage> Qle;
    public final GQk Tbw;
    public final ExecutorService XWf;
    public final qxC jiA;
    public final PackageManager lOf;
    public final bwE noQ;
    public long oQJ;
    public final Dtt uuO;
    public final String uzr;
    public final Kcd vkx;
    public final PWS wDP;
    public long wUw;
    public long zOR;
    public final AlexaClientEventBus zQM;
    public final paF zyO;
    public final PreloadAttributionManager zzR;
    public final TimeZone dMe = TimeZone.getDefault();
    public final Shr<bNQ> LPk = new Shr<>();
    public final Shr<AlexaMetricsListener> yPL = new Shr<>();
    public long Jhx = -1;

    @Inject
    public Fsz(Context context, AlexaClientEventBus alexaClientEventBus, qxC qxc, Lazy<PersistentStorage> lazy, paF paf, TimeProvider timeProvider, DeviceInformation deviceInformation, PreloadAttributionManager preloadAttributionManager, C0211jTe c0211jTe, Kcd kcd, PackageManager packageManager, PWS pws, bwE bwe, KPv kPv, GQk gQk, IUt iUt, Dtt dtt) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
        this.zyO = paf;
        this.jiA = qxc;
        this.Qle = lazy;
        this.JTe = timeProvider;
        this.Mlj = deviceInformation;
        this.zzR = preloadAttributionManager;
        this.NXS = iUt;
        this.HvC = c0211jTe;
        this.vkx = kcd;
        this.lOf = packageManager;
        this.uzr = context.getPackageName();
        this.wDP = pws;
        this.noQ = bwe;
        this.Qgh = kPv;
        this.Tbw = gQk;
        this.uuO = dtt;
        HashSet hashSet = new HashSet();
        for (AlexaMetadataBundleKey alexaMetadataBundleKey : AlexaMetadataBundleKey.values()) {
            hashSet.add(alexaMetadataBundleKey.name());
        }
        GeneratedOutlineSupport1.outline187(hashSet, "contentProvider", AlexaMetricsConstants.Launcher.OUTCOME, AlexaMetricsConstants.Launcher.TOKEN, AlexaMetricsConstants.Launcher.REASONS);
        hashSet.add(AlexaMetricsConstants.Launcher.TARGET);
        hashSet.add(AlexaMetricsConstants.AMPD.DEVICE_UNLOCK_STATE);
        hashSet.add(AlexaMetricsConstants.AMPD.LAST_UNLOCK_TIMESTAMP);
        this.CGv = hashSet;
        alexaClientEventBus.zZm(this);
        this.XWf = ExecutorFactory.newSingleThreadExecutor("metrics-authority");
    }

    @VisibleForTesting
    public void BIo(AlexaMetricsData alexaMetricsData) {
        for (AlexaMetricsListener alexaMetricsListener : this.yPL.zZm()) {
            alexaMetricsListener.onMetricsReport(alexaMetricsData);
        }
    }

    @Subscribe
    public synchronized void on(AbstractC0224oIb abstractC0224oIb) {
        this.JXl = abstractC0224oIb.zZm;
    }

    public final void zQM(String str) {
        zZm(str, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    public final void zZm(Bundle bundle, nLZ nlz) {
        kQf kqf = nlz.BIo;
        bundle.putString(AlexaMetadataBundleKey.PLAY_TOKEN.name(), kqf.mo947BIo().zZm);
        bundle.putString(AlexaMetadataBundleKey.URL_DOMAIN.name(), nlz.BIo.zQM.getHost());
        bundle.putString(AlexaMetadataBundleKey.FILE_EXTENSION.name(), nlz.BIo.zQM.getLastPathSegment());
        if (kqf.BIo != null) {
            bundle.putString(AlexaMetadataBundleKey.AUDIO_ITEM_IDENTIFIER.name(), nlz.BIo.BIo.getValue());
        }
    }

    public final void BIo(String str) {
        zZm(str, (Map<String, Object>) null);
    }

    @Subscribe
    public synchronized void on(fuM.jiA jia) {
        this.zOR = jia.zZm;
    }

    public final Bundle BIo(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                bundle2.putString(str, obj.toString());
            }
        }
        return bundle2;
    }

    @Subscribe
    public synchronized void on(tkb tkbVar) {
        QNM qnm = (QNM) tkbVar;
        this.wUw = qnm.BIo;
        long j = qnm.zQM - ((QNM) tkbVar).BIo;
        String str = "Service created in " + j + "ms";
        zZm(AlexaMetricsConstants.MetricEvents.VOX_SERVICE_CREATION_TIME, null, j);
        zZm(AlexaMetricsName.Latency.AMF_CREATION_TIME, AlexaMetricsTime.create(j), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(Ehk ehk) {
        if ("com.amazon.alexa.externalmediaplayer.ACTION_START_SERVICE_FOR_MSP".equals(((xSe) ehk).BIo.getAction())) {
            zZm(AlexaMetricsName.ExternalMediaPlayer.ACTION_START_SERVICE_FOR_MSP, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
        }
    }

    @Subscribe
    public synchronized void on(FXk fXk) {
        pfe pfeVar = ((YLU) fXk).zQM;
        StringBuilder sb = new StringBuilder();
        sb.append(((PUa) pfeVar).BIo);
        sb.append("_");
        PUa pUa = (PUa) pfeVar;
        sb.append(pUa.zZm);
        String[] strArr = {AlexaMetricsName.ExternalMediaPlayer.PLAYER_ERROR.getValue(), ((YLU) fXk).BIo.getValue(), sb.toString(), pUa.jiA.replace(".", "").replace(" ", "_")};
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb2.append(strArr[i]);
            if (i < strArr.length - 1) {
                sb2.append(".");
            }
        }
        zZm(sb2.toString(), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    public synchronized void zZm() {
        this.HvC.zQM();
        this.vkx.zQM();
        this.Tbw.zZm();
        this.wDP.zZm();
        this.noQ.BIo();
        this.Qgh.BIo();
        this.zQM.BIo(this);
        this.XWf.shutdown();
        for (MetricsConnector metricsConnector : this.zyO.zyO) {
            metricsConnector.shutdown();
        }
    }

    public synchronized void zZm(long j) {
        zZm(AlexaMetricsName.Business.ALEXA_SESSION_LENGTH, AlexaMetricsTime.create(this.JTe.elapsedRealTime() - j), AlexaMetricsCategory.BUSINESS, AlexaMetricsType.TIMER, new Bundle());
    }

    public void zZm(ExtendedClient extendedClient, bNQ bnq) {
        this.LPk.zZm(extendedClient, bnq);
    }

    public void zZm(bNQ bnq) {
        this.LPk.remove(bnq);
    }

    public void zZm(ExtendedClient extendedClient, AlexaMetricsListener alexaMetricsListener) {
        this.yPL.zZm(extendedClient, alexaMetricsListener);
    }

    public void zZm(AlexaMetricsListener alexaMetricsListener) {
        this.yPL.remove(alexaMetricsListener);
    }

    public final void zZm(UiEventName uiEventName, Bundle bundle) {
        AlexaMetricsName alexaMetricsName;
        int i = Ebf.zyO[uiEventName.ordinal()];
        if (i == 1) {
            alexaMetricsName = AlexaMetricsName.Latency.ATTENTION_SYSTEM;
        } else if (i != 3) {
            alexaMetricsName = uiEventName.getMetricsName();
        } else {
            alexaMetricsName = AlexaMetricsName.Latency.GUI_USER_PERCEIVED;
        }
        AlexaMetricsName alexaMetricsName2 = alexaMetricsName;
        if (alexaMetricsName2 == null) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Unknown metric: ");
            zZm2.append(uiEventName.name());
            Log.w(str, zZm2.toString());
            return;
        }
        int i2 = Ebf.zyO[uiEventName.ordinal()];
        if (i2 == 1) {
            return;
        }
        long j = -1;
        if (i2 != 3) {
            j = bundle.getLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), -1L);
        } else {
            long j2 = this.Jhx;
            if (j2 > 0) {
                long j3 = this.oQJ;
                if (j3 > 0) {
                    long j4 = j3 + j2;
                    long j5 = bundle.getLong(AlexaMetadataBundleKey.EVENT_REALTIME_MS.name());
                    if (j4 <= j5) {
                        j = zZm(j4, Long.valueOf(j5));
                    }
                }
            }
        }
        if (j <= 0) {
            return;
        }
        zZm(alexaMetricsName2, AlexaMetricsTime.create(j), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, BIo(bundle));
    }

    @Subscribe
    public synchronized void on(ZBK zbk) {
        if (((dZg) zbk).BIo) {
            boolean z = true;
            if (!this.MNR) {
                this.MNR = true;
                String str = "Time to first connected client: " + (zbk.zZm - this.wUw) + "ms";
            }
            this.zyO.zZm();
            int i = Build.VERSION.SDK_INT;
            if (this.uuO.zZm(this.BIo, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                z = false;
            }
            if (!this.Qle.mo358get().contains(AlexaMetricsConstants.EventConstants.LOCATION_ENABLED)) {
                if (z) {
                    zZm(AlexaMetricsConstants.MetricEvents.LOCATION_PERMISSION_INITIAL_CONVERSION, (Map<String, Object>) null);
                } else {
                    zZm(AlexaMetricsConstants.MetricEvents.LOCATION_PERMISSION_INITIAL_CONVERSION);
                }
            }
            if (z) {
                zZm(AlexaMetricsConstants.MetricEvents.LOCATION_ENABLED, (Map<String, Object>) null);
            } else {
                zZm(AlexaMetricsConstants.MetricEvents.LOCATION_ENABLED);
            }
            this.Qle.mo358get().edit().set(AlexaMetricsConstants.EventConstants.LOCATION_ENABLED, z).commitAsynchronously();
        } else {
            for (MetricsConnector metricsConnector : this.zyO.zyO) {
                metricsConnector.endSession();
            }
        }
    }

    public final void zZm(String str) {
        this.zyO.zZm(this.zyO.zZm(str, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, null));
    }

    public final void zZm(String str, @Nullable Map<String, Object> map) {
        MetricsCounter zZm2 = this.zyO.zZm(str, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, map);
        zZm2.incrementCounter();
        this.zyO.zZm(zZm2);
    }

    public final void zZm(String str, @Nullable DialogRequestIdentifier dialogRequestIdentifier, long j) {
        HashMap hashMap = new HashMap();
        if (dialogRequestIdentifier != null) {
            hashMap.put("dialogId", dialogRequestIdentifier.getValue());
        }
        this.zyO.zZm((MetricsTimer) new DefaultMetricsTimer(str, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, hashMap, j, false));
    }

    public final long zZm(long j, @Nullable Long l) {
        long longValue;
        if (l == null) {
            longValue = this.JTe.elapsedRealTime();
        } else {
            longValue = l.longValue();
        }
        return longValue - j;
    }

    @Subscribe
    public synchronized void on(xZV xzv) {
        this.LPk.BIo(((uyC) xzv).BIo);
        this.yPL.BIo(((uyC) xzv).BIo);
    }

    public final void zZm(AlexaMetricsName alexaMetricsName, AlexaMetricsTime alexaMetricsTime, AlexaMetricsCategory alexaMetricsCategory, AlexaMetricsType alexaMetricsType, Bundle bundle) {
        zZm(alexaMetricsName.getValue(), alexaMetricsTime, alexaMetricsCategory, alexaMetricsType, bundle);
    }

    public final void zZm(String str, AlexaMetricsTime alexaMetricsTime, AlexaMetricsCategory alexaMetricsCategory, AlexaMetricsType alexaMetricsType, Bundle bundle) {
        zZm(bundle);
        AlexaMetricsData build = new AlexaMetricsData.Builder(this.uzr, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE).setEventName(str).setMetricCategory(alexaMetricsCategory).setMetricType(alexaMetricsType).setMetadata(bundle).setAlexaMetricsTime(alexaMetricsTime).build();
        BIo(build);
        zZm(build);
    }

    @Subscribe
    public synchronized void on(ZRZ zrz) {
        Bundle bundle = new Bundle();
        zZm(((gsu) zrz).zQM, bundle);
        zZm(AlexaMetricsName.SdkUsage.CLIENT_CONNECTED, AlexaMetricsCount.create(((gsu) zrz).BIo), AlexaMetricsCategory.USAGE, AlexaMetricsType.COUNTER, bundle);
    }

    public final void zZm(AlexaMetricsName alexaMetricsName, AlexaMetricsCount alexaMetricsCount, AlexaMetricsCategory alexaMetricsCategory, AlexaMetricsType alexaMetricsType, Bundle bundle) {
        zZm(alexaMetricsName.getValue(), alexaMetricsCount, alexaMetricsCategory, alexaMetricsType, bundle);
    }

    public final void zZm(String str, AlexaMetricsCount alexaMetricsCount, AlexaMetricsCategory alexaMetricsCategory, AlexaMetricsType alexaMetricsType, Bundle bundle) {
        zZm(bundle);
        AlexaMetricsData build = new AlexaMetricsData.Builder(this.uzr, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE).setEventName(str).setMetricCategory(alexaMetricsCategory).setMetricType(alexaMetricsType).setMetadata(bundle).setAlexaMetricsCount(alexaMetricsCount).build();
        BIo(build);
        zZm(build);
    }

    @Subscribe
    public synchronized void on(TWb tWb) {
        int i = ((hZD) tWb).BIo;
        zZm(AlexaMetricsName.SdkUsage.CLIENT_DISCONNECTED, AlexaMetricsCount.create(i), AlexaMetricsCategory.USAGE, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(fuM.BIo bIo) {
        fuM.zZm zzm = ((Don) bIo).zQM;
        long j = bIo.zZm;
        Don don = (Don) bIo;
        DialogRequestIdentifier dialogRequestIdentifier = don.zyO;
        String invocationType = don.jiA.getInvocationType();
        ExtendedClient extendedClient = don.BIo;
        this.Jhx = -1L;
        this.oQJ = j;
        String str = AlexaMetricsConstants.EventConstants.TAP_TO_TALK;
        if (fuM.zZm.BUTTON_PRESS.equals(zzm)) {
            str = AlexaMetricsConstants.EventConstants.TAP_TO_TALK;
        } else if (fuM.zZm.WAKEWORD.equals(zzm)) {
            str = AlexaMetricsConstants.EventConstants.WAKE_WORD;
        }
        Map<String, Object> hashMap = new HashMap<>();
        Bundle bundle = new Bundle();
        hashMap.put(AlexaMetricsConstants.EventConstants.USER_SPEECH_INGRESS_TYPE, str);
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INGRESS_TYPE.name(), str);
        zZm(extendedClient, bundle);
        if (invocationType == null) {
            invocationType = String.format("%s.%s", extendedClient.getPackageName(), str);
        }
        hashMap.put(AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, invocationType);
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name(), invocationType);
        hashMap.put("dialogId", dialogRequestIdentifier.getValue());
        bundle.putString(AlexaMetadataBundleKey.DIALOG_REQUEST_ID.name(), dialogRequestIdentifier.getValue());
        zZm(AlexaMetricsConstants.MetricEvents.VOX_START, hashMap);
        zZm(AlexaMetricsName.UserInitiatedSpeechRequest.STARTED, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    public final void zZm(ExtendedClient extendedClient, Bundle bundle) {
        PackageInfo packageInfo;
        String str;
        try {
            packageInfo = this.lOf.getPackageInfo(extendedClient.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.getMessage();
            packageInfo = null;
        }
        String str2 = "";
        if (packageInfo != null) {
            str2 = Integer.toString(packageInfo.versionCode);
            str = packageInfo.versionName;
        } else {
            str = str2;
        }
        bundle.putString(AlexaMetadataBundleKey.CLIENT_AMF_VERSION.name(), extendedClient.getVersion().toString());
        bundle.putString(AlexaMetadataBundleKey.CLIENT_PACKAGE_NAME.name(), extendedClient.getPackageName());
        bundle.putString(AlexaMetadataBundleKey.CLIENT_VERSION_CODE.name(), str2);
        bundle.putString(AlexaMetadataBundleKey.CLIENT_VERSION_NAME.name(), str);
    }

    public final void zZm(Bundle bundle) {
        if (bundle == null) {
            Log.w(zZm, "Metadata is null, not adding any data");
            return;
        }
        Map<String, Object> staticDeviceProfileData = this.Mlj.getStaticDeviceProfileData();
        Map<String, Object> dynamicDeviceProfileData = this.Mlj.getDynamicDeviceProfileData(true);
        for (Map.Entry<String, Object> entry : staticDeviceProfileData.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue().toString());
        }
        for (Map.Entry<String, Object> entry2 : dynamicDeviceProfileData.entrySet()) {
            bundle.putString(entry2.getKey(), entry2.getValue().toString());
        }
        String attributionTag = this.zzR.getAttributionTag();
        if (!TextUtils.isEmpty(attributionTag)) {
            bundle.putString(AlexaMetadataBundleKey.ATTRIBUTION_TAG.name(), attributionTag);
        }
        if (!bundle.containsKey(AlexaMetadataBundleKey.LOCALE.name())) {
            bundle.putString(AlexaMetadataBundleKey.LOCALE.name(), Locale.getDefault().toLanguageTag());
        }
        bundle.putString(AlexaMetadataBundleKey.LOCAL_TIMEZONE.name(), this.dMe.getDisplayName());
        bundle.putString(AlexaMetadataBundleKey.EVENT_TIMESTAMP.name(), Long.toString(this.JTe.currentTimeMillis()));
        bundle.putString(AlexaMetadataBundleKey.LEADER_AMF_VERSION.name(), ClientVersion.zZm);
        bundle.putString(AlexaMetadataBundleKey.LEADER_PACKAGE_NAME.name(), this.uzr);
        bundle.putString(AlexaMetadataBundleKey.LEADER_VERSION_CODE.name(), "2.4.1609.0");
        bundle.putString(AlexaMetadataBundleKey.LEADER_VERSION_NAME.name(), "2.4.1609.0");
        for (String str : new HashSet(bundle.keySet())) {
            Object obj = bundle.get(str);
            if (obj == null) {
                bundle.remove(str);
            }
            if (obj instanceof Boolean) {
                bundle.remove(str);
                bundle.putString(str, String.valueOf(obj));
            } else {
                String string = bundle.getString(str);
                if (string == null || string.isEmpty()) {
                    bundle.remove(str);
                }
            }
        }
    }

    @Subscribe
    public synchronized void on(Yud yud) {
        this.Jhx = ((Sdw) yud).BIo;
    }

    @Subscribe
    public synchronized void on(HBD hbd) {
        String str = ((wFY) hbd).zQM;
        DialogRequestIdentifier dialogRequestIdentifier = ((wFY) hbd).BIo;
        long j = hbd.zZm;
        long j2 = this.Jhx;
        if (j2 >= 0) {
            long zZm2 = zZm(this.oQJ + j2, Long.valueOf(j));
            StringBuilder zZm3 = C0179Pya.zZm("UserPerceivedLatency.");
            zZm3.append(str == null ? "null" : str);
            zZm(zZm3.toString(), dialogRequestIdentifier, zZm2);
            Bundle bundle = new Bundle();
            bundle.putString(AlexaMetadataBundleKey.DIALOG_REQUEST_ID.name(), dialogRequestIdentifier == null ? "null" : dialogRequestIdentifier.getValue());
            bundle.putString(AlexaMetadataBundleKey.CARD_NAME.name(), str);
            zZm(AlexaMetricsName.Latency.GUI_DATA_RECEIVED, AlexaMetricsTime.create(zZm2), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
        }
        long zZm4 = zZm(this.zOR, Long.valueOf(j));
        StringBuilder zZm5 = C0179Pya.zZm("EstimatedUserPerceivedLatency.");
        if (str == null) {
            str = "null";
        }
        zZm5.append(str);
        zZm(zZm5.toString(), dialogRequestIdentifier, zZm4);
    }

    public final void zZm(AlexaMetricsData alexaMetricsData) {
        HashMap hashMap = new HashMap();
        hashMap.put("EventType", alexaMetricsData.getMetricsType().name());
        hashMap.put(AlexaMetricsConstants.EventConstants.CATEGORY, alexaMetricsData.getMetricsCategory().name());
        AlexaMetricsMetadata metadata = alexaMetricsData.getMetadata();
        if (metadata.containsKey(AlexaMetadataBundleKey.DIALOG_REQUEST_ID.name())) {
            hashMap.put("dialogId", metadata.get(AlexaMetadataBundleKey.DIALOG_REQUEST_ID.name()));
        }
        if (metadata.containsKey(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name())) {
            hashMap.put(AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, metadata.get(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name()));
        }
        for (String str : this.CGv) {
            if (metadata.containsKey(str)) {
                hashMap.put(str, metadata.get(str));
            }
        }
        String eventName = alexaMetricsData.getEventName();
        if (AlexaMetricsType.COUNTER == alexaMetricsData.getMetricsType()) {
            int counterValue = alexaMetricsData.getAlexaMetricsCount().getCounterValue();
            MetricsCounter zZm2 = this.zyO.zZm(eventName, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, hashMap);
            zZm2.incrementCounterByValue(counterValue);
            this.zyO.zZm(zZm2);
        } else if (AlexaMetricsType.TIMER == alexaMetricsData.getMetricsType()) {
            this.zyO.zZm((MetricsTimer) new DefaultMetricsTimer(eventName, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, hashMap, alexaMetricsData.getAlexaMetricsTime().getTimerValue(), false));
        } else {
            this.zyO.zZm(new DefaultAlexaMetricsEvent(eventName, alexaMetricsData.getComponentName(), hashMap));
        }
    }

    @Subscribe
    public synchronized void on(uxs uxsVar) {
        String alexaMetricsName;
        AlexaMetricsName alexaMetricsName2;
        Bundle bundle = new Bundle();
        AlexaMetricsCount createOne = AlexaMetricsCount.createOne();
        switch (((SHw) uxsVar).BIo.ordinal()) {
            case 0:
                alexaMetricsName = AlexaMetricsName.MetricEvents.VOX_OFFLINE_VUI_NOT_CONNECTED.toString();
                alexaMetricsName2 = AlexaMetricsName.OfflinePromptPlayed.NO_INTERNET_CONNECTION;
                break;
            case 1:
                alexaMetricsName = AlexaMetricsName.MetricEvents.VOX_OFFLINE_VUI_ALEXA_DOWN.toString();
                alexaMetricsName2 = AlexaMetricsName.OfflinePromptPlayed.ALEXA_IS_DOWN;
                break;
            case 2:
                alexaMetricsName = AlexaMetricsName.MetricEvents.VOX_OFFLINE_VUI_LOST_CONNECTION.toString();
                alexaMetricsName2 = AlexaMetricsName.OfflinePromptPlayed.INTERNET_CONNECTION_LOST;
                break;
            case 3:
                alexaMetricsName = AlexaMetricsName.MetricEvents.VOX_OFFLINE_VUI_NETWORK_LOW_BANDWIDTH.toString();
                alexaMetricsName2 = AlexaMetricsName.OfflinePromptPlayed.NETWORK_LOW_BANDWIDTH;
                break;
            case 4:
                alexaMetricsName = AlexaMetricsName.MetricEvents.VOX_OFFLINE_VUI_NETWORK_TRANSITION_AUTO.toString();
                alexaMetricsName2 = AlexaMetricsName.OfflinePromptPlayed.NETWORK_TRANSITION_AUTO;
                break;
            case 5:
                alexaMetricsName = AlexaMetricsName.MetricEvents.VOX_OFFLINE_VUI_NETWORK_TRANSITION_NON_AUTO.toString();
                alexaMetricsName2 = AlexaMetricsName.OfflinePromptPlayed.NETWORK_TRANSITION_NON_AUTO;
                break;
            case 6:
                alexaMetricsName = AlexaMetricsName.MetricEvents.VOX_OFFLINE_VUI_CONNECTIVITY_ISSUE_AUTO.toString();
                alexaMetricsName2 = AlexaMetricsName.OfflinePromptPlayed.CONNECTIVITY_ISSUE_AUTO;
                break;
            case 7:
                alexaMetricsName = AlexaMetricsName.MetricEvents.VOX_OFFLINE_VUI_CONNECTIVITY_ISSUE_NON_AUTO.toString();
                alexaMetricsName2 = AlexaMetricsName.OfflinePromptPlayed.CONNECTIVITY_ISSUE_NON_AUTO;
                break;
            default:
                alexaMetricsName = null;
                alexaMetricsName2 = null;
                break;
        }
        if (alexaMetricsName != null && alexaMetricsName2 != null) {
            zZm(alexaMetricsName, (Map<String, Object>) null);
            zZm(alexaMetricsName2, createOne, AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
        }
    }

    @Subscribe
    public synchronized void on(DlG dlG) {
        Bundle bundle = new Bundle();
        zZm(AlexaMetricsName.OfflinePromptPlayed.LOW_BANDWIDTH_VALUE, AlexaMetricsCount.create(((XWd) dlG).BIo), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(TTH tth) {
        int ordinal = ((nAN) tth).BIo.ordinal();
        if (ordinal != 0 && ordinal != 1) {
            if (((nAN) tth).jiA) {
                zZm(AlexaMetricsConstants.MetricEvents.VOX_FAILURE, (Map<String, Object>) null);
                zZm(AlexaMetricsName.UserInitiatedSpeechRequest.FAILED, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
            }
        } else {
            zZm(AlexaMetricsConstants.MetricEvents.VOX_UNSUPPORTED_DIRECTIVE, (Map<String, Object>) null);
            zZm(AlexaMetricsName.Operational.UNSUPPORTED_DIRECTIVE_RECEIVED, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
        }
        AlexaMetricsCount createOne = AlexaMetricsCount.createOne();
        Bundle bundle = new Bundle();
        nAN nan = (nAN) tth;
        bundle.putString(AlexaMetadataBundleKey.SDK_EXCEPTION_ERROR_TYPE.name(), nan.BIo.name());
        bundle.putString(AlexaMetadataBundleKey.SDK_EXCEPTION_ERROR_MESSAGE.name(), nan.zQM);
        bundle.putString(AlexaMetadataBundleKey.SDK_EXCEPTION_UNPARSED_DIRECTIVE.name(), nan.zyO);
        bundle.putString(AlexaMetadataBundleKey.SDK_EXCEPTION_USER_FACING.name(), Boolean.toString(nan.jiA));
        zZm(AlexaMetricsName.ExceptionEncountered.SDK, createOne, AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(wob wobVar) {
        StringBuilder zZm2 = C0179Pya.zZm(AlexaMetricsConstants.MetricEvents.VOX_FAILURE_AVS_ERROR_PREFIX);
        zZm2.append(((ies) wobVar).BIo);
        BIo(zZm2.toString());
        zZm(AlexaMetricsName.Operational.AVS_REQUEST_ERROR.postfix(String.valueOf(((ies) wobVar).BIo)), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(paE pae) {
        nLZ nlz = ((C0240yDN) pae).BIo;
        Bundle bundle = new Bundle();
        zZm(bundle, nlz);
        zZm(AlexaMetricsName.AudioPlayerTime.BETWEEN_PLAYBACK_STARTING_AND_PLAYBACK_STARTED, AlexaMetricsTime.create(nlz.Mlj), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
        if (nlz.lOf != 0) {
            Bundle bundle2 = new Bundle();
            zZm(bundle2, nlz);
            zZm(AlexaMetricsName.AudioPlayerTime.BETWEEN_LAST_PLAY_ITEM_FINISHED_AND_PLAYBACK_STARTED, AlexaMetricsTime.create(nlz.lOf), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle2);
        }
    }

    @Subscribe
    public synchronized void on(psG psg) {
        nLZ nlz = ((JEP) psg).BIo;
        Bundle bundle = new Bundle();
        zZm(bundle, nlz);
        zZm(AlexaMetricsName.AudioPlayer.BUFFERING_COUNT, AlexaMetricsCount.create(nlz.HvC), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
        Bundle bundle2 = new Bundle();
        zZm(bundle2, nlz);
        zZm(AlexaMetricsName.AudioPlayer.BUFFERING_TIME, AlexaMetricsTime.create(nlz.dMe), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle2);
    }

    @Subscribe
    public synchronized void on(glM glm) {
        nLZ nlz = ((UfY) glm).BIo;
        Bundle bundle = new Bundle();
        zZm(bundle, nlz);
        zZm(AlexaMetricsName.AudioPlayerTime.BETWEEN_PLAYBACK_RESUMING_AND_PLAYBACK_RESUMED, AlexaMetricsTime.create(nlz.zzR), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(PMW pmw) {
        MqA mqA;
        String str = null;
        int ordinal = ((MqA) pmw).BIo.ordinal();
        if (ordinal == 0) {
            Exception exc = ((MqA) pmw).zQM;
            if (exc == null) {
                str = AlexaMetricsName.DownchannelEstablishmentFailure.IO_EXCEPTION.getValue();
            } else {
                str = AlexaMetricsName.DownchannelEstablishmentFailure.IO_EXCEPTION.appendWith(exc.getClass().getSimpleName());
            }
        } else if (ordinal == 1) {
            str = AlexaMetricsName.DownchannelEstablishmentFailure.AVS_FAILURE.getValue();
            if (((MqA) pmw).zyO != null) {
                str = str + "_" + mqA.zyO;
            }
        } else if (ordinal == 2) {
            str = AlexaMetricsName.DownchannelEstablishmentFailure.NO_NETWORK.getValue();
        } else if (ordinal == 3) {
            str = AlexaMetricsName.Downchannel.REAL_AUTHORIZATION_FAILURE.getValue();
        }
        if (str != null) {
            zQM(str);
        }
    }

    @Subscribe
    public synchronized void on(acC acc) {
        zQM(AlexaMetricsName.Downchannel.FALSE_AUTHORIZATION_FAILURE.getValue());
    }

    @Subscribe
    public synchronized void on(Ppr ppr) {
        String str = null;
        if (((IlB) ppr).zQM) {
            int ordinal = ((IlB) ppr).BIo.ordinal();
            if (ordinal == 0) {
                str = AlexaMetricsName.SettingsUpdate.SEND_TIMEZONE_SUCCEEDED.getValue();
            } else if (ordinal == 1) {
                str = AlexaMetricsName.SettingsUpdate.SEND_LOCALE_SUCCEEDED.getValue();
            } else if (ordinal == 2) {
                str = AlexaMetricsName.SettingsUpdate.SEND_SUPPORTS_MOBILE_DOWNCHANNEL_SUCCEEDED.getValue();
            }
        } else {
            IlB ilB = (IlB) ppr;
            int ordinal2 = ilB.BIo.ordinal();
            if (ordinal2 == 0) {
                str = AlexaMetricsName.SettingsUpdate.SEND_TIMEZONE_FAILED.getValue();
            } else if (ordinal2 == 1) {
                str = AlexaMetricsName.SettingsUpdate.SEND_LOCALE_FAILED.getValue();
            } else if (ordinal2 == 2) {
                str = AlexaMetricsName.SettingsUpdate.SEND_SUPPORTS_MOBILE_DOWNCHANNEL_FAILED.getValue();
            }
            if (ilB.zyO != null) {
                str = str + "_" + ilB.zyO;
            }
        }
        if (str != null) {
            zQM(str);
        } else {
            StringBuilder zZm2 = C0179Pya.zZm("Invalid SettingsUpdateType is received: ");
            zZm2.append(((IlB) ppr).BIo.name());
            zZm2.toString();
        }
    }

    @Subscribe
    public synchronized void on(MwJ mwJ) {
        AlexaMetricsCount createOne = AlexaMetricsCount.createOne();
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.AVS_EXCEPTION_CODE.name(), ((xyS) mwJ).BIo.toString());
        bundle.putString(AlexaMetadataBundleKey.AVS_EXCEPTION_DESCRIPTION.name(), ((xyS) mwJ).zQM);
        zZm(AlexaMetricsName.ExceptionEncountered.AVS, createOne, AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009a, code lost:
        if (r9.getBoolean(com.amazon.alexa.api.AlexaMetadataBundleKey.SUCCESS.name()) != false) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0046 A[Catch: all -> 0x00b4, TryCatch #0 {, blocks: (B:3:0x0001, B:4:0x0012, B:8:0x001f, B:18:0x0038, B:25:0x004b, B:26:0x0062, B:27:0x006e, B:40:0x00a1, B:41:0x00a4, B:30:0x0074, B:33:0x0081, B:36:0x008e, B:19:0x003d, B:20:0x0040, B:21:0x0043, B:22:0x0046, B:6:0x0016, B:7:0x001a), top: B:47:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004b A[Catch: all -> 0x00b4, TryCatch #0 {, blocks: (B:3:0x0001, B:4:0x0012, B:8:0x001f, B:18:0x0038, B:25:0x004b, B:26:0x0062, B:27:0x006e, B:40:0x00a1, B:41:0x00a4, B:30:0x0074, B:33:0x0081, B:36:0x008e, B:19:0x003d, B:20:0x0040, B:21:0x0043, B:22:0x0046, B:6:0x0016, B:7:0x001a), top: B:47:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0062 A[Catch: all -> 0x00b4, TryCatch #0 {, blocks: (B:3:0x0001, B:4:0x0012, B:8:0x001f, B:18:0x0038, B:25:0x004b, B:26:0x0062, B:27:0x006e, B:40:0x00a1, B:41:0x00a4, B:30:0x0074, B:33:0x0081, B:36:0x008e, B:19:0x003d, B:20:0x0040, B:21:0x0043, B:22:0x0046, B:6:0x0016, B:7:0x001a), top: B:47:0x0001 }] */
    @org.greenrobot.eventbus.Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void on(com.amazon.alexa.AQg r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            r0 = r9
            com.amazon.alexa.vPD r0 = (com.amazon.alexa.C0236vPD) r0     // Catch: java.lang.Throwable -> Lb4
            com.amazon.alexa.api.UiEventName r0 = r0.BIo     // Catch: java.lang.Throwable -> Lb4
            com.amazon.alexa.vPD r9 = (com.amazon.alexa.C0236vPD) r9     // Catch: java.lang.Throwable -> Lb4
            android.os.Bundle r9 = r9.zQM     // Catch: java.lang.Throwable -> Lb4
            int[] r1 = com.amazon.alexa.Ebf.zyO     // Catch: java.lang.Throwable -> Lb4
            int r2 = r0.ordinal()     // Catch: java.lang.Throwable -> Lb4
            r1 = r1[r2]     // Catch: java.lang.Throwable -> Lb4
            switch(r1) {
                case 1: goto L1a;
                case 2: goto L1a;
                case 3: goto L16;
                case 4: goto L16;
                case 5: goto L16;
                case 6: goto L16;
                default: goto L15;
            }     // Catch: java.lang.Throwable -> Lb4
        L15:
            goto L1f
        L16:
            r8.zZm(r0, r9)     // Catch: java.lang.Throwable -> Lb4
            goto L1f
        L1a:
            r8.zZm(r0, r9)     // Catch: java.lang.Throwable -> Lb4
            goto Lb2
        L1f:
            int[] r1 = com.amazon.alexa.Ebf.zyO     // Catch: java.lang.Throwable -> Lb4
            int r2 = r0.ordinal()     // Catch: java.lang.Throwable -> Lb4
            r1 = r1[r2]     // Catch: java.lang.Throwable -> Lb4
            r2 = 11
            if (r1 == r2) goto L46
            r2 = 12
            if (r1 == r2) goto L43
            r2 = 4
            if (r1 == r2) goto L40
            r2 = 5
            if (r1 == r2) goto L3d
            r2 = 6
            if (r1 == r2) goto L3d
            com.amazon.alexa.api.AlexaMetricsName r1 = r0.getMetricsName()     // Catch: java.lang.Throwable -> Lb4
            goto L48
        L3d:
            com.amazon.alexa.api.AlexaMetricsName r1 = com.amazon.alexa.api.AlexaMetricsName.UiEvents.CARD_RENDER_SUCCESSFUL     // Catch: java.lang.Throwable -> Lb4
            goto L48
        L40:
            com.amazon.alexa.api.AlexaMetricsName r1 = com.amazon.alexa.api.AlexaMetricsName.UiEvents.JSON_PARSING_VALID_JSON     // Catch: java.lang.Throwable -> Lb4
            goto L48
        L43:
            com.amazon.alexa.api.AlexaMetricsName r1 = com.amazon.alexa.api.AlexaMetricsName.UiEvents.CARD_MATCHING_SUCCESS     // Catch: java.lang.Throwable -> Lb4
            goto L48
        L46:
            com.amazon.alexa.api.AlexaMetricsName r1 = com.amazon.alexa.api.AlexaMetricsName.UiEvents.CARD_MATCHING_SUCCESS     // Catch: java.lang.Throwable -> Lb4
        L48:
            r3 = r1
            if (r3 != 0) goto L62
            java.lang.String r9 = com.amazon.alexa.Fsz.zZm     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r1 = "Unknown metric: "
            java.lang.StringBuilder r1 = com.amazon.alexa.C0179Pya.zZm(r1)     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r0 = r0.name()     // Catch: java.lang.Throwable -> Lb4
            r1.append(r0)     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> Lb4
            android.util.Log.w(r9, r0)     // Catch: java.lang.Throwable -> Lb4
            goto Lb2
        L62:
            r1 = 1
            com.amazon.alexa.api.AlexaMetricsCategory r2 = com.amazon.alexa.api.AlexaMetricsCategory.BUSINESS     // Catch: java.lang.Throwable -> Lb4
            int[] r4 = com.amazon.alexa.Ebf.zyO     // Catch: java.lang.Throwable -> Lb4
            int r0 = r0.ordinal()     // Catch: java.lang.Throwable -> Lb4
            r0 = r4[r0]     // Catch: java.lang.Throwable -> Lb4
            r4 = 0
            switch(r0) {
                case 3: goto L9f;
                case 4: goto L8e;
                case 5: goto L81;
                case 6: goto L8e;
                case 7: goto L74;
                case 8: goto L9f;
                case 9: goto L9f;
                case 10: goto L9f;
                case 11: goto L72;
                default: goto L71;
            }     // Catch: java.lang.Throwable -> Lb4
        L71:
            goto La1
        L72:
            r1 = r4
            goto La1
        L74:
            com.amazon.alexa.api.AlexaMetadataBundleKey r0 = com.amazon.alexa.api.AlexaMetadataBundleKey.CARD_WAS_INTERACTED_WITH     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r0 = r0.name()     // Catch: java.lang.Throwable -> Lb4
            boolean r0 = r9.getBoolean(r0)     // Catch: java.lang.Throwable -> Lb4
            if (r0 != 0) goto L9f
            goto L9c
        L81:
            com.amazon.alexa.api.AlexaMetadataBundleKey r0 = com.amazon.alexa.api.AlexaMetadataBundleKey.SUCCESS     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r0 = r0.name()     // Catch: java.lang.Throwable -> Lb4
            boolean r0 = r9.getBoolean(r0)     // Catch: java.lang.Throwable -> Lb4
            if (r0 == 0) goto L8e
            goto Lb2
        L8e:
            com.amazon.alexa.api.AlexaMetricsCategory r2 = com.amazon.alexa.api.AlexaMetricsCategory.OPERATIONAL     // Catch: java.lang.Throwable -> Lb4
            com.amazon.alexa.api.AlexaMetadataBundleKey r0 = com.amazon.alexa.api.AlexaMetadataBundleKey.SUCCESS     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r0 = r0.name()     // Catch: java.lang.Throwable -> Lb4
            boolean r0 = r9.getBoolean(r0)     // Catch: java.lang.Throwable -> Lb4
            if (r0 != 0) goto L9f
        L9c:
            r5 = r2
            r1 = r4
            goto La4
        L9f:
            r5 = r2
            goto La4
        La1:
            com.amazon.alexa.api.AlexaMetricsCategory r0 = com.amazon.alexa.api.AlexaMetricsCategory.OPERATIONAL     // Catch: java.lang.Throwable -> Lb4
            r5 = r0
        La4:
            com.amazon.alexa.api.AlexaMetricsCount r4 = com.amazon.alexa.api.AlexaMetricsCount.create(r1)     // Catch: java.lang.Throwable -> Lb4
            com.amazon.alexa.api.AlexaMetricsType r6 = com.amazon.alexa.api.AlexaMetricsType.COUNTER     // Catch: java.lang.Throwable -> Lb4
            android.os.Bundle r7 = r8.BIo(r9)     // Catch: java.lang.Throwable -> Lb4
            r2 = r8
            r2.zZm(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> Lb4
        Lb2:
            monitor-exit(r8)
            return
        Lb4:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.Fsz.on(com.amazon.alexa.AQg):void");
    }

    @Subscribe
    public synchronized void on(AbstractC0173MQv abstractC0173MQv) {
        zZm(AlexaMetricsName.Operational.SEND_ATTACHMENT_FAILURE.appendWith(((aOP) abstractC0173MQv).BIo.name()), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(qqU qqu) {
        zZm(AlexaMetricsName.AudioPlayer.BUFFERING_TIMEOUT, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(SuC suC) {
        Bundle bundle = new Bundle();
        qib qibVar = (qib) suC;
        bundle.putBoolean(AlexaMetadataBundleKey.SUCCESS.name(), qibVar.zyO);
        zZm(AlexaMetricsName.ContextProviderGet.LATENCY_PREFIX.postfix(qibVar.BIo.getValue()), AlexaMetricsTime.create(qibVar.zQM), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.TIMER, bundle);
        zZm((qibVar.zyO ? AlexaMetricsName.ContextProviderGet.SUCCESS_PREFIX : AlexaMetricsName.ContextProviderGet.FAILURE_PREFIX).postfix(qibVar.BIo.getValue()), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(NZj nZj) {
        zZm(AlexaMetricsName.ExternalComponentState.INSERT_LATENCY, AlexaMetricsTime.create(((ZBY) nZj).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(yvr yvrVar) {
        zZm(AlexaMetricsName.ExternalComponentState.QUERY_LATENCY, AlexaMetricsTime.create(((YiY) yvrVar).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(JjA jjA) {
        zZm(AlexaMetricsName.ExternalComponentState.DELETE_LATENCY, AlexaMetricsTime.create(((WXz) jjA).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(BaP.zZm zzm) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Voice Interaction attempt: ");
        zZm2.append(((Qbg) zzm).BIo);
        zZm2.append(" version: ");
        Qbg qbg = (Qbg) zzm;
        zZm2.append(qbg.jiA);
        Log.i(str, zZm2.toString());
        Bundle bundle = new Bundle();
        String str2 = qbg.jiA;
        if (str2 != null) {
            bundle.putString("firmware_accessory", str2);
            bundle.putString(AlexaMetadataBundleKey.SOFTWARE_VERSION.name(), qbg.jiA);
        }
        bundle.putString(AlexaMetadataBundleKey.DIALOG_REQUEST_ID.name(), qbg.zyO.getValue());
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name(), qbg.zQM);
        zZm(qbg.BIo, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(ARM.zZm zzm) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Text Interaction attempt: ");
        zZm2.append(((zaQ) zzm).BIo);
        zZm2.append(" version: ");
        zaQ zaq = (zaQ) zzm;
        zZm2.append(zaq.jiA);
        Log.i(str, zZm2.toString());
        Bundle bundle = new Bundle();
        String str2 = zaq.jiA;
        if (str2 != null) {
            bundle.putString("firmware_accessory", str2);
            bundle.putString(AlexaMetadataBundleKey.SOFTWARE_VERSION.name(), zaq.jiA);
        }
        bundle.putString(AlexaMetadataBundleKey.DIALOG_REQUEST_ID.name(), zaq.zyO.getValue());
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name(), zaq.zQM);
        zZm(zaq.BIo, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(BaP.zQM zqm) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Voice Interaction result: ");
        mqC mqc = (mqC) zqm;
        zZm2.append(mqc.BIo);
        Log.i(str, zZm2.toString());
        Bundle bundle = new Bundle();
        Map<String, String> map = mqc.jiA;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
        }
        bundle.putString(AlexaMetadataBundleKey.DIALOG_REQUEST_ID.name(), mqc.zyO.getValue());
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name(), mqc.zQM);
        zZm(mqc.BIo, AlexaMetricsTime.create(mqc.Qle), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(ARM.BIo bIo) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Text Interaction result: ");
        uBu ubu = (uBu) bIo;
        zZm2.append(ubu.BIo);
        Log.i(str, zZm2.toString());
        Bundle bundle = new Bundle();
        Map<String, String> map = ubu.jiA;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
        }
        bundle.putString(AlexaMetadataBundleKey.DIALOG_REQUEST_ID.name(), ubu.zyO.getValue());
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name(), ubu.zQM);
        zZm(ubu.BIo, AlexaMetricsTime.create(ubu.Qle), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(BaP.BIo bIo) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Voice Interaction progress: ");
        zZm2.append(((hZA) bIo).BIo);
        Log.i(str, zZm2.toString());
        zZm(((hZA) bIo).BIo, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(vUW vuw) {
        Bundle bundle = new Bundle();
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("AlexaLauncher event outcome: ");
        syk sykVar = (syk) vuw;
        zZm2.append(sykVar.zyO.name());
        Log.i(str, zZm2.toString());
        bundle.putString(AlexaMetadataBundleKey.EVENT_VALUE.name(), sykVar.zyO.name());
        bundle.putString(AlexaMetricsConstants.Launcher.OUTCOME, sykVar.zyO.name());
        bundle.putString(AlexaMetadataBundleKey.REQUEST_ID.name(), sykVar.zQM.getValue());
        bundle.putString(AlexaMetricsConstants.Launcher.TOKEN, sykVar.zQM.getValue());
        String zZm3 = tkT.zZm(sykVar.jiA);
        bundle.putString(AlexaMetadataBundleKey.STATUS_CODE.name(), zZm3);
        bundle.putString(AlexaMetricsConstants.Launcher.REASONS, zZm3);
        bundle.putString(AlexaMetadataBundleKey.SOURCE.name(), ((AAX) sykVar.BIo).BIo.getValue());
        bundle.putString("contentProvider", ((AAX) sykVar.BIo).BIo.getValue());
        bundle.putString(AlexaMetadataBundleKey.TITLE.name(), ((AAX) sykVar.BIo).jiA);
        bundle.putString(AlexaMetricsConstants.Launcher.TARGET, ((AAX) sykVar.BIo).jiA);
        zZm(sykVar.Qle, AlexaMetricsCount.createOne(), AlexaMetricsCategory.BUSINESS, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(HdS.zZm zzm) {
        String str = zZm;
        Log.i(str, "AudioPlayer Connectivity Failure Resume Event: " + zzm);
        zZm(((oom) zzm).BIo ? AlexaMetricsName.AudioPlayer.CONNECTIVITY_FAILURE_RESUME_TIMEOUT : AlexaMetricsName.AudioPlayer.CONNECTIVITY_FAILURE_RESUME_SUCCESS, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(UyX uyX) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.EXTERNAL_COMPONENT_STATE_PROVIDERS_COUNT.name(), String.valueOf(((fqV) uyX).zQM));
        zZm(AlexaMetricsName.ExternalComponentState.GATHERING_LATENCY, AlexaMetricsTime.create(((fqV) uyX).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(UyS uyS) {
        zZm(AlexaMetricsName.ExternalComponentState.COUNT_EXCEEDED, AlexaMetricsCount.create(((fpL) uyS).BIo), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(bdJ bdj) {
        Object[] objArr = new Object[2];
        objArr[0] = ((FeU) bdj).BIo.getValue();
        FeU feU = (FeU) bdj;
        objArr[1] = feU.zQM ? "SUCCESS" : "FAILED";
        zZm(AlexaMetricsName.ExternalMediaPlayer.MEDIA_BROWSER_CONNECTION_STATUS.appendWith(String.format("%s.%s.ATTEMPTS", objArr)), AlexaMetricsCount.create(feU.zyO), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(HdS.BIo bIo) {
        String str = zZm;
        Log.i(str, "AudioPlayer Play Attempted Event: " + bIo);
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.PLAY_TOKEN.name(), ((Psd) bIo).BIo.mo947BIo().zZm);
        bundle.putString(AlexaMetadataBundleKey.URL_DOMAIN.name(), ((Psd) bIo).BIo.zQM.getHost());
        zZm(AlexaMetricsName.AudioPlayer.PLAY_ATTEMPT, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(HdS.zQM zqm) {
        String str = zZm;
        Log.i(str, "AudioPlayer Play Failed Event: " + zqm);
        AlexaMetricsName alexaMetricsName = ((wDd) zqm).zQM ? AlexaMetricsName.AudioPlayer.PLAY_FAILURE_CONNECTIVITY : AlexaMetricsName.AudioPlayer.PLAY_FAILURE;
        Bundle bundle = new Bundle();
        wDd wdd = (wDd) zqm;
        bundle.putString(AlexaMetadataBundleKey.PLAY_TOKEN.name(), wdd.BIo.mo947BIo().zZm);
        bundle.putString(AlexaMetadataBundleKey.URL_DOMAIN.name(), wdd.BIo.zQM.getHost());
        zZm(alexaMetricsName, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(HdS.zyO zyo) {
        String str = zZm;
        Log.i(str, "AudioPlayer Playback is slow Event: " + zyo);
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.PLAY_TOKEN.name(), ((RWT) zyo).BIo.mo947BIo().zZm);
        bundle.putString(AlexaMetadataBundleKey.URL_DOMAIN.name(), ((RWT) zyo).BIo.zQM.getHost());
        zZm(AlexaMetricsName.AudioPlayer.AUDIO_PLAYER_PLAYBACK_IS_SLOW, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(Vdm vdm) {
        zZm(AlexaMetricsName.Operational.DEPRECATED_API_USE.postfix(((AutoValue_ApiName) ((tNI) vdm).BIo).CGv), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(gKM.BIo bIo) {
        zZm(AlexaMetricsName.AccountManager.IS_LOGGED_IN_TIME_OUT, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(gKM.zZm zzm) {
        zZm(AlexaMetricsName.AccountManager.IS_LOGGED_IN_ERROR, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(UMd.zZm zzm) {
        Hvd hvd = (Hvd) zzm;
        if (!hvd.zQM) {
            zZm(AlexaMetricsName.Latency.REFRESH_EXTERNAL_CAPABILITY_PERCENTAGE_FOUND, AlexaMetricsCount.create((int) ((hvd.JTe / hvd.Qle) * 1000.0d)), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.COUNTER, new Bundle());
            if (zzm.BIo()) {
                zZm(AlexaMetricsName.Latency.REFRESH_EXTERNAL_CAPABILITY_SUCCEEDED, AlexaMetricsTime.create(((Hvd) zzm).jiA), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
            }
        }
    }

    @Subscribe
    public synchronized void on(UMd.BIo bIo) {
        zZm(AlexaMetricsName.Latency.REFRESH_EXTERNAL_CAPABILITY_TIMEDOUT, AlexaMetricsTime.create(((ppK) bIo).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(TNw tNw) {
        AWj aWj = (AWj) tNw;
        String appendWith = AlexaMetricsName.Latency.ATTENTION_SYSTEM.appendWith(aWj.BIo);
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name(), aWj.BIo);
        zZm(appendWith, AlexaMetricsTime.create(aWj.zQM), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(VXG.zyO zyo) {
        String value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_SUCCESS.getValue();
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.EVENT_VALUE.name(), ((ZBr) zyo).zQM);
        zZm(value, AlexaMetricsTime.create(((ZBr) zyo).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(VXG.zQM zqm) {
        String value;
        kBD kbd = (kBD) zqm;
        switch (kbd.BIo) {
            case -9:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE_JNI_MEMORY_POINTER_SAVING_FAILED.getValue();
                break;
            case -8:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE_AUDIO_BUFFER_ALLOCATION_FAILED.getValue();
                break;
            case -7:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE_NATIVE_MEMORY_ALLOCATION_FAILED.getValue();
                break;
            case -6:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE_JAVA_CONFIG_PARAMETER_IMPORT_FAILED.getValue();
                break;
            case -5:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE_CLASS_PROPERTIES_QUERY_FAILED.getValue();
                break;
            case -4:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE_GLOBAL_REFERENCE_CREATION_FAILED.getValue();
                break;
            case -3:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE_JNI_MEMORY_ALLOCATION_FAILED.getValue();
                break;
            case -2:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE_ALREADY_INITIALIZED.getValue();
                break;
            case -1:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE_INVALID_CONFIG_PARAMETER.getValue();
                break;
            default:
                value = AlexaMetricsName.Pryon.PRYON_INITIALIZATION_FAILURE.getValue();
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.EVENT_VALUE.name(), kbd.zQM);
        bundle.putString(AlexaMetadataBundleKey.STATUS_CODE.name(), Integer.toString(((kBD) zqm).BIo));
        zZm(value, AlexaMetricsCount.createOne(), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(VXG.jiA jia) {
        zZm(AlexaMetricsName.Pryon.PRYON_RESET.getValue(), AlexaMetricsTime.create(((NNF) jia).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(VXG.zzR zzr) {
        String value = AlexaMetricsName.WakeWordModel.WAKE_WORD_MODEL_DOWNLOAD_SUCCESS.getValue();
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.EVENT_VALUE.name(), ((NUX) zzr).zQM);
        zZm(value, AlexaMetricsTime.create(((NUX) zzr).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(VXG.Mlj mlj) {
        zZm(AlexaMetricsName.WakeWordModel.WAKE_WORD_MODEL_DOWNLOAD_INTERRUPTED.getValue(), AlexaMetricsTime.create(((UBQ) mlj).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(VXG.yPL ypl) {
        String value = AlexaMetricsName.WakeWordModel.WAKE_WORD_MODEL_DOWNLOAD_FAILURE.getValue();
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.EVENT_VALUE.name(), ((Lhs) ypl).zQM);
        Lhs lhs = (Lhs) ypl;
        bundle.putString(AlexaMetadataBundleKey.ERROR_SOURCE.name(), lhs.zyO);
        zZm(value, AlexaMetricsTime.create(lhs.BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(VXG.zZm zzm) {
        zZm(AlexaMetricsName.WakeWordModel.WAKE_WORD_MODEL_LOCALE_MISMATCH.getValue(), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(VXG.LPk lPk) {
        zZm(AlexaMetricsName.WakeWordValidation.WAKE_WORD_VALIDATION_TIMEOUT.getValue(), AlexaMetricsCount.createOne(), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(VXG.Qle qle) {
        zZm(AlexaMetricsName.WakeWordValidation.WAKE_WORD_VALIDATION_LATENCY.getValue(), AlexaMetricsTime.create(((JTw) qle).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(VXG.JTe jTe) {
        zZm(AlexaMetricsName.WakeWordValidation.WAKE_WORD_VALIDATION_SPEECH_OFFSET.getValue(), AlexaMetricsTime.create(((rIt) jTe).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(VXG.BIo bIo) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.EVENT_VALUE.name(), Integer.toString(((CQJ) bIo).BIo));
        zZm(AlexaMetricsName.Pryon.PRYON_ERROR_EVENT.getValue(), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(BjL.zQM zqm) {
        String value = AlexaMetricsName.OfflinePrompts.OFFLINE_PROMPT_DOWNLOAD_SUCCESS.getValue();
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.EVENT_VALUE.name(), ((yFV) zqm).zQM);
        zZm(value, AlexaMetricsTime.create(((yFV) zqm).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(BjL.BIo bIo) {
        zZm(AlexaMetricsName.OfflinePrompts.OFFLINE_PROMPT_DOWNLOAD_INTERRUPTED.getValue(), AlexaMetricsTime.create(((RgD) bIo).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(BjL.zZm zzm) {
        String value = AlexaMetricsName.OfflinePrompts.OFFLINE_PROMPT_DOWNLOAD_FAILURE.getValue();
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.EVENT_VALUE.name(), ((yFh) zzm).zQM);
        yFh yfh = (yFh) zzm;
        bundle.putString(AlexaMetadataBundleKey.ERROR_SOURCE.name(), yfh.zyO);
        zZm(value, AlexaMetricsTime.create(yfh.BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public void on(Fwh.zQM zqm) {
        String str = zZm;
        Log.i(str, "ExternalCapabilityAgentMetricEvent.ECAScanSuccessEvent: " + zqm);
        Bundle bundle = new Bundle();
        XOn xOn = (XOn) zqm;
        bundle.putString(AlexaMetadataBundleKey.CAPABILITY_PACKAGE.name(), xOn.zQM);
        bundle.putString(AlexaMetadataBundleKey.CAPABILITY_SERVICE.name(), xOn.BIo);
        zZm(AlexaMetricsName.ExternalCapabilityAgents.ECA_SCAN_SUCCESS.getValue(), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public void on(Fwh.BIo bIo) {
        String str = zZm;
        Log.i(str, "ExternalCapabilityAgentMetricEvent.ECAScanFailureEvent: " + bIo);
        Bundle bundle = new Bundle();
        cIb cib = (cIb) bIo;
        bundle.putString(AlexaMetadataBundleKey.CAPABILITY_PACKAGE.name(), cib.zQM);
        bundle.putString(AlexaMetadataBundleKey.CAPABILITY_SERVICE.name(), cib.BIo);
        zZm(AlexaMetricsName.ExternalCapabilityAgents.ECA_SCAN_FAILURE.getValue(), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public void on(Fwh.zZm zzm) {
        String str = zZm;
        Log.i(str, "ExternalCapabilityAgentMetricEvent.ECAMultipleAgentsErrorEvent: " + zzm);
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.CAPABILITY_NAMESPACE.name(), ((jLE) zzm).BIo.getValue());
        zZm(AlexaMetricsName.ExternalCapabilityAgents.ECA_MULTIPLE_REGISTERED_ERROR.getValue(), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(WbI wbI) {
        String str;
        Bundle bundle = new Bundle();
        MTI mti = (MTI) wbI;
        DialogRequestIdentifier dialogRequestIdentifier = mti.zQM;
        bundle.putString(AlexaMetadataBundleKey.DIALOG_REQUEST_ID.name(), dialogRequestIdentifier.getValue());
        bundle.putString(AlexaMetadataBundleKey.NETWORK_TYPE.name(), this.jiA.getNetworkType());
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name(), mti.BIo);
        if (mti.LPk) {
            str = C0179Pya.zZm(new StringBuilder(), mti.BIo, ".OPUS");
        } else {
            str = mti.BIo;
        }
        String appendWith = AlexaMetricsName.Latency.VUI_USER_PERCEIVED.appendWith(str);
        zZm(appendWith, AlexaMetricsTime.create(mti.JTe), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, bundle);
        String str2 = zZm;
        Log.i(str2, "Enhanced UPL Recording " + appendWith + RealTimeTextConstants.COLON_SPACE + mti.JTe);
        MTI mti2 = (MTI) wbI;
        long j = mti2.Qle;
        XWx xWx = mti2.zyO;
        long j2 = mti2.jiA;
        long j3 = mti.JTe;
        long epochTime = this.JTe.toEpochTime(j);
        Iterator<bNQ> it2 = this.LPk.zZm().iterator();
        while (it2.hasNext()) {
            bNQ next = it2.next();
            Iterator<bNQ> it3 = it2;
            long j4 = j3;
            long j5 = j2;
            DialogRequestIdentifier dialogRequestIdentifier2 = dialogRequestIdentifier;
            try {
                next.zZm(xWx, dialogRequestIdentifier, 0L, j2, j4, epochTime);
            } catch (RemoteException e) {
                Log.e(zZm, "Remote exception while updating UPL listener", e);
                this.zQM.zyO(xZV.zZm(this.LPk.BIo((Shr<bNQ>) next)));
            }
            it2 = it3;
            j3 = j4;
            j2 = j5;
            dialogRequestIdentifier = dialogRequestIdentifier2;
        }
    }

    @Subscribe
    public synchronized void on(jsd.BIo bIo) {
        zZm(AlexaMetricsName.MicInitialization.SUCCESS.getValue(), AlexaMetricsTime.create(((zjk) bIo).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(jsd.zZm zzm) {
        zZm(AlexaMetricsName.MicInitialization.FAILURE.getValue(), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(XnZ.BIo bIo) {
        zZm(AlexaMetricsName.ReadinessTime.NOT_READY_TO_QUICK_READY, AlexaMetricsTime.create(((QEq) bIo).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(XnZ.zZm zzm) {
        zZm(AlexaMetricsName.ReadinessTime.NOT_READY_TO_READY, AlexaMetricsTime.create(((VLd) zzm).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(XnZ.zQM zqm) {
        zZm(AlexaMetricsName.ReadinessTime.QUICK_READY_TO_READY, AlexaMetricsTime.create(((WRN) zqm).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(zeW zew) {
        zZm(AlexaMetricsName.SpeechMarks.TTS_PARSING_EXCEPTION, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(AbstractC0175Mka abstractC0175Mka) {
        zZm(AlexaMetricsName.SpeechMarks.TTS_UNSUPPORTED_MP3_FRAME, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(GSJ gsj) {
        zZm(((RwV) gsj).BIo, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(HtI htI) {
        zZm(((wCw) htI).BIo, AlexaMetricsTime.create(((wCw) htI).zQM), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(iRJ irj) {
        String value;
        if (((MKA) irj).BIo) {
            value = AlexaMetricsName.SynchronizeState.SYNCHRONIZE_STATE_FAILED_ON_DOWNCHANNEL_ESTABLISHMENT.getValue();
        } else {
            value = AlexaMetricsName.SynchronizeState.SYNCHRONIZE_STATE_FAILED.getValue();
        }
        zQM(value + "_" + ((MKA) irj).zQM);
    }

    @Subscribe
    public synchronized void on(Bob bob) {
        if (bob.BIo()) {
            long j = this.JXl;
            if (j > 0) {
                zZm(AlexaMetricsName.Downchannel.AVAILABLE_LATENCY, AlexaMetricsTime.create(bob.zZm - j), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
            }
        }
        this.JXl = 0L;
    }

    @Subscribe
    public synchronized void on(yHQ yhq) {
        if (((dpf) yhq).BIo) {
            long j = this.JXl;
            if (j >= 0) {
                zZm(AlexaMetricsName.Downchannel.CONNECTION_LATENCY, AlexaMetricsTime.create(yhq.zZm - j), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
            }
        }
    }

    @Subscribe
    public synchronized void on(Ued.BIo bIo) {
        gAT gat = (gAT) bIo;
        zZm(gat.BIo, AlexaMetricsTime.create(gat.zQM), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(Ued.zZm zzm) {
        Bundle bundle = new Bundle();
        if (MNR.zZm != ((ELT) zzm).zQM) {
            bundle.putString(AlexaMetadataBundleKey.API_CALL_ID.name(), ((C0185YfC) ((ELT) zzm).zQM).BIo);
        }
        zZm(((ELT) zzm).BIo, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(ICG.BIo bIo) {
        StringBuilder sb = new StringBuilder();
        sb.append(((whK) bIo).BIo.name());
        sb.append(".");
        whK whk = (whK) bIo;
        sb.append(whk.zQM);
        String appendWith = AlexaMetricsName.VoiceInteraction.Recoverable.DOWNCHANNEL_CONNECTED.appendWith(sb.toString());
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name(), whk.zQM);
        zZm(appendWith, AlexaMetricsTime.create(whk.zyO), AlexaMetricsCategory.BUSINESS, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(ICG.zZm zzm) {
        StringBuilder sb = new StringBuilder();
        sb.append(((spO) zzm).BIo.name());
        sb.append(".");
        spO spo = (spO) zzm;
        sb.append(spo.zQM);
        String appendWith = AlexaMetricsName.VoiceInteraction.Recoverable.DOWNCHANNEL_AVAILABLE.appendWith(sb.toString());
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.USER_SPEECH_INVOCATION_TYPE.name(), spo.zQM);
        zZm(appendWith, AlexaMetricsTime.create(spo.zyO), AlexaMetricsCategory.BUSINESS, AlexaMetricsType.TIMER, bundle);
    }

    @Subscribe
    public synchronized void on(MyZ myZ) {
        this.XWf.execute(new Fnd(this.zQM, this.NXS));
    }

    @Subscribe
    public synchronized void on(pPw.zZm.BIo bIo) {
        Bundle bundle = new Bundle();
        Rqk rqk = (Rqk) bIo;
        if (!TextUtils.isEmpty(rqk.zQM)) {
            bundle.putString(AlexaMetadataBundleKey.SOURCE.name(), rqk.zQM);
        }
        zZm(AlexaMetricsName.CapabilityAgentInteraction.DIRECTIVE_DELIVERY.appendToAlexaMetricsName(rqk.BIo.name()).appendWith("SUCCESS"), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(pPw.zZm.AbstractC0034zZm abstractC0034zZm) {
        Bundle bundle = new Bundle();
        Ybj ybj = (Ybj) abstractC0034zZm;
        if (!TextUtils.isEmpty(ybj.zyO)) {
            bundle.putString(AlexaMetadataBundleKey.SOURCE.name(), ybj.zyO);
        }
        zZm(AlexaMetricsName.CapabilityAgentInteraction.DIRECTIVE_DELIVERY.appendToAlexaMetricsName(ybj.BIo.name()).appendToAlexaMetricsName(Event.FAILURE).appendWith(((Ybj) abstractC0034zZm).zQM.name()), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(OUQ ouq) {
        zZm(AlexaMetricsName.Android.LOUDNESS_ENHANCER_UNSUPPORTED, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(NEv.BIo bIo) {
        eOP BIo = bIo.BIo();
        if (BIo.zZm()) {
            return;
        }
        Bundle bundle = new Bundle();
        vhv vhvVar = (vhv) BIo;
        bundle.putString(AlexaMetadataBundleKey.API_CALL_ID.name(), ((C0185YfC) vhvVar.BIo).BIo);
        zZm(AlexaMetricsName.SdkUsage.ApiCalls.Service.ATTEMPT.injectWith(((AutoValue_ApiName) vhvVar.zQM).CGv), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(NEv.zQM zqm) {
        eOP eop = ((ptH) zqm).BIo;
        if (eop.zZm()) {
            return;
        }
        Bundle bundle = new Bundle();
        vhv vhvVar = (vhv) eop;
        bundle.putString(AlexaMetadataBundleKey.API_CALL_ID.name(), ((C0185YfC) vhvVar.BIo).BIo);
        zZm(AlexaMetricsName.SdkUsage.ApiCalls.Service.SUCCESS.injectWith(((AutoValue_ApiName) vhvVar.zQM).CGv), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(NEv.zZm zzm) {
        eOP eop = ((cUA) zzm).BIo;
        if (eop.zZm()) {
            return;
        }
        Bundle bundle = new Bundle();
        vhv vhvVar = (vhv) eop;
        bundle.putString(AlexaMetadataBundleKey.API_CALL_ID.name(), ((C0185YfC) vhvVar.BIo).BIo);
        zZm(AlexaMetricsName.SdkUsage.ApiCalls.Service.FAILURE.injectWith(((AutoValue_ApiName) vhvVar.zQM).CGv).appendToAlexaMetricsName(((cUA) zzm).zQM.getFailureType().name()), AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }

    @Subscribe
    public synchronized void on(NEv.zyO zyo) {
        zZm(AlexaMetricsName.SdkUsage.ApiCalls.Service.TEARDOWN, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(ZZE zze) {
        zZm(AlexaMetricsName.Latency.FEATURE_FLAG_LOAD_LATENCY, AlexaMetricsTime.create(((Emg) zze).BIo), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.TIMER, new Bundle());
    }

    @Subscribe
    public synchronized void on(Ega ega) {
        zZm(AlexaMetricsName.ExternalCapabilityAgents.MISSED_EXTERNAL_CAPABILITY, AlexaMetricsCount.create(((rJR) ega).BIo), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(jLK jlk) {
        zZm(AlexaMetricsName.AccountManager.LOGGED_OUT_WHEN_SENDING_EVENT, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(XFF xff) {
        int i = ((SFM) xff).BIo;
        zZm(AlexaMetricsName.Operational.ATTACHMENT_SIZE.appendWith(((SFM) xff).zQM.name()), AlexaMetricsCount.create(i), AlexaMetricsCategory.PERFORMANCE, AlexaMetricsType.COUNTER, new Bundle());
    }

    @Subscribe
    public synchronized void on(Qqb qqb) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.EVENT_VALUE.name(), ((Gop) qqb).BIo.toString());
        Gop gop = (Gop) qqb;
        bundle.putString(AlexaMetricsConstants.AMPD.DEVICE_UNLOCK_STATE, gop.BIo.toString());
        bundle.putString(AlexaMetadataBundleKey.MESSAGE.name(), gop.zQM);
        bundle.putString(AlexaMetricsConstants.AMPD.LAST_UNLOCK_TIMESTAMP, gop.zQM);
        zZm(AlexaMetricsName.DeviceLockScreenState.DEVICE_LOCK_SCREEN_STATE_CHECK, AlexaMetricsCount.createOne(), AlexaMetricsCategory.OPERATIONAL, AlexaMetricsType.COUNTER, bundle);
    }
}
