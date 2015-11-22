package gasstationmain;

import javax.swing.SwingUtilities;
import gasstationgui.model.TableModelGasStation;
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
	    public void run() {
		new GasStationView().setVisible(true);
	    }
	});
    }
}
