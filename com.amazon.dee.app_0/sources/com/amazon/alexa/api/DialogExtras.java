package com.amazon.alexa.api;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaDialogExtras;
/* loaded from: classes6.dex */
public class DialogExtras extends AlexaDialogExtras {
    public static final AlexaDialogExtras zZm = AlexaDialogExtras.builder().build();

    public DialogExtras(@Nullable Bundle bundle) {
        super(bundle);
    }

    public static AlexaDialogExtras.Builder getBuilder(@NonNull AlexaDialogExtras alexaDialogExtras) {
        return AlexaDialogExtras.getBuilder(alexaDialogExtras);
    }
}
