package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.repositories.calling.ATCommand;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AtCommandTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/AtCommandTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/repositories/calling/ATCommand;", "()V", "COMMAND_KEY", "", "PARAMETERS_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AtCommandTransformer implements BundleTransformer<ATCommand> {
    private static final String COMMAND_KEY = "command";
    public static final AtCommandTransformer INSTANCE = new AtCommandTransformer();
    private static final String PARAMETERS_KEY = "parameters";

    private AtCommandTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public ATCommand mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        ATCommand.Builder command = new ATCommand.Builder().command(bundle.getString("command"));
        String[] stringArray = bundle.getStringArray(PARAMETERS_KEY);
        if (stringArray == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "bundle.getStringArray(PARAMETERS_KEY)!!");
        for (String str : stringArray) {
            command.parameter(str);
        }
        ATCommand build = command.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "builder.build()");
        return build;
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull ATCommand t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        Bundle bundle = new Bundle();
        bundle.putString("command", t.getCommand());
        List<String> parameters = t.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "t.parameters");
        Object[] array = parameters.toArray(new String[0]);
        if (array != null) {
            bundle.putStringArray(PARAMETERS_KEY, (String[]) array);
            return bundle;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
