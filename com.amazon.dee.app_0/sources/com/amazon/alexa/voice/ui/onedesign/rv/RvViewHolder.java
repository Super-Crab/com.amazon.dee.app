package com.amazon.alexa.voice.ui.onedesign.rv;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public abstract class RvViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "RvViewHolder";
    protected final RvActionHandler actionHandler;
    protected Object currentItem;
    protected final RvDataProvider dataProvider;

    public RvViewHolder(@NonNull View view, @NonNull RvActionHandler rvActionHandler, @NonNull RvDataProvider rvDataProvider) {
        super(view);
        this.actionHandler = rvActionHandler;
        this.dataProvider = rvDataProvider;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Constructed view-holder: ");
        outline107.append(getClass().getSimpleName());
        outline107.toString();
    }

    public void bind(Object obj) {
        this.currentItem = obj;
    }

    protected final View findViewById(@IdRes int i) {
        return this.itemView.findViewById(i);
    }

    protected int getColor(@ColorRes int i) {
        return ContextCompat.getColor(getContext(), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getContext() {
        return this.itemView.getContext();
    }

    public Object getCurrentItem() {
        return this.currentItem;
    }

    protected final Resources getResources() {
        return getContext().getResources();
    }

    protected String getString(@StringRes int i) {
        return getResources().getString(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleAction(int i, View view) {
        this.actionHandler.handle(i, getAdapterPosition(), this.currentItem, view, null);
    }

    public void onViewAttachedToWindow() {
    }

    public void onViewDetachedFromWindow() {
    }

    public void onViewRecycled() {
    }
}
