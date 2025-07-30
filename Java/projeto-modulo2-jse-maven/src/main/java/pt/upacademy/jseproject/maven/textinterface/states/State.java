package pt.upacademy.jseproject.maven.textinterface.states;

import pt.upacademy.jseproject.maven.utils.scannerUtils.ScannerUtils;

public abstract class State {
	public static final ScannerUtils sc = new ScannerUtils();

	public abstract int run();
}
