package ClasesDecorator;

import com.example.odswix.ImageViewAhorcado;

public class DibujoPiernaDer extends DibujoDecorator{

    public DibujoPiernaDer(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {

        super.getImage().drawPiernaDer();
    }
}
