package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import view.tabdrawer.ElementDrawer;
import view.tabgraphs.MarkovGraphDrawer;
import view.tabgraphs.ReachabilityGraphDrawer;
import view.tabtables.DescriptiveTableDrawer;
import view.tabtables.EmulationTablesDrawer;
import view.tabtables.TransitionsTableDrawer;
import actions.listeners.AppWindowListener;
import actions.listeners.DrawerAdjustmentListener;
import actions.menuadd.AddingArcAction;
import actions.menuadd.AddingImmediateTransitionAction;
import actions.menuadd.AddingPlaceAction;
import actions.menuadd.AddingTimeTransitionAction;
import actions.menuemulation.EmulatingEventsAction;
import actions.menuemulation.EmulatingEventAction;
import actions.menuemulation.EmulatingTimesAction;
import actions.menuemulation.ResetingEmulationAction;
import actions.menugeneral.AboutingAction;
import actions.menugeneral.CreatingAction;
import actions.menugeneral.ExitingAction;
import actions.menugeneral.ExportingAction;
import actions.menugeneral.HelpingAction;
import actions.menugeneral.OpeningAction;
import actions.menugeneral.SavingAction;
import actions.menugeneral.ScalingPanelAction;
import actions.menuundo.RedoAction;
import actions.menuundo.UndoAction;
import data.Data;
import data.modeling.EmulationManager;

