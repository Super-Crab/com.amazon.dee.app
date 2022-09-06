package kotlin.jvm.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
/* loaded from: classes11.dex */
public class SpreadBuilder {
    private final ArrayList<Object> list;

    public SpreadBuilder(int i) {
        this.list = new ArrayList<>(i);
    }

    public void add(Object obj) {
        this.list.add(obj);
    }

    public void addSpread(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr.length <= 0) {
                return;
            }
            ArrayList<Object> arrayList = this.list;
            arrayList.ensureCapacity(arrayList.size() + objArr.length);
            Collections.addAll(this.list, objArr);
        } else if (obj instanceof Collection) {
            this.list.addAll((Collection) obj);
        } else if (obj instanceof Iterable) {
            for (Object obj2 : (Iterable) obj) {
                this.list.add(obj2);
            }
        } else if (obj instanceof Iterator) {
            Iterator it2 = (Iterator) obj;
            while (it2.hasNext()) {
                this.list.add(it2.next());
            }
        } else {
            throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline44(obj, GeneratedOutlineSupport1.outline107("Don't know how to spread ")));
        }
    }

    public int size() {
        return this.list.size();
    }

    public Object[] toArray(Object[] objArr) {
        return this.list.toArray(objArr);
    }
}
