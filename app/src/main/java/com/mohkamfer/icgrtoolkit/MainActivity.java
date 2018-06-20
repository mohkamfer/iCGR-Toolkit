package com.mohkamfer.icgrtoolkit;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;

import com.mohkamfer.icgrtoolkit.fragment.DecodeFragment;
import com.mohkamfer.icgrtoolkit.fragment.EncodeFragment;
import com.mohkamfer.icgrtoolkit.fragment.GraphFragment;
import com.mohkamfer.icgrtoolkit.fragment.IntegerDecodeFragment;
import com.mohkamfer.icgrtoolkit.fragment.IntegerEncodeFragment;
import com.mohkamfer.icgrtoolkit.fragment.IntegerGraphFragment;

public class MainActivity extends AppCompatActivity {

    private IntegerEncodeFragment iEncode;
    private IntegerDecodeFragment iDecode;
    private IntegerGraphFragment iGraph;
    private EncodeFragment encodeFragment;
    private DecodeFragment decodeFragment;
    private GraphFragment graphFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "Encode CGR",
                        "Decode CGR",
                        "Graph CGR",
                        "Encode iCGR",
                        "Decode iCGR",
                        "Graph iCGR"
                }));

        encodeFragment = new EncodeFragment();
        decodeFragment = new DecodeFragment();
        graphFragment = new GraphFragment();
        iEncode = new IntegerEncodeFragment();
        iDecode = new IntegerDecodeFragment();
        iGraph = new IntegerGraphFragment();

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, encodeFragment)
                            .commit();
                } else if (position == 1) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, decodeFragment)
                            .commit();
                } else if (position == 2) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, graphFragment)
                            .commit();
                } else if (position == 3) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, iEncode)
                            .commit();
                } else if (position == 4) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, iDecode)
                            .commit();
                } else if (position == 5) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, iGraph)
                            .commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }
}
