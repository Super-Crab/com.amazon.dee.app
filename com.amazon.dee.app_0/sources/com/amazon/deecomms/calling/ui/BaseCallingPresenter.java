package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.calling.ui.BaseCallingContract;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class BaseCallingPresenter implements BaseCallingContract.Presenter {
    private DriveModeSharedPreferencesUseCase mDriveModeSharedPreferencesUseCase;

    @Inject
    public BaseCallingPresenter(DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase) {
        this.mDriveModeSharedPreferencesUseCase = driveModeSharedPreferencesUseCase;
    }

    @Override // com.amazon.deecomms.calling.ui.BaseCallingContract.Presenter
    public boolean isAppInDriveMode() {
        return this.mDriveModeSharedPreferencesUseCase.isInDriveMode();
    }
}
