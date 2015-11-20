package com.example.admin_sena.practicauno;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin_Sena on 20/11/2015.
 */
public class Conexion
{
    public static SQLiteDatabase db;

    public Conexion(Context context)
    {
        BaseDatos baseDatos = new BaseDatos(context,"Ejemplo",null,1);
        db = baseDatos.getWritableDatabase();
    }

    public static SQLiteDatabase getDb()
    {
        return db;
    }
}
