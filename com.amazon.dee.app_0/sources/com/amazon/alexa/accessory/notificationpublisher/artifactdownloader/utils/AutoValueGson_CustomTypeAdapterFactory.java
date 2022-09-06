package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.utils;

import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactManifest;
import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata;
import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.Checksum;
import com.amazon.alexa.externalnotifications.capability.directive.SetNotificationsReadDirectivePayload;
import com.amazon.alexa.externalnotifications.capability.events.NotificationsDismissedEventPayload;
import com.amazon.alexa.externalnotifications.capability.events.NotificationsReadEventPayload;
import com.amazon.alexa.externalnotifications.capability.events.NotificationsReceivedEventPayload;
import com.amazon.alexa.externalnotifications.capability.models.Application;
import com.amazon.alexa.externalnotifications.capability.models.Contact;
import com.amazon.alexa.externalnotifications.capability.models.Content;
import com.amazon.alexa.externalnotifications.capability.models.Group;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.amazon.alexa.externalnotifications.capability.models.Source;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
/* loaded from: classes.dex */
public final class AutoValueGson_CustomTypeAdapterFactory extends CustomTypeAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (Application.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Application.typeAdapter(gson);
        }
        if (ArtifactManifest.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) ArtifactManifest.typeAdapter(gson);
        }
        if (ArtifactMetadata.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) ArtifactMetadata.typeAdapter(gson);
        }
        if (Checksum.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Checksum.typeAdapter(gson);
        }
        if (Contact.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Contact.typeAdapter(gson);
        }
        if (Content.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Content.typeAdapter(gson);
        }
        if (Group.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Group.typeAdapter(gson);
        }
        if (Notification.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Notification.typeAdapter(gson);
        }
        if (NotificationsDismissedEventPayload.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) NotificationsDismissedEventPayload.typeAdapter(gson);
        }
        if (NotificationsReadEventPayload.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) NotificationsReadEventPayload.typeAdapter(gson);
        }
        if (NotificationsReceivedEventPayload.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) NotificationsReceivedEventPayload.typeAdapter(gson);
        }
        if (SetNotificationsReadDirectivePayload.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) SetNotificationsReadDirectivePayload.typeAdapter(gson);
        }
        if (!Source.class.isAssignableFrom(rawType)) {
            return null;
        }
        return (TypeAdapter<T>) Source.typeAdapter(gson);
    }
}
