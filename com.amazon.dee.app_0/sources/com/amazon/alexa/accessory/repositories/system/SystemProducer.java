package com.amazon.alexa.accessory.repositories.system;

import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.internal.repositories.MaybeResult;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface SystemProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleConnectUser(String str, Producer.Result<Common.ErrorCode> result);

        void handleDisconnectUser(String str, Producer.Result<Common.ErrorCode> result);

        void handleGetCurrentUser(Producer.Result<System.User> result);

        void handleGetI18nConfig(Producer.Result<MaybeResult.MaybeValue<DavsI18nConfig>> result);

        void handleGetUsers(Producer.Result<System.Users> result);

        void handleRemoveDevice(Producer.Result<Common.ErrorCode> result);

        void handleResetConnection(int i, boolean z, Producer.Result<Common.ErrorCode> result);

        void handleSetLocale(System.Locale locale, Producer.Result<Common.ErrorCode> result);

        void handleSwitchUser(int i, Producer.Result<Common.ErrorCode> result);

        void handleUnpairUser(String str, Producer.Result<Common.ErrorCode> result);
    }
}
