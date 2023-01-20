package com.example.lcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText precoGasolina;
    private TextInputEditText precoAlcool;

    private TextInputEditText consumoAlcool;
    private TextInputEditText consumoGasolina;
    private TextView exibeKmPorRealGas;
    private TextView exibeKmPorRealAlc;

    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precoGasolina = findViewById(R.id.editValorGasolina);
        precoAlcool = findViewById(R.id.editValorAlcool);
        consumoAlcool = findViewById(R.id.consumo_alcool);
        consumoGasolina = findViewById(R.id.consumo_gasolina);
        exibeKmPorRealGas = findViewById(R.id.exibeKmPorRealGasolina);
        exibeKmPorRealAlc = findViewById(R.id.exibeKmPorRealAlcool);
        res = findViewById(R.id.resultado);
    }

    public void calcularPreco(View view){
        String precoGas = precoGasolina.getText().toString();
        String precoAlc = precoAlcool.getText().toString();
        String consumoAlc = consumoAlcool.getText().toString();
        String consumoGas = consumoGasolina.getText().toString();

        Boolean check = validarCampos(precoAlc, precoGas, consumoAlc, consumoGas);

        if(check){
            float precoGasPorLitro = Float.parseFloat(precoGas);
            float precoAlcoolPorLitro = Float.parseFloat(precoAlc);
            float consAlcoolPorLitro = Float.parseFloat(consumoAlc);
            float consGasPorLitro = Float.parseFloat(consumoGas);

            DecimalFormat df = new DecimalFormat("0.00");
            df.setRoundingMode(RoundingMode.HALF_UP);

            float kmPorRealGas = consGasPorLitro/precoGasPorLitro;
            df.format(kmPorRealGas);
            df.format(kmPorRealGas);
            float kmPorRealAlc = consAlcoolPorLitro/precoAlcoolPorLitro;
            df.format(kmPorRealAlc);

            exibeKmPorRealGas.setText("Custo 1 Km por real de gasolina R$" + df.format(kmPorRealGas));
            exibeKmPorRealAlc.setText("Custo 1 Km por real de álcool R$" + df.format(kmPorRealAlc));

            if (kmPorRealGas < kmPorRealAlc) {
                res.setText("Utilize gasolina");
            } else {
                res.setText("Utilize álcool");
            }
        }else{
            Toast.makeText(this, "Digite valor do preço do álcool ou gasolina ou consumo do veículo",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public boolean validarCampos(String pGasolina, String pAlcool, String consAlc, String consGas) {
        if (pGasolina.length() == 0 || pAlcool.length() == 0
                || consAlc.length() == 0
                || consGas.length() == 0 ){
                return false;
        }
        return true;
    }

    public void limpar(View view){
        precoGasolina.setText("");
        precoAlcool.setText("");
        consumoAlcool.setText("");
        consumoGasolina.setText("");
        res.setText("");
    }

}