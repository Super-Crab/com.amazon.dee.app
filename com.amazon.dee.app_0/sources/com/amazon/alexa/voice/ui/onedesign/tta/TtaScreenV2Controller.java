package com.amazon.alexa.voice.ui.onedesign.tta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.cards.util.StringUtils;
import com.amazon.alexa.voice.ui.onedesign.constants.Constants;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenParameters;
import com.amazon.alexa.voice.ui.onedesign.tta.adapter.TtaSuggestionsAdapter;
import com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaPillResultViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaSuggestionHintViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaSuggestionLinkViewHolder;
import com.amazon.alexa.voice.ui.suggestions.SuggestionAction;
import com.amazon.alexa.voice.ui.suggestions.SuggestionItem;
import com.amazon.alexa.vox.ui.onedesign.R;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class TtaScreenV2Controller extends TtaScreenController {
    private static final String TAG = "TtaScreenV2Controller";
    private RecyclerView pillsView;
    private ViewGroup suggestionsContainer;
    private RecyclerView suggestionsView;
    private TtaPillsAdapter ttaPillsAdapter;
    private TtaSuggestionsAdapter ttaSuggestionsAdapter;

    public static TtaScreenParametersModel getDefaultTtaScreenParameters() {
        return new TtaScreenParameters.Builder().hintText(null).appSearchEnabled(false).build();
    }

    private void hideShowMorePill() {
        TtaPillsAdapter ttaPillsAdapter = this.ttaPillsAdapter;
        if (ttaPillsAdapter == null || ttaPillsAdapter.getItemCount() <= 0 || ((PillResultTtaItem) this.ttaPillsAdapter.getItem(0)).getItemType() != 0) {
            return;
        }
        hidePills();
    }

    private void setupPillsAdapter() {
        this.ttaPillsAdapter = new TtaPillsAdapter();
        this.ttaPillsAdapter.addActionHandler(TtaPillResultViewHolder.class, new RvActionHandler() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenV2Controller$aNbrMMHUT-pIBw9B2PiRWpjC6kg
            @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler
            public final void handle(int i, int i2, Object obj, View view, Bundle bundle) {
                TtaScreenV2Controller.this.lambda$setupPillsAdapter$0$TtaScreenV2Controller(i, i2, obj, view, bundle);
            }
        });
    }

    private void setupPillsView(View view) {
        this.pillsView = (RecyclerView) view.findViewById(R.id.app_search_pills_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        this.pillsView.setLayoutManager(linearLayoutManager);
        this.pillsView.setAdapter(this.ttaPillsAdapter);
    }

    private void setupSuggestionsAdapter() {
        this.ttaSuggestionsAdapter = new TtaSuggestionsAdapter();
        this.ttaSuggestionsAdapter.addActionHandler(TtaSuggestionLinkViewHolder.class, new RvActionHandler() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenV2Controller$gkBlq-prORc6xcYgvhljsmUUqYk
            @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler
            public final void handle(int i, int i2, Object obj, View view, Bundle bundle) {
                TtaScreenV2Controller.this.lambda$setupSuggestionsAdapter$1$TtaScreenV2Controller(i, i2, obj, view, bundle);
            }
        });
        this.ttaSuggestionsAdapter.addActionHandler(TtaSuggestionHintViewHolder.class, new RvActionHandler() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenV2Controller$wFKT1youWMDVgRp5RGtimDR-q_Y
            @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler
            public final void handle(int i, int i2, Object obj, View view, Bundle bundle) {
                TtaScreenV2Controller.this.lambda$setupSuggestionsAdapter$2$TtaScreenV2Controller(i, i2, obj, view, bundle);
            }
        });
    }

    private void setupSuggestionsView(View view) {
        this.suggestionsContainer = (ViewGroup) view.findViewById(R.id.app_search_suggestions_container);
        this.suggestionsContainer.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenV2Controller$Oke8z1iJOU0EkDiLw605Jl0RLFk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TtaScreenV2Controller.this.lambda$setupSuggestionsView$3$TtaScreenV2Controller(view2);
            }
        });
        this.suggestionsView = (RecyclerView) view.findViewById(R.id.app_search_suggestions_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.suggestionsView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.suggestionsView.getContext(), linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(this.suggestionsView.getContext().getDrawable(R.drawable.space_divider));
        this.suggestionsView.addItemDecoration(dividerItemDecoration);
        this.suggestionsView.setAdapter(this.ttaSuggestionsAdapter);
    }

    private boolean shouldNotShowSuggestions(List<Object> list, String str) {
        return list == null || list.size() == 0 || StringUtils.isBlank(str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController, com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void clear() {
        super.clear();
        hideShowMorePill();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController
    protected int getLayoutId() {
        return R.layout.text_ui_od_tta_screen_v2;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController
    protected int getThemeId() {
        return R.style.Theme_Alexa_Text_V2;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController, com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void hidePills() {
        if (this.pillsView.getVisibility() != 8) {
            this.ttaPillsAdapter.clear();
            this.pillsView.setVisibility(8);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController, com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void hideSuggestions() {
        if (this.suggestionsContainer.getVisibility() != 8) {
            this.ttaSuggestionsAdapter.clear();
            this.suggestionsContainer.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$setupPillsAdapter$0$TtaScreenV2Controller(int i, int i2, Object obj, View view, Bundle bundle) {
        this.eventSender.sendEvent(TtaUiEvent.VOX_SIMBA_ITEM_SELECTED);
        this.presenter.pillSelected((PillResultTtaItem) obj);
    }

    public /* synthetic */ void lambda$setupSuggestionsAdapter$1$TtaScreenV2Controller(int i, int i2, Object obj, View view, Bundle bundle) {
        SuggestionLinkTtaItem suggestionLinkTtaItem = (SuggestionLinkTtaItem) obj;
        this.presenter.suggestionSelected(suggestionLinkTtaItem.getItemType(), suggestionLinkTtaItem.getActionData(), suggestionLinkTtaItem.getSuggestionId(), i2);
    }

    public /* synthetic */ void lambda$setupSuggestionsAdapter$2$TtaScreenV2Controller(int i, int i2, Object obj, View view, Bundle bundle) {
        SuggestionHintTtaItem suggestionHintTtaItem = (SuggestionHintTtaItem) obj;
        this.presenter.suggestionSelected(suggestionHintTtaItem.getItemType(), suggestionHintTtaItem.getSuggestionTitle().toString(), suggestionHintTtaItem.getSuggestionId(), i2);
    }

    public /* synthetic */ void lambda$setupSuggestionsView$3$TtaScreenV2Controller(View view) {
        hideSuggestions();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController, com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        setupPillsAdapter();
        setupSuggestionsAdapter();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController, com.amazon.regulator.ViewController
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup);
        setupPillsView(onCreateView);
        this.disposables.add(this.interactor.onSuggestionResults().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$2loDovOrnGUibMttMUXKzC1X4Dk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaScreenV2Controller.this.showSuggestions((List) obj);
            }
        }));
        setupSuggestionsView(onCreateView);
        return onCreateView;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController, com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void showPills(List<Object> list) {
        this.pillsView.setVisibility(0);
        this.ttaPillsAdapter.setItems(list);
        scrollConversationToEnd();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController, com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void showSuggestions(List<Object> list) {
        String obj = this.userInputView.getTextInputView().getText().toString();
        this.ttaSuggestionsAdapter.getDataProvider().put(Constants.Keys.INPUT_TEXT_KEY, obj);
        if (shouldNotShowSuggestions(list, obj)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it2 = list.iterator();
        while (it2.hasNext()) {
            SuggestionItem suggestionItem = (SuggestionItem) it2.next();
            if (suggestionItem.getAction().equals(SuggestionAction.UTTERANCE)) {
                arrayList.add(SuggestionHintTtaItem.fromSuggestionItem(suggestionItem));
            } else {
                arrayList.add(SuggestionLinkTtaItem.fromSuggestionItem(suggestionItem));
            }
        }
        this.eventSender.sendEvent(TtaUiEvent.VOX_SIMBA_SUGGESTION_SHOWN);
        this.ttaSuggestionsAdapter.setItems(arrayList);
        this.suggestionsContainer.setVisibility(0);
    }
}
