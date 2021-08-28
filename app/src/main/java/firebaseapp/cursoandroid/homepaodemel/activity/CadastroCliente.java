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

public class CadastroCliente extends AppCompatActivity {
    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtValorTotal;
    private Button btnSalvarCliente;
    private Cliente client;
    private Button btnPagamentoEfetuado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cadastro_cliente );
        txtNome = findViewById( R.id.txtNomeId );
        txtTelefone = findViewById( R.id.txtTelefoneId );
        txtValorTotal = findViewById( R.id.valorTotalId );
        btnSalvarCliente = findViewById( R.id.btnSalvarClienteId );
        //btnPagamentoEfetuado = findViewById( R.id.btnPamentoId );



        /*SimpleMaskFormatter simpleMaskNome = new SimpleMaskFormatter( "U" );
        MaskTextWatcher maskNome = new MaskTextWatcher( txtNome,simpleMaskNome );
        txtNome.addTextChangedListener( maskNome );*/

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter( "(NN)NNNNN-NNNN" );
        MaskTextWatcher maskTelefone = new MaskTextWatcher( txtTelefone,simpleMaskTelefone );
        txtTelefone.addTextChangedListener( maskTelefone );

        SimpleMaskFormatter simpleMaskValorTotal = new SimpleMaskFormatter("R$"+"NNNNNNNN" );
        MaskTextWatcher maskValorTotal = new MaskTextWatcher( txtValorTotal,simpleMaskValorTotal );
        txtValorTotal.addTextChangedListener( maskValorTotal );

        /*Bundle extra = getIntent().getExtras();
        if(extra!=null){
            String txtPassado = extra.getString( "nome" );
            txtNome.setText( txtPassado );
            String txtTelefonePassado = extra.getString( "telefone" );
            txtTelefone.setText( txtTelefonePassado );
            String txtValorPassado = extra.getString( "valorTotal" );
            txtValorTotal.setText( txtValorPassado );

        }*/
        btnSalvarCliente.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String telefoneSemFormatacao = txtTelefone.getText().toString().replace( "(","" );
                    telefoneSemFormatacao = telefoneSemFormatacao.replace( ")","" );
                    telefoneSemFormatacao = telefoneSemFormatacao.replace( "-","" );

                    String valorTotalSemFormatacao = txtValorTotal.getText().toString().replace( "R","" );
                    valorTotalSemFormatacao = valorTotalSemFormatacao.replace( "$","" );

                    client = new Cliente();
                    client.setNome( txtNome.getText().toString() );
                    client.setTelefone( telefoneSemFormatacao );
                    client.setValorTotal( valorTotalSemFormatacao );
                    if(client.getNome().equals( "" )){
                        Toast.makeText( CadastroCliente.this,"Por favor digite o nome do cliente",Toast.LENGTH_LONG ).show();
                    }else{
                        client.salvarCliente();
                        Toast.makeText( getApplicationContext(), "Cliente cadastrado com sucesso", Toast.LENGTH_LONG).show();
                        limparCampos();
                    }

                }
            });
/*        btnPagamentoEfetuado.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefoneSemFormatacao = txtTelefone.getText().toString().replace( "(","" );
                telefoneSemFormatacao = telefoneSemFormatacao.replace( ")","" );
                telefoneSemFormatacao = telefoneSemFormatacao.replace( "-","" );

                client = new Cliente();
                client.setNome( txtNome.getText().toString() );
                client.setTelefone( telefoneSemFormatacao );
                client.setValorTotal( txtValorTotal.getText().toString() );
                if(client.getNome().equals( "" )){
                    Toast.makeText( CadastroCliente.this,"Por favor digite o nome do cliente",Toast.LENGTH_LONG ).show();
                }else{
                    client.setValorTotal( "" );
                    client.salvarCliente();
                    Toast.makeText( getApplicationContext(), "Pagamento efetuado com sucesso!", Toast.LENGTH_LONG).show();
                    limparCampos();
                }

            }
        } );*/

        }

    public void limparCampos(){
        txtNome.setText( "" );
        txtTelefone.setText( "" );
        txtValorTotal.setText( "" );
    }
}