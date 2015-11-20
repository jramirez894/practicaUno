package com.example.admin_sena.practicauno;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar extends AppCompatActivity
{
    EditText cedula;
    EditText nombre;
    EditText telefono;

    Button guardar;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Volver");

        db = Conexion.getDb();

        cedula = (EditText)findViewById(R.id.editCedula_Agregar);
        nombre = (EditText)findViewById(R.id.editNombre_Agregar);
        telefono = (EditText)findViewById(R.id.editTelefono_Agregar);

        guardar = (Button)findViewById(R.id.buttonAgregar);

        guardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String capCedula = cedula.getText().toString();
                String capNombre = nombre.getText().toString();
                String capTelefono = telefono.getText().toString();

                if (capCedula.equals("")||
                        capNombre.equals("")||
                        capTelefono.equals(""))

                {
                    Toast.makeText(Agregar.this,"Faltan Datos Llenar",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues values = new ContentValues();
                    values.put("cedula",capCedula);
                    values.put("nombre",capNombre);
                    values.put("telefono",capTelefono);
                    db.insert("Datos", null, values);
                    Toast.makeText(Agregar.this,"Se Agrego Correctamente",Toast.LENGTH_SHORT).show();
                    limpiar();
                }
            }
        });
    }

    public void limpiar ()
    {
        cedula.setText("");
        nombre.setText("");
        telefono.setText("");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar, menu);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
