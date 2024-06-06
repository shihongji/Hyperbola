package hongji.bola;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import hongji.bola.views.ContactsView;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addView(ContactsView.ID, IPageLayout.LEFT, 0.4f, layout.getEditorArea());
//		layout.addStandaloneView(ContactsView.ID, true, IPageLayout.LEFT, 0.4f, layout.getEditorArea());
	}
}
