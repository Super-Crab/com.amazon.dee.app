package com.facebook.react.views.text;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ViewGroupClickEvent;
/* loaded from: classes2.dex */
class ReactClickableSpan extends ClickableSpan implements ReactSpan {
    private final int mForegroundColor;
    private final int mReactTag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactClickableSpan(int i, int i2) {
        this.mReactTag = i;
        this.mForegroundColor = i2;
    }

    public int getReactTag() {
        return this.mReactTag;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(@NonNull View view) {
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) view.getContext(), this.mReactTag);
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new ViewGroupClickEvent(this.mReactTag));
        }
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(@NonNull TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setColor(this.mForegroundColor);
        textPaint.setUnderlineText(false);
    }
}
