package com.example.drinksdexjava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesDialogActivity extends AppCompatActivity {
    private Spinner spinBase, spinTemp, spinTamanho;
    private Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_characteristics_selection);

        // Vínculos
        spinBase = findViewById(R.id.alcoholTypeSelect);
        spinTemp = findViewById(R.id.tempTypeSelect);
        spinTamanho = findViewById(R.id.categoryTypeSelect);
        btnConfirmar = findViewById(R.id.addCategoryButton);

        // Lógica do Botão Confirmar
        btnConfirmar.setOnClickListener(v -> {
            // 1. Coletar o que está selecionado em cada um
            String base = spinBase.getSelectedItem().toString();
            String ocasiao = spinTemp.getSelectedItem().toString();
            String tamanho = spinTamanho.getSelectedItem().toString();

            // 2. Empacotar tudo para devolução
            Intent returnIntent = new Intent();
            returnIntent.putExtra("res_base", base);
            returnIntent.putExtra("res_ocasiao", ocasiao);
            returnIntent.putExtra("res_tamanho", tamanho);

            // 3. Carimbar como OK e fechar
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}
