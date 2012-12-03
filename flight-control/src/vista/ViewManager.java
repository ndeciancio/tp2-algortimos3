package vista;


public interface ViewManager {
	public void iniciarEjecucion();
	public void detenerEjecucion();
	public void removerVistaAvion(VistaAvion va);
	public void addVistaAvion(VistaAvion va);
	public void addVistaPista(VistaPista vp);
	public void removerVistaPista(VistaPista vp);
	public void showPuntaje(Integer puntos);
	public void removeAll();
}
