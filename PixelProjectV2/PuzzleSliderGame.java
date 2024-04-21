import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PuzzleSliderGame extends JFrame {

    private static final int GRID_SIZE = 4;
    private static final int CELL_SIZE = 80;
    private static final int BOARD_SIZE = GRID_SIZE * CELL_SIZE;
    private static final int SHUFFLE_MOVES = 1000;

    private JButton[][] tiles;
    private int[][] solution;
    private int emptyRow, emptyCol;
    private int moveCount;
    private Timer timer;
    private JLabel timerLabel;
    private JLabel moveLabel;

    public PuzzleSliderGame() {
        setTitle("Puzzle Slider Game");
        setSize(BOARD_SIZE, BOARD_SIZE + 50);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tiles = new JButton[GRID_SIZE][GRID_SIZE];
        solution = new int[GRID_SIZE][GRID_SIZE];

        JPanel gamePanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        gamePanel.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JButton tile = new JButton();
                tile.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                tile.setFocusable(false);
                tile.addMouseListener(new TileClickListener(i, j));
                tiles[i][j] = tile;
                gamePanel.add(tile);
            }
        }

        timerLabel = new JLabel("Timer: 0");
        moveLabel = new JLabel("Moves: 0");

        JPanel infoPanel = new JPanel();
        infoPanel.add(timerLabel);
        infoPanel.add(moveLabel);

        add(gamePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, new TimerListener());
        timer.start();

        initializeGame();
    }

    private void initializeGame() {
        moveCount = 0;
        moveLabel.setText("Moves: " + moveCount);

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                solution[i][j] = i * GRID_SIZE + j + 1;
                if (i == GRID_SIZE - 1 && j == GRID_SIZE - 1) {
                    solution[i][j] = 0;
                }
            }
        }

        shuffleTiles();

        updateUI();
    }

    private void shuffleTiles() {
        Random rand = new Random();
        emptyRow = GRID_SIZE - 1;
        emptyCol = GRID_SIZE - 1;

        for (int i = 0; i < SHUFFLE_MOVES; i++) {
            int randDirection = rand.nextInt(4);
            int newRow = emptyRow;
            int newCol = emptyCol;

            switch (randDirection) {
                case 0:
                    newRow = Math.max(0, emptyRow - 1);
                    break;
                case 1:
                    newRow = Math.min(GRID_SIZE - 1, emptyRow + 1);
                    break;
                case 2:
                    newCol = Math.max(0, emptyCol - 1);
                    break;
                case 3:
                    newCol = Math.min(GRID_SIZE - 1, emptyCol + 1);
                    break;
            }

            swapTiles(emptyRow, emptyCol, newRow, newCol);
            emptyRow = newRow;
            emptyCol = newCol;
        }
    }

    private void swapTiles(int row1, int col1, int row2, int col2) {
        int temp = solution[row1][col1];
        solution[row1][col1] = solution[row2][col2];
        solution[row2][col2] = temp;
    }

    private void updateUI() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int value = solution[i][j];
                tiles[i][j].setText(value == 0 ? "" : String.valueOf(value));
                tiles[i][j].setEnabled(value != 0);
            }
        }
    }

    private class TileClickListener extends MouseAdapter {
        private int row, col;

        public TileClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isValidMove(row, col)) {
                swapTiles(emptyRow, emptyCol, row, col);
                emptyRow = row;
                emptyCol = col;
                moveCount++;
                moveLabel.setText("Moves: " + moveCount);
                updateUI();
                if (isSolved()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(PuzzleSliderGame.this, "Congratulations! You solved the puzzle in " + moveCount + " moves.");
                }
            }
        }

        private boolean isValidMove(int row, int col) {
            return (Math.abs(emptyRow - row) == 1 && emptyCol == col) || (Math.abs(emptyCol - col) == 1 && emptyRow == row);
        }
    }

    private boolean isSolved() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (solution[i][j] != i * GRID_SIZE + j + 1 && !(i == GRID_SIZE - 1 && j == GRID_SIZE - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private class TimerListener implements ActionListener {
        private int seconds = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            seconds++;
            timerLabel.setText("Timer: " + seconds);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PuzzleSliderGame().setVisible(true));
    }
}
