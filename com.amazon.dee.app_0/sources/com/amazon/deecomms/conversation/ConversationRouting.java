package com.amazon.deecomms.conversation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.deecomms.api.navigation.CommsView;
/* loaded from: classes12.dex */
public class ConversationRouting {
    SimpleArrayMap<String, RouteHolder> routes = new SimpleArrayMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class RouteHolder {
        @NonNull
        public final CommsView commsView;
        public final String routeName;

        RouteHolder(String str, @NonNull CommsView commsView) {
            this.routeName = str;
            this.commsView = commsView;
        }
    }

    public ConversationRouting() {
        registerRoute(RouteName.CONVERSATIONS_CONTACT, CommsView.ContactCard);
        registerRoute(RouteName.CONVERSATIONS_CONTACT_LIST, CommsView.ContactList);
        registerRoute(RouteName.CONVERSATIONS_DIAGNOSTICS, CommsView.Diagnostics);
        registerRoute(RouteName.MANAGE_CONTACT_LIST, CommsView.ManageContactsList);
        registerRoute(RouteName.BLOCK_CONTACT_LIST, CommsView.BlockContactsList);
        registerRoute(RouteName.EDIT_CONTACT_CARD, CommsView.EditContact);
        registerRoute(RouteName.CHILD_CONTACT_CARD, CommsView.ChildContactCard);
        registerRoute(RouteName.WHITELIST_CONTACT, CommsView.WhitelistContact);
        registerRoute(RouteName.RELATIONSHIP_LIST, CommsView.RelationshipList);
        registerRoute(RouteName.EDIT_NICKNAME, CommsView.EditNickname);
        registerRoute(RouteName.COMMS_CONTACT_CARD, CommsView.ReactNativeContactCard);
        registerRoute(RouteName.COMMS_MANAGE_CONTACTS, CommsView.ReactNativeManageContacts);
        registerRoute(RouteName.COMMS_IMPORT_CONTACT, CommsView.ReactNativeImportContacts);
        registerRoute("v2/comms/conversation-list", CommsView.ReactNativeConversationList);
        registerRoute("v2/comms/contact-list", CommsView.ReactNativeContactList);
        registerRoute("v2/comms/conversation", CommsView.ReactNativeConversation);
        registerRoute(RouteName.COMMS_ANNOUNCEMENT, CommsView.ReactNativeAnnouncement);
        registerRoute(RouteName.COMMUNICATIONS_SETTING, CommsView.CommunicationsSettings);
        registerRoute("v2/comms/image-detail-view", CommsView.ReactNativeImageDetailView);
        registerRoute("v2/comms/announcement-list", CommsView.ReactNativeAnnouncementList);
    }

    @Nullable
    public CommsView getCommsView(String str) {
        RouteHolder routeHolder = this.routes.get(str);
        if (routeHolder == null) {
            return null;
        }
        return routeHolder.commsView;
    }

    @Nullable
    public String getRoute(@NonNull CommsView commsView) {
        int size = this.routes.size();
        for (int i = 0; i < size; i++) {
            RouteHolder valueAt = this.routes.valueAt(i);
            if (valueAt.commsView == commsView) {
                return valueAt.routeName;
            }
        }
        return null;
    }

    void registerRoute(String str, @NonNull CommsView commsView) {
        this.routes.put(str, new RouteHolder(str, commsView));
    }
}
