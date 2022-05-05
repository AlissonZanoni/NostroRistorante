package alisson.zanoni.nostroristorante.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import alisson.zanoni.nostroristorante.R;
import alisson.zanoni.nostroristorante.model.Mesa;
import alisson.zanoni.nostroristorante.model.Reserva;

public class MesaAdapter extends BaseAdapter {

    private Activity activity;
    private List<Mesa> listaMesa;

    public MesaAdapter(Activity activity, List<Mesa> listaMesa) {
        this.activity = activity;
        this.listaMesa= listaMesa;
    }

    @Override
    public int getCount() {
        return listaMesa.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMesa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.lista_mesa, parent, false);
        }
            TextView idMesa = convertView.findViewById(R.id.idMesa);
            idMesa.setText("Mesa "+((Mesa)getItem(position)).getNumeracao() +" : " + ((Mesa)getItem(position)).getQuantidadeCadeiras()+" Lugares");

            TextView obsMesa = convertView.findViewById(R.id.idDescMesa);
            obsMesa.setText("Localização: "+((Mesa)getItem(position)).getDescricao());

        return convertView;
    }
}