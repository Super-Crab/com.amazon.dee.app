package com.amazon.deecomms.smsmessaging.messagingcontroller;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.smsmessaging.database.SMSDatabaseManager;
import com.amazon.deecomms.smsmessaging.database.SMSDatabaseUtils;
import com.amazon.deecomms.smsmessaging.database.SMSMessageContract;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.deecomms.smsmessaging.model.Filter;
import com.amazon.deecomms.smsmessaging.service.sendsms.SMSSendManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class MessagingControllerDirectiveHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessagingControllerDirectiveHandler.class);
    private final CapabilitiesManager mCapabilitiesManager;
    @Inject
    MessagingControllerManager mMessagingControllerManager;

    public MessagingControllerDirectiveHandler(@NonNull CapabilitiesManager capabilitiesManager) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mCapabilitiesManager = capabilitiesManager;
    }

    private boolean checkSMSPermission(@NonNull String str) {
        if (!this.mMessagingControllerManager.checkPermissions()) {
            LOG.e("Alexa App does not have SMS permission, but received a Messaging Controller directive");
            if (str.length() <= 0) {
                return false;
            }
            this.mMessagingControllerManager.sendUpdateSendMessageStatusEvent(str, MessagingControllerConstant.SendMessageStatus.DEVICE_FAILURE.toString(), MessagingControllerConstant.SendMessageStatusReason.NO_PERMISSION.toString(), MessagingControllerConstant.NO_SMS_PERMISSION);
            return false;
        }
        return true;
    }

    private void handleRequestUploadConversationsDirective(@NonNull Context context, @NonNull String str) {
        LOG.i("Handling RequestUploadConversations Directive.");
        CounterMetric generateOperational = CounterMetric.generateOperational(SMSMessageContract.MC_UPLOAD_REQUEST);
        try {
            if (!checkSMSPermission("")) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            LOG.i("handleRequestUploadConversationsDirective, getting Filter.");
            Filter filter = (Filter) new ObjectMapper().readValue(jSONObject.getString(MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY), Filter.class);
            LOG.i("handleRequestUploadConversationsDirective, got Filter.");
            int intValue = filter.maxMessageCount.intValue();
            MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_REQUEST);
            MetricsHelper.recordCounterMetric(generateOperational, Double.valueOf(1.0d));
            this.mMessagingControllerManager.fullSync(context, intValue);
        } catch (Exception e) {
            LOG.e("Error parsing Messaging Controller RequestUploadConversations Directive payload: ", e);
            generateOperational.getMetadata().put("errorSource", e.getMessage());
        } finally {
            MetricsHelper.recordCounterMetric(generateOperational, Double.valueOf(1.0d));
            this.mMessagingControllerManager.fullSync(context, 40);
        }
    }

    private void handleSendMessageDirective(@NonNull Context context, @NonNull String str) {
        JSONObject jSONObject;
        String string;
        LOG.i("Handling SendMessages Directive.");
        CounterMetric generateOperational = CounterMetric.generateOperational(SMSMessageContract.MC_SEND);
        try {
            try {
                jSONObject = new JSONObject(str);
                string = jSONObject.getString(MessagingControllerConstant.MESSAGING_CONTROLLER_SEND_MESSAGE_REQUEST_ID);
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("SendMessage request id: ");
                sb.append(string);
                commsLogger.i(sb.toString());
                generateOperational.getMetadata().put("EventValue", string);
            } catch (Exception e) {
                LOG.e("Error parsing Messaging Controller SendMessage Directive payload: ", e);
                generateOperational.getMetadata().put("errorSource", e.getMessage());
            }
            if (!checkSMSPermission(string)) {
                return;
            }
            String string2 = jSONObject.getJSONArray("recipients").getJSONObject(0).getString("address");
            new SMSSendManager(context, this.mMessagingControllerManager, string).sendSMSMessage(string2, jSONObject.getJSONObject("messagePayload").getString("text"));
        } finally {
            MetricsHelper.recordCounterMetric(generateOperational, Double.valueOf(1.0d));
        }
    }

    private void handleUpdateMessagesStatusDirective(@NonNull Context context, @NonNull String str) {
        LOG.i("Handling UpdateMessagesStatus Directive.");
        CounterMetric generateOperational = CounterMetric.generateOperational(SMSMessageContract.MC_UPDATE);
        try {
            try {
            } catch (Exception e) {
                LOG.e("Error parsing Messaging Controller UpdateMessagesStatus Directive: ", e);
                generateOperational.getMetadata().put("errorSource", e.getMessage());
            }
            if (!checkSMSPermission("")) {
                return;
            }
            SMSDatabaseUtils sMSDatabaseUtils = new SMSDatabaseUtils(SMSDatabaseManager.getInstance(context));
            Map map = (Map) new ObjectMapper().readValue(new JSONObject(str).getString(MessagingControllerConstant.MESSAGING_CONTROLLER_STATUS_MAP_KEY), new TypeReference<Map<String, List<String>>>() { // from class: com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerDirectiveHandler.1
            });
            if (map != null && map.containsKey("READ")) {
                LOG.i("handleUpdateMessagesStatusDirective, getting messageIds List.");
                List list = (List) map.get("READ");
                if (list != null && list.size() != 0) {
                    CommsLogger commsLogger = LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("handleUpdateMessagesStatusDirective, messageIds size is ");
                    sb.append(list.size());
                    commsLogger.i(sb.toString());
                    for (int i = 0; i < list.size(); i++) {
                        String str2 = (String) list.get(i);
                        if (str2.length() < 4) {
                            CommsLogger commsLogger2 = LOG;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Unable to parse the message id send from Messaging Speechlet. Message id = ");
                            sb2.append(str2);
                            commsLogger2.i(sb2.toString());
                        } else {
                            sMSDatabaseUtils.insertMessage(SMSMessageContract.READ_TABLE_NAME, str2.substring(0, 3), str2.substring(3));
                        }
                    }
                    sMSDatabaseUtils.pruningData();
                    return;
                }
                LOG.i("handleUpdateMessagesStatusDirective, messageIds is null or empty.");
                return;
            }
            LOG.i("handleUpdateMessagesStatusDirective, statusMap is null or does not exist.");
        } finally {
            MetricsHelper.recordCounterMetric(generateOperational, Double.valueOf(1.0d));
        }
    }

    @NonNull
    public boolean handleDirective(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        CounterMetric generateOperational = CounterMetric.generateOperational(SMSMessageContract.MC_GENERIC);
        generateOperational.getMetadata().put("EventValue", str);
        Double valueOf = Double.valueOf(1.0d);
        MetricsHelper.recordCounterMetric(generateOperational, valueOf);
        if (!this.mCapabilitiesManager.isMessagingControllerFeaturesEnabled()) {
            LOG.i("SMS Messaging feature is not enabled, but received a Messaging Controller directive.");
            return true;
        }
        LOG.i("Handling Messaging Controller Directive: " + str);
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1935644865) {
            if (hashCode != -770885209) {
                if (hashCode == -313448529 && str.equals(MessagingControllerConstant.REQUEST_UPLOAD_CONVERSATIONS)) {
                    c = 2;
                }
            } else if (str.equals(MessagingControllerConstant.UPDATE_MESSAGES_STATUS)) {
                c = 1;
            }
        } else if (str.equals(MessagingControllerConstant.SEND_MESSAGE)) {
            c = 0;
        }
        if (c == 0) {
            handleSendMessageDirective(context, str2);
        } else if (c == 1) {
            handleUpdateMessagesStatusDirective(context, str2);
        } else if (c != 2) {
            LOG.i("Received unrecognized directive: " + str + " under Messaging Controller namespace.");
            CounterMetric generateOperational2 = CounterMetric.generateOperational(SMSMessageContract.MC_UNSUPPORTED);
            generateOperational2.getMetadata().put("EventValue", str);
            MetricsHelper.recordCounterMetric(generateOperational2, valueOf);
        } else {
            handleRequestUploadConversationsDirective(context, str2);
        }
        return true;
    }
}
