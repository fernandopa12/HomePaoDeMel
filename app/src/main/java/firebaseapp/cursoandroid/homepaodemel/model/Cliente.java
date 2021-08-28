package firebaseapp.cursoandroid.homepaodemel.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import firebaseapp.cursoandroid.homepaodemel.config.ConfiguracaoFirebase;

public class Cliente {
    private String nome;
    private String telefone;
    private String valorTotal;

    public Cliente() {
    }
    public void salvarCliente() {
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child( "clientes" ).child( getNome() ).setValue( this );
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }
}