package com.amazon.alexa.biloba.view.account;

import androidx.lifecycle.Observer;
import com.amazon.alexa.biloba.model.CareActor;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.biloba.view.account.-$$Lambda$ProfileSettingsView$ffazToOK5XqEQnN7d0mL8_kf9Z4  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$ProfileSettingsView$ffazToOK5XqEQnN7d0mL8_kf9Z4 implements Observer {
    private final /* synthetic */ ProfileSettingsView f$0;

    public /* synthetic */ $$Lambda$ProfileSettingsView$ffazToOK5XqEQnN7d0mL8_kf9Z4(ProfileSettingsView profileSettingsView) {
        this.f$0 = profileSettingsView;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        ProfileSettingsView.lambda$ffazToOK5XqEQnN7d0mL8_kf9Z4(this.f$0, (CareActor) obj);
    }
}
