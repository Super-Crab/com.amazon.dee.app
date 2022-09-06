package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
class AnimatorUtils {

    /* loaded from: classes.dex */
    interface AnimatorPauseListenerCompat {
        void onAnimationPause(Animator animator);

        void onAnimationResume(Animator animator);
    }

    private AnimatorUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addPauseListener(@NonNull Animator animator, @NonNull AnimatorListenerAdapter animatorListenerAdapter) {
        int i = Build.VERSION.SDK_INT;
        animator.addPauseListener(animatorListenerAdapter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void pause(@NonNull Animator animator) {
        int i = Build.VERSION.SDK_INT;
        animator.pause();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void resume(@NonNull Animator animator) {
        int i = Build.VERSION.SDK_INT;
        animator.resume();
    }
}
