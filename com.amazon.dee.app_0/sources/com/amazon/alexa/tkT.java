package com.amazon.alexa;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.FPq;
import com.amazon.alexa.IHD;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_MultipleTargetsResponseEventPayload;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_SingleTargetResponseEventPayload;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.syk;
import com.amazon.alexa.utils.TimeProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AlexaLauncherCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class tkT extends BaseCapabilityAgent {
    public static final Map<SZm, List<EnumC0198ddd>> BIo;
    public static final JsonObject zQM;
    public static final String zZm = "tkT";
    public final SCj JTe;
    public final Gson LPk;
    public final vrF Mlj;
    public final AlexaClientEventBus Qle;
    public final Msx jiA;
    public final TimeProvider yPL;
    public final vYS zyO;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(SZm.GOOGLE_PLAY_STORE, Arrays.asList(EnumC0198ddd.URI_HTTP_SCHEME, EnumC0198ddd.URI_CUSTOM_SCHEME, EnumC0198ddd.URI_APP_IDENTIFIER_SCHEME, EnumC0198ddd.URI_ANDROID_INTENT_SCHEME));
        BIo = Collections.unmodifiableMap(hashMap);
        zQM = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        zQM.add("catalogs", jsonArray);
        for (Map.Entry<SZm, List<EnumC0198ddd>> entry : BIo.entrySet()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", entry.getKey().name());
            JsonArray jsonArray2 = new JsonArray();
            for (EnumC0198ddd enumC0198ddd : entry.getValue()) {
                jsonArray2.add(enumC0198ddd.name());
            }
            jsonObject.add("identifierTypes", jsonArray2);
            jsonArray.add(jsonObject);
        }
    }

    @Inject
    public tkT(vYS vys, Msx msx, AlexaClientEventBus alexaClientEventBus, SCj sCj, Gson gson, TimeProvider timeProvider, vrF vrf) {
        super(Capability.create(AvsApiConstants.Alexa.Launcher.BIo, "0.2", zQM));
        this.zyO = vys;
        this.jiA = msx;
        this.Qle = alexaClientEventBus;
        this.JTe = sCj;
        this.LPk = gson;
        this.yPL = timeProvider;
        this.Mlj = vrf;
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        Zxk zxk;
        Zxk zxk2;
        long elapsedRealTime = this.yPL.elapsedRealTime();
        Name name = message.getHeader().getName();
        StringBuilder zZm2 = C0179Pya.zZm("onProcess ");
        zZm2.append(name.getValue());
        zZm2.toString();
        Payload payload = message.getPayload();
        boolean z = false;
        if (AvsApiConstants.Alexa.Launcher.Directives.LaunchTarget.zZm.equals(name)) {
            if (payload instanceof mLq) {
                sew sewVar = (sew) payload;
                pUc puc = sewVar.BIo;
                Aud aud = (Aud) this.JTe.zZm(puc);
                if (aud.zZm == null) {
                    zZm(zZm(false, XuC.LAUNCH_TARGET_FAILED, Zxk.TARGET_NOT_LAUNCHED, aud.zQM, puc, sewVar.zZm), puc);
                } else {
                    IUU iuu = sewVar.zZm;
                    boolean z2 = aud.BIo;
                    List<IJL> list = aud.zQM;
                    if (z2) {
                        zxk2 = Zxk.PLATFORM_DETERMINED_TARGET_LAUNCHED;
                    } else {
                        zxk2 = Zxk.TARGET_LAUNCHED;
                    }
                    Message zZm3 = zZm(true, XuC.LAUNCH_TARGET_ATTEMPTED, zxk2, list, puc, iuu);
                    Message zZm4 = zZm(false, XuC.LAUNCH_TARGET_FAILED, Zxk.TARGET_NOT_LAUNCHED, Arrays.asList(IJL.SCREEN_UNLOCK_TIMED_OUT), puc, sewVar.zZm);
                    if (this.jiA.zZm()) {
                        Log.i(zZm, "device is locked.");
                        zZm(aud.zZm, zZm3, zZm4);
                    } else {
                        Log.i(zZm, "launching target.");
                        this.zyO.zZm(aud.zZm);
                        zZm(zZm3, puc);
                    }
                }
                z = true;
            }
        } else if (AvsApiConstants.Alexa.Launcher.Directives.DisambiguateAndLaunchTarget.zZm.equals(name) && (payload instanceof gUg)) {
            rSg rsg = (rSg) payload;
            List<pUc> list2 = rsg.BIo;
            Aud aud2 = null;
            pUc puc2 = null;
            int i = 0;
            for (pUc puc3 : list2) {
                Afe zZm5 = this.JTe.zZm(puc3);
                if (((Aud) zZm5).zZm != null) {
                    i++;
                    if (aud2 == null) {
                        puc2 = puc3;
                        aud2 = zZm5;
                    }
                }
            }
            if (aud2 == null) {
                Log.i(zZm, "no app is launchable.");
                zZm(zZm(list2, rsg.zZm, Arrays.asList(IJL.NONE_INSTALLED), rsg.zQM), (pUc) GeneratedOutlineSupport1.outline25(list2, 1));
            } else {
                if (i == 1) {
                    aud2.zQM.add(IJL.SINGLE_ELIGIBLE_TARGET);
                } else {
                    aud2.zQM.add(IJL.HIGHEST_RANKED_TARGET_SELECTED);
                }
                IUU iuu2 = rsg.zZm;
                Aud aud3 = aud2;
                boolean z3 = aud3.BIo;
                List<IJL> list3 = aud3.zQM;
                if (z3) {
                    zxk = Zxk.PLATFORM_DETERMINED_TARGET_LAUNCHED;
                } else {
                    zxk = Zxk.TARGET_LAUNCHED;
                }
                Message zZm6 = zZm(true, XuC.DISAMBIGUATE_AND_LAUNCH_TARGET_ATTEMPTED, zxk, list3, puc2, iuu2);
                Message zZm7 = zZm(list2, rsg.zZm, Arrays.asList(IJL.SCREEN_UNLOCK_TIMED_OUT), rsg.zQM);
                if (this.jiA.zZm()) {
                    zZm(aud3.zZm, zZm6, zZm7);
                } else {
                    Log.i(zZm, "launching selected target.");
                    this.zyO.zZm(aud3.zZm);
                    zZm(zZm6, puc2);
                }
            }
            z = true;
        }
        if (z) {
            messageProcessingCallbacks.onFinished();
        } else {
            Log.e(zZm, "unknown payload or directive.");
            messageProcessingCallbacks.onError();
        }
        long elapsedRealTime2 = this.yPL.elapsedRealTime() - elapsedRealTime;
        long elapsedRealTime3 = this.yPL.elapsedRealTime() - this.Mlj.zyO;
        String value = name.getValue();
        if (value != null) {
            Long valueOf = Long.valueOf(elapsedRealTime2);
            Long valueOf2 = Long.valueOf(elapsedRealTime3);
            String str = "";
            if (valueOf == null) {
                str = C0179Pya.zZm(str, " processTimeMillis");
            }
            if (valueOf2 == null) {
                str = C0179Pya.zZm(str, " userPerceivedLatency");
            }
            if (str.isEmpty()) {
                this.Qle.zyO(new DbX(value, valueOf.longValue(), valueOf2.longValue(), null));
                return;
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
        throw new NullPointerException("Null directiveName");
    }

    public final void zZm(Intent intent, Message message, Message message2) {
        this.zyO.zZm(intent, zZm(message), zZm(message2));
        this.Qle.zyO(new BHW());
    }

    public final Message zZm(boolean z, XuC xuC, Zxk zxk, List<IJL> list, pUc puc, IUU iuu) {
        Name name;
        if (z) {
            name = AvsApiConstants.Alexa.Launcher.Events.Response.zZm;
        } else {
            name = AvsApiConstants.Alexa.Launcher.Events.ErrorResponse.zZm;
        }
        Header build = Header.builder().setMessageIdentifier(MessageIdentifier.createRandom()).setNamespace(AvsApiConstants.Alexa.Launcher.zZm).setName(name).build();
        FPq.zZm zzm = (FPq.zZm) UTs.zZm();
        zzm.zQM = StC.zZm(((AAX) puc).zyO);
        if (iuu != null) {
            zzm.zZm = iuu;
            String xuC2 = xuC.toString();
            if (xuC2 != null) {
                zzm.BIo = xuC2;
                if (zxk != null) {
                    zzm.zyO = zxk;
                    if (list != null) {
                        zzm.jiA = list;
                        String str = "";
                        if (zzm.zZm == null) {
                            str = C0179Pya.zZm(str, " token");
                        }
                        if (zzm.BIo == null) {
                            str = C0179Pya.zZm(str, " type");
                        }
                        if (zzm.zQM == null) {
                            str = C0179Pya.zZm(str, " target");
                        }
                        if (zzm.zyO == null) {
                            str = C0179Pya.zZm(str, " outcome");
                        }
                        if (zzm.jiA == null) {
                            str = C0179Pya.zZm(str, " reasons");
                        }
                        if (str.isEmpty()) {
                            return Message.create(build, new AutoValue_SingleTargetResponseEventPayload(zzm.zZm, zzm.BIo, zzm.zQM, zzm.zyO, zzm.jiA));
                        }
                        throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
                    }
                    throw new NullPointerException("Null reasons");
                }
                throw new NullPointerException("Null outcome");
            }
            throw new NullPointerException("Null type");
        }
        throw new NullPointerException("Null token");
    }

    public final Message zZm(List<pUc> list, IUU iuu, List<IJL> list2, String str) {
        Header build = Header.builder().setMessageIdentifier(MessageIdentifier.createRandom()).setNamespace(AvsApiConstants.Alexa.Launcher.zZm).setName(AvsApiConstants.Alexa.Launcher.Events.ErrorResponse.zZm).build();
        Zxk zxk = Zxk.NONE_LAUNCHED;
        XuC xuC = XuC.DISAMBIGUATE_AND_LAUNCH_TARGET_FAILED;
        ArrayList arrayList = new ArrayList();
        Iterator<pUc> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(StC.zZm(((AAX) it2.next()).zyO));
        }
        IHD.zZm zzm = (IHD.zZm) Jqr.zZm();
        if (iuu != null) {
            zzm.zZm = iuu;
            String xuC2 = xuC.toString();
            if (xuC2 != null) {
                zzm.BIo = xuC2;
                zzm.zQM = arrayList;
                if (zxk != null) {
                    zzm.zyO = zxk;
                    if (list2 != null) {
                        zzm.jiA = list2;
                        if (str != null) {
                            zzm.Qle = str;
                            String str2 = "";
                            if (zzm.zZm == null) {
                                str2 = C0179Pya.zZm(str2, " token");
                            }
                            if (zzm.BIo == null) {
                                str2 = C0179Pya.zZm(str2, " type");
                            }
                            if (zzm.zQM == null) {
                                str2 = C0179Pya.zZm(str2, " targets");
                            }
                            if (zzm.zyO == null) {
                                str2 = C0179Pya.zZm(str2, " outcome");
                            }
                            if (zzm.jiA == null) {
                                str2 = C0179Pya.zZm(str2, " reasons");
                            }
                            if (zzm.Qle == null) {
                                str2 = C0179Pya.zZm(str2, " description");
                            }
                            if (str2.isEmpty()) {
                                return Message.create(build, new AutoValue_MultipleTargetsResponseEventPayload(zzm.zZm, zzm.BIo, zzm.zQM, zzm.zyO, zzm.jiA, zzm.Qle));
                            }
                            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str2));
                        }
                        throw new NullPointerException("Null description");
                    }
                    throw new NullPointerException("Null reasons");
                }
                throw new NullPointerException("Null outcome");
            }
            throw new NullPointerException("Null type");
        }
        throw new NullPointerException("Null token");
    }

    public final void zZm(Message message, pUc puc) {
        this.Qle.zyO(JjI.BIo().zZm(message).zZm());
        Payload payload = message.getPayload();
        if (payload instanceof UTs) {
            syk.zZm zzm = new syk.zZm();
            FPq fPq = (FPq) payload;
            String str = fPq.BIo;
            if (str != null) {
                zzm.jiA = str;
                this.Qle.zyO(zzm.zZm(fPq.zyO).zZm(puc).zZm(fPq.zZm).zZm(fPq.jiA).zZm());
                return;
            }
            throw new NullPointerException("Null eventType");
        } else if (!(payload instanceof Jqr)) {
        } else {
            syk.zZm zzm2 = new syk.zZm();
            IHD ihd = (IHD) payload;
            String str2 = ihd.BIo;
            if (str2 != null) {
                zzm2.jiA = str2;
                this.Qle.zyO(zzm2.zZm(ihd.zyO).zZm(puc).zZm(ihd.zZm).zZm(ihd.jiA).zZm());
                return;
            }
            throw new NullPointerException("Null eventType");
        }
    }

    public static String zZm(List<IJL> list) {
        ArrayList arrayList = new ArrayList();
        ListIterator<IJL> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            arrayList.add(listIterator.next().toString());
        }
        return TextUtils.join(",", arrayList);
    }

    public final AlexaEvent zZm(Message message) {
        return new AlexaEvent(AlexaHeader.builder().setNamespace(message.getHeader().getNamespace().getValue()).setName(message.getHeader().getName().getValue()).setMessageId(message.getHeader().getMessageIdentifier().getValue()).build(), new AlexaPayload(this.LPk.toJson(message.getPayload())));
    }
}
