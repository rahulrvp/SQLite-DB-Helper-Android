package com.github.rahulrvp.sqlite_db_helper_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahulrvp.android_utils.TextViewUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.query_output_text);

        /**
         * Sample code to demonstrate SQL query generation (CREATE TABLE).
         */
        String query = new SQLiteChildTable().buildQuery();
        TextViewUtils.setText(textView, query);
    }
}
