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

package edu.umd.cfar.lamp.viper.gui.canvas;

import java.awt.geom.*;
import java.util.logging.*;

import edu.umd.cs.piccolo.*;
import edu.umd.cs.piccolo.event.*;

/**
 * 
 * @author clin
 */
public class ViperCreatorManager extends PInputManager {
	private static Logger logger = Logger
			.getLogger("edu.umd.cfar.lamp.viper.gui.canvas");

	private ViperDataPLayer layer;

	public ViperCreatorManager(ViperDataPLayer layerIn) {
		layer = layerIn;
	}

	public void mouseClicked(PInputEvent e) {
		logger.finer("Mouse clicked");
		if (layer.getActiveCreator() == null)
			return;

		layer.getActiveCreator().mouseClicked(e);
	}

	public void mousePressed(PInputEvent e) {
		if (layer.getActiveCreator() == null)
			return;

		logger.finer("Mouse pressed " + position(e.getPosition()));

		layer.getActiveCreator().mousePressed(e);
		//layer.invalidatePaint() ;
	}

	public void mouseReleased(PInputEvent e) {
		logger.fine("Mouse released " + position(e.getPosition()));
		if (layer.getActiveCreator() == null)
			return;

		logger.fine("Mouse released 2 " + position(e.getPosition()));
		layer.getActiveCreator().mouseReleased(e);
	}

	public void mouseDragged(PInputEvent e) {
		// System.out.println( "Mouse dragged" ) ;
		if (layer.getActiveCreator() == null)
			return;

		layer.getActiveCreator().mouseDragged(e);
	}

	public void mouseMoved(PInputEvent e) {
		//	System.out.println( "Mouse moved" ) ;
		if (layer.getActiveCreator() == null)
			return;

		layer.getActiveCreator().mouseMoved(e);
	}

	public void keyPressed(PInputEvent event) {
		super.keyPressed(event);
		if (layer.getActiveCreator() == null)
			return;

		layer.getActiveCreator().keyPressed(event);
	}

	public void keyReleased(PInputEvent event) {
		super.keyReleased(event);
		if (layer.getActiveCreator() == null)
			return;

		layer.getActiveCreator().keyReleased(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.umd.cs.piccolo.event.PBasicInputEventHandler#keyTyped(edu.umd.cs.piccolo.event.PInputEvent)
	 */
	public void keyTyped(PInputEvent event) {
		// TODO Auto-generated method stub
		super.keyTyped(event);

		if (layer.getActiveCreator() == null)
			return;

		layer.getActiveCreator().keyPressed(event);
	}

	public String position(Point2D pt) {
		String s = "( " + pt.getX() + ", " + pt.getY() + " )";
		return s;
	}
}

