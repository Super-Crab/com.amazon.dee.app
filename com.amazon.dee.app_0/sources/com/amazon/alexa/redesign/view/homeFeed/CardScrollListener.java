package com.amazon.alexa.redesign.view.homeFeed;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes10.dex */
public class CardScrollListener extends RecyclerView.OnScrollListener {
    public static boolean isScrolling = false;
    public static boolean offlineMode = false;
    public static String returnToCardId = "";
    public static int returnToLastVisibleCard = -1;
    public static int returnToOffset = -1;
    public static int returnToPos = -1;
    private final View headerExcludingAlert;
    private final RelativeLayout headerVoxIconTextWrapper;
    private final HomeAdapter homeAdapter;
    private int homeBackgroundColor;
    private final RelativeLayout homeHeader;
    private final HomeContract.View homeRedesignViewController;
    private final LinearLayoutManager layoutManager;
    private final HomeContract.Presenter presenter;
    private final ImageView twaKeyboard;

    public CardScrollListener(LinearLayoutManager linearLayoutManager, HomeAdapter homeAdapter, HomeContract.View view, HomeContract.Presenter presenter, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, Context context, boolean z) {
        this.layoutManager = linearLayoutManager;
        this.homeAdapter = homeAdapter;
        this.homeRedesignViewController = view;
        this.presenter = presenter;
        this.headerVoxIconTextWrapper = relativeLayout;
        this.homeHeader = relativeLayout2;
        this.headerExcludingAlert = relativeLayout2.findViewById(R.id.vox_placeholder);
        this.twaKeyboard = (ImageView) relativeLayout2.findViewById(R.id.home_header_twa_keyboard);
        offlineMode = z;
        this.homeBackgroundColor = ThemeUtil.getColorFromAttribute(context, R.attr.mosaicBackground);
    }

    private double getPercentOfVoxOnScreen(Rect rect, Rect rect2) {
        return (rect2.bottom - rect.top) / this.layoutManager.findViewByPosition(0).getHeight();
    }

    private void onFirstCardNotVox() {
        if (offlineMode) {
            this.headerVoxIconTextWrapper.setAlpha(0.3f);
        } else {
            this.headerVoxIconTextWrapper.setAlpha(1.0f);
        }
        this.headerVoxIconTextWrapper.setY((float) ((this.headerExcludingAlert.getHeight() / 2.0d) - (this.headerVoxIconTextWrapper.getHeight() / 2.0d)));
        this.homeHeader.setBackgroundColor(this.homeBackgroundColor);
        this.twaKeyboard.setAlpha(0.0f);
    }

    private void onFirstCardVox(RecyclerView recyclerView) {
        Rect rect = new Rect();
        recyclerView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        View findViewByPosition = this.layoutManager.findViewByPosition(0);
        if (findViewByPosition != null) {
            findViewByPosition.getGlobalVisibleRect(rect2);
            double percentOfVoxOnScreen = getPercentOfVoxOnScreen(rect, rect2);
            View view = recyclerView.findViewHolderForAdapterPosition(0).itemView;
            if (percentOfVoxOnScreen >= 0.71d && percentOfVoxOnScreen <= 1.0d) {
                showVoxMakeHeaderInvisible(percentOfVoxOnScreen, view);
            } else if (percentOfVoxOnScreen >= 0.71d || percentOfVoxOnScreen <= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            } else {
                showHeaderMakeVoxInvisible(percentOfVoxOnScreen, view);
            }
        }
    }

    private void onScrolledWhenFos(RecyclerView recyclerView, int i) {
        if (i == 0) {
            Rect rect = new Rect();
            recyclerView.getGlobalVisibleRect(rect);
            Rect rect2 = new Rect();
            View findViewByPosition = this.layoutManager.findViewByPosition(0);
            if (findViewByPosition == null) {
                return;
            }
            findViewByPosition.getGlobalVisibleRect(rect2);
            double percentOfVoxOnScreen = getPercentOfVoxOnScreen(rect, rect2);
            View view = recyclerView.findViewHolderForAdapterPosition(0).itemView;
            if (percentOfVoxOnScreen >= 0.6666d && percentOfVoxOnScreen <= 1.0d) {
                view.setAlpha((float) ((percentOfVoxOnScreen - 0.6666d) / 0.33340000000000003d));
                this.homeHeader.setBackgroundColor(0);
                return;
            } else if (percentOfVoxOnScreen >= 0.6666d || percentOfVoxOnScreen <= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                return;
            } else {
                this.homeHeader.setBackgroundColor(this.homeBackgroundColor);
                return;
            }
        }
        this.homeHeader.setBackgroundColor(this.homeBackgroundColor);
    }

    private void showHeaderMakeVoxInvisible(double d, View view) {
        double percentOfVoxHeaderTranslation = HomeLinearLayout.percentOfVoxHeaderTranslation(d, this.layoutManager, this.homeHeader);
        double d2 = offlineMode ? 0.3d * percentOfVoxHeaderTranslation : percentOfVoxHeaderTranslation;
        view.setAlpha(0.0f);
        this.twaKeyboard.setAlpha(0.0f);
        this.headerVoxIconTextWrapper.setAlpha((float) d2);
        this.headerVoxIconTextWrapper.setY((float) (((this.headerExcludingAlert.getHeight() / 2.0d) - (this.headerVoxIconTextWrapper.getHeight() / 2.0d)) * percentOfVoxHeaderTranslation));
        this.homeHeader.setBackgroundColor(this.homeBackgroundColor);
    }

    private void showVoxMakeHeaderInvisible(double d, View view) {
        double d2 = (d - 0.71d) / 0.29000000000000004d;
        if (offlineMode) {
            d2 *= 0.3d;
        }
        float f = (float) d2;
        view.setAlpha(f);
        this.twaKeyboard.setAlpha(f);
        this.headerVoxIconTextWrapper.setAlpha(0.0f);
        this.homeHeader.setBackgroundColor(0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0) {
            isScrolling = false;
            int findFirstVisibleItemPosition = this.layoutManager.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = this.layoutManager.findLastVisibleItemPosition();
            View findViewByPosition = this.layoutManager.findViewByPosition(findFirstVisibleItemPosition);
            returnToOffset = findViewByPosition != null ? findViewByPosition.getTop() : -1;
            RecyclerViewItem viewItem = this.homeAdapter.getViewItem(findFirstVisibleItemPosition);
            if (viewItem != null) {
                this.presenter.onScrolledToViewItem(viewItem, findFirstVisibleItemPosition, findLastVisibleItemPosition, this.homeRedesignViewController.getTotalViewItems());
                returnToCardId = viewItem.getCardId();
            }
            returnToPos = findFirstVisibleItemPosition;
            returnToLastVisibleCard = findLastVisibleItemPosition;
            if (findFirstVisibleItemPosition == 0) {
                return;
            }
            this.twaKeyboard.setAlpha(0.0f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        int findFirstVisibleItemPosition = this.layoutManager.findFirstVisibleItemPosition();
        isScrolling = true;
        if (!TemplateHelperUtil.isFireOS()) {
            if (findFirstVisibleItemPosition == 0) {
                onFirstCardVox(recyclerView);
            } else {
                onFirstCardNotVox();
            }
            ((HomeLinearLayout) this.layoutManager).setScrollDirection(i2);
        } else {
            onScrolledWhenFos(recyclerView, findFirstVisibleItemPosition);
        }
        this.homeRedesignViewController.revokeSwipeExperience();
    }
}
