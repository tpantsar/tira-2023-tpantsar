package oy.interact.tira;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import oy.interact.tira.model.PhoneBookBase;
import oy.interact.tira.student.CodeWordsCounter;
import oy.interact.tira.student.graph.Graph;
import oy.interact.tira.student.ParenthesesException;
import oy.interact.tira.tools.ProgrammingLanguages;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.QueueInterface;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;
import oy.interact.tira.factories.BSTFactory;
import oy.interact.tira.model.Coder;
import oy.interact.tira.model.PhoneBookArray;
import oy.interact.tira.model.PhoneBookBST;
import oy.interact.tira.view.CoderCallQueueFrame;
import oy.interact.tira.view.CoderDetailPanel;
import oy.interact.tira.view.CoderGraphFrame;
import oy.interact.tira.view.ContentPanel;
import oy.interact.tira.view.DataControlPanel;
import oy.interact.tira.view.HeaderPanel;
import oy.interact.tira.view.LogFrame;
import oy.interact.tira.view.TopWordsFrame;

/**
 * TIRA Coders App!
 *
 */
public class TIRACodersApp implements ActionListener {
    public static final Color CODER_APP_COLOR = new Color(90, 95, 200);

    private JFrame mainFrame;
    private JFrame logFrame;
    private CoderCallQueueFrame callQueueFrame;
    private CoderGraphFrame graphFrame;

    private static PhoneBookBase model;
    private File currentPath = null;

    public static void main(String[] args) {
        TIRACodersApp app = new TIRACodersApp();
        app.launch();
    }

    public static PhoneBookBase getModel() {
        return model;
    }

    private void launch() {
        model = new PhoneBookArray();
        mainFrame = new JFrame("TIRA Coders");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container rootPane = mainFrame.getContentPane();
        rootPane.add(new HeaderPanel(), BorderLayout.PAGE_START);
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());
        middlePanel.add(new DataControlPanel(), BorderLayout.PAGE_START);
        CoderDetailPanel coderDetails = new CoderDetailPanel();
        ContentPanel content = new ContentPanel(coderDetails);
        middlePanel.add(content, BorderLayout.CENTER);
        middlePanel.add(coderDetails, BorderLayout.EAST);
        rootPane.add(middlePanel, BorderLayout.CENTER);

        logFrame = new LogFrame();

        JMenuBar mainMenu = new JMenuBar();
        JMenu phonebookMenu = new JMenu("TIRA Coders");
        JMenuItem commandMenu = new JMenuItem("Import JSON phonebook");
        commandMenu.setActionCommand("import-json-file");
        commandMenu.addActionListener(this);
        phonebookMenu.add(commandMenu);
        commandMenu = new JMenuItem("Clear Phonebook");
        commandMenu.setActionCommand("clear-phonebook");
        commandMenu.addActionListener(this);
        phonebookMenu.add(commandMenu);
        phonebookMenu.addSeparator();
        ButtonGroup typeButtons = new ButtonGroup();
        JRadioButtonMenuItem subTypeMenuItem = new JRadioButtonMenuItem("Simple Array phonebook", true);
        subTypeMenuItem.setActionCommand("simple-array-phonebook");
        subTypeMenuItem.addActionListener(this);
        phonebookMenu.add(subTypeMenuItem);
        typeButtons.add(subTypeMenuItem);
        subTypeMenuItem = new JRadioButtonMenuItem("BST phonebook", true);
        subTypeMenuItem.setActionCommand("bst-phonebook");
        subTypeMenuItem.addActionListener(this);
        phonebookMenu.add(subTypeMenuItem);
        typeButtons.add(subTypeMenuItem);
        phonebookMenu.addSeparator();
        commandMenu = new JMenuItem("Check JSON file");
        commandMenu.setActionCommand("check-json-file");
        commandMenu.addActionListener(this);
        phonebookMenu.add(commandMenu);
        commandMenu = new JMenuItem("Create Call Queue");
        commandMenu.setActionCommand("create-call-list");
        commandMenu.addActionListener(this);
        phonebookMenu.add(commandMenu);
        commandMenu = new JMenuItem("Count Code Words (hashtable)");
        commandMenu.setActionCommand("count-code-words");
        commandMenu.addActionListener(this);
        phonebookMenu.add(commandMenu);
        commandMenu = new JMenuItem("Graph Coders");
        commandMenu.setActionCommand("graph-coders");
        commandMenu.addActionListener(this);
        phonebookMenu.add(commandMenu);
        phonebookMenu.addSeparator();
        commandMenu = new JMenuItem("View Log");
        commandMenu.setActionCommand("view-log");
        commandMenu.addActionListener(this);
        phonebookMenu.add(commandMenu);
        commandMenu = new JMenuItem("Analyze the BST");
        commandMenu.setActionCommand("bst-analysis");
        commandMenu.addActionListener(this);
        commandMenu.setEnabled(false);
        phonebookMenu.add(commandMenu);
        commandMenu = new JMenuItem("Export BST to GraphViz .dot file");
        commandMenu.setActionCommand("export-dot");
        commandMenu.addActionListener(this);
        commandMenu.setEnabled(false);
        phonebookMenu.add(commandMenu);

