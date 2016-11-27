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

package edu.umd.cfar.lamp.viper.gui.data.bbox;

import edu.umd.cfar.lamp.viper.geometry.*;
import edu.umd.cfar.lamp.viper.gui.data.*;
import edu.umd.cfar.lamp.viper.util.*;


/**
 */
public class BboxInterpolator extends HelpInterpolate {
	public ArbitraryIndexList helpInterpolate(Object alpha, Object beta, long between) throws InterpolationException {
		BoundingBox a = (BoundingBox) alpha;
		BoundingBox b = (BoundingBox) beta;

		int[] A = new int[] {a.getX(), a.getY(), a.getWidth(), a.getHeight()};
		int[] B = new int[] {b.getX(), b.getY(), b.getWidth(), b.getHeight()};
		ArbitraryIndexList l = new LengthwiseEncodedList();
		Long i = new Long(0);
		while (i.longValue() < between) {
			int[] c = new int[4];
			for (int j = 0; j < 4; j++) {
				c[j] = (int) HelpInterpolate.oneNth(A[j], B[j], i.doubleValue(), between+1);
			}
			l.set(i, i = new Long(i.longValue() + 1), new BoundingBox(c));
		}
		return l;
	}
}
