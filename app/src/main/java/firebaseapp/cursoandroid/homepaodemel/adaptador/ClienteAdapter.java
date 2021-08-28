package firebaseapp.cursoandroid.homepaodemel.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import firebaseapp.cursoandroid.homepaodemel.R;
import firebaseapp.cursoandroid.homepaodemel.model.Cliente;

public class ClienteAdapter extends ArrayAdapter<Cliente> {
    private ArrayList<Cliente> client;
    private Context context;


    public ClienteAdapter(@NonNull Context c, @NonNull ArrayList<Cliente> objects) {
        super( c, 0, objects );
        this.client =objects;
        this.context= c;
    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(client!=null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate( R.layout.lista_cliente,parent, false);

            TextView nomeCliente =view.findViewById( R.id.tv_nome );
            TextView valorTotal = view.findViewById( R.id.tv_valorTotal );

            Cliente cliente =client.get( position );
            nomeCliente.setText( cliente.getNome() );
            //valorTotal.setText("R$"+cliente.getValorTotal());
            if(cliente.getValorTotal().equals( "" )){
                valorTotal.setText("Pago");
            }else{
                valorTotal.setText("R$"+cliente.getValorTotal());
            }


        }
        return view;
    }
}
