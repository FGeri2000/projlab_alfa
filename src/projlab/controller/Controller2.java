package projlab.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Controller2 {

    public class MenuSystem {
        private JFrame frame;
        private JLabel titleLabel;
        private JButton newGameButton;
        private JButton loadGameButton;
        private JButton quitButton;

        public MenuSystem() {
            frame = new JFrame("Desert Water Network");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            titleLabel = new JLabel("Desert Water Network", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

            newGameButton = new JButton("New Game");
            newGameButton.setForeground(Color.BLACK);
            newGameButton.setOpaque(false);
            newGameButton.setContentAreaFilled(false);
            newGameButton.setBorderPainted(false);
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO megvalósítani a map kirajzolását
                    JOptionPane.showMessageDialog(frame, "New Game selected");
                }
            });

            loadGameButton = new JButton("Load Game");
            loadGameButton.setForeground(Color.BLACK);
            loadGameButton.setOpaque(false);
            loadGameButton.setContentAreaFilled(false);
            loadGameButton.setBorderPainted(false);
            loadGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO a fájlból olvasás és a fájlnak a kiválasztása, így jó-e
                    JFileChooser fileChooser = new JFileChooser();
                    int returnValue = fileChooser.showOpenDialog(frame);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        JOptionPane.showMessageDialog(frame, "A kivalasztott fajl: " + selectedFile.getAbsolutePath());
                    }
                }
            });

            quitButton = new JButton("Quit");
            quitButton.setForeground(Color.BLACK);
            quitButton.setOpaque(false);
            quitButton.setContentAreaFilled(false);
            quitButton.setBorderPainted(false);
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(3, 1, 10, 5));
            buttonPanel.setOpaque(false);
            buttonPanel.add(newGameButton);
            buttonPanel.add(loadGameButton);
            buttonPanel.add(quitButton);

            frame.setContentPane(new JLabel(new ImageIcon("menu.jpg")));
            frame.setLayout(new BorderLayout());
            frame.add(titleLabel, BorderLayout.NORTH);
            frame.add(buttonPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        }
    }

        public void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new MenuSystem();
                }
            });
        }
}
