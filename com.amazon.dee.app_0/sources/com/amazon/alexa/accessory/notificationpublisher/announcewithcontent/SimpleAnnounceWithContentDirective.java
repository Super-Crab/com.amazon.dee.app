package com.amazon.alexa.accessory.notificationpublisher.announcewithcontent;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.google.common.base.Strings;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class SimpleAnnounceWithContentDirective implements AnnounceWithContentDirective {
    private String announcementUri;
    private String contentUri;
    private String name;
    private String namespace;
    private String notificationUuid;

    public SimpleAnnounceWithContentDirective(AlexaDirective alexaDirective) throws Exception {
        Preconditions.notNull(alexaDirective, "directive");
        Preconditions.notNull(alexaDirective.getAlexaHeader(), "AlexaDirective.header");
        Preconditions.notNull(alexaDirective.getAlexaPayload(), "AlexaDirective.payload");
        this.name = alexaDirective.getAlexaHeader().getName();
        this.namespace = alexaDirective.getAlexaHeader().getNamespace();
        if (!Strings.isNullOrEmpty(this.name) && this.name.equals("AnnounceWithContent")) {
            if (!Strings.isNullOrEmpty(this.namespace) && this.namespace.equals(AnnounceWithContentCapability.INTERFACE_NAME)) {
                populateFieldsFromPayload(alexaDirective.getAlexaPayload().getPayload());
                return;
            }
            throw new IllegalArgumentException("Interface name is invalid.");
        }
        throw new IllegalArgumentException("Directive name is invalid.");
    }

    private void populateFieldsFromPayload(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObject2 = jSONObject.getJSONObject("assetUrls");
        if (!jSONObject.isNull(Constants.BUNDLE_KEY_NOTIFICATION_ID)) {
            if (!jSONObject2.isNull(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT)) {
                if (!jSONObject2.isNull("content")) {
                    this.notificationUuid = jSONObject.getString(Constants.BUNDLE_KEY_NOTIFICATION_ID);
                    this.announcementUri = jSONObject2.getString(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT);
                    this.contentUri = jSONObject2.getString("content");
                    if (!Strings.isNullOrEmpty(this.notificationUuid)) {
                        if (!Strings.isNullOrEmpty(this.announcementUri)) {
                            if (Strings.isNullOrEmpty(this.contentUri)) {
                                throw new IllegalArgumentException("content is missing");
                            }
                            return;
                        }
                        throw new IllegalArgumentException("announcement is missing");
                    }
                    throw new IllegalArgumentException("notificationId is missing");
                }
                throw new IllegalArgumentException("content is null");
            }
            throw new IllegalArgumentException("announcement is null");
        }
        throw new IllegalArgumentException("notificationId is null");
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentDirective
    public String getAnnouncementUri() {
        return this.announcementUri;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentDirective
    public String getContentUri() {
        return this.contentUri;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentDirective
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentDirective
    public String getNamespace() {
        return this.namespace;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentDirective
    public String getNotificationUuid() {
        return this.notificationUuid;
    }
}
