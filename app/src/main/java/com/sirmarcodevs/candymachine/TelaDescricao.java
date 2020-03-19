package com.sirmarcodevs.candymachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class TelaDescricao extends AppCompatActivity {

    private Spinner quantidadeSpinner;
    private TextView valortotaltextview;
    private String valorform;
    private String produto;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_descricao);

        produto = getIntent().getStringExtra("nome");
        image = (ImageView) findViewById(R.id.imagedescricao);


        valortotaltextview = (TextView) findViewById(R.id.valordescricao);

        TextView nome = (TextView) findViewById(R.id.nomedescricao);
        nome.setText(produto);

        TextView descricao = (TextView) findViewById(R.id.docedescricao);

        Map<String,String> example = new HashMap<String,String>();
        Map<String,Integer> example2 = new HashMap<String,Integer>();

        example.put( "Brigadeiro", new String( "Ingredientes: Leite condensado, chocolate ao leite em pó, manteiga e chocolate granulado." ));
        example.put( "Beijinho", new String( "Ingredientes: Leite condensado, coco ralado, manteiga e açucar cristal." ));
        example2.put("Brigadeiro", R.drawable.briiiiig2);
        example2.put("Beijinho", R.drawable.beij1);

        if ( example.containsKey( produto ) ) {
            System.out.println("Valor da Chave "+produto+
                    " = "+example.get(produto));
            descricao.setText(example.get(produto));

            image.setImageResource(example2.get(produto).intValue());
        }else{
            System.err.println("Chave não existe");
        }

        quantidadeSpinner = (Spinner) findViewById(R.id.spinnerdescricao);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.quantidade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantidadeSpinner.setAdapter(adapter);
        quantidadeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                double valorunit = 0.15;
                double valortotal = (valorunit*(Double.parseDouble(item)));
                valorform = String.valueOf(valortotal).replace(".",",")+"0";
                valortotaltextview.setText("R$ "+valorform);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    Button comprardescricao = (Button) findViewById(R.id.comprardescricao);
        comprardescricao.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            irparacadastro();
        }
    });
    }

    private void irparacadastro() {
        Intent telalogin = new Intent(this,TelaLogin.class);
        String quantidade = quantidadeSpinner.getSelectedItem().toString();
        System.out.println("Quantidade "+quantidade);
        System.out.println("Valor "+valorform);
        System.out.println("Produto "+produto);
        telalogin.putExtra("quantidade ",quantidade);
        telalogin.putExtra("valor ",valorform);
        telalogin.putExtra("produto ",produto);
        this.startActivity(telalogin);
    }

}
