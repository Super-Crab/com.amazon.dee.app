package com.amazon.alexa.voice.ui.onedesign.shopping;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.DrawableUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class ShoppingController extends ViewController implements ShoppingContract.View {
    private static final String EXTRA_CARD = "card";
    private ShoppingAdapter contentAdapter;
    private ShoppingContract.Presenter presenter;

    public static ShoppingController create(ShoppingCard shoppingCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", shoppingCard);
        ShoppingController shoppingController = new ShoppingController();
        shoppingController.setArguments(bundle);
        return shoppingController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.View
    public void add(Object obj) {
        this.contentAdapter.add(obj);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$ShoppingController(View view) {
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
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Light_Shopping);
        ShoppingCard shoppingCard = (ShoppingCard) getArguments().getParcelable("card");
        if (shoppingCard != null) {
            ShoppingMediator shoppingMediator = new ShoppingMediator(this);
            Component component = getComponent();
            this.presenter = new ShoppingPresenter(this, new ShoppingInteractor(shoppingCard, component.isRegistered(MarketplaceAuthority.class) ? ((MarketplaceAuthority) component.get(MarketplaceAuthority.class)).getMarketplace() : "US", shoppingMediator), (CardMetricsInteractor) component.get(CardMetricsInteractor.class), new AndroidResources(getContext(), ComponentUtils.getLocale(component)));
            return;
        }
        throw new IllegalStateException("Use ShoppingController.create(ShoppingCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_shopping, viewGroup, false);
        SpeechControlView speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate.findViewById(R.id.cardLayout), speechControlView);
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                ShoppingController.this.presenter.dismiss();
            }
        });
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.shopping.-$$Lambda$ShoppingController$oMsMVFb2JiY6grcsB5GZEEh6lCM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShoppingController.this.lambda$onCreateView$0$ShoppingController(view);
            }
        });
        Component component = getComponent();
        speechControlView.setComponent(component);
        this.contentAdapter = new ShoppingAdapter(ComponentUtils.getLocale(component), (CardMetricsInteractor) component.get(CardMetricsInteractor.class));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.content);
        recyclerView.setAdapter(this.contentAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingController.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                ShoppingController.this.presenter.interacted();
            }
        });
        final Drawable createSeparatorDrawable = DrawableUtils.createSeparatorDrawable(inflate.getContext());
        final int dimensionPixelSize = inflate.getResources().getDimensionPixelSize(R.dimen.voice_ui_od_shopping_item_margin_top_bottom);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingController.3
            private boolean shouldDrawSeparator(View view, RecyclerView recyclerView2, RecyclerView.State state) {
                int childAdapterPosition = recyclerView2.getChildAdapterPosition(view);
                return childAdapterPosition > 0 && childAdapterPosition <= state.getItemCount() - 1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView2, RecyclerView.State state) {
                if (recyclerView2.getChildAdapterPosition(view) == 0) {
                    rect.set(0, 0, 0, dimensionPixelSize);
                } else {
                    int i = dimensionPixelSize;
                    rect.set(0, i, 0, i);
                }
                if (shouldDrawSeparator(view, recyclerView2, state)) {
                    rect.top = createSeparatorDrawable.getIntrinsicHeight() + rect.top;
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void onDraw(Canvas canvas, RecyclerView recyclerView2, RecyclerView.State state) {
                int paddingStart = recyclerView2.getPaddingStart();
                int width = recyclerView2.getWidth() - recyclerView2.getPaddingEnd();
                int childCount = recyclerView2.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = recyclerView2.getChildAt(i);
                    if (shouldDrawSeparator(childAt, recyclerView2, state)) {
                        int top = childAt.getTop() - dimensionPixelSize;
                        createSeparatorDrawable.setBounds(childAt.getPaddingStart() + paddingStart, top, width - childAt.getPaddingEnd(), createSeparatorDrawable.getIntrinsicHeight() + top);
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
        this.presenter.viewDestroyed();
        this.contentAdapter = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }
}
