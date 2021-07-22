package gui;

import app.AppCore;
import errorHandler.Handler;
import gui.table.TableModel;
import lombok.Data;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import queryBuilder.Query;
import resource.data.Row;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Data
public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance = null;

    private AppCore appCore;
    private Handler ErrorHandler;
    private JTable jTable;
    private JScrollPane jsp;
    private JPanel bottomStatus;
    private JButton button;
    private JTextArea textArea;
    private TableModel tableModel;

    private MainFrame() {

    }

    public static MainFrame getInstance(){
        if (instance==null){
            instance=new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    private void initialise() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout( new BorderLayout() );

        textArea = new JTextArea();
        button = new JButton("Generisi.");

        tableModel = new TableModel();

        button.setMaximumSize(new Dimension(500, 100) );
        button.setMinimumSize(new Dimension(500, 100) );
        textArea.setMaximumSize(new Dimension(500, 500) );
        textArea.setMinimumSize(new Dimension(500, 100) );
        button.setPreferredSize(new Dimension(500, 100) );
        textArea.setPreferredSize(new Dimension(500, 400) );

        this.add(textArea,BorderLayout.NORTH);
        this.add(button,BorderLayout.CENTER);

        jTable = new JTable(tableModel);
        jTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        jTable.setFillsViewportHeight(true);

        this.add(new JScrollPane(jTable),BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        button.addActionListener(l ->{
            Query x = new Query(textArea.getText());
            if( x.validateQuery() ){
                System.out.println(x.compileQuery());
                System.out.println(x.compileQuery());
                this.appCore.readDataFromQuery("" , x.compileQuery() );
            }
        });

    }

    public void setErrorHandler(Handler errorHandler){
        this.ErrorHandler = errorHandler;
        this.ErrorHandler.addSubscriber(this);
    }


    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
    }


    @Override
    public void update(Notification notification) {
        if (notification.getCode() == NotificationCode.DATA_UPDATED){
            tableModel.setRows((ArrayList<Row>) notification.getData());
        }

        switch (notification.getCode()){
            case DATA_UPDATED:
                tableModel.setRows((ArrayList<Row>) notification.getData());
                break;
            case ERROR_STACK:
                String err = "";
                for(String error : (ArrayList<String>) notification.getData()){
                    err += error;
                }
                JOptionPane.showMessageDialog(this,
                        err,
                        "Syntax Error!",
                        JOptionPane.ERROR_MESSAGE
                );
                break;
            default:
                JOptionPane.showMessageDialog(this,
                        "Unknown Notification Code.",
                        "Fatal Error!",
                        JOptionPane.ERROR_MESSAGE
                );
                break;
        }
    }
}
