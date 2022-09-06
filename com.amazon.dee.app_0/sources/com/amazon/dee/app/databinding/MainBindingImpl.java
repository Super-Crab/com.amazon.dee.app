package com.amazon.dee.app.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.ViewStubProxy;
import androidx.drawerlayout.widget.DrawerLayout;
import com.amazon.dee.app.R;
import com.amazon.dee.app.generated.callback.OnClickListener;
import com.amazon.dee.app.services.toolbar.ToolbarViewModel;
import com.amazon.dee.app.ui.main.MainHandler;
import com.amazon.dee.app.ui.main.MainViewModel;
import com.amazon.dee.app.ui.view.LoadingView;
/* loaded from: classes12.dex */
public class MainBindingImpl extends MainBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback1;
    @Nullable
    private final View.OnClickListener mCallback2;
    @Nullable
    private final View.OnClickListener mCallback3;
    @Nullable
    private final View.OnClickListener mCallback4;
    @Nullable
    private final View.OnClickListener mCallback5;
    @Nullable
    private final View.OnClickListener mCallback6;
    @Nullable
    private final View.OnClickListener mCallback7;
    private long mDirtyFlags;
    private long mDirtyFlags_1;

    static {
        sViewsWithIds.put(R.id.webviewstub, 6);
        sViewsWithIds.put(R.id.root_container, 29);
        sViewsWithIds.put(R.id.toolbar_layout_container, 30);
        sViewsWithIds.put(R.id.toolbar_icon_layout, 31);
        sViewsWithIds.put(R.id.drive_mode_header, 32);
        sViewsWithIds.put(R.id.main_content, 33);
        sViewsWithIds.put(R.id.tab_channel_home_icon, 34);
        sViewsWithIds.put(R.id.tab_conversations_layout_icon, 35);
        sViewsWithIds.put(R.id.tab_conversations, 36);
        sViewsWithIds.put(R.id.tab_conversations_indicator, 37);
        sViewsWithIds.put(R.id.tab_now_playing_icon, 38);
        sViewsWithIds.put(R.id.tab_channels_device_icon, 39);
        sViewsWithIds.put(R.id.tab_more_icon, 40);
        sViewsWithIds.put(R.id.drive_mode_footer, 41);
    }

    public MainBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 42, sIncludes, sViewsWithIds));
    }

    private boolean onChangeToolbarViewModelShow(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelBackgroundColor(ObservableInt observableInt, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 33554432;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelChannelsDevicesEnabled(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelChannelsHomeEnabled(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCurrentTab(ObservableInt observableInt, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelDisableInteraction(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFooterTitleVisibility(ObservableInt observableInt, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8388608;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelHeaderTitle(ObservableField<CharSequence> observableField, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelHeaderTitleVisibility(ObservableInt observableInt, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelHideWebView(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16777216;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsBackVisible(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsConversationVisible(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsDisabled(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsDriveModeFooterVisible(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsFullScreenMode(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsHomeVisible(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1048576;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsReactNativeView(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsTabBarVisible(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsToolbarVisible(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsUserKnown(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsViewManagerVisible(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelNativeNowPlayingCardVisible(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelPlayerVisible(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4194304;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelTabAnimationFinished(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelUseChannelsTheme(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            }
            return true;
        }
        return false;
    }

    @Override // com.amazon.dee.app.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = true;
        switch (i) {
            case 1:
                MainHandler mainHandler = this.mHandler;
                if (mainHandler == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                mainHandler.onBackClicked();
                return;
            case 2:
                MainHandler mainHandler2 = this.mHandler;
                if (mainHandler2 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                mainHandler2.onHeaderClicked();
                return;
            case 3:
                MainHandler mainHandler3 = this.mHandler;
                if (mainHandler3 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                mainHandler3.onTabChannelsHomeClicked();
                return;
            case 4:
                MainHandler mainHandler4 = this.mHandler;
                if (mainHandler4 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                mainHandler4.onTabConversationsClicked();
                return;
            case 5:
                MainHandler mainHandler5 = this.mHandler;
                if (mainHandler5 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                mainHandler5.onTabChannelsEntertainmentClicked();
                return;
            case 6:
                MainHandler mainHandler6 = this.mHandler;
                if (mainHandler6 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                mainHandler6.onTabChannelsDeviceClicked();
                return;
            case 7:
                MainHandler mainHandler7 = this.mHandler;
                if (mainHandler7 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                mainHandler7.onTabNavMenuClicked();
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:184:0x0235, code lost:
        if (r51 != false) goto L796;
     */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x0726  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0744  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:716:0x099c  */
    /* JADX WARN: Removed duplicated region for block: B:720:0x09a4  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x09ab  */
    /* JADX WARN: Removed duplicated region for block: B:726:0x09b6  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x09bf  */
    /* JADX WARN: Removed duplicated region for block: B:738:0x09d9  */
    /* JADX WARN: Removed duplicated region for block: B:749:0x09f1  */
    /* JADX WARN: Removed duplicated region for block: B:752:0x09fd  */
    /* JADX WARN: Removed duplicated region for block: B:755:0x0a63  */
    /* JADX WARN: Removed duplicated region for block: B:758:0x0a71  */
    /* JADX WARN: Removed duplicated region for block: B:761:0x0a7f  */
    /* JADX WARN: Removed duplicated region for block: B:764:0x0ab7  */
    /* JADX WARN: Removed duplicated region for block: B:767:0x0ac5  */
    /* JADX WARN: Removed duplicated region for block: B:770:0x0ad0  */
    /* JADX WARN: Removed duplicated region for block: B:773:0x0ade  */
    /* JADX WARN: Removed duplicated region for block: B:776:0x0aec  */
    /* JADX WARN: Removed duplicated region for block: B:779:0x0afa  */
    /* JADX WARN: Removed duplicated region for block: B:782:0x0b08  */
    /* JADX WARN: Removed duplicated region for block: B:785:0x0b16  */
    /* JADX WARN: Removed duplicated region for block: B:788:0x0b23  */
    /* JADX WARN: Removed duplicated region for block: B:791:0x0b2e  */
    /* JADX WARN: Removed duplicated region for block: B:794:0x0b5a  */
    /* JADX WARN: Removed duplicated region for block: B:797:0x0b80  */
    /* JADX WARN: Removed duplicated region for block: B:800:0x0ba6  */
    /* JADX WARN: Removed duplicated region for block: B:803:0x0bb3  */
    /* JADX WARN: Removed duplicated region for block: B:806:0x0bc5  */
    /* JADX WARN: Removed duplicated region for block: B:809:0x0bd5  */
    /* JADX WARN: Removed duplicated region for block: B:812:0x0be5  */
    /* JADX WARN: Removed duplicated region for block: B:815:0x0bf2  */
    /* JADX WARN: Removed duplicated region for block: B:818:0x0c00  */
    /* JADX WARN: Removed duplicated region for block: B:821:0x0c10  */
    /* JADX WARN: Removed duplicated region for block: B:824:0x0c20  */
    /* JADX WARN: Removed duplicated region for block: B:827:0x0c30  */
    /* JADX WARN: Removed duplicated region for block: B:830:0x0c40  */
    /* JADX WARN: Removed duplicated region for block: B:833:0x0c50  */
    /* JADX WARN: Removed duplicated region for block: B:838:0x0c6b  */
    /* JADX WARN: Removed duplicated region for block: B:845:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x013c  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 3192
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.dee.app.databinding.MainBindingImpl.executeBindings():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags == 0 && this.mDirtyFlags_1 == 0) {
                return false;
            }
            return true;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 536870912L;
            this.mDirtyFlags_1 = 0L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelHeaderTitleVisibility((ObservableInt) obj, i2);
            case 1:
                return onChangeViewModelDisableInteraction((ObservableBoolean) obj, i2);
            case 2:
                return onChangeViewModelIsToolbarVisible((ObservableBoolean) obj, i2);
            case 3:
                return onChangeViewModelIsDriveModeFooterVisible((ObservableBoolean) obj, i2);
            case 4:
                return onChangeViewModelIsUserKnown((ObservableBoolean) obj, i2);
            case 5:
                return onChangeViewModelIsDisabled((ObservableBoolean) obj, i2);
            case 6:
                return onChangeViewModelIsFullScreenMode((ObservableBoolean) obj, i2);
            case 7:
                return onChangeViewModelCurrentTab((ObservableInt) obj, i2);
            case 8:
                return onChangeViewModelIsBackVisible((ObservableBoolean) obj, i2);
            case 9:
                return onChangeViewModelIsHomeVisible((ObservableBoolean) obj, i2);
            case 10:
                return onChangeViewModelIsReactNativeView((ObservableBoolean) obj, i2);
            case 11:
                return onChangeViewModelChannelsHomeEnabled((ObservableBoolean) obj, i2);
            case 12:
                return onChangeViewModelChannelsDevicesEnabled((ObservableBoolean) obj, i2);
            case 13:
                return onChangeViewModelIsConversationVisible((ObservableBoolean) obj, i2);
            case 14:
                return onChangeToolbarViewModelShow((ObservableBoolean) obj, i2);
            case 15:
                return onChangeViewModelHeaderTitle((ObservableField) obj, i2);
            case 16:
                return onChangeViewModelIsTabBarVisible((ObservableBoolean) obj, i2);
            case 17:
                return onChangeViewModelTabAnimationFinished((ObservableBoolean) obj, i2);
            case 18:
                return onChangeViewModelIsViewManagerVisible((ObservableBoolean) obj, i2);
            case 19:
                return onChangeViewModelNativeNowPlayingCardVisible((ObservableBoolean) obj, i2);
            case 20:
                return onChangeViewModelIsLoading((ObservableBoolean) obj, i2);
            case 21:
                return onChangeViewModelUseChannelsTheme((ObservableBoolean) obj, i2);
            case 22:
                return onChangeViewModelPlayerVisible((ObservableBoolean) obj, i2);
            case 23:
                return onChangeViewModelFooterTitleVisibility((ObservableInt) obj, i2);
            case 24:
                return onChangeViewModelHideWebView((ObservableBoolean) obj, i2);
            case 25:
                return onChangeViewModelBackgroundColor((ObservableInt) obj, i2);
            default:
                return false;
        }
    }

    @Override // com.amazon.dee.app.databinding.MainBinding
    public void setHandler(@Nullable MainHandler mainHandler) {
        this.mHandler = mainHandler;
        synchronized (this) {
            this.mDirtyFlags |= 134217728;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    @Override // com.amazon.dee.app.databinding.MainBinding
    public void setToolbarViewModel(@Nullable ToolbarViewModel toolbarViewModel) {
        this.mToolbarViewModel = toolbarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 67108864;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (26 == i) {
            setToolbarViewModel((ToolbarViewModel) obj);
        } else if (11 == i) {
            setHandler((MainHandler) obj);
        } else if (19 != i) {
            return false;
        } else {
            setViewModel((MainViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.dee.app.databinding.MainBinding
    public void setViewModel(@Nullable MainViewModel mainViewModel) {
        this.mViewModel = mainViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 268435456;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    private MainBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 26, (ImageView) objArr[2], (View) objArr[27], (FrameLayout) objArr[5], (FrameLayout) objArr[8], (DrawerLayout) objArr[0], (FrameLayout) objArr[41], (FrameLayout) objArr[32], (FrameLayout) objArr[4], (FrameLayout) objArr[10], (FrameLayout) objArr[33], (FrameLayout) objArr[28], (FrameLayout) objArr[11], (LoadingView) objArr[7], (FrameLayout) objArr[9], (RelativeLayout) objArr[29], (LinearLayout) objArr[16], (ImageView) objArr[34], (TextView) objArr[17], (LinearLayout) objArr[22], (ImageView) objArr[39], (TextView) objArr[23], (LinearLayout) objArr[20], (FrameLayout) objArr[14], (ImageView) objArr[36], (ImageView) objArr[37], (LinearLayout) objArr[18], (RelativeLayout) objArr[35], (TextView) objArr[19], (LinearLayout) objArr[15], (LinearLayout) objArr[12], (View) objArr[26], (LinearLayout) objArr[24], (ImageView) objArr[40], (TextView) objArr[25], (ImageView) objArr[38], (TextView) objArr[21], (TextView) objArr[3], (Toolbar) objArr[1], (FrameLayout) objArr[31], (FrameLayout) objArr[30], (FrameLayout) objArr[13], new ViewStubProxy((ViewStub) objArr[6]));
        this.mDirtyFlags = -1L;
        this.mDirtyFlags_1 = -1L;
        this.back.setTag(null);
        this.containerTabDivider.setTag(null);
        this.contentBackdrop.setTag(null);
        this.conversationContainer.setTag(null);
        this.drawerLayout.setTag(null);
        this.fullscreenContainer.setTag(null);
        this.homeContainer.setTag(null);
        this.player.setTag(null);
        this.primaryContainer.setTag(null);
        this.progress.setTag(null);
        this.rnContainer.setTag(null);
        this.tabChannelHome.setTag(null);
        this.tabChannelHomeText.setTag(null);
        this.tabChannelsDevice.setTag(null);
        this.tabChannelsDeviceText.setTag(null);
        this.tabChannelsEntertainment.setTag(null);
        this.tabContainer.setTag(null);
        this.tabConversationsLayout.setTag(null);
        this.tabConversationsLayoutText.setTag(null);
        this.tabLayout.setTag(null);
        this.tabLayoutContainer.setTag(null);
        this.tabLine.setTag(null);
        this.tabMore.setTag(null);
        this.tabMoreText.setTag(null);
        this.tabNowPlayingText.setTag(null);
        this.title.setTag(null);
        this.toolbar.setTag(null);
        this.transportBar.setTag(null);
        this.webviewstub.setContainingBinding(this);
        setRootTag(view);
        this.mCallback2 = new OnClickListener(this, 2);
        this.mCallback1 = new OnClickListener(this, 1);
        this.mCallback7 = new OnClickListener(this, 7);
        this.mCallback6 = new OnClickListener(this, 6);
        this.mCallback5 = new OnClickListener(this, 5);
        this.mCallback4 = new OnClickListener(this, 4);
        this.mCallback3 = new OnClickListener(this, 3);
        invalidateAll();
    }
}
