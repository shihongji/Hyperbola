package hongji.bola.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import hongji.bola.Application;
import hongji.bola.contacts.IImageKeys;
import hongji.bola.model.ContactsEntry;
import hongji.bola.model.ContactsGroup;

public class AddContactsEntryAction extends Action implements ISelectionListener, IWorkbenchAction {
	private final IWorkbenchWindow window;
	public final static String ID = "hongji.bola.actions.AddContactsEntryAction";
	private IStructuredSelection selection;
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		window.getSelectionService().removeSelectionListener(this);

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		// TODO Auto-generated method stub
		if (incoming instanceof IStructuredSelection) {
            selection = (IStructuredSelection) incoming;
            setEnabled(selection.size() == 1 && 
            		selection.getFirstElement() instanceof ContactsGroup);
		} else {
			// other selection types, text or ..
			setEnabled(false);
		}
		

	}
	
	public AddContactsEntryAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Add Contact...");
		setToolTipText("Add a new contact");
		// TODO: add imageDescriptor
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.ADD_CONTACT));
		window.getSelectionService().addSelectionListener(this);
	}
	
	public void run() {
		AddContactDialog dialog = new AddContactDialog(window.getShell());
		int code = dialog.open();
		if (code == Window.OK) {
			Object item = selection.getFirstElement();
			ContactsGroup group = (ContactsGroup) item;
			ContactsEntry entry = new ContactsEntry(group, dialog.getUserId(), dialog.getNickname(), dialog.getServer());
			group.addEntry(entry);
		}
	}

}
