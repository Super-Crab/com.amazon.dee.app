package com.amazon.alexa.protocols.messaging;

import android.os.Bundle;
import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public interface Message {
    void cancel();

    @NonNull
    Bundle get();

    boolean isCanceled();
}
