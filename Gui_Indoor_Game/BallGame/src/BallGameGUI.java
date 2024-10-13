import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BallGameGUI extends JFrame {
    private char[][] mat;
    private int R, C;
    private JPanel panel;
    private boolean isWaiting = true;
    private int currentRow, currentCol;

    public BallGameGUI() {
        initialize();
        getUserInput();
        intialFill();

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMatrix(g);
            }
        };

        panel.setPreferredSize(new Dimension(500, 500));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / 20;
                int row = e.getY() / 20;
                if (row >= 1 && row < R - 1 && col >= 1 && col < C - 1) {
                    resetBallPosition();
                    currentRow = row;
                    currentCol = col;
                    mat[row][col] = 'B';
                    panel.repaint();
                }
            }
        });

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
        JButton upButton = new JButton("Up");

        leftButton.addActionListener(e -> moveBall('L'));
        rightButton.addActionListener(e -> moveBall('R'));
        upButton.addActionListener(e -> moveBall('U'));

        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);
        buttonPanel.add(upButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initialize() {
        R = 10;
        C = 10;
    }

    private void getUserInput() {
        try {
            R = Integer.parseInt(JOptionPane.showInputDialog("Enter row size:"));
            C = Integer.parseInt(JOptionPane.showInputDialog("Enter column size:"));
            int ballPos = Integer.parseInt(JOptionPane.showInputDialog("Enter ball position at bottom (1 to " + (C - 1) + "):"));
            if (ballPos < 1 || ballPos > C - 1) {
                JOptionPane.showMessageDialog(this, "The ball position needs to be within column size defined. (1 to " + (C - 1) + ")");
                getUserInput();
            }
            currentRow = R - 2;
            currentCol = ballPos;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
            getUserInput();
        }
    }

    private void intialFill() {
        mat = new char[R][C];
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (row == 0 || row == R - 1 || col == 0 || col == C - 1) {
                    mat[row][col] = '*';
                } else if (col == currentCol && row == R - 2) {
                    mat[row][col] = 'B';
                } else {
                    mat[row][col] = '-';
                }
            }
        }
    }

    private void resetBallPosition() {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (mat[row][col] == 'B') {
                    mat[row][col] = '-';
                }
            }
        }
    }

    private void swap(int row, int col, int nrow, int ncol) {
        char temp = mat[row][col];
        mat[row][col] = mat[nrow][ncol];
        mat[nrow][ncol] = temp;
    }

    private void drawMatrix(Graphics g) {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                g.drawString(String.valueOf(mat[row][col]), col * 20, row * 20);
            }
        }
    }

    private void moveBall(char direction) {
        final boolean[] left = {direction == 'L'};
        final boolean[] up = {true};
        
        Timer timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isWaiting) return;

                if (left[0]) {
                    if (currentCol == 1) left[0] = false;  // Bounce back when hitting left wall
                } else {
                    if (currentCol == C - 2) left[0] = true;  // Bounce back when hitting right wall
                }
                if (currentRow == 1) up[0] = false;  // Bounce back when hitting top wall
                if (currentRow == R - 2) up[0] = true;  // Bounce back when hitting bottom wall

                if (left[0]) {
                    if (up[0]) {
                        swap(currentRow, currentCol, --currentRow, --currentCol);
                    } else {
                        swap(currentRow, currentCol, ++currentRow, --currentCol);
                    }
                } else {
                    if (up[0]) {
                        swap(currentRow, currentCol, --currentRow, ++currentCol);
                    } else {
                        swap(currentRow, currentCol, ++currentRow, ++currentCol);
                    }
                }

                panel.repaint();

                if (currentRow == R - 2) {
                    isWaiting = true;
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        
        isWaiting = false;
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BallGameGUI::new);
    }
}
