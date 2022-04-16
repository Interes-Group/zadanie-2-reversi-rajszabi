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
        logic.getSlider().setMinorTickSpacing(2);
        logic.getSlider().setMajorTickSpacing(2);
        logic.getSlider().setSnapToTicks(true);
        logic.getSlider().setPaintLabels(true);
        logic.getSlider().addChangeListener(logic);

        logic.getFrame().addKeyListener(logic);
        logic.getFrame().setFocusable(true);
        logic.getFrame().add(logic.getRender());

        JPanel sideMenu = new JPanel();
        JPanel queuePanel = new JPanel();
        JPanel chipsPanel = new JPanel();
        JPanel sliderPanel = new JPanel();
        JPanel resetPanel = new JPanel();

        sideMenu.setLayout(new GridLayout(4,1));
        queuePanel.setLayout(new GridBagLayout());
        chipsPanel.setLayout(new GridBagLayout());
        sliderPanel.setLayout(new GridLayout(2,1));

        logic.getQueueLabel().setFont(new Font("Mina", Font.BOLD, 18));
        logic.getChipsLabel().setFont(new Font("Mina", Font.BOLD, 18));
        logic.getMapLabel().setHorizontalAlignment(JLabel.CENTER);
        logic.getMapLabel().setFont(new Font("Mina", Font.PLAIN, 20));

        queuePanel.add(logic.getQueueLabel());
        chipsPanel.add(logic.getChipsLabel());
        sliderPanel.add(logic.getMapLabel());
        sliderPanel.add(logic.getSlider());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(logic);
        resetPanel.add(resetButton);

        sideMenu.add(queuePanel);
        sideMenu.add(chipsPanel);
        sideMenu.add(sliderPanel);
        sideMenu.add(resetButton);
        sideMenu.setBackground(Color.LIGHT_GRAY);
        logic.getFrame().add(sideMenu, BorderLayout.LINE_END);

        logic.getFrame().setVisible(true);
    }
}
