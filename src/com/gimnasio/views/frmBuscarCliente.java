/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.views;

import com.gimnasio.controller.Operaciones;
import com.gimnasio.model.ComboDto;
import com.gimnasio.model.ComboModel;
import com.gimnasio.model.MiRender;
import com.gimnasio.model.TablaDto;
import com.gimnasio.model.TablaModelo;
import com.gimnasio.model.UsuarioDto;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author rodolfo
 */
public class frmBuscarCliente extends javax.swing.JInternalFrame {

    private List<ComboDto> listComboLimite;
    private UsuarioDto usuarioSessionDto;
    private ComboModel comboLimite;
    private final String[] headTable;
    private final TablaModelo table;
    protected Operaciones operacion;
    protected frmPrincipal padre;
    private final String tipo;

    /**
     *
     * @param operacion
     * @param tipo
     */
    public frmBuscarCliente(Operaciones operacion, String tipo) {
        initComponents();

        this.operacion = operacion;
        this.tipo = tipo;

        this.headTable = new String[]{"Documento", "Nombres", "Apellidos", "Edad", "Genero", "Movil", "Fijo", "Correo"};
        int widthColumna[] = {100, 200, 200, 50, 50, 100, 100, 200};
        this.table = new TablaModelo(this.headTable);
        this.tblClientes.setModel(this.table);

        int columnas = this.tblClientes.getColumnCount();
        for (int i = 0; i < columnas; i++) {
            this.tblClientes.getColumnModel().getColumn(i).setPreferredWidth(widthColumna[i]);
        }
        setInitCombos();
    }

    /**
     *
     * @param padre
     * @param operacion
     * @param tipo
     */
    public frmBuscarCliente(frmPrincipal padre, Operaciones operacion, String tipo) {
        initComponents();

        this.operacion = operacion;
        this.padre = padre;
        this.tipo = tipo;

        this.headTable = new String[]{"Documento", "Nombres", "Apellidos", "Edad", "Genero", "Movil", "Fijo", "Correo"};
        int widthColumna[] = {100, 200, 200, 50, 50, 100, 100, 200};
        this.table = new TablaModelo(this.headTable);
        this.tblClientes.setModel(this.table);

        int columnas = this.tblClientes.getColumnCount();
        for (int i = 0; i < columnas; i++) {
            this.tblClientes.getColumnModel().getColumn(i).setPreferredWidth(widthColumna[i]);
        }
        setInitCombos();
    }

