package com.example.admin_sena.practicauno;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Visualizar extends AppCompatActivity
{
    ListView lista;
    SQLiteDatabase db;

    ArrayList<String>arrayList = new ArrayList<String>();
    ArrayList<String>arrayListcedula = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Volver");

        db = Conexion.getDb();
        lista =(ListView)findViewById(R.id.lista_Visualizar);
        ActualizarLista();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent intent = new Intent(Visualizar.this,InfListaVisualizar.class);
                intent.putExtra("cedula",arrayListcedula.get(position));
                startActivity(intent);
            }
        });

    }

    public void ActualizarLista()
    {
        arrayList.clear();
        arrayListcedula.clear();

        Cursor cursor = db.rawQuery("SELECT * FROM Datos",null);
        if (cursor.moveToFirst())
        {
            do
            {
                arrayList.add(cursor.getString(1) + " - " + cursor.getString(2));
                arrayListcedula.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visualizar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
