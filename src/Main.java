import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

class Main extends JFrame {

	private LinkedList<Email> studentLinkedList = new LinkedList<Email>();
	private static final long serialVersionUID = 1L;
	JTextArea studentTextArea = new JTextArea();
	JLabel idLabel = new JLabel("ID: ");
	JTextField idTextField = new JTextField(10);
	JLabel nameLabel = new JLabel("Name: ");
	JTextField nameTextField = new JTextField(10);
	JButton testDataButton = new JButton("Test Data");
	JButton addButton = new JButton("Add");
	JButton deleteButton = new JButton("Delete");
	JButton editButton = new JButton("Edit");
	JButton editSaveButton = new JButton("Save");
	JButton exitButton = new JButton("Exit");
	private int editIndex;

	public Main() {

		JPanel P = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel P2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel gridPanel = new JPanel(new GridLayout(2, 1));
		studentTextArea.setEditable(false);
		P.add(idLabel);
		P.add(idTextField);
		P.add(nameLabel);
		P.add(nameTextField);
		P2.add(testDataButton);
		P2.add(addButton);
		P2.add(editButton);
		P2.add(editSaveButton);
		P2.add(deleteButton);
		P2.add(exitButton);
		gridPanel.add(P);
		gridPanel.add(P2);
		editSaveButton.setEnabled(false);
		add(studentTextArea, BorderLayout.CENTER);
		add(gridPanel, BorderLayout.SOUTH);
		addButton.addActionListener(event -> addStud());
		editButton.addActionListener(event -> editStud());
		editSaveButton.addActionListener(event -> editSaveStudent());
		exitButton.addActionListener(event -> exitApplication());
		deleteButton.addActionListener(event -> deleteStudent());
		testDataButton.addActionListener(event -> addTestData());
		setTitle("Student Linked List");
	}

	// Check a Student
	private boolean isStudentIdInLinkedList(String idStr) {

		boolean inList = false;
		for (Email stud : studentLinkedList) {
			if (stud.getId().compareToIgnoreCase(idStr) == 0) {
				inList = true;
			}
		}
		return inList;
	}

	// Add new Student
	private void addStud() {

		if (isStudentIdInLinkedList(idTextField.getText()) == true) {
			JOptionPane.showMessageDialog(Main.this, "Checked: student ID is already in the database.");
		} else {
			try {
				Email stud = new Email(nameTextField.getText(), idTextField.getText());
				studentLinkedList.add(stud);
				displayAll();
				nameTextField.setText("");
				idTextField.setText("");
			} catch (EmailException error) {
				JOptionPane.showMessageDialog(Main.this, error.toString());
			}
		}
	}

	// Edit a Student
	private void editStud() {

		if (studentLinkedList.size() == 0) {
			JOptionPane.showMessageDialog(Main.this, "Error: Database is empty.");
		} else if (isStudentIdInLinkedList(idTextField.getText()) == false) {
			JOptionPane.showMessageDialog(Main.this, "Error: student ID is not in the database.");
		} else {
			editIndex = -1;
			for (int s = 0; s < studentLinkedList.size(); s++) {
				String currId = studentLinkedList.get(s).getId();
				if (currId.compareToIgnoreCase(idTextField.getText()) == 0) {
					editIndex = s;
					s = studentLinkedList.size(); // Exit Loop
				}
			}

			if (editIndex >= 0) {
				editSaveButton.setEnabled(true);
				editButton.setEnabled(false);
				testDataButton.setEnabled(false);
				addButton.setEnabled(false);
				deleteButton.setEnabled(false);
				exitButton.setEnabled(false);
				nameTextField.setText(studentLinkedList.get(editIndex).getName());
			}
		}
	}

	// Delete a Student
	private void deleteStudent() {

		if (studentLinkedList.size() == 0) {
			JOptionPane.showMessageDialog(Main.this, "Error: Database is empty.");
		} else if (isStudentIdInLinkedList(idTextField.getText()) == false) {
			JOptionPane.showMessageDialog(Main.this, "Error: student ID is not in the database.");
		} else {
			for (int s = 0; s < studentLinkedList.size(); s++) {
				String currId = studentLinkedList.get(s).getId();

				if (currId.compareToIgnoreCase(idTextField.getText()) == 0) {
					studentLinkedList.remove(s);
				}
			}
			displayAll();
			nameTextField.setText("");
			idTextField.setText("");
		}
	}

	// Put DATA into database
	private void addTestData() {

		nameTextField.setText("Sergey");
		idTextField.setText("1");
		addStud();
		nameTextField.setText("Alex");
		idTextField.setText("2");
		addStud();
		nameTextField.setText("Arthur");
		idTextField.setText("3");
		addStud();
		nameTextField.setText("Jane");
		idTextField.setText("4");
		addStud();
		nameTextField.setText("Viktoria");
		idTextField.setText("5");
		addStud();
	}

	// Save Student
	private void editSaveStudent() {

		studentLinkedList.get(editIndex).setName(nameTextField.getText());
		studentLinkedList.get(editIndex).setId(idTextField.getText());
		displayAll();
		nameTextField.setText("");
		idTextField.setText("");
		editSaveButton.setEnabled(false);
		editButton.setEnabled(true);
		testDataButton.setEnabled(true);
		addButton.setEnabled(true);
		deleteButton.setEnabled(true);
		exitButton.setEnabled(true);
	}

	// Show all string from database
	private void displayAll() {

		studentTextArea.setText("");
		for (Email stud : studentLinkedList) {
			studentTextArea.append(stud + "\n");
		}
	}

	// Close the application
	private void exitApplication() {
		System.exit(0);
	}

	// Run the application
	public static void main(String[] args) {

		Main app = new Main();
		app.setVisible(true);
		app.setSize(700, 210);
		app.setLocation(300, 100);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
