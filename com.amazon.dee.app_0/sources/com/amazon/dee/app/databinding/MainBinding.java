package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.ViewStubProxy;
import androidx.drawerlayout.widget.DrawerLayout;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.toolbar.ToolbarViewModel;
import com.amazon.dee.app.ui.main.MainHandler;
import com.amazon.dee.app.ui.main.MainViewModel;
import com.amazon.dee.app.ui.view.LoadingView;
/* loaded from: classes12.dex */
public abstract class MainBinding extends ViewDataBinding {
    @NonNull
    public final ImageView back;
    @NonNull
    public final View containerTabDivider;
    @NonNull
    public final FrameLayout contentBackdrop;
    @NonNull
    public final FrameLayout conversationContainer;
    @NonNull
    public final DrawerLayout drawerLayout;
    @NonNull
    public final FrameLayout driveModeFooter;
    @NonNull
    public final FrameLayout driveModeHeader;
    @NonNull
    public final FrameLayout fullscreenContainer;
    @NonNull
    public final FrameLayout homeContainer;
    @Bindable
    protected MainHandler mHandler;
    @Bindable
    protected ToolbarViewModel mToolbarViewModel;
    @Bindable
    protected MainViewModel mViewModel;
    @NonNull
    public final FrameLayout mainContent;
    @NonNull
    public final FrameLayout player;
    @NonNull
    public final FrameLayout primaryContainer;
    @NonNull
    public final LoadingView progress;
    @NonNull
    public final FrameLayout rnContainer;
    @NonNull
    public final RelativeLayout rootContainer;
    @NonNull
    public final LinearLayout tabChannelHome;
    @NonNull
    public final ImageView tabChannelHomeIcon;
    @NonNull
    public final TextView tabChannelHomeText;
    @NonNull
    public final LinearLayout tabChannelsDevice;
    @NonNull
    public final ImageView tabChannelsDeviceIcon;
    @NonNull
    public final TextView tabChannelsDeviceText;
    @NonNull
    public final LinearLayout tabChannelsEntertainment;
    @NonNull
    public final FrameLayout tabContainer;
    @NonNull
    public final ImageView tabConversations;
    @NonNull
    public final ImageView tabConversationsIndicator;
    @NonNull
    public final LinearLayout tabConversationsLayout;
    @NonNull
    public final RelativeLayout tabConversationsLayoutIcon;
    @NonNull
    public final TextView tabConversationsLayoutText;
    @NonNull
    public final LinearLayout tabLayout;
    @NonNull
    public final LinearLayout tabLayoutContainer;
    @NonNull
    public final View tabLine;
    @NonNull
    public final LinearLayout tabMore;
    @NonNull
    public final ImageView tabMoreIcon;
    @NonNull
    public final TextView tabMoreText;
    @NonNull
    public final ImageView tabNowPlayingIcon;
    @NonNull
    public final TextView tabNowPlayingText;
    @NonNull
    public final TextView title;
    @NonNull
    public final Toolbar toolbar;
    @NonNull
    public final FrameLayout toolbarIconLayout;
    @NonNull
    public final FrameLayout toolbarLayoutContainer;
    @NonNull
    public final FrameLayout transportBar;
    @NonNull
    public final ViewStubProxy webviewstub;

    /* JADX INFO: Access modifiers changed from: protected */
    public MainBinding(Object obj, View view, int i, ImageView imageView, View view2, FrameLayout frameLayout, FrameLayout frameLayout2, DrawerLayout drawerLayout, FrameLayout frameLayout3, FrameLayout frameLayout4, FrameLayout frameLayout5, FrameLayout frameLayout6, FrameLayout frameLayout7, FrameLayout frameLayout8, FrameLayout frameLayout9, LoadingView loadingView, FrameLayout frameLayout10, RelativeLayout relativeLayout, LinearLayout linearLayout, ImageView imageView2, TextView textView, LinearLayout linearLayout2, ImageView imageView3, TextView textView2, LinearLayout linearLayout3, FrameLayout frameLayout11, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout4, RelativeLayout relativeLayout2, TextView textView3, LinearLayout linearLayout5, LinearLayout linearLayout6, View view3, LinearLayout linearLayout7, ImageView imageView6, TextView textView4, ImageView imageView7, TextView textView5, TextView textView6, Toolbar toolbar, FrameLayout frameLayout12, FrameLayout frameLayout13, FrameLayout frameLayout14, ViewStubProxy viewStubProxy) {
        super(obj, view, i);
        this.back = imageView;
        this.containerTabDivider = view2;
        this.contentBackdrop = frameLayout;
        this.conversationContainer = frameLayout2;
        this.drawerLayout = drawerLayout;
        this.driveModeFooter = frameLayout3;
        this.driveModeHeader = frameLayout4;
        this.fullscreenContainer = frameLayout5;
        this.homeContainer = frameLayout6;
        this.mainContent = frameLayout7;
        this.player = frameLayout8;
        this.primaryContainer = frameLayout9;
        this.progress = loadingView;
        this.rnContainer = frameLayout10;
        this.rootContainer = relativeLayout;
        this.tabChannelHome = linearLayout;
        this.tabChannelHomeIcon = imageView2;
        this.tabChannelHomeText = textView;
        this.tabChannelsDevice = linearLayout2;
        this.tabChannelsDeviceIcon = imageView3;
        this.tabChannelsDeviceText = textView2;
        this.tabChannelsEntertainment = linearLayout3;
        this.tabContainer = frameLayout11;
        this.tabConversations = imageView4;
        this.tabConversationsIndicator = imageView5;
        this.tabConversationsLayout = linearLayout4;
        this.tabConversationsLayoutIcon = relativeLayout2;
        this.tabConversationsLayoutText = textView3;
        this.tabLayout = linearLayout5;
        this.tabLayoutContainer = linearLayout6;
        this.tabLine = view3;
        this.tabMore = linearLayout7;
        this.tabMoreIcon = imageView6;
        this.tabMoreText = textView4;
        this.tabNowPlayingIcon = imageView7;
        this.tabNowPlayingText = textView5;
        this.title = textView6;
        this.toolbar = toolbar;
        this.toolbarIconLayout = frameLayout12;
        this.toolbarLayoutContainer = frameLayout13;
        this.transportBar = frameLayout14;
        this.webviewstub = viewStubProxy;
    }

    public static MainBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public MainHandler getHandler() {
        return this.mHandler;
    }

    @Nullable
    public ToolbarViewModel getToolbarViewModel() {
        return this.mToolbarViewModel;
    }

    @Nullable
    public MainViewModel getViewModel() {
        return this.mViewModel;
    }

    public abstract void setHandler(@Nullable MainHandler mainHandler);

    public abstract void setToolbarViewModel(@Nullable ToolbarViewModel toolbarViewModel);

    public abstract void setViewModel(@Nullable MainViewModel mainViewModel);

    @Deprecated
    public static MainBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MainBinding) ViewDataBinding.bind(obj, view, R.layout.main);
    }

    @NonNull
    @Deprecated
    public static MainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main, viewGroup, z, obj);
    }

    @NonNull
    public static MainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main, null, false, obj);
    }
}
