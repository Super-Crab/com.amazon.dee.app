package com.amazon.alexa.accessory.repositories.nonhfpcalling;

import com.amazon.alexa.accessory.protocol.Common;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface NonHfpCallingRepository {
    Single<Common.ErrorCode> updateCallInfo(CallInfo callInfo);
}
