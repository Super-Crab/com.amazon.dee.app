package com.amazon.dee.app.dependencies;

import com.amazon.alexa.viewmanagement.impl.ViewControllerFragment;
import com.amazon.dee.app.elements.ReactFeatureControllerTransition;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.elements.ReactFeatureViewController;
import com.amazon.dee.app.ui.main.MainActivity;
import com.amazon.dee.app.ui.main.MainViewModel;
import dagger.Subcomponent;
@MainScope
@Subcomponent(modules = {MainModule.class, ConversationModule.class, ElementsModule.class, VoiceUiModule.class, HomeModule.class, ViewManagerModule.class, BatteryOptimizationUiModule.class, DriveModeMainModule.class})
/* loaded from: classes12.dex */
public interface MainComponent {
    ViewControllerFragment inject(ViewControllerFragment viewControllerFragment);

    ReactFeatureControllerTransition inject(ReactFeatureControllerTransition reactFeatureControllerTransition);

    ReactFeatureViewController inject(ReactFeatureViewController reactFeatureViewController);

    MainActivity inject(MainActivity mainActivity);

    MainViewModel inject(MainViewModel mainViewModel);

    WebComponent plus(WebModule webModule);

    ReactFeatureManager reactFeatureManager();
}
