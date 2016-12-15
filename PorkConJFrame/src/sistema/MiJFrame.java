
package sistema;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.text.DefaultCaret;


public class MiJFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = -2663919128327732145L;
	
	//Atributos
	 private javax.swing.JLabel jLabel1;
	 private javax.swing.JScrollPane jScrollPane1;
	 private javax.swing.JTextArea jTextArea1;
	 private javax.swing.JTextField jTextField1;
	 
	 private boolean esperar =  true;
	 private String res;
	 
	
	//Constructor
    public MiJFrame() {
    	setTitle("PORK");
    	initComponents();
    	setIconImage(new ImageIcon("src\\img\\icono.png").getImage());
    	setLocationRelativeTo(null);
        setVisible(true);
    }
    
    //Métodos propios
    /**void anadeLinea(String s)
	 * 
	 * Descripción: muestra una cadena por el jFrame con salto de linea
	 * Entradas: String texto
	 * Salidas: 
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: añade la cadena a la cadena del textArea y un salto de linea
	 */
    public void anadeLinea(String s){
    	jTextArea1.setText(jTextArea1.getText()+s+"\n");    
    }
    
    /**void anadeLinea()
	 * 
	 * Descripción: añade un salto de linea al JFrame
	 * Entradas: 
	 * Salidas: textArea
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: añade un salto de linea a la cadena del textArea
	 */
    public void anadeLinea(){
    	jTextArea1.setText(jTextArea1.getText()+"\n");    
    }
    
    /**void anadeTexto(String s)
	 * 
	 * Descripción: muestra una cadena por el jFrame 
	 * Entradas: String texto
	 * Salidas: 
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: añade la cadena a la cadena del textArea 
	 */
    public void anadeTexto(String s){
    	jTextArea1.setText(jTextArea1.getText()+s);
    }
    
    /**void limpiaTexto()
	 * 
	 * Descripción: elimina todo el texto mostrado del JFrame 
	 * Entradas: 
	 * Salidas: jTextArea
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: se vacía la cadena del textArea 
	 */
    public void limpiaTexto(){
    	jTextArea1.setText("");
    }
    
    /**void jTextField1KeyPressed(KeyEvent evt)
	 * 
	 * Descripción: actualiza el atributo res y vacía el texto del TextField 
	 * Entradas: KeyEvent evt
	 * Salidas: String res, boolean esperar
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: si se pulsa la tecla enter, actualiza el atributo res con el contenido
	 * 					del textField, asigna false al atributo esperar y vacía el texto del TextField 
	 */
    private void jTextField1KeyPressed(KeyEvent evt) {                                       
    	if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            res = this.jTextField1.getText();
            esperar=false;
            this.jTextField1.setText("");
       }
    }   
    /**void actualizarImagen(int i)
	 * 
	 * Descripción: actualiza la imagen a mostrar del JFrame
	 * Entradas: int id de la Fase
	 * Salidas: 
	 * Entradas/Salidas: 
	 * Precondiciones: las imagenes de la carpeta source deben existir
	 * Postcondiciones: pone una imagen u otra dependiendo del id de la fase
	 */
    public void actualizarImagen(int i){
    	if(i==-1)
    		jLabel1.setIcon(new javax.swing.ImageIcon("src\\img\\gameover.png"));
    	else
    		if(i==1 || i==3)
    			jLabel1.setIcon(new javax.swing.ImageIcon("src\\img\\inicio.png"));
    		else
    			if(i>=7 && i<=9)
    				jLabel1.setIcon(new javax.swing.ImageIcon("src\\img\\posada.png"));	
    			else
    				if(i>=11&&i<15)
    						jLabel1.setIcon(new javax.swing.ImageIcon("src\\img\\camino.png"));
    				else
    					if(i==15||i==5)
    						jLabel1.setIcon(new javax.swing.ImageIcon("src\\img\\duelo.png"));
    					else
    						jLabel1.setIcon(new javax.swing.ImageIcon("src\\img\\main.png"));	
    	
    }
    
    /**int sigInt()
	 * 
	 * Descripción: devuelve el siguiente int escrito por el usuario
	 * Entradas: 
	 * Salidas: int 
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: devuelve el contenido del textField res cuando el usuario pulsa enter
	 */
    public int sigInt(){
		while(esperar){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		esperar = true;
    	return Integer.parseInt(res);    	
    }
    
    
    /**String sigString()
	 * 
	 * Descripción: devuelve el siguiente String escrito por el usuario
	 * Entradas: 
	 * Salidas: String 
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: devuelve el contenido del textField res cuando el usuario pulsa enter
	 */
    public String sigString(){
		while(esperar){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		esperar = true;
    	return res;    	
    }
    
    
    
    //Getters
    public void setTexto(String s){
    	jTextArea1.setText(s);
    }
    
    //Setters
    public String getTexto(){
    	return jTextArea1.getText();
    }
    
    
    //Iniciar componentes
    private void initComponents() {
    	
    	
    	
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 13)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("");
        jTextArea1.setToolTipText("");
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 11)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Alberto\\Desktop\\test.png")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jTextField1)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DefaultCaret caret = (DefaultCaret)jTextArea1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        pack();
    }                      
    
                                        

    
}

