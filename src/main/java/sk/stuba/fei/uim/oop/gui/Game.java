package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {

    public Game() {
        GameLogic logic = new GameLogic();

        JFrame frame = new JFrame("Reversi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(930, 670);
        frame.setResizable(false);

        frame.setLayout(new BorderLayout());
        frame.addKeyListener(logic);
        frame.add(logic.getRender());

        JPanel sideMenu = new JPanel();
        JPanel chipsPanel = new JPanel();
        JPanel colorsPanel = new JPanel();
        JPanel resetPanel = new JPanel();

        sideMenu.setLayout(new GridLayout(4,1));
        chipsPanel.setLayout(new GridLayout(2,1));
        colorsPanel.setLayout(new GridLayout());
        resetPanel.setLayout(new GridLayout());

        chipsPanel.add(logic.getChips1Label());
        chipsPanel.add(logic.getChips2Label());

        JLabel colorLabel = new JLabel("Your color:");
        colorsPanel.add(colorLabel);
        colorsPanel.add(logic.getColorList());


        JSlider slider = new JSlider(6, 12, 8);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(2);
        slider.setPaintLabels(true);
//        slider.addChangeListener(logic);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(logic);
        resetPanel.add(resetButton);

        sideMenu.add(chipsPanel);
        sideMenu.add(slider);
        sideMenu.add(colorsPanel);
        sideMenu.add(resetButton);
        sideMenu.setBackground(Color.LIGHT_GRAY);
        frame.add(sideMenu, BorderLayout.LINE_END);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
