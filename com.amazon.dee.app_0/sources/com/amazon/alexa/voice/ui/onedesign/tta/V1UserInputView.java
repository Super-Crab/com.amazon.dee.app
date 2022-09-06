package com.amazon.alexa.voice.ui.onedesign.tta;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.amazon.alexa.vox.ui.onedesign.R;
import io.reactivex.rxjava3.subjects.PublishSubject;
/* loaded from: classes11.dex */
public class V1UserInputView extends UserInputView {
    private final View sendButton;
    private final EditText textInput;

    public V1UserInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.text_ui_od_user_input, (ViewGroup) this, false);
        this.textInput = (EditText) inflate.findViewById(R.id.input_text);
        this.sendButton = inflate.findViewById(R.id.send_button);
        View findViewById = inflate.findViewById(R.id.tta_voice_ingress);
        View view = this.sendButton;
        PublishSubject<Object> publishSubject = this.onSend;
        publishSubject.getClass();
        view.setOnClickListener(new $$Lambda$NPHi3zhGfmjwBBFyE_yS6I5qrx0(publishSubject));
        PublishSubject<Object> publishSubject2 = this.onVoiceIngress;
        publishSubject2.getClass();
        findViewById.setOnClickListener(new $$Lambda$NPHi3zhGfmjwBBFyE_yS6I5qrx0(publishSubject2));
        this.textInput.setImeOptions(4);
        this.textInput.setRawInputType(16385);
        this.textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$V1UserInputView$fLVn3eFRB50Uyub8_7Y_PDKX8A0
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return V1UserInputView.this.lambda$new$0$V1UserInputView(textView, i, keyEvent);
            }
        });
        addView(inflate);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.UserInputView
    public EditText getTextInputView() {
        return this.textInput;
    }

    public /* synthetic */ boolean lambda$new$0$V1UserInputView(TextView textView, int i, KeyEvent keyEvent) {
        this.onSend.onNext(new Object());
        return true;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.UserInputView
    public void setSendEnabled(boolean z) {
        this.sendButton.setEnabled(z);
        this.sendButton.setVisibility(z ? 0 : 8);
    }
}
