package com.amazon.alexa.voice.ui.driveMode.local;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.driveMode.R;
import com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class DriveModeLocalController extends ViewController implements DriveModeLocalContract.View, LocalDelegate {
    private static final String EXTRA_CARD = "card";
    private static final String EXTRA_DARK_THEME = "theme_dark";
    private static final String TAG = "DriveModeLocalController";
    private static int sDriveModeLocalControllerInstanceCount;
    private int mStatusBarColor;
    private int mSystemNavBarColor;
    private int mSystemVisibilityUIFlag;
    private int mUiMode;
    private DriveModeLocalContract.Presenter presenter;

    @Deprecated
    public static DriveModeLocalController create(LocalCard localCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", localCard);
        DriveModeLocalController driveModeLocalController = new DriveModeLocalController();
        driveModeLocalController.setArguments(bundle);
        return driveModeLocalController;
    }

    private void resetSystemBarsThemeColor() {
        Log.i(TAG, "resetSystemBarsThemeColor");
        Context context = getContext();
        if (context instanceof Activity) {
            Window window = ((Activity) context).getWindow();
            View decorView = window.getDecorView();
            window.setStatusBarColor(this.mStatusBarColor);
            window.setNavigationBarColor(this.mSystemNavBarColor);
            decorView.setSystemUiVisibility(this.mSystemVisibilityUIFlag);
        }
    }

    private void setSystemBarsThemeColor(boolean z) {
        Log.i(TAG, "setSystemBarsThemeColor");
        Context context = getContext();
        Resources resources = context.getResources();
        if (context instanceof Activity) {
            Window window = ((Activity) context).getWindow();
            View decorView = window.getDecorView();
            this.mStatusBarColor = window.getStatusBarColor();
            this.mSystemNavBarColor = window.getNavigationBarColor();
            this.mSystemVisibilityUIFlag = decorView.getSystemUiVisibility();
            resources.getColor(R.color.darkThemeBackground);
            int i = Build.VERSION.SDK_INT;
            int i2 = 0;
            decorView.setSystemUiVisibility(z ? 0 : 8192);
            window.setStatusBarColor(resources.getColor(R.color.backgroundColor));
            resources.getColor(R.color.darkThemeBackground);
            int i3 = Build.VERSION.SDK_INT;
            if (!z) {
                i2 = 8208;
            }
            decorView.setSystemUiVisibility(i2);
            window.setNavigationBarColor(resources.getColor(R.color.backgroundColor));
        }
    }

    private static void updateDayNightMode(Context context, int i) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.uiMode = i;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate
    public void close() {
        this.presenter.closeClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        Log.i(TAG, "onAttach");
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        this.mUiMode = getContext().getResources().getConfiguration().uiMode;
        Bundle arguments = getArguments();
        LocalCard localCard = arguments != null ? (LocalCard) arguments.getParcelable("card") : null;
        if (localCard == null) {
            Log.e(TAG, "Use DriveModeLocalController.create(LocalCard) to create a controller");
        }
        boolean z = arguments == null || arguments.getBoolean(EXTRA_DARK_THEME, true);
        updateDayNightMode(getContext(), z ? 32 : 16);
        overrideTheme(R.style.Alexa_Voice_OneDesign_Moasic_DriveMode);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sDriveModeLocalControllerInstanceCount ");
        outline107.append(sDriveModeLocalControllerInstanceCount);
        Log.i(str, outline107.toString());
        if (sDriveModeLocalControllerInstanceCount == 0) {
            setSystemBarsThemeColor(z);
        }
        sDriveModeLocalControllerInstanceCount++;
        this.presenter = new DriveModeLocalPresenter(new DriveModeLocalInteractor(new DriveModeLocalMediator(this, localCard)));
        Component component = getComponent();
        component.remove(LocalDelegate.class);
        component.provide((Class<? extends Class>) LocalDelegate.class, (Class) this).register();
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_drive_mode_local, viewGroup, false);
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                DriveModeLocalController.this.presenter.dismiss();
            }
        });
        getChildRouter("local").attach((ViewGroup) inflate.findViewById(R.id.container));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        ComponentUtils.removeDependency(getComponent(), LocalDelegate.class, this);
        sDriveModeLocalControllerInstanceCount--;
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sDriveModeLocalControllerInstanceCount ");
        outline107.append(sDriveModeLocalControllerInstanceCount);
        Log.i(str, outline107.toString());
        if (sDriveModeLocalControllerInstanceCount == 0) {
            resetSystemBarsThemeColor();
        }
        updateDayNightMode(getContext(), this.mUiMode);
    }

    @Override // com.amazon.regulator.ViewController
    protected boolean onHandleBack() {
        Router childRouter = getChildRouter("local");
        return childRouter.getBackStackSize() > 1 && childRouter.handleBack();
    }

    public static DriveModeLocalController create(LocalCard localCard, int i) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", localCard);
        bundle.putBoolean(EXTRA_DARK_THEME, i == 0);
        DriveModeLocalController driveModeLocalController = new DriveModeLocalController();
        driveModeLocalController.setArguments(bundle);
        return driveModeLocalController;
    }
}
