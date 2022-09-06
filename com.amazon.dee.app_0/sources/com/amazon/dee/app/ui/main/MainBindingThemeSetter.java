package com.amazon.dee.app.ui.main;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewStubProxy;
import com.amazon.dee.app.databinding.MainBinding;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class MainBindingThemeSetter {
    private static final String TAG = "MainBindingThemeSetter";
    private List<View> backgroundViewList;
    private Context context;
    private ObservableField<Themes> currentTheme;
    private List<Drawable> drawableList;
    private MainBinding mainBinding;
    private List<ImageView> tabIconList;
    private List<TextView> tabTextList;
    private List<TextView> textList;
    @VisibleForTesting
    Observable.OnPropertyChangedCallback themingChangeCallback;
    private Window window;

    public MainBindingThemeSetter(Window window) {
        this.window = window;
        this.context = window.getContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyTheme(AlexaTheme alexaTheme) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Applying theme ");
        outline107.append(alexaTheme.getClass().getSimpleName());
        outline107.toString();
        setMainBackgroundColors(alexaTheme.getBackgroundColor());
        setTabIconColors(alexaTheme.getTabIconColor());
        setTabTextColors(alexaTheme.getTabTextColor());
        setStatusAndNavigationBarColor(alexaTheme, this.window);
        setContrastColors(alexaTheme.getContrastColor());
        setUniqueColors(alexaTheme);
    }

    private void setContrastColors(int i) {
        setTextColors(i);
        setDrawableColors(i);
        this.mainBinding.containerTabDivider.setBackgroundColor(i);
    }

    private void setDrawableColors(int i) {
        if (this.drawableList == null) {
            this.drawableList = Arrays.asList(this.mainBinding.back.getDrawable());
        }
        for (Drawable drawable : this.drawableList) {
            drawable.setTint(i);
        }
    }

    private void setMainBackgroundColors(final int i) {
        if (this.backgroundViewList == null) {
            MainBinding mainBinding = this.mainBinding;
            this.backgroundViewList = Arrays.asList(mainBinding.fullscreenContainer, mainBinding.tabLayout, mainBinding.toolbar, mainBinding.rnContainer, mainBinding.homeContainer, mainBinding.primaryContainer, mainBinding.transportBar, mainBinding.drawerLayout, mainBinding.contentBackdrop);
            ViewStubProxy viewStubProxy = this.mainBinding.webviewstub;
            if (viewStubProxy != null && viewStubProxy.isInflated()) {
                this.backgroundViewList = new ArrayList(this.backgroundViewList);
                this.backgroundViewList.add(viewStubProxy.getRoot());
            } else {
                viewStubProxy.setOnInflateListener(new ViewStub.OnInflateListener() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainBindingThemeSetter$g3L_AT94SKS6AbdY7KyzQwYssRE
                    @Override // android.view.ViewStub.OnInflateListener
                    public final void onInflate(ViewStub viewStub, View view) {
                        view.setBackgroundColor(i);
                    }
                });
            }
        }
        for (View view : this.backgroundViewList) {
            view.setBackgroundColor(i);
        }
    }

    private void setStatusAndNavigationBarColor(AlexaTheme alexaTheme, Window window) {
        window.addFlags(Integer.MIN_VALUE);
        window.getDecorView().setSystemUiVisibility(alexaTheme.getSystemUiVisibility());
        window.setStatusBarColor(alexaTheme.getBackgroundColor());
        window.setNavigationBarColor(alexaTheme.getNavigationBarBackgroundColor());
    }

    private void setTabIconColors(ColorStateList colorStateList) {
        if (this.tabIconList == null) {
            MainBinding mainBinding = this.mainBinding;
            this.tabIconList = Arrays.asList(mainBinding.tabChannelHomeIcon, mainBinding.tabConversations, mainBinding.tabChannelsDeviceIcon, mainBinding.tabMoreIcon, mainBinding.tabNowPlayingIcon);
        }
        for (ImageView imageView : this.tabIconList) {
            imageView.setImageTintList(colorStateList);
        }
    }

    private void setTabTextColors(ColorStateList colorStateList) {
        if (this.tabTextList == null) {
            MainBinding mainBinding = this.mainBinding;
            this.tabTextList = Arrays.asList(mainBinding.tabChannelHomeText, mainBinding.tabConversationsLayoutText, mainBinding.tabChannelsDeviceText, mainBinding.tabMoreText, mainBinding.tabNowPlayingText);
        }
        for (TextView textView : this.tabTextList) {
            textView.setTextColor(colorStateList);
        }
    }

    private void setTextColors(int i) {
        if (this.textList == null) {
            this.textList = Arrays.asList(this.mainBinding.title);
        }
        for (TextView textView : this.textList) {
            textView.setTextColor(i);
        }
    }

    public void deregister() {
        Observable.OnPropertyChangedCallback onPropertyChangedCallback;
        ObservableField<Themes> observableField = this.currentTheme;
        if (observableField == null || (onPropertyChangedCallback = this.themingChangeCallback) == null) {
            return;
        }
        observableField.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }

    public void register(MainBinding mainBinding, final ObservableField<Themes> observableField) {
        this.mainBinding = mainBinding;
        this.currentTheme = observableField;
        this.themingChangeCallback = new Observable.OnPropertyChangedCallback() { // from class: com.amazon.dee.app.ui.main.MainBindingThemeSetter.1
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                if (observableField.get() == Themes.NO_USER) {
                    MainBindingThemeSetter mainBindingThemeSetter = MainBindingThemeSetter.this;
                    mainBindingThemeSetter.applyTheme(new NoUserTheme(mainBindingThemeSetter.context));
                    return;
                }
                MainBindingThemeSetter mainBindingThemeSetter2 = MainBindingThemeSetter.this;
                mainBindingThemeSetter2.applyTheme(new MosaicTheme(mainBindingThemeSetter2.context));
            }
        };
        observableField.addOnPropertyChangedCallback(this.themingChangeCallback);
    }

    @VisibleForTesting
    void setUniqueColors(AlexaTheme alexaTheme) {
        this.mainBinding.tabLine.getBackground().setTint(alexaTheme.getTabSelectionBarColor());
        this.mainBinding.tabConversationsIndicator.getDrawable().setTint(alexaTheme.getNotificationIconColor());
    }
}
