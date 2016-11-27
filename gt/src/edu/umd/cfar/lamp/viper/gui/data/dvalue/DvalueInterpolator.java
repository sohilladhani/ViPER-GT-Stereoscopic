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

package edu.umd.cfar.lamp.viper.gui.data.dvalue;

import edu.umd.cfar.lamp.viper.gui.data.*;
import edu.umd.cfar.lamp.viper.util.*;


/**
 * @author davidm
 */
public class DvalueInterpolator extends HelpInterpolate {
	public ArbitraryIndexList helpInterpolate(Object alpha, Object beta, long between) throws InterpolationException {
		int a = ((Integer) alpha).intValue();
		int b = ((Integer) beta).intValue();

		ArbitraryIndexList l = new LengthwiseEncodedList();
		Long i = new Long(0);
		while (i.longValue() < between) {
			int nval = (int) HelpInterpolate.oneNth(a, b, i.doubleValue(), between+1);
			l.set(i, i = new Long(i.longValue() + 1), new Integer(nval));
		}
		return l;
	}
}
