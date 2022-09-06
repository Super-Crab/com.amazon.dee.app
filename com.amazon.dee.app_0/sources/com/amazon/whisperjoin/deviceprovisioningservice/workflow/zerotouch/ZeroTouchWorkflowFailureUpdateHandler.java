package com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.error.HighRateOfDssRequestFailures;
import com.amazon.whisperjoin.deviceprovisioningservice.error.HighRateOfWorkflowFailures;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.DetectEventRateExceedingThreshold;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.ZeroTouchWorkflowUpdate;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSClientError;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSServiceError;
import com.amazon.whisperjoin.util.FireOSUtil;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes13.dex */
public class ZeroTouchWorkflowFailureUpdateHandler implements ObservableTransformer<ZeroTouchWorkflowUpdate, ZeroTouchWorkflowUpdate> {
    private static final String TAG = "ZeroTouchWorkflowFailureUpdateHandler";
    private final boolean mDebugMode;
    private final DetectEventRateExceedingThreshold mFailedAttemptsTracker;
    private final DetectEventRateExceedingThreshold mFailedDSSCallTracker;
    private final FireOSUtil mFireOSUtil;

    public ZeroTouchWorkflowFailureUpdateHandler(FireOSUtil fireOSUtil, Clock clock, boolean z, long j, long j2, long j3, long j4) {
        this.mFireOSUtil = fireOSUtil;
        this.mDebugMode = z;
        this.mFailedAttemptsTracker = new DetectEventRateExceedingThreshold(clock, j, j2);
        this.mFailedDSSCallTracker = new DetectEventRateExceedingThreshold(clock, j3, j4);
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<ZeroTouchWorkflowUpdate> apply(Observable<ZeroTouchWorkflowUpdate> observable) {
        return observable.flatMap(new Function<ZeroTouchWorkflowUpdate, ObservableSource<ZeroTouchWorkflowUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFailureUpdateHandler.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<ZeroTouchWorkflowUpdate> mo10358apply(ZeroTouchWorkflowUpdate zeroTouchWorkflowUpdate) throws Exception {
                if (ZeroTouchWorkflowUpdate.State.FAILURE.equals(zeroTouchWorkflowUpdate.getState())) {
                    if (ZeroTouchWorkflowFailureUpdateHandler.this.mDebugMode) {
                        WJLog.i(ZeroTouchWorkflowFailureUpdateHandler.TAG, "A workflow failure occurred in debug mode, ignoring additional handling");
                        return Observable.just(zeroTouchWorkflowUpdate);
                    }
                    Throwable throwable = zeroTouchWorkflowUpdate.getThrowable();
                    if ((throwable instanceof DSSClientError) && ((DSSClientError) throwable).isUnblessedDiscovery() && ZeroTouchWorkflowFailureUpdateHandler.this.mFireOSUtil.isPoweredDevice()) {
                        WJLog.i(ZeroTouchWorkflowFailureUpdateHandler.TAG, "High-rate of unblessed discoveries on a powered device. Not triggering backoff.");
                        return Observable.just(zeroTouchWorkflowUpdate);
                    } else if (!(throwable instanceof DSSServiceError) || !ZeroTouchWorkflowFailureUpdateHandler.this.mFailedDSSCallTracker.thresholdExceeded()) {
                        if (ZeroTouchWorkflowFailureUpdateHandler.this.mFailedAttemptsTracker.thresholdExceeded()) {
                            WJLog.i(ZeroTouchWorkflowFailureUpdateHandler.TAG, "Triggering high rate of workflow failures");
                            return Observable.error(new HighRateOfWorkflowFailures()).startWithItem(zeroTouchWorkflowUpdate);
                        }
                        return Observable.just(zeroTouchWorkflowUpdate);
                    } else {
                        WJLog.i(ZeroTouchWorkflowFailureUpdateHandler.TAG, "Triggering high rate of DSS request failures");
                        return Observable.error(new HighRateOfDssRequestFailures()).startWithItem(zeroTouchWorkflowUpdate);
                    }
                }
                return Observable.just(zeroTouchWorkflowUpdate);
            }
        });
    }

    ZeroTouchWorkflowFailureUpdateHandler(DetectEventRateExceedingThreshold detectEventRateExceedingThreshold, DetectEventRateExceedingThreshold detectEventRateExceedingThreshold2, FireOSUtil fireOSUtil, boolean z) {
        this.mFailedAttemptsTracker = detectEventRateExceedingThreshold;
        this.mFailedDSSCallTracker = detectEventRateExceedingThreshold2;
        this.mFireOSUtil = fireOSUtil;
        this.mDebugMode = z;
    }
}
