package kotlin.reflect.jvm.internal.impl.utils;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Map;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Jsr305State.kt */
/* loaded from: classes4.dex */
public final class Jsr305State$description$2 extends Lambda implements Function0<String[]> {
    final /* synthetic */ Jsr305State this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Jsr305State$description$2(Jsr305State jsr305State) {
        super(0);
        this.this$0 = jsr305State;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final String[] mo12560invoke() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.this$0.getGlobal().getDescription());
        ReportLevel migration = this.this$0.getMigration();
        if (migration != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("under-migration:");
            outline107.append(migration.getDescription());
            arrayList.add(outline107.toString());
        }
        for (Map.Entry<String, ReportLevel> entry : this.this$0.getUser().entrySet()) {
            StringBuilder outline104 = GeneratedOutlineSupport1.outline104('@');
            outline104.append(entry.getKey());
            outline104.append(JsonReaderKt.COLON);
            outline104.append(entry.getValue().getDescription());
            arrayList.add(outline104.toString());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
