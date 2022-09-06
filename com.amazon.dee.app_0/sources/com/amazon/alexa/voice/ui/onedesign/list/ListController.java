package com.amazon.alexa.voice.ui.onedesign.list;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.list.ListContract;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.DrawableUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.voice.ui.onedesign.widget.background.CardLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class ListController extends ViewController implements ListContract.View {
    private static final String EXTRA_CARD = "card";
    private ListAdapter contentAdapter;
    private ListType listType;
    private Button manageButton;
    private ListContract.Presenter presenter;
    private TextView subTitleView;
    private TextView titleView;

    /* renamed from: com.amazon.alexa.voice.ui.onedesign.list.ListController$4  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$voice$ui$onedesign$list$ListType = new int[ListType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$list$ListType[ListType.TODO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$list$ListType[ListType.SHOPPING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$list$ListType[ListType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static ListController create(ListCard listCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", listCard);
        ListController listController = new ListController();
        listController.setArguments(bundle);
        return listController;
    }

    private void setBackground(CardLayout cardLayout) {
        int ordinal = this.listType.ordinal();
        if (ordinal == 0) {
            cardLayout.setBackgroundColorAndDrawable(R.color.voice_ui_od_bg_list_shopping, R.drawable.ic_od_bg_shopping);
        } else if (ordinal == 1) {
            cardLayout.setBackgroundColorAndDrawable(R.color.voice_ui_od_bg_list_todo, R.drawable.ic_od_bg_todo);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got unexpected list type: ");
            outline107.append(this.listType);
            throw new IllegalStateException(outline107.toString());
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.View
    public void add(ListItemModel listItemModel) {
        this.contentAdapter.add(listItemModel);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$ListController(View view) {
        this.presenter.manageButtonClicked();
    }

    public /* synthetic */ void lambda$onCreateView$1$ListController(View view) {
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
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark);
        ListCard listCard = (ListCard) getArguments().getParcelable("card");
        if (listCard != null) {
            this.listType = ListType.from(listCard.getListType());
            ListInteractor listInteractor = new ListInteractor(listCard, new ListMediator(this));
            Component component = getComponent();
            this.presenter = new ListPresenter(this, listInteractor, new AndroidResources(getContext(), ComponentUtils.getLocale(component)), (CardMetricsInteractor) component.get(CardMetricsInteractor.class));
            return;
        }
        throw new IllegalStateException("Use ListController.create(ListCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_list, viewGroup, false);
        CardLayout cardLayout = (CardLayout) inflate.findViewById(R.id.cardLayout);
        SpeechControlView speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        ViewUtils.addStatusBarHeightAsTopPadding(cardLayout, speechControlView);
        setBackground(cardLayout);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.subTitleView = (TextView) inflate.findViewById(R.id.subTitle);
        this.manageButton = (Button) inflate.findViewById(R.id.manage);
        this.manageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.list.-$$Lambda$ListController$CwYrCTn6He50wZAlDcG5_UXfOqw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ListController.this.lambda$onCreateView$0$ListController(view);
            }
        });
        HeaderUtils.applyFontStyles(this.titleView, this.subTitleView);
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.list.-$$Lambda$ListController$xPYxqu9rTit7roi1w7pPwaXwFI4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ListController.this.lambda$onCreateView$1$ListController(view);
            }
        });
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.list.ListController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                ListController.this.presenter.dismiss();
            }
        });
        speechControlView.setComponent(getComponent());
        this.contentAdapter = new ListAdapter();
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.content);
        recyclerView.setAdapter(this.contentAdapter);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.voice.ui.onedesign.list.ListController.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                ListController.this.presenter.interacted();
            }
        });
        final Drawable createSeparatorDrawable = DrawableUtils.createSeparatorDrawable(inflate.getContext());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.alexa.voice.ui.onedesign.list.ListController.3
            private boolean shouldDrawSeparator(View view, RecyclerView recyclerView2, RecyclerView.State state) {
                int childAdapterPosition = recyclerView2.getChildAdapterPosition(view);
                return childAdapterPosition >= 0 && childAdapterPosition < state.getItemCount() - 1;
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
        this.presenter.viewDestroyed();
        this.contentAdapter = null;
        this.titleView = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.View
    public void setSubTitle(CharSequence charSequence) {
        this.subTitleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.View
    public void setTitle(CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }
}
