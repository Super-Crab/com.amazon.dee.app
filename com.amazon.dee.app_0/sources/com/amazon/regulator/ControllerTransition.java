package com.amazon.regulator;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes13.dex */
public interface ControllerTransition extends Parcelable {

    /* loaded from: classes13.dex */
    public interface CompletionListener {
        public static final CompletionListener NONE = new CompletionListener() { // from class: com.amazon.regulator.ControllerTransition.CompletionListener.1
            @Override // com.amazon.regulator.ControllerTransition.CompletionListener
            public void completeTransition() {
            }
        };

        void completeTransition();
    }

    void completeTransition();

    void performTransition(ViewGroup viewGroup, View view, View view2, CompletionListener completionListener);
}
