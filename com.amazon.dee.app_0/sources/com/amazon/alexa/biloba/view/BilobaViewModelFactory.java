package com.amazon.alexa.biloba.view;

import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsViewModel;
import java.util.HashMap;
/* loaded from: classes6.dex */
public final class BilobaViewModelFactory {
    private static BilobaViewModelFactory factory;
    private HashMap<String, BilobaViewModel> viewModels = new HashMap<>();

    private BilobaViewModelFactory() {
    }

    public static BilobaViewModelFactory getInstance() {
        if (factory == null) {
            factory = new BilobaViewModelFactory();
        }
        return factory;
    }

    public BilobaViewModel getViewModelFor(String str) {
        if (Routes.BILOBA_ALERT_FORM.equals(str)) {
            if (!this.viewModels.containsKey(Routes.BILOBA_ALERT_FORM)) {
                this.viewModels.put(Routes.BILOBA_ALERT_FORM, new AlertSettingsViewModel());
            }
            return this.viewModels.get(Routes.BILOBA_ALERT_FORM);
        }
        return null;
    }
}