    public final void setInitCombos() {
        ComboDto inicio;
        this.comboLimite = new ComboModel();
        this.comboLimite.getLista().clear();
        this.listComboLimite = this.operacion.getLimiteConsulta();
        inicio = new ComboDto("10", "10");
        this.listComboLimite.add(0, inicio);
        this.comboLimite.getLista().addAll(this.listComboLimite);
        this.comboLimite.setSelectedItem(inicio);
        this.cmbLimite.setModel(this.comboLimite);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        lblResultado = new javax.swing.JLabel();
        lblCantidad_clientes = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtApellidos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblLimite = new javax.swing.JLabel();
        cmbLimite = new javax.swing.JComboBox();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                setCloseIframeBusquedaCliente(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setClienteProceso(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        lblResultado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblResultado.setText("Resultados");

        lblCantidad_clientes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCantidad_clientes.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblResultado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantidad_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResultado)
                    .addComponent(lblCantidad_clientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nombres");

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/Zoom-icon.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setConsultaClientes(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Apellidos");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Documento");

        lblLimite.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblLimite.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLimite.setText("Límite");

        cmbLimite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "50", "100", "200", "1000", "Todos" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblLimite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(lblLimite)
                        .addComponent(cmbLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param evt
     */
    private void setConsultaClientes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setConsultaClientes
        this.setConsultarTableClientes();
    }//GEN-LAST:event_setConsultaClientes

    public void setConsultarTableClientes() {
        List<TablaDto> lista;
        try {
            ComboDto comboLimite = (ComboDto) this.cmbLimite.getSelectedItem();
            lista = this.operacion.getClientesDatosTablaDto(this.txtNombres.getText(), this.txtApellidos.getText(), this.txtDocumento.getText(), comboLimite.getCodigo());
            this.table.getData().clear();
            this.lblCantidad_clientes.setText(String.valueOf(lista.size()));
            lista.stream().forEach((dto) -> {
                this.table.setAgregar(dto);
            });
            this.tblClientes.setDefaultRenderer(Object.class, new MiRender(this.table));
            this.tblClientes.repaint();
        } catch (SQLException ex) {
            JLabel label = new JLabel("Error al consultar los clientes, intente nuevamente");
            label.setFont(new Font("consolas", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(this, label, "Alerta de error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(frmBuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param evt
     */
    private void setClienteProceso(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setClienteProceso
        if (evt.getClickCount() == 2) {
            int fila = this.tblClientes.getSelectedRow();
            TablaDto dto = (TablaDto) this.table.getData().get(fila);
            String documento = dto.getDato1();
            switch (Short.parseShort(this.tipo)) {
                case 1: {
                    try {
                        frmClientes frmCliente = new frmClientes(this.padre, operacion, documento);
                        frmCliente.setUsuarioSessionDto(usuarioSessionDto);
                        frmCliente.setResizable(true);
                        frmCliente.setClosable(true);
                        frmCliente.setSize(frmPrincipal.jdstPrincipal.getWidth(), frmPrincipal.jdstPrincipal.getHeight() - 1);
                        frmPrincipal.jdstPrincipal.add(frmCliente);
                        frmCliente.setVisible(true);

                    } catch (Exception ex) {
                        JLabel label = new JLabel("Se ha presentado un error, intente nuevamente");
                        label.setFont(new Font("consolas", Font.PLAIN, 14));
                        JOptionPane.showMessageDialog(this, label, "Alerta de error", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(frmBuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case 2: {
                    /*
                     frmPrincipal.fisitorepiaView = new frmFisioterapia(this.operacion, documento);
                     frmPrincipal.jdstPrincipal.add(frmPrincipal.fisitorepiaView);
                     frmPrincipal.fisitorepiaView.setSize(frmPrincipal.jdstPrincipal.getWidth(), frmPrincipal.jdstPrincipal.getHeight() - 1);
                     frmPrincipal.fisitorepiaView.setResizable(true);
                     frmPrincipal.fisitorepiaView.setClosable(true);
                     frmPrincipal.fisitorepiaView.setVisible(true);
                     */
                }
                break;
                case 3: {
                    try {
                        frmRegistrarPagos frmPagos = new frmRegistrarPagos(this.operacion, documento);
                        frmPrincipal.jdstPrincipal.add(frmPagos);
                        frmPagos.setSize(frmPrincipal.jdstPrincipal.getWidth(), frmPrincipal.jdstPrincipal.getHeight() - 1);
                        frmPagos.setUsuarioSessionDto(usuarioSessionDto);
                        frmPagos.setTipoViene(this.tipo);
                        frmPagos.setResizable(true);
                        frmPagos.setClosable(true);
                        frmPagos.setVisible(true);
                    } catch (Exception ex) {
                        JLabel label = new JLabel("Se ha presentado un error, intente nuevamente");
                        label.setFont(new Font("consolas", Font.PLAIN, 14));
                        JOptionPane.showMessageDialog(this, label, "Alerta de error", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(frmBuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
    }//GEN-LAST:event_setClienteProceso

    /**
     *
     * @param evt
     */
    private void setCloseIframeBusquedaCliente(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_setCloseIframeBusquedaCliente
        switch (Integer.parseInt(this.tipo)) {
            case 1: {
                this.padre.setBuscarClienteView(null);
            }
            break;
            case 2: {
                this.padre.setBuscarClienteView(null);
            }
            break;
        }
    }//GEN-LAST:event_setCloseIframeBusquedaCliente

    public UsuarioDto getUsuarioSessionDto() {
        return usuarioSessionDto;
    }

    /**
     *
     * @param usuarioSessionDto
     */
    public void setUsuarioSessionDto(UsuarioDto usuarioSessionDto) {
        this.usuarioSessionDto = usuarioSessionDto;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox cmbLimite;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad_clientes;
    private javax.swing.JLabel lblLimite;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
