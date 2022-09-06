package com.facebook.react.views.text;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.annotations.ReactProp;
/* loaded from: classes2.dex */
public class ReactRawTextShadowNode extends ReactShadowNodeImpl {
    @VisibleForTesting
    public static final String PROP_TEXT = "text";
    @Nullable
    private String mText = null;

    @Nullable
    public String getText() {
        return this.mText;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    @ReactProp(name = "text")
    public void setText(@Nullable String str) {
        this.mText = str;
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getViewClass());
        sb.append(" [text: ");
        return GeneratedOutlineSupport1.outline91(sb, this.mText, "]");
    }
}
