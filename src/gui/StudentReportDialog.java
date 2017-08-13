package gui;

import dao.StudentDAO;
import domain.Student;
import gui.helpers.SimpleListModel;
import java.awt.Window;
import java.util.Collection;
import javax.swing.JOptionPane;

public class StudentReportDialog extends javax.swing.JDialog {

	private final StudentDAO dao;

	private final SimpleListModel studentsModel;

	@SuppressWarnings("unchecked")
	public StudentReportDialog(Window parent, boolean modal, StudentDAO dao) {
		super(parent);
		setModal(modal);

		this.dao = dao;

		initComponents();

		// set the component names
		txtID.setName("txtID");
		lstStudents.setName("lstStudents");
		btnSearch.setName("btnSearch");
		btnDelete.setName("btnDelete");
		btnEdit.setName("btnEdit");
		cmbMajors.setName("cmbMajors");

		this.studentsModel = new SimpleListModel(dao.getAll());

		this.lstStudents.setModel(studentsModel);
		this.cmbMajors.setModel(new SimpleListModel(dao.getMajors()));
	}

	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      scrollPane = new javax.swing.JScrollPane();
      lstStudents = new javax.swing.JList<>();
      btnClose = new javax.swing.JButton();
      btnDelete = new javax.swing.JButton();
      btnEdit = new javax.swing.JButton();
      lblID = new javax.swing.JLabel();
      txtID = new javax.swing.JTextField();
      btnSearch = new javax.swing.JButton();
      lblMajor = new javax.swing.JLabel();
      cmbMajors = new javax.swing.JComboBox<>();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      lstStudents.setName("lstStudents"); // NOI18N
      scrollPane.setViewportView(lstStudents);

      btnClose.setText("Close");
      btnClose.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCloseActionPerformed(evt);
         }
      });

      btnDelete.setText("Delete");
      btnDelete.setName("btnDelete"); // NOI18N
      btnDelete.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteActionPerformed(evt);
         }
      });

      btnEdit.setText("Edit");
      btnEdit.setName("btnEdit"); // NOI18N
      btnEdit.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnEditActionPerformed(evt);
         }
      });

      lblID.setText("Student ID:");

      txtID.setName("txtID"); // NOI18N

      btnSearch.setText("Search for ID");
      btnSearch.setName("btnSearch"); // NOI18N
      btnSearch.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSearchActionPerformed(evt);
         }
      });

      lblMajor.setText("Major:");

      cmbMajors.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
      cmbMajors.setName("cmbMajor"); // NOI18N
      cmbMajors.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbMajorsActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(scrollPane)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addGap(18, 18, 18)
                  .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addGap(18, 18, 18)
                  .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(lblID)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(txtID)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnSearch))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(lblMajor)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(cmbMajors, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblID)
               .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(btnSearch))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblMajor)
               .addComponent(cmbMajors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnClose)
               .addComponent(btnDelete)
               .addComponent(btnEdit))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
		this.dispose();
   }//GEN-LAST:event_btnCloseActionPerformed

   private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

		// do nothing if no selection has been made
		if (lstStudents.isSelectionEmpty()) {
			return;
		}

		// get selected student from list
		Student selected = lstStudents.getSelectedValue();

		// show a confirmation dialog
		int result = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to delete this student?",
				"Confirm Deletion", JOptionPane.YES_NO_OPTION);

		// did the user click the yes button?
		if (result == JOptionPane.YES_OPTION) {
			// user clicked yes so delete the student
			dao.delete(selected);
			studentsModel.updateItems(dao.getAll());

			// clear the list selection since it remembers the last selected index
			lstStudents.clearSelection();
		}

   }//GEN-LAST:event_btnDeleteActionPerformed

   private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
		// do nothing if no selection has been made
		if (lstStudents.isSelectionEmpty()) {
			return;
		}

		// get selected student from list
		Student selected = lstStudents.getSelectedValue();

		StudentDialog dialog = new StudentDialog(this, true, selected, dao);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		// update list to display new student details
		studentsModel.updateItems(dao.getAll());
   }//GEN-LAST:event_btnEditActionPerformed

   private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
		String idStr = txtID.getText();

		// do nothing if no ID is entered
		if(idStr.isEmpty()) {
			return;
		}

		Integer id = Integer.parseInt(idStr);

		Student student = dao.getByID(id);

		studentsModel.updateItems(student);
   }//GEN-LAST:event_btnSearchActionPerformed

   private void cmbMajorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMajorsActionPerformed
		String major = (String)cmbMajors.getSelectedItem();
		Collection<Student> byMajor = dao.getByMajor(major);
		studentsModel.updateItems(byMajor);
   }//GEN-LAST:event_cmbMajorsActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnClose;
   private javax.swing.JButton btnDelete;
   private javax.swing.JButton btnEdit;
   private javax.swing.JButton btnSearch;
   private javax.swing.JComboBox<String> cmbMajors;
   private javax.swing.JLabel lblID;
   private javax.swing.JLabel lblMajor;
   private javax.swing.JList<Student> lstStudents;
   private javax.swing.JScrollPane scrollPane;
   private javax.swing.JTextField txtID;
   // End of variables declaration//GEN-END:variables
}
