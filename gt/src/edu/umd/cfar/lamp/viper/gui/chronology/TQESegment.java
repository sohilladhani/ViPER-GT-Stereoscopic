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


package edu.umd.cfar.lamp.viper.gui.chronology;

import java.awt.*;
import java.awt.geom.*;

import viper.api.time.*;
import edu.umd.cfar.lamp.chronicle.*;
import edu.umd.cs.piccolo.*;
import edu.umd.cs.piccolo.nodes.*;
import edu.umd.cs.piccolo.util.*;

public class TQESegment extends PNode {
	private PPath startNode;
	private PPath endNode;
	private Line2D line;
	private InstantInterval i;
	
	public TQESegment(
		double radius,
		Paint color,
		InstantInterval i) {
		startNode =
			new PPath(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
		startNode.addClientProperty(PToolTip.TOOLTIP_PROPERTY, i.getStart().toString());
		startNode.setPaint(color);
		this.addChild(startNode);
		endNode =
			new PPath(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
		startNode.addClientProperty(PToolTip.TOOLTIP_PROPERTY, i.getEndInstant().previous().toString());
		endNode.setPaint(color);
		this.addChild(endNode);
		this.setPaint(color);
		this.i = i;
	}
	public boolean setBounds(
		double x,
		double y,
		double width,
		double height) {
		if (super.setBounds(x, y, width, height)) {
			double yC = super.getBoundsReference().getCenterY();
			double x1 = super.getBoundsReference().getMinX();
			double x2 = super.getBoundsReference().getMaxX();
			x2 -= width / i.width();
			x2 = Math.max(x1, x2);

			PBounds oldStartBounds = startNode.getBoundsReference();
			double w = oldStartBounds.getWidth();
			double h = oldStartBounds.getHeight();
			double yMin = y + (height / 2) - (h / 2);
			startNode.setBounds(x1 - (w / 2), yMin, w, h);
			endNode.setBounds(x2 - (w / 2), yMin, w, h);

			line = new Line2D.Double(x1, yC, x2, yC);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return
	 */
	public PPath getEndNode() {
		return endNode;
	}

	/**
	 * @return
	 */
	public InstantInterval getI() {
		return i;
	}

	/**
	 * @return
	 */
	public PPath getStartNode() {
		return startNode;
	}
	
	/**
	 * @see edu.umd.cs.piccolo.PNode#paint(edu.umd.cs.piccolo.util.PPaintContext)
	 */
	protected void paint(PPaintContext paintContext) {
		Graphics2D g2 = paintContext.getGraphics();
		Paint old = g2.getPaint();
		g2.setPaint(getPaint());
		g2.draw(line);
		g2.setPaint(old);
	}

}
