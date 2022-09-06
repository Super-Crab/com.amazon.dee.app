package org.apache.commons.lang3.exception;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
/* loaded from: classes4.dex */
public class DefaultExceptionContext implements ExceptionContext, Serializable {
    private static final long serialVersionUID = 20110706;
    private final List<Pair<String, Object>> contextValues = new ArrayList();

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public List<Pair<String, Object>> getContextEntries() {
        return this.contextValues;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public Set<String> getContextLabels() {
        HashSet hashSet = new HashSet();
        for (Pair<String, Object> pair : this.contextValues) {
            hashSet.add(pair.getKey());
        }
        return hashSet;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public List<Object> getContextValues(String str) {
        ArrayList arrayList = new ArrayList();
        for (Pair<String, Object> pair : this.contextValues) {
            if (StringUtils.equals(str, pair.getKey())) {
                arrayList.add(pair.getValue());
            }
        }
        return arrayList;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public Object getFirstContextValue(String str) {
        for (Pair<String, Object> pair : this.contextValues) {
            if (StringUtils.equals(str, pair.getKey())) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public String getFormattedExceptionMessage(String str) {
        String sb;
        StringBuilder sb2 = new StringBuilder(256);
        if (str != null) {
            sb2.append(str);
        }
        if (this.contextValues.size() > 0) {
            if (sb2.length() > 0) {
                sb2.append('\n');
            }
            sb2.append("Exception Context:\n");
            int i = 0;
            for (Pair<String, Object> pair : this.contextValues) {
                sb2.append("\t[");
                i++;
                sb2.append(i);
                sb2.append(JsonReaderKt.COLON);
                sb2.append(pair.getKey());
                sb2.append(Config.Compare.EQUAL_TO);
                Object value = pair.getValue();
                if (value == null) {
                    sb2.append("null");
                } else {
                    try {
                        sb = value.toString();
                    } catch (Exception e) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception thrown on toString(): ");
                        outline107.append(ExceptionUtils.getStackTrace(e));
                        sb = outline107.toString();
                    }
                    sb2.append(sb);
                }
                sb2.append("]\n");
            }
            sb2.append("---------------------------------");
        }
        return sb2.toString();
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    /* renamed from: addContextValue */
    public DefaultExceptionContext mo12825addContextValue(String str, Object obj) {
        this.contextValues.add(new ImmutablePair(str, obj));
        return this;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    /* renamed from: setContextValue */
    public DefaultExceptionContext mo12826setContextValue(String str, Object obj) {
        Iterator<Pair<String, Object>> it2 = this.contextValues.iterator();
        while (it2.hasNext()) {
            if (StringUtils.equals(str, it2.next().getKey())) {
                it2.remove();
            }
        }
        mo12825addContextValue(str, obj);
        return this;
    }
}
