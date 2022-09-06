package com.amazon.alexa.accessory.repositories.calling;

import com.amazon.alexa.accessory.protocol.Common;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface CallingRepository {
    Single<Common.ErrorCode> forwardAtCommand(ATCommand aTCommand);
}
