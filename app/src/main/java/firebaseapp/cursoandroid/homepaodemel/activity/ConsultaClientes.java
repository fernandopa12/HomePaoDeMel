package firebaseapp.cursoandroid.homepaodemel.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import firebaseapp.cursoandroid.homepaodemel.R;
import firebaseapp.cursoandroid.homepaodemel.adaptador.ClienteAdapter;
import firebaseapp.cursoandroid.homepaodemel.config.ConfiguracaoFirebase;
import firebaseapp.cursoandroid.homepaodemel.model.Cliente;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.LoginFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConsultaClientes extends AppCompatActivity {
    private EditText txtBusca;
    private ListView lvResultado;
    private DatabaseReference firebase;
    private ClienteAdapter adaptador;
    //private ValueEventListener valueEventListenerCliente;
    private ArrayList<Cliente> client;
    private Cliente clienteSelecionado;

    /*@Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener( valueEventListenerCliente );
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener( valueEventListenerCliente );
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_consulta_clientes );
        txtBusca = findViewById( R.id.txtDeBuscaId );

        lvResultado = findViewById( R.id.listViewResultadoId );
        firebase = ConfiguracaoFirebase.getFirebase().child( "clientes" );
        eventoEdit();

        lvResultado.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clienteSelecionado = (Cliente)parent.getItemAtPosition( position );

                Cliente cliente =client.get( position );
                String nomeCliente = ( cliente.getNome() );
                String valorTotal = (cliente.getValorTotal());
                String telefone = (cliente.getTelefone());
                Log.i( "restante$",cliente.getValorTotal() );

                if(clienteSelecionado!=null){
                    Intent intent = new Intent( ConsultaClientes.this, PagamentoClient.class );
                    intent.putExtra( "nome",nomeCliente);
                    intent.putExtra( "valorTotal",valorTotal );
                    intent.putExtra( "telefone",telefone );
                    startActivity( intent );
                }
            }
        } );

         /*valueEventListenerCliente = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                client = new ArrayList<>(  );
                adaptador = new ClienteAdapter( ConsultaClientes.this,client );

                lvResultado.setAdapter( adaptador );
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Cliente clie =dados.getValue(Cliente.class);
                    Log.i("RESULTADO",clie.getNome());
                    client.add(clie);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };*/

    }


    private void eventoEdit() {
        txtBusca.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String palavra = txtBusca.getText().toString().trim();
                pesquisarPalavra( palavra );


            }
        } );
    }

    private void pesquisarPalavra(String palavra) {
        Query query;
        if (palavra.equals( "" )) {
            query = ConfiguracaoFirebase.getFirebase().child( "clientes" ).orderByChild( "nome" );

        } else {
            query = ConfiguracaoFirebase.getFirebase().child( "clientes" ).orderByChild( "nome" ).startAt( palavra ).endAt( palavra+"\uf8ff");
        }
        query.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                client = new ArrayList<Cliente>();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Cliente clie = dados.getValue( Cliente.class );
                    Log.i( "RESULTADO", clie.getNome() );
                    client.add( clie );
                }

                adaptador = new ClienteAdapter( ConsultaClientes.this, client );
                lvResultado.setAdapter( adaptador );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        } );
    }

    @Override
    protected void onResume() {
        super.onResume();
        pesquisarPalavra( "" );
    }
}