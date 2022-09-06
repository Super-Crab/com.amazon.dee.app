package com.amazon.alexa.accessory.capabilities.notification;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.ErrorCodeAction;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Notification;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.notification.NotificationProducer;
import com.amazon.alexa.accessory.repositories.notification.NotificationProvider;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
/* loaded from: classes.dex */
public class NotificationCapability extends AccessoryCapability {
    private final NotificationActionHandler actionHandler;
    private final NotificationProducer notificationProducer;
    private final NotificationProvider notificationProvider;
    private ControlStream stream;

    /* loaded from: classes.dex */
    final class NotificationActionHandler implements NotificationProducer.ActionHandler {
        private final ActionQueue addNotificationQueue = new ActionQueue();
        private final ActionQueue updateNotificationQueue = new ActionQueue();
        private final ActionQueue removeNotificationQueue = new ActionQueue();

        public NotificationActionHandler() {
        }

        void cancelAllActions() {
            this.addNotificationQueue.cancelAll();
            this.updateNotificationQueue.cancelAll();
            this.removeNotificationQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.notification.NotificationProducer.ActionHandler
        public void handleAddOutgoingNotification(int i, Notification.NotificationContent notificationContent, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(notificationContent, "content");
            Preconditions.notNull(result, "result");
            this.addNotificationQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.ADD_NOTIFICATION).setAddNotification(Notification.AddNotification.newBuilder().setNotificationUid(i).setNotificationContent(notificationContent).mo10084build()).mo10084build()), NotificationCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.notification.NotificationProducer.ActionHandler
        public void handleRemoveOutgoingNotification(int i, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(result, "result");
            this.removeNotificationQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.REMOVE_NOTIFICATION).setRemoveNotification(Notification.RemoveNotification.newBuilder().setNotificationUid(i).mo10084build()).mo10084build()), NotificationCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.notification.NotificationProducer.ActionHandler
        public void handleUpdateOutgoingNotification(int i, Notification.NotificationContent notificationContent, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(notificationContent, "content");
            Preconditions.notNull(result, "result");
            this.updateNotificationQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.UPDATE_NOTIFICATION).setUpdateNotification(Notification.UpdateNotification.newBuilder().setNotificationUid(i).setNotificationContent(notificationContent).mo10084build()).mo10084build()), NotificationCapability.this.stream, result));
        }
    }

    public NotificationCapability(NotificationProducer notificationProducer, NotificationProvider notificationProvider) {
        Preconditions.notNull(notificationProducer, "notificationProducer");
        Preconditions.notNull(notificationProvider, "notificationProvider");
        this.notificationProducer = notificationProducer;
        this.notificationProvider = notificationProvider;
        this.actionHandler = new NotificationActionHandler();
    }

    private ControlMessageHandler<Notification.ExecuteNotificationAction> getExecuteNotificationActionHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.notification.-$$Lambda$NotificationCapability$llWWX-T-a5wHQxPTF9q8S056FjM
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                NotificationCapability.this.lambda$getExecuteNotificationActionHandler$0$NotificationCapability(controlStream, command, (Notification.ExecuteNotificationAction) obj);
            }
        };
    }

    public /* synthetic */ void lambda$getExecuteNotificationActionHandler$0$NotificationCapability(ControlStream controlStream, Accessories.Command command, Notification.ExecuteNotificationAction executeNotificationAction) throws Exception {
        this.notificationProvider.provideActionCommandsForOutgoingNotification(executeNotificationAction);
        controlStream.respond(Accessories.Command.EXECUTE_NOTIFICATION_ACTION, Common.ErrorCode.SUCCESS);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.notificationProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.notificationProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.EXECUTE_NOTIFICATION_ACTION, getExecuteNotificationActionHandler());
        accessoryDescriptor.add(this.stream);
    }
}
