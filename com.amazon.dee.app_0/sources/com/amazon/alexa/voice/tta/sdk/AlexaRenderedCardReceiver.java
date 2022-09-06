package com.amazon.alexa.voice.tta.sdk;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaCardExtras;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.voice.tta.R;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaResponseRenderedCard;
import com.amazon.alexa.voice.ui.provider.CardRendererModelFactory;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
/* loaded from: classes11.dex */
public class AlexaRenderedCardReceiver implements AlexaCardListener {
    private static final String TAG = "ARCReceiver";
    private final CardRendererModelFactory cardRendererModelFactory;
    private final PublishSubject<TtaResponseCard> cardSubject;
    private final Context context;

    public AlexaRenderedCardReceiver(Context context) {
        this(context, PublishSubject.create(), new CardRendererModelFactory());
    }

    public Observable<TtaResponseCard> onAlexaCard() {
        return this.cardSubject;
    }

    @Override // com.amazon.alexa.api.AlexaCardListener
    public void onReceivedRenderCard(String str, AlexaCardExtras alexaCardExtras) {
        try {
            TtaResponseCard card = this.cardRendererModelFactory.getCard(str);
            if (card == null) {
                return;
            }
            this.cardSubject.onNext(new TtaResponseRenderedCard(card.getId(), this.context.getString(R.string.tta_response_card_learn_more), card.getDescription().equals(this.context.getString(R.string.tta_response_card_learn_more)) ? "" : card.getDescription(), card.getLinkUrl(), card.getCardType()));
        } catch (Exception e) {
            Log.e(TAG, "Unable to get TtaResponseCard from RenderCard data", e);
        }
    }

    @VisibleForTesting
    AlexaRenderedCardReceiver(Context context, PublishSubject publishSubject, CardRendererModelFactory cardRendererModelFactory) {
        this.context = context;
        this.cardSubject = publishSubject;
        this.cardRendererModelFactory = cardRendererModelFactory;
    }
}
