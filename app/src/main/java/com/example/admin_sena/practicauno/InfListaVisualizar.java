package com.example.admin_sena.practicauno;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

public class InfListaVisualizar extends AppCompatActivity
{

    EditText cedula;
    EditText nombre;
    EditText telefono;

    Button modificar;
    Button eliminar;
    Button guardar;

    SQLiteDatabase db;
    String strCedula ="";
    String id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_lista_visualizar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Volver");

        db = Conexion.getDb();

        Bundle bundle = getIntent().getExtras();
        strCedula = bundle.getString("cedula");


        cedula = (EditText)findViewById(R.id.editCedula_Visualizar);
        nombre = (EditText)findViewById(R.id.editNombre_Visualizar);
        telefono = (EditText)findViewById(R.id.editTelefono_Visualizar);

        llenarDatos();

        modificar = (Button)findViewById(R.id.buttonModificar);
        eliminar = (Button)findViewById(R.id.buttonELiminar);
        guardar = (Button)findViewById(R.id.buttonGuardarM);

        modificar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                cedula.setEnabled(true);
                nombre.setEnabled(true);
                telefono.setEnabled(true);

                modificar.setVisibility(View.GONE);
                eliminar.setVisibility(View.GONE);
                guardar.setVisibility(View.VISIBLE);
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertaDeConfirmacion();
            }
        });

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
                    Toast.makeText(InfListaVisualizar.this, "faltan Datos Por Llenar", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues values = new ContentValues();
                    values.put("cedula",capCedula);
                    values.put("nombre",capNombre);
                    values.put("telefono",capTelefono);
                    db.update("Datos", values, "id='" + id + "'", null);

                    cedula.setEnabled(false);
                    nombre.setEnabled(false);
                    telefono.setEnabled(false);

                    modificar.setVisibility(View.VISIBLE);
                    eliminar.setVisibility(View.VISIBLE);
                    guardar.setVisibility(View.GONE);
                    Toast.makeText(InfListaVisualizar.this, "Se Modifico Correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void  llenarDatos()
    {
        Cursor cursor = db.rawQuery("SELECT * FROM Datos WHERE cedula='"+strCedula+"'",null);

        if (cursor.moveToFirst())
        {
            do
            {
                id = cursor.getString(0);
                cedula.setText(cursor.getString(1));
                nombre.setText(cursor.getString(2));
                telefono.setText(cursor.getString(3));
            }
            while (cursor.moveToNext());
        }
    }

    public void AlertaDeConfirmacion()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Registro");
        builder.setMessage("Seguro");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                db.delete("Datos", "id='" + id + "'", null);
                Toast.makeText(InfListaVisualizar.this, "El Registro Se elimino", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InfListaVisualizar.this,Visualizar.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar",null);
        builder.setCancelable(false);
        builder.show();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inf_lista_visualizar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
