package com.amazon.alexa.voice.ui.onedesign.list;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.empty.EmptyCard;
import com.amazon.alexa.voice.ui.onedesign.empty.EmptyController;
import com.amazon.alexa.voice.ui.routing.Destination;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.amazon.regulator.ViewController;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class ListControllerFactory implements ControllerFactory<ViewController> {
    @Nullable
    public static ViewController createEmptyList(ListCard listCard) {
        if (listCard.getItemList().isEmpty()) {
            return EmptyController.create(new EmptyCard(listCard.getListType(), null, Destination.LISTS));
        }
        return null;
    }

    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public ViewController mo2780create(JSONObject jSONObject) throws JSONException {
        ListCard fromJson = ListCardFactory.fromJson(jSONObject);
        ViewController createEmptyList = createEmptyList(fromJson);
        return createEmptyList == null ? ListController.create(fromJson) : createEmptyList;
    }
}
