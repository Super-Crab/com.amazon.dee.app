package com.amazon.alexa.protocols.usermessage;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
/* loaded from: classes9.dex */
public interface UserMessageService {

    /* loaded from: classes9.dex */
    public interface MessageBuilder {
        void show();

        @NonNull
        MessageBuilder withAction(@StringRes int i, @NonNull Runnable runnable);

        @NonNull
        MessageBuilder withCancel(@StringRes int i, @NonNull Runnable runnable);

        @NonNull
        MessageBuilder withDismiss(@NonNull Runnable runnable);
    }

    @NonNull
    MessageBuilder message(@StringRes int i);

    @NonNull
    MessageBuilder message(CharSequence charSequence);
}
