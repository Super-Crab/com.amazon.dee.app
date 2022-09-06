package com.amazon.alexa.client.core.messages;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
import java.util.UUID;
@AutoValue
/* loaded from: classes6.dex */
public abstract class DialogRequestIdentifier implements StronglyTypedString {
    @VisibleForTesting
    static final String ID_PREFIX = "dr-";
    public static final DialogRequestIdentifier NONE = create("");
    @VisibleForTesting
    static final String TEXT_ID_PREFIX = "Mobile_TTA_";

    public static DialogRequestIdentifier create(String str) {
        return new AutoValue_DialogRequestIdentifier(str);
    }

    public static DialogRequestIdentifier createRandom() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(ID_PREFIX);
        outline107.append(UUID.randomUUID().toString());
        return create(outline107.toString());
    }

    public static DialogRequestIdentifier createRandomForText() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(TEXT_ID_PREFIX);
        outline107.append(UUID.randomUUID().toString());
        return create(outline107.toString());
    }

    public static TypeAdapter<DialogRequestIdentifier> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<DialogRequestIdentifier>() { // from class: com.amazon.alexa.client.core.messages.DialogRequestIdentifier.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public DialogRequestIdentifier mo1132instantiate(@NonNull String str) {
                return DialogRequestIdentifier.create(str);
            }
        };
    }
}
