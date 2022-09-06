package com.amazon.alexa.voice.ui.cards;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.cards.CardCreationEventListener;
import com.amazon.alexa.voice.ui.cards.util.LocaleUtils;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class CardRendererControllerFactory implements CardFactory {
    private static final Set<String> RTL_SUPPORTED_CARDS = new HashSet(Arrays.asList("StandardCard", "KnowledgeCard", "TextCard", "KnowledgeTextCard"));
    private static final String TAG = "CardRendererControllerFactory";
    private final CardCreationEventListener cardCreationEventListener;
    private final Map<String, ControllerFactory<? extends ViewController>> oneDesignControllerFactories;

    public CardRendererControllerFactory(CardCreationEventListener cardCreationEventListener, Map<String, ControllerFactory<? extends ViewController>> map) {
        this.cardCreationEventListener = cardCreationEventListener;
        this.oneDesignControllerFactories = map;
    }

    private JSONObject getCardJson(@Nullable String str) {
        JSONObject jSONObject;
        boolean z;
        this.cardCreationEventListener.parsingJsonStart();
        if (str == null) {
            Log.e(TAG, "Null card data.", new NullPointerException());
            this.cardCreationEventListener.errorParsingJson(CardCreationEventListener.ErrorType.CARD_DATA_NULL, "Null card data.");
        } else {
            try {
                jSONObject = new JSONObject(str);
                z = true;
            } catch (JSONException e) {
                Log.e(TAG, "Invalid JSON format!", e);
                this.cardCreationEventListener.errorParsingJson(CardCreationEventListener.ErrorType.INVALID_JSON, e.getMessage());
            }
            this.cardCreationEventListener.parsingJsonFinished(z);
            return jSONObject;
        }
        jSONObject = null;
        z = false;
        this.cardCreationEventListener.parsingJsonFinished(z);
        return jSONObject;
    }

    private String getCardType(JSONObject jSONObject) {
        try {
            return jSONObject.getString("cardType");
        } catch (JSONException e) {
            Log.e(TAG, "Couldn't find cardType label in the JSON", e);
            this.cardCreationEventListener.errorParsingJson(CardCreationEventListener.ErrorType.MISSING_CARD_TYPE, e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void register(Map<String, ControllerFactory<? extends ViewController>> map, ControllerFactory<? extends ViewController> controllerFactory, String... strArr) {
        for (String str : strArr) {
            map.put(str, controllerFactory);
        }
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardFactory
    public ViewController buildCard(@Nullable String str, Locale locale) {
        String cardType;
        ControllerFactory<? extends ViewController> factory;
        this.cardCreationEventListener.cardControllerCreationStart();
        JSONObject cardJson = getCardJson(str);
        ViewController viewController = null;
        if (cardJson == null || (cardType = getCardType(cardJson)) == null || (factory = getFactory(cardType)) == null) {
            return null;
        }
        try {
            ViewController mo2780create = factory.mo2780create(cardJson);
            if (LocaleUtils.isRtl(locale) && !RTL_SUPPORTED_CARDS.contains(cardType)) {
                if (this.cardCreationEventListener instanceof CardCreationEventListenerV2) {
                    ((CardCreationEventListenerV2) this.cardCreationEventListener).localizationNotSupported(cardType);
                }
            } else {
                this.cardCreationEventListener.foundCardFactory(cardType);
                this.cardCreationEventListener.cardControllerCreationFinished(cardType, true);
                viewController = mo2780create;
            }
        } catch (Exception e) {
            Log.e(TAG, "Fail to create ViewController", e);
            this.cardCreationEventListener.foundCardFactory(cardType);
            this.cardCreationEventListener.errorCreatingCard(cardType, e);
            this.cardCreationEventListener.errorCreatingCardController(cardType, e);
            this.cardCreationEventListener.cardControllerCreationFinished(cardType, false);
        }
        return viewController;
    }

    ControllerFactory<? extends ViewController> getFactory(@NonNull String str) {
        ControllerFactory<? extends ViewController> controllerFactory = this.oneDesignControllerFactories.get(str);
        if (controllerFactory == null) {
            GeneratedOutlineSupport1.outline162("Couldn't find a card of type: ", str, TAG);
            this.cardCreationEventListener.missingCardFactory(str);
        }
        return controllerFactory;
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardFactory
    @Deprecated
    public ViewController buildCard(@Nullable String str) {
        return buildCard(str, Locale.getDefault());
    }
}
