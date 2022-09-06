package com.amazon.alexa;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.amazon.alexa.Alc;
import com.amazon.alexa.PlA;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.drive.navigation.MappingApplication;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;
/* compiled from: NavigationCapabilityAgent.java */
/* loaded from: classes.dex */
public class yhM extends BaseCapabilityAgent {
    public static final String zZm = "yhM";
    public final spf BIo;
    public final vYS zQM;

    @Inject
    public yhM(spf spfVar, vYS vys) {
        super(Capability.create(AvsApiConstants.Navigation.BIo, "1.1"));
        this.BIo = spfVar;
        this.zQM = vys;
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        if (!this.zQM.zZm()) {
            Log.w(zZm, "Cannot launch maps activity from background");
            messageProcessingCallbacks.onError();
        } else if (AvsApiConstants.Navigation.Directives.SetDestination.zZm.equals(message.getHeader().getName())) {
            PGo pGo = (PGo) message.getPayload();
            Alc.zZm.AbstractC0010zZm abstractC0010zZm = ((QqQ) pGo.zZm).zZm;
            double d = ((UrQ) abstractC0010zZm).zZm;
            double d2 = ((UrQ) abstractC0010zZm).BIo;
            String str = pGo.BIo;
            PlA zZm2 = zZm();
            Intent intent = new Intent("android.intent.action.VIEW", zZm2.zZm(d, d2, str));
            intent.setFlags(268435456);
            intent.setPackage(zZm2.zyO);
            StringBuilder zZm3 = C0179Pya.zZm("Using ");
            zZm3.append(zZm2.zQM);
            zZm3.append(" as the navigation application");
            zZm3.toString();
            if (MappingApplication.GOOGLE_MAPS_PACKAGENAME.equals(zZm2.zyO)) {
                this.zQM.BIo(intent);
            } else {
                this.zQM.zZm(intent);
            }
            messageProcessingCallbacks.onFinished();
        } else if (AvsApiConstants.Navigation.Directives.CancelNavigation.zZm.equals(message.getHeader().getName())) {
            String str2 = zZm().zyO;
            if (MappingApplication.GOOGLE_MAPS_PACKAGENAME.equals(str2)) {
                this.zQM.zyO();
            } else if (MappingApplication.WAZE_PACKAGENAME.equals(str2)) {
                this.zQM.jiA();
            }
            messageProcessingCallbacks.onFinished();
        } else {
            messageProcessingCallbacks.onError();
        }
    }

    public final PlA zZm() {
        spf spfVar = this.BIo;
        String string = spfVar.jiA.mo358get().getString("preferred_nav_app");
        if (string == null) {
            String str = spf.zZm;
            spfVar.zZm();
            string = MappingApplication.GOOGLE_MAPS_PACKAGENAME;
        }
        List<ResolveInfo> queryIntentActivities = spfVar.zyO.queryIntentActivities(spf.zQM, 0);
        HashSet hashSet = new HashSet();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            hashSet.add(resolveInfo.activityInfo.packageName);
        }
        if (!hashSet.contains(string)) {
            String str2 = spf.zZm;
            String.format("preferred %s not availale. Default to google maps", string);
            spfVar.zZm();
            string = MappingApplication.GOOGLE_MAPS_PACKAGENAME;
        }
        char c = 65535;
        int hashCode = string.hashCode();
        if (hashCode != -660073534) {
            if (hashCode == 40719148 && string.equals(MappingApplication.GOOGLE_MAPS_PACKAGENAME)) {
                c = 1;
            }
        } else if (string.equals(MappingApplication.WAZE_PACKAGENAME)) {
            c = 0;
        }
        if (c != 0) {
            return PlA.zZm();
        }
        if (PlA.BIo == null) {
            PlA.BIo = new PlA.BIo(null);
        }
        return PlA.BIo;
    }
}
