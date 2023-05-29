package ClasesObserver;

import com.example.odswix.Perfil;

public class ObserverPerfil implements EstadisticasObserver{
    Perfil perfil;

    public void ObserverPerfil(Perfil perfil) {
        this.perfil = perfil;

    }

    @Override
    public void onEstadisticasActualizadas() {
        perfil.onEstadisticasActualizadas();

    }
}
