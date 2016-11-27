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


package edu.umd.cfar.lamp.viper.gui.data.point;

import edu.umd.cfar.lamp.viper.geometry.*;
import edu.umd.cfar.lamp.viper.gui.data.*;
import edu.umd.cfar.lamp.viper.util.*;

/**
 * Linear interpolation between two points.
 */
public class PointInterpolator extends HelpInterpolate {
	/**
	 * Interpolates from the {@link Pnt} <code>alpha</code> to the 
	 * {@link Pnt} <code>beta</code> in <code>between</code> steps.
	 * @param alpha {@inheritDoc}
	 * @param beta {@inheritDoc}
	 * @param between {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws InterpolationException {@inheritDoc}
	 */
	public ArbitraryIndexList helpInterpolate(Object alpha, Object beta, long between) throws InterpolationException {
			Pnt a = (Pnt) alpha;
			Pnt b = (Pnt) beta;

			int[] A = new int[] {a.getX().intValue(), a.getY().intValue()};
			int[] B = new int[] {b.getX().intValue(), b.getY().intValue()};
			ArbitraryIndexList l = new LengthwiseEncodedList();
			Long i = new Long(0);
			while (i.longValue() < between) {
				int[] c = new int[2];
				for (int j = 0; j < 2; j++) {
					c[j] = (int) HelpInterpolate.oneNth(A[j], B[j], i.doubleValue(), between+1);
				}
				l.set(i, i = new Long(i.longValue() + 1), new Pnt(c[0], c[1]));
			}
			return l;
	}
}
