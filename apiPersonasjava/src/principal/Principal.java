package principal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;

public class Principal {

    ConsumoAPI consumo = new ConsumoAPI();
    //endpoint obtener

    public void mostrar() {
        

    }

    public static void main(String[] args) {
        Interfaz hola = new Interfaz();

        //endpoint insertar
        //endpoint act
        //endpoint eliminar
        //extrayendo datos de un json
    }

    public void insertar() {
        Map<String, String> datosInsertar = new HashMap<>();
        datosInsertar.put("cedula", "1128904495");
        datosInsertar.put("nombres", "Duvan");
        datosInsertar.put("apellidos", "Bedoya");
        datosInsertar.put("telefono", "312");
        datosInsertar.put("direccion", "africa");
        datosInsertar.put("email", "correo@gmail.com");
        String respuesta2 = consumo.consumoPOST("http://codetesthub.com/API/Insertar.php", datosInsertar);
        System.out.println("respuesta obtener: " + respuesta2);
    }

    public void editar() {
        Map<String, String> actualizar = new HashMap<>();
        actualizar.put("cedula", "1128904495");
        actualizar.put("nombres", "Duvan");
        actualizar.put("apellidos", "Be");
        actualizar.put("telefono", "312");
        actualizar.put("direccion", "Surafrica");
        actualizar.put("email", "correo@gmail.com");
        String respuesta3 = consumo.consumoPOST("http://codetesthub.com/API/Actualizar.php", actualizar);
        System.out.println("respuesta actualizar: " + respuesta3);
    }

    public void eliminar() {
        Map<String, String> eliminar = new HashMap<>();
        eliminar.put("cedula", "1128904495");
        String respuesta4 = consumo.consumoPOST("http://codetesthub.com/API/Eliminar.php", eliminar);
        System.out.println("respuesta eliminar: " + respuesta4);
    }

}
