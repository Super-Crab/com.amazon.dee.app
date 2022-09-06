package com.amazon.alexa.voice.ui.onedesign.local;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.local.LocalContract;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class LocalController extends ViewController implements LocalContract.View, LocalDelegate {
    private static final String EXTRA_CARD = "card";
    private LocalContract.Presenter presenter;

    public static LocalController create(LocalCard localCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", localCard);
        LocalController localController = new LocalController();
        localController.setArguments(bundle);
        return localController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate
    public void close() {
        this.presenter.closeClicked();
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
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark_Local);
        Bundle arguments = getArguments();
        LocalCard localCard = arguments != null ? (LocalCard) arguments.getParcelable("card") : null;
        if (localCard != null) {
            this.presenter = new LocalPresenter(new LocalInteractor(new LocalMediator(this, localCard)));
            Component component = getComponent();
            component.remove(LocalDelegate.class);
            component.provide((Class<? extends Class>) LocalDelegate.class, (Class) this).register();
            return;
        }
        throw new IllegalStateException("Use LocalController.create(LocalCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_local, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.cardLayout);
        SpeechControlView speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        ViewUtils.addStatusBarHeightAsTopPadding(findViewById, speechControlView);
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.LocalController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                LocalController.this.presenter.dismiss();
            }
        });
        speechControlView.setComponent(getComponent());
        getChildRouter("local").attach((ViewGroup) inflate.findViewById(R.id.container));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroy() {
        ComponentUtils.removeDependency(getComponent(), LocalDelegate.class, this);
    }

    @Override // com.amazon.regulator.ViewController
    protected boolean onHandleBack() {
        Router childRouter = getChildRouter("local");
        return childRouter.getBackStackSize() > 1 && childRouter.handleBack();
    }
}
