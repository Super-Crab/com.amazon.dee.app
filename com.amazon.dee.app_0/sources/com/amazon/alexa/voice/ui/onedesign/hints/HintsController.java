package com.amazon.alexa.voice.ui.onedesign.hints;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.hints.HintsContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.StatusBar;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes11.dex */
public final class HintsController extends ViewController implements HintsContract.View {
    private TextView doneButtonView;
    private HintsContract.Presenter presenter;
    private TextView shoppingHintView;
    private TextView sunsetHintView;
    private TextView titleView;
    private TextView weatherHintView;

    public static HintsController create() {
        return new HintsController();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.View
    public void floodBackgroundToStatusBar() {
        StatusBar.tryFlood(getComponent(), getContext());
    }

    public /* synthetic */ void lambda$onCreateView$0$HintsController(View view) {
        this.presenter.doneClicked();
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
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark);
        this.presenter = new HintsPresenter(this, new HintsInteractor((OnHintsDismissedListener) getComponent().get(OnHintsDismissedListener.class), new HintsMediator(this)), new AndroidResources(getContext(), ComponentUtils.getLocale(getComponent())));
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        Preconditions.nonNull(layoutInflater, "inflater argument must be non-null.");
        Preconditions.nonNull(viewGroup, "container argument must be non-null.");
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_hints, viewGroup, false);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.weatherHintView = (TextView) inflate.findViewById(R.id.weatherText);
        this.shoppingHintView = (TextView) inflate.findViewById(R.id.shoppingText);
        this.sunsetHintView = (TextView) inflate.findViewById(R.id.sunsetText);
        this.doneButtonView = (TextView) inflate.findViewById(R.id.done);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, (TextView) inflate.findViewById(R.id.title));
        FontUtils.apply(Font.AMAZON_BOOKERLY_REGULAR_ITALIC, this.weatherHintView, this.shoppingHintView, this.sunsetHintView);
        FontUtils.apply(Font.AMAZON_EMBER_MEDIUM, this.doneButtonView);
        this.doneButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.hints.-$$Lambda$HintsController$sRK-TKRqydbO725xL1ypBvKVIz4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HintsController.this.lambda$onCreateView$0$HintsController(view);
            }
        });
        return inflate;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.View
    public void setDoneButtonText(@NonNull CharSequence charSequence) {
        Preconditions.nonNull(charSequence, "doneButtonText must be non-null.");
        this.doneButtonView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.View
    public void setShoppingHint(@NonNull CharSequence charSequence) {
        Preconditions.nonNull(charSequence, "shoppingHintText must be non-null.");
        this.shoppingHintView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.View
    public void setSunsetHint(@NonNull CharSequence charSequence) {
        Preconditions.nonNull(charSequence, "sunsetHintText must be non-null.");
        this.sunsetHintView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.View
    public void setTitle(@NonNull CharSequence charSequence) {
        Preconditions.nonNull(charSequence, "title must be non-null.");
        this.titleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.View
    public void setWeatherHint(@NonNull CharSequence charSequence) {
        Preconditions.nonNull(charSequence, "weatherHintText must be non-null.");
        this.weatherHintView.setText(charSequence);
    }
}
