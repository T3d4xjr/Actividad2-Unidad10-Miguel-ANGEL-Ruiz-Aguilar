package com.example.act2_unidad10_miguelangelruizaguilar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UsuarioAdapter extends BaseAdapter {

    private Context context;
    private List<Usuario> usuarios;
    private LayoutInflater inflater;

    public UsuarioAdapter(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
        this.inflater = LayoutInflater.from(context);  // Inflar las vistas de la actividad
    }

    @Override
    public int getCount() {
        return usuarios.size();  // Número de elementos en la lista
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);  // Obtener el usuario en una posición dada
    }

    @Override
    public long getItemId(int position) {
        return position;  // ID de la posición
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflar la vista de item_usuario si no es reutilizada
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_usuario, parent, false);
        }

        // Obtener el usuario en la posición actual
        Usuario usuario = usuarios.get(position);

        // Asignar los datos del usuario a las vistas
        TextView tvNombre = convertView.findViewById(R.id.tvNombre);
        tvNombre.setText(usuario.getNombre());  // Asegúrate de tener un método getNombre() en Usuario

        // Si quieres mostrar más datos, puedes añadir más TextViews y asignarlos aquí

        return convertView;  // Devolver la vista con los datos
    }
}
