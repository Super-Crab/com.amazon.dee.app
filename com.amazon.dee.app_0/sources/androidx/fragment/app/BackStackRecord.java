package androidx.fragment.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.LogWriter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManagerImpl;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintWriter;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator {
    static final String TAG = "FragmentManager";
    boolean mCommitted;
    int mIndex = -1;
    final FragmentManagerImpl mManager;

    public BackStackRecord(FragmentManagerImpl fragmentManagerImpl) {
        this.mManager = fragmentManagerImpl;
    }

    private static boolean isFragmentPostponed(FragmentTransaction.Op op) {
        Fragment fragment = op.mFragment;
        return fragment != null && fragment.mAdded && fragment.mView != null && !fragment.mDetached && !fragment.mHidden && fragment.isPostponed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bumpBackStackNesting(int i) {
        if (!this.mAddToBackStack) {
            return;
        }
        if (FragmentManagerImpl.DEBUG) {
            String str = "Bump nesting in " + this + " by " + i;
        }
        int size = this.mOps.size();
        for (int i2 = 0; i2 < size; i2++) {
            FragmentTransaction.Op op = this.mOps.get(i2);
            Fragment fragment = op.mFragment;
            if (fragment != null) {
                fragment.mBackStackNesting += i;
                if (FragmentManagerImpl.DEBUG) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bump nesting of ");
                    outline107.append(op.mFragment);
                    outline107.append(" to ");
                    outline107.append(op.mFragment.mBackStackNesting);
                    outline107.toString();
                }
            }
        }
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public int commit() {
        return commitInternal(false);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public int commitAllowingStateLoss() {
        return commitInternal(true);
    }

    int commitInternal(boolean z) {
        if (!this.mCommitted) {
            if (FragmentManagerImpl.DEBUG) {
                String str = "Commit: " + this;
                PrintWriter printWriter = new PrintWriter(new LogWriter(TAG));
                dump("  ", printWriter);
                printWriter.close();
            }
            this.mCommitted = true;
            if (this.mAddToBackStack) {
                this.mIndex = this.mManager.allocBackStackIndex(this);
            } else {
                this.mIndex = -1;
            }
            this.mManager.enqueueAction(this, z);
            return this.mIndex;
        }
        throw new IllegalStateException("commit already called");
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public void commitNow() {
        disallowAddToBackStack();
        this.mManager.execSingleAction(this, false);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public void commitNowAllowingStateLoss() {
        disallowAddToBackStack();
        this.mManager.execSingleAction(this, true);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction detach(@NonNull Fragment fragment) {
        FragmentManagerImpl fragmentManagerImpl = fragment.mFragmentManager;
        if (fragmentManagerImpl != null && fragmentManagerImpl != this.mManager) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot detach Fragment attached to a different FragmentManager. Fragment ");
            outline107.append(fragment.toString());
            outline107.append(" is already attached to a FragmentManager.");
            throw new IllegalStateException(outline107.toString());
        }
        return super.detach(fragment);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.fragment.app.FragmentTransaction
    public void doAddOp(int i, Fragment fragment, @Nullable String str, int i2) {
        super.doAddOp(i, fragment, str, i2);
        fragment.mFragmentManager = this.mManager;
    }

    public void dump(String str, PrintWriter printWriter) {
        dump(str, printWriter, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void executeOps() {
        int size = this.mOps.size();
        for (int i = 0; i < size; i++) {
            FragmentTransaction.Op op = this.mOps.get(i);
            Fragment fragment = op.mFragment;
            if (fragment != null) {
                fragment.setNextTransition(this.mTransition, this.mTransitionStyle);
            }
            switch (op.mCmd) {
                case 1:
                    fragment.setNextAnim(op.mEnterAnim);
                    this.mManager.addFragment(fragment, false);
                    break;
                case 2:
                default:
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown cmd: ");
                    outline107.append(op.mCmd);
                    throw new IllegalArgumentException(outline107.toString());
                case 3:
                    fragment.setNextAnim(op.mExitAnim);
                    this.mManager.removeFragment(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(op.mExitAnim);
                    this.mManager.hideFragment(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(op.mEnterAnim);
                    this.mManager.showFragment(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(op.mExitAnim);
                    this.mManager.detachFragment(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(op.mEnterAnim);
                    this.mManager.attachFragment(fragment);
                    break;
                case 8:
                    this.mManager.setPrimaryNavigationFragment(fragment);
                    break;
                case 9:
                    this.mManager.setPrimaryNavigationFragment(null);
                    break;
                case 10:
                    this.mManager.setMaxLifecycle(fragment, op.mCurrentMaxState);
                    break;
            }
            if (!this.mReorderingAllowed && op.mCmd != 1 && fragment != null) {
                this.mManager.moveFragmentToExpectedState(fragment);
            }
        }
        if (!this.mReorderingAllowed) {
            FragmentManagerImpl fragmentManagerImpl = this.mManager;
            fragmentManagerImpl.moveToState(fragmentManagerImpl.mCurState, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void executePopOps(boolean z) {
        for (int size = this.mOps.size() - 1; size >= 0; size--) {
            FragmentTransaction.Op op = this.mOps.get(size);
            Fragment fragment = op.mFragment;
            if (fragment != null) {
                fragment.setNextTransition(FragmentManagerImpl.reverseTransit(this.mTransition), this.mTransitionStyle);
            }
            switch (op.mCmd) {
                case 1:
                    fragment.setNextAnim(op.mPopExitAnim);
                    this.mManager.removeFragment(fragment);
                    break;
                case 2:
                default:
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown cmd: ");
                    outline107.append(op.mCmd);
                    throw new IllegalArgumentException(outline107.toString());
                case 3:
                    fragment.setNextAnim(op.mPopEnterAnim);
                    this.mManager.addFragment(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(op.mPopEnterAnim);
                    this.mManager.showFragment(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(op.mPopExitAnim);
                    this.mManager.hideFragment(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(op.mPopEnterAnim);
                    this.mManager.attachFragment(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(op.mPopExitAnim);
                    this.mManager.detachFragment(fragment);
                    break;
                case 8:
                    this.mManager.setPrimaryNavigationFragment(null);
                    break;
                case 9:
                    this.mManager.setPrimaryNavigationFragment(fragment);
                    break;
                case 10:
                    this.mManager.setMaxLifecycle(fragment, op.mOldMaxState);
                    break;
            }
            if (!this.mReorderingAllowed && op.mCmd != 3 && fragment != null) {
                this.mManager.moveFragmentToExpectedState(fragment);
            }
        }
        if (this.mReorderingAllowed || !z) {
            return;
        }
        FragmentManagerImpl fragmentManagerImpl = this.mManager;
        fragmentManagerImpl.moveToState(fragmentManagerImpl.mCurState, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment expandOps(ArrayList<Fragment> arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i = 0;
        while (i < this.mOps.size()) {
            FragmentTransaction.Op op = this.mOps.get(i);
            int i2 = op.mCmd;
            if (i2 != 1) {
                if (i2 == 2) {
                    Fragment fragment3 = op.mFragment;
                    int i3 = fragment3.mContainerId;
                    Fragment fragment4 = fragment2;
                    int i4 = i;
                    boolean z = false;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        Fragment fragment5 = arrayList.get(size);
                        if (fragment5.mContainerId == i3) {
                            if (fragment5 == fragment3) {
                                z = true;
                            } else {
                                if (fragment5 == fragment4) {
                                    this.mOps.add(i4, new FragmentTransaction.Op(9, fragment5));
                                    i4++;
                                    fragment4 = null;
                                }
                                FragmentTransaction.Op op2 = new FragmentTransaction.Op(3, fragment5);
                                op2.mEnterAnim = op.mEnterAnim;
                                op2.mPopEnterAnim = op.mPopEnterAnim;
                                op2.mExitAnim = op.mExitAnim;
                                op2.mPopExitAnim = op.mPopExitAnim;
                                this.mOps.add(i4, op2);
                                arrayList.remove(fragment5);
                                i4++;
                            }
                        }
                    }
                    if (z) {
                        this.mOps.remove(i4);
                        i4--;
                    } else {
                        op.mCmd = 1;
                        arrayList.add(fragment3);
                    }
                    i = i4;
                    fragment2 = fragment4;
                } else if (i2 == 3 || i2 == 6) {
                    arrayList.remove(op.mFragment);
                    Fragment fragment6 = op.mFragment;
                    if (fragment6 == fragment2) {
                        this.mOps.add(i, new FragmentTransaction.Op(9, fragment6));
                        i++;
                        fragment2 = null;
                    }
                } else if (i2 != 7) {
                    if (i2 == 8) {
                        this.mOps.add(i, new FragmentTransaction.Op(9, fragment2));
                        i++;
                        fragment2 = op.mFragment;
                    }
                }
                i++;
            }
            arrayList.add(op.mFragment);
            i++;
        }
        return fragment2;
    }

    @Override // androidx.fragment.app.FragmentManagerImpl.OpGenerator
    public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        if (FragmentManagerImpl.DEBUG) {
            String str = "Run: " + this;
        }
        arrayList.add(this);
        arrayList2.add(false);
        if (this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
            return true;
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbShortTitle() {
        if (this.mBreadCrumbShortTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes);
        }
        return this.mBreadCrumbShortTitleText;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbTitle() {
        if (this.mBreadCrumbTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes);
        }
        return this.mBreadCrumbTitleText;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    public int getId() {
        return this.mIndex;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    @Nullable
    public String getName() {
        return this.mName;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction hide(@NonNull Fragment fragment) {
        FragmentManagerImpl fragmentManagerImpl = fragment.mFragmentManager;
        if (fragmentManagerImpl != null && fragmentManagerImpl != this.mManager) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot hide Fragment attached to a different FragmentManager. Fragment ");
            outline107.append(fragment.toString());
            outline107.append(" is already attached to a FragmentManager.");
            throw new IllegalStateException(outline107.toString());
        }
        return super.hide(fragment);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean interactsWith(int i) {
        int size = this.mOps.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.mOps.get(i2).mFragment;
            int i3 = fragment != null ? fragment.mContainerId : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isPostponed() {
        for (int i = 0; i < this.mOps.size(); i++) {
            if (isFragmentPostponed(this.mOps.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction remove(@NonNull Fragment fragment) {
        FragmentManagerImpl fragmentManagerImpl = fragment.mFragmentManager;
        if (fragmentManagerImpl != null && fragmentManagerImpl != this.mManager) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot remove Fragment attached to a different FragmentManager. Fragment ");
            outline107.append(fragment.toString());
            outline107.append(" is already attached to a FragmentManager.");
            throw new IllegalStateException(outline107.toString());
        }
        return super.remove(fragment);
    }

    public void runOnCommitRunnables() {
        if (this.mCommitRunnables != null) {
            for (int i = 0; i < this.mCommitRunnables.size(); i++) {
                this.mCommitRunnables.get(i).run();
            }
            this.mCommitRunnables = null;
        }
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction setMaxLifecycle(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        if (fragment.mFragmentManager == this.mManager) {
            if (state.isAtLeast(Lifecycle.State.CREATED)) {
                return super.setMaxLifecycle(fragment, state);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot set maximum Lifecycle below ");
            outline107.append(Lifecycle.State.CREATED);
            throw new IllegalArgumentException(outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Cannot setMaxLifecycle for Fragment not attached to FragmentManager ");
        outline1072.append(this.mManager);
        throw new IllegalArgumentException(outline1072.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener onStartEnterTransitionListener) {
        for (int i = 0; i < this.mOps.size(); i++) {
            FragmentTransaction.Op op = this.mOps.get(i);
            if (isFragmentPostponed(op)) {
                op.mFragment.setOnStartEnterTransitionListener(onStartEnterTransitionListener);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        FragmentManagerImpl fragmentManagerImpl;
        if (fragment != null && (fragmentManagerImpl = fragment.mFragmentManager) != null && fragmentManagerImpl != this.mManager) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot setPrimaryNavigation for Fragment attached to a different FragmentManager. Fragment ");
            outline107.append(fragment.toString());
            outline107.append(" is already attached to a FragmentManager.");
            throw new IllegalStateException(outline107.toString());
        }
        return super.setPrimaryNavigationFragment(fragment);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction show(@NonNull Fragment fragment) {
        FragmentManagerImpl fragmentManagerImpl = fragment.mFragmentManager;
        if (fragmentManagerImpl != null && fragmentManagerImpl != this.mManager) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot show Fragment attached to a different FragmentManager. Fragment ");
            outline107.append(fragment.toString());
            outline107.append(" is already attached to a FragmentManager.");
            throw new IllegalStateException(outline107.toString());
        }
        return super.show(fragment);
    }

    public String toString() {
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(128, "BackStackEntry{");
        outline105.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            outline105.append(" #");
            outline105.append(this.mIndex);
        }
        if (this.mName != null) {
            outline105.append(" ");
            outline105.append(this.mName);
        }
        outline105.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline105.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment trackAddedFragmentsInPop(ArrayList<Fragment> arrayList, Fragment fragment) {
        for (int size = this.mOps.size() - 1; size >= 0; size--) {
            FragmentTransaction.Op op = this.mOps.get(size);
            int i = op.mCmd;
            if (i != 1) {
                if (i != 3) {
                    switch (i) {
                        case 8:
                            fragment = null;
                            break;
                        case 9:
                            fragment = op.mFragment;
                            break;
                        case 10:
                            op.mCurrentMaxState = op.mOldMaxState;
                            break;
                    }
                }
                arrayList.add(op.mFragment);
            }
            arrayList.remove(op.mFragment);
        }
        return fragment;
    }

    public void dump(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.mName);
            printWriter.print(" mIndex=");
            printWriter.print(this.mIndex);
            printWriter.print(" mCommitted=");
            printWriter.println(this.mCommitted);
            if (this.mTransition != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.mTransition));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (this.mEnterAnim != 0 || this.mExitAnim != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mEnterAnim));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.mExitAnim));
            }
            if (this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.mBreadCrumbTitleText);
            }
            if (this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (!this.mOps.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            int size = this.mOps.size();
            for (int i = 0; i < size; i++) {
                FragmentTransaction.Op op = this.mOps.get(i);
                switch (op.mCmd) {
                    case 0:
                        str2 = AppInformation.NULL;
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case 8:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    case 10:
                        str2 = "OP_SET_MAX_LIFECYCLE";
                        break;
                    default:
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cmd=");
                        outline107.append(op.mCmd);
                        str2 = outline107.toString();
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(RealTimeTextConstants.COLON_SPACE);
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(op.mFragment);
                if (z) {
                    if (op.mEnterAnim != 0 || op.mExitAnim != 0) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(op.mEnterAnim));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(op.mExitAnim));
                    }
                    if (op.mPopEnterAnim != 0 || op.mPopExitAnim != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(op.mPopEnterAnim));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(op.mPopExitAnim));
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean interactsWith(ArrayList<BackStackRecord> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.mOps.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.mOps.get(i4).mFragment;
            int i5 = fragment != null ? fragment.mContainerId : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    BackStackRecord backStackRecord = arrayList.get(i6);
                    int size2 = backStackRecord.mOps.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = backStackRecord.mOps.get(i7).mFragment;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }
}
