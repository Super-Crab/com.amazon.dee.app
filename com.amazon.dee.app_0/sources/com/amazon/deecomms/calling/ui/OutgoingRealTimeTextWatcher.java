package com.amazon.deecomms.calling.ui;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.accessibility.RealTimeTextPresenter;
import com.amazon.deecomms.calling.accessibility.RealTimeTextView;
import java.lang.ref.WeakReference;
/* loaded from: classes12.dex */
public class OutgoingRealTimeTextWatcher implements TextWatcher {
    private Handler ephemeralTextHandler;
    private Runnable ephemeralTextRunnable;
    private WeakReference<EditText> nearSideEditText;
    private RealTimeTextPresenter realTimeTextPresenter;
    private RealTimeTextView realTimeTextView;

    public OutgoingRealTimeTextWatcher(@NonNull RealTimeTextPresenter realTimeTextPresenter, @NonNull RealTimeTextView realTimeTextView, @NonNull EditText editText) {
        this.realTimeTextPresenter = realTimeTextPresenter;
        this.realTimeTextView = realTimeTextView;
        this.nearSideEditText = new WeakReference<>(editText);
        init();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(@NonNull Editable editable) {
        this.realTimeTextPresenter.localTextInputChanged(editable.toString());
        EditText editText = this.nearSideEditText.get();
        if (editText != null) {
            this.realTimeTextView.enforceTwoLineLimit(editText, false);
            this.realTimeTextView.setLocalTextState(editText.getText().toString());
            this.realTimeTextView.addClearButtonToEditText();
        }
        this.ephemeralTextHandler.postDelayed(this.ephemeralTextRunnable, 8000L);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(@NonNull CharSequence charSequence, int i, int i2, int i3) {
        this.realTimeTextPresenter.beforeTextInputChanged(charSequence);
    }

    public void init() {
        this.ephemeralTextHandler = new Handler(Looper.getMainLooper());
        this.ephemeralTextRunnable = new Runnable() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$OutgoingRealTimeTextWatcher$qbF76_T63r4emDFJJ4x3UZ3qo_0
            @Override // java.lang.Runnable
            public final void run() {
                OutgoingRealTimeTextWatcher.this.lambda$init$0$OutgoingRealTimeTextWatcher();
            }
        };
    }

    public /* synthetic */ void lambda$init$0$OutgoingRealTimeTextWatcher() {
        WeakReference<EditText> weakReference = this.nearSideEditText;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        EditText editText = this.nearSideEditText.get();
        if (editText.length() <= 0) {
            return;
        }
        editText.getEditableText().clear();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@NonNull CharSequence charSequence, int i, int i2, int i3) {
        this.ephemeralTextHandler.removeCallbacks(this.ephemeralTextRunnable);
    }

    public OutgoingRealTimeTextWatcher(@NonNull RealTimeTextPresenter realTimeTextPresenter, @NonNull RealTimeTextView realTimeTextView, @NonNull EditText editText, @NonNull Handler handler, @NonNull Runnable runnable) {
        this.realTimeTextPresenter = realTimeTextPresenter;
        this.realTimeTextView = realTimeTextView;
        this.nearSideEditText = new WeakReference<>(editText);
        this.ephemeralTextHandler = handler;
        this.ephemeralTextRunnable = runnable;
    }
}
