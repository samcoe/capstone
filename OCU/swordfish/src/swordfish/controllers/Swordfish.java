package swordfish.controllers;

import swordfish.views.MobileDirectionDisplayKeyboard;
import swordfish.views.XBox360_DirectionDisplay;
import swordfish.views.window.ImageAnalyzerWindow;
import swordfish.views.window.LiveStreamerWindow;
import swordfish.views.window.LiveStreamerWindow2;

/**
 *
 * @author Sam Coe and Joe Robinson
 *
 */
public class Swordfish {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean do_robot_controller = true,
                do_video_streamer = false,
                do_xbox_dir_diplay = false,
                do_mobile_dis_keyboard = false,
                do_image_processor = false;

        //Pi 1 controls driving
        String p1_addr = "192.168.1.9";
        //Pi 2 controls servos and lift system
        String p2_addr = "192.168.1.69";
        int marlin_port = 5555;
        int tuna_port = 6789;

        VideoStreamer vs;
        VideoStreamer vs2;
        LiveStreamerWindow2 lsw2 =  new LiveStreamerWindow2();
        lsw2.setVisible(true);
        LiveStreamerWindow lsw = new LiveStreamerWindow(lsw2);;
        RobotController rc;

        if (do_video_streamer) {
            vs2 = new VideoStreamer();
            vs2.connect(p2_addr, tuna_port, lsw2);
            lsw2.setVideoStreamer(vs2);
            lsw.setVisible(true);
            vs = new VideoStreamer();
            vs.connect(p1_addr, tuna_port, lsw);
            lsw.setVideoStreamer(vs);
            lsw.setMediaWindows();
       }

        if (do_robot_controller) {
            rc = new RobotController();
            rc.connect(p2_addr, p2_addr, marlin_port, lsw);
        }
        
        if (do_xbox_dir_diplay) {
            XBox360_DirectionDisplay xboxDD = new XBox360_DirectionDisplay();
            xboxDD.setVisible(true);
        }

        if (do_mobile_dis_keyboard) {
            MobileDirectionDisplayKeyboard mddk = new MobileDirectionDisplayKeyboard();
            mddk.setVisible(true);
        }

        if (do_image_processor) {
            ImageAnalyzerWindow iaw = new ImageAnalyzerWindow();
            iaw.setVisible(true);
        }
    }
}
