package com.amazon.alexa.drive.view;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.util.DriveModeAnimationUtils;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.viewmanagement.api.ViewController;
/* loaded from: classes7.dex */
public abstract class ViewManagerViewController implements ViewController {
    private static final String TAG = "ViewManagerViewController";
    private Context context;
    private boolean isAttached;
    private View mView;

    public Context getContext() {
        return this.context;
    }

    protected abstract DriveModeThemeManager getDriveModeThemeManager();

    public View getView() {
        return this.mView;
    }

    public boolean isAttached() {
        return this.isAttached;
    }

    protected boolean isDomainView() {
        return true;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.context = layoutInflater.getContext();
        return layoutInflater.cloneInContext(new ContextThemeWrapper(this.context, getDriveModeThemeManager().getTheme())).inflate(R.layout.dm_view_controller_container, viewGroup, false);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        final ViewGroup viewGroup = (ViewGroup) view;
        final ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, getDriveModeThemeManager().getTheme());
        this.mView = view;
        this.isAttached = true;
        if (!isDomainView() || viewGroup.getChildAt(0) == null) {
            return;
        }
        new Handler().post(new Runnable() { // from class: com.amazon.alexa.drive.view.-$$Lambda$ViewManagerViewController$N3_W4RrWWv0bRf1AfIVvqfVr1fE
            @Override // java.lang.Runnable
            public final void run() {
                DriveModeAnimationUtils.performDomainAnimation(viewGroup.getChildAt(0), contextThemeWrapper);
            }
        });
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        this.isAttached = false;
    }
}
