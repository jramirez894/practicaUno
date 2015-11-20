package com.example.admin_sena.practicauno;

import android.widget.TextView;

/**
 * Created by Admin_Sena on 20/11/2015.
 */
public class ItemsMenuDrawer
{
    int icono;
    String descripcion;

    public ItemsMenuDrawer(int icono, String descripcion) {
        this.icono = icono;
        this.descripcion = descripcion;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
