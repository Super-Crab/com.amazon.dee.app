package kotlin.reflect.jvm.internal.impl.renderer;

import com.amazon.alexa.mobilytics.configuration.Config;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: DescriptorRenderer.kt */
/* loaded from: classes4.dex */
public enum RenderingFormat {
    PLAIN { // from class: kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat.PLAIN
        @Override // kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat
        @NotNull
        public String escape(@NotNull String string) {
            Intrinsics.checkParameterIsNotNull(string, "string");
            return string;
        }
    },
    HTML { // from class: kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat.HTML
        @Override // kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat
        @NotNull
        public String escape(@NotNull String string) {
            String replace$default;
            String replace$default2;
            Intrinsics.checkParameterIsNotNull(string, "string");
            replace$default = StringsKt__StringsJVMKt.replace$default(string, Config.Compare.LESS_THAN, "&lt;", false, 4, (Object) null);
            replace$default2 = StringsKt__StringsJVMKt.replace$default(replace$default, Config.Compare.GREATER_THAN, "&gt;", false, 4, (Object) null);
            return replace$default2;
        }
    };

    @NotNull
    public abstract String escape(@NotNull String str);

    /* synthetic */ RenderingFormat(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
