package com.gimnasio.views;

import com.gimnasio.controller.Operaciones;
import com.gimnasio.model.ClienteDto;
import com.gimnasio.model.ClientePaqueteDto;
import com.gimnasio.model.ComboDto;
import com.gimnasio.model.ComboModel;
import com.gimnasio.model.UsuarioDto;
import com.gimnasio.model.enums.EEstadoPlan;
import com.gimnasio.model.enums.ESiNo;
import com.gimnasio.util.Util;
import com.google.common.base.Joiner;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author rodolfo
 */
public class frmRegistrarPagos extends javax.swing.JInternalFrame {

    private List<ComboDto> listComboDescuentos;
    private List<ComboDto> listComboPaquetes;
    private ComboModel comboDescuentos;
    private ComboModel comboPaquetes;

    protected ClientePaqueteDto clientePaqueteDto;
    protected UsuarioDto usuarioSessionDto;
    protected ClienteDto clienteDto;
    protected Operaciones operacion;

    private frmClientes clientePadre;
    protected String tipoViene;
    protected boolean registraAsistencia;

    /**
     * Creates new form frmPagos
     *
     * @param frmCliente
     * @param operacion
     * @param clienteDto
     * @throws java.lang.Exception
     */
    public frmRegistrarPagos(frmClientes frmCliente, Operaciones operacion, ClienteDto clienteDto) throws Exception {
        initComponents();

        this.clientePaqueteDto = new ClientePaqueteDto();
        this.registraAsistencia = false;
        this.clientePadre = frmCliente;
        this.clienteDto = clienteDto;
        this.operacion = operacion;
        this.setInitCombos();
        this.panelTiquetera.setVisible(false);
        if (!Util.getVacio(clienteDto.getPersonaDto().getNombreCompleto())) {
            this.clientePaqueteDto = this.operacion.getPaqueteActivoCliente(String.valueOf(this.clienteDto.getId()), null);
            this.lblDocumento_cliente.setText(clienteDto.getPersonaDto().getNumeroIdentificacion());
            this.lblNombre_cliente.setText(clienteDto.getPersonaDto().getNombreCompleto());
            if (this.clientePaqueteDto.getId() != null) {
                this.setAsignarValores();
            }
            this.setAsignatFoto();
        }
    }

    /**
     *
     * @param operacion
     * @param documento
     * @throws Exception
     */
    public frmRegistrarPagos(Operaciones operacion, String documento) throws Exception {
        initComponents();
        this.registraAsistencia = false;
        this.operacion = operacion;
        List<ClienteDto> listCliente = this.operacion.getClienteDatos(null, documento);
        if (listCliente.size() > 0) {
            ClienteDto clienteTemp = new ClienteDto();
            for (ClienteDto cliente : listCliente) {
                if (documento.equals(cliente.getPersonaDto().getNumeroIdentificacion())) {
                    clienteTemp = cliente;
                    break;
                }
            }
            this.clienteDto = clienteTemp;
        }
        if (this.clienteDto.getId() != null) {
            this.clientePaqueteDto = this.operacion.getPaqueteActivoCliente(String.valueOf(this.clienteDto.getId()), null);
            setInitCombos();
            this.panelTiquetera.setVisible(false);
            if (!Util.getVacio(clienteDto.getPersonaDto().getNombreCompleto())) {
                this.lblNombre_cliente.setText(clienteDto.getPersonaDto().getNombreCompleto());
                this.lblDocumento_cliente.setText(clienteDto.getPersonaDto().getNumeroIdentificacion());
            }
            if (this.clientePaqueteDto.getId() != null) {
                this.setAsignarValores();
            }
            this.setAsignatFoto();
        }
    }

