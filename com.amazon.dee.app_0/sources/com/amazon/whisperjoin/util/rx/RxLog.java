package com.amazon.whisperjoin.util.rx;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Locale;
/* loaded from: classes13.dex */
public class RxLog {
    public static Action doFinally(final String str, final String str2) {
        return new Action() { // from class: com.amazon.whisperjoin.util.rx.RxLog.4
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Exception {
                String str3 = str;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[DoFinally]: ");
                outline107.append(str2);
                WJLog.d(str3, outline107.toString());
            }
        };
    }

    public static Action doOnComplete(final String str, final String str2) {
        return new Action() { // from class: com.amazon.whisperjoin.util.rx.RxLog.5
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Exception {
                String str3 = str;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[OnComplete]: ");
                outline107.append(str2);
                WJLog.d(str3, outline107.toString());
            }
        };
    }

    public static Consumer<Throwable> doOnError(final String str, final String str2) {
        return new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.util.rx.RxLog.6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Throwable th) throws Exception {
                String str3 = str;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[OnError]: ");
                outline107.append(str2);
                WJLog.e(str3, outline107.toString(), th);
            }
        };
    }

    public static <T> Consumer<T> doOnNext(final String str, final String str2) {
        return new Consumer<T>() { // from class: com.amazon.whisperjoin.util.rx.RxLog.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(T t) throws Exception {
                WJLog.d(str, String.format(Locale.ENGLISH, "[OnNext]: %s - [%s]", str2, t.toString()));
            }
        };
    }

    public static Consumer<Disposable> doOnSubscribe(final String str, final String str2) {
        return new Consumer<Disposable>() { // from class: com.amazon.whisperjoin.util.rx.RxLog.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Disposable disposable) throws Exception {
                String str3 = str;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[OnSubscribe]: ");
                outline107.append(str2);
                WJLog.d(str3, outline107.toString());
            }
        };
    }

    public static <T> Consumer<T> doOnSuccess(final String str, final String str2) {
        return new Consumer<T>() { // from class: com.amazon.whisperjoin.util.rx.RxLog.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(T t) throws Exception {
                WJLog.d(str, String.format(Locale.ENGLISH, "[OnSuccess]: %s - [%s]", str2, t.toString()));
            }
        };
    }
}
