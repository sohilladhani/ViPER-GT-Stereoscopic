/***************************************
 *            ViPER                    *
 *  The Video Processing               *
 *         Evaluation Resource         *
 *                                     *
 *  Distributed under the GPL license  *
 *        Terms available at gnu.org.  *
 *                                     *
 *  Copyright University of Maryland,  *
 *                      College Park.  *
 ***************************************/

package edu.umd.cfar.lamp.viper.gui.table;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import viper.api.time.*;
import viper.api.time.Frame;
import edu.umd.cfar.lamp.viper.gui.core.*;
import edu.umd.cfar.lamp.viper.gui.remote.*;


class InterpQuery extends JFrame {
	protected JInstantField number;
	private Iterator which;
	private ViperViewMediator mediator;
	private JPanel panel;
	private JRadioButton preferFrame;
	private JRadioButton preferTime;
	public InterpQuery(Iterator l, ViperViewMediator mediator) {
		super("Interpolate from Current Frame");
		this.mediator = mediator;
		which = l;
		JButton interpolate = new JButton("Interpolate");
		JButton cancel = new JButton("Cancel");
		preferFrame = new JRadioButton("Frame");
		preferFrame.setSelected(true);
		preferTime = new JRadioButton("Millisecond");
		number = new JInstantField();
		number.setPreferredSize(new Dimension(100, 24));

		interpolate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				execute();
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterpQuery.this.setVisible(false);
				InterpQuery.this.dispose();
			}
		});
		preferFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				number.setUnitPreference(Frame.class);
				preferTime.setSelected(false);
			}
		});
		preferTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				number.setUnitPreference(Time.class);
				preferFrame.setSelected(false);
			}
		});

		panel = new JPanel();
		panel.add(preferFrame);
		panel.add(preferTime);
		panel.add(number);
		panel.add(cancel);
		panel.add(interpolate);
		panel.validate();
		super.getContentPane().add(panel);
		super.pack();
		super.validate();
	}
	protected void execute() {
		Instant to = (Instant) number.getValue();
		Instant from = mediator.getMajorMoment();
		mediator.getPropagator().interpolateDescriptors(which, from, to);
		InterpQuery.this.setVisible(false);
		InterpQuery.this.dispose();
	}
}