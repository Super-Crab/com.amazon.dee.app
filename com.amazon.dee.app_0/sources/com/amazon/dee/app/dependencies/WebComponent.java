package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.WebApp;
import dagger.Subcomponent;
@WebScope
@Subcomponent(modules = {WebModule.class, BridgeModule.class})
/* loaded from: classes12.dex */
public interface WebComponent {
    void inject(WebApp webApp);
}
