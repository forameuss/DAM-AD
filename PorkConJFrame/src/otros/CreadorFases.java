package otros;

import fases.FaseImpl;
import fases.TipoFase;
import objetos.Arma;
import objetos.EnemigoImpl;
import objetos.Pocion;
import sistema.GestionBaseDeDatos;

public class CreadorFases {
	
	
	/**ESTE PROGRAMA SIRVE PARA ESCRIBIR LAS FASES EN LA BASE DE DATOS, NO FORMA PARTE DEL PROGRAMA GENERAL*/
	
	public static void main(String[] args) {
		GestionBaseDeDatos gbd = new GestionBaseDeDatos("localhost", "porkAdmin", "porkAdmin");

		// Fase 1
		String s11 = "\nEstás condenado a morir incinerado en la plaza del pueblo por tus graves delitos contra el reino de Java."
				+ "\nLos cargos de los que se te acusan son: \"herencia múltiple\",  \"no saber controlar los bucles\",  \"no "
				+ "\nusar excepciones\" y lo más grave de todo, \"hacer métodos sin interfaces\". Te quedan pocas opciones,"
				+ "\neste parece ser tu final, ¿qué haces?";
		String s12 = "Aceptas tu condena y te quedas en silencio muriendo por tus ideas.";
		String s13 = ("Mantienes tus ideas pero suplicas que alguien se apiade de ti.");
		String s14 = ("Vendes tus ideas y sucumbes a las órdenes de la reina para sobrevivir.");
		String[] s1 = new String[] { s12, s13, s14 };
		int[] o1 = new int[] { 2, 3, 4 };
		FaseImpl f1 = new FaseImpl(1, TipoFase.OPCIONES_NORMALES, s11, s1, o1, null, null, 0);

		// Fase 2
		String s2 = "\nHay gente que te apoyaba en silencio y te ven morir como un mártir en la plaza del pueblo. Eres un héroe, pero "
				+ "\neres hombre muerto. Estudia otro lenguaje en otra vida.";
		FaseImpl f2 = new FaseImpl(2, TipoFase.GAME_OVER, s2, null, null, null, null, 0);

		// Fase 3
		String s31 = "\nSuplicas piedad a viva voz, sin éxito aparente. El verdugo se dispone a prender la llama, cuando de repente, una"
				+ "\nflecha atraviesa la cabeza del ejecutor. Ante el asombro de todo el pueblo, notas que alguien te ha desatado."
				+ "\nDebes actuar rápido.";
		String s32 = "Intentar escapar.";
		String s33 = "Enfrentarse a tus opresores";
		String[] s3 = new String[] {s32, s33};
		int[] o3 = new int[] {6,5};
		FaseImpl f3 = new FaseImpl(3, TipoFase.OPCIONES_NORMALES, s31, s3, o3, null, null, 0);

		// Fase 4
		String s4 = "Lo siento, este juego no es para vendidos como tú. Se férreo en tus ideas,"
				+ "\nadaptarte a lo que te digan los demás igual te da de comer, pero te quita la personalidad.";
		FaseImpl f4 = new FaseImpl(4, TipoFase.GAME_OVER, s4, null, null, null, null, 0);

		// Fase 5
		EnemigoImpl e5 =  new EnemigoImpl("Verdugo", 500, 30, 5);
		Arma a5 = new Arma("Espada excepcional", 200);
		FaseImpl f5 = new FaseImpl(5, TipoFase.COMBATE, null, null, null, e5, a5, 0);

		// Fase 6
		String s61 = "Aprovechas el desconcierto para zafarte y sales corriendo de la plaza. Tras varios minutos, parece que la "
				+ "\nsituación se ha calmado. Tienes que planear bien el siguiente paso.";
		String s62 = "Salir de la ciudad";
		String s63 = "Intentar buscar un refugio dentro de la ciudad.";
		String[] s6 = new String[]{s62, s63};
		int[] o6 = new int[]{11,7};
		FaseImpl f6 = new FaseImpl(6, TipoFase.OPCIONES_NORMALES, s61, s6, o6, null, null, 0);

		

		// Fase 7
		String s71 = "Tras merodear un poco, descubres una posada algo sobria pero bastante discreta. Parece el mejor sitio para "
				+ "\nesconderse, pero aún hay tiempo para recapacitar.";
		String s72 = "Esconderse en la posada";
		String s73 = "Olvidarse de esconderse y salir de la ciudad.";
		String[] s7 = new String[]{s72,s73};
		int[] o7 = new int[]{8,11};
		FaseImpl f7 = new FaseImpl(7, TipoFase.OPCIONES_NORMALES, s71, s7, o7, null, null, 0);

		// Fase 8
		String s81 = "Te refugias en la posada y parece que pasas desapercibido para todos. De repente, escuchas la voz de un extraño:"
				+ "\n\t-¡Oye tú!, acércate."
				+ "\nDebes tener cuidado con tu siguiente paso, tu tapadera puede depender de ello.";
		String s82 = "Acercarse al extraño";
		String s83 = "Ignorarle.";
		String[] s8 = new String[]{s82,s83};
		int[] o8 = new int[]{9,10};
		FaseImpl f8 = new FaseImpl(8, TipoFase.OPCIONES_NORMALES, s81, s8, o8, null, null, 0);

		// Fase 9
		String s91 = "Te acercas al extraño, está sentado solo, en un rincón de la posada. Es un hombre de altura media, marcado con "
				+ "\nmuchas cicatrices y una mirada profunda. Te sientas junto a él, éste se inclina hacia tí y te susurra."
				+ "\n\t-Sé quién eres. Corres muchísmo peligro si te quedas en la ciudad. Tienes que hacerme caso, huye, "
				+ "\n\t te necesitan fuera."
				+ "\nRápidamente, el hombre se saca una especie de espada y te la da."
				+ "\n\t-Toma, necesitarás esto. Puede que no entiendas nada, pero no hay tiempo para explicaciones. ¡Tienes que huir!"
				+ "\nEl individuo ha sido claro, pero nunca se sabe cuál es la mejor opción:";
		String s92 = "No hacer caso al extraño, esto huele a chamusquina";
		String s93 = "Hacerle caso y huir de la ciudad.";
		String[] s9 = new String[]{s92,s93};
		int[] o9 = new int[]{10,11};
		Arma a9 = new Arma("Espada booleana", 20);
		FaseImpl f9 = new FaseImpl(9, TipoFase.OPCIONES_RECOMPENSA, s91, s9, o9, null, a9, 0);

		// Fase 10
		String s101 = "Ignoras a ese desconocido y sigues en tu sigiloso escondite sin llamar la atención. Sin embargo, de repente entran"
				+ "\nen la posada un grupo de guardias que se dirigen a tí, y sin un ápice de duda te rebanan el cuello al grito de:"
				+ "\n\t-¡Alabada sea la reina de Java!" + "\nTe han matado antes de que pudieras reaccionar.";
		FaseImpl f10 = new FaseImpl(10, TipoFase.GAME_OVER, s101, null, null, null, null, 0);
		
		// Fase 11
		String s111 = "Abandonas la ciudad sin problemas, y sigues el camino para alejarte lo máximo posible de tus "
				+ "\nperseguidores. Al poco de alejarte de las murallas, divisas a un mercader a lo lejos. Puede que sea interesante"
				+ "\ninteractuar con el...";
		String s112 = "Asaltar al mercader";
		String s113 = "Ignorarlo y seguir tu camino";
		String s114 = "Hablar con él";
		String[] sa11 = new String[]{s112, s113, s114};
		int[] o11 = new int[]{15,16,12};
		FaseImpl f11 = new FaseImpl(11, TipoFase.OPCIONES_NORMALES, s111, sa11, o11, null, null, 0);
		
		
		// Fase 12
		String s121 = "Te acercas al mercader y sin ni siquera decir una palabra éste te empieza a vender sus productos."
				+ "\n\t-¡Bien implementado seáis, mi amigo! Tengo las mejores interfaces de todo el reino de java, "
				+ "\n\t con estudios de calidad y las APIs más exóticas de todo el mundo comentado. ¿Te gustaría echar un"
				+ "\n\t vistazo a mi inventario?"
				+ "\nEs tentador, pero tienes otros intereses.";
		String s122 = "Pedir indicaciones para avanzar.";
		String s123 = "Contarle tu historia y preguntar por un buen sitio a donde huir.";
		String[] sa12 = new String[]{s122,s123};
		int[] o12 = new int[]{14,13};
		FaseImpl f12 = new FaseImpl(12, TipoFase.OPCIONES_NORMALES, s121, sa12, o12, null, null, 0);
		
		// Fase 13
		String s131 = "Explicas tu historia al mercader, el cuál te mira desconfiadamente. Sin previo aviso, él empieza a gritar:"
				+ "\n\t-¡Guardias!¡Un fugitivo!¡Socorro!"
				+ "\nLa mala suerte te acompaña, y varios guardias se acercan y te apresan sin que puedas hacer nada."
				+ "\nEl mercader añade:"
				+ "\n\t-Lo siento, amigo, pero no puedo soportar a los salvajes como tú. Vosotros habéis destrozado la programación.";
		FaseImpl f13 = new FaseImpl(13, TipoFase.GAME_OVER, s131, null, null, null, null, 0);
		
		// Fase 14
		String s141 = "El mercader, amablemente te indica el camino:"
				+ "\n\t-Si sigues avanzando por éste camino, llegarás al reino de C."
				+ "\nTras agradecérselo, el mercader te regala una poción y te dice sonriendo:"
				+ "\n\t-Me has caído bien, creo que tienes futuro, y me gustaría que me recuerdes si llegas lejos.";
		String s142 = "Aceptar regalo y proseguir el camino.";
		String s143 = "Aceptar regalo y atacar al mercader.";
		String[] sa14 = new String[]{s142,s143};
		int[] o14 = new int[]{16,15};
		Pocion p14 = new Pocion("Poción pequeña", 50);
		FaseImpl f14 = new FaseImpl(14, TipoFase.OPCIONES_RECOMPENSA, s141, sa14, o14, null, p14, 0);					
		
		
		// Fase 15
		EnemigoImpl e15 = new EnemigoImpl("Mercader", 60, 10, 1);
		Pocion p15 = new Pocion("Poción mediana", 75);
		int o15[] = new int[]{16};
		FaseImpl f15 = new FaseImpl(15, TipoFase.COMBATE, null, null, o15, e15, p15, 0);

		
		FaseImpl[] fases = new FaseImpl[] {f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15};

		for (FaseImpl temp : fases) {
			gbd.escribeFaseBaseDeDatos(temp);
			System.out.println(temp);
		}

		
		gbd.desconectar();
	}

}
