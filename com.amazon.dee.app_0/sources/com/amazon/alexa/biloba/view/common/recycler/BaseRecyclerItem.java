package com.amazon.alexa.biloba.view.common.recycler;

import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes6.dex */
public class BaseRecyclerItem<T> {
    public static final int NO_VARIABLE = -1;
    private T data;
    private int layoutId;
    private int variableId;

    public BaseRecyclerItem(@NonNull T t, int i, int i2) {
        this.data = t;
        this.layoutId = i;
        this.variableId = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseRecyclerItem baseRecyclerItem = (BaseRecyclerItem) obj;
        return this.layoutId == baseRecyclerItem.layoutId && this.variableId == baseRecyclerItem.variableId && this.data.equals(baseRecyclerItem.data);
    }

    public T getData() {
        return this.data;
    }

    public int getLayoutId() {
        return this.layoutId;
    }

    public int getVariableId() {
        return this.variableId;
    }

    public int hashCode() {
        return Objects.hash(this.data, Integer.valueOf(this.layoutId), Integer.valueOf(this.variableId));
    }

    public BaseRecyclerItem(@NonNull T t, int i) {
        this.data = t;
        this.layoutId = i;
        this.variableId = -1;
    }
}
