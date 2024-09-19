import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Paint extends JComponent implements Runnable {
    private Image image; // the canvas
    private Graphics2D graphics2D;  // enables drawing
    private int curX, curY, oldX, oldY;

    private JButton clrButton, fillButton, eraseButton, randomButton, hexButton, rgbButton;
    private JTextField hexField = new JTextField("#", 10);
    private JTextField redField = new JTextField(5);
    private JTextField greenField = new JTextField(5);
    private JTextField blueField = new JTextField(5);

    private Random random = new Random();

    public Paint() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY();
                if (graphics2D != null) {
                    graphics2D.setStroke(new BasicStroke(5));
                    graphics2D.drawLine(oldX, oldY, curX, curY);
                    repaint();
                    oldX = curX;
                    oldY = curY;
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }


    private void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        hexField.setText("#");
        redField.setText("");
        greenField.setText("");
        blueField.setText("");
        repaint();
    }

    private void fill() {
        graphics2D.setPaint(graphics2D.getColor());
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        clearFields();
    }

    private void erase() {
        graphics2D.setPaint(Color.white);
    }

    private void randomColor() {
        Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        graphics2D.setPaint(randomColor);
        updateColorFields(randomColor);
    }

    private void hexColor(String hex) {
        try {
            Color color = Color.decode(hex);
            graphics2D.setPaint(color);
            updateColorFields(color);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not a valid Hex Value", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rgbColor(int red, int green, int blue) {
        try {
            Color color = new Color(red, green, blue);
            graphics2D.setPaint(color);
            updateColorFields(color);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Not a valid RGB Value", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateColorFields(Color color) {
        String hex = "#" + Integer.toHexString(color.getRGB()).substring(2);
        hexField.setText(hex);
        redField.setText(String.valueOf(color.getRed()));
        greenField.setText(String.valueOf(color.getGreen()));
        blueField.setText(String.valueOf(color.getBlue()));
    }

    private void clearFields() {
        hexField.setText("#");
        redField.setText("");
        greenField.setText("");
        blueField.setText("");
    }

    public void run() {
        JFrame frame = new JFrame("Paint Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this, BorderLayout.CENTER);

        addButtons();

        JPanel topPanel = new JPanel();
        topPanel.add(clrButton);
        topPanel.add(fillButton);
        topPanel.add(eraseButton);
        topPanel.add(randomButton);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(hexField);
        bottomPanel.add(hexButton);
        bottomPanel.add(redField);
        bottomPanel.add(greenField);
        bottomPanel.add(blueField);
        bottomPanel.add(rgbButton);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addButtons() {
        clrButton = new JButton("Clear");
        clrButton.addActionListener(e -> clear());

        fillButton = new JButton("Fill");
        fillButton.addActionListener(e -> fill());

        eraseButton = new JButton("Erase");
        eraseButton.addActionListener(e -> erase());

        randomButton = new JButton("Random");
        randomButton.addActionListener(e -> randomColor());

        hexButton = new JButton("Hex");
        hexButton.addActionListener(e -> hexColor(hexField.getText()));

        rgbButton = new JButton("RGB");
        rgbButton.addActionListener(e -> {
            int red = getColor(redField.getText());
            int green = getColor(greenField.getText());
            int blue = getColor(blueField.getText());
            rgbColor(red, green, blue);
        });
    }

    private int getColor(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Paint());
    }
}
