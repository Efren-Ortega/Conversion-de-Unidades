package com.example.conversion_unidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Longitud extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    String[] items = new String[]{"Kilómetro", "Metro", "Centímetro", "Milímetro", "Milla", "Pie", "Yarda", "Pulgada"};
    private Spinner longitudesEntrada;
    private Spinner longitudesSalida;
    private int selected_1;
    private int selected_2;
    private EditText et_entrada, et_salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longitud);
        setTitle("Longitud");

        longitudesEntrada = (Spinner)findViewById(R.id.spinner_TempEntrada);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(Longitud.this,
                android.R.layout.simple_spinner_item,items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        longitudesEntrada.setAdapter(adapter);
        longitudesEntrada.setOnItemSelectedListener(this);

        longitudesSalida = (Spinner)findViewById(R.id.spinner_tempSalida);
        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(Longitud.this,
                android.R.layout.simple_spinner_item,items);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        longitudesSalida.setAdapter(adapter2);
        longitudesSalida.setOnItemSelectedListener(this);

        et_entrada = (EditText) findViewById(R.id.et_entrada);
        et_salida = (EditText) findViewById(R.id.et_salida);
        et_entrada.setText("1");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        double cm = 0;
        double res = 0;

        //Detectamos la Unidad de Longitud a convertir para convertilo a centimetros y con esa base convertirlos a la Unidad de Longitud de salida seleccionada
        switch (longitudesEntrada.getSelectedItem().toString()){
            case "Kilómetro":
                cm = Float.parseFloat(et_entrada.getText().toString()) * 100000;
                break;

            case "Metro":
                cm = Float.parseFloat(et_entrada.getText().toString()) * 100;
                break;

            case "Centímetro":
                cm = Float.parseFloat(et_entrada.getText().toString());
                break;

            case "Milímetro":
                cm = Float.parseFloat(et_entrada.getText().toString()) / 10;
                break;

            case "Milla":
                cm = Float.parseFloat(et_entrada.getText().toString()) * 160900;
                break;

            case "Pie":
                cm = Float.parseFloat(et_entrada.getText().toString()) * 30.48;
                break;

            case "Yarda":
                cm = Float.parseFloat(et_entrada.getText().toString()) * 91.44;
                break;

            case "Pulgada":
                cm = Float.parseFloat(et_entrada.getText().toString()) * 2.54;
                break;
        }

        //Con la conversión anterior a CM ahora convertimos ese valor en Centimetro al la unidad de longitud seleccionada.
        switch (longitudesSalida.getSelectedItem().toString()){
            case "Kilómetro":
                res = cm / 100000;
                break;

            case "Metro":
                res = cm / 100;
                break;

            case "Centímetro":
                res = cm;
                break;

            case "Milímetro":
                res = cm * 10;
                break;

            case "Milla":
                res = cm / 160900;
                break;

            case "Pie":
                res = cm / 30.48;
                break;

            case "Yarda":
                res = cm / 91.44;
                break;

            case "Pulgada":
                res = cm / 2.54;
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