package com.amazon.alexa.redesign.utils.verifier;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.redesign.entity.AlertBannerModel;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes10.dex */
public class EmptyMetricsConditionCallback implements ConditionCallback {
    private final Mobilytics mobilytics;

    public EmptyMetricsConditionCallback(Mobilytics mobilytics) {
        this.mobilytics = mobilytics;
    }

    private String checkIfKeyIsEmpty(String str, String str2, String str3) {
        return str3.isEmpty() ? GeneratedOutlineSupport1.outline75(str, AccessoryMetricsConstants.DELIMITER, str2) : str;
    }

    @Override // com.amazon.alexa.redesign.utils.verifier.ConditionCallback
    public void doThis(CardModel cardModel) {
        Map<String, Object> topLevelMetricsObject = cardModel.getTopLevelMetricsObject();
        if (topLevelMetricsObject == null || (cardModel instanceof AlertBannerModel)) {
            return;
        }
        String fieldForEvent = HomeMetricsRecorder.getFieldForEvent(topLevelMetricsObject, EntertainmentConstants.TYPE_SECTION);
        String fieldForEvent2 = HomeMetricsRecorder.getFieldForEvent(topLevelMetricsObject, "contentId");
        String fieldForEvent3 = HomeMetricsRecorder.getFieldForEvent(topLevelMetricsObject, "contentType");
        String fieldForEvent4 = HomeMetricsRecorder.getFieldForEvent(topLevelMetricsObject, "contentProvider");
        String checkIfKeyIsEmpty = checkIfKeyIsEmpty(checkIfKeyIsEmpty(checkIfKeyIsEmpty(checkIfKeyIsEmpty(checkIfKeyIsEmpty("", EntertainmentConstants.TYPE_SECTION, fieldForEvent), "contentId", fieldForEvent2), "contentType", fieldForEvent3), "contentProvider", fieldForEvent4), "modelVersion", HomeMetricsRecorder.getFieldForEvent(topLevelMetricsObject, "modelVersion"));
        if (checkIfKeyIsEmpty.isEmpty()) {
            return;
        }
        this.mobilytics.recordDataEvent("jasper-home-native.missingMetrics", checkIfKeyIsEmpty, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.GET_FEED, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
    }

    @Override // com.amazon.alexa.redesign.utils.verifier.ConditionCallback
    public boolean isTrueWhen(CardModel cardModel) {
        return true;
    }

    @Override // com.amazon.alexa.redesign.utils.verifier.ConditionCallback
    public void onComplete() {
    }
}
