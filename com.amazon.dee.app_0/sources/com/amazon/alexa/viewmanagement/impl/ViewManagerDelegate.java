package com.amazon.alexa.viewmanagement.impl;

import android.view.Menu;
import androidx.annotation.MenuRes;
import androidx.appcompat.widget.Toolbar;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
/* loaded from: classes10.dex */
public interface ViewManagerDelegate extends ViewManagerEventNotifier {
    void resetHeaderMenu();

    Menu setHeaderMenu(@MenuRes int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener);

    void setHeaderTitle(CharSequence charSequence);
}
