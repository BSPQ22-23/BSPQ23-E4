package spq.windows;

import javax.swing.JFrame;

public class MyWindow extends JFrame {

    public MyWindow() {
        super("My Window"); // Set the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the window is closed
        setSize(400, 300); // Set the size of the window
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Show the window
    }
    
    
}
