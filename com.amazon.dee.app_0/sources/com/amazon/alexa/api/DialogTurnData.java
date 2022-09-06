package com.amazon.alexa.api;

import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Objects;
/* loaded from: classes6.dex */
public class DialogTurnData {
    private static final String TAG = "DialogTurnData";
    private final String dialogTurnId;

    /* loaded from: classes6.dex */
    public static class Builder {
        private String dialogTurnId;

        public DialogTurnData build() {
            Preconditions.notNull(this.dialogTurnId, "Dialog Turn ID must be supplied");
            return new DialogTurnData(this.dialogTurnId);
        }

        public Builder setDialogTurnId(String str) {
            this.dialogTurnId = str;
            return this;
        }
    }

    /* loaded from: classes6.dex */
    static class DialogTurnDataAdapter implements u<DialogTurnData> {

        /* JADX INFO: Access modifiers changed from: package-private */
        @VisibleForTesting
        /* loaded from: classes6.dex */
        public enum DialogTurnDataKey implements Bundles.Key {
            DIALOG_TURN_ID
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public DialogTurnData mo844createFromBundle(Bundle bundle) {
            return new DialogTurnData(Bundles.getString(bundle, DialogTurnDataKey.DIALOG_TURN_ID));
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(DialogTurnData dialogTurnData) {
            Bundle bundle = new Bundle();
            bundle.putString(DialogTurnDataKey.DIALOG_TURN_ID.name(), dialogTurnData.dialogTurnId);
            return bundle;
        }
    }

    private DialogTurnData(String str) {
        Preconditions.notNull(str, "Dialog Turn ID must be supplied");
        this.dialogTurnId = str;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DialogTurnData) {
            return getDialogTurnId().equals(((DialogTurnData) obj).getDialogTurnId());
        }
        return false;
    }

    public String getDialogTurnId() {
        return this.dialogTurnId;
    }

    public int hashCode() {
        return Objects.hash(getDialogTurnId());
    }
}
