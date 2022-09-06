package com.amazon.alexa.voice.ui.onedesign.local.detail;

import android.text.TextUtils;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class LocalDetailPresenter implements LocalDetailContract.Presenter {
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private final LocalDetailContract.Interactor interactor;
    private final Locale locale;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final LocalDetailContract.View view;

    public LocalDetailPresenter(LocalDetailContract.View view, LocalDetailContract.Interactor interactor, Resources resources, CardMetricsInteractor cardMetricsInteractor, Locale locale) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.metricsInteractor = cardMetricsInteractor;
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

    private CharSequence buildOpeningHours(List<CharSequence> list) {
        CharSequence[] charSequenceArr = (CharSequence[]) list.toArray(new CharSequence[list.size()]);
        if (charSequenceArr.length == 0) {
            return null;
        }
        return TextUtils.concat(this.resources.getString(R.string.voice_ui_od_local_today), RealTimeTextConstants.COLON_SPACE, TextUtils.join("\n", charSequenceArr));
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
        return this.interactor.getBusiness().getClass().getSimpleName();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Presenter
    public void addressClicked() {
        this.interactor.showLocation();
        this.metricsInteractor.reportNavigationToExternalLink(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Presenter
    public void backClicked() {
        this.interactor.back();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Presenter
    public void linkClicked() {
        this.interactor.openLink();
        this.metricsInteractor.reportNavigationToExternalLink(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Presenter
    public void phoneNumberClicked() {
        this.interactor.callBusiness();
        this.metricsInteractor.reportNavigationToExternalLink(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Presenter
    public void start() {
        LocalCardModel.BusinessModel business = this.interactor.getBusiness();
        this.view.floodBackgroundToStatusBar();
        this.view.setProviderLogoAndDescription(business.getProviderLogoUrl(), buildRatingSourceAccessibilityLabel(business.getDataSource()));
        this.view.setImageUrl(business.getImageUrl());
        this.view.setMergedProviderInfo(business.getMergedProviderInfo());
        this.view.setName(business.getName());
        this.view.setRating(business.getDataSource(), business.getStarRatingUrl(), buildRatingAccessibilityLabel(business.getRating()));
        this.view.setReviewCount(buildReviewCount(business.getReviewCount()), buildReviewCountAccessibilityLabel(business.getReviewCount()));
        this.view.setAddress(business.getAddress(), buildAddressAccessibilityLabel(business.getAddress()));
        this.view.setPhoneNumber(business.getPhoneNumber(), buildPhoneNumberAccessibilityLabel(business.getPhoneNumber()));
        CharSequence buildOpeningHours = buildOpeningHours(business.getOpeningHours());
        this.view.setOpeningHours(buildOpeningHours, buildOpeningHoursAccessibilityLabel(buildOpeningHours));
        this.view.setDescription(buildDescription(business.getSpendiness(), business.getCategories()), buildDescriptionAccessibilityLabel(business.getSpendiness(), business.getCategories()));
        CharSequence buildDistanceText = buildDistanceText(business.getDistanceValue(), business.getDistanceUnit());
        this.view.setDistance(buildDistanceText, buildDistanceAccessibilityLabel(buildDistanceText));
        if (!TextUtils.isEmpty(business.goToActionUrl())) {
            this.view.setLinkText(business.goToActionText().toUpperCase(this.locale));
        } else {
            this.view.setLinkText(null);
        }
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }
}
