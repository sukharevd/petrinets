package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import data.Data;

import actions.listeners.DrawerAdjustmentListener;
import actions.menuadd.AddingArcAction;
import actions.menuadd.AddingImmediateTransitionAction;
import actions.menuadd.AddingPlaceAction;
import actions.menuadd.AddingTimeTransitionAction;
import actions.menugeneral.AboutingAction;
import actions.menugeneral.CreatingAction;
import actions.menugeneral.ExitingAction;
import actions.menugeneral.ExportingAction;
import actions.menugeneral.HelpingAction;
import actions.menugeneral.OpeningAction;
import actions.menugeneral.SavingAction;
import actions.menuundo.RedoAction;
import actions.menuundo.UndoAction;

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

    private ElementDrawer elementDrawer;

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
    public AppFrame(final Data data) {
        super();
        this.data = data;
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
    public/* synchronized */final ElementDrawer getElementDrawer() {
        return elementDrawer;
    }

    // TODO: remove synchronized
    /**
     * @param drawer
     *            the elementDrawer to set
     */
    public/* synchronized */final void setElementDrawer(ElementDrawer drawer) {
        elementDrawer = drawer;
    }

    public void initializeMenuBar() {

        JMenuBar bar;

        JMenu file = new JMenu("File");
        // JMenu mode = new JMenu("Mode");
        JMenu edit = new JMenu("Edit");
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

        newf.setAction(new CreatingAction(data, this));
        newf.setText("New");
        newf.setMnemonic(KeyEvent.VK_N);
        newf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));

        open.setAction(new OpeningAction(data, this));
        open.setText("Open...");
        open.setMnemonic(KeyEvent.VK_O);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));

        save.setAction(new SavingAction(data, this));
        save.setText("Save as...");
        save.setMnemonic(KeyEvent.VK_S);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));

        export.setAction(new ExportingAction(data, this));
        export.setText("Export...");
        export.setMnemonic(KeyEvent.VK_E);
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.CTRL_MASK));

        close.setAction(new CreatingAction(data, this));
        close.setText("Close");
        close.setMnemonic(KeyEvent.VK_W);
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
                ActionEvent.CTRL_MASK));

        exit.setAction(new ExitingAction(data, this));
        exit.setText("Exit");
        exit.setMnemonic(KeyEvent.VK_Q);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                ActionEvent.CTRL_MASK));

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

        // -------------------------------------
        // ---------> Mode Menu <---------------
        // -------------------------------------

        // JMenuItem editor = new JMenuItem();
        // // JMenuItem deleter = new JMenuItem();
        // JMenuItem tree = new JMenuItem();
        // JMenuItem emul = new JMenuItem();
        //
        // editor.setAction(new ModelEditorAction());
        // editor.setText("Editor");
        // editor.setMnemonic(KeyEvent.VK_E);
        // editor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
        // ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        //
        // tree.setAction(new ModelTreeAction(data));
        // tree.setText("Tree");
        // tree.setMnemonic(KeyEvent.VK_T);
        // tree.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
        // ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        //
        // emul.setAction(new ModelEmulationAction());
        // emul.setText("Emulate");
        // emul.setMnemonic(KeyEvent.VK_M);
        // emul.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
        // ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));

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

        file.add(newf);
        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(export);
        file.addSeparator();
        file.add(close);
        file.addSeparator();
        file.add(exit);

        edit.add(undo);
        edit.add(redo);
        edit.addSeparator();
        edit.add(addPlace);
        edit.add(addTTransition);
        edit.add(addITransition);
        edit.add(addArc);

        // mode.add(editor);
        // mode.add(tree);
        // mode.add(emul);

        help.add(chelp);
        help.add(about);

        bar = new JMenuBar();
        bar.add(file);
        bar.add(edit);
        // bar.add(mode);
        bar.add(help);

        setJMenuBar(bar);
    }

    // protected void makeToolBarButton(int seeYourKP) {
    //        
    // }
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
        String imgLocation = "res/" + imageName + ".gif";
        String imgLocationPng = "res/" + imageName + ".png";
        URL imageURL = AppFrame.class.getResource(imgLocation);
        URL imageURLpng = AppFrame.class.getResource(imgLocationPng);

        // Create and initialize the button.
        JButton button = new JButton();
        button.setAction(action);
        button.setToolTipText(toolTipText);

        if (imageURL != null) { // image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else { // no image found
            if (imageURLpng != null) { // image found
                button.setIcon(new ImageIcon(imageURLpng, altText));
            } else { // no image found
                button.setText(altText);
                System.err.println("Resource not found: " + imgLocation);
            }
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
                this), "Create new file", "New");
        toolBar.add(button);

        button = makeToolBarButton("Open24", new OpeningAction(data, this),
                "Open", "Open");
        toolBar.add(button);

        button = makeToolBarButton("Save24", new SavingAction(data, this),
                "Save", "Save as");
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

        add(toolBar, "North");

    }

    public void createAndShowGUI() {
        setTitle("Petri nets Builder");
        setSize(fWidth, fHeight);
        setMinimumSize(new Dimension(fWidth, fHeight));

        initializeMenuBar();
        initializeToolBar();

        // organization of tab pane:
        JPanel tablePanel = new JPanel();
        JPanel drawingPanel = new JPanel(new BorderLayout());
        JPanel markovGraphPanel = new MarkovGraphDrawer(data);
        JPanel reachabiblityGraphPanel = new ReachabilityGraphDrawer(data);
        // drawingPanel.setOpaque(true);

        JTabbedPane tabPane = new JTabbedPane();
        tabPane.add("Drawing", drawingPanel);
        tabPane.add("Tables", tablePanel);
        tabPane.add("Markov Graph", markovGraphPanel);
        tabPane.add("Reachabiblity Graph", reachabiblityGraphPanel);

        this.getContentPane().add(tabPane);

        // organization of scroll pane for dawingPanel:
        elementDrawer = new ElementDrawer(data, this);
        JScrollPane scroller = new JScrollPane(elementDrawer);
        AdjustmentListener listener = new DrawerAdjustmentListener(this);
        scroller.getHorizontalScrollBar().addAdjustmentListener(listener);
        scroller.getVerticalScrollBar().addAdjustmentListener(listener);
        drawingPanel.add(scroller, BorderLayout.CENTER);

        setVisible(true);

        final JFrame thisFrame = this;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent we) {
                ExitingAction.exit(data, thisFrame);
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}