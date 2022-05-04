package alisson.zanoni.nostroristorante.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import alisson.zanoni.nostroristorante.R;
import alisson.zanoni.nostroristorante.model.Comida;

public class ComidaAdapter extends BaseAdapter {

    private Activity activity;
    private List<Comida> listaComidas;

    public ComidaAdapter(Activity activity, List<Comida> listaComidas) {
        this.activity = activity;
        this.listaComidas = listaComidas;
    }

    @Override
    public int getCount() {
        return listaComidas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaComidas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.lista_comida, parent, false);
        }

        TextView nomeComida = convertView.findViewById(R.id.idNomeComida);
        nomeComida.setText("Nome: " + ((Comida)getItem(position)).getNome());

        TextView descricaoComida = convertView.findViewById(R.id.idDescricaoComida);
        descricaoComida.setText("Descrição: " + ((Comida)getItem(position)).getDescricao());

        TextView precoComida = convertView.findViewById(R.id.idPrecoComida);
        precoComida.setText("Preço: " + ((Comida)getItem(position)).getPreco());

        return convertView;
    }
}
