package com.amazon.deecomms.contacts.operations;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.deecomms.common.service.CommsJobIntentService;
/* loaded from: classes12.dex */
public class ContactsOperationsService extends CommsJobIntentService {
    private static final int JOB_ID = CommsJobIntentService.generateJobId(ContactsOperationsService.class);
    private ContactsOperationsDelegate mContactsOperationsDelegate;

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, ContactsOperationsService.class, JOB_ID, intent);
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mContactsOperationsDelegate = new ContactsOperationsDelegate(new ContactsOperationHandler());
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        this.mContactsOperationsDelegate.onHandleIntent(intent);
    }
}
