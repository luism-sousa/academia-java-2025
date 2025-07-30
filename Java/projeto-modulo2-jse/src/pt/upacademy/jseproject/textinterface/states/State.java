package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.utils.scannerUtils.ScannerUtils;

public abstract class State {
	public static final ScannerUtils sc = new ScannerUtils();

	public abstract int run();
}
