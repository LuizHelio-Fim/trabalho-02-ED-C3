
package app;

import view.TelaSistema;

public class MainTela {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new TelaSistema();
        });
    }
}