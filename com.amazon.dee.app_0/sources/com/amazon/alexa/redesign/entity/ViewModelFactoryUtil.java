package com.amazon.alexa.redesign.entity;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.entity.viewtypes.AlarmsTimersModel;
import com.amazon.alexa.redesign.entity.viewtypes.BorderedTextButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.CarouselChipModel;
import com.amazon.alexa.redesign.entity.viewtypes.CarouselGridModel;
import com.amazon.alexa.redesign.entity.viewtypes.EmptyModel;
import com.amazon.alexa.redesign.entity.viewtypes.GridIconTitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.HeaderModel;
import com.amazon.alexa.redesign.entity.viewtypes.IconTitleSubtitleHintModel;
import com.amazon.alexa.redesign.entity.viewtypes.IconTitleSubtitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ImageButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.ImageModel;
import com.amazon.alexa.redesign.entity.viewtypes.OptInBottomSheetModel;
import com.amazon.alexa.redesign.entity.viewtypes.TextButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.TitleSubtitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleState;
import com.amazon.alexa.redesign.utils.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class ViewModelFactoryUtil {
    private ViewModelFactoryUtil() {
    }

    @NonNull
    public static ViewTypeModel getAlarmsTimersModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("action");
        JSONObject jSONObject2 = jSONObject.getJSONObject("resources");
        String string = jSONObject2.getString("imageUrl");
        String string2 = jSONObject2.getString("title");
        String optString = jSONObject2.optString("subtitle");
        String optString2 = jSONObject2.optString("tint");
        String optString3 = jSONObject2.optString("imageTheme");
        String optString4 = jSONObject2.optString("imageType", Constants.IMAGE_TYPE_ICON);
        boolean z = jSONObject2.getBoolean("italicized");
        boolean optBoolean = jSONObject2.optBoolean(TtmlNode.BOLD, true);
        long optLong = jSONObject2.optLong("triggerTime", 0L);
        return new AlarmsTimersModel(new IconTitleSubtitleModel.Builder().withAction(actionFactory.getAction(optJSONObject)).withIconUrl(string).withSubtitle(optString).withTitle(string2).withViewPosition(position).withImageType(optString4).withItalicized(z).withBold(optBoolean).withTint(optString2).withImageTheme(optString3), jSONObject2.optString("state"), optLong);
    }

    @NonNull
    public static ViewTypeModel getBorderedTextButtonModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        return new BorderedTextButtonModel.Builder().withButtonText(jSONObject.getJSONObject("resources").getString("text")).withViewPosition(getPosition(jSONObject)).withAction(actionFactory.getAction(jSONObject.optJSONObject("action"))).mo2376build();
    }

    @NonNull
    public static ViewTypeModel getCarouselChipModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory, @NonNull ViewModelFactory viewModelFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("action");
        JSONArray jSONArray = jSONObject.getJSONObject("resources").getJSONArray("chips");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(viewModelFactory.getModel(jSONArray.getJSONObject(i)));
        }
        return new CarouselChipModel(arrayList, actionFactory.getAction(optJSONObject), position);
    }

    @NonNull
    public static ViewTypeModel getCarouselGridModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory, @NonNull ViewModelFactory viewModelFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("action");
        JSONArray jSONArray = jSONObject.getJSONObject("resources").getJSONArray("grids");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(viewModelFactory.getModel(jSONArray.getJSONObject(i)));
        }
        return new CarouselGridModel(arrayList, actionFactory.getAction(optJSONObject), position);
    }

    @NonNull
    public static ViewTypeModel getChipIconTitleModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        boolean z = jSONObject.getBoolean("circular");
        String optString = jSONObject.optString("carouselItemId");
        String optString2 = jSONObject.optString("carouselItemType");
        String optString3 = jSONObject.optString("carouselItemProvider");
        JSONArray jSONArray = jSONObject.getJSONArray("states");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            JSONObject optJSONObject = jSONObject2.optJSONObject("action");
            JSONObject jSONObject3 = jSONObject2.getJSONObject("resources");
            String string = jSONObject2.getString("state");
            String string2 = jSONObject3.getString("title");
            String optString4 = jSONObject3.optString("imageType", Constants.IMAGE_TYPE_ICON);
            arrayList.add(new ChipIconTitleState.Builder().withAction(actionFactory.getAction(optJSONObject)).withState(string).withImageType(optString4).withImageUrl(jSONObject3.optString("imageUrl", "")).withTitle(string2).build());
        }
        return new ChipIconTitleModel.Builder().withCircular(z).withStates(arrayList).withViewPosition(position).withCarouselItemId(optString).withCarouselItemType(optString2).withCarouselItemProvider(optString3).build();
    }

    @NonNull
    public static ViewTypeModel getEmptyModel(@NonNull JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return new EmptyModel(getPosition(jSONObject));
    }

    @NonNull
    public static ViewTypeModel getGridIconTitleModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("action");
        JSONObject jSONObject2 = jSONObject.getJSONObject("resources");
        String string = jSONObject2.getString("title");
        String optString = jSONObject2.optString("imageType", Constants.IMAGE_TYPE_ICON);
        String string2 = jSONObject2.getString("imageUrl");
        String optString2 = jSONObject.optString("carouselItemId");
        return new GridIconTitleModel.Builder().withAction(actionFactory.getAction(optJSONObject)).withImageType(optString).withImageUrl(string2).withTitle(string).withViewPosition(position).withCarouselItemId(optString2).withCarouselItemProvider(jSONObject.optString("carouselItemProvider")).withCarouselItemType(jSONObject.optString("carouselItemType")).build();
    }

    @NonNull
    public static ViewTypeModel getHeaderModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("action");
        return new HeaderModel.Builder().withAction(actionFactory.getAction(optJSONObject)).withTitle(jSONObject.getJSONObject("resources").getString("title")).withViewPosition(position).build();
    }

    @NonNull
    public static ViewTypeModel getIconTitleSubtitleModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("action");
        JSONObject jSONObject2 = jSONObject.getJSONObject("resources");
        String string = jSONObject2.getString("imageUrl");
        String string2 = jSONObject2.getString("title");
        String optString = jSONObject2.optString("subtitle");
        String optString2 = jSONObject2.optString("tint");
        String optString3 = jSONObject2.optString("imageTheme");
        String optString4 = jSONObject2.optString("imageType", Constants.IMAGE_TYPE_ICON);
        boolean z = jSONObject2.getBoolean("italicized");
        return new IconTitleSubtitleModel.Builder().withAction(actionFactory.getAction(optJSONObject)).withIconUrl(string).withSubtitle(optString).withTitle(string2).withViewPosition(position).withImageType(optString4).withItalicized(z).withBold(jSONObject2.optBoolean(TtmlNode.BOLD, true)).withTint(optString2).withImageTheme(optString3).build();
    }

    @NonNull
    public static ViewTypeModel getImageButtonModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("action");
        return new ImageButtonModel.Builder().withAction(actionFactory.getAction(optJSONObject)).withImageUrl(jSONObject.getJSONObject("resources").getString("imageUrl")).withViewPosition(position).build();
    }

    @NonNull
    public static ViewTypeModel getImageModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        return new ImageModel.Builder().withImageUri(jSONObject.getJSONObject("resources").getString("imageUrl")).withViewPosition(getPosition(jSONObject)).withAction(actionFactory.getAction(jSONObject.optJSONObject("action"))).build();
    }

    @NonNull
    public static ViewTypeModel getOptInBottomSheetModel(@NonNull JSONObject jSONObject, @NonNull ViewModelFactory viewModelFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("resources");
        JSONArray jSONArray = jSONObject2.getJSONArray("buttons");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(viewModelFactory.getModel(jSONArray.getJSONObject(i)));
        }
        String string = jSONObject2.getString("imageUrl");
        String string2 = jSONObject2.getString("header");
        String string3 = jSONObject2.getString("subHeader");
        String string4 = jSONObject2.getString("bodyText");
        return new OptInBottomSheetModel.Builder().withViewPosition(position).withImageUrl(string).withHeader(string2).withSubHeader(string3).withBodyText(string4).withBottomSheetId(jSONObject2.getString("bottomSheetId")).withButtons(arrayList).build();
    }

    public static int getPosition(@NonNull JSONObject jSONObject) throws IllegalArgumentException {
        try {
            return Integer.parseInt(jSONObject.getString(ViewProps.POSITION));
        } catch (NumberFormatException | JSONException unused) {
            throw new IllegalArgumentException("HomeChannelNative: Position was not a number");
        }
    }

    @NonNull
    public static ViewTypeModel getSkillsModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("action");
        JSONObject jSONObject2 = jSONObject.getJSONObject("resources");
        String string = jSONObject2.getString("imageUrl");
        String string2 = jSONObject2.getString("title");
        String optString = jSONObject2.optString("subtitle");
        String optString2 = jSONObject2.optString("hint");
        String optString3 = jSONObject2.optString("tint");
        return new IconTitleSubtitleHintModel.Builder().withAction(actionFactory.getAction(optJSONObject)).withIconUrl(string).withSubtitle(optString).withTitle(string2).withViewPosition(position).withImageType(jSONObject2.optString("imageType", Constants.IMAGE_TYPE_ICON)).withTint(optString3).withImageTheme(jSONObject2.optString("imageTheme")).withHint(optString2).build();
    }

    @NonNull
    public static ViewTypeModel getTextButtonModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        return new TextButtonModel.Builder().withButtonText(jSONObject.getJSONObject("resources").getString("text")).withViewPosition(getPosition(jSONObject)).withAction(actionFactory.getAction(jSONObject.optJSONObject("action"))).mo2376build();
    }

    @NonNull
    public static ViewTypeModel getTitleSubtitleModel(@NonNull JSONObject jSONObject, @NonNull ActionFactory actionFactory) throws JSONException, IllegalArgumentException {
        int position = getPosition(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("action");
        JSONObject jSONObject2 = jSONObject.getJSONObject("resources");
        return new TitleSubtitleModel.Builder().withAction(actionFactory.getAction(optJSONObject)).withSubtitle(jSONObject2.optString("subtitle")).withTitle(jSONObject2.getString("title")).withViewPosition(position).build();
    }

    @NonNull
    public static String getViewType(@NonNull JSONObject jSONObject) throws JSONException {
        String optString = jSONObject.optString("viewType");
        return optString.equals("SingleButtonView") ? jSONObject.getJSONObject("resources").optString("type") : optString;
    }
}
