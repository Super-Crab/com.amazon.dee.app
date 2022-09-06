package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
/* loaded from: classes11.dex */
public interface NewUserPrimerContract extends HandsfreePrimerContract {

    /* loaded from: classes11.dex */
    public interface Interactor extends HandsfreePrimerContract.Interactor {
        void permissionsResultReceived();
    }

    /* loaded from: classes11.dex */
    public interface Presenter extends HandsfreePrimerContract.Presenter {
        void permissionsResultReceived();

        void showAlert();
    }
}
