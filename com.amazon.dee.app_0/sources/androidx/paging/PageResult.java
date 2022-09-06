package androidx.paging;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
class PageResult<T> {
    static final int APPEND = 1;
    static final int INIT = 0;
    static final int PREPEND = 2;
    static final int TILE = 3;
    public final int leadingNulls;
    @NonNull
    public final List<T> page;
    public final int positionOffset;
    public final int trailingNulls;
    private static final PageResult EMPTY_RESULT = new PageResult(Collections.emptyList(), 0);
    private static final PageResult INVALID_RESULT = new PageResult(Collections.emptyList(), 0);

    /* loaded from: classes.dex */
    static abstract class Receiver<T> {
        @MainThread
        public abstract void onPageResult(int i, @NonNull PageResult<T> pageResult);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface ResultType {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PageResult(@NonNull List<T> list, int i, int i2, int i3) {
        this.page = list;
        this.leadingNulls = i;
        this.trailingNulls = i2;
        this.positionOffset = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> PageResult<T> getEmptyResult() {
        return EMPTY_RESULT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> PageResult<T> getInvalidResult() {
        return INVALID_RESULT;
    }

    public boolean isInvalid() {
        return this == INVALID_RESULT;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Result ");
        outline107.append(this.leadingNulls);
        outline107.append(", ");
        outline107.append(this.page);
        outline107.append(", ");
        outline107.append(this.trailingNulls);
        outline107.append(", offset ");
        outline107.append(this.positionOffset);
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PageResult(@NonNull List<T> list, int i) {
        this.page = list;
        this.leadingNulls = 0;
        this.trailingNulls = 0;
        this.positionOffset = i;
    }
}
