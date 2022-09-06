package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public enum AlexaState implements Parcelable {
    IDLE,
    PREPARING_TO_LISTEN,
    LISTENING,
    FINISHING_LISTENING,
    THINKING,
    SPEAKING,
    ERROR,
    UNKNOWN;
    
    public static final Parcelable.Creator<AlexaState> CREATOR = new Parcelable.Creator<AlexaState>() { // from class: com.amazon.alexa.api.AlexaState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaState mo815createFromParcel(Parcel parcel) {
            return (AlexaState) parcel.readSerializable();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaState[] mo816newArray(int i) {
            return new AlexaState[i];
        }
    };

    /* loaded from: classes6.dex */
    static class AlexaStateBundleAdapter implements u<AlexaState> {
        private static final String ALEXA_STATE = "alexaState";
        private static final String TAG = "AlexaStateBundleAdapter";

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public AlexaState mo844createFromBundle(@NonNull Bundle bundle) {
            Preconditions.notNull(bundle, "Bundle can't be null");
            String string = bundle.getString(ALEXA_STATE);
            if (!TextUtils.isEmpty(string)) {
                return AlexaState.valueOf(string);
            }
            String str = TAG;
            Log.e(str, "Unable to create AlexaState from bundle: " + bundle);
            return null;
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(@NonNull AlexaState alexaState) {
            Preconditions.notNull(alexaState, "AlexaState can't be null");
            Bundle bundle = new Bundle();
            bundle.putString(ALEXA_STATE, alexaState.name());
            return bundle;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this);
    }
}
