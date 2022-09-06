package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessoryclient.common.api.AccessoryDataBeaconResult;
import com.amazon.alexa.accessoryclient.common.api.AccessoryInquiryResult;
import com.amazon.alexa.accessoryclient.common.api.AccessoryScanResult;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoryScanner.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H&J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/AccessoryScanner;", "", "observeOnBleAccessoryFoundNearby", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryScanResult;", "observeOnBleDataBeaconFoundNearby", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryDataBeaconResult;", "observeOnConnectedAccessoryFound", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryInquiryResult;", "observeOnConnectedAccessoryLost", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AccessoryScanner {
    @NotNull
    Observable<AccessoryScanResult> observeOnBleAccessoryFoundNearby();

    @NotNull
    Observable<AccessoryDataBeaconResult> observeOnBleDataBeaconFoundNearby();

    @NotNull
    Observable<AccessoryInquiryResult> observeOnConnectedAccessoryFound();

    @NotNull
    Observable<AccessoryInquiryResult> observeOnConnectedAccessoryLost();
}
