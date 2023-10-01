package com.example.trabalho.modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalho.Controlers.ControlerClientes;
import com.example.trabalho.Controlers.ControlerItens;
import com.example.trabalho.Controlers.ControlerPedidos;
import com.example.trabalho.R;
import com.example.trabalho.classes.Clientes;
import com.example.trabalho.classes.Itens;
import com.example.trabalho.classes.Pedidos;

import java.util.ArrayList;

public class LancarPedido extends AppCompatActivity {

    private AutoCompleteTextView tvClientes;
    private Spinner spItem;
    private EditText edQuantidade;
    private EditText edValorUnitario;
    private Button btAdicionarItem;
    private TextView tvListaItensPedidos;
    private TextView tvValorTotalPedidos;
    private Button btPagamento;
    private Button btConfirmarPedidos;
    private TextView tvErroClientes;
    private int posicaoSelecionado = 0;
    private ArrayList<Clientes> clientes;
    private RadioGroup rgSistema;
    private RadioButton rbPagamentoPrazo;
    private RadioButton rbPagamentoVista;
    private Spinner spParcelas;
    private ArrayList<Itens> itensAdd;
    private ArrayList<Itens> listaItens;
    private ArrayList<Clientes> listaClientes;
    private ArrayList<Pedidos> listaQuantidade;
    private ArrayList<Pedidos> listaValores;
    private int quantidadeTotal;
    private int valoresTotais;
    private TextView tvNClientes;
    Pedidos pedidos = new Pedidos();
    private String[] vetPrazo = new String[]{"1x", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x", "11x", "12x",};
    private String[] vetAVista = new String[]{"A vista"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar_pedido);
        tvClientes = findViewById(R.id.tvClientes);
        spItem = findViewById(R.id.spItem);
        edQuantidade = findViewById(R.id.edQuantidade);
        edValorUnitario = findViewById(R.id.edValorUnitario);
        btAdicionarItem = findViewById(R.id.btAdicionarItem);
        tvListaItensPedidos = findViewById(R.id.tvListaItensPedidos);
        rgSistema = findViewById(R.id.rgSistema);
        rbPagamentoPrazo = findViewById(R.id.rbPagamentoPrazo);
        rbPagamentoVista = findViewById(R.id.rbPagamentoVista);
        tvValorTotalPedidos = findViewById(R.id.tvValorTotalPedidos);
        btConfirmarPedidos = findViewById(R.id.btConfirmarPedidos);
        tvErroClientes = findViewById(R.id.tvErroClientes);
        spParcelas = findViewById(R.id.spParcelas);
        tvNClientes = findViewById(R.id.tvNClientes);
        btPagamento = findViewById(R.id.btPagamento);

        preencherListaClientes();
        carregarItens();

        btAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarItem();
                listaItensAdicionados();
            }
        });

        rgSistema.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                pagamento();
            }
        });

        btPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaPedidos();
            }
        });

        btConfirmarPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LancarPedido.this, "Pedido concluido!\n Volte Sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }


    public void preencherListaClientes() {
        clientes = ControlerClientes.getInstanciaCliente().retornarClientes();
        String[] vetorClientes = new String[clientes.size() + 1];
        vetorClientes[0] = "";
        for (int i = 0; i < clientes.size(); i++) {
            Clientes clientes1 = clientes.get(i);
            vetorClientes[i + 1] = clientes1.getNome();
        }

        ArrayAdapter adapter = new ArrayAdapter(
                LancarPedido.this,
                android.R.layout.simple_dropdown_item_1line, vetorClientes);

        tvClientes.setAdapter(adapter);
    }

    private void carregarItens() {
        itensAdd = ControlerItens.getInstanciaItem().retornarItens();
        String[] vetorItens = new String[itensAdd.size()];

        for (int i = 0; i < itensAdd.size(); i++) {
            Itens itens1 = itensAdd.get(i);
            vetorItens[i] = itens1.getNome();
        }
        ArrayAdapter adapter = new ArrayAdapter(LancarPedido.this, android.R.layout.simple_dropdown_item_1line, vetorItens);

        spItem.setAdapter(adapter);
    }

    private void adicionarItem() {
        String itemSelecionado = spItem.getSelectedItem().toString();
        int clienteSelecionado = spItem.getSelectedItemPosition();
        if (clienteSelecionado < 0) {
            Toast.makeText(this, "Um item deve ser selecionado!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edQuantidade.getText().toString().isEmpty()) {
            edQuantidade.setError("Uma quantidade deve ser informada!");
            return;
        }
        if (edValorUnitario.getText().toString().isEmpty()) {
            edValorUnitario.setError("Um valor deve ser informado!");
            return;
        }

        pedidos.setNomeItem(itemSelecionado);
        pedidos.setQuantidade(Integer.parseInt(edQuantidade.getText().toString()));
        pedidos.setValor(Double.parseDouble(edValorUnitario.getText().toString()));
        ControlerPedidos.getControlerPedidos().salvarPedidos(pedidos);

        Toast.makeText(LancarPedido.this, "Item" +
                " adicionado com sucesso!", Toast.LENGTH_LONG).show();

        salvarClientesSelecionados();
        salvarItensSelecionados();
        salvarQuantidadeTotalPedido();
        salvarValorTotalPedido();
        informarCliente();
    }

    private void listaPedidos() {
        int parcelaSelecionada = 1 + spParcelas.getSelectedItemPosition();
        if (rbPagamentoPrazo.isChecked()) {
            tvValorTotalPedidos.setText("Quantidade de parcelas Informadas: " + parcelaSelecionada + "\n" +
                    "Valor das parcelas: " + ((quantidadeTotal * valoresTotais) * 1.05) / parcelaSelecionada + "\n" +
                    "Valor total do pedido: " + (quantidadeTotal * valoresTotais) * 1.05 + "\n");
        } else {
            tvValorTotalPedidos.setText("Quantidade de parcelas Informadas: " + parcelaSelecionada + "\n" +
                    "Valor das parcelas: " + (quantidadeTotal * valoresTotais) * 0.95 + "\n" +
                    "Valor total do pedido: " + (quantidadeTotal * valoresTotais) * 0.95 + "\n");

        }
    }

    private void informarCliente() {
        String texto = "";
        for (Clientes clientes : ControlerClientes.getInstanciaCliente().retornarClientes()) {
            texto += "Cliente: " + clientes.getNome() + "\n" +
                    "-----------------------------------------\n";
        }
        tvNClientes.setText(texto);
    }

    private void salvarItensSelecionados() {
        listaItens = ControlerItens.getInstanciaItem().retornarItens();
        String[] vetItens = new String[listaItens.size()];

        for (int i = 0; i < listaItens.size(); i++) {
            Itens itens = listaItens.get(i);
            vetItens[i] = itens.getNome();
        }
        ArrayAdapter adapter = new ArrayAdapter(LancarPedido.this, android.R.layout.simple_dropdown_item_1line, vetItens);

        spItem.setAdapter(adapter);
    }

    private void pagamento() {
        ArrayAdapter adapter = null;
        if (rbPagamentoPrazo.isChecked()) {
            adapter = new ArrayAdapter<>(LancarPedido.this, android.R.layout.simple_dropdown_item_1line, vetPrazo);
        }
        if (rbPagamentoVista.isChecked()) {
            adapter = new ArrayAdapter<>(LancarPedido.this, android.R.layout.simple_dropdown_item_1line, vetAVista);
        }
        spParcelas.setAdapter(adapter);
    }

    private void listaItensAdicionados() {
        String texto = "";
        ArrayList<Pedidos> lista = ControlerPedidos.getControlerPedidos().retornarPedidos();
        for (Pedidos pedidos : lista) {
            texto += "Item: " + pedidos.getNomeItem() + " - Quantidade: " + pedidos.getQuantidade() + " - Valor total: " + pedidos.getQuantidade() * pedidos.getValor() + "\n";
        }
        tvListaItensPedidos.setText(texto);
    }

    private void salvarClientesSelecionados() {
        listaClientes = ControlerClientes.getInstanciaCliente().retornarClientes();
        String[] vetClientes = new String[listaClientes.size()];

        for (int i = 0; i < listaClientes.size(); i++) {
            Clientes clientes1 = listaClientes.get(i);
            vetClientes[i] = clientes1.getNome();
        }
        ArrayAdapter adapter = new ArrayAdapter(LancarPedido.this, android.R.layout.simple_dropdown_item_1line, vetClientes);

        tvClientes.setAdapter(adapter);
    }


    private void salvarQuantidadeTotalPedido() {
        listaQuantidade = ControlerPedidos.getControlerPedidos().retornarPedidos();
        int[] vetQuantidade = new int[listaQuantidade.size()];

        for (int i = 0; i < listaQuantidade.size(); i++) {
            Pedidos pedidos = listaQuantidade.get(i);
            vetQuantidade[i] = pedidos.getQuantidade();
            quantidadeTotal = vetQuantidade[i] + pedidos.getQuantidade();
        }
    }

    private void salvarValorTotalPedido() {
        listaValores = ControlerPedidos.getControlerPedidos().retornarPedidos();
        Double[] vetValores = new Double[listaValores.size()];

        for (int i = 0; i < listaValores.size(); i++) {
            Pedidos pedidos = listaValores.get(i);
            vetValores[i] = pedidos.getValor();
            valoresTotais = (int) (vetValores[i] + pedidos.getValor());
        }
    }


}