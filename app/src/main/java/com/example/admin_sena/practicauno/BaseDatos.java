package com.example.admin_sena.practicauno;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin_Sena on 20/11/2015.
 */
public class BaseDatos extends SQLiteOpenHelper
{
    public String datos = "CREATE TABLE Datos(id INTEGER PRIMARY KEY AUTOINCREMENT, cedula TEXT, nombre TEXT, telefono TEXT )";

    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(datos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(datos);
        db.execSQL("DROP TABLE IFF EXISTS Datos");
    }
}
