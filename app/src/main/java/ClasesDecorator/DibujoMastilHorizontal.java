package ClasesDecorator;

import com.example.odswix.ImageViewAhorcado;

public class DibujoMastilHorizontal extends DibujoDecorator{

    public DibujoMastilHorizontal(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {
        super.getImage().drawMastilHor();
    }
}
