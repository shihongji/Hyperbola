package hongji.bola.views;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;

import hongji.bola.HyperbolaAdapterFactory;
import hongji.bola.model.Contact;
import hongji.bola.model.ContactsEntry;
import hongji.bola.model.ContactsGroup;
import hongji.bola.model.IContactsListener;
import hongji.bola.model.Presence;
import hongji.bola.model.Session;

public class ContactsView extends ViewPart {
	public static final String ID = "hongji.bola.views.ContactsView";
	private TreeViewer treeViewer;
	private Session session;
	private IAdapterFactory adapterFactory = new HyperbolaAdapterFactory();

	public ContactsView() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		initializeSession(); // temporary tweak to build a fake model
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		Platform.getAdapterManager().registerAdapters(adapterFactory, Contact.class);
		getSite().setSelectionProvider(treeViewer);
		treeViewer.setLabelProvider(new WorkbenchLabelProvider());
		treeViewer.setContentProvider(new BaseWorkbenchContentProvider());
		treeViewer.setInput(session.getRoot());
		session.getRoot().addContactsListener(new IContactsListener() {
			public void contactsChanged(ContactsGroup contacts, ContactsEntry entry) {
				treeViewer.refresh();
			}
		});

	}
	
	private void initializeSession() {
		session = new Session();
		ContactsGroup root = session.getRoot();
		ContactsGroup friendsGroup = new ContactsGroup(root, "Friends");
		root.addEntry(friendsGroup);
		friendsGroup.addEntry(new ContactsEntry(friendsGroup, "Alice", "aliz", "localhost"));
		friendsGroup.addEntry(new ContactsEntry(friendsGroup, "Sydney", "syd", "localhost"));
        ContactsGroup familyGroup = new ContactsGroup(root, "Family");
        root.addEntry(familyGroup);
        familyGroup.addEntry(new ContactsEntry(familyGroup, "Mom", "mom", "localhost"));
		// change the presence of the first contact
		((ContactsEntry) friendsGroup.getEntries()[0]).setPresence(Presence.ONLINE);
		((ContactsEntry) friendsGroup.getEntries()[1]).setPresence(Presence.AWAY);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Platform.getAdapterManager().unregisterAdapters(adapterFactory);
		super.dispose();
	}

}
