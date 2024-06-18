package Principal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class infoPoke extends javax.swing.JFrame {

    JsonObject digimon;
    String urlInfo;
    DefaultTableModel modelo;
    DefaultTableModel modelo1;
    DefaultTableModel modelo2;
    DefaultTableModel modelo3;

    public infoPoke(JsonObject digimon) {
        this.digimon = digimon;

        initComponents();
        initAlternComponents();
    }

    public void initAlternComponents() {
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("InfoPokemon");
        modelo = (DefaultTableModel) tablaNivel.getModel();
        modelo1 = (DefaultTableModel) tablaAtributos.getModel();
        modelo2 = (DefaultTableModel) tablaTipos.getModel();
        modelo3 = (DefaultTableModel) tablaTerrenos.getModel();
        llamar();
         scroll4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void llamar() {
        try {
            String imagenUrl = this.digimon.get("image").getAsString();
            URL url = new URL(imagenUrl);
            Image imagen = getToolkit().createImage(url);
            imagen = imagen.getScaledInstance(197, 181, Image.SCALE_SMOOTH);
            etqImagen.setIcon(new ImageIcon(imagen));
            String nombre = this.digimon.get("name").getAsString();
            urlInfo = this.digimon.get("href").getAsString();

            etqNombre.setText(nombre);
            llenarTabla();
            llenarTerrenos();

        } catch (MalformedURLException ex) {
            System.out.println("Error en URL" + ex.getMessage());
        }
        panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panelPrincipal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelPrincipal.setCursor(new Cursor(Cursor.HAND_CURSOR));
                panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
        });
    }

    public void llenarTabla() {
        ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET(urlInfo);
        JsonObject list = JsonParser.parseString(respuesta).getAsJsonObject();
        JsonArray habilidades = list.getAsJsonArray("levels");
        System.out.println(habilidades);
        JsonArray tipos = list.getAsJsonArray("types");
        System.out.println(tipos);
        JsonArray atributos = list.getAsJsonArray("attributes");
        System.out.println(atributos);

        modelo.setRowCount(0);
        modelo1.setRowCount(0);
        modelo2.setRowCount(0);
        

        for (int i = 0; i < habilidades.size(); i++) {
            JsonObject temp = habilidades.get(i).getAsJsonObject();

            String nivell = temp.get("level").getAsString();

            modelo.addRow(new Object[]{nivell});
        }
        for (int i = 0; i < tipos.size(); i++) {
            JsonObject temp = tipos.get(i).getAsJsonObject();

            String tipoo = temp.get("type").getAsString();

            modelo1.addRow(new Object[]{tipoo});
        }
        for (int i = 0; i < atributos.size(); i++) {
            JsonObject temp = atributos.get(i).getAsJsonObject();

            String atribu = temp.get("attribute").getAsString();

            modelo2.addRow(new Object[]{atribu});
        }

    }
    
    public void llenarTerrenos(){
        ConsumoAPI consumo = new ConsumoAPI();
        String respuesta = consumo.consumoGET(urlInfo);
        JsonObject list = JsonParser.parseString(respuesta).getAsJsonObject();
        JsonArray habilidades = list.getAsJsonArray("fields");
        System.out.println(habilidades);
        
        modelo3.setRowCount(0);
        for (int i = 0; i < habilidades.size(); i++) {
            JsonObject temp = habilidades.get(i).getAsJsonObject();

            String fieldd = temp.get("field").getAsString();

            modelo3.addRow(new Object[]{fieldd});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        etqImagen = new javax.swing.JLabel();
        etqNombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNivel = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTipos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAtributos = new javax.swing.JTable();
        scroll4 = new javax.swing.JScrollPane();
        tablaTerrenos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        etqImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        etqNombre.setFont(new java.awt.Font("Tempus Sans ITC", 2, 18)); // NOI18N
        etqNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        tablaNivel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 14)); // NOI18N
        tablaNivel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Nivel"
            }
        ));
        tablaNivel.setToolTipText("");
        jScrollPane1.setViewportView(tablaNivel);

        tablaTipos.setFont(new java.awt.Font("Tempus Sans ITC", 2, 14)); // NOI18N
        tablaTipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Tipos"
            }
        ));
        tablaTipos.setToolTipText("");
        jScrollPane2.setViewportView(tablaTipos);

        tablaAtributos.setFont(new java.awt.Font("Tempus Sans ITC", 2, 14)); // NOI18N
        tablaAtributos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Atributos"
            }
        ));
        tablaAtributos.setToolTipText("");
        jScrollPane3.setViewportView(tablaAtributos);

        tablaTerrenos.setFont(new java.awt.Font("Tempus Sans ITC", 2, 14)); // NOI18N
        tablaTerrenos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Terrenos"
            }
        ));
        tablaTerrenos.setToolTipText("");
        scroll4.setViewportView(tablaTerrenos);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(etqImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(etqNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scroll4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(etqNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etqImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(58, 58, 58)
                .addComponent(scroll4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 614, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel etqImagen;
    private javax.swing.JLabel etqNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane scroll4;
    private javax.swing.JTable tablaAtributos;
    private javax.swing.JTable tablaNivel;
    private javax.swing.JTable tablaTerrenos;
    private javax.swing.JTable tablaTipos;
    // End of variables declaration//GEN-END:variables
}
