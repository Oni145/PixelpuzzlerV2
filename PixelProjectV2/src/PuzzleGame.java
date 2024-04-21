import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PuzzleGame extends JFrame {

    private static final int GRID_SIZE = 3; // Changed to 5
    private static final int BOARD_SIZE = 800;
    private static final int CELL_SIZE = BOARD_SIZE / GRID_SIZE;
    private static final String IMAGE_DIRECTORY = "photos"; // Directory containing images

    private JButton[][] tiles;
    private BufferedImage image;
    private List<BufferedImage> tileImages;
    private int emptyRow, emptyCol;
    private int moveCount;
    private Timer timer;
    private JLabel timerLabel;
    private JLabel moveLabel;

    public PuzzleGame() {
        setTitle("Puzzle Slider Game");
        setSize(BOARD_SIZE, BOARD_SIZE + 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        tiles = new JButton[GRID_SIZE][GRID_SIZE];
        tileImages = new ArrayList<>();

        JPanel controlPanel = new JPanel();
        JButton resetButton = new JButton("Another photo");
        JButton hintButton = new JButton("Hint");
        controlPanel.add(resetButton);
        controlPanel.add(hintButton);

        JPanel gamePanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE, 0, 0));
        gamePanel.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Load random image and split it into tiles
        loadRandomImage();
        splitImage();

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

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame();
            }
        });

        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHint();
            }
        });

        add(controlPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, new TimerListener());
        timer.start();

        initializeGame();
    }

    private void initializeGame() {
        moveCount = 0;
        moveLabel.setText("Moves: " + moveCount);

        // Load random image and split it into tiles
        loadRandomImage();
        splitImage();

        List<Integer> tileIndexes = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            tileIndexes.add(i);
        }
        Collections.shuffle(tileIndexes);

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int index = tileIndexes.get(i * GRID_SIZE + j);
                int row = index / GRID_SIZE;
                int col = index % GRID_SIZE;
                tiles[i][j].setIcon(new ImageIcon(tileImages.get(row * GRID_SIZE + col)));
                if (index == GRID_SIZE * GRID_SIZE - 1) {
                    emptyRow = i;
                    emptyCol = j;
                    tiles[i][j].setIcon(null);
                }
            }
        }
    }

    private void loadRandomImage() {
        File directory = new File(IMAGE_DIRECTORY);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));
        if (files != null && files.length > 0) {
            Random rand = new Random();
            int randIndex = rand.nextInt(files.length);
            try {
                image = ImageIO.read(files[randIndex]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No image files found in the directory: " + IMAGE_DIRECTORY);
        }
    }

    private void splitImage() {
        tileImages.clear(); // Clear previous tile images
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                int startX = x * (width / GRID_SIZE);
                int startY = y * (height / GRID_SIZE);
                int endX = startX + (width / GRID_SIZE);
                int endY = startY + (height / GRID_SIZE);

                BufferedImage tileImage = new BufferedImage(CELL_SIZE, CELL_SIZE, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = tileImage.createGraphics();
                g.drawImage(image, 0, 0, CELL_SIZE, CELL_SIZE, startX, startY, endX, endY, null);
                g.dispose();
                tileImages.add(tileImage);
            }
        }
    }

    private void showHint() {
        JOptionPane.showMessageDialog(this, new ImageIcon(image), "Hint", JOptionPane.PLAIN_MESSAGE);
    }

    private class TileClickListener extends MouseAdapter {
        private int row, col;

        public TileClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton clickedTile = tiles[row][col];
            if (isValidMove(row, col, emptyRow, emptyCol)) {
                tiles[emptyRow][emptyCol].setIcon(clickedTile.getIcon());
                tiles[row][col].setIcon(null);

                // Update empty tile position
                emptyRow = row;
                emptyCol = col;
                moveCount++;
                moveLabel.setText("Moves: " + moveCount);

                // Check if puzzle is solved
                if (isPuzzleSolved()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(PuzzleGame.this, "Congratulations! You've solved the puzzle!\nTime: " + timerLabel.getText() + "\nMoves: " + moveCount);
                }
            }
        }

        private boolean isValidMove(int row, int col, int emptyRow, int emptyCol) {
            return (Math.abs(row - emptyRow) == 1 && col == emptyCol) || (Math.abs(col - emptyCol) == 1 && row == emptyRow);
        }

        private boolean isPuzzleSolved() {
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    if (tiles[i][j].getIcon() == null) {
                        if (i != GRID_SIZE - 1 || j != GRID_SIZE - 1) {
                            return false;
                        }
                    } else {
                        int index = i * GRID_SIZE + j;
                        int row = index / GRID_SIZE;
                        int col = index % GRID_SIZE;
                        if (tiles[i][j].getIcon() != null && !tileImages.get(row * GRID_SIZE + col).equals(((ImageIcon) tiles[i][j].getIcon()).getImage())) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
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
        SwingUtilities.invokeLater(() -> new PuzzleGame().setVisible(true));
    }
}

