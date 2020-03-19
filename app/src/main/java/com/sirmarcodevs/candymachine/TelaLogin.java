package com.sirmarcodevs.candymachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TelaLogin extends AppCompatActivity {

    private EditText telefonetextfield;
    private EditText cpftextfield;
    private EditText nometextfield;
    private String lastChar;
    private String lastChart;
    private EditText ceptextfield;
    private String lastChar2;
    private EditText endtextfield;
    private EditText comptextfield;
    private EditText emailtextfield;
    private String produtoView;
    private TextView prod;
    private String quantidadeView;
    private TextView quant;
    private String valorView;
    private TextView valor;
    private String telefonecasdastro;
    private String cpfcasdastro;
    private String cepcasdastro;
    private String emailcasdastro;
    private String endcasdastro;
    private String compcasdastro;
    private String nomecasdastro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        Button botaoconfirmar = (Button) findViewById(R.id.botaoconfirmar);
        botaoconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irparaconfirmacao();
            }
        });

        produtoView = getIntent().getStringExtra("produto ");
        prod = (TextView) findViewById(R.id.produtoView);
        prod.setText(produtoView);

        quantidadeView = getIntent().getStringExtra("quantidade ");
        quant = (TextView) findViewById(R.id.quantidadeView);
        quant.setText(quantidadeView);

        valorView = getIntent().getStringExtra("valor ");
        valor = (TextView) findViewById(R.id.valorView);
        valor.setText("R$"+valorView);

        emailtextfield = (EditText) findViewById(R.id.emailtextfield);
        endtextfield = (EditText) findViewById(R.id.enderecotextfield);
        comptextfield = (EditText) findViewById(R.id.comptextfield);
        nometextfield =  (EditText) findViewById(R.id.nometextfield);

        telefonetextfield = (EditText) findViewById(R.id.Telefonetextfield);
        lastChar = "";

        telefonetextfield.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int digits = telefonetextfield.getText().toString().length();

                if (digits > 1){
                    lastChar = telefonetextfield.getText().toString().substring(digits-1);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int digits = telefonetextfield.getText().toString().length();
                String data = PhoneNumberUtils.formatNumber(telefonetextfield.getText().toString(), "55");
                Log.d("data",""+data);
                Log.d("LENGTH",""+digits);

                if (!lastChar.equals("-")) {
                    if (digits == 5) {
                        telefonetextfield.append("-");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cpftextfield = (EditText) findViewById(R.id.CPFtextfield);
        lastChart = "";

        cpftextfield.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int digits = cpftextfield.getText().toString().length();

                if (digits > 1){
                    lastChart = cpftextfield.getText().toString().substring(digits-1);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int digits = cpftextfield.getText().toString().length();
                Log.d("LENGTH",""+digits);
                if (!lastChart.equals("-")) {
                    if (digits == 3 || digits == 7) {
                        cpftextfield.append(".");
                    }
                    if (digits == 11) {
                        cpftextfield.append("-");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ceptextfield = (EditText) findViewById(R.id.ceptextfield);
        lastChar2 = "";

        ceptextfield.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int digits = ceptextfield.getText().toString().length();

                if (digits > 1){
                    lastChar2 = ceptextfield.getText().toString().substring(digits-1);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int digits = ceptextfield.getText().toString().length();
                Log.d("LENGTH",""+digits);
                if (!lastChar2.equals("-")) {
                    if (digits == 5) {
                        ceptextfield.append("-");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void irparaconfirmacao() {
        Intent telapedido = new Intent(this,TelaPedido.class);
        this.startActivity(telapedido);
        lercontador();


    }

    private void lercampos(String contador){

        emailcasdastro = emailtextfield.getText().toString();
        compcasdastro = comptextfield.getText().toString();
        endcasdastro = endtextfield.getText().toString();
        telefonecasdastro = telefonetextfield.getText().toString();
        cpfcasdastro = cpftextfield.getText().toString();
        cepcasdastro = ceptextfield.getText().toString();
        nomecasdastro = nometextfield.getText().toString();

        int contatual = Integer.parseInt(contador);
        contatual = contatual+1;
        String contatualString = String.valueOf(contatual);
        Map<String, String> userData = new HashMap<String, String>();

        userData.put("numero_pedido",contatualString);
        userData.put("nome",nomecasdastro);
        userData.put("cpf",cpfcasdastro);
        userData.put("endereco",endcasdastro);
        userData.put("complemento",compcasdastro);
        userData.put("cep",cepcasdastro);
        userData.put("email",emailcasdastro);
        userData.put("telefone",telefonecasdastro);
        userData.put("produto",produtoView);
        userData.put("quantidade",quantidadeView);
        userData.put("valor",valorView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("pedidos-pendentes").child(contatualString);
        myRef.setValue(userData);
        DatabaseReference contadorRef = database.getReference("contador-pedidos");
        contadorRef.setValue(contatual);

    }

    private void lercontador (){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference contadorRef = database.getReference("contador-pedidos");
        contadorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String contador = dataSnapshot.getValue().toString();
                lercampos(contador);
                System.out.println(contador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


