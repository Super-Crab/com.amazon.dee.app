package com.amazon.alexa.accessory.repositories.notification;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Notification;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.notification.NotificationProducer;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import io.reactivex.rxjava3.processors.PublishProcessor;
/* loaded from: classes6.dex */
public class MemoryNotificationRepository extends BaseProducer<NotificationProducer.ActionHandler> implements NotificationRepository, NotificationProducer, NotificationProvider {
    private final FlowableProcessor<Notification.ExecuteNotificationAction> outgoingNotificationActionProcessor = PublishProcessor.create().toSerialized();
    private final Object lock = new Object();

    private static boolean isOutgoingNotificationIdValid(int i) {
        return i >= 0;
    }

    @Override // com.amazon.alexa.accessory.repositories.notification.NotificationRepository
    public Single<Common.ErrorCode> addOutgoingNotification(final int i, final Notification.NotificationContent notificationContent) {
        if (!isOutgoingNotificationIdValid(i)) {
            return Single.just(Common.ErrorCode.INVALID);
        }
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.notification.-$$Lambda$MemoryNotificationRepository$16H73bWzRHQa1AvQa9GOZj2iLac
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryNotificationRepository.this.lambda$addOutgoingNotification$0$MemoryNotificationRepository(i, notificationContent, result);
            }
        });
    }

    public /* synthetic */ void lambda$addOutgoingNotification$0$MemoryNotificationRepository(int i, Notification.NotificationContent notificationContent, Producer.Result result) {
        getHandler().handleAddOutgoingNotification(i, notificationContent, result);
    }

    public /* synthetic */ void lambda$removeOutgoingNotification$2$MemoryNotificationRepository(int i, Producer.Result result) {
        getHandler().handleRemoveOutgoingNotification(i, result);
    }

    public /* synthetic */ void lambda$updateOutgoingNotification$1$MemoryNotificationRepository(int i, Notification.NotificationContent notificationContent, Producer.Result result) {
        getHandler().handleUpdateOutgoingNotification(i, notificationContent, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.notification.NotificationProvider
    public void provideActionCommandsForOutgoingNotification(Notification.ExecuteNotificationAction executeNotificationAction) {
        Preconditions.notNull(executeNotificationAction, "action");
        synchronized (this.lock) {
            this.outgoingNotificationActionProcessor.onNext(executeNotificationAction);
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.notification.NotificationRepository
    public Flowable<Notification.ExecuteNotificationAction> queryActionCommandsForOutgoingNotifications() {
        return this.outgoingNotificationActionProcessor;
    }

    @Override // com.amazon.alexa.accessory.repositories.notification.NotificationRepository
    public Single<Common.ErrorCode> removeOutgoingNotification(final int i) {
        if (!isOutgoingNotificationIdValid(i)) {
            return Single.just(Common.ErrorCode.INVALID);
        }
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.notification.-$$Lambda$MemoryNotificationRepository$3b-4DGGkSrjtuYsUqS04idmN1IE
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryNotificationRepository.this.lambda$removeOutgoingNotification$2$MemoryNotificationRepository(i, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.notification.NotificationRepository
    public Single<Common.ErrorCode> updateOutgoingNotification(final int i, final Notification.NotificationContent notificationContent) {
        if (!isOutgoingNotificationIdValid(i)) {
            return Single.just(Common.ErrorCode.INVALID);
        }
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.notification.-$$Lambda$MemoryNotificationRepository$Tutggs5whM9go4GXT96SAQ0gaRY
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryNotificationRepository.this.lambda$updateOutgoingNotification$1$MemoryNotificationRepository(i, notificationContent, result);
            }
        });
    }
}
