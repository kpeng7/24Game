package com.example.kevinpeng.a24game;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;

import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    public PriorityQueue<Integer> numholder = new PriorityQueue<>();
    public PriorityQueue<String> mathholder = new PriorityQueue<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void card1clicked(View view) {
        ImageButton buttonclicked = (ImageButton) findViewById(R.id.card1);
        int num = buttonclicked.get;
        numholder.add(num);
        calculate();

    }

    public String calculate() {
        if (numholder.size() == 4 && mathholder.size() == 3) {

        }
    }
}
