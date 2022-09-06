package com.amazon.alexa.voice.ui.onedesign.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class MoviesController extends ViewController implements MoviesContract.View, MovieCloseActionDelegate {
    private static final String EXTRA_CARD = "card";
    private MoviesContract.Presenter presenter;

    public static MoviesController create(MoviesCard moviesCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", moviesCard);
        MoviesController moviesController = new MoviesController();
        moviesController.setArguments(bundle);
        return moviesController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MovieCloseActionDelegate
    public void closeClicked() {
        this.presenter.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark_Movie);
        Bundle arguments = getArguments();
        MoviesCard moviesCard = arguments != null ? (MoviesCard) arguments.getParcelable("card") : null;
        if (moviesCard != null) {
            MoviesInteractor moviesInteractor = new MoviesInteractor(new MoviesMediator(this, moviesCard));
            Component component = getComponent();
            component.remove(MovieCloseActionDelegate.class);
            component.provide((Class<? extends Class>) MovieCloseActionDelegate.class, (Class) this).register();
            this.presenter = new MoviesPresenter(moviesInteractor);
            return;
        }
        throw new IllegalStateException("Use MoviesController.create(MoviesCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_movies, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.cardLayout);
        SpeechControlView speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        ViewUtils.addStatusBarHeightAsTopPadding(findViewById, speechControlView);
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.MoviesController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                MoviesController.this.presenter.dismiss();
            }
        });
        speechControlView.setComponent(getComponent());
        getChildRouter(MoviesContract.ROUTER).attach((ViewGroup) inflate.findViewById(R.id.container));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroy() {
        ComponentUtils.removeDependency(getComponent(), LocalDelegate.class, this);
    }

    @Override // com.amazon.regulator.ViewController
    protected boolean onHandleBack() {
        Router childRouter = getChildRouter(MoviesContract.ROUTER);
        return childRouter.getBackStackSize() > 1 && childRouter.handleBack();
    }
}
