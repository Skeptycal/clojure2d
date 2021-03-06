package clojure2d.java.reconstruction;

import net.jafama.FastMath;

public class Sinc extends AFilter {
    private double tau;

    public Sinc(double radius, double tau) {
        super(radius);
        this.tau = FastMath.abs(tau);
        init();
    }

    public Sinc() {
        this(1.5,1.5);
    }
    
    private double sinc(double v) {
        if (v<1.0e-5) return 1.0;
        double pv = FastMath.PI*v;
        return FastMath.sin(pv)/pv;
    }

    private double windowedSinc(double v) {
        double vv = FastMath.abs(v);
        if (vv>radius) return 0.0;
        return sinc(vv)*sinc(vv/tau);
    }

    public double evaluate(double x, double y) {
        return windowedSinc(x)*windowedSinc(y);
    }

    public String getName() {
        return "Windowed Sinc (Lanchos)";
    }
}
