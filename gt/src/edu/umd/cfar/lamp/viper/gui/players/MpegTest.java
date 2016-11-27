package edu.umd.cfar.lamp.viper.gui.players;


import java.awt.*;
import java.awt.image.*;

import edu.umd.cfar.lamp.nmpeg.*;

/**
 * Simple wrapper for the VirtualDub4Java MPEG decoder.
 */
public class MpegTest {
	InputFileMPEG file;
	private int w;
	private int h;

	static private void initialize(){
		Runtime rt = Runtime.getRuntime();
//		File libPath = new File("../viper-vdub/VirtualDub4Java/Debug/VirtualDub4Java.dll");
//		rt.load(libPath.getAbsolutePath());
		rt.loadLibrary("VirtualDub4Java");
		nmpeg.InitVdub();
		initialized = true;
		rt.addShutdownHook(new Thread() {
			public void run() {
				nmpeg.DeinitVdub();
				initialized = false;
			}});
	}
	private static boolean initialized = false;

	public MpegTest(String path) {
		if (!initialized) {
			initialize();
		} else {
			nmpeg.DeinitVdub();
			nmpeg.InitVdub();
		}
		file = new InputFileMPEG();
		file.InitS(new String[]{path});
		BITMAPINFOHEADER bih = file.getVideoMPEGSource().getImageFormat();
		w = bih.getBiWidth();
		h = bih.getBiHeight();
		//long bcompression = bih.getBiCompression();
		//int bdepth = bih.getBiBitCount();
		//("Image is " + w + "x" + h + " for " + getNumFrames() + " frames  at " + bdepth + "bpp");
	}
	public int getNumFrames() {
		return file.getFrameCount();
	}
	public char getFrameTypeChar(int f) {
		return file.getVideoMPEGSource().getFrameTypeChar(f);
	}
	public Image getFrame(int f) {
		ColorModel m;// = ColorModel.getRGBdefault();
		m = new DirectColorModel(32,
		0x0000ff00,	// Red
		0x00ff0000,	// Green
		0xff000000,	// Blue
		0x00000000	// Alpha
		);
		file.getVideoMPEGSource().goToFrame(f);
		ImageProducer ip = new ByteBufferAsIntBufferSource(w, h, m, file.getVideoMPEGSource().getFrameBuffer());
		Image i = Toolkit.getDefaultToolkit().createImage(ip);
		return i;
	}
	public static void main(String[] args) {
		MpegTest toTest = new MpegTest("C:/Documents and Settings/davidm/Desktop/20020214.mpg");
		System.err.println("frames = " + toTest.getNumFrames());
		toTest.getFrame(0);
//		toTest.file.getVideoMPEGSource().copyBufferToClipboard();
	}
}
