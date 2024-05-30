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
    int enPagina = 20;
    int paginaActual = 1;
    int totalPaginas;
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
        pasar();

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
    int offset = (paginaActual - 1) * 20;

    public void cargarBotones() {
        panelBotones.removeAll();
        ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET("https://pokeapi.co/api/v2/pokemon?=offset" + offset + "&limit=20");
        JsonObject list = JsonParser.parseString(respuesta).getAsJsonObject();
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

    public void pasar() {
        panelPasar.removeAll();

        panelPasar.setLayout(new BoxLayout(panelPasar, BoxLayout.X_AXIS));
        JButton anterior = new JButton("<");
        anterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (paginaActual > 1) {
                    paginaActual--;
                    cargarBotones();

                }
            }
        });
        totalPaginas = (int) Math.ceil((double) lista.size() / enPagina);
        panelPasar.add(anterior);
        for (int i = 1; i < totalPaginas; i++) {
            JButton actual = new JButton(String.valueOf(i));

            actual.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    paginaActual = Integer.parseInt(e.getActionCommand());
                    cargarBotones();

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

                }
            }
        });
        panelPasar.add(siguiente);
        panelPasar.revalidate();
        panelPasar.repaint();
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
            .addGap(0, 513, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPasar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(icono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(icono, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icono;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelPasar;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
