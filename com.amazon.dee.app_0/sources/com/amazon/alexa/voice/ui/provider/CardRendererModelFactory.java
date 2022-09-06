package com.amazon.alexa.voice.ui.provider;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.standard.StandardControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.alexa.voice.ui.tta.ModelFactory;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class CardRendererModelFactory {
    private static final String TAG = "CRModelFactory";
    private final Map<String, ModelFactory<? extends TtaResponseCard>> oneDesignModelFactories = new HashMap();

    private Map<String, ModelFactory<? extends TtaResponseCard>> getControllerFactories() {
        this.oneDesignModelFactories.put("StandardCard", new StandardControllerFactory());
        this.oneDesignModelFactories.put("KnowledgeCard", new StandardControllerFactory());
        this.oneDesignModelFactories.put("TextCard", new StandardControllerFactory());
        this.oneDesignModelFactories.put("KnowledgeTextCard", new StandardControllerFactory());
        return this.oneDesignModelFactories;
    }

    public TtaResponseCard getCard(@Nullable String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        String optionalString = JSONUtils.optionalString(jSONObject, "cardType", (String) null);
        if (optionalString == null) {
            Log.e(TAG, "Fail to create TtaResponseCard because of null cardType.");
            return null;
        }
        ModelFactory<? extends TtaResponseCard> modelFactory = getControllerFactories().get(optionalString);
        if (modelFactory == null) {
            Log.e(TAG, "Fail to create TtaResponseCard because card type not in ControllerFactories.");
            return null;
        }
        try {
            return modelFactory.build(jSONObject);
        } catch (Exception e) {
            Log.e(TAG, "Fail to create TtaResponseCard", e);
            return null;
        }
    }
}
