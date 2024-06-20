package Principal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;

public class terrenos extends javax.swing.JFrame {

    JsonObject digimon;
    String urlInfo;
    String image;
    String fieldss;

    public terrenos(JsonObject digimon) {
        this.digimon = digimon;
        initComponents();
        initAlternComponents();
    }

    public void initAlternComponents() {
        urlInfo = this.digimon.get("href").getAsString();
        System.out.println(urlInfo);
        
        ConsumoAPI consumo = new ConsumoAPI();
        String endpoint = urlInfo;
        String respuesta = consumo.consumoGET(endpoint);

        JsonObject datos = JsonParser.parseString(respuesta).getAsJsonObject();
        JsonArray terrenoss = datos.getAsJsonArray("fields");
        
        for (int i = 0; i < terrenoss.size(); i++) {
            JsonObject temp = terrenoss.get(i).getAsJsonObject();
             image = temp.get("image").getAsString();
            System.out.println("imagen: "+image);
             fieldss = temp.get("field").getAsString();
            System.out.println("imagen: "+fieldss);
        }
        
            Image imagen = getToolkit().createImage(image);
            imagen = imagen.getScaledInstance(197, 181, Image.SCALE_SMOOTH);
            etqImagen.setIcon(new ImageIcon(imagen));
            String nombre = fieldss;
             
            etqNombre.setText(nombre);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        etqImagen = new javax.swing.JLabel();
        etqNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etqNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etqNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel etqImagen;
    private javax.swing.JLabel etqNombre;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
