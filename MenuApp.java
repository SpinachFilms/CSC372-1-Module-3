import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Random;

public class MenuApp extends JFrame {
    private JTextArea textArea;

    public MenuApp() {
        setTitle("CSC372 - Menu GUI");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem showDateTime = new JMenuItem("Show Date and Time");
        JMenuItem saveToFile = new JMenuItem("Save to log.txt");
        JMenuItem changeColor = new JMenuItem("Change Background Color (Green Hue)");
        JMenuItem exitApp = new JMenuItem("Exit");

        showDateTime.addActionListener(e -> {
            LocalDateTime now = LocalDateTime.now();
            textArea.append("Current Date and Time: " + now + "\n");
        });

        saveToFile.addActionListener(e -> {
            try {
                Files.write(Paths.get("log.txt"), textArea.getText().getBytes());
                JOptionPane.showMessageDialog(this, "Saved to log.txt");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.");
            }
        });

        changeColor.addActionListener(e -> {
            Random rand = new Random();
            int greenHue = 100 + rand.nextInt(156); // Limita entre 100 y 255
            Color greenShade = new Color(rand.nextInt(56), greenHue, rand.nextInt(56));
            getContentPane().setBackground(greenShade);
        });

        exitApp.addActionListener(e -> System.exit(0));

        menu.add(showDateTime);
        menu.add(saveToFile);
        menu.add(changeColor);
        menu.add(exitApp);
        menuBar.add(menu);

        setJMenuBar(menuBar);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuApp());
    }
}
