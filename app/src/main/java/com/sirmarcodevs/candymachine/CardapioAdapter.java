package com.sirmarcodevs.candymachine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CardapioAdapter extends ArrayAdapter<Doce> {

    private Context mContext;
    private List<Doce> listaDoces = new ArrayList<>();

    public CardapioAdapter(@NonNull Context context, ArrayList<Doce> list) {
        super(context, 0 , list);
        mContext = context;
        listaDoces = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.doce_row,parent,false);

        final Doce doce = listaDoces.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.item_imagem);
        image.setImageDrawable(doce.getImagemdoce());

        TextView nome = (TextView) listItem.findViewById(R.id.item_textonome);
        nome.setText(doce.getNomedoce());

        TextView descricao = (TextView) listItem.findViewById(R.id.item_textodescricao);
        descricao.setText(doce.getDescricaodoce());

        Button botaocomprar = (Button) listItem.findViewById(R.id.item_botaocomprar);
        botaocomprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irparadescricao(doce);
            }
        });

        return listItem;
    }

    private void irparadescricao(Doce doce) {
        Intent teladescricao = new Intent(mContext,TelaDescricao.class);
        teladescricao.putExtra("nome",doce.getNomedoce());
        teladescricao.putExtra("descricao",doce.getDescricaodoce());
        mContext.startActivity(teladescricao);
    }
}