package com.amazon.dee.app.ui.fullscreentakeover;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import com.amazon.dee.app.services.logging.Log;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
/* loaded from: classes12.dex */
public class FullScreenTakeoverViewModel {
    private static final String ELEMENTS_FST_MODULE_NAME = "ElementsFullScreenTakeover";
    private static final String TAG = Log.tag(FullScreenTakeoverViewModel.class);
    private volatile boolean isInitialized;
    private ReactInstanceManager reactInstanceManager;

    public FullScreenTakeoverViewModel(ReactInstanceManager reactInstanceManager) {
        this.reactInstanceManager = reactInstanceManager;
    }

    @VisibleForTesting
    protected ReactRootView createFullScreenTakeoverView(Context context) {
        return new ReactRootView(context);
    }

    public void initialize(Context context, ViewGroup viewGroup) {
        if (this.isInitialized) {
            return;
        }
        ReactRootView createFullScreenTakeoverView = createFullScreenTakeoverView(context);
        createFullScreenTakeoverView.startReactApplication(this.reactInstanceManager, ELEMENTS_FST_MODULE_NAME, null);
        createFullScreenTakeoverView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        viewGroup.addView(createFullScreenTakeoverView);
        this.isInitialized = true;
    }
}
