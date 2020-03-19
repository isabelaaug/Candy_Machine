package com.sirmarcodevs.candymachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.View;
import android.widget.Button;

public class TelaPedido extends AppCompatActivity {

    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pedido);

        menuButton = (Button) findViewById(R.id.buttonmenu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irparahome();}
        });
    }

    private void irparahome() {
        Intent MainActivity = new Intent(this,MainActivity.class);
        this.startActivity(MainActivity);
    }

}

