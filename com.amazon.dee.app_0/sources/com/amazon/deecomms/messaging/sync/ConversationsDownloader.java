package com.amazon.deecomms.messaging.sync;

import android.text.TextUtils;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.TimePeriodHelper;
import com.amazon.deecomms.messaging.model.Conversation;
import com.amazon.deecomms.messaging.model.response.ConversationsResponse;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class ConversationsDownloader {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ConversationsDownloader.class);
    private String mRegisteredCommsID;
    private TimePeriodHelper mTimePeriodHelper = new TimePeriodHelper();

    public ConversationsDownloader(String str) {
        this.mRegisteredCommsID = str;
    }

    private ConversationsResponse getConversations(long j, long j2, boolean z) {
        String format = MessageFormat.format(AppUrl.GET_CONVERSATIONS_FROM_COMMS_ID, this.mRegisteredCommsID);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1(" API Suffix ");
        outline1.append(LOG.sensitive(format));
        commsLogger.i(outline1.toString());
        IHttpClient.Request request = new ACMSClient(MetricKeys.OP_GET_RECENT_CONVO).request(format);
        if (j > 0) {
            request.addQueryParameter(AppUrl.MODIFIED_SINCE_TIME, this.mTimePeriodHelper.convertToISOFormat(j));
        }
        if (j2 > 0) {
            request.addQueryParameter(AppUrl.MODIFIED_BEFORE_TIME, this.mTimePeriodHelper.convertToISOFormat(j2));
        }
        request.addQueryParameter(AppUrl.LATEST, z ? "true" : PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
        try {
            IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().addQueryParameter(AppUrl.INCLUDE_HOMEGROUP, Boolean.TRUE.toString()).get().mo3640execute();
            ConversationsResponse conversationsResponse = (ConversationsResponse) mo3640execute.convert(ConversationsResponse.class);
            if (conversationsResponse != null && conversationsResponse.getConversations() != null) {
                CommsLogger commsLogger2 = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Downloaded conversations, size: ");
                sb.append(conversationsResponse.getConversations().length);
                commsLogger2.i(sb.toString());
            }
            mo3640execute.close();
            return conversationsResponse;
        } catch (ServiceException | IOException e) {
            LOG.e("Exception in httpClient", e);
            return null;
        }
    }

    public List<Conversation> getConversationsAscending(long j) {
        ArrayList arrayList = new ArrayList();
        long j2 = j;
        while (true) {
            ConversationsResponse conversations = getConversations(j2, 0L, false);
            if (conversations == null || conversations.getConversations() == null || conversations.getConversations().length == 0) {
                break;
            }
            arrayList.addAll(Arrays.asList(conversations.getConversations()));
            if (conversations.getLastPage()) {
                break;
            }
            long j3 = 0;
            for (Conversation conversation : conversations.getConversations()) {
                j3 = Math.max(j3, this.mTimePeriodHelper.convertTimestampStringToMills(conversation.getLastModifiedTimestamp()));
            }
            j2 = j3 + 1;
        }
        return arrayList;
    }

    public List<Conversation> getConversationsDescending(long j) {
        Conversation[] conversations;
        ArrayList arrayList = new ArrayList();
        long j2 = j;
        while (true) {
            ConversationsResponse conversations2 = getConversations(0L, j2, true);
            if (conversations2 == null || conversations2.getConversations() == null || conversations2.getConversations().length == 0) {
                break;
            }
            arrayList.addAll(Arrays.asList(conversations2.getConversations()));
            if (conversations2.getLastPage()) {
                break;
            }
            long j3 = Long.MAX_VALUE;
            for (Conversation conversation : conversations2.getConversations()) {
                if (!TextUtils.isEmpty(conversation.getLastModifiedTimestamp())) {
                    j3 = Math.min(j3, this.mTimePeriodHelper.convertTimestampStringToMills(conversation.getLastModifiedTimestamp()));
                }
            }
            j2 = j3 - 1;
        }
        return arrayList;
    }
}
