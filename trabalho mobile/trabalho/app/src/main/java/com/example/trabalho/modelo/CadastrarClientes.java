package com.example.trabalho.modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalho.Controlers.ControlerClientes;
import com.example.trabalho.R;
import com.example.trabalho.classes.Clientes;

public class CadastrarClientes extends AppCompatActivity {


    private EditText edCpfCliente;
    private EditText edNomeCliente;
    private Button btCadastroClientes;
    private TextView tvListaDClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_clientes);
        edNomeCliente = findViewById(R.id.edNomeCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);
        tvListaDClientes = findViewById(R.id.tvListaDClientes);
        btCadastroClientes = findViewById(R.id.btCadastroClientes);

        atualizarClientes();

        btCadastroClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarClientes();
            }
        });
    }

    private void salvarClientes() {
        int cpf;
        if (edCpfCliente.getText().toString().isEmpty()) {
            edCpfCliente.setError("O CPF do Cliente deve ser informado!!");
            return;
        } else {
            try {
                cpf = Integer.parseInt(edCpfCliente.getText().toString());
            } catch (Exception ex) {
                edCpfCliente.setError("Texto invalido, informe semente Número!");
                return;
            }
        }
        if (edNomeCliente.getText().toString().isEmpty()) {
            edNomeCliente.setError("O nome do Cliente deve ser informado!");
            return;
        }

        Clientes clientes = new Clientes();
        clientes.setNome(edNomeCliente.getText().toString());
        clientes.setCpf(edCpfCliente.getText().toString());

        ControlerClientes.getInstanciaCliente().salvarClientes(clientes);

        Toast.makeText(CadastrarClientes.this,"Cliente" +
                " cadastrado!",Toast.LENGTH_LONG).show();

        finish();
    }

    private void atualizarClientes() {
        String dadosCliente = "";
        for (Clientes clientes : ControlerClientes.getInstanciaCliente().retornarClientes()) {
            dadosCliente += "Nome: " + clientes.getNome() + "\n" +
                    "CPF: " + clientes.getCpf() + "\n" +
                    "-----------------------------------------\n";
        }
        tvListaDClientes.setText(dadosCliente);

    }
}