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
import javax.swing.table.DefaultTableModel;

public class crud extends javax.swing.JFrame {

    JButton[] botones;
    JsonArray lista;
    String siguiente;
    String anterior;
    DefaultTableModel modelo;

    public crud() {
        initComponents();
        initAlternComponents();

        modelo = (DefaultTableModel) tabla.getModel();
    }

    public void initAlternComponents() {
        setVisible(true);
        setLocationRelativeTo(null);
        cargarBotones();
       pack();

    }

    public void mostrarPoke(String url) {
        try {
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
            Image escalada = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            icono.setIcon(new ImageIcon(escalada));
            icono.setBackground(Color.WHITE);
            icono.setHorizontalTextPosition(SwingConstants.CENTER);
            icono.setVerticalTextPosition(SwingConstants.BOTTOM);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void cargarBotones() {
        panelBotones.removeAll();
        ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET("https://pokeapi.co/api/v2/pokemon");
        JsonObject list = JsonParser.parseString(respuesta).getAsJsonObject();
        siguiente = list.get("next").getAsString();
        
        lista = list.getAsJsonArray("results");
        System.out.println(list);

        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        botones = new JButton[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            JsonObject pokemon = lista.get(i).getAsJsonObject();
            String nombre = pokemon.get("name").getAsString();
            String url = pokemon.get("url").getAsString();

            System.out.println(nombre);

            botones[i] = new JButton(nombre);
            botones[i].setPreferredSize(new Dimension(146, botones[i].getPreferredSize().height));
            botones[i].setMaximumSize(new Dimension(146, botones[i].getPreferredSize().height));
            botones[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mostrarPoke(url);
                    mostrarInfo(url);
                }
            });

            panelBotones.add(botones[i]);
            System.out.println("Boton agregado: " + nombre);

        }

        panelBotones.revalidate();
        panelBotones.repaint();
        System.out.println(lista);

    }

    

    public void mostrarInfo(String url) {
        System.out.println(url);
        ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET(url);
        JsonObject list = JsonParser.parseString(respuesta).getAsJsonObject();
        JsonArray habilidades = list.getAsJsonArray("abilities");
        System.out.println(habilidades);
        modelo.setRowCount(0);
        int contador = 1;
        for (int i = 0; i < habilidades.size(); i++) {
            JsonObject temp = habilidades.get(i).getAsJsonObject();
            JsonObject habilidades2 = temp.getAsJsonObject("ability");

            Object[] fila = {
                contador,
                habilidades2.get("name").getAsString(),
                habilidades2.get("url").getAsString(),
            
        };
        modelo.addRow(fila);
        contador++;
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotones = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelPasar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        icono = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        panelPasar.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout panelPasarLayout = new javax.swing.GroupLayout(panelPasar);
        panelPasar.setLayout(panelPasarLayout);
        panelPasarLayout.setHorizontalGroup(
            panelPasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelPasarLayout.setVerticalGroup(
            panelPasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "N°", "Habilidad", "Url"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jButton1.setText(">");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("<");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPasar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(icono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(icono, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(panelPasar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
       panelBotones.removeAll();
       ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET(siguiente);
        JsonObject list = JsonParser.parseString(respuesta).getAsJsonObject();
        siguiente = list.get("next").getAsString();
        anterior = list.get("previous").getAsString();
        System.out.println(siguiente);
        
        lista = list.getAsJsonArray("results");
        System.out.println(list);

        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        botones = new JButton[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            JsonObject pokemon = lista.get(i).getAsJsonObject();
            String nombre = pokemon.get("name").getAsString();
            String url = pokemon.get("url").getAsString();

            System.out.println(nombre);

            botones[i] = new JButton(nombre);
            botones[i].setPreferredSize(new Dimension(146, botones[i].getPreferredSize().height));
            botones[i].setMaximumSize(new Dimension(146, botones[i].getPreferredSize().height));
            botones[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mostrarPoke(url);
                    mostrarInfo(url);
                }
            });

            panelBotones.add(botones[i]);
            System.out.println("Boton agregado: " + nombre);

        }

        panelBotones.revalidate();
        panelBotones.repaint();
        System.out.println(lista);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        panelBotones.removeAll();
       ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET(anterior);
        JsonObject list = JsonParser.parseString(respuesta).getAsJsonObject();
        siguiente = list.get("next").getAsString();
        if (list.has("previous") && !list.get("previous").isJsonNull()) {
                anterior = list.get("previous").getAsString();
            } else {
                anterior = null;
            }
        System.out.println(siguiente);
        
        lista = list.getAsJsonArray("results");
        System.out.println(list);

        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        botones = new JButton[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            JsonObject pokemon = lista.get(i).getAsJsonObject();
            String nombre = pokemon.get("name").getAsString();
            String url = pokemon.get("url").getAsString();

            System.out.println(nombre);

            botones[i] = new JButton(nombre);
            botones[i].setPreferredSize(new Dimension(146, botones[i].getPreferredSize().height));
            botones[i].setMaximumSize(new Dimension(146, botones[i].getPreferredSize().height));
            botones[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mostrarPoke(url);
                    mostrarInfo(url);
                }
            });

            panelBotones.add(botones[i]);
            System.out.println("Boton agregado: " + nombre);

        }

        panelBotones.revalidate();
        panelBotones.repaint();
        System.out.println(lista);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icono;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelPasar;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
