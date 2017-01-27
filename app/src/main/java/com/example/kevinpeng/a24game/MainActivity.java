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
import java.util.HashSet;
import java.util.Random;

import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    public PriorityQueue<Integer> numholder = new PriorityQueue<>();
    public PriorityQueue<String> mathholder = new PriorityQueue<>();
    private String[] cards = new String[52];
    private int card1;
    private int card2;
    private int card3;
    private int card4;

    private void addAllCards() {
        for (int i = 0; i < 52; i++) {
            String str = "";
            switch(i % 13) {
                case 0: str += "ace_of_";
                case 1: str += "two_of_";
                case 2: str += "three_of_";
                case 3: str += "four_of_";
                case 4: str += "five_of_";
                case 5: str += "six_of_";
                case 6: str += "seven_of_";
                case 7: str += "eight_of_";
                case 8: str += "nine_of_";
                case 9: str += "ten_of_";
                case 10: str += "jack_of_";
                case 11: str += "queen_of_";
                case 12: str += "king_of_";
            }
            switch(i / 52) {
                case 0: str += "clubs.png";
                case 1: str += "diamonds.png";
                case 2: str += "hearts.png";
                case 3: str += "spades.png";
            }
            cards[i] = str;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addAllCards();
        startGame();
    }

    private void startGame() {
        int[] cards = new int[4];
        HashSet<Integer> randomCards = new HashSet<Integer>();
        Random rand = new Random();
        while (randomCards.size() < 4) {
            randomCards.add(rand.nextInt(52));
        }
        int count = 0;
        for (Integer i: randomCards) {
            switch(count) {
                case 0: card1 = i;
                case 1: card2 = i;
                case 2: card3 = i;
                case 3: card4 = i;
            }
        }
        ImageButton firstCard = (ImageButton) findViewById(R.id.card1);
        String url = "drawable/" + cards[card1];
        int imageKey = getResources().getIdentifier(url, "drawable", getPackageName());
        firstCard.setImageResource(imageKey);
        ImageButton secondCard = (ImageButton) findViewById(R.id.card1);
        url = "drawable/" + cards[card2];
        imageKey = getResources().getIdentifier(url, "drawable", getPackageName());
        secondCard.setImageResource(imageKey);
        ImageButton thirdCard = (ImageButton) findViewById(R.id.card1);
        url = "drawable/" + cards[card1];
        imageKey = getResources().getIdentifier(url, "drawable", getPackageName());
        thirdCard.setImageResource(imageKey);
        ImageButton fourthCard = (ImageButton) findViewById(R.id.card1);
        url = "drawable/" + cards[card1];
        imageKey = getResources().getIdentifier(url, "drawable", getPackageName());
        fourthCard.setImageResource(imageKey);
    }

    public void card1clicked(View view) {
        ImageButton buttonclicked = (ImageButton) findViewById(R.id.card1);
        buttonclicked.setEnabled(false);
        int num = card1 % 13 + 1;
        numclicked(num);
    }

    public void card2clicked(View view) {
        ImageButton buttonclicked = (ImageButton) findViewById(R.id.card2);
        buttonclicked.setEnabled(false);
        int num = card2 % 13 + 1;
        numclicked(num);
    }

    public void card3clicked(View view) {
        ImageButton buttonclicked = (ImageButton) findViewById(R.id.card3);
        buttonclicked.setEnabled(false);
        int num = card3 % 13 + 1;
        numclicked(num);
    }

    public void card4clicked(View view) {
        ImageButton buttonclicked = (ImageButton) findViewById(R.id.card4);
        buttonclicked.setEnabled(false);
        int num = card4 % 13 + 1;
        numclicked(num);
    }

    public void plusclicked(View view) {
        Button buttonclicked = (Button) findViewById(R.id.plus);
        String op = "+";
        opclicked(op);
    }

    public void minusclicked(View view) {
        Button buttonclicked = (Button) findViewById(R.id.minus);
        String op = "-";
        opclicked(op);
    }

    public void numclicked(int num) {
        numholder.add(num);
        int ans = calculate();
        if (ans == 24) {
            TextView winning = (TextView) findViewById(R.id.winmessage);
            winning.setText("You Won!");
        }
    }

    public void opclicked(String op) {
        mathholder.add(op);
        int ans = calculate();
        if (ans == 24) {
            TextView winning = (TextView) findViewById(R.id.winmessage);
            winning.setText("You Won!");
        }

    }

    public int calculate() {
        int value = 0;
        if (numholder.size() == 4 && mathholder.size() == 3) {
            int first = numholder.poll();
            int second = numholder.poll();
            String firstop = mathholder.poll();
            value = operation(firstop, first, second);
            int third = numholder.poll();
            String secondop = mathholder.poll();
            value = operation(secondop, value, third);
            int fourth = numholder.poll();
            String thirdop = mathholder.poll();
            value = operation(thirdop, value, fourth);
        }
        return value;
    }

    public int operation(String op, int a, int b) {
        if (op == "+") {
            return a + b;
        } else if (op == "-") {
            return a - b;
        } else if (op == "*") {
            return a * b;
        } else {
            return a / b;
        }
    }

    public void clear(View view) {
        numholder.clear();
        mathholder.clear();
        ImageButton buttonclicked1 = (ImageButton) findViewById(R.id.card1);
        ImageButton buttonclicked2 = (ImageButton) findViewById(R.id.card2);
        ImageButton buttonclicked3 = (ImageButton) findViewById(R.id.card3);
        ImageButton buttonclicked4 = (ImageButton) findViewById(R.id.card4);
        buttonclicked1.setEnabled(true);
        buttonclicked2.setEnabled(true);
        buttonclicked3.setEnabled(true);
        buttonclicked4.setEnabled(true);
    }

    public void reset(View view) {

    }





}
