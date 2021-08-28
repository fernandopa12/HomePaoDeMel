package firebaseapp.cursoandroid.homepaodemel.activity;

import androidx.appcompat.app.AppCompatActivity;
import firebaseapp.cursoandroid.homepaodemel.R;
import firebaseapp.cursoandroid.homepaodemel.model.Cliente;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class PagamentoClient extends AppCompatActivity {
    private EditText nomeCliente;
    private EditText telefoneCliente;
    private EditText valorCliente;
    private Button btnAtualizarDados;
    private Button btnPagarConta;
    private Cliente client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pagamento_client );

        nomeCliente = findViewById( R.id.NomeId );
        telefoneCliente = findViewById( R.id.TelefoneId );
        valorCliente = findViewById( R.id.valorId );
        btnAtualizarDados = findViewById( R.id.SalvarClientId );
        btnPagarConta = findViewById( R.id.PagamentoId );



        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter( "(NN)NNNNN-NNNN" );
        MaskTextWatcher maskTelefone = new MaskTextWatcher( telefoneCliente, simpleMaskTelefone );
        telefoneCliente.addTextChangedListener( maskTelefone );

        SimpleMaskFormatter simpleMaskValorTotal = new SimpleMaskFormatter( "R$" + "NNNNNNNN" );
        MaskTextWatcher maskValorTotal = new MaskTextWatcher( valorCliente, simpleMaskValorTotal );
        valorCliente.addTextChangedListener( maskValorTotal );

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String txtPassado = extra.getString( "nome" );
            nomeCliente.setText( txtPassado );
            String txtTelefonePassado = extra.getString( "telefone" );
            telefoneCliente.setText( txtTelefonePassado );
            String txtValorPassado = extra.getString( "valorTotal" );
            valorCliente.setText( txtValorPassado );

        }

        btnAtualizarDados.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String telefoneSemFormatacao = telefoneCliente.getText().toString().replace( "(", "" );
                telefoneSemFormatacao = telefoneSemFormatacao.replace( ")", "" );
                telefoneSemFormatacao = telefoneSemFormatacao.replace( "-", "" );

                String valorTotalSemFormatacao = valorCliente.getText().toString().replace( "R", "" );
                valorTotalSemFormatacao = valorTotalSemFormatacao.replace( "$", "" );

                client = new Cliente();
                client.setNome( nomeCliente.getText().toString() );
                client.setTelefone( telefoneSemFormatacao );
                client.setValorTotal( valorTotalSemFormatacao );
                if (client.getNome().equals( "" )) {
                    Toast.makeText( PagamentoClient.this, "Por favor digite o nome do cliente", Toast.LENGTH_LONG ).show();
                } else {
                    client.salvarCliente();
                    Toast.makeText( getApplicationContext(), "Dados Atualizados com sucesso", Toast.LENGTH_LONG ).show();
                    limparCampos();
                }

            }

        } );
        btnPagarConta.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefoneSemFormatacao = telefoneCliente.getText().toString().replace( "(","" );
                telefoneSemFormatacao = telefoneSemFormatacao.replace( ")","" );
                telefoneSemFormatacao = telefoneSemFormatacao.replace( "-","" );

                client = new Cliente();
                client.setNome( nomeCliente.getText().toString() );
                client.setTelefone( telefoneSemFormatacao );
                client.setValorTotal( valorCliente.getText().toString() );
                if(client.getNome().equals( "" )){
                    Toast.makeText( PagamentoClient.this,"Por favor digite o nome do cliente",Toast.LENGTH_LONG ).show();
                }else{
                    client.setValorTotal( "" );
                    client.salvarCliente();
                    Toast.makeText( PagamentoClient.this, "Pagamento efetuado com sucesso!", Toast.LENGTH_LONG).show();
                    limparCampos();
                }

            }
        } );
    }

    public void limparCampos() {
        nomeCliente.setText( "" );
        telefoneCliente.setText( "" );
        valorCliente.setText( "" );

    }
}

