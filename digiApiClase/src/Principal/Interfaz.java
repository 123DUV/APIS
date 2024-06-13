package Principal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Interfaz extends javax.swing.JFrame {

    int pagina;//es pagina actual sirve para el paginador

    public Interfaz() {
        this.pagina = 0;
        initComponents();
        initAlternComponents();//es para componentes visuales pq el initcomponents no se puede modificar
        cargarDigimon();

    }

    public void initAlternComponents() {
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("DigiAPI");

        panelItems.setLayout(new GridBagLayout());
    }

    public void cargarDigimon() {
        ConsumoAPI consumo = new ConsumoAPI();
        String endpoint = "https://digi-api.com/api/v1/digimon?page=" + this.pagina;//ese this.pagina es para q se pueda cambiar correctamente en cada caso el endpoint
        String respuesta = consumo.consumoGET(endpoint);

        JsonObject datos = JsonParser.parseString(respuesta).getAsJsonObject();
        JsonArray digimones = datos.getAsJsonArray("content");//ahi traigo el content de la respuesta....es un metodo mas simple, menos codigo

        GridBagConstraints restriccion = new GridBagConstraints();
        panelItems.removeAll();
        int contx = 0;
        int conty = 0;

        for (int i = 0; i < digimones.size(); i++) {
            JsonObject temp = digimones.get(i).getAsJsonObject();
            ItemDigimon item = new ItemDigimon(temp);

            restriccion.gridx = contx;
            restriccion.gridy = conty;
            restriccion.gridwidth = 1;
            restriccion.gridheight = 1;
            restriccion.weightx = 1;
            restriccion.weighty = 1;
            restriccion.fill = GridBagConstraints.BOTH;
            panelItems.add(item, restriccion);
            if (contx == 2) {
                contx = 0;
                conty++;
            } else {
                contx++;
            }
        }
        cargarPaginador();
        revalidate();
        repaint();
    }

    public void cargarPaginador() {

        panelPaginador.removeAll();
        //crear boton q lleva a la pagina 1
        JButton btnOne = new JButton("<<");
        panelPaginador.add(btnOne);
        btnOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pagina = 0;
                cargarDigimon();

            }
        });
        //crear el boton de anterior "<"
        JButton btnBef = new JButton("<");
        panelPaginador.add(btnBef);
        btnBef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pagina > 0) {
                    pagina--;
                    cargarDigimon();
                }
            }

        });
        //crear el boton de paginas
        for (int i = 1; i <= 7; i++) {
            JButton btnMid = new JButton("" + i);
            panelPaginador.add(btnMid);
            
            if((i-1)==pagina){
                btnMid.setBackground(Color.BLACK);
                btnMid.setForeground(Color.WHITE);
            }
            final int posicion = i;
            btnMid.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pagina=posicion-1;
                    cargarDigimon();
                }

            });

        }
        //crear el boton de siguiente ">"
        JButton btnNext = new JButton(">");
        panelPaginador.add(btnNext);
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pagina < 291) {
                    pagina++;
                    cargarDigimon();
                }
            }
        });
        //crear el boton de ultima pagina
        JButton btnLast = new JButton(">>");
        panelPaginador.add(btnLast);
        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pagina = 291;
                cargarDigimon();

            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelItems = new javax.swing.JPanel();
        panelPaginador = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        panelItems.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panelItemsLayout = new javax.swing.GroupLayout(panelItems);
        panelItems.setLayout(panelItemsLayout);
        panelItemsLayout.setHorizontalGroup(
            panelItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 836, Short.MAX_VALUE)
        );
        panelItemsLayout.setVerticalGroup(
            panelItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );

        panelPaginador.setBackground(new java.awt.Color(204, 204, 204));
        panelPaginador.setLayout(new javax.swing.BoxLayout(panelPaginador, javax.swing.BoxLayout.X_AXIS));

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelPaginador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelPaginador, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JPanel panelItems;
    private javax.swing.JPanel panelPaginador;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
