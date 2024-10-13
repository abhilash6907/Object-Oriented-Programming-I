import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ArcheryGameEnhanced extends JPanel implements ActionListener, KeyListener {
    private int arrowX, arrowY;
    private boolean shotFired = false;
    private boolean gameWon = false;
    private Timer timer;

    private final int TARGET_X = 700;
    private final int TARGET_Y = 250;
    private final int TARGET_WIDTH = 50;
    private final int TARGET_HEIGHT = 100;

    public ArcheryGameEnhanced() {
        arrowX = 100;  // Initial X position of the arrow (near the bow)
        arrowY = 300; // Initial Y position of the arrow
        timer = new Timer(10, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw gradient sky
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint skyGradient = new GradientPaint(0, 0, Color.CYAN, 0, getHeight() / 2, Color.WHITE);
        g2d.setPaint(skyGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight() / 2);

        // Draw the ground
        g.setColor(new Color(60, 179, 113));
        g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // Draw the target with shadows
        g.setColor(new Color(139, 69, 19));  // Shadow color
        g.fillOval(TARGET_X + 5, TARGET_Y + 5, TARGET_WIDTH, TARGET_HEIGHT); // Shadow
        g.setColor(Color.RED);
        g.fillOval(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT);
        g.setColor(Color.WHITE);
        g.fillOval(TARGET_X + 10, TARGET_Y + 10, TARGET_WIDTH - 20, TARGET_HEIGHT - 20);
        g.setColor(Color.RED);
        g.fillOval(TARGET_X + 20, TARGET_Y + 20, TARGET_WIDTH - 40, TARGET_HEIGHT - 40);

        // Draw the bow (stationary)
        g.setColor(Color.DARK_GRAY);
        g.fillRect(50, 250, 10, 100);  // Bow body
        g.drawLine(55, 250, 55, 350);  // Bow string

        // Draw the arrow
        g.setColor(Color.BLACK);
        g.fillRect(arrowX, arrowY, 50, 5);  // Arrow body
        g.setColor(Color.GRAY);
        int[] arrowTipX = {arrowX + 50, arrowX + 60, arrowX + 50};  // Tip coordinates
        int[] arrowTipY = {arrowY - 5, arrowY + 2, arrowY + 7};  // Tip Y coordinates
        g.fillPolygon(arrowTipX, arrowTipY, 3);  // Arrowhead

        // Draw arrow feathers
        g.setColor(Color.YELLOW);
        g.fillRect(arrowX - 10, arrowY, 5, 2);  // Feather on the left
        g.fillRect(arrowX - 10, arrowY + 3, 5, 2);  // Feather on the left lower

        // Display "Game Won" message
        if (gameWon) {
            g.setColor(Color.BLUE);
            g.setFont(new Font("Serif", Font.BOLD, 36));
            g.drawString("You hit the target!", 250, 100);
        }

        // Display score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Use UP/DOWN to aim, SPACE to shoot, 'R' to reset", 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If shot has been fired, move arrow to the right
        if (shotFired && !gameWon) {
            arrowX += 5;

            // Check if arrow hits the target
            if (arrowX + 50 >= TARGET_X && arrowY >= TARGET_Y && arrowY <= TARGET_Y + TARGET_HEIGHT) {
                gameWon = true;  // Arrow hit the target
            }

            // Reset if the arrow goes off the screen
            if (arrowX > getWidth()) {
                resetGame();  // Reset for the next round
            }
        }

        repaint();  // Repaint the panel on every tick
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Arrow up and down movement
        if (key == KeyEvent.VK_UP && arrowY > 0) {
            arrowY -= 10;  // Move arrow up
        } else if (key == KeyEvent.VK_DOWN && arrowY < getHeight() - 10) {
            arrowY += 10;  // Move arrow down
        }

        // Shoot the arrow
        if (key == KeyEvent.VK_SPACE) {
            shotFired = true;  // Arrow will start moving to the right
        }

        // Reset the game by pressing 'R'
        if (key == KeyEvent.VK_R) {
            resetGame();
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    // Reset game state for a new round
    private void resetGame() {
        arrowX = 100;  // Reset arrow to initial position near the bow
        arrowY = 300;
        shotFired = false;
        gameWon = false;
    }

    // Main method to start the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Enhanced Archery Game");
            ArcheryGameEnhanced game = new ArcheryGameEnhanced();
            frame.add(game);
            frame.setSize(800, 600);  // Set frame size
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
