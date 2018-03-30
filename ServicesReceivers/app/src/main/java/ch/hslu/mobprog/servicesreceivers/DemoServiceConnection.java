package ch.hslu.mobprog.servicesreceivers;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.HashMap;

/**
 * Created by sandr on 30.03.2018.
 */

public class DemoServiceConnection implements ServiceConnection {
    private DemoService.DemoServiceBinder binder;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.binder = (DemoService.DemoServiceBinder)service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        this.binder = null;
    }

    public DemoService.DemoServiceBinder getBinder(){
        return binder;
    }
}
