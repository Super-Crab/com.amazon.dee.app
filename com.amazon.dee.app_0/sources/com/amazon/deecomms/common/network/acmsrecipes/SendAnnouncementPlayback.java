package com.amazon.deecomms.common.network.acmsrecipes;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class SendAnnouncementPlayback {
    private final ACMSClient acmsClient;
    private final String apiSuffix;
    private final String content;
    private final String directedId;
    private final String sourceName;
    private final String tag;
    private final String type;

    public SendAnnouncementPlayback(String str, String str2, String str3, String str4) {
        this.tag = "AnnouncementPlayback";
        this.directedId = str;
        this.sourceName = str2;
        this.type = str3;
        this.content = str4;
        this.acmsClient = new ACMSClient(MetricKeys.OP_INBOUND_ANNOUNCEMENT_PLAYBACK);
        this.apiSuffix = MessageFormat.format(AppUrl.INBOUND_ANNOUNCEMENT_PLAYBACK_API, str);
    }

    public ObjectNode prepareRequestBody() {
        char c;
        ObjectNode mo7041createObjectNode = new ObjectMapper().mo7041createObjectNode();
        mo7041createObjectNode.put("directedId", this.directedId);
        mo7041createObjectNode.put("sourceName", this.sourceName);
        mo7041createObjectNode.put("type", this.type);
        String str = this.type;
        int hashCode = str.hashCode();
        if (hashCode != -389653234) {
            if (hashCode == 1068011914 && str.equals("announcement/text-simple")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("announcement/audio")) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            mo7041createObjectNode.put("messageText", this.content);
        } else {
            mo7041createObjectNode.put("mediaId", this.content);
        }
        return mo7041createObjectNode;
    }

    public IHttpClient.Response sendAnnouncementPlayback() throws ServiceException {
        try {
            return this.acmsClient.request(this.apiSuffix).authenticated(this.directedId).postJson(prepareRequestBody()).mo3640execute();
        } catch (ServiceException e) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Error when sending playback: ");
            outline1.append(e.getMessage());
            Log.e("AnnouncementPlayback", outline1.toString(), e);
            throw e;
        }
    }

    @VisibleForTesting
    public SendAnnouncementPlayback(String str, String str2, String str3, String str4, ACMSClient aCMSClient) {
        this.tag = "AnnouncementPlayback";
        this.directedId = str;
        this.sourceName = str2;
        this.type = str3;
        this.content = str4;
        this.acmsClient = aCMSClient;
        this.apiSuffix = MessageFormat.format(AppUrl.INBOUND_ANNOUNCEMENT_PLAYBACK_API, str);
    }
}
