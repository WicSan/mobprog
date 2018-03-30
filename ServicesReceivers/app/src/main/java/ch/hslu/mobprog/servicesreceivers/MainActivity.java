package ch.hslu.mobprog.servicesreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DemoServiceConnection conn;
    private BroadcastReceiver tempListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter("ACTION_MY_BROADCAST");
        //this.getBaseContext().registerReceiver(new DemoBroadcastReceiver(), filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClickServiceStart(View button){
        Intent service = new Intent(this, DemoService.class);
        startService(service);
    }

    public void onClickServiceStop(View button){
        stopService(new Intent(this, DemoService.class));
    }

    public void onClickIntentServiceStart(View button){
        Intent service = new Intent(this, DemoIntentService.class);
        startService(service);
    }

    public void onClickScanService(View button){
        if(conn != null) {
            DemoService.DemoServiceBinder binder = conn.getBinder();
            binder.getNumberOfJobsCompleted();
            String text = "Service Abfrage-Resultat:\nErledigte Jobs: %d, Laufende Jobs: %d";
            text = String.format(text, binder.getNumberOfJobsCompleted(), binder.getNumberOfJobsRunning());
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Service not bound.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickResetService(View button){
        conn.getBinder().resetCounters();
    }

    public void onClickCheckbox(View checkbox){
        CheckBox cb = (CheckBox)checkbox;
        if(cb.isChecked()) {
            Intent service = new Intent(this, DemoService.class);
            conn = new DemoServiceConnection();
            bindService(service, conn, Context.BIND_AUTO_CREATE);
        }else {
            unbindService(conn);
        }
    }

    public void onClickSendBroadcast(View button){
        Intent broadcast = new Intent("ACTION_MY_BROADCAST");
        sendBroadcast(broadcast);
    }

    public void onClickRegisterBroadcast(View checkbox){
        final TextView text = findViewById(R.id.broadcasts);

        if(((CheckBox)checkbox).isChecked()){
            tempListener = new BroadcastReceiver() {
                private int count = 0;

                @Override
                public void onReceive(Context context, Intent intent) {
                    this.count++;
                    text.setText(String.format("Broadcast #%d erhalten!", count));
                }
            };

            IntentFilter filter = new IntentFilter("ACTION_MY_BROADCAST");
            this.getBaseContext().registerReceiver(tempListener, filter);
        }else{
            text.setText("Broadcastempfang deaktiviert");
            unregisterReceiver(tempListener);
        }
    }
}
