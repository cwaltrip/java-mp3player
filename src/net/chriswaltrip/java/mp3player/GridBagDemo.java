package net.chriswaltrip.java.mp3player;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

/*
 * GridBagLayoutDemo.java requires no other files.
 */

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GridBagDemo {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;

    public static void addComponentsToPane(Container pane) {

        JButton button;
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
//        c.gridwidth = 5;
//        c.gridheight = 6;

//	button = new JButton("Long-Named Button 4");
//	c.fill = GridBagConstraints.BOTH;
////	c.ipady = 40;      //make this component tall
//	c.weightx = 0.5;
//	c.gridwidth = 5;
//	c.gridx = 0;
//	c.gridy = 0;
//	pane.add(button, c);

	button = new JButton("Button 1");
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 0.5;
//	c.gridwidth = 1;
	c.gridx = 0;
	c.gridy = 1;
	pane.add(button, c);

	button = new JButton("Button 2");
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 0.5;
//	c.gridwidth = 1;
	c.gridx = 1;
	c.gridy = 1;
	pane.add(button, c);

	button = new JButton("Button 3");
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 0.5;
//	c.gridwidth = 1;
	c.gridx = 2;
	c.gridy = 1;
	pane.add(button, c);

	button = new JButton("Button 4");
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 0.5;
//	c.gridwidth = 2;
	c.gridx = 3;
	c.gridy = 1;
	pane.add(button, c);

	button = new JButton("Button 5");
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 0.5;
//	c.gridwidth = 3;
	c.gridx = 4;
	c.gridy = 1;
	pane.add(button, c);

//	button = new JButton("Button 6");
//	c.fill = GridBagConstraints.BOTH;
//	c.weightx = 0.5;
////	c.gridwidth = 1;
//	c.gridx = 1;
//	c.gridy = 3;
//	pane.add(button, c);
//
//	button = new JButton("Button 7");
//	c.fill = GridBagConstraints.BOTH;
//	c.weightx = 0.5;
////	c.gridwidth = 1;
//	c.gridx = 2;
//	c.gridy = 4;
//	pane.add(button, c);
//
//	button = new JButton("Button 8");
//	c.fill = GridBagConstraints.BOTH;
//	c.weightx = 0.5;
////	c.gridwidth = 3;
//	c.gridx = 3;
//	c.gridy = 5;
//	pane.add(button, c);

	button = new JButton("Third");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 2;
	c.gridy = 2;
	pane.add(button, c);

	button = new JButton("Fourth");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 3;
	c.gridy = 3;
	pane.add(button, c);

	button = new JButton("Fifth");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 4;
	c.gridy = 4;
	pane.add(button, c);

	button = new JButton("Last");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = 0;       //reset to default
//	c.weighty = 1.0;   //request any extra vertical space
	c.anchor = GridBagConstraints.PAGE_END; //bottom of space
//	c.insets = new Insets(10,0,0,0);  //top padding
	c.gridx = 1;       //aligned with button 2
	c.gridwidth = 2;   //2 columns wide
	c.gridy = 5;       //sixth row
	pane.add(button, c);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
    
}