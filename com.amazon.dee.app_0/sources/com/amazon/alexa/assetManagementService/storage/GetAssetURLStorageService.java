package com.amazon.alexa.assetManagementService.storage;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.assetManagementService.dao.AssetMappingDao;
import com.amazon.alexa.assetManagementService.entity.AssetMapping;
import com.amazon.alexa.assetManagementService.util.AssetMappingDatabase;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/* loaded from: classes6.dex */
public class GetAssetURLStorageService {
    private final AssetMappingDao assetMappingDao;
    @VisibleForTesting
    final ExecutorService executorService;

    public GetAssetURLStorageService(Context context, ExecutorService executorService) {
        this.assetMappingDao = AssetMappingDatabase.getInstance(context).assetMappingDao();
        this.executorService = executorService;
    }

    public Future clearAll() {
        return this.executorService.submit(new Runnable() { // from class: com.amazon.alexa.assetManagementService.storage.-$$Lambda$GetAssetURLStorageService$UM_ZEFYGKRsa1fq9KmNh-KBDC8E
            @Override // java.lang.Runnable
            public final void run() {
                GetAssetURLStorageService.this.lambda$clearAll$1$GetAssetURLStorageService();
            }
        });
    }

    public Future<List<String>> getAllBundleIds() {
        return this.executorService.submit(new Callable() { // from class: com.amazon.alexa.assetManagementService.storage.-$$Lambda$GetAssetURLStorageService$3dVgYgQ2aLKgWxAJdiR_-kRnn7I
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return GetAssetURLStorageService.this.lambda$getAllBundleIds$4$GetAssetURLStorageService();
            }
        });
    }

    public Future<List<AssetMapping>> getAssetMapping(@NonNull final String str) {
        return this.executorService.submit(new Callable() { // from class: com.amazon.alexa.assetManagementService.storage.-$$Lambda$GetAssetURLStorageService$1aWHRrIHlTb52wqGYvAL7MCTsjQ
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return GetAssetURLStorageService.this.lambda$getAssetMapping$3$GetAssetURLStorageService(str);
            }
        });
    }

    public /* synthetic */ void lambda$clearAll$1$GetAssetURLStorageService() {
        this.assetMappingDao.deleteAll();
    }

    public /* synthetic */ List lambda$getAllBundleIds$4$GetAssetURLStorageService() throws Exception {
        return this.assetMappingDao.getAllBundleIds();
    }

    public /* synthetic */ List lambda$getAssetMapping$3$GetAssetURLStorageService(String str) throws Exception {
        return this.assetMappingDao.get(str);
    }

    public /* synthetic */ List lambda$loadAll$2$GetAssetURLStorageService() throws Exception {
        return this.assetMappingDao.getAll();
    }

    public /* synthetic */ void lambda$save$0$GetAssetURLStorageService(List list) {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            this.assetMappingDao.insert((AssetMapping) it2.next());
        }
    }

    public Future<List<AssetMapping>> loadAll() {
        return this.executorService.submit(new Callable() { // from class: com.amazon.alexa.assetManagementService.storage.-$$Lambda$GetAssetURLStorageService$eBQqCth0o8Qkmnag5F156aNp32s
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return GetAssetURLStorageService.this.lambda$loadAll$2$GetAssetURLStorageService();
            }
        });
    }

    public Future save(@NonNull final List<AssetMapping> list) {
        return this.executorService.submit(new Runnable() { // from class: com.amazon.alexa.assetManagementService.storage.-$$Lambda$GetAssetURLStorageService$_Adgjdc_gFs4gMwAWwHEMGfbIug
            @Override // java.lang.Runnable
            public final void run() {
                GetAssetURLStorageService.this.lambda$save$0$GetAssetURLStorageService(list);
            }
        });
    }
}
