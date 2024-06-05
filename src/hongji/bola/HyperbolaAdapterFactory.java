package hongji.bola;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import hongji.bola.model.ContactsGroup;
import hongji.bola.model.Presence;
import hongji.bola.model.ContactsEntry;
import hongji.bola.model.Contact;

public class HyperbolaAdapterFactory implements IAdapterFactory {
	private IWorkbenchAdapter groupAdapter = new IWorkbenchAdapter() {
		@Override
		public Object getParent(Object o) {
			return ((ContactsGroup) o).getParent();
		}

		@Override
		public String getLabel(Object o) {
			// TODO Auto-generated method stub
			ContactsGroup group = (ContactsGroup) o;
			int available = 0;
			Contact[] entries = group.getEntries();
			for (Contact entry : entries) {
				if (entry instanceof ContactsEntry) {
					if (((ContactsEntry) entry).getPresence() != Presence.INVISIBLE)
						available++;
				}
			}
			return group.getName() + " (" + available + "/" + entries.length + ")";
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			// TODO to be filled
			return AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.GROUP);
		}

		@Override
		public Object[] getChildren(Object o) {
			return ((ContactsGroup) o).getEntries();
		}
	};

	private IWorkbenchAdapter entryAdapter = new IWorkbenchAdapter() {
		@Override
		public Object getParent(Object o) {
			return ((ContactsEntry) o).getParent();
		}

		@Override
		public String getLabel(Object o) {
			ContactsEntry entry = (ContactsEntry) o;
			return entry.getName() + " (" + entry.getNickname() + "@" + entry.getServer() + ")";
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			// TODO to be filled
			ContactsEntry entry = (ContactsEntry) object;
			String key = presenceToKey(entry.getPresence());
			return AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, key);
		}

		@Override
		public Object[] getChildren(Object o) {
			return new Object[0];
		}
	};

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		// TODO Auto-generated method stub
		if (adapterType == IWorkbenchAdapter.class) {
			if (adaptableObject instanceof ContactsGroup)
				return adapterType.cast(groupAdapter);
			if (adaptableObject instanceof ContactsEntry)
				return adapterType.cast(entryAdapter);
		}
		return null;
	}

	public Class<?>[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class };
	}

	private String presenceToKey(Presence presence) {
		if (presence == Presence.ONLINE)
			return IImageKeys.ONLINE;
		if (presence == Presence.AWAY) 
			return IImageKeys.AWAY;
		if (presence == Presence.DO_NOT_DISTURB)
			return IImageKeys.DO_NOT_DISTURB;
		if (presence == Presence.INVISIBLE)
			return IImageKeys.OFFLINE;
		return IImageKeys.OFFLINE;
	}

}
