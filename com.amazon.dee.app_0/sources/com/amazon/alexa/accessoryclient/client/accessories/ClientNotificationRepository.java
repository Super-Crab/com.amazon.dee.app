package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Notification;
import com.amazon.alexa.accessory.repositories.notification.NotificationRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.NotificationWithContentRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.response.ErrorCodeResponse;
import com.amazon.alexa.accessoryclient.common.query.response.ExecuteNotificationActionResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import com.amazon.deecomms.common.Constants;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientNotificationRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientNotificationRepository;", "Lcom/amazon/alexa/accessory/repositories/notification/NotificationRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "addOutgoingNotification", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/protocol/Common$ErrorCode;", Constants.BUNDLE_KEY_NOTIFICATION_ID, "", "notificationContent", "Lcom/amazon/alexa/accessory/protocol/Notification$NotificationContent;", "queryActionCommandsForOutgoingNotifications", "Lio/reactivex/rxjava3/core/Flowable;", "Lcom/amazon/alexa/accessory/protocol/Notification$ExecuteNotificationAction;", "removeOutgoingNotification", "updateOutgoingNotification", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientNotificationRepository implements NotificationRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientNotificationRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.notification.NotificationRepository
    @NotNull
    public Single<Common.ErrorCode> addOutgoingNotification(int i, @NotNull Notification.NotificationContent notificationContent) {
        Intrinsics.checkParameterIsNotNull(notificationContent, "notificationContent");
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.ADD_OUTGOING_NOTIFICATION, new NotificationWithContentRequest(i, notificationContent, this.identifier), ErrorCodeResponse.Transformer.INSTANCE).map(ClientNotificationRepository$addOutgoingNotification$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ADD_…   ).map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.notification.NotificationRepository
    @NotNull
    public Flowable<Notification.ExecuteNotificationAction> queryActionCommandsForOutgoingNotifications() {
        Flowable<Notification.ExecuteNotificationAction> map = this.client.create(ApiIdentifier.QUERY_ACTION_COMMANDS_FOR_OUTGOING_NOTIFICATIONS, new QuerySessionRequest(this.identifier), ExecuteNotificationActionResponse.Transformer.INSTANCE).toFlowable(BackpressureStrategy.BUFFER).map(ClientNotificationRepository$queryActionCommandsForOutgoingNotifications$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…ecuteNotificationAction }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.notification.NotificationRepository
    @NotNull
    public Single<Common.ErrorCode> removeOutgoingNotification(int i) {
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.REMOVE_OUTGOING_NOTIFICATION, new QuerySessionIntRequest(this.identifier, i), ErrorCodeResponse.Transformer.INSTANCE).map(ClientNotificationRepository$removeOutgoingNotification$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(REMO…   ).map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.notification.NotificationRepository
    @NotNull
    public Single<Common.ErrorCode> updateOutgoingNotification(int i, @NotNull Notification.NotificationContent notificationContent) {
        Intrinsics.checkParameterIsNotNull(notificationContent, "notificationContent");
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.UPDATE_OUTGOING_NOTIFICATION, new NotificationWithContentRequest(i, notificationContent, this.identifier), ErrorCodeResponse.Transformer.INSTANCE).map(ClientNotificationRepository$updateOutgoingNotification$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(UPDA…   ).map { it.errorCode }");
        return map;
    }
}
