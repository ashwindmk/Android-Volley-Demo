package com.example.ashwin.volleytest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bluelinelabs.logansquare.LoganSquare;
import com.example.ashwin.volleytest.models.Employee;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TAG_STRING_REQUEST = "string_request";
    private ArrayList<Employee> mEmployees;

    private ProgressDialog mProgressDialog;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Constants.DEBUG_LOGGING, TAG + " : onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        startRequest();
    }

    private void startRequest() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        Request request = new StringRequest (
                Request.Method.GET,
                Constants.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(Constants.DEBUG_LOGGING, TAG + " : onResponse() : response : " + response);
                        try {
                            mEmployees = (ArrayList<Employee>) LoganSquare.parseList(response, Employee.class);

                            String str = "";
                            for (Employee e : mEmployees) {
                                str += e.getName() + "\n";
                            }
                            mTextView.setText(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mProgressDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            String errorMessage = new String(error.networkResponse.data, "UTF-8");
                            Log.d(Constants.DEBUG_LOGGING, TAG + " : onErrorResponse() : error : " + errorMessage);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mProgressDialog.hide();
                    }
                }
        );

        // Adding request to request queue
        VolleySingleton.getInstance().addToRequestQueue(request, TAG_STRING_REQUEST);
    }

    @Override
    protected void onDestroy() {
        VolleySingleton.getInstance().cancelPendingRequests(TAG_STRING_REQUEST);
        super.onDestroy();
    }
}
