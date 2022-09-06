package com.amazon.alexa.drive.comms;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import com.amazon.alexa.drive.service.DefaultDriveModeService;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.Lazy;
/* loaded from: classes7.dex */
public class CommsManager {
    private static final String TAG = "CommsManager";
    private CommsCardProvider mCommsCardProvider;
    private CommsLandingPageContract.Interactor mCommsLandingPageInteractor;
    private Context mContext;
    private DefaultDriveModeService mDriveModeService;
    private DriveModeThemeManager mDriveModeThemeManager;
    private Lazy<DriveModeCardsProvider> mLazyCardProvider;
    private WeblabManager mWeblabManager;

    public CommsManager(@NonNull Context context, CommsLandingPageContract.Interactor interactor, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        this.mContext = context.getApplicationContext();
        initDriveModeService();
        this.mCommsLandingPageInteractor = interactor;
        this.mDriveModeThemeManager = driveModeThemeManager;
        this.mWeblabManager = weblabManager;
    }

    private void initDriveModeService() {
        Log.i(TAG, "initDriveModeService");
        this.mDriveModeService = (DefaultDriveModeService) ComponentRegistry.getInstance().get(DriveModeService.class).orNull();
    }

    private void initLandingPageCardProvider() {
        Log.i(TAG, "initLandingPageCardProvider");
        this.mLazyCardProvider = new Lazy() { // from class: com.amazon.alexa.drive.comms.-$$Lambda$CommsManager$NVfuEj9GqQ1Sy50BCysCU_xHWuA
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return CommsManager.this.lambda$initLandingPageCardProvider$0$CommsManager();
            }
        };
        if (getDefaultDriveModeService() != null) {
            getDefaultDriveModeService().addCardsProvider(this.mLazyCardProvider);
        }
    }

    private void removeLandingPageCardProvider() {
        Log.i(TAG, "removeLandingPageCardProvider");
        if (getDefaultDriveModeService() != null) {
            getDefaultDriveModeService().getProviders().remove(this.mCommsCardProvider);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: createCommsCardProvider */
    public CommsCardProvider lambda$initLandingPageCardProvider$0$CommsManager() {
        this.mCommsCardProvider = new CommsCardProvider(this.mContext, this.mCommsLandingPageInteractor, this.mDriveModeThemeManager);
        return this.mCommsCardProvider;
    }

    public void deinit() {
        Log.i(TAG, "deinit");
        removeLandingPageCardProvider();
    }

    CommsCardProvider getCommsCardProvider() {
        return this.mCommsCardProvider;
    }

    DefaultDriveModeService getDefaultDriveModeService() {
        return this.mDriveModeService;
    }

    Lazy<DriveModeCardsProvider> getLazyDriveModeCardsProvider() {
        return this.mLazyCardProvider;
    }

    public void init() {
        Log.i(TAG, "init");
        if (!this.mWeblabManager.isCommsNativeWeblabEnabled()) {
            Log.i(TAG, "weblab for native comms disabled, no more initializing");
        } else {
            initLandingPageCardProvider();
        }
    }
}
