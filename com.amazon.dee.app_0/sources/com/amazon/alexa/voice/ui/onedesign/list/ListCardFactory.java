package com.amazon.alexa.voice.ui.onedesign.list;

import com.amazon.alexa.voice.ui.onedesign.list.ListCard;
import com.amazon.alexa.voice.ui.onedesign.list.ListCardModel;
import com.amazon.alexa.voice.ui.onedesign.util.CharSequenceUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class ListCardFactory {
    private ListCardFactory() {
    }

    public static ListCard fromJson(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("listType");
        return new ListCard.Builder().listType(string).itemList(makeListItems(jSONObject)).build();
    }

    private static List<ListCardModel.ItemModel> makeListItems(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = jSONObject.getJSONArray("listItems");
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(new ListCard.Item.Builder().name(CharSequenceUtils.capitalize(jSONArray.getJSONObject(i).getString("listItemValue"))).build());
        }
        return arrayList;
    }
}
