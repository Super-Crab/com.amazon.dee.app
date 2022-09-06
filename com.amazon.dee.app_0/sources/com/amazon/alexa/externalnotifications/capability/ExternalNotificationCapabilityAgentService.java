package com.amazon.alexa.externalnotifications.capability;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaDirective;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes7.dex */
public class ExternalNotificationCapabilityAgentService extends AlexaCapabilityAgentService {
    private static final String TAG = ExternalNotificationCapabilityAgentService.class.getSimpleName();

    private void sendSetReadDirectiveBroadcast(String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent();
            intent.setAction(ExternalNotificationsDirectiveReceiver.INTENT_ACTION_SET_READ_DIRECTIVE_RECEIVED);
            intent.putExtra(ExternalNotificationsDirectiveReceiver.INTENT_KEY_SET_READ_PAYLOAD_EXTRA, str);
            sendBroadcast(intent, "com.amazon.alexa.externalnotifications.directivelistener");
            Log.i(TAG, "Sent SetRead directive broadcast");
        }
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public Set<AlexaCapability> getCapabilities() {
        return Collections.singleton(AlexaCapability.create(ExternalNotificationsCapabilityAgentConstants.INTERFACE_NAME, "1.0"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public boolean process(AlexaDirective alexaDirective) {
        if (alexaDirective != null) {
            if (!ExternalNotificationsCapabilityAgentConstants.INTERFACE_NAME.equals(alexaDirective.getNamespace())) {
                Log.w(TAG, "process: Not Alexa.Notifications.External namespace");
                return false;
            }
            String name = alexaDirective.getName();
            if (!ExternalNotificationsCapabilityAgentConstants.SET_READ_DIRECTIVE_NAME.equals(name)) {
                GeneratedOutlineSupport1.outline162("process: Unknown directive ", name, TAG);
                return false;
            }
            sendSetReadDirectiveBroadcast(alexaDirective.getAlexaPayload().getPayload());
            return true;
        }
        Log.e(TAG, "process: Alexa directive is null!");
        return false;
    }
}
