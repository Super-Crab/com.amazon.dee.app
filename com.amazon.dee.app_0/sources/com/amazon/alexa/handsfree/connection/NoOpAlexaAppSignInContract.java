package com.amazon.alexa.handsfree.connection;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
/* loaded from: classes8.dex */
public class NoOpAlexaAppSignInContract implements AlexaAppSignInContract {
    private static final String TAG = "NoOpAlexaAppSignInContract";

    @Override // com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract
    public boolean getSignInState(@NonNull Context context, boolean z) {
        return z;
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract
    public void setup(@NonNull Context context, boolean z) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract
    public void teardown(@NonNull Context context) {
    }
}
