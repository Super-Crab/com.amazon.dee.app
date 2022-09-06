package com.amazon.alexa.mobilytics.event.serializer.handlers;

import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.google.common.base.Preconditions;
import java.util.EnumSet;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
@Singleton
/* loaded from: classes9.dex */
public class UserDataHandler implements DataHandler {
    private static final String KEY = "customer";
    private final MobilyticsUserProvider userProvider;

    @Inject
    public UserDataHandler(@NonNull MobilyticsUserProvider mobilyticsUserProvider) {
        this.userProvider = (MobilyticsUserProvider) Preconditions.checkNotNull(mobilyticsUserProvider);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler
    @Nullable
    public Pair<String, JSONObject> process(@NonNull MobilyticsEvent mobilyticsEvent) throws JSONException {
        String str;
        String str2;
        String str3;
        String str4;
        JSONObject jSONObject = new JSONObject();
        MobilyticsUser user = this.userProvider.user();
        String str5 = "Unknown";
        if (user != null) {
            str5 = user.directedId();
            str = user.marketplaceId();
            str2 = user.personId();
            str3 = user.countryOfResidence();
            str4 = user.personIdv2();
            Iterator it2 = EnumSet.allOf(MobilyticsUser.Attribute.class).iterator();
            while (it2.hasNext()) {
                MobilyticsUser.Attribute attribute = (MobilyticsUser.Attribute) it2.next();
                String attribute2 = user.attribute(attribute);
                if (!TextUtils.isEmpty(attribute2)) {
                    jSONObject.put(attribute.id(), attribute2);
                }
            }
        } else {
            str = str5;
            str2 = str;
            str3 = str2;
            str4 = str3;
        }
        jSONObject.put("directedId", str5).put(WebConstants.WebviewConstants.MARKETPLACE_ID, str).put(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID, str2).put(MetricsConfiguration.COUNTRY_OF_RESIDENCE, str3).put("personIdv2", str4);
        return Pair.create(KEY, jSONObject);
    }
}
