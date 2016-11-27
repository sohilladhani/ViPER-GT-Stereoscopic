/*
 * Created on Feb 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package edu.umd.cfar.lamp.viper.gui.players;

import java.awt.*;
import java.io.*;

import viper.api.time.*;
import viper.api.time.Frame;

/**
 * @author davidm
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NativePlayer extends DataPlayerHelper {
	private MpegTest mt;
	private Frame now;
	private static boolean used = false;

	public NativePlayer(String path) {
		if (used) {
			//throw new IllegalStateException("Unfortunately, you can only use the native player once.");
		}
		String x = path.toLowerCase();
		if ((x.endsWith("g") && (x.endsWith("mpg") || x.endsWith("mpeg")))
			|| x.endsWith("avi") || x.endsWith("mov") || x.endsWith("mp4")
			|| x.endsWith("mp2")) {
			used = true;
			mt = new MpegTest(path);
			setNow(getSpan().getStartInstant());
		} else {
			throw new IllegalStateException("Not a recognized file extension: " + path);
		}
	}

	protected Image helpGetImage(Frame f)
		throws IOException, InterruptedException {
		return mt.getFrame(f.intValue()-1);
	}

	public Span getSpan() {
		return new Span(new Frame(1), new Frame(mt.getNumFrames()+1));
	}

	public Instant getNow() {
		return now;
	}

	public void setNow(Instant i) {
		now = (Frame) i;
	}

	public FrameRate getRate() {
		return new RationalFrameRate(1);
	}
	
	/* (non-Javadoc)
	 * @see edu.umd.cfar.lamp.viper.gui.players.DataPlayer#getImageType(viper.api.time.Instant)
	 */
	public String getImageType(Instant i) {
		Frame f = getRate().asFrame(i);
		char c = mt.getFrameTypeChar(f.intValue()-1);
		switch(c) {
			case 'B':
				return DataPlayer.B_FRAME;
			case 'P':
				return DataPlayer.P_FRAME;
			case 'I':
				return DataPlayer.I_FRAME;
			default:
				return DataPlayer.UNKNOWN_FRAME;
		}
	}
}
