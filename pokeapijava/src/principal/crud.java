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
    int enPagina = 5;
    int paginaActual = 1;
    int totalPaginas    ;

    public crud() {
        initComponents();
        initAlternComponents();
    }

    public void initAlternComponents() {
        setVisible(true);
        setLocationRelativeTo(null);
        cargarBotones();
        pasar();

    }

    public void mostrarInfo(String url) {
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
        lista = list.getAsJsonArray("results");
        System.out.println(list);

       
        int inicio = (paginaActual - 1) * enPagina;
        int ultimo = Math.min(inicio + enPagina, lista.size(    ));
        botones = new JButton[ultimo - inicio];
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        for (int i = inicio; i < ultimo; i++) {
            JsonObject pokemon = lista.get(i).getAsJsonObject();
            String nombre = pokemon.get("name").getAsString();
            String url = pokemon.get("url").getAsString();

            //System.out.println(nombre);
            botones[i - inicio] = new JButton(nombre);
            botones[i - inicio].setPreferredSize(new Dimension(146, botones[i - inicio].getPreferredSize().height));
            botones[i - inicio].setMaximumSize(new Dimension(146, botones[i - inicio].getPreferredSize().height));
            botones[i - inicio].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mostrarInfo(url);
                }
            });

            panelBotones.add(botones[i - inicio]);
            System.out.println("Boton agregado: " + nombre);

        }

        panelBotones.revalidate();
        panelBotones.repaint();
        System.out.println(lista);

    }

    public void pasar() {
        panelPasar.removeAll();
        
        panelPasar.setLayout(new BoxLayout(panelPasar, BoxLayout.X_AXIS));
        JButton anterior = new JButton("<");
        anterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (paginaActual > 1) {
                    paginaActual--;
                    cargarBotones();
                    pasar();
                }
            }
        });
        totalPaginas = (int)Math.ceil((double)lista.size() / enPagina);
        panelPasar.add(anterior);
        for (int i = 1; i < totalPaginas; i++) {
            JButton actual = new JButton(String.valueOf(i));
            
            actual.addActionListener(new ActionListener() {  
                public void actionPerformed(ActionEvent e) {
                    paginaActual = Integer.parseInt(e.getActionCommand());
                    cargarBotones();
                    pasar();
                }
            });
            panelPasar.add(actual);
        }
        JButton siguiente = new JButton(">");
        anterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (paginaActual < totalPaginas) {
                    paginaActual++;
                    cargarBotones();
                    pasar();
                }
            }
        });
        panelPasar.add(siguiente);
        panelPasar.revalidate();
        panelPasar.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotones = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        icono = new javax.swing.JLabel();
        panelPasar = new javax.swing.JPanel();

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

        panelPasar.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout panelPasarLayout = new javax.swing.GroupLayout(panelPasar);
        panelPasar.setLayout(panelPasarLayout);
        panelPasarLayout.setHorizontalGroup(
            panelPasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelPasarLayout.setVerticalGroup(
            panelPasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

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
            .addComponent(panelPasar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(icono, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
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
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icono;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelPasar;
    // End of variables declaration//GEN-END:variables
}
