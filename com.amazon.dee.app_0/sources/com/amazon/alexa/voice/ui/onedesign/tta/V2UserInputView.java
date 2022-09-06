package com.amazon.alexa.voice.ui.onedesign.tta;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.alexa.vox.ui.onedesign.R;
import io.reactivex.rxjava3.subjects.PublishSubject;
/* loaded from: classes11.dex */
public class V2UserInputView extends UserInputView {
    private static final int LEVEL_TEXT_ACTIVE = 2;
    private static final int LEVEL_TEXT_INACTIVE = 1;
    private final View sendButton;
    private final EditText textInput;
    private final ImageView textInputBackground;

    public V2UserInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.text_ui_od_user_input_v2, (ViewGroup) this, false);
        this.textInput = (EditText) inflate.findViewById(R.id.input_text);
        this.textInputBackground = (ImageView) inflate.findViewById(R.id.input_background);
        this.sendButton = inflate.findViewById(R.id.send_button);
        View findViewById = inflate.findViewById(R.id.tta_voice_ingress);
        inflate.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$V2UserInputView$DNodLyhKK1DwPX4sfRh48XY1Hcg
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                V2UserInputView.this.onGlobalFocusChange(view, view2);
            }
        });
        View view = this.sendButton;
        PublishSubject<Object> publishSubject = this.onSend;
        publishSubject.getClass();
        view.setOnClickListener(new $$Lambda$NPHi3zhGfmjwBBFyE_yS6I5qrx0(publishSubject));
        PublishSubject<Object> publishSubject2 = this.onVoiceIngress;
        publishSubject2.getClass();
        findViewById.setOnClickListener(new $$Lambda$NPHi3zhGfmjwBBFyE_yS6I5qrx0(publishSubject2));
        this.textInput.setImeOptions(4);
        this.textInput.setRawInputType(16385);
        this.textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$V2UserInputView$-8Sc0IcmT-SnJGx7P8dQhEPHyNc
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return V2UserInputView.this.lambda$new$0$V2UserInputView(textView, i, keyEvent);
            }
        });
        updateVisualState();
        addView(inflate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGlobalFocusChange(View view, View view2) {
        updateVisualState();
    }

    private void updateVisualState() {
        this.textInputBackground.setImageLevel(this.textInput.hasFocus() ? 2 : 1);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.UserInputView
    public EditText getTextInputView() {
        return this.textInput;
    }

    public /* synthetic */ boolean lambda$new$0$V2UserInputView(TextView textView, int i, KeyEvent keyEvent) {
        this.onSend.onNext(new Object());
        return true;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.UserInputView
    public void setSendEnabled(boolean z) {
        this.sendButton.setEnabled(z);
    }
}
