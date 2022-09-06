package com.amazon.alexa.accessory.repositories.instrumentation;

import com.amazon.alexa.accessory.protocol.Common;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface InstrumentationRepository {
    Single<Common.ErrorCode> issueRemoteClearPairing();

    Single<Common.ErrorCode> issueRemoteCommand(String str);

    Single<Common.ErrorCode> issueRemoteReset();

    Single<Common.ErrorCode> issueRemoteRestart();
}
