import javax.swing.JFrame;
import javax.swing.JLabel;

public class InventoryManagementWindow extends JFrame {

    public InventoryManagementWindow() {
        setTitle("Inventory Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window when closed
        JLabel label = new JLabel("Inventory Management Window");
        add(label);
    }
}
