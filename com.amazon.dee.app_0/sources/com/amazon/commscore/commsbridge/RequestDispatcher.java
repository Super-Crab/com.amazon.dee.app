package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
/* loaded from: classes12.dex */
interface RequestDispatcher {
    void dispatch(@NonNull RequestMessage requestMessage, @NonNull ResponseResolver responseResolver);
}
