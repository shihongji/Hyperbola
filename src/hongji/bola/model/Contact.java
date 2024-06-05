package hongji.bola.model;

import org.eclipse.core.runtime.PlatformObject;

public abstract class Contact extends PlatformObject {
	public abstract String getName();

	public abstract ContactsGroup getParent();
}