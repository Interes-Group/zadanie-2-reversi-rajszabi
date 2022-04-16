package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {

    public Game() {

        GameLogic logic = new GameLogic();
        logic.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logic.getFrame().setSize(700, 520);
        logic.getFrame().setResizable(false);
        logic.getFrame().setLayout(new BorderLayout());
        logic.getFrame().addKeyListener(logic);

        logic.getSlider().setPaintTrack(true);
        logic.getSlider().setMinorTickSpacing(1);
        logic.getSlider().setMajorTickSpacing(2);
        logic.getSlider().setPaintLabels(true);
        logic.getSlider().addChangeListener(logic);

        logic.getFrame().addKeyListener(logic);
        logic.getFrame().setFocusable(true);
        logic.getFrame().add(logic.getRender());

        JPanel sideMenu = new JPanel();
        JPanel chipsPanel = new JPanel();
        JPanel resetPanel = new JPanel();

        sideMenu.setLayout(new GridLayout(3,1));
        chipsPanel.setLayout(new GridLayout(2,1));
        resetPanel.setLayout(new GridLayout());

        chipsPanel.add(logic.getChips1Label());
        chipsPanel.add(logic.getChips2Label());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(logic);
        resetPanel.add(resetButton);

        sideMenu.add(chipsPanel);
        sideMenu.add(logic.getSlider());
        sideMenu.add(resetButton);
        sideMenu.setBackground(Color.LIGHT_GRAY);
        logic.getFrame().add(sideMenu, BorderLayout.LINE_END);

        logic.getFrame().setVisible(true);
    }
}
