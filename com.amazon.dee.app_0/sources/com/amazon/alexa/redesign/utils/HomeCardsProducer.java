package com.amazon.alexa.redesign.utils;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.entity.AlertBannerModel;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.ViewModelFactory;
import com.amazon.alexa.redesign.entity.ViewModelFactoryUtil;
import com.amazon.alexa.redesign.entity.templates.CarouselChipTemplateModel;
import com.amazon.alexa.redesign.entity.templates.CarouselGridTemplateModel;
import com.amazon.alexa.redesign.entity.templates.HeroCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.ListTemplateModel;
import com.amazon.alexa.redesign.entity.templates.MiniCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.SkillsSingleTemplateModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class HomeCardsProducer {
    private static final Gson gson = new Gson();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface NewCardFactory<CardModel> {
        CardModel create(JSONObject jSONObject, List<ViewTypeModel> list) throws JSONException;
    }

    private HomeCardsProducer() {
    }

    private static void addContentMetricsData(JSONObject jSONObject, Map<String, Object> map) {
        map.put("contentId", jSONObject.optString("contentId"));
        map.put("contentType", jSONObject.optString("contentType"));
        map.put("contentProvider", jSONObject.optString("contentProvider"));
        map.put("contentSource", jSONObject.optString("contentSource"));
    }

    private static void addP13nMetadata(JSONObject jSONObject, Map<String, Object> map) {
        map.put("modelVersion", jSONObject.optString("modelVersion"));
        map.put(EntertainmentConstants.TYPE_SECTION, jSONObject.optString(EntertainmentConstants.TYPE_SECTION));
        map.put("index", Integer.valueOf(jSONObject.optInt("index")));
        map.put("totalNumberOfItems", Integer.valueOf(jSONObject.optInt("totalNumberOfItems")));
    }

    @NonNull
    @VisibleForTesting
    static AlertBannerModel createOutageAlertModel(@NonNull JSONObject jSONObject) {
        AlertBannerModel alertBannerModel = (AlertBannerModel) gson.fromJson(jSONObject.toString(), (Class<Object>) AlertBannerModel.class);
        if (alertBannerModel.getAlertMessage() == null) {
            alertBannerModel.setAlertMessage("");
        }
        return alertBannerModel;
    }

    private static List<ViewTypeModel> createViewModelList(JSONObject jSONObject, ActionFactory actionFactory, int i) throws JSONException, IllegalArgumentException, ViewMalformedException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = jSONObject.getJSONArray("slots");
        ViewModelFactory viewModelFactory = new ViewModelFactory(actionFactory);
        verifyAllSlotsPresent(jSONArray, i);
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
            String viewType = ViewModelFactoryUtil.getViewType(jSONObject2);
            try {
                verifySlotPositionInRange(ViewModelFactoryUtil.getPosition(jSONObject2), i);
                arrayList.add(viewModelFactory.getModel(jSONObject2));
            } catch (Exception e) {
                throw new ViewMalformedException(e.getMessage(), viewType);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ad A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.amazon.alexa.redesign.entity.CardModel> fromRawHomeCards(org.json.JSONObject r8, com.amazon.alexa.redesign.actions.ActionFactory r9, com.amazon.alexa.redesign.HomeContract.OEInteractor r10, boolean r11) {
        /*
            java.lang.String r0 = ""
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$H26qcj9QBksoXupIQpvXrA2HE6Q r3 = com.amazon.alexa.redesign.utils.$$Lambda$HomeCardsProducer$H26qcj9QBksoXupIQpvXrA2HE6Q.INSTANCE
            java.lang.String r4 = "MiniTemplate"
            r2.put(r4, r3)
            com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$HPFm_OGsK6Tn5OkDNcdMp01VrGQ r3 = com.amazon.alexa.redesign.utils.$$Lambda$HomeCardsProducer$HPFm_OGsK6Tn5OkDNcdMp01VrGQ.INSTANCE
            java.lang.String r4 = "HeroTemplate"
            r2.put(r4, r3)
            com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$8H-Iq-YPjkgSZPUmhwO2qN4mGWs r3 = com.amazon.alexa.redesign.utils.$$Lambda$HomeCardsProducer$8HIqYPjkgSZPUmhwO2qN4mGWs.INSTANCE
            java.lang.String r4 = "ListTemplate"
            r2.put(r4, r3)
            com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$uGuaSDh2NhPBey7eKzUsq6EunoM r3 = com.amazon.alexa.redesign.utils.$$Lambda$HomeCardsProducer$uGuaSDh2NhPBey7eKzUsq6EunoM.INSTANCE
            java.lang.String r4 = "SkillsSingle"
            r2.put(r4, r3)
            com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$Ya94zBOsS7Vm0eBnorFNpM-yFyQ r3 = com.amazon.alexa.redesign.utils.$$Lambda$HomeCardsProducer$Ya94zBOsS7Vm0eBnorFNpMyFyQ.INSTANCE
            java.lang.String r4 = "CarouselTemplate"
            r2.put(r4, r3)
            com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$tnN9OiyKidkBnC5LnISZQTw-OaY r3 = com.amazon.alexa.redesign.utils.$$Lambda$HomeCardsProducer$tnN9OiyKidkBnC5LnISZQTwOaY.INSTANCE
            java.lang.String r4 = "CarouselGridTemplate"
            r2.put(r4, r3)
            java.lang.String r3 = "content"
            org.json.JSONArray r8 = r8.optJSONArray(r3)
            if (r8 == 0) goto Lbf
            r3 = 0
        L3f:
            int r4 = r8.length()
            if (r3 >= r4) goto Lbf
            org.json.JSONObject r4 = r8.getJSONObject(r3)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L9a java.lang.IllegalArgumentException -> L9c org.json.JSONException -> L9e
            java.lang.String r5 = "templateType"
            java.lang.String r5 = r4.optString(r5, r0)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L9a java.lang.IllegalArgumentException -> L9c org.json.JSONException -> L9e
            java.lang.String r6 = "AlertBanner"
            boolean r6 = r5.equals(r6)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            if (r6 == 0) goto L5f
            com.amazon.alexa.redesign.entity.AlertBannerModel r4 = createOutageAlertModel(r4)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            r1.add(r4)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            goto Lbc
        L5f:
            boolean r6 = r2.containsKey(r5)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            if (r6 == 0) goto L8b
            java.util.Map<java.lang.String, java.lang.Integer> r6 = com.amazon.alexa.redesign.utils.Constants.SLOT_COUNTS     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            boolean r6 = r6.containsKey(r5)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            if (r6 == 0) goto L8b
            java.util.Map<java.lang.String, java.lang.Integer> r6 = com.amazon.alexa.redesign.utils.Constants.SLOT_COUNTS     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            java.lang.Object r6 = r6.get(r5)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            int r6 = r6.intValue()     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            java.util.List r6 = createViewModelList(r4, r9, r6)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            java.lang.Object r7 = r2.get(r5)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            com.amazon.alexa.redesign.utils.HomeCardsProducer$NewCardFactory r7 = (com.amazon.alexa.redesign.utils.HomeCardsProducer.NewCardFactory) r7     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            java.lang.Object r4 = r7.create(r4, r6)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            r1.add(r4)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            goto Lbc
        L8b:
            com.amazon.alexa.redesign.entity.templates.DomainCardTemplateModel r6 = new com.amazon.alexa.redesign.entity.templates.DomainCardTemplateModel     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            r6.<init>(r4)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            r1.add(r6)     // Catch: com.amazon.alexa.redesign.utils.ViewMalformedException -> L94 java.lang.IllegalArgumentException -> L96 org.json.JSONException -> L98
            goto Lbc
        L94:
            r4 = move-exception
            goto La0
        L96:
            r4 = move-exception
            goto La0
        L98:
            r4 = move-exception
            goto La0
        L9a:
            r4 = move-exception
            goto L9f
        L9c:
            r4 = move-exception
            goto L9f
        L9e:
            r4 = move-exception
        L9f:
            r5 = r0
        La0:
            java.lang.String r6 = r4.getMessage()
            java.lang.String r7 = "HomeChannelNative: "
            android.util.Log.e(r7, r6)
            boolean r6 = r4 instanceof com.amazon.alexa.redesign.utils.ViewMalformedException
            if (r6 == 0) goto Lb9
            if (r11 == 0) goto Lb9
            com.amazon.alexa.redesign.utils.ViewMalformedException r4 = (com.amazon.alexa.redesign.utils.ViewMalformedException) r4
            java.lang.String r4 = r4.getViewType()
            r10.recordMalformedViewOccurrence(r4, r5)
            goto Lbc
        Lb9:
            r10.recordMalformedDataOccurrence(r5)
        Lbc:
            int r3 = r3 + 1
            goto L3f
        Lbf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.redesign.utils.HomeCardsProducer.fromRawHomeCards(org.json.JSONObject, com.amazon.alexa.redesign.actions.ActionFactory, com.amazon.alexa.redesign.HomeContract$OEInteractor, boolean):java.util.List");
    }

    public static Map<String, Object> getTopLevelMetricsData(@NonNull JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        JSONObject optJSONObject = jSONObject.optJSONObject("p13nMetadata");
        addContentMetricsData(jSONObject, hashMap);
        if (optJSONObject != null) {
            addP13nMetadata(optJSONObject, hashMap);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CardModel lambda$fromRawHomeCards$0(JSONObject jSONObject, List list) throws JSONException {
        return new MiniCardTemplateModel(jSONObject, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CardModel lambda$fromRawHomeCards$1(JSONObject jSONObject, List list) throws JSONException {
        return new HeroCardTemplateModel(jSONObject, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CardModel lambda$fromRawHomeCards$2(JSONObject jSONObject, List list) throws JSONException {
        return new ListTemplateModel(jSONObject, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CardModel lambda$fromRawHomeCards$3(JSONObject jSONObject, List list) throws JSONException {
        return new SkillsSingleTemplateModel(jSONObject, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CardModel lambda$fromRawHomeCards$4(JSONObject jSONObject, List list) throws JSONException {
        return new CarouselChipTemplateModel(jSONObject, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CardModel lambda$fromRawHomeCards$5(JSONObject jSONObject, List list) throws JSONException {
        return new CarouselGridTemplateModel(jSONObject, list);
    }

    private static void verifyAllSlotsPresent(@NonNull JSONArray jSONArray, int i) throws IllegalArgumentException {
        if (i == jSONArray.length()) {
            return;
        }
        throw new IllegalArgumentException("HomeChannelNative: Slots in json array and number of slots dont match");
    }

    private static void verifySlotPositionInRange(int i, int i2) throws IllegalArgumentException {
        if (i < 0 || i >= i2) {
            throw new IllegalArgumentException("HomeChannelNative: Position for slot not in range.");
        }
    }
}
