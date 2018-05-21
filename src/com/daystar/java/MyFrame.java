/**
 * A  number guessing game java program
 * by Isaac Sichangi Wanjala
 * 21st May 2018
 * isaacsichangi@gmail.com
 *
 *
 */


package com.daystar.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MyFrame extends JFrame {

    private JLabel title;
    private JLabel status;
    private JButton replay;
    private JTextField textField;
    private JPanel panel;
    private int guessed_number;
    private int val;
    private int gap;


    public MyFrame() {

        super("Guessing Number Game");

        guessed_number = getRandomNumber(100);

        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container containerPane = getContentPane();
        containerPane.setLayout(new FlowLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        title = new JLabel();
        title.setText("GUESS A NUMBER BETWEEN 1 TO 100");


        textField = new JTextField();

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {


                } else {

                    int length = textField.getText().length();

                    if (length > 0 && length < 3) {

                        val = Integer.parseInt(textField.getText() + String.valueOf(Character.getNumericValue(e.getKeyChar())));

                        gap = guessed_number - val;

                        if (gap == 0) {

                            status.setText("CORRECT");


                            restartGame();

                        } else if (gap >= 1 && gap <= 5) {


                            status.setText("TOO CLOSE");

                            changeColorRed();

                        } else {

                            status.setText("TOO FAR");
                           changeColorBlue();

                        }


                    } else {


                        val = Character.getNumericValue(e.getKeyChar());


                        gap = guessed_number - val;

                        if (gap == 0) {

                            status.setText("CORRECT");



                            restartGame();

                        } else if (gap >= 1 && gap <= 5) {

                           changeColorRed();

                            status.setText("TOO CLOSE");

                        } else {

                            status.setText("TOO FAR");
                           changeColorBlue();

                        }


                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

                    int length = textField.getText().length();

                    if (length < 1) {

                       resetState();

                    } else {

                        val = Integer.parseInt(textField.getText());
                        gap = guessed_number - val;

                        if (gap == 0) {



                            status.setText("CORRECT");

                            restartGame();

                        } else if (gap >= 1 && gap <= 5) {

                           changeColorRed();

                            status.setText("TOO CLOSE");

                        } else {

                            status.setText("TOO FAR");
                           changeColorBlue();

                        }


                    }


                }

            }
        });
        replay = new JButton();
        replay.setText("Play Again");
        replay.setEnabled(false);

        replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

             guessed_number =    getRandomNumber(100);
             textField.setText("");
             resetState();
             replay.setEnabled(false);
             textField.setEnabled(true);

            }
        });

        status = new JLabel();


        panel.add(title);
        panel.add(textField);
        panel.add(replay);
        panel.add(status);

        containerPane.add(panel);

    }

    private int getRandomNumber(int max) {

        int value = 0;

        Random random = new Random();

        value = random.nextInt(max + 1);

        return value;

    }


    private void changeColorBlue() {

        textField.setForeground(new Color(255, 255, 255));
        textField.setBackground(new Color(0, 0, 255));
    }


    private void changeColorRed() {

        textField.setBackground(new Color(255, 0, 0));
        textField.setForeground(new Color(255, 255, 255));


    }



    private void resetState(){

        textField.setBackground(new Color(255, 255, 255));
        textField.setForeground(new Color(0, 0, 0));
        status.setText("");
    }

    private void restartGame(){

        textField.setEnabled(false);
     replay.setEnabled(true);
    }

}