package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Notification;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.NotificationContentTransformer;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NotificationWithContentRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001dB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00000\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/NotificationWithContentRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", Constants.BUNDLE_KEY_NOTIFICATION_ID, "", "notificationContent", "Lcom/amazon/alexa/accessory/protocol/Notification$NotificationContent;", "ipcCallIdentifier", "", "(ILcom/amazon/alexa/accessory/protocol/Notification$NotificationContent;Ljava/lang/String;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getIpcCallIdentifier", "()Ljava/lang/String;", "getNotificationContent", "()Lcom/amazon/alexa/accessory/protocol/Notification$NotificationContent;", "getNotificationId", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NotificationWithContentRequest implements Query.Request<NotificationWithContentRequest> {
    @NotNull
    private final BundleTransformer<NotificationWithContentRequest> bundleTransformer;
    @NotNull
    private final String ipcCallIdentifier;
    @NotNull
    private final Notification.NotificationContent notificationContent;
    private final int notificationId;

    /* compiled from: NotificationWithContentRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/NotificationWithContentRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/NotificationWithContentRequest;", "()V", "IPC_SESSION_IDENTIFIER_KEY", "", "NOTIFICATION_CONTENT_KEY", "NOTIFICATION_ID_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "request", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<NotificationWithContentRequest> {
        public static final Transformer INSTANCE = new Transformer();
        private static final String IPC_SESSION_IDENTIFIER_KEY = "identifier";
        private static final String NOTIFICATION_CONTENT_KEY = "notificationContent";
        private static final String NOTIFICATION_ID_KEY = "notificationId";

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public NotificationWithContentRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            int i = bundle.getInt("notificationId");
            NotificationContentTransformer notificationContentTransformer = NotificationContentTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle(NOTIFICATION_CONTENT_KEY);
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(NOTIFICATION_CONTENT_KEY)!!");
            Notification.NotificationContent mo568fromBundle = notificationContentTransformer.mo568fromBundle(bundle2);
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IPC_SESSION_IDENTIFIER_KEY)!!");
            return new NotificationWithContentRequest(i, mo568fromBundle, string);
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull NotificationWithContentRequest request) {
            Intrinsics.checkParameterIsNotNull(request, "request");
            Bundle bundle = new Bundle();
            bundle.putInt("notificationId", request.getNotificationId());
            bundle.putBundle(NOTIFICATION_CONTENT_KEY, NotificationContentTransformer.INSTANCE.toBundle(request.getNotificationContent()));
            bundle.putString("identifier", request.getIpcCallIdentifier());
            return bundle;
        }
    }

    public NotificationWithContentRequest(int i, @NotNull Notification.NotificationContent notificationContent, @NotNull String ipcCallIdentifier) {
        Intrinsics.checkParameterIsNotNull(notificationContent, "notificationContent");
        Intrinsics.checkParameterIsNotNull(ipcCallIdentifier, "ipcCallIdentifier");
        this.notificationId = i;
        this.notificationContent = notificationContent;
        this.ipcCallIdentifier = ipcCallIdentifier;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ NotificationWithContentRequest copy$default(NotificationWithContentRequest notificationWithContentRequest, int i, Notification.NotificationContent notificationContent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = notificationWithContentRequest.notificationId;
        }
        if ((i2 & 2) != 0) {
            notificationContent = notificationWithContentRequest.notificationContent;
        }
        if ((i2 & 4) != 0) {
            str = notificationWithContentRequest.ipcCallIdentifier;
        }
        return notificationWithContentRequest.copy(i, notificationContent, str);
    }

    public final int component1() {
        return this.notificationId;
    }

    @NotNull
    public final Notification.NotificationContent component2() {
        return this.notificationContent;
    }

    @NotNull
    public final String component3() {
        return this.ipcCallIdentifier;
    }

    @NotNull
    public final NotificationWithContentRequest copy(int i, @NotNull Notification.NotificationContent notificationContent, @NotNull String ipcCallIdentifier) {
        Intrinsics.checkParameterIsNotNull(notificationContent, "notificationContent");
        Intrinsics.checkParameterIsNotNull(ipcCallIdentifier, "ipcCallIdentifier");
        return new NotificationWithContentRequest(i, notificationContent, ipcCallIdentifier);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof NotificationWithContentRequest)) {
                return false;
            }
            NotificationWithContentRequest notificationWithContentRequest = (NotificationWithContentRequest) obj;
            return this.notificationId == notificationWithContentRequest.notificationId && Intrinsics.areEqual(this.notificationContent, notificationWithContentRequest.notificationContent) && Intrinsics.areEqual(this.ipcCallIdentifier, notificationWithContentRequest.ipcCallIdentifier);
        }
        return true;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public Bundle getBundle() {
        return Query.Request.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public BundleTransformer<NotificationWithContentRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final String getIpcCallIdentifier() {
        return this.ipcCallIdentifier;
    }

    @NotNull
    public final Notification.NotificationContent getNotificationContent() {
        return this.notificationContent;
    }

    public final int getNotificationId() {
        return this.notificationId;
    }

    public int hashCode() {
        int i = this.notificationId * 31;
        Notification.NotificationContent notificationContent = this.notificationContent;
        int i2 = 0;
        int hashCode = (i + (notificationContent != null ? notificationContent.hashCode() : 0)) * 31;
        String str = this.ipcCallIdentifier;
        if (str != null) {
            i2 = str.hashCode();
        }
        return hashCode + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NotificationWithContentRequest(notificationId=");
        outline107.append(this.notificationId);
        outline107.append(", notificationContent=");
        outline107.append(this.notificationContent);
        outline107.append(", ipcCallIdentifier=");
        return GeneratedOutlineSupport1.outline91(outline107, this.ipcCallIdentifier, ")");
    }
}
