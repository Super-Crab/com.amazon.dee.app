package com.amazon.alexa.voice.ui.onedesign.standard;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaResponseRenderedCard;
import com.amazon.alexa.voice.ui.onedesign.util.AvsDomain;
import com.amazon.alexa.voice.ui.onedesign.util.AvsDomainTypes;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.amazon.alexa.voice.ui.tta.ModelFactory;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.amazon.regulator.ViewController;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class StandardControllerFactory implements ControllerFactory<ViewController>, ModelFactory<TtaResponseCard> {
    private static final String[] BLACK_LISTED_DOMAINS = {AvsDomainTypes.JOKE};
    private static final String TAG = "SCFactory";

    private boolean isCardPresentable(@Nullable StandardCardModel standardCardModel) {
        if (standardCardModel == null) {
            return false;
        }
        return !TextUtils.isEmpty(standardCardModel.getTitle()) || !TextUtils.isEmpty(standardCardModel.getContent()) || !TextUtils.isEmpty(standardCardModel.getContentImageUrl());
    }

    private boolean shouldCreateControllerForCard(@Nullable StandardCardModel standardCardModel) {
        return isCardPresentable(standardCardModel) && !AvsDomain.containsAnyDomain(standardCardModel.getDomains(), BLACK_LISTED_DOMAINS);
    }

    @Override // com.amazon.alexa.voice.ui.tta.ModelFactory
    public TtaResponseCard build(@NonNull JSONObject jSONObject) throws JSONException {
        StandardCardModel fromJson = StandardCardFactory.fromJson(jSONObject);
        CharSequence linkUrl = fromJson.getLinkUrl();
        CharSequence linkText = fromJson.getLinkText();
        CharSequence cardType = fromJson.getCardType();
        if (linkUrl != null && URLUtil.isValidUrl(linkUrl.toString())) {
            return new TtaResponseRenderedCard(UUID.randomUUID().toString(), "", linkText != null ? linkText.toString() : "", linkUrl.toString(), cardType.toString());
        }
        Log.e(TAG, "Link is null.");
        return null;
    }

    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    @Nullable
    /* renamed from: create */
    public ViewController mo2780create(@NonNull JSONObject jSONObject) throws JSONException {
        StandardCardModel fromJson = StandardCardFactory.fromJson(jSONObject);
        if (shouldCreateControllerForCard(fromJson)) {
            return StandardController.create(fromJson);
        }
        return null;
    }
}
