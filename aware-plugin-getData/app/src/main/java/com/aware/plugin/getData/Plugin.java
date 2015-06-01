package com.aware.plugin.getData;

import android.content.Intent;
import android.util.Log;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.aware.utils.Aware_Plugin;

public class Plugin extends Aware_Plugin {

    @Override
    public void onCreate() {
        super.onCreate();
        if( DEBUG ) Log.d(TAG, "getData plugin running");

        //Initialize our plugin's settings
        if( Aware.getSetting(this, Settings.STATUS_PLUGIN_GETDATA).length() == 0 ) {
            Aware.setSetting(this, Settings.STATUS_PLUGIN_GETDATA, true);
        }

        // Activate sensor
        Aware.setSetting(getApplicationContext(), Aware_Preferences.STATUS_ACCELEROMETER, true);
        Aware.setSetting(this, Aware_Preferences.FREQUENCY_ACCELEROMETER, 200000);

        Aware.setSetting(getApplicationContext(), Aware_Preferences.STATUS_GYROSCOPE, true);
        Aware.setSetting(this, Aware_Preferences.FREQUENCY_GYROSCOPE, 200000);

        //Any active plugin/sensor shares its overall context using broadcasts
        CONTEXT_PRODUCER = new ContextProducer() {
            @Override
            public void onContext() {
                //Broadcast your context here
            }
        };

        //To sync data to the server, you'll need to set this variables from your ContentProvider
        //DATABASE_TABLES =
        //TABLES_FIELDS =
        //CONTEXT_URIS = new Uri[]{ }

        //Ask AWARE to apply your settings
        sendBroadcast(new Intent(Aware.ACTION_AWARE_REFRESH));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //This function gets called every 5 minutes by AWARE to make sure this plugin is still running.
        TAG = "getData";
        DEBUG = Aware.getSetting(this, Aware_Preferences.DEBUG_FLAG).equals("true");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if( DEBUG ) Log.d(TAG, "getData plugin terminated");
        Aware.setSetting(this, Settings.STATUS_PLUGIN_GETDATA, false);

        //Deactivate any sensors/plugins you activated here
        Aware.setSetting(getApplicationContext(), Aware_Preferences.STATUS_ACCELEROMETER, false);
        Aware.setSetting(getApplicationContext(), Aware_Preferences.STATUS_GYROSCOPE, false);

        //Ask AWARE to apply your settings
        sendBroadcast(new Intent(Aware.ACTION_AWARE_REFRESH));
    }
}
