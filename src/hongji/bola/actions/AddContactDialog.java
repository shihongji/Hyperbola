package hongji.bola.actions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Add roster entry dialog, which prompts for the entry details.
 */
public class AddContactDialog extends Dialog {

	private Text userIdText;

	private Text serverText;

	private Text nicknameText;

	private String userId;

	private String server;

	private String nickname;

	public AddContactDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Add Contact"); //$NON-NLS-1$
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);

		Label userIdLabel = new Label(composite, SWT.NONE);
		userIdLabel.setText("&User id:"); //$NON-NLS-1$
		userIdLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		userIdText = new Text(composite, SWT.BORDER);
		userIdText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, false));

		Label serverLabel = new Label(composite, SWT.NONE);
		serverLabel.setText("&Server:"); //$NON-NLS-1$
		serverLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		serverText = new Text(composite, SWT.BORDER);
		serverText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, false));

		Label nicknameLabel = new Label(composite, SWT.NONE);
		nicknameLabel.setText("&Nickname:"); //$NON-NLS-1$
		nicknameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		nicknameText = new Text(composite, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, true,
				false);
		gridData.widthHint = convertHeightInCharsToPixels(20);
		nicknameText.setLayoutData(gridData);

		return composite;
	}

	@Override
	protected void okPressed() {
		nickname = nicknameText.getText();
		server = serverText.getText();
		userId = userIdText.getText();

		if (nickname.equals("")) { //$NON-NLS-1$
			MessageDialog.openError(getShell(), "Invalid Nickname", //$NON-NLS-1$
					"Nickname field must not be blank."); //$NON-NLS-1$
			return;
		}
		if (server.equals("")) { //$NON-NLS-1$
			MessageDialog.openError(getShell(), "Invalid Server", //$NON-NLS-1$
					"Server field must not be blank."); //$NON-NLS-1$
			return;
		}
		if (userId.equals("")) { //$NON-NLS-1$
			MessageDialog.openError(getShell(), "Invalid User id", //$NON-NLS-1$
					"User id field must not be blank."); //$NON-NLS-1$
			return;
		}

		super.okPressed();
	}

	public String getUserId() {
		return userId;
	}

	public String getServer() {
		return server;
	}

	public String getNickname() {
		return nickname;
	}
}
