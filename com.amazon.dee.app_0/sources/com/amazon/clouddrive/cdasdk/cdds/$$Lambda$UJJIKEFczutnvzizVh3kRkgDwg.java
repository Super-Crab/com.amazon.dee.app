package com.amazon.clouddrive.cdasdk.cdds;

import com.amazon.clouddrive.cdasdk.ResponseBodyToInputStream;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.ResponseBody;
/* compiled from: lambda */
/* renamed from: com.amazon.clouddrive.cdasdk.cdds.-$$Lambda$UJJIKEFczutnvz-izVh3kRkgDwg  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$UJJIKEFczutnvzizVh3kRkgDwg implements Function {
    public static final /* synthetic */ $$Lambda$UJJIKEFczutnvzizVh3kRkgDwg INSTANCE = new $$Lambda$UJJIKEFczutnvzizVh3kRkgDwg();

    private /* synthetic */ $$Lambda$UJJIKEFczutnvzizVh3kRkgDwg() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ResponseBodyToInputStream.toStream((ResponseBody) obj);
    }
}
