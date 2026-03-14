package app;

import model.NeuralCanvas;
import processing.ImageProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class AppWindow extends JFrame implements ImageProcessor {
    private final NeuralCanvas canvas;
    private final CanvasPanel canvasPanel;
    private int currentSelectedColor = 0xFFFF0000;

    public AppWindow(int width, int height) {
        this.canvas = new NeuralCanvas(width, height);
        this.canvasPanel = new CanvasPanel();
        setTitle("Neural canvas GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout( new BorderLayout());
        setupUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupUI(){
        JPanel toolbar = new JPanel();

        JButton greyscale = new JButton("Greyscale");
        greyscale.addActionListener(e -> {
            apply(canvas,ImageProcessor.createGrayscale());
            canvasPanel.repaint();
        });

        JButton invert = new JButton("Invert");
        invert.addActionListener(e -> {
            apply(canvas,ImageProcessor.createInvert());
            canvasPanel.repaint();
        });

        toolbar.add(greyscale);
        toolbar.add(invert);
        add(toolbar, BorderLayout.NORTH);

        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event){
                canvas.new RenderContext().floodFill(event.getX(),event.getY(),currentSelectedColor);
                canvasPanel.repaint();
            }
        });
        add(canvasPanel, BorderLayout.CENTER);
    }

    public class CanvasPanel extends JPanel {
        private final BufferedImage image;

        public CanvasPanel() {
            setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));

            this.image = new BufferedImage(
                    canvas.getWidth(),
                    canvas.getHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int y = 0; y < canvas.getHeight(); y++) {
                for (int x = 0; x < canvas.getWidth(); x++) {
                    image.setRGB(x, y, canvas.getColor(x, y));
                }
            }

            g.drawImage(image, 0, 0, null);
        }
    }




}
