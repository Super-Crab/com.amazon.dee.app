package com.amazon.dee.app.ui.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.amazon.dee.app.framework.ViewModel;
/* loaded from: classes12.dex */
public class LoadingProgressViewModel implements ViewModel {
    String message;

    @Override // com.amazon.dee.app.framework.ViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void destroy() {
    }

    public String getMessage() {
        return this.message;
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    @Nullable
    public Bundle saveState() {
        return null;
    }

    public void setMessage(CharSequence charSequence) {
        this.message = charSequence.toString();
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void updateContentMode(int i) {
    }
}
