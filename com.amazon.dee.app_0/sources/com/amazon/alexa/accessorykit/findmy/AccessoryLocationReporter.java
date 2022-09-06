package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessorykit.findmy.reporter.ReportLocationsResponse;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface AccessoryLocationReporter {
    Single<ReportLocationsResponse> reportAccessoryLocation(LocationInformation locationInformation, FindMyAccessoryInformation findMyAccessoryInformation);
}
