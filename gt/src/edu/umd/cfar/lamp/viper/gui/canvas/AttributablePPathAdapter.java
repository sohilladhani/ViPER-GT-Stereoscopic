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

import edu.umd.cfar.lamp.viper.gui.canvas.datatypes.*;
import edu.umd.cfar.lamp.viper.util.*;
import edu.umd.cs.piccolo.nodes.*;

/**
 * Adds some style management to the PPath node.
 * @author davidm
 */
public abstract class AttributablePPathAdapter extends PPath implements Attributable {
	private ShapeDisplayProperties displayProperties = HighlightSingleton.STYLE_UNSELECTED;
	private ShapeDisplayProperties highlightDisplayProperties = HighlightSingleton.STYLE_HOVER;
	private ShapeDisplayProperties handleDisplayProperties = HighlightSingleton.STYLE_HANDLE;
	
	public ShapeDisplayProperties getHandleDisplayProperties() {
		return handleDisplayProperties;
	}
	public ShapeDisplayProperties getDisplayProperties() {
		return displayProperties;
	}
	public ShapeDisplayProperties getHighlightDisplayProperties() {
		return highlightDisplayProperties;
	}
	/** @inheritDoc */
	public void setHandleDisplayProperties(
			ShapeDisplayProperties handleDisplayProperties) {
		this.handleDisplayProperties = handleDisplayProperties;
		resetStyle();
	}
	/** @inheritDoc */
	public void setDisplayProperties(ShapeDisplayProperties properties) {
		this.displayProperties = properties;
		resetStyle();
	}
	/** @inheritDoc */
	public void setHighlightDisplayProperties(
			ShapeDisplayProperties properties) {
		this.highlightDisplayProperties = properties;
		resetStyle();
	}
	
	/**
	 * Called by the setDisplayProperties methods, and whenever the 
	 * style needs changing.
	 */
	protected abstract void resetStyle();
}