    public final void setInitCombos() {
        try {
            ComboDto inicio;
            this.comboPaquetes = new ComboModel();
            this.comboPaquetes.getLista().clear();
            this.listComboPaquetes = this.operacion.getPaquetesEnumerado();
            inicio = new ComboDto("", "-------------");
            this.listComboPaquetes.add(0, inicio);
            this.comboPaquetes.getLista().addAll(this.listComboPaquetes);
            this.comboPaquetes.setSelectedItem(inicio);
            this.cmbPaquete.setModel(this.comboPaquetes);

            this.comboDescuentos = new ComboModel();
            this.comboDescuentos.getLista().clear();
            this.listComboPaquetes = this.operacion.getDescuentosEnumerado();
            inicio = new ComboDto("", "-------------");
            this.listComboPaquetes.add(0, inicio);
            this.comboDescuentos.getLista().addAll(this.listComboPaquetes);
            this.comboDescuentos.setSelectedItem(inicio);
            this.cmbDescuento.setModel(this.comboDescuentos);
            this.txtFecha_inicio.setDate(new Date());
        } catch (Exception ex) {
            Logger.getLogger(frmRegistrarPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void setAsignatFoto() {
        if (this.clienteDto.getPersonaDto().getFotoPerfil().trim().length() > 0) {
            File file = new File("fotos/" + this.clienteDto.getPersonaDto().getFotoPerfil());
            if (file.exists()) {
                Image image = new ImageIcon(file.getAbsolutePath()).getImage();
                lblFotoCliente.setIcon(new ImageIcon(image.getScaledInstance(128, 128, Image.SCALE_DEFAULT)));
                lblFotoCliente.repaint();
            } else {
                this.setNoFile(lblFotoCliente);
            }
        } else {
            setNoFile(lblFotoCliente);
        }
    }

    public final void setAsignarValores() {
        try {
            if (this.clientePaqueteDto.getPaqueteId() > 0) {
                this.cmbPaquete.setSelectedIndex(Integer.parseInt(this.clientePaqueteDto.getPaqueteId().toString()));
                this.cmbPaquete.repaint();
            }
            if (this.clientePaqueteDto.getDescuentoId() > 0) {
                this.cmbDescuento.setSelectedIndex(Integer.parseInt(this.clientePaqueteDto.getDescuentoId().toString()));
                this.cmbDescuento.repaint();
            }
            if (this.clientePaqueteDto.getNumeroDiasTiquetera() > 0) {
                this.txtDias_tiquetera.setText(String.valueOf(this.clientePaqueteDto.getNumeroDiasTiquetera()));
            }
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(clientePaqueteDto.getFechaIniciaPaquete());
            this.txtFecha_inicio.setDate(date);
            if (this.clientePaqueteDto.getPrecioBase() > 0) {
                this.txtPrecio_base.setText(String.valueOf(this.clientePaqueteDto.getPrecioBase()));
            }
            if (this.clientePaqueteDto.getValorTotal() > 0) {
                this.txtTolal_pagar.setText(String.valueOf(this.clientePaqueteDto.getValorTotal()));
            }
        } catch (ParseException ex) {
            Logger.getLogger(frmRegistrarPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setNoFile(JLabel lblFoto) {
        File file = new File("files/no-file.png");
        if (file.exists()) {
            Image image = new ImageIcon(file.getAbsolutePath()).getImage();
            Util.setPintarFotoPerfil(image, lblFoto);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        cmbPaquete = new javax.swing.JComboBox();
        lblPaquete = new javax.swing.JLabel();
        cmbDescuento = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        panelTiquetera = new javax.swing.JPanel();
        txtDias_tiquetera = new javax.swing.JTextField();
        lblTiquetera = new javax.swing.JLabel();
        panelCliente = new javax.swing.JPanel();
        lblNombre_cliente = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblFotoCliente = new javax.swing.JLabel();
        lblDocumento_cliente = new javax.swing.JLabel();
        txtTolal_pagar = new javax.swing.JTextField();
        txtPrecio_base = new javax.swing.JTextField();
        lblPrecio_base = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFecha_inicio = new com.toedter.calendar.JDateChooser();

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/floppy-icon.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setRegistrarPagoPlan(evt);
            }
        });

        cmbPaquete.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPaquete.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                setVerificaPaqueteTiquetera(evt);
            }
        });

        lblPaquete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPaquete.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPaquete.setText("Paquete");

        cmbDescuento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDescuento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                setCalculaDescuento(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Descuento");

        txtDias_tiquetera.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                setValidaDiasTiquetera(evt);
            }
        });
        txtDias_tiquetera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setValidaSoloNumeros(evt);
            }
        });

        lblTiquetera.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTiquetera.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTiquetera.setText("Numero de días");

        javax.swing.GroupLayout panelTiqueteraLayout = new javax.swing.GroupLayout(panelTiquetera);
        panelTiquetera.setLayout(panelTiqueteraLayout);
        panelTiqueteraLayout.setHorizontalGroup(
            panelTiqueteraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelTiqueteraLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTiquetera)
                .addGap(5, 5, 5)
                .addComponent(txtDias_tiquetera, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
        );
        panelTiqueteraLayout.setVerticalGroup(
            panelTiqueteraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTiqueteraLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelTiqueteraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDias_tiquetera, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTiquetera))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombre_cliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombre_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Foto");

        lblFotoCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFotoCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFotoCliente.setPreferredSize(new java.awt.Dimension(128, 128));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFotoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFotoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        lblDocumento_cliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDocumento_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelClienteLayout = new javax.swing.GroupLayout(panelCliente);
        panelCliente.setLayout(panelClienteLayout);
        panelClienteLayout.setHorizontalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDocumento_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblNombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(panelClienteLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelClienteLayout.setVerticalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClienteLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDocumento_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        txtTolal_pagar.setEditable(false);

        txtPrecio_base.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                setCalculaValorTotalConPrecioBase(evt);
            }
        });
        txtPrecio_base.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setValidaSoloNumeros(evt);
            }
        });

        lblPrecio_base.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrecio_base.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrecio_base.setText("Precio Base");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Total a pagar");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Fecha de Inicio");

        txtFecha_inicio.setDateFormatString("yyyy-MM-dd");
        txtFecha_inicio.setPreferredSize(new java.awt.Dimension(6, 20));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblPrecio_base)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecio_base, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblPaquete)
                        .addGap(5, 5, 5)
                        .addComponent(cmbPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(5, 5, 5)
                        .addComponent(cmbDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelTiquetera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTolal_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPaquete))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(15, 15, 15)
                        .addComponent(panelTiquetera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio_base, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrecio_base))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTolal_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param evt
     */
    private void setRegistrarPagoPlan(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setRegistrarPagoPlan
        List<String> listMessages = new ArrayList();
        ComboDto comboPaq = (ComboDto) this.cmbPaquete.getSelectedItem();
        ComboDto comboDes = (ComboDto) this.cmbDescuento.getSelectedItem();
        if (Util.getVacio(comboPaq.getCodigo())) {
            listMessages.add("<li>" + this.lblPaquete.getText() + "</li>");
        }
        if (!Util.getVacio(comboPaq.getCodigo())) {
            if (!Util.getVacio(comboPaq.getAuxiliar()) && (comboPaq.getAuxiliar().equals(String.valueOf(ESiNo.SI.getId())))) {
                if (this.txtDias_tiquetera.getText().equals("")) {
                    listMessages.add("<li>" + this.lblTiquetera.getText() + "</li>");
                }
            }
        }
        if (this.txtFecha_inicio.getDate() == null) {
            listMessages.add("<li>Fecha inicio del " + this.lblPaquete.getText().toLowerCase() + "</li>");
        }
        if (this.txtPrecio_base.getText().equals("")) {
            listMessages.add("<li>" + this.lblPrecio_base.getText() + "</li>");
        }
        if (listMessages.size() > 0) {
            JLabel label = new JLabel("<html>Verífique la siguiente lista de campos obligatorios:\n<ol>" + Joiner.on("\n").join(listMessages) + "</ol></html>");
            label.setFont(new Font("consolas", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(this, label, "Alerta de verificación de datos", JOptionPane.WARNING_MESSAGE);
        } else {
            this.clientePaqueteDto.setClienteId(this.clienteDto.getId());
            this.clientePaqueteDto.setPaqueteId(new Long(comboPaq.getCodigo()));
            if (!Util.getVacio(comboDes.getCodigo())) {
                this.clientePaqueteDto.setDescuentoId(new Long(comboDes.getCodigo()));
            } else {
                this.clientePaqueteDto.setDescuentoId(null);
            }
            if (!Util.getVacio(comboPaq.getAuxiliar()) && (comboPaq.getAuxiliar().equals(String.valueOf(ESiNo.SI.getId())))) {
                this.clientePaqueteDto.setNumeroDiasTiquetera(Short.parseShort(this.txtDias_tiquetera.getText()));
                this.clientePaqueteDto.setFechaFinalizaPaquete(null);
            }
            this.clientePaqueteDto.setPrecioBase(Double.parseDouble(this.txtPrecio_base.getText()));
            this.clientePaqueteDto.setValorTotal(Double.parseDouble(this.txtTolal_pagar.getText()));
            this.clientePaqueteDto.setEstado(EEstadoPlan.ACTIVO.getId());
            if (this.txtFecha_inicio.getDate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaNacimiento = sdf.format(this.txtFecha_inicio.getDate().getTime());
                this.clientePaqueteDto.setFechaIniciaPaquete(fechaNacimiento);
            }
            this.clientePaqueteDto.setUsuarioId(this.usuarioSessionDto.getId());
            try {
                try {
                    if (!Util.getVacio(comboPaq.getAuxiliar()) && (comboPaq.getAuxiliar().equals(String.valueOf(ESiNo.NO.getId())))) {
                        this.clientePaqueteDto.setFechaFinalizaPaquete(this.setVerificaFechaFinalizaPaquete(comboPaq, comboDes));
                    }
                    boolean correct = this.operacion.setGuardaPagoPaqueteCliente(this.clientePaqueteDto, this.isRegistraAsistencia());
                    if (correct) {
                        JLabel label = new JLabel(this.isRegistraAsistencia() ? "El registro para el pago y asistencia del cliente se ha realizado correctamente" : "El registro para el pago se ha realizado correctamente");
                        label.setFont(new Font("consolas", Font.PLAIN, 14));
                        JOptionPane.showMessageDialog(this, label, "Información", JOptionPane.INFORMATION_MESSAGE);
                        if (Short.parseShort(this.tipoViene) == 1) {
                            this.clientePadre.setVisible(false);
                            this.setVisible(false);
                        } else {
                            this.setVisible(false);
                        }

                    }
                } catch (Exception ex) {
                    JLabel label = new JLabel("Se presentó un error para guardar el proceso de pago del paquete, intente nuevamente");
                    label.setFont(new Font("consolas", Font.PLAIN, 14));
                    JOptionPane.showMessageDialog(this, label, "Alerta de error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(frmRegistrarPagos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception ex) {
                JLabel label = new JLabel("Se presentó un error para guardar el proceso de pago del paquete, intente nuevamente");
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(this, label, "Alerta de error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(frmRegistrarPagos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_setRegistrarPagoPlan

    /**
     *
     * @param comboPaq
     * @param comboDes
     * @return
     * @throws Exception
     */
    public String setVerificaFechaFinalizaPaquete(ComboDto comboPaq, ComboDto comboDes) throws Exception {
        String fechaFinal = "";
        try {
            Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(this.clientePaqueteDto.getFechaIniciaPaquete()); //Date();//
            SimpleDateFormat fechaTemporal = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaRetorna = fechaInicio;
            switch (Short.parseShort(comboPaq.getCoAssistant())) {
                case 1: {
                    //fechaRetorna = sumarRestarHorasFecha(fechaInicio, Calendar.MONTH, 1);
                }
                break;
                case 2: {// ETipoPlan.MES
                    fechaRetorna = sumarRestarHorasFecha(fechaInicio, Calendar.MONTH, 1);
                }
                break;
                case 3: {// ETipoPlan.BIMESTRE
                    fechaRetorna = sumarRestarHorasFecha(fechaInicio, Calendar.MONTH, 2);
                }
                break;
                case 4: {// ETipoPlan.TRIMETESTRE
                    fechaRetorna = sumarRestarHorasFecha(fechaInicio, Calendar.MONTH, 3);
                }
                break;
                case 5: {// ETipoPlan.CUATRIMETESTRE
                    fechaRetorna = sumarRestarHorasFecha(fechaInicio, Calendar.MONTH, 4);
                }
                break;
                case 6: {// ETipoPlan.SEMESTRE
                    fechaRetorna = sumarRestarHorasFecha(fechaInicio, Calendar.MONTH, 6);
                }
                break;
                case 7: {// ETipoPlan.ANIO
                    fechaRetorna = sumarRestarHorasFecha(fechaInicio, Calendar.YEAR, 1);
                }
                break;
            }
            fechaFinal = fechaTemporal.format(fechaRetorna);
        } catch (ParseException | NumberFormatException ex) {
            throw ex;
        }
        return fechaFinal;
    }

    /**
     *
     * @param fecha
     * @param tipo
     * @param horas
     * @return
     */
    public Date sumarRestarHorasFecha(Date fecha, int tipo, int horas) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(tipo, horas); // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    /**
     *
     * @param evt
     */
    private void setVerificaPaqueteTiquetera(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_setVerificaPaqueteTiquetera
        Double valorTotal = 0.0;
        ComboDto comboPaq = (ComboDto) this.cmbPaquete.getSelectedItem();
        ComboDto comboDes = (ComboDto) this.cmbDescuento.getSelectedItem();
        if (!Util.getVacio(comboPaq.getCodigo())) {
            valorTotal += Double.parseDouble(comboPaq.getAssistant());
            if (!Util.getVacio(comboPaq.getAuxiliar()) && (comboPaq.getAuxiliar().equals(String.valueOf(ESiNo.SI.getId())))) {
                this.panelTiquetera.setVisible(true);
            } else {
                this.panelTiquetera.setVisible(false);
            }
            if (!this.txtDias_tiquetera.getText().equals("")) {
                int diasTiquetera = Short.parseShort(this.txtDias_tiquetera.getText());
                valorTotal = (diasTiquetera * valorTotal);
            }
            if (!Util.getVacio(comboDes.getCodigo())) {
                double descuento = Double.parseDouble(comboDes.getAuxiliar());
                if (descuento > 100) {
                    valorTotal -= descuento;
                } else {
                    descuento = descuento / 100;
                    descuento = valorTotal * descuento;
                    valorTotal -= descuento;
                }
            }
        } else {
            this.panelTiquetera.setVisible(false);
        }
        this.txtPrecio_base.setText(comboPaq.getAssistant());
        this.txtTolal_pagar.setText(valorTotal.toString());
    }//GEN-LAST:event_setVerificaPaqueteTiquetera

    /**
     *
     * @param evt
     */
    private void setValidaDiasTiquetera(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_setValidaDiasTiquetera
        if (!this.txtDias_tiquetera.getText().equals("")) {
            int diasTiquetera = Short.parseShort(this.txtDias_tiquetera.getText());
            if (diasTiquetera > 30) {
                JLabel label = new JLabel("El valor para el numero de dias de tiquetera no puede exceder los 30 días");
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(this, label, "Alerta de verificación de datos", JOptionPane.WARNING_MESSAGE);
            } else if (diasTiquetera < 1) {
                JLabel label = new JLabel("El valor para el número de dias de tiquetera no puede ser menor a 1");
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(this, label, "Alerta de verificación de datos", JOptionPane.WARNING_MESSAGE);
            } else {
                Double valorTotal = 0.0;
                ComboDto comboPaq = (ComboDto) this.cmbPaquete.getSelectedItem();
                ComboDto comboDes = (ComboDto) this.cmbDescuento.getSelectedItem();

                valorTotal += Double.parseDouble(comboPaq.getAssistant());
                valorTotal = (diasTiquetera * valorTotal);
                if (!Util.getVacio(comboDes.getCodigo())) {
                    double descuento = Double.parseDouble(comboDes.getAuxiliar());
                    if (descuento > 100) {
                        valorTotal -= descuento;
                    } else {
                        descuento = descuento / 100;
                        descuento = valorTotal * descuento;
                        valorTotal -= descuento;
                    }
                }
                this.txtPrecio_base.setText(comboPaq.getAssistant());
                this.txtTolal_pagar.setText(valorTotal.toString());
            }
        }
    }//GEN-LAST:event_setValidaDiasTiquetera

    /**
     *
     * @param evt
     */
    private void setValidaSoloNumeros(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setValidaSoloNumeros
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
            JLabel label = new JLabel("Solo esta permitido el ingreso de números");
            label.setFont(new Font("consolas", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(this, label, "Error de ingreso de datos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_setValidaSoloNumeros

    /**
     *
     * @param evt
     */
    private void setCalculaDescuento(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_setCalculaDescuento
        setVerificaPaqueteTiquetera(evt);
    }//GEN-LAST:event_setCalculaDescuento

    /**
     *
     * @param evt
     */
    private void setCalculaValorTotalConPrecioBase(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_setCalculaValorTotalConPrecioBase
        if (!this.txtPrecio_base.getText().equals("")) {
            double precioBase = Double.parseDouble(this.txtPrecio_base.getText());
            if (precioBase > 1000) {
                Double valorTotal = 0.0;
                ComboDto comboPaq = (ComboDto) this.cmbPaquete.getSelectedItem();
                ComboDto comboDes = (ComboDto) this.cmbDescuento.getSelectedItem();
                if (!Util.getVacio(comboPaq.getCodigo())) {
                    valorTotal += precioBase;
                    if (!this.txtDias_tiquetera.getText().equals("")) {
                        int diasTiquetera = Short.parseShort(this.txtDias_tiquetera.getText());
                        valorTotal = (diasTiquetera * valorTotal);
                    }
                    if (!Util.getVacio(comboDes.getCodigo())) {
                        double descuento = Double.parseDouble(comboDes.getAuxiliar());
                        if (descuento > 100) {
                            valorTotal -= descuento;
                        } else {
                            descuento = descuento / 100;
                            descuento = precioBase * descuento;
                            valorTotal -= descuento;
                        }
                    }
                }
                this.txtTolal_pagar.setText(valorTotal.toString());
            } else {
                JLabel label = new JLabel("El precio base no debe ser inferior a 1000 pesos");
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(this, label, "Error de ingreso de datos", JOptionPane.WARNING_MESSAGE);
                this.txtPrecio_base.setText("");
            }
        }
    }//GEN-LAST:event_setCalculaValorTotalConPrecioBase

    public ClienteDto getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDto clienteDto) {
        this.clienteDto = clienteDto;
    }

    public UsuarioDto getUsuarioSessionDto() {
        return usuarioSessionDto;
    }

    public void setUsuarioSessionDto(UsuarioDto usuarioSessionDto) {
        this.usuarioSessionDto = usuarioSessionDto;
    }

    public String getTipoViene() {
        return tipoViene;
    }

    public void setTipoViene(String tipoViene) {
        this.tipoViene = tipoViene;
    }

    public boolean isRegistraAsistencia() {
        return registraAsistencia;
    }

    public void setRegistraAsistencia(boolean registraAsistencia) {
        this.registraAsistencia = registraAsistencia;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbDescuento;
    private javax.swing.JComboBox cmbPaquete;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblDocumento_cliente;
    private javax.swing.JLabel lblFotoCliente;
    private javax.swing.JLabel lblNombre_cliente;
    private javax.swing.JLabel lblPaquete;
    private javax.swing.JLabel lblPrecio_base;
    private javax.swing.JLabel lblTiquetera;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelTiquetera;
    private javax.swing.JTextField txtDias_tiquetera;
    private com.toedter.calendar.JDateChooser txtFecha_inicio;
    private javax.swing.JTextField txtPrecio_base;
    private javax.swing.JTextField txtTolal_pagar;
    // End of variables declaration//GEN-END:variables
}
