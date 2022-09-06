package com.amazon.alexa.accessory.repositories.instrumentation;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface InstrumentationProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleIssueRemoteClearPairing(Producer.Result<Common.ErrorCode> result);

        void handleIssueRemoteCommand(String str, Producer.Result<Common.ErrorCode> result);

        void handleIssueRemoteReset(Producer.Result<Common.ErrorCode> result);

        void handleIssueRemoteRestart(Producer.Result<Common.ErrorCode> result);
    }
}
