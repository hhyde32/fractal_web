package com.example.fractal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FractalController {

    @GetMapping(
            value = "/api/fractal",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public byte[] generateFractal(
            @RequestParam(defaultValue = "600") int size,
            @RequestParam(defaultValue = "4.0") double width,
            @RequestParam(defaultValue = "-2.0") double originReal,
            @RequestParam(defaultValue = "2.0") double originImag,
            @RequestParam(defaultValue = "false") boolean colourIterations
    ) throws IOException {

        size = Math.max(100, Math.min(size, 1200));

        Complex[] coefficients = new Complex[] {
                new Complex(-1.0, 0.0),
                new Complex(0.0, 0.0),
                new Complex(0.0, 0.0),
                new Complex(1.0, 0.0)
        };

        Polynomial polynomial = new Polynomial(coefficients);

        Main fractal = new Main(
                polynomial,
                new Complex(originReal, originImag),
                width,
                size
        );

        BufferedImage image = fractal.generate(colourIterations);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image, "png", output);

        return output.toByteArray();
    }
}

