package gasstationmain;

import javax.swing.SwingUtilities;
import gasstationgui.view.GasStationView;

/**
 *
 * @author Giovani
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
	    public void run() {
		new GasStationView().setVisible(true);
	    }
	});
    }
}
