package firebaseapp.cursoandroid.homepaodemel.activity;

import androidx.appcompat.app.AppCompatActivity;
import firebaseapp.cursoandroid.homepaodemel.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgBtnCadastrarCliente;
    private ImageButton imgBtnConsultarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        imgBtnConsultarCliente = findViewById( R.id.imgConsultarId );
        imgBtnCadastrarCliente = findViewById( R.id.imgBtnCadastrarId );

        imgBtnCadastrarCliente.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, CadastroCliente.class );
                startActivity( intent );
            }
        } );
        imgBtnConsultarCliente.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, ConsultaClientes.class );
                startActivity( intent );
            }
        } );
    }
}
