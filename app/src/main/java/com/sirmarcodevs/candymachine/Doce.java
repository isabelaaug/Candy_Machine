package com.sirmarcodevs.candymachine;

import android.graphics.drawable.Drawable;

public class Doce {
    private String nomedoce;
    private String descricaodoce;
    private Drawable imagemdoce;

    public Doce(String nomedoce, String descricaodoce, Drawable imagemdoce) {
        this.nomedoce = nomedoce;
        this.descricaodoce = descricaodoce;
        this.imagemdoce = imagemdoce;
    }

    public String getNomedoce() {
        return nomedoce;
    }

    public void setNomedoce(String nomedoce) {
        this.nomedoce = nomedoce;
    }

    public String getDescricaodoce() {
        return descricaodoce;
    }

    public void setDescricaodoce(String descricaodoce) {
        this.descricaodoce = descricaodoce;
    }

    public Drawable getImagemdoce() {
        return imagemdoce;
    }

    public void setImagemdoce(Drawable imagemdoce) {
        this.imagemdoce = imagemdoce;
    }
}