/**
 * Main frame of application, Creates and shows GUI.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AppFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -2731382124691105140L;

    private Data data;

    private EmulationManager emulator;

    private ElementDrawer elementDrawer;

    private ReachabilityGraphDrawer reachabiblityGraph;

    private MarkovGraphDrawer markGraph;

    /**
     * Start width of the frame.
     */
    private int fWidth = 800;

    /**
     * Start height of the frame.
     */
    private int fHeight = 500;

    /**
     * @param data
     */
    public AppFrame(final Data data, final EmulationManager emulator) {
        super();
        this.data = data;
        this.emulator = emulator;
    }

    /**
     * @return the fWidth
     */
    public final int getfWidth() {
        return fWidth;
    }

    /**
     * @param width
     *            the fWidth to set
     */
    public final void setFWidth(int width) {
        fWidth = width;
    }

    /**
     * @return the fHeight
     */
    public final int getFHeight() {
        return fHeight;
    }

    /**
     * @param height
     *            the fHeight to set
     */
    public final void setFHeight(int height) {
        fHeight = height;
    }

    /**
     * @return the elementDrawer
     */
    public final ElementDrawer getElementDrawer() {
        return elementDrawer;
    }

    /**
     * @param drawer
     *            the elementDrawer to set
     */
    public final void setElementDrawer(ElementDrawer drawer) {
        elementDrawer = drawer;
    }

    protected void initializeMenuBar() {

        JMenuBar bar;

        JMenu file = new JMenu("File");
        JMenu view = new JMenu("View");
        JMenu edit = new JMenu("Edit");
        JMenu emulation = new JMenu("Emulation");
        JMenu help = new JMenu("Help");

        int fontsize = 12;
        file.setFont(new Font("Lucida", 1, fontsize));
        edit.setFont(new Font("Lucida", 1, fontsize));
        help.setFont(new Font("Lucida", 1, fontsize));

        // -------------------------------------
        // ---------> File Menu <---------------
        // -------------------------------------

        JMenuItem newf = new JMenuItem();
        JMenuItem open = new JMenuItem();
        JMenuItem save = new JMenuItem();
        JMenuItem export = new JMenuItem();
        JMenuItem close = new JMenuItem();
        JMenuItem exit = new JMenuItem();

        newf.setAction(new CreatingAction(data, emulator, this));
        newf.setText("New");
        newf.setMnemonic(KeyEvent.VK_N);
        newf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));

        open.setAction(new OpeningAction(data, emulator, this));
        open.setText("Open...");
        open.setMnemonic(KeyEvent.VK_O);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));

        save.setAction(new SavingAction(data, emulator, this));
        save.setText("Save as...");
        save.setMnemonic(KeyEvent.VK_S);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));

        export.setAction(new ExportingAction(data, this));
        export.setText("Export...");
        export.setMnemonic(KeyEvent.VK_E);
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.CTRL_MASK));

        close.setAction(new CreatingAction(data, emulator, this));
        close.setText("Close");
        close.setMnemonic(KeyEvent.VK_W);
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
                ActionEvent.CTRL_MASK));

        exit.setAction(new ExitingAction(data, this));
        exit.setText("Exit");
        exit.setMnemonic(KeyEvent.VK_Q);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                ActionEvent.CTRL_MASK));
        
        file.add(newf);
        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(export);
        file.addSeparator();
        file.add(close);
        file.addSeparator();
        file.add(exit);

        // -------------------------------------
        // ---------> View Menu <---------------
        // -------------------------------------

        JMenuItem scPlusR = new JMenuItem();
        JMenuItem scMinusR = new JMenuItem();
        JMenuItem scMinusM = new JMenuItem();
        JMenuItem scPlusM = new JMenuItem();
        
        scPlusM.setAction(new ScalingPanelAction(markGraph, true));
        scPlusM.setText("Scale Plus");
        scPlusM.setMnemonic(KeyEvent.VK_0);
        scPlusM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0));

        scMinusM.setAction(new ScalingPanelAction(markGraph, false));
        scMinusM.setText("Scale Minus");
        scMinusM.setMnemonic(KeyEvent.VK_MINUS);
        scMinusM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0));

        scPlusR.setAction(new ScalingPanelAction(reachabiblityGraph, true));
        scPlusR.setText("Scale Plus");
        scPlusR.setMnemonic(KeyEvent.VK_1);
        scPlusR.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0));

        scMinusR.setAction(new ScalingPanelAction(reachabiblityGraph, false));
        scMinusR.setText("Scale Minus");
        scMinusR.setMnemonic(KeyEvent.VK_2);
        scMinusR.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0));
        
        view.add(scPlusM);
        view.add(scMinusM);
        view.add(scPlusR);
        view.add(scMinusR);

        // -------------------------------------
        // ---------> Edit Menu <---------------
        // -------------------------------------

        JMenuItem undo = new JMenuItem();
        JMenuItem redo = new JMenuItem();
        JMenuItem addPlace = new JMenuItem();
        JMenuItem addTTransition = new JMenuItem();
        JMenuItem addITransition = new JMenuItem();
        JMenuItem addArc = new JMenuItem();

        undo.setAction(new UndoAction(data, this));
        undo.setText("Undo");
        undo.setMnemonic(KeyEvent.VK_Z);
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                ActionEvent.CTRL_MASK));

        redo.setAction(new RedoAction(data, this));
        redo.setText("Redo");
        redo.setMnemonic(KeyEvent.VK_Y);
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                ActionEvent.CTRL_MASK));

        addPlace.setAction(new AddingTimeTransitionAction(data));
        addPlace.setText("Add Place");
        addPlace.setMnemonic(KeyEvent.VK_P);
        addPlace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                ActionEvent.CTRL_MASK));

        addTTransition.setAction(new AddingTimeTransitionAction(data));
        addTTransition.setText("Add Time Transition");
        addTTransition.setMnemonic(KeyEvent.VK_T);
        addTTransition.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                ActionEvent.CTRL_MASK));

        addITransition.setAction(new AddingImmediateTransitionAction(data));
        addITransition.setText("Add Immediate Transition");
        addITransition.setMnemonic(KeyEvent.VK_I);
        addITransition.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                ActionEvent.CTRL_MASK));

        addArc.setAction(new AddingArcAction(data));
        addArc.setText("Add Arc");
        addArc.setMnemonic(KeyEvent.VK_A);
        addArc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                ActionEvent.CTRL_MASK));
        
        edit.add(undo);
        edit.add(redo);
        edit.addSeparator();
        edit.add(addPlace);
        edit.add(addTTransition);
        edit.add(addITransition);
        edit.add(addArc);

        // -------------------------------------
        // --------> Emulation Menu <-----------
        // -------------------------------------

        JMenuItem oneStep = new JMenuItem();
        JMenuItem oneKStep = new JMenuItem();
        JMenuItem reset = new JMenuItem();
        // TODO: add hot keys:
        oneStep.setAction(new EmulatingEventAction(emulator, data, this));
        oneStep.setText("One Step");
        oneStep.setMnemonic(KeyEvent.VK_1);
        oneStep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,
                ActionEvent.CTRL_MASK));

        oneKStep.setAction(new EmulatingEventsAction(emulator, data, this));
        oneKStep.setText("10K Steps");
        oneKStep.setMnemonic(KeyEvent.VK_K);
        oneKStep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,
                ActionEvent.CTRL_MASK));

        reset.setAction(new ResetingEmulationAction(emulator, data, this));
        reset.setText("Reset");
        reset.setMnemonic(KeyEvent.VK_R);
        reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                ActionEvent.CTRL_MASK));

        emulation.add(oneStep);
        emulation.add(oneKStep);
        emulation.addSeparator();
        emulation.add(reset);

        // -------------------------------------
        // ---------> Help Menu <---------------
        // -------------------------------------

        JMenuItem chelp = new JMenuItem();
        JMenuItem about = new JMenuItem();

        chelp.setAction(new HelpingAction(this));
        chelp.setText("Context Help...");
        chelp.setMnemonic(KeyEvent.VK_F1);
        chelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

        about.setAction(new AboutingAction(this));
        about.setText("About...");
        about.setMnemonic(KeyEvent.VK_B);
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
                ActionEvent.CTRL_MASK));
        
        help.add(chelp);
        help.add(about);

        bar = new JMenuBar();
        bar.add(file);
        bar.add(view);
        bar.add(edit);
        bar.add(emulation);
        bar.add(help);

        setJMenuBar(bar);
    }

    /**
     * Creates the button for tool bar with specified parameters.
     * 
     * @param imageName
     *            the name of image file
     * @param action
     *            the action, which produced when button is clicked.
     * @param toolTipText
     *            the text of tool tip of the button
     * @param altText
     *            the text which is displayed if image isn't found.
     * @return the button for tool bar
     */
    protected JButton makeToolBarButton(String imageName,
            final AbstractAction action, final String toolTipText,
            final String altText) {
        // Look for the image.
        String imgLocation = "res/" + imageName + ".png";
        URL imageURL = AppFrame.class.getResource(imgLocation);

        // Create and initialize the button.
        JButton button = new JButton();
        button.setAction(action);
        button.setToolTipText(toolTipText);

        if (imageURL != null) { // image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else { // no image found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }
        return button;
    }

    /**
     * Initializes and shows tool bar with all its buttons. Initializes the
     * event handler for every component.
     */
    protected void initializeToolBar() {
        JToolBar toolBar = new JToolBar("Tool bar");

        JButton button = makeToolBarButton("New24", new CreatingAction(data,
                emulator, this), "Create new file", "New");
        toolBar.add(button);

        button = makeToolBarButton("Open24", new OpeningAction(data, emulator,
                this), "Open", "Open");
        toolBar.add(button);

        button = makeToolBarButton("Save24", new SavingAction(data, emulator,
                this), "Save", "Save as");
        toolBar.add(button);

        toolBar.addSeparator();

        button = makeToolBarButton("Export24", new ExportingAction(data, this),
                "Export", "Export");
        toolBar.add(button);

        toolBar.addSeparator();

        button = makeToolBarButton("place24", new AddingPlaceAction(data),
                "Add New Place", "Place");
        toolBar.add(button);

        button = makeToolBarButton("im_transition24",
                new AddingImmediateTransitionAction(data),
                "Add New Immediate Transition", "Immediate Transition");
        toolBar.add(button);

        button = makeToolBarButton("time_transition24",
                new AddingTimeTransitionAction(data),
                "Add New Time Transition", "Time Transition");
        toolBar.add(button);

        button = makeToolBarButton("arc24", new AddingArcAction(data),
                "Add New Arc", "Arc");
        toolBar.add(button);

        toolBar.addSeparator();

        button = makeToolBarButton("emul124", new EmulatingEventAction(emulator,
                data, this), "Emulate 1 event", "Emulate (1)");
        toolBar.add(button);

        button = makeToolBarButton("emuln24", new EmulatingEventsAction(
                emulator, data, this), "Emulate N events", "Emulate (N)");
        toolBar.add(button);
        
        button = makeToolBarButton("emult24", new EmulatingTimesAction(
                emulator, data, this), "Emulate while time", "Emulate (T)");
        toolBar.add(button);
        
        button = makeToolBarButton("reset24", new ResetingEmulationAction(
                emulator, data, this), "Reset emulation", "Reset");
        toolBar.add(button);

        add(toolBar, "North");

    }

    protected void panelToPanelWithScroll(JPanel inPanel, JPanel outPanel) {
        JScrollPane scroller = new JScrollPane(inPanel);
        AdjustmentListener listener = new DrawerAdjustmentListener(this);
        scroller.getHorizontalScrollBar().addAdjustmentListener(listener);
        scroller.getVerticalScrollBar().addAdjustmentListener(listener);
        outPanel.add(scroller, BorderLayout.CENTER);
    }

    protected void initializeTabs() {
        JPanel drawingPanel = new JPanel(new BorderLayout());
        JPanel transTablePanel = new JPanel(new BorderLayout());
        JPanel descrTablePanel = new JPanel(new BorderLayout());
        JPanel emulTablePanel = new JPanel(new BorderLayout());
        JPanel reachabiblityGraphPanel = new JPanel(new BorderLayout());
        JPanel markovGraphPanel = new JPanel(new BorderLayout());
        JPanel emulatingPanel = new JPanel(new BorderLayout());

        elementDrawer = new ElementDrawer(data, this);
        reachabiblityGraph = new ReachabilityGraphDrawer(data);
        markGraph = new MarkovGraphDrawer(data);
        DescriptiveTableDrawer descrTable = new DescriptiveTableDrawer(data);
        TransitionsTableDrawer transTable = new TransitionsTableDrawer(data,
                this);
        EmulationTablesDrawer emulTable = new EmulationTablesDrawer(data,
                emulator, this);
        ElementDrawer emulatingDrawer = new ElementDrawer(emulator.getData(),
                this);

        panelToPanelWithScroll(elementDrawer, drawingPanel);
        panelToPanelWithScroll(reachabiblityGraph, reachabiblityGraphPanel);
        panelToPanelWithScroll(markGraph, markovGraphPanel);
        panelToPanelWithScroll(transTable, transTablePanel);
        panelToPanelWithScroll(descrTable, descrTablePanel);
        panelToPanelWithScroll(emulTable, emulTablePanel);
        panelToPanelWithScroll(emulatingDrawer, emulatingPanel);

        JTabbedPane tabPane = new JTabbedPane();
        tabPane.add("Editing", drawingPanel);
        tabPane.add("Descriptive Tables", descrTablePanel);
        tabPane.add("Markov Graph", markovGraphPanel);
        tabPane.add("Reachabiblity Graph", reachabiblityGraphPanel);
        tabPane.add("Transitions Table", transTablePanel);
        tabPane.add("Emulation Table", emulTablePanel);
        tabPane.add("Emulating", emulatingPanel);

        this.getContentPane().add(tabPane);
    }

    public void createAndShowGUI() {
        setTitle("Petri nets Builder");
        setSize(fWidth, fHeight);
        setMinimumSize(new Dimension(fWidth, fHeight));
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (UnsupportedLookAndFeelException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        initializeTabs();
        initializeMenuBar();
        initializeToolBar();

        setVisible(true);

        addWindowListener(new AppWindowListener(data, this));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}