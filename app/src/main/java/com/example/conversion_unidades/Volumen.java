package com.example.conversion_unidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Volumen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] items = new String[]{"Litro", "Mililitro", "Metro Cubico", "Galon"};
    private Spinner volumenEntrada;
    private Spinner volumenSalida;
    private EditText et_entrada, et_salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen);
        setTitle("Peso");

        volumenEntrada = (Spinner)findViewById(R.id.spinner_volumenEntrada);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Volumen.this,
                android.R.layout.simple_spinner_item,items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        volumenEntrada.setAdapter(adapter);
        volumenEntrada.setOnItemSelectedListener(this);

        volumenSalida = (Spinner)findViewById(R.id.spinner_volumenSalida);
        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(Volumen.this,
                android.R.layout.simple_spinner_item,items);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        volumenSalida.setAdapter(adapter2);
        volumenSalida.setOnItemSelectedListener(this);

        et_entrada = (EditText) findViewById(R.id.et_entrada);
        et_salida = (EditText) findViewById(R.id.et_salida);
        et_entrada.setText("1");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        double Lt = 0;
        double res = 0;

        if(!validarCampos()) return;

        //Detectamos la Unidad de Longitud a convertir para convertilo a Litros y con esa base convertirlos a la Unidad de Longitud de salida seleccionada
        switch (volumenEntrada.getSelectedItem().toString()){
            case "Litro":
                Lt = Float.parseFloat(et_entrada.getText().toString());
                break;

            case "Mililitro":
                Lt = Float.parseFloat(et_entrada.getText().toString()) / 1000;
                break;

            case "Metro Cubico":
                Lt = Float.parseFloat(et_entrada.getText().toString()) * 1000;
                break;

            case "Galon":
                Lt = Float.parseFloat(et_entrada.getText().toString()) * 3.785;
                break;
        }

        //Con la conversión anterior a Kg ahora convertimos ese valor en Kilogramo a la unidad de longitud seleccionada.
        switch (volumenSalida.getSelectedItem().toString()){
            case "Litro":
                res = Lt;
                break;

            case "Mililitro":
                res = Lt * 1000;
                break;

            case "Metro Cubico":
                res = Lt / 1000;
                break;

            case "Galon":
                res = Lt / 3.785;
                break;
        }

        et_salida.setText(Double.toString(res));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    public boolean validarCampos(){
        if(et_entrada.getText().toString().matches("")){
            Toast.makeText(this, "Ingrese un Valor a Convertir", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}