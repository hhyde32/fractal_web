package com.example.fractal;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Main {
    private Secant iterator;
    private Complex origin;
    private double width;
    private final int numPixels;

    private ArrayList<Complex> roots;
    private Color[][] colours;
    private boolean colourIterations;
    private BufferedImage fractal;
    private Graphics2D g2;

    public Main(
            Polynomial p,
            Complex origin,
            double width,
            int numPixels
    ) {
        this.iterator = new Secant(p);
        this.width = width;
        this.origin = origin;
        this.numPixels = numPixels;

        setupFractal();
    }

    public void printRoots() {
        // Print roots.
        System.out.println(this.roots);
    }

    public ArrayList<Complex> getRoots() {
        // Get roots.
        return roots;
    }

    public int index(Complex root) {
        // Get the index of a roots.
        for (int i = 0; i < roots.size(); i++) {
            if (roots.get(i).add(root.negate()).abs() < Secant.TOL) {
                return i;
            }
        }
        return -1;
    }

    public Complex pixelToComplex(int i, int j) {
        // Converts pixel positions to complex numbers starting from the origin.
        double dz = width / numPixels;
        return new Complex(
                origin.getReal() + i * dz, 
                origin.getImag() - j * dz
        );
    }

    public void createFractal(boolean colourIterations) {
        // Create a fractal image.

        this.colourIterations = colourIterations;
        this.roots = new ArrayList<Complex>();

        // Iterate over each pixel at position (j, k).
        for (int j = 0; j < numPixels; j++) {
            for (int k = 0; k < numPixels; k++) {
                // Translate each pixel to a complex number.
                Complex z = pixelToComplex(j, k);

                // Use zero and this complex number as to run though secant algorithm.
                iterator.iterate(new Complex(), z);

                if (iterator.getError() != Secant.Error.OK) {
                    continue; // Skip this pixel.
                }

                Complex root = iterator.getRoot();
                int n = iterator.getNumIterations();

                int rootIndex = index(root);

                // Check to see if root is found already.
                if (rootIndex == -1) {
                    if (roots.size() < 5) {
                        roots.add(root);
                        rootIndex = roots.size() - 1;
                    } else {
                        continue; // Skip colouring if something goes wrong.
                    }
                }

                colourPixel(j, k, rootIndex, n);
            }
        }
    }


    private void setupFractal() {
        // Set up the fractal image.
        int i, j;

        if (iterator.getF().degree() < 3 || iterator.getF().degree() > 5)
            throw new RuntimeException("Degree of polynomial must be between 3 and 5 inclusive!");

        this.colours = new Color[5][Secant.MAXITER];
        this.colours[0][0] = Color.RED;
        this.colours[1][0] = Color.GREEN;
        this.colours[2][0] = Color.BLUE;
        this.colours[3][0] = Color.CYAN;
        this.colours[4][0] = Color.MAGENTA;

        for (i = 0; i < 5; i++) {
            float[] components = colours[i][0].getRGBComponents(null);
            float[] delta = new float[3];

            for (j = 0; j < 3; j++) delta[j] = 0.8f * components[j] / Secant.MAXITER;

            for (j = 1; j < Secant.MAXITER; j++) {
                float[] tmp = colours[i][j - 1].getRGBComponents(null);
                colours[i][j] = new Color(tmp[0] - delta[0], tmp[1] - delta[1], tmp[2] - delta[2]);
            }
        }

        fractal = new BufferedImage(numPixels, numPixels, BufferedImage.TYPE_INT_RGB);
        g2 = fractal.createGraphics();
    }

    private void colourPixel(int i, int j, int rootColour, int numIter) {
        if (colourIterations) {
            int colourIndex = Math.max(
                    0,
                    Math.min(numIter - 1, Secant.MAXITER - 1)
            );

            g2.setColor(colours[rootColour][colourIndex]);
        } else {
            g2.setColor(colours[rootColour][0]);
        }

        g2.fillRect(i, j, 1, 1);
    }


    public void saveFractal(String fileName) {
        // Save the fractal image to a file.
        try {
            File outputfile = new File(fileName);
            ImageIO.write(fractal, "png", outputfile);
        } catch (IOException e) {
            System.out.println("I got an error trying to save! Maybe you're out of space?");
        }
    }

    public BufferedImage generate(boolean colourIterations) {
        createFractal(colourIterations);
        return fractal;
    }

    public BufferedImage getFractal() {
        return fractal;
    }

    public static void main(String[] args) {
        // Complex number and range to draw the fractal for.

        // Coefficients for f(z) = z^3 - 1.
        Complex[] coeff = new Complex[] {
            new Complex(-1.0, 0.0), // constant term
            new Complex(0.0, 0.0),  // z
            new Complex(0.0, 0.0),  // z^2
            new Complex(1.0, 0.0)   // z^3
        };

        Polynomial p = new Polynomial(coeff);

        // Centered at origin (-2.0 + 2.0i) with a width of 4.0.
        Main project = new Main(p, new Complex(-2.0, 2.0), 4.0, 600);

        // Draw fractal images.
        project.createFractal(false);
        project.saveFractal("images/fractal-light.png");
        project.createFractal(true);
        project.saveFractal("images/fractal-dark.png");
    }
}
