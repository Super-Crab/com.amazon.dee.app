package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.mode.model.WirelessChargingEventModel;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.util.LogTag;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.gson.GsonBuilder;
/* loaded from: classes9.dex */
public class WirelessChargingCommandV2 extends Command {
    private static final String TAG = LogTag.forClass(WirelessChargingCommandV2.class);
    private final Boolean isChargingError;

    public WirelessChargingCommandV2(StateDependencies stateDependencies, Boolean bool) {
        super(stateDependencies);
        this.isChargingError = bool;
    }

    private String getWirelessChargingEventJson() {
        return new GsonBuilder().create().toJson(new WirelessChargingEventModel(this.isChargingError.booleanValue()));
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        Log.i(TAG, "execute WirelessChargingCommandV2");
        Message build = new Message.Builder().setEventType(Constants.WIRELESS_CHARGING_STATUS_CHANGED_EVENT).setPayload(getWirelessChargingEventJson()).build();
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        eventBus.publish(build);
    }

    public boolean getIsChargingError() {
        return this.isChargingError.booleanValue();
    }
}
