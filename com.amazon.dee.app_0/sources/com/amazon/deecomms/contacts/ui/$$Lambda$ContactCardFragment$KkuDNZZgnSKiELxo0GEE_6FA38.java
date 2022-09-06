package com.amazon.deecomms.contacts.ui;

import android.view.View;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.util.FragmentNavigationRouter;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$KkuDNZZgnSKiELxo-0GEE_6FA38  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$ContactCardFragment$KkuDNZZgnSKiELxo0GEE_6FA38 implements View.OnLongClickListener {
    public static final /* synthetic */ $$Lambda$ContactCardFragment$KkuDNZZgnSKiELxo0GEE_6FA38 INSTANCE = new $$Lambda$ContactCardFragment$KkuDNZZgnSKiELxo0GEE_6FA38();

    private /* synthetic */ $$Lambda$ContactCardFragment$KkuDNZZgnSKiELxo0GEE_6FA38() {
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        FragmentNavigationRouter.switchToFragment(CommsView.Diagnostics, null, true);
        return true;
    }
}
