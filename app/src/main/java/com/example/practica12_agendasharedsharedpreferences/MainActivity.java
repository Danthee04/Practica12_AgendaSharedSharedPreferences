package com.example.practica12_agendasharedsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_contacto, et_datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_contacto = (EditText)findViewById(R.id.txt_contacto);
        et_datos = (EditText)findViewById(R.id.txt_datos);
    }
    //METODO PARA BOTON GUARDAR
    public void Guardar(View view){
        String contacto_string = et_contacto.getText().toString();//creamos un objeto de tipo string donde se almacenaran los datos ingresados por el usuario
        String datos_string = et_datos.getText().toString();//creamos un objeto de tipo string donde se almacenaran los datos ingresados por el usuario

        SharedPreferences preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);//creamos el objeto de SharedPreference y usamos agenda como referencia
        SharedPreferences.Editor obj_editor = preferencias.edit();//Creacion del objeto para poder editar nuestras preferencias compartidas
        obj_editor.putString(contacto_string, datos_string);//colocacion de valores, 2 parametros, el primero sera la referencia al dato que vamos a guardar y que ya creamos en forma de string, el segundo parametro son los datos que queremos guardar en este caso los datos y que tambien creamos en una variable string
        obj_editor.commit();//confirmacion de los datos recuperados

        Toast.makeText(this, "El contacto se ha guardado", Toast.LENGTH_SHORT).show();//mostrar etiqueta al usuariuo de que se ha guardado el contacto
    }

    //METODO PARA BOTON BUSCAR
    public void Buscar(View view){
        String contacto_string = et_contacto.getText().toString();//recuperamos el valor/nombre que ingreso el usuario en nuestro EditText

        SharedPreferences preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);//creamos un objeto de tipo string donde referenciamos a  uestro objeto creado previamente con el nombre "agenda"
        String datos = preferencias.getString(contacto_string, "");//vamos a almacenar los datos que posteriormente se van a mostrar del comtacto que nuestrpo usuario esta buscando, el primer parametro indica la referencia para encontrar los datos que estamos guardando y bque teneoms como variable contacto_string, el segundo parametro se coloca el valor que nosotros queremos mostrar en este caso los datos del contacto y basta con solo colocar comillas

        //Estructura condicional creada para que la app envie un mensaje de confirmacion o de advertencia al usuario en caso de haber o no haber guardado el contacto ingresado y en caso de haberse guardado nos muestre los datos del contacto deseado
        if(datos.length() == 0) {
            Toast.makeText(this,"No se encontro ningun registro.",Toast.LENGTH_SHORT).show();
        } else{
            et_datos.setText(datos);//Colocamos los datos almacenados en el objeto Shared Preference en nuestro objeto EditText
        }
    }

}