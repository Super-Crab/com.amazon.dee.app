package com.amazon.alexa.voice.ui.onedesign.tta;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
/* loaded from: classes11.dex */
public abstract class UserInputView extends FrameLayout {
    protected final PublishSubject<Object> onSend;
    protected final PublishSubject<Object> onVoiceIngress;

    public UserInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSend = PublishSubject.create();
        this.onVoiceIngress = PublishSubject.create();
    }

    public abstract EditText getTextInputView();

    public final Observable<?> onSend() {
        return this.onSend;
    }

    public final Observable<?> onVoiceIngress() {
        return this.onVoiceIngress;
    }

    public abstract void setSendEnabled(boolean z);
}
