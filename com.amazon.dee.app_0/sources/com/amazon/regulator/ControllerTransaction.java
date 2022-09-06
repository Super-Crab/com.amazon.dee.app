package com.amazon.regulator;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ControllerTransaction implements Parcelable {
    public static final Parcelable.Creator<ControllerTransaction> CREATOR = new Parcelable.Creator<ControllerTransaction>() { // from class: com.amazon.regulator.ControllerTransaction.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ControllerTransaction mo4483createFromParcel(Parcel parcel) {
            return new ControllerTransaction(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ControllerTransaction[] mo4484newArray(int i) {
            return new ControllerTransaction[i];
        }
    };
    private final ViewController controller;
    private final ControllerTransition popTransition;
    private final ControllerTransition pushTransition;
    private final String tag;

    public ControllerTransaction(ViewController viewController, ControllerTransition controllerTransition, ControllerTransition controllerTransition2, String str) {
        Preconditions.nonNull(viewController, "controller == null");
        this.controller = viewController;
        this.popTransition = controllerTransition;
        this.pushTransition = controllerTransition2;
        this.tag = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ViewController getController() {
        return this.controller;
    }

    public ControllerTransition getPopTransition() {
        return this.popTransition;
    }

    public ControllerTransition getPushTransition() {
        return this.pushTransition;
    }

    public String getTag() {
        return this.tag;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.controller.getClass().getName());
        parcel.writeBundle(this.controller.saveState());
        parcel.writeParcelable(this.popTransition, i);
        parcel.writeParcelable(this.pushTransition, i);
        parcel.writeString(this.tag);
    }

    ControllerTransaction(Parcel parcel) {
        try {
            this.controller = (ViewController) Class.forName(parcel.readString()).newInstance();
            ClassLoader classLoader = ControllerTransaction.class.getClassLoader();
            this.controller.restoreState(parcel.readBundle(classLoader));
            this.popTransition = (ControllerTransition) parcel.readParcelable(classLoader);
            this.pushTransition = (ControllerTransition) parcel.readParcelable(classLoader);
            this.tag = parcel.readString();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("ViewController has to have a public constructor without any parameters", e);
        }
    }

    public ControllerTransaction(ViewController viewController, ControllerTransition controllerTransition, ControllerTransition controllerTransition2) {
        this(viewController, controllerTransition, controllerTransition2, null);
    }

    public ControllerTransaction(ViewController viewController, ControllerTransition controllerTransition) {
        this(viewController, null, controllerTransition, null);
    }

    public ControllerTransaction(ViewController viewController, ControllerTransition controllerTransition, String str) {
        this(viewController, null, controllerTransition, str);
    }

    public ControllerTransaction(ViewController viewController) {
        this(viewController, null, null, null);
    }

    public ControllerTransaction(ViewController viewController, String str) {
        this(viewController, null, null, str);
    }
}
