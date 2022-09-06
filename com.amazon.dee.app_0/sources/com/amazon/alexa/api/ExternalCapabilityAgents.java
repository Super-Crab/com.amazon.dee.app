package com.amazon.alexa.api;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.InterfaceC0189bOx;
import com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.CapabilityInterface;
import com.amazon.alexa.client.core.capabilities.CapabilityType;
import com.amazon.alexa.noF;
import com.amazon.alexa.pPw;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.zEy;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class ExternalCapabilityAgents {
    public static final String zZm = "ExternalCapabilityAgents";

    /* loaded from: classes6.dex */
    private static class DirectiveProcessingCallbacksProxy extends AlexaDirectiveProcessingCallbacksProxy.Stub {
        public final InterfaceC0189bOx zZm;

        public /* synthetic */ DirectiveProcessingCallbacksProxy(InterfaceC0189bOx interfaceC0189bOx, AnonymousClass1 anonymousClass1) {
            this.zZm = interfaceC0189bOx;
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onCancelFinished() throws RemoteException {
            zEy.zZm zzm = (zEy.zZm) this.zZm;
            zzm.BIo.onStopped();
            zzm.zZm();
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onCancelStarted() throws RemoteException {
            ((zEy.zZm) this.zZm).zQM();
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onError() throws RemoteException {
            ((zEy.zZm) this.zZm).BIo();
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onProcessFinished() throws RemoteException {
            zEy.zZm zzm = (zEy.zZm) this.zZm;
            zzm.BIo.onFinished();
            zzm.zZm();
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onProcessStarted() throws RemoteException {
            ((zEy.zZm) this.zZm).zQM();
        }
    }

    public static void BIo(ExternalCapabilityAgentConnection externalCapabilityAgentConnection, String str) throws noF {
        Preconditions.notNull(externalCapabilityAgentConnection, "ExternalCapabilityAgentConnection == null");
        Preconditions.notNull(str, "alexaDirectiveId == null");
        zZm(externalCapabilityAgentConnection);
        ExtendedClient client = externalCapabilityAgentConnection.getClient();
        CapabilityAgentMessageSender mo838get = externalCapabilityAgentConnection.mo838get();
        try {
            if (mo838get != null) {
                Log.i(zZm, String.format("Process passing directive %s to external capability agent %s", str, client.getPackageName()));
                mo838get.process(client, str);
                return;
            }
            zZm("alexaCapabilityAgentServiceInterface is null", pPw.BIo.NULL_SERVICE);
            throw null;
        } catch (RemoteException e) {
            zZm(e, pPw.BIo.REMOTE_EXCEPTION);
            throw null;
        }
    }

    public static int zZm() {
        return 8;
    }

    public static void zZm(ExternalCapabilityAgentConnection externalCapabilityAgentConnection, AlexaDirective alexaDirective, InterfaceC0189bOx interfaceC0189bOx) throws noF {
        Preconditions.notNull(externalCapabilityAgentConnection, "ExternalCapabilityAgentConnection == null");
        Preconditions.notNull(alexaDirective, "alexaDirective == null");
        Preconditions.notNull(interfaceC0189bOx, "ExternalDirectiveProcessingCallbacks == null");
        zZm(externalCapabilityAgentConnection);
        DirectiveProcessingCallbacksProxy directiveProcessingCallbacksProxy = new DirectiveProcessingCallbacksProxy(interfaceC0189bOx, null);
        ExtendedClient client = externalCapabilityAgentConnection.getClient();
        CapabilityAgentMessageSender mo838get = externalCapabilityAgentConnection.mo838get();
        try {
            if (mo838get != null) {
                Log.i(zZm, String.format("Preprocess passing directive %s (%s) to external capability agent %s", alexaDirective.getName(), alexaDirective.getAlexaHeader().getMessageId(), client.getPackageName()));
                mo838get.preprocess(client, alexaDirective, directiveProcessingCallbacksProxy);
                return;
            }
            zZm("alexaCapabilityAgentServiceInterface is null", pPw.BIo.NULL_SERVICE);
            throw null;
        } catch (RemoteException e) {
            zZm(e, pPw.BIo.REMOTE_EXCEPTION);
            throw null;
        }
    }

    public static Set<Capability> BIo(ExternalCapabilityAgentConnection externalCapabilityAgentConnection) throws noF {
        Preconditions.notNull(externalCapabilityAgentConnection, "ExternalCapabilityAgentConnection == null");
        zZm(externalCapabilityAgentConnection);
        HashSet hashSet = new HashSet();
        ExtendedClient client = externalCapabilityAgentConnection.getClient();
        CapabilityAgentMessageSender mo838get = externalCapabilityAgentConnection.mo838get();
        try {
            if (mo838get != null) {
                hashSet.addAll(zZm(mo838get.getCapabilities(client)));
                return hashSet;
            }
            zZm("alexaCapabilityAgentServiceInterface is null", pPw.BIo.CONNECTION_FAILURE);
            throw null;
        } catch (Exception e) {
            zZm(e, pPw.BIo.UNKNOWN);
            throw null;
        }
    }

    public static void zZm(ExternalCapabilityAgentConnection externalCapabilityAgentConnection, String str) throws noF {
        Preconditions.notNull(externalCapabilityAgentConnection, "ExternalCapabilityAgentConnection == null");
        Preconditions.notNull(str, "alexaDirectiveId == null");
        zZm(externalCapabilityAgentConnection);
        ExtendedClient client = externalCapabilityAgentConnection.getClient();
        CapabilityAgentMessageSender mo838get = externalCapabilityAgentConnection.mo838get();
        try {
            if (mo838get != null) {
                Log.i(zZm, String.format("Cancel passing directive %s to external capability agent %s", str, client.getPackageName()));
                mo838get.cancel(client, str);
                return;
            }
            zZm("alexaCapabilityAgentServiceInterface is null", pPw.BIo.NULL_SERVICE);
            throw null;
        } catch (RemoteException e) {
            zZm(e, pPw.BIo.REMOTE_EXCEPTION);
            throw null;
        }
    }

    public static Set<Capability> zZm(List<AlexaCapability> list) {
        if (list == null) {
            return Collections.emptySet();
        }
        JsonParser jsonParser = new JsonParser();
        HashSet hashSet = new HashSet();
        for (AlexaCapability alexaCapability : list) {
            JSONObject configurations = alexaCapability.getConfigurations();
            JsonObject jsonObject = null;
            if (configurations != null) {
                try {
                    jsonObject = (JsonObject) jsonParser.parse(configurations.toString());
                } catch (JsonSyntaxException unused) {
                    String str = zZm;
                    Log.e(str, "Unable to parse configurations from capability: " + configurations);
                }
            }
            hashSet.add(Capability.create(CapabilityType.create(alexaCapability.getType()), CapabilityInterface.create(alexaCapability.getInterface()), alexaCapability.getVersion(), jsonObject));
        }
        return hashSet;
    }

    public static void zZm(Exception exc, pPw.BIo bIo) throws noF {
        Log.e(zZm, "Unable to communicate with external capability agent", exc);
        throw new noF("Unable to communicate with external capability agent", exc, bIo);
    }

    public static void zZm(String str, pPw.BIo bIo) throws noF {
        Log.e(zZm, str);
        throw new noF(str, bIo);
    }

    public static void zZm(ExternalCapabilityAgentConnection externalCapabilityAgentConnection) throws noF {
        if (externalCapabilityAgentConnection.isConnected()) {
            return;
        }
        Log.e(zZm, "External capability agent is disconnected");
        throw new noF("External capability agent is disconnected", pPw.BIo.CONNECTION_FAILURE);
    }

    public static long zZm(int i) {
        if (i > 8 || i <= 0) {
            return 0L;
        }
        return ((long) Math.pow(2.0d, i)) * 1000;
    }
}
