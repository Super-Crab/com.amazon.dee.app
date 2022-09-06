package com.amazon.alexa.redesign.view.homeFeed;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.view.templates.TemplateType;
/* loaded from: classes10.dex */
public class HomeLinearLayout extends LinearLayoutManager {
    private static final double HEADER_SNAP_POINT = 0.5d;
    private static final double VOX_SNAP_POINT = 0.855d;
    public static boolean firstRenderComplete = false;
    public static boolean firstRenderCompleteWithLogout = false;
    public static boolean homecardsShownOnce = false;
    private HomeAdapter adapter;
    private RelativeLayout header;
    private int mostRecentDy;
    private HomeContract.Presenter presenter;
    private HomeContract.View view;

    public HomeLinearLayout(Context context, HomeContract.Presenter presenter, HomeAdapter homeAdapter, HomeContract.View view, RelativeLayout relativeLayout) {
        super(context);
        this.mostRecentDy = 0;
        this.presenter = presenter;
        this.adapter = homeAdapter;
        this.view = view;
        this.header = relativeLayout;
    }

    public static double percentOfVoxHeaderTranslation(double d, LinearLayoutManager linearLayoutManager, View view) {
        double height = linearLayoutManager.findViewByPosition(0).getHeight();
        return 1.0d - (((height * d) - view.getHeight()) / ((0.71d * height) - view.getHeight()));
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public int findFirstVisibleItemPosition() {
        int findFirstVisibleItemPosition = super.findFirstVisibleItemPosition();
        View findViewByPosition = findViewByPosition(findFirstVisibleItemPosition);
        if (findViewByPosition != null) {
            Rect rect = new Rect();
            findViewByPosition.getGlobalVisibleRect(rect);
            return (rect.height() <= this.header.getHeight() || rect.bottom <= 0) ? findFirstVisibleItemPosition + 1 : findFirstVisibleItemPosition;
        }
        return findFirstVisibleItemPosition;
    }

    public View findSnapView() {
        View findViewByPosition;
        if (findFirstCompletelyVisibleItemPosition() != 0 && super.findFirstVisibleItemPosition() != 1 && (findViewByPosition = findViewByPosition(0)) != null) {
            Rect rect = new Rect();
            findViewByPosition.getGlobalVisibleRect(rect);
            if (rect.bottom >= 0 && rect.height() > this.header.getHeight()) {
                double height = (rect.height() * 1.0d) / findViewByPosition.getHeight();
                if (this.mostRecentDy > 0) {
                    return height > VOX_SNAP_POINT ? findViewByPosition : findViewByPosition(1);
                }
                double percentOfVoxHeaderTranslation = percentOfVoxHeaderTranslation(height, this, this.header);
                if (this.mostRecentDy >= 0) {
                    return findViewByPosition(1);
                }
                return percentOfVoxHeaderTranslation > 0.5d ? findViewByPosition(1) : findViewByPosition;
            }
        }
        return null;
    }

    public int getSnapHeight(View view) {
        Rect rect = new Rect();
        View findViewByPosition = findViewByPosition(0);
        if (findViewByPosition != null) {
            findViewByPosition.getGlobalVisibleRect(rect);
            if (view.equals(findViewByPosition)) {
                return (findViewByPosition.getHeight() - rect.height()) * (-1);
            }
            return rect.height() - this.header.getHeight();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            Log.e("HomeLinearLayout", e.getMessage());
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = findLastVisibleItemPosition();
        int itemCount = getItemCount();
        TemplateType from = (itemCount == 0 || findFirstVisibleItemPosition == -1) ? null : TemplateType.from(this.adapter.getItemViewType(findFirstVisibleItemPosition));
        boolean z = from != null && from.equals(TemplateType.VoxIngress) && itemCount >= 1;
        if (!firstRenderComplete && z) {
            firstRenderComplete = true;
            this.presenter.onVoxIngressFinishedLoading(true);
        }
        if (!firstRenderCompleteWithLogout && z) {
            firstRenderCompleteWithLogout = true;
            this.presenter.onVoxIngressFinishedLoadingWithLogout(true);
        }
        if (itemCount > 1) {
            if (homecardsShownOnce) {
                this.presenter.onHomeCardsViewFinishedLoading(false);
            } else {
                homecardsShownOnce = true;
                HomeContract.View view = this.view;
                if (view != null) {
                    view.recordCardsSeenOnColdStart(findFirstVisibleItemPosition, findLastVisibleItemPosition);
                }
            }
            this.presenter.onLayoutCompleted();
        }
    }

    public void resetFirstRenderCompleteOnLogout() {
        firstRenderCompleteWithLogout = false;
    }

    public void setScrollDirection(int i) {
        this.mostRecentDy = i;
    }
}
