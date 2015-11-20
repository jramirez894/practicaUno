package com.example.admin_sena.practicauno;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    DrawerLayout drawerLayout;
    ArrayList<ItemsMenuDrawer>drawers = new ArrayList<ItemsMenuDrawer>();
    ListView listaMenuDrawer;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Conexion conexion = new Conexion(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Menu");
        actionBar.getDisplayOptions();
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout =(DrawerLayout)findViewById(R.id.menuDrawer);
        listaMenuDrawer =(ListView)findViewById(R.id.listaMenuDrawer);

        String[] itemsListaMenuDrawer = getResources().getStringArray(R.array.itemMenu);
        drawers.add(new ItemsMenuDrawer(R.mipmap.ic_launcher,itemsListaMenuDrawer[0]));
        drawers.add(new ItemsMenuDrawer(R.mipmap.ic_launcher, itemsListaMenuDrawer[1]));

        listaMenuDrawer.setAdapter(new AdapterMenuDrawer(this, drawers));

        toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout, R.string.open,R.string.close);

        listaMenuDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent intent;

                switch (position)
                {
                    case 0:
                        intent = new Intent(MainActivity.this,Agregar.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this,Visualizar.class);
                        startActivity(intent);
                        break;
                }
            }
        });


    }

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (toggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
