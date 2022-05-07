package alisson.zanoni.nostroristorante.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import alisson.zanoni.nostroristorante.R;
import alisson.zanoni.nostroristorante.model.Comida;
import alisson.zanoni.nostroristorante.model.Reserva;

public class ReservaAdapter extends BaseAdapter {

    private Activity activity;
    private List<Reserva> listaReserva;

    public ReservaAdapter(Activity activity, List<Reserva> listaReserva) {
        this.activity = activity;
        this.listaReserva = listaReserva;
    }

    @Override
    public int getCount() {
        return listaReserva.size();
    }

    @Override
    public Object getItem(int position) {
        return listaReserva.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.lista_reserva, parent, false);
        }

        TextView dataReserva = convertView.findViewById(R.id.idDataReserva);
        dataReserva.setText("Reserva para o dia: "+((Reserva)getItem(position)).getDataReserva());

        TextView qtdPessoas = convertView.findViewById(R.id.idQtdPessoas);
        qtdPessoas.setText("Quantidade de pessoas: "+((Reserva)getItem(position)).getQuantidadePessoas());

        TextView numMesa = convertView.findViewById(R.id.idNumMesa);
        numMesa.setText("Número da mesa: "+((Reserva)getItem(position)).getNumeracaoMesa());

        TextView descObs = convertView.findViewById(R.id.idDescObs);
        descObs.setText("Observações: " + ((Reserva)getItem(position)).getObservacoes());
        return convertView;
    }
}