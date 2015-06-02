package com.aware.plugin.getData;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import com.aware.providers.Accelerometer_Provider;

import org.achartengine.model.XYSeries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Melon Griottes Bananes on 30/05/15.
 */
public class DataHandlerGraph {

    /* Checks if external storage is available for read and write */
    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* -------- Probably unnecessary...
    private static File getStorageDir(String name) {


        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), name);
        if (!file.mkdirs()) {
            Log.e("LOG", "Directory not created");
        }
        return file;
    }
    */

    public static tripleSeries getAccelerometerData(ContentResolver resolver, long start, long end) {
        // Test if dir is available
        if (!isExternalStorageWritable())
            return null;

        XYSeries x_series = new XYSeries("X acceleration");
        XYSeries y_series = new XYSeries("Y acceleration");
        XYSeries z_series = new XYSeries("Z acceleration");


        // Query for data
        Cursor cursor = resolver.query(Accelerometer_Provider.Accelerometer_Data.CONTENT_URI,
                new String[]{Accelerometer_Provider.Accelerometer_Data.VALUES_0, Accelerometer_Provider.Accelerometer_Data.VALUES_1, Accelerometer_Provider.Accelerometer_Data.VALUES_2, Accelerometer_Provider.Accelerometer_Data.TIMESTAMP},
                Accelerometer_Provider.Accelerometer_Data.TIMESTAMP + " between " + start + " AND " + end, null, null);

        // Got data ?
        if (!cursor.moveToFirst())
            return null;

        // Data in a string
        //String result = "X            Y             Z\n";
        do {
            //Inspiration
            //frequency_series.add(light_cursor.getPosition(), light_cursor.getDouble(light_cursor.getColumnIndex(Light_Data.LIGHT_LUX)));

            double X = cursor.getDouble(cursor.getColumnIndex(Accelerometer_Provider.Accelerometer_Data.VALUES_0));
            double Y = cursor.getDouble(cursor.getColumnIndex(Accelerometer_Provider.Accelerometer_Data.VALUES_1));
            double Z = cursor.getDouble(cursor.getColumnIndex(Accelerometer_Provider.Accelerometer_Data.VALUES_2));

            x_series.add(cursor.getPosition(),X);
            y_series.add(cursor.getPosition(),Y);
            z_series.add(cursor.getPosition(),Z);


            //result += X + "    " + Y + "    " + Z + "\n";
        } while (cursor.moveToNext());
        cursor.close();

        /* We don't wanna do this here
        // Writing data in a file
        File dir = getStorageDir("Logs");
        File file = new File(dir, "LogAccelerometer.txt");
        try {
            if (!dir.exists())
                dir.createNewFile();
            if (file.exists())
                file.delete();
            file.createNewFile();

            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(fOut);
            myOutWriter.append(result);
            myOutWriter.close();
            fOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        return new tripleSeries(x_series,y_series,z_series);
    }
}
