# Polynomial Fractals
This project generates fractal images from 
polynomials, using the [Secant algorithm](https://en.wikipedia.org/wiki/Secant_method) to find roots.

The main interest of this project is to study the behavior of root convergence in the complex plane.
Each point is assigned a color according to what root of a polynomial it converges to when
using the Secant method. It may be intuitive to think that each point converges to the root that is 
closest in distance to it but it is not the case. As you can see it forms these beautiful patterns that 
are infinitely detailed.

## Visualization
The fractals below were generated from the polynomial $f(z) = z^3 - 1$ centered at $-2.0 + 2.0i$
with a width of $4$. The dark fractal uses the number of iterations to alter the brightness of the 
pixels; the brighter a pixel is the fewer iterations it took to arrive at a root.

#### Light fractal
![Light Newton fractal](images/light_fractal.png)

#### Dark fractal
![Dark Newton fractal](images/dark_fractal.png)

## Requirements

- Java 17 or later

## Output

Generated images are saved in the `images` directory.

## Project structure

- `Complex.java` — complex-number operations
- `Polynomial.java` — polynomial representation and evaluation
- `Secant.java` — root-finding algorithm
- `Main.java` — image generation and program entry point
## Usage

Clone the repository:

```bash
git clone https://github.com/hhyde32/newton_fractal.git
cd matvar
pip install -r requirements.txt
```

Run the following commands to compile and save the generated fractals to the images directory.
This may take around a minute depending on your system.

```bash
javac Main.java
java Main
```
You can edit the bottom of the Main.java file to generate different fractals.
