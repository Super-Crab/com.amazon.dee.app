package com.amazon.identity.auth.device;

import android.content.Intent;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface z {
    String A();

    boolean N(String str);

    void a(Intent intent, String str);

    void a(String str, Intent intent, String str2);

    void a(String str, Set<Integer> set, Intent intent, String str2);

    MultipleAccountManager.AccountMappingType[] a(String str, int i);

    void b(String str, Intent intent, String str2);

    void c(Bundle bundle, Bundle bundle2);
}
