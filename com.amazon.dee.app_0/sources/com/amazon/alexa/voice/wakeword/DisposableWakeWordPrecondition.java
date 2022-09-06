package com.amazon.alexa.voice.wakeword;

import com.amazon.alexa.wakeword.precondition.BaseWakeWordPrecondition;
import com.amazon.alexa.wakeword.precondition.WakeWordPrecondition;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes11.dex */
public class DisposableWakeWordPrecondition extends BaseWakeWordPrecondition {
    private boolean isWakeWordAllowed;
    private final Disposable subscription;

    public DisposableWakeWordPrecondition(ObservableWakeWordPrecondition observableWakeWordPrecondition) {
        this.subscription = observableWakeWordPrecondition.isWakeWordAllowed().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.wakeword.-$$Lambda$DisposableWakeWordPrecondition$jGRigR6Uf2s4C9BH-g9qtQ4WZIQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DisposableWakeWordPrecondition.this.updateCurrentValue(((Boolean) obj).booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentValue(boolean z) {
        this.isWakeWordAllowed = z;
        notifyStateChanged();
    }

    @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
    public boolean isWakeWordAllowed() {
        return this.isWakeWordAllowed;
    }

    @Override // com.amazon.alexa.wakeword.precondition.BaseWakeWordPrecondition, com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
    public void unsubscribe(WakeWordPrecondition.ChangeListener changeListener) {
        super.unsubscribe(changeListener);
        this.subscription.dispose();
    }
}
