package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Objects;
/* loaded from: classes6.dex */
public class DialogData {
    private static final String TAG = "DialogData";
    private final String dialogId;

    /* loaded from: classes6.dex */
    public static class Builder {
        private String dialogId;

        public DialogData build() {
            Preconditions.notNull(this.dialogId, "Dialog ID must be supplied");
            return new DialogData(this.dialogId);
        }

        public Builder setDialogId(String str) {
            this.dialogId = str;
            return this;
        }
    }

    /* loaded from: classes6.dex */
    static class DialogTurnDataAdapter implements u<DialogData> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public enum DialogDataKey implements Bundles.Key {
            DIALOG_ID
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public DialogData mo844createFromBundle(Bundle bundle) {
            return new DialogData(Bundles.getString(bundle, DialogDataKey.DIALOG_ID));
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(DialogData dialogData) {
            Bundle bundle = new Bundle();
            bundle.putString(DialogDataKey.DIALOG_ID.name(), dialogData.dialogId);
            return bundle;
        }
    }

    private DialogData(String str) {
        this.dialogId = str;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DialogData) {
            return getDialogId().equals(((DialogData) obj).getDialogId());
        }
        return false;
    }

    public String getDialogId() {
        return this.dialogId;
    }

    public int hashCode() {
        return Objects.hash(getDialogId());
    }
}
