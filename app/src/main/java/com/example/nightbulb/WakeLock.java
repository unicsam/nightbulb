package com.example.nightbulb;
import android.content.Context;
import android.os.PowerManager;

public class WakeLock{
    private PowerManager.WakeLock wakeLock;

    public WakeLock(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "WakeLockHelper::WakeLockTag");
    }

    public void wake() {
        if (wakeLock != null && !wakeLock.isHeld()) {
            wakeLock.acquire();
        }
    }

    public void release() {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
    }
}
