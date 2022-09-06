package com.amazon.alexa.devicesetup.sdk.whisperjoin.zerotouch;

import android.os.RemoteException;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.helper.ProvisioningConstants;
import com.amazon.alexa.devicesetup.utils.EventBusUtil;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class ProvisioningEventCallback extends ProvisioningWorkflowEventCallback.Stub {
    EventBusUtil eventBusUtil = getEventBusUtil();

    private String parseSetupSetupMessageToJson(String str, String str2, String str3) {
        try {
            return new JSONObject().put(ProvisioningConstants.EVENT_PROVISIONING_EVENT_WORKFLOW_STEP, str2).put(ProvisioningConstants.EVENT_PROVISIONING_EVENT_WORKFLOW_MESSAGE, str3).toString();
        } catch (JSONException unused) {
            sendUnexpectedErrorMessageToEventBus(str);
            return null;
        }
    }

    private void sendUnexpectedErrorMessageToEventBus(String str) {
        EventBusUtil eventBusUtil = this.eventBusUtil;
        eventBusUtil.sendMessageToEventBus("error::" + str);
    }

    protected EventBusUtil getEventBusUtil() {
        return EventBusUtil.instance();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
    public void onComplete() throws RemoteException {
        this.eventBusUtil.sendToEventBus(new Message.Builder().setDate(System.currentTimeMillis()).setEventType("ffs::zerotouchsetup::OnComplete").setSource(Message.Source.Local).build());
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
    public void onError(String str, String str2, String str3) throws RemoteException {
        String parseSetupSetupMessageToJson = parseSetupSetupMessageToJson("ProvisioningEventCallback::onError", str, str2);
        if (parseSetupSetupMessageToJson != null) {
            this.eventBusUtil.sendMessageToEventBus(parseSetupSetupMessageToJson, "ffs::zerotouchsetup::OnError");
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
    public void onNext(String str, String str2) throws RemoteException {
        String parseSetupSetupMessageToJson = parseSetupSetupMessageToJson("ProvisioningEventCallback::onNext", str, str2);
        if (parseSetupSetupMessageToJson != null) {
            this.eventBusUtil.sendMessageToEventBus(parseSetupSetupMessageToJson, "ffs::zerotouchsetup::OnNext");
        }
    }
}
