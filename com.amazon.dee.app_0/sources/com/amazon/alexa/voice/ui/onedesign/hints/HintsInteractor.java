package com.amazon.alexa.voice.ui.onedesign.hints;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.hints.HintsContract;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes11.dex */
public final class HintsInteractor implements HintsContract.Interactor {
    private final OnHintsDismissedListener listener;
    private final HintsContract.Mediator mediator;

    public HintsInteractor(@Nullable OnHintsDismissedListener onHintsDismissedListener, @NonNull HintsContract.Mediator mediator) {
        Preconditions.nonNull(mediator, "mediator muts be non-null.");
        this.listener = onHintsDismissedListener;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
        OnHintsDismissedListener onHintsDismissedListener = this.listener;
        if (onHintsDismissedListener != null) {
            onHintsDismissedListener.onHintsDismissed();
        }
    }
}
