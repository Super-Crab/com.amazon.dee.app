package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new Parcelable.Creator<FragmentState>() { // from class: androidx.fragment.app.FragmentState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public FragmentState mo170createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public FragmentState[] mo171newArray(int i) {
            return new FragmentState[i];
        }
    };
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    Fragment mInstance;
    final int mMaxLifecycleState;
    final boolean mRemoving;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;
    final String mWho;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentState(Fragment fragment) {
        this.mClassName = fragment.getClass().getName();
        this.mWho = fragment.mWho;
        this.mFromLayout = fragment.mFromLayout;
        this.mFragmentId = fragment.mFragmentId;
        this.mContainerId = fragment.mContainerId;
        this.mTag = fragment.mTag;
        this.mRetainInstance = fragment.mRetainInstance;
        this.mRemoving = fragment.mRemoving;
        this.mDetached = fragment.mDetached;
        this.mArguments = fragment.mArguments;
        this.mHidden = fragment.mHidden;
        this.mMaxLifecycleState = fragment.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull FragmentFactory fragmentFactory) {
        if (this.mInstance == null) {
            Bundle bundle = this.mArguments;
            if (bundle != null) {
                bundle.setClassLoader(classLoader);
            }
            this.mInstance = fragmentFactory.instantiate(classLoader, this.mClassName);
            this.mInstance.setArguments(this.mArguments);
            Bundle bundle2 = this.mSavedFragmentState;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
                this.mInstance.mSavedFragmentState = this.mSavedFragmentState;
            } else {
                this.mInstance.mSavedFragmentState = new Bundle();
            }
            Fragment fragment = this.mInstance;
            fragment.mWho = this.mWho;
            fragment.mFromLayout = this.mFromLayout;
            fragment.mRestored = true;
            fragment.mFragmentId = this.mFragmentId;
            fragment.mContainerId = this.mContainerId;
            fragment.mTag = this.mTag;
            fragment.mRetainInstance = this.mRetainInstance;
            fragment.mRemoving = this.mRemoving;
            fragment.mDetached = this.mDetached;
            fragment.mHidden = this.mHidden;
            fragment.mMaxState = Lifecycle.State.values()[this.mMaxLifecycleState];
            if (FragmentManagerImpl.DEBUG) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Instantiated fragment ");
                outline107.append(this.mInstance);
                outline107.toString();
            }
        }
        return this.mInstance;
    }

    @NonNull
    public String toString() {
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(128, "FragmentState{");
        outline105.append(this.mClassName);
        outline105.append(" (");
        outline105.append(this.mWho);
        outline105.append(")}:");
        if (this.mFromLayout) {
            outline105.append(" fromLayout");
        }
        if (this.mContainerId != 0) {
            outline105.append(" id=0x");
            outline105.append(Integer.toHexString(this.mContainerId));
        }
        String str = this.mTag;
        if (str != null && !str.isEmpty()) {
            outline105.append(" tag=");
            outline105.append(this.mTag);
        }
        if (this.mRetainInstance) {
            outline105.append(" retainInstance");
        }
        if (this.mRemoving) {
            outline105.append(" removing");
        }
        if (this.mDetached) {
            outline105.append(" detached");
        }
        if (this.mHidden) {
            outline105.append(" hidden");
        }
        return outline105.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mClassName);
        parcel.writeString(this.mWho);
        parcel.writeInt(this.mFromLayout ? 1 : 0);
        parcel.writeInt(this.mFragmentId);
        parcel.writeInt(this.mContainerId);
        parcel.writeString(this.mTag);
        parcel.writeInt(this.mRetainInstance ? 1 : 0);
        parcel.writeInt(this.mRemoving ? 1 : 0);
        parcel.writeInt(this.mDetached ? 1 : 0);
        parcel.writeBundle(this.mArguments);
        parcel.writeInt(this.mHidden ? 1 : 0);
        parcel.writeBundle(this.mSavedFragmentState);
        parcel.writeInt(this.mMaxLifecycleState);
    }

    FragmentState(Parcel parcel) {
        this.mClassName = parcel.readString();
        this.mWho = parcel.readString();
        boolean z = true;
        this.mFromLayout = parcel.readInt() != 0;
        this.mFragmentId = parcel.readInt();
        this.mContainerId = parcel.readInt();
        this.mTag = parcel.readString();
        this.mRetainInstance = parcel.readInt() != 0;
        this.mRemoving = parcel.readInt() != 0;
        this.mDetached = parcel.readInt() != 0;
        this.mArguments = parcel.readBundle();
        this.mHidden = parcel.readInt() == 0 ? false : z;
        this.mSavedFragmentState = parcel.readBundle();
        this.mMaxLifecycleState = parcel.readInt();
    }
}
