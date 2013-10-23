/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package swordfish.ui;
// <editor-fold defaultstate="expanded" desc="Imports">
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.Container.*;
import java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.awt.Container.*;
import java.awt.event.KeyEvent.*;
import java.awt.Component;

import javax.swing.BorderFactory.*;
import javax.swing.GroupLayout.*;
import javax.swing.GroupLayout;
import javax.swing.BorderFactory; 
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout.*;
import javax.swing.*;
import javax.swing.JSlider;

import swordfish.MobileDirectionDisplay;
import java.sql.Time.*;
import ij.*;
import ij.gui.*;


// </editor-fold>

/** 
/**
* 
* 
* 
* 
 * 
 * @since  October 22, 2013
 * @author jrob
 */
public class FunctioningMainMenu extends JFrame 
implements KeyListener{
    
    private JLabel icon_down;
    private JLabel icon_left;
    private JLabel icon_right;
    private JLabel icon_up;
    ImagePlus kk;
//    private JPanel p_directionals;
    private boolean do_debug = true;
    private  JLabel jLabel2;
    private ImageIcon cur_moment_view;
//    jLabel2 = new javax.swing.JLabel();
//    private String dir_image_icons = "/resources/";

    
    public FunctioningMainMenu() {
         
      //  init();
        buildGui();
      
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * 
     */
    // <editor-fold defaultstate="collapsed" desc="buildGui">                          
    private void buildGui() {

    
        // <editor-fold defaultstate="collapsed" desc="Instantiate Components">       
        p_live_streaming = new JPanel();
        l_vid_player = new JLabel();
        jLabel2 = new JLabel();
        
        p_title = new JPanel();
        l_title = new JLabel();
        p_file_handling = new JPanel();       
        l_brightness = new JLabel();
        l_brightness1 = new JLabel();
        
        b_load = new JButton();
        b_save = new JButton();
        
          
        p_directional = new JPanel();
        p_inspect_tools = new JPanel();
        jPanel5 = new JPanel();
        
        canvas2 = new Canvas();

        slide_contrast = new JSlider();
        slide_brightness = new JSlider();

        rb_gray_scale = new JRadioButton();
        rb_rgb = new JRadioButton();
        
        jMenuBar1 = new JMenuBar();
        menu_file = new JMenu();
        menu_edit = new JMenu();
        menu_tools = new JMenu();
        menu_help = new JMenu();
        menu_about = new JMenu();
        kk = new ImagePlus();
        // </editor-fold> 
        
    
        // <editor-fold defaultstate="collapsed" desc="Video Panel">              
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Robotic Vision");

        p_live_streaming.setBorder(BorderFactory.createTitledBorder(null, "Live Streaming", TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        p_live_streaming.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        p_live_streaming.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        l_vid_player.setText("<Media Player>");

        GroupLayout p_live_streamingLayout = new GroupLayout(p_live_streaming);
        p_live_streaming.setLayout(p_live_streamingLayout);
        p_live_streamingLayout.setHorizontalGroup(
            p_live_streamingLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(p_live_streamingLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(l_vid_player)
                .addContainerGap(163, Short.MAX_VALUE))
        );
        p_live_streamingLayout.setVerticalGroup(
            p_live_streamingLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(p_live_streamingLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(l_vid_player)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        p_title.setFont(new java.awt.Font("Arial", 3, 13)); // NOI18N

        l_title.setFont(new java.awt.Font("Lucida Grande", 3, 18)); // NOI18N
        l_title.setHorizontalAlignment(SwingConstants.CENTER);
        l_title.setText("Robotic Vision v0.1");

        GroupLayout p_titleLayout = new GroupLayout(p_title);
        p_title.setLayout(p_titleLayout);
        p_titleLayout.setHorizontalGroup(
            p_titleLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, p_titleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_title, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
        );
        p_titleLayout.setVerticalGroup(
            p_titleLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(p_titleLayout.createSequentialGroup()
                .addComponent(l_title)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        p_file_handling.setBorder(BorderFactory.createTitledBorder(null, "File Handling", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        b_load.setText("Load");
        b_load.setMaximumSize(new java.awt.Dimension(45, 29));
        b_load.setMinimumSize(new java.awt.Dimension(45, 29));
        b_load.setPreferredSize(new java.awt.Dimension(45, 29));
        b_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_loadActionPerformed(evt);
            }
        });

        b_save.setText("Save");
        b_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_saveActionPerformed(evt);
            }
        });

        GroupLayout p_file_handlingLayout = new GroupLayout(p_file_handling);
        p_file_handling.setLayout(p_file_handlingLayout);
        p_file_handlingLayout.setHorizontalGroup(
            p_file_handlingLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(p_file_handlingLayout.createSequentialGroup()
                .addComponent(b_load, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_save, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
        );

      //  p_file_handlingLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {b_load, b_save}, );

        p_file_handlingLayout.linkSize
	   	    (SwingConstants.HORIZONTAL, new Component[] 
		{b_load, b_save});


        p_file_handlingLayout.setVerticalGroup(
            p_file_handlingLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(p_file_handlingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(p_file_handlingLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(b_load, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_save))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        b_load.getAccessibleContext().setAccessibleName("b_load");
   // </editor-fold>
        
            
        // <editor-fold defaultstate="collapsed" desc="Image Viewer">     
        jPanel5.setBorder(BorderFactory.createTitledBorder(null, "Captured Moment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
         //       .addGap(19, 19, 19)//
                .addContainerGap() //235
                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(canvas2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                //.addContainerGap(262, Short.MAX_VALUE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
              //  .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING) 
              //  .addGap(0, 206, Short.MAX_VALUE)
                 .addComponent(jLabel2)
                
//               .addComponent(kk)
                .addComponent(canvas2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               // .addContainerGap(276, Short.MAX_VALUE))
                  .addGap(6, 6, 6))));
      
//       jPanel5.setM
                jPanel5.setMaximumSize(jPanel5.getSize() );
        // </editor-fold> 
        
        
        p_directional.setBorder(BorderFactory.createTitledBorder(null, "Directionals", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        GroupLayout p_directionalLayout = new GroupLayout(p_directional);
        p_directional.setLayout(p_directionalLayout);
        p_directionalLayout.setHorizontalGroup(
            p_directionalLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        p_directionalLayout.setVerticalGroup(
            p_directionalLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        p_inspect_tools.setBorder(BorderFactory.createTitledBorder(null, "Inspection Tools", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        l_brightness.setText("Brightness");

        l_brightness1.setText("Contrast");

        rb_gray_scale.setText("Gray-Scale");
        rb_gray_scale.setToolTipText("");
        rb_gray_scale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_gray_scaleActionPerformed(evt);
            }
        });

        rb_rgb.setText("RGB");
        rb_rgb.setToolTipText("");
        rb_rgb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_rgbActionPerformed(evt);
            }
        });

        GroupLayout p_inspect_toolsLayout = new GroupLayout(p_inspect_tools);
        p_inspect_tools.setLayout(p_inspect_toolsLayout);
        p_inspect_toolsLayout.setHorizontalGroup(
            p_inspect_toolsLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(p_inspect_toolsLayout.createSequentialGroup()
                .addGroup(p_inspect_toolsLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(l_brightness1)
                    .addComponent(l_brightness))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(p_inspect_toolsLayout.createSequentialGroup()
                .addGroup(p_inspect_toolsLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(p_inspect_toolsLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(p_inspect_toolsLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(slide_brightness, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(slide_contrast, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(p_inspect_toolsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rb_gray_scale)
                        .addGap(18, 18, 18)
                        .addComponent(rb_rgb)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        p_inspect_toolsLayout.setVerticalGroup(
            p_inspect_toolsLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(p_inspect_toolsLayout.createSequentialGroup()
                .addGroup(p_inspect_toolsLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(rb_gray_scale)
                    .addComponent(rb_rgb))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_brightness)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slide_brightness, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_brightness1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slide_contrast, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        menu_file.setText("File");
        jMenuBar1.add(menu_file);

        menu_edit.setText("Edit");
        jMenuBar1.add(menu_edit);

        menu_tools.setLabel("Tools");
        jMenuBar1.add(menu_tools);

        menu_help.setText("Help");
        jMenuBar1.add(menu_help);

        menu_about.setText("About");
        jMenuBar1.add(menu_about);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(p_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE) //
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(p_directional, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)//
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(p_file_handling, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(p_live_streaming, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_inspect_tools, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //layout.linkSize(new Component[] {jPanel5, p_inspect_tools}, SwingConstants.HORIZONTAL);
     layout.linkSize
	   	    (SwingConstants.HORIZONTAL, new Component[] 
		{jPanel5, p_inspect_tools});

     
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(p_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(p_live_streaming, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                
//                  .addGroup(panel_allLayout.createParallelGroup()
///                    .addComponent(panel_fly, GroupLayout.DEFAULT_SIZE, 
//				  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                
                .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                
                    .addComponent(p_directional, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_file_handling, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_inspect_tools, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        	
        layout.linkSize
	    (SwingConstants.VERTICAL, new Component[] 
		{jPanel5, p_live_streaming});
                
       // layout.linkSize(SwingConstants.VERTICAL, new Component[] {jPanel5, p_live_streaming}, );
//panel_dirLayout.linkSize
//	    (SwingConstants.VERTICAL, new Component[] 
//		{text_dir_prefix, text_label_extension, text_synf_extension});
        pack();
    }// </editor-fold>                        

      
    /**
     * This method is called from within the constructor to initialize the 
     * components and listeners.
     */
    /* 
    // <editor-fold defaultstate="collapsed" desc="init">                          
    private void init(){
        
           // instantiate 
     //   p_directionals = new JPanel();
        icon_up = new JLabel();
        icon_right = new JLabel();
        icon_left = new JLabel();
        icon_down = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       */ 
       // System.out.println(System.getProperty("user.dir"));
        // Get images to set as icons on label JComponents; 
        // default state disabled
   /*     icon_up.setIcon(new ImageIcon
                (getClass().getResource(dir_image_icons + "Arrow_up.jpg"))); 
        icon_up.setEnabled(false);

        icon_right.setIcon(new ImageIcon(getClass().getResource(dir_image_icons + "Arrow_right.jpg"))); // NOI18N
        icon_right.setEnabled(false);

        icon_left.setIcon(new ImageIcon(getClass().getResource(dir_image_icons + "Arrow_left.jpg"))); // NOI18N
        icon_left.setEnabled(false);

        icon_down.setIcon(new ImageIcon(getClass().getResource(dir_image_icons + "Arrow_down.jpg"))); // NOI18N
        icon_down.setEnabled(false);
*/
    
     /*    
        GroupLayout p_directionalsLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(p_directionalsLayout);
        p_directionalsLayout.setHorizontalGroup(
                p_directionalsLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(p_directionalsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_left, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(p_directionalsLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(icon_up, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                .addComponent(icon_down))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon_right)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        p_directionalsLayout.setVerticalGroup(
                p_directionalsLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(p_directionalsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(p_directionalsLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(p_directionalsLayout.createSequentialGroup()
                .addComponent(icon_up)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon_down))
                .addGroup(p_directionalsLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(p_directionalsLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(icon_right)
                .addComponent(icon_left))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        icon_up.getAccessibleContext().setAccessibleName("U");
        icon_left.getAccessibleContext().setAccessibleName("Up");
        icon_down.getAccessibleContext().setAccessibleName("L_down");
*//*
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .add(p_directionals, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .add(p_directionals, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
*/
 /*        //       ControllerEnvironment.getDefaultEnvironment().addControllerListener(this);
        addKeyListener(this);

        
    }*/
    // </editor-fold>
  
    
    
    private void b_loadActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void b_saveActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        
        cur_moment_view = 
                new ImageIcon("/Users/jrob/capstoneECE/capstone/OCU/swordfish/resources/hanger_test_image.jpg");
        ImagePlus jj = new ImagePlus("/Users/jrob/capstoneECE/capstone/OCU/swordfish/resources/hanger_test_image.jpg");
        kk.setImage(jj);
//        cur_moment_view.
              // jLabel2.setIcon(kk.clone());//cur_moment_view); 

    }                                        

    private void rb_gray_scaleActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void rb_rgbActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

       @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Handle the key pressed event from the text field.
     * The function checks if event was triggered via arrow keys on keypad. If 
     * so, the corresponding arrow icon will be enabled, i.e., will display 
     * its true color.
     */
    @Override
    public void keyPressed(KeyEvent e) {

        if (do_debug)
            System.out.println("Key Press: " + e.toString());
        

        int location = e.getKeyCode();
        
        
        if (location == KeyEvent.VK_LEFT) {
            icon_left.setEnabled(true);
        } else if (location == KeyEvent.VK_RIGHT) {
            icon_right.setEnabled(true);
        } else if (location == KeyEvent.VK_UP) {
            
            icon_up.setEnabled(true);
        } else if (location == KeyEvent.VK_DOWN) {
            icon_down.setEnabled(true);
        }
    }

    
    /**
     * Handle the key released event from the text field.
     * The function checks if event was triggered via arrow keys on keypad. If 
     * so, the corresponding arrow icon will be disabled, i.e., will lose its 
     * true color set when key was pressed.
     */
    @Override
    public void keyReleased(KeyEvent e) 
    {             
        if (do_debug)
            System.out.println("Key Released: " + e.toString());
                
        int location = e.getKeyCode();
        
        if (location == KeyEvent.VK_LEFT) {
            icon_left.setEnabled(false);
        } else if (location == KeyEvent.VK_RIGHT) {
            icon_right.setEnabled(false);
        } else if (location == KeyEvent.VK_UP) {
            
            icon_up.setEnabled(false);
        } else if (location == KeyEvent.VK_DOWN) {
            icon_down.setEnabled(false);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
                new MobileDirectionDisplay().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private java.awt.Canvas canvas2;
    private JButton b_load;
    private JButton b_save;
    private JLabel l_title;
    private JLabel l_vid_player;
     
//    private JLabel jLabel2;
      
    private JMenuBar jMenuBar1;
    private JPanel p_title;
    private JPanel jPanel5;
    private JRadioButton rb_gray_scale;
    private JRadioButton rb_rgb;
    private JLabel l_brightness;
    private JLabel l_brightness1;
    private JMenu menu_about;
    private JMenu menu_edit;
    private JMenu menu_file;
    private JMenu menu_help;
    private JMenu menu_tools;
    private JPanel p_directional;
    private JPanel p_file_handling;
    private JPanel p_inspect_tools;
    private JPanel p_live_streaming;
    private JSlider slide_brightness;
    private JSlider slide_contrast;
    // End of variables declaration                   

  //  @Override
    //public void actionPerformed(ActionEvent ae) {
      //System.out.println(ae.getSource());
      //System.out.println("eeee");
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        
//    }

    private void println(String property) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}


