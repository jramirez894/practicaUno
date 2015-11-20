package com.example.admin_sena.practicauno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin_Sena on 20/11/2015.
 */
public class AdapterMenuDrawer extends ArrayAdapter
{
    public AdapterMenuDrawer(Context context, List objects)
    {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView ==null)
        {
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.items_menudrawer,null);
        }

        ItemsMenuDrawer itemsMenuDrawer = (ItemsMenuDrawer)getItem(position);

        ImageView icono = (ImageView)convertView.findViewById(R.id.imageIcono);
        TextView descripcion = (TextView)convertView.findViewById(R.id.txtDescripcion);

        icono.setImageResource(itemsMenuDrawer.getIcono());
        descripcion.setText(itemsMenuDrawer.getDescripcion());

        return convertView;
    }
}
