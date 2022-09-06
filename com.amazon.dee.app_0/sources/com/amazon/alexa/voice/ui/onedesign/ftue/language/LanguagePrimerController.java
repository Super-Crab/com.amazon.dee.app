package com.amazon.alexa.voice.ui.onedesign.ftue.language;

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
import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerParameters;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.DividerDecoration;
import com.amazon.alexa.voice.ui.onedesign.widget.OnItemClickListener;
import com.amazon.regulator.ViewController;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class LanguagePrimerController extends ViewController implements LanguagePrimerContract.View {
    private static final String AVAILABLE_LANGUAGES_PARAMETER = "availableLanguages";
    private static final String CURRENT_LANGUAGE_PARAMETER = "currentLanguage";
    private static final String RECOMMENDED_LANGUAGES_PARAMETER = "recommendedLanguages";
    private static final String TAG = "LanguagePrimerController";
    private TextView availableLanguageHeaderTextView;
    private LanguageListAdapter availableLanguagesListAdapter;
    private View availableLocalesContainer;
    private Button continueButtonView;
    private TextView headingView;
    private OnItemSelectionDetected onItemSelectionDetected = new OnItemSelectionDetected() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.language.-$$Lambda$LanguagePrimerController$D8fR-6wjom24OHjZCjfQy4DaSaU
        @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.OnItemSelectionDetected
        public final void onSelectionDetected(LanguageListItem languageListItem) {
            LanguagePrimerController.this.lambda$new$3$LanguagePrimerController(languageListItem);
        }
    };
    private LanguagePrimerContract.Presenter presenter;
    private TextView recommendedLanguageHeaderTextView;
    private LanguageListAdapter recommendedLanguagesListAdapter;
    private View recommendedLocalesContainer;
    private TextView sectionDescriptionTextView;
    private TextView titleView;

    public static LanguagePrimerController create(@NonNull LanguagePrimerParameters languagePrimerParameters) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CURRENT_LANGUAGE_PARAMETER, languagePrimerParameters.getCurrentLanguage());
        bundle.putParcelableArrayList(AVAILABLE_LANGUAGES_PARAMETER, languagePrimerParameters.getAvailableLanguages());
        bundle.putParcelableArrayList(RECOMMENDED_LANGUAGES_PARAMETER, languagePrimerParameters.getRecommendedLanguages());
        LanguagePrimerController languagePrimerController = new LanguagePrimerController();
        languagePrimerController.setArguments(bundle);
        return languagePrimerController;
    }

    private LanguagePrimerParameters getLanguagePrimerParameters(Bundle bundle) {
        LanguagePrimerParameters.Builder builder = new LanguagePrimerParameters.Builder();
        builder.currentLanguage((Language) bundle.getParcelable(CURRENT_LANGUAGE_PARAMETER));
        ArrayList<Language> parcelableArrayList = bundle.getParcelableArrayList(AVAILABLE_LANGUAGES_PARAMETER);
        if (parcelableArrayList == null) {
            parcelableArrayList = new ArrayList<>();
        }
        builder.availableLanguages(parcelableArrayList);
        ArrayList<Language> parcelableArrayList2 = bundle.getParcelableArrayList(RECOMMENDED_LANGUAGES_PARAMETER);
        if (parcelableArrayList2 == null) {
            parcelableArrayList2 = new ArrayList<>();
        }
        builder.recommendedLanguages(parcelableArrayList2);
        return builder.build();
    }

    private Language getSelectedLanguage() {
        LanguageListItem selectedItem = this.recommendedLanguagesListAdapter.getSelectedItem();
        if (selectedItem != null) {
            return selectedItem.getLanguage();
        }
        return this.availableLanguagesListAdapter.getSelectedItem().getLanguage();
    }

    private void updateHeaderProperties(View view) {
        this.titleView = (TextView) view.findViewById(R.id.title);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.titleView);
        view.findViewById(R.id.subTitle).setVisibility(4);
        view.findViewById(R.id.close).setVisibility(8);
        view.findViewById(R.id.back).setVisibility(8);
    }

    public /* synthetic */ void lambda$new$3$LanguagePrimerController(LanguageListItem languageListItem) {
        setContinueButtonEnabled(true);
    }

    public /* synthetic */ void lambda$onCreateView$0$LanguagePrimerController(View view) {
        Language selectedLanguage = getSelectedLanguage();
        if (selectedLanguage == null) {
            Log.e(TAG, "selectedLanguage is null, this shouldn't happen!");
        } else {
            this.presenter.continueClicked(selectedLanguage);
        }
    }

    public /* synthetic */ void lambda$onCreateView$1$LanguagePrimerController(LanguageListItem languageListItem) {
        this.recommendedLanguagesListAdapter.clearSelection();
    }

    public /* synthetic */ void lambda$onCreateView$2$LanguagePrimerController(LanguageListItem languageListItem) {
        this.availableLanguagesListAdapter.clearSelection();
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
        LanguagePrimerParameters languagePrimerParameters = getLanguagePrimerParameters(getArguments());
        LocaleAuthorityWrapper from = LocaleAuthorityWrapper.from(getComponent());
        this.presenter = new LanguagePrimerPresenter(this, new LanguagePrimerInteractor(new LanguagePrimerMediator(this, from), languagePrimerParameters), new AndroidResources(getContext(), from.getLocale()), getComponent().isRegistered(MetricsBridge.class) ? (MetricsBridge) getComponent().get(MetricsBridge.class) : null);
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
        this.continueButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.language.-$$Lambda$LanguagePrimerController$P8mv-nFE8EIK5c96rJWhaqf6zM8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LanguagePrimerController.this.lambda$onCreateView$0$LanguagePrimerController(view);
            }
        });
        setContinueButtonEnabled(false);
        this.availableLanguagesListAdapter = new LanguageListAdapter(new OnItemClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.language.-$$Lambda$LanguagePrimerController$5D2X33JisVps9Rs3_oCLaW_3Z3A
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.OnItemClickListener
            public final void onItemClicked(Object obj) {
                LanguagePrimerController.this.lambda$onCreateView$1$LanguagePrimerController((LanguageListItem) obj);
            }
        }, this.onItemSelectionDetected);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.availableLanguages);
        recyclerView.setAdapter(this.availableLanguagesListAdapter);
        recyclerView.addItemDecoration(new DividerDecoration(getContext(), R.color.voice_ui_od_list_item_divider, this.availableLanguagesListAdapter));
        this.recommendedLanguagesListAdapter = new LanguageListAdapter(new OnItemClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.language.-$$Lambda$LanguagePrimerController$BKPIAKy3yREJEYgyz4vNqrmXYV4
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.OnItemClickListener
            public final void onItemClicked(Object obj) {
                LanguagePrimerController.this.lambda$onCreateView$2$LanguagePrimerController((LanguageListItem) obj);
            }
        }, this.onItemSelectionDetected);
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(R.id.recommendedLanguages);
        recyclerView2.setAdapter(this.recommendedLanguagesListAdapter);
        recyclerView2.addItemDecoration(new DividerDecoration(getContext(), R.color.voice_ui_od_list_item_divider, this.recommendedLanguagesListAdapter));
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

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setAvailableLanguages(List<LanguageListItem> list) {
        this.availableLanguagesListAdapter.set(list);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setAvailableLanguagesHeaderText(CharSequence charSequence) {
        this.availableLanguageHeaderTextView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setAvailableSectionVisible(boolean z) {
        this.availableLocalesContainer.setVisibility(z ? 0 : 8);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setContinueButtonEnabled(boolean z) {
        this.continueButtonView.setEnabled(z);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setContinueButtonText(CharSequence charSequence) {
        this.continueButtonView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setHeadingText(CharSequence charSequence) {
        this.headingView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setRecommendedLanguages(List<LanguageListItem> list) {
        this.recommendedLanguagesListAdapter.set(list);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setRecommendedLanguagesHeaderText(CharSequence charSequence) {
        this.recommendedLanguageHeaderTextView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setRecommendedSectionVisible(boolean z) {
        this.recommendedLocalesContainer.setVisibility(z ? 0 : 8);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setSectionDescription(CharSequence charSequence) {
        this.sectionDescriptionTextView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.View
    public void setTitle(CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }
}
