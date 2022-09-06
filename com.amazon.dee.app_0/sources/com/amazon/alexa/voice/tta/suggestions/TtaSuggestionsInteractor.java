package com.amazon.alexa.voice.tta.suggestions;

import android.content.Context;
import com.amazon.alexa.voice.ui.suggestions.SuggestionItem;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import com.amazon.alexa.voice.ui.tta.TtaSuggestionsListener;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class TtaSuggestionsInteractor {
    private final Context context;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final TtaSuggestionHandler suggestionHandler;
    private final TtaSuggestionModel suggestionModel;
    private final TtaNavigator ttaNavigator;

    @Inject
    public TtaSuggestionsInteractor(TtaSuggestionModel ttaSuggestionModel, TtaSuggestionHandler ttaSuggestionHandler, TtaNavigator ttaNavigator, Context context) {
        this.suggestionModel = ttaSuggestionModel;
        this.suggestionHandler = ttaSuggestionHandler;
        this.ttaNavigator = ttaNavigator;
        this.context = context;
    }

    public void getSuggestions(String str) {
        this.suggestionModel.getSuggestions(str);
    }

    public void initializeHandler(boolean z) {
        this.suggestionHandler.initialize(this, z);
        this.suggestionModel.initialize(z);
        this.disposable.add(this.suggestionModel.onInAppRoute().subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.suggestions.-$$Lambda$TtaSuggestionsInteractor$xQusOyJA68oOA-eSYrQaJGGYFbs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaSuggestionsInteractor.this.lambda$initializeHandler$0$TtaSuggestionsInteractor((String) obj);
            }
        }));
        this.disposable.add(this.suggestionModel.onExternalRoute().subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.suggestions.-$$Lambda$TtaSuggestionsInteractor$1j_RFIpnaFcRuoJjxeXYLtFhzJ4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaSuggestionsInteractor.this.lambda$initializeHandler$1$TtaSuggestionsInteractor((String) obj);
            }
        }));
    }

    public /* synthetic */ void lambda$initializeHandler$0$TtaSuggestionsInteractor(String str) throws Throwable {
        this.ttaNavigator.navigateInApp(this.context, str, null);
    }

    public /* synthetic */ void lambda$initializeHandler$1$TtaSuggestionsInteractor(String str) throws Throwable {
        this.ttaNavigator.navigateToExternalUrl(this.context, str);
    }

    public void onSuggestionSelected(String str, String str2, String str3) {
        this.suggestionModel.onSuggestionSelected(str, str2, str3);
    }

    public Observable<String> onSuggestionUtterance() {
        return this.suggestionModel.onUtterance();
    }

    public void release() {
        this.suggestionModel.release();
        this.disposable.clear();
    }

    public void setSuggestionListener(final TtaSuggestionsListener ttaSuggestionsListener) {
        CompositeDisposable compositeDisposable = this.disposable;
        Observable<List<SuggestionItem>> suggestionsList = this.suggestionModel.suggestionsList();
        ttaSuggestionsListener.getClass();
        compositeDisposable.add(suggestionsList.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.suggestions.-$$Lambda$JrBSbnOPK0OPUNdu5FYNq9zsw-0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaSuggestionsListener.this.onSuggestionsList((List) obj);
            }
        }));
    }
}
