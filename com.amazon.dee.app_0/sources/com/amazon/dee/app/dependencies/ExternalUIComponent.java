package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.external.ExternalUIViewModel;
import dagger.Subcomponent;
@ExternalUIScope
@Subcomponent(modules = {ExternalUIModule.class})
/* loaded from: classes12.dex */
public interface ExternalUIComponent {
    ExternalUIViewModel inject(ExternalUIViewModel externalUIViewModel);
}
