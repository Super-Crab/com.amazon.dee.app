package com.amazon.alexa.client.core.messages;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class MessageMetadata {
    public static final MessageMetadata EMPTY = create("", DialogRequestIdentifier.NONE);

    public static MessageMetadata create(@Nullable DialogRequestIdentifier dialogRequestIdentifier) {
        return create("", dialogRequestIdentifier);
    }

    public abstract DialogRequestIdentifier getOriginatingDialogRequestIdentifier();

    public abstract String getUnparsedMessage();

    public static MessageMetadata create(@Nullable String str, @Nullable DialogRequestIdentifier dialogRequestIdentifier) {
        if (str == null) {
            str = "";
        }
        if (dialogRequestIdentifier == null) {
            dialogRequestIdentifier = DialogRequestIdentifier.NONE;
        }
        return new AutoValue_MessageMetadata(str, dialogRequestIdentifier);
    }
}
