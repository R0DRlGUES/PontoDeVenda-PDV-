package com.example.trabalho.modelo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.trabalho.Controlers.ControlerItens;
import com.example.trabalho.R;
import com.example.trabalho.classes.Itens;

public class ItensDeVenda extends AppCompatActivity {
    private EditText edCodigo;
    private Button btCadastroItens;
    private EditText edNomeItem;
    private EditText edDescricao;
    private EditText edValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_de_venda);

        edCodigo = findViewById(R.id.edCodigo);
        edDescricao = findViewById(R.id.edDescricaoItem);
        edValor = findViewById(R.id.edValor);
        btCadastroItens = findViewById(R.id.btCadastroItens);
        edNomeItem = findViewById(R.id.edNomeItem);

        btCadastroItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastroItem();
                onRestart();
            }
        });
    }
    private void cadastroItem() {
        if (edNomeItem.getText().toString().isEmpty()) {
            edNomeItem.setError("Informe o nome do item!");
            return;
        }
        if (edValor.getText().toString().isEmpty()) {
            edValor.setError("O valor do produto deve ser informado!");
            return;
        }
        if (edCodigo.getText().toString().isEmpty()) {
            edCodigo.setError("Código deve ser Informado!");
            return;
        }
        if (edDescricao.getText().toString().isEmpty()) {
            edDescricao.setError("A descrição deve ser infomada!");
            return;
        }

        Itens itens = new Itens();
        itens.setCodigo(Integer.parseInt(edCodigo.getText().toString()));
        itens.setValor(Double.parseDouble(edValor.getText().toString()));
        itens.setNome(edNomeItem.getText().toString());
        itens.setDescricao(edDescricao.getText().toString());
        ControlerItens.getInstanciaItem().salvarItens(itens);
        finish();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(ItensDeVenda.this, "Item" +
                " cadastrado!!", Toast.LENGTH_LONG).show();
    }
}