package com.amazon.alexa.voice.ui.onedesign.sports.update;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.DrawableUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.voice.ui.onedesign.widget.OnCloseClickListener;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
import com.amazon.regulator.ViewController;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class SportsUpdateController extends ViewController implements SportsUpdateContract.View {
    private static final String EXTRA_CARD = "card";
    private SportsUpdateAdapter contentAdapter;
    private SportsUpdateContract.Presenter presenter;

    public static SportsUpdateController create(SportsUpdateCard sportsUpdateCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", sportsUpdateCard);
        SportsUpdateController sportsUpdateController = new SportsUpdateController();
        sportsUpdateController.setArguments(bundle);
        return sportsUpdateController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.View
    public void add(Object obj) {
        this.contentAdapter.add(obj);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.View
    public CharSequence appendTitle(CharSequence charSequence) {
        return charSequence;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark);
        SportsUpdateCard sportsUpdateCard = (SportsUpdateCard) getArguments().getParcelable("card");
        if (sportsUpdateCard != null) {
            this.presenter = new SportsUpdatePresenter(this, new SportsUpdateInteractor(sportsUpdateCard, new SportsUpdateMediator(this)));
            return;
        }
        throw new IllegalStateException("Use SportsUpdateController.create(SportsUpdateCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_sports_update, viewGroup, false);
        SpeechControlView speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.content);
        ViewUtils.addStatusBarHeightAsTopPadding(recyclerView, speechControlView);
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                SportsUpdateController.this.presenter.dismiss();
            }
        });
        Component component = getComponent();
        speechControlView.setComponent(component);
        Locale locale = ComponentUtils.getLocale(component);
        final SportsUpdateContract.Presenter presenter = this.presenter;
        presenter.getClass();
        this.contentAdapter = new SportsUpdateAdapter(new OnCloseClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.update.-$$Lambda$ou1MUygGKQm97kJmHKB6OWQPlIo
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.OnCloseClickListener
            public final void onCloseClicked() {
                SportsUpdateContract.Presenter.this.closeClicked();
            }
        }, locale);
        recyclerView.setAdapter(this.contentAdapter);
        final Drawable createSeparatorDrawable = DrawableUtils.createSeparatorDrawable(inflate.getContext());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateController.2
            private boolean shouldDrawSeparator(View view, RecyclerView recyclerView2, RecyclerView.State state) {
                int childAdapterPosition = recyclerView2.getChildAdapterPosition(view);
                return childAdapterPosition > 0 && childAdapterPosition < state.getItemCount() - 1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView2, RecyclerView.State state) {
                rect.set(0, 0, 0, 0);
                if (shouldDrawSeparator(view, recyclerView2, state)) {
                    rect.bottom = createSeparatorDrawable.getIntrinsicHeight() + rect.bottom;
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void onDraw(Canvas canvas, RecyclerView recyclerView2, RecyclerView.State state) {
                int paddingStart = recyclerView2.getPaddingStart();
                int width = recyclerView2.getWidth() - recyclerView2.getPaddingEnd();
                int childCount = recyclerView2.getChildCount();
                for (int i = 0; i < childCount - 1; i++) {
                    View childAt = recyclerView2.getChildAt(i);
                    if (shouldDrawSeparator(childAt, recyclerView2, state)) {
                        int bottom = childAt.getBottom();
                        createSeparatorDrawable.setBounds(childAt.getPaddingStart() + paddingStart, bottom, width - childAt.getPaddingEnd(), createSeparatorDrawable.getIntrinsicHeight() + bottom);
                        createSeparatorDrawable.draw(canvas);
                    }
                }
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.contentAdapter = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }
}