        phonebookMenu.addSeparator();
        commandMenu = new JMenuItem("Exit");
        commandMenu.setActionCommand("exit-app");
        commandMenu.addActionListener(this);
        phonebookMenu.add(commandMenu);

        mainMenu.add(phonebookMenu);
        mainFrame.setJMenuBar(mainMenu);

        mainFrame.setPreferredSize(new Dimension(1200, 800));
        mainFrame.pack();
        mainFrame.setVisible(true);

        logFrame.setVisible(true);
    }

    private void enableBSTMenus(boolean enabled) {
        JMenu mainMenu = mainFrame.getJMenuBar().getMenu(0);
        int menuCount = mainMenu.getMenuComponentCount();
        Component component = mainMenu.getMenuComponent(menuCount - 3);
        component.setEnabled(enabled);
        component = mainMenu.getMenuComponent(menuCount - 4);
        component.setEnabled(enabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("import-json-file")) {
                importPhoneBook();
            } else if (e.getActionCommand().equals("simple-array-phonebook")) {
                if (null != graphFrame) {
                    graphFrame.dispose();
                    graphFrame = null;
                }
                enableBSTMenus(false);
                PhoneBookBase newModel = new PhoneBookArray();
                model.moveObservers(newModel);
                model = newModel;
                model.notifyObservers(PhoneBookBase.Notification.MODEL_CHANGED);
            } else if (e.getActionCommand().equals("bst-phonebook")) {
                if (null != graphFrame) {
                    graphFrame.dispose();
                    graphFrame = null;
                }
                enableBSTMenus(true);
                PhoneBookBase newModel = new PhoneBookBST();
                model.moveObservers(newModel);
                model = newModel;
                model.notifyObservers(PhoneBookBase.Notification.MODEL_CHANGED);
            } else if (e.getActionCommand().equals("check-json-file")) {
                checkJSONPhoneBook();
            } else if (e.getActionCommand().equals("create-call-list")) {
                try {
                    showSelectLanguageForCallList();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(mainFrame, "Error: " + e1.getMessage(),
                            "Could not open file",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getActionCommand().equals("count-code-words")) {
                countWords();
            } else if (e.getActionCommand().equals("graph-coders")) {
                try {
                    Graph<Coder> graph = model.createGraph();
                    Coder[] coders = model.toArray();
                    if (null != graph && coders.length > 0) {
                        if (null != graphFrame) {
                            graphFrame.dispose();
                        }
                        graphFrame = new CoderGraphFrame(graph, coders);
                    }
                } catch (NotYetImplementedException e1) {
                    JOptionPane.showMessageDialog(mainFrame, " " + e1.getMessage(),
                            "Not Yet Implemented",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(mainFrame, "Error: " + e2.getMessage(),
                            "Could not open file",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getActionCommand().equals("clear-phonebook")) {
                model.clearPhonebook();
            } else if (e.getActionCommand().equals("bst-analysis")) {
                bstAnalysis();
            } else if (e.getActionCommand().equals("export-dot")) {
                exportDot();
            } else if (e.getActionCommand().equals("view-log")) {
                logFrame.setVisible(!logFrame.isVisible());
            } else if (e.getActionCommand().equals("exit-app")) {
                logFrame.dispose();
                mainFrame.dispose();
                if (null != graphFrame) {
                    graphFrame.dispose();
                }
                if (null != callQueueFrame && callQueueFrame.isVisible()) {
                    callQueueFrame.dispose();
                }
            }
        } catch (NotYetImplementedException noImpl) {
            JOptionPane.showMessageDialog(mainFrame, "Not yet implemented: " + noImpl.getMessage(),
                    "Do not try this yet",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void bstAnalysis() {
        PhoneBookBase model = getModel();
        if (model instanceof PhoneBookBST) {
            try {
                PhoneBookBST bstModel = (PhoneBookBST) model;
                TIRAKeyedOrderedContainer<String, Coder> bst = bstModel.getContainer();
                if (null != bst) {
                    // TODO: student: implement a BSTAnalyzerVisitor (optional).
                    long start = System.currentTimeMillis();
                    Visitor<String, Coder> visitor = BSTFactory.createBSTAnalyzerVisitor();
                    if (null != visitor) {
                        bst.accept(visitor);
                        long duration = System.currentTimeMillis() - start;
                        bstModel.addMeasurement("Analyzing BST", duration);
                        System.out.println(visitor);
                        bstModel.addMeasurement(visitor.toString(), 0);
                        JOptionPane.showMessageDialog(mainFrame, visitor.toString(),
                                "BST Analysis done",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainFrame, "Something wrong with analysing the BST: " + e.getMessage(),
                        "Could not do the analysis",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportDot() {
        PhoneBookBase model = getModel();
        if (model instanceof PhoneBookBST) {
            try {
                PhoneBookBST bstModel = (PhoneBookBST) model;
                TIRAKeyedOrderedContainer<String, Coder> bst = bstModel.getContainer();
                if (null != bst) {
                    // TODO: student: implement a BSTToDotFileTreeVisitor (optional).
                    long start = System.currentTimeMillis();
                    Visitor<String, Coder> visitor = BSTFactory
                            .createBSTToDotFileTreeVisitor(getModel().getCurrentFileName(), "tree.dot.txt");
                    if (null != visitor) {
                        bst.accept(visitor);
                        visitor.close();
                        long duration = System.currentTimeMillis() - start;
                        bstModel.addMeasurement("Creating a GraphViz dot file from BST", duration);
                        JOptionPane.showMessageDialog(mainFrame,
                                "GraphViz dot file is in tree.dot.txt in TIRACoders app directory.\nUse GraphViz to convert it to a graph image.",
                                "DOT File generated",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(mainFrame, "Something wrong with the file: " + e.getMessage(),
                        "Could not open file",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Something wrong with generating dot file from BST: " + e.getMessage(),
                        "Could not produce .dot file",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void countWords() {
        JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (currentPath == null) {
            directoryChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        } else {
            directoryChooser.setCurrentDirectory(currentPath);
        }
        int result = directoryChooser.showOpenDialog(mainFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = directoryChooser.getSelectedFile();
            currentPath = new File(selectedFile.getParent());
            try {
                CodeWordsCounter wordCounter = new CodeWordsCounter();
                wordCounter.countWordsinSourceCodeFiles(selectedFile);
                System.out.format("[measurement] Counted the words from code in %d ms (not including file handling)%n",
                        wordCounter.cumulativeTimeInMilliseconds);
                Pair<String, Integer>[] topWords = wordCounter.topCodeWords(100);
                if (topWords != null) {
                    new TopWordsFrame(selectedFile, topWords);
                    for (Pair<String,Integer> item : topWords) {
                        System.out.format("%10s\t%6d%n", item.getKey(), item.getValue());
                    }
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(mainFrame, "Error: " + e1.getMessage(),
                        "Could not open file",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void checkJSONPhoneBook() {
        JFileChooser jsonFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PhoneBook JSON files", "json");
        jsonFileChooser.setFileFilter(filter);
        if (currentPath == null) {
            jsonFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        } else {
            jsonFileChooser.setCurrentDirectory(currentPath);
        }
        int result = jsonFileChooser.showOpenDialog(mainFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jsonFileChooser.getSelectedFile();
            currentPath = new File(selectedFile.getParent());
            try {
                model.checkJSONFile(selectedFile);
                JOptionPane.showMessageDialog(mainFrame, "JSON file " + selectedFile.getName() + " is valid",
                        "JSON File OK",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(mainFrame, "Something wrong with the file: " + e1.getMessage(),
                        "Could not open file",
                        JOptionPane.ERROR_MESSAGE);
            } catch (ParenthesesException e2) {
                JOptionPane.showMessageDialog(mainFrame, "Something wrong with the file: " + e2.getMessage(),
                        "Could not open file",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void importPhoneBook() {
        JFileChooser jsonFileChooser = new JFileChooser();
        if (currentPath == null) {
            jsonFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        } else {
            jsonFileChooser.setCurrentDirectory(currentPath);
        }
        int result = jsonFileChooser.showOpenDialog(mainFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jsonFileChooser.getSelectedFile();
            currentPath = new File(selectedFile.getParent());
            try {
                if (null != graphFrame) {
                    graphFrame.dispose();
                    graphFrame = null;
                }
                TIRACodersApp.getModel().loadPhoneBook(selectedFile);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(mainFrame, "Something wrong with the file: " + e1.getMessage(),
                        "Could not open file",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    private void showSelectLanguageForCallList() throws Exception {
        String selectedLanguage = (String) JOptionPane.showInputDialog(
                mainFrame,
                "Select the language skills you want to create a call queue for.",
                "Language selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ProgrammingLanguages.languages,
                "");
        if (selectedLanguage != null && selectedLanguage.length() > 0) {
            QueueInterface<Coder> coderQueue = getModel().getCoders(selectedLanguage);
            callQueueFrame = new CoderCallQueueFrame(coderQueue, selectedLanguage);
        }
    }
}
