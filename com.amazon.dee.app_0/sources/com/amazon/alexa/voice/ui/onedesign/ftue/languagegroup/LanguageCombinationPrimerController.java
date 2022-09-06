package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerParameters;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.DividerDecoration;
import com.amazon.alexa.voice.ui.onedesign.widget.OnItemClickListener;
import com.amazon.regulator.ViewController;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class LanguageCombinationPrimerController extends ViewController implements LanguageCombinationPrimerContract.View {
    private static final String AVAILABLE_LANGUAGE_COMBINATIONS_PARAMETER = "availableLanguageCombinations";
    private static final String CURRENT_LANGUAGE_COMBINATION_PARAMETER = "currentLanguageCombination";
    private static final String RECOMMENDED_LANGUAGE_COMBINATIONS_PARAMETER = "recommendedLanguageCombinations";
    private static final String TAG = "LanguageCombinationPrimerController";
    private LanguageCombinationListAdapter availableLanguageCombinationsListAdapter;
    private TextView availableLanguageHeaderTextView;
    private View availableLocalesContainer;
    private Button continueButtonView;
    private TextView headingView;
    private OnItemSelectionDetected onItemSelectionDetected = new OnItemSelectionDetected() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.-$$Lambda$LanguageCombinationPrimerController$jvI1Bc2p3f9-RAokCoZjPu4ils0
        @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.OnItemSelectionDetected
        public final void onSelectionDetected(LanguageCombinationListItem languageCombinationListItem) {
            LanguageCombinationPrimerController.this.lambda$new$3$LanguageCombinationPrimerController(languageCombinationListItem);
        }
    };
    private LanguageCombinationPrimerContract.Presenter presenter;
    private LanguageCombinationListAdapter recommendedLanguageCombinationsListAdapter;
    private TextView recommendedLanguageHeaderTextView;
    private View recommendedLocalesContainer;
    private TextView sectionDescriptionTextView;
    private TextView titleView;

    public static LanguageCombinationPrimerController create(@NonNull LanguageCombinationPrimerParameters languageCombinationPrimerParameters) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CURRENT_LANGUAGE_COMBINATION_PARAMETER, languageCombinationPrimerParameters.getCurrentLanguage());
        bundle.putParcelableArrayList(AVAILABLE_LANGUAGE_COMBINATIONS_PARAMETER, languageCombinationPrimerParameters.getAvailableLanguages());
        bundle.putParcelableArrayList(RECOMMENDED_LANGUAGE_COMBINATIONS_PARAMETER, languageCombinationPrimerParameters.getRecommendedLanguages());
        LanguageCombinationPrimerController languageCombinationPrimerController = new LanguageCombinationPrimerController();
        languageCombinationPrimerController.setArguments(bundle);
        return languageCombinationPrimerController;
    }

    private LanguageCombinationPrimerParameters getLanguageCombinationPrimerParameters(Bundle bundle) {
        LanguageCombinationPrimerParameters.Builder builder = new LanguageCombinationPrimerParameters.Builder();
        builder.currentLanguage((LanguageGroup) bundle.getParcelable(CURRENT_LANGUAGE_COMBINATION_PARAMETER));
        ArrayList<LanguageGroup> parcelableArrayList = bundle.getParcelableArrayList(AVAILABLE_LANGUAGE_COMBINATIONS_PARAMETER);
        if (parcelableArrayList == null) {
            parcelableArrayList = new ArrayList<>();
        }
        builder.availableLanguages(parcelableArrayList);
        ArrayList<LanguageGroup> parcelableArrayList2 = bundle.getParcelableArrayList(RECOMMENDED_LANGUAGE_COMBINATIONS_PARAMETER);
        if (parcelableArrayList2 == null) {
            parcelableArrayList2 = new ArrayList<>();
        }
        builder.recommendedLanguages(parcelableArrayList2);
        return builder.build();
    }

    private LanguageGroup getSelectedLanguage() {
        LanguageCombinationListItem selectedItem = this.recommendedLanguageCombinationsListAdapter.getSelectedItem();
        if (selectedItem != null) {
            return selectedItem.getLanguageGroup();
        }
        return this.availableLanguageCombinationsListAdapter.getSelectedItem().getLanguageGroup();
    }

    private void updateHeaderProperties(View view) {
        this.titleView = (TextView) view.findViewById(R.id.title);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.titleView);
        view.findViewById(R.id.subTitle).setVisibility(4);
        view.findViewById(R.id.close).setVisibility(8);
        view.findViewById(R.id.back).setVisibility(8);
    }

    public /* synthetic */ void lambda$new$3$LanguageCombinationPrimerController(LanguageCombinationListItem languageCombinationListItem) {
        setContinueButtonEnabled(true);
    }

    public /* synthetic */ void lambda$onCreateView$0$LanguageCombinationPrimerController(View view) {
        LanguageGroup selectedLanguage = getSelectedLanguage();
        if (selectedLanguage == null) {
            Log.e(TAG, "selectedLanguage is null, this shouldn't happen!");
        } else {
            this.presenter.continueClicked(selectedLanguage);
        }
    }

    public /* synthetic */ void lambda$onCreateView$1$LanguageCombinationPrimerController(LanguageCombinationListItem languageCombinationListItem) {
        this.recommendedLanguageCombinationsListAdapter.clearSelection();
    }

    public /* synthetic */ void lambda$onCreateView$2$LanguageCombinationPrimerController(LanguageCombinationListItem languageCombinationListItem) {
        this.availableLanguageCombinationsListAdapter.clearSelection();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(View view) {
        super.onAttach(view);
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        overrideTheme(R.style.Alexa_Voice_OneDesign_Moasic);
        LanguageCombinationPrimerParameters languageCombinationPrimerParameters = getLanguageCombinationPrimerParameters(getArguments());
        LocaleAuthorityWrapper from = LocaleAuthorityWrapper.from(getComponent());
        this.presenter = new LanguageCombinationPrimerPresenter(this, new LanguageCombinationPrimerInteractor(new LanguageCombinationPrimerMediator(this, from), languageCombinationPrimerParameters), new AndroidResources(getContext(), from.getLocale()), getComponent().isRegistered(MetricsBridge.class) ? (MetricsBridge) getComponent().get(MetricsBridge.class) : null);
    }

    @Override // com.amazon.regulator.ViewController
    protected View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_language_ftue_primer, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        updateHeaderProperties(inflate);
        this.recommendedLocalesContainer = inflate.findViewById(R.id.recommendedLocalesContainer);
        this.availableLocalesContainer = inflate.findViewById(R.id.availableLocalesContainer);
        this.headingView = (TextView) inflate.findViewById(R.id.headingText);
        this.availableLanguageHeaderTextView = (TextView) inflate.findViewById(R.id.availableLanguagesHeader).findViewById(R.id.headerText);
        this.recommendedLanguageHeaderTextView = (TextView) inflate.findViewById(R.id.recommendedLanguagesHeader).findViewById(R.id.headerText);
        this.sectionDescriptionTextView = (TextView) inflate.findViewById(R.id.noteText);
        this.continueButtonView = (Button) inflate.findViewById(R.id.continueButton);
        this.continueButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.-$$Lambda$LanguageCombinationPrimerController$FVCUWro4JqeCqgqaI-P7TPMHa3o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LanguageCombinationPrimerController.this.lambda$onCreateView$0$LanguageCombinationPrimerController(view);
            }
        });
        setContinueButtonEnabled(false);
        this.availableLanguageCombinationsListAdapter = new LanguageCombinationListAdapter(new OnItemClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.-$$Lambda$LanguageCombinationPrimerController$Xp0_vqN-2rOXb21K-V7zB69ASCg
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.OnItemClickListener
            public final void onItemClicked(Object obj) {
                LanguageCombinationPrimerController.this.lambda$onCreateView$1$LanguageCombinationPrimerController((LanguageCombinationListItem) obj);
            }
        }, this.onItemSelectionDetected);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.availableLanguages);
        recyclerView.setAdapter(this.availableLanguageCombinationsListAdapter);
        recyclerView.addItemDecoration(new DividerDecoration(getContext(), R.color.voice_ui_od_list_item_divider, this.availableLanguageCombinationsListAdapter));
        this.recommendedLanguageCombinationsListAdapter = new LanguageCombinationListAdapter(new OnItemClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.-$$Lambda$LanguageCombinationPrimerController$HFGI50_QvsEPUvuVmRr9ishPRtM
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.OnItemClickListener
            public final void onItemClicked(Object obj) {
                LanguageCombinationPrimerController.this.lambda$onCreateView$2$LanguageCombinationPrimerController((LanguageCombinationListItem) obj);
            }
        }, this.onItemSelectionDetected);
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(R.id.recommendedLanguages);
        recyclerView2.setAdapter(this.recommendedLanguageCombinationsListAdapter);
        recyclerView2.addItemDecoration(new DividerDecoration(getContext(), R.color.voice_ui_od_list_item_divider, this.recommendedLanguageCombinationsListAdapter));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.titleView = null;
        this.headingView = null;
        this.availableLanguageHeaderTextView = null;
        this.recommendedLanguageHeaderTextView = null;
        this.sectionDescriptionTextView = null;
        this.continueButtonView = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setAvailableLanguages(List<LanguageCombinationListItem> list) {
        this.availableLanguageCombinationsListAdapter.set(list);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setAvailableLanguagesHeaderText(CharSequence charSequence) {
        this.availableLanguageHeaderTextView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setAvailableSectionVisible(boolean z) {
        this.availableLocalesContainer.setVisibility(z ? 0 : 8);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setContinueButtonEnabled(boolean z) {
        this.continueButtonView.setEnabled(z);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setContinueButtonText(CharSequence charSequence) {
        this.continueButtonView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setHeadingText(CharSequence charSequence) {
        this.headingView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setRecommendedLanguages(List<LanguageCombinationListItem> list) {
        this.recommendedLanguageCombinationsListAdapter.set(list);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setRecommendedLanguagesHeaderText(CharSequence charSequence) {
        this.recommendedLanguageHeaderTextView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setRecommendedSectionVisible(boolean z) {
        this.recommendedLocalesContainer.setVisibility(z ? 0 : 8);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setSectionDescription(CharSequence charSequence) {
        this.sectionDescriptionTextView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.View
    public void setTitle(CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }
}
