package com.amazon.alexa.voice.ui.driveMode.local.detail;

import android.text.TextUtils;
import com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeVoxUiMetricsConstants;
import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.amazon.alexa.voice.ui.driveMode.util.DriverDistractionLog;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class DriveModeLocalDetailPresenter implements DriveModeLocalDetailContract.Presenter {
    private final DriveModeCardMetricsInteractor driveModeCardMetricsInteractor;
    private final DriverDistractionLog driverDistractionLog;
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private final DriveModeLocalDetailContract.Interactor interactor;
    private final Locale locale;
    private final Resources resources;
    private final DriveModeLocalDetailContract.View view;

    public DriveModeLocalDetailPresenter(DriveModeLocalDetailContract.View view, DriveModeLocalDetailContract.Interactor interactor, Resources resources, DriveModeCardMetricsInteractor driveModeCardMetricsInteractor, DriverDistractionLog driverDistractionLog, Locale locale) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.driveModeCardMetricsInteractor = driveModeCardMetricsInteractor;
        this.driverDistractionLog = driverDistractionLog;
        this.locale = locale;
    }

    private CharSequence buildAddressAccessibilityLabel(CharSequence charSequence) {
        return this.resources.getString(R.string.voice_ui_od_local_accessibility_address, charSequence);
    }

    private static CharSequence buildDescription(CharSequence charSequence, List<CharSequence> list) {
        CharSequence concat = charSequence != null ? TextUtils.concat(charSequence, " • ") : "";
        int i = 0;
        while (i < list.size()) {
            concat = i == 0 ? TextUtils.concat(concat, list.get(i)) : TextUtils.concat(concat, ", ", list.get(i));
            i++;
        }
        return concat;
    }

    private CharSequence buildDescriptionAccessibilityLabel(CharSequence charSequence, List<CharSequence> list) {
        StringBuilder sb = new StringBuilder();
        if (charSequence != null && charSequence.length() > 0) {
            sb.append(this.resources.getString(R.string.voice_ui_od_local_accessibility_pricing, Integer.valueOf(charSequence.length()), 4));
            sb.append(".");
        }
        if (list != null && list.size() > 0) {
            sb.append(this.resources.getString(R.string.voice_ui_od_local_accessibility_offering));
            for (CharSequence charSequence2 : list) {
                sb.append(charSequence2);
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private CharSequence buildDistanceAccessibilityLabel(CharSequence charSequence) {
        return this.resources.getString(R.string.voice_ui_od_local_accessibility_distance, charSequence);
    }

    private CharSequence buildDistanceText(float f, CharSequence charSequence) {
        return this.resources.getString(R.string.voice_ui_od_local_distance_format, Float.valueOf(f), charSequence);
    }

    private CharSequence buildOpeningHours(List<CharSequence> list, String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1986416409) {
            if (str.equals(DriveModeVoxUiConstants.NORMAL)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -1560189025) {
            if (hashCode == 2656902 && str.equals(DriveModeVoxUiConstants.WARN)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(DriveModeVoxUiConstants.CRITICAL)) {
                c = 2;
            }
            c = 65535;
        }
        return (c == 0 || c == 1 || c == 2) ? str2 : buildOpeningHours(list);
    }

    private CharSequence buildOpeningHoursAccessibilityLabel(CharSequence charSequence) {
        return this.resources.getString(R.string.voice_ui_od_local_accessibility_opening_hours, charSequence);
    }

    private CharSequence buildPhoneNumberAccessibilityLabel(CharSequence charSequence) {
        return this.resources.getString(R.string.voice_ui_od_local_accessibility_phone_number, charSequence);
    }

    private CharSequence buildRatingAccessibilityLabel(float f) {
        return this.resources.getString(R.string.voice_ui_od_local_accessibility_rating, Float.valueOf(f), "5");
    }

    private CharSequence buildRatingSourceAccessibilityLabel(CharSequence charSequence) {
        return this.resources.getString(R.string.voice_ui_od_local_accessibility_provided_by, charSequence);
    }

    private CharSequence buildReviewCount(int i) {
        return TextUtils.concat("(", Integer.toString(i), ")");
    }

    private CharSequence buildReviewCountAccessibilityLabel(int i) {
        return this.resources.getString(R.string.voice_ui_od_local_accessibility_number_of_reviews, Integer.valueOf(i));
    }

    private String getCardName() {
        return DriveModeVoxUiMetricsConstants.METRIC_VOICECARDS_POIDETAILS;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Presenter
    public void addressClicked() {
        this.interactor.showLocation();
        this.driveModeCardMetricsInteractor.reportNavigationToExternalLink(DriveModeVoxUiMetricsConstants.METRIC_VOICECARDS_POIDETAILSADDRESS);
        this.driverDistractionLog.logTouch("VoiceCards_POIDetailsAddress_Selected");
        this.interactionTracker.notifyInteracted();
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Presenter
    public void backClicked() {
        this.interactor.back();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Presenter
    public void phoneNumberClicked() {
        this.interactor.callBusiness();
        this.driveModeCardMetricsInteractor.reportNavigationToExternalLink(DriveModeVoxUiMetricsConstants.METRIC_VOICECARDS_POIDETAILSPHONE);
        this.driverDistractionLog.logTouch("VoiceCards_POIDetailsPhone_Selected");
        this.interactionTracker.notifyInteracted();
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Presenter
    public void start() {
        LocalCardModel.BusinessModel business = this.interactor.getBusiness();
        if (business.getDataSource().equals("")) {
            this.view.setProviderLogoAndDescription(business.getProviderLogoUrl(), buildRatingSourceAccessibilityLabel(business.getDataSource()), false);
            this.view.setRating(business.getDataSource(), business.getStarRatingUrl(), buildRatingAccessibilityLabel(business.getRating()), false);
            this.view.setReviewCount(buildReviewCount(business.getReviewCount()), buildReviewCountAccessibilityLabel(business.getReviewCount()), false);
        } else {
            this.view.setProviderLogoAndDescription(business.getProviderLogoUrl(), buildRatingSourceAccessibilityLabel(business.getDataSource()), true);
            this.view.setRating(business.getDataSource(), business.getStarRatingUrl(), buildRatingAccessibilityLabel(business.getRating()), true);
            this.view.setReviewCount(buildReviewCount(business.getReviewCount()), buildReviewCountAccessibilityLabel(business.getReviewCount()), true);
        }
        this.view.setName(business.getName());
        this.view.setAddress(business.getAddress(), buildAddressAccessibilityLabel(business.getAddress()));
        CharSequence buildOpeningHours = buildOpeningHours(business.getOpeningHours(), business.getSpecialBusinessHoursDisplayType(), business.getSpecialBusinessHoursDisplayMessage());
        this.view.setOpeningHours(buildOpeningHours, buildOpeningHoursAccessibilityLabel(buildOpeningHours), business.getSpecialBusinessHoursDisplayType());
        this.view.setDescription(buildDescription(business.getSpendiness(), business.getCategories()), buildDescriptionAccessibilityLabel(business.getSpendiness(), business.getCategories()));
        CharSequence buildDistanceText = buildDistanceText(business.getDistanceValue(), business.getDistanceUnit());
        this.view.setDistance(buildDistanceText, buildDistanceAccessibilityLabel(buildDistanceText));
        this.view.setDataProvider(business.getMergedProviderInfo());
        this.driveModeCardMetricsInteractor.reportCardShown(DriveModeVoxUiMetricsConstants.METRIC_VOICECARDS_POIDETAILS);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Presenter
    public void viewDestroyed() {
        this.driveModeCardMetricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }

    private CharSequence buildOpeningHours(List<CharSequence> list) {
        CharSequence[] charSequenceArr = (CharSequence[]) list.toArray(new CharSequence[list.size()]);
        if (charSequenceArr.length == 0) {
            return null;
        }
        return TextUtils.concat(this.resources.getString(R.string.voice_ui_od_local_today), RealTimeTextConstants.COLON_SPACE, TextUtils.join("\n", charSequenceArr));
    }
}
