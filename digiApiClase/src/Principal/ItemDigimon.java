
package Principal;

import com.google.gson.JsonObject;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;


public class ItemDigimon extends javax.swing.JPanel {
    
    JsonObject digimon;//aqui almacenamos la info q viene del array
    String urlImagn;
    String namee;
   
    public ItemDigimon(JsonObject digimon) {
        this.digimon = digimon;
        initComponents();
        initAlternComponents();
    }
    public void initAlternComponents(){
        try {
            String imagenUrl = this.digimon.get("image").getAsString();
            URL url = new URL(imagenUrl);
            Image imagen = getToolkit().createImage(url);
            imagen = imagen.getScaledInstance(197, 181, Image.SCALE_SMOOTH);
            etqImagen.setIcon(new ImageIcon(imagen));
            String nombre = this.digimon.get("name").getAsString();
             
            etqNombre.setText(nombre);
            
        } catch (MalformedURLException ex) {
            System.out.println("Error en URL"+ex.getMessage());
        }
        panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panelPrincipal.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panelPrincipal.setCursor(new Cursor(Cursor.HAND_CURSOR));
                panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                
            }
            @Override
            public void mouseExited(MouseEvent e){
                panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
            }
        });
    }
    
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        etqImagen = new javax.swing.JLabel();
        etqNombre = new javax.swing.JLabel();

        panelPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelPrincipalMouseClicked(evt);
            }
        });

        etqImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        etqNombre.setFont(new java.awt.Font("Tempus Sans ITC", 2, 14)); // NOI18N
        etqNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(etqNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addGap(33, 33, 33))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etqNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void panelPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseClicked
        infoPoke ventana = new infoPoke(digimon);
        terrenos ventanaTerrenos = new terrenos(digimon);
        
        
        System.out.println("click en: "+this.digimon.get("name").getAsString());
        System.out.println("Endpoint: "+this.digimon.get("image").getAsString());
        
    }//GEN-LAST:event_panelPrincipalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel etqImagen;
    private javax.swing.JLabel etqNombre;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
