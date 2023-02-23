package com.example.conversion_unidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Peso extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] items = new String[]{"Tonelada", "Kilogramo", "Gramo", "Onza", "Libra"};
    private Spinner pesosEntrada;
    private Spinner pesosSalida;
    private EditText et_entrada, et_salida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso);
        setTitle("Peso");

        pesosEntrada = (Spinner)findViewById(R.id.spinner_TempEntrada);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Peso.this,
                android.R.layout.simple_spinner_item,items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pesosEntrada.setAdapter(adapter);
        pesosEntrada.setOnItemSelectedListener(this);

        pesosSalida = (Spinner)findViewById(R.id.spinner_tempSalida);
        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(Peso.this,
                android.R.layout.simple_spinner_item,items);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pesosSalida.setAdapter(adapter2);
        pesosSalida.setOnItemSelectedListener(this);

        et_entrada = (EditText) findViewById(R.id.et_entrada);
        et_salida = (EditText) findViewById(R.id.et_salida);
        et_entrada.setText("1");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        double Kg = 0;
        double res = 0;

        if(!validarCampos()) return;

        //Detectamos la Unidad de Longitud a convertir para convertilo a centimetros y con esa base convertirlos a la Unidad de Longitud de salida seleccionada
        switch (pesosEntrada.getSelectedItem().toString()){
            case "Tonelada":
                Kg = Float.parseFloat(et_entrada.getText().toString()) * 1000;
                break;

            case "Kilogramo":
                Kg = Float.parseFloat(et_entrada.getText().toString());
                break;

            case "Gramo":
                Kg = Float.parseFloat(et_entrada.getText().toString()) / 1000;
                break;

            case "Onza":
                Kg = Float.parseFloat(et_entrada.getText().toString()) * 6.35;
            break;

            case "Libra":
                Kg = Float.parseFloat(et_entrada.getText().toString()) / 2.205;
                break;
        }

        //Con la conversión anterior a Kg ahora convertimos ese valor en Kilogramo a la unidad de longitud seleccionada.
        switch (pesosSalida.getSelectedItem().toString()){
            case "Tonelada":
                res = Kg / 1000;
                break;

            case "Kilogramo":
                res = Kg;
                break;

            case "Gramo":
                res = Kg * 1000;
                break;

            case "Onza":
                res = Kg / 6.35;
                break;

            case "Libra":
                res =Kg * 2.205;
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