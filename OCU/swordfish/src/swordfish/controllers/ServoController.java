/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swordfish.controllers;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jrob
 */
class ServoController {

    private ChannelExec channel;
    private JSch jsch;
    private Session session;
    String[] default_commands = new String[2];
    String[][] commands = new String[2][15];
    private static final int lower_limit = 50;
    private static final int upper_limit = 220;
    private static final int default_val = 150;
    private int[] current_position = new int[2];

    public ServoController() {
        default_commands[0] = "echo 0=50% > /dev/servoblaster;";
        default_commands[1] = "echo 1=50% > /dev/servoblaster;";

        commands[0][0] = "echo 0=-10 > /dev/servoblaster;";
        commands[0][1] = "echo 0=+10 > /dev/servoblaster;";

        commands[1][0] = "echo 1=-10 > /dev/servoblaster;";
        commands[1][1] = "echo 1=+10 > /dev/servoblaster;";


    }

    /**
     * Connects to Pi #2 via ssh to control servo (camera controls)
     */
    public boolean connect() {
        jsch = new JSch();
        try {
            session = jsch.getSession("pi", "raspberrypi", 22);
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
            return false;

        }
        session.setPassword("raspberry");
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        try {
            session.connect();
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
            return false;
        }
        return true;
    }
    /*
     * Move Servo
     */

    public void execute(String command) throws JSchException, IOException {
        channel = (ChannelExec) session.openChannel("exec");
        BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
        channel.setCommand(command);
        channel.connect();

        String msg = null;
        while ((msg = in.readLine()) != null) {
            System.out.println(msg);
        }
    }

    public void execute(int servo_id, int command) throws JSchException, IOException {
        channel = (ChannelExec) session.openChannel("exec");
        BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
        channel.setCommand(commands[servo_id][command]);
        channel.connect();

        String msg = null;
        while ((msg = in.readLine()) != null) {
            System.out.println(msg);
        }
    }

    /**
     * set back to default position
     */
    public void default_position(int servo_id) throws JSchException, IOException {

//        for (int ind = 0; ind < 2; ind++) {

        if (servo_id == 0) {
            channel = (ChannelExec) session.openChannel("exec");
            BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            channel.setCommand(default_commands[0]);
            channel.connect();

            String msg = null;
            while ((msg = in.readLine()) != null) {
                System.out.println(msg);
            }
            current_position[0] = default_val;
        } else if (servo_id == 1) {
            channel = (ChannelExec) session.openChannel("exec");
            BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            channel.setCommand(default_commands[1]);
            channel.connect();

            String msg = null;
            while ((msg = in.readLine()) != null) {
                System.out.println(msg);
            }
            current_position[1] = default_val;
        }


//            channel.disconnect();
//        }

    }

    public void exit() {
//        try {
//            this.default_position();
//        } catch (JSchException | IOException ex) {
//            Logger.getLogger(ServoController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        channel.disconnect();
        session.disconnect();
        System.out.println("Servo has been disconnected");
    }
}
