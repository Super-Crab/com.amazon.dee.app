package com.amazon.alexa.redesign.actions;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.routing.api.RoutingService;
import com.facebook.react.uimanager.ViewProps;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DevicePickerAction.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/redesign/actions/DevicePickerAction;", "Lcom/amazon/alexa/redesign/actions/Action;", "commonDevicePickerMessage", "Lcom/amazon/alexa/eventbus/api/Message;", "homeDevicePickerMessage", "eventBus", "Lcom/amazon/alexa/eventbus/api/EventBus;", "routingService", "Lcom/amazon/alexa/routing/api/RoutingService;", "metaActionType", "", ViewProps.ACCESSIBILITY_LABEL, "(Lcom/amazon/alexa/eventbus/api/Message;Lcom/amazon/alexa/eventbus/api/Message;Lcom/amazon/alexa/eventbus/api/EventBus;Lcom/amazon/alexa/routing/api/RoutingService;Ljava/lang/String;Ljava/lang/String;)V", "execute", "", "executionParams", "", "", "getOnClickUserLeavesHome", "", "AlexaMobileAndroidHomeChannel_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public final class DevicePickerAction extends Action {
    private final Message commonDevicePickerMessage;
    private final EventBus eventBus;
    private final Message homeDevicePickerMessage;
    private final RoutingService routingService;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DevicePickerAction(@NotNull Message commonDevicePickerMessage, @NotNull Message homeDevicePickerMessage, @NotNull EventBus eventBus, @NotNull RoutingService routingService, @NotNull String metaActionType, @NotNull String accessibilityLabel) {
        super(ActionFactory.DEVICE_PICKER_ACTION, metaActionType, accessibilityLabel);
        Intrinsics.checkParameterIsNotNull(commonDevicePickerMessage, "commonDevicePickerMessage");
        Intrinsics.checkParameterIsNotNull(homeDevicePickerMessage, "homeDevicePickerMessage");
        Intrinsics.checkParameterIsNotNull(eventBus, "eventBus");
        Intrinsics.checkParameterIsNotNull(routingService, "routingService");
        Intrinsics.checkParameterIsNotNull(metaActionType, "metaActionType");
        Intrinsics.checkParameterIsNotNull(accessibilityLabel, "accessibilityLabel");
        this.eventBus = eventBus;
        this.commonDevicePickerMessage = commonDevicePickerMessage;
        this.homeDevicePickerMessage = homeDevicePickerMessage;
        this.routingService = routingService;
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute() {
        this.eventBus.publish(this.commonDevicePickerMessage);
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public boolean getOnClickUserLeavesHome() {
        return false;
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute(@NotNull Map<String, ? extends Object> executionParams) {
        Intrinsics.checkParameterIsNotNull(executionParams, "executionParams");
        this.eventBus.publish(this.commonDevicePickerMessage);
    }
}
