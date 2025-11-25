package documentacio_git;

/**
 * Enumeració que defineix els possibles estats en què es pot trobar un material.
 * Aquests estats determinen la disponibilitat de l'ítem per a nous préstecs.
 */
public enum MaterialStates {
    /**
     * El material està a l'inventari i es pot prestar immediatament.
     */
	DISPONIBLE,
    
    /**
     * El material està actualment en possessió d'un usuari.
     */
    PRESTADO,
    
    /**
     * El material està avariat o en procés de manteniment.
     */
    REPARACION,
    
    /**
     * El material ha estat retirat definitivament de l'inventari.
     */
    BAJA
}