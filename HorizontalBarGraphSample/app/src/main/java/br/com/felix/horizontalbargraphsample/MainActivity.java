package br.com.felix.horizontalbargraphsample;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.felix.horizontalbargraph.HorizontalBar;
import br.com.felix.horizontalbargraph.model.BarItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HorizontalBar horizontal = findViewById(R.id.horizontal);
        horizontal.init(this).hasAnimation(true).addAll(itens()).build();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                horizontal.add(new BarItem("xxx", 500d));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        horizontal.add(new BarItem("yyy", 1000d));
                    }
                }, 5000);
            }
        }, 5000);

    }

    private List<BarItem> itens() {
        List<BarItem> items = new ArrayList<>();

        int i = 0;
        items.add(new BarItem("Teste " + i, 250d));
        i++;

        items.add(new BarItem("Teste " + i, 800d, 550d));
        i++;

        items.add(new BarItem("Teste " + i, 750d, Color.RED, Color.WHITE));
        i++;

        items.add(new BarItem("Teste " + i, 800d, 600d, Color.RED, Color.GREEN, Color.WHITE, Color.WHITE));

        return items;
    }
}
