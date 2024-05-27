/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package principal;
import principal.Principal;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author SENA
 */
public class Ver extends javax.swing.JPanel {

    DefaultTableModel modelo;
    public Ver() {
        initComponents();
        initAlternComponents();
        
        modelo= (DefaultTableModel)tabla.getModel();
        llenar();
    }
    public void initAlternComponents(){
        
    }
public void llenar(){
    ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET("http://codetesthub.com/API/Obtener.php");
        
        JsonArray lista = JsonParser.parseString(respuesta).getAsJsonArray();
        if(lista!=null){
            modelo.setRowCount(0);
            int contador = 1;
            for (int i = 0; i < lista.size(); i++) {
                JsonObject temp = lista.get(i).getAsJsonObject();
                
                Object[] fila = {
                    contador,
                    temp.get("cedula").getAsString(),
                    temp.get("nombres").getAsString(),
                    temp.get("apellidos").getAsString(),
                    temp.get("telefono").getAsString(),
                    temp.get("direccion").getAsString(),
                    temp.get("email").getAsString(),

                    
                };
                modelo.addRow(fila);
                contador++;
            }
        }else{
            JOptionPane.showMessageDialog(null, "error");
        }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombres", "Apellidos", "Telefono", "Direccion", "Email"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
