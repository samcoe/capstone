package user_tools;

import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent.*;

import net.java.games.input.ControllerEvent.*;
import net.java.games.input.AbstractController.*;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Controller.PortType.*;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.ControllerEvent;
import net.java.games.input.ControllerListener;
import net.java.games.input.Rumbler;
import net.java.games.input.Version;
import net.java.games.input.OSXEnvironmentPlugin;
import static user_tools.GameControllerPad.NUM_BUTTONS;

/**
 *
 * @author J. Robinson
 * @date September 10, 2013
 *
 * The purpose of this Class is to visual show the user when an arrow key on the
 * keyboard is pressed/held down.
 *
 * The display is constructed using image icons located in source directory with
 * the directory 'resources.' The interface used to listen for key events is
 * simply the KeyListener class offered through JAVA's API. The functions used
 * within this interface is setEnabled(true or false)... which adds color when
 * set true; else, color is removed.
 *
 */
public class XBox360_DirectionDisplay extends JFrame
        implements KeyListener,
        ActionListener,
        ControllerListener {

    private JLabel icon_down;
    private JLabel icon_left;
    private JLabel icon_right;
    private JLabel icon_up;
    private JPanel p_directionals;
    private boolean do_debug = true;
    private boolean[] key_flag = new boolean[1];
    private String dir_image_icons = "/user_tools/resources/";
    // global vars for controller
    private Controller controller; // for the game pad
    private Rumbler[] rumblers;
    private Component[] comps; // holds the components 
    // comps[] indices for specific components 
    private int xAxisIdx, yAxisIdx, zAxisIdx, rzAxisIdx;
    // indices for the analog sticks axes 
    private int povIdx; // index for the POV hat 
    private XBox360_DirectionDisplay gpController;
    // End of variables declaration   
    //public static final int DISPLAY_HEIGHT = 480;
    //public static final int DISPLAY_WIDTH = 640;
    public static final int NUM_BUTTONS = 12;
    private int buttonsIdx[]; // indices for the buttons  ava Prog. Techniques for Games. Chapter 28.9. Game Pad Draft #3 (9th Oct. 06) 
    private int rumblerIdx; // index for the rumbler being used 
    // <editor-fold defaultstate="collapsed" desc="Constructors">   

    /**
     * Creates new form Controls_Display
     */
    public XBox360_DirectionDisplay() {
        initComponents();
        initXBoxController();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //   Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH,DISPLAY_HEIGHT));
//Display.setFullscreen(false);
//Display.setTitle("Hello LWJGL World!");
//Display.create();

        //      Keyboard.create();
//throw new RuntimeException("Compiled Code");
    }

    public XBox360_DirectionDisplay(String name) {
        super(name);
    }// </editor-fold> 

    public void ControllerEvent(Controller cc)
    {
        System.out.println("dfdf");
    }
    public void initXBoxController() {
        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
        Controller[] cs = ce.getControllers();

        System.out.println("JInput version: " + Version.getVersion());
        ControllerEnvironment.getDefaultEnvironment();
        // print the name and type of each controller 
        for (int i = 0; i < cs.length; i++) {
            System.out.println(i + ". "
                    + cs[i].getName() + ", " + cs[i].getType());
        }

        //ce.addControllerListener(this);
        if (ce.isSupported()) {
            System.out.println("support");
        } else {
            System.out.println("no");
        }

        // get the game pad controller 
        controller = findGamePad(cs);
        System.out.println("Game controller: "
                + controller.getName() + ", "
                + controller.getType());
        // collect indices for the required game pad components 
        findCompIndices(controller);
        findRumblers(controller);

        //Controller.PortType.USB;

        System.out.print("here: " + Controller.PortType.USB);//USBisSupported());


        //  addControllerListener(this);


        ///        Event.class.
        // value = comps[ buttonsIdx[i] ].getPollData(); 



        // get the game pad controller 
        controller = findGamePad(cs);
        System.out.println("Game controller: "
                + controller.getName() + ", "
                + controller.getType());
        // collect indices for the required game pad components 
        findCompIndices(controller);
        findRumblers(controller);

    }

    private boolean isButton(Component c) {
        if (!c.isAnalog() && !c.isRelative()) { // digital & absolute 
            String className = c.getIdentifier().getClass().getName();
            if (className.endsWith("Button")) {
                return true;
            }
        }
        return false;
    } // end of isButton()

    public boolean isButtonPressed(int pos) {
        if ((pos < 1) || (pos > NUM_BUTTONS)) {
            System.out.println("Button position out of range (1-"
                    + NUM_BUTTONS + "): " + pos);
            return false;
        }
        float value = comps[ buttonsIdx[pos - 1]].getPollData();
        // array range is 0-NUM_BUTTONS-1 
        return ((value == 0.0f) ? false : true);
    } // end of isButtonPressed() 

    private void findButtons(Component[] comps) {
        buttonsIdx = new int[NUM_BUTTONS];
        int numButtons = 0;
        Component c;
        for (int i = 0; i < comps.length; i++) {
            c = comps[i];
            if (isButton(c)) { // deal with a button 
                if (numButtons == NUM_BUTTONS) // already enough buttons 
                {
                    System.out.println("Found an extra button; index: "
                            + i + ". Ignoring it");
                } else {
                    buttonsIdx[numButtons] = i; // store button index 
                    System.out.println("Found " + c.getName() + "; index: " + i);
                    numButtons++;
                }
            }
        }
        // fill empty spots in buttonsIdx[] with -1's 
        if (numButtons < NUM_BUTTONS) {
            System.out.println("Too few buttons (" + numButtons
                    + "); expecting " + NUM_BUTTONS);
            while (numButtons < NUM_BUTTONS) {
                buttonsIdx[numButtons] = -1;
                numButtons++;
            }
        }
    } // end of findButtons()

    private void findCompIndices(Controller controller) {
        comps = controller.getComponents();
        if (comps.length == 0) {
            System.out.println("No Components found");
            System.exit(0);
        } else {
            System.out.println("Num. Components: " + comps.length);
        }
        // get indices for sticks axes: (x,y) and (z,rz) 
        xAxisIdx = findCompIndex(comps,
                Component.Identifier.Axis.X, "x-axis");
        yAxisIdx = findCompIndex(comps,
                Component.Identifier.Axis.Y, "y-axis");
        zAxisIdx = findCompIndex(comps,
                Component.Identifier.Axis.Z, "z-axis");
        rzAxisIdx = findCompIndex(comps,
                Component.Identifier.Axis.RZ, "rz-axis");
        // get POV hat index 
        povIdx = findCompIndex(comps,
                Component.Identifier.Axis.POV, "POV hat");
        findButtons(comps);
    } // end of findCompIndices() 

    private int findCompIndex(Component[] comps,
            Component.Identifier id, String nm) {
        Component c;
        for (int i = 0; i < comps.length; i++) {
            c = comps[i];
            if ((c.getIdentifier() == id) && !c.isRelative()) {
                System.out.println("Found " + c.getName() + "; index: " + i);
                return i;
            }
        }
        System.out.println("No " + nm + " component found");
        return -1;
    } // end of findCompIndex() 

    private void findRumblers(Controller controller) {
        // get the game pad's rumblers 
        rumblers = controller.getRumblers();
        if (rumblers.length == 0) {
            System.out.println("No Rumblers found");
            rumblerIdx = -1;
        } else {
            System.out.println("Rumblers found: " + rumblers.length);
            rumblerIdx = rumblers.length - 1; // use last rumbler 
        }
    } // end of findRumblers() 

    private Controller findGamePad(Controller[] cs) {
        Controller.Type type;
        int index = 0;
        while (index < cs.length) {
            type = cs[index].getType();
            if ((type == Controller.Type.GAMEPAD)
                    || (type == Controller.Type.STICK)) {
                break;
            }
            index++;
        }
        if (index == cs.length) {
            System.out.println("No game pad found");
            System.exit(0);
        } else {
            System.out.println("Game pad index: " + Integer.toString(index));
        }
        return cs[index];
    } // end of findGamePad() 
 
    // <editor-fold defaultstate="collapsed" desc="Event Listeners">   
    /**
     * Handle the key typed event from the text field.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("KeyTyped");
    }

    /**
     * Handle the key pressed event from the text field. The function checks if
     * event was triggered via arrow keys on keypad. If so, the corresponding
     * arrow icon will be enabled, i.e., will display its true color.
     */
    @Override
    public void keyPressed(KeyEvent e) {

        if (key_flag[0]) {
            return;
        } else {
            key_flag[0] = true;
        }


        if (do_debug) {
            System.out.println("Key Press: " + e.toString());
        }


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
     * Handle the key released event from the text field. The function checks if
     * event was triggered via arrow keys on keypad. If so, the corresponding
     * arrow icon will be disabled, i.e., will lose its true color set when key
     * was pressed.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        key_flag[0] = false;

        if (do_debug) {
            System.out.println("Key Released: " + e.toString());
        }

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
     * Handle the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("ActionP");
    }
    // </editor-fold>  

    public void processKeyboard() {
        System.out.println("fjbdf");

    }

    // <editor-fold defaultstate="collapsed" desc="initComponents()">  
    /**
     * This method is called from within the constructor to initialize the form.
     * It sets the components on the displayed panel; in addition, sets
     * appropriate event listeners.
     */
    private void initComponents() {

        // instantiate 
        p_directionals = new JPanel();
        icon_up = new JLabel();
        icon_right = new JLabel();
        icon_left = new JLabel();
        icon_down = new JLabel();


        // set flags
        key_flag[0] = false;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Get images to set as icons on label JComponents; 
        // default state disabled
        icon_up.setIcon(new ImageIcon(getClass().getResource(dir_image_icons + "Arrow_up.jpg")));
        icon_up.setEnabled(false);

        icon_right.setIcon(new ImageIcon(getClass().getResource(dir_image_icons + "Arrow_right.jpg"))); // NOI18N
        icon_right.setEnabled(false);

        icon_left.setIcon(new ImageIcon(getClass().getResource(dir_image_icons + "Arrow_left.jpg"))); // NOI18N
        icon_left.setEnabled(false);

        icon_down.setIcon(new ImageIcon(getClass().getResource(dir_image_icons + "Arrow_down.jpg"))); // NOI18N
        icon_down.setEnabled(false);

        // Layour components
        org.jdesktop.layout.GroupLayout p_directionalsLayout = new org.jdesktop.layout.GroupLayout(p_directionals);
        p_directionals.setLayout(p_directionalsLayout);
        p_directionalsLayout.setHorizontalGroup(
                p_directionalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(p_directionalsLayout.createSequentialGroup()
                .addContainerGap()
                .add(icon_left, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(p_directionalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(icon_up, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(icon_down))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(icon_right)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        p_directionalsLayout.setVerticalGroup(
                p_directionalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(p_directionalsLayout.createSequentialGroup()
                .addContainerGap()
                .add(p_directionalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(p_directionalsLayout.createSequentialGroup()
                .add(icon_up)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(icon_down))
                .add(p_directionalsLayout.createSequentialGroup()
                .add(41, 41, 41)
                .add(p_directionalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(icon_right)
                .add(icon_left))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        icon_up.getAccessibleContext().setAccessibleName("U");
        icon_left.getAccessibleContext().setAccessibleName("Up");
        icon_down.getAccessibleContext().setAccessibleName("L_down");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(p_directionals, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(p_directionals, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));


        addKeyListener(this);
//        controllerListener(this);
        pack();
    }// </editor-fold>    

    @Override
    public void controllerRemoved(ControllerEvent ev) {

        System.out.println("cont Out");
        System.out.println(ev.getController());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerAdded(ControllerEvent ev) {

        System.out.println("cont in");
        System.out.println(ev.getController());
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} // end class