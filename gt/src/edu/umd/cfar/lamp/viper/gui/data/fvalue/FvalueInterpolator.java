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

package edu.umd.cfar.lamp.viper.gui.data.fvalue;

import edu.umd.cfar.lamp.viper.gui.data.*;
import edu.umd.cfar.lamp.viper.util.*;


/**
 * @author davidm
 */
public class FvalueInterpolator extends HelpInterpolate {
	public ArbitraryIndexList helpInterpolate(Object alpha, Object beta, long between) throws InterpolationException {
		double a = ((Double) alpha).doubleValue();
		double b = ((Double) beta).doubleValue();

		ArbitraryIndexList l = new LengthwiseEncodedList();
		Long i = new Long(0);
		while (i.longValue() < between) {
			double nval = HelpInterpolate.oneNth(a, b, i.doubleValue(), between+1);
			l.set(i, i = new Long(i.longValue() + 1), new Double(nval));
		}
		return l;
	}
}
