
package Principal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.BorderLayout;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class terrenos2 extends javax.swing.JPanel {

    
    private String field;
    private String imageUrl;
  

    public terrenos2(String field, String imageUrl) {
        this.field = field;
        this.imageUrl = imageUrl;
        initComponents();
        initAlternComponents();
    }  
    
    public void initAlternComponents(){
        
             try {
            
            URL url = new URL(imageUrl);
            Image imagen = getToolkit().createImage(url);
            imagen = imagen.getScaledInstance(197, 181, Image.SCALE_SMOOTH);
            etqImagen.setIcon(new ImageIcon(imagen));
            
             
            etqNombre.setText(field);
            
        } catch (MalformedURLException ex) {
            System.out.println("Error en URL"+ex.getMessage());
        }
    }

   

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etqImagen = new javax.swing.JLabel();
        etqNombre = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etqNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(etqImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etqNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel etqImagen;
    private javax.swing.JLabel etqNombre;
    // End of variables declaration//GEN-END:variables
}
