package com.amazon.alexa.viewprovider.registry;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.fitness.view.homecard.FitnessViewProvider;
import com.amazon.alexa.home.viewprovider.react.ReactCardViewProvider;
import com.amazon.alexa.mode.drive.DriveModeIngressCardViewProvider;
import com.amazon.alexa.sendtoapp.activitycard.SendToAppViewProvider;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.provider.ViewProvider;
import com.amazon.alexa.viewprovider.api.registry.ViewProviderRegistry;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import com.facebook.react.ReactInstanceManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class CardViewProviderRegistry implements ViewProviderRegistry {
    private final BridgeStatusService bridgeStatusService;
    private final Context context;
    private List<ViewProvider> providers;
    private final ReactInstanceManager reactInstanceManager;

    public CardViewProviderRegistry(Context context, ReactInstanceManager reactInstanceManager, BridgeStatusService bridgeStatusService) {
        this.context = context;
        this.reactInstanceManager = reactInstanceManager;
        this.bridgeStatusService = bridgeStatusService;
    }

    private List<ViewProvider> getViewProviders() {
        List<ViewProvider> list = this.providers;
        if (list != null) {
            return list;
        }
        this.providers = new ArrayList();
        this.providers.add(new ReactCardViewProvider(this.context, this.reactInstanceManager, this.bridgeStatusService));
        this.providers.add(new DriveModeIngressCardViewProvider(this.context));
        this.providers.add(new FitnessViewProvider(this.context));
        this.providers.add(new SendToAppViewProvider(this.context));
        return this.providers;
    }

    @Override // com.amazon.alexa.viewprovider.api.registry.ViewProviderRegistry
    @Nullable
    public ViewModule createViewModule(@NonNull JSONObject jSONObject, @NonNull EventCapture eventCapture) {
        for (ViewProvider viewProvider : getViewProviders()) {
            if (viewProvider.isSupported(jSONObject)) {
                return viewProvider.createView(jSONObject, eventCapture);
            }
        }
        return null;
    }
}
