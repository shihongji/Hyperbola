package hongji.bola;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import hongji.bola.actions.AddContactsEntryAction;
import hongji.bola.contacts.IImageKeys;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	// Add actions in menu bar
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private IWorkbenchWindow window;
	private AddContactsEntryAction addContact;
	private StatusLineContributionItem statusItem;
	private Image statusImage;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		this.window = window;
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
		addContact = new AddContactsEntryAction(window);
		register(addContact);
		super.makeActions(window);

	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
//		super.fillMenuBar(menuBar);
		MenuManager hyperbolaMenu = new MenuManager("&Hyperbola", "hyperbola");
		hyperbolaMenu.add(addContact);
		hyperbolaMenu.add(new Separator());
		hyperbolaMenu.add(exitAction);
		MenuManager helpMenu = new MenuManager("&Help", "help");
		helpMenu.add(aboutAction);
		// create menus that are filled in by other plug-ins
//		hyperbolaMenu.add(new GroupMarker("other-actions"));
//		hyperbolaMenu.appendToGroup("other-actions", aboutAction);
		menuBar.add(hyperbolaMenu);
		// cascading menu
		hyperbolaMenu.add(helpMenu);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
		coolBar.add(toolbar);
		toolbar.add(addContact);
	}

	@Override
	protected void fillStatusLine(IStatusLineManager statusLine) {
//		System.out.println("fillStatusLine");
//		statusLine.setMessage("Initializing...");
		statusItem = new StatusLineContributionItem("LoggedInStatus");
		statusItem.setText("Logged in");
		statusLine.add(statusItem);
//		System.out.println("Running postWindowOpen");
//		try {
//			statusImage = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.ONLINE)
//					.createImage();
//			System.out.println("statusImage: " + statusImage);
//		} catch (Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//		}
//		IStatusLineManager statusLine = getWindowConfigurer().getActionBarConfigurer().getStatusLineManager();
//		System.out.println("Setting status message");
	}

	@Override
	public void dispose() {
		exitAction.dispose();
		aboutAction.dispose();
		addContact.dispose();
		super.dispose();
	}
}
