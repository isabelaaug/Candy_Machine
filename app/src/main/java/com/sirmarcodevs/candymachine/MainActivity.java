package com.sirmarcodevs.candymachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listacardapio;
    private CardapioAdapter cardapioadapter;
    private ArrayList<Doce> doces = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();
        initComponents();
    }

    private void initDatabase() {
        Doce doce1 = new Doce("Brigadeiro","Doce de Chocolate Preto",getResources().getDrawable(R.drawable.briiiiig2));
        Doce doce2 = new Doce("Beijinho","Doce de Coco",getResources().getDrawable(R.drawable.beij1));
        doces.add(doce1);
        doces.add(doce2);
    }


    private void initComponents() {
        listacardapio = findViewById(R.id.listacardapio);
        System.out.println("DOCES:"+doces.size());
        cardapioadapter = new CardapioAdapter(this,doces);
        listacardapio.setAdapter(cardapioadapter);
    }

}

