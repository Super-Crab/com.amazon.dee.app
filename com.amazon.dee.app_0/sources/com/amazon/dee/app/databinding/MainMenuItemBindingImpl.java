package com.amazon.dee.app.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.amazon.dee.app.generated.callback.OnClickListener;
import com.amazon.dee.app.ui.main.MainViewModel;
import com.amazon.dee.app.ui.menu.MenuItem;
import com.amazon.dee.app.ui.menu.MenuItemHandler;
/* loaded from: classes12.dex */
public class MainMenuItemBindingImpl extends MainMenuItemBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private final View.OnClickListener mCallback15;
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final LinearLayout mboundView1;
    @NonNull
    private final TextView mboundView2;
    @NonNull
    private final ImageView mboundView3;
    @NonNull
    private final View mboundView4;

    public MainMenuItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewModelUseChannelsTheme(ObservableBoolean observableBoolean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // com.amazon.dee.app.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        MenuItemHandler menuItemHandler = this.mHandler;
        MenuItem menuItem = this.mMenuItem;
        if (menuItemHandler != null) {
            menuItemHandler.onMenuItemSelected(menuItem);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        CharSequence charSequence;
        Drawable drawable;
        int i;
        int i2;
        boolean z;
        boolean z2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        MenuItem menuItem = this.mMenuItem;
        MainViewModel mainViewModel = this.mViewModel;
        int i3 = ((j & 20) > 0L ? 1 : ((j & 20) == 0L ? 0 : -1));
        String str = null;
        ObservableBoolean observableBoolean = null;
        boolean z3 = false;
        if (i3 != 0) {
            if (menuItem != null) {
                z = menuItem.showDivider();
                drawable = menuItem.getBadgeIcon();
                z2 = menuItem.isVisible();
                charSequence = menuItem.getText();
            } else {
                charSequence = null;
                drawable = null;
                z = false;
                z2 = false;
            }
            if (i3 != 0) {
                j |= z ? 256L : 128L;
            }
            if ((j & 20) != 0) {
                j |= z2 ? 64L : 32L;
            }
            i = 8;
            i2 = z ? 0 : 8;
            if (z2) {
                i = 0;
            }
        } else {
            charSequence = null;
            drawable = null;
            i = 0;
            i2 = 0;
        }
        int i4 = ((j & 25) > 0L ? 1 : ((j & 25) == 0L ? 0 : -1));
        if (i4 != 0) {
            if (mainViewModel != null) {
                observableBoolean = mainViewModel.useChannelsTheme;
            }
            updateRegistration(0, observableBoolean);
            if (observableBoolean != null) {
                z3 = observableBoolean.get();
            }
            if (i4 != 0) {
                j |= z3 ? 1024L : 512L;
            }
            str = z3 ? "ember-bold" : "ember-regular";
        }
        if ((j & 16) != 0) {
            this.mboundView1.setOnClickListener(this.mCallback15);
        }
        if ((j & 20) != 0) {
            this.mboundView1.setVisibility(i);
            TextViewBindingAdapter.setText(this.mboundView2, charSequence);
            ImageViewAdapter.setSrc(this.mboundView3, drawable);
            this.mboundView4.setVisibility(i2);
            if (ViewDataBinding.getBuildSdkInt() >= 4) {
                this.mboundView2.setContentDescription(charSequence);
            }
        }
        if ((j & 25) != 0) {
            FontAdapter.setFont(this.mboundView2, str);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModelUseChannelsTheme((ObservableBoolean) obj, i2);
    }

    @Override // com.amazon.dee.app.databinding.MainMenuItemBinding
    public void setHandler(@Nullable MenuItemHandler menuItemHandler) {
        this.mHandler = menuItemHandler;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    @Override // com.amazon.dee.app.databinding.MainMenuItemBinding
    public void setMenuItem(@Nullable MenuItem menuItem) {
        this.mMenuItem = menuItem;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(29);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (11 == i) {
            setHandler((MenuItemHandler) obj);
        } else if (29 == i) {
            setMenuItem((MenuItem) obj);
        } else if (19 != i) {
            return false;
        } else {
            setViewModel((MainViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.dee.app.databinding.MainMenuItemBinding
    public void setViewModel(@Nullable MainViewModel mainViewModel) {
        this.mViewModel = mainViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    private MainMenuItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (LinearLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (LinearLayout) objArr[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) objArr[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (ImageView) objArr[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (View) objArr[4];
        this.mboundView4.setTag(null);
        setRootTag(view);
        this.mCallback15 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
