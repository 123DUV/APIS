package principal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class crud extends javax.swing.JFrame {

    JButton[] botones;
    JsonArray lista;

    public crud() {
        initComponents();
        initAlternComponents();
    }

    public void initAlternComponents() {
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public void mostrarInfo(String url) {
        try{
        System.out.println(url);
        ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET(url);
        JsonObject list = JsonParser.parseString(respuesta).getAsJsonObject();
        JsonObject sprites = list.getAsJsonObject("sprites");
        
        System.out.println(lista);

        String imagenUrl = sprites.get("front_shiny").getAsString();
        System.out.println(imagenUrl);
        ImageIcon pokeImagen = new ImageIcon(new URL(imagenUrl));
        Image image = pokeImagen.getImage();
        Image   escalada = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        icono.setIcon(new ImageIcon(escalada    ));
        icono.setBackground(Color.WHITE);
        icono.setHorizontalTextPosition(SwingConstants.CENTER);
        icono.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void cargarBotones() {
        panelBotones.removeAll();
        ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET("https://pokeapi.co/api/v2/pokemon");
        JsonObject list = JsonParser.parseString(respuesta).getAsJsonObject();
        lista = list.getAsJsonArray("results");

        botones = new JButton[lista.size()];
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        for (int i = 0; i < lista.size(); i++) {
            JsonObject pokemon = lista.get(i).getAsJsonObject();
            String nombre = pokemon.get("name").getAsString();
            String url = pokemon.get("url").getAsString();

            //System.out.println(nombre);
            botones[i] = new JButton(nombre);
            botones[i].setPreferredSize(new Dimension(146, botones[i].getPreferredSize().height));
            botones[i].setMaximumSize(new Dimension(146, botones[i].getPreferredSize().height));
            botones[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mostrarInfo(url);
                }
            });

            panelBotones.add(botones[i]);
            System.out.println("Boton agregado: " + nombre);

        }
        pack();
        panelBotones.revalidate();
        panelBotones.repaint();
        System.out.println(lista);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotones = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        icono = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBotones.setBackground(new java.awt.Color(51, 51, 51));
        panelBotones.setFont(new java.awt.Font("Tempus Sans ITC", 2, 18)); // NOI18N

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tempus Sans ITC", 2, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(242, 242, 242));
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        icono.setFont(new java.awt.Font("Tempus Sans ITC", 2, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(icono, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(icono, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarBotones();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icono;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelBotones;
    // End of variables declaration//GEN-END:variables
}
