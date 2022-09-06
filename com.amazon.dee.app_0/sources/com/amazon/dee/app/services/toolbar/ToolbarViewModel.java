package com.amazon.dee.app.services.toolbar;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableBoolean;
import com.amazon.dee.app.framework.ViewModel;
/* loaded from: classes12.dex */
public class ToolbarViewModel implements ViewModel {
    static final String TOOLBAR_SHOWN = "toolbarShown";
    public final ObservableBoolean show = new ObservableBoolean(false);

    @Override // com.amazon.dee.app.framework.ViewModel
    public void create(@Nullable Bundle bundle) {
        if (bundle != null) {
            this.show.set(bundle.getBoolean(TOOLBAR_SHOWN));
        }
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void destroy() {
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    @Nullable
    public Bundle saveState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(TOOLBAR_SHOWN, this.show.get());
        return bundle;
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void updateContentMode(int i) {
    }
}
