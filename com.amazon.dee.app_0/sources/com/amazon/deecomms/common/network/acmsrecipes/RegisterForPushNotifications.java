package com.amazon.deecomms.common.network.acmsrecipes;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.notifications.PushNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class RegisterForPushNotifications {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, RegisterForPushNotifications.class);
    private final ACMSClient client;

    public RegisterForPushNotifications(@NonNull String str) {
        this.client = new ACMSClient(str);
    }

    public String execute(String str, String str2, String str3, PushNotificationService pushNotificationService, PushNotificationService pushNotificationService2, String str4, String str5) throws ServiceException {
        if (str == null || str2 == null) {
            return null;
        }
        String format = MessageFormat.format(AppUrl.PUSH_NOTIFICATION_REGISTER, str, str2);
        ObjectNode mo7041createObjectNode = new ObjectMapper().mo7041createObjectNode();
        mo7041createObjectNode.put("applicationId", str3);
        mo7041createObjectNode.put("pushToken", str5);
        mo7041createObjectNode.put("pushDeliveryType", pushNotificationService.toString());
        mo7041createObjectNode.put("rootDirectedId", str4);
        if (pushNotificationService2 != null) {
            mo7041createObjectNode.put("fallbackPushType", pushNotificationService2.toString());
        }
        return this.client.request(format).authenticatedAsCurrentCommsUser().postJson(mo7041createObjectNode).mo3640execute().getBody();
    }

    public String executeSwallowException(String str, String str2, String str3, PushNotificationService pushNotificationService, PushNotificationService pushNotificationService2, String str4, String str5) {
        try {
            return execute(str, str2, str3, pushNotificationService, pushNotificationService2, str4, str5);
        } catch (ServiceException e) {
            LOG.e("Error while RegisterForPushNotifications", e);
            return null;
        }
    }
}
