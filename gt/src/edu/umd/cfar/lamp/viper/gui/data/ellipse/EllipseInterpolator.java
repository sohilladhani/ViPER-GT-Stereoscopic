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

package edu.umd.cfar.lamp.viper.gui.data.ellipse;

import edu.umd.cfar.lamp.viper.geometry.*;
import edu.umd.cfar.lamp.viper.gui.data.*;
import edu.umd.cfar.lamp.viper.util.*;

/**
 * @author davidm
 *
 */
public class EllipseInterpolator extends HelpInterpolate {
	private int nr(int theta) {
		theta = theta % 360;
		if (theta < 0) {
			theta += 360;
		}
		return theta;
	}


	public ArbitraryIndexList helpInterpolate(Object alpha, Object beta, long between) throws InterpolationException {
		Ellipse a = (Ellipse) alpha;
		Ellipse b = (Ellipse) beta;

		int[] A = new int[] {a.getX().intValue(), a.getY().intValue(), a.getWidth().intValue(), a.getHeight().intValue(), nr(a.getRotation())};
		int[] B = new int[] {b.getX().intValue(), b.getY().intValue(), b.getWidth().intValue(), b.getHeight().intValue(), nr(b.getRotation())};
		if (A[4] - B[4] > 180) {
			B[4] += 360;
		} else if (A[4] - B[4] < -180) {
			B[4] -= 360;
		}
		ArbitraryIndexList l = new LengthwiseEncodedList();
		Long i = new Long(0);
		while (i.longValue() < between) {
			int[] c = new int[5];
			for (int j = 0; j < 5; j++) {
				c[j] = (int) HelpInterpolate.oneNth(A[j], B[j], i.doubleValue(), between+1);
			}
			l.set(i, i = new Long(i.longValue() + 1), new Ellipse(c[0], c[1], c[2], c[3], c[4]));
		}
		return l;
	}

}
