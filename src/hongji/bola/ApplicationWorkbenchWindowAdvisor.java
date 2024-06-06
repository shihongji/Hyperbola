package hongji.bola;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import hongji.bola.contacts.IImageKeys;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	private Image statusImage;

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(800, 600));
		// The menu bar is shown only if it contains menu items
		configurer.setShowMenuBar(true);
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true);
		configurer.setTitle("Hyperbola"); //$NON-NLS-1$
	}

	@Override
	public void postWindowOpen() {
		System.out.println("Running postWindowOpen");
		try {
			statusImage = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.ONLINE)
					.createImage();
			System.out.println("statusImage: " + statusImage);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		IStatusLineManager statusLine = getWindowConfigurer().getActionBarConfigurer().getStatusLineManager();
		statusLine.setMessage("Online");
//		if (statusLine instanceof StatusLineManager) {
//			Control statusControl = ((StatusLineManager) statusLine).createControl(getWindowConfigurer().getWindow().getShell());
//			if (statusControl != null) {
//				System.out.println("Setting status control");
//			} else {
//				System.out.println("Status control is null");
//			}
//		}
//        // Check if the status line exists and is not disposed
//        if (statusLine != null ) {
//            System.out.println("Setting status message");
//            statusLine.setMessage(statusImage, "Online");
//            System.out.println("Status message set");
//        } else {
//            System.out.println("Status line does not exist or is disposed");
//        }
	}

	@Override
	public void dispose() {
		if (statusImage != null) {
			statusImage.dispose();
		}
		super.dispose();
	}
}
