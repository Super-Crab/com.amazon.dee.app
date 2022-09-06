package com.amazon.alexa.voice.ui.onedesign.rv;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class RvItemsSpecs<M> {
    public static final String TAG = "RvItemsSpecs";
    private final Map<Integer, Class<? extends RvViewHolder>> viewHolderClassByViewType = new HashMap();

    public void add(M m) {
        Class<? extends RvViewHolder> viewHolderClassByItem = getViewHolderClassByItem(m);
        this.viewHolderClassByViewType.put(Integer.valueOf(viewHolderClassByItem.hashCode()), viewHolderClassByItem);
    }

    public void addAll(List<M> list) {
        for (M m : list) {
            add(m);
        }
    }

    public void clear() {
        this.viewHolderClassByViewType.clear();
    }

    @VisibleForTesting
    Class<? extends RvViewHolder> getViewHolderClassByItem(Object obj) {
        ListItem listItem = (ListItem) obj.getClass().getAnnotation(ListItem.class);
        if (listItem != null) {
            return listItem.viewHolderClass();
        }
        if (obj instanceof Listable) {
            return ((Listable) obj).getViewHolderClass();
        }
        Log.e(TAG, "Couldn't find ViewHolder assigned to this item.");
        return DefaultViewHolder.class;
    }

    public Class<? extends RvViewHolder> getViewHolderClassByViewType(int i) {
        return this.viewHolderClassByViewType.get(Integer.valueOf(i));
    }

    public int getViewTypeByItem(Object obj) {
        return getViewHolderClassByItem(obj).hashCode();
    }
}
