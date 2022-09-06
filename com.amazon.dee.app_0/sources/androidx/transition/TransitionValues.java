package androidx.transition;

import android.view.View;
import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class TransitionValues {
    public View view;
    public final Map<String, Object> values = new HashMap();
    final ArrayList<Transition> mTargetedTransitions = new ArrayList<>();

    @Deprecated
    public TransitionValues() {
    }

    public boolean equals(Object obj) {
        if (obj instanceof TransitionValues) {
            TransitionValues transitionValues = (TransitionValues) obj;
            return this.view == transitionValues.view && this.values.equals(transitionValues.values);
        }
        return false;
    }

    public int hashCode() {
        return this.values.hashCode() + (this.view.hashCode() * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransitionValues@");
        outline107.append(Integer.toHexString(hashCode()));
        outline107.append(":\n");
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(outline107.toString(), "    view = ");
        outline113.append(this.view);
        outline113.append("\n");
        String outline72 = GeneratedOutlineSupport1.outline72(outline113.toString(), "    values:");
        for (String str : this.values.keySet()) {
            outline72 = outline72 + "    " + str + RealTimeTextConstants.COLON_SPACE + this.values.get(str) + "\n";
        }
        return outline72;
    }

    public TransitionValues(@NonNull View view) {
        this.view = view;
    }
}
