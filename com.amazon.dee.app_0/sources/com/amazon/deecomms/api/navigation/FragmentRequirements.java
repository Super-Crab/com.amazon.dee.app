package com.amazon.deecomms.api.navigation;

import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.amazon.deecomms.common.util.Utils;
/* loaded from: classes12.dex */
public class FragmentRequirements {
    private String mFragmentId;
    private int mMenuId;
    private MenuInflatedCallback mMenuInflatedCallback;
    private Toolbar.OnMenuItemClickListener mMenuItemClickListener;
    private String mTitle = "";
    private boolean mUsesBackButton = true;
    private boolean mUsesHeader = true;
    private boolean mUsesFooter = true;
    private boolean mUsesMenu = false;

    /* loaded from: classes12.dex */
    public interface MenuInflatedCallback {
        void onInflated(Menu menu);
    }

    public FragmentRequirements(Fragment fragment) {
        this.mFragmentId = fragment.getClass().getSimpleName();
    }

    public String getFragmentId() {
        return this.mFragmentId;
    }

    public int getMenuId() {
        return this.mMenuId;
    }

    public Toolbar.OnMenuItemClickListener getMenuItemClickListener() {
        return this.mMenuItemClickListener;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public FragmentRequirements hidesFooter() {
        this.mUsesFooter = false;
        return this;
    }

    public FragmentRequirements hidesHeader() {
        this.mUsesHeader = false;
        return this;
    }

    public void menuInflatedCallback(Menu menu) {
        MenuInflatedCallback menuInflatedCallback = this.mMenuInflatedCallback;
        if (menuInflatedCallback != null) {
            menuInflatedCallback.onInflated(menu);
        }
    }

    public FragmentRequirements showsFooter() {
        this.mUsesFooter = true;
        return this;
    }

    public FragmentRequirements showsHeader() {
        this.mUsesHeader = true;
        return this;
    }

    public boolean usesBackButton() {
        return this.mUsesBackButton;
    }

    public boolean usesFooter() {
        return this.mUsesFooter;
    }

    public boolean usesHeader() {
        return this.mUsesHeader;
    }

    public boolean usesMenu() {
        return this.mUsesMenu;
    }

    public boolean usesTitle() {
        return !Utils.isNullOrEmpty(this.mTitle);
    }

    public FragmentRequirements withBackButton() {
        this.mUsesBackButton = true;
        return this;
    }

    public FragmentRequirements withMenu(int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener, MenuInflatedCallback menuInflatedCallback) {
        this.mUsesMenu = true;
        this.mMenuId = i;
        this.mMenuItemClickListener = onMenuItemClickListener;
        this.mMenuInflatedCallback = menuInflatedCallback;
        return this;
    }

    public FragmentRequirements withNoBackButton() {
        this.mUsesBackButton = false;
        return this;
    }

    public FragmentRequirements withNoMenu() {
        this.mUsesMenu = false;
        this.mMenuId = 0;
        this.mMenuItemClickListener = null;
        this.mMenuInflatedCallback = null;
        return this;
    }

    public FragmentRequirements withTitle(String str) {
        this.mTitle = str;
        return this;
    }
}
