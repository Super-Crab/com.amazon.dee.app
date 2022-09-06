package com.amazon.alexa.mode.drive;

import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.google.common.base.MoreObjects;
/* loaded from: classes9.dex */
public class Card {
    private final String contentId;
    private final String contentProvider;
    private final String contentSource;
    private final String contentType;
    private final String customTemplateRoute;
    private final String id;
    private final P13nMetadata p13nMetadata;
    private final String templateType;

    /* loaded from: classes9.dex */
    public static class P13nMetadata {
        private String section;

        /* JADX INFO: Access modifiers changed from: package-private */
        public P13nMetadata(String str) {
            this.section = str;
        }

        public String getSection() {
            return this.section;
        }

        @NonNull
        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) P13nMetadata.class).add(EntertainmentConstants.TYPE_SECTION, this.section).toString();
        }
    }

    public Card(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7, @NonNull P13nMetadata p13nMetadata) {
        this.id = str;
        this.templateType = str2;
        this.customTemplateRoute = str3;
        this.contentProvider = str4;
        this.contentSource = str5;
        this.contentType = str6;
        this.contentId = str7;
        this.p13nMetadata = p13nMetadata;
    }

    public String getCustomTemplateRoute() {
        return this.customTemplateRoute;
    }

    public String getId() {
        return this.id;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    @NonNull
    public String toString() {
        return MoreObjects.toStringHelper((Class<?>) Card.class).add("id", this.id).add("templateType", this.templateType).add("customTemplateRoute", this.customTemplateRoute).add("contentProvider", this.contentProvider).add("contentSource", this.contentSource).add("contentType", this.contentType).add("contentId", this.contentId).add("p13nMetadata", this.p13nMetadata).toString();
    }
}
