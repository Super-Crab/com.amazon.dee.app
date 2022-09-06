;(function() {

    var appCache = window.applicationCache,

    //
    // @param n
    //            status code from window.applicationCache.status
    // @return English description of provided status code
    //
    getDescriptionOfStatusCode = function(n) {
        var statusMessages = [ "uncached", "idle", "checking", "downloading", "update ready", "obsolete" ];
        return statusMessages[n];
    },

    // to let us know when app cache is complete
    appCacheCheck = setInterval(function() {

        if ((appCache.status !== appCache.CHECKING) && (appCache.status !== appCache.DOWNLOADING)) {

            console.log("appCache status: "+ getDescriptionOfStatusCode(appCache.status));
            clearInterval(appCacheCheck);

            if (typeof AppCacheBridge !== "undefined") {
                AppCacheBridge.onAppCacheComplete();
            }
        }
    }, 5000);
 }());
