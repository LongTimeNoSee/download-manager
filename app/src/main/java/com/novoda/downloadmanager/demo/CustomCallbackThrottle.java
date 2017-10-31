package com.novoda.downloadmanager.demo;

import com.novoda.notils.logger.simple.Log;
import com.novoda.downloadmanager.CallbackThrottle;
import com.novoda.downloadmanager.DownloadBatchCallback;
import com.novoda.downloadmanager.DownloadBatchStatus;

// Must be public
public class CustomCallbackThrottle implements CallbackThrottle {

    private DownloadBatchCallback callback;

    @Override
    public void setCallback(DownloadBatchCallback callback) {
        Log.v("setCallback");
        this.callback = callback;
    }

    @Override
    public void update(DownloadBatchStatus downloadBatchStatus) {
        Log.v("update " + downloadBatchStatus.getDownloadBatchTitle().asString()
                      + ", progress: " + downloadBatchStatus.percentageDownloaded() + "%");

        if (callback == null) {
            return;
        }

        // no throttle is done, we call the callback imediatelly
        callback.onUpdate(downloadBatchStatus);
    }

    @Override
    public void stopUpdates() {
        Log.v("stopUpdates");
    }
}
