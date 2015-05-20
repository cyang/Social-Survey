import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Created by Christopher Yang


public class Form extends JFrame {
    public Form(){
        super("Social Survey");
        initComponents();
    }

    private void initComponents(){
        final JPanel contentPane = new JPanel();
        GridBagLayout layout  = new GridBagLayout();

        contentPane.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(1,2,1,2);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        JLabel firstNameL = new JLabel("Enter first name:");
        layout.setConstraints(firstNameL, constraints);
        contentPane.add(firstNameL);

        constraints.gridx = 6;
        constraints.gridy = 1;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        final JTextField firstNameT = new JTextField(10);
        layout.setConstraints(firstNameT, constraints);
        contentPane.add(firstNameT);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        final JLabel lastNameL = new JLabel("Enter last name:");
        layout.setConstraints(lastNameL, constraints);
        contentPane.add(lastNameL);

        constraints.gridx = 6;
        constraints.gridy = 2;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        final JTextField lastNameT = new JTextField();
        layout.setConstraints(lastNameT, constraints);
        contentPane.add(lastNameT);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        JLabel ageL = new JLabel("Enter age:");
        layout.setConstraints(ageL, constraints);
        contentPane.add(ageL);

        constraints.gridx = 6;
        constraints.gridy = 3;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        final JTextField ageT = new JTextField();
        layout.setConstraints(ageT, constraints);
        contentPane.add(ageT);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        JLabel jobL = new JLabel("Enter job:");
        layout.setConstraints(jobL, constraints);
        contentPane.add(jobL);

        constraints.gridx = 6;
        constraints.gridy = 4;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        final JTextField jobT = new JTextField();
        layout.setConstraints(jobT, constraints);
        contentPane.add(jobT);

        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        JLabel incomeL = new JLabel("Enter income:    $");
        layout.setConstraints(incomeL, constraints);
        contentPane.add(incomeL);

        constraints.gridx = 6;
        constraints.gridy = 5;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        final JTextField incomeT = new JTextField();
        layout.setConstraints(incomeT, constraints);
        contentPane.add(incomeT);

        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 2;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;


        constraints.insets = new Insets(30,0,0,0);

        JButton insertB = new JButton("Insert");
        insertB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (firstNameT.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Please enter a first name");
                    else if (lastNameT.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Please enter a last name");
                    else if (jobT.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Please enter a job");
                    else {
                        BOParticipant participant = new BOParticipant(firstNameT.getText(),
                                lastNameT.getText(), Integer.parseInt(ageT.getText()), jobT.getText(),
                                Double.parseDouble(incomeT.getText()));

                        participant.write();

                        JOptionPane.showMessageDialog(null, "Inserted data");
                        firstNameT.setText("");
                        lastNameT.setText("");
                        ageT.setText("");
                        jobT.setText("");
                        incomeT.setText("");
                    }} catch (NumberFormatException error){
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for age/income");
                }

            }
        });

        layout.setConstraints(insertB, constraints);
        contentPane.add(insertB);


        final JButton resultsB = new JButton("Results for");
        constraints.gridx = 9;
        constraints.gridy = 8;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        final JTextField resultsT = new JTextField();
        layout.setConstraints(resultsT, constraints);
        contentPane.add(resultsT);

        constraints.gridx = 2;
        constraints.gridy = 8;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        resultsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                remove(contentPane);
                //TDOD
                try {
                    if (resultsT.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Please input a job to show the results");
                    else {
                        FileInputStream in = new FileInputStream("database.csv");
                        Scanner scanner = new Scanner(in);

                        double jobCounts = 0;
                        double peopleCount = 0;
                        double totalAge = 0;
                        double totalIncome = 0;

                        while (scanner.hasNextLine()) {
                            String s = scanner.nextLine();
                            String[] array1 = s.split(", ");

                            peopleCount++;

                            for (int i = 0; i < array1.length; i++) {
                                if (array1[i].equals(resultsT.getText())) {
                                    jobCounts++;
                                    totalAge += Double.parseDouble(array1[i - 1]);
                                    totalIncome += Double.parseDouble(array1[i + 1]);
                                }

                            }
                        }

                        JOptionPane.showMessageDialog(null, "There are " + (int) jobCounts + " " + resultsT.getText() + "(s)");
                        JOptionPane.showMessageDialog(null, jobCounts / peopleCount * 100
                                + "% of the people who participated in the survey are " + resultsT.getText() + "(s)");
                        JOptionPane.showMessageDialog(null, "The average age is " + totalAge / jobCounts
                                + " and the average income is $" + totalIncome / jobCounts);
                    }

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }


            }
        });
        layout.setConstraints(resultsB, constraints);
        contentPane.add(resultsB);

        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(contentPane);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();

    }

    public static void main(String[] args) {
        (new Form()).setVisible(true);
    }
}
