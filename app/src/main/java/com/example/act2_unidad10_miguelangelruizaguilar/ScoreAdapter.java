package com.example.act2_unidad10_miguelangelruizaguilar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {

    private Context context;
    private List<Score> puntuaciones;
    private LayoutInflater inflater;

    public ScoreAdapter(Context context, List<Score> puntuaciones) {
        this.context = context;
        this.puntuaciones = puntuaciones;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return puntuaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return puntuaciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_score, parent, false);  // Asegúrate de tener este layout para los items
        }

        Score puntuacion = puntuaciones.get(position);

        // Referenciar los TextViews donde se mostrarán los datos
        TextView tvNombre = convertView.findViewById(R.id.tvNombre);
        TextView tvPuntuacion = convertView.findViewById(R.id.tvPuntuacion);

        // Asignar el nombre y la puntuación
        tvNombre.setText(puntuacion.getNombre());
        tvPuntuacion.setText(String.valueOf(puntuacion.getPuntuacion()));

        return convertView;
    }
}
