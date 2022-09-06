package com.amazon.alexa.fitness.view.workoutTab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.routing.api.RouteParameter;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: RecyclerViewAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0011H\u0016J\u0018\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0011H\u0016J\u0010\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/RecyclerViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "scrollListener", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "(Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;)V", "getAfitsDataHelper", "()Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "setAfitsDataHelper", "(Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;)V", "getScrollListener", "()Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "setScrollListener", "(Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;)V", "getItemCount", "", "getItemViewType", ViewProps.POSITION, "onAttachedToRecyclerView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "viewHolder", "onCreateViewHolder", RouteParameter.PARENT, "Landroid/view/ViewGroup;", "viewType", "onDetachedFromRecyclerView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NotNull
    private AfitsDataHelper afitsDataHelper;
    @NotNull
    private RecyclerView.OnScrollListener scrollListener;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessListType.values().length];

        static {
            $EnumSwitchMapping$0[FitnessListType.LIST_ITEM.ordinal()] = 1;
            $EnumSwitchMapping$0[FitnessListType.HEADER.ordinal()] = 2;
            $EnumSwitchMapping$0[FitnessListType.WEEKLY_VIEW.ordinal()] = 3;
            $EnumSwitchMapping$0[FitnessListType.LOADING.ordinal()] = 4;
        }
    }

    public RecyclerViewAdapter(@NotNull AfitsDataHelper afitsDataHelper, @NotNull RecyclerView.OnScrollListener scrollListener) {
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "afitsDataHelper");
        Intrinsics.checkParameterIsNotNull(scrollListener, "scrollListener");
        this.afitsDataHelper = afitsDataHelper;
        this.scrollListener = scrollListener;
    }

    @NotNull
    public final AfitsDataHelper getAfitsDataHelper() {
        return this.afitsDataHelper;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.afitsDataHelper.getWorkoutHistoryList().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.afitsDataHelper.getWorkoutHistoryList().get(i).getItemType().getValue();
    }

    @NotNull
    public final RecyclerView.OnScrollListener getScrollListener() {
        return this.scrollListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(this.scrollListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        if (viewHolder instanceof ListViewHolder) {
            ListViewHolder listViewHolder = (ListViewHolder) viewHolder;
            listViewHolder.updateListItemView(listViewHolder, i);
        } else if (viewHolder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            headerViewHolder.updateHeaderView(headerViewHolder, i);
        } else if (!(viewHolder instanceof WeeklyViewHolder)) {
        } else {
            ((WeeklyViewHolder) viewHolder).updateWeeklySummaryView();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        int i2 = WhenMappings.$EnumSwitchMapping$0[FitnessListType.Companion.fromInt(i).ordinal()];
        if (i2 == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_layout, parent, false);
            FullScreenUtil.Companion.setFullScreenView(view);
            Intrinsics.checkExpressionValueIsNotNull(view, "view");
            return new ListViewHolder(view, this.afitsDataHelper);
        } else if (i2 == 2) {
            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_header_layout, parent, false);
            FullScreenUtil.Companion.setFullScreenView(view2);
            Intrinsics.checkExpressionValueIsNotNull(view2, "view");
            return new HeaderViewHolder(view2, this.afitsDataHelper);
        } else if (i2 == 3) {
            View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weekly_layout, parent, false);
            FullScreenUtil.Companion.setFullScreenView(view3);
            Intrinsics.checkExpressionValueIsNotNull(view3, "view");
            return new WeeklyViewHolder(view3, this.afitsDataHelper);
        } else if (i2 != 4) {
            throw new NoWhenBranchMatchedException();
        } else {
            View view4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_loading_layout, parent, false);
            FullScreenUtil.Companion.setFullScreenView(view4);
            Intrinsics.checkExpressionValueIsNotNull(view4, "view");
            return new LoadingViewHolder(view4);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(@NotNull RecyclerView recyclerView) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        recyclerView.removeOnScrollListener(this.scrollListener);
    }

    public final void setAfitsDataHelper(@NotNull AfitsDataHelper afitsDataHelper) {
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "<set-?>");
        this.afitsDataHelper = afitsDataHelper;
    }

    public final void setScrollListener(@NotNull RecyclerView.OnScrollListener onScrollListener) {
        Intrinsics.checkParameterIsNotNull(onScrollListener, "<set-?>");
        this.scrollListener = onScrollListener;
    }
}
